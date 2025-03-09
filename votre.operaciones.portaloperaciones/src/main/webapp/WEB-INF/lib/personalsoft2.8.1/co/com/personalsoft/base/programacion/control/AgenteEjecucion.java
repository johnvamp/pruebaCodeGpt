package co.com.personalsoft.base.programacion.control;

import co.com.personalsoft.base.beans.TareaDTO;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.programacion.tareas.Tarea;
import co.com.personalsoft.base.programacion.tareas.TareaProgramada;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import org.apache.log4j.Logger;

public class AgenteEjecucion implements Observer, Serializable {
   private static final long serialVersionUID = 5991972946041089478L;
   private static AgenteEjecucion agente;
   private static Collection<Tarea> colaProcesos;
   private static Logger logger = Logger.getLogger(AgenteEjecucion.class);
   private long contTareas = 0L;
   private boolean esTareaPorDefecto = false;

   private AgenteEjecucion() {
      String definicionCola = "";
      definicionCola = Configuracion.getInstance().getParametro("claseImplementacionColaAgente");
      if (definicionCola == null || definicionCola.trim().equals("")) {
         definicionCola = "co.com.personalsoft.base.programacion.colecciones.ColaTareas";
      }

      try {
         colaProcesos = (Collection)((Collection)Class.forName(definicionCola).newInstance());
         Collections.synchronizedCollection(colaProcesos);
      } catch (Exception var4) {
         PersonalsoftException personal = new PersonalsoftException(var4);
         logger.error(personal.getTraza());
      }

   }

   private long getSiguienteIdTarea() {
      long idTarea = 0L;
      String consultarBD = this.consultarHabilitarPersistenciaTareasBD();
      if (this.contTareas != 0L && consultarBD.trim().equalsIgnoreCase("S")) {
         AgenteEjecucionBDHelper agenteEjecucionBDHelper = new AgenteEjecucionBDHelper();
         idTarea = agenteEjecucionBDHelper.getSiguienteIdTarea(this.contTareas);
         this.contTareas = idTarea;
      }

      return ++this.contTareas;
   }

   public static AgenteEjecucion getInstance() {
      if (agente == null) {
         agente = new AgenteEjecucion();
         AgenteEjecucionBDHelper agenteEjecucionBDHelper = new AgenteEjecucionBDHelper();
         if (!agente.esTareaPorDefecto) {
            try {
               agente.configurarTareaPorDefecto();
               String consultarPersistenciaTareasBD = agenteEjecucionBDHelper.consultarHabilitarPersistenciaTareasBD();
               if (consultarPersistenciaTareasBD.equalsIgnoreCase("S")) {
                  agente.cargarTareasBD();
               }
            } catch (Exception var3) {
               PersonalsoftException personal = new PersonalsoftException(var3);
               logger.error(personal.getTraza());
            }
         }
      }

      return agente;
   }

   public void update(Observable obs, Object atributoCambiado) {
      String idTarea = "";
      Tarea tarea = null;
      if (atributoCambiado != null) {
         idTarea = atributoCambiado.toString();
         if (idTarea != null && !idTarea.equals("")) {
            tarea = this.buscarTareaEnCola(idTarea);
            if (tarea != null) {
               logger.info("La tarea con id: " + idTarea != null ? idTarea : " ha terminado su ejecución " + (tarea.isTareaTerminadaConExito() ? "con éxito." : "con error."));
            }
         }
      }

   }

   public String programarTarea(Object tareaObj, String nombreMetodoEjecucion) throws Exception {
      if (tareaObj instanceof String) {
         tareaObj = this.construirObjeto((String)tareaObj);
      }

      Tarea tarea = new Tarea(tareaObj, nombreMetodoEjecucion, (Object[])null);
      tarea.setIdTarea(String.valueOf(this.getSiguienteIdTarea()));
      tarea.setName(tarea.getIdTarea());
      tarea.setTareaProgramada(new TareaProgramada(tarea));
      colaProcesos.add(tarea);
      AgenteHelper agenteHelper = new AgenteHelper();
      agenteHelper.programarTimerTarea(tarea, (Calendar)null, 0, "");
      logger.info("La tarea con id: " + tarea.getIdTarea() + "\n" + tarea.toString() + "\n se ha programado exitosamente.");
      return tarea.getIdTarea();
   }

