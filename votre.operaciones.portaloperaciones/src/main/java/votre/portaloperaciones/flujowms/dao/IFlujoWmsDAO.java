package votre.portaloperaciones.flujowms.dao;

import java.util.ArrayList;

import co.com.personalsoft.base.excepcion.PersonalsoftException;

import votre.portaloperaciones.flujowms.beans.AlertasDTO;
import votre.portaloperaciones.flujowms.beans.FlujoWmsDTO;
import votre.portaloperaciones.flujowms.beans.TituloFlujoWmsDTO;

public interface IFlujoWmsDAO {
	public  ArrayList<FlujoWmsDTO> consultaFlujoWms (FlujoWmsDTO flujoWmsDTO) throws PersonalsoftException;
	public  ArrayList<TituloFlujoWmsDTO> consultaTituloFlujoWms (TituloFlujoWmsDTO tituloFlujoWmsDTO) throws PersonalsoftException;
	public AlertasDTO consultarEstados(String codCia) throws PersonalsoftException;
	public AlertasDTO consultarDetalleEstado(AlertasDTO alertasDTO) throws PersonalsoftException; 
	
}
