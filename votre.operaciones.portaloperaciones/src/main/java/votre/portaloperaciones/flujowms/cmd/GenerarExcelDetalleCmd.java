package votre.portaloperaciones.flujowms.cmd;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import votre.portaloperaciones.flujowms.beans.AlertasDTO;
import votre.portaloperaciones.flujowms.delegate.FlujoWmsBusiness;
import votre.portaloperaciones.util.Constantes;
import votre.utils.excel.write.Celda;
import votre.utils.excel.write.GeneradorExcel;
import votre.utils.excel.write.ReporteExcelDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class GenerarExcelDetalleCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private String nroEstado;
	private String tituloEstado;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		AlertasDTO alertasDTO = null;
		FlujoWmsBusiness flujoWmsBusiness = null;
		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		nroEstado = req.getParameter("nroEstado") != null ? req.getParameter("nroEstado") : "";
		tituloEstado = req.getParameter("tituloEstado") != null ? req.getParameter("tituloEstado") : "";
		try{
			alertasDTO = new AlertasDTO();
			flujoWmsBusiness = new FlujoWmsBusiness();
			
			alertasDTO.setCodCia(codCia);
			alertasDTO.setNroEstado(nroEstado);
			
			alertasDTO = flujoWmsBusiness.consultarDetalleEstado(alertasDTO);
			if(alertasDTO != null){
				if(!alertasDTO.getEstado().equals(Constantes.ERROR_SP)){
					ReporteExcelDTO reporteExcelDTO = generarExcelDTO(alertasDTO);
					GeneradorExcel generadorExcel = new GeneradorExcel();
					HSSFWorkbook workbook = generadorExcel.generarInforme(reporteExcelDTO);
					res.setHeader("Content-Disposition", "attachment; filename=Detalle-Estados.xls");
			        ServletOutputStream out = res.getOutputStream();
			        workbook.write(out);
			        out.flush();
			        out.close();
				}
			}
			
		}
		catch(Exception e){
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}
	
	private ReporteExcelDTO generarExcelDTO(AlertasDTO reporte) throws Exception{
		ReporteExcelDTO excelDTO = new ReporteExcelDTO("");
		
		ArrayList<List<Celda>> registros = new ArrayList<List<Celda>>();
		ArrayList<String> titulos = new ArrayList<String>();
		
		//Titulos
		titulos.add("REGION");
		titulos.add("MAIL PLAN");
		titulos.add("ZONA");
		titulos.add("CAMPAÑA");
		if(nroEstado.equals(Constantes.CODIGO_PEDIDO_ENVIADO_WMS) || nroEstado.equals(Constantes.CODIGO_PEDIDO_CONSOLIDADO)){
			titulos.add("DEPARTAMENTO");
			titulos.add("CIUDAD");
		}
		titulos.add("CEDULA");
		titulos.add("NOMBRE");
		if(nroEstado.equals(Constantes.CODIGO_PEDIDO_ENVIADO)){
			titulos.add("TIPO TRANSMISION");
			titulos.add("FECHA TRANSMISION");
			titulos.add("HORA TRASMISION");
		}
		if(nroEstado.equals(Constantes.CODIGO_PEDIDO_SUBIDO)){
			titulos.add("# DE ORDEN");
			titulos.add("CONCEPTO DE RETENCION");
			titulos.add("DIA DE CONFERENCIA OFICIAL");
		}
		if(nroEstado.equals(Constantes.CODIGO_PEDIDO_LIBERADO)){
			titulos.add("# DE ORDEN");
			titulos.add("FECHA DE LIBERACION");
			titulos.add("HORA DE LIBERACION");
		}
		if(nroEstado.equals(Constantes.CODIGO_PEDIDO_ASIGNADO)){
			titulos.add("# DE ORDEN");
			titulos.add("FECHA DE LIBERACION");
			titulos.add("HORA DE LIBERACION");
			titulos.add("FECHA DE ASIGNACION");
			titulos.add("HORA DE ASIGNACION");
		}
		if(nroEstado.equals(Constantes.CODIGO_PEDIDO_ENVIADO_WMS)){
			titulos.add("# DE ORDEN");
			titulos.add("# DE PICK TICKET");
			titulos.add("ESTADO ACTUAL PICK TICKET");
			titulos.add("CANTIDAD DE CARTONES");
			titulos.add("HORAS RETRASO");
		}
		if(nroEstado.equals(Constantes.CODIGO_PEDIDO_CONSOLIDADO)){
			titulos.add("# DE ORDEN");
			titulos.add("# DE PICK TICKET");
			titulos.add("TRANSPORTISTA");
			titulos.add("FECHA DE CONSOLIDACION");
			titulos.add("HORA DE CONSOLIDACION");
			titulos.add("HORAS RETRASO");
		}
		
		//Registros
		for (AlertasDTO reg : reporte.getDetalle()) {
			if (reg == null)
				continue;
			List<Celda> registro = new ArrayList<Celda>();
			registro.add(new Celda(reg.getRegion()));
			registro.add(new Celda(reg.getMainPlan()));
			registro.add(new Celda(reg.getZona()));
			registro.add(new Celda(reg.getCampana()));
			if(nroEstado.equals(Constantes.CODIGO_PEDIDO_ENVIADO_WMS) || nroEstado.equals(Constantes.CODIGO_PEDIDO_CONSOLIDADO)){
				registro.add(new Celda(reg.getDepartamento()));
				registro.add(new Celda(reg.getCiudad()));
			}
			registro.add(new Celda(reg.getCedula()));
			registro.add(new Celda(reg.getNombre()));
			if(nroEstado.equals(Constantes.CODIGO_PEDIDO_ENVIADO)){
				registro.add(new Celda(reg.getTransmision()));
				registro.add(new Celda(reg.getFechaTrans()));
				registro.add(new Celda(reg.getHoraTrans()));
			}
			if(nroEstado.equals(Constantes.CODIGO_PEDIDO_SUBIDO)){
				registro.add(new Celda(reg.getNroOrden()));
				registro.add(new Celda(reg.getConceptoRetencion()));
				registro.add(new Celda(reg.getFechaConferencia()));
			}
			if(nroEstado.equals(Constantes.CODIGO_PEDIDO_LIBERADO)){
				registro.add(new Celda(reg.getNroOrden()));
				registro.add(new Celda(reg.getFechaLiberacion()));
				registro.add(new Celda(reg.getHoraLiberacion()));
			}
			if(nroEstado.equals(Constantes.CODIGO_PEDIDO_ASIGNADO)){
				registro.add(new Celda(reg.getNroOrden()));
				registro.add(new Celda(reg.getFechaLiberacion()));
				registro.add(new Celda(reg.getHoraLiberacion()));
				registro.add(new Celda(reg.getFechaAsignacion()));
				registro.add(new Celda(reg.getHoraAsignacion()));
			}
			if(nroEstado.equals(Constantes.CODIGO_PEDIDO_ENVIADO_WMS)){
				registro.add(new Celda(reg.getNroOrden()));
				registro.add(new Celda(reg.getPickTicket()));
				registro.add(new Celda(reg.getEstadoPickTicket()));
				registro.add(new Celda(reg.getNroCajas()));
				registro.add(new Celda(reg.getHoraRetrasoWms()));
			}
			if(nroEstado.equals(Constantes.CODIGO_PEDIDO_CONSOLIDADO)){
				registro.add(new Celda(reg.getNroOrden()));
				registro.add(new Celda(reg.getPickTicket()));
				registro.add(new Celda(reg.getTransportista()));
				registro.add(new Celda(reg.getFechaConsolidacion()));
				registro.add(new Celda(reg.getHoraConsolidacion()));
				registro.add(new Celda(reg.getHoraRetrasoConsolidado()));
			}
			registros.add(registro);
		}
		excelDTO.setNombreReporte("DETALLE " + tituloEstado);
		excelDTO.setPintarNombre(true);
		excelDTO.setTitulos(titulos);
		excelDTO.addRegistros(registros);
		
		return excelDTO;
	}

}
