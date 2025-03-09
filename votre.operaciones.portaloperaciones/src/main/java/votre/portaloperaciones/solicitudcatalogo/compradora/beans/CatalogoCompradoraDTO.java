package votre.portaloperaciones.solicitudcatalogo.compradora.beans;


public class CatalogoCompradoraDTO {
	
	private RegistrosConsultaDTO[] registros;
	private String codCia;
	private String titulo1;
	private String titulo2;
	private String titulo3;
	private String titulo4;
	private String boton;
	
	private String estado;
	private String descripcion;
	
	public String getBoton() {
		return boton;
	}
	public void setBoton(String boton) {
		this.boton = boton;
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
	public RegistrosConsultaDTO[] getRegistros() {
		return registros;
	}
	public void setRegistros(RegistrosConsultaDTO[] registros) {
		this.registros = registros;
	}
}
