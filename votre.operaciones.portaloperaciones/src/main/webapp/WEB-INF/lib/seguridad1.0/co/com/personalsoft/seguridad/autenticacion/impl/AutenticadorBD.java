package co.com.personalsoft.seguridad.autenticacion.impl;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.seguridad.beans.AplicacionSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.UsuarioSeguridadDTO;
import co.com.personalsoft.seguridad.bd.UsuariosHelperBD;
import co.com.personalsoft.seguridad.servicio.GestorSeguridad;
import org.apache.log4j.Logger;

public class AutenticadorBD extends Autenticador {
   private Logger logger = Logger.getLogger(this.getClass());

   public UsuarioSeguridadDTO autenticar(UsuarioSeguridadDTO usuarioSeguridadDTO) {
      UsuarioSeguridadDTO autenticado = null;
      AplicacionSeguridadDTO aplicacionSeguridadDTO = null;

      try {
         aplicacionSeguridadDTO = (AplicacionSeguridadDTO)GestorSeguridad.getInstance().getParametro("aplicacion");
         if (aplicacionSeguridadDTO != null) {
            UsuariosHelperBD usuariosHelperBD = new UsuariosHelperBD();
            autenticado = usuariosHelperBD.autenticarUsuario(aplicacionSeguridadDTO, usuarioSeguridadDTO);
         }
      } catch (PersonalsoftException var5) {
         this.logger.error(var5.getTraza());
      }

      return autenticado;
   }
}
