package votre.portaloperaciones.flujowms.mgr;

import java.util.ArrayList;

import votre.dao.DAOFactory;
import votre.portaloperaciones.flujowms.beans.AlertasDTO;
import votre.portaloperaciones.flujowms.beans.FlujoWmsDTO;
import votre.portaloperaciones.flujowms.beans.TituloFlujoWmsDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class FlujoWmsMgr {
	public ArrayList<FlujoWmsDTO> flujoWms (DAOFactory factory,FlujoWmsDTO flujoWmsDTO) throws PersonalsoftException{
		return factory.getFlujoWms().consultaFlujoWms(flujoWmsDTO);
	}
	
	public ArrayList<TituloFlujoWmsDTO> tituloFlujoWms (DAOFactory factory,TituloFlujoWmsDTO tituloFlujoWmsDTO) throws PersonalsoftException{
		return factory.getTituloFlujoWms().consultaTituloFlujoWms(tituloFlujoWmsDTO);
	}
	
	public AlertasDTO consultarEstados(DAOFactory factory, String codCia) throws PersonalsoftException{
		return factory.getFlujoWms().consultarEstados(codCia);
	}
	
	public AlertasDTO consultarDetalleEstado(DAOFactory factory, AlertasDTO alertasDTO) throws PersonalsoftException{
		return factory.getFlujoWms().consultarDetalleEstado(alertasDTO);
	}
}
