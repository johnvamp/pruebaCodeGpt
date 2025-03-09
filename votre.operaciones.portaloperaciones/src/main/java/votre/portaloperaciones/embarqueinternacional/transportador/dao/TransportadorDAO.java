package votre.portaloperaciones.embarqueinternacional.transportador.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import votre.portaloperaciones.embarqueinternacional.transportador.beans.TransportadorDTO;
import votre.portaloperaciones.embarqueinternacional.transportador.beans.TransportadorDetalleDTO;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.util.CargadorMsj;

public class TransportadorDAO implements ITransportadorDAO {

	private BDHelper bdHelper;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public TransportadorDAO(BDHelper bdhelper){
		super();		
		this.bdHelper = bdhelper;
	}
	
	public TransportadorDTO consultarTransportadores(TransportadorDTO transportador) throws PersonalsoftException{
		TransportadorDTO obtenido = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<TransportadorDetalleDTO> arrTransportadores = null;
		TransportadorDetalleDTO[] transportadores;
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, transportador.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, transportador.getAccion(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, transportador.getTransportista(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_TRANSPORTADORES");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				obtenido = new TransportadorDTO();
				
				obtenido.setTitEmbarque(cs.getString(4).trim());
				obtenido.setTitTransportador(cs.getString(5).trim());
				obtenido.setTitCamion(cs.getString(6).trim());
				obtenido.setTitAceptar(cs.getString(7).trim());
				obtenido.setTitRegresar(cs.getString(8).trim());
				obtenido.setTitOrden(cs.getString(9).trim());
				obtenido.setTitGuia(cs.getString(10).trim());
				obtenido.setEstado(cs.getString(11).trim());
				obtenido.setDescripcion(cs.getString(12).trim());
				
				rs = cs.getResultSet();
				if(rs != null){
					arrTransportadores = new ArrayList<TransportadorDetalleDTO>();
					while(rs.next()){
						TransportadorDetalleDTO detalle = new TransportadorDetalleDTO();
						if(transportador.getAccion().equals(Constantes.ACCION_CAMION)){
							detalle.setCamion(rs.getString(1).trim());
							detalle.setNombre(rs.getString(2).trim());
							detalle.setCodigo(rs.getString(3).trim());
							detalle.setPlaca(rs.getString(4).trim());
							detalle.setFecha(rs.getString(5).trim());
							detalle.setValorConcatenado(detalle.getCodigo()+"|"+detalle.getNombre()+"|"+detalle.getPlaca()+"|"+detalle.getFecha());
						}
						else if(transportador.getAccion().equals(Constantes.ACCION_TRANSPORTADOR)){
							detalle.setCodigo(rs.getString(1).trim());
							detalle.setNombre(rs.getString(2).trim());
						}
						arrTransportadores.add(detalle);
					}

				transportadores = arrTransportadores.toArray(new TransportadorDetalleDTO[arrTransportadores.size()]);
				obtenido.setDetalles(transportadores);
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
