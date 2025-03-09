package votre.portaloperaciones.embarqueinternacional.transportador.facade;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.portaloperaciones.embarqueinternacional.transportador.beans.TransportadorDTO;

public interface ITransportadorFacade {

	public TransportadorDTO consultarTransportadores(TransportadorDTO transportadorDTO) throws PersonalsoftException;
}
