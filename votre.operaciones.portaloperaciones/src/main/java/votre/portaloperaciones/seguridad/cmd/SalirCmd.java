package votre.portaloperaciones.seguridad.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class SalirCmd  implements IBaseCmd{

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		
		req.getSession().invalidate();
		if(req.getAttribute("mensaje") == null){
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("SALIDA_SEGURA"));
		}
		
	}

}
