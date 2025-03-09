package votre.portaloperaciones.transportistas.cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import votre.portaloperaciones.transportistas.beans.TransportistasComboDTO;
import votre.portaloperaciones.transportistas.delegate.TransportistasBusiness;
import votre.portaloperaciones.util.JSON;
import co.com.personalsoft.base.beans.Item;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class EnviarMaestrosMovilCmd implements IBaseCmd{
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		TransportistasBusiness transportistasBusiness;
		transportistasBusiness = new TransportistasBusiness();
		Map<String,Object> salida = new HashMap<String,Object>();
		
		String pCia = req.getParameter("cia") != null ? req.getParameter("cia") : "";
		
		//Listar los motivos de entrega Primera y Segunda visita
		TransportistasComboDTO dto1 = new TransportistasComboDTO();
		dto1.setDsCia(pCia);
		dto1.setDsTipo("V");
		dto1 = transportistasBusiness.TransportistasCombo( dto1 );
		
		ArrayList<Item> estadosPrimera = new ArrayList<Item>();
		Iterator<String> it = dto1.getDsEstaCombo().keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			Item item = new Item(key, dto1.getDsEstaCombo().get(key));
			estadosPrimera.add(item);
		}
		salida.put("estadosPrimeraVisita", estadosPrimera);
		
		//Listar los motivos de entrega Tercera visita
		TransportistasComboDTO dto2 = new TransportistasComboDTO();
		dto2.setDsCia(pCia);
		dto2.setDsTipo("F");
		dto2 = transportistasBusiness.TransportistasCombo( dto2 );
		
		ArrayList<Item> estadosUltima = new ArrayList<Item>();
		Iterator<String> it2 = dto2.getDsEstaCombo().keySet().iterator();
		while(it2.hasNext()){
			String key = it2.next();
			Item item = new Item(key, dto2.getDsEstaCombo().get(key));
			estadosUltima.add(item);
		}
		salida.put("estadosUltimaVisita", estadosUltima);
		
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
