package votre.portaloperaciones.despachocatalogo.reportes.manager;


import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.dao.DAOFactory;
import votre.portaloperaciones.despachocatalogo.reportes.beans.InformacionDTO;
import votre.portaloperaciones.despachocatalogo.reportes.beans.ReportesDTO;

public class ReportesMgr {

	public ReportesDTO generarExcel(DAOFactory factory, ReportesDTO reporte) throws PersonalsoftException{
		return factory.getReportes().generarExcel(reporte);
	}
	
	public InformacionDTO consultar(DAOFactory factory, InformacionDTO informacion) throws PersonalsoftException{
		return factory.getReportes().consultar(informacion);
	}
}
