package votre.portaloperaciones.consultasku.consultas.facade;

import java.sql.SQLException;

import votre.dao.DAOFactory;
import votre.portaloperaciones.consultasku.consultas.beans.ConsultasDTO;
import votre.portaloperaciones.consultasku.consultas.manager.ConsultasMgr;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class ConsultasFacade implements IConsultasFacade {

	public ConsultasDTO consultasReferencias(ConsultasDTO consultasDTO) throws PersonalsoftException {
		DAOFactory factory = null;
		ConsultasMgr consultasMgr = null;
		ConsultasDTO obtenido = null;
		
		try{
			factory = new DAOFactory();
			consultasMgr = new ConsultasMgr();
			obtenido = consultasMgr.consultarReferencias(factory, consultasDTO);
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

	public ConsultasDTO consultarPrecios(ConsultasDTO consultasDTO) throws PersonalsoftException {
		DAOFactory factory = null;
		ConsultasMgr consultasMgr = null;
		ConsultasDTO obtenido = null;
		
		try{
			factory = new DAOFactory();
			consultasMgr = new ConsultasMgr();
			obtenido = consultasMgr.consultarPrecios(factory, consultasDTO);
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

	public ConsultasDTO consultarAuditoria(ConsultasDTO consultasDTO) throws PersonalsoftException {
		DAOFactory factory = null;
		ConsultasMgr consultasMgr = null;
		ConsultasDTO obtenido = null;
		
		try{
			factory = new DAOFactory();
			consultasMgr = new ConsultasMgr();
			obtenido = consultasMgr.consultarAuditoria(factory, consultasDTO);
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

	public ConsultasDTO consultarDescripcionSKU(ConsultasDTO consultasDTO) throws PersonalsoftException {
		DAOFactory factory = null;
		ConsultasMgr consultasMgr = null;
		ConsultasDTO obtenido = null;
		
		try{
			factory = new DAOFactory();
			consultasMgr = new ConsultasMgr();
			obtenido = consultasMgr.consultarDescripcionSKU(factory, consultasDTO);
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
