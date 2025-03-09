package votre.portaloperaciones.despachocatalogo.labels.beans;

public class DespachoDTO {

	private String codCia;
	private String zona;
	private String tituloApli;
	private String tituloPant;
	private String tituloFecha;
	private String tituloPais;
	private String pais;	
	private String botonSolicitar;
	private String botonRegresar;
	private String labelsPendientes;
	private String tituloZona;
	private String campana;
	
	private String estado;
	private String descripcion;
	
	public String getBotonRegresar() {
		return botonRegresar;
	}
	public void setBotonRegresar(String botonRegresar) {
		this.botonRegresar = botonRegresar;
	}
	public String getCodCia() {
		return codCia;
	}
	public void setCodCia(String codCia) {
		this.codCia = codCia;
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
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public String getBotonSolicitar() {
		return botonSolicitar;
	}
	public void setBotonSolicitar(String botonSolicitar) {
		this.botonSolicitar = botonSolicitar;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCampana() {
		return campana;
	}
	public void setCampana(String campana) {
		this.campana = campana;
	}
	public String getTituloZona() {
		return tituloZona;
	}
	public void setTituloZona(String tituloZona) {
		this.tituloZona = tituloZona;
	}
	public String getLabelsPendientes() {
		return labelsPendientes;
	}
	public void setLabelsPendientes(String labelsPendientes) {
		this.labelsPendientes = labelsPendientes;
	}
}
