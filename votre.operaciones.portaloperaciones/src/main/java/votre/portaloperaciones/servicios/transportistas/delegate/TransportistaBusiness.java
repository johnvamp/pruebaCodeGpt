package votre.portaloperaciones.servicios.transportistas.delegate;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

//import votre.genericos.email.delegate.EmailBusinessProxy;
import votre.portaloperaciones.servicios.transportistas.beans.EstructuraArchivoDTO;
import votre.portaloperaciones.servicios.transportistas.beans.RegistroDTO;
import votre.portaloperaciones.servicios.transportistas.beans.TransportistaDTO;
//import votre.portaloperaciones.servicios.transportistas.facade.TransportistaFacade;
import votre.portaloperaciones.util.Constantes;
import votre.utils.excel.write.GeneradorExcel;
import votre.utils.excel.write.ReporteExcelDTO;
//import votre.utils.files.FileManager;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class TransportistaBusiness {
	
	Logger logger = Logger.getLogger(TransportistaBusiness.class);
	
//	public String enviarEmailTransportistas(String query, String nombreArchivo, String biblioteca, String tipoArchivo, String nombreReporte, String delimitador, String imprimeEncabezados,
//											String remitente, String destinatario, String remitenteMostrar, String asunto, String mensaje, String usuario, String password){
//		String retorno = "";
//		String rutaArchivosTmp = "";
//		EmailBusinessProxy email = new EmailBusinessProxy();
//		TransportistaFacade facade = new TransportistaFacade();
//		TransportistaDTO transportistaDTO = new TransportistaDTO();
//		asunto = reemplazarCaracteres(asunto);
//		mensaje = reemplazarCaracteres(mensaje);
//		remitenteMostrar = reemplazarCaracteres(remitenteMostrar);
//		
//		try{
//			if(autenticarServicioGenerico(usuario, password)){
//				/*
//				 * 1. Consultar la estructura del archivo y consultar los registros del Query enviado por parámetro 
//				 */
//				transportistaDTO.setNombreArchivo(nombreArchivo);
//				transportistaDTO.setBiblioteca(biblioteca);
//				transportistaDTO.setQuery(query);
//				transportistaDTO = facade.consultarDetalleArchivoTransportistas(transportistaDTO);
//				
//				
//				/*
//				 * 2. Generar el Archivo de Excel o el archivo plano
//				 */
//				if(tipoArchivo != null && "xls".equals(tipoArchivo)){
//					rutaArchivosTmp = generarExcel(transportistaDTO, nombreReporte);
//				}else{
//					if(delimitador != null && "pos".equalsIgnoreCase(delimitador)){
//						rutaArchivosTmp = generarPlanoPorPosiciones(transportistaDTO, nombreReporte);
//					}else{
//						rutaArchivosTmp = generarPlano(transportistaDTO, nombreReporte, delimitador, imprimeEncabezados);
//					}
//				}
//				
//				
//				/*
//				 * 3. Enviar por Email el archivo
//				 */
////				email.setEndpoint(Configuracion.getInstance().getServicio("EmailBusiness").getRuta());
////				String[] destinatarios = destinatario != null ? destinatario.split("\\|") : null;
////				retorno = email.enviarEmailFull(remitente, destinatarios, remitenteMostrar, asunto, mensaje, usuario, password, null, null, null, rutaArchivosTmp);
//				
//				
//				/*
//				 * 4. Borrar el archivo generado
//				 */
//				FileManager.deleteFile(rutaArchivosTmp);
//			}
//			
//		}catch (PersonalsoftException e) {
//			logger.error(e.getTraza());
//		}catch (Exception e) {
//			logger.error(new PersonalsoftException(e).getTraza());
//		}
//		
//		return retorno;
//	}
	
	
	private String generarExcel(TransportistaDTO transportistaDTO, String nombreReporte) throws PersonalsoftException{
		ReporteExcelDTO reporte;
		GeneradorExcel generadorExcel = new GeneradorExcel();
		String rutaArchivosTmp = "";
		
		try{
			HSSFWorkbook wb = generadorExcel.getWb();
			rutaArchivosTmp = Configuracion.getInstance().getParametroApp("RUTA_ARCHIVOS_TMP") != null ? Configuracion.getInstance().getParametroApp("RUTA_ARCHIVOS_TMP") : Constantes.RUTA_ARCHIVOS_TMP;
			
			HSSFFont fontNormal = wb.createFont();
			fontNormal.setColor(HSSFColor.BLACK.index);
			fontNormal.setFontHeightInPoints((short)8);	
			
			HSSFFont fontTitulo = wb.createFont();
			fontTitulo.setColor(HSSFColor.BLACK.index);
			fontTitulo.setFontHeightInPoints((short)10);
			fontTitulo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			HSSFCellStyle styleContenido = wb.createCellStyle();
	        styleContenido.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        styleContenido.setWrapText(true);
	        styleContenido.setFont(fontNormal);
	        styleContenido.setBorderTop((short)1);
	        styleContenido.setBorderRight((short)1);
	        styleContenido.setBorderBottom((short)1);
	        styleContenido.setBorderLeft((short)1);
	        
			HSSFCellStyle styleTitulo = wb.createCellStyle();
			styleTitulo.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			styleTitulo.setWrapText(false);
	        styleTitulo.setFont(fontTitulo);
	        styleTitulo.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	        styleTitulo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        styleTitulo.setBorderTop((short)1);
	        styleTitulo.setBorderRight((short)1);
	        styleTitulo.setBorderBottom((short)1);
	        styleTitulo.setBorderLeft((short)1);
	        
			reporte = generarExcelDTO(transportistaDTO, nombreReporte);
			generadorExcel.setStyleContenido(styleContenido);
			generadorExcel.setStyleContenido2(styleContenido);
			generadorExcel.setStyleTitulo(styleTitulo);
			
			HSSFWorkbook workbook = generadorExcel.generarInforme(reporte);
			rutaArchivosTmp = rutaArchivosTmp+nombreReporte.toString()+".xls";
			FileOutputStream fileOut = new FileOutputStream(rutaArchivosTmp);
			
	        workbook.write(fileOut);
	        fileOut.flush();
	        fileOut.close();
		}catch (Exception e) {
			throw new PersonalsoftException(e);
		}
		
		return rutaArchivosTmp;
	}
	
	
	private ReporteExcelDTO generarExcelDTO(TransportistaDTO transportistaDTO, String nombreReporte) throws Exception{
		
		ReporteExcelDTO excelDTO = new ReporteExcelDTO(nombreReporte);
		ArrayList<List<String>> registros = new ArrayList<List<String>>();
		ArrayList<String> titulos = new ArrayList<String>();
		ArrayList<ArrayList<String>>  cabeceras = new ArrayList<ArrayList<String>>();
		
		// Titulos
		for (EstructuraArchivoDTO columna : transportistaDTO.getColumnas()) {
			titulos.add(columna.getDescripcion());
		}
		
		
		// Registros
		for (RegistroDTO[] columnas : transportistaDTO.getRegistros()){
			List<String> registro = new ArrayList<String>();
			
			for (RegistroDTO valorCelda : columnas) {
				registro.add(valorCelda.getValor());
			}
			
			registros.add(registro);
		}
		
		excelDTO.setNombreReporte(nombreReporte);
		excelDTO.setPintarNombre(true);
		excelDTO.setTitulos(titulos);
		excelDTO.setCabeceras(cabeceras);
		excelDTO.setRegistros(registros);
		
		return excelDTO;
	}
	
	
	private String generarPlano(TransportistaDTO transportistaDTO, String nombreReporte, String delimitador, String imprimeEncabezados) throws PersonalsoftException{
		String rutaArchivosTmp = "";
		StringBuffer linea = null;
		boolean isFirstLinea = true;
		
		try{
			rutaArchivosTmp = Configuracion.getInstance().getParametroApp("RUTA_ARCHIVOS_TMP") != null ? Configuracion.getInstance().getParametroApp("RUTA_ARCHIVOS_TMP") : Constantes.RUTA_ARCHIVOS_TMP;
			rutaArchivosTmp = rutaArchivosTmp+nombreReporte.toString()+".txt";
			BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivosTmp));
			
			if(imprimeEncabezados != null && "S".equals(imprimeEncabezados)){
				linea = new StringBuffer();
				for (EstructuraArchivoDTO columna : transportistaDTO.getColumnas()) {
					if(linea.length() == 0){
						linea.append(columna.getDescripcion());
					}else{
						linea.append(delimitador);
						linea.append(columna.getDescripcion());
					}
				}
				writer.write(linea.toString());
				isFirstLinea = false;
			}
			
			for (RegistroDTO[] columnas : transportistaDTO.getRegistros()){
				linea =  new StringBuffer();
				for (RegistroDTO valorCelda : columnas) {
					if(linea.length() == 0){
						linea.append(valorCelda.getValor());
					}else{
						linea.append(delimitador);
						linea.append(valorCelda.getValor());
					}
				}
				
				if(isFirstLinea){
					writer.write(linea.toString());
					isFirstLinea = false;
				}else{
					writer.newLine();
					writer.write(linea.toString());
				}
				
			}
			
			writer.flush();
			writer.close();
		}catch (Exception e) {
			throw new PersonalsoftException(e);
		}
		
		return rutaArchivosTmp;
	}
	
	
	private String generarPlanoPorPosiciones(TransportistaDTO transportistaDTO, String nombreReporte) throws PersonalsoftException{
		String rutaArchivosTmp = "";
		StringBuffer linea = null;
		boolean isFirstLinea = true;
		
		try{
			rutaArchivosTmp = Configuracion.getInstance().getParametroApp("RUTA_ARCHIVOS_TMP") != null ? Configuracion.getInstance().getParametroApp("RUTA_ARCHIVOS_TMP") : Constantes.RUTA_ARCHIVOS_TMP;
			rutaArchivosTmp = rutaArchivosTmp+nombreReporte.toString()+".txt";
			BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivosTmp));
			
			for (RegistroDTO[] registros : transportistaDTO.getRegistros()){
				linea =  new StringBuffer();
				for (RegistroDTO valorCelda : registros) {
					linea.append( assemblerValorPorPosiciones(valorCelda) );
				}
				
				if(isFirstLinea){
					writer.write(linea.toString());
					isFirstLinea = false;
				}else{
					writer.newLine();
					writer.write(linea.toString());
				}
				
			}
			
			writer.flush();
			writer.close();
		}catch (Exception e) {
			throw new PersonalsoftException(e);
		}
		
		return rutaArchivosTmp;
	}
	
	private String assemblerValorPorPosiciones(RegistroDTO registroDTO){
		String valor = registroDTO.getValor();
		
		if("A".equals(registroDTO.getEstructura().getTipoCampo())){
			//Si es Alfanumerico, se rellena de espacios a la derecha
			valor = padRight(valor, registroDTO.getEstructura().getTamanoCampo());
		}
		else{
			//Si es numerico, se rellena con ceros a la izquierda
			valor = padLeftNumber(valor, registroDTO.getEstructura().getTamanoCampo());
		}
		return valor;
	}
	
	
	private static String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}

	
	private static String padLeft(String s, int n) {
	    return String.format("%1$" + n + "s", s);  
	}
	
	private static String padLeftNumber(String s, int n){
		return padLeft(s, n).replaceAll(" ", "0");
	}
	
	
	private static String reemplazarCaracteres(String campo){
		if(campo != null){
			campo = campo.replaceAll("\\|x10a\\|", "á");
			campo = campo.replaceAll("\\|x11b\\|", "é");
			campo = campo.replaceAll("\\|x12c\\|", "í");
			campo = campo.replaceAll("\\|x13d\\|", "ó");
			campo = campo.replaceAll("\\|x14e\\|", "ú");
			campo = campo.replaceAll("\\|x15f\\|", "Á");
			campo = campo.replaceAll("\\|x16g\\|", "É");
			campo = campo.replaceAll("\\|x17h\\|", "Í");
			campo = campo.replaceAll("\\|x18i\\|", "Ó");
			campo = campo.replaceAll("\\|x19j\\|", "Ú");
			campo = campo.replaceAll("\\|x20k\\|", "ñ");
			campo = campo.replaceAll("\\|x21l\\|", "Ñ");
			campo = campo.replaceAll("\\|x22m\\|", "<br>");
			campo = campo.replaceAll("\\|x23n\\|", "<b>");
			campo = campo.replaceAll("\\|x24o\\|", "</b>");
			campo = campo.replaceAll("\\|x25p\\|", "<");
			campo = campo.replaceAll("\\|x26q\\|", ">");
		}
		
		return campo;
	}
	
	private static boolean autenticarServicioGenerico(String usuario, String password) throws PersonalsoftException{
		boolean isValid = false;
		
		if(usuario != null && password != null){
			if( usuario.equals(Configuracion.getInstance().getParametroApp("USUARIOGENERICOVOTRE")) && 
					password.equals(Configuracion.getInstance().getParametroApp("PASSGENERICOVOTRE")) ){
				isValid = true;
			}
		}
		
		return isValid;
	}
}
