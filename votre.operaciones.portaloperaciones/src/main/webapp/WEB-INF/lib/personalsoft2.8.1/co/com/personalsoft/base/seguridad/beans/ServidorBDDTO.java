package co.com.personalsoft.base.seguridad.beans;

import java.io.Serializable;

public class ServidorBDDTO implements Serializable {
   private static final long serialVersionUID = 4588925887758533268L;
   private String clave;
   private String nombreJNDI;
   private String nombreServidor;
   private String url;
   private String usuario;
   private String driver;

   public String getClave() {
      return this.clave;
   }

   public void setClave(String clave) {
      this.clave = clave;
   }

   public String getNombreJNDI() {
      return this.nombreJNDI;
   }

   public void setNombreJNDI(String nombreJNDI) {
      this.nombreJNDI = nombreJNDI;
   }

   public String getNombreServidor() {
      return this.nombreServidor;
   }

   public void setNombreServidor(String nombreServidor) {
      this.nombreServidor = nombreServidor;
   }

   public String getUrl() {
      return this.url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public String getUsuario() {
      return this.usuario;
   }

   public void setUsuario(String usuario) {
      this.usuario = usuario;
   }

   public String getDriver() {
      return this.driver;
   }

   public void setDriver(String driver) {
      this.driver = driver;
   }

   public String toString() {
      StringBuilder str = new StringBuilder();
      str.append(" Clave " + this.getClave() + "\n");
      str.append(" Nombre JNDI " + this.getNombreJNDI() + "\n");
      str.append(" Nombre Servidor " + this.getNombreServidor() + "\n");
      str.append(" Url " + this.getUrl() + "\n");
      str.append(" Usuario " + this.getUsuario() + "\n");
      str.append(" Driver " + this.getDriver());
      return str.toString();
   }
}
