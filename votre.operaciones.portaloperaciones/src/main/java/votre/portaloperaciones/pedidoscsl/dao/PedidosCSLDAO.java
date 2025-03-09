package votre.portaloperaciones.pedidoscsl.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.util.CargadorMsj;
import votre.portaloperaciones.pedidoscsl.beans.ListaPedidosDTO;
import votre.portaloperaciones.pedidoscsl.beans.PedidosCSLDTO;
import votre.portaloperaciones.util.Constantes;
import votre.portaloperaciones.util.Util;

public class PedidosCSLDAO implements IPedidosCSLDAO {
	
	private BDHelper bdHelper;
	private Logger logger = Logger.getLogger(this.getClass());

	public PedidosCSLDAO(BDHelper bdHelper) {
		super();
		this.bdHelper = bdHelper;
	}

	public PedidosCSLDTO listarOpciones(PedidosCSLDTO dto) throws PersonalsoftException {
		CallableStatement cs = null;
		ResultSet rs = null;
		PedidosCSLDTO obtenido = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<ListaPedidosDTO> arrPedidos = new ArrayList<ListaPedidosDTO>();
		PersonalsoftException ps = null;
		int pos = 0;
		
		parametros.add(new Parametro(Types.VARCHAR, dto.getCodCia(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, dto.getAccion(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_LISTAR_PEDIDOS");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = dto;
				pos = 2;
				obtenido.setSpSts(cs.getString(++pos) != null ? cs.getString(pos).trim() : "");
				obtenido.setSpDes(cs.getString(++pos) != null ? cs.getString(pos).trim() : "");
				
				rs = cs.getResultSet();
				if(rs != null){
					if(Constantes.ACCION_TIPO_PEDIDO.equals(dto.getAccion())){
						while(rs.next()){
							pos = 0;
							ListaPedidosDTO pedido = new ListaPedidosDTO();
							pedido.setTipoPedido(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
							pedido.setDesPedido(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
							pedido.setTipoTransporte(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
							pedido.setDestinatario(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
							
							arrPedidos.add(pedido);
						}
						obtenido.setListaPedidos(arrPedidos.toArray(new ListaPedidosDTO[arrPedidos.size()]));
					}
					else if(Constantes.ACCION_REGIONES.equals(dto.getAccion())){
						ArrayList<String> arrRegiones = new ArrayList<String>();
						while(rs.next()){
							pos = 0;
							String region = rs.getString(++pos) != null ? rs.getString(pos).trim() : "";
							arrRegiones.add(region);
						}
						obtenido.setArrDatosCombo(arrRegiones);
					}
					else if(Constantes.ACCION_ZONA.equals(dto.getAccion())){
						ArrayList<String> arrZonas = new ArrayList<String>();
						while(rs.next()){
							pos = 0;
							String zona = rs.getString(++pos) != null ? rs.getString(pos).trim() : "";
							arrZonas.add(zona);
						}
						obtenido.setArrDatosCombo(arrZonas);
					}
					else if(Constantes.ACCION_COMANDO.equals(dto.getAccion())){
						ArrayList<PedidosCSLDTO> arrComandos = new ArrayList<PedidosCSLDTO>();
						while(rs.next()){
							pos = 0;
							PedidosCSLDTO datosComando = new PedidosCSLDTO();
							datosComando.setCedulaComando(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
							datosComando.setNombreComando(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
							
							arrComandos.add(datosComando);
						}
						obtenido.setArrPedidos(arrComandos);
					}
				}
			}
		} catch (SQLException e) {
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

	public PedidosCSLDTO validarSolicitud(PedidosCSLDTO dto) throws PersonalsoftException {
		CallableStatement cs = null;
		PedidosCSLDTO obtenido = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		int pos = 0;
		
		parametros.add(new Parametro(Types.VARCHAR, dto.getCodCia(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getDestinatario(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getVal1(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getSku(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getCantidad(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getCentroCostos(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_VALIDAR_SOLICITUD");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = dto;
				pos = 6;
				obtenido.setSpSts(cs.getString(++pos) != null ? cs.getString(pos).trim() : "");
				obtenido.setSpDes(cs.getString(++pos) != null ? cs.getString(pos).trim() : "");
			}
		} catch (SQLException e) {
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

	public PedidosCSLDTO grabarItem(PedidosCSLDTO dto) throws PersonalsoftException {
		CallableStatement cs = null;
		PedidosCSLDTO obtenido = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		int pos = 0;
		
		parametros.add(new Parametro(Types.VARCHAR, dto.getCodCia(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, dto.getTipoPedido(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, dto.getSku(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.BIGINT, dto.getCantidad(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, dto.getVal1(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, dto.getDestinatario(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, dto.getCentroCostos(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, dto.getUsuario(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, dto.getEstrategia(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_GRABAR_ITEM");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = dto;
				pos = 9;
				obtenido.setSpSts(cs.getString(++pos) != null ? cs.getString(pos).trim() : "");
				obtenido.setSpDes(cs.getString(++pos) != null ? cs.getString(pos).trim() : "");
			}
		} catch (SQLException e) {
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

	public PedidosCSLDTO consultarSolicitud(PedidosCSLDTO dto) throws PersonalsoftException {
		CallableStatement cs = null;
		ResultSet rs = null;
		PedidosCSLDTO obtenido = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<ListaPedidosDTO> arrPedidos = new ArrayList<ListaPedidosDTO>();
		PersonalsoftException ps = null;
		int pos = 0;
		
		parametros.add(new Parametro(Types.VARCHAR, dto.getCodCia(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, dto.getUsuario(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, dto.getNumOrden(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, dto.getCedula(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_CONSUL_SOLICITUD");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = dto;
				pos = 4;
				obtenido.setSpSts(cs.getString(++pos) != null ? cs.getString(pos).trim() : "");
				obtenido.setSpDes(cs.getString(++pos) != null ? cs.getString(pos).trim() : "");
				
				rs = cs.getResultSet();
				if(rs != null){
					while(rs.next()){
						pos = 0;
						ListaPedidosDTO pedido = new ListaPedidosDTO();
						pedido.setNumeroOrden(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						pedido.setTipoPedido(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						pedido.setEstrategia(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						pedido.setEstado(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						pedido.setCantidad(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						pedido.setDesPedido(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						
						arrPedidos.add(pedido);
					}
					obtenido.setListaPedidos(arrPedidos.toArray(new ListaPedidosDTO[arrPedidos.size()]));
				}
			}
		} catch (SQLException e) {
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

	public PedidosCSLDTO consultarDetalleSolicitud(PedidosCSLDTO dto) throws PersonalsoftException {
		CallableStatement cs = null;
		ResultSet rs = null;
		PedidosCSLDTO obtenido = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<PedidosCSLDTO> arrDetalle = new ArrayList<PedidosCSLDTO>();
		PersonalsoftException ps = null;
		int pos = 0;
		
		parametros.add(new Parametro(Types.VARCHAR, dto.getCodCia(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, dto.getNumOrden(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_DETALLE_SOLICITUD");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = dto;
				pos = 2;
				obtenido.setSpSts(cs.getString(++pos) != null ? cs.getString(pos).trim() : "");
				obtenido.setSpDes(cs.getString(++pos) != null ? cs.getString(pos).trim() : "");
				
				rs = cs.getResultSet();
				if(rs != null){
					while(rs.next()){
						pos = 0;
						PedidosCSLDTO detallePedido = new PedidosCSLDTO();
						detallePedido.setCedula(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						detallePedido.setSku(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						detallePedido.setDescripcionItem(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						detallePedido.setCantidad(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						detallePedido.setCentroCostos(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						detallePedido.setDestinatario(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						detallePedido.setTransportista(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						detallePedido.setTransportadora(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						detallePedido.setGuia(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						detallePedido.setFechaEmbarque(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						detallePedido.setGuiaReempaque(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						
						arrDetalle.add(detallePedido);
					}
					obtenido.setArrPedidos(arrDetalle);
				}
			}
		} catch (SQLException e) {
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

	public PedidosCSLDTO consultarAuditoria(PedidosCSLDTO dto) throws PersonalsoftException {
		CallableStatement cs = null;
		ResultSet rs = null;
		PedidosCSLDTO obtenido = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<PedidosCSLDTO> arrAuditoria = new ArrayList<PedidosCSLDTO>();
		PersonalsoftException ps = null;
		int pos = 0;
		
		parametros.add(new Parametro(Types.VARCHAR, dto.getCodCia(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, dto.getNumOrden(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_AUDI_SOLICITUD");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = dto;
				pos = 2;
				obtenido.setSpSts(cs.getString(++pos) != null ? cs.getString(pos).trim() : "");
				obtenido.setSpDes(cs.getString(++pos) != null ? cs.getString(pos).trim() : "");
				
				rs = cs.getResultSet();
				if(rs != null){
					while(rs.next()){
						pos = 0;
						PedidosCSLDTO auditoria = new PedidosCSLDTO();
						auditoria.setAccionInfo(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						auditoria.setNumOrden(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						auditoria.setFecha(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						auditoria.setHora(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						auditoria.setUsuario(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						auditoria.setObservacion(rs.getString(++pos) != null ? rs.getString(pos).trim() : "");
						
						arrAuditoria.add(auditoria);
					}
					obtenido.setArrPedidos(arrAuditoria);
				}
			}
		} catch (SQLException e) {
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
}