package votre.portaloperaciones.indicadordepedidos.beans;

import java.io.Serializable;

public class ResultadoComboDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigo;
	private String nombre;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
