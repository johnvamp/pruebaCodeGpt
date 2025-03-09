package votre.portaloperaciones.servicios.transportistas.facade;

import java.sql.SQLException;

import votre.dao.DAOFactory;
import votre.portaloperaciones.servicios.transportistas.beans.TransportistaDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class TransportistaFacade implements ITransportistaFacade{
	
	public TransportistaDTO consultarDetalleArchivoTransportistas(TransportistaDTO transportistaDTO) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		
		try {
			daoFactory = new DAOFactory();
			transportistaDTO = daoFactory.getTransportista().consultarEstructuraArchivo(transportistaDTO);
			transportistaDTO = daoFactory.getTransportista().consultarDetalleArchivoTransportistas(transportistaDTO);
			
		} catch (PersonalsoftException e) {
			throw (PersonalsoftException) e;
		} catch (Exception e) {
			throw new PersonalsoftException(e);
		} finally {
			try {
				daoFactory.cerrarConexion();
			} catch (SQLException exception) {
				throw new PersonalsoftException(exception);
			}
		}
		return transportistaDTO;
	}
	
	public TransportistaDTO consultarEstructuraArchivo(TransportistaDTO transportistaDTO) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		
		try {
			daoFactory = new DAOFactory();
			transportistaDTO = daoFactory.getTransportista().consultarEstructuraArchivo(transportistaDTO);
			
		} catch (PersonalsoftException e) {
			throw (PersonalsoftException) e;
		} catch (Exception e) {
			throw new PersonalsoftException(e);
		} finally {
			try {
				daoFactory.cerrarConexion();
			} catch (SQLException exception) {
				throw new PersonalsoftException(exception);
			}
		}
		return transportistaDTO;
	}

}
