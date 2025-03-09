package votre.portaloperaciones.activaciondemandas.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class EnviosDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	public EnviosDTO() {
	}
	
	private String codCia;
	private String tipo;
	private String accion;
	private String valor;
	private String codInterno;
	private String numOrden;
	private String sku;
	private String usuario;
	
	private String status;
	private String dsStatus;
	
	//Resulset
	private String tipoEnvio;
	private String totalRegistros;
	private String campana;
	private String cedula;
	private String nombre;
	private String descripSku;
	
	private ArrayList<EnviosDTO> arrEnvios;

	public String getCodCia() {
		return codCia;
	}
	public void setCodCia(String codCia) {
		this.codCia = codCia;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getCodInterno() {
		return codInterno;
	}
	public void setCodInterno(String codInterno) {
		this.codInterno = codInterno;
	}
	public String getNumOrden() {
		return numOrden;
	}
	public void setNumOrden(String numOrden) {
		this.numOrden = numOrden;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDsStatus() {
		return dsStatus;
	}
	public void setDsStatus(String dsStatus) {
		this.dsStatus = dsStatus;
	}
	public String getTipoEnvio() {
		return tipoEnvio;
	}
	public void setTipoEnvio(String tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}
	public String getTotalRegistros() {
		return totalRegistros;
	}
	public void setTotalRegistros(String totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	public String getCampana() {
		return campana;
	}
	public void setCampana(String campana) {
		this.campana = campana;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripSku() {
		return descripSku;
	}
	public void setDescripSku(String descripSku) {
		this.descripSku = descripSku;
	}
	public ArrayList<EnviosDTO> getArrEnvios() {
		return arrEnvios;
	}
	public void setArrEnvios(ArrayList<EnviosDTO> arrEnvios) {
		this.arrEnvios = arrEnvios;
	}

}
