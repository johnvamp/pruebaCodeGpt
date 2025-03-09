package votre.portaloperaciones.despachocatalogo.reportes.facade;

import java.sql.SQLException;

import votre.dao.DAOFactory;
import votre.portaloperaciones.despachocatalogo.reportes.beans.InformacionDTO;
import votre.portaloperaciones.despachocatalogo.reportes.beans.ReportesDTO;
import votre.portaloperaciones.despachocatalogo.reportes.manager.ReportesMgr;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class ReportesFacade implements IReportesFacade {

	public ReportesDTO generarExcel(InformacionDTO informacion, ReportesDTO reporte) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		ReportesMgr reportesMgr = null;
		ReportesDTO obtenido = null;		
		try {
			if(Constantes.isLeocomus(reporte.getCodCia())){
				daoFactory = new DAOFactory(Configuracion.getInstance().getParametro("jndiUSA"));
			}
			else{
				daoFactory = new DAOFactory();
			}
			reportesMgr = new ReportesMgr();
			reportesMgr.consultar(daoFactory, informacion);
			obtenido = reportesMgr.generarExcel(daoFactory, reporte);
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

	public InformacionDTO consultar(InformacionDTO informacion) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		ReportesMgr reportesMgr = null;
		InformacionDTO obtenido = null;		
		try {
		  if(Constantes.isLeocomus(informacion.getCodCia())){
				daoFactory = new DAOFactory(Configuracion.getInstance().getParametro("jndiUSA"));
			}
			else{
				daoFactory = new DAOFactory();
			}
			reportesMgr = new ReportesMgr();
			obtenido= reportesMgr.consultar(daoFactory, informacion);
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

	public ReportesDTO generarLabel(ReportesDTO reporte) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		ReportesMgr reportesMgr = null;
		ReportesDTO obtenido = null;		
		try {
      if(Constantes.isLeocomus(reporte.getCodCia())){
				daoFactory = new DAOFactory(Configuracion.getInstance().getParametro("jndiUSA"));
			}
			else{
				daoFactory = new DAOFactory();
			}
			reportesMgr = new ReportesMgr();
			obtenido = reportesMgr.generarExcel(daoFactory, reporte);
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
