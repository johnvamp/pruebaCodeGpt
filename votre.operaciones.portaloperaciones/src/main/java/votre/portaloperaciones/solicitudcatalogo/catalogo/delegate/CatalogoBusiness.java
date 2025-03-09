package votre.portaloperaciones.solicitudcatalogo.catalogo.delegate;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.service.ServiceLocator;
import votre.portaloperaciones.solicitudcatalogo.catalogo.beans.CatalogoDTO;
import votre.portaloperaciones.solicitudcatalogo.catalogo.facade.ICatalogoFacade;

public class CatalogoBusiness {
	
	private final String claveFacade = "catalogoFacade";
	private ICatalogoFacade catalogoFacade;
	
	public CatalogoBusiness() throws PersonalsoftException{
		String nombreServicio = Configuracion.getInstance().getServicio(claveFacade).getRuta();
		catalogoFacade = (ICatalogoFacade) ServiceLocator.getInstance().lookup( nombreServicio );
	}
	
	public CatalogoDTO verCatalogo(CatalogoDTO catalogo) throws PersonalsoftException{
		return catalogoFacade.verCatalogo(catalogo);
	}
}
