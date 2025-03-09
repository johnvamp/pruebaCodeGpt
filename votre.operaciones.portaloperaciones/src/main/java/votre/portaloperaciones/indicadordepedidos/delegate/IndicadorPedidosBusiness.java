package votre.portaloperaciones.indicadordepedidos.delegate;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import votre.portaloperaciones.indicadordepedidos.beans.DatosComboDTO;
import votre.portaloperaciones.indicadordepedidos.beans.IndicadorPedidosDTO;
import votre.portaloperaciones.indicadordepedidos.beans.ParamDepartamentoDTO;
import votre.portaloperaciones.indicadordepedidos.beans.ParametrosCiudadDTO;
import votre.portaloperaciones.indicadordepedidos.beans.PedidosPorRangoDTO;
import votre.portaloperaciones.indicadordepedidos.facade.IIndicadorPedidosFacade;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.service.ServiceLocator;

public class IndicadorPedidosBusiness {

	private final String claveFacede = "IndicadorPedidos";
	private IIndicadorPedidosFacade IndicadorPedidosFacade;
	Logger logger = Logger.getLogger(this.getClass());
	
	public IndicadorPedidosBusiness() throws PersonalsoftException{
		String nombreServicio = Configuracion.getInstance().getServicio(claveFacede).getRuta();
		IndicadorPedidosFacade = (IIndicadorPedidosFacade) ServiceLocator.getInstance().lookup(nombreServicio);
	}
	
	public ArrayList<IndicadorPedidosDTO> IndicadorPedidosGraf1 (IndicadorPedidosDTO indicadorPedidosDTO) throws PersonalsoftException {
		return IndicadorPedidosFacade.indicadorPedidosGraf1(indicadorPedidosDTO);
	}
	
	public DatosComboDTO consultarDatosCombo (DatosComboDTO datosComboDTO) throws PersonalsoftException{
		return IndicadorPedidosFacade.consultarDatosCombo(datosComboDTO);
	}
	
	public ArrayList<PedidosPorRangoDTO> pedidosPorRango (PedidosPorRangoDTO pedidosPorRangoDTO) throws PersonalsoftException {
		return IndicadorPedidosFacade.pedidosPorRango(pedidosPorRangoDTO);
	}
	
	public ParametrosCiudadDTO consultarCiudades (ParametrosCiudadDTO parametrosCiudad) throws PersonalsoftException {
		return IndicadorPedidosFacade.consultarCiudades(parametrosCiudad);
	}
	
	public ParamDepartamentoDTO consultarDepartamentos (ParamDepartamentoDTO paramDepartamento) throws PersonalsoftException {
		return IndicadorPedidosFacade.consultarDepartamentos(paramDepartamento);
	}
}
