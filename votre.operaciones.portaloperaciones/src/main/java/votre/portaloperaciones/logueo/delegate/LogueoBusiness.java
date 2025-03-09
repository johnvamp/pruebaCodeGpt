package votre.portaloperaciones.logueo.delegate;

import java.util.ArrayList;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.service.ServiceLocator;

import votre.portaloperaciones.logueo.beans.LogueoDTO;
import votre.portaloperaciones.logueo.facade.ILogueoFacade;

public class LogueoBusiness {
	private final String claveFacade = "logueoFacade";
	private ILogueoFacade logueoFacade;
	
	public LogueoBusiness() throws PersonalsoftException{
		String nombreServicio = Configuracion.getInstance().getServicio(claveFacade).getRuta();
		logueoFacade = (ILogueoFacade) ServiceLocator.getInstance().lookup(nombreServicio);		
	}
	
	public ArrayList<LogueoDTO> consultarPaises(LogueoDTO logueoDTO) throws PersonalsoftException{
		return logueoFacade.consultarPaises(logueoDTO);
	}

}
