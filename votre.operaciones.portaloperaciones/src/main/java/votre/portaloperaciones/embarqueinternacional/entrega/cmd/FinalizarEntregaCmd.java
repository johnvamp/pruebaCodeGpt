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

public class FinalizarEntregaCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String codTransportador;
	private String placa;
	private String fecha;
	private String mensaje;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		EntregaBusiness entregaBusiness = null;
		EntregaDTO entregaDTO = new EntregaDTO();
		try{
			entregaBusiness = new EntregaBusiness();
			obtenerDatos(req);
			dtoAssembler(entregaDTO);
			
			entregaDTO = entregaBusiness.verEntregaParcial(entregaDTO);
			if(entregaDTO != null){
				if(!entregaDTO.getEstado().equals(Constantes.EXITO_SP)){
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
		codTransportador = req.getParameter("codTransportador") != null ? req.getParameter("codTransportador") : "0";
		placa = req.getParameter("txtCamion") != null ? req.getParameter("txtCamion") : "";
		fecha = req.getParameter("fecha") != null ? req.getParameter("fecha") : "";
	}
	
	private void dtoAssembler(final EntregaDTO entregaDTO) {
		entregaDTO.setCodCia(codCia);
		entregaDTO.setAccion(Constantes.ACCION_FINALIZAR);
		entregaDTO.setCodTransportador(codTransportador);
		entregaDTO.setPlaca(placa);
		entregaDTO.setFecha(fecha);
		entregaDTO.setNumOrden("0");
		entregaDTO.setCausal("");
	}

}
