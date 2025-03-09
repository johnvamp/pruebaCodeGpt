package votre.portaloperaciones.pedidoscsl.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;

import votre.portaloperaciones.pedidoscsl.beans.PedidosCSLDTO;
import votre.portaloperaciones.pedidoscsl.facade.PedidosCSLFacade;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class ValidarSolicitudAjaxCmd implements IBaseCmd {
	private Logger logger = Logger.getLogger(this.getClass());

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		try{
			PedidosCSLFacade pedidosCSLFacade = new PedidosCSLFacade();
			PedidosCSLDTO pedidosCSLDTO = dtoAssembler(req);
			
			pedidosCSLDTO = pedidosCSLFacade.validarSolictud(pedidosCSLDTO);
			
			if(pedidosCSLDTO != null){
				JAXBContext context = JAXBContext.newInstance(PedidosCSLDTO.class);
				Marshaller m = context.createMarshaller();
				res.setContentType("text/xml; charset=UTF-8");
				m.marshal(pedidosCSLDTO, res.getWriter());
			}
		}
		catch(Exception e){
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}
	
	private PedidosCSLDTO dtoAssembler(HttpServletRequest req){
		PedidosCSLDTO pedidosCSLDTO = new PedidosCSLDTO();
		int relleno;
		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";;
		String destinatario = req.getParameter("destinatario") != null ? req.getParameter("destinatario").trim() : "";
		String val1 = req.getParameter("val1") != null ? req.getParameter("val1").trim() : "";
		String cantidad = req.getParameter("cantidad") != null ? req.getParameter("cantidad").trim() : "";
		String centroCostos = req.getParameter("centroCostos") != null ? req.getParameter("centroCostos").trim() : "";
		
		StringBuffer refe = new StringBuffer(req.getParameter("referencia") != null ? req.getParameter("referencia").trim() : "");
		pedidosCSLDTO.setReferencia(refe.toString()); 
		if(refe.length()>=7){
			refe = new StringBuffer(refe.substring(0, 7));
		}
		else{
			relleno = 7 - refe.length();
			for (int i = 0; i < relleno; i++) {
				refe.append(" ");
			}
		}
		
		StringBuffer color = new StringBuffer(req.getParameter("color") != null ? req.getParameter("color").trim() : "");
		pedidosCSLDTO.setColor(color.toString());
		if(color.length()>=3){
			color = new StringBuffer(color.substring(0, 3));
		}
		else{
			relleno = 3 - color.length();
			for (int i = 0; i < relleno; i++) {
				color.insert(0, " ");
			}
		}

		StringBuffer talla = new StringBuffer(req.getParameter("talla") != null ? req.getParameter("talla").trim() : "");
		pedidosCSLDTO.setTalla(talla.toString());
		if(talla.length()>=3){
			talla = new StringBuffer(talla.substring(0, 3));
		}
		else{
			relleno = 3 - talla.length();
			for (int i = 0; i < relleno; i++) {
				talla.insert(0, " ");
			}
		}
		
		StringBuffer sku = new StringBuffer(refe);
		sku.append(" ");
		sku.append(color);
		sku.append(" ");
		sku.append(talla);
		
		pedidosCSLDTO.setCodCia(codCia);
		pedidosCSLDTO.setDestinatario(destinatario);
		pedidosCSLDTO.setVal1(val1);;
		pedidosCSLDTO.setSku(sku.toString()); 
		pedidosCSLDTO.setCantidad(cantidad);
		pedidosCSLDTO.setCentroCostos(centroCostos);
		
		return pedidosCSLDTO;
	}
}