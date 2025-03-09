package votre.portaloperaciones.despachocatalogo.reportes.dao;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.portaloperaciones.despachocatalogo.reportes.beans.InformacionDTO;
import votre.portaloperaciones.despachocatalogo.reportes.beans.ReportesDTO;

public interface IReportesDAO {
	
	public ReportesDTO generarExcel(ReportesDTO reporte) throws PersonalsoftException; 
	public InformacionDTO consultar(InformacionDTO informacion) throws PersonalsoftException;
}
