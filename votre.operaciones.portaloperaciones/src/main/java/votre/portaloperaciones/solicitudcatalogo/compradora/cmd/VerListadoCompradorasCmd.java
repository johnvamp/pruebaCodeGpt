package votre.portaloperaciones.solicitudcatalogo.compradora.cmd;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.solicitudcatalogo.compradora.beans.CompradoraDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.delegate.CompradoraBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class VerListadoCompradorasCmd implements IBaseCmd {
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String nombre;
	private String mensaje;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		CompradoraBusiness compradoraBusiness = new CompradoraBusiness();
		CompradoraDTO compradora = new CompradoraDTO();
		CompradoraDTO listadoCompradoras = null;
		
		try{
			obtenerDatos(req);
			if(validarDatos()){
				dtoAssembler(compradora);
				listadoCompradoras = compradoraBusiness.verListadoCompradoras(compradora);
				
				if (listadoCompradoras != null){
					if (listadoCompradoras.getRegistros().length > 0){
						if (listadoCompradoras.getEstado().equals(Constantes.EXITO_SP)){
							req.setAttribute("compradoras", listadoCompradoras.getRegistros());
							req.setAttribute("titPais", listadoCompradoras.getTituloPais());
							req.setAttribute("titTipo", listadoCompradoras.getTituloTipo());
							req.setAttribute("titCedula", listadoCompradoras.getTituloCedula());
							req.setAttribute("titNombre", listadoCompradoras.getTituloNombre());
							req.setAttribute("titTelefono", listadoCompradoras.getTituloTelefono());
							req.setAttribute("nombre", nombre);
							req.setAttribute("nroRegistros", listadoCompradoras.getRegistros().length);
							req.setAttribute("registrosXPagina", Constantes.REGISTROSXPAGINA);
						}
						else{
							mensaje = listadoCompradoras.getDescripcion();
						}
					}
					else{
						mensaje = "No hay Compradoras con este nombre.";
					}
				}
			}			
			
			String siguienteRuta = "compradora.jspListado.do";
			
			if (mensaje != null && !mensaje.equals("")){
				req.setAttribute("mensaje", mensaje);
				siguienteRuta = "catalogo.verCatalogo.do";
			}
				
			
			Configuracion.getInstance().getContext().getRequestDispatcher(siguienteRuta).forward(req, res);
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}

	private void obtenerDatos(HttpServletRequest req) {
		codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		nombre = req.getParameter("nombre") != null ? req.getParameter("nombre") : "";
	}
	
	private boolean validarDatos(){
		if (nombre.length() > 30 ){
			mensaje = "Nombre del participante tiene que ser menos a 30 caracteres.";
			return false;
		}
		
		if(nombre.equals("")){
			mensaje="Debe seleccionar un parámetro de consulta.";
		}
		return true;
	}
	
	private void dtoAssembler(final CompradoraDTO compradora){
		compradora.setCodCia(codCia);
		compradora.setNombre(nombre.trim());
	}
}
