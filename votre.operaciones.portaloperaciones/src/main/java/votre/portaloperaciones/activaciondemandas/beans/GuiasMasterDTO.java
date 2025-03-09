package votre.portaloperaciones.activaciondemandas.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class GuiasMasterDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	public GuiasMasterDTO() {
	}

	private String codCia;
	private String accion;
	private String estado;
	private String descripcion;
	
	//Resulset
	private String cedulaTrans;
	private String nombreTrans;
	private String codigoTrans;
	private String totalPremios;
	private String cantidad;
	
	private ArrayList<GuiasMasterDTO> guias;

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

	public String getCedulaTrans() {
		return cedulaTrans;
	}

	public void setCedulaTrans(String cedulaTrans) {
		this.cedulaTrans = cedulaTrans;
	}

	public String getNombreTrans() {
		return nombreTrans;
	}

	public void setNombreTrans(String nombreTrans) {
		this.nombreTrans = nombreTrans;
	}

	public String getCodigoTrans() {
		return codigoTrans;
	}

	public void setCodigoTrans(String codigoTrans) {
		this.codigoTrans = codigoTrans;
	}

	public String getTotalPremios() {
		return totalPremios;
	}

	public void setTotalPremios(String totalPremios) {
		this.totalPremios = totalPremios;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public ArrayList<GuiasMasterDTO> getGuias() {
		return guias;
	}

	public void setGuias(ArrayList<GuiasMasterDTO> guias) {
		this.guias = guias;
	}
}
