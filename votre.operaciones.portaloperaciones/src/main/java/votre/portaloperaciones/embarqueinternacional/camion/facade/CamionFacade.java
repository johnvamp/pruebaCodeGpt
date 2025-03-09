package votre.portaloperaciones.embarqueinternacional.camion.facade;

import java.sql.SQLException;

import votre.dao.DAOFactory;
import votre.portaloperaciones.embarqueinternacional.camion.beans.CamionDTO;
import votre.portaloperaciones.embarqueinternacional.camion.manager.CamionMgr;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class CamionFacade implements ICamionFacade {

	public CamionDTO consultaCamionesAbiertos(CamionDTO camionDTO) throws PersonalsoftException {
		DAOFactory factory = null;
		CamionMgr camionMgr = null;
		CamionDTO obtenido = null;
		
		try{
			factory = new DAOFactory();
			camionMgr = new CamionMgr();
			obtenido = camionMgr.consultaCamionesAbiertos(factory, camionDTO);
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
