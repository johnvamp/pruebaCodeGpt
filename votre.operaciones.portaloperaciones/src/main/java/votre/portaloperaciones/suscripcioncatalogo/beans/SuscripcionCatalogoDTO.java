package votre.portaloperaciones.suscripcioncatalogo.beans;

public class SuscripcionCatalogoDTO {

	private String codCia;
	private String idCompradora;
	private String accion;
	private String usuario;
	private String medioIngreso;
	private String numCatalogos;
	private String estado;
	private String campana;
	
	private String dsStatus;
	private String nmStatus;
	
	public SuscripcionCatalogoDTO() {
	}

	public String getCodCia() {
		return codCia;
	}

	public void setCodCia(String codCia) {
		this.codCia = codCia;
	}

	public String getIdCompradora() {
		return idCompradora;
	}

	public void setIdCompradora(String idCompradora) {
		this.idCompradora = idCompradora;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getMedioIngreso() {
		return medioIngreso;
	}

	public void setMedioIngreso(String medioIngreso) {
		this.medioIngreso = medioIngreso;
	}

	public String getNumCatalogos() {
		return numCatalogos;
	}

	public void setNumCatalogos(String numCatalogos) {
		this.numCatalogos = numCatalogos;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCampana() {
		return campana;
	}

	public void setCampana(String campana) {
		this.campana = campana;
	}

	public String getDsStatus() {
		return dsStatus;
	}

	public void setDsStatus(String dsStatus) {
		this.dsStatus = dsStatus;
	}

	public String getNmStatus() {
		return nmStatus;
	}

	public void setNmStatus(String nmStatus) {
		this.nmStatus = nmStatus;
	}

}
