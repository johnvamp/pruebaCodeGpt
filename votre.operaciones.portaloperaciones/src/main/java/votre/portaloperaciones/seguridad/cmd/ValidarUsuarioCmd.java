package votre.portaloperaciones.seguridad.cmd;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.util.Constantes;
import votre.utils.moduloseguridad.ModuloSeguridad;
import votre.utils.moduloseguridad.beans.UsuarioDTO;
import co.com.personalsoft.base.beans.Campo;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.seguridad.encripcion.Blowfish;
import co.com.personalsoft.base.util.CargadorMsj;
import co.com.personalsoft.base.util.Validador;

public class ValidarUsuarioCmd implements IBaseCmd {
	private Logger logger = Logger.getLogger(this.getClass());
	private String parametro;
	private String usuario;
	private String password;
	private String compania;
	private String nombreCia;
	private String[] datos;
	private String mensaje ="";
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		
		String siguienteRuta = "seguridad.jspFrames.do";
		UsuarioDTO usuarioDTO = null;
		String strValidacion = null;
		ModuloSeguridad moduloSeguridad = new ModuloSeguridad();
		Blowfish blowfish = null;
		String pswDesencriptado = "";
		String aplicacion = Configuracion.getInstance().getParametroApp("COD_APP_PORTAL");
		String endpoint = Configuracion.getInstance().getServicio("ModuloSeguridad").getRuta();
		try{			
			obtenerDatos(req);
			
			if(!parametro.equals("logueo")){
				blowfish = new Blowfish();
				pswDesencriptado = blowfish.desencriptar(password.trim(), Constantes.SEMILLA);
			}
			else {
				pswDesencriptado = password.trim();
			}
			
			usuarioDTO = new UsuarioDTO();
			usuarioDTO.setUsuario(usuario.trim());
			usuarioDTO.setPassword(pswDesencriptado);
			usuarioDTO.setCodCompania(compania);
			usuarioDTO.setAplicacion(aplicacion);
			
			strValidacion = Validador.validarFormulario(validarInformacion(usuarioDTO));
			if(strValidacion.trim().equals("")){				
				usuarioDTO = moduloSeguridad.validarUsuario(usuarioDTO, endpoint);
				usuarioDTO = moduloSeguridad.consultarPerfil(usuarioDTO, endpoint);
				if(usuarioDTO.getError() == null){
					req.getSession().setAttribute(Constantes.USUARIO_SESION, usuarioDTO);
					req.getSession().setAttribute(Constantes.NOMBRE_USUARIO,usuarioDTO.getNombre());
					req.getSession().setAttribute(Constantes.COD_CIA, compania);
					req.getSession().setAttribute(Constantes.NOM_PAIS, nombreCia);
				}
				else{
					mensaje = usuarioDTO.getError();
				}
				
			}
			else{
				mensaje = strValidacion;
			}
			if (mensaje != null && !mensaje.equals("")){
				req.setAttribute("mensaje", mensaje);
				siguienteRuta = "login.do";
			}
			
			Configuracion.getInstance().getContext().getRequestDispatcher(siguienteRuta).forward(req, res);
		}
		catch(Exception e){
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}
	
	private void obtenerDatos(HttpServletRequest req) {
		parametro = req.getParameter("parametro") != null ? req.getParameter("parametro") : "";
		usuario = req.getParameter("usu") != null ? req.getParameter("usu").toUpperCase() : "";
		password = req.getParameter("psw") != null ? req.getParameter("psw") : "";
		compania = req.getParameter("cia") != null ? req.getParameter("cia") : "";
		nombreCia = req.getParameter("nomcia") != null ? req.getParameter("nomcia") : "";
		
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
