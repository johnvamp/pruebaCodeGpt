package votre.portaloperaciones.transportistas.cmd;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;
import votre.portaloperaciones.transportistas.beans.TransportistasSeleccionarDTO;
import votre.portaloperaciones.transportistas.delegate.TransportistasBusiness;
import votre.portaloperaciones.util.Constantes;
import votre.portaloperaciones.util.Util;

/**
 * Consulta lista de transportistas
 * @author camiloserna
 * @date 27/04/2017
 */
public class PediTransportCampaniasCMD implements IBaseCmd{

	private Logger logger = Logger.getLogger(this.getClass());
	PersonalsoftException ps = null;
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		TransportistasBusiness transportistasBusiness;
		TransportistasSeleccionarDTO dto = null;
		try{
			transportistasBusiness = new TransportistasBusiness();
			dto = dtoAssembler(req);
			
			String jsonDto = null;
			Gson gson = new Gson();
			try {
				Integer.parseInt(dto.getDsCia());
				dto = transportistasBusiness.TransportistasSeleccionar(dto);
			} catch (NumberFormatException nfe) {
//				dto.set("1");
//				dto.setMsgError("No existe el código de la compañía, por favor inicie nuevamente la sesión.");
			}
			jsonDto = gson.toJson(dto);
			
			res.setContentType("application/json; charset='UTF-8'");
			PrintWriter out = res.getWriter();
			out.print(jsonDto);
			out.flush();
		}
		catch (Exception e) {
			ps = new PersonalsoftException(e);
			logger.error(ps.getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
			throw ps;
		}
	}
	
	private TransportistasSeleccionarDTO dtoAssembler(HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		
		String a_codCia = Util.ifEmpty((String) req.getSession().getAttribute( Constantes.COD_CIA ));
		String p_dsTipo = Util.ifEmpty(req.getParameter("p_dsTipo"));
		
		TransportistasSeleccionarDTO dto = new TransportistasSeleccionarDTO();
		dto.setDsCia(a_codCia);
		dto.setDsTipo(p_dsTipo);
		
		return dto;
	}

}
