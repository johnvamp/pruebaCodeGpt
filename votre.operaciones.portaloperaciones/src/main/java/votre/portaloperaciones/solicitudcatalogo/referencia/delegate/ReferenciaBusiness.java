package votre.portaloperaciones.solicitudcatalogo.referencia.delegate;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.service.ServiceLocator;
import votre.portaloperaciones.solicitudcatalogo.referencia.beans.AuditoriaDTO;
import votre.portaloperaciones.solicitudcatalogo.referencia.beans.ReferenciaDTO;
import votre.portaloperaciones.solicitudcatalogo.referencia.facade.IReferenciaFacade;

public class ReferenciaBusiness {
	
	private final String claveFacade = "referenciaFacade";
	private IReferenciaFacade referenciaFacade;
	
	public ReferenciaBusiness() throws PersonalsoftException{
		String nombreServicio = Configuracion.getInstance().getServicio(claveFacade).getRuta();
		referenciaFacade = (IReferenciaFacade) ServiceLocator.getInstance().lookup( nombreServicio );
	}

	public ReferenciaDTO verInformacionReferencia(ReferenciaDTO referencia) throws PersonalsoftException{
		return referenciaFacade.verInformacionReferencia(referencia);
	}
	
	public AuditoriaDTO verAuditoriaReferencia(ReferenciaDTO referencia) throws PersonalsoftException{
		return referenciaFacade.verAuditoriaReferencia(referencia);
	}
}
