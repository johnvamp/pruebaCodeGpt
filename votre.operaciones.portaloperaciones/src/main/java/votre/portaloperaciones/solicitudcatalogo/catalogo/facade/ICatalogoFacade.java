package votre.portaloperaciones.solicitudcatalogo.catalogo.facade;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.portaloperaciones.solicitudcatalogo.catalogo.beans.CatalogoDTO;

public interface ICatalogoFacade {
	
	public CatalogoDTO verCatalogo(CatalogoDTO catalogo) throws PersonalsoftException;
}
