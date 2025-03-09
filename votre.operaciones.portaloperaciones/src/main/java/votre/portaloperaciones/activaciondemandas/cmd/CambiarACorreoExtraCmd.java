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

public class CambiarACorreoExtraCmd implements IBaseCmd {
	
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
			String referencias = req.getParameter("referencias") != null ? req.getParameter("referencias") : "";
			String descripciones = req.getParameter("descripciones") != null ? req.getParameter("descripciones") : "";
			referenciasDTO.setCodCia(codCia);
			referenciasDTO.setReferencia(referencias);
			referenciasDTO.setDescripRefe(descripciones);
			
			referenciasDTO = business.cambiarACorreoExtra(referenciasDTO);
			if(referenciasDTO != null){
				if(referenciasDTO.getEstado().equals(Constantes.EXITO_SP)){
					req.setAttribute("referencias", referenciasDTO.getReferencias());
					req.setAttribute("totalRefe", referenciasDTO.getReferencias().size());
					siguienteRuta = "activaciondemandas.jspResumenReferencias.do";
				}
				else{
					siguienteRuta = "activaciondemandas.consultarReferencias.do";
					req.setAttribute("accion", Constantes.ACCION_CAMBIAR_REFE);
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
