package co.com.personalsoft.base.bd;

import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.beans.Sentencia;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.util.OperadorArchivos;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.log4j.Logger;

public class BDHelper {
   private Connection conn;
   private static Logger log = Logger.getLogger(BDHelper.class);
   private String dbProductName = "";
   private String indicadorParametro = ">>";

   public BDHelper() throws PersonalsoftException {
      try {
         this.conn = AdminBD.getInstance().obtenerConexionJNDI();
         if (this.conn != null) {
            this.conn.setAutoCommit(false);
         }

      } catch (PersonalsoftException var3) {
         log.error(var3.getTraza());
         throw var3;
      } catch (SQLException var4) {
         PersonalsoftException personal = new PersonalsoftException(var4);
         log.error(personal.getTraza());
         throw personal;
      }
   }

   public BDHelper(String jndi) throws PersonalsoftException {
      try {
         this.conn = AdminBD.getInstance().obtenerConexionJNDI(jndi);
      } catch (PersonalsoftException var3) {
         log.error(var3.getTraza());
         throw var3;
      }
   }

   public BDHelper(String driver, String url, String usuario, String clave) throws PersonalsoftException {
      try {
         this.conn = AdminBD.getInstance().obtenerConexion(driver, url, usuario, clave);
      } catch (PersonalsoftException var6) {
         log.error(var6.getTraza());
         throw var6;
      }
   }

   public BDHelper(Connection conn) {
      this.conn = conn;
   }

   public Connection getConn() {
      return this.conn;
   }

   public void setConn(Connection conn) {
      this.conn = conn;
   }

   public String getIndicadorParametro() {
      return this.indicadorParametro;
   }

   public void setIndicadorParametro(String indicadorParametro) {
      this.indicadorParametro = indicadorParametro;
   }

   public PreparedStatement cargarPreparedStatement(StringBuffer query) throws SQLException {
      return this.cargarPreparedStatement((StringBuffer)query, (ArrayList)null);
   }

   public PreparedStatement cargarPreparedStatement(StringBuffer query, ArrayList parametros) throws SQLException {
      PreparedStatement psmt = null;
      Sentencia sentencia = new Sentencia();
      sentencia.setRutaRecurso("");
      sentencia.setSentenciaOrigen(query.toString());
      sentencia.setParametros(parametros);
      sentencia = this.prepararSentencia(sentencia);
      psmt = this.conn.prepareStatement(sentencia.getSentenciaPsmt().replaceAll("\n", " "));
      if (sentencia != null && sentencia.getParametros() != null && sentencia.getParametros().size() > 0) {
         psmt = this.cargarParametrosPsmt(psmt, sentencia);
      }

      return psmt;
   }

   public PreparedStatement cargarPreparedStatement(String rutaArchivo, ArrayList parametros) throws SQLException {
      PreparedStatement psmt = null;
      Sentencia sentencia = new Sentencia();
      sentencia.setRutaRecurso(rutaArchivo);
      InputStream inputStr = null;
      String claveQuery = Configuracion.getInstance().getQuery(rutaArchivo);
      if (claveQuery == null) {
         inputStr = Configuracion.getInstance().getContext().getResourceAsStream(rutaArchivo);

         try {
            claveQuery = OperadorArchivos.cargarArchivo(inputStr);
         } catch (IOException var8) {
            log.error(var8);
         }

         Configuracion.getInstance().putQuery(rutaArchivo, claveQuery);
      }

      sentencia.setSentenciaOrigen(claveQuery);
      sentencia.setParametros(parametros);
      sentencia = this.prepararSentencia(sentencia);
      psmt = this.conn.prepareStatement(sentencia.getSentenciaPsmt().replaceAll("\n", " "));
      if (sentencia != null && sentencia.getParametros() != null && sentencia.getParametros().size() > 0) {
         psmt = this.cargarParametrosPsmt(psmt, sentencia);
      }

      return psmt;
   }

   public CallableStatement cargarCallableStatement(String nombreProcedimiento, ArrayList parametros) throws SQLException {
      CallableStatement csmt = null;
      Sentencia sentencia = new Sentencia();
      sentencia.setNombreProcedimiento(nombreProcedimiento);
      sentencia.setParametros(parametros);
      sentencia = this.prepararSentenciaCallable(sentencia);
      csmt = this.conn.prepareCall(sentencia.getInvocacionProcedimiento());
      if (parametros != null && !parametros.isEmpty()) {
         csmt = this.cargarParametrosCsmt(csmt, sentencia);
      }

      return csmt;
   }

   public CallableStatement cargarCallableStatement(String nombreProcedimiento) throws SQLException {
      CallableStatement csmt = this.cargarCallableStatement(nombreProcedimiento, (ArrayList)null);
      return csmt;
   }

