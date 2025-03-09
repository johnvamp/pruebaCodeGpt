package votre.portaloperaciones.transportistas.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class PedidosDevolverEstadoCmd implements IBaseCmd {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
        String[] guias = req.getParameterValues("chkGuias");
    }

}
