package votre.portaloperaciones.despachocatalogo.embarquecatalogo.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class TransportadorasDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codCia;
	private String accion;
	private String status;
	private String dsstatus;
	
	//Resulset
	private String codigoTrans;
	private String transportadora;
	private ArrayList<TransportadorasDTO> arrTrans;
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDsstatus() {
		return dsstatus;
	}
	public void setDsstatus(String dsstatus) {
		this.dsstatus = dsstatus;
	}
	public String getCodigoTrans() {
		return codigoTrans;
	}
	public void setCodigoTrans(String codigoTrans) {
		this.codigoTrans = codigoTrans;
	}
	public String getTransportadora() {
		return transportadora;
	}
	public void setTransportadora(String transportadora) {
		this.transportadora = transportadora;
	}
	public ArrayList<TransportadorasDTO> getArrTrans() {
		return arrTrans;
	}
	public void setArrTrans(ArrayList<TransportadorasDTO> arrTrans) {
		this.arrTrans = arrTrans;
	}

}
