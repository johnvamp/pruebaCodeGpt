package co.com.personalsoft.base.seguridad.beans;

import java.io.Serializable;

public class AplicacionSeguridadDTO implements Serializable {
   private static final long serialVersionUID = -1474414581148320417L;
   private String codigoAplicacion;
   private String nombreAplicacion;
   private MensajeDTO mensajeDTO = new MensajeDTO();

   public String getCodigoAplicacion() {
      return this.codigoAplicacion;
   }

   public void setCodigoAplicacion(String codigoAplicacion) {
      this.codigoAplicacion = codigoAplicacion;
   }

   public String getNombreAplicacion() {
      return this.nombreAplicacion;
   }

   public void setNombreAplicacion(String nombreAplicacion) {
      this.nombreAplicacion = nombreAplicacion;
   }

   public String toString() {
      StringBuilder str = new StringBuilder();
      str.append("Nombre Aplicación " + this.getNombreAplicacion() + "\n");
      str.append("Código Aplicación " + this.getCodigoAplicacion());
      return str.toString();
   }

   public MensajeDTO getMensajeDTO() {
      return this.mensajeDTO;
   }

   public void setMensajeDTO(MensajeDTO mensajeDTO) {
      this.mensajeDTO = mensajeDTO;
   }
}
