package co.com.personalsoft.base.bd;

import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.beans.ParametroTareaDTO;
import co.com.personalsoft.base.beans.TareaDTO;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.util.Fecha;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

public class TareasConsultasHelper {
   private BDHelper bdHelper;
   private boolean ejecucionPorQuerys;

   public TareasConsultasHelper() {
      this.bdHelper = null;
      String tipoRecurso = Configuracion.getInstance().getParametro("tipoRecursosBDFramework");
      this.ejecucionPorQuerys = tipoRecurso != null && tipoRecurso.equals("SQL");
   }

   public TareasConsultasHelper(BDHelper helper) {
      this();
      this.bdHelper = helper;
   }

   public long cargarMaximaIdTarea() throws PersonalsoftException {
      String rutaQueryMensaje = Configuracion.getInstance().getParametro("maximoIdTarea");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ResultSet rs = null;
      long maximaTareaId = 1L;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         try {
            if (this.ejecucionPorQuerys) {
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje);
               if (cs != null) {
                  cs.execute();
                  rs = cs.getResultSet();
               }
            }

            if (rs != null && rs.next()) {
               maximaTareaId = (long)rs.getInt("IDTAREA");
            }
         } catch (Exception var15) {
            if (var15 instanceof PersonalsoftException) {
               throw (PersonalsoftException)var15;
            }

            throw new PersonalsoftException(var15);
         } finally {
            try {
               BDHelper.close(cs);
               BDHelper.close(ps);
               BDHelper.close(rs);
            } catch (SQLException var14) {
               new PersonalsoftException(var14);
            }

         }
      }

      return maximaTareaId;
   }

   public ArrayList<TareaDTO> cargarTareasPendientes() throws PersonalsoftException {
      String rutaQueryMensaje = Configuracion.getInstance().getParametro("cargarTareasPendientes");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ResultSet rs = null;
      ArrayList<TareaDTO> tareas = new ArrayList();
      TareaDTO tarea = null;
      ParametroTareaDTO parametroTareaDTO = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         try {
            if (this.ejecucionPorQuerys) {
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje);
               if (cs != null) {
                  cs.execute();
                  rs = cs.getResultSet();
               }
            }

            if (rs != null) {
               for(; rs.next(); tareas.add(tarea)) {
                  tarea = new TareaDTO();
                  tarea = this.configurarTarea(rs, tarea);
                  parametroTareaDTO = this.cargarParametrosTarea(rs.getInt("IDTAREA"));
                  if (parametroTareaDTO != null) {
                     if (parametroTareaDTO.getParametrosEjecucion().size() > 0) {
                        tarea.setParametrosEjecucion(parametroTareaDTO.getParametrosEjecucion().toArray(new Object[parametroTareaDTO.getParametrosEjecucion().size()]));
                     }

                     if (parametroTareaDTO.getParametrosError().size() > 0) {
                        tarea.setParametrosEjecucionError(parametroTareaDTO.getParametrosError().toArray(new Object[parametroTareaDTO.getParametrosError().size()]));
                     }
                  }
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

   private TareaDTO configurarTarea(ResultSet rs, TareaDTO tarea) throws SQLException {
      tarea.setIdTarea(rs.getString("IDTAREA"));
      tarea.setNombre(rs.getString("DSNOMBRE_TAREA").trim());
      tarea.setNombreClase(rs.getString("DSNOMBRE_CLASE").trim());
      tarea.setMetodoEjecucion(rs.getString("DSMETODO_EJECUCION").trim());
      tarea.setMetodoEjecucionCasoError(rs.getString("DSMETODO_ERROR") != null ? rs.getString("DSMETODO_ERROR") : "");
      tarea.setIntervaloEjucionHoras(rs.getString("NMINTERVALO").trim());
      tarea.setUnidadTiempo(rs.getString("DSUNIDAD_TIEMPO").trim());
      tarea.setFechaEjecucion(rs.getString("FEEJECUCION"));
      tarea.setTareaPersistente("S");
      return tarea;
   }

   public ArrayList<TareaDTO> cargarTareasProgramadas() throws PersonalsoftException {
      String rutaQueryMensaje = Configuracion.getInstance().getParametro("cargarTareasProgramadas");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ResultSet rs = null;
      ArrayList<TareaDTO> tareas = new ArrayList();
      TareaDTO tarea = null;
      ParametroTareaDTO parametroTareaDTO = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         try {
            if (this.ejecucionPorQuerys) {
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje);
               if (cs != null) {
                  cs.execute();
                  rs = cs.getResultSet();
               }
            }

            if (rs != null) {
               for(; rs.next(); tareas.add(tarea)) {
                  new TareaDTO();
                  tarea = this.configurarTarea(rs);
                  parametroTareaDTO = this.cargarParametrosTarea(rs.getInt("IDTAREA"));
                  if (parametroTareaDTO != null) {
                     if (parametroTareaDTO.getParametrosEjecucion().size() > 0) {
                        tarea.setParametrosEjecucion(parametroTareaDTO.getParametrosEjecucion().toArray(new Object[parametroTareaDTO.getParametrosEjecucion().size()]));
                     }

                     if (parametroTareaDTO.getParametrosError().size() > 0) {
                        tarea.setParametrosEjecucionError(parametroTareaDTO.getParametrosError().toArray(new Object[parametroTareaDTO.getParametrosError().size()]));
                     }
                  }
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

   private TareaDTO configurarTarea(ResultSet rs) throws SQLException {
      TareaDTO tarea = new TareaDTO();
      tarea.setIdTarea(rs.getString("IDTAREA"));
      tarea.setNombre(rs.getString("DSNOMBRE_TAREA").trim());
      tarea.setNombreClase(rs.getString("DSNOMBRE_CLASE").trim());
      tarea.setMetodoEjecucion(rs.getString("DSMETODO_EJECUCION").trim());
      tarea.setMetodoEjecucionCasoError(rs.getString("DSMETODO_ERROR") != null ? rs.getString("DSMETODO_ERROR") : "");
      tarea.setIntervaloEjecucion(rs.getInt("NMINTERVALO"));
      tarea.setUnidadTiempo(rs.getString("DSUNIDAD_TIEMPO").trim());
      if (rs.getString("FEEJECUCION") != null) {
         Calendar calendar = GregorianCalendar.getInstance();
         calendar.setTime(Fecha.cambiarTipoDatoDate(rs.getString("FEEJECUCION"), "yyyy-MM-dd HH:mm:ss"));
         tarea.setFecha(calendar);
      }

      tarea.setFechaProgramacion(rs.getString("FEPROGRAMACION").trim());
      tarea.setTareaPendiente(rs.getString("SNTAREA_PENDIENTE"));
      tarea.setTareaPersistente("S");
      return tarea;
   }
}
