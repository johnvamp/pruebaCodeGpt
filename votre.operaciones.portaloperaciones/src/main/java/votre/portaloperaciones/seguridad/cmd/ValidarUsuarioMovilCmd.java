package votre.portaloperaciones.seguridad.cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.util.Constantes;
import votre.portaloperaciones.util.JSON;
import votre.utils.moduloseguridad.ModuloSeguridad;
import votre.utils.moduloseguridad.beans.UsuarioDTO;
import co.com.personalsoft.base.beans.Campo;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.Validador;

public class ValidarUsuarioMovilCmd implements IBaseCmd {
	private String usuario;
	private String password;
	private String compania;
	private String nombreCia;
	private String infoDevice;
	private String[] datos;
	private String mensaje ="";
	private Logger info = Logger.getLogger("info");
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		
		UsuarioDTO usuarioDTO = null;
		String strValidacion = null;
		ModuloSeguridad moduloSeguridad = new ModuloSeguridad();
		String aplicacion = Configuracion.getInstance().getParametroApp("COD_APP_PORTAL");
		String endpoint = Configuracion.getInstance().getServicio("ModuloSeguridad").getRuta();
		Map<String,Object> salida = new HashMap<String,Object>();
		
		obtenerDatos(req);
		
		usuarioDTO = new UsuarioDTO();
		usuarioDTO.setUsuario(usuario.trim());
		usuarioDTO.setPassword(password);
		usuarioDTO.setCodCompania(compania);
		usuarioDTO.setAplicacion(aplicacion);
		
		strValidacion = Validador.validarFormulario(validarInformacion(usuarioDTO));
		if(strValidacion.trim().equals("")){				
			usuarioDTO = moduloSeguridad.validarUsuario(usuarioDTO, endpoint);
			usuarioDTO = moduloSeguridad.consultarPerfil(usuarioDTO, endpoint);
			if(usuarioDTO.getError() == null){
				salida.put(Constantes.USUARIO_SESION, usuarioDTO);
				salida.put(Constantes.NOMBRE_USUARIO,usuarioDTO.getNombre());
				salida.put(Constantes.COD_CIA, compania);
				salida.put(Constantes.NOM_PAIS, nombreCia);
				
				info.info("El usuario: "+usuario+", se ha logueado en la App Tranportistas. Info Device: "+infoDevice);
			}
			else{
				mensaje = usuarioDTO.getError();
			}
		}
		else{
			mensaje = strValidacion;
		}
		
		if (mensaje != null && !mensaje.equals("")){
			salida.put("mensaje", mensaje);
		}
		
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
	
	private void obtenerDatos(HttpServletRequest req) {
		usuario = req.getParameter("usu") != null ? req.getParameter("usu").toUpperCase() : "";
		password = req.getParameter("psw") != null ? req.getParameter("psw") : "";
		compania = req.getParameter("cia") != null ? req.getParameter("cia") : "";
		nombreCia = req.getParameter("nomcia") != null ? req.getParameter("nomcia") : "";
		infoDevice = req.getParameter("infoDevice") != null ? req.getParameter("infoDevice") : "";
		
		if(compania.length() > 3){
			datos = compania.split("-");
			compania = datos[0];
			nombreCia = datos[1];
		}
	}
	
	private Collection<Campo> validarInformacion(UsuarioDTO usuarioDTO){		
		ArrayList<Campo> datos = new ArrayList<Campo>();
		datos.add(new Campo(usuarioDTO.getUsuario(),Validador.STRING,true,"Usuario",false));
		datos.add(new Campo(usuarioDTO.getPassword(),Validador.STRING,true,"Clave",false));
		datos.add(new Campo(usuarioDTO.getCodCompania(),Validador.STRING,true,"CodCompania",false));
		return datos;
	}
	
}
