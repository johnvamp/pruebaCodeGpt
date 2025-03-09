package votre.portaloperaciones.solicitudcatalogo.referencia.manager;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.dao.DAOFactory;
import votre.portaloperaciones.solicitudcatalogo.referencia.beans.AuditoriaDTO;
import votre.portaloperaciones.solicitudcatalogo.referencia.beans.ReferenciaDTO;

public class ReferenciaMgr {

	public ReferenciaDTO verInformacionReferencia(DAOFactory factory, ReferenciaDTO referencia) throws PersonalsoftException{
		return factory.getReferencia().verInformacionReferencia(referencia);
	}
	
	public AuditoriaDTO verAuditoriaCatalogo(DAOFactory factory, ReferenciaDTO referencia) throws PersonalsoftException{
		return factory.getReferencia().verAuditoriaCatalogo(referencia);
	}
}
