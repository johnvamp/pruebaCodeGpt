package votre.portaloperaciones.logueo.beans;

public class LogueoDTO{
	private String codCia;
	private String cedula;
	private String nombrePais;
	private String boton;
	
	private String estado;
	private String descripcion;
	
	public LogueoDTO(){
		
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
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

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	public String getCodCia() {
		return codCia;
	}

	public void setCodCia(String codCia) {
		this.codCia = codCia;
	}

	public String getBoton() {
		return boton;
	}

	public void setBoton(String boton) {
		this.boton = boton;
	}
}
