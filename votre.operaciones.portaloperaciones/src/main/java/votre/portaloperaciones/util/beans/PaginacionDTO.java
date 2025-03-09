package votre.portaloperaciones.util.beans;

import java.io.Serializable;
import java.util.ArrayList;


public class PaginacionDTO implements Serializable{

	private int inicio;
	private int limite;
	private int totalRegistros;
	private ArrayList<ParDTO> enlaces;
	private ParDTO primero;
	private ParDTO ultimo;
	private ParDTO siguiente;
	private ParDTO anterior;
	private int paginaActual;
	
	public PaginacionDTO() {
		inicio = 0;
		limite = 15;
		totalRegistros = 0;
		enlaces = new ArrayList<ParDTO>();
		paginaActual = 1;
	}
	
	public int getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}

	public ParDTO getPrimero() {
		primero = null;
		if(enlaces!=null && !enlaces.isEmpty()){
			primero = enlaces.get(0);
		}
		return primero;
	}

	public void setPrimero(ParDTO primero) {
		this.primero = primero;
	}

	public ParDTO getUltimo() {
		ultimo = null;
		if(enlaces!=null && !enlaces.isEmpty()){
			ultimo = enlaces.get(enlaces.size()-1);
		}
		return ultimo;
	}

	public void setUltimo(ParDTO ultimo) {
		this.ultimo = ultimo;
	}

	public void definirPaginacion(int t){
		this.totalRegistros = t;
		int numEnlaces = 0;
		int posicion = 1;
		ParDTO parDTO = null;
		enlaces = new ArrayList<ParDTO>();
		if(totalRegistros > limite){
			numEnlaces = totalRegistros / limite;
			if((totalRegistros % limite) != 0){
				numEnlaces++;
			}
			for(int i = 1; i<=numEnlaces; i++){
				parDTO = new ParDTO();
				parDTO.setValor(""+i);
				parDTO.setClave(""+posicion);
				posicion += limite;
				enlaces.add(parDTO);
			}
		}
	}

	public int getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(int totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	public ArrayList<ParDTO> getEnlaces() {
		return enlaces;
	}

	public void setEnlaces(ArrayList<ParDTO> enlaces) {
		this.enlaces = enlaces;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public ParDTO getSiguiente() {
		siguiente = null;
		if(enlaces!=null && !enlaces.isEmpty()){
			if(paginaActual<enlaces.size()){
				siguiente = enlaces.get(paginaActual);
			}
			else{
				siguiente = getUltimo();
			}
		}
		return siguiente;
	}

	public void setSiguiente(ParDTO siguiente) {
		this.siguiente = siguiente;
	}

	public ParDTO getAnterior() {
		anterior = null;
		if(enlaces!=null && !enlaces.isEmpty()){
			if(paginaActual>1){
				anterior = enlaces.get(paginaActual-2);
			}
			else{
				anterior = getPrimero();
			}
		}
		return anterior;
	}

	public void setAnterior(ParDTO anterior) {
		this.anterior = anterior;
	}

}
