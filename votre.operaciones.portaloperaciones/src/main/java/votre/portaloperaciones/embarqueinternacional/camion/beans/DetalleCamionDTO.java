package votre.portaloperaciones.embarqueinternacional.camion.beans;

import java.io.Serializable;

public class DetalleCamionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DetalleCamionDTO() {
	}
	
	private String estado;
	private String transportador;
	private String fecha;
	private String camion;
	private String numCajas;

	public String getCamion() {
		return camion;
	}
	public void setCamion(String camion) {
		this.camion = camion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getNumCajas() {
		return numCajas;
	}
	public void setNumCajas(String numCajas) {
		this.numCajas = numCajas;
	}
	public String getTransportador() {
		return transportador;
	}
	public void setTransportador(String transportador) {
		this.transportador = transportador;
	}

}
