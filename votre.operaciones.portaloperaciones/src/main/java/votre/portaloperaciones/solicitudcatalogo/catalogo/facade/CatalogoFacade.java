package votre.portaloperaciones.solicitudcatalogo.catalogo.facade;

import java.sql.SQLException;

import votre.dao.DAOFactory;
import votre.portaloperaciones.solicitudcatalogo.catalogo.beans.CatalogoDTO;
import votre.portaloperaciones.solicitudcatalogo.catalogo.manager.CatalogoMgr;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class CatalogoFacade implements ICatalogoFacade {

	public CatalogoDTO verCatalogo(CatalogoDTO catalogo) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		CatalogoMgr catalogoMgr = null;
		CatalogoDTO obtenido = null;
		
		try {
		  if (Constantes.isLeocomus(catalogo.getCodCia())) {
				daoFactory = new DAOFactory(Configuracion.getInstance().getParametro("jndiUSA"));
			}
			else{
				daoFactory = new DAOFactory();
			}
			catalogoMgr = new CatalogoMgr();
			obtenido = catalogoMgr.verCatalogo(daoFactory,catalogo);
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
