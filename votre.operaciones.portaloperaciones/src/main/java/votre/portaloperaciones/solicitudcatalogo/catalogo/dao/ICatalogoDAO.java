package votre.portaloperaciones.solicitudcatalogo.catalogo.dao;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.portaloperaciones.solicitudcatalogo.catalogo.beans.CatalogoDTO;

public interface ICatalogoDAO {
	
	public CatalogoDTO verCatalogo(CatalogoDTO catalogo) throws PersonalsoftException;

}
