package votre.portaloperaciones.seguridad.cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import votre.portaloperaciones.util.JSON;
import votre.utils.moduloseguridad.ModuloSeguridad;
import votre.utils.moduloseguridad.beans.UsuarioDTO;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class ConsultarCompaniasMovilCmd implements IBaseCmd{

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		
		ArrayList<UsuarioDTO> companias = new ArrayList<UsuarioDTO>();
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		ModuloSeguridad moduloSeguridad = new ModuloSeguridad();
		String aplicacion = Configuracion.getInstance().getParametroApp("COD_APP_PORTAL");
		String endpoint = Configuracion.getInstance().getServicio("ModuloSeguridad").getRuta();
		Map<String,Object> salida = new HashMap<String,Object>();
		
		usuarioDTO.setUsuario(req.getParameter("usu") != null ? req.getParameter("usu").toUpperCase() : "");
		usuarioDTO.setAplicacion(aplicacion);
		companias = moduloSeguridad.consultarCompanias(usuarioDTO, endpoint);
		salida.put("companias", companias);
		
		try{
			res.setContentType("application/json; charset='UTF-8'");
			res.setHeader("Cache-Control", "must-revalidate, no-cache, no-store, max-age=0");
			res.setHeader("Pragma", "no-cache");
			res.setDateHeader("Expires", 0);
			res.getWriter().write( JSON.dump(salida) );
		}catch (IOException e) {
			throw new PersonalsoftException(e);
		}
	}
}
