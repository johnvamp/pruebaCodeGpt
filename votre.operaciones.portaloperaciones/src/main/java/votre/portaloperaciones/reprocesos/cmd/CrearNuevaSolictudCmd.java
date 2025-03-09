package votre.portaloperaciones.reprocesos.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.reprocesos.beans.SolicitudDTO;
import votre.portaloperaciones.reprocesos.facade.ReprocesosFacade;
import votre.portaloperaciones.util.Constantes;
import votre.utils.moduloseguridad.beans.UsuarioDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;
import co.com.personalsoft.base.util.Fecha;

public class CrearNuevaSolictudCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String fechaEntrega;
	private String tipoEntrega;
	private String observacion;
	private String usuario;
	private String mensaje;

	public void execute(HttpServletRequest req, HttpServletResponse res)throws PersonalsoftException {
		SolicitudDTO solicitudDTO = null;
		ReprocesosFacade reprocesosFacade = null;
		try{
			reprocesosFacade = new ReprocesosFacade();
			solicitudDTO = new SolicitudDTO();
			obtenerDatos(req);
			dtoAssembler(solicitudDTO);
			
			solicitudDTO = reprocesosFacade.crearNuevaSolicitud(solicitudDTO);
			if(solicitudDTO != null){
				if(solicitudDTO.getStatus().equals(Constantes.EXITO_SP)){
					req.setAttribute("numRequerimiento", solicitudDTO.getNumRequerimiento());
					fechaEntrega = Fecha.cambiarFormatoFecha(fechaEntrega, Fecha.EXPR_YYYYMMDD, Fecha.EXPR_DDMMYYYY_LINEA);
					req.setAttribute("fechaEntrega", fechaEntrega);
					req.setAttribute("usuario", solicitudDTO.getUsuario());
					req.setAttribute("tipoEntrega", solicitudDTO.getTipoEntrega());
				}
				else{
					mensaje = solicitudDTO.getDsStatus();
				}
			}
			
			if(mensaje != null && mensaje != ""){
				req.setAttribute("mensaje", mensaje);
			}
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}
	
	public void obtenerDatos(HttpServletRequest req){
		codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		fechaEntrega = req.getParameter("fechaEntrega") != null ? req.getParameter("fechaEntrega") : "";
		fechaEntrega = fechaEntrega.replaceAll("-", "");
		tipoEntrega = req.getParameter("tipoEntrega") != null ? req.getParameter("tipoEntrega") : "";
		observacion = req.getParameter("observacion") != null ? req.getParameter("observacion") : "";
		UsuarioDTO usuarioDTO = req.getSession().getAttribute( Constantes.USUARIO_SESION ) != null ? ((UsuarioDTO)req.getSession().getAttribute( Constantes.USUARIO_SESION )) : null;
		usuario = usuarioDTO.getUsuario();
	}
	
	private void dtoAssembler(final SolicitudDTO dto){
		dto.setCodCia(codCia);
		dto.setFechaEntrega(fechaEntrega);
		dto.setTipoEntrega(tipoEntrega);
		dto.setObservacion(observacion);
		dto.setUsuario(usuario);
	}
}