   public String programarTarea(Object tareaObj, String nombreMetodoEjecucion, Calendar fechaEjecucion) throws Exception {
      if (tareaObj instanceof String) {
         tareaObj = this.construirObjeto((String)tareaObj);
      }

      Tarea tarea = new Tarea(tareaObj, nombreMetodoEjecucion, (Object[])null);
      tarea.setIdTarea(String.valueOf(this.getSiguienteIdTarea()));
      tarea.setName(tarea.getIdTarea());
      tarea.setTareaProgramada(new TareaProgramada(tarea));
      colaProcesos.add(tarea);
      AgenteHelper agenteHelper = new AgenteHelper();
      agenteHelper.programarTimerTarea(tarea, fechaEjecucion, 0, "");
      logger.info("La tarea con id: " + tarea.getIdTarea() + "\n" + tarea.toString() + "\n se ha programado exitosamente.");
      return tarea.getIdTarea();
   }

   public String programarTarea(Object tareaObj, String nombreMetodoEjecucion, String metodoCasoError) throws Exception {
      if (tareaObj instanceof String) {
         tareaObj = this.construirObjeto((String)tareaObj);
      }

      Tarea tarea = new Tarea(tareaObj, nombreMetodoEjecucion, (Object[])null, metodoCasoError, (Object[])null, 0, (String)null, (Calendar)null);
      tarea.setIdTarea(String.valueOf(this.getSiguienteIdTarea()));
      tarea.setName(tarea.getIdTarea());
      tarea.setTareaProgramada(new TareaProgramada(tarea));
      colaProcesos.add(tarea);
      AgenteHelper agenteHelper = new AgenteHelper();
      agenteHelper.programarTimerTarea(tarea, (Calendar)null, 0, "");
      logger.info("La tarea con id: " + tarea.getIdTarea() + "\n" + tarea.toString() + "\n se ha programado exitosamente.");
      return tarea.getIdTarea();
   }

   public String programarTarea(Object tareaObj, String nombreMetodoEjecucion, String metodoCasoError, Calendar fechaEjecucion, int intervaloEjecucion) throws Exception {
      if (tareaObj instanceof String) {
         tareaObj = this.construirObjeto((String)tareaObj);
      }

      Tarea tarea = new Tarea(tareaObj, nombreMetodoEjecucion, (Object[])null, metodoCasoError, (Object[])null, intervaloEjecucion, "", fechaEjecucion);
      tarea.setIdTarea(String.valueOf(this.getSiguienteIdTarea()));
      tarea.setName(tarea.getIdTarea());
      tarea.setTareaProgramada(new TareaProgramada(tarea));
      colaProcesos.add(tarea);
      AgenteHelper agenteHelper = new AgenteHelper();
      agenteHelper.programarTimerTarea(tarea, fechaEjecucion, intervaloEjecucion, "");
      logger.info("La tarea con id: " + tarea.getIdTarea() + "\n" + tarea.toString() + "\n se ha programado exitosamente .");
      return tarea.getIdTarea();
   }

   public String programarTarea(Object tareaObj, String nombreMetodoEjecucion, Calendar fechaEjecucion, int intervaloEjecucion, Object... parametrosEjecucion) throws Exception {
      if (tareaObj instanceof String) {
         tareaObj = this.construirObjeto((String)tareaObj);
      }

      Tarea tarea = new Tarea(tareaObj, nombreMetodoEjecucion, parametrosEjecucion);
      tarea.setIdTarea(String.valueOf(this.getSiguienteIdTarea()));
      tarea.setName(tarea.getIdTarea());
      tarea.setTareaProgramada(new TareaProgramada(tarea));
      colaProcesos.add(tarea);
      AgenteHelper agenteHelper = new AgenteHelper();
      agenteHelper.programarTimerTarea(tarea, fechaEjecucion, intervaloEjecucion, "");
      logger.info("La tarea con id: " + tarea.getIdTarea() + "\n" + tarea.toString() + "\n se ha programado exitosamente.");
      return tarea.getIdTarea();
   }

