package votre.portaloperaciones.embarqueinternacional.reimprimir.dao;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.portaloperaciones.embarqueinternacional.reimprimir.beans.ReimprimirDTO;

public interface IReimprimirDAO {

	public ReimprimirDTO reimprimirEmbarque(ReimprimirDTO reimprimirDTO) throws PersonalsoftException;
}
