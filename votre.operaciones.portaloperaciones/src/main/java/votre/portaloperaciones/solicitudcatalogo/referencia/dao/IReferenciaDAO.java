package votre.portaloperaciones.solicitudcatalogo.referencia.dao;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.portaloperaciones.solicitudcatalogo.referencia.beans.AuditoriaDTO;
import votre.portaloperaciones.solicitudcatalogo.referencia.beans.ReferenciaDTO;

public interface IReferenciaDAO {

	public ReferenciaDTO verInformacionReferencia(ReferenciaDTO referencia) throws PersonalsoftException;
	public AuditoriaDTO verAuditoriaCatalogo(ReferenciaDTO referencia) throws PersonalsoftException;
}
