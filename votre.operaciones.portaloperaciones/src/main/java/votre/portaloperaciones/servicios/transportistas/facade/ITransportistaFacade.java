package votre.portaloperaciones.servicios.transportistas.facade;

import votre.portaloperaciones.servicios.transportistas.beans.TransportistaDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public interface ITransportistaFacade {
	
	public TransportistaDTO consultarEstructuraArchivo(TransportistaDTO transportistaDTO) throws PersonalsoftException ;
	
	public TransportistaDTO consultarDetalleArchivoTransportistas(TransportistaDTO transportistaDTO) throws PersonalsoftException ;

}
