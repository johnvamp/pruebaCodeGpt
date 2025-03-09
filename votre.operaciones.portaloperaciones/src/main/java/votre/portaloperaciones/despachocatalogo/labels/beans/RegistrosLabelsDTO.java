package votre.portaloperaciones.despachocatalogo.labels.beans;

public class RegistrosLabelsDTO {
	
	private String codigo;
	private String tituloLabel;
	private String cantidad;
	private String indFecha;
	private String indGnLabels;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getIndFecha() {
		return indFecha;
	}
	public void setIndFecha(String indFecha) {
		this.indFecha = indFecha;
	}
	public String getIndGnLabels() {
		return indGnLabels;
	}
	public void setIndGnLabels(String indGnLabels) {
		this.indGnLabels = indGnLabels;
	}	
	public String getTituloLabel() {
		return tituloLabel;
	}
	public void setTituloLabel(String tituloLabel) {
		this.tituloLabel = tituloLabel;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
}
