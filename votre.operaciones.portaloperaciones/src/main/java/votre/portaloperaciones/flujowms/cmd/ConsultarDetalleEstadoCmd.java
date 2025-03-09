package votre.portaloperaciones.flujowms.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.flujowms.beans.AlertasDTO;
import votre.portaloperaciones.flujowms.delegate.FlujoWmsBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class ConsultarDetalleEstadoCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		AlertasDTO alertasDTO = null;
		FlujoWmsBusiness flujoWmsBusiness = null;
		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		String nroEstado = req.getParameter("nroEstado") != null ? req.getParameter("nroEstado") : "";
		String tituloEstado = req.getParameter("tituloEstado") != null ? req.getParameter("tituloEstado") : "";
		String mensaje = "";
		try{
			alertasDTO = new AlertasDTO();
			flujoWmsBusiness = new FlujoWmsBusiness();
			
			alertasDTO.setCodCia(codCia);
			alertasDTO.setNroEstado(nroEstado);
			
			alertasDTO = flujoWmsBusiness.consultarDetalleEstado(alertasDTO);
			if(alertasDTO != null){
				if(alertasDTO.getEstado().equals(Constantes.ERROR_SP)){
					mensaje = alertasDTO.getDescripcion();
				}
				else{
					req.setAttribute("registros", alertasDTO.getDetalle());
					req.setAttribute("nroEstado", nroEstado);
					req.setAttribute("tituloEstado", tituloEstado);
					req.setAttribute("codigoEnviado", Constantes.CODIGO_PEDIDO_ENVIADO);
					req.setAttribute("codigoSubido", Constantes.CODIGO_PEDIDO_SUBIDO);
					req.setAttribute("codigoLiberado", Constantes.CODIGO_PEDIDO_LIBERADO);
					req.setAttribute("codigoAsignado", Constantes.CODIGO_PEDIDO_ASIGNADO);
					req.setAttribute("codigoEnviadoWms", Constantes.CODIGO_PEDIDO_ENVIADO_WMS);
					req.setAttribute("codigoConsolidado", Constantes.CODIGO_PEDIDO_CONSOLIDADO);
				}
			}
			
			if(mensaje != null && mensaje != ""){
				req.setAttribute("mensaje", mensaje);
			}
			
		}
		catch(Exception e){
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}

}
