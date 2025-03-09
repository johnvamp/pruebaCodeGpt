package votre.portaloperaciones.embarqueinternacional.camion.facade;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.portaloperaciones.embarqueinternacional.camion.beans.CamionDTO;

public interface ICamionFacade {

	public CamionDTO consultaCamionesAbiertos(CamionDTO camionDTO) throws PersonalsoftException;
}
