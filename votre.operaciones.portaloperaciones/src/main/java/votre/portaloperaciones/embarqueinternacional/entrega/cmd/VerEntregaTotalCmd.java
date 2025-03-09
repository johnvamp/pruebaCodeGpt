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

public class VerEntregaTotalCmd implements IBaseCmd {

	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String camion;
	private String codTransportador;
	private String transportador;
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
			
			entregaDTO = entregaBusiness.verEntrega(entregaDTO);
			if(entregaDTO != null){
				if(entregaDTO.getEstado().equals(Constantes.EXITO_SP)){
					req.setAttribute("titPantalla", entregaDTO.getTitEntrega());
					req.setAttribute("titTransportador", entregaDTO.getTitTransportador());
					req.setAttribute("transportador", transportador);
					req.setAttribute("titCamion", entregaDTO.getTitCamion());
					req.setAttribute("camion", entregaDTO.getPlaca());
					req.setAttribute("titTotal", entregaDTO.getTitTotalCajas());
					req.setAttribute("total", entregaDTO.getNroPedidos());
					req.setAttribute("titCerrar", entregaDTO.getTitConfirmar());
					req.setAttribute("titAceptar", entregaDTO.getTitAceptar().toUpperCase());
					req.setAttribute("titRegresar", entregaDTO.getTitRegresar().toUpperCase());
					req.setAttribute("codTransportador", entregaDTO.getCodTransportador());
					req.setAttribute("fecha", entregaDTO.getFecha());
					req.setAttribute("accion", "entregaTotal");
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
		camion = req.getParameter("camion") != null ? req.getParameter("camion") : "";
		if(camion != ""){
			String[] datos = camion.split("\\|");
			codTransportador = datos[0];
			transportador = datos[1];
			placa = datos[2];
			fecha = datos[3];
		}
	}
	
	private void dtoAssembler(final EntregaDTO entregaDTO) {
		entregaDTO.setCodCia(codCia);
		entregaDTO.setAccion(Constantes.ACCION_ENTREGATOTAL);
		entregaDTO.setCodTransportador(codTransportador);
		entregaDTO.setPlaca(placa);
		entregaDTO.setFecha(fecha);
	}

}
