package votre.portaloperaciones.flujowms.cmd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import votre.portaloperaciones.flujowms.beans.FlujoWmsDTO;
import votre.portaloperaciones.flujowms.delegate.FlujoWmsBusiness;

import votre.portaloperaciones.util.Constantes;
import votre.utils.excel.write.Celda;
import votre.utils.excel.write.GeneradorExcel;
import votre.utils.excel.write.ReporteExcelDTO;



import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.Fecha;

public class FlujoWmsXls implements IBaseCmd{


	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		
	FlujoWmsDTO flujoWmsDTO = new FlujoWmsDTO();
	FlujoWmsBusiness flujoWmsBusiness = new FlujoWmsBusiness(); 
	
	String codCia;
	codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
	String idTipoFlujoB = req.getParameter("idTipoFlujo");	
	String idTipoFlujoBB ="";
	
	if(idTipoFlujoB.equals("1"))
	{
		idTipoFlujoBB = "3";
	}
	
	if(idTipoFlujoB.equals("2"))
	{
		idTipoFlujoBB = "4";
	}
	
	
	
	
	flujoWmsDTO.setIdCompania(codCia);
	flujoWmsDTO.setIdTipoFlujoWms(idTipoFlujoBB);
	String lxcTitulo = "";
	if(idTipoFlujoB.equals("1"))
	{
		lxcTitulo = "Flujo WMS -- Pedido Sin Consolidar";
	}
	
	if(idTipoFlujoB.equals("2"))
	{
		lxcTitulo = "Flujo WMS -- Pedidos Sin Embarcar";
	}	
   
	String descripcion = "";
	String enviarExcel = "S";
	
	ArrayList<FlujoWmsDTO> flujoWms = new ArrayList<FlujoWmsDTO>();
	flujoWms = flujoWmsBusiness.consultaFlujoWms(flujoWmsDTO);
	req.setAttribute("flujowms", flujoWms);	
	 if(enviarExcel != null && enviarExcel.equals("S")){	
		 
		 	ReporteExcelDTO excelDTO = new ReporteExcelDTO("Flujo WMS");	    	
			ArrayList<ArrayList<String>> cabeceras = new ArrayList<ArrayList<String>>();
			ArrayList<List<String>> registros = new ArrayList<List<String>>();
			ArrayList<String> titulosArch = new ArrayList<String>();
			ArrayList<List<Celda>> pies = new ArrayList<List<Celda>>();
		 
			
			//Cabeceras
			ArrayList<String> cabecera = new ArrayList<String>();
		
			cabecera = new ArrayList<String>();
			cabecera.add("Compañia: ");
			cabecera.add(codCia);			
			cabeceras.add(cabecera);
			
			cabecera = new ArrayList<String>();
			cabecera.add("");
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
			titulosArch.add("Retraso (Horas)");	
			titulosArch.add("Tipo Compradora");	
			titulosArch.add("Transportadora");	
			titulosArch.add("Zona");	
			titulosArch.add("Campaña");	
			titulosArch.add("Mail Plan");
			titulosArch.add("Región");
			
			
			
			
			//	 Registros
			
			String fechaActual = Fecha.getFechaServidor(Fecha.EXPR_YYYYMMDD_LINEA);
			String horaActual = Fecha.getFechaServidor(Fecha.EXPR_HHMMSS_DOS_PUNTOS);
			
			for (FlujoWmsDTO flujoDTO : flujoWms){
						
				
				List<String> columnas = new ArrayList<String>();
				columnas.add(flujoDTO.getOrden());
				columnas.add(flujoDTO.getEstado());
				columnas.add(flujoDTO.getPickt());
				columnas.add(flujoDTO.getFecha());
				columnas.add(flujoDTO.getHora());
				columnas.add(flujoDTO.getCedula());
				columnas.add(flujoDTO.getNombre());
				columnas.add(flujoDTO.getProceso());
				
				
				String fechahoraTabla = flujoDTO.getFecha()+" "+flujoDTO.getHora();
				String fechaHoraActual =  fechaActual+" "+horaActual;
				
				java.util.Date hora1;
				java.util.Date hora2;
				
				try {							
					hora1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fechahoraTabla);
					hora2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fechaHoraActual); 
					
					long lantes = hora1.getTime(); 
					long lahora = hora2.getTime(); 
					long diferencia = (lahora - lantes); 
					long totalHoras = (diferencia/3600000);
					String horaTotal = String.valueOf(totalHoras);					
					columnas.add(horaTotal);
				
				} catch (ParseException e) {
					e.printStackTrace();
				} 
				
				columnas.add(flujoDTO.getTipoCom());
				columnas.add(flujoDTO.getTranspo());
				columnas.add(flujoDTO.getZona());
				columnas.add(flujoDTO.getMp());	
				columnas.add(flujoDTO.getMailPlan());
				columnas.add(flujoDTO.getRegion());
				
				registros.add(columnas);
			}
			excelDTO.setCabeceras(cabeceras);	
			excelDTO.setRegistros(registros);
			excelDTO.setTitulos(titulosArch);
			excelDTO.addPies(pies);
			
			GeneradorExcel generadorExcel = new GeneradorExcel();	    				
			HSSFWorkbook workbook = generadorExcel.generarInforme(excelDTO);	
			
			String filenameXls = "";
			if(idTipoFlujoB.equals("1"))
			{
				filenameXls = "FlujoWmsSinConsolidar.xls";
			}
			
			if(idTipoFlujoB.equals("2"))
			{
				filenameXls = "FlujoWmsSinEmbarcar.xls";
			}	
			
			
		   	try {
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
