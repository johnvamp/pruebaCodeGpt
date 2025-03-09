package votre.portaloperaciones.embarqueinternacional.entrega.cmd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.embarqueinternacional.causales.beans.CausalDTO;
import votre.portaloperaciones.embarqueinternacional.causales.delegate.CausalBusiness;
import votre.portaloperaciones.embarqueinternacional.entrega.beans.EntregaDTO;
import votre.portaloperaciones.embarqueinternacional.entrega.delegate.EntregaBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class VerEntregaParcialCmd implements IBaseCmd {
	
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
		ArrayList<CausalDTO> causales = new ArrayList<CausalDTO>();
		try{
			entregaBusiness = new EntregaBusiness();
			obtenerDatos(req);
			dtoAssembler(entregaDTO);
			
			entregaDTO = entregaBusiness.verEntregaParcial(entregaDTO);
			if(entregaDTO != null){
				if(entregaDTO.getEstado().equals(Constantes.EXITO_SP)){
					req.setAttribute("titEntrega", entregaDTO.getTitEntrega());
					req.setAttribute("codTransportador", entregaDTO.getCodTransportador());
					req.setAttribute("fecha", entregaDTO.getFecha());
					req.setAttribute("titTransportador", entregaDTO.getTitTransportador());
					req.setAttribute("transportador", transportador);
					req.setAttribute("titCamion", entregaDTO.getTitCamion());
					req.setAttribute("camion", entregaDTO.getPlaca());
					req.setAttribute("titTotal", entregaDTO.getTitTotal());
					req.setAttribute("numCajas", entregaDTO.getNroPedidos());
					req.setAttribute("titCausal", entregaDTO.getTitCausal());
					req.setAttribute("titNumOrden", entregaDTO.getTitNumOrden());
					req.setAttribute("titAceptar", entregaDTO.getTitAceptar().toUpperCase());
					req.setAttribute("titFinalizar", entregaDTO.getTitFinalizar().toUpperCase());
					req.setAttribute("titCancelar", entregaDTO.getTitCancelar().toUpperCase());
					req.setAttribute("titNoentregado", entregaDTO.getTitNoentregado());
					req.setAttribute("titEntregado", entregaDTO.getTitEntregado());
					
					CausalBusiness causalBusiness = new CausalBusiness();
					causales = causalBusiness.cargarCausales(codCia);
					req.setAttribute("causales", causales);
					
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
		else{
			codTransportador = (String) (req.getAttribute("codTransportador") != null ? req.getAttribute("codTransportador") : "0");
			transportador = (String) (req.getAttribute("transportador") != null ? req.getAttribute("transportador") : "");
			placa = (String) (req.getAttribute("placa") != null ? req.getAttribute("placa") : "");
			fecha = (String) (req.getAttribute("fecha") != null ? req.getAttribute("fecha") : "");
			mensaje = (String) (req.getAttribute("mensaje") != null ? req.getAttribute("mensaje") : "");
		}
	}
	
	private void dtoAssembler(final EntregaDTO entregaDTO) {
		entregaDTO.setCodCia(codCia);
		entregaDTO.setAccion(Constantes.ACCION_ENTREGA);
		entregaDTO.setCodTransportador(codTransportador);
		entregaDTO.setPlaca(placa);
		entregaDTO.setFecha(fecha);
		entregaDTO.setNumOrden("0");
		entregaDTO.setCausal("");
	}

}
