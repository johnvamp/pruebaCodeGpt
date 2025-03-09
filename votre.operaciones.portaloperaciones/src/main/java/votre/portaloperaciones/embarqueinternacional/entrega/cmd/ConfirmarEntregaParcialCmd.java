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

public class ConfirmarEntregaParcialCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String codTransportador;
	private String transportador;
	private String placa;
	private String fecha;
	private String causal;
	private String numOrden;
	private String accion;
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
				req.setAttribute("codTransportador", codTransportador);
				req.setAttribute("transportador", transportador);
				req.setAttribute("placa", placa);
				req.setAttribute("fecha", fecha);
				mensaje = entregaDTO.getDescripcion();
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
		transportador = req.getParameter("txtTransportador") != null ? req.getParameter("txtTransportador") : "";
		placa = req.getParameter("txtCamion") != null ? req.getParameter("txtCamion") : "";
		fecha = req.getParameter("fecha") != null ? req.getParameter("fecha") : "";
		numOrden = req.getParameter("txtOrden") != null ? req.getParameter("txtOrden") : "";
		causal = req.getParameter("causal") != null ? req.getParameter("causal") : "";
		accion = req.getParameter("accion") != null ? req.getParameter("accion") : "";
	}
	
	private void dtoAssembler(final EntregaDTO entregaDTO) {
		entregaDTO.setCodCia(codCia);
		entregaDTO.setAccion(accion);
		entregaDTO.setCodTransportador(codTransportador);
		entregaDTO.setPlaca(placa);
		entregaDTO.setFecha(fecha);
		entregaDTO.setNumOrden(numOrden);
		entregaDTO.setCausal(causal);
	}

}
