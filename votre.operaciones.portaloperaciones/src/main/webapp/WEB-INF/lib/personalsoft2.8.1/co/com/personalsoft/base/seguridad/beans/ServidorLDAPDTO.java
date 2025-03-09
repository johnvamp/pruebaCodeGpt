package co.com.personalsoft.base.seguridad.beans;

import java.io.Serializable;

public class ServidorLDAPDTO implements Serializable {
   private static final long serialVersionUID = 4588925885158533268L;
   private String baseDN;
   private String clave;
   private String host;
   private String nombreServidor;
   private String protocolo;
   private String usuario;
   private int puerto;
   private int puertoSSL;

   public String getBaseDN() {
      return this.baseDN;
   }

   public void setBaseDN(String baseDN) {
      this.baseDN = baseDN;
   }

   public String getClave() {
      return this.clave;
   }

   public void setClave(String clave) {
      this.clave = clave;
   }

   public String getHost() {
      return this.host;
   }

   public void setHost(String host) {
      this.host = host;
   }

   public String getNombreServidor() {
      return this.nombreServidor;
   }

   public void setNombreServidor(String nombreServidor) {
      this.nombreServidor = nombreServidor;
   }

   public String getProtocolo() {
      return this.protocolo;
   }

   public void setProtocolo(String protocolo) {
      this.protocolo = protocolo;
   }

   public int getPuerto() {
      return this.puerto;
   }

   public void setPuerto(int puerto) {
      this.puerto = puerto;
   }

   public int getPuertoSSL() {
      return this.puertoSSL;
   }

   public void setPuertoSSL(int puertoSSL) {
      this.puertoSSL = puertoSSL;
   }

   public String getUsuario() {
      return this.usuario;
   }

   public void setUsuario(String usuario) {
      this.usuario = usuario;
   }

   public String toString() {
      StringBuilder str = new StringBuilder();
      str.append(" BaseDN " + this.getBaseDN() + "\n");
      str.append(" Clave " + this.getClave() + "\n");
      str.append(" Host " + this.getHost() + "\n");
      str.append(" Nombre Servidor " + this.getNombreServidor() + "\n");
      str.append(" Prototocolo " + this.getProtocolo() + "\n");
      str.append(" Usuario " + this.getUsuario());
      str.append(" Puerto " + this.getPuerto());
      str.append(" PuertoSSL " + this.getPuertoSSL());
      return str.toString();
   }
}
