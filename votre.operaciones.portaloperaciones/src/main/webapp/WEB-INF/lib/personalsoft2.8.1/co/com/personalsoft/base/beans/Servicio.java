package co.com.personalsoft.base.beans;

import java.io.Serializable;

public class Servicio implements Serializable {
   private static final long serialVersionUID = -8860481004468730096L;
   public static final String TIPO_EJB = "EJB";
   public static final String TIPO_WEB_SERVICE = "WS";
   public static final String TIPO_POJO = "POJO";
   private String ruta;
   private String tipo;
   private String nombre;
   private String usuario;
   private String clave;

   public Servicio() {
   }

   public Servicio(String nombre, String ruta, String tipo) {
      this.nombre = nombre;
      this.ruta = ruta;
      this.tipo = tipo;
   }

   public String getRuta() {
      return this.ruta;
   }

   public void setRuta(String ruta) {
      this.ruta = ruta != null ? ruta : "";
   }

   public String getTipo() {
      return this.tipo;
   }

   public void setTipo(String tipo) {
      this.tipo = tipo;
   }

   public String getNombre() {
      return this.nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getClave() {
      return this.clave;
   }

   public void setClave(String clave) {
      this.clave = clave != null ? clave : "";
   }

   public String getUsuario() {
      return this.usuario;
   }

   public void setUsuario(String usuario) {
      this.usuario = usuario != null ? usuario : "";
   }
}
