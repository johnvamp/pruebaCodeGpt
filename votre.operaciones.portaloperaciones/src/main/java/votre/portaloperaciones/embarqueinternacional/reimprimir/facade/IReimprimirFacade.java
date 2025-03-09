package votre.portaloperaciones.embarqueinternacional.reimprimir.facade;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.portaloperaciones.embarqueinternacional.reimprimir.beans.ReimprimirDTO;

public interface IReimprimirFacade {

	public ReimprimirDTO reimprimirEmbarque(ReimprimirDTO reimprimirDTO) throws PersonalsoftException;
}
