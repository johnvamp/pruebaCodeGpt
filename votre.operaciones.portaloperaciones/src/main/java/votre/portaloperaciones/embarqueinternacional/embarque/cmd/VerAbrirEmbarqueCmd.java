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

public class VerAbrirEmbarqueCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String mensaje;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		TransportadorBusinnes transportadorBusinnes = null;
		TransportadorDTO transportador = new TransportadorDTO();
		try{
			transportadorBusinnes = new TransportadorBusinnes();
			codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
			mensaje = (String) (req.getAttribute("mensaje") != null ? req.getAttribute("mensaje") : "");
			dtoAssembler(transportador);
			transportador = transportadorBusinnes.consultarTransportadores(transportador);
			if(transportador != null){
				if(transportador.getEstado().equals(Constantes.EXITO_SP)){
					req.setAttribute("titTransportador", transportador.getTitTransportador());
					req.setAttribute("titCamion", transportador.getTitCamion());
					req.setAttribute("titGuia", transportador.getTitGuia());
					req.setAttribute("titOrden", transportador.getTitOrden());
					req.setAttribute("titAceptar", transportador.getTitAceptar().toUpperCase());
					req.setAttribute("titRegresar", transportador.getTitRegresar().toUpperCase());
					req.setAttribute("transportadores", transportador.getDetalles());
				}
				else{
					mensaje = transportador.getDescripcion();
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
	
	private void dtoAssembler(final TransportadorDTO transportador) {
		transportador.setCodCia(codCia);
		transportador.setAccion(Constantes.ACCION_TRANSPORTADOR);
		transportador.setTransportista("0");
	}

}
