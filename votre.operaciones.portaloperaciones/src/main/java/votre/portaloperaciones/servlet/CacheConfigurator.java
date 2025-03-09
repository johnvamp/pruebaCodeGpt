package votre.portaloperaciones.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class CacheConfigurator extends HttpServlet implements Servlet {
	static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger( this.getClass() );	
	
	public void init() throws ServletException {
		PersonalsoftException personalsoftException = null;
		try{
			//Carga todos los parámetros de aplicación encontrados en el web.xml y los de la base de datos.
			Configuracion.getInstance().setContext(getServletContext());
			Configuracion.getInstance().initCache();
			log.debug(" CacheConfigurator.start....");			
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
    public void destroy() 
	{
//        AgenteEjecucion.getInstance().terminarEjecucionAgente();
        super.destroy();
    }
}

