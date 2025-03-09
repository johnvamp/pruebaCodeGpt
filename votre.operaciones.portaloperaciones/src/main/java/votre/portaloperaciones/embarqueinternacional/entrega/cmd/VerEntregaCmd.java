package votre.portaloperaciones.embarqueinternacional.entrega.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.embarqueinternacional.entrega.beans.EntregaDTO;
import votre.portaloperaciones.embarqueinternacional.entrega.delegate.EntregaBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class VerEntregaCmd implements IBaseCmd {
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String mensaje;
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		EntregaBusiness entregaBusiness = null;
		EntregaDTO entregaDTO = new EntregaDTO();
		try{
			entregaBusiness = new EntregaBusiness();
			obtenerDatos(req);
			dtoAssembler(entregaDTO);
			entregaDTO = entregaBusiness.verEntrega(entregaDTO);
			if(entregaDTO != null){
				if(entregaDTO.getEstado().equals(Constantes.EXITO_SP)){
					req.setAttribute("titEntrega", entregaDTO.getTitEntrega());
					req.setAttribute("titTransportador", entregaDTO.getTitTransportador());
					req.setAttribute("titCamion", entregaDTO.getTitCamion());
					req.setAttribute("titParcial", entregaDTO.getTitParcial());
					req.setAttribute("titTotal", entregaDTO.getTitTotal());
					req.setAttribute("titAceptar", entregaDTO.getTitAceptar().toUpperCase());
					req.setAttribute("titRegresar", entregaDTO.getTitRegresar().toUpperCase());
					req.setAttribute("transportadores", entregaDTO.getDetalle());
				}
				else{
					mensaje = entregaDTO.getDescripcion();
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
	
	private void obtenerDatos(HttpServletRequest req){
		codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
	}
	
	private void dtoAssembler(final EntregaDTO entregaDTO) {
		entregaDTO.setCodCia(codCia);
		entregaDTO.setAccion(Constantes.ACCION_ENTREGA);
		entregaDTO.setCodTransportador("0");
		entregaDTO.setPlaca("");
		entregaDTO.setFecha("");
	}
}
