package votre.portaloperaciones.embarqueinternacional.entrega.beans;

import votre.portaloperaciones.embarqueinternacional.transportador.beans.TransportadorDetalleDTO;

public class EntregaDTO {

	private String codCia;
	private String accion;
	private String codTransportador;
	private String placa;
	private String fecha;
	private String nroPedidos;
	private String numOrden;
	private String causal;
	private String titEntrega;
	private String titTransportador;
	private String titCamion;
	private String titParcial;
	private String titTotal;
	private String titAceptar;
	private String titRegresar;
	private String titCausal;
	private String titNumOrden;
	private String titFinalizar;
	private String titCancelar;
	private String titNoentregado;
	private String titEntregado;
	private String titTotalCajas;
	private String titConfirmar;
	private String respuesta;
	private String cantidadProcesada;
	private TransportadorDetalleDTO[] detalle;
	
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
	public String getTitParcial() {
		return titParcial;
	}
	public void setTitParcial(String titParcial) {
		this.titParcial = titParcial;
	}
	public String getTitRegresar() {
		return titRegresar;
	}
	public void setTitRegresar(String titRegresar) {
		this.titRegresar = titRegresar;
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
	public String getTitEntrega() {
		return titEntrega;
	}
	public void setTitEntrega(String titEntrega) {
		this.titEntrega = titEntrega;
	}
	public TransportadorDetalleDTO[] getDetalle() {
		return detalle;
	}
	public void setDetalle(TransportadorDetalleDTO[] detalle) {
		this.detalle = detalle;
	}
	public String getNroPedidos() {
		return nroPedidos;
	}
	public void setNroPedidos(String nroPedidos) {
		this.nroPedidos = nroPedidos;
	}
	public String getCausal() {
		return causal;
	}
	public void setCausal(String causal) {
		this.causal = causal;
	}
	public String getNumOrden() {
		return numOrden;
	}
	public void setNumOrden(String numOrden) {
		this.numOrden = numOrden;
	}
	public String getTitCausal() {
		return titCausal;
	}
	public void setTitCausal(String titCausal) {
		this.titCausal = titCausal;
	}
	public String getTitCancelar() {
		return titCancelar;
	}
	public void setTitCancelar(String titCancelar) {
		this.titCancelar = titCancelar;
	}
	public String getTitEntregado() {
		return titEntregado;
	}
	public void setTitEntregado(String titEntregado) {
		this.titEntregado = titEntregado;
	}
	public String getTitFinalizar() {
		return titFinalizar;
	}
	public void setTitFinalizar(String titFinalizar) {
		this.titFinalizar = titFinalizar;
	}
	public String getTitNoentregado() {
		return titNoentregado;
	}
	public void setTitNoentregado(String titNoentregado) {
		this.titNoentregado = titNoentregado;
	}
	public String getTitNumOrden() {
		return titNumOrden;
	}
	public void setTitNumOrden(String titNumOrden) {
		this.titNumOrden = titNumOrden;
	}
	public String getTitConfirmar() {
		return titConfirmar;
	}
	public void setTitConfirmar(String titConfirmar) {
		this.titConfirmar = titConfirmar;
	}
	public String getTitTotalCajas() {
		return titTotalCajas;
	}
	public void setTitTotalCajas(String titTotalCajas) {
		this.titTotalCajas = titTotalCajas;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public String getCantidadProcesada() {
		return cantidadProcesada;
	}
	public void setCantidadProcesada(String cantidadProcesada) {
		this.cantidadProcesada = cantidadProcesada;
	}
}
