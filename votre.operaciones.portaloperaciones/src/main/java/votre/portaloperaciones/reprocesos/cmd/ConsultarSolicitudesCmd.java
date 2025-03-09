package votre.portaloperaciones.reprocesos.cmd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.reprocesos.beans.SolicitudDTO;
import votre.portaloperaciones.reprocesos.facade.ReprocesosFacade;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class ConsultarSolicitudesCmd implements IBaseCmd {

	private Logger logger = Logger.getLogger(this.getClass());
	private String mensajeAprobar = "";
	private String mensajeTramite = "";
	private String mensaje;
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		SolicitudDTO solicitudDTO = null;
		ReprocesosFacade reprocesosFacade = null;
		try{
			reprocesosFacade = new ReprocesosFacade();
			solicitudDTO = new SolicitudDTO();
			ArrayList<SolicitudDTO> arrSolicitudes = new ArrayList<SolicitudDTO>();
			ArrayList<SolicitudDTO> arrSolicitudesTramite = new ArrayList<SolicitudDTO>();
			String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";;
			solicitudDTO.setCodCia(codCia);
			solicitudDTO.setNumRequerimiento("");
			solicitudDTO.setAccionTramite("");
			solicitudDTO.setAccion(Constantes.ACCION_CONSULTAR_SOLICITUD);
			
			arrSolicitudes = reprocesosFacade.consultarSolicitudes(solicitudDTO);
			if(arrSolicitudes != null && arrSolicitudes.size() > 0){
					req.setAttribute("solicitudesAprobar", arrSolicitudes);
					req.setAttribute("nroRegistros", arrSolicitudes.size());
					req.setAttribute("registrosXPagina", Constantes.REGISTROSXPAGINA_SOLICITUD);
			}
			else{
				mensajeAprobar = "No se encontraron solicitudes por aprobar.";
			}
			solicitudDTO.setAccionTramite(Constantes.ACCION_CONSULTAR_TRAMITE);
			arrSolicitudesTramite = reprocesosFacade.consultarSolicitudes(solicitudDTO);
			if(arrSolicitudesTramite != null && arrSolicitudesTramite.size() > 0){
				req.setAttribute("solicitudesTramite", arrSolicitudesTramite);
				req.setAttribute("nroRegistrosT", arrSolicitudesTramite.size());
				req.setAttribute("registrosXPagina", Constantes.REGISTROSXPAGINA_SOLICITUD);
			}
			else{
				mensajeTramite = "No se encontraron solicitudes en tramite.";
			}
			
			if(mensajeAprobar != null && mensajeAprobar != ""){
				req.setAttribute("mensajeAprobar", mensajeAprobar);
			}
			
			if(mensajeTramite != null && mensajeTramite != ""){
				req.setAttribute("mensajeTramite", mensajeTramite);
			}
			mensaje = (String) (req.getAttribute("mensaje") != null ? req.getAttribute("mensaje") : "");
			if(mensaje != null && mensaje != ""){
				req.setAttribute("mensaje", mensaje);
			}
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}

}
