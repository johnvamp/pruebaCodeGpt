package votre.portaloperaciones.pedidoscsl.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.pedidoscsl.beans.PedidosCSLDTO;
import votre.portaloperaciones.pedidoscsl.facade.PedidosCSLFacade;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class ConsultarAuditoriaSolicitudCmd implements IBaseCmd {

	private Logger logger = Logger.getLogger(this.getClass());
	PersonalsoftException ps = null;
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		PedidosCSLFacade pedidosCSLFacade = null;
		PedidosCSLDTO dto = null;
		try{
			String mensaje = "";
			pedidosCSLFacade = new PedidosCSLFacade();
			dto = dtoAssembler(req);
			
			dto = pedidosCSLFacade.consultarAuditoria(dto);
			if(dto != null){
				if(dto.getSpSts().equals(Constantes.EXITO_SP)){
					req.setAttribute("datos", dto);
				}
				else{
					mensaje = dto.getSpDes();
				}
			}
			
			if(mensaje != null && mensaje != ""){
				req.setAttribute("mensaje", mensaje);
			}
		}
		catch (Exception e) {
			ps = new PersonalsoftException(e);
			logger.error(ps.getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
			throw ps;
		}
	}
	
	private PedidosCSLDTO dtoAssembler(HttpServletRequest req){
		PedidosCSLDTO dto = new PedidosCSLDTO();
		
		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";;
		String ordenConsultar = req.getParameter("ordenConsultar")!=null ? req.getParameter("ordenConsultar") : "";
		String estrategia = req.getParameter("estrategia")!=null ? req.getParameter("estrategia") : "";
		String desPedido = req.getParameter("desPedido")!=null ? req.getParameter("desPedido") : "";
		String usuario = req.getParameter("usuario")!=null ? req.getParameter("usuario") : "";
		String numOrden = req.getParameter("numOrden")!=null ? req.getParameter("numOrden") : "";
		
		dto.setCodCia(codCia);
		dto.setNumOrden(ordenConsultar);
		dto.setEstrategia(estrategia);
		
		req.setAttribute("desPedido", desPedido);
		req.setAttribute("usuario", usuario);
		req.setAttribute("numOrden", numOrden);
		
		return dto;
	}	
}
