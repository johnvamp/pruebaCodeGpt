package co.com.personalsoft.base.seguridad.beans;

import java.io.Serializable;

public class CargoSeguridadDTO implements Serializable, Cloneable, Comparable<CargoSeguridadDTO> {
   private static final long serialVersionUID = -314942841114266166L;
   private String codigoCargo;
   private String nombreCargo;
   private String nivel;
   private MensajeDTO mensajeDTO = new MensajeDTO();

   public Object clone() throws CloneNotSupportedException {
      CargoSeguridadDTO cargoSeguridadDTO = null;
      cargoSeguridadDTO = (CargoSeguridadDTO)super.clone();
      if (this.getMensajeDTO() != null) {
         cargoSeguridadDTO.setMensajeDTO((MensajeDTO)this.getMensajeDTO().clone());
      }

      return cargoSeguridadDTO;
   }

   public String getCodigoCargo() {
      return this.codigoCargo;
   }

   public void setCodigoCargo(String codigoCargo) {
      this.codigoCargo = codigoCargo;
   }

   public String getNombreCargo() {
      return this.nombreCargo;
   }

   public void setNombreCargo(String nombreCargo) {
      this.nombreCargo = nombreCargo;
   }

   public String getNivel() {
      return this.nivel;
   }

   public void setNivel(String nivel) {
      this.nivel = nivel;
   }

   public String toString() {
      StringBuilder str = new StringBuilder();
      str.append(" Código Cargo " + this.getCodigoCargo() + "\n");
      str.append(" Nombre Cargo " + this.getNombreCargo() + "\n");
      str.append(" Nivel Cargo " + this.getNivel() + "\n");
      str.append(" Mensaje Usuario " + this.mensajeDTO.getMensajeUsuario() + "\n");
      str.append(" Mensaje Técnico " + this.mensajeDTO.getMensajeTecnico());
      return str.toString();
   }

   public MensajeDTO getMensajeDTO() {
      return this.mensajeDTO;
   }

   public void setMensajeDTO(MensajeDTO mensajeDTO) {
      this.mensajeDTO = mensajeDTO;
   }

   public int compareTo(CargoSeguridadDTO cargoSeguridadDTO) {
      return this.nombreCargo.compareTo(cargoSeguridadDTO.getNombreCargo());
   }
}
