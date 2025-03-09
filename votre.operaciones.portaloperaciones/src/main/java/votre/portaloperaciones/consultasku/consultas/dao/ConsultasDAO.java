package votre.portaloperaciones.consultasku.consultas.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import votre.portaloperaciones.consultasku.consultas.beans.ConsultasDTO;
import votre.portaloperaciones.consultasku.consultas.beans.DetalleConsultasDTO;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.util.CargadorMsj;

public class ConsultasDAO implements IConsultasDAO {

	private BDHelper bdHelper;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public ConsultasDAO(BDHelper bdHelper) {
		super();
		this.bdHelper = bdHelper;
	}

	public ConsultasDTO consultarReferencias(ConsultasDTO consultasDTO) throws PersonalsoftException {
		ConsultasDTO obtenido = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<DetalleConsultasDTO> arrDetalle = null;
		DetalleConsultasDTO[] detalle;
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, consultasDTO.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, consultasDTO.getAccion(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, consultasDTO.getReferencia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, consultasDTO.getColor(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, consultasDTO.getTalla(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, consultasDTO.getCodigoBodega(),Parametro.IN_OUT));
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
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_REFERENCIAS");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = new ConsultasDTO();
				obtenido.setCodCia(cs.getString(1).trim());
				obtenido.setReferencia(cs.getString(3).trim());
				obtenido.setColor(cs.getString(4).trim());
				obtenido.setTalla(cs.getString(5).trim());
				obtenido.setCodigoBodega(cs.getString(6).trim());
				obtenido.setTituloPais(cs.getString(7).trim());
				obtenido.setTituloSaldos(cs.getString(8).trim());
				obtenido.setTituloReferencia(cs.getString(9).trim());
				obtenido.setTituloColor(cs.getString(10).trim());
				obtenido.setTituloTalla(cs.getString(11).trim());
				obtenido.setTituloBodega(cs.getString(12).trim());
				obtenido.setTituloDescripcion(cs.getString(14).trim());
				obtenido.setTituloLinea(cs.getString(15).trim());
				obtenido.setTituloUbicacion(cs.getString(16).trim());
				obtenido.setTituloCantidadD(cs.getString(17).trim());
				obtenido.setTituloCantidadP(cs.getString(18).trim());
				obtenido.setTituloCantidadA(cs.getString(19).trim());
				obtenido.setTituloDisponibleP(cs.getString(20).trim());
				obtenido.setTituloDisponibleaA(cs.getString(21).trim());
				obtenido.setTituloCanPicking(cs.getString(22).trim());
				obtenido.setTituloPrecio(cs.getString(23).trim());
				obtenido.setTituloConsultar(cs.getString(24).trim());
				obtenido.setTituloLimpiar(cs.getString(25).trim());
				obtenido.setTituloCodigoBarras(cs.getString(26).trim());
				obtenido.setEstado(cs.getString(27).trim());
				obtenido.setDescripcion(cs.getString(28).trim());
				
				rs = cs.getResultSet();
				if(rs != null){
					arrDetalle = new ArrayList<DetalleConsultasDTO>();
					while(rs.next()){
						DetalleConsultasDTO detalleConsultasDTO = new DetalleConsultasDTO();
						detalleConsultasDTO.setReferencia(rs.getString(1).trim());
						detalleConsultasDTO.setDescripcion(rs.getString(2).trim());
						detalleConsultasDTO.setLineaGrabacion(rs.getString(3).trim());
						detalleConsultasDTO.setUbicacion(rs.getString(4).trim());
						detalleConsultasDTO.setCantidadDispo(rs.getString(5).trim());
						detalleConsultasDTO.setCantidadPedi(rs.getString(6).trim());
						detalleConsultasDTO.setCantidadAsig(rs.getString(7).trim());
						detalleConsultasDTO.setDisponiblePedir(rs.getString(8).trim());
						detalleConsultasDTO.setDisponibleAsig(rs.getString(9).trim());
						detalleConsultasDTO.setCantidadPicking(rs.getString(10).trim());
						detalleConsultasDTO.setCodigoBarras(rs.getString(11).trim());
						
						arrDetalle.add(detalleConsultasDTO);
					}
					detalle = arrDetalle.toArray(new DetalleConsultasDTO[arrDetalle.size()]);
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

	public ConsultasDTO consultarPrecios(ConsultasDTO consultasDTO) throws PersonalsoftException {
		ConsultasDTO obtenido = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<DetalleConsultasDTO> arrDetalle = null;
		DetalleConsultasDTO[] detalle;
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, consultasDTO.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, consultasDTO.getSku(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_PRECIOS");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = new ConsultasDTO();
				obtenido.setTituloCampana(cs.getString(3).trim());
				obtenido.setTituloCustomer(cs.getString(4).trim());
				obtenido.setTituloCantidad(cs.getString(5).trim());
				obtenido.setTituloPrecio(cs.getString(6).trim());
				obtenido.setTituloRegresar(cs.getString(7).trim());
				obtenido.setEstado(cs.getString(8).trim());
				obtenido.setDescripcion(cs.getString(9).trim());
				
				rs = cs.getResultSet();
				if(rs != null){
					arrDetalle = new ArrayList<DetalleConsultasDTO>();
					while(rs.next()){
						DetalleConsultasDTO detalleConsultasDTO = new DetalleConsultasDTO();
						detalleConsultasDTO.setCampana(rs.getString(1).trim());
						detalleConsultasDTO.setCustomerClass(rs.getString(2).trim());
						detalleConsultasDTO.setCantidad(rs.getString(3).trim());
						detalleConsultasDTO.setPrecio(rs.getString(4).trim());
						
						arrDetalle.add(detalleConsultasDTO);
					}
					detalle = arrDetalle.toArray(new DetalleConsultasDTO[arrDetalle.size()]);
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

	public ConsultasDTO consultarAuditoria(ConsultasDTO consultasDTO) throws PersonalsoftException {
		ConsultasDTO obtenido = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<DetalleConsultasDTO> arrDetalle = null;
		DetalleConsultasDTO[] detalle;
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, consultasDTO.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, consultasDTO.getSku(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, consultasDTO.getCodigoBodega(),Parametro.IN_OUT));
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
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_AUDITORIA");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = new ConsultasDTO();
				obtenido.setTituloHistoria(cs.getString(4).trim());
				obtenido.setTituloBodega(cs.getString(5).trim());
				obtenido.setTitulocodigoTrans(cs.getString(6).trim());
				obtenido.setTituloUnidades(cs.getString(7).trim());
				obtenido.setTituloCantidadTrans(cs.getString(8).trim());
				obtenido.setTituloFechaTrans(cs.getString(9).trim());
				obtenido.setTituloOrden(cs.getString(10).trim());
				obtenido.setTituloCreado(cs.getString(11).trim());
				obtenido.setTituloFechaCreacion(cs.getString(12).trim());
				obtenido.setTituloCodigoAud(cs.getString(13).trim());
				obtenido.setTituloRegresar(cs.getString(14).trim());
				obtenido.setEstado(cs.getString(15).trim());
				obtenido.setDescripcion(cs.getString(16).trim());
				
				rs = cs.getResultSet();
				if(rs != null){
					arrDetalle = new ArrayList<DetalleConsultasDTO>();
					while(rs.next()){
						DetalleConsultasDTO detalleConsultasDTO = new DetalleConsultasDTO();
						detalleConsultasDTO.setBodega(rs.getString(1).trim());
						detalleConsultasDTO.setCodigoTrans(rs.getString(2).trim());
						detalleConsultasDTO.setUnidades(rs.getString(3).trim());
						detalleConsultasDTO.setCantidadTrans(rs.getString(4).trim());
						detalleConsultasDTO.setFechaTrans(rs.getString(5).trim());
						detalleConsultasDTO.setOrden(rs.getString(6).trim());
						detalleConsultasDTO.setCreado(rs.getString(7).trim());
						detalleConsultasDTO.setFechaCreacion(rs.getString(8).trim());
						detalleConsultasDTO.setCodigoAud(rs.getString(9).trim());
						
						arrDetalle.add(detalleConsultasDTO);
					}
					detalle = arrDetalle.toArray(new DetalleConsultasDTO[arrDetalle.size()]);
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

	public ConsultasDTO consultarDescripcionSKU(ConsultasDTO consultasDTO) throws PersonalsoftException {
		ConsultasDTO obtenido = null;
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, consultasDTO.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, consultasDTO.getSku(),Parametro.IN_OUT));
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
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_DESCRIPCIONSKU");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = new ConsultasDTO();
				obtenido.setReferencia(cs.getString(2).trim());
				obtenido.setDescripcionRef(cs.getString(3).trim());
				obtenido.setVendor(cs.getString(4).trim());
				obtenido.setMarca(cs.getString(5).trim());
				obtenido.setEstadoOP(cs.getString(6).trim());
				obtenido.setUen(cs.getString(7).trim());
				obtenido.setKit(cs.getString(8).trim());
				obtenido.setIndic(cs.getString(9).trim());
				obtenido.setTituloReferencia(cs.getString(10).trim());
				obtenido.setTituloDescRef(cs.getString(11).trim());
				obtenido.setTituloVendor(cs.getString(12).trim());
				obtenido.setTituloMarca(cs.getString(13).trim());
				obtenido.setTituloUen(cs.getString(14).trim());
				obtenido.setTituloKit(cs.getString(15).trim());
				obtenido.setTituloIndic(cs.getString(16).trim());
				obtenido.setTituloEstado(cs.getString(17).trim());
				obtenido.setEstado(cs.getString(18).trim());
				obtenido.setDescripcion(cs.getString(19).trim());
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
