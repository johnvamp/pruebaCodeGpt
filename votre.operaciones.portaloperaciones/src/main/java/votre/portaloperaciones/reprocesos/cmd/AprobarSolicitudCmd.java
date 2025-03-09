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
import co.com.personalsoft.base.util.Fecha;

public class AprobarSolicitudCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String numRequerimiento;
	private String fechaInicio;
	private String fechaEntrega;
	private String comentario;
	private String accion;
	private String mensaje;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		SolicitudDTO solicitudDTO = null;
		ReprocesosFacade reprocesosFacade = null;
		try{
			solicitudDTO = new SolicitudDTO();
			reprocesosFacade = new ReprocesosFacade();
			obtenerDatos(req);
			dtoAssembler(solicitudDTO);
			
			solicitudDTO = reprocesosFacade.aprobarSolicitud(solicitudDTO);
			if(solicitudDTO != null){
				if(solicitudDTO.getStatus().equals(Constantes.EXITO_SP)){
					if(accion.equals(Constantes.ACCION_APROBAR_SOLICITUD)){
						mensaje = "Solicitud aprobada.";
					}
					if(accion.equals(Constantes.ACCION_MODIFICAR_SOLICITUD)){
						mensaje = "Solicitud modificada y aprobada.";
					}
					if(accion.equals(Constantes.ACCION_RECHAZAR_SOLICITUD)){
						mensaje = "Solicitud rechazada.";
					}
				}
				else{
					mensaje = solicitudDTO.getDsStatus(); 
				}
			}
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
		accion = req.getParameter("accion") != null ? req.getParameter("accion") : "";
		fechaInicio = req.getParameter("fechaInicio") != null ? req.getParameter("fechaInicio") : "";
		fechaInicio = Fecha.cambiarFormatoFecha(fechaInicio, Fecha.EXPR_YYYYMMDD_LINEA, Fecha.EXPR_YYYYMMDD);
		fechaEntrega = req.getParameter("fechaEntrega") != null ? req.getParameter("fechaEntrega") : "";
		fechaEntrega = Fecha.cambiarFormatoFecha(fechaEntrega, Fecha.EXPR_YYYYMMDD_LINEA, Fecha.EXPR_YYYYMMDD);
		comentario = req.getParameter("comentario") != null ? req.getParameter("comentario") : "";
	}
	
	private void dtoAssembler(final SolicitudDTO dto){
		dto.setCodCia(codCia);
		dto.setNumRequerimiento(numRequerimiento);
		dto.setAccion(accion);
		dto.setFechaInicio(fechaInicio);
		dto.setFechaEntrega(fechaEntrega);
		dto.setObservacion(comentario);
		
	}
}
