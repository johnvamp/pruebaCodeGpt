package votre.portaloperaciones.transportistas.cmd;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import votre.portaloperaciones.transportistas.beans.TransportistasSeleccionarDTO;
import votre.portaloperaciones.transportistas.delegate.TransportistasBusiness;
import votre.portaloperaciones.util.JSON;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class ConsultarCampanasPedidosTransCmd implements IBaseCmd{

	public void execute(HttpServletRequest req, HttpServletResponse res)throws PersonalsoftException {
		Map<String,Object> salida = new HashMap<String,Object>();
		TransportistasBusiness transportistasBusiness = new TransportistasBusiness();
		TransportistasSeleccionarDTO transportistasSeleccionarComboCampanaDTO = new TransportistasSeleccionarDTO();
		
		String pCia = req.getParameter("cia") != null ? req.getParameter("cia") : "";
		
		transportistasSeleccionarComboCampanaDTO.setDsCia(pCia);
		transportistasSeleccionarComboCampanaDTO.setDsTipo("C");
		transportistasSeleccionarComboCampanaDTO = transportistasBusiness.TransportistasSeleccionar(transportistasSeleccionarComboCampanaDTO);
		salida.put("campanasPedidos", transportistasSeleccionarComboCampanaDTO.getCampanas());
		
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
