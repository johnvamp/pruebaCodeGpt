package votre.portaloperaciones.seguridad.cmd;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.log4j.Logger;

import votre.utils.moduloseguridad.ModuloSeguridad;
import votre.utils.moduloseguridad.beans.UsuarioDTO;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class ConsultarCompaniasCmd implements IBaseCmd{
	private Logger logger = Logger.getLogger(this.getClass());

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		
		AjaxXmlBuilder xml = new AjaxXmlBuilder();
		ArrayList<UsuarioDTO> companias = new ArrayList<UsuarioDTO>();
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		ModuloSeguridad moduloSeguridad = new ModuloSeguridad();
		String aplicacion = Configuracion.getInstance().getParametroApp("COD_APP_PORTAL");
		String endpoint = Configuracion.getInstance().getServicio("ModuloSeguridad").getRuta();
		try{
			usuarioDTO.setUsuario(req.getParameter("usuario") != null ? req.getParameter("usuario").toUpperCase() : "");
			usuarioDTO.setAplicacion(aplicacion);
			
			companias = moduloSeguridad.consultarCompanias(usuarioDTO, endpoint);	
			
			xml.addItems(companias,"compania","codCompania");
			res.setContentType("text/xml; charset=UTF-8");
			
			PrintWriter printwriter;
			printwriter = res.getWriter();
			printwriter.write(xml.toString());
			printwriter.close();
		}
		catch(Exception e){
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}

}
