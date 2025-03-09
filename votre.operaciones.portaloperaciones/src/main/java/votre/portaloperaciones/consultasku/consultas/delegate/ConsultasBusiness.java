package votre.portaloperaciones.consultasku.consultas.delegate;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.service.ServiceLocator;
import votre.portaloperaciones.consultasku.consultas.beans.ConsultasDTO;
import votre.portaloperaciones.consultasku.consultas.facade.IConsultasFacade;

public class ConsultasBusiness {

	private final String claveFacade = "consultasFacade";
	private IConsultasFacade consultasFacade;
	
	public ConsultasBusiness() throws PersonalsoftException{
		String nombreServicio = Configuracion.getInstance().getServicio(claveFacade).getRuta();
		consultasFacade = (IConsultasFacade)ServiceLocator.getInstance().lookup(nombreServicio);
	}
	
	public ConsultasDTO consultarReferencias(ConsultasDTO consultasDTO) throws PersonalsoftException{
		return consultasFacade.consultasReferencias(consultasDTO);
	}
	
	public ConsultasDTO consultarPrecios(ConsultasDTO consultasDTO) throws PersonalsoftException{
		return consultasFacade.consultarPrecios(consultasDTO);
	}
	
	public ConsultasDTO consultarAuditoria(ConsultasDTO consultasDTO) throws PersonalsoftException{
		return consultasFacade.consultarAuditoria(consultasDTO);
	}
	
	public ConsultasDTO consultarDescripcionSKU(ConsultasDTO consultasDTO) throws PersonalsoftException{
		return consultasFacade.consultarDescripcionSKU(consultasDTO);
	}
}
