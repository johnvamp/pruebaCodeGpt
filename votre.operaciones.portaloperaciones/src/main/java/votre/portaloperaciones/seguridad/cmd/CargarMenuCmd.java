package votre.portaloperaciones.seguridad.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.seguridad.util.UtilSesion;
import votre.portaloperaciones.util.Constantes;
import votre.utils.moduloseguridad.ModuloSeguridad;
import votre.utils.moduloseguridad.beans.UsuarioDTO;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class CargarMenuCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		
		try{
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO =  (UsuarioDTO) UtilSesion.getObjetoEnSesion(req, Constantes.USUARIO_SESION);
			String xml = "";
			ModuloSeguridad  moduloSeguridad = new ModuloSeguridad();
			String endpoint = Configuracion.getInstance().getServicio("ModuloSeguridad").getRuta();
			xml = (String) moduloSeguridad.consultarOpciones(usuarioDTO, endpoint,"contenido");
			//System.err.println(xml);
			req.setAttribute("menuXml", xml);
			
			
		}catch(Exception e){
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}
}
