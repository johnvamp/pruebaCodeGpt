package votre.portaloperaciones.despachocatalogo.labels.facade;

import java.util.ArrayList;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.portaloperaciones.despachocatalogo.labels.beans.DespachoDTO;
import votre.portaloperaciones.despachocatalogo.labels.beans.LabelsDTO;
import votre.portaloperaciones.despachocatalogo.labels.beans.ZonasDTO;

public interface ILabelsFacade {

	public LabelsDTO cargarLabels(LabelsDTO labels) throws PersonalsoftException;
	public DespachoDTO consultar(LabelsDTO labels) throws PersonalsoftException;
	public ArrayList<ZonasDTO> cargarZonas(LabelsDTO labels) throws PersonalsoftException;
} 
