package co.com.personalsoft.base.programacion.tareas;

import java.io.Serializable;
import java.util.TimerTask;

public class TareaProgramada extends TimerTask implements Serializable {
   private static final long serialVersionUID = 5866301282874811304L;
   private Tarea tarea;

   public TareaProgramada(Tarea tarea) {
      this.tarea = tarea;
   }

   public void run() {
      this.tarea.run();
   }

   public Tarea getTarea() {
      return this.tarea;
   }

   public void setTarea(Tarea tarea) {
      this.tarea = tarea;
   }
}
