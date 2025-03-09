package votre.portaloperaciones.indicadordepedidos.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import votre.portaloperaciones.indicadordepedidos.beans.ParametrosCiudadDTO;
import votre.portaloperaciones.indicadordepedidos.delegate.IndicadorPedidosBusiness;
import votre.portaloperaciones.util.Constantes;

public class ConsultarCiudadCombo implements IBaseCmd {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		
		ParametrosCiudadDTO paramCiudad = new ParametrosCiudadDTO();
		
		IndicadorPedidosBusiness indicadorPedidosBusiness = new IndicadorPedidosBusiness();
		
		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		String codDpto = req.getParameter("idDepartamentoSelect");
		
		if(codDpto == null || codDpto.equals("")) {
			throw new PersonalsoftException("seleccione un Departamento");
		}
		
		paramCiudad.setCodCia(codCia);
		paramCiudad.setCodDpto(codDpto);
		ParametrosCiudadDTO cidades = indicadorPedidosBusiness.consultarCiudades (paramCiudad);
		req.setAttribute("ciudades", cidades.getCiudades());
		req.setAttribute("idOpcSelect", req.getParameter("idOpcSelect"));
		req.setAttribute("idDepartamentoSelect", codDpto);
		req.setAttribute("idEstadoInicial", req.getParameter("idEstadoInicial"));
		req.setAttribute("idEstadoFinal", req.getParameter("idEstadoFinal"));
		req.setAttribute("campanaTBox", req.getParameter("campanaTBox"));
		
	}

}