   public String programarTarea(Object tareaObj, String nombreMetodoEjecucion, Object... parametrosEjecucion) throws Exception {
      if (tareaObj instanceof String) {
         tareaObj = this.construirObjeto((String)tareaObj);
      }

      Tarea tarea = new Tarea(tareaObj, nombreMetodoEjecucion, parametrosEjecucion == null ? new Object[0] : parametrosEjecucion);
      tarea.setIdTarea(String.valueOf(this.getSiguienteIdTarea()));
      tarea.setName(tarea.getIdTarea());
      tarea.setTareaProgramada(new TareaProgramada(tarea));
      colaProcesos.add(tarea);
      AgenteHelper agenteHelper = new AgenteHelper();
      agenteHelper.programarTimerTarea(tarea, (Calendar)null, 0, "");
      logger.info("La tarea con id: " + tarea.getIdTarea() + "\n" + tarea.toString() + "\n se ha programado exitosamente.");
      return tarea.getIdTarea();
   }

   public String programarTarea(Object tareaObj, String nombreMetodoEjecucion, Collection<Object> parametrosEjecucion, String metodoCasoError, Collection<Object> parametrosCasosError) throws Exception {
      if (tareaObj instanceof String) {
         tareaObj = this.construirObjeto((String)tareaObj);
      }

      Tarea tarea = null;
      if (metodoCasoError == null) {
         tarea = new Tarea(tareaObj, nombreMetodoEjecucion, parametrosEjecucion == null ? new Object[0] : parametrosEjecucion.toArray(new Object[parametrosEjecucion.size()]));
      } else {
         tarea = new Tarea(tareaObj, nombreMetodoEjecucion, parametrosEjecucion == null ? new Object[0] : parametrosEjecucion.toArray(new Object[parametrosEjecucion.size()]), metodoCasoError, parametrosCasosError == null ? new Object[0] : parametrosCasosError.toArray(new Object[parametrosCasosError.size()]), 0, (String)null, (Calendar)null);
      }

      tarea.setTareaProgramada(new TareaProgramada(tarea));
      tarea.setIdTarea(String.valueOf(this.getSiguienteIdTarea()));
      colaProcesos.add(tarea);
      AgenteHelper agenteHelper = new AgenteHelper();
      agenteHelper.programarTimerTarea(tarea, (Calendar)null, 0, "");
      logger.info("La tarea con id: " + tarea.getIdTarea() + "\n" + tarea.toString() + "\n se ha programado exitosamente.");
      return tarea.getIdTarea();
   }

   public String programarTarea(Object tareaObj, String nombreMetodoEjecucion, Collection<Object> parametrosEjecucion, String metodoCasoError, Collection<Object> parametrosCasosError, Calendar fechaEjecucion, int intervaloEjecucion) throws Exception {
      if (tareaObj instanceof String) {
         tareaObj = this.construirObjeto((String)tareaObj);
      }

      Tarea tarea = null;
      if (metodoCasoError == null) {
         tarea = new Tarea(tareaObj, nombreMetodoEjecucion, parametrosEjecucion == null ? new Object[0] : parametrosEjecucion.toArray(new Object[parametrosEjecucion.size()]));
      } else {
         tarea = new Tarea(tareaObj, nombreMetodoEjecucion, parametrosEjecucion == null ? new Object[0] : parametrosEjecucion.toArray(new Object[parametrosEjecucion.size()]), metodoCasoError, parametrosCasosError == null ? new Object[0] : parametrosCasosError.toArray(new Object[parametrosCasosError.size()]), intervaloEjecucion, (String)null, fechaEjecucion);
      }

      tarea.setTareaProgramada(new TareaProgramada(tarea));
      tarea.setIdTarea(String.valueOf(this.getSiguienteIdTarea()));
      colaProcesos.add(tarea);
      AgenteHelper agenteHelper = new AgenteHelper();
      agenteHelper.programarTimerTarea(tarea, fechaEjecucion, intervaloEjecucion, "");
      logger.info("La tarea con id: " + tarea.getIdTarea() + "\n" + tarea.toString() + "\n se ha programado exitosamente.");
      return tarea.getIdTarea();
   }

   public boolean cancelarEjecucionTarea(String idTarea) {
      Tarea tarea = this.buscarTareaEnCola(idTarea);
      logger.info("La tarea con id: " + tarea.getIdTarea() + "\n" + tarea.toString() + "\n cancelando ejecución tarea.");
      if (!tarea.isTareaTerminada() && !tarea.isTareaEnProceso()) {
         tarea.getTimer().cancel();
         return false;
      } else {
         return false;
      }
   }

