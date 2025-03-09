package votre.portaloperaciones.transportistas.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import votre.portaloperaciones.transportistas.beans.TransportistasDTO;
import votre.portaloperaciones.transportistas.delegate.TransportistasBusiness;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class GuardarTransCmd implements IBaseCmd{

	public void execute(HttpServletRequest req, HttpServletResponse res)throws PersonalsoftException {
		TransportistasDTO transportistasDTO = new TransportistasDTO();
		TransportistasBusiness transportistasBusiness;
		transportistasBusiness = new TransportistasBusiness();
		
		String pCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
						
		String numeroOrden = req.getParameter("numeroOrdenE") != null ? req.getParameter("numeroOrdenE") : "";
		String numeroGuia = req.getParameter("numeroGuiaE") != null ? req.getParameter("numeroGuiaE") : "";
		String transportistasCombo = req.getParameter("estadoE") != null ? req.getParameter("estadoE") : "";
		String observaciones = req.getParameter("observacionE") != null ? req.getParameter("observacionE") : "";
		String codigoCampana =  req.getParameter("buscarCampana") != null ? req.getParameter("buscarCampana") : "";
		
				
		transportistasDTO.setpCia(pCia);
		transportistasDTO.setNumeroOrden(numeroOrden);
		transportistasDTO.setNumeroGuia(numeroGuia);
		
		String EstadoCombo="";
		String CodigoCombo="";	
		
		if(transportistasCombo != null)
		{
			String[] valores = transportistasCombo.split("-");
			if(valores.length == 2){
			  EstadoCombo = valores[0];
			  CodigoCombo = valores[1];		  
			}
		}
		
		transportistasDTO.setEstadoCombo(EstadoCombo);
		transportistasDTO.setCodigoCombo(CodigoCombo);	
		transportistasDTO.setObservaciones(observaciones);
		
		transportistasDTO = transportistasBusiness.TransportistasGuardar(transportistasDTO);

		String Error = transportistasDTO.getError();
		String menError = transportistasDTO.getMsgError();
		
		req.setAttribute("ErrorGuar",Error);		
		req.setAttribute("MsgErrorGuar",menError);
		req.setAttribute("buscarCampana", codigoCampana);
		
		
	}

}
