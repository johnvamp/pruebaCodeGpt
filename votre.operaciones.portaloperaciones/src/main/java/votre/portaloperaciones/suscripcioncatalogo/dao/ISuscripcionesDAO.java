package votre.portaloperaciones.suscripcioncatalogo.dao;

import votre.portaloperaciones.suscripcioncatalogo.beans.SuscripcionCatalogoDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public interface ISuscripcionesDAO {
	
	public SuscripcionCatalogoDTO suscribir(SuscripcionCatalogoDTO dto) throws PersonalsoftException;

}
