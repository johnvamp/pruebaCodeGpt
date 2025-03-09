package votre.portaloperaciones.embarqueinternacional.entrega.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import votre.portaloperaciones.embarqueinternacional.entrega.beans.EntregaDTO;
import votre.portaloperaciones.embarqueinternacional.transportador.beans.TransportadorDetalleDTO;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.util.CargadorMsj;

public class EntregaDAO implements IEntregaDAO {
	
	private BDHelper bdHelper;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public EntregaDAO(BDHelper bdhelper){
		super();		
		this.bdHelper = bdhelper;
	}

	public EntregaDTO verEntrega(EntregaDTO entregaDTO) throws PersonalsoftException {
		EntregaDTO obtenido = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<TransportadorDetalleDTO> arrTransportadores = null;
		TransportadorDetalleDTO[] transportadores;
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, entregaDTO.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, entregaDTO.getAccion(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, entregaDTO.getCodTransportador(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, entregaDTO.getPlaca(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, entregaDTO.getFecha(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.DECIMAL, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_ENTREGAS");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				obtenido = new EntregaDTO();
				
				obtenido.setCodTransportador(cs.getString(3).trim());
				obtenido.setPlaca(cs.getString(4).trim());
				obtenido.setFecha(cs.getString(5).trim());
				obtenido.setNroPedidos(cs.getString(6));
				obtenido.setTitEntrega(cs.getString(7).trim());
				obtenido.setTitTransportador(cs.getString(8).trim());
				obtenido.setTitCamion(cs.getString(9).trim());
				obtenido.setTitAceptar(cs.getString(10).trim());
				obtenido.setTitRegresar(cs.getString(11).trim());
				obtenido.setTitTotal(cs.getString(12).trim());
				obtenido.setTitParcial(cs.getString(13).trim());
				obtenido.setTitTotalCajas(cs.getString(14).trim());
				obtenido.setTitConfirmar(cs.getString(15).trim());
				obtenido.setEstado(cs.getString(16).trim());
				obtenido.setDescripcion(cs.getString(17).trim());
				
				rs = cs.getResultSet();
				if(rs != null){
					arrTransportadores = new ArrayList<TransportadorDetalleDTO>();
					while(rs.next()){
						TransportadorDetalleDTO detalle = new TransportadorDetalleDTO();
						if(entregaDTO.getAccion().equals(Constantes.ACCION_ENTREGA)){
							detalle.setCodigo(rs.getString(1).trim());
							detalle.setNombre(rs.getString(2).trim());
						}
						if(entregaDTO.getAccion().equals(Constantes.ACCION_TRANSPORTADOR)){
							detalle.setCamion(rs.getString(1).trim());
							detalle.setNombre(rs.getString(2).trim());
							detalle.setCodigo(rs.getString(3).trim());
							detalle.setPlaca(rs.getString(4).trim());
							detalle.setFecha(rs.getString(5).trim());
							detalle.setValorConcatenado(detalle.getCodigo()+"|"+detalle.getNombre()+"|"+detalle.getPlaca()+"|"+detalle.getFecha());
						}
						arrTransportadores.add(detalle);
					}
					transportadores = arrTransportadores.toArray(new TransportadorDetalleDTO[arrTransportadores.size()]);
					obtenido.setDetalle(transportadores);
				}
			}
		}
		catch (SQLException e) {
			ps = new PersonalsoftException(e);
			ps.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("errorGeneral"));
			logger.error(ps.getTraza());
			throw ps;
		} finally {
			try {
				BDHelper.close(cs);
				BDHelper.close(rs);
			} catch (SQLException e) {
				ps = new PersonalsoftException(e);
				ps.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("errorGeneral"));
				logger.error(ps.getTraza());
				throw ps;
			}
		}
		
				
		return obtenido;
	}

