package co.com.personalsoft.base.bd;

import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class ConfiguracionHelper {
   private BDHelper bdHelper;
   private boolean ejecucionPorQuerys;

   public ConfiguracionHelper() {
      this.bdHelper = null;
      String tipoRecurso = Configuracion.getInstance().getParametro("tipoRecursosBDFramework");
      this.ejecucionPorQuerys = tipoRecurso != null && tipoRecurso.equalsIgnoreCase("SQL");
   }

   public ConfiguracionHelper(BDHelper helper) {
      this();
      this.bdHelper = helper;
   }

   public HashMap<String, String> cargarConfiguracion() throws PersonalsoftException {
      String rutaQueryConfiguracion = Configuracion.getInstance().getParametro("queryConfiguracion");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ResultSet rs = null;
      HashMap<String, String> cargarConfiguracion = new HashMap();
      String codigo = "";
      String descripcion = "";
      if (rutaQueryConfiguracion != null && rutaQueryConfiguracion.length() > 0) {
         try {
            if (this.ejecucionPorQuerys) {
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryConfiguracion);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               cs = this.bdHelper.cargarCallableStatement(rutaQueryConfiguracion);
               if (cs != null) {
                  cs.execute();
                  rs = cs.getResultSet();
               }
            }

            if (rs != null) {
               while(rs.next()) {
                  codigo = rs.getString("CLAVE") != null ? rs.getString("CLAVE").trim() : "";
                  if (!codigo.equals("")) {
                     descripcion = rs.getString("VALOR") != null ? rs.getString("VALOR").trim() : "";
                     cargarConfiguracion.put(codigo, descripcion);
                  }
               }
            }
         } catch (Exception var16) {
            var16.printStackTrace();
            throw new PersonalsoftException(var16);
         } finally {
            try {
               BDHelper.close(ps);
               BDHelper.close(cs);
               BDHelper.close(rs);
               if (this.bdHelper != null) {
                  this.bdHelper.cerrarConexion();
               }
            } catch (Exception var15) {
               throw new PersonalsoftException(var15);
            }

         }
      }

      return cargarConfiguracion;
   }

   public String[] cargarParametrosConfiguracionBD() throws PersonalsoftException {
      String rutaQueryConfiguracion = Configuracion.getInstance().getParametro("queryConfiguracion");
      PreparedStatement ps = null;
      ResultSet rs = null;
      CallableStatement cs = null;
      String codigo = "";
      String descripcion = "";
      ArrayList<String> parametros = new ArrayList();
      if (rutaQueryConfiguracion != null && rutaQueryConfiguracion.length() > 0) {
         try {
            if (this.ejecucionPorQuerys) {
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryConfiguracion);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               cs = this.bdHelper.cargarCallableStatement(rutaQueryConfiguracion);
               if (cs != null) {
                  cs.execute();
                  rs = cs.getResultSet();
               }
            }

            if (rs != null) {
               while(rs.next()) {
                  codigo = rs.getString("CLAVE") != null ? rs.getString("CLAVE") : "";
                  descripcion = rs.getString("VALOR") != null ? rs.getString("VALOR") : "";
                  parametros.add(codigo.trim());
                  parametros.add(descripcion.trim());
               }
            }
         } catch (Exception var16) {
            throw new PersonalsoftException(var16);
         } finally {
            try {
               BDHelper.close(ps);
               BDHelper.close(cs);
               BDHelper.close(rs);
               if (this.bdHelper != null) {
                  this.bdHelper.cerrarConexion();
               }
            } catch (Exception var15) {
               throw new PersonalsoftException(var15);
            }

         }
      }

      return (String[])parametros.toArray(new String[parametros.size()]);
   }

   public String cargarParametroConfiguracionBD(String clave) throws PersonalsoftException {
      String rutaQueryConfiguracion = Configuracion.getInstance().getParametro("queryConfiguracionClave");
      PreparedStatement ps = null;
      ResultSet rs = null;
      CallableStatement cs = null;
      String valor = "";
      ArrayList<Parametro> parametros = new ArrayList();
      if (rutaQueryConfiguracion != null && rutaQueryConfiguracion.length() > 0) {
         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("CLAVE", 12, clave));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryConfiguracion, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro(12, clave, 1));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryConfiguracion, parametros);
               if (cs != null) {
                  cs.execute();
                  rs = cs.getResultSet();
               }
            }

            if (rs != null && rs.next()) {
               valor = rs.getString("VALOR") != null ? rs.getString("VALOR") : "";
            }
         } catch (Exception var15) {
            throw new PersonalsoftException(var15);
         } finally {
            try {
               BDHelper.close(ps);
               BDHelper.close(cs);
               BDHelper.close(rs);
               if (this.bdHelper != null) {
                  this.bdHelper.cerrarConexion();
               }
            } catch (Exception var16) {
               throw new PersonalsoftException(var16);
            }

         }
      }

      return valor;
   }

   public void ingresarParametroBD(String clave, String valor) throws PersonalsoftException {
      String rutaQueryConfiguracion = Configuracion.getInstance().getParametro("insertConfiguracion");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ArrayList parametros = new ArrayList();

      try {
         if (this.ejecucionPorQuerys) {
            parametros.add(new Parametro("CLAVE", 12, clave));
            parametros.add(new Parametro("VALOR", 12, valor));
            ps = this.bdHelper.cargarPreparedStatement(rutaQueryConfiguracion, parametros);
            ps.executeUpdate();
         } else {
            parametros.add(new Parametro(12, clave, 1));
            parametros.add(new Parametro(12, valor, 1));
            cs = this.bdHelper.cargarCallableStatement(rutaQueryConfiguracion, parametros);
            cs.execute();
         }
      } catch (Exception var15) {
         var15.printStackTrace();
         throw new PersonalsoftException(var15);
      } finally {
         try {
            BDHelper.close(ps);
            BDHelper.close(cs);
         } catch (Exception var14) {
            throw new PersonalsoftException(var14);
         }
      }

   }

   public void actualizarParametroBD(String clave, String valor) throws PersonalsoftException {
      String rutaQueryConfiguracion = Configuracion.getInstance().getParametro("updateConfiguracion");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ArrayList parametros = new ArrayList();

      try {
         if (this.ejecucionPorQuerys) {
            parametros.add(new Parametro("CLAVE", 12, clave));
            parametros.add(new Parametro("VALOR", 12, valor));
            ps = this.bdHelper.cargarPreparedStatement(rutaQueryConfiguracion, parametros);
            ps.executeUpdate();
         } else {
            parametros.add(new Parametro(12, clave, 1));
            parametros.add(new Parametro(12, valor, 1));
            cs = this.bdHelper.cargarCallableStatement(rutaQueryConfiguracion, parametros);
            cs.execute();
         }
      } catch (Exception var15) {
         var15.printStackTrace();
         throw new PersonalsoftException(var15);
      } finally {
         try {
            BDHelper.close(ps);
            BDHelper.close(cs);
         } catch (Exception var14) {
            throw new PersonalsoftException(var14);
         }
      }

   }

   public void borrarParametroBD(String clave) throws PersonalsoftException {
      String rutaQueryConfiguracion = Configuracion.getInstance().getParametro("deleteConfiguracion");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ArrayList parametros = new ArrayList();

      try {
         if (this.ejecucionPorQuerys) {
            parametros.add(new Parametro("CLAVE", 12, clave));
            ps = this.bdHelper.cargarPreparedStatement(rutaQueryConfiguracion, parametros);
            ps.executeUpdate();
         } else {
            parametros.add(new Parametro(12, clave, 1));
            cs = this.bdHelper.cargarCallableStatement(rutaQueryConfiguracion, parametros);
            cs.execute();
         }
      } catch (Exception var14) {
         var14.printStackTrace();
         throw new PersonalsoftException(var14);
      } finally {
         try {
            BDHelper.close(ps);
            BDHelper.close(cs);
         } catch (Exception var13) {
            throw new PersonalsoftException(var13);
         }
      }

   }
}
