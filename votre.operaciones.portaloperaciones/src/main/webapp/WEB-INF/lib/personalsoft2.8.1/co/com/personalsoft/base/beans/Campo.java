package co.com.personalsoft.base.beans;

import java.io.Serializable;

public class Campo implements Serializable {
   private static final long serialVersionUID = -8862483874462730796L;
   private String nombre;
   private int type;
   private boolean obligatorio;
   private String nombreUsuario;
   private boolean uppperCase;

   public Campo() {
   }

   public Campo(String nombre, int type, boolean obligatorio, String nombreUsuario, boolean upperCase) {
      this.nombre = nombre;
      this.type = type;
      this.obligatorio = obligatorio;
      this.nombreUsuario = nombreUsuario;
      this.uppperCase = upperCase;
   }

   public String getNombre() {
      return this.nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getNombreUsuario() {
      return this.nombreUsuario;
   }

   public void setNombreUsuario(String nombreUsuario) {
      this.nombreUsuario = nombreUsuario;
   }

   public boolean isObligatorio() {
      return this.obligatorio;
   }

   public void setObligatorio(boolean obligatorio) {
      this.obligatorio = obligatorio;
   }

   public int getType() {
      return this.type;
   }

   public void setType(int type) {
      this.type = type;
   }

   public boolean isUppperCase() {
      return this.uppperCase;
   }

   public void setUppperCase(boolean uppperCase) {
      this.uppperCase = uppperCase;
   }
}
