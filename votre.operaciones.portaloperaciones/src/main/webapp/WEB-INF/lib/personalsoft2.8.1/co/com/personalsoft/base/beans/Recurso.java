package co.com.personalsoft.base.beans;

import java.io.Serializable;

public class Recurso implements Serializable, Comparable<Recurso> {
   private static final long serialVersionUID = -8871483879462744796L;
   private String accion;
   private String entidad;
   private String localizacion;
   private String siguiente;
   private boolean esComando = false;

   public Recurso() {
   }

   public Recurso(String localizacion, String siguiente, boolean esComando) {
      this.localizacion = localizacion;
      this.siguiente = siguiente;
      this.esComando = esComando;
   }

   public Recurso(String entidad, String accion, String localizacion, String siguiente, boolean esComando) {
      this.entidad = entidad;
      this.accion = accion;
      this.localizacion = localizacion;
      this.siguiente = siguiente;
      this.esComando = esComando;
   }

   public Recurso(String localizacion) {
      this.localizacion = localizacion;
   }

   public boolean isEsComando() {
      return this.esComando;
   }

   public void setEsComando(boolean esComando) {
      this.esComando = esComando;
   }

   public String getLocalizacion() {
      return this.localizacion;
   }

   public void setLocalizacion(String localizacion) {
      this.localizacion = localizacion;
   }

   public String getSiguiente() {
      return this.siguiente;
   }

   public void setSiguiente(String siguiente) {
      this.siguiente = siguiente;
   }

   public String getAccion() {
      return this.accion;
   }

   public void setAccion(String accion) {
      this.accion = accion;
   }

   public String getEntidad() {
      return this.entidad;
   }

   public void setEntidad(String entidad) {
      this.entidad = entidad;
   }

   public int compareTo(Recurso recurso) {
      return (this.entidad + "." + this.accion).compareTo(recurso.getEntidad() + "." + recurso.getAccion());
   }

   public String toString() {
      String salida = (this.accion != null ? "Accion: [" + this.accion + "] " : "") + "Localización: [" + this.localizacion + "] " + (this.siguiente != null ? "Siguiente: [" + this.siguiente + "] " : "") + "¿Es comando?: " + (this.esComando ? "SI" : "NO") + ".";
      return salida;
   }
}
