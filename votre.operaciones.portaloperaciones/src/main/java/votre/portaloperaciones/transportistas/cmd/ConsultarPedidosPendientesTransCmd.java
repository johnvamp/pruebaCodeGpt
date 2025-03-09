package votre.portaloperaciones.transportistas.cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import votre.portaloperaciones.transportistas.beans.TransportistasDTO;
import votre.portaloperaciones.transportistas.delegate.TransportistasBusiness;
import votre.portaloperaciones.util.JSON;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class ConsultarPedidosPendientesTransCmd implements IBaseCmd{

	public void execute(HttpServletRequest req, HttpServletResponse res)throws PersonalsoftException {
		Map<String,Object> salida = new HashMap<String,Object>();
		TransportistasBusiness transportistasBusiness = new TransportistasBusiness();
		TransportistasDTO transportistasDTO = new TransportistasDTO();
		
		String pCia = req.getParameter("cia") != null ? req.getParameter("cia") : "";
		String campanaBuscar = req.getParameter("campanaBuscar") != null ? req.getParameter("campanaBuscar") : "";
		String usu = req.getParameter("usu") != null ? req.getParameter("usu") : "";
		
		transportistasDTO.setpEst("P");
		transportistasDTO.setpCia(pCia);
		transportistasDTO.setGuiaBuscar("");	
		transportistasDTO.setNumeroCampana(campanaBuscar);
		transportistasDTO.setNumeroZona("");
		transportistasDTO.setpUsr(usu);
		transportistasDTO.setpTipoConsult("");
		transportistasDTO.setValor("");
		ArrayList<TransportistasDTO> pedidosPendientes = new ArrayList<TransportistasDTO>();
		pedidosPendientes = transportistasBusiness.Transportistas(transportistasDTO);
		
		salida.put("pedidosPendientes", pedidosPendientes);
		
		try{
			res.setContentType("application/json; charset='UTF-8'");
			res.setHeader("Cache-Control", "must-revalidate, no-cache, no-store, max-age=0");
			res.setHeader("Pragma", "no-cache");
			res.setDateHeader("Expires", 0);
			res.getWriter().write( JSON.dump(salida) );
		}catch (IOException e) {
			throw new PersonalsoftException(e);
		}
	}
}
