package votre.portaloperaciones.seguridad.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.utils.moduloseguridad.ModuloSeguridad;
import votre.utils.moduloseguridad.beans.UsuarioDTO;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class CambiarClaveCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private String usuario;
	private String password;
	private String newPassword;
	private String mensaje;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		String siguienteRuta = "jspCambiarClave.do";
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		ModuloSeguridad moduloSeguridad = new ModuloSeguridad();
		String endpoint = Configuracion.getInstance().getServicio("ModuloSeguridad").getRuta();
		try{
			obtenerDatos(req);
			dtoAssembler(usuarioDTO);
			usuarioDTO = moduloSeguridad.cambiarClave(usuarioDTO, endpoint);
			if(usuarioDTO != null){
				mensaje = usuarioDTO.getError();
				if(mensaje.equals("")){
					mensaje = "Cambio de contraseña exitoso";
				}
			}
			
			if(mensaje != null && !mensaje.equals("")){
				req.setAttribute("mensaje", mensaje);
			}
			
			Configuracion.getInstance().getContext().getRequestDispatcher(siguienteRuta).forward(req, res);
		}
		catch(Exception e){
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}
	
	private void obtenerDatos(HttpServletRequest req) {
		usuario = req.getParameter("usu") != null ? req.getParameter("usu").toUpperCase() : "";
		password = req.getParameter("psw") != null ? req.getParameter("psw") : "";
		newPassword = req.getParameter("newpsw") != null ? req.getParameter("newpsw") : "";
	}
	
	private void dtoAssembler(final UsuarioDTO usuarioDTO){
		usuarioDTO.setUsuario(usuario);
		usuarioDTO.setPassword(password);
		usuarioDTO.setNewPassword(newPassword);
	}

}
