package votre.portaloperaciones.activaciondemandas.delegate;

import java.util.ArrayList;

import votre.portaloperaciones.activaciondemandas.beans.AsignarDTO;
import votre.portaloperaciones.activaciondemandas.beans.CasoDTO;
import votre.portaloperaciones.activaciondemandas.beans.CmpDemandaDTO;
import votre.portaloperaciones.activaciondemandas.beans.EnviosDTO;
import votre.portaloperaciones.activaciondemandas.beans.GuiasMasterDTO;
import votre.portaloperaciones.activaciondemandas.beans.NovedadActDemandaDTO;
import votre.portaloperaciones.activaciondemandas.beans.OpcionDTO;
import votre.portaloperaciones.activaciondemandas.beans.RefeDemandaDTO;
import votre.portaloperaciones.activaciondemandas.beans.ReferenciasDTO;
import votre.portaloperaciones.activaciondemandas.facade.IActivacionDemandasFacade;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.service.ServiceLocator;

public class ActivacionDemandasBusiness {
	
	private final String claveFacade = "activacionDemandasFacade";
	private IActivacionDemandasFacade actDemandasFacade;
	
	public ActivacionDemandasBusiness() throws PersonalsoftException{
		String nombreServicio = Configuracion.getInstance().getServicio(claveFacade).getRuta();
		actDemandasFacade = (IActivacionDemandasFacade) ServiceLocator.getInstance().lookup( nombreServicio );
	}
	
	public ArrayList<OpcionDTO> consultarOpciones(OpcionDTO opcionDTO) throws PersonalsoftException{
		return actDemandasFacade.consultarOpciones(opcionDTO);
	}
	
	public CmpDemandaDTO validarCompradora(CmpDemandaDTO cmpDemandaDTO) throws PersonalsoftException{
		return actDemandasFacade.validarCompradora(cmpDemandaDTO);
	}
	
	public RefeDemandaDTO validarReferencia(RefeDemandaDTO refeDemandaDTO) throws PersonalsoftException{
		return actDemandasFacade.validarReferencia(refeDemandaDTO);
	}
	
	public NovedadActDemandaDTO grabarNovedad(NovedadActDemandaDTO dto) throws PersonalsoftException{
		return actDemandasFacade.grabarNovedad(dto);
	}
	
	public ArrayList<CasoDTO> consultarCasos(CasoDTO dto) throws PersonalsoftException{
		return actDemandasFacade.consultarCasos(dto);
	}

	public CasoDTO grabarAprobacion(CasoDTO dto) throws PersonalsoftException{
		return actDemandasFacade.grabarAprobacion(dto);
	}
	
	public AsignarDTO asignarTransportadores(AsignarDTO dto) throws PersonalsoftException{
		return actDemandasFacade.asignarTransportadores(dto);
	}
	
	public ReferenciasDTO consultarReferencias(ReferenciasDTO dto) throws PersonalsoftException{
		return actDemandasFacade.consultarReferencias(dto);
	}
	
	public AsignarDTO generarGuias(AsignarDTO dto) throws PersonalsoftException{
		return actDemandasFacade.generarGuias(dto);
	}
	
	public ReferenciasDTO cambiarACorreoExtra(ReferenciasDTO dto) throws PersonalsoftException{
		return actDemandasFacade.cambiarACorreoExtra(dto);
	}
	
	public EnviosDTO eliminarEnvios(EnviosDTO dto) throws PersonalsoftException{
		return actDemandasFacade.eliminarEnvios(dto);
	}
	
	public GuiasMasterDTO consultarGuiasMaster(GuiasMasterDTO dto) throws PersonalsoftException{
		return actDemandasFacade.consultarGuiasMaster(dto);
	}
	
	public ArrayList<GuiasMasterDTO> imprimirGuiasMaster(ArrayList<GuiasMasterDTO> arrGuias) throws PersonalsoftException{
		return actDemandasFacade.imprimirGuiasMaster(arrGuias);
	}
}
