package votre.portaloperaciones.activaciondemandas.dao;

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
import votre.portaloperaciones.activaciondemandas.beans.AsignarDTO;
import votre.portaloperaciones.activaciondemandas.beans.CasoDTO;
import votre.portaloperaciones.activaciondemandas.beans.CmpDemandaDTO;
import votre.portaloperaciones.activaciondemandas.beans.EnviosDTO;
import votre.portaloperaciones.activaciondemandas.beans.GuiasMasterDTO;
import votre.portaloperaciones.activaciondemandas.beans.NovedadActDemandaDTO;
import votre.portaloperaciones.activaciondemandas.beans.OpcionDTO;
import votre.portaloperaciones.activaciondemandas.beans.RefeDemandaDTO;
import votre.portaloperaciones.activaciondemandas.beans.ReferenciasDTO;
import votre.portaloperaciones.util.Constantes;

public class ActivacionDemandasDAO implements IActivacionDemandasDAO {

	private BDHelper bdHelper;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public ActivacionDemandasDAO(BDHelper bdHelper) {
		super();
		this.bdHelper = bdHelper;
	}

	public ArrayList<OpcionDTO> consultarOpciones(OpcionDTO opcionDTO) throws PersonalsoftException {
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<OpcionDTO> opciones = new ArrayList<OpcionDTO>();
		PersonalsoftException ps = null;
		OpcionDTO obtenido = null;
		
		parametros.add(new Parametro(Types.VARCHAR, opcionDTO.getCia(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, opcionDTO.getAccion(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_LISTAR_VARIOS");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				rs = cs.getResultSet();
				if(rs != null){
					while(rs.next()){
						obtenido = new OpcionDTO();
						obtenido.setId(rs.getString(1).trim());
						obtenido.setNombre(rs.getString(2).trim());
						
						opciones.add(obtenido);
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
		
		return opciones;
	}

	public CmpDemandaDTO validarCompradora(CmpDemandaDTO cmpDemandaDTO) throws PersonalsoftException {
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, cmpDemandaDTO.getCia(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, cmpDemandaDTO.getId(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.NUMERIC, 0,Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_VALIDACOMPRADORA");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();

				cmpDemandaDTO.setNombre(cs.getString(3).trim());
				cmpDemandaDTO.setCiudad(cs.getString(4).trim());
				cmpDemandaDTO.setDepto(cs.getString(5).trim());
				cmpDemandaDTO.setRegion(cs.getString(6).trim());
				cmpDemandaDTO.setMailPlan(cs.getString(7).trim());
				cmpDemandaDTO.setTipo(cs.getString(8).trim());
				cmpDemandaDTO.setZona(cs.getString(9).trim());
				cmpDemandaDTO.setCodInterno(cs.getString(10));
				cmpDemandaDTO.setStatus(cs.getString(11));
				cmpDemandaDTO.setDsStatus(cs.getString(12));
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
		return cmpDemandaDTO;
	}

	public RefeDemandaDTO validarReferencia(RefeDemandaDTO refeDemandaDTO) throws PersonalsoftException {
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, refeDemandaDTO.getCia(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, refeDemandaDTO.getSku(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, refeDemandaDTO.getCampana(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_VALIDASKU");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();

				refeDemandaDTO.setDescripcion(cs.getString(4).trim());
				refeDemandaDTO.setStatus(cs.getString(5));
				refeDemandaDTO.setDsStatus(cs.getString(6));
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
		return refeDemandaDTO;
	}

	public NovedadActDemandaDTO grabarNovedad(NovedadActDemandaDTO dto) throws PersonalsoftException {
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, dto.getCia(),Parametro.IN));
		parametros.add(new Parametro(Types.BIGINT, Long.parseLong(dto.getCmpDemandaDTO().getCodInterno()),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getRefeDemandaDTO().getSku(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getRefeDemandaDTO().getCampana(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getMotivo(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getArea(),Parametro.IN));
		parametros.add(new Parametro(Types.NUMERIC, dto.getCantidad(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getTipo(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getUsuario(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getRefeDemandaDTO().getSkuSusti(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_GRABARNOVEDAD");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();

				dto.setStatus(cs.getString(11) != null ? cs.getString(11).trim() : "");
				dto.setDsStatus(cs.getString(12) != null ? cs.getString(12).trim() : "");
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
		return dto;
	}

	public ArrayList<CasoDTO> consultarCasos(CasoDTO dto) throws PersonalsoftException {
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<CasoDTO> aprobaciones = new ArrayList<CasoDTO>();
		PersonalsoftException ps = null;
		CasoDTO obtenido = null;
		int pos = 0;
		
		parametros.add(new Parametro(Types.VARCHAR, dto.getCia(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getCriterio(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getSeleccion(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getValor(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getAccionExcel()!=null ? dto.getAccionExcel():"N",Parametro.IN));
		parametros.add(new Parametro(Types.NUMERIC, dto.getSemilla(),Parametro.IN));
		parametros.add(new Parametro(Types.NUMERIC, 0,Parametro.OUT));
		parametros.add(new Parametro(Types.NUMERIC, 0,Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_CONSULTAR_CASOS");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				pos = 7;
				dto.setTotalPaginas(cs.getInt(pos++));
				dto.setIncremento(cs.getInt(pos++));
				dto.setStatus(cs.getString(pos++));
				dto.setDsStatus(cs.getString(pos++));
				
				rs = cs.getResultSet();
				if(rs != null){
					while(rs.next()){
						obtenido = new CasoDTO();
						pos = 1;
						//pos = 0 Es la posicion del registro, no es necesa
						obtenido.setId(rs.getString(++pos).trim());
						obtenido.setCodInterno(rs.getString(++pos).trim());
						obtenido.setNombre(rs.getString(++pos).trim());
						obtenido.setCiudad(rs.getString(++pos).trim());
						obtenido.setDepto(rs.getString(++pos).trim());
						obtenido.setRegion(rs.getString(++pos).trim());
						obtenido.setMailPlan(rs.getString(++pos).trim());
						obtenido.setTipo(rs.getString(++pos).trim());
						obtenido.setZona(rs.getString(++pos).trim());
						obtenido.setReferencia(rs.getString(++pos).trim());
						obtenido.setCampanaCaso(rs.getString(++pos).trim());
						obtenido.setDescripcion(rs.getString(++pos).trim());
						obtenido.setCantidad(rs.getString(++pos).trim());
						obtenido.setMotivo(rs.getString(++pos).trim());
						obtenido.setArea(rs.getString(++pos).trim());
						obtenido.setFechaRegistro(rs.getString(++pos).trim());
						obtenido.setCampanaReclamo(rs.getString(++pos).trim());
						obtenido.setEstado(rs.getString(++pos).trim());
						obtenido.setOrden(rs.getString(++pos).trim());
						obtenido.setSkuSustituto(rs.getString(++pos)!=null ? rs.getString(pos).trim():"");
						obtenido.setDsSkuSustituto(rs.getString(++pos)!=null ? rs.getString(pos).trim():"");
						obtenido.setCantidadEntregada(rs.getString(++pos).trim());
						obtenido.setTipoDespacho(rs.getString(++pos).trim());
						obtenido.setFechaAprobacion(rs.getString(++pos).trim());
						obtenido.setGuia(rs.getString(++pos).trim());
						obtenido.setTransportadora(rs.getString(++pos).trim());
						obtenido.setCedulaTransportista(rs.getString(++pos).trim());
						obtenido.setDireccionDespacho(rs.getString(++pos).trim());
						obtenido.setBarrioDespacho(rs.getString(++pos).trim());
						obtenido.setCiudadDespacho(rs.getString(++pos).trim());
						obtenido.setDeptoDespacho(rs.getString(++pos).trim());
						obtenido.setTel1Despacho(rs.getString(++pos).trim());
						obtenido.setTarifa(rs.getString(++pos).trim());
						obtenido.setFechaDespacho(rs.getString(++pos).trim());
						pos++;
						obtenido.setDsTransportadora(rs.getString(++pos).trim());
						obtenido.setNombreTransportista(rs.getString(++pos).trim());
						obtenido.setGuiaMaster(rs.getString(++pos).trim());
						obtenido.setCampanaIni(rs.getString(++pos).trim());
						obtenido.setCampanaFin(rs.getString(++pos).trim());
						obtenido.setTipoProducto(rs.getString(++pos).trim());
						obtenido.setDespachoAuto(rs.getString(++pos).trim());
						obtenido.setCelular(rs.getString(++pos).trim());
						obtenido.setSector(rs.getString(++pos).trim());
						obtenido.setCampanaOrigen(rs.getString(++pos).trim());
						obtenido.setDsEstado(rs.getString(++pos).trim());
						
						aprobaciones.add(obtenido);
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
		
		return aprobaciones;
	}

	public CasoDTO grabarAprobacion(CasoDTO dto) throws PersonalsoftException {
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, dto.getCia(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getAccion(),Parametro.IN));
		parametros.add(new Parametro(Types.BIGINT, Long.parseLong(dto.getCodInterno()),Parametro.IN));
		parametros.add(new Parametro(Types.BIGINT, Long.parseLong(dto.getOrden()),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getReferencia(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getSkuSustituto(),Parametro.IN));
		parametros.add(new Parametro(Types.BIGINT, dto.getCantidad()!=null && !"".equals(dto.getCantidad()) ? Long.parseLong(dto.getCantidad()) : 0,Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getTipoDespacho(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getUsuario(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getGuia()!=null ? dto.getGuia().trim():"",Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getTransportadora()!=null ? dto.getTransportadora().trim():"",Parametro.IN));
		parametros.add(new Parametro(Types.BIGINT, dto.getCedulaTransportista()!=null && !"".equals(dto.getCedulaTransportista()) ?Long.parseLong(dto.getCedulaTransportista()):0,Parametro.IN));
		parametros.add(new Parametro(Types.BIGINT, dto.getFechaDespacho()!=null ?Long.parseLong(dto.getFechaDespacho()):0,Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_GRABARAPROBACION");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();

				dto.setStatus(cs.getString(14));
				dto.setDsStatus(cs.getString(15));
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
		return dto;
	}

	public AsignarDTO asignarTransportadores(AsignarDTO dto) throws PersonalsoftException {
		AsignarDTO obtenido = null;
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		try{
			parametros.add(new Parametro(Types.VARCHAR, dto.getCodCia(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getAccion(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getValor(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, dto.getCantidad(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
			parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
			
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_AGPEDIDOWMS");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = new AsignarDTO();
				obtenido.setEstado(cs.getString(5) != null ? cs.getString(5).trim() : "");
				obtenido.setDescripcion(cs.getString(6) != null ? cs.getString(6).trim() : "");
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

	public ReferenciasDTO consultarReferencias(ReferenciasDTO dto) throws PersonalsoftException {
		ReferenciasDTO obtenido = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<ReferenciasDTO> referencias = new ArrayList<ReferenciasDTO>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, dto.getCodCia(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getAccion(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_LISTAR_VARIOS");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = new ReferenciasDTO();
				obtenido.setEstado(cs.getString(3) != null ? cs.getString(3).trim() : "");
				obtenido.setDescripcion(cs.getString(4) != null ? cs.getString(4).trim() : "");
				
				rs = cs.getResultSet();
				if(rs != null){
					while (rs.next()) {
						ReferenciasDTO referenciasDTO = new ReferenciasDTO();
						referenciasDTO.setReferencia(rs.getString(1) != null ? rs.getString(1).trim() : "");
						referenciasDTO.setDescripRefe(rs.getString(2) != null ? rs.getString(2).trim() : "");
						if(Constantes.ACCION_CAMBIAR_REFE.equals(dto.getAccion()) || Constantes.ACCION_REFERENCIAS.equals(dto.getAccion())){
							referenciasDTO.setCantidadCompra(rs.getString(3) != null ? rs.getString(3).trim() : "");
						}
						referencias.add(referenciasDTO);
					}
					obtenido.setReferencias(referencias);
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

	public AsignarDTO generarGuias(AsignarDTO dto) throws PersonalsoftException {
		AsignarDTO obtenido = null;
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		try{
			parametros.add(new Parametro(Types.VARCHAR, dto.getCodCia(),Parametro.IN));
			parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
			parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
			
			
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_GENERARGUIAS");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = new AsignarDTO();
				obtenido.setEstado(cs.getString(3) != null ? cs.getString(3).trim() : "");
				obtenido.setDescripcion(cs.getString(4) != null ? cs.getString(4).trim() : "");
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

	public ReferenciasDTO cambiarACorreoExtra(ReferenciasDTO dto) throws PersonalsoftException {
		ReferenciasDTO obtenido = null;
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, dto.getCodCia(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getReferencia(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_CORREO_EXTRA");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();

				obtenido = dto;
				obtenido.setEstado(cs.getString(3) != null ? cs.getString(3).trim() : "");
				obtenido.setDescripcion(cs.getString(4) != null ? cs.getString(4).trim() : "");
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

	public EnviosDTO eliminarEnvios(EnviosDTO dto) throws PersonalsoftException {
		EnviosDTO obtenido = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<EnviosDTO> arrEnvios = new ArrayList<EnviosDTO>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, dto.getCodCia(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getTipo(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getAccion(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getValor(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getCodInterno(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getNumOrden(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getSku(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getUsuario(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_ELIMINAR_ENVIOS");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = dto;
				obtenido.setStatus(cs.getString(9) != null ? cs.getString(9).trim() : "");
				obtenido.setDsStatus(cs.getString(10) != null ? cs.getString(10).trim() : "");
				
				rs = cs.getResultSet();
				if(rs != null){
					while(rs.next()){
						if(dto.getTipo().equals(Constantes.TIPO_ELIMINAR_ENVIO)){
							EnviosDTO enviosDTO = new EnviosDTO();
							enviosDTO.setTipoEnvio(rs.getString(1) != null ? rs.getString(1).trim() : "");
							enviosDTO.setTotalRegistros(rs.getString(2) != null ? rs.getString(2).trim() : "");
							
							arrEnvios.add(enviosDTO);
						}
						if(dto.getTipo().equals(Constantes.TIPO_ELIMINAR_CEDULA)){
							EnviosDTO enviosDTO = new EnviosDTO();
							enviosDTO.setCampana(rs.getString(1) != null ? rs.getString(1).trim() : "");
							enviosDTO.setCedula(rs.getString(2) != null ? rs.getString(2).trim() : "");
							enviosDTO.setNombre(rs.getString(3) != null ? rs.getString(3).trim() : "");
							enviosDTO.setSku(rs.getString(4) != null ? rs.getString(4).trim() : "");
							enviosDTO.setDescripSku(rs.getString(5) != null ? rs.getString(5).trim() : "");
							enviosDTO.setCodInterno(rs.getString(6) != null ? rs.getString(6).trim() : "");
							enviosDTO.setNumOrden(rs.getString(7) != null ? rs.getString(7).trim() : "");
							
							arrEnvios.add(enviosDTO);
						}
					}
					obtenido.setArrEnvios(arrEnvios);
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
	
	public GuiasMasterDTO consultarGuiasMaster(GuiasMasterDTO dto) throws PersonalsoftException {
		GuiasMasterDTO obtenido = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<GuiasMasterDTO> guias = new ArrayList<GuiasMasterDTO>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, dto.getCodCia(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getAccion(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getCedulaTrans(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getCodigoTrans(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getCantidad(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_GUIAS_MASTER");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = new GuiasMasterDTO();
				obtenido.setEstado(cs.getString(6) != null ? cs.getString(6).trim() : "");
				obtenido.setDescripcion(cs.getString(7) != null ? cs.getString(7).trim() : "");
				
				rs = cs.getResultSet();
				if(rs != null){
					while (rs.next()) {
						GuiasMasterDTO guiasMasterDTO = new GuiasMasterDTO();
						guiasMasterDTO.setCedulaTrans(rs.getString(1) != null ? rs.getString(1).trim() : "");
						guiasMasterDTO.setNombreTrans(rs.getString(2) != null ? rs.getString(2).trim() : "");
						guiasMasterDTO.setCodigoTrans(rs.getString(3) != null ? rs.getString(3).trim() : "");
						guiasMasterDTO.setTotalPremios(rs.getString(4) != null ? rs.getString(4).trim() : "");
						
						guias.add(guiasMasterDTO);
					}
					obtenido.setGuias(guias);
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
}
