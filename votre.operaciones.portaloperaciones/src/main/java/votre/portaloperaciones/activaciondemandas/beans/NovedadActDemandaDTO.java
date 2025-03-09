package votre.portaloperaciones.activaciondemandas.beans;

public class NovedadActDemandaDTO {

	private RefeDemandaDTO refeDemandaDTO;
	private CmpDemandaDTO cmpDemandaDTO;
	private String cia;
	private String motivo;
	private String dsMotivo;
	private String area;
	private String dsArea;
	private int cantidad;
	private String tipo;
	private String usuario;
	private String status;
	private String dsStatus;

	public NovedadActDemandaDTO() {
	}

	public String getCia() {
		return cia;
	}

	public void setCia(String cia) {
		this.cia = cia;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public CmpDemandaDTO getCmpDemandaDTO() {
		return cmpDemandaDTO;
	}

	public void setCmpDemandaDTO(CmpDemandaDTO cmpDemandaDTO) {
		this.cmpDemandaDTO = cmpDemandaDTO;
	}

	public String getDsArea() {
		return dsArea;
	}

	public void setDsArea(String dsArea) {
		this.dsArea = dsArea;
	}

	public String getDsMotivo() {
		return dsMotivo;
	}

	public void setDsMotivo(String dsMotivo) {
		this.dsMotivo = dsMotivo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public RefeDemandaDTO getRefeDemandaDTO() {
		return refeDemandaDTO;
	}

	public void setRefeDemandaDTO(RefeDemandaDTO refeDemandaDTO) {
		this.refeDemandaDTO = refeDemandaDTO;
	}

	public String getDsStatus() {
		return dsStatus;
	}

	public void setDsStatus(String dsStatus) {
		this.dsStatus = dsStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
