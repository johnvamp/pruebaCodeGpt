package co.com.personalsoft.base.programacion.control;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.bd.TareasConsultasHelper;
import co.com.personalsoft.base.bd.TareasConsultasParametrosHelper;
import co.com.personalsoft.base.bd.TareasHelper;
import co.com.personalsoft.base.beans.ParametroTareaDTO;
import co.com.personalsoft.base.beans.TareaDTO;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.programacion.tareas.Tarea;
import co.com.personalsoft.base.util.Fecha;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import org.apache.log4j.Logger;

public class AgenteEjecucionBDHelper implements Serializable {
   private static final long serialVersionUID = 5991972946041014478L;
   private Logger log = Logger.getLogger(this.getClass());

   public String consultarHistoricoAgenteTareasBD() {
      String historicoAgenteTareasBD = "";
      historicoAgenteTareasBD = Configuracion.getInstance().getParametro("permiteHistoricoAgenteTareas");
      if (historicoAgenteTareasBD == null || historicoAgenteTareasBD.equals("")) {
         historicoAgenteTareasBD = "N";
      }

      return historicoAgenteTareasBD;
   }

   public String consultarHabilitarPersistenciaTareasBD() {
      String habilitarPersistenciaTareasBD = "";
      habilitarPersistenciaTareasBD = Configuracion.getInstance().getParametro("permitePersistenciaTareasBD");
      if (habilitarPersistenciaTareasBD == null || habilitarPersistenciaTareasBD.equals("")) {
         habilitarPersistenciaTareasBD = "N";
      }

      return habilitarPersistenciaTareasBD;
   }

   public String habilitarPersistenciaTareasBD(Tarea tarea) {
      String habilitarPersistenciaTareasBD = "";
      if (tarea.isTareaPersistente()) {
         habilitarPersistenciaTareasBD = Configuracion.getInstance().getParametro("permitePersistenciaTareasBD");
         if (habilitarPersistenciaTareasBD == null || habilitarPersistenciaTareasBD.equals("")) {
            habilitarPersistenciaTareasBD = "N";
         }
      }

      return habilitarPersistenciaTareasBD;
   }

   public void adicionarTarea(Tarea tarea, String fechaEjecucion, String unidadTiempo, int intervalo) {
      String fechaProgramacion = Fecha.getFechaServidor("yyyy-MM-dd HH:mm:ss");
      ArrayList<ParametroTareaDTO> parametros = new ArrayList();
      TareasHelper tareasHelper = null;
      BDHelper bdHelper = null;

      try {
         bdHelper = new BDHelper();
         tareasHelper = new TareasHelper(bdHelper);
         int adicionarTarea = tareasHelper.ingresarTarea(tarea, fechaProgramacion, fechaEjecucion, unidadTiempo, intervalo);
         if (adicionarTarea > 0) {
            parametros = this.configurarParametros(parametros, tarea.getParametrosEjecucion(), tarea.getTiposParametrosEjecucion(), "E");
            parametros = this.configurarParametros(parametros, tarea.getParametrosEjecucionError(), tarea.getTiposParametrosEjecucionError(), "F");
            if (parametros.size() > 0) {
               int idParametro = 0;
               Iterator var12 = parametros.iterator();

               while(var12.hasNext()) {
                  ParametroTareaDTO parametroTareaDTO = (ParametroTareaDTO)var12.next();
                  ++idParametro;
                  tareasHelper.ingresarParametroTarea(tarea, parametroTareaDTO, idParametro);
               }
            }
         }
      } catch (PersonalsoftException var21) {
         this.log.error(var21.getTraza());
      } finally {
         try {
            bdHelper.commitTransaction();
            if (bdHelper != null) {
               bdHelper.cerrarConexion();
            }
         } catch (SQLException var20) {
            new PersonalsoftException(var20);
         }

      }

   }

