package co.com.personalsoft.base.programacion.control;

import co.com.personalsoft.base.beans.TareaDTO;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.programacion.tareas.Tarea;
import co.com.personalsoft.base.util.Fecha;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class AgenteEjecucionHelper implements Serializable {
   private static final long serialVersionUID = 5991972946041014478L;

   public String mensajeWarning() {
      StringBuilder str = new StringBuilder();
      str.append("La tarea que ha programado para ejecución, sin embargo no podrá persistir a base de datos,\n ");
      str.append("ya que esta opción está deshabilitada para la aplicación");
      return str.toString();
   }

   public TareaDTO configurarTareaPorDefecto() {
      TareaDTO tareaDTO = new TareaDTO();
      String tareaObj = AgenteColector.class.getCanonicalName();
      String nombre = "Recolector de tareas programadas";
      String nombreMetodoEjecucion = "iniciarRecoleccion";
      String intervaloEjecucionHoras = Configuracion.getInstance().getParametro("intervaloTiempoAgenteColector");
      String unidadTiempoEjecucionAgente = Configuracion.getInstance().getParametro("unidadTiempoAgenteColector");
      String permitePersistenciaTareasBD = Configuracion.getInstance().getParametro("permitePersistenciaTareasBD");
      String tiempoRemocionTareas = Configuracion.getInstance().getParametro("tiempoRemocionTareas");
      String unidadTiempoRemocionTareas = Configuracion.getInstance().getParametro("unidadTiempoRemocionTareas");
      String permiteHistorico = Configuracion.getInstance().getParametro("permiteHistoricoAgenteTareas");
      Object[] parametrosEjecucion = new Object[3];
      long tiempoRemocionTarea = 1L;
      if (nombre == null || nombre.trim().length() == 0) {
         nombre = "agenteColector";
      }

      if (nombreMetodoEjecucion == null || nombreMetodoEjecucion.trim().length() == 0) {
         nombreMetodoEjecucion = "iniciarRecoleccion";
      }

      if (intervaloEjecucionHoras == null || intervaloEjecucionHoras.trim().length() == 0) {
         intervaloEjecucionHoras = "12";
      }

      if (permitePersistenciaTareasBD == null || permitePersistenciaTareasBD.trim().length() == 0) {
         permitePersistenciaTareasBD = "N";
      }

      if (unidadTiempoEjecucionAgente == null || unidadTiempoEjecucionAgente.trim().length() == 0) {
         unidadTiempoEjecucionAgente = "H";
      }

      if (tiempoRemocionTareas == null || tiempoRemocionTareas.trim().length() == 0) {
         tiempoRemocionTareas = "48";
      }

      if (permiteHistorico == null || permiteHistorico.trim().length() == 0) {
         permiteHistorico = "N";
      }

      if (unidadTiempoRemocionTareas.trim().equalsIgnoreCase("H")) {
         tiempoRemocionTarea = (long)Integer.parseInt(tiempoRemocionTareas) * 3600000L;
      } else if (unidadTiempoRemocionTareas.trim().equalsIgnoreCase("M")) {
         tiempoRemocionTarea = (long)Integer.parseInt(tiempoRemocionTareas) * 60000L;
      }

      parametrosEjecucion[0] = tiempoRemocionTarea;
      parametrosEjecucion[1] = permiteHistorico;
      parametrosEjecucion[2] = permitePersistenciaTareasBD;
      tareaDTO.setParametrosEjecucion(parametrosEjecucion);
      tareaDTO.setNombreClase(tareaObj);
      tareaDTO.setNombre(nombre);
      tareaDTO.setMetodoEjecucion(nombreMetodoEjecucion);
      tareaDTO.setIntervaloEjucionHoras(intervaloEjecucionHoras);
      tareaDTO.setTareaPersistente("N");
      tareaDTO.setUnidadTiempo(unidadTiempoEjecucionAgente);
      tareaDTO.setFecha(GregorianCalendar.getInstance());
      return tareaDTO;
   }

   public String[] consultarTareasProgramadas() throws Exception {
      Collection<Tarea> colaProcesos = AgenteEjecucion.getInstance().getColaProcesos();
      return this.adicionarTareasNoPersistentes(colaProcesos);
   }

   private String[] adicionarTareasNoPersistentes(Collection<Tarea> colaProcesos) {
      ArrayList<String> adicionados = new ArrayList();
      if (colaProcesos != null && !colaProcesos.isEmpty()) {
         Iterator var4 = colaProcesos.iterator();

         while(var4.hasNext()) {
            Tarea tarea = (Tarea)var4.next();
            if (!tarea.isTareaPersistente()) {
               adicionados.add(this.dtoAssembler(tarea));
            }
         }
      }

      return adicionados != null && !adicionados.isEmpty() ? (String[])adicionados.toArray(new String[adicionados.size()]) : new String[0];
   }

   public String dtoAssembler(Tarea tarea) {
      StringBuilder str = new StringBuilder();
      String unidadEjecucion = tarea.getIntervaloEjecucion() + " " + tarea.getUnidadTiempo();
      String estado = " ";
      String fechaProgramacion = "-";
      if (tarea.getFechaEjecucion() != null) {
         fechaProgramacion = Fecha.cambiarTipoDato(tarea.getFechaEjecucion().getTime(), "yyyy-MM-dd HH:mm:ss");
      }

      if (tarea.isTareaEnProceso()) {
         estado = "En Proceso";
      } else if (tarea.isTareaTerminada() && tarea.isTareaTerminadaConExito()) {
         estado = "Terminada";
      } else if (tarea.isTareaTerminada() && !tarea.isTareaTerminada()) {
         estado = "Terminada con error";
      }

      str.append(tarea.getIdTarea() + "Æ");
      str.append(tarea.getName() + "Æ");
      str.append(unidadEjecucion + "Æ");
      str.append(estado + "Æ");
      str.append(fechaProgramacion + "Æ");
      str.append("N");
      return str.toString();
   }
}
