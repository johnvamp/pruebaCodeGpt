package co.com.personalsoft.base.seguridad.beans;

import java.io.Serializable;

public class MensajeDTO implements Serializable, Cloneable {
   private static final long serialVersionUID = -1484602777843227243L;
   public static final int EXITO = 1;
   public static final int ERROR = 2;
   private int tipo;
   private String mensajeUsuario;
   private String mensajeTecnico;
   private boolean exito;

   public Object clone() throws CloneNotSupportedException {
      MensajeDTO mensajeDTO = null;
      mensajeDTO = (MensajeDTO)super.clone();
      return mensajeDTO;
   }

   public String getMensajeTecnico() {
      return this.mensajeTecnico;
   }

   public void setMensajeTecnico(String mensajeTecnico) {
      this.mensajeTecnico = mensajeTecnico;
   }

   public String getMensajeUsuario() {
      return this.mensajeUsuario;
   }

   public void setMensajeUsuario(String mensajeUsuario) {
      this.mensajeUsuario = mensajeUsuario;
   }

   public int getTipo() {
      return this.tipo;
   }

   public void setTipo(int tipo) {
      this.tipo = tipo;
   }

   public boolean isExito() {
      if (this.tipo == 1) {
         this.exito = true;
      } else {
         this.exito = false;
      }

      return this.exito;
   }

   public void setExito(boolean exito) {
      this.exito = exito;
   }
}
