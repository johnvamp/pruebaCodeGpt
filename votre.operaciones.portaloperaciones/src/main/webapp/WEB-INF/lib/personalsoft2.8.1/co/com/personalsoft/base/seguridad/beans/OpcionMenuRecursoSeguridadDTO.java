package co.com.personalsoft.base.seguridad.beans;

import java.io.Serializable;

public class OpcionMenuRecursoSeguridadDTO implements Serializable {
   private static final long serialVersionUID = -141449898987320417L;
   private String dsNombre;
   private String existe;
   private RecursoSeguridadDTO recursoSeguridadDTO;

   public RecursoSeguridadDTO getRecursoSeguridadDTO() {
      return this.recursoSeguridadDTO;
   }

   public void setRecursoSeguridadDTO(RecursoSeguridadDTO recursoSeguridadDTO) {
      this.recursoSeguridadDTO = recursoSeguridadDTO;
   }

   public String getDsNombre() {
      return this.dsNombre;
   }

   public void setDsNombre(String dsNombre) {
      this.dsNombre = dsNombre;
   }

   public String getExiste() {
      return this.existe;
   }

   public void setExiste(String existe) {
      this.existe = existe;
   }
}
