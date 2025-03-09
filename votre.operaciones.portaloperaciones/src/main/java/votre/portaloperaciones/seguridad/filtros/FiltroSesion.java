package votre.portaloperaciones.seguridad.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import votre.portaloperaciones.seguridad.util.UtilSesion;
import votre.portaloperaciones.util.Constantes;
import votre.utils.moduloseguridad.beans.UsuarioDTO;
import co.com.personalsoft.base.beans.Recurso;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.util.CargadorMsj;

public class FiltroSesion implements Filter{
	private Logger logger = Logger.getLogger(this.getClass());

	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		try {
			verificarSesion(req, res,chain);
		} catch (Exception e) {
			logger.error("Error en el filtro de sesion");
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

	private void verificarSesion(ServletRequest request, ServletResponse response,FilterChain chain)throws Exception{
		HttpServletRequest req = (HttpServletRequest) request;
		UsuarioDTO usuarioDTO= null;
		String rutaValidar = "";

		int pSeparador = req.getRequestURI().lastIndexOf("/");
		if (pSeparador != -1) {
			rutaValidar = req.getRequestURI().substring(pSeparador + 1, req.getRequestURI().length()); //elimina el contexto
			rutaValidar = rutaValidar.substring(0, rutaValidar.length()-3); //elimina ".do"
		}
		
		Recurso recurso = (Recurso)Configuracion.getInstance().getParametro(rutaValidar,Configuracion.getInstance().getParametro("rutaRecursosAceptadosCache"));

		if(recurso != null){
			chain.doFilter(request, response);
		}
		else {
			usuarioDTO =  (UsuarioDTO) UtilSesion.getObjetoEnSesion(req, Constantes.USUARIO_SESION);
			if(usuarioDTO != null){
				chain.doFilter(request, response);
			}
			else {
				req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("SIN_SESION"));
				req.getRequestDispatcher("salir.do").forward(request, response);
			}
		}
	}

	
}
