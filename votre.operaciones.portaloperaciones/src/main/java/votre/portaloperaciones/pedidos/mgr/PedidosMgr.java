package votre.portaloperaciones.pedidos.mgr;

import java.util.ArrayList;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.dao.DAOFactory;
import votre.portaloperaciones.pedidos.beans.PedidosDTO;

public class PedidosMgr {
	
	public PedidosMgr(){}
	
	public PedidosDTO pedidosLista (DAOFactory factory, PedidosDTO pedidosDTO) throws PersonalsoftException{
		return factory.getPedidos().tipoPedidosCombo(pedidosDTO);
	}
	
	public PedidosDTO guardarCompradora (DAOFactory factory, PedidosDTO pedidosDTO) throws PersonalsoftException{
		return factory.getPedidos().guardarCompradora(pedidosDTO);
	}
	
	public ArrayList<PedidosDTO> listarDetalle (DAOFactory factory, PedidosDTO pedidosDTO)throws PersonalsoftException{
		return factory.getPedidos().listarDetalle(pedidosDTO);
	}
	

	public PedidosDTO tipoCombo (DAOFactory factory, PedidosDTO pedidosDTO)throws PersonalsoftException{
		return factory.getPedidos().tipoCombo(pedidosDTO);
	}
	
	
	

}
