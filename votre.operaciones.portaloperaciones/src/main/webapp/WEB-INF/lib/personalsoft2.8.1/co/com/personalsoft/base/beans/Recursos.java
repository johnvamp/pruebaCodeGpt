package co.com.personalsoft.base.beans;

import java.util.HashMap;

public class Recursos {
   private HashMap<String, Recurso> recursosControlados;
   private HashMap<String, Recurso> recursosAceptados;

   public HashMap<String, Recurso> getRecursosControlados() {
      return this.recursosControlados;
   }

   public void setRecursosControlados(HashMap<String, Recurso> recursos) {
      this.recursosControlados = recursos;
   }

   public HashMap<String, Recurso> getRecursosAceptados() {
      return this.recursosAceptados;
   }

   public void setRecursosAceptados(HashMap<String, Recurso> recursosAceptados) {
      this.recursosAceptados = recursosAceptados;
   }
}
