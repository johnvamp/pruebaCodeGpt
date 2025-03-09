package votre.portaloperaciones.solicitudcatalogo.compradora.facade;

import votre.portaloperaciones.solicitudcatalogo.catalogo.beans.CatalogoDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.beans.CatalogoCompradoraDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.beans.CompradoraDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.beans.EnviosDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public interface ICompradoraFacade {
	public CompradoraDTO verListadoCompradoras(CompradoraDTO compradora) throws PersonalsoftException;
	public CompradoraDTO verInformacionCompradora(CatalogoDTO catalogo) throws PersonalsoftException;
	public CatalogoCompradoraDTO consultarCatalogos(String codCia) throws PersonalsoftException;
	public EnviosDTO verResumenEnvios(CompradoraDTO compradora) throws PersonalsoftException;
	public CompradoraDTO guardar(CompradoraDTO compradora) throws PersonalsoftException;
	public CompradoraDTO eliminar(CompradoraDTO compradora) throws PersonalsoftException;
	public CompradoraDTO getDatosCompradoraXGuia(CompradoraDTO compradora) throws PersonalsoftException ;
}

