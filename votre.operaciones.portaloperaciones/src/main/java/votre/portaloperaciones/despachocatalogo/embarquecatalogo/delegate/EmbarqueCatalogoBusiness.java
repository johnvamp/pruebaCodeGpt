package votre.portaloperaciones.despachocatalogo.embarquecatalogo.delegate;

import votre.portaloperaciones.despachocatalogo.embarquecatalogo.beans.GuiasEmbarqueDTO;
import votre.portaloperaciones.despachocatalogo.embarquecatalogo.beans.TransportadorasDTO;
import votre.portaloperaciones.despachocatalogo.embarquecatalogo.facade.IEmbarqueCatalogoFacade;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.service.ServiceLocator;

public class EmbarqueCatalogoBusiness {

	private final String claveFacade = "embarqueCatalogoFacade";
	private IEmbarqueCatalogoFacade embarqueFacade;
	
	public EmbarqueCatalogoBusiness() throws PersonalsoftException{
		String nombreServicio = Configuracion.getInstance().getServicio(claveFacade).getRuta();
		embarqueFacade = (IEmbarqueCatalogoFacade) ServiceLocator.getInstance().lookup( nombreServicio );
	}
	
	public TransportadorasDTO consultarTransportadoras(TransportadorasDTO dto) throws PersonalsoftException{
		return embarqueFacade.consultarTransportadoras(dto);
	}
	
	public GuiasEmbarqueDTO enviarGuiasEmbarque(GuiasEmbarqueDTO dto) throws PersonalsoftException{
		return embarqueFacade.enviarGuiasEmbarque(dto);
	}
}
