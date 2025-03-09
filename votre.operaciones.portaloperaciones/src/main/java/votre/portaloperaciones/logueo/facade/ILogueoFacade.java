package votre.portaloperaciones.logueo.facade;

import java.util.ArrayList;

import co.com.personalsoft.base.excepcion.PersonalsoftException;

import votre.portaloperaciones.logueo.beans.LogueoDTO;

public interface ILogueoFacade {
	
	public ArrayList<LogueoDTO> consultarPaises(LogueoDTO logueoDTO) throws PersonalsoftException;
}
