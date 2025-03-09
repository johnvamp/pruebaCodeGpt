package co.com.personalsoft.base.programacion.control;

import co.com.personalsoft.base.programacion.tareas.Tarea;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Timer;

public class AgenteHelper implements Serializable {
   private static final long serialVersionUID = 5921022311606613390L;
   public static final long CONVERSION_HORA_MILIS = 3600000L;
   public static final long CONVERSION_MINUTO_MILIS = 60000L;

   public Tarea programarTimerTarea(Tarea tarea, Calendar fechaEjecucionUsuario, int intervalo, String unidadTiempo) {
      Timer timer = null;
      long tiempo = 0L;
      tarea.addObserver(AgenteEjecucion.getInstance());
      if (fechaEjecucionUsuario != null && intervalo > 0 && !unidadTiempo.equals("")) {
         if (unidadTiempo.equals("H")) {
            tiempo = 3600000L;
         } else if (unidadTiempo.equals("M")) {
            tiempo = 60000L;
         }

         timer = new Timer();
         tarea.setTareaRecurrente(true);
         timer.schedule(tarea.getTareaProgramada(), fechaEjecucionUsuario.getTime(), (long)intervalo * tiempo);
      } else if (fechaEjecucionUsuario != null && intervalo == 0) {
         timer = new Timer();
         tarea.setTareaRecurrente(true);
         timer.schedule(tarea.getTareaProgramada(), fechaEjecucionUsuario.getTime());
      }

      if (fechaEjecucionUsuario == null) {
         tarea.start();
      }

      return tarea;
   }
}
