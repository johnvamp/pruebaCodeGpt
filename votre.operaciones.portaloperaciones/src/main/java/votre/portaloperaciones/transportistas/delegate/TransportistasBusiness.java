package votre.portaloperaciones.transportistas.delegate;

import java.util.ArrayList;

import org.apache.log4j.Logger;


import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.service.ServiceLocator;
import votre.portaloperaciones.transportistas.beans.TransportistaReversarDTO;
import votre.portaloperaciones.transportistas.beans.TransportistasComboDTO;
import votre.portaloperaciones.transportistas.beans.TransportistasDTO;
import votre.portaloperaciones.transportistas.beans.TransportistasSeleccionarDTO;
import votre.portaloperaciones.transportistas.facade.ITransportistasFacade;

public class TransportistasBusiness {
	
	private final String claveFacade = "Transportistas";
	private ITransportistasFacade TransportistasFacade;
	Logger logger = Logger.getLogger(this.getClass());
	
	
	public TransportistasBusiness() throws PersonalsoftException{
		String nombreServicio = Configuracion.getInstance().getServicio(claveFacade).getRuta();
		TransportistasFacade = (ITransportistasFacade) ServiceLocator.getInstance().lookup(nombreServicio);
	}
	
	
	public ArrayList<TransportistasDTO> Transportistas (TransportistasDTO transportistasDTO) throws PersonalsoftException{
	 return TransportistasFacade.transportistas(transportistasDTO);	
	}
	
	public TransportistasDTO TransportistasGuardar (TransportistasDTO transportistasDTO) throws PersonalsoftException{
		 return TransportistasFacade.transportistasGuardar(transportistasDTO);	
		}
	
	public TransportistasComboDTO TransportistasCombo (TransportistasComboDTO transportistasComboDTO) throws PersonalsoftException{
	//public ArrayList<TransportistasComboDTO> TransportistasCombo (TransportistasComboDTO transportistasComboDTO) throws PersonalsoftException{
		 return TransportistasFacade.transportistasCombo(transportistasComboDTO);
		}
	
	public TransportistasSeleccionarDTO TransportistasSeleccionar (TransportistasSeleccionarDTO transportistasSeleccionarDTO)throws PersonalsoftException{
		return TransportistasFacade.transportistasSeleccionar(transportistasSeleccionarDTO);
	}
	
	public ArrayList<TransportistasDTO> guardarEntregados(ArrayList<TransportistasDTO> arrTrans) throws PersonalsoftException{
		return TransportistasFacade.guardarEntregados(arrTrans);
	}
	
	public ArrayList<TransportistasDTO> guardarEntregadosMovil(ArrayList<TransportistasDTO> arrTrans) throws PersonalsoftException{
		return TransportistasFacade.guardarEntregadosMovil(arrTrans);
	}
	
	public TransportistaReversarDTO reversarEstado(TransportistaReversarDTO reversar) throws PersonalsoftException{
	    return TransportistasFacade.reversarEstado(reversar);
	}
}
