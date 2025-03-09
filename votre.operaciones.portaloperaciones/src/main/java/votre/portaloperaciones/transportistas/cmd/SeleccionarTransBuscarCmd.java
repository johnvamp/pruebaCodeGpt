package votre.portaloperaciones.transportistas.cmd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import votre.portaloperaciones.transportistas.beans.TransportistasDTO;
import votre.portaloperaciones.transportistas.delegate.TransportistasBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class SeleccionarTransBuscarCmd implements IBaseCmd {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		TransportistasDTO transportistasDTO = new TransportistasDTO();
		TransportistasBusiness transportistasBusiness;
		transportistasBusiness = new TransportistasBusiness();
		
		String pCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		
		String codigoTranspor =  req.getParameter("buscarTranspor") != null ? req.getParameter("buscarTranspor") : "";
		String codigoCampana =  req.getParameter("buscarCampana") != null ? req.getParameter("buscarCampana") : "";
		String codigoEstado =  req.getParameter("buscarEstado") != null ? req.getParameter("buscarEstado") : "";
		
	
		transportistasDTO.setpEst(codigoEstado);
		transportistasDTO.setpCia(pCia);
		transportistasDTO.setpUsr(codigoTranspor);
		transportistasDTO.setNumeroCampana(codigoCampana);
		transportistasDTO.setNumeroZona("");
		transportistasDTO.setpTipoConsult("");
		transportistasDTO.setValor("");
		
		ArrayList<TransportistasDTO> transportistas = new ArrayList<TransportistasDTO>();
		transportistas = transportistasBusiness.Transportistas(transportistasDTO);

		
		req.setAttribute("transportistas",  transportistas);		
		req.setAttribute("transportistasComboCampanaSelect",  codigoCampana);
		req.setAttribute("transportistasComboTransporSelect",  codigoTranspor);
		req.setAttribute("transportistasComboEstadoSelect",  codigoEstado);
		
		String NomTrasnpo = transportistasDTO.getNomTranspo();
		req.setAttribute("NomTrasnpo",NomTrasnpo);
		String NRegistros = transportistasDTO.getTotalPedi();
		req.setAttribute("NRegistros",NRegistros);
		String Error = transportistasDTO.getError();
		req.setAttribute("ErrorLista",Error);
		String menError = transportistasDTO.getMsgError();
		req.setAttribute("menErrorLista",menError);
		
		
	}

}
