package votre.portaloperaciones.logueo.manager;

import java.util.ArrayList;

import co.com.personalsoft.base.excepcion.PersonalsoftException;

import votre.dao.DAOFactory;
import votre.portaloperaciones.logueo.beans.LogueoDTO;

public class LogueoMgr {
	
	public ArrayList<LogueoDTO> consultarPaises (DAOFactory factory, LogueoDTO logueoDTO) throws PersonalsoftException{
		return factory.getLogueo().consultarPaises(logueoDTO);
	}

}
