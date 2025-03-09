package votre.portaloperaciones.embarqueinternacional.camion.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import votre.portaloperaciones.embarqueinternacional.camion.beans.CamionDTO;
import votre.portaloperaciones.embarqueinternacional.camion.beans.DetalleCamionDTO;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.util.CargadorMsj;

public class CamionDAO implements ICamionDAO {

	private BDHelper bdHelper;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public CamionDAO(BDHelper bdhelper){
		super();		
		this.bdHelper = bdhelper;
	}
	
	public CamionDTO consultaCamionesAbiertos(CamionDTO camionDTO) throws PersonalsoftException {
		CamionDTO obtenido = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<DetalleCamionDTO> arrDetalle = new ArrayList<DetalleCamionDTO>();
		DetalleCamionDTO[] detalle;
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, camionDTO.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, camionDTO.getAccion(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.DECIMAL, "",Parametro.OUT));
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
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_CAMIONES_ABIERTOS");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				obtenido = new CamionDTO();
				
				obtenido.setTotCamionesAbiertos(cs.getString(3).trim());
				obtenido.setTotCamionesPendientes(cs.getString(4).trim());
				obtenido.setTitConsultas(cs.getString(5).trim());
				obtenido.setTitCamiones(cs.getString(6).trim());
				obtenido.setTitEstado(cs.getString(7).trim());
				obtenido.setTitTransportador(cs.getString(8).trim());
				obtenido.setTitFecha(cs.getString(9).trim());
				obtenido.setTitCamion(cs.getString(10).trim());
				obtenido.setTitNumCajas(cs.getString(11).trim());
				obtenido.setTitTotal(cs.getString(12).trim());
				obtenido.setTitCamionesPendientes(cs.getString(14).trim());
				obtenido.setTitCamionesAbiertos(cs.getString(15).trim());
				obtenido.setTitReimprimir(cs.getString(16).trim());
				obtenido.setTitConsultar(cs.getString(17).trim());
				obtenido.setTitRegresar(cs.getString(18).trim());
				
				obtenido.setEstado(cs.getString(19).trim());
				obtenido.setDescripcion(cs.getString(20).trim());
				
				rs = cs.getResultSet();
				if(rs != null){
					arrDetalle = new ArrayList<DetalleCamionDTO>();
					while(rs.next()){
						DetalleCamionDTO detalleCamionDTO = new DetalleCamionDTO();
						if(camionDTO.getAccion().equals(Constantes.ACCION_CONSULTA)){
							detalleCamionDTO.setEstado(rs.getString(1).trim());
							detalleCamionDTO.setTransportador(rs.getString(2).trim());
							detalleCamionDTO.setFecha(rs.getString(3).trim()+"/"+rs.getString(4).trim()+"/"+rs.getString(5).trim());
							detalleCamionDTO.setCamion(rs.getString(6).trim());
							detalleCamionDTO.setNumCajas(rs.getString(7).trim());
						}
						arrDetalle.add(detalleCamionDTO);
					}
					detalle = arrDetalle.toArray(new DetalleCamionDTO[arrDetalle.size()]);
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
