package votre.portaloperaciones.pedidos.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import votre.portaloperaciones.pedidos.beans.PedidosDTO;
import votre.portaloperaciones.pedidos.delegate.PedidosBusiness;
import votre.portaloperaciones.util.Constantes;
import votre.utils.moduloseguridad.beans.UsuarioDTO;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class GuardarPedidoCmd implements IBaseCmd{


	public void execute(HttpServletRequest req, HttpServletResponse res)throws PersonalsoftException {

		PedidosBusiness pedidosBusiness;
		pedidosBusiness = new PedidosBusiness();
		
		String pCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
 		String guardarCodCompradora =  req.getParameter("guardarCodCompradora") != null ? req.getParameter("guardarCodCompradora") : "";
		String guardarcodZona =  req.getParameter("guardarcodZona") != null ? req.getParameter("guardarcodZona") : "";
		String guardarTipoCombo = req.getParameter("tipoCombo") != null ? req.getParameter("tipoCombo") : "";		
		String buscarTipoPedido =  req.getParameter("buscarTipoPedido") != null ? req.getParameter("buscarTipoPedido") : ""; 		
		StringBuffer guardarCodReferencia =  new StringBuffer(req.getParameter("guardarCodReferencia") != null ? req.getParameter("guardarCodReferencia") : "");
		StringBuffer guardarCodTalla =  new StringBuffer(req.getParameter("guardarCodTalla") != null ? req.getParameter("guardarCodTalla") : "");
		StringBuffer guardarCodColor =  new StringBuffer(req.getParameter("guardarCodColor") != null ? req.getParameter("guardarCodColor") : "");
 		String guardarcodCantidad =  req.getParameter("guardarcodCantidad") != null ? req.getParameter("guardarcodCantidad") : "";
 		UsuarioDTO usuarioDTO = req.getSession().getAttribute( Constantes.USUARIO_SESION ) != null ? ((UsuarioDTO)req.getSession().getAttribute( Constantes.USUARIO_SESION )) : null;
 		String pUsu = req.getSession().getAttribute( usuarioDTO.getUsuario()) != null ? ((String)req.getSession().getAttribute( usuarioDTO.getUsuario() )).trim() : "";
  	  
 		String Cedd="";
 		
 		if(guardarCodCompradora.equals(""))
 		{
 		 if(guardarcodZona.equals(""))
 		 {
 			if (guardarTipoCombo.equals("")==false) 
 			{
 		    	Cedd= guardarTipoCombo;	
 			}
 		 }
 		 else
 		 {
 			Cedd = guardarcodZona;
 		 }
 		}
 		else
 		{
 			Cedd = guardarCodCompradora;
 		}		
 		
 		if(Cedd.equals("")== false)
 		{ 		
 		if(guardarCodReferencia.toString().equals("")==false & guardarCodTalla.toString().equals("")==false & guardarCodColor.toString().equals("")==false & guardarcodCantidad.equals(""))
 		{ 		
 		int relleno;
 		if(guardarCodReferencia.length()>=7)
 		{
	 	 guardarCodReferencia = new StringBuffer(guardarCodReferencia.substring(0, 7));
	 	}
	 	else
	 	{
		 	relleno = 7 - guardarCodReferencia.length();
		 	for (int i = 0; i < relleno; i++) 
		 		{
		 		guardarCodReferencia.append(" ");
		 		}
	 	}		 	

	 	if(guardarCodColor.length()>=3)
	 	{
	 		guardarCodColor = new StringBuffer(guardarCodColor.substring(0, 3));
	 	}
	 	else
	 	{
		 	relleno = 3 - guardarCodColor.length();
		 	for (int i = 0; i < relleno; i++) 
		 	{
		 		guardarCodColor.insert(0, " ");
		 	}
	 	}


	 	if(guardarCodTalla.length()>=3)
	 	{
	 		guardarCodTalla = new StringBuffer(guardarCodTalla.substring(0, 3));
	 	}
	 	else
	 	{
		 	relleno = 3 - guardarCodTalla.length();
		 	for (int i = 0; i < relleno; i++) 
		 	{
		 		guardarCodTalla.insert(0, " ");
		 	}
	 	}
	 	
	 	StringBuffer sku = new StringBuffer(guardarCodReferencia);
	 	sku.append(" ");
	 	sku.append(guardarCodColor);
	 	sku.append(" ");
	 	sku.append(guardarCodTalla);	 	  
	   
	 	String skuf = sku.toString();
	 	
 		String tipo = buscarTipoPedido;
 		tipo = tipo.substring(0,2);
 	
 		int dato = buscarTipoPedido.length(); 		
 		String Destipo = buscarTipoPedido; 		
 		Destipo = Destipo.substring(3,dato); 
 		
 		PedidosDTO pedidosDTO = new PedidosDTO();
		pedidosDTO.setDsCia(pCia);		
		pedidosDTO.setDsTipop(tipo);
		pedidosDTO.setDsSku(skuf);
		pedidosDTO.setDsUnip(guardarcodCantidad);		
		pedidosDTO.setDsCedd(Cedd);		
		pedidosDTO.setDsDest(Destipo);
		pedidosDTO.setDsUsu(pUsu);
		pedidosDTO = pedidosBusiness.GuardarCompradora(pedidosDTO);
		req.setAttribute("TipoPedido",pedidosDTO.getDsTipoPedidoCombo());
		req.setAttribute("tipoPedidoSelect",buscarTipoPedido);		
		req.setAttribute("status",pedidosDTO.getDsStatus());
		req.setAttribute("pstatus",pedidosDTO.getDsPdestatus());
		
 		}
 		else
 		{
 			req.setAttribute("status","1");
 			req.setAttribute("pstatus","Error al Insertar los Datos - Faltan Datos");
 			
 		}
 		}	
 		else
 		{
 			req.setAttribute("status","1");
 			req.setAttribute("pstatus","Error al Insertar los Datos - Faltan Datos");
 			
 		}		
	}
}
