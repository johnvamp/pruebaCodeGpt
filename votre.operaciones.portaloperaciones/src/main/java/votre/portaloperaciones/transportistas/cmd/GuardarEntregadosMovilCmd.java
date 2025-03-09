package votre.portaloperaciones.transportistas.cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

//import votre.portalcompradoras.servicios.delegate.EmbarqueBusinessProxy;
import votre.portaloperaciones.solicitudcatalogo.compradora.beans.CompradoraDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.facade.CompradoraFacade;
import votre.portaloperaciones.transportistas.beans.TransportistasDTO;
import votre.portaloperaciones.transportistas.delegate.TransportistasBusiness;
import votre.portaloperaciones.util.Constantes;
import votre.portaloperaciones.util.JSON;
import votre.utils.moduloseguridad.ModuloSeguridad;
import votre.utils.moduloseguridad.beans.UsuarioDTO;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;
import co.com.personalsoft.base.util.Fecha;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GuardarEntregadosMovilCmd implements IBaseCmd {
	private Logger logger = Logger.getLogger(this.getClass());
	private Logger info = Logger.getLogger("info");
	ArrayList<TransportistasDTO> registros = new ArrayList<TransportistasDTO>();
	private String usuario;
	private String password;
	private String cia;
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		TransportistasBusiness business;
		Map<String,Object> salida = new HashMap<String,Object>();
		UsuarioDTO usuarioDTO = null;
		ModuloSeguridad moduloSeguridad = new ModuloSeguridad();
		String aplicacion = Configuracion.getInstance().getParametroApp("COD_APP_PORTAL");
		String endpoint = Configuracion.getInstance().getServicio("ModuloSeguridad").getRuta();
		
		try{
			obtenerDatos(req);
			
			/*1. Validar nuevamente el usuario contra el módulo de seguridad*/
			usuarioDTO = new UsuarioDTO();
			usuarioDTO.setUsuario(usuario.trim());
			usuarioDTO.setPassword(password);
			usuarioDTO.setCodCompania(cia);
			usuarioDTO.setAplicacion(aplicacion);
			usuarioDTO = moduloSeguridad.validarUsuario(usuarioDTO, endpoint);
			
			if(usuarioDTO.getError() == null){
				/*2. Guardar las guias*/
				business = new TransportistasBusiness();
				ArrayList<TransportistasDTO> arrErrores = business.guardarEntregadosMovil(registros);
				
				if(arrErrores != null){
					if(arrErrores.size() > 0){
						salida.put("status", "1");
						salida.put("arrMensajes", arrErrores);
						salida.put("nroMensajes", arrErrores.size());
					}
					else{
						salida.put("status", "0");
						salida.put("mensaje", "Proceso ejecutado exitosamente.");
					}
					
//					enviarEmailsCompradoras(arrErrores);
				}
			}
			else{
				salida.put("status", "3");
				salida.put("mensaje", usuarioDTO.getError());
			}
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			salida.put("status", "2");
			salida.put("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
		
		try{
			res.setContentType("application/json; charset='UTF-8'");
			res.setHeader("Cache-Control", "must-revalidate, no-cache, no-store, max-age=0");
			res.setHeader("Pragma", "no-cache");
			res.setDateHeader("Expires", 0);
			res.getWriter().write( JSON.dump(salida) );
		}catch (IOException e) {
			throw new PersonalsoftException(e);
		}
	}
	
	private void obtenerDatos(HttpServletRequest req){
		cia = req.getParameter("cia") != null ? req.getParameter("cia") : "";
		usuario = req.getParameter("usuSync") != null ? req.getParameter("usuSync").toUpperCase() : "";
		password = req.getParameter("passSync") != null ? req.getParameter("passSync") : "";
		String dataGuias = req.getParameter("dataGuias");
		
		info.info("El usuario: "+usuario+", Envio las guias: "+dataGuias);
		
		JsonObject dataGuiasJson = (JsonObject) new JsonParser().parse(dataGuias.toString());
		JsonArray entries = (JsonArray) dataGuiasJson.get("guias");
		
		for (int i = 0; i < entries.size(); i++) {
			JsonObject guia = (JsonObject) entries.get(i);
			TransportistasDTO dto = new TransportistasDTO();
			
			dto.setpCia(cia);
			dto.setNumeroOrden("0");
			dto.setNumeroGuia( guia.get("nroGuia") != null ? guia.get("nroGuia").getAsString() : "0" );
			
			String fechaEscaner = guia.get("fecha") != null ? guia.get("fecha").getAsString() : "";
			if(!"".equals(fechaEscaner) && fechaEscaner.split(" ").length > 1){
				String fecha = fechaEscaner.split(" ")[0];
				String hora = fechaEscaner.split(" ")[1];
				
				fecha = formatFechaHora(fecha.split("/")[2]) + formatFechaHora(fecha.split("/")[1]) + fecha.split("/")[0];
				hora = formatFechaHora(hora.split(":")[0]) + formatFechaHora(hora.split(":")[1]) + formatFechaHora(hora.split(":")[2]);
				
				dto.setFecEnt( fecha );
				dto.setHorEnt( hora );
			}
			
			
			String[] valores = guia.get("idVisit") != null ? guia.get("idVisit").getAsString().split("-") : null;
			if(valores != null && valores.length == 2){
				dto.setEstadoCombo( valores[0] );
				dto.setCodigoCombo( valores[1] );
			}
			dto.setObservaciones("");
			registros.add(dto);
		}
	}
	
//	private void enviarEmailsCompradoras(ArrayList<TransportistasDTO> arrErrores){
//		final ArrayList<TransportistasDTO> guiasEnviarEmail = new ArrayList<TransportistasDTO>();
//		if(registros != null && arrErrores != null){
//			for (TransportistasDTO transmisionEnviada : registros) {
//				boolean isGuiaOK = true;
//				
//				for (TransportistasDTO transmisionError : arrErrores) {
//					//Se pregunta si la guia enviada está dentro las guias que el SP rechazó
//					if(transmisionEnviada.getNumeroGuia().equals( transmisionError.getNumeroGuia() )){
//						isGuiaOK = false;
//						break;
//					}
//				}
//				
//				if(isGuiaOK){
//					TransportistasDTO guiaExitosa = new TransportistasDTO();
//					guiaExitosa.setNumeroGuia(transmisionEnviada.getNumeroGuia());
//					guiaExitosa.setFecEnt(transmisionEnviada.getFecEnt());
//					guiaExitosa.setHorEnt(transmisionEnviada.getHorEnt());
//					guiasEnviarEmail.add( guiaExitosa );
//				}
//			}
//		}
//		
//		if(!guiasEnviarEmail.isEmpty()){
//			Thread thread = new Thread(){
//				public void run(){
////					EmbarqueBusinessProxy proxy = new EmbarqueBusinessProxy();
////					proxy.setEndpoint( Configuracion.getInstance().getServicio("EmbarqueBusiness").getRuta());
//					
////					String remitente = Configuracion.getInstance().getParametroApp("REMITENTE");
////					String remitenteMostrar = Configuracion.getInstance().getParametroApp("REMITENTE_MOSTRAR");
////					String asunto = Configuracion.getInstance().getParametroApp("ASUNTO_GUIAENTREGADA");
////					StringBuffer mensaje = null;
////					String destinatario = "";
//					
//	                for (TransportistasDTO guia : guiasEnviarEmail) {
//	                	try{
//							CompradoraDTO compradoraDTO = new CompradoraDTO();
//							CompradoraFacade compradoraFacade = new CompradoraFacade();
//							compradoraDTO.setNroGuia(guia.getNumeroGuia());
//							compradoraDTO.setCodCia(cia);
//							compradoraDTO = compradoraFacade.getDatosCompradoraXGuia(compradoraDTO);
//							
//							if(compradoraDTO.getEmail() != null && compradoraDTO.getAutorizaEnvioEmail() != null &&
//									!compradoraDTO.getEmail().equals("") && "S".equals(compradoraDTO.getAutorizaEnvioEmail()) ){
//								
//								destinatario = compradoraDTO.getEmail();
//								
//								String fechaEnt = guia.getFecEnt() != null && !"".equals(guia.getFecEnt()) ? Fecha.cambiarFormatoFecha(guia.getFecEnt(), Fecha.EXPR_YYYYMMDD, Fecha.EXPR_DDMMYYYY_LINEA) : Fecha.getFechaServidor(Fecha.EXPR_DDMMYYYY_LINEA);
//								
//								String horaEnt = guia.getHorEnt() != null && !"".equals(guia.getHorEnt()) ? guia.getHorEnt() : "120000";
//								horaEnt = horaEnt.substring(0, 2) + ":" + horaEnt.substring(2, 4) + ":" + horaEnt.substring(4, 6);
//								
//								mensaje = new StringBuffer();
//								mensaje.append("<p>Querida <strong>"+ compradoraDTO.getNombre() +"</strong>.</p>");
//								mensaje.append("<p>Tu pedido Leonisa de campa&ntilde;a <strong>"+ compradoraDTO.getCampanaPedido() +"</strong>, ");
//								mensaje.append("con el n&uacute;mero de gu&iacute;a n&uacute;mero <strong>"+ guia.getNumeroGuia() +"</strong>,&nbsp;");
//								mensaje.append("ha sido entregado con &eacute;xito ");
//								mensaje.append("el d&iacute;a "+ fechaEnt +" a las "+ horaEnt +"</p>");
//								mensaje.append("<p>Un abrazo,</p>");
//								mensaje.append("<p><strong>Equipo Leonisa</strong></p>");
//								
//								proxy.enviarEmailGenericoCompradoras(Constantes.getCodInternacional(cia), 
//										remitente, 
//										destinatario, 
//										remitenteMostrar, 
//										asunto, 
//										mensaje.toString(), 
//										null, null, null);
//							}
//							
//	                	}catch(PersonalsoftException e){
//	                		logger.error(e.getTraza());
//		                }catch(Exception e){
//	                		logger.error(new PersonalsoftException(e).getTraza());
//	                	}
//					}
//				}
//			};
//			
//			thread.start();
//		}
//		
//	}
	
	
	private static String formatFechaHora(String numero){
		String numeroFormat = numero;
		if(numero != null && !"".equals(numero)){
			if(numero.length() == 1){
				numeroFormat = "0"+numero;
			}
		}
		return numeroFormat; 
	}
}