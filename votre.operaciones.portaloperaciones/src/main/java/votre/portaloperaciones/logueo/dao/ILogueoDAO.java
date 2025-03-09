package votre.portaloperaciones.logueo.dao;

import java.util.ArrayList;

import votre.portaloperaciones.logueo.beans.LogueoDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public interface ILogueoDAO {

	public ArrayList<LogueoDTO> consultarPaises(LogueoDTO logueoDTO) throws PersonalsoftException;
}
