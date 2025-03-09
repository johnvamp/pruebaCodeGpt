package votre.portaloperaciones.indicadordepedidos.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class DatosComboDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	public DatosComboDTO() {
	}
	
	private String codCia;
	private String accion;
	
	private ArrayList<String> listado;

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

	public ArrayList<String> getListado() {
		return listado;
	}

	public void setListado(ArrayList<String> listado) {
		this.listado = listado;
	}
}
