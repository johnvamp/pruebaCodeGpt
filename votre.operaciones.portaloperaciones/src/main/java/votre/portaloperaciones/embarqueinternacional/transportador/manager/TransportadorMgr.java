package votre.portaloperaciones.embarqueinternacional.transportador.manager;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.dao.DAOFactory;
import votre.portaloperaciones.embarqueinternacional.transportador.beans.TransportadorDTO;

public class TransportadorMgr {

	public TransportadorDTO consultarTransportadores(DAOFactory factory, TransportadorDTO transportadorDTO) throws PersonalsoftException{
		return factory.getTransportador().consultarTransportadores(transportadorDTO);
	}
}
