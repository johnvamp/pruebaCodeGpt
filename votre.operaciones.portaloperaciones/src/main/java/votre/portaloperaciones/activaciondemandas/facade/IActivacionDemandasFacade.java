package votre.portaloperaciones.activaciondemandas.facade;

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
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public interface IActivacionDemandasFacade {

	public ArrayList<OpcionDTO> consultarOpciones(OpcionDTO opcionDTO) throws PersonalsoftException;
	public CmpDemandaDTO validarCompradora(CmpDemandaDTO cmpDemandaDTO) throws PersonalsoftException;
	public RefeDemandaDTO validarReferencia(RefeDemandaDTO refeDemandaDTO) throws PersonalsoftException;
	public NovedadActDemandaDTO grabarNovedad(NovedadActDemandaDTO dto) throws PersonalsoftException;
	public ArrayList<CasoDTO> consultarCasos(CasoDTO dto) throws PersonalsoftException;
	public CasoDTO grabarAprobacion(CasoDTO dto) throws PersonalsoftException;
	public AsignarDTO asignarTransportadores(AsignarDTO dto) throws PersonalsoftException;
	public ReferenciasDTO consultarReferencias(ReferenciasDTO dto) throws PersonalsoftException;
	public AsignarDTO generarGuias(AsignarDTO dto) throws PersonalsoftException;
	public ReferenciasDTO cambiarACorreoExtra(ReferenciasDTO dto) throws PersonalsoftException;
	public EnviosDTO eliminarEnvios(EnviosDTO dto) throws PersonalsoftException;
	public GuiasMasterDTO consultarGuiasMaster(GuiasMasterDTO dto) throws PersonalsoftException;
	public ArrayList<GuiasMasterDTO> imprimirGuiasMaster(ArrayList<GuiasMasterDTO> arrGuias) throws PersonalsoftException;
}
