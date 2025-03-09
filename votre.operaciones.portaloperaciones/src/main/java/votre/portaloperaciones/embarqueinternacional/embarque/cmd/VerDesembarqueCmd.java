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

public class VerDesembarqueCmd implements IBaseCmd {

	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String camion;
	private String codTransportador;
	private String transportador;
	private String placa;
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
				if(embarqueDTO.getEstado().equals(Constantes.EXITO_SP)){
					req.setAttribute("titPantalla", embarqueDTO.getTitPantalla());
					req.setAttribute("titTransportador", embarqueDTO.getTitTransportador());
					req.setAttribute("transportador", transportador);
					req.setAttribute("titCamion", embarqueDTO.getTitCamion());
					req.setAttribute("camion", embarqueDTO.getPlaca());
					req.setAttribute("titTotal", embarqueDTO.getTitTotal());
					req.setAttribute("total", embarqueDTO.getNroPedidos());
					req.setAttribute("titNumOrden", embarqueDTO.getTitNroOrden());
					req.setAttribute("titAceptar", embarqueDTO.getTitAceptar().toUpperCase());
					req.setAttribute("titRegresar", embarqueDTO.getTitRegresar().toUpperCase());
					req.setAttribute("codTransportador", embarqueDTO.getCodTransportador());
					req.setAttribute("fecha", embarqueDTO.getFecha());
					req.setAttribute("accion", "desembarcar");
				}
				else{
					mensaje = embarqueDTO.getDescripcion() +" ("+ embarqueDTO.getPlaca() +")" ;
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
		camion = req.getParameter("camion") != null ? req.getParameter("camion") : "";
		if(camion != ""){
			String[] datos = camion.split("\\|");
			codTransportador = datos[0];
			transportador = datos[1];
			placa = datos[2];
			fecha = datos[3];
		}
		else{
			codTransportador = (String) (req.getAttribute("codTransportador") != null ? req.getAttribute("codTransportador") : "0");
			transportador = (String) (req.getAttribute("transportador") != null ? req.getAttribute("transportador") : "");
			placa = (String) (req.getAttribute("placa") != null ? req.getAttribute("placa") : "");
			fecha = (String) (req.getAttribute("fecha") != null ? req.getAttribute("fecha") : "");
			mensaje = (String) (req.getAttribute("mensaje") != null ? req.getAttribute("mensaje") : "");
		}
	}
	
	private void dtoAssembler(final EmbarqueDTO embarqueDTO){
		embarqueDTO.setCodCia(codCia);
		embarqueDTO.setAccion(Constantes.ACCION_VERDESEMBARQUE);
		embarqueDTO.setCodTransportador(codTransportador);
		embarqueDTO.setPlaca(placa);
		embarqueDTO.setNumOrden("0");
		embarqueDTO.setFecha(fecha);
	}

}
