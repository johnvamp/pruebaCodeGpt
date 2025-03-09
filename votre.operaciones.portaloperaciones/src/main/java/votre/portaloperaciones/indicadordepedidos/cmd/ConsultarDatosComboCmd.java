package votre.portaloperaciones.indicadordepedidos.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import votre.portaloperaciones.indicadordepedidos.beans.DatosComboDTO;
import votre.portaloperaciones.indicadordepedidos.beans.ParamDepartamentoDTO;
import votre.portaloperaciones.indicadordepedidos.beans.ParametrosCiudadDTO;
import votre.portaloperaciones.indicadordepedidos.delegate.IndicadorPedidosBusiness;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class ConsultarDatosComboCmd implements IBaseCmd {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		
		DatosComboDTO datosComboDTO = new DatosComboDTO();
		IndicadorPedidosBusiness indicadorPedidosBusiness;
		indicadorPedidosBusiness = new IndicadorPedidosBusiness();
		
		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		
		datosComboDTO.setCodCia(codCia);
		datosComboDTO.setAccion(Constantes.ACCION_ESTADOS);
		datosComboDTO = indicadorPedidosBusiness.consultarDatosCombo(datosComboDTO);
		req.setAttribute("estados", datosComboDTO.getListado());
		
		datosComboDTO = new DatosComboDTO();
		datosComboDTO.setCodCia(codCia);
		datosComboDTO.setAccion(Constantes.ACCION_REGIONES);
		datosComboDTO = indicadorPedidosBusiness.consultarDatosCombo(datosComboDTO);
		req.setAttribute("regiones", datosComboDTO.getListado());
		
		datosComboDTO = new DatosComboDTO();
		datosComboDTO.setCodCia(codCia);
		datosComboDTO.setAccion(Constantes.ACCION_COMPRADORAS);
		datosComboDTO = indicadorPedidosBusiness.consultarDatosCombo(datosComboDTO);
		req.setAttribute("compradoras", datosComboDTO.getListado());
		
		ParamDepartamentoDTO paramDepartamento = new ParamDepartamentoDTO();
		paramDepartamento.setCodCia(codCia);
		ParamDepartamentoDTO departamentos = indicadorPedidosBusiness.consultarDepartamentos(paramDepartamento);
		req.setAttribute("departamentos", departamentos.getDepartamentos());
	}
}
