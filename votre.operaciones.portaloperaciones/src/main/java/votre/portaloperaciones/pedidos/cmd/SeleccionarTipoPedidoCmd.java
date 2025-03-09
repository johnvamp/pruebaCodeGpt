package votre.portaloperaciones.pedidos.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import votre.portaloperaciones.pedidos.beans.PedidosDTO;
import votre.portaloperaciones.pedidos.delegate.PedidosBusiness;
import votre.portaloperaciones.util.Constantes;
import votre.utils.moduloseguridad.beans.UsuarioDTO;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class SeleccionarTipoPedidoCmd implements IBaseCmd{

	public void execute(HttpServletRequest req, HttpServletResponse res)throws PersonalsoftException {
		
		PedidosBusiness pedidosBusiness;
		pedidosBusiness = new PedidosBusiness();
		
		UsuarioDTO usuarioDTO = req.getSession().getAttribute( Constantes.USUARIO_SESION ) != null ? ((UsuarioDTO)req.getSession().getAttribute( Constantes.USUARIO_SESION )) : null;
		String pCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
 		String pUsu = req.getSession().getAttribute(usuarioDTO.getUsuario()) != null ? ((String)req.getSession().getAttribute(usuarioDTO.getUsuario())).trim() : "";
		String buscarTipoPedido =  req.getParameter("buscarTipoPedido") != null ? req.getParameter("buscarTipoPedido") : "";
	
		PedidosDTO pedidosDTO = new PedidosDTO();
		PedidosDTO pedidosComboDTO = new PedidosDTO();
		pedidosDTO.setDsCia(pCia);
		pedidosDTO.setDsUsu(pUsu);
		pedidosDTO = pedidosBusiness.TipoPedidosLista(pedidosDTO);
		req.setAttribute("TipoPedido",pedidosDTO.getDsTipoPedidoCombo());
		req.setAttribute("tipoPedidoSelect",buscarTipoPedido);	
			
		pedidosComboDTO.setDsCia(pCia);		
		String tipo="";
 			
		if(buscarTipoPedido.length() > 0 )
 		{
 			tipo= buscarTipoPedido;
 	 		tipo = tipo.substring(0,2);
 		} 		
 		
 		if(tipo.equals("MJ"))
 		{
 			pedidosComboDTO.setDsTipoLis("R");	
 		}
 		
 		if(tipo.equals("CM"))
 		{
 			pedidosComboDTO.setDsTipoLis("D");	
 		}
		
 		if(tipo.equals("MJ") || tipo.equals("CM") )
 		{
 			pedidosDTO = pedidosBusiness.TipoCombo(pedidosComboDTO);
 			req.setAttribute("TipoCombo",pedidosDTO.getDsTipoCombo());	
 		}		
	}	
}