	public EntregaDTO verEntregaParcial(EntregaDTO entregaDTO) throws PersonalsoftException {
		EntregaDTO obtenido = null;
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, entregaDTO.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, entregaDTO.getAccion(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, entregaDTO.getCodTransportador(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, entregaDTO.getPlaca(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, entregaDTO.getFecha(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.DECIMAL, new Integer(entregaDTO.getNumOrden()),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, entregaDTO.getCausal(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.DECIMAL, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_ENTREGA_PARCIAL");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = new EntregaDTO();
				obtenido.setCodTransportador(cs.getString(3).trim());
				obtenido.setPlaca(cs.getString(4).trim());
				obtenido.setFecha(cs.getString(5).trim());
				obtenido.setNroPedidos(cs.getString(8).trim());
				obtenido.setTitEntrega(cs.getString(9).trim());
				obtenido.setTitTransportador(cs.getString(10).trim());
				obtenido.setTitCamion(cs.getString(11).trim());
				obtenido.setTitTotal(cs.getString(12).trim());
				obtenido.setTitCausal(cs.getString(13).trim());
				obtenido.setTitNumOrden(cs.getString(14).trim());
				obtenido.setTitAceptar(cs.getString(15).trim());
				obtenido.setTitFinalizar(cs.getString(16).trim());
				obtenido.setTitCancelar(cs.getString(17).trim());
				obtenido.setTitNoentregado(cs.getString(18).trim());
				obtenido.setTitEntregado(cs.getString(19).trim());
				obtenido.setEstado(cs.getString(20).trim());
				obtenido.setDescripcion(cs.getString(21).trim());
			}
		}
		catch (SQLException e) {
			ps = new PersonalsoftException(e);
			ps.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("errorGeneral"));
			logger.error(ps.getTraza());
			throw ps;
		} finally {
			try {
				BDHelper.close(cs);
			} catch (SQLException e) {
				ps = new PersonalsoftException(e);
				ps.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("errorGeneral"));
				logger.error(ps.getTraza());
				throw ps;
			}
		}
		return obtenido;
	}

	public EntregaDTO confirmarEntrega(EntregaDTO entregaDTO) throws PersonalsoftException {
		EntregaDTO obtenido = null;
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, entregaDTO.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, entregaDTO.getCodTransportador(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, entregaDTO.getPlaca(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, entregaDTO.getRespuesta(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, entregaDTO.getFecha(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_ENTREGA_TOTAL");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = new EntregaDTO();
				obtenido.setCodTransportador(cs.getString(2).trim());
				obtenido.setPlaca(cs.getString(3).trim());
				obtenido.setRespuesta(cs.getString(4).trim());
				obtenido.setFecha(cs.getString(5).trim());
				obtenido.setEstado(cs.getString(6).trim());
				obtenido.setDescripcion(cs.getString(7).trim());
			}
		}
		catch (SQLException e) {
			ps = new PersonalsoftException(e);
			ps.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("errorGeneral"));
			logger.error(ps.getTraza());
			throw ps;
		} finally {
			try {
				BDHelper.close(cs);
			} catch (SQLException e) {
				ps = new PersonalsoftException(e);
				ps.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("errorGeneral"));
				logger.error(ps.getTraza());
				throw ps;
			}
		}
		return obtenido;
	}

	public EntregaDTO procesarExcel(ArrayList<EntregaDTO> entregas) throws PersonalsoftException {
		EntregaDTO obtenido = null;
		CallableStatement cs = null; 
        ArrayList<Parametro> parametros = null; 
        PersonalsoftException ps = null;
        
        try{ 
            String rutaSql = Configuracion.getInstance().getParametroApp("SP_ENTREGA_EXCEL");
            int cantRegistrosProcesados = 0;
            for (EntregaDTO entrega : entregas) {
            	parametros = new ArrayList<Parametro>();
            	parametros.add(new Parametro(Types.VARCHAR, entrega.getCodCia(),Parametro.IN_OUT)); 
            	parametros.add(new Parametro(Types.VARCHAR, entrega.getCodTransportador(),Parametro.IN_OUT));
            	parametros.add(new Parametro(Types.DECIMAL, new Integer(entrega.getNumOrden()),Parametro.IN_OUT));
            	parametros.add(new Parametro(Types.VARCHAR, entrega.getCausal(),Parametro.IN_OUT));
            	parametros.add(new Parametro(Types.VARCHAR, entrega.getFecha(),Parametro.IN_OUT));
            	parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
            	parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
            	
            	cs = this.bdHelper.cargarCallableStatement(rutaSql, parametros);
            	
            	if(cs != null){
            		cs.execute();
            		
            		obtenido = new EntregaDTO();
            		obtenido.setEstado(cs.getString(6).trim());
            		obtenido.setDescripcion(cs.getString(7).trim());
            		cantRegistrosProcesados++;
            	}
			}
            obtenido.setCantidadProcesada(String.valueOf(cantRegistrosProcesados));
        }
        catch (SQLException e) {
			ps = new PersonalsoftException(e);
			ps.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("errorGeneral"));
			logger.error(ps.getTraza());
			throw ps;
		} finally {
			try {
				BDHelper.close(cs);
			} catch (SQLException e) {
				ps = new PersonalsoftException(e);
				ps.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("errorGeneral"));
				logger.error(ps.getTraza());
				throw ps;
			}
		}
        
		return obtenido;
	}
}
