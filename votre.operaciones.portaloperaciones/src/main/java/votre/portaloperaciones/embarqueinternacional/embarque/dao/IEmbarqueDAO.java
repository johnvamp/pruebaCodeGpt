package votre.portaloperaciones.embarqueinternacional.embarque.dao;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.portaloperaciones.embarqueinternacional.embarque.beans.EmbarqueDTO;

public interface IEmbarqueDAO {

	public EmbarqueDTO cargarTitulos(String codCia) throws PersonalsoftException;
	public EmbarqueDTO abrirEmbarque(EmbarqueDTO embarqueDTO) throws PersonalsoftException;
	public EmbarqueDTO embarcar(EmbarqueDTO embarqueDTO) throws PersonalsoftException;
	public EmbarqueDTO detenerEmbarque(EmbarqueDTO embarqueDTO) throws PersonalsoftException;
	public EmbarqueDTO cerrarEmbarque(EmbarqueDTO embarqueDTO) throws PersonalsoftException;
	public EmbarqueDTO desembarcar(EmbarqueDTO embarqueDTO) throws PersonalsoftException;
}
