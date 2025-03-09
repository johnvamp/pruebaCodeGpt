package votre.portaloperaciones.suscripcioncatalogo.facade;

import votre.portaloperaciones.suscripcioncatalogo.beans.SuscripcionCatalogoDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public interface ISuscripcionesFacade {
	
	public SuscripcionCatalogoDTO suscribir(SuscripcionCatalogoDTO dto) throws PersonalsoftException;

}
