package votre.portaloperaciones.embarqueinternacional.reimprimir.beans;

import java.io.Serializable;

public class DetalleReimprimirDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DetalleReimprimirDTO() {
	}
	
	private String codTransportador;
	private String transportador;
	private String camion;
	private String placa;
	private String fecha;
	private String valorConcatenado;
	private String observacion;
	private String numOrden;
	private String zona;
	private String cedula;
	private String nombre;
	private String direccion;
	private String telefono;
	private String destino;
	private String valorDeclarado;
	private String campana;
	private String porteria;
	private String telefono2;
	private String celular;
	private String distrito;
	private String canton;
	private String provincia;
	private String valorOrden;

	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNumOrden() {
		return numOrden;
	}
	public void setNumOrden(String numOrden) {
		this.numOrden = numOrden;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getValorDeclarado() {
		return valorDeclarado;
	}
	public void setValorDeclarado(String valorDeclarado) {
		this.valorDeclarado = valorDeclarado;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public String getCodTransportador() {
		return codTransportador;
	}
	public void setCodTransportador(String codTransportador) {
		this.codTransportador = codTransportador;
	}
	public String getTransportador() {
		return transportador;
	}
	public void setTransportador(String transportador) {
		this.transportador = transportador;
	}
	public String getCamion() {
		return camion;
	}
	public void setCamion(String camion) {
		this.camion = camion;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getValorConcatenado() {
		return valorConcatenado;
	}
	public void setValorConcatenado(String valorConcatenado) {
		this.valorConcatenado = valorConcatenado;
	}
	public String getCampana() {
		return campana;
	}
	public void setCampana(String campana) {
		this.campana = campana;
	}
	public String getCanton() {
		return canton;
	}
	public void setCanton(String canton) {
		this.canton = canton;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	public String getPorteria() {
		return porteria;
	}
	public void setPorteria(String porteria) {
		this.porteria = porteria;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	public String getValorOrden() {
		return valorOrden;
	}
	public void setValorOrden(String valorOrden) {
		this.valorOrden = valorOrden;
	}

}
