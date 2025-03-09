package votre.portaloperaciones.seguridad.cmd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.seguridad.util.UtilSesion;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;


public class ValidarExportarAjaxCmd implements IBaseCmd{
	
	Logger logger = Logger.getLogger(this.getClass());
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws PersonalsoftException {
		
		PrintWriter printWriter;
		//System.err.println("lo llamo verificar exportar!!");
		try {
			response.setContentType("text/xml; charset=UTF-8");
			
			Boolean puedeExportar =  (Boolean) UtilSesion.getObjetoEnSesion(request, Constantes.OBJETO_BANDERA_EXPORTAR);
			StringBuffer xml = new StringBuffer("<xml>");
			String puede = (puedeExportar==null || puedeExportar) ? "1":"0";
			xml.append("<puede>"+puede+"</puede>");
			xml.append("</xml>");

			printWriter = response.getWriter();
			printWriter.write(xml.toString());
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			logger.error(e);
		}
		
	}

}