   public void reprogramarTarea(String idTarea, Calendar nuevaFechaEjecucion, int intervaloEjecucionHoras) {
      Tarea tarea = this.buscarTareaEnCola(idTarea);
      if (tarea != null) {
         tarea.getTimer().cancel();
         AgenteHelper agenteHelper = new AgenteHelper();
         agenteHelper.programarTimerTarea(tarea, nuevaFechaEjecucion, intervaloEjecucionHoras, "");
         logger.info("La tarea con id: " + tarea.getIdTarea() + "\n" + tarea.toString() + "\n se ha reprogramado exitosamente.");
      }

   }

   public String consultarDatosTarea(String idTarea) {
      Tarea tarea = this.buscarTareaEnCola(idTarea);
      if (tarea != null) {
         AgenteEjecucionConfiguracionHelper agenteEjecucionConfiguracionHelper = new AgenteEjecucionConfiguracionHelper();
         return agenteEjecucionConfiguracionHelper.dtoAssembler(tarea);
      } else {
         return "";
      }
   }

   public Object consultarResultadoTarea(String idTarea) throws Exception {
      Tarea tarea = this.buscarTareaEnCola(idTarea);
      logger.info("La tarea con id: " + tarea.getIdTarea() + "\n" + tarea.toString() + "\n consultando datos.");
      if (tarea != null) {
         return tarea.getResultadoEjecucionMetodo();
      } else if (!tarea.isTareaEnProceso() && tarea.isTareaTerminadaConExito()) {
         return tarea.isTareaTerminadaConExito() ? tarea.getResultadoEjecucionMetodo() : null;
      } else {
         throw new Exception("La tarea con los siguientes parametros ha fallado: " + tarea.toString());
      }
   }

   public Object consultarResultadoTareaConError(String idTarea) {
      Tarea tarea = this.buscarTareaEnCola(idTarea);
      logger.info("La tarea con id: " + tarea.getIdTarea() + "\n" + tarea.toString() + "\n consultando datos método error.");
      return tarea != null ? tarea.getResultadoEjecucionMetodo() : null;
   }

   public void esperarResultadosTareas(long tiempoMaxEsperaTarea, String... idTarea) {
      ArrayList<Tarea> tareas = new ArrayList();
      Tarea tareaTmp = null;
      logger.info(" esperando resultados tareas.");

      for(int i = 0; i < idTarea.length; ++i) {
         tareaTmp = this.buscarTareaEnCola(idTarea[i]);
         if (tareaTmp != null) {
            tareas.add(tareaTmp);
         }
      }

      Iterator var7 = tareas.iterator();

      while(var7.hasNext()) {
         Tarea tarea = (Tarea)var7.next();

         try {
            if (!tarea.isTareaRecurrente()) {
               tarea.join(tiempoMaxEsperaTarea);
            }
         } catch (InterruptedException var10) {
            PersonalsoftException personalsoftException = new PersonalsoftException(var10);
            logger.error(personalsoftException.getTraza());
         }
      }

   }

   private Object construirObjeto(String nombreClase) throws Exception {
      return Class.forName(nombreClase).newInstance();
   }

   private Tarea buscarTareaEnCola(String idTarea) {
      idTarea = idTarea != null ? idTarea.trim() : "";
      Iterator var3 = colaProcesos.iterator();

      while(var3.hasNext()) {
         Tarea tarea = (Tarea)var3.next();
         if (tarea.getIdTarea().trim().equals(idTarea)) {
            return tarea;
         }
      }

      return null;
   }

   public String consultarHabilitarPersistenciaTareasBD() {
      return (new AgenteEjecucionBDHelper()).consultarHabilitarPersistenciaTareasBD();
   }

   public Collection<Tarea> getColaProcesos() {
      return colaProcesos;
   }

   public void cargarTareasBD() throws Exception {
      (new AgenteEjecucionBDHelper()).cargarTareasBD();
   }

   public void configurarTareaPorDefecto() throws Exception {
      AgenteEjecucionHelper agenteEjecucionHelper = new AgenteEjecucionHelper();
      TareaDTO tareaDTO = agenteEjecucionHelper.configurarTareaPorDefecto();
      this.programarTarea((String)null, tareaDTO.getNombreClase(), tareaDTO.getNombre(), tareaDTO.getMetodoEjecucion(), tareaDTO.getParametrosEjecucion(), tareaDTO.getMetodoEjecucionCasoError(), (Object[])null, tareaDTO.getFecha(), Integer.parseInt(tareaDTO.getIntervaloEjucionHoras()), !tareaDTO.isTareaPersistente().equals("N"), tareaDTO.getUnidadTiempo());
   }

