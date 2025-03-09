package votre.portaloperaciones.solicitudcatalogo.compradora.delegate;

import votre.portaloperaciones.solicitudcatalogo.catalogo.beans.CatalogoDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.beans.CatalogoCompradoraDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.beans.CompradoraDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.beans.EnviosDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.facade.ICompradoraFacade;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.service.ServiceLocator;

public class CompradoraBusiness {
	private final String claveFacade = "compradoraFacade";
	private ICompradoraFacade compradoraFacade;
	
	public CompradoraBusiness() throws PersonalsoftException{
		String nombreServicio = Configuracion.getInstance().getServicio(claveFacade).getRuta();
		compradoraFacade = (ICompradoraFacade) ServiceLocator.getInstance().lookup( nombreServicio );
	}
	
	public CompradoraDTO verListadoCompradoras(CompradoraDTO compradora) throws PersonalsoftException{
		return compradoraFacade.verListadoCompradoras(compradora);
	}
	
	public CompradoraDTO verInformacionCompradora(CatalogoDTO catalogo) throws PersonalsoftException{
		return compradoraFacade.verInformacionCompradora(catalogo);
	}
	
	public CatalogoCompradoraDTO consultarCatalogos(String codCia) throws PersonalsoftException{
		return compradoraFacade.consultarCatalogos(codCia);
	}
	
	public EnviosDTO verResumenEnvios(CompradoraDTO compradora) throws PersonalsoftException{
		return compradoraFacade.verResumenEnvios(compradora);
	}
	
	public CompradoraDTO guardar(CompradoraDTO compradora) throws PersonalsoftException{
		return compradoraFacade.guardar(compradora);
	}
	
	public CompradoraDTO eliminar(CompradoraDTO compradora) throws PersonalsoftException{
		return compradoraFacade.eliminar(compradora);
	}
	
	public CompradoraDTO getDatosCompradoraXGuia(CompradoraDTO compradora) throws PersonalsoftException {
		return compradoraFacade.getDatosCompradoraXGuia(compradora);
	}
}
