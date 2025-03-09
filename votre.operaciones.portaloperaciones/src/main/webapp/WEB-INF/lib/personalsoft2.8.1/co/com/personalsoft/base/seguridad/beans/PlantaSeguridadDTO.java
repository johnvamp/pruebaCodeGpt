package co.com.personalsoft.base.seguridad.beans;

import java.io.Serializable;

public class PlantaSeguridadDTO implements Serializable, Cloneable {
   private static final long serialVersionUID = 7584438219528408917L;
   private String codigoPlanta;
   private String nombrePlanta;
   private String estado;
   private MensajeDTO mensajeDTO = null;

   public String getEstado() {
      return this.estado;
   }

   public void setEstado(String estado) {
      this.estado = estado;
   }

   public MensajeDTO getMensajeDTO() {
      return this.mensajeDTO;
   }

   public void setMensajeDTO(MensajeDTO mensajeDTO) {
      this.mensajeDTO = mensajeDTO;
   }

   public Object clone() throws CloneNotSupportedException {
      PlantaSeguridadDTO plantaSeguridadDTO = null;
      plantaSeguridadDTO = (PlantaSeguridadDTO)super.clone();
      return plantaSeguridadDTO;
   }

   public PlantaSeguridadDTO() {
   }

   public PlantaSeguridadDTO(String idPlanta) {
      this.codigoPlanta = idPlanta;
   }

   public PlantaSeguridadDTO(String idPlanta, String dsNombre) {
      this.codigoPlanta = idPlanta;
      this.nombrePlanta = dsNombre;
   }

   public String getNombrePlanta() {
      return this.nombrePlanta;
   }

   public void setNombrePlanta(String dsNombre) {
      this.nombrePlanta = dsNombre;
   }

   public String getCodigoPlanta() {
      return this.codigoPlanta;
   }

   public void setCodigoPlanta(String idPlanta) {
      this.codigoPlanta = idPlanta;
   }
}
