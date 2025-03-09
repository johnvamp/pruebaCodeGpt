package votre.portaloperaciones.flujowms.delegate;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.service.ServiceLocator;

import votre.portaloperaciones.flujowms.beans.AlertasDTO;
import votre.portaloperaciones.flujowms.beans.FlujoWmsDTO;
import votre.portaloperaciones.flujowms.beans.TituloFlujoWmsDTO;
import votre.portaloperaciones.flujowms.facade.IFlujoWmsFacade;

public class FlujoWmsBusiness {

	private final String claveFacede = "flujoWms";
	private IFlujoWmsFacade flujoWmsFacade;
	Logger logger = Logger.getLogger(this.getClass());
	
	public FlujoWmsBusiness() throws PersonalsoftException{
		String nombreServicio = Configuracion.getInstance().getServicio(claveFacede).getRuta();
		flujoWmsFacade = (IFlujoWmsFacade) ServiceLocator.getInstance().lookup(nombreServicio);
	}
	
	public ArrayList<FlujoWmsDTO> consultaFlujoWms (FlujoWmsDTO flujoWmsDTO) throws PersonalsoftException{
		return flujoWmsFacade.consultaFlujoWms(flujoWmsDTO);
	}
	
	public ArrayList<TituloFlujoWmsDTO> conusltaTituloFlujoWms(TituloFlujoWmsDTO tituloFlujoWmsDTO) throws PersonalsoftException{
		return flujoWmsFacade.consultaTituloFlujoWms(tituloFlujoWmsDTO);
	}
	
	public AlertasDTO consultarEstados(String codCia) throws PersonalsoftException{
		return flujoWmsFacade.consultarEstados(codCia);
	}
	
	public AlertasDTO consultarDetalleEstado(AlertasDTO alertasDTO) throws PersonalsoftException{
		return flujoWmsFacade.consultarDetalleEstado(alertasDTO);	
	}
}
