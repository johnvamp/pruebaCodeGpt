package votre.portaloperaciones.embarqueinternacional.transportador.cmd;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.log4j.Logger;

import votre.portaloperaciones.embarqueinternacional.transportador.beans.TransportadorDTO;
import votre.portaloperaciones.embarqueinternacional.transportador.beans.TransportadorDetalleDTO;
import votre.portaloperaciones.embarqueinternacional.transportador.delegate.TransportadorBusinnes;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class ConsultarCamionesAjaxCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String codTrans;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		AjaxXmlBuilder xml = new AjaxXmlBuilder();
		TransportadorBusinnes transportadorBusinnes = null;
		TransportadorDTO transportadorDTO = new TransportadorDTO();
		TransportadorDetalleDTO[] arrCamiones = null;
		ArrayList<TransportadorDetalleDTO> camiones = new ArrayList<TransportadorDetalleDTO>();
		try{
			transportadorBusinnes = new TransportadorBusinnes();
			obtenerDatos(req);
			dtoAssembler(transportadorDTO);
			transportadorDTO = transportadorBusinnes.consultarTransportadores(transportadorDTO);
			if(transportadorDTO != null){
				if(transportadorDTO.getEstado().equals(Constantes.EXITO_SP)){
					arrCamiones = transportadorDTO.getDetalles();
					if(arrCamiones != null){
						TransportadorDetalleDTO detalleDTO = new TransportadorDetalleDTO();
						detalleDTO.setValorConcatenado("0");
						detalleDTO.setCamion("-Seleccione-");
						camiones.add(detalleDTO);
						for(int i=0; i<arrCamiones.length;i++){
							camiones.add(arrCamiones[i]);
						}
					}
				}
			}
			xml.addItems(camiones,"camion","valorConcatenado");
			
			res.setContentType("text/xml; charset=UTF-8");
			
			PrintWriter printwriter;
			printwriter = res.getWriter();
			printwriter.write(xml.toString());
			printwriter.close();
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}
	
	private void obtenerDatos(HttpServletRequest req){
		codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		codTrans = req.getParameter("codTrans") != null ? req.getParameter("codTrans") : "0";
	}
	
	private void dtoAssembler(final TransportadorDTO transportador) {
		transportador.setCodCia(codCia);
		transportador.setAccion(Constantes.ACCION_CAMION);
		transportador.setTransportista(codTrans);
	}
}
