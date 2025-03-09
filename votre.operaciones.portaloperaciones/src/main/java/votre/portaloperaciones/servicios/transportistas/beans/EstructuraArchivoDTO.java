package votre.portaloperaciones.servicios.transportistas.beans;

public class EstructuraArchivoDTO {
	
	private String nombre;
	private String descripcion;
	private String tipoCampo; //A: Alfanumerico; S, P: Numerico
	private int numeroDecimales;
	private int numeroEnteros;
	private int tamanoCampo;
	private int posicionInicial;
	
	public EstructuraArchivoDTO() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipoCampo() {
		return tipoCampo;
	}

	public void setTipoCampo(String tipoCampo) {
		this.tipoCampo = tipoCampo;
	}

	public int getNumeroDecimales() {
		return numeroDecimales;
	}

	public void setNumeroDecimales(int numeroDecimales) {
		this.numeroDecimales = numeroDecimales;
	}

	public int getNumeroEnteros() {
		return numeroEnteros;
	}

	public void setNumeroEnteros(int numeroEnteros) {
		this.numeroEnteros = numeroEnteros;
	}

	public int getTamanoCampo() {
		return tamanoCampo;
	}

	public void setTamanoCampo(int tamanoCampo) {
		this.tamanoCampo = tamanoCampo;
	}

	public int getPosicionInicial() {
		return posicionInicial;
	}

	public void setPosicionInicial(int posicionInicial) {
		this.posicionInicial = posicionInicial;
	}
	
}
