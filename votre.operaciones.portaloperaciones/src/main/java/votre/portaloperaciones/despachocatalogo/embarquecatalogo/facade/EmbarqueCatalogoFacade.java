package votre.portaloperaciones.despachocatalogo.embarquecatalogo.facade;

import java.sql.SQLException;

import votre.dao.DAOFactory;
import votre.portaloperaciones.despachocatalogo.embarquecatalogo.beans.GuiasEmbarqueDTO;
import votre.portaloperaciones.despachocatalogo.embarquecatalogo.beans.TransportadorasDTO;
import votre.portaloperaciones.despachocatalogo.embarquecatalogo.manager.EmbarqueCatalogoMgr;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class EmbarqueCatalogoFacade implements IEmbarqueCatalogoFacade {

	public TransportadorasDTO consultarTransportadoras(TransportadorasDTO dto) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		TransportadorasDTO obtenido = null;
		try {
			daoFactory = new DAOFactory();
			obtenido = daoFactory.getEmbarqueCatalogo().consultarTransportadoras(dto);
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

	public GuiasEmbarqueDTO enviarGuiasEmbarque(GuiasEmbarqueDTO dto) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		GuiasEmbarqueDTO obtenido = null;
		EmbarqueCatalogoMgr embarqueCatalogoMgr = null;
		try {
			daoFactory = new DAOFactory();
			embarqueCatalogoMgr = new EmbarqueCatalogoMgr();
			obtenido = embarqueCatalogoMgr.enviarGuiasEmbarque(daoFactory, dto);
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
