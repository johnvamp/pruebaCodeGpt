package votre.portaloperaciones.despachocatalogo.labels.facade;

import java.sql.SQLException;
import java.util.ArrayList;

import votre.dao.DAOFactory;
import votre.portaloperaciones.despachocatalogo.labels.beans.DespachoDTO;
import votre.portaloperaciones.despachocatalogo.labels.beans.LabelsDTO;
import votre.portaloperaciones.despachocatalogo.labels.beans.ZonasDTO;
import votre.portaloperaciones.despachocatalogo.labels.manager.LabelsMgr;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class LabelsFacade implements ILabelsFacade {

	public LabelsDTO cargarLabels(LabelsDTO labels) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		LabelsMgr labelsMgr = null;
		LabelsDTO obtenido = null;
		try {
		  if(Constantes.isLeocomus(labels.getCodCia())) {
				daoFactory = new DAOFactory(Configuracion.getInstance().getParametro("jndiUSA"));
			}
			else{
				daoFactory = new DAOFactory();
			}

			labelsMgr = new LabelsMgr();
			obtenido = labelsMgr.cargarLabels(daoFactory, labels);
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

	public DespachoDTO consultar(LabelsDTO labels) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		LabelsMgr labelsMgr = null;
		DespachoDTO obtenido = null;
		try {
			daoFactory = new DAOFactory();
			labelsMgr = new LabelsMgr();
			obtenido = labelsMgr.consultar(daoFactory, labels);
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

	public ArrayList<ZonasDTO> cargarZonas(LabelsDTO labels) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		LabelsMgr labelsMgr = null;
		ArrayList<ZonasDTO> obtenido = null;
		try {
		    if(Constantes.isLeocomus(labels.getCodCia())) {
		        daoFactory = new DAOFactory(Configuracion.getInstance().getParametro("jndiUSA"));
		    }
		    else{
		        daoFactory = new DAOFactory();
		    }
		
			labelsMgr = new LabelsMgr();
			obtenido = labelsMgr.cargarZonas(daoFactory, labels);
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
