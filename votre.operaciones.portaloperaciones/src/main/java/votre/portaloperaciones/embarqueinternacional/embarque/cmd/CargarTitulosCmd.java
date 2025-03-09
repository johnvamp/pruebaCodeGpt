package votre.portaloperaciones.embarqueinternacional.embarque.cmd;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.embarqueinternacional.embarque.beans.EmbarqueDTO;
import votre.portaloperaciones.embarqueinternacional.embarque.delegate.EmbarqueBusinnes;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.beans.Campo;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;
import co.com.personalsoft.base.util.Validador;

public class CargarTitulosCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String mensaje;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		EmbarqueBusinnes embarqueBusinnes = new EmbarqueBusinnes();
		EmbarqueDTO embarqueDTO = new EmbarqueDTO();
		String strValidacion = null;
		
		try{
			codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
			String nomPais = req.getSession().getAttribute( Constantes.NOM_PAIS ) != null ? ((String)req.getSession().getAttribute( Constantes.NOM_PAIS )).trim() : "";
			strValidacion = Validador.validarFormulario(validarInformacion(codCia));
			if(strValidacion.trim().equals("")){
				embarqueDTO = embarqueBusinnes.cargarTitulos(codCia);
				if(embarqueDTO != null){
					if(embarqueDTO.getEstado().equals(Constantes.EXITO_SP)){
						req.setAttribute("nomPais", nomPais);
						req.setAttribute("titulo2", embarqueDTO.getTitulo2());
						req.setAttribute("titulo3", embarqueDTO.getTitulo3());
						req.setAttribute("titulo4", embarqueDTO.getTitulo4());
						req.setAttribute("titulo5", embarqueDTO.getTitulo5());
						req.setAttribute("titulo6", embarqueDTO.getTitulo6());
					}
					else{
						mensaje = embarqueDTO.getDescripcion();
					}
				}
				if (mensaje != null && !mensaje.equals("")){
					req.setAttribute("mensaje", mensaje);
				}
			}
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}
	
	private Collection<Campo> validarInformacion(String codCia){		
		ArrayList<Campo> datos = new ArrayList<Campo>();
		datos.add(new Campo(codCia,Validador.STRING,true,"codCia",false));
		return datos;
	}

}
