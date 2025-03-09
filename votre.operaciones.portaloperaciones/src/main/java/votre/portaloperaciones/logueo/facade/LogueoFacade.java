package votre.portaloperaciones.logueo.facade;

import java.sql.SQLException;
import java.util.ArrayList;

import co.com.personalsoft.base.excepcion.PersonalsoftException;

import votre.dao.DAOFactory;
import votre.portaloperaciones.logueo.beans.LogueoDTO;
import votre.portaloperaciones.logueo.manager.LogueoMgr;

public class LogueoFacade implements ILogueoFacade {
	
	public ArrayList<LogueoDTO> consultarPaises (LogueoDTO logueoDTO) throws PersonalsoftException{
		DAOFactory daoFactory = null;
		LogueoMgr logueoMgr = null;
		ArrayList<LogueoDTO> obtenido = null;
	
		try{
			daoFactory = new DAOFactory();
			logueoMgr = new LogueoMgr();
			obtenido = logueoMgr.consultarPaises(daoFactory, logueoDTO);
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