   public void actualizarTarea(Tarea tarea, String fechaEjecucion, String unidadTiempo, int intervalo) {
      String fechaProgramacion = Fecha.getFechaServidor("yyyy-MM-dd HH:mm:ss");
      ArrayList<ParametroTareaDTO> parametros = new ArrayList();
      TareasHelper tareasHelper = null;
      BDHelper bdHelper = null;

      try {
         bdHelper = new BDHelper();
         tareasHelper = new TareasHelper(bdHelper);
         int actualizarTarea = tareasHelper.actualizarTarea(tarea, fechaProgramacion, fechaEjecucion, unidadTiempo, intervalo);
         if (actualizarTarea > 0) {
            this.borrarTarea(tarea);
            parametros = this.configurarParametros(parametros, tarea.getParametrosEjecucion(), tarea.getTiposParametrosEjecucion(), "E");
            parametros = this.configurarParametros(parametros, tarea.getParametrosEjecucionError(), tarea.getTiposParametrosEjecucionError(), "F");
            if (parametros.size() > 0) {
               int idParametro = 0;
               Iterator var12 = parametros.iterator();

               while(var12.hasNext()) {
                  ParametroTareaDTO parametroTareaDTO = (ParametroTareaDTO)var12.next();
                  ++idParametro;
                  tareasHelper.ingresarParametroTarea(tarea, parametroTareaDTO, idParametro);
               }
            }
         }
      } catch (PersonalsoftException var21) {
         this.log.error(var21.getTraza());
      } finally {
         try {
            bdHelper.commitTransaction();
            if (bdHelper != null) {
               bdHelper.cerrarConexion();
            }
         } catch (SQLException var20) {
            new PersonalsoftException(var20);
         }

      }

   }

   public void actualizarResultadoTarea(Tarea tarea) {
      String fechaResultado = Fecha.getFechaServidor("yyyy-MM-dd HH:mm:ss");
      TareasHelper tareasHelper = null;
      BDHelper bdHelper = null;
      boolean var5 = false;

      try {
         bdHelper = new BDHelper();
         tareasHelper = new TareasHelper(bdHelper);
         int adicionado = tareasHelper.actualizarEstadoDeTarea(tarea);
         if (adicionado > 0) {
            tareasHelper.ingresarResultadoTarea(tarea, fechaResultado);
         }
      } catch (PersonalsoftException var15) {
         this.log.error(var15.getTraza());
      } finally {
         try {
            bdHelper.commitTransaction();
            if (bdHelper != null) {
               bdHelper.cerrarConexion();
            }
         } catch (SQLException var14) {
            new PersonalsoftException(var14);
         }

      }

   }

   public ArrayList<ParametroTareaDTO> configurarParametros(ArrayList<ParametroTareaDTO> parametros, Object[] valoresParametros, Class[] tiposParametros, String tipo) {
      if (valoresParametros != null && valoresParametros.length > 0 && tiposParametros != null && tiposParametros.length > 0) {
         for(int k = 0; k < valoresParametros.length; ++k) {
            if (valoresParametros[k] != null && tiposParametros[k] != null) {
               ParametroTareaDTO parametroTareaDTO = new ParametroTareaDTO();
               parametroTareaDTO.setDsParamExito(tipo);
               parametroTareaDTO.setDsValor(valoresParametros[k].toString());
               parametroTareaDTO.setDsTipoParam(tiposParametros[k].getSimpleName());
               parametros.add(parametroTareaDTO);
            }
         }
      }

      return parametros;
   }

   public int actualizarEstadoTarea(Tarea tarea) {
      TareasHelper tareasHelper = null;
      BDHelper bdHelper = null;
      int actualizar = 0;

      try {
         bdHelper = new BDHelper();
         tareasHelper = new TareasHelper(bdHelper);
         actualizar = tareasHelper.actualizarEstadoTarea(tarea);
      } catch (PersonalsoftException var14) {
         this.log.error(var14.getTraza());
      } finally {
         try {
            bdHelper.commitTransaction();
            if (bdHelper != null) {
               bdHelper.cerrarConexion();
            }
         } catch (SQLException var13) {
            new PersonalsoftException(var13);
         }

      }

      return actualizar;
   }