   public PreparedStatement cargarPreparedStatement(String rutaArchivo) throws SQLException {
      return this.cargarPreparedStatement((String)rutaArchivo, (ArrayList)null);
   }

   private Sentencia prepararSentenciaCallable(Sentencia sentencia) throws SQLException {
      Parametro paramDebug = null;
      String cadenaParamDebug = "";
      String cadenaInvocacion = "{call " + sentencia.getNombreProcedimiento() + "(";
      int j;
      if (sentencia.getParametros() != null && sentencia.getParametros().size() > 0) {
         for(j = 0; j < sentencia.getParametros().size(); ++j) {
            cadenaInvocacion = cadenaInvocacion + "?,";
         }

         cadenaInvocacion = cadenaInvocacion.substring(0, cadenaInvocacion.length() - 1);
      }

      cadenaInvocacion = cadenaInvocacion + ")}";
      sentencia.setInvocacionProcedimiento(cadenaInvocacion);
      if (log.isDebugEnabled() && sentencia.getParametros() != null && sentencia.getParametros().size() > 0) {
         for(j = 0; j < sentencia.getParametros().size(); ++j) {
            paramDebug = (Parametro)sentencia.getParametros().get(j);
            cadenaParamDebug = cadenaParamDebug + "[ Tipo: " + paramDebug.getTipoParametro() + " Valor: " + (paramDebug.getValorParametro() != null ? paramDebug.getValorParametro().toString() : "null") + " TipoES: " + paramDebug.getTipoES() + "]\n";
         }

         log.debug("Parametros ejecución de la sentencia  [" + cadenaParamDebug.toString() + "]");
      }

      return sentencia;
   }

   private Sentencia prepararSentencia(Sentencia sentencia) throws SQLException {
      Parametro param = null;
      Parametro paramTmp = null;
      Parametro paramDebug = null;
      int posParam = 0;
      int posParamAlmanena = false;
      int tamanoColeccionOriginal = false;
      boolean parametroEncontrado = false;
      String mensaje = "";
      StringBuffer cadenaParamDebug = new StringBuffer();
      sentencia.setSentenciaPsmt(sentencia.getSentenciaOrigen());
      int j;
      if (sentencia.getParametros() != null && sentencia.getParametros().size() > 0) {
         int tamanoColeccionOriginal = sentencia.getParametros().size();

         for(j = 0; j < tamanoColeccionOriginal; ++j) {
            param = (Parametro)sentencia.getParametros().get(j);

            while(true) {
               while(posParam != -1) {
                  posParam = sentencia.getSentenciaOrigen().indexOf(this.indicadorParametro + param.getNombreParametro(), posParam + 1);
                  int posParamAlmanena = posParam;
                  if (posParam != -1) {
                     posParamAlmanena = posParam + posParam + 1;
                  }

                  if (parametroEncontrado && posParam != -1) {
                     paramTmp = (Parametro)param.clone();
                     paramTmp.setIndice(posParamAlmanena);
                     sentencia.getParametros().add(paramTmp);
                  } else if (posParam != -1) {
                     param.setIndice(posParamAlmanena);
                     parametroEncontrado = true;
                  }
               }

               parametroEncontrado = false;
               posParam = 0;
               posParamAlmanena = false;
               sentencia.setSentenciaPsmt(sentencia.getSentenciaPsmt().replaceAll(this.indicadorParametro + param.getNombreParametro(), "?"));
               break;
            }
         }
      }

      mensaje = this.validarSentenciaFinal(sentencia);
      if (!mensaje.equals("")) {
         throw new SQLException(mensaje);
      } else {
         sentencia = this.ordenarParametros(sentencia);
         if (log.isDebugEnabled() && sentencia != null && sentencia.getParametros() != null && sentencia.getParametros().size() > 0) {
            for(j = 0; j < sentencia.getParametros().size(); ++j) {
               paramDebug = (Parametro)sentencia.getParametros().get(j);
               cadenaParamDebug.append("[Parametro: " + (paramDebug != null ? paramDebug.getNombreParametro() : "null") + " Tipo: " + (paramDebug != null ? paramDebug.getTipoParametro() : "null") + " Valor: " + (paramDebug != null ? paramDebug.getValorParametro().toString() : "null") + "]\n");
            }

            log.debug("Parametros ejecución de la sentencia [" + cadenaParamDebug.toString() + "]");
         }

         return sentencia;
      }
   }

