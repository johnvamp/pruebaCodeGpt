package votre.portaloperaciones.pedidoscsl.cmd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.pedidoscsl.beans.PedidosCSLDTO;
import votre.portaloperaciones.pedidoscsl.facade.PedidosCSLFacade;
import votre.portaloperaciones.util.Constantes;
import votre.utils.moduloseguridad.beans.UsuarioDTO;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class GrabarPedidosCmd implements IBaseCmd {
	private Logger logger = Logger.getLogger(this.getClass());
	PersonalsoftException ps = null;
	private String accionSiguiente;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		PedidosCSLFacade pedidosCSLFacade = null;
		PedidosCSLDTO dto = null;
		try{
			String mensaje = "";
			String siguienteRuta = "";
		
			pedidosCSLFacade = new PedidosCSLFacade();
			dto = dtoAssembler(req);
			
			if(accionSiguiente.equals(Constantes.ACCION_PEDIDO_INDIVIDUAL)){
				siguienteRuta = "pedidoscsl.verPedidoIndividual.do";
			}
			else if(accionSiguiente.equals(Constantes.ACCION_PEDIDO_EXCEL)){
				siguienteRuta = "pedidoscsl.verImportarExcel.do";
			}
			
			dto = pedidosCSLFacade.grabarItem(dto);
			if(dto != null){
				if(dto.getSpSts().equals(Constantes.EXITO_SP)){
					mensaje = "Solicitud grabada con el número " + dto.getSpDes();
				}
				else{
					mensaje = dto.getSpDes();
				}
			}
			if(mensaje != null && mensaje != ""){
				req.setAttribute("mensaje", mensaje);
			}
			Configuracion.getInstance().getContext().getRequestDispatcher(siguienteRuta).forward(req, res);
		}
		catch (Exception e) {
			ps = new PersonalsoftException(e);
			logger.error(ps.getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
			throw ps;
		}
	}

	private PedidosCSLDTO dtoAssembler(HttpServletRequest req){
		ArrayList<PedidosCSLDTO> arrPedidos = new ArrayList<PedidosCSLDTO>();
		PedidosCSLDTO pedidosCSLDTO = new PedidosCSLDTO();
		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";;
		String tipoPedido = req.getParameter("tipoPedido") != null ? req.getParameter("tipoPedido").trim() : "";
		String destinatario = req.getParameter("destinatario") != null ? req.getParameter("destinatario").trim() : "";
		String estrategia = req.getParameter("desEstrategia") != null ? req.getParameter("desEstrategia").trim() : "";
		UsuarioDTO usuarioDTO = req.getSession().getAttribute( Constantes.USUARIO_SESION ) != null ? ((UsuarioDTO)req.getSession().getAttribute( Constantes.USUARIO_SESION )) : null;
		String usuario = usuarioDTO.getUsuario();
		int nroRegistros = Integer.parseInt(req.getParameter("nroRegistros") != null ? req.getParameter("nroRegistros").trim() : "0");
		accionSiguiente = req.getParameter("accionSiguiente") != null ? req.getParameter("accionSiguiente").trim() : ""; 
		
		for (int i = 1; i <= nroRegistros; i++) {
			PedidosCSLDTO pedidos = new PedidosCSLDTO();
			String variable = req.getParameter("variable"+i) != null ? req.getParameter("variable"+i).trim() : "";
			String sku = req.getParameter("sku"+i) != null ? req.getParameter("sku"+i).trim() : "";
			String cantidad = req.getParameter("cantidad"+i) != null ? req.getParameter("cantidad"+i).trim() : "";
			String centroCostos = req.getParameter("centroCostos"+i) != null ? req.getParameter("centroCostos"+i).trim() : "";
			
			pedidos.setCodCia(codCia);
			pedidos.setTipoPedido(tipoPedido);
			pedidos.setSku(sku);
			pedidos.setCantidad(cantidad);
			pedidos.setVal1(variable);
			pedidos.setDestinatario(destinatario);
			pedidos.setCentroCostos(centroCostos);
			pedidos.setUsuario(usuario);
			pedidos.setEstrategia(estrategia);
			
			arrPedidos.add(pedidos);
		}
		pedidosCSLDTO.setArrPedidos(arrPedidos);
		
		return pedidosCSLDTO;
	}
}
