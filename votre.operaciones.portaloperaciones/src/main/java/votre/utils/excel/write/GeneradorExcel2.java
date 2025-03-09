package votre.utils.excel.write;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;


public class GeneradorExcel2 {

	private HSSFWorkbook wb;
	private HSSFCellStyle styleNegrita;
	private HSSFCellStyle styleTitulo;
	private HSSFCellStyle styleContenido;
	private HSSFCellStyle styleContenido2;
	private HSSFCellStyle stylePies;
	private HSSFFont fontNegroNegrita;
	private HSSFFont fontNormal;
	private HSSFFont fontTitulo;
	private HSSFDataFormat formateador; 
	private String formatoNumero = "#,##0.00";

	public void setFormatoNumero(String formatoNumero) {
		this.formatoNumero = formatoNumero;
	}

	public GeneradorExcel2() {
		wb = new HSSFWorkbook();

		fontTitulo = wb.createFont();
		fontTitulo.setColor(HSSFColor.BLACK.index);
		fontTitulo.setFontHeightInPoints((short)12);
		fontTitulo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		fontNegroNegrita = wb.createFont();
		fontNegroNegrita.setColor(HSSFColor.BLACK.index);
		fontNegroNegrita.setFontHeightInPoints((short)8);
		fontNegroNegrita.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		fontNormal = wb.createFont();
		fontNormal.setColor(HSSFColor.BLACK.index);
		fontNormal.setFontHeightInPoints((short)8);

		styleNegrita = wb.createCellStyle();
		styleNegrita.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleNegrita.setWrapText(false);
		styleNegrita.setFont(fontNegroNegrita);

		styleTitulo = wb.createCellStyle();
		styleTitulo.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleTitulo.setWrapText(false);
        styleTitulo.setFont(fontTitulo);
        styleTitulo.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        styleTitulo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        styleContenido = wb.createCellStyle();
        styleContenido.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleContenido.setWrapText(true);
        styleContenido.setFont(fontNormal);
        
        styleContenido2 = wb.createCellStyle();
        styleContenido2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleContenido2.setWrapText(true);
        styleContenido2.setFont(fontNormal);
        styleContenido2.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        styleContenido2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        
        stylePies = styleTitulo;
        
        formateador = wb.createDataFormat();
	}
	
