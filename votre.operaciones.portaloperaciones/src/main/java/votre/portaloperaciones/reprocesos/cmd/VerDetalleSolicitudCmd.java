package votre.portaloperaciones.reprocesos.cmd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.reprocesos.beans.SolicitudDTO;
import votre.portaloperaciones.reprocesos.facade.ReprocesosFacade;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;
import co.com.personalsoft.base.util.Fecha;

public class VerDetalleSolicitudCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String numRequerimiento;
	private String fechaCreacion;
	private String fechaEntrega;
	private String tipoEntrega;
	private String observacion;
	private String accionTramite;
	private String accion;
	private String mensaje;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		SolicitudDTO solicitudDTO = null;
		ReprocesosFacade reprocesosFacade = null;
		try{
			String siguienteRuta = "";
			solicitudDTO = new SolicitudDTO();
			reprocesosFacade = new ReprocesosFacade();
			obtenerDatos(req);
			dtoAssembler(solicitudDTO);
			ArrayList<SolicitudDTO> arrReferencias = new ArrayList<SolicitudDTO>();
			
			if(accion.equals(Constantes.ACCION_CONSULTAR_SOLICITUD)){
				siguienteRuta = "reprocesos.jspDetalleSolicitud.do";
			}
			if(accion.equals(Constantes.ACCION_CONSULTAR_REPROCESO)){
				siguienteRuta = "reprocesos.jspDetalleReproceso.do";
			}
			
			arrReferencias = reprocesosFacade.consultarSolicitudes(solicitudDTO);
			if(arrReferencias != null && arrReferencias.size() > 0){
				req.setAttribute("referencias", arrReferencias);
				req.setAttribute("nroRegistros", arrReferencias.size());
				req.setAttribute("registrosXPagina", Constantes.REGISTROSXPAGINA_SOLICITUD);
			}
			else{
				mensaje = "No se encontraron referencias agregadas.";
			}
			
			req.setAttribute("numRequerimiento", numRequerimiento);
			req.setAttribute("fechaCreacion", fechaCreacion);
			req.setAttribute("fechaEntrega", fechaEntrega);
			req.setAttribute("fechaOriginal", Fecha.cambiarFormatoFecha(fechaEntrega, Fecha.EXPR_DDMMYYYY_LINEA, Fecha.EXPR_YYYYMMDD_LINEA));
			req.setAttribute("tipoEntrega", tipoEntrega);
			req.setAttribute("observacion", observacion);
			
			if(mensaje != null && mensaje != ""){
				req.setAttribute("mensaje", mensaje);
			}
			
			Configuracion.getInstance().getContext().getRequestDispatcher(siguienteRuta).forward(req, res);
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}

	public void obtenerDatos(HttpServletRequest req){
		codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		numRequerimiento = req.getParameter("numRequerimiento") != null ? req.getParameter("numRequerimiento") : "";
		fechaCreacion = req.getParameter("fechaCreacion") != null ? req.getParameter("fechaCreacion") : "";
		fechaEntrega = req.getParameter("fechaEntrega") != null ? req.getParameter("fechaEntrega") : "";
		tipoEntrega = req.getParameter("tipoEntrega") != null ? req.getParameter("tipoEntrega") : "";
		observacion = req.getParameter("observacion") != null ? req.getParameter("observacion") : "";
		accionTramite = req.getParameter("accionTramite") != null ? req.getParameter("accionTramite") : "";
		accion = req.getParameter("accion") != null ? req.getParameter("accion") : "";
	}
	
	private void dtoAssembler(final SolicitudDTO dto){
		dto.setCodCia(codCia);
		dto.setNumRequerimiento(numRequerimiento);
		dto.setAccionTramite(accionTramite);
		dto.setAccion(Constantes.ACCION_CONSULTAR_REFERENCIA);
	}
}
