package votre.portaloperaciones.embarqueinternacional.reimprimir.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import votre.portaloperaciones.embarqueinternacional.reimprimir.beans.DetalleReimprimirDTO;
import votre.portaloperaciones.embarqueinternacional.reimprimir.beans.ReimprimirDTO;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.util.CargadorMsj;

public class ReimprimirDAO implements IReimprimirDAO {
	
	private BDHelper bdHelper;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public ReimprimirDAO(BDHelper bdhelper){
		super();		
		this.bdHelper = bdhelper;
	}

	public ReimprimirDTO reimprimirEmbarque(ReimprimirDTO reimprimirDTO) throws PersonalsoftException {
		ReimprimirDTO obtenido = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<DetalleReimprimirDTO> arrDetalle = new ArrayList<DetalleReimprimirDTO>();
		DetalleReimprimirDTO[] detalle;
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, reimprimirDTO.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, reimprimirDTO.getAccion(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, reimprimirDTO.getCodTransportador(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, reimprimirDTO.getCamion(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, reimprimirDTO.getFecha(),Parametro.IN_OUT));
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
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_REIMPRIMIR");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				obtenido = new ReimprimirDTO();
				
				obtenido.setCodTransportador(cs.getString(3).trim());
				obtenido.setCamion(cs.getString(4).trim());
				obtenido.setFecha(cs.getString(5).trim());
				obtenido.setTitReimpresion(cs.getString(7).trim());
				obtenido.setTitTransportador(cs.getString(8).trim());
				obtenido.setTitCamion(cs.getString(9).trim());
				obtenido.setTitFecha(cs.getString(10).trim());
				obtenido.setTitReimprimir(cs.getString(11).trim());
				obtenido.setTitRegresar(cs.getString(12).trim());
				obtenido.setTitCompraDirecta(cs.getString(13).trim());
				obtenido.setTitRelacionEmbarque(cs.getString(14).trim());
				obtenido.setTitCamionEnc(cs.getString(15).trim());
				obtenido.setTitFechaEnc(cs.getString(16).trim());
				obtenido.setTitNroOrden(cs.getString(17).trim());
				obtenido.setTitCedula(cs.getString(18).trim());
				obtenido.setTitNombre(cs.getString(19).trim());
				obtenido.setTitDireccion(cs.getString(20).trim());
				obtenido.setTitDestino(cs.getString(21).trim());
				obtenido.setTitTotal(cs.getString(22).trim());
				obtenido.setTitZona(cs.getString(23).trim());
				obtenido.setTitTelefono(cs.getString(24).trim());
				obtenido.setValorConcatenado(cs.getString(25).trim());
				obtenido.setTitCampana(cs.getString(26).trim());
				obtenido.setTitPorteria(cs.getString(27).trim());
				obtenido.setTitTelefono2(cs.getString(28).trim());
				obtenido.setTitCelular(cs.getString(29).trim());
				obtenido.setTitDistrito(cs.getString(30).trim());
				obtenido.setTitCanton(cs.getString(31).trim());
				obtenido.setTitProvincia(cs.getString(32).trim());
				obtenido.setTitValorOrden(cs.getString(33).trim());
				obtenido.setTransportador(cs.getString(34).trim());
				obtenido.setEstado(cs.getString(35).trim());
				obtenido.setDescripcion(cs.getString(36).trim());
				
				rs = cs.getResultSet();
				if(rs != null){
					arrDetalle = new ArrayList<DetalleReimprimirDTO>();
					while(rs.next()){
						DetalleReimprimirDTO detalleReimprimirDTO = new DetalleReimprimirDTO();
						if(reimprimirDTO.getAccion().equals(Constantes.ACCION_TRANSPORTADOR_REIMPRIMIR)){
							detalleReimprimirDTO.setCodTransportador(rs.getString(1).trim());
							detalleReimprimirDTO.setTransportador(rs.getString(2).trim());
						}
						if(reimprimirDTO.getAccion().equals(Constantes.ACCION_CAMION_REIMPRIMIR)){
							detalleReimprimirDTO.setCamion(rs.getString(1).trim());
							detalleReimprimirDTO.setCodTransportador(rs.getString(3).trim());
							detalleReimprimirDTO.setPlaca(rs.getString(4).trim());
							detalleReimprimirDTO.setFecha(rs.getString(5).trim());
							detalleReimprimirDTO.setValorConcatenado(detalleReimprimirDTO.getCodTransportador()+"|"+detalleReimprimirDTO.getPlaca()+"|"+detalleReimprimirDTO.getFecha());
						}
						if(reimprimirDTO.getAccion().equals(Constantes.ACCION_REIMPRIMIR)){
							detalleReimprimirDTO.setObservacion(rs.getString(1).trim());
							detalleReimprimirDTO.setNumOrden(rs.getString(2).trim());
							detalleReimprimirDTO.setZona(rs.getString(3).trim());
							detalleReimprimirDTO.setCedula(rs.getString(4).trim());
							detalleReimprimirDTO.setNombre(rs.getString(5).trim());
							detalleReimprimirDTO.setDireccion(rs.getString(6).trim());
							detalleReimprimirDTO.setTelefono(rs.getString(7).trim());
							detalleReimprimirDTO.setDestino(rs.getString(8).trim());
							detalleReimprimirDTO.setValorDeclarado(rs.getString(9).trim());
							detalleReimprimirDTO.setCampana(rs.getString(10).trim());
							detalleReimprimirDTO.setPorteria(rs.getString(11).trim());
							detalleReimprimirDTO.setTelefono2(rs.getString(12).trim());
							detalleReimprimirDTO.setCelular(rs.getString(13).trim());
							detalleReimprimirDTO.setDistrito(rs.getString(14).trim());
							detalleReimprimirDTO.setCanton(rs.getString(15).trim());
							detalleReimprimirDTO.setProvincia(rs.getString(16).trim());
							detalleReimprimirDTO.setValorOrden(rs.getString(17).trim());
						}
						arrDetalle.add(detalleReimprimirDTO);
					}
					detalle = arrDetalle.toArray(new DetalleReimprimirDTO[arrDetalle.size()]);
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

}
