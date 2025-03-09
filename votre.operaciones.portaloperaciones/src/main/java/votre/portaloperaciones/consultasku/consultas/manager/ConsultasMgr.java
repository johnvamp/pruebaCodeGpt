package votre.portaloperaciones.consultasku.consultas.manager;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.dao.DAOFactory;
import votre.portaloperaciones.consultasku.consultas.beans.ConsultasDTO;

public class ConsultasMgr {

	public ConsultasDTO consultarReferencias(DAOFactory factory,ConsultasDTO consultasDTO) throws PersonalsoftException{
		return factory.getConsultas().consultarReferencias(consultasDTO);
	}
	
	public ConsultasDTO consultarPrecios(DAOFactory factory, ConsultasDTO consultasDTO) throws PersonalsoftException{
		return factory.getConsultas().consultarPrecios(consultasDTO);
	}
	
	public ConsultasDTO consultarAuditoria(DAOFactory factory, ConsultasDTO consultasDTO) throws PersonalsoftException{
		return factory.getConsultas().consultarAuditoria(consultasDTO);
	}
	
	public ConsultasDTO consultarDescripcionSKU(DAOFactory factory, ConsultasDTO consultasDTO) throws PersonalsoftException{
		return factory.getConsultas().consultarDescripcionSKU(consultasDTO);
	}
}
