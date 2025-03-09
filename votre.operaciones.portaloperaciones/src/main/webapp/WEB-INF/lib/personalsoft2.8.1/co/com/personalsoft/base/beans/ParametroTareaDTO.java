package co.com.personalsoft.base.beans;

import java.io.Serializable;
import java.util.Collection;

public class ParametroTareaDTO implements Serializable {
   private static final long serialVersionUID = -3426916212717566544L;
   private String dsTipoParam;
   private String dsParamExito;
   private String dsValor;
   private Collection<Object> parametrosEjecucion;
   private Collection<Object> parametrosError;

   public ParametroTareaDTO() {
   }

   public ParametroTareaDTO(String dsTipoParam, String dsParamExito, String dsValor) {
      this.dsTipoParam = dsTipoParam;
      this.dsParamExito = dsParamExito;
      this.dsValor = dsValor;
   }

   public String getDsParamExito() {
      return this.dsParamExito;
   }

   public void setDsParamExito(String dsParamExito) {
      this.dsParamExito = dsParamExito;
   }

   public String getDsTipoParam() {
      return this.dsTipoParam;
   }

   public void setDsTipoParam(String dsTipoParam) {
      this.dsTipoParam = dsTipoParam;
   }

   public String getDsValor() {
      return this.dsValor;
   }

   public void setDsValor(String dsValor) {
      this.dsValor = dsValor;
   }

   public Collection<Object> getParametrosEjecucion() {
      return this.parametrosEjecucion;
   }

   public void setParametrosEjecucion(Collection<Object> parametrosEjecucion) {
      this.parametrosEjecucion = parametrosEjecucion;
   }

   public Collection<Object> getParametrosError() {
      return this.parametrosError;
   }

   public void setParametrosError(Collection<Object> parametrosError) {
      this.parametrosError = parametrosError;
   }
}
