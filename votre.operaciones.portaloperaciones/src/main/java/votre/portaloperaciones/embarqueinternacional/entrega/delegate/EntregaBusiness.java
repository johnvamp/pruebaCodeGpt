package votre.portaloperaciones.embarqueinternacional.entrega.delegate;

import java.util.ArrayList;

import votre.portaloperaciones.embarqueinternacional.entrega.beans.EntregaDTO;
import votre.portaloperaciones.embarqueinternacional.entrega.facade.IEntregaFacade;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.service.ServiceLocator;

public class EntregaBusiness {
	
	private final String claveFacade = "entregaFacade";
	private IEntregaFacade entregaFacade;
	
	public EntregaBusiness() throws PersonalsoftException{
		String nombreServicio = Configuracion.getInstance().getServicio(claveFacade).getRuta();
		entregaFacade = (IEntregaFacade)ServiceLocator.getInstance().lookup(nombreServicio);
	}

	public EntregaDTO verEntrega(EntregaDTO entregaDTO) throws PersonalsoftException{
		return entregaFacade.verEntrega(entregaDTO);
	}
	
	public EntregaDTO verEntregaParcial(EntregaDTO entregaDTO) throws PersonalsoftException{
		return entregaFacade.verEntregaParcial(entregaDTO);
	}
	
	public EntregaDTO confirmarEntrega(EntregaDTO entregaDTO) throws PersonalsoftException{
		return entregaFacade.confirmarEntrega(entregaDTO);
	}
	
	public EntregaDTO procesarExcel(ArrayList<EntregaDTO> entregas) throws PersonalsoftException{
		return entregaFacade.procesarExcel(entregas);
	}
}
