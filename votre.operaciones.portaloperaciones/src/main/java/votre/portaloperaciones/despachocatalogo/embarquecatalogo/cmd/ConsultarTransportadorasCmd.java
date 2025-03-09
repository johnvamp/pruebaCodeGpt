package votre.portaloperaciones.despachocatalogo.embarquecatalogo.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.despachocatalogo.embarquecatalogo.beans.TransportadorasDTO;
import votre.portaloperaciones.despachocatalogo.embarquecatalogo.delegate.EmbarqueCatalogoBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class ConsultarTransportadorasCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		TransportadorasDTO dto = null;
		EmbarqueCatalogoBusiness business = null;
		try{
			String mensaje = "";
			dto = new TransportadorasDTO();
			business = new EmbarqueCatalogoBusiness();
			
			String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
			
			dto.setCodCia(codCia);
			dto.setAccion(Constantes.ACCION_TRANSPORTADORAS);
			
			dto = business.consultarTransportadoras(dto);
			if(dto != null){
				if(dto.getStatus().equals(Constantes.EXITO_SP)){
					req.setAttribute("transportadoras", dto.getArrTrans());
				}
				else{
					mensaje = dto.getDsstatus();
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
