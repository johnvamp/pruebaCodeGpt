package co.com.personalsoft.base.beans;

import java.io.Serializable;

public class ResultadoTareaDTO implements Serializable {
   private static final long serialVersionUID = 2449044633405768311L;
   private String estado;
   private String fechaTerminacion;
   private String resultado;
   private String metodoEjecuta;

   public String getEstado() {
      return this.estado;
   }

   public void setEstado(String estado) {
      this.estado = estado;
   }

   public String getFechaTerminacion() {
      return this.fechaTerminacion;
   }

   public void setFechaTerminacion(String fechaTerminacion) {
      this.fechaTerminacion = fechaTerminacion;
   }

   public String getResultado() {
      return this.resultado;
   }

   public void setResultado(String resultado) {
      this.resultado = resultado;
   }

   public String getMetodoEjecuta() {
      return this.metodoEjecuta;
   }

   public void setMetodoEjecuta(String metodoEjecuta) {
      this.metodoEjecuta = metodoEjecuta;
   }
}
