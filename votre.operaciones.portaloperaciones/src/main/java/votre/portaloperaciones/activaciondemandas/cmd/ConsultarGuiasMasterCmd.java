package votre.portaloperaciones.activaciondemandas.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.activaciondemandas.beans.GuiasMasterDTO;
import votre.portaloperaciones.activaciondemandas.delegate.ActivacionDemandasBusiness;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class ConsultarGuiasMasterCmd implements IBaseCmd {

	private Logger logger = Logger.getLogger(this.getClass());
	private String mensaje;
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		GuiasMasterDTO guiasMasterDTO = null;
		ActivacionDemandasBusiness business = null;
		try{
			guiasMasterDTO = new GuiasMasterDTO();
			business = new ActivacionDemandasBusiness();
			
			String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
			String accion = req.getParameter("accionMaster") != null ? req.getParameter("accionMaster") : "";
			if("".equals(accion)){
				accion = (String) (req.getAttribute("accionMaster") != null ? req.getAttribute("accionMaster") : "");
			}
			guiasMasterDTO.setCodCia(codCia);
			guiasMasterDTO.setAccion(accion);
			guiasMasterDTO.setCedulaTrans("");
			guiasMasterDTO.setCodigoTrans("");
			guiasMasterDTO.setCantidad("");
			
			guiasMasterDTO = business.consultarGuiasMaster(guiasMasterDTO);
			if(guiasMasterDTO != null){
				if(guiasMasterDTO.getEstado().equals(Constantes.EXITO_SP)){
					req.setAttribute("guias", guiasMasterDTO.getGuias());
					req.setAttribute("nroGuiasMaster", guiasMasterDTO.getGuias() != null ? guiasMasterDTO.getGuias().size() : "0");
				}
				else{
					mensaje = guiasMasterDTO.getDescripcion();
				}
			}
			
			if (mensaje != null && !mensaje.equals("")){
				req.setAttribute("mensaje", mensaje);				
			}
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}

}
