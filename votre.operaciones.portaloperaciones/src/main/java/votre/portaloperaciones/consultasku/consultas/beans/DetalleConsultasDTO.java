package votre.portaloperaciones.consultasku.consultas.beans;

import java.io.Serializable;

public class DetalleConsultasDTO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DetalleConsultasDTO() {
	}
	
	private String referencia;
	private String descripcion;
	private String lineaGrabacion;
	private String ubicacion;
	private String cantidadDispo;
	private String cantidadPedi;
	private String cantidadAsig;
	private String disponiblePedir;
	private String disponibleAsig;
	private String cantidadPicking;
	private String codigoBarras;
	private String campana;
	private String customerClass;
	private String cantidad;
	private String precio;
	private String bodega;
	private String codigoTrans;
	private String unidades;
	private String cantidadTrans;
	private String fechaTrans;
	private String orden;
	private String creado;
	private String fechaCreacion;
	private String codigoAud;

	public String getBodega() {
		return bodega;
	}
	public void setBodega(String bodega) {
		this.bodega = bodega;
	}
	public String getCantidadTrans() {
		return cantidadTrans;
	}
	public void setCantidadTrans(String cantidadTrans) {
		this.cantidadTrans = cantidadTrans;
	}
	public String getCodigoAud() {
		return codigoAud;
	}
	public void setCodigoAud(String codigoAud) {
		this.codigoAud = codigoAud;
	}
	public String getCodigoTrans() {
		return codigoTrans;
	}
	public void setCodigoTrans(String codigoTrans) {
		this.codigoTrans = codigoTrans;
	}
	public String getCreado() {
		return creado;
	}
	public void setCreado(String creado) {
		this.creado = creado;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getFechaTrans() {
		return fechaTrans;
	}
	public void setFechaTrans(String fechaTrans) {
		this.fechaTrans = fechaTrans;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public String getUnidades() {
		return unidades;
	}
	public void setUnidades(String unidades) {
		this.unidades = unidades;
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
	public String getCustomerClass() {
		return customerClass;
	}
	public void setCustomerClass(String customerClass) {
		this.customerClass = customerClass;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public String getCantidadAsig() {
		return cantidadAsig;
	}
	public void setCantidadAsig(String cantidadAsig) {
		this.cantidadAsig = cantidadAsig;
	}
	public String getCantidadDispo() {
		return cantidadDispo;
	}
	public void setCantidadDispo(String cantidadDispo) {
		this.cantidadDispo = cantidadDispo;
	}
	public String getCantidadPicking() {
		return cantidadPicking;
	}
	public void setCantidadPicking(String cantidadPicking) {
		this.cantidadPicking = cantidadPicking;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDisponibleAsig() {
		return disponibleAsig;
	}
	public void setDisponibleAsig(String disponibleAsig) {
		this.disponibleAsig = disponibleAsig;
	}
	public String getDisponiblePedir() {
		return disponiblePedir;
	}
	public void setDisponiblePedir(String disponiblePedir) {
		this.disponiblePedir = disponiblePedir;
	}
	public String getLineaGrabacion() {
		return lineaGrabacion;
	}
	public void setLineaGrabacion(String lineaGrabacion) {
		this.lineaGrabacion = lineaGrabacion;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getCantidadPedi() {
		return cantidadPedi;
	}
	public void setCantidadPedi(String cantidadPedi) {
		this.cantidadPedi = cantidadPedi;
	}

}
