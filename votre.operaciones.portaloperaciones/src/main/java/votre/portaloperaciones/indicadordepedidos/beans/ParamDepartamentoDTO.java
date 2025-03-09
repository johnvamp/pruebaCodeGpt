package votre.portaloperaciones.indicadordepedidos.beans;

import java.io.Serializable;
import java.util.List;

public class ParamDepartamentoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codCia;
	private List<ResultadoComboDTO> departamentos;

	public String getCodCia() {
		return codCia;
	}

	public void setCodCia(String codCia) {
		this.codCia = codCia;
	}

	public List<ResultadoComboDTO> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<ResultadoComboDTO> departamentos) {
		this.departamentos = departamentos;
	}
	
}
