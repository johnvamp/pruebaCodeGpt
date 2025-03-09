package votre.portaloperaciones.embarqueinternacional.transportador.facade;

import java.sql.SQLException;

import votre.dao.DAOFactory;
import votre.portaloperaciones.embarqueinternacional.transportador.beans.TransportadorDTO;
import votre.portaloperaciones.embarqueinternacional.transportador.manager.TransportadorMgr;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class TransportadorFacade implements ITransportadorFacade{

	public TransportadorDTO consultarTransportadores(TransportadorDTO transportadorDTO) throws PersonalsoftException {
		DAOFactory factory = null;
		TransportadorMgr transportadorMgr = null;
		TransportadorDTO obtenido = null;
		
		try{
			factory = new DAOFactory();
			transportadorMgr = new TransportadorMgr();
			obtenido = transportadorMgr.consultarTransportadores(factory, transportadorDTO);
			
		} catch (Exception e) {
			if (e instanceof PersonalsoftException) {
				throw (PersonalsoftException) e;
			} else {
				throw new PersonalsoftException(e);
			}
		} finally {
			try {
				factory.cerrarConexion();
			} catch (SQLException exception) {
				throw new PersonalsoftException(exception);
			}
		}
		return obtenido;
	}

}
