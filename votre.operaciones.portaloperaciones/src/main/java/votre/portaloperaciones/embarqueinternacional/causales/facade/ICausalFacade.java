package votre.portaloperaciones.embarqueinternacional.causales.facade;

import java.util.ArrayList;

import co.com.personalsoft.base.excepcion.PersonalsoftException;

import votre.portaloperaciones.embarqueinternacional.causales.beans.CausalDTO;

public interface ICausalFacade {

	public ArrayList<CausalDTO> cargarCausales(String codCia) throws PersonalsoftException;
}
