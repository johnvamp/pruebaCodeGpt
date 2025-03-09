package votre.portaloperaciones.reprocesos.facade;

import java.sql.SQLException;
import java.util.ArrayList;

import votre.dao.DAOFactory;
import votre.portaloperaciones.reprocesos.beans.SolicitudDTO;
import votre.portaloperaciones.reprocesos.manager.ReprocesosMgr;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class ReprocesosFacade implements IReprocesosFacade {

	public SolicitudDTO crearNuevaSolicitud(SolicitudDTO dto) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		ReprocesosMgr reprocesosMgr = null;
		SolicitudDTO obtenido = null;
		try{
			daoFactory = new DAOFactory();
			reprocesosMgr = new ReprocesosMgr();
			obtenido = reprocesosMgr.crearNuevaSolicitud(daoFactory, dto);
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

	public SolicitudDTO agregarReferencia(SolicitudDTO dto) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		ReprocesosMgr reprocesosMgr = null;
		SolicitudDTO obtenido = null;
		try{
			daoFactory = new DAOFactory();
			reprocesosMgr = new ReprocesosMgr();
			obtenido = reprocesosMgr.agregarReferencia(daoFactory, dto);
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

	public ArrayList<SolicitudDTO> consultarSolicitudes(SolicitudDTO dto) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		ReprocesosMgr reprocesosMgr = null;
		ArrayList<SolicitudDTO> arrSolicitudes = null;
		try{
			daoFactory = new DAOFactory();
			reprocesosMgr = new ReprocesosMgr();
			arrSolicitudes = reprocesosMgr.consultarSolicitudes(daoFactory, dto);
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
		return arrSolicitudes;
	}

	public SolicitudDTO aprobarSolicitud(SolicitudDTO dto) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		ReprocesosMgr reprocesosMgr = null;
		SolicitudDTO obtenido = null;
		try{
			daoFactory = new DAOFactory();
			reprocesosMgr = new ReprocesosMgr();
			obtenido = reprocesosMgr.aprobarSolicitud(daoFactory, dto);
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

	public SolicitudDTO procesarReferencia(SolicitudDTO dto) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		ReprocesosMgr reprocesosMgr = null;
		SolicitudDTO obtenido = null;
		try{
			daoFactory = new DAOFactory();
			reprocesosMgr = new ReprocesosMgr();
			obtenido = reprocesosMgr.procesarReferecia(daoFactory, dto);
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
