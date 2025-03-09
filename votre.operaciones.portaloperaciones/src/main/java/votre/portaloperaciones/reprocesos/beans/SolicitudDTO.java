package votre.portaloperaciones.reprocesos.beans;

import java.util.ArrayList;

public class SolicitudDTO {

	public SolicitudDTO() {
	}
	
	private String codCia;
	private String fechaEntrega;
	private String fechaCreacion;
	private String fechaInicio;
	private String tipoEntrega;
	private String observacion;
	private String usuario;
	private String numRequerimiento;
	private String referencia;
	private String cantidad;
	private String accionTramite;
	private String accion;
	private String estado;
	private String ubicacion;
	
	private String status;
	private String dsStatus;
	
	private ArrayList<SolicitudDTO> arrSolicitudes;
	
	public String getCodCia() {
		return codCia;
	}
	public void setCodCia(String codCia) {
		this.codCia = codCia;
	}
	public String getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getTipoEntrega() {
		return tipoEntrega;
	}
	public void setTipoEntrega(String tipoEntrega) {
		this.tipoEntrega = tipoEntrega;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNumRequerimiento() {
		return numRequerimiento;
	}
	public void setNumRequerimiento(String numRequerimiento) {
		this.numRequerimiento = numRequerimiento;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getAccionTramite() {
		return accionTramite;
	}
	public void setAccionTramite(String accionTramite) {
		this.accionTramite = accionTramite;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public ArrayList<SolicitudDTO> getArrSolicitudes() {
		return arrSolicitudes;
	}
	public void setArrSolicitudes(ArrayList<SolicitudDTO> arrSolicitudes) {
		this.arrSolicitudes = arrSolicitudes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDsStatus() {
		return dsStatus;
	}
	public void setDsStatus(String dsStatus) {
		this.dsStatus = dsStatus;
	}

}
