package votre.portaloperaciones.consultasku.bodegas.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import votre.portaloperaciones.consultasku.bodegas.beans.BodegaDTO;
import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class BodegaDAO implements IBodegaDAO {
	
	private BDHelper bdHelper;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public BodegaDAO(BDHelper bdHelper) {
		super();
		this.bdHelper = bdHelper;
	}

	public ArrayList<BodegaDTO> consultarBodegas(String codCia) throws PersonalsoftException {
		ArrayList<BodegaDTO> retorno = new ArrayList<BodegaDTO>();
		BodegaDTO bodegaDTO = null;
		
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, codCia,Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_BODEGAS");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				rs = cs.getResultSet();
				if(rs != null){
					while(rs.next()){
						bodegaDTO = new BodegaDTO();
						bodegaDTO.setCodigo(rs.getString(1).trim());
						bodegaDTO.setNombre(rs.getString(2).trim());
						bodegaDTO.setEstado(cs.getString(2).trim());
						bodegaDTO.setDescripcion(cs.getString(3).trim());
						
						retorno.add(bodegaDTO);
					}
				}
			}
		}
		catch (SQLException e) {
			ps = new PersonalsoftException(e);
			logger.error(ps.getTraza());
			throw ps;
		} finally {
			try {
				BDHelper.close(cs);
				BDHelper.close(rs);
			} catch (SQLException e) {
				throw new PersonalsoftException(e);
			}
		}
		
		return retorno;
	}

}
