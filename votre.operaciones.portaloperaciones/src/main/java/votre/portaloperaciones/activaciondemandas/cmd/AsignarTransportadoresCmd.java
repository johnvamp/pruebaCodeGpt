package votre.portaloperaciones.activaciondemandas.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.activaciondemandas.beans.AsignarDTO;
import votre.portaloperaciones.activaciondemandas.delegate.ActivacionDemandasBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class AsignarTransportadoresCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String accion;
	private String valor;
	private String mensaje;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		AsignarDTO asignarDTO = null;
		ActivacionDemandasBusiness business = null;
		try{
			asignarDTO = new AsignarDTO();
			business = new ActivacionDemandasBusiness();
			obtenerDatos(req);
			dtoAssembler(asignarDTO);
			
			asignarDTO = business.asignarTransportadores(asignarDTO);
			if(asignarDTO != null){
				if(asignarDTO.getEstado().equals(Constantes.EXITO_SP)){
					mensaje = asignarDTO.getDescripcion();
				}
				else{
					mensaje = asignarDTO.getDescripcion();
				}
			}
			req.setAttribute("accionMaster", Constantes.ACCION_GUIA_MASTER);
			if (mensaje != null && !mensaje.equals("")){
				req.setAttribute("mensaje", mensaje);				
			}
			
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}
	
	public void obtenerDatos(HttpServletRequest req){
		codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		accion = req.getParameter("accion") != null ? req.getParameter("accion") : "";
		valor = req.getParameter("valor") != null ? req.getParameter("valor") : "";
	}

	private void dtoAssembler(final AsignarDTO dto){
		dto.setCodCia(codCia);
		dto.setAccion(accion);
		dto.setValor(valor);
	}
}
