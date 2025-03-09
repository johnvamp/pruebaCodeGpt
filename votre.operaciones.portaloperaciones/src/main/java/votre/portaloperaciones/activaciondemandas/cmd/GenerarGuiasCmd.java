package votre.portaloperaciones.activaciondemandas.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.activaciondemandas.beans.AsignarDTO;
import votre.portaloperaciones.activaciondemandas.delegate.ActivacionDemandasBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class GenerarGuiasCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		AsignarDTO asignarDTO = null;
		ActivacionDemandasBusiness business = null;
		String codCia;
		String mensaje = "";
		
		try{
			asignarDTO = new AsignarDTO();
			business = new ActivacionDemandasBusiness();
			
			codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
			asignarDTO.setCodCia(codCia);
			
			asignarDTO = business.generarGuias(asignarDTO);
			if(asignarDTO != null){
				if(asignarDTO.getEstado().equals(Constantes.EXITO_SP)){
					mensaje = asignarDTO.getDescripcion();
				}
				else{
					mensaje = asignarDTO.getDescripcion();
				}
			}
			
			if (mensaje != null && !mensaje.equals("")){
				req.setAttribute("mensaje", mensaje);				
			}
			req.setAttribute("accionMaster", Constantes.ACCION_GUIA_MASTER);
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}

}
