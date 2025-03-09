package co.com.personalsoft.base.programacion.tareas;

import java.util.Observable;

public class DelegatedObservable extends Observable {
   public void clearChanged() {
      super.clearChanged();
   }

   public void setChanged() {
      super.setChanged();
   }
}
