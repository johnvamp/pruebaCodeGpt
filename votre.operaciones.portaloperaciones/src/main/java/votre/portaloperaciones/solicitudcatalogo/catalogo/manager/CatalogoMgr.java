package votre.portaloperaciones.solicitudcatalogo.catalogo.manager;

import votre.dao.DAOFactory;
import votre.portaloperaciones.solicitudcatalogo.catalogo.beans.CatalogoDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class CatalogoMgr {
	
	public CatalogoDTO verCatalogo(DAOFactory factory,CatalogoDTO catalogo) throws PersonalsoftException{
		return factory.getCatalogo().verCatalogo(catalogo);
	}

}
