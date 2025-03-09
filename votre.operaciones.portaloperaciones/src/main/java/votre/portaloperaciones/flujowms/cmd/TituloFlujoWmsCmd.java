package votre.portaloperaciones.flujowms.cmd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import votre.portaloperaciones.flujowms.beans.TituloFlujoWmsDTO;
import votre.portaloperaciones.flujowms.delegate.FlujoWmsBusiness;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class TituloFlujoWmsCmd implements IBaseCmd {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		TituloFlujoWmsDTO tituloFlujoWmsDTO = new TituloFlujoWmsDTO(); 
		FlujoWmsBusiness flujoWmsBusiness = new FlujoWmsBusiness(); 
		
		String idTipoFlujoB = req.getParameter("idTipoFlujo");
		
		tituloFlujoWmsDTO.setIdCompania("001");
		tituloFlujoWmsDTO.setIdTituloFlujoWms("3");
		ArrayList<TituloFlujoWmsDTO> tituloFlujoWms = new ArrayList<TituloFlujoWmsDTO>();
		tituloFlujoWms = flujoWmsBusiness.conusltaTituloFlujoWms(tituloFlujoWmsDTO);
		req.setAttribute("tituloflujowms", tituloFlujoWms);
		req.setAttribute("titulo1",tituloFlujoWms.get(0).getTitulo());
		req.setAttribute("titulo2",tituloFlujoWms.get(1).getTitulo());
		req.setAttribute("titulo3",tituloFlujoWms.get(2).getTitulo());
		req.setAttribute("titulo4",tituloFlujoWms.get(3).getTitulo());
		req.setAttribute("titulo5",tituloFlujoWms.get(4).getTitulo());
		req.setAttribute("titulo6",tituloFlujoWms.get(5).getTitulo());
		req.setAttribute("titulo7",tituloFlujoWms.get(6).getTitulo());
		req.setAttribute("titulo8",tituloFlujoWms.get(7).getTitulo());
		req.setAttribute("titulo9",tituloFlujoWms.get(8).getTitulo());
		req.setAttribute("titulo10",tituloFlujoWms.get(9).getTitulo());
		req.setAttribute("titulo11",tituloFlujoWms.get(10).getTitulo());
		req.setAttribute("titulo12",tituloFlujoWms.get(11).getTitulo());
		req.setAttribute("titulo13",tituloFlujoWms.get(12).getTitulo());

		
		req.setAttribute("idTipoFlujo", idTipoFlujoB);
		
	}

}
