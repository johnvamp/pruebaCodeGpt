package votre.portaloperaciones.solicitudcatalogo.compradora.manager;


import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.dao.DAOFactory;
import votre.portaloperaciones.solicitudcatalogo.catalogo.beans.CatalogoDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.beans.CatalogoCompradoraDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.beans.CompradoraDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.beans.EnviosDTO;;

public class CompradoraMgr {
	
	public CompradoraDTO verListadoCompradoras(DAOFactory factory,CompradoraDTO compradora) throws PersonalsoftException{
		return factory.getCompradora().verListadoCompradoras(compradora);
	}
	
	public CompradoraDTO verInformacionCompradora(DAOFactory factory,CatalogoDTO catalogo) throws PersonalsoftException{
		return factory.getCompradora().verInformacionCompradora(catalogo);
	}
	
	public CatalogoCompradoraDTO consultarCatalogos(DAOFactory factory, String codCia) throws PersonalsoftException{
		return factory.getCompradora().consultarCatalogos(codCia);
	}
	
	public EnviosDTO verResumenEnvios(DAOFactory factory, CompradoraDTO compradora) throws PersonalsoftException{
		return factory.getCompradora().verResumenEnvios(compradora);
	}
	
	public CompradoraDTO guardar(DAOFactory factory, CompradoraDTO compradora) throws PersonalsoftException{
		return factory.getCompradora().guardar(compradora);
	}
	
	public CompradoraDTO eliminar(DAOFactory factory, CompradoraDTO compradora) throws PersonalsoftException{
		return factory.getCompradora().eliminar(compradora);
	}
	
	public CompradoraDTO getDatosCompradoraXGuia(DAOFactory factory, CompradoraDTO compradora) throws PersonalsoftException {
		return factory.getCompradora().getDatosCompradoraXGuia(compradora);
	}

}
