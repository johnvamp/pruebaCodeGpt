package votre.portaloperaciones.transportistas.cmd;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import votre.portaloperaciones.util.JSON;
import votre.utils.moduloseguridad.ModuloSeguridad;
import votre.utils.moduloseguridad.beans.UsuarioDTO;

public class CambiarContrasenaMovilCmd implements IBaseCmd{
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		Map<String,Object> salida = new HashMap<String,Object>();
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		ModuloSeguridad moduloSeguridad = new ModuloSeguridad();
		
		String usuario = req.getParameter("usu") != null ? req.getParameter("usu").toUpperCase() : "";
		String oldpsw = req.getParameter("oldpsw") != null ? req.getParameter("oldpsw") : "";
		String newPassword = req.getParameter("newpsw") != null ? req.getParameter("newpsw") : "";

		try{
		    salida.put("status", "1");
		    usuarioDTO.setUsuario(usuario);
	        usuarioDTO.setPassword(oldpsw);
	        usuarioDTO.setNewPassword(newPassword);
		    String endpoint = Configuracion.getInstance().getServicio("ModuloSeguridad").getRuta();
		    
		    usuarioDTO = moduloSeguridad.cambiarClave(usuarioDTO, endpoint);
            if(usuarioDTO != null){
                String mensaje = usuarioDTO.getError();
                if("".equals(mensaje)){
                    mensaje = "La contraseña fue cambiada correctamente. Ingresa de nuevo.";
                    salida.put("status", "0");
                }
                
                salida.put("descStatus", mensaje);
            }
		    
			res.setContentType("application/json; charset='UTF-8'");
			res.setHeader("Cache-Control", "must-revalidate, no-cache, no-store, max-age=0");
			res.setHeader("Pragma", "no-cache");
			res.setDateHeader("Expires", 0);
			res.getWriter().write( JSON.dump(salida) );
		}catch (IOException e) {
			throw new PersonalsoftException(e);
		}
	}

}
