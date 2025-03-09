package votre.portaloperaciones.transportistas.cmd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.transportistas.beans.TransportistasDTO;
import votre.portaloperaciones.transportistas.delegate.TransportistasBusiness;
import votre.portaloperaciones.util.Constantes;
import votre.portaloperaciones.util.Util;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class GuardarEntregadosCmd implements IBaseCmd {
	private Logger logger = Logger.getLogger(this.getClass());
	ArrayList<TransportistasDTO> registros = new ArrayList<TransportistasDTO>();

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		TransportistasBusiness business;
		try{
			business = new TransportistasBusiness();
			obtenerDatos(req);
			String mensaje = "";
			String codigoCampana =  req.getParameter("transportistasComboCampana") != null ? req.getParameter("transportistasComboCampana") : "";
			String TipoTranspo =  req.getParameter("pEstBuscar") != null ? req.getParameter("pEstBuscar") : "";
			String zona =  req.getParameter("zona") != null ? req.getParameter("zona") : "";
			ArrayList<TransportistasDTO> arrObtenido;
			arrObtenido = business.guardarEntregados(registros);
			if(arrObtenido != null){
				if(arrObtenido.size() > 0){
					req.setAttribute("arrMensajes", arrObtenido);
					req.setAttribute("nroMensajes", arrObtenido.size());
				}
				else{
					mensaje = "Proceso ejecutado exitosamente.";
				}
			}
			req.setAttribute("buscarCampana", codigoCampana);
			req.setAttribute("pEstBuscar", TipoTranspo);
			req.setAttribute("buscarZona", zona);
			req.setAttribute("mensaje", mensaje);
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}
	
	private void obtenerDatos(HttpServletRequest req){
		String vec_orden = Util.ifEmpty( req.getParameter("vec_orden") );
		String vec_guia = Util.ifEmpty( req.getParameter("vec_guia") );
		
		String[] vecorden = vec_orden.split(","); 
		String[] vecguia  = vec_guia.split(","); 
		
		String cia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		for (int i = 0; i < vecorden.length; i++) {
			TransportistasDTO dto = new TransportistasDTO();
			dto.setpCia(cia);
			dto.setNumeroOrden(vecorden[i]);
			dto.setNumeroGuia(vecguia[i]);
			dto.setCodigoCombo(Constantes.CODIGO_COMBO);
			dto.setEstadoCombo("");
			dto.setObservaciones("");
			
			registros.add(dto);
		}
	}

}
