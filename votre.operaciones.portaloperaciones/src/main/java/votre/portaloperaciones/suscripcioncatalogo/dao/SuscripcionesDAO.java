package votre.portaloperaciones.suscripcioncatalogo.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import votre.portaloperaciones.suscripcioncatalogo.beans.SuscripcionCatalogoDTO;
import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.util.CargadorMsj;

public class SuscripcionesDAO implements ISuscripcionesDAO{

	private BDHelper bdHelper;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public SuscripcionesDAO(BDHelper bdHelper) {
		super();
		this.bdHelper = bdHelper;
	}

	public SuscripcionCatalogoDTO suscribir(SuscripcionCatalogoDTO dto)	throws PersonalsoftException {
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, dto.getCodCia(),Parametro.IN));
		parametros.add(new Parametro(Types.DECIMAL, new Double( dto.getIdCompradora() ),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getAccion(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getUsuario(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getMedioIngreso(),Parametro.IN));
		parametros.add(new Parametro(Types.NUMERIC, dto.getNumCatalogos()!=null ? Integer.parseInt(dto.getNumCatalogos()) : 0, Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		try {
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_SUSCRIPCIONES");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if (cs != null) {
				cs.execute();
				
				dto.setNumCatalogos(cs.getString(6));
				dto.setEstado(cs.getString(7));
				dto.setCampana(cs.getString(11));
				dto.setDsStatus(cs.getString(12));
				dto.setNmStatus(cs.getString(13));
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
		return dto;
	}

}
