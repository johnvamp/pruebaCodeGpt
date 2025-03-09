package votre.portaloperaciones.solicitudcatalogo.catalogo.beans;

public class CatalogoDTO{
	
	//Atributos utilizados para mostrar la pantalla de consulta de catalogos
	//---------------------------------------------------------------------
	private String codCia;
	private String cedula;
	private String accion;
	private String tituloPais;
	private String opcionCedula;
	private String opcionNombre;
	private String tituloBoton;
	private String nroCaso;
	//---------------------------------------------------------------------
	
	private String estado;
	private String descripcion;

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
	public String getOpcionCedula() {
		return opcionCedula;
	}
	public void setOpcionCedula(String opcionCedula) {
		this.opcionCedula = opcionCedula;
	}
	public String getOpcionNombre() {
		return opcionNombre;
	}
	public void setOpcionNombre(String opcionNombre) {
		this.opcionNombre = opcionNombre;
	}
	public String getTituloBoton() {
		return tituloBoton;
	}
	public void setTituloBoton(String tituloBoton) {
		this.tituloBoton = tituloBoton;
	}
	public String getTituloPais() {
		return tituloPais;
	}
	public void setTituloPais(String tituloPais) {
		this.tituloPais = tituloPais;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getCodCia() {
		return codCia;
	}
	public void setCodCia(String codCia) {
		this.codCia = codCia;
	}
	public String getNroCaso() {
		return nroCaso;
	}
	public void setNroCaso(String nroCaso) {
		this.nroCaso = nroCaso;
	}

}
