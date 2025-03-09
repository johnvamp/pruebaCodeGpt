package votre.portaloperaciones.embarqueinternacional.embarque.facade;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.portaloperaciones.embarqueinternacional.embarque.beans.EmbarqueDTO;

public interface IEmbarqueFacade {
	
	public EmbarqueDTO cargarTitulos(String codCia) throws PersonalsoftException;
	public EmbarqueDTO abrirEmbarque(EmbarqueDTO embarqueDTO) throws PersonalsoftException;
	public EmbarqueDTO embarcar(EmbarqueDTO embarqueDTO) throws PersonalsoftException;
	public EmbarqueDTO detenerEmbarque(EmbarqueDTO embarqueDTO) throws PersonalsoftException;
	public EmbarqueDTO cerrarEmbarque(EmbarqueDTO embarqueDTO) throws PersonalsoftException;
	public EmbarqueDTO desembarque(EmbarqueDTO embarqueDTO) throws PersonalsoftException;

}
