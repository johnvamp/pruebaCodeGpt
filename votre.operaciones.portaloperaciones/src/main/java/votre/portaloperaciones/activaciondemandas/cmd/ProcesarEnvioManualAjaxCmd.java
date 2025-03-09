package votre.portaloperaciones.activaciondemandas.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;

import votre.portaloperaciones.activaciondemandas.beans.CasoDTO;
import votre.portaloperaciones.activaciondemandas.delegate.ActivacionDemandasBusiness;
import votre.portaloperaciones.util.Constantes;
import votre.portaloperaciones.util.beans.StatusDTO;
import votre.utils.moduloseguridad.beans.UsuarioDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class ProcesarEnvioManualAjaxCmd implements IBaseCmd {

	private Logger logger = Logger.getLogger(this.getClass());
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		
		ActivacionDemandasBusiness demandasBusiness = null;
		try{
			demandasBusiness = new ActivacionDemandasBusiness();
			StatusDTO statusDTO = new StatusDTO();
			CasoDTO aprobacionDTO = obtenerAprobacion(req);

			aprobacionDTO = demandasBusiness.grabarAprobacion(aprobacionDTO);
			statusDTO.setStatus(aprobacionDTO.getStatus());
			statusDTO.setDsStatus(aprobacionDTO.getDsStatus());
			
			JAXBContext context = JAXBContext.newInstance(StatusDTO.class);
			Marshaller m = context.createMarshaller();
			res.setContentType("text/xml; charset=UTF-8");
			m.marshal(statusDTO, res.getWriter());
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}
	
	private CasoDTO obtenerAprobacion(HttpServletRequest req){
		CasoDTO aprobacionDTO = new CasoDTO();
		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		UsuarioDTO usuarioDTO = req.getSession().getAttribute( Constantes.USUARIO_SESION ) != null ? ((UsuarioDTO)req.getSession().getAttribute( Constantes.USUARIO_SESION )) : null;
		
		aprobacionDTO.setCia(codCia);
		aprobacionDTO.setUsuario(usuarioDTO.getUsuario());
		aprobacionDTO.setAccion(Constantes.ACCION_ENVIAR_MANUAL);
		aprobacionDTO.setCodInterno(req.getParameter("codInterno") != null ? req.getParameter("codInterno") : "0");
		aprobacionDTO.setOrden(req.getParameter("orden") != null ? req.getParameter("orden") : "0");
		aprobacionDTO.setReferencia(req.getParameter("sku") != null ? req.getParameter("sku") : "");
		aprobacionDTO.setSkuSustituto(req.getParameter("skuSustituto") != null ? req.getParameter("skuSustituto") : "");
		aprobacionDTO.setCantidad(req.getParameter("cantidad") != null ? req.getParameter("cantidad") : "0");
		aprobacionDTO.setTipoDespacho(req.getParameter("tipoDespacho") != null ? req.getParameter("tipoDespacho") : "");

		aprobacionDTO.setGuia(req.getParameter("txtGuia") != null ? req.getParameter("txtGuia") : "0");
		aprobacionDTO.setTransportadora(req.getParameter("transportadora") != null ? req.getParameter("transportadora") : "");
		aprobacionDTO.setCedulaTransportista(req.getParameter("transportista") != null ? req.getParameter("transportista") : "0");
		aprobacionDTO.setFechaDespacho(req.getParameter("txtFecha") != null ? req.getParameter("txtFecha") : "");

		return aprobacionDTO;
	}
}
