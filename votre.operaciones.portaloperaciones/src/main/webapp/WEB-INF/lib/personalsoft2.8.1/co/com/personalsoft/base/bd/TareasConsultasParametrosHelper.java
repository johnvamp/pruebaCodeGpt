package co.com.personalsoft.base.bd;

import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.beans.ParametroTareaDTO;
import co.com.personalsoft.base.beans.TareaDTO;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class TareasConsultasParametrosHelper {
   private BDHelper bdHelper;
   private boolean ejecucionPorQuerys;

   public TareasConsultasParametrosHelper() {
      this.bdHelper = null;
      String tipoRecurso = Configuracion.getInstance().getParametro("tipoRecursosBDFramework");
      this.ejecucionPorQuerys = tipoRecurso != null && tipoRecurso.equals("SQL");
   }

   public TareasConsultasParametrosHelper(BDHelper helper) {
      this();
      this.bdHelper = helper;
   }

   private ParametroTareaDTO cargarParametrosTarea(int idTarea) throws PersonalsoftException {
      String rutaQueryMensaje = Configuracion.getInstance().getParametro("cargarParametrosTareas");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ResultSet rs = null;
      Collection<Object> parametrosError = new ArrayList();
      Collection<Object> parametrosExito = new ArrayList();
      ArrayList<Parametro> parametros = new ArrayList();
      ParametroTareaDTO parametroTareaDTO = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDTAREA", 4, new Integer(idTarea)));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro("IDTAREA", 4, new Integer(idTarea), 1));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  rs = cs.getResultSet();
               }
            }

            if (rs != null) {
               parametroTareaDTO = new ParametroTareaDTO();

               while(rs.next()) {
                  String snParamExito = rs.getString("SNPARAM_EXITO");
                  if (snParamExito.equalsIgnoreCase("E")) {
                     parametrosExito.add(rs.getString("DSVALOR").trim() + "Æ" + rs.getString("DSTIPO_PARAM").trim());
                  } else if (snParamExito.equalsIgnoreCase("F")) {
                     parametrosError.add(rs.getString("DSVALOR").trim() + "Æ" + rs.getString("DSTIPO_PARAM").trim());
                  }
               }

               parametroTareaDTO.setParametrosEjecucion(parametrosExito);
               parametroTareaDTO.setParametrosError(parametrosError);
            }
         } catch (Exception var18) {
            if (var18 instanceof PersonalsoftException) {
               throw (PersonalsoftException)var18;
            }

            throw new PersonalsoftException(var18);
         } finally {
            try {
               BDHelper.close(cs);
               BDHelper.close(ps);
               BDHelper.close(rs);
            } catch (SQLException var17) {
               new PersonalsoftException(var17);
            }

         }
      }

      return parametroTareaDTO;
   }

   public String cargarTareaProgramada(String idTarea) throws PersonalsoftException {
      String rutaQueryMensaje = Configuracion.getInstance().getParametro("cargarTareaProgramada");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ResultSet rs = null;
      String tarea = null;
      ParametroTareaDTO parametroTareaDTO = null;
      ArrayList<Parametro> parametros = new ArrayList();
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDTAREA", 2, new Integer(idTarea)));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro("IDTAREA", 4, new Integer(idTarea), 1));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  rs = cs.getResultSet();
               }
            }

            if (rs != null && rs.next()) {
               tarea = this.configurarEncabezadoTarea(rs);
               parametroTareaDTO = this.cargarParametrosTarea(rs.getInt("IDTAREA"));
               if (parametroTareaDTO != null) {
                  tarea = tarea + this.configurarParametrosTarea(parametroTareaDTO);
               }

               tarea = tarea + "Æ" + this.cargarResultadosTarea(rs.getInt("IDTAREA"));
            }
         } catch (Exception var17) {
            if (var17 instanceof PersonalsoftException) {
               throw (PersonalsoftException)var17;
            }

            throw new PersonalsoftException(var17);
         } finally {
            try {
               BDHelper.close(cs);
               BDHelper.close(ps);
               BDHelper.close(rs);
            } catch (SQLException var16) {
               new PersonalsoftException(var16);
            }

         }
      }

      return tarea;
   }

   private String configurarEncabezadoTarea(ResultSet rs) throws SQLException {
      StringBuilder str = new StringBuilder();
      String metodoError = " ";
      String fechaEjecucion = " ";
      if (rs.getString("DSMETODO_ERROR") != null) {
         metodoError = rs.getString("DSMETODO_ERROR").trim();
         if (metodoError.equals("")) {
            metodoError = " ";
         }
      }

      if (rs.getString("FEEJECUCION") != null) {
         fechaEjecucion = rs.getString("FEEJECUCION").trim();
         if (fechaEjecucion.equals("")) {
            fechaEjecucion = " ";
         }
      }

      str.append(rs.getString("IDTAREA") + "Æ");
      str.append(rs.getString("DSNOMBRE_TAREA").trim() + "Æ");
      str.append(rs.getString("DSNOMBRE_CLASE").trim() + "Æ");
      str.append(rs.getString("DSMETODO_EJECUCION").trim() + "Æ");
      str.append(metodoError + "Æ");
      str.append(rs.getInt("NMINTERVALO") + "Æ");
      str.append(rs.getString("DSUNIDAD_TIEMPO").trim() + "Æ");
      str.append(fechaEjecucion + "Æ");
      str.append("S");
      return str.toString();
   }

   private String configurarEncabezado(ResultSet rs) throws SQLException {
      StringBuilder str = new StringBuilder();
      String metodoError = " ";
      String fechaEjecucion = " ";
      String unidadTiempo = rs.getInt("NMINTERVALO") + " " + rs.getString("DSUNIDAD_TIEMPO").trim();
      String estado = " ";
      if (rs.getString("DSMETODO_ERROR") != null) {
         metodoError = rs.getString("DSMETODO_ERROR").trim();
         if (metodoError.equals("")) {
            metodoError = " ";
         }
      }

      if (rs.getString("FEPROGRAMACION") != null) {
         fechaEjecucion = rs.getString("FEPROGRAMACION").trim();
         if (fechaEjecucion.equals("")) {
            fechaEjecucion = " ";
         }
      }

      if (rs.getString("SNTAREA_PENDIENTE").equalsIgnoreCase("N")) {
         estado = "Terminada";
      } else {
         estado = "Pendiente";
      }

      str.append(rs.getString("IDTAREA") + "Æ");
      str.append(rs.getString("DSNOMBRE_TAREA").trim() + "Æ");
      str.append(unidadTiempo + "Æ");
      str.append(estado + "Æ");
      str.append(fechaEjecucion + "Æ");
      str.append("S");
      return str.toString();
   }

   private String configurarParametrosTarea(ParametroTareaDTO parametroTareaDTO) {
      String str = "";
      String valor;
      String[] valores;
      Object object;
      Iterator var6;
      if (parametroTareaDTO.getParametrosEjecucion() != null && !parametroTareaDTO.getParametrosEjecucion().isEmpty()) {
         valor = "";
         valores = (String[])null;

         for(var6 = parametroTareaDTO.getParametrosEjecucion().iterator(); var6.hasNext(); str = str + valor) {
            object = var6.next();
            valores = ((String)object).split("Æ");
            valor = "Æ" + valores[1] + "," + valores[0];
         }
      }

      if (parametroTareaDTO.getParametrosError() != null && !parametroTareaDTO.getParametrosError().isEmpty()) {
         valor = "";
         valores = (String[])null;

         for(var6 = parametroTareaDTO.getParametrosError().iterator(); var6.hasNext(); str = str + valor) {
            object = var6.next();
            valores = ((String)object).split("Æ");
            valor = "Æ" + valores[1] + ";" + valores[0];
         }
      }

      return str;
   }

   public ArrayList<String> cargarTareasProgramadas(TareaDTO tareaDTO) throws PersonalsoftException {
      String rutaQueryMensaje = Configuracion.getInstance().getParametro("cargarTareasProgramadasParametros");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ResultSet rs = null;
      ArrayList<String> tareas = new ArrayList();
      ArrayList<Parametro> parametros = new ArrayList();
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDTAREA", 12, tareaDTO.getIdTarea()));
               parametros.add(new Parametro("FEEJECUCION_INICIO", 12, tareaDTO.getFechaEjecucionInicial()));
               parametros.add(new Parametro("FEEJECUCION_FIN", 12, tareaDTO.getFechaEjecucionFinal()));
               parametros.add(new Parametro("DSNOMBRE_TAREA", 12, tareaDTO.getNombre()));
               parametros.add(new Parametro("SNTAREA_PENDIENTE", 12, tareaDTO.getEstado()));
               parametros.add(new Parametro("FEEJECUCION_CONTROL", 12, tareaDTO.getFechaEjecucion()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro("IDTAREA", 12, tareaDTO.getIdTarea(), 1));
               parametros.add(new Parametro("FEEJECUCION_INICIO", 12, tareaDTO.getFechaEjecucionInicial(), 1));
               parametros.add(new Parametro("FEEJECUCION_FIN", 12, tareaDTO.getFechaEjecucionFinal(), 1));
               parametros.add(new Parametro("DSNOMBRE_TAREA", 12, tareaDTO.getNombre(), 1));
               parametros.add(new Parametro("SNTAREA_PENDIENTE", 12, tareaDTO.getEstado(), 1));
               parametros.add(new Parametro("FEEJECUCION_CONTROL", 12, tareaDTO.getFechaEjecucion(), 1));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  rs = cs.getResultSet();
               }
            }

            if (rs != null) {
               while(rs.next()) {
                  tareas.add(this.configurarEncabezado(rs));
               }
            }
         } catch (Exception var16) {
            if (var16 instanceof PersonalsoftException) {
               throw (PersonalsoftException)var16;
            }

            throw new PersonalsoftException(var16);
         } finally {
            try {
               BDHelper.close(cs);
               BDHelper.close(ps);
               BDHelper.close(rs);
            } catch (SQLException var15) {
               new PersonalsoftException(var15);
            }

         }
      }

      return tareas;
   }

   public String cargarResultadosTarea(int idTarea) throws PersonalsoftException {
      String rutaQueryMensaje = Configuracion.getInstance().getParametro("cargarResultadosTarea");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = new ArrayList();
      String str = "";
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDTAREA", 4, new Integer(idTarea)));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro("IDTAREA", 4, new Integer(idTarea), 1));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  rs = cs.getResultSet();
               }
            }

            if (rs != null) {
               while(rs.next()) {
                  str = str + this.configurarResultadosTarea(rs);
               }
            }
         } catch (Exception var16) {
            if (var16 instanceof PersonalsoftException) {
               throw (PersonalsoftException)var16;
            }

            throw new PersonalsoftException(var16);
         } finally {
            try {
               BDHelper.close(cs);
               BDHelper.close(ps);
               BDHelper.close(rs);
            } catch (SQLException var15) {
               new PersonalsoftException(var15);
            }

         }
      }

      return str;
   }

   private String configurarResultadosTarea(ResultSet rs) throws SQLException {
      StringBuilder str = new StringBuilder();
      String fechaResultado = " ";
      String estado = " ";
      String resultado = " ";
      String metodoEjecuta = " - ";
      if (rs.getString("DSESTADO_FIN") != null) {
         estado = rs.getString("DSESTADO_FIN").trim();
         if (estado.equals("")) {
            estado = " ";
         } else if (estado.equalsIgnoreCase("E")) {
            estado = "Éxito";
         } else {
            estado = "Fracaso";
         }
      }

      if (rs.getString("FERESULTADO") != null) {
         fechaResultado = rs.getString("FERESULTADO").trim();
         if (fechaResultado.equals("")) {
            fechaResultado = " ";
         }
      }

      str.append("Æ" + estado + "€");
      str.append(fechaResultado + "€");
      if (rs.getString("DSRESULTADO") != null) {
         resultado = rs.getString("DSRESULTADO").trim();
         if (resultado.equals("")) {
            resultado = " - ";
         }
      }

      if (rs.getString("DSMETODO_EJECUTA") != null) {
         metodoEjecuta = rs.getString("DSMETODO_EJECUTA").trim();
         if (metodoEjecuta.equals("")) {
            metodoEjecuta = " - ";
         }
      }

      str.append(resultado + "€" + metodoEjecuta);
      return str.toString();
   }
}
