package votre.portaloperaciones.consultasku.bodegas.facade;

import java.util.ArrayList;

import co.com.personalsoft.base.excepcion.PersonalsoftException;

import votre.portaloperaciones.consultasku.bodegas.beans.BodegaDTO;

public interface IBodegaFacade {

	public ArrayList<BodegaDTO> consultarBodegas(String codCia) throws PersonalsoftException;
}
