package co.com.personalsoft.base.ftp;

public class Servidor {
   private String host;
   private int puerto;
   private String usuario;
   private String clave;
   private String metodo;
   private String rutaArchivos;
   private boolean isAscii;
   private boolean isActivo;

   public boolean isAscii() {
      return this.isAscii;
   }

   public void setAscii(boolean isAscii) {
      this.isAscii = isAscii;
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

   public boolean isActivo() {
      return this.isActivo;
   }

   public void setActivo(boolean isActivo) {
      this.isActivo = isActivo;
   }

   public String getMetodo() {
      return this.metodo;
   }

   public void setMetodo(String metodo) {
      this.metodo = metodo;
   }

   public int getPuerto() {
      return this.puerto;
   }

   public void setPuerto(int puerto) {
      this.puerto = puerto;
   }

   public String getRutaArchivos() {
      return this.rutaArchivos;
   }

   public void setRutaArchivos(String rutaArchivos) {
      this.rutaArchivos = rutaArchivos;
   }

   public String getUsuario() {
      return this.usuario;
   }

   public void setUsuario(String usuario) {
      this.usuario = usuario;
   }
}
