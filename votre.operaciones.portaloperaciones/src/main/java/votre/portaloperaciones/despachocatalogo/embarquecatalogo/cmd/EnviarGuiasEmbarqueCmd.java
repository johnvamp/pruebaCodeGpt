package votre.portaloperaciones.despachocatalogo.embarquecatalogo.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.despachocatalogo.embarquecatalogo.beans.GuiasEmbarqueDTO;
import votre.portaloperaciones.despachocatalogo.embarquecatalogo.delegate.EmbarqueCatalogoBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class EnviarGuiasEmbarqueCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		GuiasEmbarqueDTO guiasEmbarqueDTO = null;
		EmbarqueCatalogoBusiness business = null;
		try{
			String mensaje = "";
			guiasEmbarqueDTO = obtenerDatos(req);
			business = new EmbarqueCatalogoBusiness();
			
			guiasEmbarqueDTO = business.enviarGuiasEmbarque(guiasEmbarqueDTO);
			if(guiasEmbarqueDTO != null){
				if(guiasEmbarqueDTO.getArrResumen().size() > 0){
					req.setAttribute("arrMensajes", guiasEmbarqueDTO.getArrResumen());
					req.setAttribute("nroMensajes", guiasEmbarqueDTO.getArrResumen().size());
				}
				else{
					mensaje = "Guías embarcadas exitosamente.";
				}
			}
			req.setAttribute("mensaje", mensaje);
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}
	
	public GuiasEmbarqueDTO obtenerDatos(HttpServletRequest req){
		GuiasEmbarqueDTO guiasEmbarque = new GuiasEmbarqueDTO();
		
		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		String codTrans = req.getParameter("transportadoras") != null ? req.getParameter("transportadoras") : "";
		String guias = req.getParameter("guias") != null ? req.getParameter("guias") : "";
		
		guiasEmbarque.setCodCia(codCia);
		guiasEmbarque.setCodTrans(codTrans);
		guiasEmbarque.setNroguia(guias);
		
		return guiasEmbarque;
	}
}
