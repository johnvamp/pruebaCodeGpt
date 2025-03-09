package votre.portaloperaciones.transportistas.beans;

public class TransportistasDTO {

	private String numeroCampana;
	private String campania;
	private String numeroOrden;
	private String numeroGuia;
	private String cedula;
	private String nombre;
	private String fechaEmbarque;
	private String horaEmbarque;
	private String primeraVisita;
	private String segundaVisita;
	private String terceraVisita;
	private String observaciones;

	private String numeroTel1;
	private String numeroTel2;
	private String numeroZona;
	private String departamento;
	private String ciudad;
	private String direccion;
	private String numeroGuiaMas;
	private String porte;
	private String valor;
	private String fecEnt;
	private String horEnt;

	private String pCia;
	private String pEst;
	private String pUsr;
	private String pTipoConsult;

	private String nomTranspo;
	private String totalPedi;
	private String error;
	private String msgError;

	private String estadoCombo;
	private String codigoCombo;

	private String guiaBuscar;
	
	public TransportistasDTO() {
		super();
	}

	public TransportistasDTO(String pCia, String pEst, String pUsr, String numeroCampana, String numeroZona, 
			String pTipoConsult, String valor) {
		super();
		this.pCia = pCia;
		this.pEst = pEst;
		this.pUsr = pUsr;
		this.numeroCampana = numeroCampana;
		this.numeroZona = numeroZona;
		this.pTipoConsult = pTipoConsult;
		this.valor = valor;
	}

	public String getpTipoConsult() {
		return pTipoConsult;
	}

	public void setpTipoConsult(String pTipoConsult) {
		this.pTipoConsult = pTipoConsult;
	}

	public String getGuiaBuscar() {
		return guiaBuscar;
	}

	public void setGuiaBuscar(String guiaBuscar) {
		this.guiaBuscar = guiaBuscar;
	}

	public String getEstadoCombo() {
		return estadoCombo;
	}

	public void setEstadoCombo(String estadoCombo) {
		this.estadoCombo = estadoCombo;
	}

	public String getCodigoCombo() {
		return codigoCombo;
	}

	public void setCodigoCombo(String codigoCombo) {
		this.codigoCombo = codigoCombo;
	}

	public String getNumeroTel1() {
		return numeroTel1;
	}

	public void setNumeroTel1(String numeroTel1) {
		this.numeroTel1 = numeroTel1;
	}

	public String getNumeroTel2() {
		return numeroTel2;
	}

	public void setNumeroTel2(String numeroTel2) {
		this.numeroTel2 = numeroTel2;
	}

	public String getNumeroZona() {
		return numeroZona;
	}

	public void setNumeroZona(String numeroZona) {
		this.numeroZona = numeroZona;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNumeroGuiaMas() {
		return numeroGuiaMas;
	}

	public void setNumeroGuiaMas(String numeroGuiaMas) {
		this.numeroGuiaMas = numeroGuiaMas;
	}

	public String getPorte() {
		return porte;
	}

	public void setPorte(String porte) {
		this.porte = porte;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getFecEnt() {
		return fecEnt;
	}

	public void setFecEnt(String fecEnt) {
		this.fecEnt = fecEnt;
	}

	public String getHorEnt() {
		return horEnt;
	}

	public void setHorEnt(String horEnt) {
		this.horEnt = horEnt;
	}

	public String getNomTranspo() {
		return nomTranspo;
	}

	public void setNomTranspo(String nomTranspo) {
		this.nomTranspo = nomTranspo;
	}

	public String getTotalPedi() {
		return totalPedi;
	}

	public void setTotalPedi(String totalPedi) {
		this.totalPedi = totalPedi;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMsgError() {
		return msgError;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public String getpCia() {
		return pCia;
	}

	public void setpCia(String pCia) {
		this.pCia = pCia;
	}

	public String getpEst() {
		return pEst;
	}

	public void setpEst(String pEst) {
		this.pEst = pEst;
	}

	public String getpUsr() {
		return pUsr;
	}

	public void setpUsr(String pUsr) {
		this.pUsr = pUsr;
	}

	public String getNumeroCampana() {
		return numeroCampana;
	}

	public void setNumeroCampana(String numeroCampana) {
		this.numeroCampana = numeroCampana;
	}

	public String getCampania() {
		return campania;
	}

	public void setCampania(String campania) {
		this.campania = campania;
	}

	public String getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public String getNumeroGuia() {
		return numeroGuia;
	}

	public void setNumeroGuia(String numeroGuia) {
		this.numeroGuia = numeroGuia;
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

	public String getFechaEmbarque() {
		return fechaEmbarque;
	}

	public void setFechaEmbarque(String fechaEmbarque) {
		this.fechaEmbarque = fechaEmbarque;
	}

	public String getHoraEmbarque() {
		return horaEmbarque;
	}

	public void setHoraEmbarque(String horaEmbarque) {
		this.horaEmbarque = horaEmbarque;
	}

	public String getPrimeraVisita() {
		return primeraVisita;
	}

	public void setPrimeraVisita(String primeraVisita) {
		this.primeraVisita = primeraVisita;
	}

	public String getSegundaVisita() {
		return segundaVisita;
	}

	public void setSegundaVisita(String segundaVisita) {
		this.segundaVisita = segundaVisita;
	}

	public String getTerceraVisita() {
		return terceraVisita;
	}

	public void setTerceraVisita(String terceraVisita) {
		this.terceraVisita = terceraVisita;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
