package votre.portaloperaciones.solicitudcatalogo.referencia.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import votre.portaloperaciones.solicitudcatalogo.referencia.beans.AuditoriaDTO;
import votre.portaloperaciones.solicitudcatalogo.referencia.beans.ReferenciaDTO;
import votre.portaloperaciones.solicitudcatalogo.referencia.beans.RegistrosReferenciaDTO;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.util.CargadorMsj;

public class ReferenciaDAO implements IReferenciaDAO{
	
	private BDHelper bdHelper;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public ReferenciaDAO(BDHelper bdHelper) {
		super();
		this.bdHelper = bdHelper;
	}

	public ReferenciaDTO verInformacionReferencia(ReferenciaDTO referencia) throws PersonalsoftException {
		ReferenciaDTO obtenido = null;
		RegistrosReferenciaDTO[] registros = null;		
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<RegistrosReferenciaDTO> arrRegistros = new ArrayList<RegistrosReferenciaDTO>();
		int pos=0;
		
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, referencia.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.DECIMAL, new Integer( referencia.getCedula() ),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, referencia.getCampana(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.DECIMAL, new Integer( referencia.getNroLinea() ),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_INFO_REFERENCIA");
			if(Constantes.CODCIA_USA.equals(referencia.getCodCia())){
				rutaSQL = Configuracion.getInstance().getParametroApp("SP_INFO_REFEREN_USA");
			}
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if (cs != null) {
				cs.execute();
				
				obtenido = new ReferenciaDTO();
				obtenido.setTitulo1(cs.getString(5).trim());
				obtenido.setTitulo2(cs.getString(6).trim());
				obtenido.setTitulo3(cs.getString(7).trim());
				obtenido.setTitulo4(cs.getString(8).trim());
				obtenido.setTitulo5(cs.getString(9).trim());
				obtenido.setTitulo6(cs.getString(10).trim());
				obtenido.setEstado(cs.getString(11).trim());
				obtenido.setDescripcion(cs.getString(12).trim());
				
				ResultSet rs = cs.getResultSet();	
				if (rs != null) {
					while (rs.next()) {
						RegistrosReferenciaDTO registrosReferenciaDTO = new RegistrosReferenciaDTO();
						registrosReferenciaDTO.setCampana(rs.getString(1).trim());
						registrosReferenciaDTO.setLineaGrabacion(rs.getString(2).trim());
						registrosReferenciaDTO.setSku(rs.getString(3).trim());
						registrosReferenciaDTO.setDescripcion(rs.getString(4).trim());
						registrosReferenciaDTO.setCantidad(rs.getString(5).trim());
						arrRegistros.add(registrosReferenciaDTO);
					}
				}
				pos = 0;
				registros = new RegistrosReferenciaDTO[arrRegistros.size()];
				for (Iterator<RegistrosReferenciaDTO> iter = arrRegistros.iterator(); iter.hasNext();){
					RegistrosReferenciaDTO registrosReferenciaDTO = (RegistrosReferenciaDTO) iter.next();
					registros[pos] = registrosReferenciaDTO;
					++pos;
				}
				obtenido.setRegistros(registros);				
			}
			
			
		}catch (SQLException e) {
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

	public AuditoriaDTO verAuditoriaCatalogo(ReferenciaDTO referencia) throws PersonalsoftException {
		AuditoriaDTO obtenido = null;
		RegistrosReferenciaDTO[] registros = null;		
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<RegistrosReferenciaDTO> arrRegistros = new ArrayList<RegistrosReferenciaDTO>();
		int pos=0;
		
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, referencia.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.DECIMAL, new Integer( referencia.getCedula() ),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, referencia.getCampana(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, referencia.getSku(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.DECIMAL, new Integer( referencia.getNroLinea() ),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_INFO_DESPACHO");
			if(Constantes.CODCIA_USA.equals(referencia.getCodCia())){
				rutaSQL = Configuracion.getInstance().getParametroApp("SP_INFO_DESPACHO_USA");
			}
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if (cs != null) {
				cs.execute();
				
				obtenido = new AuditoriaDTO();
				obtenido.setTitulo1(cs.getString(6).trim());
				obtenido.setTitulo2(cs.getString(7).trim());
				obtenido.setTitulo3(cs.getString(8).trim());
				obtenido.setTitulo4(cs.getString(9).trim());
				obtenido.setTitulo5(cs.getString(10).trim());
				obtenido.setEstado(cs.getString(11).trim());
				obtenido.setDescripcion(cs.getString(12).trim());
				
				ResultSet rs = cs.getResultSet();	
				if (rs != null) {
					while (rs.next()) {
						RegistrosReferenciaDTO registrosReferenciaDTO = new RegistrosReferenciaDTO();
						registrosReferenciaDTO.setDescripAuditoria(rs.getString(1).trim());
						registrosReferenciaDTO.setFecha(rs.getString(2).trim());
						registrosReferenciaDTO.setHora(rs.getString(3).trim());
						registrosReferenciaDTO.setUsuario(rs.getString(4).trim());
						arrRegistros.add(registrosReferenciaDTO);
					}
				}
				pos = 0;
				registros = new RegistrosReferenciaDTO[arrRegistros.size()];
				for (Iterator<RegistrosReferenciaDTO> iter = arrRegistros.iterator(); iter.hasNext();){
					RegistrosReferenciaDTO registrosReferenciaDTO = (RegistrosReferenciaDTO) iter.next();
					registros[pos] = registrosReferenciaDTO;
					++pos;
				}
				obtenido.setRegistros(registros);
			}
			
		}catch (SQLException e) {
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
