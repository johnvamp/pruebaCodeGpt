package votre.portaloperaciones.despachocatalogo.embarquecatalogo.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import votre.portaloperaciones.despachocatalogo.embarquecatalogo.beans.GuiasEmbarqueDTO;
import votre.portaloperaciones.despachocatalogo.embarquecatalogo.beans.TransportadorasDTO;
import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.util.CargadorMsj;

public class EmbarqueCatalogoDAO implements IEmbarqueCatalogoDAO {
	private BDHelper bdHelper;
	private Logger logger = Logger.getLogger(this.getClass());

	public EmbarqueCatalogoDAO(BDHelper bdhelper) {
		super();		
		this.bdHelper = bdhelper;
	}

	public TransportadorasDTO consultarTransportadoras(TransportadorasDTO dto) throws PersonalsoftException {
		TransportadorasDTO obtenido = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<TransportadorasDTO> arrTrans = new ArrayList<TransportadorasDTO>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, dto.getCodCia(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getAccion(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_LISTAR_VARIOS");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = new TransportadorasDTO();
				obtenido.setStatus(cs.getString(3) != null ? cs.getString(3).trim() : "");
				obtenido.setDsstatus(cs.getString(4) != null ? cs.getString(4).trim() : "");
				
				rs = cs.getResultSet();
				if(rs != null){
					while (rs.next()) {
						TransportadorasDTO transportadorasDTO = new TransportadorasDTO();
						transportadorasDTO.setCodigoTrans(rs.getString(1) != null ? rs.getString(1).trim() : "");
						transportadorasDTO.setTransportadora(rs.getString(2) != null ? rs.getString(2).trim() : "");
						
						arrTrans.add(transportadorasDTO);
					}
					obtenido.setArrTrans(arrTrans);
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

	public GuiasEmbarqueDTO enviarGuiasEmbarque(GuiasEmbarqueDTO dto) throws PersonalsoftException {
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, dto.getCodCia(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getCodTrans(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, dto.getNroguia(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_GUIAS_EMBARQUE");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				dto.setStatus(cs.getString(4) != null ? cs.getString(4).trim() : "");
				dto.setDsStatus(cs.getString(5) != null ? cs.getString(5).trim() : "");
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
		return dto;
	}

	public GuiasEmbarqueDTO finalizarEmbarqueGuias(String codCia) throws PersonalsoftException {
		GuiasEmbarqueDTO obtenido = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, codCia,Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_FIN_EMBARQUE");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				obtenido = new GuiasEmbarqueDTO();
				obtenido.setStatus(cs.getString(2) != null ? cs.getString(2).trim() : "");
				obtenido.setDsStatus(cs.getString(3) != null ? cs.getString(3).trim() : "");
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
