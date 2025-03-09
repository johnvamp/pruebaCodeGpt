package votre.portaloperaciones.activaciondemandas.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import votre.portaloperaciones.activaciondemandas.beans.CasoDTO;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class VerDetalleCasoCmd implements IBaseCmd {

	//private Logger logger = Logger.getLogger(this.getClass());
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		CasoDTO dto = obtenerCaso(req);
		req.setAttribute("caso", dto);
	}

	private CasoDTO obtenerCaso(HttpServletRequest req){

		CasoDTO caso = new CasoDTO();
		
		caso.setId(req.getParameter("id") != null ? req.getParameter("id") : "");
		caso.setCodInterno(req.getParameter("codInterno") != null ? req.getParameter("codInterno") : "");
		caso.setNombre(req.getParameter("nombre") != null ? req.getParameter("nombre") : "");
		caso.setCiudad(req.getParameter("ciudad") != null ? req.getParameter("ciudad") : "");
		caso.setDepto(req.getParameter("depto") != null ? req.getParameter("depto") : "");
		caso.setRegion(req.getParameter("region") != null ? req.getParameter("region") : "");
		caso.setMailPlan(req.getParameter("mailPlan") != null ? req.getParameter("mailPlan") : "");
		caso.setTipo(req.getParameter("tipo") != null ? req.getParameter("tipo") : "");
		caso.setZona(req.getParameter("zona") != null ? req.getParameter("zona") : "");
		caso.setReferencia(req.getParameter("sku") != null ? req.getParameter("sku") : "");
		caso.setCampanaOrigen(req.getParameter("campana") != null ? req.getParameter("campana") : "");
		caso.setDescripcion(req.getParameter("descripcion") != null ? req.getParameter("descripcion") : "");
		caso.setCantidad(req.getParameter("cantidad") != null ? req.getParameter("cantidad") : "");
		caso.setMotivo(req.getParameter("motivo") != null ? req.getParameter("motivo") : "");
		caso.setArea(req.getParameter("area") != null ? req.getParameter("area") : "");
		caso.setFechaRegistro(req.getParameter("fechaRegistro") != null ? req.getParameter("fechaRegistro") : "");
		caso.setCampanaReclamo(req.getParameter("campanaReclamo") != null ? req.getParameter("campanaReclamo") : "");
		caso.setEstado(req.getParameter("estado") != null ? req.getParameter("estado") : "");
		caso.setDsEstado(req.getParameter("dsEstado") != null ? req.getParameter("dsEstado") : "");
		caso.setOrden(req.getParameter("orden") != null ? req.getParameter("orden") : "");
		caso.setSkuSustituto(req.getParameter("skuSustituto") != null ? req.getParameter("skuSustituto") : "");
		caso.setDsSkuSustituto(req.getParameter("dsSkuSustituto") != null ? req.getParameter("dsSkuSustituto") : "");
		caso.setCantidadEntregada(req.getParameter("cantidadEntregada") != null ? req.getParameter("cantidadEntregada") : "");
		caso.setFechaAprobacion(req.getParameter("fechaAprobacion") != null ? req.getParameter("fechaAprobacion") : "");
		caso.setGuia(req.getParameter("guia") != null ? req.getParameter("guia") : "");
		caso.setTransportadora(req.getParameter("transportadora") != null ? req.getParameter("transportadora") : "");
		caso.setCedulaTransportista(req.getParameter("cedulaTransportista") != null ? req.getParameter("cedulaTransportista") : "");
		caso.setDireccionDespacho(req.getParameter("direccionDespacho") != null ? req.getParameter("direccionDespacho") : "");
		caso.setBarrioDespacho(req.getParameter("barrioDespacho") != null ? req.getParameter("barrioDespacho") : "");
		caso.setCiudadDespacho(req.getParameter("ciudadDespacho") != null ? req.getParameter("ciudadDespacho") : "");
		caso.setDeptoDespacho(req.getParameter("deptoDespacho") != null ? req.getParameter("deptoDespacho") : "");
		caso.setTel1Despacho(req.getParameter("tel1Despacho") != null ? req.getParameter("tel1Despacho") : "");
		caso.setTarifa(req.getParameter("tarifa") != null ? req.getParameter("tarifa") : "");
		caso.setFechaDespacho(req.getParameter("fechaDespacho") != null ? req.getParameter("fechaDespacho") : "");
		String tipoDespacho = req.getParameter("tipoDespacho") != null ? req.getParameter("tipoDespacho") : "";
		if(tipoDespacho.equals(Constantes.TIPO_DESPACHO_EXTRA)){
			tipoDespacho = "EXTRA";
		}
		if(tipoDespacho.equals(Constantes.TIPO_DESPACHO_BACK)){
			tipoDespacho = "BACKORDER";
		}
		caso.setTipoDespacho(tipoDespacho);
		caso.setDsTransportadora(req.getParameter("dsTransportadora") != null ? req.getParameter("dsTransportadora") : "");
		caso.setNombreTransportista(req.getParameter("nombreTransportista") != null ? req.getParameter("nombreTransportista") : "");
		caso.setGuiaMaster(req.getParameter("guiaMaster") != null ? req.getParameter("guiaMaster") : "");

		return caso;
	}
	
}
