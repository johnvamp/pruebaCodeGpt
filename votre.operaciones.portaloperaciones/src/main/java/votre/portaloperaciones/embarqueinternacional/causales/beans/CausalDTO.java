package votre.portaloperaciones.embarqueinternacional.causales.beans;

import java.io.Serializable;

public class CausalDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CausalDTO() {
	}
	
	private String codigo;
	private String causal;

	public String getCausal() {
		return causal;
	}
	public void setCausal(String causal) {
		this.causal = causal;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
