package votre.portaloperaciones.embarqueinternacional.reimprimir.beans;

import java.io.Serializable;

public class ReimprimirDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReimprimirDTO() {
	}
	
	private String codCia;
	private String accion;
	private String codTransportador;
	private String camion;
	private String fecha;
	private String titReimpresion;
	private String titTransportador;
	private String titCamion;
	private String titFecha;
	private String titReimprimir;
	private String titRegresar;
	private String titCompraDirecta;
	private String titRelacionEmbarque;
	private String titCamionEnc;
	private String titFechaEnc;
	private String titNroOrden;
	private String titCedula;
	private String titNombre;
	private String titDireccion;
	private String titDestino;
	private String titTotal;
	private String titZona;
	private String titTelefono;
	private String valorConcatenado;
	private String titObservacion;
	private String titVdeclarado;
	private String transportador;
	private String titCampana;
	private String titPorteria;
	private String titTelefono2;
	private String titCelular;
	private String titDistrito;
	private String titCanton;
	private String titProvincia;
	private String titValorOrden;
	
	private DetalleReimprimirDTO[] detalle; 
	                             
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
	public String getCodTransportador() {
		return codTransportador;
	}
	public void setCodTransportador(String codTransportador) {
		this.codTransportador = codTransportador;
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
	public String getTitCamion() {
		return titCamion;
	}
	public void setTitCamion(String titCamion) {
		this.titCamion = titCamion;
	}
	public String getTitFecha() {
		return titFecha;
	}
	public void setTitFecha(String titFecha) {
		this.titFecha = titFecha;
	}
	public String getTitRegresar() {
		return titRegresar;
	}
	public void setTitRegresar(String titRegresar) {
		this.titRegresar = titRegresar;
	}
	public String getTitReimpresion() {
		return titReimpresion;
	}
	public void setTitReimpresion(String titReimpresion) {
		this.titReimpresion = titReimpresion;
	}
	public String getTitReimprimir() {
		return titReimprimir;
	}
	public void setTitReimprimir(String titReimprimir) {
		this.titReimprimir = titReimprimir;
	}
	public String getTitTransportador() {
		return titTransportador;
	}
	public void setTitTransportador(String titTransportador) {
		this.titTransportador = titTransportador;
	}
	public DetalleReimprimirDTO[] getDetalle() {
		return detalle;
	}
	public void setDetalle(DetalleReimprimirDTO[] detalle) {
		this.detalle = detalle;
	}
	public String getTitCamionEnc() {
		return titCamionEnc;
	}
	public void setTitCamionEnc(String titCamionEnc) {
		this.titCamionEnc = titCamionEnc;
	}
	public String getTitCedula() {
		return titCedula;
	}
	public void setTitCedula(String titCedula) {
		this.titCedula = titCedula;
	}
	public String getTitCompraDirecta() {
		return titCompraDirecta;
	}
	public void setTitCompraDirecta(String titCompraDirecta) {
		this.titCompraDirecta = titCompraDirecta;
	}
	public String getTitDestino() {
		return titDestino;
	}
	public void setTitDestino(String titDestino) {
		this.titDestino = titDestino;
	}
	public String getTitDireccion() {
		return titDireccion;
	}
	public void setTitDireccion(String titDireccion) {
		this.titDireccion = titDireccion;
	}
	public String getTitFechaEnc() {
		return titFechaEnc;
	}
	public void setTitFechaEnc(String titFechaEnc) {
		this.titFechaEnc = titFechaEnc;
	}
	public String getTitNombre() {
		return titNombre;
	}
	public void setTitNombre(String titNombre) {
		this.titNombre = titNombre;
	}
	public String getTitNroOrden() {
		return titNroOrden;
	}
	public void setTitNroOrden(String titNroOrden) {
		this.titNroOrden = titNroOrden;
	}
	public String getTitObservacion() {
		return titObservacion;
	}
	public void setTitObservacion(String titObservacion) {
		this.titObservacion = titObservacion;
	}
	public String getTitRelacionEmbarque() {
		return titRelacionEmbarque;
	}
	public void setTitRelacionEmbarque(String titRelacionEmbarque) {
		this.titRelacionEmbarque = titRelacionEmbarque;
	}
	public String getTitTotal() {
		return titTotal;
	}
	public void setTitTotal(String titTotal) {
		this.titTotal = titTotal;
	}
	public String getTitVdeclarado() {
		return titVdeclarado;
	}
	public void setTitVdeclarado(String titVdeclarado) {
		this.titVdeclarado = titVdeclarado;
	}
	public String getTransportador() {
		return transportador;
	}
	public void setTransportador(String transportador) {
		this.transportador = transportador;
	}
	public String getValorConcatenado() {
		return valorConcatenado;
	}
	public void setValorConcatenado(String valorConcatenado) {
		this.valorConcatenado = valorConcatenado;
	}
	public String getTitTelefono() {
		return titTelefono;
	}
	public void setTitTelefono(String titTelefono) {
		this.titTelefono = titTelefono;
	}
	public String getTitZona() {
		return titZona;
	}
	public void setTitZona(String titZona) {
		this.titZona = titZona;
	}
	public String getTitCampana() {
		return titCampana;
	}
	public void setTitCampana(String titCampana) {
		this.titCampana = titCampana;
	}
	public String getTitCelular() {
		return titCelular;
	}
	public void setTitCelular(String titCelular) {
		this.titCelular = titCelular;
	}
	public String getTitDistrito() {
		return titDistrito;
	}
	public void setTitDistrito(String titDistrito) {
		this.titDistrito = titDistrito;
	}
	public String getTitPorteria() {
		return titPorteria;
	}
	public void setTitPorteria(String titPorteria) {
		this.titPorteria = titPorteria;
	}
	public String getTitTelefono2() {
		return titTelefono2;
	}
	public void setTitTelefono2(String titTelefono2) {
		this.titTelefono2 = titTelefono2;
	}
	public String getTitValorOrden() {
		return titValorOrden;
	}
	public void setTitValorOrden(String titValorOrden) {
		this.titValorOrden = titValorOrden;
	}
	public String getTitCanton() {
		return titCanton;
	}
	public void setTitCanton(String titCanton) {
		this.titCanton = titCanton;
	}
	public String getTitProvincia() {
		return titProvincia;
	}
	public void setTitProvincia(String titProvincia) {
		this.titProvincia = titProvincia;
	}

}
