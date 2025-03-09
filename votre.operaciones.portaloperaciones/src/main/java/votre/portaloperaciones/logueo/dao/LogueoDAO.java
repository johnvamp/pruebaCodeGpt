package votre.portaloperaciones.logueo.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import java.sql.Types;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.util.CargadorMsj;

import votre.portaloperaciones.logueo.beans.LogueoDTO;
import votre.portaloperaciones.util.Constantes;

public class LogueoDAO implements ILogueoDAO {
	private BDHelper bdHelper;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public LogueoDAO(BDHelper bdHelper) {
		super();
		this.bdHelper = bdHelper;
	}
	
	public ArrayList<LogueoDTO> consultarPaises (LogueoDTO logueoDTO) throws PersonalsoftException{
		ArrayList<LogueoDTO> retorno = new ArrayList<LogueoDTO>();
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.IN_OUT));
		parametros.add(new Parametro(Types.DECIMAL, 0,Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		try {
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_INICIO");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if (cs != null) {
				cs.execute();
				
				ResultSet rs = cs.getResultSet();
				
				if (rs != null) {
					while (rs.next()) {
						logueoDTO = new LogueoDTO();
						logueoDTO.setCodCia(rs.getString(1).trim());
						logueoDTO.setNombrePais(rs.getString(2).trim());
						
						logueoDTO.setBoton(cs.getString(5).trim());
						logueoDTO.setEstado(cs.getString(8).trim());
						logueoDTO.setDescripcion(cs.getString(9).trim());
						
						retorno.add(logueoDTO);
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
			} catch (SQLException e) {
				ps = new PersonalsoftException(e);
				ps.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("errorGeneral"));
				logger.error(ps.getTraza());
				throw ps;
			}
		}
		
		if (retorno.isEmpty()) {
			logueoDTO = new LogueoDTO();
			logueoDTO.setEstado(Constantes.ERROR_SP);
			retorno.add(logueoDTO);
		}
		
		return retorno;
	}
}
