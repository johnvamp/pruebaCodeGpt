package votre.portaloperaciones.despachocatalogo.reportes.beans;

public class InformacionDTO {

	private String codCia;
	private String codigoLabel;
	private String accion;
	private String fechaIni;
	private String fechaFin;
	private String usuario;
	private String zona;
	private String campana;
	private DetalleReportesDTO[] detalle;
	
	private String titulo;
	private String tituloNombre;
	private String tituloDireccion;
	private String tituloCiudad;
	private String tituloDepartamento;
	private String tituloCodPostal;
	
	private String estado;
	private String descripcion;
	
	private String tituloTelefono1;
	private String tituloCedula;
	private String tituloTelefono2;
	private String tituloBarrio;
	private String tituloNroCatalogos;
	private String tituloTipo;
	private String tituloZona;
	private String tituloMailPlan;
	private String tituloTipoCompra;
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getCampana() {
		return campana;
	}
	public void setCampana(String campana) {
		this.campana = campana;
	}
	public String getCodCia() {
		return codCia;
	}
	public void setCodCia(String codCia) {
		this.codCia = codCia;
	}
	public String getCodigoLabel() {
		return codigoLabel;
	}
	public void setCodigoLabel(String codigoLabel) {
		this.codigoLabel = codigoLabel;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public DetalleReportesDTO[] getDetalle() {
		return detalle;
	}
	public void setDetalle(DetalleReportesDTO[] detalle) {
		this.detalle = detalle;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTituloBarrio() {
		return tituloBarrio;
	}
	public void setTituloBarrio(String tituloBarrio) {
		this.tituloBarrio = tituloBarrio;
	}
	public String getTituloCedula() {
		return tituloCedula;
	}
	public void setTituloCedula(String tituloCedula) {
		this.tituloCedula = tituloCedula;
	}
	public String getTituloCiudad() {
		return tituloCiudad;
	}
	public void setTituloCiudad(String tituloCiudad) {
		this.tituloCiudad = tituloCiudad;
	}
	public String getTituloCodPostal() {
		return tituloCodPostal;
	}
	public void setTituloCodPostal(String tituloCodPostal) {
		this.tituloCodPostal = tituloCodPostal;
	}
	public String getTituloDepartamento() {
		return tituloDepartamento;
	}
	public void setTituloDepartamento(String tituloDepartamento) {
		this.tituloDepartamento = tituloDepartamento;
	}
	public String getTituloDireccion() {
		return tituloDireccion;
	}
	public void setTituloDireccion(String tituloDireccion) {
		this.tituloDireccion = tituloDireccion;
	}
	public String getTituloMailPlan() {
		return tituloMailPlan;
	}
	public void setTituloMailPlan(String tituloMailPlan) {
		this.tituloMailPlan = tituloMailPlan;
	}
	public String getTituloNombre() {
		return tituloNombre;
	}
	public void setTituloNombre(String tituloNombre) {
		this.tituloNombre = tituloNombre;
	}
	public String getTituloNroCatalogos() {
		return tituloNroCatalogos;
	}
	public void setTituloNroCatalogos(String tituloNroCatalogos) {
		this.tituloNroCatalogos = tituloNroCatalogos;
	}
	public String getTituloTelefono1() {
		return tituloTelefono1;
	}
	public void setTituloTelefono1(String tituloTelefono1) {
		this.tituloTelefono1 = tituloTelefono1;
	}
	public String getTituloTelefono2() {
		return tituloTelefono2;
	}
	public void setTituloTelefono2(String tituloTelefono2) {
		this.tituloTelefono2 = tituloTelefono2;
	}
	public String getTituloTipo() {
		return tituloTipo;
	}
	public void setTituloTipo(String tituloTipo) {
		this.tituloTipo = tituloTipo;
	}
	public String getTituloTipoCompra() {
		return tituloTipoCompra;
	}
	public void setTituloTipoCompra(String tituloTipoCompra) {
		this.tituloTipoCompra = tituloTipoCompra;
	}
	public String getTituloZona() {
		return tituloZona;
	}
	public void setTituloZona(String tituloZona) {
		this.tituloZona = tituloZona;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
}
