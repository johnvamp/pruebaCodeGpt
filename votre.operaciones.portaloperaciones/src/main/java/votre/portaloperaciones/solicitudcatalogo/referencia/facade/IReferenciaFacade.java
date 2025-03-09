package votre.portaloperaciones.solicitudcatalogo.referencia.facade;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.portaloperaciones.solicitudcatalogo.referencia.beans.AuditoriaDTO;
import votre.portaloperaciones.solicitudcatalogo.referencia.beans.ReferenciaDTO;

public interface IReferenciaFacade {
	
	public ReferenciaDTO verInformacionReferencia(ReferenciaDTO referencia) throws PersonalsoftException;
	public AuditoriaDTO verAuditoriaReferencia(ReferenciaDTO referencia) throws PersonalsoftException;

}
