package votre.portaloperaciones.despachocatalogo.reportes.delegate;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.service.ServiceLocator;
import votre.portaloperaciones.despachocatalogo.reportes.beans.InformacionDTO;
import votre.portaloperaciones.despachocatalogo.reportes.beans.ReportesDTO;
import votre.portaloperaciones.despachocatalogo.reportes.facade.IReportesFacade;

public class ReportesBusiness {
	private final String claveFacade = "reportesFacade";
	private IReportesFacade reportesFacade;
	
	public ReportesBusiness() throws PersonalsoftException{
		String nombreServicio = Configuracion.getInstance().getServicio(claveFacade).getRuta();
		reportesFacade = (IReportesFacade) ServiceLocator.getInstance().lookup( nombreServicio );
	}
	
	public ReportesDTO generarExcel(InformacionDTO informacion,ReportesDTO reporte) throws PersonalsoftException{
		return reportesFacade.generarExcel(informacion, reporte);
	}
	
	public ReportesDTO generarLabel(ReportesDTO reporte) throws PersonalsoftException{
		return reportesFacade.generarLabel(reporte);
	}
	
	public InformacionDTO consultar(InformacionDTO informacion) throws PersonalsoftException{
		return reportesFacade.consultar(informacion);
	}
}
