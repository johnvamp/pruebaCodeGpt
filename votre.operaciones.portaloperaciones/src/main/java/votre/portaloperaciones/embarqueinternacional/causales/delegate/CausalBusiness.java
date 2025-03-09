package votre.portaloperaciones.embarqueinternacional.causales.delegate;

import java.util.ArrayList;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.service.ServiceLocator;
import votre.portaloperaciones.embarqueinternacional.causales.beans.CausalDTO;
import votre.portaloperaciones.embarqueinternacional.causales.facade.ICausalFacade;

public class CausalBusiness {

	private final String claveFacade = "causalFacade";
	private ICausalFacade causalFacade;
	
	public CausalBusiness() throws PersonalsoftException{
		String nombreServicio = Configuracion.getInstance().getServicio(claveFacade).getRuta();
		causalFacade = (ICausalFacade)ServiceLocator.getInstance().lookup(nombreServicio);
	}
	
	public ArrayList<CausalDTO> cargarCausales(String codCia) throws PersonalsoftException{
		return causalFacade.cargarCausales(codCia);
	}
}
