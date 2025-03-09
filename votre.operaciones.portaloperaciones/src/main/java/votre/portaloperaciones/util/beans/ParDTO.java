package votre.portaloperaciones.util.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "par")
public class ParDTO implements Serializable{

	private String clave;
	private String valor;
	
	public ParDTO() {
	}
	
	public ParDTO(String clave, String valor) {
		this.clave = clave;
		this.valor = valor;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
