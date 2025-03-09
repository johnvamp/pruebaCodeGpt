package votre.portaloperaciones.solicitudcatalogo.compradora.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.solicitudcatalogo.compradora.beans.CompradoraDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.delegate.CompradoraBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class EliminarCmd implements IBaseCmd {
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String cedula;
	private String fila;
	private String sku;
	private String cantidad;
	private String parametro;
	private String nombre;
	private String nroCaso;
	private String mensaje;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		CompradoraBusiness compradoraBusiness = new CompradoraBusiness();
		CompradoraDTO compradora = new CompradoraDTO();	
		String siguienteRuta = "compradora.verInformacionCompradora.do";
		try{
			obtenerDatos(req);
			dtoAssembler(compradora);
			compradora = compradoraBusiness.eliminar(compradora);
			if(compradora != null){
				if(compradora.getEstado().equals(Constantes.EXITO_SP)){	
					req.setAttribute("cedula", cedula);
					req.setAttribute("nroCaso", nroCaso);
					req.setAttribute("parametro", parametro);
					req.setAttribute("nombre",nombre);
				}
				else{
					mensaje = compradora.getDescripcion();
				}
			}

			if (mensaje != null && !mensaje.equals("")){
				req.setAttribute("mensaje", mensaje);				
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
		fila = req.getParameter("nroLinea") != null ? req.getParameter("nroLinea") : "";
		nroCaso = req.getParameter("nroCaso") != null ? req.getParameter("nroCaso") : "";
		sku = req.getParameter("sku") != null ? req.getParameter("sku") : "";		
		cantidad = req.getParameter("cantidad") != null || req.getParameter("cantidad") != ""  ? req.getParameter("cantidad") : "0";
		parametro= req.getParameter("parametro") != null ? req.getParameter("parametro") : "";
		nombre= req.getParameter("nombre") != null ? req.getParameter("nombre") : "";
	}
	
	private void dtoAssembler(final CompradoraDTO compradora){
		compradora.setCodCia(codCia);
		compradora.setCedula(cedula);
		compradora.setSku(sku);		
		compradora.setCantidad(cantidad);
		compradora.setFila(fila);
	}
}
