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

public class ListarPedidosCmd implements IBaseCmd {

	private Logger logger = Logger.getLogger(this.getClass());
	PersonalsoftException ps = null;
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		PedidosCSLFacade pedidosCSLFacade = null;
		PedidosCSLDTO dto = null;
		try{
			String mensaje = "";
			pedidosCSLFacade = new PedidosCSLFacade();
			dto = dtoAssembler(req);
			
			dto = pedidosCSLFacade.listarOpciones(dto);
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
		String accion = req.getParameter("accion")!=null ? req.getParameter("accion") : "";
		
		dto.setCodCia(codCia);
		dto.setAccion(accion);
		
		req.setAttribute("accion", accion);
		
		return dto;
	}	
}
