package votre.portaloperaciones.flujowms.facade;

import java.util.ArrayList;

import co.com.personalsoft.base.excepcion.PersonalsoftException;

import votre.portaloperaciones.flujowms.beans.AlertasDTO;
import votre.portaloperaciones.flujowms.beans.FlujoWmsDTO;
import votre.portaloperaciones.flujowms.beans.TituloFlujoWmsDTO;

public interface IFlujoWmsFacade {

	public ArrayList<FlujoWmsDTO> consultaFlujoWms(FlujoWmsDTO flujoWmsDTO) throws PersonalsoftException;
	public ArrayList<TituloFlujoWmsDTO> consultaTituloFlujoWms(TituloFlujoWmsDTO tituloFlujoWmsDTO) throws PersonalsoftException;
	public AlertasDTO consultarEstados(String codCia) throws PersonalsoftException;
	public AlertasDTO consultarDetalleEstado(AlertasDTO alertasDTO) throws PersonalsoftException;
}
