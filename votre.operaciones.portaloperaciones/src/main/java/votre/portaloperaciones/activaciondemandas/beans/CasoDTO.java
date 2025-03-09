package votre.portaloperaciones.activaciondemandas.beans;

import java.io.Serializable;

public class CasoDTO implements Serializable{

	private String cia;
	private String id;
	private String nombre;
	private String ciudad;
	private String depto;
	private String region;
	private String mailPlan;
	private String tipo;
	private String zona;
	private String codInterno;
	private String accion;

	private String criterio;
	private String seleccion;
	private String valor;
	private String refeSku;
	private String tallaSku;
	private String colorSku;
	
	private String campanaOrigen;
	private String campanaReclamo;
	private String campanaCaso;
	private String referencia;
	private String descripcion;
	private String cantidad;

	private String motivo;
	private String area;
	private String fechaRegistro;
	private String estado;
	private String dsEstado;
	private String orden;
	private String skuSustituto;
	private String dsSkuSustituto;
	private String cantidadEntregada;
	private String tipoDespacho;
	private String fechaAprobacion;
	private String guia;
	private String transportadora;
	private String cedulaTransportista;
	private String direccionDespacho;
	private String barrioDespacho;
	private String ciudadDespacho;
	private String deptoDespacho;
	private String tel1Despacho;
	private String tarifa;
	private String fechaDespacho;
	private String dsTransportadora;
	private String nombreTransportista;
	private String guiaMaster;
	private String campanaIni;
	private String campanaFin;
	private String tipoProducto;
	private String despachoAuto;
	private String sector;
	private String celular;

	private String usuario;
	private String status;
	private String dsStatus;
	
	//atributos para paginacion
	private String accionExcel;
	private int semilla;
	private int paginaActual;
	private int totalPaginas;
	private int incremento;

	public CasoDTO() {
		semilla = 0;
	}
	
	public String getCampanaCaso() {
		return campanaCaso;
	}

	public void setCampanaCaso(String campanaCaso) {
		this.campanaCaso = campanaCaso;
	}
	
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCia() {
		return cia;
	}

	public void setCia(String cia) {
		this.cia = cia;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCodInterno() {
		return codInterno;
	}

	public void setCodInterno(String codInterno) {
		this.codInterno = codInterno;
	}

	public String getDepto() {
		return depto;
	}

	public void setDepto(String depto) {
		this.depto = depto;
	}

	public String getDsStatus() {
		return dsStatus;
	}

	public void setDsStatus(String dsStatus) {
		this.dsStatus = dsStatus;
	}

	public String getMailPlan() {
		return mailPlan;
	}

	public void setMailPlan(String mailPlan) {
		this.mailPlan = mailPlan;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(String seleccion) {
		this.seleccion = seleccion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCampanaOrigen() {
		return campanaOrigen;
	}

	public void setCampanaOrigen(String campanaOrigen) {
		this.campanaOrigen = campanaOrigen;
	}

	public String getCampanaReclamo() {
		return campanaReclamo;
	}

	public void setCampanaReclamo(String campanaReclamo) {
		this.campanaReclamo = campanaReclamo;
	}

	public String getFechaDespacho() {
		return fechaDespacho;
	}

	public void setFechaDespacho(String fechaDespacho) {
		this.fechaDespacho = fechaDespacho;
	}

	public String getDsTransportadora() {
		return dsTransportadora;
	}

	public void setDsTransportadora(String dsTransportadora) {
		this.dsTransportadora = dsTransportadora;
	}

	public String getNombreTransportista() {
		return nombreTransportista;
	}

	public void setNombreTransportista(String nombreTransportista) {
		this.nombreTransportista = nombreTransportista;
	}

	public String getGuiaMaster() {
		return guiaMaster;
	}

	public void setGuiaMaster(String guiaMaster) {
		this.guiaMaster = guiaMaster;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getBarrioDespacho() {
		return barrioDespacho;
	}

	public void setBarrioDespacho(String barrioDespacho) {
		this.barrioDespacho = barrioDespacho;
	}

	public String getCantidadEntregada() {
		return cantidadEntregada;
	}

	public void setCantidadEntregada(String cantidadEntregada) {
		this.cantidadEntregada = cantidadEntregada;
	}

	public String getCedulaTransportista() {
		return cedulaTransportista;
	}

	public void setCedulaTransportista(String cedulaTransportista) {
		this.cedulaTransportista = cedulaTransportista;
	}

	public String getCiudadDespacho() {
		return ciudadDespacho;
	}

	public void setCiudadDespacho(String ciudadDespacho) {
		this.ciudadDespacho = ciudadDespacho;
	}

	public String getDeptoDespacho() {
		return deptoDespacho;
	}

	public void setDeptoDespacho(String deptoDespacho) {
		this.deptoDespacho = deptoDespacho;
	}

	public String getDireccionDespacho() {
		return direccionDespacho;
	}

	public void setDireccionDespacho(String direccionDespacho) {
		this.direccionDespacho = direccionDespacho;
	}

	public String getDsSkuSustituto() {
		return dsSkuSustituto;
	}

	public void setDsSkuSustituto(String dsSkuSustituto) {
		this.dsSkuSustituto = dsSkuSustituto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaAprobacion() {
		return fechaAprobacion;
	}

	public void setFechaAprobacion(String fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getGuia() {
		return guia;
	}

	public void setGuia(String guia) {
		this.guia = guia;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public String getSkuSustituto() {
		return skuSustituto;
	}

	public void setSkuSustituto(String skuSustituto) {
		this.skuSustituto = skuSustituto;
	}

	public String getTarifa() {
		return tarifa;
	}

	public void setTarifa(String tarifa) {
		this.tarifa = tarifa;
	}

	public String getTel1Despacho() {
		return tel1Despacho;
	}

	public void setTel1Despacho(String tel1Despacho) {
		this.tel1Despacho = tel1Despacho;
	}

	public String getTipoDespacho() {
		return tipoDespacho;
	}

	public void setTipoDespacho(String tipoDespacho) {
		this.tipoDespacho = tipoDespacho;
	}

	public String getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(String transportadora) {
		this.transportadora = transportadora;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getAccionExcel() {
		return accionExcel;
	}

	public void setAccionExcel(String accionExcel) {
		this.accionExcel = accionExcel;
	}

	public int getIncremento() {
		return incremento;
	}

	public void setIncremento(int incremento) {
		this.incremento = incremento;
	}

	public int getSemilla() {
		return semilla;
	}

	public void setSemilla(int semilla) {
		this.semilla = semilla;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}

	public String getDsEstado() {
		return dsEstado;
	}

	public void setDsEstado(String dsEstado) {
		this.dsEstado = dsEstado;
	}

	public String getColorSku() {
		return colorSku;
	}

	public void setColorSku(String colorSku) {
		this.colorSku = colorSku;
	}

	public String getRefeSku() {
		return refeSku;
	}

	public void setRefeSku(String refeSku) {
		this.refeSku = refeSku;
	}

	public String getTallaSku() {
		return tallaSku;
	}

	public void setTallaSku(String tallaSku) {
		this.tallaSku = tallaSku;
	}

	public String getCampanaIni() {
		return campanaIni;
	}

	public void setCampanaIni(String campanaIni) {
		this.campanaIni = campanaIni;
	}

	public String getCampanaFin() {
		return campanaFin;
	}

	public void setCampanaFin(String campanaFin) {
		this.campanaFin = campanaFin;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getDespachoAuto() {
		return despachoAuto;
	}

	public void setDespachoAuto(String despachoAuto) {
		this.despachoAuto = despachoAuto;
	}

}
