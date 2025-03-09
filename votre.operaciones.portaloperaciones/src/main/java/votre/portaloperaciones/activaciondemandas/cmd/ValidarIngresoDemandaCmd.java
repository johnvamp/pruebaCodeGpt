package votre.portaloperaciones.activaciondemandas.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.activaciondemandas.beans.CmpDemandaDTO;
import votre.portaloperaciones.activaciondemandas.beans.NovedadActDemandaDTO;
import votre.portaloperaciones.activaciondemandas.beans.RefeDemandaDTO;
import votre.portaloperaciones.activaciondemandas.delegate.ActivacionDemandasBusiness;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class ValidarIngresoDemandaCmd implements IBaseCmd {

	private Logger logger = Logger.getLogger(this.getClass());
	private String refeSustituto;
	private String colorSustituto;
	private String tallaSustituto;
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		
		String siguienteRuta = "activaciondemandas.jspValidacion.do";
		String mensaje = "";
		String msg =  (String) req.getAttribute("mensaje");
		if(msg != null){
			refeSustituto = (String) (req.getAttribute("refeSusti") != null ? req.getAttribute("refeSusti") : "");
			colorSustituto = (String) (req.getAttribute("colorSusti") != null ? req.getAttribute("colorSusti") : "");
			tallaSustituto = (String) (req.getAttribute("tallaSusti") != null ? req.getAttribute("tallaSusti") : "");
		}
		ActivacionDemandasBusiness demandasBusiness = null;
		try{
			demandasBusiness = new ActivacionDemandasBusiness();
			String tituloGuardar = (String) req.getAttribute("TITULO_GUARDAR");
			NovedadActDemandaDTO novedadGuardada  = (NovedadActDemandaDTO) req.getAttribute("novedad");
			
			NovedadActDemandaDTO novedadActDemandaDTO = novedadGuardada != null ? novedadGuardada : obtenerNovedad(req);
			
			CmpDemandaDTO cmpValidada = demandasBusiness.validarCompradora(novedadActDemandaDTO.getCmpDemandaDTO());
			if(cmpValidada != null && cmpValidada.getStatus().equals(Constantes.EXITO_SP)){
				RefeDemandaDTO refeValidada = demandasBusiness.validarReferencia(novedadActDemandaDTO.getRefeDemandaDTO());
				if(refeValidada == null || refeValidada.getStatus().equals(Constantes.ERROR_SP)){
					siguienteRuta = "activaciondemandas.verIngreso.do";
					mensaje = refeValidada != null ? refeValidada.getDsStatus() : "";
				}
				else{
					if(!"".equals(refeSustituto) && refeSustituto != null){	
						refeValidada = obtenerSustituto(req);
						refeValidada = demandasBusiness.validarReferencia(refeValidada);
						if(refeValidada == null || refeValidada.getStatus().equals(Constantes.ERROR_SP)){
							siguienteRuta = "activaciondemandas.verIngreso.do";
							mensaje = refeValidada != null ? refeValidada.getDsStatus() : "";
						}
						req.setAttribute("skuSusti", refeValidada.getSku());
					}
				}
			}
			else{
				siguienteRuta = "activaciondemandas.verIngreso.do";
				mensaje = cmpValidada != null ? cmpValidada.getDsStatus() : "";
			}
			
			req.setAttribute("TITULO_GUARDAR", tituloGuardar != null ? tituloGuardar : "GRABAR");
			req.setAttribute("mensaje", msg != null ? msg : mensaje);
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
		int relleno;
		NovedadActDemandaDTO novedadActDemandaDTO = new NovedadActDemandaDTO();
		CmpDemandaDTO cmpDemandaDTO = new CmpDemandaDTO();
		RefeDemandaDTO refeDemandaDTO = new RefeDemandaDTO();
		
		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		
		cmpDemandaDTO.setCia(codCia);
		cmpDemandaDTO.setId(req.getParameter("cedula") != null ? req.getParameter("cedula") : "");
		
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
		
		novedadActDemandaDTO.setCmpDemandaDTO(cmpDemandaDTO);
		novedadActDemandaDTO.setRefeDemandaDTO(refeDemandaDTO);
		novedadActDemandaDTO.setMotivo(req.getParameter("motivo") != null ? req.getParameter("motivo") : "");
		novedadActDemandaDTO.setDsMotivo(req.getParameter("txtMotivo") != null ? req.getParameter("txtMotivo") : "");
		novedadActDemandaDTO.setArea(req.getParameter("area") != null ? req.getParameter("area") : "");
		novedadActDemandaDTO.setDsArea(req.getParameter("txtArea") != null ? req.getParameter("txtArea") : "");
		novedadActDemandaDTO.setCantidad(req.getParameter("cantidad") != null && !"".equals(req.getParameter("cantidad")) ? Integer.parseInt(req.getParameter("cantidad")) : 0);
		
		refeSustituto = req.getParameter("txtRefeSusti") != null ? req.getParameter("txtRefeSusti").trim() : "";
		colorSustituto = req.getParameter("txtColorSusti") != null ? req.getParameter("txtColorSusti").trim() : "";
		tallaSustituto = req.getParameter("txtTallaSusti") != null ? req.getParameter("txtTallaSusti").trim() : "";

		return novedadActDemandaDTO;
	}
	
	private RefeDemandaDTO obtenerSustituto(HttpServletRequest req){
		int relleno;
		RefeDemandaDTO refeDemandaDTO = new RefeDemandaDTO();
		
		StringBuffer refeSusti = new StringBuffer(refeSustituto);
		if(refeSusti.length()>=7){
			refeSusti = new StringBuffer(refeSusti.substring(0, 7));
		}
		else{
			relleno = 7 - refeSusti.length();
			for (int i = 0; i < relleno; i++) {
				refeSusti.append(" ");
			}
		}
		
		StringBuffer colorSusti = new StringBuffer(colorSustituto);
		if(colorSusti.length()>=3){
			colorSusti = new StringBuffer(colorSusti.substring(0, 3));
		}
		else{
			relleno = 3 - colorSusti.length();
			for (int i = 0; i < relleno; i++) {
				colorSusti.insert(0, " ");
			}
		}

		StringBuffer tallaSusti = new StringBuffer(tallaSustituto);
		if(tallaSusti.length()>=3){
			tallaSusti = new StringBuffer(tallaSusti.substring(0, 3));
		}
		else{
			relleno = 3 - tallaSusti.length();
			for (int i = 0; i < relleno; i++) {
				tallaSusti.insert(0, " ");
			}
		}
		
		StringBuffer skuSusti = new StringBuffer(refeSusti);
		skuSusti.append(" ");
		skuSusti.append(colorSusti);
		skuSusti.append(" ");
		skuSusti.append(tallaSusti);
		
		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		refeDemandaDTO.setCia(codCia);
		refeDemandaDTO.setCampana(req.getParameter("campana") != null ? req.getParameter("campana") : "");
		refeDemandaDTO.setSku(skuSusti.toString());
		refeSustituto = refeSusti.toString();
		colorSustituto = colorSusti.toString();
		tallaSustituto = tallaSusti.toString();
		
		return refeDemandaDTO;
	}
	
}
