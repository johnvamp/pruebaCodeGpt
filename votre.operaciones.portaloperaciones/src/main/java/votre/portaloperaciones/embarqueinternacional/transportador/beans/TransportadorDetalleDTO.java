package votre.portaloperaciones.embarqueinternacional.transportador.beans;

public class TransportadorDetalleDTO {

	private String codigo;
	private String nombre;
	private String camion;
	private String placa;
	private String fecha;
	private String valorConcatenado;
	
	public String getValorConcatenado() {
		return valorConcatenado;
	}
	public void setValorConcatenado(String valorConcatenado) {
		this.valorConcatenado = valorConcatenado;
	}
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
	public String getCamion() {
		return camion;
	}
	public void setCamion(String camion) {
		this.camion = camion;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
}
