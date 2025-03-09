package co.com.personalsoft.base.seguridad.beans;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

public class RecursoSeguridadDTO implements Serializable {
   private static final long serialVersionUID = -7384602777843227243L;
   private String codigoRecurso;
   private String nombreRecurso;
   private MensajeDTO mensajeDTO = new MensajeDTO();
   private PerfilSeguridadDTO perfilSeguridadDTO;
   private Set<String> perfilesPermitidos;

   public Set<String> getPerfilesPermitidos() {
      return this.perfilesPermitidos;
   }

   public void setPerfilesPermitidos(Set<String> perfilesPermitidos) {
      this.perfilesPermitidos = perfilesPermitidos;
   }

   public String getCodigoRecurso() {
      return this.codigoRecurso;
   }

   public void setCodigoRecurso(String codigoRecurso) {
      this.codigoRecurso = codigoRecurso;
   }

   public String getNombreRecurso() {
      return this.nombreRecurso;
   }

   public void setNombreRecurso(String nombreRecurso) {
      this.nombreRecurso = nombreRecurso;
   }

   public String toString() {
      StringBuilder str = new StringBuilder();
      str.append("Código Rcurso" + this.getCodigoRecurso() + "\n");
      str.append("Nombre Recurso" + this.getNombreRecurso() + "\n");
      str.append(" Mensaje Usuario " + this.mensajeDTO.getMensajeUsuario() + "\n");
      str.append(" Mensaje Técnico " + this.mensajeDTO.getMensajeTecnico());
      if (this.perfilesPermitidos != null && !this.perfilesPermitidos.isEmpty()) {
         Iterator var3 = this.perfilesPermitidos.iterator();

         while(var3.hasNext()) {
            String nombre = (String)var3.next();
            str.append(" Código Perfil" + nombre + "\n");
         }
      }

      return str.toString();
   }

   public MensajeDTO getMensajeDTO() {
      return this.mensajeDTO;
   }

   public void setMensajeDTO(MensajeDTO mensajeDTO) {
      this.mensajeDTO = mensajeDTO;
   }

   public PerfilSeguridadDTO getPerfilSeguridadDTO() {
      return this.perfilSeguridadDTO;
   }

   public void setPerfilSeguridadDTO(PerfilSeguridadDTO perfilSeguridadDTO) {
      this.perfilSeguridadDTO = perfilSeguridadDTO;
   }
}
