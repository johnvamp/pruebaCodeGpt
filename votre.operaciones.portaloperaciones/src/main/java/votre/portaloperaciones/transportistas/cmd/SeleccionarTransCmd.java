package votre.portaloperaciones.transportistas.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import votre.portaloperaciones.transportistas.beans.TransportistasSeleccionarDTO;
import votre.portaloperaciones.transportistas.delegate.TransportistasBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class SeleccionarTransCmd implements IBaseCmd{

	public void execute(HttpServletRequest req, HttpServletResponse res)throws PersonalsoftException {
		TransportistasBusiness transportistasBusiness;
		transportistasBusiness = new TransportistasBusiness();
		
		String pCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		
		TransportistasSeleccionarDTO transportistasSeleccionarComboTransporDTO = new TransportistasSeleccionarDTO();
		transportistasSeleccionarComboTransporDTO.setDsCia(pCia);
		transportistasSeleccionarComboTransporDTO.setDsTipo("T");
		transportistasSeleccionarComboTransporDTO = transportistasBusiness.TransportistasSeleccionar(transportistasSeleccionarComboTransporDTO);
		req.setAttribute("transportistasComboTranspor", transportistasSeleccionarComboTransporDTO.getDsListaTranspoCampana());
		
		TransportistasSeleccionarDTO transportistasSeleccionarComboCampanaDTO = new TransportistasSeleccionarDTO();
		transportistasSeleccionarComboCampanaDTO.setDsCia(pCia);
		transportistasSeleccionarComboCampanaDTO.setDsTipo("C");
		transportistasSeleccionarComboCampanaDTO = transportistasBusiness.TransportistasSeleccionar(transportistasSeleccionarComboCampanaDTO);
		//req.setAttribute("transportistasComboCampana", transportistasSeleccionarComboCampanaDTO.getDsListaTranspoCampana());
		req.setAttribute("transportistasComboCampana", transportistasSeleccionarComboCampanaDTO.getCampanas());
		
		TransportistasSeleccionarDTO transportistasSeleccionarComboEstadoDTO = new TransportistasSeleccionarDTO();
		transportistasSeleccionarComboEstadoDTO.setDsCia(pCia);
		transportistasSeleccionarComboEstadoDTO.setDsTipo("E");
		transportistasSeleccionarComboEstadoDTO = transportistasBusiness.TransportistasSeleccionar(transportistasSeleccionarComboEstadoDTO);
		req.setAttribute("transportistasComboEstado", transportistasSeleccionarComboEstadoDTO.getDsListaTranspoEstado());
	}
}
