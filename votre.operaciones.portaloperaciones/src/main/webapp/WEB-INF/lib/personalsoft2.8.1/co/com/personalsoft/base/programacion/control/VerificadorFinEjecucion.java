package co.com.personalsoft.base.programacion.control;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.programacion.tareas.Tarea;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.log4j.Logger;

public class VerificadorFinEjecucion implements Serializable {
   private static final long serialVersionUID = 4205032778097456897L;
   private Logger logger = Logger.getLogger(this.getClass());

   public void esperarFinEjecucionTareas(ArrayList<Tarea> tareas) {
      String parametroTiempo = Configuracion.getInstance().getParametro("tiempoMaximoEsperaTareasMillis");
      long tiempoEspera = 0L;
      if (parametroTiempo != null && !parametroTiempo.equals("")) {
         tiempoEspera = Long.parseLong(parametroTiempo);
      } else {
         tiempoEspera = 0L;
      }

      Iterator var6 = tareas.iterator();

      while(var6.hasNext()) {
         Tarea tarea = (Tarea)var6.next();

         try {
            if (!tarea.isTareaRecurrente()) {
               tarea.join(tiempoEspera);
            }
         } catch (InterruptedException var9) {
            PersonalsoftException personalsoftException = new PersonalsoftException(var9);
            this.logger.error(personalsoftException.getTraza());
         }
      }

   }
}
