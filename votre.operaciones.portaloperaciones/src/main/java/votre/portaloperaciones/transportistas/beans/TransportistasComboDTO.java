package votre.portaloperaciones.transportistas.beans;

import java.util.LinkedHashMap;

public class TransportistasComboDTO {

	private String dsCia;
	private String dsTipo;

	private LinkedHashMap<String, String> dsEstaCombo;

	private String dsEsta;
	private String dsCod;

	private String dsDescr;

	public LinkedHashMap<String, String> getDsEstaCombo() {
		return dsEstaCombo;
	}

	public void setDsEstaCombo(LinkedHashMap<String, String> dsEstaCombo) {
		this.dsEstaCombo = dsEstaCombo;
	}

	public String getDsCia() {
		return dsCia;
	}

	public void setDsCia(String dsCia) {
		this.dsCia = dsCia;
	}

	public String getDsTipo() {
		return dsTipo;
	}

	public void setDsTipo(String dsTipo) {
		this.dsTipo = dsTipo;
	}

	public String getDsEsta() {
		return dsEsta;
	}

	public void setDsEsta(String dsEsta) {
		this.dsEsta = dsEsta;
	}

	public String getDsCod() {
		return dsCod;
	}

	public void setDsCod(String dsCod) {
		this.dsCod = dsCod;
	}

	public String getDsDescr() {
		return dsDescr;
	}

	public void setDsDescr(String dsDescr) {
		this.dsDescr = dsDescr;
	}

}
