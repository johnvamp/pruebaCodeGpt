package votre.portaloperaciones.despachocatalogo.reportes.facade;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.portaloperaciones.despachocatalogo.reportes.beans.InformacionDTO;
import votre.portaloperaciones.despachocatalogo.reportes.beans.ReportesDTO;

public interface IReportesFacade {

	public ReportesDTO generarExcel(InformacionDTO informacion,ReportesDTO reporte) throws PersonalsoftException;
	public ReportesDTO generarLabel(ReportesDTO reporte) throws PersonalsoftException;
	public InformacionDTO consultar(InformacionDTO informacion) throws PersonalsoftException;
	
}
