package co.com.personalsoft.base.beans;

import java.io.Serializable;
import java.util.HashMap;

public class Servicios implements Serializable {
   private static final long serialVersionUID = -8869181000448710096L;
   private HashMap servicios;

   public HashMap getServicios() {
      return this.servicios;
   }

   public void setServicios(HashMap servicios) {
      this.servicios = servicios;
   }
}
