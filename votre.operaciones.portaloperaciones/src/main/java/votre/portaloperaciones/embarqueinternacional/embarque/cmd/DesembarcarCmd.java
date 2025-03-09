package votre.portaloperaciones.embarqueinternacional.embarque.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.embarqueinternacional.embarque.beans.EmbarqueDTO;
import votre.portaloperaciones.embarqueinternacional.embarque.delegate.EmbarqueBusinnes;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class DesembarcarCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String camion;
	private String codTransportador;
	private String transportador;
	private String placa;
	private String numOrden;
	private String fecha;
	private String mensaje;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		EmbarqueBusinnes embarqueBusinnes = null;
		EmbarqueDTO embarqueDTO = new EmbarqueDTO();
		try{
			embarqueBusinnes = new EmbarqueBusinnes();
			obtenerDatos(req);
			dtoAssembler(embarqueDTO);
			
			embarqueDTO = embarqueBusinnes.desembarque(embarqueDTO);
			if(embarqueDTO != null){
				mensaje = embarqueDTO.getDescripcion() +" ("+ embarqueDTO.getPlaca() +")";
				req.setAttribute("codTransportador", embarqueDTO.getCodTransportador());
				req.setAttribute("transportador", transportador);
				req.setAttribute("placa", embarqueDTO.getPlaca());
				req.setAttribute("fecha", embarqueDTO.getFecha());
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
		numOrden = req.getParameter("txtOrden") != null ? req.getParameter("txtOrden") : "0";
		fecha = req.getParameter("fecha") != null ? req.getParameter("fecha") : "";
		
	}
	
	private void dtoAssembler(final EmbarqueDTO embarqueDTO){
		embarqueDTO.setCodCia(codCia);
		embarqueDTO.setAccion(Constantes.ACCION_DESEMBARQUE);
		embarqueDTO.setCodTransportador(codTransportador);
		embarqueDTO.setPlaca(placa);
		embarqueDTO.setNumOrden(numOrden);
		embarqueDTO.setFecha(fecha);
	}

}
