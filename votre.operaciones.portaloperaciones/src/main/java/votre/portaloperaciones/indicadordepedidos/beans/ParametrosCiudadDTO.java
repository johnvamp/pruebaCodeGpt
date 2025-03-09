package votre.portaloperaciones.indicadordepedidos.beans;

import java.io.Serializable;
import java.util.List;

public class ParametrosCiudadDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codCia;
	private String codDpto;
	private List<ResultadoComboDTO> ciudades;


	public String getCodCia() {
		return codCia;
	}

	public void setCodCia(String codCia) {
		this.codCia = codCia;
	}

	public String getCodDpto() {
		return codDpto;
	}

	public void setCodDpto(String codDpto) {
		this.codDpto = codDpto;
	}

	public List<ResultadoComboDTO> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<ResultadoComboDTO> ciudades) {
		this.ciudades = ciudades;
	}
	
}
