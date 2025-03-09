package votre.portaloperaciones.pedidoscsl.beans;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PedidosCSLDTO {

	private String codCia;
	private String accion;
	private String destinatario;
	private String val1;
	private String sku;
	private String referencia;
	private String color;
	private String talla;
	private String cantidad;
	private String centroCostos;
	private String tipoPedido;
	private String estrategia;
	private String usuario;
	private String cedulaComando;
	private String nombreComando;
	private String numOrden;
	private String cedula;
	private String descripcionItem;
	private String guia;
	private String transportista ;
	private String transportadora;
	private String accionInfo;
	private String fecha;
	private String hora;
	private String observacion;
	
	private String spSts;
	private String spDes;
	
	private ListaPedidosDTO[] listaPedidos;
	private ArrayList<PedidosCSLDTO> arrPedidos;
	private ArrayList<String> arrDatosCombo;
	
	private String fechaEmbarque;
	private String guiaReempaque;
	
	public String getCodCia() {
		return codCia;
	}
	public void setCodCia(String codCia) {
		this.codCia = codCia;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getVal1() {
		return val1;
	}
	public void setVal1(String val1) {
		this.val1 = val1;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getTalla() {
		return talla;
	}
	public void setTalla(String talla) {
		this.talla = talla;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getCentroCostos() {
		return centroCostos;
	}
	public void setCentroCostos(String centroCostos) {
		this.centroCostos = centroCostos;
	}
	public String getTipoPedido() {
		return tipoPedido;
	}
	public void setTipoPedido(String tipoPedido) {
		this.tipoPedido = tipoPedido;
	}
	public String getEstrategia() {
		return estrategia;
	}
	public void setEstrategia(String estrategia) {
		this.estrategia = estrategia;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getCedulaComando() {
		return cedulaComando;
	}
	public void setCedulaComando(String cedulaComando) {
		this.cedulaComando = cedulaComando;
	}
	public String getNombreComando() {
		return nombreComando;
	}
	public void setNombreComando(String nombreComando) {
		this.nombreComando = nombreComando;
	}
	public String getNumOrden() {
		return numOrden;
	}
	public void setNumOrden(String numOrden) {
		this.numOrden = numOrden;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getDescripcionItem() {
		return descripcionItem;
	}
	public void setDescripcionItem(String descripcionItem) {
		this.descripcionItem = descripcionItem;
	}
	public String getGuia() {
		return guia;
	}
	public void setGuia(String guia) {
		this.guia = guia;
	}
	public String getTransportista() {
		return transportista;
	}
	public void setTransportista(String transportista) {
		this.transportista = transportista;
	}
	public String getTransportadora() {
		return transportadora;
	}
	public void setTransportadora(String transportadora) {
		this.transportadora = transportadora;
	}
	public String getAccionInfo() {
		return accionInfo;
	}
	public void setAccionInfo(String accionInfo) {
		this.accionInfo = accionInfo;
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
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getSpSts() {
		return spSts;
	}
	public void setSpSts(String spSts) {
		this.spSts = spSts;
	}
	public String getSpDes() {
		return spDes;
	}
	public void setSpDes(String spDes) {
		this.spDes = spDes;
	}
	public ListaPedidosDTO[] getListaPedidos() {
		return listaPedidos;
	}
	public void setListaPedidos(ListaPedidosDTO[] listaPedidos) {
		this.listaPedidos = listaPedidos;
	}
	public ArrayList<PedidosCSLDTO> getArrPedidos() {
		return arrPedidos;
	}
	public void setArrPedidos(ArrayList<PedidosCSLDTO> arrPedidos) {
		this.arrPedidos = arrPedidos;
	}
	public ArrayList<String> getArrDatosCombo() {
		return arrDatosCombo;
	}
	public void setArrDatosCombo(ArrayList<String> arrDatosCombo) {
		this.arrDatosCombo = arrDatosCombo;
	}
	public String getFechaEmbarque() {
		return fechaEmbarque;
	}
	public void setFechaEmbarque(String fechaEmbarque) {
		this.fechaEmbarque = fechaEmbarque;
	}
	public String getGuiaReempaque() {
		return guiaReempaque;
	}
	public void setGuiaReempaque(String guiaReempaque) {
		this.guiaReempaque = guiaReempaque;
	}
}
