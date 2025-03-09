package votre.portaloperaciones.solicitudcatalogo.catalogo.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.solicitudcatalogo.catalogo.beans.CatalogoDTO;
import votre.portaloperaciones.solicitudcatalogo.catalogo.delegate.CatalogoBusiness;
import votre.portaloperaciones.util.Constantes;


import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class VerCatalogoCmd implements IBaseCmd {
	private Logger logger = Logger.getLogger(this.getClass());
	private String mensaje;
	private String codCia;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
	
		CatalogoBusiness catalogoBusiness = new CatalogoBusiness();
		CatalogoDTO catalogo = new CatalogoDTO();
		
		try {
			codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
			dtoAssembler(catalogo);
			catalogo = catalogoBusiness.verCatalogo(catalogo);
			String nomPais = req.getSession().getAttribute( Constantes.NOM_PAIS ) != null ? ((String)req.getSession().getAttribute( Constantes.NOM_PAIS )).trim() : "";
			
			if(catalogo != null){
				if(catalogo.getEstado().equals(Constantes.EXITO_SP)){
					req.setAttribute("pais", catalogo.getTituloPais());
					req.setAttribute("nomPais", nomPais);
					req.setAttribute("opcionCedula", catalogo.getOpcionCedula());
					req.setAttribute("opcionNombre", catalogo.getOpcionNombre());
					req.setAttribute("boton", catalogo.getTituloBoton());
				}
				else{
					mensaje = catalogo.getDescripcion().trim();
				}
			}
			if (mensaje != null && !mensaje.equals("")){
				req.setAttribute("mensaje", mensaje);
			}
		}
		catch (Exception e){
			logger.error(new PersonalsoftException(e).getTraza());
		}
	}
	
	private void dtoAssembler(final CatalogoDTO catalogo){
		catalogo.setCodCia(codCia);
		catalogo.setCedula("0");
		catalogo.setAccion("");
	}
}
