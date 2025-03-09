package votre.portaloperaciones.embarqueinternacional.embarque.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.embarqueinternacional.embarque.beans.EmbarqueDTO;
import votre.portaloperaciones.embarqueinternacional.embarque.delegate.EmbarqueBusinnes;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class AbrirEmbarqueCmd implements IBaseCmd {

	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String codTransportador;
	private String placa;
	private String tipoEmbarque;
	private String mensaje;
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		String siguienteRuta = "embarque.cargarTitulos.do";
		EmbarqueBusinnes embarqueBusinnes = null;
		EmbarqueDTO embarqueDTO = new EmbarqueDTO();
		try{
			embarqueBusinnes = new EmbarqueBusinnes();
			obtenerDatos(req);
			dtoAssembler(embarqueDTO);
			
			embarqueDTO = embarqueBusinnes.abrirEmbarque(embarqueDTO);
			if(embarqueDTO != null){
				if(embarqueDTO.getEstado().equals(Constantes.EXITO_SP)){
					mensaje = "Camion " + embarqueDTO.getPlaca() + " creado exitosamente.";
				}
				else{
					mensaje = "No es posible crear el camión con placa: " + embarqueDTO.getPlaca() + ". " + embarqueDTO.getDescripcion();
					siguienteRuta = "embarque.verAbrirEmbarque.do";
				}
			}
			
			if(mensaje != null && !mensaje.equals("")){
				req.setAttribute("mensaje", mensaje);
			}
			
			Configuracion.getInstance().getContext().getRequestDispatcher(siguienteRuta).forward(req, res);
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
	}
	
	private void obtenerDatos(HttpServletRequest req){
		codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		codTransportador = req.getParameter("transportador") != null ? req.getParameter("transportador") : "0";
		placa = req.getParameter("txtCamion") != null ? req.getParameter("txtCamion") : "";
		tipoEmbarque = req.getParameter("tipoEmbarque") != null ? req.getParameter("tipoEmbarque") : "";
	}
	
	private void dtoAssembler(final EmbarqueDTO embarqueDTO){
		embarqueDTO.setCodCia(codCia);
		embarqueDTO.setAccion(Constantes.ACCION_ABRIR_EMBARQUE);
		embarqueDTO.setCodTransportador(codTransportador);
		embarqueDTO.setPlaca(placa);
		embarqueDTO.setTipoEmbarque(tipoEmbarque);
		embarqueDTO.setFecha("");
	}

}
