package votre.portaloperaciones.embarqueinternacional.reimprimir.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.embarqueinternacional.reimprimir.beans.ReimprimirDTO;
import votre.portaloperaciones.embarqueinternacional.reimprimir.delegate.ReimprimirBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class ReimprimirCmd implements IBaseCmd {

	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String camion;
	private String codTransportador;
	private String placa;
	private String fecha;
	private String mensaje;
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		ReimprimirBusiness reimprimirBusiness = null;
		ReimprimirDTO reimprimirDTO = new ReimprimirDTO();
		try{
			reimprimirBusiness = new ReimprimirBusiness();
			obtenerDatos(req);
			dtoAssembler(reimprimirDTO);
			
			reimprimirDTO = reimprimirBusiness.reimprimirEmbarque(reimprimirDTO);
			if(reimprimirDTO != null){
				if(reimprimirDTO.getEstado().equals(Constantes.EXITO_SP)){
					String titulos[] = reimprimirDTO.getValorConcatenado().split("\\|\\|");
					if(titulos != null){
						reimprimirDTO.setTitObservacion(titulos[0]);
						reimprimirDTO.setTitVdeclarado(titulos[1]);
					}
					req.setAttribute("codTransportador", reimprimirDTO.getCodTransportador());
					req.setAttribute("camion", reimprimirDTO.getCamion());
					req.setAttribute("fecha", reimprimirDTO.getFecha());
					req.setAttribute("titReimpresion", reimprimirDTO.getTitReimpresion());
					req.setAttribute("titCompraDirecta", reimprimirDTO.getTitCompraDirecta());
					req.setAttribute("titRelacion", reimprimirDTO.getTitRelacionEmbarque());
					req.setAttribute("titTransportador", reimprimirDTO.getTitTransportador());
					req.setAttribute("transportador", reimprimirDTO.getTransportador());
					req.setAttribute("titCamion", reimprimirDTO.getTitCamionEnc());
					req.setAttribute("titFecha", reimprimirDTO.getTitFechaEnc());
					req.setAttribute("titObservacion", reimprimirDTO.getTitObservacion());
					req.setAttribute("titNroOrden", reimprimirDTO.getTitNroOrden());
					req.setAttribute("titZona", reimprimirDTO.getTitZona());
					req.setAttribute("titCedula", reimprimirDTO.getTitCedula());
					req.setAttribute("titNombre", reimprimirDTO.getTitNombre());
					req.setAttribute("titDireccion", reimprimirDTO.getTitDireccion());
					req.setAttribute("titTelefono", reimprimirDTO.getTitTelefono());
					req.setAttribute("titDestino", reimprimirDTO.getTitDestino());
					req.setAttribute("titVdeclarado", reimprimirDTO.getTitVdeclarado());
					req.setAttribute("titCampana", reimprimirDTO.getTitCampana());
					req.setAttribute("titPorteria", reimprimirDTO.getTitPorteria());
					req.setAttribute("titTelefono2", reimprimirDTO.getTitTelefono2());
					req.setAttribute("titCelular", reimprimirDTO.getTitCelular());
					req.setAttribute("titDistrito", reimprimirDTO.getTitDistrito());
					req.setAttribute("titCanton", reimprimirDTO.getTitCanton());
					req.setAttribute("titProvincia", reimprimirDTO.getTitProvincia());
					req.setAttribute("titValorOrden", reimprimirDTO.getTitValorOrden());
					req.setAttribute("titTotal", reimprimirDTO.getTitTotal());
					req.setAttribute("titRegresar", reimprimirDTO.getTitRegresar().toUpperCase());
					req.setAttribute("registros", reimprimirDTO.getDetalle());
					req.setAttribute("nroRegistros", reimprimirDTO.getDetalle().length);
					req.setAttribute("registrosXPagina", Constantes.REGISTROSXPAGINA);
					req.setAttribute("accion", "reimprimir");
					req.setAttribute("estado", reimprimirDTO.getEstado());
				}
				else{
					mensaje = reimprimirDTO.getDescripcion();
					req.setAttribute("estado", reimprimirDTO.getEstado());
					req.setAttribute("accion", "reimprimir");
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
			placa = datos[1];
			fecha = datos[2];
		}
	}
	
	private void dtoAssembler(final ReimprimirDTO reimprimirDTO){
		reimprimirDTO.setCodCia(codCia);
		reimprimirDTO.setAccion(Constantes.ACCION_REIMPRIMIR);
		reimprimirDTO.setCodTransportador(codTransportador);
		reimprimirDTO.setCamion(placa);
		reimprimirDTO.setFecha(fecha);
	}

}
