package votre.portaloperaciones.consultasku.bodegas.manager;

import java.util.ArrayList;

import co.com.personalsoft.base.excepcion.PersonalsoftException;

import votre.dao.DAOFactory;
import votre.portaloperaciones.consultasku.bodegas.beans.BodegaDTO;

public class BodegaMgr {

	public ArrayList<BodegaDTO> consultarBodegas(DAOFactory factory, String codCia) throws PersonalsoftException{
		return factory.getBodega().consultarBodegas(codCia);
	}
}
