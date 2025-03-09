package co.com.personalsoft.base.programacion.control;

import co.com.personalsoft.base.programacion.tareas.Tarea;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class AgenteColector implements Serializable {
   private static final long serialVersionUID = 5085923102158779842L;
   public static final String NOMBRE_RECOLECTOR = "Recolector de tareas programadas";

   public void iniciarRecoleccion(Long tiempoRemocionTarea, String permiteHistoricoAgenteTareas, String permitePersistenciaTareasBD) {
      Collection<Tarea> colaProcesos = AgenteEjecucion.getInstance().getColaProcesos();
      ArrayList<Tarea> eliminados = new ArrayList();
      if (!colaProcesos.isEmpty()) {
         Iterator var7 = colaProcesos.iterator();

         while(var7.hasNext()) {
            Tarea tarea = (Tarea)var7.next();
            if (!tarea.getProcesoProgramado().getClass().getName().equals(this.getClass().getName())) {
               boolean evaluarTarea = false;
               if (!tarea.isTareaRecurrente()) {
                  if (tarea.isTareaTerminada()) {
                     evaluarTarea = this.evaluarTarea(permiteHistoricoAgenteTareas, permitePersistenciaTareasBD, tarea);
                     if (evaluarTarea) {
                        eliminados.add(tarea);
                     }
                  } else if (!tarea.isTareaTerminada() && System.currentTimeMillis() - tarea.getTiempoInicio() > tiempoRemocionTarea) {
                     evaluarTarea = this.evaluarTarea(permiteHistoricoAgenteTareas, permitePersistenciaTareasBD, tarea);
                     if (evaluarTarea) {
                        eliminados.add(tarea);
                     }
                  }
               }
            }
         }

         if (!eliminados.isEmpty()) {
            colaProcesos.removeAll(eliminados);
         }
      }

   }

   private boolean evaluarTarea(String permiteHistoricoAgenteTareas, String permitePersistenciaTareasBD, Tarea tarea) {
      boolean resultado = true;
      if (tarea.isTareaPersistente()) {
         int procesados = false;
         AgenteEjecucionBDHelper agenteEjecucionBDHelper = new AgenteEjecucionBDHelper();
         int procesados;
         if (permitePersistenciaTareasBD.trim().equalsIgnoreCase("S") && permiteHistoricoAgenteTareas.trim().equalsIgnoreCase("S")) {
            procesados = agenteEjecucionBDHelper.actualizarEstadoTarea(tarea);
            resultado = procesados > 0;
         } else if (permitePersistenciaTareasBD.trim().equalsIgnoreCase("S")) {
            String consultarHistoricoAgenteTareasBD = agenteEjecucionBDHelper.consultarHistoricoAgenteTareasBD();
            if (consultarHistoricoAgenteTareasBD.equalsIgnoreCase("N")) {
               procesados = agenteEjecucionBDHelper.eliminarTarea(tarea);
               resultado = procesados > 0;
            }
         }
      }

      return resultado;
   }
}
