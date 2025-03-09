package votre.portaloperaciones.embarqueinternacional.reimprimir.manager;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.dao.DAOFactory;
import votre.portaloperaciones.embarqueinternacional.reimprimir.beans.ReimprimirDTO;

public class ReimprimirMgr {

	public ReimprimirDTO reimprimirEmbarque(DAOFactory factory, ReimprimirDTO reimprimirDTO) throws PersonalsoftException{
		return factory.getReimprimir().reimprimirEmbarque(reimprimirDTO);
	}
}
