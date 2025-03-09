package votre.portaloperaciones.embarqueinternacional.embarque.delegate;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.service.ServiceLocator;
import votre.portaloperaciones.embarqueinternacional.embarque.beans.EmbarqueDTO;
import votre.portaloperaciones.embarqueinternacional.embarque.facade.IEmbarqueFacade;

public class EmbarqueBusinnes {

	private final String claveFacade = "embarqueFacade";
	private IEmbarqueFacade embarqueFacade;
	
	public EmbarqueBusinnes() throws PersonalsoftException{
		String nombreServicio = Configuracion.getInstance().getServicio(claveFacade).getRuta();
		embarqueFacade = (IEmbarqueFacade) ServiceLocator.getInstance().lookup(nombreServicio);
	}
	
	public EmbarqueDTO cargarTitulos(String codCia) throws PersonalsoftException{
		return embarqueFacade.cargarTitulos(codCia);
	}
	
	public EmbarqueDTO abrirEmbarque(EmbarqueDTO embarqueDTO) throws PersonalsoftException{
		return embarqueFacade.abrirEmbarque(embarqueDTO);
	}
	
	public EmbarqueDTO embarcar(EmbarqueDTO embarqueDTO) throws PersonalsoftException{
		return embarqueFacade.embarcar(embarqueDTO);
	}
	
	public EmbarqueDTO detenerEmbarque(EmbarqueDTO embarqueDTO) throws PersonalsoftException{
		return embarqueFacade.detenerEmbarque(embarqueDTO);
	}
	
	public EmbarqueDTO cerrarEmbarque(EmbarqueDTO embarqueDTO) throws PersonalsoftException{
		return embarqueFacade.cerrarEmbarque(embarqueDTO);
	}
	
	public EmbarqueDTO desembarque(EmbarqueDTO embarqueDTO) throws PersonalsoftException{
		return embarqueFacade.desembarque(embarqueDTO);
	}
}
