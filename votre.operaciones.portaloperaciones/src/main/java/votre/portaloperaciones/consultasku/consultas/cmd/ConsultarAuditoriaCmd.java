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

public class ConsultarAuditoriaCmd implements IBaseCmd {

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
			req.getSession().setAttribute(Constantes.OBJETO_BANDERA_EXPORTAR, new Boolean(false));
			consultasBusiness = new ConsultasBusiness();
			obtenerDatos(req);
			dtoAssembler(consultasDTO);
			
			consultasDTO = consultasBusiness.consultarAuditoria(consultasDTO);
			if(consultasDTO != null){
				if(consultasDTO.getEstado().equals(Constantes.EXITO_SP)){
					req.setAttribute("tituloHistoria", consultasDTO.getTituloHistoria());
					req.setAttribute("tituloBodega", consultasDTO.getTituloBodega());
					req.setAttribute("tituloCodigoTrans", consultasDTO.getTitulocodigoTrans());
					req.setAttribute("tituloUnidades", consultasDTO.getTituloUnidades());
					req.setAttribute("tituloCantidadTrans", consultasDTO.getTituloCantidadTrans());
					req.setAttribute("tituloFechaTrans", consultasDTO.getTituloFechaTrans());
					req.setAttribute("tituloOrden", consultasDTO.getTituloOrden());
					req.setAttribute("tituloCreado", consultasDTO.getTituloCreado());
					req.setAttribute("tituloFechaCre", consultasDTO.getTituloFechaCreacion());
					req.setAttribute("tituloCodigoAud", consultasDTO.getTituloCodigoAud());
					req.setAttribute("tituloRegresar", consultasDTO.getTituloRegresar());
					req.setAttribute("detalles", consultasDTO.getDetalle());
					req.setAttribute("nroRegistros", consultasDTO.getDetalle().length);
					req.setAttribute("registrosXPagina", Constantes.REGISTROSXPAGINA);
				}
				else{
					mensaje = consultasDTO.getDescripcion();
				}
			}
			
			req.setAttribute("referencia", referencia);
			req.setAttribute("color", color);
			req.setAttribute("talla", talla);
			req.setAttribute("codBodega", bodega);
			
			if(mensaje != null && !mensaje.equals("")){
				req.setAttribute("mensaje", mensaje);
			}
			req.getSession().setAttribute(Constantes.OBJETO_BANDERA_EXPORTAR, new Boolean(true));
		}
		catch (Exception e) {
			req.getSession().setAttribute(Constantes.OBJETO_BANDERA_EXPORTAR, new Boolean(true));
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
		consultasDTO.setCodigoBodega(bodega);
	}

}
