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

public class ConsultarRequerimientosCmd implements IBaseCmd {

	private Logger logger = Logger.getLogger(this.getClass());
	private String mensaje;
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		SolicitudDTO solicitudDTO = null;
		ReprocesosFacade reprocesosFacade = null;
		try{
			reprocesosFacade = new ReprocesosFacade();
			solicitudDTO = new SolicitudDTO();
			ArrayList<SolicitudDTO> arrSolicitudes = new ArrayList<SolicitudDTO>();
			String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";;
			String accionTramite = (String) (req.getAttribute("accionTramite") != null ? req.getAttribute("accionTramite") : ""); 
			if(accionTramite.equals("")){
				accionTramite = req.getParameter("accionTramite") != null ? req.getParameter("accionTramite") : "";	
			}
			String accion = (String) (req.getAttribute("accion") != null ? req.getAttribute("accion") : ""); 
			if(accion.equals("")){
				accion = req.getParameter("accion") != null ? req.getParameter("accion") : "";
			}
			solicitudDTO.setCodCia(codCia);
			solicitudDTO.setNumRequerimiento("");
			solicitudDTO.setAccionTramite(accionTramite);
			solicitudDTO.setAccion(accion);
			
			String siguienteRuta = "";
			if(accion.equals(Constantes.ACCION_CONSULTAR_REFERENCIA)){
				siguienteRuta = "reprocesos.jspSolicitudInsumos.do";
			}
			if(accion.equals(Constantes.ACCION_CONSULTAR_SOLICITUD)){
				siguienteRuta = "reprocesos.jspEjecucionReproceso.do";
			}
			
			mensaje = (String) (req.getAttribute("mensaje") != null ? req.getAttribute("mensaje") : "");
			
			arrSolicitudes = reprocesosFacade.consultarSolicitudes(solicitudDTO);
			if(arrSolicitudes != null && arrSolicitudes.size() > 0){
					req.setAttribute("solicitudes", arrSolicitudes);
					req.setAttribute("nroRegistros", arrSolicitudes.size());
					req.setAttribute("registrosXPagina", Constantes.REGISTROSXPAGINA_REQUERIMIENTOS);
			}
			else{
				mensaje = "No se encontraron solicitudes.";
			}
			
			
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

}