   public int borrarTarea(Tarea tarea) {
      TareasHelper tareasHelper = null;
      BDHelper bdHelper = null;
      int eliminado = 0;

      try {
         bdHelper = new BDHelper();
         tareasHelper = new TareasHelper(bdHelper);
         eliminado = tareasHelper.eliminarParametrosTarea(tarea);
      } catch (PersonalsoftException var14) {
         this.log.error(var14.getTraza());
      } finally {
         try {
            bdHelper.commitTransaction();
            if (bdHelper != null) {
               bdHelper.cerrarConexion();
            }
         } catch (SQLException var13) {
            new PersonalsoftException(var13);
         }

      }

      return eliminado;
   }

   public int eliminarTarea(Tarea tarea) {
      TareasHelper tareasHelper = null;
      BDHelper bdHelper = null;
      int eliminado = 0;

      try {
         bdHelper = new BDHelper();
         tareasHelper = new TareasHelper(bdHelper);
         tareasHelper.eliminarParametrosTarea(tarea);
         tareasHelper.eliminarResultadoTarea(tarea);
         eliminado = tareasHelper.eliminarTarea(tarea);
      } catch (PersonalsoftException var14) {
         this.log.error(var14.getTraza());
      } finally {
         try {
            bdHelper.commitTransaction();
            if (bdHelper != null) {
               bdHelper.cerrarConexion();
            }
         } catch (SQLException var13) {
            new PersonalsoftException(var13);
         }

      }

      return eliminado;
   }

   public String[] consultarTareasProgramadasBD() throws Exception {
      TareasConsultasHelper tareasHelper = null;
      BDHelper bdHelper = null;
      String[] tareas = (String[])null;
      ArrayList data = null;

      try {
         bdHelper = new BDHelper();
         tareasHelper = new TareasConsultasHelper(bdHelper);
         data = tareasHelper.cargarTareasProgramadas();
      } catch (PersonalsoftException var15) {
         this.log.error(var15.getTraza());
      } finally {
         try {
            if (bdHelper != null) {
               bdHelper.cerrarConexion();
            }
         } catch (SQLException var14) {
            new PersonalsoftException(var14);
         }

      }

      if (data != null && !data.isEmpty()) {
         AgenteEjecucionConfiguracionHelper agenteEjecucionConfiguracionHelper = new AgenteEjecucionConfiguracionHelper();
         tareas = agenteEjecucionConfiguracionHelper.dtoAssembler(data);
         Iterator var7 = data.iterator();

         while(var7.hasNext()) {
            TareaDTO tareaDTO = (TareaDTO)var7.next();
            Tarea tarea = AgenteEjecucion.getInstance().buscarTarea(tareaDTO.getIdTarea());
            if (tarea == null) {
               AgenteEjecucion.getInstance().programarTarea(tareaDTO.getIdTarea(), tareaDTO.getNombreClase(), tareaDTO.getNombre(), tareaDTO.getMetodoEjecucion(), tareaDTO.getParametrosEjecucion(), tareaDTO.getMetodoEjecucionCasoError(), tareaDTO.getParametrosEjecucionError(), tareaDTO.getFecha(), tareaDTO.getIntervaloEjecucion(), true, tareaDTO.getUnidadTiempo());
            }
         }
      }

      return tareas;
   }

