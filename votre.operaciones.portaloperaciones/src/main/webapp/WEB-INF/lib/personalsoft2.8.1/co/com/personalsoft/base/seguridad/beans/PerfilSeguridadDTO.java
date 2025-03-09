package co.com.personalsoft.base.seguridad.beans;

import java.io.Serializable;

public class PerfilSeguridadDTO implements Serializable, Cloneable, Comparable<PerfilSeguridadDTO> {
   private static final long serialVersionUID = -1397473581148320417L;
   private String codigoPerfil;
   private String nombrePerfil;
   private String[] opcionesPermitidas;
   private MensajeDTO mensajeDTO = new MensajeDTO();
   private Object atributoAdicional;

   public Object clone() throws CloneNotSupportedException {
      PerfilSeguridadDTO perfilSeguridadDTO = null;
      perfilSeguridadDTO = (PerfilSeguridadDTO)super.clone();
      if (this.getMensajeDTO() != null) {
         perfilSeguridadDTO.setMensajeDTO((MensajeDTO)this.getMensajeDTO().clone());
      }

      return perfilSeguridadDTO;
   }

   public Object getAtributoAdicional() {
      return this.atributoAdicional;
   }

   public void setAtributoAdicional(Object atributoAdicional) {
      this.atributoAdicional = atributoAdicional;
   }

   public String getCodigoPerfil() {
      return this.codigoPerfil;
   }

   public void setCodigoPerfil(String codigoPerfil) {
      this.codigoPerfil = codigoPerfil;
   }

   public String getNombrePerfil() {
      return this.nombrePerfil;
   }

   public void setNombrePerfil(String nombrePerfil) {
      this.nombrePerfil = nombrePerfil;
   }

   public String toString() {
      StringBuilder str = new StringBuilder();
      str.append(" Código Perfil " + this.getCodigoPerfil() + "\n");
      str.append(" Nombre Peril " + this.getNombrePerfil() + "\n");
      str.append(" Mensaje Usuario " + this.mensajeDTO.getMensajeUsuario() + "\n");
      str.append(" Mensaje Técnico " + this.mensajeDTO.getMensajeTecnico());
      return str.toString();
   }

   public String[] getOpcionesPermitidas() {
      return this.opcionesPermitidas;
   }

   public void setOpcionesPermitidas(String[] opcionesPermitidas) {
      this.opcionesPermitidas = opcionesPermitidas;
   }

   public MensajeDTO getMensajeDTO() {
      return this.mensajeDTO;
   }

   public void setMensajeDTO(MensajeDTO mensajeDTO) {
      this.mensajeDTO = mensajeDTO;
   }

   public int compareTo(PerfilSeguridadDTO perfilSeguridadDTO) {
      String codigoPerfilEvaluar = this.codigoPerfil != null && !this.codigoPerfil.equalsIgnoreCase("") ? this.codigoPerfil.trim() : "";
      String codigoPerfilEvaluarParam = perfilSeguridadDTO.getCodigoPerfil() != null && !perfilSeguridadDTO.getCodigoPerfil().equalsIgnoreCase("") ? perfilSeguridadDTO.getCodigoPerfil().trim() : "";
      return Integer.parseInt(codigoPerfilEvaluar) > Integer.parseInt(codigoPerfilEvaluarParam) ? 1 : 0;
   }
}
