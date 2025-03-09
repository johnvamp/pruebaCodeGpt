package votre.portaloperaciones.embarqueinternacional.reimprimir.cmd;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import votre.portaloperaciones.embarqueinternacional.reimprimir.beans.DetalleReimprimirDTO;
import votre.portaloperaciones.embarqueinternacional.reimprimir.beans.ReimprimirDTO;
import votre.portaloperaciones.embarqueinternacional.reimprimir.delegate.ReimprimirBusiness;
import votre.portaloperaciones.util.Constantes;
import votre.utils.excel.write.GeneradorExcel;
import votre.utils.excel.write.ReporteExcelDTO;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class GenerarExcelCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String codTransportador;
	private String camion;
	private String fecha;
	private String accion;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		ReimprimirBusiness reimprimirBusiness = null;
		ReimprimirDTO reimprimirDTO = new ReimprimirDTO();
		try{
			reimprimirBusiness = new ReimprimirBusiness();
			obtenerDatos(req);
			dtoAssembler(reimprimirDTO);
			
			reimprimirDTO = reimprimirBusiness.reimprimirEmbarque(reimprimirDTO);
			if(reimprimirDTO != null){
				if(reimprimirDTO.getEstado().equals(Constantes.EXITO_SP)){
					String titulos[] = reimprimirDTO.getValorConcatenado().split("\\|\\|");
					if(titulos != null){
						reimprimirDTO.setTitObservacion(titulos[0]);
						reimprimirDTO.setTitVdeclarado(titulos[1]);
					}
					ReporteExcelDTO reporteExcelDTO = generarExcelDTO(reimprimirDTO);
					GeneradorExcel generadorExcel = new GeneradorExcel();
					HSSFWorkbook workbook = generadorExcel.generarInforme(reporteExcelDTO);
					res.setHeader("Content-Disposition", "attachment; filename=DetalleEmbarque.xls");
			        ServletOutputStream out = res.getOutputStream();
			        workbook.write(out);
			        out.flush();
			        out.close();
				}
			}
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}
	
	private void obtenerDatos(HttpServletRequest req){
		codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		codTransportador = req.getParameter("codTransportador") != null ? req.getParameter("codTransportador") : "0";
		camion = req.getParameter("camion") != null ? req.getParameter("camion") : "";
		fecha = req.getParameter("fecha") != null ? req.getParameter("fecha") : "";
		accion = req.getParameter("nomAccion") != null ? req.getParameter("nomAccion") : "";	
	}
	
	private void dtoAssembler(final ReimprimirDTO reimprimirDTO){
		reimprimirDTO.setCodCia(codCia);
		reimprimirDTO.setAccion(Constantes.ACCION_REIMPRIMIR);
		reimprimirDTO.setCodTransportador(codTransportador);
		reimprimirDTO.setCamion(camion);
		reimprimirDTO.setFecha(fecha);
	}
	
	private ReporteExcelDTO generarExcelDTO(ReimprimirDTO reimprimirDTO) throws Exception{
		ReporteExcelDTO excelDTO = new ReporteExcelDTO("");
		
		ArrayList<List<String>> registros = new ArrayList<List<String>>();
		ArrayList<String> titulos = new ArrayList<String>();
		ArrayList<ArrayList<String>>  cabeceras = new ArrayList<ArrayList<String>>();
		ArrayList<List<String>> pies = new ArrayList<List<String>>();
		
		// Cabeceras
		ArrayList<String> cabecera;
		
		cabecera = new ArrayList<String>();
		cabecera.add(reimprimirDTO.getTitCompraDirecta());
		cabeceras.add(cabecera);
		
		cabecera = new ArrayList<String>();
		cabecera.add(reimprimirDTO.getTitRelacionEmbarque());
		cabeceras.add(cabecera);
		
		cabecera = new ArrayList<String>();
		cabecera.add(reimprimirDTO.getTitTransportador()+":");
		cabecera.add(reimprimirDTO.getTransportador());
		cabeceras.add(cabecera);
		
		cabecera = new ArrayList<String>();
		String[] camion = reimprimirDTO.getTitCamionEnc().split(":");
		cabecera.add(camion[0]+":");
		cabecera.add(camion[1]);
		cabeceras.add(cabecera);
		
		cabecera = new ArrayList<String>();
		String[] fecha = reimprimirDTO.getTitFechaEnc().split(":");
		cabecera.add(fecha[0]+":");
		cabecera.add(fecha[1]);
		cabeceras.add(cabecera);
		
		//Titulos
		titulos.add(reimprimirDTO.getTitObservacion());
		titulos.add(reimprimirDTO.getTitNroOrden());
		titulos.add(reimprimirDTO.getTitZona());
		titulos.add(reimprimirDTO.getTitCedula());
		titulos.add(reimprimirDTO.getTitNombre());
		titulos.add(reimprimirDTO.getTitDireccion());
		titulos.add(reimprimirDTO.getTitTelefono());
		titulos.add(reimprimirDTO.getTitDestino());
		titulos.add(reimprimirDTO.getTitVdeclarado());
		titulos.add(reimprimirDTO.getTitCampana());
		titulos.add(reimprimirDTO.getTitPorteria());
		titulos.add(reimprimirDTO.getTitTelefono2());
		titulos.add(reimprimirDTO.getTitCelular());
		titulos.add(reimprimirDTO.getTitDistrito());
		titulos.add(reimprimirDTO.getTitCanton());
		titulos.add(reimprimirDTO.getTitProvincia());
		titulos.add(reimprimirDTO.getTitValorOrden());
		
		//Registros
		for(DetalleReimprimirDTO reg : reimprimirDTO.getDetalle()){
			if (reg == null)
				continue;
			List<String> registro = new ArrayList<String>();
			registro.add(reg.getObservacion());
			registro.add(reg.getNumOrden());
			registro.add(reg.getZona());
			registro.add(reg.getCedula());
			registro.add(reg.getNombre());
			registro.add(reg.getDireccion());
			registro.add(reg.getTelefono());
			registro.add(reg.getDestino());
			registro.add(reg.getValorDeclarado());
			registro.add(reg.getCampana());
			registro.add(reg.getPorteria());
			registro.add(reg.getTelefono2());
			registro.add(reg.getCelular());
			registro.add(reg.getDistrito());
			registro.add(reg.getCanton());
			registro.add(reg.getProvincia());
			registro.add(reg.getValorOrden());
			
			registros.add(registro);
		}
		
		//Pies
		ArrayList<String> pie;
		
		pie = new ArrayList<String>();
		String[] total = reimprimirDTO.getTitTotal().split(":");
		pie.add("Total Guias:");
		pie.add(total[1]);
		pies.add(pie);
		
		if(accion.equals("cerrarEmbarque")){
			excelDTO.setNombreReporte("Cerrar Camión");
		}
		else{
			excelDTO.setNombreReporte(reimprimirDTO.getTitReimpresion());
		}
		excelDTO.setPintarNombre(true);
		excelDTO.setTitulos(titulos);
		excelDTO.setCabeceras(cabeceras);		
		excelDTO.setRegistros(registros);
		excelDTO.setPies(pies);
		
		return excelDTO;
	}

}
