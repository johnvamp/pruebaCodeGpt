package votre.portaloperaciones.activaciondemandas.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.activaciondemandas.beans.EnviosDTO;
import votre.portaloperaciones.activaciondemandas.delegate.ActivacionDemandasBusiness;
import votre.portaloperaciones.util.Constantes;
import votre.utils.moduloseguridad.beans.UsuarioDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class EliminarEnviosCmd implements IBaseCmd{

	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String tipo;
	private String accion;
	private String refeSku;
	private String colorSku;
	private String tallaSku;
	private String usuario;
	private String cedula;
	private String codInterno;
	private String numOrden;
	private String sku;
	private String mensaje;
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		ActivacionDemandasBusiness business = null;
		EnviosDTO enviosDTO = null;
		try{
			business = new ActivacionDemandasBusiness();
			enviosDTO = new EnviosDTO();
			obtenerDatos(req);
			dtoAssembler(enviosDTO);
			
			enviosDTO = business.eliminarEnvios(enviosDTO);
			if(enviosDTO != null){
				if(enviosDTO.getStatus().equals(Constantes.EXITO_SP)){
					if(tipo.equals(Constantes.TIPO_ELIMINAR_ENVIO) && accion.equals(Constantes.ACCION_CONSULTAR_ENVIO)){
						if(enviosDTO.getArrEnvios() != null && enviosDTO.getArrEnvios().size() > 0){
							req.setAttribute("arrEnvios", enviosDTO.getArrEnvios());
							req.setAttribute("nroRegistrosM", enviosDTO.getArrEnvios().size());
						}
						else{
							mensaje = "No se encontraron registros.";
						}
						req.setAttribute("refeBuscar", refeSku);
						req.setAttribute("colorBuscar", colorSku);
						req.setAttribute("tallaBuscar", tallaSku);
						req.setAttribute("mostrarDivMasiva", "true");
						req.setAttribute("banderaCheckMasiva", "checked");
					}
					if(tipo.equals(Constantes.TIPO_ELIMINAR_CEDULA) && accion.equals(Constantes.ACCION_CONSULTAR_ENVIO)){
						if(enviosDTO.getArrEnvios() != null && enviosDTO.getArrEnvios().size() > 0){
							req.setAttribute("arrEnvios", enviosDTO.getArrEnvios());
							req.setAttribute("nroRegistrosI", enviosDTO.getArrEnvios().size());
						}
						else{
							mensaje = "No se encontraron registros.";
						}
						req.setAttribute("cedulaBuscar", cedula);
						req.setAttribute("mostrarDivIndi", "true");
						req.setAttribute("banderaCheckIndi", "checked");
					}
					if(accion.equals(Constantes.ACCION_COMFIRMAR_ENVIO)){
						mensaje = enviosDTO.getDsStatus();
					}
				}
				else{
					mensaje = enviosDTO.getDsStatus();
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
		tipo = req.getParameter("tipo") != null ? req.getParameter("tipo") : "";
		accion = req.getParameter("accion") != null ? req.getParameter("accion") : "";
		if(tipo.equals(Constantes.TIPO_ELIMINAR_ENVIO)){
			refeSku = req.getParameter("refeBuscar") != null ? req.getParameter("refeBuscar") : "";
			colorSku = req.getParameter("colorBuscar") != null ? req.getParameter("colorBuscar") : "";
			tallaSku = req.getParameter("tallaBuscar") != null ? req.getParameter("tallaBuscar") : "";
		}
		if(tipo.equals(Constantes.TIPO_ELIMINAR_CEDULA)){
			cedula = req.getParameter("cedulaBuscar") != null ? req.getParameter("cedulaBuscar") : "";
		}
		if(tipo.equals(Constantes.TIPO_ELIMINAR_CEDULA) && accion.equals(Constantes.ACCION_COMFIRMAR_ENVIO)){
			codInterno = req.getParameter("codInterno") != null ? req.getParameter("codInterno") : "";
			numOrden = req.getParameter("numOrden") != null ? req.getParameter("numOrden") : "";
			sku = req.getParameter("sku") != null ? req.getParameter("sku") : "";
		}
		UsuarioDTO usuarioDTO = req.getSession().getAttribute( Constantes.USUARIO_SESION ) != null ? ((UsuarioDTO)req.getSession().getAttribute( Constantes.USUARIO_SESION )) : null;
		usuario = usuarioDTO.getUsuario();
	}

	private void dtoAssembler(final EnviosDTO dto){
		dto.setCodCia(codCia);
		dto.setTipo(tipo);
		dto.setAccion(accion);
		
		if(tipo.equals(Constantes.TIPO_ELIMINAR_ENVIO)){
			int relleno;
			StringBuffer refe = new StringBuffer(refeSku);
			if(refe.length()>=7){
				refe = new StringBuffer(refe.substring(0, 7));
			}
			else{
				relleno = 7 - refe.length();
				for (int i = 0; i < relleno; i++) {
					refe.append(" ");
				}
			}
			
			StringBuffer color = new StringBuffer(colorSku);
			if(color.length()>=3){
				color = new StringBuffer(color.substring(0, 3));
			}
			else{
				relleno = 3 - color.length();
				for (int i = 0; i < relleno; i++) {
					color.insert(0, " ");
				}
			}

			StringBuffer talla = new StringBuffer(tallaSku);
			if(talla.length()>=3){
				talla = new StringBuffer(talla.substring(0, 3));
			}
			else{
				relleno = 3 - talla.length();
				for (int i = 0; i < relleno; i++) {
					talla.insert(0, " ");
				}
			}

			StringBuffer sku = new StringBuffer(refe);
			sku.append(" ");
			sku.append(color);
			sku.append(" ");
			sku.append(talla);
			dto.setValor(sku.toString());
		}
		if(tipo.equals(Constantes.TIPO_ELIMINAR_CEDULA)){
			dto.setValor(cedula);
		}
		if(tipo.equals(Constantes.TIPO_ELIMINAR_CEDULA) && accion.equals(Constantes.ACCION_COMFIRMAR_ENVIO)){
			dto.setCodInterno(codInterno);
			dto.setNumOrden(numOrden);
			dto.setSku(sku);
		}
		dto.setUsuario(usuario);
	}
}
