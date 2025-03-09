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
import co.com.personalsoft.base.util.Fecha;

public class CargarEmbarqueCmd implements IBaseCmd {

	private Logger logger = Logger.getLogger(this.getClass());
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		EmbarqueBusinnes embarqueBusinnes = null;
		EmbarqueDTO embarqueDTO = new EmbarqueDTO();
		String mensaje = null;
		try{
			embarqueBusinnes = new EmbarqueBusinnes();
			embarqueDTO = obtenerDatos(req);
			
			embarqueDTO = embarqueBusinnes.embarcar(embarqueDTO);
			if(embarqueDTO != null){
				if(!embarqueDTO.getEstado().equals(Constantes.EXITO_SP)){
					mensaje = embarqueDTO.getDescripcion() + " (" + embarqueDTO.getNumOrden() +")";
					req.setAttribute("mensaje", mensaje);
				}
				/*
				req.setAttribute("codTransportador", embarqueDTO.getCodTransportador());
				req.setAttribute("transportador", embarqueDTO.getTransportador());
				req.setAttribute("placa", embarqueDTO.getPlaca());
				req.setAttribute("fecha", embarqueDTO.getFecha());
				*/
			}
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}
	
	private EmbarqueDTO obtenerDatos(HttpServletRequest req){

		EmbarqueDTO embarqueDTO = new EmbarqueDTO();
		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		String codTransportador = req.getParameter("codTransportador") != null ? req.getParameter("codTransportador") : "0";
		String transportador = req.getParameter("txtTransportador") != null ? req.getParameter("txtTransportador") : "";
		String placa = req.getParameter("txtCamion") != null ? req.getParameter("txtCamion") : "";
		String numOrden = req.getParameter("txtOrden") != null ? req.getParameter("txtOrden") : "0";
		String fecha = req.getParameter("fecha") != null ? req.getParameter("fecha") : "";
		if(fecha.equals("")){
			fecha = Fecha.getFechaServidor("yyyyMMdd");
		}
		
		embarqueDTO.setCodCia(codCia);
		embarqueDTO.setCodTransportador(codTransportador);
		embarqueDTO.setPlaca(placa);
		embarqueDTO.setNumOrden(numOrden);
		embarqueDTO.setFecha(fecha);
		embarqueDTO.setTransportador(transportador);

		return embarqueDTO;
	}
	
}
