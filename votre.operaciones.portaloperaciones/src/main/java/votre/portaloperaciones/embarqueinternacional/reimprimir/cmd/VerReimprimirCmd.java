package votre.portaloperaciones.embarqueinternacional.reimprimir.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.embarqueinternacional.reimprimir.beans.ReimprimirDTO;
import votre.portaloperaciones.embarqueinternacional.reimprimir.delegate.ReimprimirBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class VerReimprimirCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String mensaje;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		ReimprimirBusiness reimprimirBusiness = null;
		ReimprimirDTO reimprimirDTO = new ReimprimirDTO();
		try{
			reimprimirBusiness = new ReimprimirBusiness();
			codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
			dtoAssembler(reimprimirDTO);
			
			reimprimirDTO = reimprimirBusiness.reimprimirEmbarque(reimprimirDTO);
			if(reimprimirDTO != null){
				if(reimprimirDTO.getEstado().equals(Constantes.EXITO_SP)){
					req.setAttribute("titReimpresion", reimprimirDTO.getTitReimpresion());
					req.setAttribute("titTransportador", reimprimirDTO.getTitTransportador());
					req.setAttribute("titCamion", reimprimirDTO.getTitCamion());
					req.setAttribute("titReimprimir", reimprimirDTO.getTitReimprimir().toUpperCase());
					req.setAttribute("titRegresar", reimprimirDTO.getTitRegresar().toUpperCase());
					req.setAttribute("transportadores", reimprimirDTO.getDetalle());
				}
				else{
					mensaje = reimprimirDTO.getDescripcion();
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

	private void dtoAssembler(final ReimprimirDTO reimprimirDTO){
		reimprimirDTO.setCodCia(codCia);
		reimprimirDTO.setAccion(Constantes.ACCION_TRANSPORTADOR_REIMPRIMIR);
		reimprimirDTO.setCodTransportador("0");
		reimprimirDTO.setCamion("");
		reimprimirDTO.setFecha("");
	}
}
