package votre.portaloperaciones.embarqueinternacional.causales.manager;

import java.util.ArrayList;

import co.com.personalsoft.base.excepcion.PersonalsoftException;

import votre.dao.DAOFactory;
import votre.portaloperaciones.embarqueinternacional.causales.beans.CausalDTO;

public class CausalMgr {

	public ArrayList<CausalDTO> cargarCausales(DAOFactory factory, String codCia) throws PersonalsoftException{
		return factory.getCausal().cargarCausales(codCia);
	}
}
