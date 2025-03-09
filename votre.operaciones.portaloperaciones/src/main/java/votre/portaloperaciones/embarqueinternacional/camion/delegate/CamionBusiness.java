package votre.portaloperaciones.embarqueinternacional.camion.delegate;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.service.ServiceLocator;
import votre.portaloperaciones.embarqueinternacional.camion.beans.CamionDTO;
import votre.portaloperaciones.embarqueinternacional.camion.facade.ICamionFacade;

public class CamionBusiness {

	private final String claveFacade = "camionFacade";
	private ICamionFacade camionFacade;
	
	public CamionBusiness() throws PersonalsoftException{
		String nombreServicio = Configuracion.getInstance().getServicio(claveFacade).getRuta();
		camionFacade = (ICamionFacade)ServiceLocator.getInstance().lookup(nombreServicio);
	}
	
	public CamionDTO consultarCamionesAbiertos(CamionDTO camionDTO) throws PersonalsoftException{
		return camionFacade.consultaCamionesAbiertos(camionDTO);
	}
}
