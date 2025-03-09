package votre.portaloperaciones.consultasku.consultas.cmd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.consultasku.bodegas.beans.BodegaDTO;
import votre.portaloperaciones.consultasku.bodegas.delegate.BodegaBusiness;
import votre.portaloperaciones.consultasku.consultas.beans.ConsultasDTO;
import votre.portaloperaciones.consultasku.consultas.delegate.ConsultasBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class CargarTitulosCmd implements IBaseCmd {

	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
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
			consultasDTO = consultasBusiness.consultarReferencias(consultasDTO);
			if(consultasDTO != null){
				if(consultasDTO.getEstado().equals(Constantes.EXITO_SP)){
					req.setAttribute("tituloPais", consultasDTO.getTituloPais());
					req.setAttribute("tituloSaldos", consultasDTO.getTituloSaldos());
					req.setAttribute("tituloReferencia", consultasDTO.getTituloReferencia());
					req.setAttribute("tituloColor", consultasDTO.getTituloColor());
					req.setAttribute("tituloTalla", consultasDTO.getTituloTalla());
					req.setAttribute("tituloBodega", consultasDTO.getTituloBodega());
					req.setAttribute("tituloConsultar", consultasDTO.getTituloConsultar().toUpperCase());
					req.setAttribute("tituloLimpiar", consultasDTO.getTituloLimpiar().toUpperCase());
					req.setAttribute("tituloDescripcion", consultasDTO.getTituloDescripcion());
					req.setAttribute("tituloLinea", consultasDTO.getTituloLinea());
					req.setAttribute("tituloUbicacion", consultasDTO.getTituloUbicacion());
					req.setAttribute("tituloCantidadD", consultasDTO.getTituloCantidadD());
					req.setAttribute("tituloCantidadP", consultasDTO.getTituloCantidadP());
					req.setAttribute("tituloCantidadA", consultasDTO.getTituloCantidadA());
					req.setAttribute("tituloDisponibleP", consultasDTO.getTituloDisponibleP());
					req.setAttribute("tituloDisponibleA", consultasDTO.getTituloDisponibleaA());
					req.setAttribute("tituloCanPicking", consultasDTO.getTituloCanPicking());
					req.setAttribute("tituloCodigoBarras", consultasDTO.getTituloCodigoBarras());
					req.setAttribute("tituloPrecio", consultasDTO.getTituloPrecio());
					req.setAttribute("mostrarResulset", Constantes.MOSTRAR_NO);
					req.setAttribute("referencia", referencia);
					req.setAttribute("color", color);
					req.setAttribute("talla", talla);
					req.setAttribute("codBodega", bodega);
					
					BodegaBusiness bodegaBusiness = new BodegaBusiness();
					ArrayList<BodegaDTO> bodegas = new ArrayList<BodegaDTO>();
					bodegas = bodegaBusiness.consultarBodegas(codCia);
					req.setAttribute("bodegas", bodegas);
					
					consultasDTO.setAccion(Constantes.ACCION_PROCESAR);	
					if(!referencia.equals("") && !bodega.equals("")){
						if(!consultasDTO.getColor().equals("") && consultasDTO.getColor().length()<3){
				            int largo = consultasDTO.getColor().length();
				            for(int i = largo; i<3; i++){
				            	consultasDTO.setColor(" "+consultasDTO.getColor());
				            }
				        }
				        if(!consultasDTO.getTalla().equals("") && consultasDTO.getTalla().length()<3){
				            int largo = consultasDTO.getTalla().length();
				            for(int i = largo; i<3; i++){
				            	consultasDTO.setTalla(" "+consultasDTO.getTalla());
				            }
				        }
						consultasDTO = consultasBusiness.consultarReferencias(consultasDTO);
						if(consultasDTO != null){
							if(consultasDTO.getEstado().equals(Constantes.EXITO_SP)){
								req.setAttribute("detalles", consultasDTO.getDetalle());
								req.setAttribute("nroRegistros", consultasDTO.getDetalle().length);
								req.setAttribute("registrosXPagina", Constantes.REGISTROSXPAGINA_REFERENCIAS);
								req.setAttribute("mostrarResulset", Constantes.MOSTRAR_SI);
							}
							else{
								mensaje = consultasDTO.getDescripcion();
								req.setAttribute("mostrarResulset", Constantes.MOSTRAR_NO);
							}
						}
					}
				}
				else{
					mensaje = consultasDTO.getDescripcion();
				}
			}
			
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
		referencia = req.getParameter("referencia") != null ? req.getParameter("referencia") : "";
		color = req.getParameter("color") != null ? req.getParameter("color") : "";
		talla = req.getParameter("talla") != null ? req.getParameter("talla") : "";
		bodega = req.getParameter("nomBodega") != null ? req.getParameter("nomBodega") : "";
	}
	
	private void dtoAssembler(final ConsultasDTO consultasDTO){
		consultasDTO.setCodCia(codCia);
		consultasDTO.setAccion(Constantes.ACCION_TITULOS);
		consultasDTO.setReferencia(referencia);
		consultasDTO.setColor(color);
		consultasDTO.setTalla(talla);
		consultasDTO.setCodigoBodega(bodega);
	}

}