   private String validarSentenciaFinal(Sentencia sentencia) {
      StringBuffer parametrosFaltantes = new StringBuffer();
      String nombreParametro = "";
      String mensaje = "";
      int posParam = 0;
      int posFinNombreEnter = false;
      int posFinNombreEspacio = false;
      boolean var8 = false;

      while(true) {
         do {
            if (posParam == -1) {
               if (!parametrosFaltantes.toString().equals("")) {
                  mensaje = "Los siguientes parámetros no fueron enviados: " + parametrosFaltantes.toString();
               }

               return mensaje;
            }

            posParam = sentencia.getSentenciaPsmt().indexOf(this.indicadorParametro, posParam + 1);
         } while(posParam == -1);

         int posFinNombreEnter = sentencia.getSentenciaPsmt().indexOf("\n", posParam);
         int posFinNombreEspacio = sentencia.getSentenciaPsmt().indexOf(" ", posParam);
         int posFinNombre;
         if (posFinNombreEnter != -1 && posFinNombreEspacio != -1) {
            posFinNombre = posFinNombreEnter < posFinNombreEspacio ? posFinNombreEnter : posFinNombreEspacio;
         } else if (posFinNombreEnter != -1) {
            posFinNombre = posFinNombreEnter;
         } else if (posFinNombreEspacio != -1) {
            posFinNombre = posFinNombreEspacio;
         } else {
            posFinNombre = sentencia.getSentenciaPsmt().length() - 1;
         }

         nombreParametro = sentencia.getSentenciaPsmt().substring(posParam, posFinNombre);
         parametrosFaltantes.append(nombreParametro);
         parametrosFaltantes.append(" ");
      }
   }

   private Sentencia ordenarParametros(Sentencia sentencia) {
      Parametro paramTmp = null;
      if (sentencia != null && sentencia.getParametros() != null && sentencia.getParametros().size() > 0) {
         for(int i = 0; i < sentencia.getParametros().size(); ++i) {
            for(int j = i; j < sentencia.getParametros().size(); ++j) {
               if (((Parametro)sentencia.getParametros().get(i)).getIndice() > ((Parametro)sentencia.getParametros().get(j)).getIndice()) {
                  paramTmp = (Parametro)sentencia.getParametros().get(i);
                  sentencia.getParametros().set(i, (Parametro)sentencia.getParametros().get(j));
                  sentencia.getParametros().set(j, paramTmp);
               }
            }
         }
      }

      return sentencia;
   }

   private PreparedStatement cargarParametrosPsmt(PreparedStatement psmt, Sentencia sentencia) throws SQLException {
      BDConfigureStatementHelper helper = new BDConfigureStatementHelper();
      return helper.cargarParametrosPsmt(psmt, sentencia);
   }

   private CallableStatement cargarParametrosCsmt(CallableStatement csmt, Sentencia sentencia) throws SQLException {
      BDConfigureStatementHelper helper = new BDConfigureStatementHelper();
      return helper.cargarParametrosCsmt(csmt, sentencia);
   }

   public void commitTransaction() throws SQLException {
      try {
         if (this.conn != null && !this.conn.isClosed()) {
            this.conn.commit();
         }

      } catch (SQLException var2) {
         throw var2;
      }
   }

   public void rollbackTransaction() throws SQLException {
      try {
         if (this.conn != null && !this.conn.isClosed()) {
            this.conn.rollback();
         }

      } catch (SQLException var2) {
         throw var2;
      }
   }

   public static void close(Object object) throws SQLException {
      try {
         if (object instanceof Connection) {
            Connection connection = (Connection)object;
            if (connection != null && !connection.isClosed()) {
               connection.close();
            }
         } else if (object instanceof ResultSet) {
            ResultSet resultSet = (ResultSet)object;
            if (resultSet != null) {
               resultSet.close();
            }
         } else if (object instanceof Statement) {
            Statement statement = (Statement)object;
            if (statement != null) {
               statement.close();
            }
         } else if (object instanceof PreparedStatement) {
            PreparedStatement preparedStatement = (PreparedStatement)object;
            if (preparedStatement != null) {
               preparedStatement.close();
            }
         } else if (object instanceof CallableStatement) {
            CallableStatement callableStatement = (CallableStatement)object;
            if (callableStatement != null) {
               callableStatement.close();
            }
         }

      } catch (SQLException var2) {
         throw var2;
      }
   }

   public void cerrarConexion() throws SQLException {
      try {
         if (this.conn != null && !this.conn.isClosed()) {
            this.conn.close();
         }

      } catch (SQLException var2) {
         throw var2;
      }
   }

   public boolean isMySql() {
      if (this.dbProductName.equals("")) {
         try {
            DatabaseMetaData dbmd = this.conn.getMetaData();
            this.dbProductName = dbmd.getDatabaseProductName();
         } catch (Exception var2) {
            this.dbProductName = "";
         }
      }

      return this.dbProductName.contains("MySQL");
   }

   public String getDbProductName() {
      return this.dbProductName;
   }

   public void setDbProductName(String dbProductName) {
      this.dbProductName = dbProductName;
   }
}
