package votre.portaloperaciones.activaciondemandas.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.activaciondemandas.beans.ReferenciasDTO;
import votre.portaloperaciones.activaciondemandas.delegate.ActivacionDemandasBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class ConsultarReferenciasCmd implements IBaseCmd {

	private Logger logger = Logger.getLogger(this.getClass());
	private String mensaje;
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		ReferenciasDTO referenciasDTO = null;
		ActivacionDemandasBusiness business = null;
		String siguienteRuta = "";
		try{
			referenciasDTO = new ReferenciasDTO();
			business = new ActivacionDemandasBusiness();
			
			String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";;
			String accion = req.getParameter("accion") != null ? req.getParameter("accion") : "";
			if("".equals(accion)){
				accion = (String) (req.getAttribute("accion") != null ? req.getAttribute("accion") : "");
			}
			referenciasDTO.setCodCia(codCia);
			referenciasDTO.setAccion(accion);
			
			if(accion.equals(Constantes.ACCION_REFERENCIAS)){
//				siguienteRuta = "activaciondemandas.consultarGuiasMaster.do";
				siguienteRuta = "activaciondemandas.jspAsignarTransportadores.do";
				req.setAttribute("accionMaster", Constantes.ACCION_GUIA_MASTER);
			}
			if(accion.equals(Constantes.ACCION_CAMBIAR_REFE)){
				siguienteRuta = "activaciondemandas.jspCambiarReferencias.do";
			}
			
			referenciasDTO = business.consultarReferencias(referenciasDTO);
			if(referenciasDTO != null){
				if(referenciasDTO.getEstado().equals(Constantes.EXITO_SP)){
					req.setAttribute("referencias", referenciasDTO.getReferencias());
					req.setAttribute("totalRefe", referenciasDTO.getReferencias().size());
					req.setAttribute("mostrarDiv", "true");
					req.setAttribute("banderaCheck", "checked");
					req.setAttribute("accion", Constantes.ACCION_REFERENCIAS);
					req.setAttribute("estado", referenciasDTO.getEstado());
				}
				else{
					mensaje = referenciasDTO.getDescripcion();
				}
			}
			
			if (mensaje != null && !mensaje.equals("")){
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
