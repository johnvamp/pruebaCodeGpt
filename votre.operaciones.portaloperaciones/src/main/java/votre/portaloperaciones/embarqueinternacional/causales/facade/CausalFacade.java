package votre.portaloperaciones.embarqueinternacional.causales.facade;

import java.sql.SQLException;
import java.util.ArrayList;

import votre.dao.DAOFactory;
import votre.portaloperaciones.embarqueinternacional.causales.beans.CausalDTO;
import votre.portaloperaciones.embarqueinternacional.causales.manager.CausalMgr;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class CausalFacade implements ICausalFacade {

	public ArrayList<CausalDTO> cargarCausales(String codCia) throws PersonalsoftException {
		DAOFactory factory = null;
		CausalMgr causalMgr = null;
		ArrayList<CausalDTO> obtenido = null;
		
		try{
			factory = new DAOFactory();
			causalMgr = new CausalMgr();
			obtenido = causalMgr.cargarCausales(factory, codCia);
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
