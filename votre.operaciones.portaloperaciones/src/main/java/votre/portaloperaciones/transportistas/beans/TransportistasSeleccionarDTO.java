package votre.portaloperaciones.transportistas.beans;

import java.util.ArrayList;
import java.util.Map;

public class TransportistasSeleccionarDTO {

	private String dsCia;
	private String dsTipo;
	private String dsCampana;
	private Map<String, String> dsListaTranspoCampana;
	private Map<String, String> dsListaTranspoEstado;
	private ArrayList<String> campanas;

	public Map<String, String> getDsListaTranspoEstado() {
		return dsListaTranspoEstado;
	}

	public void setDsListaTranspoEstado(Map<String, String> dsListaTranspoEstado) {
		this.dsListaTranspoEstado = dsListaTranspoEstado;
	}

	public ArrayList<String> getCampanas() {
		return campanas;
	}

	public void setCampanas(ArrayList<String> campanas) {
		this.campanas = campanas;
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

	public String getDsCampana() {
		return dsCampana;
	}

	public void setDsCampana(String dsCampana) {
		this.dsCampana = dsCampana;
	}

	public Map<String, String> getDsListaTranspoCampana() {
		return dsListaTranspoCampana;
	}

	public void setDsListaTranspoCampana(Map<String, String> dsListaTranspoCampana) {
		this.dsListaTranspoCampana = dsListaTranspoCampana;
	}

}
