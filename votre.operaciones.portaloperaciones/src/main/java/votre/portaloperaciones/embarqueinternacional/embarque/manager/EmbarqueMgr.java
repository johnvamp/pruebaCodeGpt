package votre.portaloperaciones.embarqueinternacional.embarque.manager;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.dao.DAOFactory;
import votre.portaloperaciones.embarqueinternacional.embarque.beans.EmbarqueDTO;

public class EmbarqueMgr {

	public EmbarqueDTO cargarTitulos(DAOFactory factory,String codCia) throws PersonalsoftException{
		return factory.getEmbarque().cargarTitulos(codCia);
	}
	
	public EmbarqueDTO abrirEmbarque(DAOFactory factory, EmbarqueDTO embarqueDTO) throws PersonalsoftException{
		return factory.getEmbarque().abrirEmbarque(embarqueDTO);
	}
	
	public EmbarqueDTO embarcar(DAOFactory factory, EmbarqueDTO embarqueDTO) throws PersonalsoftException{
		return factory.getEmbarque().embarcar(embarqueDTO);
	}
	
	public EmbarqueDTO detenerEmbarque(DAOFactory factory, EmbarqueDTO embarqueDTO) throws PersonalsoftException{
		return factory.getEmbarque().detenerEmbarque(embarqueDTO);
	}
	
	public EmbarqueDTO cerrarEmbarque(DAOFactory factory, EmbarqueDTO embarqueDTO) throws PersonalsoftException{
		return factory.getEmbarque().cerrarEmbarque(embarqueDTO);
	}
	
	public EmbarqueDTO desembarcar(DAOFactory factory, EmbarqueDTO embarqueDTO) throws PersonalsoftException{
		return factory.getEmbarque().desembarcar(embarqueDTO);
	}
}
