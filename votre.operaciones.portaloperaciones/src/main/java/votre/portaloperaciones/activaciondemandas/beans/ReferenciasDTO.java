package votre.portaloperaciones.activaciondemandas.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class ReferenciasDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	public ReferenciasDTO() {
	}
	
	private String codCia;
	private String accion;
	private String estado;
	private String descripcion;
	
	//Resulset
	private String referencia;
	private String descripRefe;
	private String cantidadCompra;
	private ArrayList<ReferenciasDTO> referencias;

	public String getCodCia() {
		return codCia;
	}

	public void setCodCia(String codCia) {
		this.codCia = codCia;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public String getDescripRefe() {
		return descripRefe;
	}

	public void setDescripRefe(String descripRefe) {
		this.descripRefe = descripRefe;
	}

	public String getCantidadCompra() {
		return cantidadCompra;
	}

	public void setCantidadCompra(String cantidadCompra) {
		this.cantidadCompra = cantidadCompra;
	}

	public ArrayList<ReferenciasDTO> getReferencias() {
		return referencias;
	}

	public void setReferencias(ArrayList<ReferenciasDTO> referencias) {
		this.referencias = referencias;
	}

}
