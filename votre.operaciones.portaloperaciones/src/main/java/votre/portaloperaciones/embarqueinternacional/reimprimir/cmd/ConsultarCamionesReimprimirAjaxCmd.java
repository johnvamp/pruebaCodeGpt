package votre.portaloperaciones.embarqueinternacional.reimprimir.cmd;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.log4j.Logger;

import votre.portaloperaciones.embarqueinternacional.reimprimir.beans.DetalleReimprimirDTO;
import votre.portaloperaciones.embarqueinternacional.reimprimir.beans.ReimprimirDTO;
import votre.portaloperaciones.embarqueinternacional.reimprimir.delegate.ReimprimirBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class ConsultarCamionesReimprimirAjaxCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String codTrans;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		AjaxXmlBuilder xml = new AjaxXmlBuilder();
		ReimprimirBusiness reimprimirBusiness = null;
		ReimprimirDTO reimprimirDTO = new ReimprimirDTO();
		DetalleReimprimirDTO[] arrCamiones =  null;
		ArrayList<DetalleReimprimirDTO> camiones = new ArrayList<DetalleReimprimirDTO>();
		try{
			reimprimirBusiness = new ReimprimirBusiness();
			obtenerDatos(req);
			dtoAssembler(reimprimirDTO);
			
			reimprimirDTO = reimprimirBusiness.reimprimirEmbarque(reimprimirDTO);
			if(reimprimirDTO != null){
				if(reimprimirDTO.getEstado().equals(Constantes.EXITO_SP)){
					arrCamiones = reimprimirDTO.getDetalle();
					if(arrCamiones != null){
						DetalleReimprimirDTO detalleReimprimirDTO = new DetalleReimprimirDTO();
						detalleReimprimirDTO.setValorConcatenado("0");
						detalleReimprimirDTO.setCamion("-Seleccione-");
						camiones.add(detalleReimprimirDTO);
						for (int i = 0; i < arrCamiones.length; i++) {
							camiones.add(arrCamiones[i]);
						}
					}
				}
				else{
					DetalleReimprimirDTO detalleReimprimirDTO = new DetalleReimprimirDTO();
					detalleReimprimirDTO.setValorConcatenado("0");
					detalleReimprimirDTO.setCamion("-Seleccione-");
					camiones.add(detalleReimprimirDTO);
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
	
	private void dtoAssembler(final ReimprimirDTO reimprimirDTO){
		reimprimirDTO.setCodCia(codCia);
		reimprimirDTO.setAccion(Constantes.ACCION_CAMION_REIMPRIMIR);
		reimprimirDTO.setCodTransportador(codTrans);
		reimprimirDTO.setCamion("");
		reimprimirDTO.setFecha("");
	}

}
