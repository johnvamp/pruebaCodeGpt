package votre.portaloperaciones.despachocatalogo.reportes.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.despachocatalogo.reportes.beans.InformacionDTO;
import votre.portaloperaciones.despachocatalogo.reportes.delegate.ReportesBusiness;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class GenerarInformacionCmd implements IBaseCmd {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private String codCia;
	private String codigoLabel;
	private String fechaIni;
	private String fechaFin;
	private String usuario;
	private String zona;
	private String campana;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		ReportesBusiness reportesBusiness = new ReportesBusiness();
		InformacionDTO informacion = new InformacionDTO();
		
		try{
			obtenerDatos(req);
			dtoAssembler(informacion);
			informacion = reportesBusiness.consultar(informacion);
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}

	public void obtenerDatos(HttpServletRequest req){
		codCia = req.getParameter("codCia");
		codigoLabel = req.getParameter("codigoLabel");
		fechaIni = req.getParameter("fechaIni") != null ? req.getParameter("fechaIni") : "";
		fechaFin = req.getParameter("fechaFin") != null ? req.getParameter("fechaFin") : "";
		usuario = req.getParameter("usuario");
		zona = req.getParameter("zona") != null && !"0".equals(req.getParameter("zona")) ? req.getParameter("zona") : "";
		campana= req.getParameter("campana") != null ? req.getParameter("campana") : "";
	}
	
	private void dtoAssembler(final InformacionDTO informacion){
		informacion.setCodCia(codCia);
		informacion.setCodigoLabel(codigoLabel);
		informacion.setAccion(Constantes.ACCION_GENERAR);
		informacion.setFechaIni(fechaIni);
		informacion.setFechaFin(fechaFin);
		if(usuario.length() > 10){
			informacion.setUsuario(usuario.substring(0, 10));
		}
		else{
			informacion.setUsuario(usuario);
		}
		informacion.setZona(zona);
		informacion.setCampana(campana);
	}
}
