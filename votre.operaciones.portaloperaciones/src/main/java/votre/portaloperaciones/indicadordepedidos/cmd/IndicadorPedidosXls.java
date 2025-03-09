package votre.portaloperaciones.indicadordepedidos.cmd;


import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import votre.portaloperaciones.indicadordepedidos.beans.IndicadorPedidosDTO;
import votre.portaloperaciones.indicadordepedidos.delegate.IndicadorPedidosBusiness;
import votre.portaloperaciones.util.Constantes;
import votre.utils.excel.write.Celda;
import votre.utils.excel.write.GeneradorExcel2;
import votre.utils.excel.write.ReporteExcelDTO;




import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;


public class IndicadorPedidosXls implements IBaseCmd{


	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		
		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		String idOpcSelect = (String) req.getSession().getAttribute("idOpcSelect");
		String idCampana = (String) req.getSession().getAttribute("idCampana");
		String idZona = (String) req.getSession().getAttribute("idZona");
		String idCampanaZ = (String) req.getSession().getAttribute("idCampanaZ");
		String idFechaInicial = (String) req.getSession().getAttribute("idFechaInicial");
		String idFechaFinal = (String) req.getSession().getAttribute("idFechaFinal");
		String idEstado = (String) req.getSession().getAttribute("idEstado");
		String idEstadoExcel = req.getParameter("idEstadoIni");
		String tipoCompradora = (String) req.getSession().getAttribute("tipoCompradora");
		String campanaCompra = (String) req.getSession().getAttribute("campanaCompra");
		String idEstadoInicial = "";				
		String idEstadoFinal = "";
		
		if(idEstadoExcel.equals("1")){
			idEstadoInicial = (String) req.getSession().getAttribute("idEstadoIni"); 						
			idEstadoFinal = (String) req.getSession().getAttribute("idEstadoFin"); 
			if(idEstadoInicial.equals("TODOS")){
				idEstadoInicial = "";
			}
			if(idEstadoFinal.equals("TODOS")){
				idEstadoFinal = "";
			}
		}
		if(idEstadoExcel.equals("2")){
			idEstadoInicial = (String) req.getSession().getAttribute("idEstadoIni"); 						
			idEstadoFinal = "";
			
			if(idEstadoInicial.equals("TODOS")){
				idEstadoInicial = "";
			}
		}
		if(idEstado == null){
			idEstado = "";
		}
	
		String idCampanaR = (String) req.getSession().getAttribute("idCampanaR");
		String idRegion = (String) req.getSession().getAttribute("idRegion");		
		IndicadorPedidosDTO indicadorPedidosDTO = new IndicadorPedidosDTO();
		IndicadorPedidosBusiness indicadorPedidosBusiness;
		indicadorPedidosBusiness = new IndicadorPedidosBusiness();
		
		indicadorPedidosDTO.setCodCia(codCia);
		
