package votre.portaloperaciones.embarqueinternacional.embarque.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.embarqueinternacional.transportador.beans.TransportadorDTO;
import votre.portaloperaciones.embarqueinternacional.transportador.delegate.TransportadorBusinnes;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class VerAccionesEmbarqueCmd implements IBaseCmd {
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String accion;
	private String mensaje;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		TransportadorBusinnes transportadorBusinnes = null;
		TransportadorDTO transportadorDTO = new TransportadorDTO();
		try{
			transportadorBusinnes = new TransportadorBusinnes();
			obtenerDatos(req);
			dtoAssembler(transportadorDTO);
			transportadorDTO = transportadorBusinnes.consultarTransportadores(transportadorDTO);
			
			if(transportadorDTO != null){
				if(transportadorDTO.getEstado().equals(Constantes.EXITO_SP)){
					req.setAttribute("titTransportador", transportadorDTO.getTitTransportador());
					req.setAttribute("titCamion", transportadorDTO.getTitCamion());
					req.setAttribute("titAceptar", transportadorDTO.getTitAceptar().toUpperCase());
					req.setAttribute("titRegresar", transportadorDTO.getTitRegresar().toUpperCase());
					req.setAttribute("transportadores", transportadorDTO.getDetalles());
					req.setAttribute("accion", accion);
				}
				else{
					mensaje = transportadorDTO.getDescripcion();
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
		accion = req.getParameter("nomAccion") != null ? req.getParameter("nomAccion") : "";
		if(accion.equals("")){
			accion = (String) (req.getAttribute("nomAccion") != null ? req.getAttribute("nomAccion") : "");
		}
	}
	
	private void dtoAssembler(final TransportadorDTO transportador) {
		transportador.setCodCia(codCia);
		transportador.setAccion(Constantes.ACCION_TRANSPORTADOR);
		transportador.setTransportista("0");
	}
}
