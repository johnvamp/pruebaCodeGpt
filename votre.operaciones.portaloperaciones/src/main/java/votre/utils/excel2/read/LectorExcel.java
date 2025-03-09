package votre.utils.excel2.read;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import votre.utils.excel.read.Celda;
import votre.utils.excel.read.Tipos;

public class LectorExcel {

	private String errorColumnaLongitud = "Error en la longitud del contenido de la celda ";
	private String errorTipoDato = "Error en el tipo de contenido de la celda ";
	private String errorTipoNumerico = " El tipo de dato debe ser numérico";
	private String errorVacio = " La celda se encuentra vacía";
	private boolean buscarCorrectos = true;
	private byte[] archivo;
	private HSSFWorkbook libro = null;
	private boolean lecturaCorrecta = false;
	private ArrayList<Celda> columnas = new ArrayList<Celda>();
	private int nroHojas;
	
	public LectorExcel(byte[] contenidoArchivo, ArrayList<Celda> columnas) throws Exception {
		if (columnas == null || columnas.isEmpty())
			throw new Exception("No se envio la lista de columnas para leer de excel");

		this.columnas = columnas;
		this.archivo = contenidoArchivo;
		
		try {
			ByteArrayInputStream input = new ByteArrayInputStream(archivo);
			POIFSFileSystem fs = new POIFSFileSystem(input);
			libro = new HSSFWorkbook(fs);
			nroHojas = libro.getNumberOfSheets();
			lecturaCorrecta = true;

		} catch (Exception e) {
			lecturaCorrecta = false;
			throw e;
		}
	}

	
	public String validarArchivo() {
		if (!lecturaCorrecta)
			return "Ocurrio un error en la lectura del archivo";
		
		StringBuffer retorno = new StringBuffer();
		HSSFSheet hojaArchivo;
		HSSFRow filaTitulo;
		int nroCols = columnas.size();

		if (libro.getNumberOfSheets() > 0) {
			hojaArchivo = libro.getSheetAt(0);
			/*filaTitulo = hojaArchivo.getRow(0);
			if (filaTitulo != null){
				if (filaTitulo.getLastCellNum() != nroCols) {
					retorno.append( "No se envio todas las columnas especificadas" );
				}
			}
			else {
				retorno.append( "No existe la fila de titulos" );
			}*/
		} else {
			retorno.append( "No hay hojas a leer" );
		}
		return retorno.toString();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<Celda>> leerArchivo(int nroHojas) {
		ArrayList<ArrayList<Celda>> filas = new ArrayList<ArrayList<Celda>>();
		ArrayList<ArrayList<Celda>> filasInvalidas = new ArrayList<ArrayList<Celda>>();
		HSSFSheet hojaArchivo;
		HSSFRow fila;
		HSSFCell cell;

		try{
			for (int sheet = 0; sheet < 1; sheet++) {
				hojaArchivo = libro.getSheetAt(sheet);
				for(int row = 1; row <= hojaArchivo.getLastRowNum(); row++){
					fila = hojaArchivo.getRow(row);
					ArrayList<Celda> listaFila = new ArrayList<Celda>();
					
					for (int col = 0; col < columnas.size(); col ++){
						cell = fila.getCell(col, HSSFRow.CREATE_NULL_AS_BLANK);
						Celda tipoColumna = columnas.get(col);
						Celda celda = leerAtributo(cell, tipoColumna);
						if (buscarCorrectos) {
							if (celda.getLength() != -1)
								listaFila.add( celda );
							else {
								buscarCorrectos = false;
								listaFila = new ArrayList<Celda>();
								celda.setValor( (String)celda.getValor() + getNombreCelda(col + 1) + (row + 1));
								listaFila.add( celda );
							}
						} else {
							if (celda.getLength() == -1) {
								celda.setValor( (String)celda.getValor() + getNombreCelda(col + 1) + (row + 1));
								listaFila.add( celda );
							}
						}
					}
					
					if (buscarCorrectos)
						filas.add( listaFila );
					else
						filasInvalidas.add( listaFila );
				}
			}
		}
		catch(Exception e){
			System.err.println(e);
		}
		
		if (buscarCorrectos)
			return filas;
		return filasInvalidas;
	}
	
	private Celda leerAtributo(HSSFCell celda, Celda tipoColumna){
		Tipos tipo = tipoColumna.getTipo();
		String valor = "";
		
		if ( celda.getCellType() == HSSFCell.CELL_TYPE_STRING)
			valor = celda.getRichStringCellValue().getString().trim();
		if ( celda.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
			if (tipoColumna.getLengthDecimal() > 0)
				valor = new Double( celda.getNumericCellValue() ).toString();
			else
				valor = new Integer (new Double( celda.getNumericCellValue()).intValue() ).toString();
		}		
		
		if (tipoColumna.getLengthDecimal() > 0) {
			int pos = valor.indexOf(".");
			if (pos != -1) {
				if (pos > tipoColumna.getLength() || valor.length() - pos > tipoColumna.getLengthDecimal())
					return new Celda<String>(errorColumnaLongitud, -1);
			} else if ( valor.length() > tipoColumna.getLength())
				return new Celda<String>(errorColumnaLongitud, -1);
			
		} else if ( valor.length() > tipoColumna.getLength())
			return new Celda<String>(errorColumnaLongitud, -1);
			
		try {
			if (tipo == Tipos.STRING)
				return new Celda<String>( valor );
			
			if (tipo == Tipos.ENTERO)
				return new Celda<Integer>( new Double( valor ).intValue() );
			
			if (tipo == Tipos.DECIMAL)
				return new Celda<Double>( new Double( valor ) );
			
			if (tipo == Tipos.LONG)
				return new Celda<Long>( new Double( valor ).longValue() );
			
		}
		catch(Exception e){
			System.err.println( errorTipoDato );
		}
		return new Celda<String>(errorTipoDato, -1);
	}

	public int getNroHojas() {
		return nroHojas;
	}
		
	private String getNombreCelda(int column){
		int veces = column / 26;
		if (column <= 26)
			return new Character((char)(65 + (column % 27) - 1)).toString();
		else
			return getNombreCelda( veces ) + getNombreCelda( column - (26 * veces));
	}
	
	public ArrayList<ArrayList<Celda>> leerArchivoSorteo(int nroHojas) {
		ArrayList<ArrayList<Celda>> filas = new ArrayList<ArrayList<Celda>>();
		ArrayList<ArrayList<Celda>> filasInvalidas = new ArrayList<ArrayList<Celda>>();
		HSSFSheet hojaArchivo;
		HSSFRow fila;
		HSSFCell cell;

		try{
			for (int sheet = 0; sheet < nroHojas; sheet++) {
				hojaArchivo = libro.getSheetAt(sheet);
				for(int row = 1; row <= hojaArchivo.getLastRowNum(); row++){
					fila = hojaArchivo.getRow(row);
					ArrayList<Celda> listaFila = new ArrayList<Celda>();
					
					for (int col = 0; col < columnas.size(); col ++){
						cell = fila.getCell(col, HSSFRow.CREATE_NULL_AS_BLANK);
						Celda tipoColumna = columnas.get(col);
						Celda celda = leerAtributoSorteo(cell, tipoColumna,col);
						if (buscarCorrectos) {
							if (celda.getLength() != -1)
								listaFila.add( celda );
							else {
								buscarCorrectos = false;
								listaFila = new ArrayList<Celda>();
								celda.setValor( (String)celda.getValor() + getNombreCelda(col + 1) + (row + 1));
								listaFila.add( celda );
							}
						} else {
							if (celda.getLength() == -1) {
								celda.setValor( (String)celda.getValor() + getNombreCelda(col + 1) + (row + 1));
								listaFila.add( celda );
							}
						}
					}
					
					if (buscarCorrectos)
						filas.add( listaFila );
					else
						filasInvalidas.add( listaFila );
				}
			}
		}
		catch(Exception e){
			System.err.println(e);
		}
		
		if (buscarCorrectos)
			return filas;
		return filasInvalidas;
	}
	
	private Celda leerAtributoSorteo(HSSFCell celda, Celda tipoColumna, int columna){
		Tipos tipo = tipoColumna.getTipo();
		String valor = "";
		
		if ( celda.getCellType() == HSSFCell.CELL_TYPE_STRING)
			valor = celda.getRichStringCellValue().getString().trim();
		if ( celda.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
			if (tipoColumna.getLengthDecimal() > 0)
				valor = new Double( celda.getNumericCellValue() ).toString();
			else
				valor = new Integer (new Double( celda.getNumericCellValue()).intValue() ).toString();
		}
		
		if( celda.getCellType() == HSSFCell.CELL_TYPE_BLANK){
			valor = "";
		}
		
		if (tipoColumna.getLengthDecimal() > 0) {
			int pos = valor.indexOf(".");
			if (pos != -1) {
				if (pos > tipoColumna.getLength() || valor.length() - pos > tipoColumna.getLengthDecimal())
					return new Celda<String>(errorColumnaLongitud, -1);
			} else if ( valor.length() > tipoColumna.getLength())
				return new Celda<String>(errorColumnaLongitud, -1);
			
		} else if ( valor.length() > tipoColumna.getLength())
			return new Celda<String>(errorColumnaLongitud, -1);
		
		if (tipo == Tipos.ENTERO && valor.equals("") && (columna == 3 || columna == 9 || columna == 11)){
			valor = "0";
		}
		
		try {
			Celda celdaRetornada = null;
			if (tipo == Tipos.STRING && (!valor.equals("") || columna == 4 || columna == 6 || columna == 7 || columna == 8 || columna == 9 || columna == 10)){
				celdaRetornada = new Celda<String>( valor );
			}
			else if (columna == 1 || columna == 2){
				celdaRetornada = new Celda<String>(errorTipoDato, -1 , errorVacio);
			}
			
			if (tipo == Tipos.ENTERO && !valor.equals("")) {
				celdaRetornada = new Celda<Integer>( new Double( valor ).intValue() );
			}
			else if (columna == 0 || columna == 5){
				celdaRetornada = new Celda<String>(errorTipoDato, -1 , errorVacio);
			}
			
			if (tipo == Tipos.DECIMAL){
				celdaRetornada = new Celda<Double>( new Double( valor ) );
			}
			
			if (tipo == Tipos.LONG){
				celdaRetornada = new Celda<Long>( new Double( valor ).longValue() );
			}
			
			return celdaRetornada;
		}
		catch(Exception e){
			System.err.println( errorTipoDato );
		}
		return new Celda<String>(errorTipoDato, -1 , errorTipoNumerico);
	}
	
}
