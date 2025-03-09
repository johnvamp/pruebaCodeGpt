package votre.portaloperaciones.activaciondemandas.cmd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.activaciondemandas.beans.GuiasMasterDTO;
import votre.portaloperaciones.activaciondemandas.delegate.ActivacionDemandasBusiness;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class ImprimirGuiasMasterCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	ArrayList<GuiasMasterDTO> arrGuias;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		ActivacionDemandasBusiness business = null;
		try{
			String mensaje = "";
			arrGuias = new ArrayList<GuiasMasterDTO>();
			business = new ActivacionDemandasBusiness();
			obtenerDatos(req);
			ArrayList<GuiasMasterDTO> arrObtenido;
			
			arrObtenido = business.imprimirGuiasMaster(arrGuias);
			if(arrObtenido != null){
				if(arrObtenido.size() > 0){
					req.setAttribute("arrMensajes", arrObtenido);
					req.setAttribute("nroMensajes", arrObtenido.size());
				}
				else{
					mensaje = "Guías generadas correctamente.";
				}
			}
			req.setAttribute("accionMaster", Constantes.ACCION_GUIA_MASTER);
			req.setAttribute("mensaje", mensaje);
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}

	public void obtenerDatos(HttpServletRequest req){
		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		int nroRegistros = Integer.parseInt(req.getParameter("nroGuiasMaster") != null ? req.getParameter("nroGuiasMaster") : "0");
		String cedulaTrans;
		String nombreTrans;
		String codigoTrans;
		int cantidad;
		for (int i = 1; i <= nroRegistros; i++) {
			cantidad = Integer.parseInt(req.getParameter("cantidad_"+i) != null ? req.getParameter("cantidad_"+i) : "0");
			if(cantidad > 0){
				GuiasMasterDTO dto = new GuiasMasterDTO();
				cedulaTrans = req.getParameter("cedulaTrans_"+i) != null ? req.getParameter("cedulaTrans_"+i) : "";
				nombreTrans = req.getParameter("nombreTrans_"+i) != null ? req.getParameter("nombreTrans_"+i) : "";
				codigoTrans = req.getParameter("codigoTrans_"+i) != null ? req.getParameter("codigoTrans_"+i) : "";
				dto.setCodCia(codCia);
				dto.setAccion(Constantes.ACCION_IMPRIMIR_MASTER);
				dto.setCedulaTrans(cedulaTrans);
				dto.setNombreTrans(nombreTrans);	
				dto.setCodigoTrans(codigoTrans);
				dto.setCantidad(String.valueOf(cantidad));
				
				arrGuias.add(dto);
			}
		}
	}
}
