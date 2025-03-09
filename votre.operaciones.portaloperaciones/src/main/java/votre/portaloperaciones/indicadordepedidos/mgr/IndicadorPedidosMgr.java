package votre.portaloperaciones.indicadordepedidos.mgr;

import java.util.ArrayList;

import co.com.personalsoft.base.excepcion.PersonalsoftException;

import votre.dao.DAOFactory;
import votre.portaloperaciones.indicadordepedidos.beans.IndicadorPedidosDTO;
import votre.portaloperaciones.indicadordepedidos.beans.PedidosPorRangoDTO;

public class IndicadorPedidosMgr {

	public IndicadorPedidosMgr(){}

	public ArrayList<IndicadorPedidosDTO> indicadorPedidosGraf1 (DAOFactory factory, IndicadorPedidosDTO indicadorPedidosDTO) throws PersonalsoftException{
		return factory.getIndicadorPedidosGraf1().indicadorPedidosGraf1(indicadorPedidosDTO);
	}
	
	public ArrayList<PedidosPorRangoDTO> pedidosPorRango (DAOFactory factory, PedidosPorRangoDTO pedidosPorRangoDTO) throws PersonalsoftException{
		return factory.getIndicadorPedidosGraf1().pedidosPorRango(pedidosPorRangoDTO);
	}
}
