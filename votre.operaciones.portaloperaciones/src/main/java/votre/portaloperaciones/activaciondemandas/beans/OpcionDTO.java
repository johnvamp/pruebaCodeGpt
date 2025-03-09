package votre.portaloperaciones.activaciondemandas.beans;

import java.io.Serializable;

public class OpcionDTO implements Serializable{

	private String id;
	private String nombre;
	private String cia;
	private String accion;

	public OpcionDTO() {
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

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getCia() {
		return cia;
	}

	public void setCia(String cia) {
		this.cia = cia;
	}

}
