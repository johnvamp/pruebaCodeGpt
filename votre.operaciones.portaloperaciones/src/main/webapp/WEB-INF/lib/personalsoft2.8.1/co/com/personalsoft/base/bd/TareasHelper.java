package co.com.personalsoft.base.bd;

import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.beans.ParametroTareaDTO;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.programacion.tareas.Tarea;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class TareasHelper {
   private BDHelper bdHelper;
   private boolean ejecucionPorQuerys;

   public TareasHelper() {
      this.bdHelper = null;
      String tipoRecurso = Configuracion.getInstance().getParametro("tipoRecursosBDFramework");
      this.ejecucionPorQuerys = tipoRecurso != null && tipoRecurso.equals("SQL");
   }

   public TareasHelper(BDHelper helper) {
      this();
      this.bdHelper = helper;
   }

   public int ingresarTarea(Tarea tarea, String fechaProgramacion, String fechaEjecucion, String unidadTiempo, int intervalo) throws PersonalsoftException {
      String rutaQueryMensaje = Configuracion.getInstance().getParametro("adicionarTarea");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ArrayList<Parametro> parametros = new ArrayList();
      int adicionado = 0;
      String estado = "";
      if (tarea.isTareaTerminada() && tarea.isTareaTerminadaConExito()) {
         estado = "N";
      } else if (tarea.isTareaEnProceso()) {
         estado = "S";
      } else if (tarea.isTareaTerminada() && !tarea.isTareaTerminadaConExito()) {
         estado = "S";
      }

      if (!tarea.isTareaTerminada()) {
         estado = "S";
      }

      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDTAREA", 2, new Integer(tarea.getIdTarea())));
               parametros.add(new Parametro("DSNOMBRE_TAREA", 12, tarea.getName()));
               parametros.add(new Parametro("DSNOMBRE_CLASE", 12, tarea.getProcesoProgramado().getClass().getName()));
               parametros.add(new Parametro("DSMETODO_EJECUCION", 12, tarea.getMetodoEjecucion().getName()));
               parametros.add(new Parametro("DSMETODO_ERROR", 12, tarea.getMetodoEjecucionCasoError() != null ? tarea.getMetodoEjecucionCasoError().getName() : ""));
               parametros.add(new Parametro("DSUNIDAD_TIEMPO", 12, unidadTiempo));
               parametros.add(new Parametro("NMINTERVALO", 4, intervalo));
               parametros.add(new Parametro("FEPROGRAMACION", 12, fechaProgramacion));
               parametros.add(new Parametro("SNTAREA_PENDIENTE", 12, estado));
               parametros.add(new Parametro("FEEJECUCION", 12, fechaEjecucion));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  adicionado = ps.executeUpdate();
               }
            } else {
               parametros.add(new Parametro("IDTAREA", 4, new Integer(tarea.getIdTarea()), 1));
               parametros.add(new Parametro("DSNOMBRE_TAREA", 12, tarea.getName(), 1));
               parametros.add(new Parametro("DSNOMBRE_CLASE", 12, tarea.getProcesoProgramado().getClass().getName(), 1));
               parametros.add(new Parametro("DSMETODO_EJECUCION", 12, tarea.getMetodoEjecucion().getName(), 1));
               parametros.add(new Parametro("DSMETODO_ERROR", 12, tarea.getMetodoEjecucionCasoError() != null ? tarea.getMetodoEjecucionCasoError().getName() : "", 1));
               parametros.add(new Parametro("DSUNIDAD_TIEMPO", 12, unidadTiempo, 1));
               parametros.add(new Parametro("NMINTERVALO", 4, new Integer(intervalo), 1));
               parametros.add(new Parametro("FEPROGRAMACION", 12, fechaProgramacion, 1));
               parametros.add(new Parametro("SNTAREA_PENDIENTE", 12, estado, 1));
               parametros.add(new Parametro("FEEJECUCION", 12, fechaEjecucion, 1));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  adicionado = 1;
               }
            }
         } catch (Exception var20) {
            if (var20 instanceof PersonalsoftException) {
               throw (PersonalsoftException)var20;
            }

            throw new PersonalsoftException(var20);
         } finally {
            try {
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var19) {
               new PersonalsoftException(var19);
            }

         }
      }

      return adicionado;
   }

   public int actualizarTarea(Tarea tarea, String fechaProgramacion, String fechaEjecucion, String unidadTiempo, int intervalo) throws PersonalsoftException {
      String rutaQueryMensaje = Configuracion.getInstance().getParametro("actualizarTarea");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ArrayList<Parametro> parametros = new ArrayList();
      int adicionado = 0;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDTAREA", 2, new Integer(tarea.getIdTarea())));
               parametros.add(new Parametro("DSNOMBRE_TAREA", 12, tarea.getName()));
               parametros.add(new Parametro("DSNOMBRE_CLASE", 12, tarea.getProcesoProgramado().getClass().getName()));
               parametros.add(new Parametro("DSMETODO_EJECUCION", 12, tarea.getMetodoEjecucion().getName()));
               parametros.add(new Parametro("DSMETODO_ERROR", 12, tarea.getMetodoEjecucionCasoError() != null ? tarea.getMetodoEjecucionCasoError().getName() : ""));
               parametros.add(new Parametro("DSUNIDAD_TIEMPO", 12, unidadTiempo));
               parametros.add(new Parametro("NMINTERVALO", 4, intervalo));
               parametros.add(new Parametro("FEPROGRAMACION", 12, fechaProgramacion));
               parametros.add(new Parametro("SNTAREA_PENDIENTE", 12, tarea.isTareaTerminadaConExito() && !tarea.isTareaRecurrente() ? "N" : "S"));
               parametros.add(new Parametro("FEEJECUCION", 12, fechaEjecucion));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  adicionado = ps.executeUpdate();
               }
            } else {
               parametros.add(new Parametro("IDTAREA", 4, new Integer(tarea.getIdTarea()), 1));
               parametros.add(new Parametro("DSNOMBRE_TAREA", 12, tarea.getName(), 1));
               parametros.add(new Parametro("DSNOMBRE_CLASE", 12, tarea.getProcesoProgramado().getClass().getName(), 1));
               parametros.add(new Parametro("DSMETODO_EJECUCION", 12, tarea.getMetodoEjecucion().getName(), 1));
               parametros.add(new Parametro("DSMETODO_ERROR", 12, tarea.getMetodoEjecucionCasoError() != null ? tarea.getMetodoEjecucionCasoError().getName() : "", 1));
               parametros.add(new Parametro("DSUNIDAD_TIEMPO", 12, unidadTiempo, 1));
               parametros.add(new Parametro("NMINTERVALO", 4, new Integer(intervalo), 1));
               parametros.add(new Parametro("FEPROGRAMACION", 12, fechaProgramacion, 1));
               parametros.add(new Parametro("SNTAREA_PENDIENTE", 12, tarea.isTareaTerminadaConExito() && !tarea.isTareaRecurrente() ? "N" : "S", 1));
               parametros.add(new Parametro("FEEJECUCION", 12, fechaEjecucion, 1));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  adicionado = 1;
               }
            }
         } catch (Exception var19) {
            if (var19 instanceof PersonalsoftException) {
               throw (PersonalsoftException)var19;
            }

            throw new PersonalsoftException(var19);
         } finally {
            try {
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var18) {
               new PersonalsoftException(var18);
            }

         }
      }

      return adicionado;
   }

   public int actualizarEstadoDeTarea(Tarea tarea) throws PersonalsoftException {
      String rutaQueryMensaje = Configuracion.getInstance().getParametro("actualizarEstadoDeTarea");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ArrayList<Parametro> parametros = new ArrayList();
      int adicionado = 0;
      String estado = "";
      estado = !tarea.isTareaRecurrente() ? "N" : "S";
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDTAREA", 2, new Integer(tarea.getIdTarea())));
               parametros.add(new Parametro("SNTAREA_PENDIENTE", 12, estado));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  adicionado = ps.executeUpdate();
               }
            } else {
               parametros.add(new Parametro("IDTAREA", 4, new Integer(tarea.getIdTarea()), 1));
               parametros.add(new Parametro("SNTAREA_PENDIENTE", 12, estado, 1));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  adicionado = 1;
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
            } catch (SQLException var15) {
               new PersonalsoftException(var15);
            }

         }
      }

      return adicionado;
   }

   public int ingresarResultadoTarea(Tarea tarea, String fechaResultado) throws PersonalsoftException {
      String rutaQueryMensaje = Configuracion.getInstance().getParametro("adicionarResultadoTarea");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ArrayList<Parametro> parametros = new ArrayList();
      int adicionado = 0;
      String estado = "";
      String resultado = tarea.getResultadoEjecucionMetodo() != null ? tarea.getResultadoEjecucionMetodo().toString() : "";
      if (tarea.isTareaTerminada() && tarea.isTareaTerminadaConExito()) {
         estado = "E";
      } else if (tarea.isTareaTerminada() && !tarea.isTareaTerminadaConExito()) {
         estado = "F";
      } else if (tarea.isTareaEnProceso()) {
         estado = "E";
      }

      if (!tarea.isTareaTerminada()) {
         estado = "F";
      }

      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDTAREA", 4, new Integer(tarea.getIdTarea())));
               parametros.add(new Parametro("DSRESULTADO", 12, resultado));
               parametros.add(new Parametro("FERESULTADO", 12, fechaResultado));
               parametros.add(new Parametro("DSESTADO_FIN", 12, estado));
               parametros.add(new Parametro("DSMETODO_EJECUTA", 12, tarea.getNombreMetodoEjecutado()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  adicionado = ps.executeUpdate();
               }
            } else {
               parametros.add(new Parametro("IDTAREA", 4, new Integer(tarea.getIdTarea()), 1));
               parametros.add(new Parametro("DSRESULTADO", 12, resultado, 1));
               parametros.add(new Parametro("FERESULTADO", 12, fechaResultado, 1));
               parametros.add(new Parametro("DSESTADO_FIN", 12, estado, 1));
               parametros.add(new Parametro("DSMETODO_EJECUTA", 12, tarea.getNombreMetodoEjecutado(), 1));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  adicionado = cs.executeUpdate();
               }
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
            } catch (SQLException var17) {
               new PersonalsoftException(var17);
            }

         }
      }

      return adicionado;
   }

   public void ingresarParametroTarea(Tarea tarea, ParametroTareaDTO parametroTareaDTO, int idParametro) throws PersonalsoftException {
      String rutaQueryMensaje = Configuracion.getInstance().getParametro("adicionarParametroTarea");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ArrayList<Parametro> parametros = new ArrayList();
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDTAREA", 4, new Integer(tarea.getIdTarea())));
               parametros.add(new Parametro("IDPARAMETRO", 4, idParametro));
               parametros.add(new Parametro("DSTIPO_PARAM", 12, parametroTareaDTO.getDsTipoParam()));
               parametros.add(new Parametro("SNPARAM_EXITO", 12, parametroTareaDTO.getDsParamExito()));
               parametros.add(new Parametro("DSVALOR", 12, parametroTareaDTO.getDsValor()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
               }
            } else {
               parametros.add(new Parametro("IDTAREA", 4, new Integer(tarea.getIdTarea()), 1));
               parametros.add(new Parametro("IDPARAMETRO", 4, idParametro, 1));
               parametros.add(new Parametro("DSTIPO_PARAM", 12, parametroTareaDTO.getDsTipoParam(), 1));
               parametros.add(new Parametro("SNPARAM_EXITO", 12, parametroTareaDTO.getDsParamExito(), 1));
               parametros.add(new Parametro("DSVALOR", 12, parametroTareaDTO.getDsValor(), 1));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
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
            } catch (SQLException var15) {
               new PersonalsoftException(var15);
            }

         }
      }

   }

   public int eliminarParametrosTarea(Tarea tarea) throws PersonalsoftException {
      String rutaQueryMensaje = Configuracion.getInstance().getParametro("deleteParametrosTarea");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ArrayList<Parametro> parametros = new ArrayList();
      int borrados = 0;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDTAREA", 2, new Integer(tarea.getIdTarea())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  borrados = ps.executeUpdate();
               }
            } else {
               parametros.add(new Parametro("IDTAREA", 4, new Integer(tarea.getIdTarea()), 1));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  borrados = 1;
               }
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
            } catch (SQLException var14) {
               new PersonalsoftException(var14);
            }

         }
      }

      return borrados;
   }

   public int eliminarResultadoTarea(Tarea tarea) throws PersonalsoftException {
      String rutaQueryMensaje = Configuracion.getInstance().getParametro("deleteResultadosTarea");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ArrayList<Parametro> parametros = new ArrayList();
      int borrados = 0;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDTAREA", 2, new Integer(tarea.getIdTarea())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  borrados = ps.executeUpdate();
               }
            } else {
               parametros.add(new Parametro("IDTAREA", 4, new Integer(tarea.getIdTarea()), 1));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  borrados = 1;
               }
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
            } catch (SQLException var14) {
               new PersonalsoftException(var14);
            }

         }
      }

      return borrados;
   }

   public int eliminarTarea(Tarea tarea) throws PersonalsoftException {
      String rutaQueryMensaje = Configuracion.getInstance().getParametro("deleteTarea");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ArrayList<Parametro> parametros = new ArrayList();
      int borrados = 0;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDTAREA", 2, new Integer(tarea.getIdTarea())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  borrados = ps.executeUpdate();
               }
            } else {
               parametros.add(new Parametro("IDTAREA", 4, new Integer(tarea.getIdTarea()), 1));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  borrados = 1;
               }
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
            } catch (SQLException var14) {
               new PersonalsoftException(var14);
            }

         }
      }

      return borrados;
   }

   public int actualizarEstadoTarea(Tarea tarea) throws PersonalsoftException {
      String rutaQueryMensaje = Configuracion.getInstance().getParametro("actualizarEstadoTarea");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ArrayList<Parametro> parametros = new ArrayList();
      int actualizados = 0;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDTAREA", 2, new Integer(tarea.getIdTarea())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  actualizados = ps.executeUpdate();
               }
            } else {
               parametros.add(new Parametro("IDTAREA", 4, new Integer(tarea.getIdTarea()), 1));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  actualizados = 1;
               }
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
            } catch (SQLException var14) {
               new PersonalsoftException(var14);
            }

         }
      }

      return actualizados;
   }
}
