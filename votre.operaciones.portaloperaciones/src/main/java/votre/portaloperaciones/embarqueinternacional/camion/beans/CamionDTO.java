package votre.portaloperaciones.embarqueinternacional.camion.beans;

public class CamionDTO {

	private String codCia;
	private String accion;
	private String totCamionesAbiertos;
	private String totCamionesPendientes;
	private String titConsultas;
	private String titCamiones;
	private String titEstado;
	private String titTransportador;
	private String titFecha;
	private String titCamion;
	private String titNumCajas;
	private String titTotal;
	private String titCamionesAbiertos;
	private String titCamionesPendientes;
	private String titReimprimir;
	private String titConsultar;
	private String titRegresar;
	private DetalleCamionDTO[] detalle;
	
	private String estado;
	private String descripcion;
	
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getCodCia() {
		return codCia;
	}
	public void setCodCia(String codCia) {
		this.codCia = codCia;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTitCamion() {
		return titCamion;
	}
	public void setTitCamion(String titCamion) {
		this.titCamion = titCamion;
	}
	public String getTitCamiones() {
		return titCamiones;
	}
	public void setTitCamiones(String titCamiones) {
		this.titCamiones = titCamiones;
	}
	public String getTitCamionesAbiertos() {
		return titCamionesAbiertos;
	}
	public void setTitCamionesAbiertos(String titCamionesAbiertos) {
		this.titCamionesAbiertos = titCamionesAbiertos;
	}
	public String getTitCamionesPendientes() {
		return titCamionesPendientes;
	}
	public void setTitCamionesPendientes(String titCamionesPendientes) {
		this.titCamionesPendientes = titCamionesPendientes;
	}
	public String getTitConsultar() {
		return titConsultar;
	}
	public void setTitConsultar(String titConsultar) {
		this.titConsultar = titConsultar;
	}
	public String getTitConsultas() {
		return titConsultas;
	}
	public void setTitConsultas(String titConsultas) {
		this.titConsultas = titConsultas;
	}
	public String getTitEstado() {
		return titEstado;
	}
	public void setTitEstado(String titEstado) {
		this.titEstado = titEstado;
	}
	public String getTitFecha() {
		return titFecha;
	}
	public void setTitFecha(String titFecha) {
		this.titFecha = titFecha;
	}
	public String getTitNumCajas() {
		return titNumCajas;
	}
	public void setTitNumCajas(String titNumCajas) {
		this.titNumCajas = titNumCajas;
	}
	public String getTitRegresar() {
		return titRegresar;
	}
	public void setTitRegresar(String titRegresar) {
		this.titRegresar = titRegresar;
	}
	public String getTitReimprimir() {
		return titReimprimir;
	}
	public void setTitReimprimir(String titReimprimir) {
		this.titReimprimir = titReimprimir;
	}
	public String getTitTotal() {
		return titTotal;
	}
	public void setTitTotal(String titTotal) {
		this.titTotal = titTotal;
	}
	public String getTitTransportador() {
		return titTransportador;
	}
	public void setTitTransportador(String titTransportador) {
		this.titTransportador = titTransportador;
	}
	public String getTotCamionesAbiertos() {
		return totCamionesAbiertos;
	}
	public void setTotCamionesAbiertos(String totCamionesAbiertos) {
		this.totCamionesAbiertos = totCamionesAbiertos;
	}
	public String getTotCamionesPendientes() {
		return totCamionesPendientes;
	}
	public void setTotCamionesPendientes(String totCamionesPendientes) {
		this.totCamionesPendientes = totCamionesPendientes;
	}
	public DetalleCamionDTO[] getDetalle() {
		return detalle;
	}
	public void setDetalle(DetalleCamionDTO[] detalle) {
		this.detalle = detalle;
	}
}
