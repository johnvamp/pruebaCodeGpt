package votre.portaloperaciones.despachocatalogo.labels.delegate;

import java.util.ArrayList;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.service.ServiceLocator;
import votre.portaloperaciones.despachocatalogo.labels.beans.DespachoDTO;
import votre.portaloperaciones.despachocatalogo.labels.beans.LabelsDTO;
import votre.portaloperaciones.despachocatalogo.labels.beans.ZonasDTO;
import votre.portaloperaciones.despachocatalogo.labels.facade.ILabelsFacade;

public class LabelsBusiness {
	private final String claveFacade = "labelsFacade";
	private ILabelsFacade labelsFacade;
	
	public LabelsBusiness() throws PersonalsoftException{
		String nombreServicio = Configuracion.getInstance().getServicio(claveFacade).getRuta();
		labelsFacade = (ILabelsFacade) ServiceLocator.getInstance().lookup( nombreServicio );
	}
	
	public LabelsDTO cargarLabels(LabelsDTO labels) throws PersonalsoftException{
		return labelsFacade.cargarLabels(labels);
	}
	
	public DespachoDTO consultar(LabelsDTO labels) throws PersonalsoftException{
		return labelsFacade.consultar(labels);
	}
	
	public ArrayList<ZonasDTO> cargarZonas(LabelsDTO labels) throws PersonalsoftException{
		return labelsFacade.cargarZonas(labels);
	}
}
