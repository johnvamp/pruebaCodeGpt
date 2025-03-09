package votre.portaloperaciones.solicitudcatalogo.compradora.beans;

public class EnviosDTO {
	
	private RegistrosConsultaDTO[] registros;
	private String codCia;
	private String cedula;
	private String nroCaso;
	private String titulo1;
	private String titulo2;
	private String titulo3;
	private String titulo4;
	private String titulo5;
	private String titulo6;
	private String titulo7;
	
	private String estado;
	private String descripcion;
	
	
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
	public RegistrosConsultaDTO[] getRegistros() {
		return registros;
	}
	public void setRegistros(RegistrosConsultaDTO[] registros) {
		this.registros = registros;
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
	public String getTitulo7() {
		return titulo7;
	}
	public void setTitulo7(String titulo7) {
		this.titulo7 = titulo7;
	}
	public String getNroCaso() {
		return nroCaso;
	}
	public void setNroCaso(String nroCaso) {
		this.nroCaso = nroCaso;
	}
	
}
