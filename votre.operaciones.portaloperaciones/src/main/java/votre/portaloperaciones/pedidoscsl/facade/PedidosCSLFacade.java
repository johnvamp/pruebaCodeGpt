package votre.portaloperaciones.pedidoscsl.facade;

import java.sql.SQLException;

import votre.dao.DAOFactory;
import votre.portaloperaciones.pedidoscsl.beans.PedidosCSLDTO;
import votre.portaloperaciones.pedidoscsl.manager.PedidosCSLMgr;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class PedidosCSLFacade implements IPedidosCSLFacade {

	public PedidosCSLDTO listarOpciones(PedidosCSLDTO dto) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		PedidosCSLDTO pedidosCSLDTO = null;
		try{
			daoFactory = new DAOFactory();
			pedidosCSLDTO = daoFactory.getPedidosCSL().listarOpciones(dto);
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
		return pedidosCSLDTO;
	}

	public PedidosCSLDTO validarSolictud(PedidosCSLDTO dto) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		PedidosCSLDTO pedidosCSLDTO = null;
		try{
			daoFactory = new DAOFactory();
			pedidosCSLDTO = daoFactory.getPedidosCSL().validarSolicitud(dto);
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
		return pedidosCSLDTO;
	}

	public PedidosCSLDTO grabarItem(PedidosCSLDTO dto) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		PedidosCSLDTO pedidosCSLDTO = null;
		PedidosCSLMgr pedidosCSLMgr = null;
		try{
			daoFactory = new DAOFactory();
			pedidosCSLMgr = new PedidosCSLMgr();
			pedidosCSLDTO = pedidosCSLMgr.grabarItem(daoFactory, dto);
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
		return pedidosCSLDTO;
	}

	public PedidosCSLDTO consultarSolicitud(PedidosCSLDTO dto) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		PedidosCSLDTO pedidosCSLDTO = null;
		try{
			daoFactory = new DAOFactory();
			pedidosCSLDTO = daoFactory.getPedidosCSL().consultarSolicitud(dto);
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
		return pedidosCSLDTO;
	}

	public PedidosCSLDTO consultarDetalleSolicitud(PedidosCSLDTO dto) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		PedidosCSLDTO pedidosCSLDTO = null;
		try{
			daoFactory = new DAOFactory();
			pedidosCSLDTO = daoFactory.getPedidosCSL().consultarDetalleSolicitud(dto);
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
		return pedidosCSLDTO;
	}

	public PedidosCSLDTO consultarAuditoria(PedidosCSLDTO dto) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		PedidosCSLDTO pedidosCSLDTO = null;
		try{
			daoFactory = new DAOFactory();
			pedidosCSLDTO = daoFactory.getPedidosCSL().consultarAuditoria(dto);
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
		return pedidosCSLDTO;
	}
}