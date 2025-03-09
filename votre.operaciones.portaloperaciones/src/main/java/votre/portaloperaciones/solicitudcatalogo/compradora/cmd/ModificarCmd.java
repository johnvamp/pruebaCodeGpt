package votre.portaloperaciones.solicitudcatalogo.compradora.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.seguridad.util.UtilSesion;
import votre.portaloperaciones.solicitudcatalogo.compradora.beans.CompradoraDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.delegate.CompradoraBusiness;
import votre.portaloperaciones.util.Constantes;
import votre.utils.moduloseguridad.beans.UsuarioDTO;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class ModificarCmd implements IBaseCmd {
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String cedula;
	private String nombreCompra;
	private String direccion;
	private String telefono1;
	private String telefono2;
	private String barrio;
	private String celular;
	private String municipio;
	private String departamento;
	private String codigoPostal;
	UsuarioDTO usuario = new UsuarioDTO();
	private String nroCaso;
	private String parametro;
	private String nombre;
	private String mensaje;
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		CompradoraBusiness compradoraBusiness = new CompradoraBusiness();
		CompradoraDTO compradora = new CompradoraDTO();
		String siguienteRuta = "";
		try{
			obtenerDatos(req);			
			dtoAssembler(compradora);
			compradora = compradoraBusiness.guardar(compradora);
			if(compradora != null){
				if(compradora.getEstado().equals(Constantes.EXITO_SP)){
					req.setAttribute("cedula", cedula);
					req.setAttribute("parametro", parametro);
					req.setAttribute("nombre",nombre);
					siguienteRuta = "compradora.verInformacionCompradora.do";
				}
				else{
					mensaje = compradora.getDescripcion();
				}
			}						

			if (mensaje != null && !mensaje.equals("")){
				req.setAttribute("mensaje", mensaje);				
				siguienteRuta = "compradora.modificar.do";
			}
			
			Configuracion.getInstance().getContext().getRequestDispatcher(siguienteRuta).forward(req, res);
		}
		catch (Exception e){
			logger.error(new PersonalsoftException(e).getTraza());
		}
		
	}

	private void obtenerDatos(HttpServletRequest req) {
		codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		cedula = req.getParameter("cedula") != null || req.getParameter("cedula") != "" ? req.getParameter("cedula") : "0";
		nombreCompra= req.getParameter("nombreCompra") != null ? req.getParameter("nombreCompra") : "";
		direccion= req.getParameter("direccion") != null ? req.getParameter("direccion") : "";
		telefono1= req.getParameter("telefono1") != null || req.getParameter("telefono1") != ""  ? req.getParameter("telefono1") : "0";
		telefono2= req.getParameter("telefono2") != null || req.getParameter("telefono2") != ""  ? req.getParameter("telefono2") : "0";
		barrio = req.getParameter("barrio") != null ? req.getParameter("barrio") : "";
		celular = req.getParameter("celular") != null || req.getParameter("celular") != ""  ? req.getParameter("celular") : "0";
		municipio = req.getParameter("municipio") != null ? req.getParameter("municipio") : "";
		departamento = req.getParameter("departamento") != null ? req.getParameter("departamento") : "";
		codigoPostal = req.getParameter("codigoPostal") != null ? req.getParameter("codigoPostal") : "";
		usuario= (UsuarioDTO) UtilSesion.getObjetoEnSesion(req, Constantes.USUARIO_SESION);
		nroCaso = req.getParameter("nroCaso") != null ? req.getParameter("nroCaso") : "";
		parametro= req.getParameter("parametro") != null ? req.getParameter("parametro") : "";
		nombre= req.getParameter("nombre") != null ? req.getParameter("nombre") : "";
	}
	
	private void dtoAssembler(final CompradoraDTO compradora){
		compradora.setCodCia(codCia);
		compradora.setCedula(cedula);
		compradora.setNombre(nombreCompra);
		compradora.setDireccion(direccion.toUpperCase());
		compradora.setTelefono1(telefono1);
		compradora.setTelefono2(telefono2);
		compradora.setBarrio(barrio.toUpperCase());
		compradora.setCelular(celular);
		compradora.setMunicipio(municipio.toUpperCase());
		compradora.setDepartamento(departamento);
		compradora.setCodigoPostal(codigoPostal);
		compradora.setSku("");
		compradora.setDescripRefer("");
		compradora.setCantidad("0");
		compradora.setAccion(Constantes.ACCION_MODIFICAR);
		if(usuario.getUsuario().length() > 10){
			compradora.setUsuario(usuario.getUsuario().substring(0, 10));
		}
		else{
			compradora.setUsuario(usuario.getUsuario());
		}
		compradora.setNroCaso(nroCaso);
	}
}
