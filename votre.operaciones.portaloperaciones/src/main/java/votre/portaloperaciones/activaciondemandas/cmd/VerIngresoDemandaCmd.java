package votre.portaloperaciones.activaciondemandas.cmd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import votre.portaloperaciones.activaciondemandas.beans.CmpDemandaDTO;
import votre.portaloperaciones.activaciondemandas.beans.NovedadActDemandaDTO;
import votre.portaloperaciones.activaciondemandas.beans.OpcionDTO;
import votre.portaloperaciones.activaciondemandas.beans.RefeDemandaDTO;
import votre.portaloperaciones.activaciondemandas.delegate.ActivacionDemandasBusiness;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class VerIngresoDemandaCmd implements IBaseCmd {

	//private Logger logger = Logger.getLogger(this.getClass());
	private String refeSustituto;
	private String colorSustituto;
	private String tallaSustituto;
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		ActivacionDemandasBusiness ingresosBusiness = new ActivacionDemandasBusiness();
		ArrayList<OpcionDTO> motivos = new ArrayList<OpcionDTO>();
		ArrayList<OpcionDTO> areas = new ArrayList<OpcionDTO>();
		OpcionDTO motivo = new OpcionDTO();
		OpcionDTO area = new OpcionDTO();

		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		NovedadActDemandaDTO novedadActDemandaDTO = req.getAttribute("registro_exitoso") == null ? obtenerNovedad(req) : null;

		motivo.setCia(codCia);
		motivo.setAccion(Constantes.ACCION_MOTIVOS_DEMANDA);
		area.setCia(codCia);
		area.setAccion(Constantes.ACCION_AREAS_DEMANDA);
		
		motivos = ingresosBusiness.consultarOpciones(motivo);
		areas = ingresosBusiness.consultarOpciones(area);

		req.setAttribute("novedad", novedadActDemandaDTO);
		req.setAttribute("motivos", motivos);
		req.setAttribute("areas", areas);
		req.setAttribute("refeSusti", refeSustituto);
		req.setAttribute("colorSusti", colorSustituto);
		req.setAttribute("tallaSusti", tallaSustituto);
	}

	private NovedadActDemandaDTO obtenerNovedad(HttpServletRequest req){

		NovedadActDemandaDTO novedadActDemandaDTO = new NovedadActDemandaDTO();
		CmpDemandaDTO cmpDemandaDTO = new CmpDemandaDTO();
		RefeDemandaDTO refeDemandaDTO = new RefeDemandaDTO();
		
		cmpDemandaDTO.setId(req.getParameter("cedula") != null ? req.getParameter("cedula") : "");

		refeDemandaDTO.setCampana(req.getParameter("campana") != null ? req.getParameter("campana") : "");
		refeDemandaDTO.setReferencia(req.getParameter("txtRefe") != null ? req.getParameter("txtRefe") : "");
		refeDemandaDTO.setColor(req.getParameter("txtColor") != null ? req.getParameter("txtColor") : "");
		refeDemandaDTO.setTalla(req.getParameter("txtTalla") != null ? req.getParameter("txtTalla") : "");
		
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
	
}
