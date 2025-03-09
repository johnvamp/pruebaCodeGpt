package votre.portaloperaciones.embarqueinternacional.embarque.facade;

import java.sql.SQLException;

import votre.dao.DAOFactory;
import votre.portaloperaciones.embarqueinternacional.embarque.beans.EmbarqueDTO;
import votre.portaloperaciones.embarqueinternacional.embarque.manager.EmbarqueMgr;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class EmbarqueFacade implements IEmbarqueFacade {

	public EmbarqueDTO cargarTitulos(String codCia) throws PersonalsoftException {
		DAOFactory factory = null;
		EmbarqueMgr embarqueMgr = null;
		EmbarqueDTO obtenido = null;
		
		try {
			factory = new DAOFactory();
			embarqueMgr = new EmbarqueMgr();
			obtenido = embarqueMgr.cargarTitulos(factory, codCia);
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

	public EmbarqueDTO abrirEmbarque(EmbarqueDTO embarqueDTO) throws PersonalsoftException {
		DAOFactory factory = null;
		EmbarqueMgr embarqueMgr = null;
		EmbarqueDTO obtenido = null;
		
		try{
			factory = new DAOFactory();
			embarqueMgr = new EmbarqueMgr();
			obtenido = embarqueMgr.abrirEmbarque(factory, embarqueDTO);
			
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

	public EmbarqueDTO embarcar(EmbarqueDTO embarqueDTO) throws PersonalsoftException {
		DAOFactory factory = null;
		EmbarqueMgr embarqueMgr = null;
		EmbarqueDTO obtenido = null;
		
		try {
			factory = new DAOFactory();
			embarqueMgr = new EmbarqueMgr();
			obtenido = embarqueMgr.embarcar(factory, embarqueDTO);
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

	public EmbarqueDTO detenerEmbarque(EmbarqueDTO embarqueDTO) throws PersonalsoftException {
		DAOFactory factory = null;
		EmbarqueMgr embarqueMgr = null;
		EmbarqueDTO obtenido = null;
		
		try {
			factory = new DAOFactory();
			embarqueMgr = new EmbarqueMgr();
			obtenido = embarqueMgr.detenerEmbarque(factory, embarqueDTO);
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

	public EmbarqueDTO cerrarEmbarque(EmbarqueDTO embarqueDTO) throws PersonalsoftException {
		DAOFactory factory = null;
		EmbarqueMgr embarqueMgr = null;
		EmbarqueDTO obtenido = null;
		
		try {
			factory = new DAOFactory();
			embarqueMgr = new EmbarqueMgr();
			obtenido = embarqueMgr.cerrarEmbarque(factory, embarqueDTO);
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

	public EmbarqueDTO desembarque(EmbarqueDTO embarqueDTO) throws PersonalsoftException {
		DAOFactory factory = null;
		EmbarqueMgr embarqueMgr = null;
		EmbarqueDTO obtenido = null;
		
		try{
			factory = new DAOFactory();
			embarqueMgr = new EmbarqueMgr();
			obtenido = embarqueMgr.desembarcar(factory, embarqueDTO);
			
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
