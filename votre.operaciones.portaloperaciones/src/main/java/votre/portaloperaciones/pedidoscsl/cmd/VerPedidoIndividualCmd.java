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

public class VerPedidoIndividualCmd implements IBaseCmd {

	private Logger logger = Logger.getLogger(this.getClass());
	PersonalsoftException ps = null;
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		try{
			String tipoPedido = req.getParameter("tipoPedido")!=null ? req.getParameter("tipoPedido") : "";
			String desPedido = req.getParameter("desPedido")!=null ? req.getParameter("desPedido") : "";
			String destinatario = req.getParameter("destinatario")!=null ? req.getParameter("destinatario") : "";
			String desEstrategia = req.getParameter("desEstrategia")!=null ? req.getParameter("desEstrategia") : "";
			String accion = req.getParameter("accion")!=null ? req.getParameter("accion") : "";
			
			if(destinatario.equals(Constantes.DESTINATARIO_REGION) || destinatario.equals(Constantes.DESTINATARIO_LIDER)
					|| destinatario.equals(Constantes.DESTINATARIO_COMANDO)){
				PedidosCSLFacade pedidosCSLFacade = new PedidosCSLFacade();
				PedidosCSLDTO dto = new PedidosCSLDTO();
				String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";;
				dto.setCodCia(codCia);
				if(destinatario.equals(Constantes.DESTINATARIO_REGION)){
					dto.setAccion(Constantes.ACCION_REGIONES);
				}
				else if(destinatario.equals(Constantes.DESTINATARIO_LIDER)){
					dto.setAccion(Constantes.ACCION_ZONA);
				}
				else if(destinatario.equals(Constantes.DESTINATARIO_COMANDO)){
					dto.setAccion(Constantes.ACCION_COMANDO);
				}
				
				dto = pedidosCSLFacade.listarOpciones(dto);
				if(dto != null){
					if(dto.getSpSts().equals(Constantes.EXITO_SP)){
						if(destinatario.equals(Constantes.DESTINATARIO_COMANDO)){
							req.setAttribute("arrComandos", dto.getArrPedidos());
						}
						else{
							req.setAttribute("arrCombo", dto.getArrDatosCombo());
						}
					}
				}
			}
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
