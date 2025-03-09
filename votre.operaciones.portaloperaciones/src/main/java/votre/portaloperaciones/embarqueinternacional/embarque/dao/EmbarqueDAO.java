package votre.portaloperaciones.embarqueinternacional.embarque.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import votre.portaloperaciones.embarqueinternacional.embarque.beans.DetalleEmbarqueDTO;
import votre.portaloperaciones.embarqueinternacional.embarque.beans.EmbarqueDTO;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.util.CargadorMsj;

public class EmbarqueDAO implements IEmbarqueDAO {
	
	private BDHelper bdHelper;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public EmbarqueDAO(BDHelper bdhelper){
		super();		
		this.bdHelper = bdhelper;
	}

	public EmbarqueDTO cargarTitulos(String codCia) throws PersonalsoftException {
		EmbarqueDTO obtenido = null;
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		
		PersonalsoftException ps = null;
		parametros.add(new Parametro(Types.VARCHAR, codCia,Parametro.IN_OUT));
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
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_CARGAR_TITULOS");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = new EmbarqueDTO();
				obtenido.setCodCia(cs.getString(1).trim());
				obtenido.setTitulo0(cs.getString(2).trim());
				obtenido.setTitulo1(cs.getString(3).trim());
				obtenido.setTitulo2(cs.getString(4).trim());
				obtenido.setTitulo3(cs.getString(5).trim());
				obtenido.setTitulo4(cs.getString(6).trim());
				obtenido.setTitulo5(cs.getString(7).trim());
				obtenido.setTitulo6(cs.getString(8).trim());
				
				obtenido.setEstado(cs.getString(9).trim());
				obtenido.setDescripcion(cs.getString(10).trim());
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

	public EmbarqueDTO abrirEmbarque(EmbarqueDTO embarqueDTO) throws PersonalsoftException {
		EmbarqueDTO obtenido = null;
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getAccion(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getCodTransportador(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getPlaca(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getTipoEmbarque(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getFecha(),Parametro.IN_OUT));
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
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_ABRIR_EMBARQUE");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = embarqueDTO;
				
				obtenido.setCodTransportador(cs.getString(3).trim());
				obtenido.setPlaca(cs.getString(4).trim());
				obtenido.setFecha(cs.getString(6).trim());
				obtenido.setNroPedidos(cs.getString(7).trim());
				obtenido.setTitPantalla(cs.getString(8).trim());
				obtenido.setTitTransportador(cs.getString(9).trim());
				obtenido.setTitCamion(cs.getString(10).trim());
				obtenido.setTitTotal(cs.getString(11).trim());
				obtenido.setNumOrden(cs.getString(12).trim());
				obtenido.setTitAceptar(cs.getString(13).trim());
				obtenido.setTitDetener(cs.getString(14).trim());
				obtenido.setTitFinalizar(cs.getString(15).trim());
				obtenido.setEstado(cs.getString(16).trim());
				obtenido.setDescripcion(cs.getString(17).trim());
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

	public EmbarqueDTO embarcar(EmbarqueDTO embarqueDTO) throws PersonalsoftException {
		EmbarqueDTO obtenido = null;
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getCodTransportador(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getPlaca(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.DECIMAL, new Double (embarqueDTO.getNumOrden()),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getFecha(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.DECIMAL, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_EMBARCAR");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = embarqueDTO;
				obtenido.setCodTransportador(cs.getString(2).trim());
				obtenido.setPlaca(cs.getString(3).trim());
				obtenido.setNumOrden(cs.getString(4).trim());
				obtenido.setFecha(cs.getString(5).trim());
				obtenido.setNroPedidos(cs.getString(6).trim());
				obtenido.setEstado(cs.getString(7).trim());
				obtenido.setDescripcion(cs.getString(8).trim());
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

	public EmbarqueDTO detenerEmbarque(EmbarqueDTO embarqueDTO) throws PersonalsoftException {
		EmbarqueDTO obtenido = null;
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getCodTransportador(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getPlaca(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getFecha(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_DETENER_EMBARQUE");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = new EmbarqueDTO();
				obtenido.setCodTransportador(cs.getString(2).trim());
				obtenido.setPlaca(cs.getString(3).trim());
				obtenido.setFecha(cs.getString(4).trim());
				obtenido.setEstado(cs.getString(5).trim());
				obtenido.setDescripcion(cs.getString(6).trim());
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

	public EmbarqueDTO cerrarEmbarque(EmbarqueDTO embarqueDTO) throws PersonalsoftException {
		EmbarqueDTO obtenido = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<DetalleEmbarqueDTO> arrDetalle = new ArrayList<DetalleEmbarqueDTO>();
		DetalleEmbarqueDTO[] detalle;
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getCodTransportador(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getPlaca(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getRespuesta(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getAccion(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getFecha(),Parametro.IN_OUT));
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
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_CERRAR_EMBARQUE");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = new EmbarqueDTO();
				obtenido.setCodTransportador(cs.getString(2).trim());
				obtenido.setPlaca(cs.getString(3).trim());
				obtenido.setFecha(cs.getString(6).trim());
				obtenido.setNroPedidos(cs.getString(7).trim());
				obtenido.setTitPantalla(cs.getString(8).trim());
				obtenido.setTitTransportador(cs.getString(9).trim());
				obtenido.setTitCamion(cs.getString(10).trim());
				obtenido.setTitTotal(cs.getString(11).trim());
				obtenido.setTitCerrar(cs.getString(12).trim());
				obtenido.setTitRegresar(cs.getString(13).trim());
				obtenido.setTitAceptar(cs.getString(14).trim());
				obtenido.setTitCompraDirecta(cs.getString(15).trim());
				obtenido.setTitRelacionEmbarque(cs.getString(16).trim());
				obtenido.setTitCamionEnc(cs.getString(17).trim());
				obtenido.setTitFechaEnc(cs.getString(18).trim());
				obtenido.setTitNroOrden(cs.getString(19).trim());
				obtenido.setTitCedula(cs.getString(20).trim());
				obtenido.setTitNombre(cs.getString(21).trim());
				obtenido.setTitDireccion(cs.getString(22).trim());
				obtenido.setTitZona(cs.getString(23).trim());
				obtenido.setTitTotalGuias(cs.getString(24).trim());
				obtenido.setTitDestino(cs.getString(25).trim());
				obtenido.setTitTelefono(cs.getString(26).trim());
				obtenido.setValorConcatenado(cs.getString(27).trim());
				obtenido.setTitCampana(cs.getString(28).trim());
				obtenido.setTitPorteria(cs.getString(29).trim());
				obtenido.setTitTelefono2(cs.getString(30).trim());
				obtenido.setTitCelular(cs.getString(31).trim());
				obtenido.setTitDistrito(cs.getString(32).trim());
				obtenido.setTitCanton(cs.getString(33).trim());
				obtenido.setTitProvincia(cs.getString(34).trim());
				obtenido.setTitValorOrden(cs.getString(35).trim());
				obtenido.setTransportador(cs.getString(36).trim());
				obtenido.setEstado(cs.getString(37).trim());
				obtenido.setDescripcion(cs.getString(38).trim());
				
				rs = cs.getResultSet();
				
				if(rs != null){
					arrDetalle = new ArrayList<DetalleEmbarqueDTO>();
					while(rs.next()){
						DetalleEmbarqueDTO detalleEmbarqueDTO = new DetalleEmbarqueDTO();
						detalleEmbarqueDTO.setObservacion(rs.getString(1).trim());
						detalleEmbarqueDTO.setNumOrden(rs.getString(2).trim());
						detalleEmbarqueDTO.setZona(rs.getString(3).trim());
						detalleEmbarqueDTO.setCedula(rs.getString(4).trim());
						detalleEmbarqueDTO.setNombre(rs.getString(5).trim());
						detalleEmbarqueDTO.setDireccion(rs.getString(6).trim());
						detalleEmbarqueDTO.setTelefono(rs.getString(7).trim());
						detalleEmbarqueDTO.setDestino(rs.getString(8).trim());
						detalleEmbarqueDTO.setValorDeclarado(rs.getString(9).trim());
						detalleEmbarqueDTO.setCampana(rs.getString(10).trim());
						detalleEmbarqueDTO.setPorteria(rs.getString(11).trim());
						detalleEmbarqueDTO.setTelefono2(rs.getString(12).trim());
						detalleEmbarqueDTO.setCelular(rs.getString(13).trim());
						detalleEmbarqueDTO.setDistrito(rs.getString(14).trim());
						detalleEmbarqueDTO.setCanton(rs.getString(15).trim());
						detalleEmbarqueDTO.setProvincia(rs.getString(16).trim());
						detalleEmbarqueDTO.setValorOrden(rs.getString(17).trim());
						
						arrDetalle.add(detalleEmbarqueDTO);
					}
					detalle = arrDetalle.toArray(new DetalleEmbarqueDTO[arrDetalle.size()]);
					obtenido.setDetalle(detalle);
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

	public EmbarqueDTO desembarcar(EmbarqueDTO embarqueDTO) throws PersonalsoftException {
		EmbarqueDTO obtenido = null;
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getAccion(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getCodTransportador(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getPlaca(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.DECIMAL, new Integer(embarqueDTO.getNumOrden()),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, embarqueDTO.getFecha(),Parametro.IN_OUT));
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
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_DESEMBARQUE");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = new EmbarqueDTO();
				obtenido.setCodTransportador(cs.getString(3).trim());
				obtenido.setPlaca(cs.getString(4).trim());
				obtenido.setFecha(cs.getString(6).trim());
				obtenido.setNroPedidos(cs.getString(7).trim());
				obtenido.setTitPantalla(cs.getString(8).trim());
				obtenido.setTitTransportador(cs.getString(9).trim());
				obtenido.setTitCamion(cs.getString(10).trim());
				obtenido.setTitTotal(cs.getString(11).trim());
				obtenido.setTitNroOrden(cs.getString(12).trim());
				obtenido.setTitAceptar(cs.getString(13).trim());
				obtenido.setTitRegresar(cs.getString(14).trim());
				obtenido.setEstado(cs.getString(15).trim());
				obtenido.setDescripcion(cs.getString(16).trim());
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
