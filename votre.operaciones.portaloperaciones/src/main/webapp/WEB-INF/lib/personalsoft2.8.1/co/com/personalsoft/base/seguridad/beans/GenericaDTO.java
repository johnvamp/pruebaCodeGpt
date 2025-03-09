package co.com.personalsoft.base.seguridad.beans;

import java.io.Serializable;

public class GenericaDTO implements Serializable {
   private static final long serialVersionUID = 1L;
   private String atributo1;
   private String atributo2;
   private String atributo3;
   private String atributo4;
   private String atributo5;
   private MensajeDTO mensajeDTO = new MensajeDTO();

   public String getAtributo1() {
      return this.atributo1;
   }

   public void setAtributo1(String atributo1) {
      this.atributo1 = atributo1;
   }

   public String getAtributo2() {
      return this.atributo2;
   }

   public void setAtributo2(String atributo2) {
      this.atributo2 = atributo2;
   }

   public String getAtributo3() {
      return this.atributo3;
   }

   public void setAtributo3(String atributo3) {
      this.atributo3 = atributo3;
   }

   public String getAtributo4() {
      return this.atributo4;
   }

   public void setAtributo4(String atributo4) {
      this.atributo4 = atributo4;
   }

   public String getAtributo5() {
      return this.atributo5;
   }

   public void setAtributo5(String atributo5) {
      this.atributo5 = atributo5;
   }

   public MensajeDTO getMensajeDTO() {
      return this.mensajeDTO;
   }

   public void setMensajeDTO(MensajeDTO mensajeDTO) {
      this.mensajeDTO = mensajeDTO;
   }
}
