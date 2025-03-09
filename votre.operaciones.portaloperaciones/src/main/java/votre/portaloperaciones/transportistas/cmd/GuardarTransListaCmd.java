package votre.portaloperaciones.transportistas.cmd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import votre.portaloperaciones.transportistas.beans.TransportistasDTO;
import votre.portaloperaciones.transportistas.delegate.TransportistasBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class GuardarTransListaCmd implements IBaseCmd{

	public void execute(HttpServletRequest req, HttpServletResponse res)throws PersonalsoftException {
		TransportistasDTO transportistasDTO = new TransportistasDTO();
		TransportistasBusiness transportistasBusiness;
		transportistasBusiness = new TransportistasBusiness();

		String pCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		
		String textoListGuia = req.getParameter("listGuia") != null ? req.getParameter("listGuia") : "";
		String [] lineasListGuia = textoListGuia.split("\r\n");
		
		ArrayList<String> lineasListGuiaError = new ArrayList<String>();
		
		for(int i=0;i<lineasListGuia.length;i++){
			
			String numeroGuia = lineasListGuia[i];
			transportistasDTO.setpCia(pCia);
			transportistasDTO.setNumeroOrden("0");
			transportistasDTO.setNumeroGuia(numeroGuia);
			transportistasDTO.setEstadoCombo("Entre");
			transportistasDTO.setCodigoCombo("520");	
			transportistasDTO.setObservaciones("");
			
			
			transportistasDTO = transportistasBusiness.TransportistasGuardar(transportistasDTO);

			String Error = transportistasDTO.getError();
			req.setAttribute("ErrorLista",Error);
			String menError = transportistasDTO.getMsgError();
			req.setAttribute("menErrorLista",menError);
			
			if(Error.equals("1")){
				lineasListGuiaError.add(numeroGuia + " - "+ menError);			
			}		
        }		
		req.setAttribute("ListGuiaError",lineasListGuiaError);
		
	}

}
