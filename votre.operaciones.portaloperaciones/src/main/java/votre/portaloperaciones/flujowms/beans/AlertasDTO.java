package votre.portaloperaciones.flujowms.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class AlertasDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codCia;
	private String estado;
	private String descripcion;
	
	//Resulset
	private String titEstado;
	private String pedidos;
	private String desEstado;
	private String nroEstado;
	private String region;
	private String mainPlan;
	private String zona;
	private String cedula;
	private String nombre;
	private String transmision;
	private String fechaTrans;
	private String horaTrans;
	private String nroOrden;
	private String conceptoRetencion;
	private String fechaConferencia;
	private String fechaLiberacion;
	private String horaLiberacion;
	private String fechaAsignacion;
	private String horaAsignacion;
	private String departamento;
	private String ciudad;
	private String pickTicket;
	private String estadoPickTicket;
	private String nroCajas;
	private String transportista;
	private String fechaConsolidacion;
	private String horaConsolidacion;
	private String campana;
	private String horaRetrasoWms;
	private String horaRetrasoConsolidado;
	
	private ArrayList<AlertasDTO> detalle;
	
	public String getCodCia() {
		return codCia;
	}
	public void setCodCia(String codCia) {
		this.codCia = codCia;
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
	public String getTitEstado() {
		return titEstado;
	}
	public void setTitEstado(String titEstado) {
		this.titEstado = titEstado;
	}
	public String getPedidos() {
		return pedidos;
	}
	public void setPedidos(String pedidos) {
		this.pedidos = pedidos;
	}
	public String getDesEstado() {
		return desEstado;
	}
	public void setDesEstado(String desEstado) {
		this.desEstado = desEstado;
	}
	public String getNroEstado() {
		return nroEstado;
	}
	public void setNroEstado(String nroEstado) {
		this.nroEstado = nroEstado;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getMainPlan() {
		return mainPlan;
	}
	public void setMainPlan(String mainPlan) {
		this.mainPlan = mainPlan;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
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
	public String getTransmision() {
		return transmision;
	}
	public void setTransmision(String transmision) {
		this.transmision = transmision;
	}
	public String getFechaTrans() {
		return fechaTrans;
	}
	public void setFechaTrans(String fechaTrans) {
		this.fechaTrans = fechaTrans;
	}
	public String getHoraTrans() {
		return horaTrans;
	}
	public void setHoraTrans(String horaTrans) {
		this.horaTrans = horaTrans;
	}
	public String getNroOrden() {
		return nroOrden;
	}
	public void setNroOrden(String nroOrden) {
		this.nroOrden = nroOrden;
	}
	public String getConceptoRetencion() {
		return conceptoRetencion;
	}
	public void setConceptoRetencion(String conceptoRetencion) {
		this.conceptoRetencion = conceptoRetencion;
	}
	public String getFechaConferencia() {
		return fechaConferencia;
	}
	public void setFechaConferencia(String fechaConferencia) {
		this.fechaConferencia = fechaConferencia;
	}
	public String getFechaLiberacion() {
		return fechaLiberacion;
	}
	public void setFechaLiberacion(String fechaLiberacion) {
		this.fechaLiberacion = fechaLiberacion;
	}
	public String getHoraLiberacion() {
		return horaLiberacion;
	}
	public void setHoraLiberacion(String horaLiberacion) {
		this.horaLiberacion = horaLiberacion;
	}
	public String getFechaAsignacion() {
		return fechaAsignacion;
	}
	public void setFechaAsignacion(String fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}
	public String getHoraAsignacion() {
		return horaAsignacion;
	}
	public void setHoraAsignacion(String horaAsignacion) {
		this.horaAsignacion = horaAsignacion;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getPickTicket() {
		return pickTicket;
	}
	public void setPickTicket(String pickTicket) {
		this.pickTicket = pickTicket;
	}
	public String getEstadoPickTicket() {
		return estadoPickTicket;
	}
	public void setEstadoPickTicket(String estadoPickTicket) {
		this.estadoPickTicket = estadoPickTicket;
	}
	public String getNroCajas() {
		return nroCajas;
	}
	public void setNroCajas(String nroCajas) {
		this.nroCajas = nroCajas;
	}
	public String getTransportista() {
		return transportista;
	}
	public void setTransportista(String transportista) {
		this.transportista = transportista;
	}
	public String getFechaConsolidacion() {
		return fechaConsolidacion;
	}
	public void setFechaConsolidacion(String fechaConsolidacion) {
		this.fechaConsolidacion = fechaConsolidacion;
	}
	public String getHoraConsolidacion() {
		return horaConsolidacion;
	}
	public void setHoraConsolidacion(String horaConsolidacion) {
		this.horaConsolidacion = horaConsolidacion;
	}
	public String getCampana() {
		return campana;
	}
	public void setCampana(String campana) {
		this.campana = campana;
	}
	public String getHoraRetrasoWms() {
		return horaRetrasoWms;
	}
	public void setHoraRetrasoWms(String horaRetrasoWms) {
		this.horaRetrasoWms = horaRetrasoWms;
	}
	public String getHoraRetrasoConsolidado() {
		return horaRetrasoConsolidado;
	}
	public void setHoraRetrasoConsolidado(String horaRetrasoConsolidado) {
		this.horaRetrasoConsolidado = horaRetrasoConsolidado;
	}
	public ArrayList<AlertasDTO> getDetalle() {
		return detalle;
	}
	public void setDetalle(ArrayList<AlertasDTO> detalle) {
		this.detalle = detalle;
	}
}
