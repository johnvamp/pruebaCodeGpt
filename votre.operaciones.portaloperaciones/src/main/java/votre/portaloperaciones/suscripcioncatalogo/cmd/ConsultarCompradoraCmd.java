package votre.portaloperaciones.suscripcioncatalogo.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.suscripcioncatalogo.beans.SuscripcionCatalogoDTO;
import votre.portaloperaciones.suscripcioncatalogo.delegate.SuscripcionesBusiness;
import votre.portaloperaciones.util.Constantes;
import votre.utils.moduloseguridad.beans.UsuarioDTO;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class ConsultarCompradoraCmd implements IBaseCmd {

	private Logger logger = Logger.getLogger(this.getClass());
	PersonalsoftException ps = null;
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		SuscripcionesBusiness suscripcionesBusiness = null;
		SuscripcionCatalogoDTO dto = null;
		String mensaje = "";
		String siguienteRuta = "suscripciones.jspSolicitudId.do";
		try{
			suscripcionesBusiness = new SuscripcionesBusiness();
			dto = dtoAssembler(req);
			
			dto = suscripcionesBusiness.suscribir(dto);
			if(dto != null){
				if(Constantes.ERROR_SP.equals(dto.getNmStatus())){
					mensaje = dto.getDsStatus();
				}
				else{
					if(Constantes.SUSCRIPCIONES_ESTADO_NUEVA.equals(dto.getEstado())){
						siguienteRuta = "suscripciones.jspFormCatalogos.do";
						req.setAttribute("cedula", dto.getIdCompradora());
						req.setAttribute("accion", Constantes.SUSCRIPCIONES_ACCION_INGRESAR);
					}
					if(Constantes.SUSCRIPCIONES_ESTADO_VIGENTE.equals(dto.getEstado())){
						siguienteRuta = "suscripciones.jspFormCatalogos.do";
						req.setAttribute("cedula", dto.getIdCompradora());
						req.setAttribute("cantidad", dto.getNumCatalogos());
						req.setAttribute("accion", Constantes.SUSCRIPCIONES_ACCION_ACTUALIZAR);
					}
				}
			}
			req.setAttribute("mensaje", mensaje);
			Configuracion.getInstance().getContext().getRequestDispatcher(siguienteRuta).forward(req, res);
		}
		catch (Exception e) {
			ps = new PersonalsoftException(e);
			logger.error(ps.getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
			throw ps;
		}
	}
	
	private SuscripcionCatalogoDTO dtoAssembler(HttpServletRequest req) {
		SuscripcionCatalogoDTO dto = new SuscripcionCatalogoDTO();

		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		UsuarioDTO usuarioDTO = (UsuarioDTO) req.getSession().getAttribute(Constantes.USUARIO_SESION);

		String cedula = req.getParameter("cedula") != null ? req.getParameter("cedula") : "0";
		
		dto.setCodCia(Constantes.getCodInternacional(codCia));
		dto.setIdCompradora(cedula);
		dto.setUsuario(usuarioDTO.getUsuario());
		dto.setMedioIngreso(Constantes.SUSCRIPCIONES_MEDIO);
		dto.setAccion(Constantes.SUSCRIPCIONES_ACCION_CONSULTAR);
		
		return dto;
	}

}
