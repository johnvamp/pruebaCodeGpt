package votre.portaloperaciones.servicios.transportistas.beans;

import java.util.ArrayList;

public class TransportistaDTO {
	
	private String nombreArchivo;
	private String biblioteca;
	private String query;
	private ArrayList<EstructuraArchivoDTO> columnas;
	private ArrayList<RegistroDTO[]> registros;
	
	public TransportistaDTO() {
	}
	
	public ArrayList<EstructuraArchivoDTO> getColumnas() {
		return columnas;
	}
	public void setColumnas(ArrayList<EstructuraArchivoDTO> columnas) {
		this.columnas = columnas;
	}
	
	public String getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(String biblioteca) {
		this.biblioteca = biblioteca;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public ArrayList<RegistroDTO[]> getRegistros() {
		return registros;
	}

	public void setRegistros(ArrayList<RegistroDTO[]> registros) {
		this.registros = registros;
	}

}
