package votre.portaloperaciones.embarqueinternacional.embarque.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.embarqueinternacional.embarque.beans.EmbarqueDTO;
import votre.portaloperaciones.embarqueinternacional.embarque.delegate.EmbarqueBusinnes;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class CerrarEmbarqueCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String codTransportador;
	private String placa;
	private String fecha;
	private String respuesta;
	private String mensaje;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		String siguienteRuta = "embarque.verAccionesEmbarque.do";
		EmbarqueBusinnes embarqueBusinnes = null;
		EmbarqueDTO embarqueDTO = new EmbarqueDTO();
		try{
			embarqueBusinnes = new EmbarqueBusinnes();
			obtenerDatos(req);
			if(respuesta.equals("S")){
				dtoAssembler(embarqueDTO);
				embarqueDTO = embarqueBusinnes.cerrarEmbarque(embarqueDTO);
				if(embarqueDTO != null){
					if(embarqueDTO.getEstado().equals(Constantes.EXITO_SP)){
						siguienteRuta = "reimprimir.jspDetalle.do";
						String titulos[] = embarqueDTO.getValorConcatenado().split("\\|\\|");
						if(titulos != null){
							embarqueDTO.setTitObservacion(titulos[0]);
							embarqueDTO.setTitVdeclarado(titulos[1]);
						}
						req.setAttribute("codTransportador", embarqueDTO.getCodTransportador());
						req.setAttribute("camion", embarqueDTO.getPlaca());
						req.setAttribute("fecha", embarqueDTO.getFecha());
						req.setAttribute("titCerrarCamion", embarqueDTO.getTitPantalla());
						req.setAttribute("titCompraDirecta", embarqueDTO.getTitCompraDirecta());
						req.setAttribute("titRelacion", embarqueDTO.getTitRelacionEmbarque());
						req.setAttribute("titTransportador", embarqueDTO.getTitTransportador());
						req.setAttribute("transportador", embarqueDTO.getTransportador());
						req.setAttribute("titCamion", embarqueDTO.getTitCamionEnc());
						req.setAttribute("titFecha", embarqueDTO.getTitFechaEnc());
						req.setAttribute("titObservacion", embarqueDTO.getTitObservacion());
						req.setAttribute("titNroOrden", embarqueDTO.getTitNroOrden());
						req.setAttribute("titZona", embarqueDTO.getTitZona());
						req.setAttribute("titCedula", embarqueDTO.getTitCedula());
						req.setAttribute("titNombre", embarqueDTO.getTitNombre());
						req.setAttribute("titDireccion", embarqueDTO.getTitDireccion());
						req.setAttribute("titTelefono", embarqueDTO.getTitTelefono());
						req.setAttribute("titDestino", embarqueDTO.getTitDestino());
						req.setAttribute("titVdeclarado", embarqueDTO.getTitVdeclarado());
						req.setAttribute("titCampana", embarqueDTO.getTitCampana());
						req.setAttribute("titPorteria", embarqueDTO.getTitPorteria());
						req.setAttribute("titTelefono2", embarqueDTO.getTitTelefono2());
						req.setAttribute("titCelular", embarqueDTO.getTitCelular());
						req.setAttribute("titDistrito", embarqueDTO.getTitDistrito());
						req.setAttribute("titCanton", embarqueDTO.getTitCanton());
						req.setAttribute("titProvincia", embarqueDTO.getTitProvincia());
						req.setAttribute("titValorOrden", embarqueDTO.getTitValorOrden());
						req.setAttribute("titTotal", embarqueDTO.getTitTotalGuias());
						req.setAttribute("titRegresar", embarqueDTO.getTitRegresar().toUpperCase());
						req.setAttribute("registros", embarqueDTO.getDetalle());
						req.setAttribute("nroRegistros", embarqueDTO.getDetalle().length);
						req.setAttribute("registrosXPagina", Constantes.REGISTROSXPAGINA);
						req.setAttribute("accion", "cerrarEmbarque");
						req.setAttribute("estado", embarqueDTO.getEstado());
						mensaje = embarqueDTO.getDescripcion();
					}
					else{
						req.setAttribute("nomAccion", "cerrarEmbarque");
						req.setAttribute("estado", embarqueDTO.getEstado());
						mensaje = embarqueDTO.getDescripcion() + " (" + embarqueDTO.getPlaca() +")";
					}
				}
			}
			else{
				req.setAttribute("nomAccion", "cerrarEmbarque");
			}
			
			if(mensaje != null && !mensaje.equals("")){
				req.setAttribute("mensaje", mensaje);
			}
			
			Configuracion.getInstance().getContext().getRequestDispatcher(siguienteRuta).forward(req, res);
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
		respuesta = req.getParameter("respuesta") != null ? req.getParameter("respuesta") : "";
		fecha = req.getParameter("fecha") != null ? req.getParameter("fecha") : "";
		
	}
	
	private void dtoAssembler(final EmbarqueDTO embarqueDTO){
		embarqueDTO.setCodCia(codCia);
		embarqueDTO.setCodTransportador(codTransportador);
		embarqueDTO.setPlaca(placa);
		embarqueDTO.setRespuesta(respuesta);
		embarqueDTO.setAccion(Constantes.ACCION_CERRAR);
		embarqueDTO.setFecha(fecha);
	}

}
