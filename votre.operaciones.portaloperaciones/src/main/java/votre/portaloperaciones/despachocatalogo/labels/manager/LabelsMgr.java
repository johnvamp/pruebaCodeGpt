package votre.portaloperaciones.despachocatalogo.labels.manager;

import java.util.ArrayList;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.dao.DAOFactory;
import votre.portaloperaciones.despachocatalogo.labels.beans.DespachoDTO;
import votre.portaloperaciones.despachocatalogo.labels.beans.LabelsDTO;
import votre.portaloperaciones.despachocatalogo.labels.beans.ZonasDTO;

public class LabelsMgr {
	
	public LabelsDTO cargarLabels(DAOFactory factory, LabelsDTO labels) throws PersonalsoftException{
		return factory.getLabels().cargarLabels(labels);
	}
	
	public DespachoDTO consultar(DAOFactory factory, LabelsDTO labels) throws PersonalsoftException{
		return factory.getLabels().consultar(labels);
	}
	
	public ArrayList<ZonasDTO> cargarZonas(DAOFactory factory, LabelsDTO labels) throws PersonalsoftException{
		return factory.getLabels().cargarZonas(labels);
	}
		
}
