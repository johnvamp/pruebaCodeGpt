package votre.portaloperaciones.despachocatalogo.embarquecatalogo.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class GuiasEmbarqueDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codCia;
	private String codTrans;
	private String nroguia;
	private String status;
	private String dsStatus;
	
	private ArrayList<GuiasEmbarqueDTO> arrResumen;
	
	public String getCodCia() {
		return codCia;
	}
	public void setCodCia(String codCia) {
		this.codCia = codCia;
	}
	public String getCodTrans() {
		return codTrans;
	}
	public void setCodTrans(String codTrans) {
		this.codTrans = codTrans;
	}
	public String getNroguia() {
		return nroguia;
	}
	public void setNroguia(String nroguia) {
		this.nroguia = nroguia;
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
	public ArrayList<GuiasEmbarqueDTO> getArrResumen() {
		return arrResumen;
	}
	public void setArrResumen(ArrayList<GuiasEmbarqueDTO> arrResumen) {
		this.arrResumen = arrResumen;
	}
}
