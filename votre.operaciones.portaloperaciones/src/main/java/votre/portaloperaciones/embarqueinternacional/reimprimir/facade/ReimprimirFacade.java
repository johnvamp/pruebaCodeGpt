package votre.portaloperaciones.embarqueinternacional.reimprimir.facade;

import java.sql.SQLException;

import votre.dao.DAOFactory;
import votre.portaloperaciones.embarqueinternacional.reimprimir.beans.ReimprimirDTO;
import votre.portaloperaciones.embarqueinternacional.reimprimir.manager.ReimprimirMgr;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class ReimprimirFacade implements IReimprimirFacade {

	public ReimprimirDTO reimprimirEmbarque(ReimprimirDTO reimprimirDTO) throws PersonalsoftException {
		DAOFactory factory = null;
		ReimprimirMgr reimprimirMgr = null;
		ReimprimirDTO obtenido = null;
		
		try{
			factory = new DAOFactory();
			reimprimirMgr = new ReimprimirMgr();
			obtenido = reimprimirMgr.reimprimirEmbarque(factory, reimprimirDTO);
			
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
