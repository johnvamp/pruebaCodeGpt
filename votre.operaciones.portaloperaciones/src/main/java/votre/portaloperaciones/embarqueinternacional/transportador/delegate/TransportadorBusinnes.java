package votre.portaloperaciones.embarqueinternacional.transportador.delegate;

import votre.portaloperaciones.embarqueinternacional.transportador.beans.TransportadorDTO;
import votre.portaloperaciones.embarqueinternacional.transportador.facade.ITransportadorFacade;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.service.ServiceLocator;

public class TransportadorBusinnes {

	private final String claveFacade = "transportadorFacade";
	private ITransportadorFacade transportadorFacade;
	
	public TransportadorBusinnes() throws PersonalsoftException{
		String nombreServicio = Configuracion.getInstance().getServicio(claveFacade).getRuta();
		transportadorFacade = (ITransportadorFacade)ServiceLocator.getInstance().lookup(nombreServicio);
	}
	
	public TransportadorDTO consultarTransportadores(TransportadorDTO transportadorDTO) throws PersonalsoftException{
		return transportadorFacade.consultarTransportadores(transportadorDTO);
	}
}