		if(idOpcSelect != null){
			if(idOpcSelect.equals("1")){
				indicadorPedidosDTO.setAccion("F");
				indicadorPedidosDTO.setEstadoIni(idEstadoInicial);
				indicadorPedidosDTO.setEstadoFin(idEstadoFinal);
				indicadorPedidosDTO.setDatoConsulta1(idFechaInicial);
				indicadorPedidosDTO.setDatoConsulta2(idFechaFinal);	
				indicadorPedidosDTO.setDato6("S");
			}
			if(idOpcSelect.equals("2")){
				indicadorPedidosDTO.setAccion("Z");
				indicadorPedidosDTO.setEstadoIni(idEstadoInicial);
				indicadorPedidosDTO.setEstadoFin(idEstadoFinal);
				indicadorPedidosDTO.setDatoConsulta1(idCampanaZ);
				indicadorPedidosDTO.setDatoConsulta2(idZona);
				indicadorPedidosDTO.setDato6("S");
			}
			if(idOpcSelect.equals("3")){
				indicadorPedidosDTO.setAccion("C");
				indicadorPedidosDTO.setEstadoIni(idEstadoInicial);
				indicadorPedidosDTO.setEstadoFin(idEstadoFinal);
				indicadorPedidosDTO.setDatoConsulta1(idCampana);
				indicadorPedidosDTO.setDatoConsulta2("");
				indicadorPedidosDTO.setDato6("S");
			}
			if(idOpcSelect.equals("4")){
				indicadorPedidosDTO.setAccion("R");
				indicadorPedidosDTO.setEstadoIni(idEstadoInicial);
				indicadorPedidosDTO.setEstadoFin(idEstadoFinal);
				indicadorPedidosDTO.setDatoConsulta1(idCampanaR);
				indicadorPedidosDTO.setDatoConsulta2(idRegion);
				indicadorPedidosDTO.setDato6("S");
			}
			if(idOpcSelect.equals("5")){
				indicadorPedidosDTO.setAccion("V");
				indicadorPedidosDTO.setEstadoIni(idEstadoInicial);
				indicadorPedidosDTO.setEstadoFin(idEstadoFinal);
				indicadorPedidosDTO.setDatoConsulta1(campanaCompra);
				indicadorPedidosDTO.setDatoConsulta2(tipoCompradora);
				indicadorPedidosDTO.setDato6("S");
			}
			
			ArrayList<IndicadorPedidosDTO>  indicadorPedidosGrafica = new ArrayList<IndicadorPedidosDTO>();
			indicadorPedidosGrafica = indicadorPedidosBusiness.IndicadorPedidosGraf1(indicadorPedidosDTO);
			req.setAttribute("indicadorPedidos", indicadorPedidosGrafica);
	
			String descripcion = "";
			String enviarExcel = "S";
			
			String lxcTitulo = "";
			lxcTitulo = "Indicador De Pedidos";
			if(enviarExcel != null && enviarExcel.equals("S")){	
			 	ReporteExcelDTO excelDTO = new ReporteExcelDTO("Indicador De Pedidos");	    	
				ArrayList<ArrayList<String>> cabeceras = new ArrayList<ArrayList<String>>();
				ArrayList<List<String>> registros = new ArrayList<List<String>>();
				ArrayList<String> titulosArch = new ArrayList<String>();
				ArrayList<List<Celda>> pies = new ArrayList<List<Celda>>();
			
				//Cabeceras
				ArrayList<String> cabecera = new ArrayList<String>();
			
				cabecera = new ArrayList<String>();
				cabecera.add("Campaña: ");
				if(idCampana.equals("")){
					cabecera.add(idCampanaZ);				
				}	
				else{
					cabecera.add(idCampana);
				}
				if(!campanaCompra.equals("")){
					cabecera.add(campanaCompra);
				}
				cabeceras.add(cabecera);
			
				cabecera = new ArrayList<String>();
				cabecera.add("Zona: ");
				cabecera.add(idZona);
				cabeceras.add(cabecera);			
			
				cabecera = new ArrayList<String>();
				cabecera.add("Fecha Inicial: ");
				cabecera.add(idFechaInicial);
				cabeceras.add(cabecera);	
				
				cabecera = new ArrayList<String>();
				cabecera.add("Fecha Final: ");
				cabecera.add(idFechaFinal);
				cabeceras.add(cabecera);		
			
				cabecera = new ArrayList<String>();
				cabecera.add("");
				cabecera.add("");
			
				cabecera.add(lxcTitulo);
				cabecera.add(descripcion);
				cabeceras.add(cabecera);
			
				//Titulos			
				titulosArch.add("Numero orden");
				titulosArch.add("Ult.Estado");
				titulosArch.add("PickTicket");
				titulosArch.add("Fecha estado");
				titulosArch.add("Hora estado");
				titulosArch.add("Cédula");
				titulosArch.add("Nombre compradora");
				titulosArch.add("Proceso");
				titulosArch.add("Tipo Compradora");	
				titulosArch.add("Transportadora");	
				titulosArch.add("Zona");	
				titulosArch.add("Campaña");	
				titulosArch.add("Mail Plan");
				titulosArch.add("Región");		
				titulosArch.add("Tiempo");	
			
				//Registros
				for (IndicadorPedidosDTO indicadorPediDTO : indicadorPedidosGrafica){
					List<String> columnas = new ArrayList<String>();
					columnas.add(indicadorPediDTO.getDato15());
					columnas.add(indicadorPediDTO.getDato16());
					columnas.add(indicadorPediDTO.getDato17());
					columnas.add(indicadorPediDTO.getDato18());
					columnas.add(indicadorPediDTO.getDato19());
					columnas.add(indicadorPediDTO.getDato20());
					columnas.add(indicadorPediDTO.getDato21());
					columnas.add(indicadorPediDTO.getDato22());
					columnas.add(indicadorPediDTO.getDato23());
					columnas.add(indicadorPediDTO.getDato24());
					columnas.add(indicadorPediDTO.getDato25());
					columnas.add(indicadorPediDTO.getDato26());
					columnas.add(indicadorPediDTO.getDato27());
					columnas.add(indicadorPediDTO.getDato28());
					columnas.add(indicadorPediDTO.getDato29());
					registros.add(columnas);
				}
				excelDTO.setCabeceras(cabeceras);	
				excelDTO.setRegistros(registros);
				excelDTO.setTitulos(titulosArch);
				excelDTO.addPies(pies);
				
				GeneradorExcel2 generadorExcel = new GeneradorExcel2();	    				
				HSSFWorkbook workbook = generadorExcel.generarInforme(excelDTO);	
				
				String filenameXls = "";
	     		filenameXls = "IndicadorPedidos.xls";
	     		try{
		    		res.setHeader("Content-Disposition", "attachment; filename="+filenameXls+"");
			        ServletOutputStream out = res.getOutputStream();
			        workbook.write(out);
			        out.flush();
			        out.close();	
		    	}catch (Exception e) {
		    
		    	}
			}
		}
	}
}