   public void terminarEjecucionAgente() {
      if (colaProcesos != null && !colaProcesos.isEmpty()) {
         Tarea tarea;
         for(Iterator iter = colaProcesos.iterator(); iter.hasNext(); tarea.stop()) {
            tarea = (Tarea)iter.next();
            if (tarea.getTimer() != null) {
               tarea.getTimer().cancel();
            }
         }

         colaProcesos.clear();
      }

   }

   public void reprogramarTarea(String idTarea, String tareaObj, String nombre, String nombreMetodoEjecucion, Object[] parametrosEjecucion, String metodoCasoError, Object[] parametrosCasosError, Calendar nuevaFechaEjecucion, int intervaloEjecucion, boolean tareaPersistente, String unidadTiempo) throws Exception {
      Tarea tarea = this.buscarTareaEnCola(idTarea);
      if (tarea != null) {
         Object clase = null;
         clase = this.construirObjeto(tareaObj);
         boolean eliminarTarea = false;
         if (colaProcesos.remove(tarea)) {
            eliminarTarea = true;
         }

         try {
            tarea.getTimer().cancel();
         } catch (Throwable var17) {
         }

         AgenteEjecucionConfiguracionHelper agenteEjecucionConfiguracionHelper = new AgenteEjecucionConfiguracionHelper();
         tarea = agenteEjecucionConfiguracionHelper.configurarTarea(clase, tarea, tareaObj, nombre, nombreMetodoEjecucion, parametrosEjecucion, metodoCasoError, parametrosCasosError, nuevaFechaEjecucion, intervaloEjecucion, tareaPersistente, unidadTiempo);
         tarea.setTareaProgramada(new TareaProgramada(tarea));
         tarea.setIdTarea(idTarea);
         tarea.setName(nombre);
         tarea.setTareaPersistente(tareaPersistente);
         if (eliminarTarea) {
            colaProcesos.add(tarea);
         }

         AgenteHelper agenteHelper = new AgenteHelper();
         if (!this.esTareaPorDefecto) {
            this.esTareaPorDefecto = true;
         }

         agenteHelper.programarTimerTarea(tarea, nuevaFechaEjecucion, intervaloEjecucion, unidadTiempo);
         agenteEjecucionConfiguracionHelper.reprogramarTarea(tarea);
      }

   }

   public String programarTarea(String idTarea, String nombreClaseTarea, String nombre, String nombreMetodoEjecucion, Object[] parametrosEjecucion, String metodoCasoError, Object[] parametrosCasosError, Calendar fechaEjecucion, int intervaloEjecucion, boolean tareaPersistente, String unidadTiempo) throws Exception {
      Object clase = null;
      clase = this.construirObjeto(nombreClaseTarea);
      Tarea tarea = null;
      AgenteEjecucionConfiguracionHelper agenteEjecucionConfiguracionHelper = new AgenteEjecucionConfiguracionHelper();
      tarea = agenteEjecucionConfiguracionHelper.configurarTarea(clase, tarea, nombreClaseTarea, nombre, nombreMetodoEjecucion, parametrosEjecucion, metodoCasoError, parametrosCasosError, fechaEjecucion, intervaloEjecucion, tareaPersistente, unidadTiempo);
      tarea.setTareaProgramada(new TareaProgramada(tarea));
      if (idTarea != null) {
         tarea.setIdTarea(idTarea);
      } else {
         tarea.setIdTarea(String.valueOf(this.getSiguienteIdTarea()));
      }

      tarea.setName(nombre + tarea.getIdTarea());
      tarea.setTareaPersistente(tareaPersistente);
      colaProcesos.add(tarea);
      AgenteHelper agenteHelper = new AgenteHelper();
      if (!this.esTareaPorDefecto) {
         this.esTareaPorDefecto = true;
      }

      agenteHelper.programarTimerTarea(tarea, fechaEjecucion, intervaloEjecucion, unidadTiempo);
      if (tareaPersistente) {
         tarea = agenteEjecucionConfiguracionHelper.programarTarea(tarea);
      }

      logger.info("La tarea con id: " + tarea.getIdTarea() + "\n" + tarea.toString() + "\n se ha programado exitosamente.");
      return tarea.getIdTarea();
   }

   public Tarea buscarTarea(String idTarea) {
      return this.buscarTareaEnCola(idTarea);
   }
}
