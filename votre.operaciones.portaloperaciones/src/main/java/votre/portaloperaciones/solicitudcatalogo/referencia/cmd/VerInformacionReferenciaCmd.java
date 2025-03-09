package votre.portaloperaciones.solicitudcatalogo.referencia.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.solicitudcatalogo.catalogo.beans.CatalogoDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.beans.CompradoraDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.delegate.CompradoraBusiness;
import votre.portaloperaciones.solicitudcatalogo.referencia.beans.AuditoriaDTO;
import votre.portaloperaciones.solicitudcatalogo.referencia.beans.ReferenciaDTO;
import votre.portaloperaciones.solicitudcatalogo.referencia.delegate.ReferenciaBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class VerInformacionReferenciaCmd implements IBaseCmd {
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String cedula;
	private String campana;
	private String nroLinea;
	private String nroCaso;
	private String sku;
	private String parametro;
	private String nombre;
	private String mensaje;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		CatalogoDTO  catalogo = new CatalogoDTO();
		CompradoraBusiness compradoraBusiness = new CompradoraBusiness();
		CompradoraDTO compradora = new CompradoraDTO();
		ReferenciaBusiness referenciaBusiness = new ReferenciaBusiness();
		ReferenciaDTO referencia = new ReferenciaDTO();
		AuditoriaDTO auditoria = new AuditoriaDTO();
		String siguienteRuta = "historial.jspRelacion.do";
		
		try{
			obtenerDatos(req);
			if(validarDatos()){
				dtoAssembler(catalogo,referencia);			
				compradora = compradoraBusiness.verInformacionCompradora(catalogo);
				referencia = referenciaBusiness.verInformacionReferencia(referencia);
				dtoAssembler(catalogo,referencia);
				auditoria = referenciaBusiness.verAuditoriaReferencia(referencia);
				
				if (compradora != null){
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
				if(referencia != null){
					if(referencia.getEstado().equals(Constantes.EXITO_SP)){
						req.setAttribute("registros", referencia.getRegistros());
						req.setAttribute("titulo1Ref", referencia.getTitulo1());
						req.setAttribute("titulo2Ref", referencia.getTitulo2());
						req.setAttribute("titulo3Ref", referencia.getTitulo3());
						req.setAttribute("titulo4Ref", referencia.getTitulo4());
						req.setAttribute("titulo5Ref", referencia.getTitulo5());
						req.setAttribute("titulo6Ref", referencia.getTitulo6());
					}
					else{
						mensaje = referencia.getDescripcion();
					}
				}
				
				if(auditoria != null){
					if(auditoria.getEstado().equals(Constantes.EXITO_SP)){
						req.setAttribute("registrosAudi", auditoria.getRegistros());
						req.setAttribute("titulo1Audi", auditoria.getTitulo1());
						req.setAttribute("titulo2Audi", auditoria.getTitulo2());
						req.setAttribute("titulo3Audi", auditoria.getTitulo3());
						req.setAttribute("titulo4Audi", auditoria.getTitulo4());
						req.setAttribute("titulo5Audi", auditoria.getTitulo5());					
					}
					else{
						mensaje = auditoria.getDescripcion();
					}
				}
			}		
			
			req.setAttribute("parametro", parametro);
			req.setAttribute("nombre", nombre);
			req.setAttribute("nroCaso", nroCaso);
			
			if (mensaje != null && !mensaje.equals("")){
				req.setAttribute("mensaje", mensaje);
				siguienteRuta = "catalogo.verCatalogo.do";
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
		campana = req.getParameter("campana") != null ? req.getParameter("campana") : "";
		nroCaso = req.getParameter("nroCaso") != null || req.getParameter("nroCaso") != "" ? req.getParameter("nroCaso") : "0";
		nroLinea = req.getParameter("nroLinea") != null || req.getParameter("nroLinea") != "" ? req.getParameter("nroLinea") : "0";
		sku = req.getParameter("sku") != null ? req.getParameter("sku") : "";
		parametro= req.getParameter("parametro") != null ? req.getParameter("parametro") : "";
		nombre= req.getParameter("nombre") != null ? req.getParameter("nombre") : "";
	}
	
	private boolean validarDatos(){
		if(cedula == null || cedula == ""){
			mensaje = "Debe seleccionar un parámetro de consulta.";
			return false;
		}		
		return true;
	}
	
	private void dtoAssembler(final CatalogoDTO catalogo, ReferenciaDTO referencia){
		catalogo.setCodCia(codCia);
		catalogo.setCedula(cedula);
		catalogo.setNroCaso(nroCaso);
		referencia.setCodCia(codCia);
		referencia.setCedula(cedula);
		referencia.setCampana(campana);
		referencia.setNroLinea(nroLinea);
		referencia.setSku(sku);
	}

}
