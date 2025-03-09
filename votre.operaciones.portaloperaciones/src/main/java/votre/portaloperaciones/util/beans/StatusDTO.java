package votre.portaloperaciones.util.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "salida")
public class StatusDTO implements Serializable{

	private String status;
	private String dsStatus;
	
	public StatusDTO() {
	}

	public StatusDTO(String status, String dsStatus) {
		super();
		this.status = status;
		this.dsStatus = dsStatus;
	}

	public String getDsStatus() {
		return dsStatus;
	}

	public void setDsStatus(String dsStatus) {
		this.dsStatus = dsStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
