package co.com.personalsoft.base.beans;

import java.io.Serializable;

public class Item implements Serializable, Comparable<Item> {
   private static final long serialVersionUID = -1262283844462738796L;
   private String codigo;
   private String descripcion;

   public Item() {
   }

   public Item(String codigo, String descripcion) {
      this.codigo = codigo;
      this.descripcion = descripcion;
   }

   public String getCodigo() {
      return this.codigo;
   }

   public void setCodigo(String codigo) {
      this.codigo = codigo;
   }

   public String getDescripcion() {
      return this.descripcion;
   }

   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }

   public int compareTo(Item item) {
      return this.codigo.compareTo(item.getCodigo());
   }
}
