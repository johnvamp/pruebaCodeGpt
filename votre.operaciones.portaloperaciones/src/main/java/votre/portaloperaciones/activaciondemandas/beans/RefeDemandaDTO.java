package votre.portaloperaciones.activaciondemandas.beans;

import java.io.Serializable;

public class RefeDemandaDTO implements Serializable{

	private String cia;
	private String sku;
	private String skuSusti;
	private String referencia;
	private String color;
	private String talla;
	private String campana;
	private String descripcion;
	private String status;
	private String dsStatus;

	public RefeDemandaDTO() {
	}

	public String getCampana() {
		return campana;
	}

	public void setCampana(String campana) {
		this.campana = campana;
	}

	public String getCia() {
		return cia;
	}

	public void setCia(String cia) {
		this.cia = cia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDsStatus() {
		return dsStatus;
	}

	public void setDsStatus(String dsStatus) {
		this.dsStatus = dsStatus;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getSkuSusti() {
		return skuSusti;
	}

	public void setSkuSusti(String skuSusti) {
		this.skuSusti = skuSusti;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

}
