package votre.portaloperaciones.seguridad.cmd;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import votre.portaloperaciones.util.Constantes;


public class SessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		try {			
			String codCia = (String) arg0.getSession().getAttribute( Constantes.COD_CIA );
			
		}
		catch (Exception e) {
		}
		
	}

}
