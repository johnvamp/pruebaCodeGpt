package votre.portaloperaciones.consultasku.bodegas.facade;

import java.sql.SQLException;
import java.util.ArrayList;

import votre.dao.DAOFactory;
import votre.portaloperaciones.consultasku.bodegas.beans.BodegaDTO;
import votre.portaloperaciones.consultasku.bodegas.manager.BodegaMgr;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class BodegaFacade implements IBodegaFacade {

	public ArrayList<BodegaDTO> consultarBodegas(String codCia) throws PersonalsoftException {
		DAOFactory factory = null;
		BodegaMgr bodegaMgr = null;
		ArrayList<BodegaDTO> obtenido = null;
		
		try{
			factory = new DAOFactory();
			bodegaMgr = new BodegaMgr();
			obtenido = bodegaMgr.consultarBodegas(factory, codCia);
		}
		catch (Exception e) {
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
