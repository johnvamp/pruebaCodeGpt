package votre.portaloperaciones.consultasku.bodegas.beans;

import java.io.Serializable;

public class BodegaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BodegaDTO() {
	}
	
	private String codigo;
	private String nombre;
	
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
