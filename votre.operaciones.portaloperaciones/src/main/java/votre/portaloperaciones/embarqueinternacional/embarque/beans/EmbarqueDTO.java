package votre.portaloperaciones.embarqueinternacional.embarque.beans;

public class EmbarqueDTO {
	
	private String codCia;
	private String titulo0;
	private String titulo1;
	private String titulo2;
	private String titulo3;
	private String titulo4;
	private String titulo5;
	private String titulo6;
	private String accion;
	private String codTransportador;
	private String placa;
	private String tipoEmbarque;
	private String fecha;
	private String nroPedidos;
	private String titPantalla;
	private String titTransportador;
	private String titCamion;
	private String titTotal;
	private String numOrden;
	private String titAceptar;
	private String titDetener;
	private String titFinalizar;
	private String respuesta;
	private String titCerrar;
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
	private String titTotalGuias;
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
	
	private DetalleEmbarqueDTO[] detalle;
	
	private String estado;
	private String descripcion;
	
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
	public String getTitulo0() {
		return titulo0;
	}
	public void setTitulo0(String titulo0) {
		this.titulo0 = titulo0;
	}
	public String getTitulo1() {
		return titulo1;
	}
	public void setTitulo1(String titulo1) {
		this.titulo1 = titulo1;
	}
	public String getTitulo2() {
		return titulo2;
	}
	public void setTitulo2(String titulo2) {
		this.titulo2 = titulo2;
	}
	public String getTitulo3() {
		return titulo3;
	}
	public void setTitulo3(String titulo3) {
		this.titulo3 = titulo3;
	}
	public String getTitulo4() {
		return titulo4;
	}
	public void setTitulo4(String titulo4) {
		this.titulo4 = titulo4;
	}
	public String getTitulo5() {
		return titulo5;
	}
	public void setTitulo5(String titulo5) {
		this.titulo5 = titulo5;
	}
	public String getTitulo6() {
		return titulo6;
	}
	public void setTitulo6(String titulo6) {
		this.titulo6 = titulo6;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getCodTransportador() {
		return codTransportador;
	}
	public void setCodTransportador(String codTransportador) {
		this.codTransportador = codTransportador;
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
	public String getTipoEmbarque() {
		return tipoEmbarque;
	}
	public void setTipoEmbarque(String tipoEmbarque) {
		this.tipoEmbarque = tipoEmbarque;
	}
	public String getNroPedidos() {
		return nroPedidos;
	}
	public void setNroPedidos(String nroPedidos) {
		this.nroPedidos = nroPedidos;
	}
	public String getNumOrden() {
		return numOrden;
	}
	public void setNumOrden(String numOrden) {
		this.numOrden = numOrden;
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
	public String getTitDetener() {
		return titDetener;
	}
	public void setTitDetener(String titDetener) {
		this.titDetener = titDetener;
	}
	public String getTitFinalizar() {
		return titFinalizar;
	}
	public void setTitFinalizar(String titFinalizar) {
		this.titFinalizar = titFinalizar;
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
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public String getTitPantalla() {
		return titPantalla;
	}
	public void setTitPantalla(String titPantalla) {
		this.titPantalla = titPantalla;
	}
	public String getTitCerrar() {
		return titCerrar;
	}
	public void setTitCerrar(String titCerrar) {
		this.titCerrar = titCerrar;
	}
	public String getTitRegresar() {
		return titRegresar;
	}
	public void setTitRegresar(String titRegresar) {
		this.titRegresar = titRegresar;
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
	public String getTitTelefono() {
		return titTelefono;
	}
	public void setTitTelefono(String titTelefono) {
		this.titTelefono = titTelefono;
	}
	public String getTitTotalGuias() {
		return titTotalGuias;
	}
	public void setTitTotalGuias(String titTotalGuias) {
		this.titTotalGuias = titTotalGuias;
	}
	public String getTitVdeclarado() {
		return titVdeclarado;
	}
	public void setTitVdeclarado(String titVdeclarado) {
		this.titVdeclarado = titVdeclarado;
	}
	public String getTitZona() {
		return titZona;
	}
	public void setTitZona(String titZona) {
		this.titZona = titZona;
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
	public DetalleEmbarqueDTO[] getDetalle() {
		return detalle;
	}
	public void setDetalle(DetalleEmbarqueDTO[] detalle) {
		this.detalle = detalle;
	}
	public String getTitCampana() {
		return titCampana;
	}
	public void setTitCampana(String titCampana) {
		this.titCampana = titCampana;
	}
	public String getTitCanton() {
		return titCanton;
	}
	public void setTitCanton(String titCanton) {
		this.titCanton = titCanton;
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
	public String getTitProvincia() {
		return titProvincia;
	}
	public void setTitProvincia(String titProvincia) {
		this.titProvincia = titProvincia;
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
}
