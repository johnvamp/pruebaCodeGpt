package votre.portaloperaciones.suscripcioncatalogo.delegate;

import votre.portaloperaciones.suscripcioncatalogo.beans.SuscripcionCatalogoDTO;
import votre.portaloperaciones.suscripcioncatalogo.facade.ISuscripcionesFacade;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.service.ServiceLocator;

public class SuscripcionesBusiness {
	
	private final String claveFacade = "suscripcionesFacade";
	private ISuscripcionesFacade suscripcionesFacade;
	
	public SuscripcionesBusiness() throws PersonalsoftException{
		String nombreServicio = Configuracion.getInstance().getServicio(claveFacade).getRuta();
		suscripcionesFacade = (ISuscripcionesFacade) ServiceLocator.getInstance().lookup( nombreServicio );
	}
	
	public SuscripcionCatalogoDTO suscribir(SuscripcionCatalogoDTO dto) throws PersonalsoftException{
		return suscripcionesFacade.suscribir(dto);
	}
}
