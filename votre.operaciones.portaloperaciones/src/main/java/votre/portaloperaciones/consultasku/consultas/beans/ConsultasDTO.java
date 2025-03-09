package votre.portaloperaciones.consultasku.consultas.beans;

import java.io.Serializable;

public class ConsultasDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConsultasDTO() {
	}
	
	private String codCia;
	private String accion;
	private String referencia;
	private String color;
	private String talla;
	private String codigoBodega;
	private String tituloPais;
	private String tituloSaldos;
	private String tituloReferencia;
	private String tituloColor;
	private String tituloTalla;
	private String tituloBodega;
	private String tituloDescripcion;
	private String tituloLinea;
	private String tituloUbicacion;
	private String tituloCantidadD;
	private String tituloCantidadP;
	private String tituloCantidadA;
	private String tituloDisponibleP;
	private String tituloDisponibleaA;
	private String tituloCanPicking;
	private String tituloPrecio;
	private String tituloConsultar;
	private String tituloLimpiar;
	private String tituloCodigoBarras;
	private String sku;
	private String tituloCampana;
	private String tituloCustomer;
	private String tituloCantidad;
	private String tituloRegresar;
	private String tituloHistoria;
	private String titulocodigoTrans;
	private String tituloUnidades;
	private String tituloCantidadTrans;
	private String tituloFechaTrans;
	private String tituloOrden;
	private String tituloCreado;
	private String tituloFechaCreacion;
	private String tituloCodigoAud;
	private String tituloDescRef;
	private String tituloVendor;
	private String tituloMarca;
	private String tituloUen;
	private String tituloKit;
	private String tituloIndic;
	private String tituloEstado;
	private String descripcionRef;
	private String vendor;
	private String marca;
	private String uen;
	private String kit;
	private String indic;
	private String estadoOP;
	
	private DetalleConsultasDTO[] detalle;
	
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
	public String getCodigoBodega() {
		return codigoBodega;
	}
	public void setCodigoBodega(String codigoBodega) {
		this.codigoBodega = codigoBodega;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
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
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getTalla() {
		return talla;
	}
	public void setTalla(String talla) {
		this.talla = talla;
	}
	public String getTituloBodega() {
		return tituloBodega;
	}
	public void setTituloBodega(String tituloBodega) {
		this.tituloBodega = tituloBodega;
	}
	public String getTituloCanPicking() {
		return tituloCanPicking;
	}
	public void setTituloCanPicking(String tituloCanPicking) {
		this.tituloCanPicking = tituloCanPicking;
	}
	public String getTituloCantidadA() {
		return tituloCantidadA;
	}
	public void setTituloCantidadA(String tituloCantidadA) {
		this.tituloCantidadA = tituloCantidadA;
	}
	public String getTituloCantidadD() {
		return tituloCantidadD;
	}
	public void setTituloCantidadD(String tituloCantidadD) {
		this.tituloCantidadD = tituloCantidadD;
	}
	public String getTituloCantidadP() {
		return tituloCantidadP;
	}
	public void setTituloCantidadP(String tituloCantidadP) {
		this.tituloCantidadP = tituloCantidadP;
	}
	public String getTituloCodigoBarras() {
		return tituloCodigoBarras;
	}
	public void setTituloCodigoBarras(String tituloCodigoBarras) {
		this.tituloCodigoBarras = tituloCodigoBarras;
	}
	public String getTituloColor() {
		return tituloColor;
	}
	public void setTituloColor(String tituloColor) {
		this.tituloColor = tituloColor;
	}
	public String getTituloConsultar() {
		return tituloConsultar;
	}
	public void setTituloConsultar(String tituloConsultar) {
		this.tituloConsultar = tituloConsultar;
	}
	public String getTituloDescripcion() {
		return tituloDescripcion;
	}
	public void setTituloDescripcion(String tituloDescripcion) {
		this.tituloDescripcion = tituloDescripcion;
	}
	public String getTituloDisponibleaA() {
		return tituloDisponibleaA;
	}
	public void setTituloDisponibleaA(String tituloDisponibleaA) {
		this.tituloDisponibleaA = tituloDisponibleaA;
	}
	public String getTituloDisponibleP() {
		return tituloDisponibleP;
	}
	public void setTituloDisponibleP(String tituloDisponibleP) {
		this.tituloDisponibleP = tituloDisponibleP;
	}
	public String getTituloLimpiar() {
		return tituloLimpiar;
	}
	public void setTituloLimpiar(String tituloLimpiar) {
		this.tituloLimpiar = tituloLimpiar;
	}
	public String getTituloLinea() {
		return tituloLinea;
	}
	public void setTituloLinea(String tituloLinea) {
		this.tituloLinea = tituloLinea;
	}
	public String getTituloPais() {
		return tituloPais;
	}
	public void setTituloPais(String tituloPais) {
		this.tituloPais = tituloPais;
	}
	public String getTituloPrecio() {
		return tituloPrecio;
	}
	public void setTituloPrecio(String tituloPrecio) {
		this.tituloPrecio = tituloPrecio;
	}
	public String getTituloReferencia() {
		return tituloReferencia;
	}
	public void setTituloReferencia(String tituloReferencia) {
		this.tituloReferencia = tituloReferencia;
	}
	public String getTituloSaldos() {
		return tituloSaldos;
	}
	public void setTituloSaldos(String tituloSaldos) {
		this.tituloSaldos = tituloSaldos;
	}
	public String getTituloTalla() {
		return tituloTalla;
	}
	public void setTituloTalla(String tituloTalla) {
		this.tituloTalla = tituloTalla;
	}
	public String getTituloUbicacion() {
		return tituloUbicacion;
	}
	public void setTituloUbicacion(String tituloUbicacion) {
		this.tituloUbicacion = tituloUbicacion;
	}
	public DetalleConsultasDTO[] getDetalle() {
		return detalle;
	}
	public void setDetalle(DetalleConsultasDTO[] detalle) {
		this.detalle = detalle;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getTituloCampana() {
		return tituloCampana;
	}
	public void setTituloCampana(String tituloCampana) {
		this.tituloCampana = tituloCampana;
	}
	public String getTituloCantidad() {
		return tituloCantidad;
	}
	public void setTituloCantidad(String tituloCantidad) {
		this.tituloCantidad = tituloCantidad;
	}
	public String getTituloCustomer() {
		return tituloCustomer;
	}
	public void setTituloCustomer(String tituloCustomer) {
		this.tituloCustomer = tituloCustomer;
	}
	public String getTituloRegresar() {
		return tituloRegresar;
	}
	public void setTituloRegresar(String tituloRegresar) {
		this.tituloRegresar = tituloRegresar;
	}
	public String getTituloCantidadTrans() {
		return tituloCantidadTrans;
	}
	public void setTituloCantidadTrans(String tituloCantidadTrans) {
		this.tituloCantidadTrans = tituloCantidadTrans;
	}
	public String getTituloCodigoAud() {
		return tituloCodigoAud;
	}
	public void setTituloCodigoAud(String tituloCodigoAud) {
		this.tituloCodigoAud = tituloCodigoAud;
	}
	public String getTitulocodigoTrans() {
		return titulocodigoTrans;
	}
	public void setTitulocodigoTrans(String titulocodigoTrans) {
		this.titulocodigoTrans = titulocodigoTrans;
	}
	public String getTituloCreado() {
		return tituloCreado;
	}
	public void setTituloCreado(String tituloCreado) {
		this.tituloCreado = tituloCreado;
	}
	public String getTituloFechaCreacion() {
		return tituloFechaCreacion;
	}
	public void setTituloFechaCreacion(String tituloFechaCreacion) {
		this.tituloFechaCreacion = tituloFechaCreacion;
	}
	public String getTituloFechaTrans() {
		return tituloFechaTrans;
	}
	public void setTituloFechaTrans(String tituloFechaTrans) {
		this.tituloFechaTrans = tituloFechaTrans;
	}
	public String getTituloHistoria() {
		return tituloHistoria;
	}
	public void setTituloHistoria(String tituloHistoria) {
		this.tituloHistoria = tituloHistoria;
	}
	public String getTituloOrden() {
		return tituloOrden;
	}
	public void setTituloOrden(String tituloOrden) {
		this.tituloOrden = tituloOrden;
	}
	public String getTituloUnidades() {
		return tituloUnidades;
	}
	public void setTituloUnidades(String tituloUnidades) {
		this.tituloUnidades = tituloUnidades;
	}
	public String getTituloDescRef() {
		return tituloDescRef;
	}
	public void setTituloDescRef(String tituloDescRef) {
		this.tituloDescRef = tituloDescRef;
	}
	public String getTituloEstado() {
		return tituloEstado;
	}
	public void setTituloEstado(String tituloEstado) {
		this.tituloEstado = tituloEstado;
	}
	public String getTituloIndic() {
		return tituloIndic;
	}
	public void setTituloIndic(String tituloIndic) {
		this.tituloIndic = tituloIndic;
	}
	public String getTituloKit() {
		return tituloKit;
	}
	public void setTituloKit(String tituloKit) {
		this.tituloKit = tituloKit;
	}
	public String getTituloMarca() {
		return tituloMarca;
	}
	public void setTituloMarca(String tituloMarca) {
		this.tituloMarca = tituloMarca;
	}
	public String getTituloUen() {
		return tituloUen;
	}
	public void setTituloUen(String tituloUen) {
		this.tituloUen = tituloUen;
	}
	public String getTituloVendor() {
		return tituloVendor;
	}
	public void setTituloVendor(String tituloVendor) {
		this.tituloVendor = tituloVendor;
	}
	public String getDescripcionRef() {
		return descripcionRef;
	}
	public void setDescripcionRef(String descripcionRef) {
		this.descripcionRef = descripcionRef;
	}
	public String getEstadoOP() {
		return estadoOP;
	}
	public void setEstadoOP(String estadoOP) {
		this.estadoOP = estadoOP;
	}
	public String getIndic() {
		return indic;
	}
	public void setIndic(String indic) {
		this.indic = indic;
	}
	public String getKit() {
		return kit;
	}
	public void setKit(String kit) {
		this.kit = kit;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getUen() {
		return uen;
	}
	public void setUen(String uen) {
		this.uen = uen;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
}
