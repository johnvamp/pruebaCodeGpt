package votre.portaloperaciones.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.seguridad.servicio.GestorSeguridad;

public class Seguridad extends HttpServlet implements Servlet {
	static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger( this.getClass() );	
	
	public void init() throws ServletException {
		PersonalsoftException personalsoftException = null;
		try{
			//Carga todos los par�metros de aplicaci�n encontrados en el web.xml y los de la base de datos.
			GestorSeguridad.getInstance();
			GestorSeguridad.getInstance().init();			
			log.debug(" Seguridad.start....");
		}catch(Exception e){	
			if(e instanceof PersonalsoftException){
				personalsoftException = (PersonalsoftException)e;
			}else{
				personalsoftException = new PersonalsoftException(e);
			}	
			log.error(personalsoftException.getTraza());
		}
	}

	@Override
	public void destroy() {		
		super.destroy();
	}
}

