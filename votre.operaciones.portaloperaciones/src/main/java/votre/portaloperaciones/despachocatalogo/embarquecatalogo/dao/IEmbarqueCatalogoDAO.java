package votre.portaloperaciones.despachocatalogo.embarquecatalogo.dao;

import votre.portaloperaciones.despachocatalogo.embarquecatalogo.beans.GuiasEmbarqueDTO;
import votre.portaloperaciones.despachocatalogo.embarquecatalogo.beans.TransportadorasDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public interface IEmbarqueCatalogoDAO {

	public TransportadorasDTO consultarTransportadoras(TransportadorasDTO dto) throws PersonalsoftException;
	public GuiasEmbarqueDTO enviarGuiasEmbarque(GuiasEmbarqueDTO dto) throws PersonalsoftException;
	public GuiasEmbarqueDTO finalizarEmbarqueGuias(String codCia) throws PersonalsoftException;
}
