package votre.portaloperaciones.reprocesos.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.reprocesos.beans.SolicitudDTO;
import votre.portaloperaciones.reprocesos.facade.ReprocesosFacade;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class ProcesarReferenciaCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String numRequerimiento;
	private String referencia;
	private String accion;
	private String ubicacion;
	private String estado;
	private String cantidad;
	private String mensaje;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		SolicitudDTO solicitudDTO = null;
		ReprocesosFacade reprocesosFacade = null;
		try{
			solicitudDTO = new SolicitudDTO();
			reprocesosFacade = new ReprocesosFacade();
			obtenerDatos(req);
			dtoAssembler(solicitudDTO);
			
			solicitudDTO = reprocesosFacade.procesarReferencia(solicitudDTO);
			if(solicitudDTO != null){
				if(solicitudDTO.getStatus().equals(Constantes.EXITO_SP)){
					mensaje = "Referencia procesada con éxito.";
				}
				else{
					mensaje = solicitudDTO.getDsStatus();
				}
			}
			
			req.setAttribute("accionTramite", Constantes.ACCION_CONSULTAR_INSUMOS);
			req.setAttribute("accion", Constantes.ACCION_CONSULTAR_REFERENCIA);
			
			if(mensaje != null && mensaje != ""){
				req.setAttribute("mensaje", mensaje);
			}
			
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}
	
	public void obtenerDatos(HttpServletRequest req){
		codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		numRequerimiento = req.getParameter("numRequerimiento") != null ? req.getParameter("numRequerimiento") : "";
		referencia = req.getParameter("referencia") != null ? req.getParameter("referencia") : "";
		accion = req.getParameter("accion") != null ? req.getParameter("accion") : "";
		ubicacion = req.getParameter("ubicacion") != null ? req.getParameter("ubicacion") : "";
		estado = req.getParameter("estado") != null ? req.getParameter("estado") : "";
		cantidad = req.getParameter("cantidad") != null ? req.getParameter("cantidad") : "";
	}
	
	private void dtoAssembler(final SolicitudDTO dto){
		dto.setCodCia(codCia);
		dto.setNumRequerimiento(numRequerimiento);
		dto.setReferencia(referencia);
		dto.setAccion(accion);
		dto.setUbicacion(ubicacion);
		dto.setEstado(estado);
		dto.setCantidad(cantidad);
	}

}
