package votre.portaloperaciones.embarqueinternacional.camion.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.embarqueinternacional.camion.beans.CamionDTO;
import votre.portaloperaciones.embarqueinternacional.camion.delegate.CamionBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class VerConsultarCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String mensaje;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		CamionBusiness camionBusiness = null;
		CamionDTO camionDTO = new CamionDTO();
		try{
			camionBusiness = new CamionBusiness();
			codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
			camionDTO.setCodCia(codCia);
			camionDTO.setAccion(Constantes.ACCION_VERCONSULTAR);
			
			camionDTO = camionBusiness.consultarCamionesAbiertos(camionDTO);
			if(camionDTO != null){
				if(camionDTO.getEstado().equals(Constantes.EXITO_SP)){
					req.setAttribute("titConsultas", camionDTO.getTitConsultas());
					req.setAttribute("titCamionesAbiertos", camionDTO.getTitCamionesAbiertos());
					req.setAttribute("titReimprimir", camionDTO.getTitReimprimir());
					req.setAttribute("titConsultar", camionDTO.getTitConsultar().toUpperCase());
					req.setAttribute("titRegresar", camionDTO.getTitRegresar().toUpperCase());
				}
				else{
					mensaje = camionDTO.getDescripcion();
				}
			}
			
			if(mensaje != null && !mensaje.equals("")){
				req.setAttribute("mensaje", mensaje);
			}
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}

}
