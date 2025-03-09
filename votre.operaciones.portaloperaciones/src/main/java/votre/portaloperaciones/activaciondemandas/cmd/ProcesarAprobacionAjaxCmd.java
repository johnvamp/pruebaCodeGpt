package votre.portaloperaciones.activaciondemandas.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;

import votre.portaloperaciones.activaciondemandas.beans.CasoDTO;
import votre.portaloperaciones.activaciondemandas.beans.RefeDemandaDTO;
import votre.portaloperaciones.activaciondemandas.delegate.ActivacionDemandasBusiness;
import votre.portaloperaciones.util.Constantes;
import votre.portaloperaciones.util.beans.StatusDTO;
import votre.utils.moduloseguridad.beans.UsuarioDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class ProcesarAprobacionAjaxCmd implements IBaseCmd {

	private Logger logger = Logger.getLogger(this.getClass());
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		
		ActivacionDemandasBusiness demandasBusiness = null;
		try{
			demandasBusiness = new ActivacionDemandasBusiness();
			boolean puedeGrabar = true;
			StatusDTO statusDTO = new StatusDTO();
			CasoDTO aprobacionDTO = obtenerAprobacion(req);
			RefeDemandaDTO refeDemandaDTO = obtenerNovedad(req);
			aprobacionDTO.setSkuSustituto(refeDemandaDTO.getSku());

			if(Constantes.ACCION_APROBAR.equals(aprobacionDTO.getAccion())){
				RefeDemandaDTO refeValidada = demandasBusiness.validarReferencia(refeDemandaDTO);
				if(refeValidada.getStatus().equals(Constantes.ERROR_SP)){
					puedeGrabar = false;
				}
				statusDTO.setStatus(refeValidada.getStatus());
				statusDTO.setDsStatus(refeValidada.getDsStatus());
			}
			//Se procede a guardar la aprobacion
			if(puedeGrabar){
				aprobacionDTO = demandasBusiness.grabarAprobacion(aprobacionDTO);
				statusDTO.setStatus(aprobacionDTO.getStatus());
				statusDTO.setDsStatus(aprobacionDTO.getDsStatus());
			}
			
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
		aprobacionDTO.setAccion(req.getParameter("accion") != null ? req.getParameter("accion") : Constantes.ACCION_APROBAR);
		aprobacionDTO.setCodInterno(req.getParameter("codInterno") != null ? req.getParameter("codInterno") : "0");
		aprobacionDTO.setOrden(req.getParameter("orden") != null ? req.getParameter("orden") : "0");
		aprobacionDTO.setReferencia(req.getParameter("sku") != null ? req.getParameter("sku") : "");
		aprobacionDTO.setCantidad(req.getParameter("cantidad") != null ? req.getParameter("cantidad") : "0");
		aprobacionDTO.setTipoDespacho(req.getParameter("tipoDespacho") != null ? req.getParameter("tipoDespacho") : "");

		return aprobacionDTO;
	}

	private RefeDemandaDTO obtenerNovedad(HttpServletRequest req){
		int relleno;
		RefeDemandaDTO refeDemandaDTO = new RefeDemandaDTO();
		
		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		
		refeDemandaDTO.setCia(codCia);
		refeDemandaDTO.setCampana(req.getParameter("campana") != null ? req.getParameter("campana") : "");
		
		StringBuffer refe = new StringBuffer(req.getParameter("txtRefe") != null ? req.getParameter("txtRefe").trim() : "");
		if(refe.length()>=7){
			refe = new StringBuffer(refe.substring(0, 7));
		}
		else{
			relleno = 7 - refe.length();
			for (int i = 0; i < relleno; i++) {
				refe.append(" ");
			}
		}
		
		StringBuffer color = new StringBuffer(req.getParameter("txtColor") != null ? req.getParameter("txtColor").trim() : "");
		if(color.length()>=3){
			color = new StringBuffer(color.substring(0, 3));
		}
		else{
			relleno = 3 - color.length();
			for (int i = 0; i < relleno; i++) {
				color.insert(0, " ");
			}
		}

		StringBuffer talla = new StringBuffer(req.getParameter("txtTalla") != null ? req.getParameter("txtTalla").trim() : "");
		if(talla.length()>=3){
			talla = new StringBuffer(talla.substring(0, 3));
		}
		else{
			relleno = 3 - talla.length();
			for (int i = 0; i < relleno; i++) {
				talla.insert(0, " ");
			}
		}
		
		StringBuffer sku = new StringBuffer(refe);
		sku.append(" ");
		sku.append(color);
		sku.append(" ");
		sku.append(talla);
		
		refeDemandaDTO.setReferencia(refe.toString());
		refeDemandaDTO.setColor(color.toString());
		refeDemandaDTO.setTalla(talla.toString());
		refeDemandaDTO.setSku(sku.toString());

		return refeDemandaDTO;
	}
	
}
