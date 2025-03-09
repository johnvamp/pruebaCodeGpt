package co.com.personalsoft.base.beans;

import java.io.Serializable;
import org.apache.log4j.Logger;

public class Parametro implements Serializable, Cloneable {
   private Logger log = Logger.getLogger(this.getClass());
   private static final long serialVersionUID = -7037262933514884843L;
   public static final int IN = 1;
   public static final int OUT = 2;
   public static final int IN_OUT = 3;
   private int tipoParametro;
   private int tipoES;
   private String nombreParametro;
   private Object valorParametro;
   private int indice;

   public Object clone() {
      try {
         return (Parametro)super.clone();
      } catch (CloneNotSupportedException var2) {
         this.log.error(var2);
         return null;
      }
   }

   public Parametro(String nombreParametro, int tipoParametro, Object valorParametro) {
      this.nombreParametro = nombreParametro;
      this.tipoParametro = tipoParametro;
      this.valorParametro = valorParametro;
   }

   public Parametro(String nombreParametro, int tipoParametro, Object valorParametro, int tipoES) {
      this.nombreParametro = nombreParametro;
      this.tipoParametro = tipoParametro;
      this.valorParametro = valorParametro;
      this.tipoES = tipoES;
   }

   public Parametro(int tipoParametro, Object valorParametro, int tipoES) {
      this.tipoParametro = tipoParametro;
      this.valorParametro = valorParametro;
      this.tipoES = tipoES;
   }

   public int getIndice() {
      return this.indice;
   }

   public void setIndice(int indice) {
      this.indice = indice;
   }

   public String getNombreParametro() {
      return this.nombreParametro;
   }

   public void setNombreParametro(String nombreParametro) {
      this.nombreParametro = nombreParametro;
   }

   public int getTipoParametro() {
      return this.tipoParametro;
   }

   public void setTipoParametro(int tipoParametro) {
      this.tipoParametro = tipoParametro;
   }

   public Object getValorParametro() {
      return this.valorParametro;
   }

   public void setValorParametro(Object valorParametro) {
      this.valorParametro = valorParametro;
   }

   public int getTipoES() {
      return this.tipoES;
   }

   public void setTipoES(int tipoES) {
      this.tipoES = tipoES;
   }
}
