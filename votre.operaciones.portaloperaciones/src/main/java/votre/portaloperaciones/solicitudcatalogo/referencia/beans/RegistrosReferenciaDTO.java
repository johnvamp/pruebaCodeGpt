package votre.portaloperaciones.solicitudcatalogo.referencia.beans;

public class RegistrosReferenciaDTO {

	private String campana;
	private String lineaGrabacion;
	private String sku;
	private String descripcion;
	private String cantidad;
	private String descripAuditoria;
	private String fecha;
	private String hora;
	private String usuario;
	
	public String getDescripAuditoria() {
		return descripAuditoria;
	}
	public void setDescripAuditoria(String descripAuditoria) {
		this.descripAuditoria = descripAuditoria;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getCampana() {
		return campana;
	}
	public void setCampana(String campana) {
		this.campana = campana;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getLineaGrabacion() {
		return lineaGrabacion;
	}
	public void setLineaGrabacion(String lineaGrabacion) {
		this.lineaGrabacion = lineaGrabacion;
	}	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
