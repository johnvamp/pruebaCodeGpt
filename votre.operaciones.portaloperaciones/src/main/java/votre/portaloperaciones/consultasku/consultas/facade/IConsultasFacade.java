package votre.portaloperaciones.consultasku.consultas.facade;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.portaloperaciones.consultasku.consultas.beans.ConsultasDTO;

public interface IConsultasFacade {

	public ConsultasDTO consultasReferencias(ConsultasDTO consultasDTO) throws PersonalsoftException;
	public ConsultasDTO consultarPrecios(ConsultasDTO consultasDTO) throws PersonalsoftException;
	public ConsultasDTO consultarAuditoria(ConsultasDTO consultasDTO) throws PersonalsoftException;
	public ConsultasDTO consultarDescripcionSKU(ConsultasDTO consultasDTO) throws PersonalsoftException;
}
