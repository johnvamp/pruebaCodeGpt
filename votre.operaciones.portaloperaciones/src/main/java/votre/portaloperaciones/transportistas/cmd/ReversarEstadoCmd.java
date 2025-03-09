package votre.portaloperaciones.transportistas.cmd;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import votre.portaloperaciones.transportistas.beans.TransportistaReversarDTO;
import votre.portaloperaciones.transportistas.delegate.TransportistasBusiness;
import votre.portaloperaciones.util.Constantes;
import votre.utils.moduloseguridad.beans.UsuarioDTO;

public class ReversarEstadoCmd implements IBaseCmd {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
        TransportistaReversarDTO reversarDTO = new TransportistaReversarDTO();
        TransportistasBusiness transportistasBusiness = new TransportistasBusiness();
        
        //codigo compania
        String pCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
        UsuarioDTO user = req.getSession().getAttribute( Constantes.USUARIO_SESION)  != null ? ((UsuarioDTO)req.getSession().getAttribute( Constantes.USUARIO_SESION )) : null;
        String reversarReq = req.getParameter("arrayReversar") != null ? req.getParameter("arrayReversar") : "";
        
        reversarDTO.setCompany(pCia);
        reversarDTO.setUser(user.getUsuario());
        reversarDTO.setJsonReversar(reversarReq);
        reversarDTO.setState("");
        reversarDTO.setDescState("");
        
        TransportistaReversarDTO reversarDTOResponse = transportistasBusiness.reversarEstado(reversarDTO);
        req.setAttribute("mensaje",(reversarDTOResponse.getState().equals("0") ? "La transacción ha sido exitosa" : "Ha ocurrido un error"));
    }

}
