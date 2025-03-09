package votre.portaloperaciones.suscripcioncatalogo.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import votre.portaloperaciones.suscripcioncatalogo.beans.SuscripcionCatalogoDTO;
import votre.portaloperaciones.util.Constantes;
import votre.utils.moduloseguridad.beans.UsuarioDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class VerCancelarCmd implements IBaseCmd {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {

		SuscripcionCatalogoDTO dto = null;
		dto = dtoAssembler(req);
		
		req.setAttribute("datos", dto);
	}
	
	private SuscripcionCatalogoDTO dtoAssembler(HttpServletRequest req) {
		SuscripcionCatalogoDTO dto = new SuscripcionCatalogoDTO();

		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		UsuarioDTO usuarioDTO = (UsuarioDTO) req.getSession().getAttribute(Constantes.USUARIO_SESION);

		String cedula = req.getParameter("cedula") != null ? req.getParameter("cedula") : "0";
		String cantidad = req.getParameter("cantidad") != null ? req.getParameter("cantidad") : "0";
		
		dto.setCodCia(Constantes.getCodInternacional(codCia));
		dto.setIdCompradora(cedula);
		dto.setNumCatalogos(cantidad);
		dto.setUsuario(usuarioDTO.getUsuario());
		dto.setMedioIngreso(Constantes.SUSCRIPCIONES_MEDIO);
		
		return dto;
	}

}
