package votre.portaloperaciones.pedidos.delegate;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.service.ServiceLocator;

import votre.portaloperaciones.pedidos.beans.PedidosDTO;
import votre.portaloperaciones.pedidos.facade.IPedidosFacade;

public class PedidosBusiness {
	
	private final String claveFacade ="Pedidos";
	private IPedidosFacade PedidosFacade;
	Logger logger = Logger.getLogger(this.getClass());
	
	public PedidosBusiness() throws PersonalsoftException{
		String nombreServicio = Configuracion.getInstance().getServicio(claveFacade).getRuta();
		PedidosFacade = (IPedidosFacade) ServiceLocator.getInstance().lookup(nombreServicio); 
	}
	
	public PedidosDTO TipoPedidosLista (PedidosDTO pedidosDTO) throws PersonalsoftException{
		return PedidosFacade.listaPedidos(pedidosDTO);
	}
	
	public PedidosDTO GuardarCompradora (PedidosDTO pedidosDTO) throws PersonalsoftException{
		return PedidosFacade.guardarCompradora(pedidosDTO);
	}
	
	public ArrayList<PedidosDTO> listarDetalle (PedidosDTO pedidosDTO)throws PersonalsoftException{
		return PedidosFacade.listarDetalle(pedidosDTO);
	}
	
	public PedidosDTO TipoCombo (PedidosDTO pedidosDTO) throws PersonalsoftException{
		return PedidosFacade.tipoCombo(pedidosDTO);
	}

}
