
package votre.portaloperaciones.flujowms.beans;

public class FlujoWmsDTO {

	private String idCompania;
	private String idTipoFlujoWms;
	
	private String orden;
	private String estado;
	private String pickt;
	private String fecha;
	private String hora;
	private String cedula;
	private String nombre;
	private String proceso;
	
	private String tipoCom;
	private String transpo;
	private String zona;
	private String mp;
	private String mailPlan;
	private String region; 
	
	private long fechahoraDT;
	
	

	
	public String getIdCompania() {
		return idCompania;
	}
	public void setIdCompania(String idCompania) {
		this.idCompania = idCompania;
	}
	public String getIdTipoFlujoWms() {
		return idTipoFlujoWms;
	}
	public void setIdTipoFlujoWms(String idTipoFlujoWms) {
		this.idTipoFlujoWms = idTipoFlujoWms;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
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
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public String getPickt() {
		return pickt;
	}
	public void setPickt(String pickt) {
		this.pickt = pickt;
	}
	public String getProceso() {
		return proceso;
	}
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}
	public long getFechahoraDT() {
		return fechahoraDT;
	}
	public void setFechahoraDT(long fechahoraDT) {
		this.fechahoraDT = fechahoraDT;
	}
	public String getMp() {
		return mp;
	}
	public void setMp(String mp) {
		this.mp = mp;
	}
	public String getTipoCom() {
		return tipoCom;
	}
	public void setTipoCom(String tipoCom) {
		this.tipoCom = tipoCom;
	}
	public String getTranspo() {
		return transpo;
	}
	public void setTranspo(String transpo) {
		this.transpo = transpo;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public String getMailPlan() {
		return mailPlan;
	}
	public void setMailPlan(String mailPlan) {
		this.mailPlan = mailPlan;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
}
