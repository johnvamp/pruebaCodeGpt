package votre.portaloperaciones.suscripcioncatalogo.facade;

import java.sql.SQLException;

import votre.dao.DAOFactory;
import votre.portaloperaciones.suscripcioncatalogo.beans.SuscripcionCatalogoDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class SuscripcionesFacade implements ISuscripcionesFacade {

	public SuscripcionCatalogoDTO suscribir(SuscripcionCatalogoDTO dto)	throws PersonalsoftException {
		DAOFactory daoFactory = null;
		SuscripcionCatalogoDTO obtenido = null;
		
		try {
			daoFactory = new DAOFactory();
			obtenido = daoFactory.getSuscripciones().suscribir(dto);
		} catch (Exception e) {
			if (e instanceof PersonalsoftException) {
				throw (PersonalsoftException) e;
			} else {
				throw new PersonalsoftException(e);
			}
		} finally {
			try {
				daoFactory.cerrarConexion();
			} catch (SQLException exception) {
				throw new PersonalsoftException(exception);
			}
		}
		return obtenido;
	}
}
