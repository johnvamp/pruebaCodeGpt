package votre.portaloperaciones.solicitudcatalogo.referencia.facade;

import java.sql.SQLException;

import votre.dao.DAOFactory;
import votre.portaloperaciones.solicitudcatalogo.referencia.beans.AuditoriaDTO;
import votre.portaloperaciones.solicitudcatalogo.referencia.beans.ReferenciaDTO;
import votre.portaloperaciones.solicitudcatalogo.referencia.manager.ReferenciaMgr;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class ReferenciaFacade implements IReferenciaFacade {

	public ReferenciaDTO verInformacionReferencia(ReferenciaDTO referencia) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		ReferenciaMgr referenciaMgr = null;
		ReferenciaDTO obtenido = null;
		try {
			if(Constantes.CODCIA_USA.equals(referencia.getCodCia())){
				daoFactory = new DAOFactory(Configuracion.getInstance().getParametro("jndiUSA"));
			}
			else{
				daoFactory = new DAOFactory();
			}
			referenciaMgr = new ReferenciaMgr();
			obtenido = referenciaMgr.verInformacionReferencia(daoFactory, referencia);
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

	public AuditoriaDTO verAuditoriaReferencia(ReferenciaDTO referencia) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		ReferenciaMgr referenciaMgr = null;
		AuditoriaDTO obtenido = null;
		try {
			if(Constantes.CODCIA_USA.equals(referencia.getCodCia())){
				daoFactory = new DAOFactory(Configuracion.getInstance().getParametro("jndiUSA"));
			}
			else{
				daoFactory = new DAOFactory();
			}
			referenciaMgr = new ReferenciaMgr();
			obtenido = referenciaMgr.verAuditoriaCatalogo(daoFactory, referencia);
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
