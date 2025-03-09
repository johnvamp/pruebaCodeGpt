package votre.portaloperaciones.despachocatalogo.reportes.cmd;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.utils.excel.write.GeneradorExcel;
import votre.utils.excel.write.ReporteExcelDTO;
import votre.utils.excel.write.Celda;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import votre.portaloperaciones.despachocatalogo.reportes.beans.InformacionDTO;
import votre.portaloperaciones.despachocatalogo.reportes.beans.ReportesDTO;
import votre.portaloperaciones.despachocatalogo.reportes.beans.DetalleReportesDTO;
import votre.portaloperaciones.despachocatalogo.reportes.delegate.ReportesBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class GenerarReportesCmd implements IBaseCmd {
	private Logger logger = Logger.getLogger(this.getClass());
	PersonalsoftException ps = null;
	
	private String parametro;
	private String codCia;
	private String codigoLabel;
	private String fechaIni;
	private String fechaFin;
	private String usuario;
	private String zona;
	private String campana;
	private String titulo;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		ReportesBusiness reportesBusiness = new ReportesBusiness();
		InformacionDTO informacion = new InformacionDTO();
		ReportesDTO reporte = new ReportesDTO();
		try{
			req.getSession().setAttribute(Constantes.OBJETO_BANDERA_EXPORTAR, new Boolean(false));
			obtenerDatos(req);
			dtoAssembler(informacion,reporte);
			if(parametro.equals("generarExcel")){
				reporte = reportesBusiness.generarExcel(informacion, reporte);
				if(reporte != null){
					if(reporte.getEstado().equals(Constantes.EXITO_SP)){						
						ReporteExcelDTO reporteExcelDTO = generarExcelDTO(reporte);
						GeneradorExcel generadorExcel = new GeneradorExcel();
						HSSFWorkbook workbook = generadorExcel.generarInforme(reporteExcelDTO);
						res.setHeader("Content-Disposition", "attachment; filename=RelacionDespacho"+reporte.getCodCia()+".xls");
				        ServletOutputStream out = res.getOutputStream();
				        workbook.write(out);
				        out.flush();
				        out.close();
					}
				}
			}
			if(parametro.equals("generarLabel")){
				reporte = reportesBusiness.generarLabel(reporte);
				if(reporte != null){
					if(reporte.getEstado().equals(Constantes.EXITO_SP)){
						ReporteExcelDTO reporteLabelDTO = generarLabelDTO(reporte);
						GeneradorExcel generadorExcel = new GeneradorExcel();
						HSSFWorkbook workbook = generadorExcel.generarInforme(reporteLabelDTO);						
						res.setHeader("Content-Disposition", "attachment; filename=RelacionEnvio"+reporte.getCodCia()+".xls");
				        ServletOutputStream out = res.getOutputStream();
				        workbook.write(out);
				        out.flush();
				        out.close();
					}
				}
			}
			req.getSession().setAttribute(Constantes.OBJETO_BANDERA_EXPORTAR, new Boolean(true));
		}
		catch (Exception e) {
			req.getSession().setAttribute(Constantes.OBJETO_BANDERA_EXPORTAR, new Boolean(true));
			ps = new PersonalsoftException(e);
			logger.error(ps.getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
			throw ps;
		}
		
	}
	
	public void obtenerDatos(HttpServletRequest req){
		parametro = req.getParameter("parametro");
		codCia = req.getParameter("codCia");
		codigoLabel = req.getParameter("codigoLabel");
		fechaIni = req.getParameter("fechaIni") != null ? req.getParameter("fechaIni") : "";
		fechaFin = req.getParameter("fechaFin") != null ? req.getParameter("fechaFin") : "";
		usuario = req.getParameter("usuario");
		zona = req.getParameter("zona") != null && !"0".equals(req.getParameter("zona")) ? req.getParameter("zona") : "";
		campana= req.getParameter("txtCampana") != null ? req.getParameter("txtCampana") : "";
		titulo = req.getParameter("titulo") != null ? req.getParameter("titulo") : "";
	}
	
	private void dtoAssembler(final InformacionDTO informacion,ReportesDTO reporte){
		informacion.setCodCia(codCia);
		informacion.setCodigoLabel(codigoLabel);		
		informacion.setAccion(Constantes.ACCION_GENERAR);
		informacion.setFechaIni(fechaIni);
		informacion.setFechaFin(fechaFin);
		if(usuario.length() > 10){
			informacion.setUsuario(usuario.substring(0, 10));
			reporte.setUsuario(usuario.substring(0, 10));
		}
		else{
			informacion.setUsuario(usuario);
			reporte.setUsuario(usuario);
		}
		informacion.setZona(zona);
		informacion.setCampana(campana);
		
		reporte.setCodCia(codCia);
		reporte.setCodigoLabel(codigoLabel);
		if(parametro.equals("generarExcel")){
			reporte.setAccion(Constantes.ACCION_GENERAR);
		}
		if(parametro.equals("generarLabel")){
			reporte.setAccion(Constantes.ACCION_GENERAR_LABEL);
		}
		reporte.setZona(zona);
		reporte.setCampana(campana);
	}
	
	private ReporteExcelDTO generarExcelDTO(ReportesDTO reporte) throws Exception{
		ReporteExcelDTO excelDTO = new ReporteExcelDTO("");
		
		ArrayList<List<Celda>> registros = new ArrayList<List<Celda>>();
		ArrayList<String> titulos = new ArrayList<String>();
		
		//Titulos
		if(reporte.getCodCia().equals(Constantes.CHILE) && (reporte.getCodigoLabel().equals("2") || reporte.getCodigoLabel().equals("3"))){
			titulos.add(reporte.getTituloCedula());
			titulos.add(reporte.getTituloNombre());
			titulos.add(reporte.getTituloDireccion());
			titulos.add(reporte.getTituloTelefono1());
			titulos.add(reporte.getTituloTelefono2());
			titulos.add(reporte.getTituloBarrio());
			titulos.add(reporte.getTituloCiudad());
			titulos.add(reporte.getTituloDepartamento());
			titulos.add(reporte.getTituloCodPostal());
			titulos.add(reporte.getTituloNroCatalogos());
			titulos.add(reporte.getTituloTipo());
		}
		else{
			titulos.add(reporte.getTituloCedula());
			titulos.add(reporte.getTituloNombre());
			titulos.add(reporte.getTituloDireccion());
			titulos.add(reporte.getTituloTelefono1());
			titulos.add(reporte.getTituloTelefono2());
			if(reporte.getCodigoLabel().equals(Constantes.COD_LABEL_CORTE_CATALOGOS) || reporte.getCodigoLabel().equals(Constantes.COD_LABEL_COMPRADORAS_ACTIVAS)
					|| reporte.getCodigoLabel().equals(Constantes.COD_LABEL_ENVIO)){
				if(Constantes.CODCIA_USA.equals(reporte.getCodCia()) || Constantes.CODCIA_MEXICO.equals(reporte.getCodCia()) || Constantes.CODCIA_PUERTORICO.equals(reporte.getCodCia())){
					titulos.add("Celular");
				}
				else{
					titulos.add(reporte.getTituloCelular());
				}
			}
			titulos.add(reporte.getTituloBarrio());
			titulos.add(reporte.getTituloCiudad());
			titulos.add(reporte.getTituloDepartamento());
			titulos.add(reporte.getTituloCodPostal());
			titulos.add(reporte.getTituloNroCatalogos());
			titulos.add(reporte.getTituloTipo());
			titulos.add(reporte.getTituloZona());
			titulos.add(reporte.getTituloMailPlan());
			titulos.add(reporte.getTituloTipoCompra());
			if(reporte.getCodigoLabel().equals(Constantes.COD_LABEL_ENVIO)){
				titulos.add(reporte.getTituloObservacion());
				if(Constantes.CODCIA_USA.equals(reporte.getCodCia())){
					titulos.add(reporte.getTituloEmail());
					titulos.add(reporte.getTituloFechaInscripcion());
					titulos.add(reporte.getTituloIdioma());
				}
			}
		}
		
		//Registros
		for(DetalleReportesDTO reg : reporte.getDetalle()){
			if (reg == null)
				continue;
			List<Celda> registro = new ArrayList<Celda>();
			if(reporte.getCodCia().equals(Constantes.CHILE) && (reporte.getCodigoLabel().equals("2") || reporte.getCodigoLabel().equals("3"))){
				registro.add(new Celda(reg.getCedula()));
				registro.add(new Celda(reg.getNombre()));
				registro.add(new Celda(reg.getDireccion()));
				registro.add(new Celda(reg.getTelefono1(),true));
				registro.add(new Celda(reg.getTelefono2(),true));
				registro.add(new Celda(reg.getBarrio()));
				registro.add(new Celda(reg.getCiudad()));
				registro.add(new Celda(reg.getDepartamento()));
				registro.add(new Celda(reg.getCodPostal()));
				registro.add(new Celda(reg.getNroCatalogos(),true));
				registro.add(new Celda(reg.getTipo()));
			}
			else{
				registro.add(new Celda(reg.getCedula()));
				registro.add(new Celda(reg.getNombre()));
				registro.add(new Celda(reg.getDireccion()));
				registro.add(new Celda(reg.getTelefono1(),true));
				registro.add(new Celda(reg.getTelefono2(),true));
				if(reporte.getCodigoLabel().equals(Constantes.COD_LABEL_CORTE_CATALOGOS) || reporte.getCodigoLabel().equals(Constantes.COD_LABEL_COMPRADORAS_ACTIVAS)
						|| reporte.getCodigoLabel().equals(Constantes.COD_LABEL_ENVIO)){
					registro.add(new Celda(reg.getCelular(),true));
				}
				registro.add(new Celda(reg.getBarrio()));
				registro.add(new Celda(reg.getCiudad()));
				registro.add(new Celda(reg.getDepartamento()));
				registro.add(new Celda(reg.getCodPostal()));
				registro.add(new Celda(reg.getNroCatalogos(),true));
				registro.add(new Celda(reg.getTipo()));
				registro.add(new Celda(reg.getZona(),true));
				registro.add(new Celda(reg.getMailPlan()));
				registro.add(new Celda(reg.getTipoCompra()));
				if(reporte.getCodigoLabel().equals(Constantes.COD_LABEL_ENVIO)){
					registro.add(new Celda(reg.getObservacion()));
					if(Constantes.CODCIA_USA.equals(reporte.getCodCia())){
						registro.add(new Celda(reg.getEmail()));
						registro.add(new Celda(reg.getFechaInscripcion()));
						registro.add(new Celda(reg.getIdioma()));
					}
				}
			}
			registros.add(registro);
		}
		excelDTO.setNombreReporte(titulo);
		excelDTO.setPintarNombre(true);
		excelDTO.setTitulos(titulos);
		excelDTO.addRegistros(registros);
		
		return excelDTO;
	}
	
	private ReporteExcelDTO generarLabelDTO(ReportesDTO reporte) throws Exception{
		ReporteExcelDTO excelDTO = new ReporteExcelDTO("");
		
		ArrayList<List<Celda>> registros = new ArrayList<List<Celda>>();
		ArrayList<String> titulos = new ArrayList<String>();
		
		//Titulos
		titulos.add(reporte.getTituloCedula());
		titulos.add(reporte.getTituloNombre());
		titulos.add(reporte.getTituloDireccion());
		titulos.add(reporte.getTituloTelefono1());
		if(reporte.getCodCia().equals("500") || reporte.getCodCia().equals("100")){
			titulos.add(reporte.getTituloTelefono2());
			titulos.add(reporte.getTituloBarrio());
		}
		
		//Registros
		for(DetalleReportesDTO reg : reporte.getDetalle()){
			if (reg == null)
				continue;
			List<Celda> registro = new ArrayList<Celda>();
			registro.add(new Celda(reg.getCedula()));
			registro.add(new Celda(reg.getNombre()));
			registro.add(new Celda(reg.getDireccion()));
			registro.add(new Celda(reg.getTelefono1(),true));
			if(reporte.getCodCia().equals("500") || reporte.getCodCia().equals("100")){
				registro.add(new Celda(reg.getTelefono2(),true));
				registro.add(new Celda(reg.getBarrio()));
			}
			registros.add(registro);
		}
		excelDTO.setNombreReporte(titulo);
		excelDTO.setPintarNombre(true);
		excelDTO.setTitulos(titulos);	
		excelDTO.addRegistros(registros);
		
		return excelDTO;
	}
}
