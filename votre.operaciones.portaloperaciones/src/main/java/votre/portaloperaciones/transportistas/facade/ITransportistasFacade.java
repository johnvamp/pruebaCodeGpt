package votre.portaloperaciones.transportistas.facade;

import java.util.ArrayList;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.portaloperaciones.transportistas.beans.TransportistaReversarDTO;
import votre.portaloperaciones.transportistas.beans.TransportistasComboDTO;
import votre.portaloperaciones.transportistas.beans.TransportistasDTO;
import votre.portaloperaciones.transportistas.beans.TransportistasSeleccionarDTO;

public interface ITransportistasFacade {

	public ArrayList<TransportistasDTO> transportistas (TransportistasDTO transportistasDTO) throws PersonalsoftException; 
	public TransportistasDTO transportistasGuardar (TransportistasDTO transportistasDTO) throws PersonalsoftException; 

	
	//public ArrayList<TransportistasComboDTO> transportistasCombo (TransportistasComboDTO transportistasComboDTO) throws PersonalsoftException; 

	public TransportistasComboDTO transportistasCombo (TransportistasComboDTO transportistasComboDTO) throws PersonalsoftException;
	
	public TransportistasSeleccionarDTO transportistasSeleccionar (TransportistasSeleccionarDTO transportistasSeleccionarDTO) throws PersonalsoftException;
	public ArrayList<TransportistasDTO> guardarEntregados(ArrayList<TransportistasDTO> arrTrans) throws PersonalsoftException;
	
	public ArrayList<TransportistasDTO> guardarEntregadosMovil(ArrayList<TransportistasDTO> arrTrans) throws PersonalsoftException ;
	
	public TransportistaReversarDTO reversarEstado(TransportistaReversarDTO reversar) throws PersonalsoftException;
}
