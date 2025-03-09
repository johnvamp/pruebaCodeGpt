package votre.portaloperaciones.embarqueinternacional.transportador.beans;

public class TransportadorDTO {
	
	private String codCia;
	private String accion;
	private String transportista;
	private String titEmbarque;
	private String titTransportador;
	private String titCamion;
	private String titAceptar;
	private String titRegresar;
	private String titOrden;
	private String titGuia;

	private TransportadorDetalleDTO[] detalles;
	
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
	public String getTransportista() {
		return transportista;
	}
	public void setTransportista(String transportista) {
		this.transportista = transportista;
	}
	public TransportadorDetalleDTO[] getDetalles() {
		return detalles;
	}
	public void setDetalles(TransportadorDetalleDTO[] detalles) {
		this.detalles = detalles;
	}
	public String getTitAceptar() {
		return titAceptar;
	}
	public void setTitAceptar(String titAceptar) {
		this.titAceptar = titAceptar;
	}
	public String getTitCamion() {
		return titCamion;
	}
	public void setTitCamion(String titCamion) {
		this.titCamion = titCamion;
	}
	public String getTitEmbarque() {
		return titEmbarque;
	}
	public void setTitEmbarque(String titEmbarque) {
		this.titEmbarque = titEmbarque;
	}
	public String getTitGuia() {
		return titGuia;
	}
	public void setTitGuia(String titGuia) {
		this.titGuia = titGuia;
	}
	public String getTitOrden() {
		return titOrden;
	}
	public void setTitOrden(String titOrden) {
		this.titOrden = titOrden;
	}
	public String getTitRegresar() {
		return titRegresar;
	}
	public void setTitRegresar(String titRegresar) {
		this.titRegresar = titRegresar;
	}
	public String getTitTransportador() {
		return titTransportador;
	}
	public void setTitTransportador(String titTransportador) {
		this.titTransportador = titTransportador;
	}
}
