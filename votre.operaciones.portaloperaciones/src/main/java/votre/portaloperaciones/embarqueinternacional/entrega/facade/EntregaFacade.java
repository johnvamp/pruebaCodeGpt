package votre.portaloperaciones.embarqueinternacional.entrega.facade;

import java.sql.SQLException;
import java.util.ArrayList;

import votre.dao.DAOFactory;
import votre.portaloperaciones.embarqueinternacional.entrega.beans.EntregaDTO;
import votre.portaloperaciones.embarqueinternacional.entrega.manager.EntregaMgr;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class EntregaFacade implements IEntregaFacade {

	public EntregaDTO verEntrega(EntregaDTO entregaDTO) throws PersonalsoftException {
		DAOFactory factory = null;
		EntregaMgr entregaMgr = null;
		EntregaDTO obtenido = null;
		
		try{
			factory = new DAOFactory();
			entregaMgr = new EntregaMgr();
			obtenido = entregaMgr.verEntrega(factory, entregaDTO);
			
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

	public EntregaDTO verEntregaParcial(EntregaDTO entregaDTO) throws PersonalsoftException {
		DAOFactory factory = null;
		EntregaMgr entregaMgr = null;
		EntregaDTO obtenido = null;
		
		try{
			factory = new DAOFactory();
			entregaMgr = new EntregaMgr();
			obtenido = entregaMgr.verEntregaParcial(factory, entregaDTO);
			
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

	public EntregaDTO confirmarEntrega(EntregaDTO entregaDTO) throws PersonalsoftException {
		DAOFactory factory = null;
		EntregaMgr entregaMgr = null;
		EntregaDTO obtenido = null;
		
		try{
			factory = new DAOFactory();
			entregaMgr = new EntregaMgr();
			obtenido = entregaMgr.confirmarEntrega(factory, entregaDTO);
			
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

	public EntregaDTO procesarExcel(ArrayList<EntregaDTO> entregas) throws PersonalsoftException {
		DAOFactory factory = null;
		EntregaMgr entregaMgr = null;
		EntregaDTO obtenido = null;
		
		try{
			factory = new DAOFactory();
			entregaMgr = new EntregaMgr();
			obtenido = entregaMgr.procesarExcel(factory, entregas);
			
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
