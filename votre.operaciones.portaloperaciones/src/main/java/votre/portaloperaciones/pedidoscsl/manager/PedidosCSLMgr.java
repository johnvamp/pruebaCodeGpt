package votre.portaloperaciones.pedidoscsl.manager;

import org.apache.log4j.Logger;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.dao.DAOFactory;
import votre.portaloperaciones.pedidoscsl.beans.PedidosCSLDTO;

public class PedidosCSLMgr {
	private Logger logger = Logger.getLogger(this.getClass());

	public PedidosCSLDTO grabarItem(DAOFactory daoFactory, PedidosCSLDTO dto) throws PersonalsoftException{
		PedidosCSLDTO obtenido = null;
		for (PedidosCSLDTO pedido : dto.getArrPedidos()) {
			try{
				obtenido = daoFactory.getPedidosCSL().grabarItem(pedido);
			}
			catch(Exception e){
				logger.error(new PersonalsoftException(e).getTraza());
			}
		}
		return obtenido;
	}
}
