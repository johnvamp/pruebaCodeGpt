package votre.portaloperaciones.solicitudcatalogo.compradora.beans;

public class RegistrosConsultaDTO {

	private String pais;
	private String tipo;
	private String cedula;
	private String nombreCompradora;
	private String telefono;
	private String nroCaso;
	private String sku;
	private String descripcion;
	private String cantidad;	
	private String campana;
	private String lineaGrabacion;
	private String btnEliminar;	
	
	public String getCampana() {
		return campana;
	}
	public void setCampana(String campana) {
		this.campana = campana;
	}	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombreCompradora() {
		return nombreCompradora;
	}
	public void setNombreCompradora(String nombreCompradora) {
		this.nombreCompradora = nombreCompradora;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getBtnEliminar() {
		return btnEliminar;
	}
	public void setBtnEliminar(String btnEliminar) {
		this.btnEliminar = btnEliminar;
	}
	public String getLineaGrabacion() {
		return lineaGrabacion;
	}
	public void setLineaGrabacion(String lineaGrabacion) {
		this.lineaGrabacion = lineaGrabacion;
	}
	public String getNroCaso() {
		return nroCaso;
	}
	public void setNroCaso(String nroCaso) {
		this.nroCaso = nroCaso;
	}
	
}
