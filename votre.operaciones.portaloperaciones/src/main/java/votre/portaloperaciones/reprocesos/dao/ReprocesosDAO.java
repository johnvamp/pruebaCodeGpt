package votre.portaloperaciones.reprocesos.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import votre.portaloperaciones.reprocesos.beans.SolicitudDTO;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.util.CargadorMsj;
import co.com.personalsoft.base.util.Fecha;

public class ReprocesosDAO implements IReprocesosDAO {
	
	private BDHelper bdHelper;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public ReprocesosDAO(BDHelper bdHelper) {
		super();
		this.bdHelper = bdHelper;
	}

	public SolicitudDTO crearNuevaSolictud(SolicitudDTO dto) throws PersonalsoftException {
		SolicitudDTO obtenido = null;
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		try{
			parametros.add(new Parametro(Types.VARCHAR, dto.getCodCia(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getFechaEntrega(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getTipoEntrega(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getObservacion(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getUsuario(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
			parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
			parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
			
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_NUEVASOLICITUD");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = dto;
				obtenido.setNumRequerimiento(cs.getString(6) != null ? cs.getString(6).trim() : "");
				obtenido.setStatus(cs.getString(7) != null ? cs.getString(7).trim() : "");
				obtenido.setDsStatus(cs.getString(8) != null ? cs.getString(8).trim() : "");
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

	public SolicitudDTO agregarReferencia(SolicitudDTO dto) throws PersonalsoftException {
		SolicitudDTO obtenido = null;
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		try{
			parametros.add(new Parametro(Types.VARCHAR, dto.getCodCia(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getNumRequerimiento(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getReferencia(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getCantidad(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
			parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
			
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_AGREGARREFE");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = dto;
				obtenido.setStatus(cs.getString(5) != null ? cs.getString(5).trim() : "");
				obtenido.setDsStatus(cs.getString(6) != null ? cs.getString(6).trim() : "");
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

	public ArrayList<SolicitudDTO> consultarSolicitudes(SolicitudDTO dto) throws PersonalsoftException {
		ArrayList<SolicitudDTO> arrSolicitudes = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		try{
			parametros.add(new Parametro(Types.VARCHAR, dto.getCodCia(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getNumRequerimiento(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getAccionTramite(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getAccion(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
			parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
			
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_LISTARSOLICITUD");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				rs = cs.getResultSet();
				if(rs != null){
					arrSolicitudes = new ArrayList<SolicitudDTO>();
					while(rs.next()){
						SolicitudDTO solicitud = new SolicitudDTO();
						if(dto.getAccionTramite().equals(Constantes.ACCION_CONSULTAR_INSUMOS) &&
								dto.getAccion().equals(Constantes.ACCION_CONSULTAR_REFERENCIA)){
							solicitud.setNumRequerimiento(rs.getString(1) != null ? rs.getString(1).trim() : "");
							solicitud.setReferencia(rs.getString(2) != null ? rs.getString(2).trim() : "");
							solicitud.setCantidad(rs.getString(3) != null ? rs.getString(3).trim() : "");
							solicitud.setFechaInicio(rs.getString(6) != null ? rs.getString(6).trim() : "");
							solicitud.setFechaInicio(Fecha.cambiarFormatoFecha(solicitud.getFechaInicio(), Fecha.EXPR_YYYYMMDD, Fecha.EXPR_DDMMYYYY_LINEA));
							solicitud.setUbicacion(rs.getString(7) != null ? rs.getString(7).trim() : "");
							solicitud.setEstado(rs.getString(8) != null ? rs.getString(8).trim() : "");
						}
						else if(dto.getAccionTramite().equals(Constantes.ACCION_CONSULTAR_REPROCESO) &&
								dto.getAccion().equals(Constantes.ACCION_CONSULTAR_SOLICITUD)){
							solicitud.setNumRequerimiento(rs.getString(1) != null ? rs.getString(1).trim() : "");
							solicitud.setUsuario(rs.getString(2) != null ? rs.getString(2).trim() : "");
							solicitud.setFechaEntrega(rs.getString(3) != null ? rs.getString(3).trim() : "");
							solicitud.setFechaEntrega(Fecha.cambiarFormatoFecha(solicitud.getFechaEntrega(), Fecha.EXPR_YYYYMMDD, Fecha.EXPR_DDMMYYYY_LINEA));
							solicitud.setFechaInicio(rs.getString(4) != null ? rs.getString(4).trim() : "");
							solicitud.setFechaInicio(Fecha.cambiarFormatoFecha(solicitud.getFechaInicio(), Fecha.EXPR_YYYYMMDD, Fecha.EXPR_DDMMYYYY_LINEA));
							solicitud.setObservacion(rs.getString(5) != null ? rs.getString(5).trim() : "");
							solicitud.setEstado(rs.getString(6) != null ? rs.getString(6).trim() : "");
							solicitud.setTipoEntrega(rs.getString(7) != null ? rs.getString(7).trim() : "");
						}
						else if(dto.getAccionTramite().equals(Constantes.ACCION_CONSULTAR_TRAMITE) &&
								dto.getAccion().equals(Constantes.ACCION_CONSULTAR_SOLICITUD)){
							solicitud.setNumRequerimiento(rs.getString(1) != null ? rs.getString(1).trim() : "");
							solicitud.setUsuario(rs.getString(2) != null ? rs.getString(2).trim() : "");
							solicitud.setFechaEntrega(rs.getString(3) != null ? rs.getString(3).trim() : "");
							solicitud.setFechaEntrega(Fecha.cambiarFormatoFecha(solicitud.getFechaEntrega(), Fecha.EXPR_YYYYMMDD, Fecha.EXPR_DDMMYYYY_LINEA));
							solicitud.setEstado(rs.getString(6) != null ? rs.getString(6).trim() : "");
						}
						else if(dto.getAccion().equals(Constantes.ACCION_CONSULTAR_REFERENCIA)){
							solicitud.setNumRequerimiento(rs.getString(1) != null ? rs.getString(1).trim() : "");
							solicitud.setReferencia(rs.getString(2) != null ? rs.getString(2).trim() : "");
							solicitud.setCantidad(rs.getString(3) != null ? rs.getString(3).trim() : "");
							solicitud.setStatus(cs.getString(5) != null ? cs.getString(5).trim() : "");
							solicitud.setDsStatus(cs.getString(6) != null ? cs.getString(6).trim() : "");
						}
						else if(dto.getAccion().equals(Constantes.ACCION_CONSULTAR_SOLICITUD)){
							solicitud.setNumRequerimiento(rs.getString(1) != null ? rs.getString(1).trim() : "");
							solicitud.setUsuario(rs.getString(2) != null ? rs.getString(2).trim() : "");
							solicitud.setFechaEntrega(rs.getString(3) != null ? rs.getString(3).trim() : "");
							solicitud.setFechaEntrega(Fecha.cambiarFormatoFecha(solicitud.getFechaEntrega(), Fecha.EXPR_YYYYMMDD, Fecha.EXPR_DDMMYYYY_LINEA));
							solicitud.setFechaCreacion(rs.getString(4) != null ? rs.getString(4).trim() : "");
							solicitud.setFechaCreacion(Fecha.cambiarFormatoFecha(solicitud.getFechaCreacion(), Fecha.EXPR_YYYYMMDD, Fecha.EXPR_DDMMYYYY_LINEA));
							solicitud.setObservacion(rs.getString(5) != null ? rs.getString(5).trim() : "");
							solicitud.setTipoEntrega(rs.getString(7) != null ? rs.getString(7).trim() : "");
						}
						arrSolicitudes.add(solicitud);
					}
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
		return arrSolicitudes;
	}

	public SolicitudDTO aprobarSolicitud(SolicitudDTO dto) throws PersonalsoftException {
		SolicitudDTO obtenido = null;
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		try{
			parametros.add(new Parametro(Types.VARCHAR, dto.getCodCia(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getNumRequerimiento(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getAccion(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getFechaInicio(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getFechaEntrega(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getObservacion(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
			parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
			
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_APROBAR_SOLICITUD");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = dto;
				obtenido.setStatus(cs.getString(7) != null ? cs.getString(7).trim() : "");
				obtenido.setDsStatus(cs.getString(8) != null ? cs.getString(8).trim() : "");
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

	public SolicitudDTO procesarReferencia(SolicitudDTO dto) throws PersonalsoftException {
		SolicitudDTO obtenido = null;
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		try{
			parametros.add(new Parametro(Types.VARCHAR, dto.getCodCia(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getNumRequerimiento(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getReferencia(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getAccion(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getUbicacion(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getEstado(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getCantidad(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
			parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
			
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_PROCESAR_REFE");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = new SolicitudDTO();
				obtenido.setStatus(cs.getString(8) != null ? cs.getString(8).trim() : "");
				obtenido.setDsStatus(cs.getString(9) != null ? cs.getString(9).trim() : "");
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
}
