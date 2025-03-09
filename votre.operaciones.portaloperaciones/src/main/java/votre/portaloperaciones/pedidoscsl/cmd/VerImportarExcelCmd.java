package votre.portaloperaciones.pedidoscsl.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class VerImportarExcelCmd implements IBaseCmd {

	private Logger logger = Logger.getLogger(this.getClass());
	PersonalsoftException ps = null;
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		try{
			String tipoPedido = req.getParameter("tipoPedido")!=null ? req.getParameter("tipoPedido") : "";
			String desPedido = req.getParameter("desPedido")!=null ? req.getParameter("desPedido") : "";
			String destinatario = req.getParameter("destinatario")!=null ? req.getParameter("destinatario") : "";
			String desEstrategia = req.getParameter("desEstrategia")!=null ? req.getParameter("desEstrategia") : "";
			String accion = req.getParameter("accion")!=null ? req.getParameter("accion") : "";
			
			req.setAttribute("tipoPedido", tipoPedido);
			req.setAttribute("desPedido", desPedido);
			req.setAttribute("destinatario", destinatario);
			req.setAttribute("desEstrategia", desEstrategia);
			req.setAttribute("accion", accion);
		}
		catch (Exception e) {
			ps = new PersonalsoftException(e);
			logger.error(ps.getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
			throw ps;
		}
	}	
}