	public HSSFWorkbook generarInforme(ReporteExcelDTO reporte){
		HSSFSheet hoja = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		int posicion = 0;
		int posFila = 0;

		//hoja = wb.createSheet(reporte.getNombreReporte()); 
		// Se pone en comentario porque genera problema con las tildes 
		hoja = wb.createSheet("Hoja1"); 		

		int nroTitulos = 0;
		for (List<Celda> lst : reporte.getTitulos()){
			if (lst.size() > nroTitulos)
				nroTitulos = lst.size();
		}
		
		if(reporte.isPintarNombre()){
			posFila = 1;
			CellRangeAddress region = new CellRangeAddress(posFila, posFila, 0, nroTitulos - 1);
			hoja.addMergedRegion(region);
			row = hoja.createRow(posFila);
			cell = row.createCell(0);
			cell.setCellValue( new HSSFRichTextString( reporte.getNombreReporte() ) );
			cell.setCellStyle(styleTitulo);
			posFila+=2;
		}

		//Ahora los datos de las cabeceras
		if(reporte.getCabeceras() != null){
			for (ArrayList<String> fila : reporte.getCabeceras()) {
				row = hoja.createRow(posFila);
				posicion = 0;
				for (String dato : fila) {
					cell = row.createCell(posicion);
					cell.setCellValue( new HSSFRichTextString(dato) );
					cell.setCellStyle(styleNegrita);
					posicion++;
				}
				posFila++;
			}
			posFila++;
		}

		//Ahora los titulos de las columnas
		posicion = 0;
		if(reporte.getTitulos() != null){
			for (List<Celda> titulos : reporte.getTitulos()) {
				row = hoja.createRow(posFila);
				posicion = 0;
				for (Celda celda : titulos) {
					cell = row.createCell(posicion);
					cell.setCellValue(new HSSFRichTextString(celda.getContenido()));
					
					if (celda.isAplicaEstilo()) {
						if (celda.getEstilo() != null)
							cell.setCellStyle( celda.getEstilo() );
						else
							cell.setCellStyle( styleTitulo );
					}
					
					if (celda.getContenido().equals("")){
						posicion = posicion + celda.getNroColumnas();
						continue;
					}
					if (celda.getNroColumnas() != 1 || celda.getNroFilas() != 1){
						CellRangeAddress region = new CellRangeAddress(posFila, posFila + celda.getNroFilas() - 1, posicion, posicion + celda.getNroColumnas() - 1);
						hoja.addMergedRegion(region);
						posicion = posicion + celda.getNroColumnas();
					}
					else
						posicion++;
				}
				posFila++;
			}
		}

		//Ahora los datos de las columnas
		int numHoja = 2;
		if(reporte.getRegistros() != null){
			for (List<Celda> fila : reporte.getRegistros()) {
				
				if(posFila == 65000){
					String nomHoja = "Hoja" + numHoja;
					hoja = wb.createSheet(nomHoja);	
					posFila = 0;
					numHoja ++;
					
					///I Fpalacio
					
					//Ahora los titulos de las columnas
					posicion = 0;
					if(reporte.getTitulos() != null){
						for (List<Celda> titulos : reporte.getTitulos()) {
							row = hoja.createRow(posFila);
							posicion = 0;
							for (Celda celda : titulos) {
								cell = row.createCell(posicion);
								cell.setCellValue(new HSSFRichTextString(celda.getContenido()));
								
								if (celda.isAplicaEstilo()) {
									if (celda.getEstilo() != null)
										cell.setCellStyle( celda.getEstilo() );
									else
										cell.setCellStyle( styleTitulo );
								}
								
								if (celda.getContenido().equals("")){
									posicion = posicion + celda.getNroColumnas();
									continue;
								}
								if (celda.getNroColumnas() != 1 || celda.getNroFilas() != 1){
									CellRangeAddress region = new CellRangeAddress(posFila, posFila + celda.getNroFilas() - 1, posicion, posicion + celda.getNroColumnas() - 1);
									hoja.addMergedRegion(region);
									posicion = posicion + celda.getNroColumnas();
								}
								else
									posicion++;
							}
							posFila++;
						}
					}
					
					/// F Palacio
					
					
				}
				

				
				row = hoja.createRow(posFila);
				posicion = 0;
				for (Celda celda : fila) {					
					cell = row.createCell(posicion);
					if (celda.isNumero()){
						try{
							cell.setCellValue( new Double(celda.getContenido().replaceAll(",", "")) );
						}
						catch (Exception e){
							cell.setCellValue( new HSSFRichTextString(celda.getContenido()) );
						}
					}
					else
						cell.setCellValue( new HSSFRichTextString(celda.getContenido()) );
					
					if (celda.isAplicaEstilo()) {
						if (celda.getEstilo() != null)
							cell.setCellStyle( celda.getEstilo() );
						else {							
							cell.setCellStyle(styleContenido);
							/*
							if (posFila % 2 == 0)
								cell.setCellStyle(styleContenido);
							else
								cell.setCellStyle(styleContenido2);
							*/
							if(celda.isNumero() && celda.isAplicarFormatoNumero()){
								cell.getCellStyle().setDataFormat(formateador.getFormat(formatoNumero));
							}
						}
					}
					
					if (celda.getContenido().equals("")){
						posicion = posicion + celda.getNroColumnas();
						continue;
					}
					if (celda.getNroColumnas() != 1 || celda.getNroFilas() != 1){
						CellRangeAddress region = new CellRangeAddress(posFila, posFila + celda.getNroFilas() - 1, posicion, posicion + celda.getNroColumnas() - 1);
						hoja.addMergedRegion(region);
						posicion = posicion + celda.getNroColumnas();
					}
					else
						posicion++;
				}
				posFila++;
				
			
				
				
			}
		}
		
		// Pie de pagina del Reporte
		if (reporte.getPies() != null){
			posFila++;
			for (List<Celda> pies : reporte.getPies()) {
				row = hoja.createRow(posFila);
				posicion = 0;
				for (Celda celda : pies) {
					cell = row.createCell(posicion);
					if (celda.isNumero()){
						try{
							cell.setCellValue( new Double(celda.getContenido().replaceAll(",", "")) );
						}
						catch (Exception e){
							cell.setCellValue( new HSSFRichTextString(celda.getContenido()) );
						}
					}
					else
						cell.setCellValue( new HSSFRichTextString(celda.getContenido()) );
					
					if (celda.isAplicaEstilo()) {
						if (celda.getEstilo() != null)
							cell.setCellStyle( celda.getEstilo() );
						else
							cell.setCellStyle(stylePies);
					}
					//else
					//	cell.setCellStyle(styleNegrita);
					
					if (celda.getContenido().equals("")){
						posicion = posicion + celda.getNroColumnas();
						continue;
					}
					if (celda.getNroColumnas() != 1 || celda.getNroFilas() != 1){
						CellRangeAddress region = new CellRangeAddress(posFila, posFila + celda.getNroFilas() - 1, posicion, posicion + celda.getNroColumnas() - 1);
						hoja.addMergedRegion(region);
						posicion = posicion + celda.getNroColumnas();
					}
					else
						posicion++;
				}
				posFila++;
			}
		}
		
		//Se asigna un ancho predeterminado a las columnas
		if(reporte.getTitulos() != null){
			for (int i = 0; i < nroTitulos; i++) {
				hoja.autoSizeColumn((short)i);
			}
		}

		return wb;
	}

	public void setStyleContenido(HSSFCellStyle styleContenido) {
		this.styleContenido = styleContenido;
	}
	public void setStyleContenido2(HSSFCellStyle styleContenido2) {
		this.styleContenido2 = styleContenido2;
	}
	public void setStyleNegrita(HSSFCellStyle styleNegrita) {
		this.styleNegrita = styleNegrita;
	}
	public void setStylePies(HSSFCellStyle stylePies) {
		this.stylePies = stylePies;
	}
	public void setStyleTitulo(HSSFCellStyle styleTitulo) {
		this.styleTitulo = styleTitulo;
	}

	public HSSFWorkbook getWb() {
		return wb;
	}

	public void setWb(HSSFWorkbook wb) {
		this.wb = wb;
	}

	public HSSFCellStyle getStyleContenido() {
		return styleContenido;
	}

	public HSSFCellStyle getStyleContenido2() {
		return styleContenido2;
	}

	public HSSFCellStyle getStylePies() {
		return stylePies;
	}

	public HSSFCellStyle getStyleTitulo() {
		return styleTitulo;
	}
}