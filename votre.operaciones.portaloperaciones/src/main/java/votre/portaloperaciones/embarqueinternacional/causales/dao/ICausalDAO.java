package votre.portaloperaciones.embarqueinternacional.causales.dao;

import java.util.ArrayList;

import co.com.personalsoft.base.excepcion.PersonalsoftException;

import votre.portaloperaciones.embarqueinternacional.causales.beans.CausalDTO;

public interface ICausalDAO {

	public ArrayList<CausalDTO> cargarCausales(String codCia) throws PersonalsoftException;
}
