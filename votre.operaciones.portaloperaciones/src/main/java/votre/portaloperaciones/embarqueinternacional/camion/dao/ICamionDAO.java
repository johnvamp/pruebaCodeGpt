package votre.portaloperaciones.embarqueinternacional.camion.dao;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.portaloperaciones.embarqueinternacional.camion.beans.CamionDTO;

public interface ICamionDAO {
	
	public CamionDTO consultaCamionesAbiertos(CamionDTO camionDTO) throws PersonalsoftException;
}
