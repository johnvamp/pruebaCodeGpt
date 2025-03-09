package votre.portaloperaciones.seguridad.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UtilSesion {
	
	static public void vaciarSesion(HttpServletRequest req){
		HttpSession s = req.getSession();
		if (s != null) {
			Enumeration e = s.getAttributeNames();
			while (e != null && e.hasMoreElements()) {
				String valor = (String) e.nextElement();
				s.removeAttribute(valor);
			}
		}
	}
	
	static public void quitarVariableSesion(HttpServletRequest req, String variable){
		HttpSession s = req.getSession();
		if (s != null) {
			s.removeAttribute(variable);
		}
	}
	
	public static Object getObjetoEnSesion(HttpServletRequest req, String clave){
		Object valor = null;
		if(req.getSession() != null){
			valor = req.getSession().getAttribute(clave);
		}
		return valor;
	}
}
