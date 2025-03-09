package votre.portaloperaciones.despachocatalogo.embarquecatalogo.facade;

import votre.portaloperaciones.despachocatalogo.embarquecatalogo.beans.GuiasEmbarqueDTO;
import votre.portaloperaciones.despachocatalogo.embarquecatalogo.beans.TransportadorasDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public interface IEmbarqueCatalogoFacade {

	public TransportadorasDTO consultarTransportadoras(TransportadorasDTO dto) throws PersonalsoftException;
	public GuiasEmbarqueDTO enviarGuiasEmbarque(GuiasEmbarqueDTO dto) throws PersonalsoftException;
}
