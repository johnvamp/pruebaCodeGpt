package votre.portaloperaciones.solicitudcatalogo.catalogo.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import votre.portaloperaciones.solicitudcatalogo.catalogo.beans.CatalogoDTO;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.util.CargadorMsj;

public class CatalogoDAO implements ICatalogoDAO{

	private BDHelper bdHelper;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public CatalogoDAO(BDHelper bdHelper) {
		super();
		this.bdHelper = bdHelper;
	}

	public CatalogoDTO verCatalogo(CatalogoDTO catalogo) throws PersonalsoftException {
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, catalogo.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.DECIMAL, new Integer( catalogo.getCedula() ),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, catalogo.getAccion(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		try {
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_INICIO");
			if (Constantes.isLeocomus(catalogo.getCodCia())) {
				rutaSQL = Configuracion.getInstance().getParametroApp("SP_INICIO_USA");
			}
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if (cs != null) {
				cs.execute();
				
				catalogo.setTituloPais(cs.getString(3).trim());
				catalogo.setOpcionCedula(cs.getString(4).trim());
				catalogo.setTituloBoton(cs.getString(5).trim());
				catalogo.setOpcionNombre(cs.getString(6).trim());
				
				catalogo.setEstado(cs.getString(8).trim());
				catalogo.setDescripcion(cs.getString(9).trim());
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
		return catalogo;
	}

}
