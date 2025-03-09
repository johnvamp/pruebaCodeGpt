package votre.portaloperaciones.activaciondemandas.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.activaciondemandas.beans.CmpDemandaDTO;
import votre.portaloperaciones.activaciondemandas.beans.NovedadActDemandaDTO;
import votre.portaloperaciones.activaciondemandas.beans.RefeDemandaDTO;
import votre.portaloperaciones.activaciondemandas.delegate.ActivacionDemandasBusiness;
import votre.portaloperaciones.util.Constantes;
import votre.utils.moduloseguridad.beans.UsuarioDTO;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class GrabarNovedadDemandaCmd implements IBaseCmd {

	private Logger logger = Logger.getLogger(this.getClass());
	private String refeSustituto;
	private String colorSustituto;
	private String tallaSustituto;
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		
		String siguienteRuta = "activaciondemandas.validarIngreso.do";
		String mensaje = "";
		ActivacionDemandasBusiness demandasBusiness = null;
		try{
			demandasBusiness = new ActivacionDemandasBusiness();
			NovedadActDemandaDTO novedadActDemandaDTO = obtenerNovedad(req);
			
			NovedadActDemandaDTO guardada = demandasBusiness.grabarNovedad(novedadActDemandaDTO);
			if(guardada != null && guardada.getStatus().equals(Constantes.EXITO_SP)){
				siguienteRuta = "activaciondemandas.verIngreso.do";
				mensaje = guardada.getDsStatus();
				req.setAttribute("registro_exitoso", 1);
			}
			else{
				if(guardada != null){
					if(guardada.getStatus().equals(Constantes.NO_EXI_DATOS)){
						guardada.setTipo(Constantes.ACCION_CONFIRMACION);
						req.setAttribute("TITULO_GUARDAR", "CONFIRMAR");
					}
					mensaje = guardada.getDsStatus();
				}
			}
			
			req.setAttribute("mensaje", mensaje);
			req.setAttribute("novedad", novedadActDemandaDTO);
			req.setAttribute("refeSusti", refeSustituto);
			req.setAttribute("colorSusti", colorSustituto);
			req.setAttribute("tallaSusti", tallaSustituto);
			Configuracion.getInstance().getContext().getRequestDispatcher(siguienteRuta).forward(req, res);
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}

	private NovedadActDemandaDTO obtenerNovedad(HttpServletRequest req){
		NovedadActDemandaDTO novedadActDemandaDTO = new NovedadActDemandaDTO();
		CmpDemandaDTO cmpDemandaDTO = new CmpDemandaDTO();
		RefeDemandaDTO refeDemandaDTO = new RefeDemandaDTO();
		
		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		UsuarioDTO usuarioDTO = req.getSession().getAttribute( Constantes.USUARIO_SESION ) != null ? ((UsuarioDTO)req.getSession().getAttribute( Constantes.USUARIO_SESION )) : null;
		
		cmpDemandaDTO.setCia(codCia);
		cmpDemandaDTO.setId(req.getParameter("cedula") != null ? req.getParameter("cedula") : "");
		cmpDemandaDTO.setCodInterno(req.getParameter("codInterno") != null ? req.getParameter("codInterno") : "");
		
		refeDemandaDTO.setCia(codCia);
		refeDemandaDTO.setCampana(req.getParameter("campana") != null ? req.getParameter("campana") : "");
		refeDemandaDTO.setReferencia(req.getParameter("txtRefe") != null ? req.getParameter("txtRefe") : "");
		refeDemandaDTO.setColor(req.getParameter("txtColor") != null ? req.getParameter("txtColor") : "");
		refeDemandaDTO.setTalla(req.getParameter("txtTalla") != null ? req.getParameter("txtTalla") : "");
		refeDemandaDTO.setSku(req.getParameter("sku") != null ? req.getParameter("sku") : "");
		refeDemandaDTO.setSkuSusti(req.getParameter("skuSusti") != null ? req.getParameter("skuSusti") : "");
		refeSustituto = req.getParameter("txtRefeSusti") != null ? req.getParameter("txtRefeSusti").trim() : "";
		colorSustituto = req.getParameter("txtColorSusti") != null ? req.getParameter("txtColorSusti").trim() : "";
		tallaSustituto = req.getParameter("txtTallaSusti") != null ? req.getParameter("txtTallaSusti").trim() : "";
		
		novedadActDemandaDTO.setCia(codCia);
		novedadActDemandaDTO.setUsuario(usuarioDTO.getUsuario());
		novedadActDemandaDTO.setCmpDemandaDTO(cmpDemandaDTO);
		novedadActDemandaDTO.setRefeDemandaDTO(refeDemandaDTO);
		novedadActDemandaDTO.setMotivo(req.getParameter("motivo") != null ? req.getParameter("motivo") : "");
		novedadActDemandaDTO.setDsMotivo(req.getParameter("txtMotivo") != null ? req.getParameter("txtMotivo") : "");
		novedadActDemandaDTO.setArea(req.getParameter("area") != null ? req.getParameter("area") : "");
		novedadActDemandaDTO.setDsArea(req.getParameter("txtArea") != null ? req.getParameter("txtArea") : "");
		novedadActDemandaDTO.setCantidad(req.getParameter("cantidad") != null && !"".equals(req.getParameter("cantidad")) ? Integer.parseInt(req.getParameter("cantidad")) : 0);

		novedadActDemandaDTO.setTipo(req.getParameter("confirmacion") != null ? req.getParameter("confirmacion") : "");

		return novedadActDemandaDTO;
	}
	
}
