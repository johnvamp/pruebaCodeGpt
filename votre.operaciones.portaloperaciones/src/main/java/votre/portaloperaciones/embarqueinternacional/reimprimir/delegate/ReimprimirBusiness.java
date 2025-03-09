package votre.portaloperaciones.embarqueinternacional.reimprimir.delegate;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.service.ServiceLocator;
import votre.portaloperaciones.embarqueinternacional.reimprimir.beans.ReimprimirDTO;
import votre.portaloperaciones.embarqueinternacional.reimprimir.facade.IReimprimirFacade;

public class ReimprimirBusiness {

	private final String claveFacade = "reimprimirFacade";
	private IReimprimirFacade reimprimirFacade;
	
	public ReimprimirBusiness() throws PersonalsoftException{
		String nombreServicio = Configuracion.getInstance().getServicio(claveFacade).getRuta();
		reimprimirFacade = (IReimprimirFacade)ServiceLocator.getInstance().lookup(nombreServicio);
	}
	
	public ReimprimirDTO reimprimirEmbarque(ReimprimirDTO reimprimirDTO) throws PersonalsoftException{
		return reimprimirFacade.reimprimirEmbarque(reimprimirDTO);
	}
}
