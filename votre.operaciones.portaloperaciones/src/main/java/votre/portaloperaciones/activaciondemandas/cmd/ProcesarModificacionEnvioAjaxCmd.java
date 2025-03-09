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

public class ProcesarModificacionEnvioAjaxCmd implements IBaseCmd {

	private Logger logger = Logger.getLogger(this.getClass());
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		
		ActivacionDemandasBusiness demandasBusiness = null;
		try{
			demandasBusiness = new ActivacionDemandasBusiness();
			StatusDTO statusDTO = new StatusDTO();
			CasoDTO aprobacionDTO = obtenerModificacion(req);

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
	
	private CasoDTO obtenerModificacion(HttpServletRequest req){
		CasoDTO dtoDTO = new CasoDTO();
		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		UsuarioDTO usuarioDTO = req.getSession().getAttribute( Constantes.USUARIO_SESION ) != null ? ((UsuarioDTO)req.getSession().getAttribute( Constantes.USUARIO_SESION )) : null;
		
		dtoDTO.setCia(codCia);
		dtoDTO.setUsuario(usuarioDTO.getUsuario());
		dtoDTO.setAccion(Constantes.ACCION_MODIFICAR_ENVIO_MANUAL);
		dtoDTO.setCodInterno(req.getParameter("codInterno") != null ? req.getParameter("codInterno") : "0");
		dtoDTO.setOrden(req.getParameter("orden") != null ? req.getParameter("orden") : "0");
		dtoDTO.setReferencia(req.getParameter("sku") != null ? req.getParameter("sku") : "");
		dtoDTO.setSkuSustituto(req.getParameter("skuSustituto") != null ? req.getParameter("skuSustituto") : "");
		dtoDTO.setCantidad(req.getParameter("cantidad") != null ? req.getParameter("cantidad") : "0");
		dtoDTO.setTipoDespacho(req.getParameter("tipoDespacho") != null ? req.getParameter("tipoDespacho") : "");

		dtoDTO.setGuia(req.getParameter("txtGuia") != null ? req.getParameter("txtGuia") : "0");
		dtoDTO.setTransportadora(req.getParameter("transportadora") != null ? req.getParameter("transportadora") : "");
		dtoDTO.setCedulaTransportista(req.getParameter("transportista") != null ? req.getParameter("transportista") : "0");
		dtoDTO.setFechaDespacho(req.getParameter("txtFecha") != null ? req.getParameter("txtFecha") : "");

		return dtoDTO;
	}
}
