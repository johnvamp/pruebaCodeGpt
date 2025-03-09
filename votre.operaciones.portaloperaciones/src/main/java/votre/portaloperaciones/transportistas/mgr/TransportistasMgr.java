package votre.portaloperaciones.transportistas.mgr;

import java.util.ArrayList;

import votre.dao.DAOFactory;
import votre.portaloperaciones.transportistas.beans.TransportistaReversarDTO;
import votre.portaloperaciones.transportistas.beans.TransportistasComboDTO;
import votre.portaloperaciones.transportistas.beans.TransportistasDTO;
import votre.portaloperaciones.transportistas.beans.TransportistasSeleccionarDTO;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class TransportistasMgr {
	
	public TransportistasMgr(){}
	
	public ArrayList<TransportistasDTO> transportistas (DAOFactory factory, TransportistasDTO transportistasDTO)throws PersonalsoftException{
		return factory.getTransportistas().consultaTransportitas(transportistasDTO);
	}
	
	
	public TransportistasDTO transportistasGuardar (DAOFactory factory, TransportistasDTO transportistasDTO)throws PersonalsoftException{
		return factory.getTransportistas().consultaTransportitasGuardar(transportistasDTO);
	}
	
	public TransportistasComboDTO transportistasCombo (DAOFactory factory, TransportistasComboDTO transportistasComboDTO)throws PersonalsoftException{
		//return factory.getTransportistas().consultaTransportitasCombo(transportistasComboDTO);		
		return factory.getTransportistas().consultaTransportitasCombo(transportistasComboDTO);
	}
	
	
	public  TransportistasSeleccionarDTO transportistasSeleccionar (DAOFactory factory, TransportistasSeleccionarDTO transportistasSeleccionarDTO)throws PersonalsoftException{
		return factory.getTransportistas().consultaTransportitasListas(transportistasSeleccionarDTO);
	}
	
	public ArrayList<TransportistasDTO> guardarEntregados(DAOFactory factory, ArrayList<TransportistasDTO> arrTrans) throws PersonalsoftException{
		TransportistasDTO obtenido = null;
		ArrayList<TransportistasDTO> arrObtenido = new ArrayList<TransportistasDTO>();
		for (TransportistasDTO transportistasDTO : arrTrans) {
			obtenido = factory.getTransportistas().consultaTransportitasGuardar(transportistasDTO);
			if(!obtenido.getError().equals(Constantes.EXITO_SP)){
				arrObtenido.add(obtenido);
			}
		}
		return arrObtenido;
	}
	
	public ArrayList<TransportistasDTO> guardarEntregadosMovil(DAOFactory factory, ArrayList<TransportistasDTO> arrTrans) throws PersonalsoftException{
		TransportistasDTO obtenido = null;
		ArrayList<TransportistasDTO> arrObtenido = new ArrayList<TransportistasDTO>();
		for (TransportistasDTO transportistasDTO : arrTrans) {
			//Si por alguna razón no viene la fecha de escaner, entonces se invoca el SP normal para que ponga la fecha de transmision
			if(transportistasDTO.getFecEnt() != null && !"".equals(transportistasDTO.getFecEnt())){
				obtenido = factory.getTransportistas().consultaTransportitasGuardarMovil(transportistasDTO);
			}else{
				obtenido = factory.getTransportistas().consultaTransportitasGuardar(transportistasDTO);
			}
			if(!obtenido.getError().equals(Constantes.EXITO_SP)){
				arrObtenido.add(obtenido);
			}
		}
		return arrObtenido;
	}
	
	public TransportistaReversarDTO reversarEstado(DAOFactory factory, TransportistaReversarDTO reversar) throws PersonalsoftException{
	    return factory.getTransportistas().reversarEstado(reversar);
	}
}
