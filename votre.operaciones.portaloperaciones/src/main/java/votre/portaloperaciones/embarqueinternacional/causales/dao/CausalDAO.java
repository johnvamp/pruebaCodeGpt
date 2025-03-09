package votre.portaloperaciones.embarqueinternacional.causales.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import votre.portaloperaciones.embarqueinternacional.causales.beans.CausalDTO;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class CausalDAO implements ICausalDAO {

	private BDHelper bdHelper;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public CausalDAO(BDHelper bdHelper) {
		super();
		this.bdHelper = bdHelper;
	}

	public ArrayList<CausalDTO> cargarCausales(String codCia) throws PersonalsoftException {
		ArrayList<CausalDTO> retorno = new ArrayList<CausalDTO>();
		CausalDTO causalDTO = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, codCia,Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_CAUSALES");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				rs = cs.getResultSet();
				if(rs != null){
					while(rs.next()){
						causalDTO = new CausalDTO();
						causalDTO.setCodigo(rs.getString(1).trim());
						causalDTO.setCausal(rs.getString(2).trim());
		
						retorno.add(causalDTO);
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
