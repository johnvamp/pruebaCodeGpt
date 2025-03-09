package votre.portaloperaciones.activaciondemandas.cmd;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import votre.portaloperaciones.activaciondemandas.beans.CasoDTO;
import votre.portaloperaciones.activaciondemandas.beans.OpcionDTO;
import votre.portaloperaciones.activaciondemandas.delegate.ActivacionDemandasBusiness;
import votre.portaloperaciones.util.Constantes;
import votre.utils.excel.write.Celda;
import votre.utils.excel.write.GeneradorExcel;
import votre.utils.excel.write.ReporteExcelDTO;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class ConsultarEnviosManualesCmd implements IBaseCmd {

	private Logger logger = Logger.getLogger(this.getClass());
	private String excel;
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		ActivacionDemandasBusiness actBusiness = new ActivacionDemandasBusiness();
		ArrayList<OpcionDTO> opciones = new ArrayList<OpcionDTO>();
		ArrayList<OpcionDTO> transportadoras = new ArrayList<OpcionDTO>();
		ArrayList<OpcionDTO> transportistas = new ArrayList<OpcionDTO>();
		OpcionDTO opcion = new OpcionDTO();
		CasoDTO dto = null;
		ArrayList<CasoDTO> lista = new ArrayList<CasoDTO>();
		String siguienteRuta = "activaciondemandas.jspEnviosManuales.do";

		try{
			String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
			excel = req.getParameter("generaExcel") != null ? req.getParameter("generaExcel") : "";
			String consultar = req.getParameter("consultar");
	
			opcion.setCia(codCia);
			opcion.setAccion(Constantes.ACCION_SELECCION_DEMANDA);
			opciones = actBusiness.consultarOpciones(opcion);
			
			opcion.setAccion(Constantes.ACCION_SELECCION_TRANSPORTADOR);
			transportadoras = actBusiness.consultarOpciones(opcion);
	
			opcion.setAccion(Constantes.ACCION_SELECCION_TRANSPORTISTA);
			transportistas = actBusiness.consultarOpciones(opcion);
	
			if(consultar != null){
				dto = obtenerEnvio(req);
				lista = actBusiness.consultarCasos(dto);
				req.setAttribute("busqueda", dto);
			}
			
			if("".equals(excel)){
				req.setAttribute("opciones", opciones);
				req.setAttribute("transportadoras", transportadoras);
				req.setAttribute("transportistas", transportistas);
				req.setAttribute("envios", lista);
				req.setAttribute("cantidadEnvios", lista.size());
				req.setAttribute("SELECCION_TODO", Constantes.SELECCION_TODO);
				req.setAttribute("SELECCION_REFERENCIA", Constantes.SELECCION_REFERENCIA);
				req.getSession().setAttribute(Constantes.OBJETO_BANDERA_EXPORTAR, new Boolean(true));
				Configuracion.getInstance().getContext().getRequestDispatcher(siguienteRuta).forward(req, res);
			}
			else{
				ReporteExcelDTO reporteExcelDTO = generarExcelDTO(lista);
				GeneradorExcel generadorExcel = new GeneradorExcel();
				HSSFWorkbook workbook = generadorExcel.generarInforme(reporteExcelDTO);
				res.setHeader("Content-Disposition", "attachment; filename=ReporteEnvios.xls");
		        ServletOutputStream out = res.getOutputStream();
		        workbook.write(out);
		        out.flush();
		        out.close();
				req.getSession().setAttribute(Constantes.OBJETO_BANDERA_EXPORTAR, new Boolean(true));
			}
		}
		catch (Exception e) {
			req.getSession().setAttribute(Constantes.OBJETO_BANDERA_EXPORTAR, new Boolean(true));
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}
	
	private ReporteExcelDTO generarExcelDTO(ArrayList<CasoDTO> casos) {
		ReporteExcelDTO excelDTO = new ReporteExcelDTO("Envios");

		//ArrayList<List<String>> registros = new ArrayList<List<String>>();
		ArrayList<List<Celda>> registros = new ArrayList<List<Celda>>();
		ArrayList<String> titulos = new ArrayList<String>();

		//Titulos
		titulos.add("ID");
		titulos.add("COD INTERNO");
		titulos.add("NOMBRE");
		titulos.add("CIUDAD");
		titulos.add("DEPARTAMENTO");
		titulos.add("REGION");
		titulos.add("MAIL PLAN");
		titulos.add("TIPO");
		titulos.add("ZONA");
		titulos.add("SKU");
		titulos.add("DESCRIPCION");
		titulos.add("CANTIDAD");
		titulos.add("CAMPAÑA ORIGEN");
		titulos.add("MOTIVO");
		titulos.add("AREA");
		titulos.add("FECHA REGISTRO");
		titulos.add("CAMPAÑA RECLAMO");
		titulos.add("ESTADO");
		titulos.add("ORDEN");
		titulos.add("SKU SUSTITUTO");
		titulos.add("DESCRIPCION SUSTITUTO");
		titulos.add("CANTIDAD ENTREGADA");
		titulos.add("TIPO DESPACHO");
		titulos.add("FECHA APROBACION");
		titulos.add("GUIA");
		titulos.add("TRANSPORTADORA");
		titulos.add("TRANSPORTISTA");
		titulos.add("DIRECCION DESPACHO");
		titulos.add("BARRIO DESPACHO");
		titulos.add("CIUDAD DESPACHO");
		titulos.add("DEPTO DESPACHO");
		titulos.add("TELEFONO");
		titulos.add("TARIFA");
		titulos.add("FECHA DESPACHO");
		
		for(CasoDTO dto : casos){
			/*
			List<String> columnas = new ArrayList<String>();
			columnas.add(dto.getId());
			columnas.add(dto.getCodInterno());
			columnas.add(dto.getNombre());
			columnas.add(dto.getCiudad());
			columnas.add(dto.getDepto());
			registros.add(columnas);*/
			
			List<Celda> registro = new ArrayList<Celda>();
			registro.add(new Celda(dto.getId()));
			registro.add(new Celda(dto.getCodInterno()));
			registro.add(new Celda(dto.getNombre()));
			registro.add(new Celda(dto.getCiudad()));
			registro.add(new Celda(dto.getDepto()));
			
			registro.add(new Celda(dto.getRegion()));
			registro.add(new Celda(dto.getMailPlan()));
			registro.add(new Celda(dto.getTipo()));
			registro.add(new Celda(dto.getZona()));
			registro.add(new Celda(dto.getReferencia()));
			registro.add(new Celda(dto.getDescripcion()));
			registro.add(new Celda(dto.getCantidad()));
			registro.add(new Celda(dto.getCampanaOrigen()));
			registro.add(new Celda(dto.getMotivo()));
			registro.add(new Celda(dto.getArea()));
			registro.add(new Celda(dto.getFechaRegistro()));
			registro.add(new Celda(dto.getCampanaReclamo()));
			registro.add(new Celda(dto.getDsEstado()));
			registro.add(new Celda(dto.getOrden()));
			registro.add(new Celda(dto.getSkuSustituto()));
			registro.add(new Celda(dto.getDsSkuSustituto()));
			registro.add(new Celda(dto.getCantidadEntregada()));
			registro.add(new Celda(dto.getTipoDespacho()));
			registro.add(new Celda(dto.getFechaAprobacion()));
			registro.add(new Celda(dto.getGuia()));
			registro.add(new Celda(dto.getTransportadora()));
			registro.add(new Celda(dto.getCedulaTransportista()));
			registro.add(new Celda(dto.getDireccionDespacho()));
			registro.add(new Celda(dto.getBarrioDespacho()));
			registro.add(new Celda(dto.getCiudadDespacho()));
			registro.add(new Celda(dto.getDeptoDespacho()));
			registro.add(new Celda(dto.getTel1Despacho()));
			registro.add(new Celda(dto.getTarifa()));
			registro.add(new Celda(dto.getFechaDespacho()));
			registros.add(registro);
		}

		excelDTO.setPintarNombre(true);
		excelDTO.setTitulos(titulos);	
		//excelDTO.setRegistros(registros);
		excelDTO.addRegistros(registros);
		return excelDTO;
	}

	private CasoDTO obtenerEnvio(HttpServletRequest req){

		CasoDTO casoDTO = new CasoDTO();
		int relleno;
		
		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		casoDTO.setCia(codCia);
		casoDTO.setCriterio(Constantes.CRITERIO_ENVIO_MANUAL);
		casoDTO.setSeleccion(req.getParameter("seleccionBuscar") != null ? req.getParameter("seleccionBuscar") : "");
		casoDTO.setValor(req.getParameter("opcionBuscar") != null ? req.getParameter("opcionBuscar") : "");
		casoDTO.setRefeSku(req.getParameter("refeBuscar") != null ? req.getParameter("refeBuscar") : "");
		casoDTO.setColorSku(req.getParameter("colorBuscar") != null ? req.getParameter("colorBuscar") : "");
		casoDTO.setTallaSku(req.getParameter("tallaBuscar") != null ? req.getParameter("tallaBuscar") : "");

		if(Constantes.SELECCION_TODO.equals(casoDTO.getSeleccion())){
			casoDTO.setValor("");
		}
		else if(Constantes.SELECCION_REFERENCIA.equals(casoDTO.getSeleccion())){
			StringBuffer refe = new StringBuffer(casoDTO.getRefeSku());
			if(refe.length()>=7){
				refe = new StringBuffer(refe.substring(0, 7));
			}
			else{
				relleno = 7 - refe.length();
				for (int i = 0; i < relleno; i++) {
					refe.append(" ");
				}
			}
			
			StringBuffer color = new StringBuffer(casoDTO.getColorSku());
			if(color.length()>=3){
				color = new StringBuffer(color.substring(0, 3));
			}
			else{
				relleno = 3 - color.length();
				for (int i = 0; i < relleno; i++) {
					color.insert(0, " ");
				}
			}

			StringBuffer talla = new StringBuffer(casoDTO.getTallaSku());
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
			casoDTO.setValor(sku.toString());
		}
		int paginaActual = req.getParameter("paginaActual") != null && !"".equals(req.getParameter("paginaActual")) ? Integer.parseInt(req.getParameter("paginaActual")) : 1;
		String registro = Configuracion.getInstance().getParametroApp("PAGINACION_CANT_REG");
		int incremento = registro != null ? Integer.parseInt(registro):30;
		int semilla = 0;
		if(paginaActual > 1){
			semilla = (paginaActual-1) * incremento;
		}
		else{
			paginaActual = 1;
		}
		casoDTO.setSemilla(semilla);
		casoDTO.setPaginaActual(paginaActual);
		casoDTO.setAccionExcel(excel);

		return casoDTO;
	}
	
}
