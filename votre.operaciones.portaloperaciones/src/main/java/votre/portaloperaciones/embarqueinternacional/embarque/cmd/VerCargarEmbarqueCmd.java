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

public class VerCargarEmbarqueCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private String mensaje;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		EmbarqueBusinnes embarqueBusinnes = null;
		EmbarqueDTO embarqueDTO = null;
		try{
			embarqueBusinnes = new EmbarqueBusinnes();
			embarqueDTO = obtenerDatos(req);
			
			embarqueDTO = embarqueBusinnes.abrirEmbarque(embarqueDTO);
			if(embarqueDTO != null){
				if(embarqueDTO.getEstado().equals(Constantes.EXITO_SP)){
					req.setAttribute("titPantalla", embarqueDTO.getTitPantalla());
					req.setAttribute("titTransportador", embarqueDTO.getTitTransportador());
					req.setAttribute("transportador", embarqueDTO.getTransportador());
					req.setAttribute("titCamion", embarqueDTO.getTitCamion());
					req.setAttribute("camion", embarqueDTO.getPlaca());
					req.setAttribute("titTotal", embarqueDTO.getTitTotal());
					req.setAttribute("total", embarqueDTO.getNroPedidos());
					req.setAttribute("titNumOrden", embarqueDTO.getNumOrden());
					req.setAttribute("titAceptar", embarqueDTO.getTitAceptar().toUpperCase());
					req.setAttribute("titDetener", embarqueDTO.getTitDetener().toUpperCase());
					req.setAttribute("titFinalizar", embarqueDTO.getTitFinalizar().toUpperCase());
					req.setAttribute("codTransportador", embarqueDTO.getCodTransportador());
					req.setAttribute("fecha", embarqueDTO.getFecha());
					req.setAttribute("accion", "embarcar");
				}
				else{
					mensaje = embarqueDTO.getDescripcion();
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
	
	private EmbarqueDTO obtenerDatos(HttpServletRequest req){
		EmbarqueDTO embarqueDTO = new EmbarqueDTO();
		String codTransportador;
		String transportador;
		String placa;
		String fecha;
		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		String camion = req.getParameter("camion") != null ? req.getParameter("camion") : "";
		if(camion != ""){
			String[] datos = camion.split("\\|");
			codTransportador = datos[0];
			transportador = datos[1];
			placa = datos[2];
			fecha = datos[3];
			if(fecha.equals("")){
				fecha = Fecha.getFechaServidor("yyyyMMdd");
			}
		}
		else{
			codTransportador = req.getParameter("codTransportador") != null ? req.getParameter("codTransportador") : "0";
			transportador = req.getParameter("txtTransportador") != null ? req.getParameter("txtTransportador") : "";
			placa = req.getParameter("txtCamion") != null ? req.getParameter("txtCamion") : "";
			fecha = req.getParameter("fecha") != null ? req.getParameter("fecha") : "";
			if(fecha.equals("")){
				fecha = Fecha.getFechaServidor("yyyyMMdd");
			}
			mensaje = (String) (req.getAttribute("mensaje") != null ? req.getAttribute("mensaje") : "");
		}
		
		embarqueDTO.setCodCia(codCia);
		embarqueDTO.setAccion(Constantes.ACCION_EMBARQUE);
		embarqueDTO.setCodTransportador(codTransportador);
		embarqueDTO.setTransportador(transportador);
		embarqueDTO.setPlaca(placa);
		embarqueDTO.setTipoEmbarque("");
		embarqueDTO.setFecha(fecha);

		return embarqueDTO;
	}
	
}