   public String consultarTareaProgramadaBD(String idTarea) throws Exception {
      TareasConsultasParametrosHelper tareasHelper = null;
      BDHelper bdHelper = null;
      String tareas = null;

      try {
         bdHelper = new BDHelper();
         tareasHelper = new TareasConsultasParametrosHelper(bdHelper);
         tareas = tareasHelper.cargarTareaProgramada(idTarea);
      } catch (PersonalsoftException var14) {
         this.log.error(var14.getTraza());
      } finally {
         try {
            if (bdHelper != null) {
               bdHelper.cerrarConexion();
            }
         } catch (SQLException var13) {
            new PersonalsoftException(var13);
         }

      }

      return tareas;
   }

   public long getSiguienteIdTarea(long idTarea) {
      long idTareaMax = 0L;
      TareasConsultasHelper tareasHelper = null;
      BDHelper bdHelper = null;

      try {
         bdHelper = new BDHelper();
         tareasHelper = new TareasConsultasHelper(bdHelper);
         idTareaMax = tareasHelper.cargarMaximaIdTarea();
      } catch (PersonalsoftException var16) {
         this.log.error(var16.getTraza());
      } finally {
         try {
            if (bdHelper != null) {
               bdHelper.cerrarConexion();
            }
         } catch (SQLException var15) {
            new PersonalsoftException(var15);
         }

      }

      return idTareaMax > idTarea ? idTareaMax : idTarea;
   }

   public void cargarTareasBD() throws Exception {
      TareasConsultasHelper tareasHelper = null;
      BDHelper bdHelper = null;
      ArrayList tareasPendientes = null;

      try {
         bdHelper = new BDHelper();
         tareasHelper = new TareasConsultasHelper(bdHelper);
         tareasPendientes = tareasHelper.cargarTareasPendientes();
      } catch (PersonalsoftException var13) {
         this.log.error(var13.getTraza());
      } finally {
         try {
            if (bdHelper != null) {
               bdHelper.cerrarConexion();
            }
         } catch (SQLException var12) {
            new PersonalsoftException(var12);
         }

      }

      TareaDTO tareaDTO;
      Calendar fechaEjecucion;
      if (tareasPendientes != null && !tareasPendientes.isEmpty()) {
         for(Iterator var5 = tareasPendientes.iterator(); var5.hasNext(); AgenteEjecucion.getInstance().programarTarea(tareaDTO.getIdTarea(), tareaDTO.getNombreClase(), tareaDTO.getNombre(), tareaDTO.getMetodoEjecucion(), tareaDTO.getParametrosEjecucion(), tareaDTO.getMetodoEjecucionCasoError() != null ? tareaDTO.getMetodoEjecucionCasoError().trim() : "", tareaDTO.getParametrosEjecucionError(), fechaEjecucion, Integer.parseInt(tareaDTO.getIntervaloEjucionHoras()), tareaDTO.getTareaPersistente().equalsIgnoreCase("S"), tareaDTO.getUnidadTiempo())) {
            tareaDTO = (TareaDTO)var5.next();
            fechaEjecucion = null;
            if (tareaDTO.getFechaEjecucion() != null) {
               fechaEjecucion = GregorianCalendar.getInstance();
               fechaEjecucion.setTime(Fecha.cambiarTipoDatoDate(tareaDTO.getFechaEjecucion(), "yyyy-MM-dd HH:mm:ss"));
            }
         }
      }

   }

   public ArrayList<String> consultarTareasProgramadasBD(TareaDTO tareaDTO) throws Exception {
      TareasConsultasParametrosHelper tareasHelper = null;
      BDHelper bdHelper = null;
      ArrayList tareas = new ArrayList();

      try {
         bdHelper = new BDHelper();
         tareasHelper = new TareasConsultasParametrosHelper(bdHelper);
         tareas = tareasHelper.cargarTareasProgramadas(tareaDTO);
      } catch (PersonalsoftException var14) {
         this.log.error(var14.getTraza());
      } finally {
         try {
            if (bdHelper != null) {
               bdHelper.cerrarConexion();
            }
         } catch (SQLException var13) {
            new PersonalsoftException(var13);
         }

      }

      return tareas;
   }
}
