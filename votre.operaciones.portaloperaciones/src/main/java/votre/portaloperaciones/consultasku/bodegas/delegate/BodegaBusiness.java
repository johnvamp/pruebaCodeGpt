package votre.portaloperaciones.consultasku.bodegas.delegate;

import java.util.ArrayList;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.service.ServiceLocator;
import votre.portaloperaciones.consultasku.bodegas.beans.BodegaDTO;
import votre.portaloperaciones.consultasku.bodegas.facade.IBodegaFacade;

public class BodegaBusiness {

	private final String claveFacade = "bodegaFacade";
	private IBodegaFacade bodegaFacade;
	
	public BodegaBusiness() throws PersonalsoftException{
		String nombreServicio = Configuracion.getInstance().getServicio(claveFacade).getRuta();
		bodegaFacade = (IBodegaFacade)ServiceLocator.getInstance().lookup(nombreServicio);
	}
	
	public ArrayList<BodegaDTO> consultarBodegas(String codCia) throws PersonalsoftException{
		return bodegaFacade.consultarBodegas(codCia);
	}
}
