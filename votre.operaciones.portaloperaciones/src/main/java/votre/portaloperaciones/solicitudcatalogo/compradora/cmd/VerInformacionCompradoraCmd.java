package votre.portaloperaciones.solicitudcatalogo.compradora.cmd;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.solicitudcatalogo.catalogo.beans.CatalogoDTO;
import votre.portaloperaciones.solicitudcatalogo.catalogo.delegate.CatalogoBusiness;
import votre.portaloperaciones.solicitudcatalogo.compradora.beans.CatalogoCompradoraDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.beans.CompradoraDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.beans.EnviosDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.delegate.CompradoraBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.beans.Campo;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.Validador;

public class VerInformacionCompradoraCmd implements IBaseCmd {
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String cedula;
	private String parametro;
	private String nroCaso;
	private String nombre;
	private String mensaje;
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		CatalogoBusiness catalogoBusiness = new CatalogoBusiness();
		CatalogoDTO catalogo = new CatalogoDTO();
		CompradoraBusiness compradoraBusiness = new CompradoraBusiness();
		CompradoraDTO compradora = new CompradoraDTO();
		CatalogoCompradoraDTO catalogoCompradora = new CatalogoCompradoraDTO();
		EnviosDTO envios = new EnviosDTO();
		String strValidacion = null;
		
		try{
			obtenerDatos(req);
			dtoAssembler(catalogo);
			strValidacion = Validador.validarFormulario(validarInformacion(catalogo));
			if(strValidacion.trim().equals("")){
				if(parametro.equals("")){
					catalogo = catalogoBusiness.verCatalogo(catalogo);
				}
				
				if(parametro.equals("listado") || catalogo != null){
					if(parametro.equals("listado") || catalogo.getEstado().equals(Constantes.EXITO_SP)){
						compradora = compradoraBusiness.verInformacionCompradora(catalogo);
						catalogoCompradora = compradoraBusiness.consultarCatalogos(codCia);
						envios = compradoraBusiness.verResumenEnvios(compradora);
						
						if(compradora != null){
							if(compradora.getEstado().equals(Constantes.EXITO_SP)){
								req.setAttribute("titulo1", compradora.getTitulo1());
								req.setAttribute("titulo2", compradora.getTitulo2());
								req.setAttribute("titulo3", compradora.getTitulo3());
								req.setAttribute("titulo4", compradora.getTitulo4());
								req.setAttribute("titulo5", compradora.getTitulo5());
								req.setAttribute("titulo6", compradora.getTitulo6());
								req.setAttribute("titulo7", compradora.getTitulo7());
								req.setAttribute("titulo8", compradora.getTitulo8());
								req.setAttribute("titulo9", compradora.getTitulo9());
								req.setAttribute("titulo10", compradora.getTitulo10());
								req.setAttribute("titulo11", compradora.getTitulo11());
								req.setAttribute("titulo12", compradora.getTitulo12());
								req.setAttribute("titulo13", compradora.getTitulo13());
								req.setAttribute("cedula", compradora.getCedula());
								req.setAttribute("nombreCompra", compradora.getNombreCompradora());
								req.setAttribute("direccion", compradora.getDireccion());
								req.setAttribute("telefono1", compradora.getTelefono1());
								req.setAttribute("telefono2", compradora.getTelefono2());
								req.setAttribute("barrio", compradora.getBarrio());
								req.setAttribute("celular", compradora.getCelular());
								req.setAttribute("municipio", compradora.getMunicipio());
								req.setAttribute("departamento", compradora.getDepartamento());
								req.setAttribute("codigoPostal", compradora.getCodigoPostal());
								req.setAttribute("pais", compradora.getPais());	
							}
							else{
								mensaje = compradora.getDescripcion();
							}						
						}
						if (catalogoCompradora != null){
							if(catalogoCompradora.getEstado().equals(Constantes.EXITO_SP)){
								req.setAttribute("catalogos", catalogoCompradora.getRegistros());
								req.setAttribute("titulo1Cata", catalogoCompradora.getTitulo1());
								req.setAttribute("titulo2Cata", catalogoCompradora.getTitulo2());
								req.setAttribute("titulo3Cata", catalogoCompradora.getTitulo3());
								req.setAttribute("titulo4Cata", catalogoCompradora.getTitulo4());
								req.setAttribute("boton", catalogoCompradora.getBoton());
								req.setAttribute("estadoCata", catalogoCompradora.getEstado());
							}
							else{
								req.setAttribute("estadoCata", catalogoCompradora.getEstado());
								req.setAttribute("descripCata",catalogoCompradora.getDescripcion());
							}							
						}
						if(envios != null){
							if(envios.getEstado().equals(Constantes.EXITO_SP)){
								req.setAttribute("registros", envios.getRegistros());
								req.setAttribute("titulo1His", envios.getTitulo1());
								req.setAttribute("titulo2His", envios.getTitulo2());
								req.setAttribute("titulo3His", envios.getTitulo3());
								req.setAttribute("titulo4His", envios.getTitulo4());
								req.setAttribute("titulo5His", envios.getTitulo5());
								req.setAttribute("titulo6His", envios.getTitulo6());
								req.setAttribute("titulo7His", envios.getTitulo7());
								req.setAttribute("estado", envios.getEstado());
							}
							else{
								req.setAttribute("estado", envios.getEstado());
							}
						}
					}
					else{
						mensaje = catalogo.getDescripcion();
					}
				}
			}
			else{
				mensaje = strValidacion;
			}
			
			String siguienteRuta = "compradora.jspInformacion.do";
			req.setAttribute("parametro", parametro);
			req.setAttribute("codCia", codCia);
			req.setAttribute("nombre", nombre);
			req.setAttribute("nroCaso", nroCaso);
			
			if (mensaje != null && !mensaje.equals("")){
				req.setAttribute("mensaje", mensaje);
				if(parametro.equals("listado")){
					siguienteRuta = "compradora.jspListado.do";
				}
				else{
					siguienteRuta = "catalogo.verCatalogo.do";
				}
				
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
		if(cedula == null){
			cedula = (String) req.getAttribute("cedula");
		}
		parametro= req.getParameter("parametro") != null ? req.getParameter("parametro") : "";
		nroCaso= req.getParameter("nroCaso") != null ? req.getParameter("nroCaso") : "";
		nombre= req.getParameter("nombre") != null ? req.getParameter("nombre") : "";		
	}	
	
	private void dtoAssembler(final CatalogoDTO catalogo){
		catalogo.setCodCia(codCia);
		catalogo.setCedula(cedula);
		catalogo.setNroCaso(nroCaso);
		catalogo.setAccion(Constantes.ACCION_VALIDAR);
	}
	
	private Collection<Campo> validarInformacion(CatalogoDTO catalogoDTO){		
		ArrayList<Campo> datos = new ArrayList<Campo>();
		datos.add(new Campo(catalogoDTO.getCodCia(),Validador.STRING,true,"CodCIa",false));
		datos.add(new Campo(catalogoDTO.getCedula(),Validador.STRING,true,"Cedula",false));
		return datos;
	}
}
