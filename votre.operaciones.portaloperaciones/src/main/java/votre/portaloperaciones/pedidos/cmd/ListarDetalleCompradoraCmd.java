package votre.portaloperaciones.pedidos.cmd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import votre.portaloperaciones.pedidos.beans.PedidosDTO;
import votre.portaloperaciones.pedidos.delegate.PedidosBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class ListarDetalleCompradoraCmd implements IBaseCmd{


	public void execute(HttpServletRequest req, HttpServletResponse res)throws PersonalsoftException {
		PedidosBusiness pedidosBusiness;
		pedidosBusiness = new PedidosBusiness();
		
		String pCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
 		String buscarTipoPedido =  req.getParameter("buscarTipoPedido") != null ? req.getParameter("buscarTipoPedido") : "";
 		String numeroPedido =  req.getParameter("numeroPedido") != null ? req.getParameter("numeroPedido") : "";
  		
 		String tipo="";
 		
 		if(buscarTipoPedido.length() > 0)
 		{
 		tipo= buscarTipoPedido;
 		tipo = tipo.substring(0,2);
 		}
 			
 		if(buscarTipoPedido.length() > 0 || numeroPedido.length() > 0 )	
 		{ 	 		
 		PedidosDTO pedidosDTO = new PedidosDTO();
 		pedidosDTO.setDsCia(pCia);
		pedidosDTO.setDsTipoPed(tipo);
		pedidosDTO.setDsNumPed(numeroPedido);
		
		ArrayList<PedidosDTO> listarDetalle = new ArrayList<PedidosDTO>();		
		listarDetalle = pedidosBusiness.listarDetalle(pedidosDTO);
		if(listarDetalle.size()>0)
			{
			req.setAttribute("listarDetalleCompradora",listarDetalle);
			}
 		}
		
	}

}
