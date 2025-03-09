package co.com.personalsoft.seguridad.autorizacion.impl;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.seguridad.beans.RecursoSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.UsuarioSeguridadDTO;
import co.com.personalsoft.seguridad.autorizacion.interf.IAutorizador;
import co.com.personalsoft.seguridad.servicio.GestorSeguridad;

public class AutorizadorBD implements IAutorizador {
   public boolean tienePermiso(String perfil, String recurso) throws PersonalsoftException {
      RecursoSeguridadDTO recursoSeguridadDTO = GestorSeguridad.getInstance().getRecursoSeguridad(recurso);
      boolean permiso = false;
      if (recursoSeguridadDTO != null && recursoSeguridadDTO.getPerfilesPermitidos() != null && recursoSeguridadDTO.getPerfilesPermitidos().contains(perfil)) {
         permiso = true;
      }

      return permiso;
   }

   public boolean tienePermiso(UsuarioSeguridadDTO usuarioSeguridadDTO, String recurso) throws PersonalsoftException {
      RecursoSeguridadDTO recursoSeguridadDTO = GestorSeguridad.getInstance().getRecursoSeguridad(recurso);
      boolean permiso = false;
      if (recursoSeguridadDTO != null && recursoSeguridadDTO.getPerfilesPermitidos() != null && recursoSeguridadDTO.getPerfilesPermitidos().contains(usuarioSeguridadDTO.getPerfilSeguridadDTO().getCodigoPerfil())) {
         permiso = true;
      }

      return permiso;
   }
}
