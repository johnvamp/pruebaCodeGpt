package votre.portaloperaciones.embarqueinternacional.camion.manager;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.dao.DAOFactory;
import votre.portaloperaciones.embarqueinternacional.camion.beans.CamionDTO;

public class CamionMgr {

	public CamionDTO consultaCamionesAbiertos(DAOFactory factory ,CamionDTO camionDTO) throws PersonalsoftException{
		return factory.getCamion().consultaCamionesAbiertos(camionDTO);
	}
}
