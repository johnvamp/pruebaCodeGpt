package votre.portaloperaciones.pedidos.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.apache.log4j.Logger;

import votre.portaloperaciones.pedidos.beans.PedidosDTO;
import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.util.CargadorMsj;

public class PedidosDAO implements IPedidosDAO{

	private BDHelper bdHelper;
	Logger logger = Logger.getLogger(this.getClass());
	Logger info = Logger.getLogger("info");
	
	public PedidosDAO(){}
	public PedidosDAO(BDHelper bdHelper){
		this.bdHelper = bdHelper;
	}

	public PedidosDTO tipoPedidosCombo(PedidosDTO pedidosDTO)throws PersonalsoftException {
		CallableStatement cs = null;
		ResultSet rs = null;
		
		PedidosDTO pedidoTipo = new PedidosDTO();
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		LinkedHashMap<String, String> desCombo = new LinkedHashMap<String, String>();
		
		parametros.add(new Parametro(Types.VARCHAR, pedidosDTO.getDsCia(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, pedidosDTO.getDsUsu(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_PEDLISTARTIPO");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			if(cs != null){
				cs.execute();
				rs= cs.getResultSet();
				if(rs != null){	
					while (rs.next()){
						String cadena1=  rs.getString(1).trim() + " "+ rs.getString(3).trim();					
						desCombo.put(cadena1,rs.getString(2).trim());
					}
					pedidoTipo.setDsTipoPedidoCombo(desCombo);
				}				
			}			
		}
		catch (SQLException e) {
			logger.error(e);
			throw new PersonalsoftException(e);
		}finally{
			try{
				BDHelper.close(cs);
				BDHelper.close(rs);
				
			}catch (SQLException e) {
				throw new PersonalsoftException(e);
			}
		}		
		return pedidoTipo;		
	}

	
	
	public PedidosDTO guardarCompradora(PedidosDTO pedidosDTO)throws PersonalsoftException {
		CallableStatement cs = null;
		PedidosDTO guardarCompradora = new PedidosDTO();
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		
		parametros.add(new Parametro(Types.VARCHAR, pedidosDTO.getDsCia(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, pedidosDTO.getDsTipop(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, pedidosDTO.getDsSku(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, pedidosDTO.getDsUnip(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, pedidosDTO.getDsCedd(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, pedidosDTO.getDsDest(), Parametro.IN_OUT));		
		parametros.add(new Parametro(Types.VARCHAR, pedidosDTO.getDsUsu(), Parametro.IN_OUT));		
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
				
		try{			
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_PEDGRABAITEM");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			if(cs != null){
				cs.execute();
				guardarCompradora.setDsStatus(cs.getString(8).trim());	
				guardarCompradora.setDsPdestatus(cs.getString(9).trim());						
			}			
		}
		catch (SQLException e) {
			logger.error(e);
			throw new PersonalsoftException(e);
		}finally{
			try{
				BDHelper.close(cs);				
			}catch (SQLException e) {
				throw new PersonalsoftException(e);
			}
		}		
		return guardarCompradora;	
	}
	
	
	public ArrayList<PedidosDTO> listarDetalle(PedidosDTO pedidosDTO)throws PersonalsoftException {
		CallableStatement cs = null;
		ResultSet rs = null;
		
		PedidosDTO obtenido = null;
		
		ArrayList<PedidosDTO> listarDetalle = new ArrayList<PedidosDTO>();
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, pedidosDTO.getDsCia(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, pedidosDTO.getDsTipoPed(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, pedidosDTO.getDsNumPed(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_PEDLISTA");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			if(cs != null){
				cs.execute();
				rs= cs.getResultSet();
				pedidosDTO.setDsStatus(cs.getString(4).trim());	
				pedidosDTO.setDsPdestatus(cs.getString(5).trim());	
				if(rs != null){
					while (rs.next()){
						obtenido = new PedidosDTO();
						obtenido.setDsNumPed(rs.getString(1).trim());
						obtenido.setDsOrdPed(rs.getString(2).trim());
						obtenido.setDsCedd(rs.getString(3).trim());
						obtenido.setDsSku(rs.getString(4).trim());
						obtenido.setDsUnip(rs.getString(5).trim());
						obtenido.setDsEstado(rs.getString(6).trim());
						obtenido.setDsUsu(rs.getString(7).trim());
						obtenido.setDsFecha(rs.getString(8).trim());
						obtenido.setDsHora(rs.getString(9).trim());
						obtenido.setDsSec(rs.getString(10).trim());
						obtenido.setDsZona(rs.getString(11).trim());					
						listarDetalle.add(obtenido);
					}
				}
			}
		}
		catch (SQLException e) {
			ps = new PersonalsoftException(e);
			ps.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("errorGeneral"));
			logger.error(ps.getTraza());
			throw ps;
		}finally{
			try{
				BDHelper.close(cs);
				BDHelper.close(rs);
				
			}catch (SQLException e) {
				ps = new PersonalsoftException(e);
				ps.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("errorGeneral"));
				logger.error(ps.getTraza());
				throw ps;
			}
		}			
		return listarDetalle;
	}
	
	
public PedidosDTO tipoCombo (PedidosDTO pedidosDTO)throws PersonalsoftException {
		CallableStatement cs = null;
		ResultSet rs = null;
		
		PedidosDTO pedidoTipoRC = new PedidosDTO();
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		LinkedHashMap<String, String> desCombo = new LinkedHashMap<String, String>();
		
		parametros.add(new Parametro(Types.VARCHAR, pedidosDTO.getDsCia(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, pedidosDTO.getDsTipoLis(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_PEDLISTARTIPORC");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			if(cs != null){
				cs.execute();
				rs= cs.getResultSet();
				if(rs != null){	
					while (rs.next()){					
						desCombo.put(rs.getString(1).trim(),rs.getString(1).trim());
					}
					pedidoTipoRC.setDsTipoCombo(desCombo);
				}				
			}			
		}
		catch (SQLException e) {
			logger.error(e);
			throw new PersonalsoftException(e);
		}finally{
			try{
				BDHelper.close(cs);
				BDHelper.close(rs);
				
			}catch (SQLException e) {
				throw new PersonalsoftException(e);
			}
		}		
		return pedidoTipoRC;		
	}	
	
}
