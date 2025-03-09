package votre.portaloperaciones.seguridad.cmd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.logueo.beans.LogueoDTO;
import votre.portaloperaciones.logueo.delegate.LogueoBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class VerLoginCmd implements IBaseCmd{
	private Logger logger = Logger.getLogger(this.getClass());
	private String mensaje;
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		
		LogueoBusiness logueoBusiness = new LogueoBusiness();
		LogueoDTO logueoDTO = new LogueoDTO();
		ArrayList<LogueoDTO> paises = null;
		
		mensaje = req.getParameter("msg");
		try {		
//			paises = logueoBusiness.consultarPaises(logueoDTO);
//			if (paises != null && paises.get(0).getEstado().equals(Constantes.EXITO_SP)){
//				req.setAttribute("paises", paises);
//				req.setAttribute("boton", paises.get(0).getBoton());
//			}
//			else{
//				mensaje = paises.get(0).getDescripcion();
//			}
			if (mensaje != null && !mensaje.equals("")){
				req.setAttribute("mensaje", mensaje);
			}
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}
}
