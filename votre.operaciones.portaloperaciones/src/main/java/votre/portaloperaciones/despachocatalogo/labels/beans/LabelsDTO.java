package votre.portaloperaciones.despachocatalogo.labels.beans;

public class LabelsDTO {
	
	private String codCia;
	private String zona;
	private String tituloZona;
	private String tituloApli;
	private String tituloPant;
	private String tituloFecha;
	private String tituloPais;
	private String pais;	
	private String botonExcel;
	private String botonRegresar;
	private String botonGenerar;
	private String botonLabels;
	private RegistrosLabelsDTO[] registros;
	
	private String estado;
	private String desscrpcion;	
	
	public String getBotonExcel() {
		return botonExcel;
	}
	public void setBotonExcel(String botonExcel) {
		this.botonExcel = botonExcel;
	}
	public String getBotonGenerar() {
		return botonGenerar;
	}
	public void setBotonGenerar(String botonGenerar) {
		this.botonGenerar = botonGenerar;
	}
	public String getBotonLabels() {
		return botonLabels;
	}
	public void setBotonLabels(String botonLabels) {
		this.botonLabels = botonLabels;
	}
	public String getBotonRegresar() {
		return botonRegresar;
	}
	public void setBotonRegresar(String botonRegresar) {
		this.botonRegresar = botonRegresar;
	}
	public String getDesscrpcion() {
		return desscrpcion;
	}
	public void setDesscrpcion(String desscrpcion) {
		this.desscrpcion = desscrpcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getTituloApli() {
		return tituloApli;
	}
	public void setTituloApli(String tituloApli) {
		this.tituloApli = tituloApli;
	}
	public String getTituloFecha() {
		return tituloFecha;
	}
	public void setTituloFecha(String tituloFecha) {
		this.tituloFecha = tituloFecha;
	}
	public String getTituloPais() {
		return tituloPais;
	}
	public void setTituloPais(String tituloPais) {
		this.tituloPais = tituloPais;
	}
	public String getTituloPant() {
		return tituloPant;
	}
	public void setTituloPant(String tituloPant) {
		this.tituloPant = tituloPant;
	}
	public String getCodCia() {
		return codCia;
	}
	public void setCodCia(String codCia) {
		this.codCia = codCia;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public RegistrosLabelsDTO[] getRegistros() {
		return registros;
	}
	public void setRegistros(RegistrosLabelsDTO[] registros) {
		this.registros = registros;
	}
	public String getTituloZona() {
		return tituloZona;
	}
	public void setTituloZona(String tituloZona) {
		this.tituloZona = tituloZona;
	}
}
