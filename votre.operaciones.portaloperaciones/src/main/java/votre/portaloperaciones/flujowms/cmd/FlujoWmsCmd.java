package votre.portaloperaciones.flujowms.cmd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import votre.portaloperaciones.flujowms.beans.FlujoWmsDTO;
import votre.portaloperaciones.flujowms.delegate.FlujoWmsBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class FlujoWmsCmd implements IBaseCmd{

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		
		
		
		FlujoWmsDTO flujoWmsDTO = new FlujoWmsDTO();
		FlujoWmsBusiness flujoWmsBusiness = new FlujoWmsBusiness(); 
		String codCia;
		codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		String idTipoFlujoB = req.getParameter("idTipoFlujo");	

		flujoWmsDTO.setIdCompania(codCia);
		flujoWmsDTO.setIdTipoFlujoWms(idTipoFlujoB);
		
		ArrayList<FlujoWmsDTO> flujoWms = new ArrayList<FlujoWmsDTO>();
		flujoWms = flujoWmsBusiness.consultaFlujoWms(flujoWmsDTO);
		req.setAttribute("flujowms", flujoWms);		
		req.setAttribute("tipoFlujo", idTipoFlujoB);
	}

}
