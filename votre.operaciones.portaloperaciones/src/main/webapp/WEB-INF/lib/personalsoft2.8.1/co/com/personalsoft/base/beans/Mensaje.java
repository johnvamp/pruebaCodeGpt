package co.com.personalsoft.base.beans;

import co.com.personalsoft.base.config.Configuracion;
import java.io.Serializable;
import java.util.Locale;

public class Mensaje implements Serializable {
   private static final long serialVersionUID = 4122211510875569474L;
   private String clave;
   private String valor;
   private Locale idioma;

   public Mensaje() {
   }

   public Mensaje(String clave) {
      this.clave = clave;
   }

   public Mensaje(String clave, String valor) {
      this.clave = clave;
      this.valor = valor;
   }

   public Mensaje(String clave, String valor, Locale idioma) {
      this.clave = clave;
      this.valor = valor;
      this.idioma = idioma;
   }

   public String getClave() {
      return this.clave;
   }

   public void setClave(String clave) {
      this.clave = clave;
   }

   public Locale getIdioma() {
      return this.idioma;
   }

   public void setIdioma(Locale idioma) {
      this.idioma = idioma;
   }

   public String getValor() {
      return this.valor;
   }

   public void setValor(String valor) {
      this.valor = valor;
   }

   public String getClavePrimaria() {
      String idiomaMensaje = "";
      String paisIdioma = Configuracion.getInstance().getParametro("idiomaPorDefecto");
      if (this.idioma == null) {
         idiomaMensaje = paisIdioma;
      } else {
         idiomaMensaje = this.idioma.getLanguage().toLowerCase() + "_" + this.idioma.getCountry().toUpperCase();
      }

      return this.clave + "-" + idiomaMensaje;
   }
}
