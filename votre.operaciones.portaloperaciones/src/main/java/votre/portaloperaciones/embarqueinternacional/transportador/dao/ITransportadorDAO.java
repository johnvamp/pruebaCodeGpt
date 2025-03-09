package votre.portaloperaciones.embarqueinternacional.transportador.dao;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.portaloperaciones.embarqueinternacional.transportador.beans.TransportadorDTO;

public interface ITransportadorDAO {

	public TransportadorDTO consultarTransportadores(TransportadorDTO transportadorDTO) throws PersonalsoftException;
}
