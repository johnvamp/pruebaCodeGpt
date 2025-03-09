package votre.portaloperaciones.transportistas.cmd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import votre.portaloperaciones.transportistas.beans.TransportistasComboDTO;
import votre.portaloperaciones.transportistas.beans.TransportistasDTO;
import votre.portaloperaciones.transportistas.beans.TransportistasSeleccionarDTO;
import votre.portaloperaciones.transportistas.delegate.TransportistasBusiness;
import votre.portaloperaciones.util.Constantes;
import votre.portaloperaciones.util.Util;
import votre.utils.moduloseguridad.beans.UsuarioDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

/**
 * Se modifica el servicio para que las busquedas se realicen en lado el servidor
 * por medio del SP al cual se le hizo una modificación
 * @author camiloserna
 * @update 10/05/2017
 */
public class PedidosTransCmd implements IBaseCmd{

	public void execute(HttpServletRequest req, HttpServletResponse res)throws PersonalsoftException {
		TransportistasDTO transportistasDTO = null;
		TransportistasBusiness transportistasBusiness;
		transportistasBusiness = new TransportistasBusiness();
		
		String Error;
		String MsgError;
		String ErrorCedual = "0";
		String MsgErrorCedual = "";
		String ErrorGuia = "0";
		String MsgErrorGuia ="";
		String NRegistrosPendi = "1";
		
		String     pCia          = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		UsuarioDTO pUsr          = (UsuarioDTO) (req.getSession().getAttribute( Constantes.USUARIO_SESION) != null ? req.getSession().getAttribute( Constantes.USUARIO_SESION ) : "");
		String 	   codigoCampana = req.getParameter("buscarCampana") != null ? req.getParameter("buscarCampana") : "";
		String     TipoTranspo   = req.getParameter("pEstBuscar") != null ? req.getParameter("pEstBuscar") : "";
		String     guiaBuscar    = req.getParameter("buscarGuia") != null ? req.getParameter("buscarGuia") : "";
		String     cedulaBuscar  = req.getParameter("buscarCedula") != null ? req.getParameter("buscarCedula") : "";
		String     codigoZona    = req.getParameter("buscarZona") != null ? req.getParameter("buscarZona") : "";
		String     tipoConsulta  = req.getParameter("tipoConsulta") != null ? req.getParameter("tipoConsulta") : "";
		
		if("".equals( Util.ifEmpty(codigoCampana) )){
			codigoCampana =  (String) (req.getAttribute("buscarCampana") != null ? req.getAttribute("buscarCampana") : "");
		}
		if("".equals( Util.ifEmpty(TipoTranspo) )){
			TipoTranspo =  (String) (req.getAttribute("pEstBuscar") != null ? req.getAttribute("pEstBuscar") : "");
		}
		if("".equals( Util.ifEmpty(codigoZona) )){
			codigoZona =  (String) (req.getAttribute("buscarZona") != null ? req.getAttribute("buscarZona") : "");
		}
		
		guiaBuscar = guiaBuscar.replaceAll(" ","").replaceAll("\\.","").replaceAll(",","");
		
		if(tipoConsulta.equals("G")){
			transportistasDTO = new TransportistasDTO( pCia, TipoTranspo, pUsr.getUsuario(), codigoCampana, codigoZona, tipoConsulta, guiaBuscar );
		}
		else if(tipoConsulta.equals("C")){
			transportistasDTO = new TransportistasDTO( pCia, TipoTranspo, pUsr.getUsuario(), codigoCampana, codigoZona, tipoConsulta, cedulaBuscar );
		}
		else{
			transportistasDTO = new TransportistasDTO( pCia, TipoTranspo, pUsr.getUsuario(), codigoCampana, codigoZona, tipoConsulta, "" );
		}
		
		ArrayList<TransportistasDTO> transportistas = new ArrayList<TransportistasDTO>();
		transportistas = transportistasBusiness.Transportistas(transportistasDTO);
		String NRegistros = transportistasDTO.getTotalPedi();
		String NomTranspo = transportistasDTO.getNomTranspo();
		String TotalPedi = transportistasDTO.getTotalPedi();
		Error = transportistasDTO.getError();
		MsgError = transportistasDTO.getMsgError();
		
		if (!guiaBuscar.equals("")) {
			for (TransportistasDTO transportistasDTO2 : transportistas) {
				if (guiaBuscar.equals(transportistasDTO2.getNumeroGuia())) {
					req.setAttribute("mosTabla", "1");
					ErrorGuia = "0";
					MsgErrorGuia = "";
					NRegistrosPendi = "0";
					break;
				} else {
					ErrorGuia = "1";
					NRegistros = "0";
					TotalPedi = "0";
					MsgErrorGuia = "No se Encontraron Registros";
				}
			}
		}

		if (!cedulaBuscar.equals("")) {
			for (TransportistasDTO transportistasDTO2 : transportistas) {
				if (cedulaBuscar.equals(transportistasDTO2.getCedula())) {
					req.setAttribute("mosTabla", "2");
					ErrorCedual = "0";
					MsgErrorCedual = "";
					NRegistrosPendi = "0";
					break;
				} else {
					ErrorCedual = "1";
					NRegistros = "0";
					TotalPedi = "0";
					MsgErrorCedual = "No se Encontraron Registros";
				}
			}
		}
	
		TransportistasComboDTO transportistasComboDTO1y2 = new TransportistasComboDTO();
		transportistasComboDTO1y2.setDsCia(pCia);
		transportistasComboDTO1y2.setDsTipo("V");	
		transportistasComboDTO1y2 = transportistasBusiness.TransportistasCombo(transportistasComboDTO1y2);
    	req.setAttribute("transportistasCombo1y2", transportistasComboDTO1y2.getDsEstaCombo());
     	
		TransportistasComboDTO transportistasComboDTO3 = new TransportistasComboDTO();
		transportistasComboDTO3.setDsTipo("F");	
		transportistasComboDTO3 = transportistasBusiness.TransportistasCombo(transportistasComboDTO3);
		req.setAttribute("transportistasCombo3",  transportistasComboDTO3.getDsEstaCombo());
		
		req.setAttribute("transportistas",  transportistas);
		req.setAttribute("buscarGuia",  guiaBuscar);
		req.setAttribute("cedulaBuscar", cedulaBuscar);
		req.setAttribute("NomTranspo",NomTranspo);
		req.setAttribute("TotalPedi", TotalPedi);
		req.setAttribute("Error", Error);
		req.setAttribute("MsgError", MsgError);	
		req.setAttribute("NRegistros",NRegistros);		
		req.setAttribute("codigoZona",codigoZona);
		req.setAttribute("ErrorCedual", ErrorCedual);
		req.setAttribute("MsgErrorCedual", MsgErrorCedual);	
		req.setAttribute("ErrorGuia", ErrorGuia);
		req.setAttribute("MsgErrorGuia", MsgErrorGuia);	
		req.setAttribute("NRegistrosPendi", NRegistrosPendi);	
		req.setAttribute("zona", codigoZona);
		
		TransportistasSeleccionarDTO transportistasSeleccionarComboCampanaDTO = new TransportistasSeleccionarDTO();
		transportistasSeleccionarComboCampanaDTO.setDsCia(pCia);
		transportistasSeleccionarComboCampanaDTO.setDsTipo("C");
		transportistasSeleccionarComboCampanaDTO = transportistasBusiness.TransportistasSeleccionar(transportistasSeleccionarComboCampanaDTO);
		req.setAttribute("transportistasComboCampana", transportistasSeleccionarComboCampanaDTO.getCampanas());
		
		req.setAttribute("transportistasComboCampanaSelect",codigoCampana);
	}
}
