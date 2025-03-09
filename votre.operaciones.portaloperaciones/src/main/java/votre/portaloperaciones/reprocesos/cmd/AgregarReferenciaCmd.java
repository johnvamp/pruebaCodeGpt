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

public class AgregarReferenciaCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String numRequerimiento;
	private String referencia;
	private String cantidad;
	private String fechaEntrega;
	private String tipoEntrega;
	private String usuario;
	private String mensaje;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		SolicitudDTO solicitudDTO = null;
		ReprocesosFacade reprocesosFacade = null;
		try{
			reprocesosFacade = new ReprocesosFacade();
			solicitudDTO = new SolicitudDTO();
			obtenerDatos(req);
			dtoAssembler(solicitudDTO);
			
			solicitudDTO = reprocesosFacade.agregarReferencia(solicitudDTO);
			if(solicitudDTO != null){
				if(!solicitudDTO.getStatus().equals(Constantes.EXITO_SP)){
					mensaje = solicitudDTO.getDsStatus();
				}
				if(solicitudDTO.getArrSolicitudes() != null && solicitudDTO.getArrSolicitudes().size() > 0){
					if(solicitudDTO.getArrSolicitudes().get(0).getStatus().equals(Constantes.EXITO_SP)){
						req.setAttribute("registros", solicitudDTO.getArrSolicitudes());
						req.setAttribute("numRegistros", solicitudDTO.getArrSolicitudes().size());
					}
					else{
						mensaje = solicitudDTO.getArrSolicitudes().get(0).getDsStatus();
					}
				}
			}
			req.setAttribute("numRequerimiento", numRequerimiento);
			req.setAttribute("fechaEntrega", fechaEntrega);
			req.setAttribute("usuario", usuario);
			req.setAttribute("tipoEntrega", tipoEntrega);
			
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
		referencia = req.getParameter("txtReferencia") != null ? req.getParameter("txtReferencia") : "";
		cantidad = req.getParameter("txtCantidad") != null ? req.getParameter("txtCantidad") : "";
		fechaEntrega = req.getParameter("fechaEntrega") != null ? req.getParameter("fechaEntrega") : "";
		tipoEntrega = req.getParameter("tipoEntrega") != null ? req.getParameter("tipoEntrega") : "";
		usuario = req.getParameter("usuario") != null ? req.getParameter("usuario") : "";
	}
	
	private void dtoAssembler(final SolicitudDTO dto){
		dto.setCodCia(codCia);
		dto.setNumRequerimiento(numRequerimiento);
		dto.setReferencia(referencia);
		dto.setCantidad(cantidad);
		dto.setAccionTramite("");
		dto.setAccion(Constantes.ACCION_CONSULTAR_REFERENCIA);
	}
}
