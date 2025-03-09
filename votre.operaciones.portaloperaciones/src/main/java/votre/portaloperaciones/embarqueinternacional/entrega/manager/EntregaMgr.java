package votre.portaloperaciones.embarqueinternacional.entrega.manager;

import java.util.ArrayList;

import votre.dao.DAOFactory;
import votre.portaloperaciones.embarqueinternacional.entrega.beans.EntregaDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class EntregaMgr {

	public EntregaDTO verEntrega(DAOFactory factory, EntregaDTO entregaDTO) throws PersonalsoftException{
		return factory.getEntrega().verEntrega(entregaDTO);
	}
	public EntregaDTO verEntregaParcial(DAOFactory factory, EntregaDTO entregaDTO) throws PersonalsoftException{
		return factory.getEntrega().verEntregaParcial(entregaDTO);
	}
	public EntregaDTO confirmarEntrega(DAOFactory factory, EntregaDTO entregaDTO) throws PersonalsoftException{
		return factory.getEntrega().confirmarEntrega(entregaDTO);
	}
	public EntregaDTO procesarExcel(DAOFactory factory, ArrayList<EntregaDTO> entregas) throws PersonalsoftException{
		return factory.getEntrega().procesarExcel(entregas);
	}
}
