package votre.portaloperaciones.indicadordepedidos.facade;

import java.util.ArrayList;

import votre.portaloperaciones.indicadordepedidos.beans.DatosComboDTO;
import votre.portaloperaciones.indicadordepedidos.beans.IndicadorPedidosDTO;
import votre.portaloperaciones.indicadordepedidos.beans.ParamDepartamentoDTO;
import votre.portaloperaciones.indicadordepedidos.beans.ParametrosCiudadDTO;
import votre.portaloperaciones.indicadordepedidos.beans.PedidosPorRangoDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public interface IIndicadorPedidosFacade {

	public ArrayList<IndicadorPedidosDTO> indicadorPedidosGraf1 (IndicadorPedidosDTO indicadorPedidosDTO) throws PersonalsoftException;  
	public DatosComboDTO consultarDatosCombo (DatosComboDTO datosComboDTO) throws PersonalsoftException;
	public ArrayList<PedidosPorRangoDTO> pedidosPorRango (PedidosPorRangoDTO pedidosPorRangoDTO) throws PersonalsoftException;  
	public ParametrosCiudadDTO consultarCiudades (ParametrosCiudadDTO parametrosCiudad) throws PersonalsoftException;  
	public ParamDepartamentoDTO consultarDepartamentos (ParamDepartamentoDTO paramDepartamento) throws PersonalsoftException;
}