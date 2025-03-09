package votre.portaloperaciones.consultasku.consultas.cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.consultasku.consultas.beans.ConsultasDTO;
import votre.portaloperaciones.consultasku.consultas.delegate.ConsultasBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class ConsultarPreciosCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String sku;
	private String referencia;
	private String color;
	private String talla;
	private String bodega;
	private String mensaje;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		ConsultasBusiness consultasBusiness = null;
		ConsultasDTO consultasDTO = new ConsultasDTO();
		try{
			consultasBusiness = new ConsultasBusiness();
			obtenerDatos(req);
			dtoAssembler(consultasDTO);
			consultasDTO = consultasBusiness.consultarPrecios(consultasDTO);
			if(consultasDTO != null){
				if(consultasDTO.getEstado().equals(Constantes.EXITO_SP)){
					req.setAttribute("tituloCampana", consultasDTO.getTituloCampana());
					req.setAttribute("tituloCustomer", consultasDTO.getTituloCustomer());
					req.setAttribute("tituloCantidad", consultasDTO.getTituloCantidad());
					req.setAttribute("tituloPrecio", consultasDTO.getTituloPrecio());
					req.setAttribute("tituloRegresar", consultasDTO.getTituloRegresar().toUpperCase());
					req.setAttribute("estado", consultasDTO.getEstado());
					req.setAttribute("detalles", consultasDTO.getDetalle());
				}
				else{
					mensaje = consultasDTO.getDescripcion();
					req.setAttribute("estado", consultasDTO.getEstado());
					req.setAttribute("tituloRegresar", "REGRESAR");
				}
			}
			
			req.setAttribute("referencia", referencia);
			req.setAttribute("color", color);
			req.setAttribute("talla", talla);
			req.setAttribute("codBodega", bodega);
			
			if(mensaje != null && !mensaje.equals("")){
				req.setAttribute("mensaje", mensaje);
			}
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}
		
	}
	
	private void obtenerDatos(HttpServletRequest req) {
		codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		sku = req.getParameter("sku") != null ? req.getParameter("sku") : "";
		referencia = req.getParameter("referencia") != null ? req.getParameter("referencia") : "";
		color = req.getParameter("color") != null ? req.getParameter("color") : "";
		talla = req.getParameter("talla") != null ? req.getParameter("talla") : "";
		bodega = req.getParameter("nomBodega") != null ? req.getParameter("nomBodega") : "";
	}
	
	private void dtoAssembler(final ConsultasDTO consultasDTO){
		consultasDTO.setCodCia(codCia);
		consultasDTO.setSku(sku);
	}

}
