package votre.portaloperaciones.flujowms.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;


import votre.portaloperaciones.flujowms.beans.AlertasDTO;
import votre.portaloperaciones.flujowms.beans.FlujoWmsDTO;
import votre.portaloperaciones.flujowms.beans.TituloFlujoWmsDTO;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.util.Fecha;

public class FlujoWmsDAO implements IFlujoWmsDAO{
	
	private static final int HALF_HOUR = 0;
	private static final int ONE_HOUR = HALF_HOUR * 2;
	
	
	private BDHelper bdHelper;
	Logger logger = Logger.getLogger(this.getClass());
	public FlujoWmsDAO(){}
	public FlujoWmsDAO(BDHelper bdHelper){
		this.bdHelper = bdHelper;
	}
	
	
	public ArrayList<FlujoWmsDTO> consultaFlujoWms(FlujoWmsDTO flujoWmsDTO) throws PersonalsoftException {
		
		String cont;
		String contM = "";
		ArrayList<String> horas = new ArrayList<String>();
		ArrayList<String> minutos = new ArrayList<String>();
		String fechaActual = Fecha.getFechaServidor(Fecha.EXPR_YYYYMMDD_LINEA);
		String horaActual = Fecha.getFechaServidor(Fecha.EXPR_HHMMSS_DOS_PUNTOS);		
		
		CallableStatement cs = null;
		ResultSet rs = null;
		FlujoWmsDTO obtenido = null;
		
		ArrayList<FlujoWmsDTO> flujoWms = new ArrayList<FlujoWmsDTO>();
		ArrayList<Parametro> parametros =  new ArrayList<Parametro>();
		
		parametros.add(new Parametro(Types.VARCHAR,flujoWmsDTO.getIdCompania(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR,flujoWmsDTO.getIdTipoFlujoWms(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_FLUJOWMS");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			if(cs != null){
				cs.execute();
				rs= cs.getResultSet();
				if(rs != null){
					while (rs.next()){
						
					   	for (int h=1; h<=23; h++) {
					   		cont = ""+h;
					   		if(h <=9){
					   			cont = "0"+h;
					   		}
					   		horas.add(cont);
					   	}

					   	for (int m=1; m<=59; m++) {
							contM = ""+m;
							if(m <=9){
								contM = "0"+m;
							}
							minutos.add(contM);
					   	}				
						
						obtenido = new FlujoWmsDTO();
						obtenido.setOrden(rs.getString(1).trim());
						obtenido.setEstado(rs.getString(2).trim());
						obtenido.setPickt(rs.getString(3).trim());
						obtenido.setFecha(rs.getString(4).trim());
						obtenido.setHora(rs.getString(5).trim());
						obtenido.setCedula(rs.getString(6).trim());
						obtenido.setNombre(rs.getString(7).trim());
						obtenido.setProceso(rs.getString(8).trim());
											
						
						
						obtenido.setTipoCom(rs.getString(9).trim());
						obtenido.setTranspo(rs.getString(10).trim());
						obtenido.setZona(rs.getString(11).trim());
						obtenido.setMp(rs.getString(12).trim());
						obtenido.setMailPlan(rs.getString(13).trim());
						obtenido.setRegion(rs.getString(14).trim());
						
						
						String fechaTabla = rs.getString(4).trim();
						String horaTabla = rs.getString(5).trim();
						String fechahoraTabla = fechaTabla+" "+horaTabla;
						String fechaHoraActual =  fechaActual+" "+horaActual;
												
						java.util.Date hora1;
						java.util.Date hora2;
						try {							
							hora1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fechahoraTabla);
							hora2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fechaHoraActual); 
							
							long lantes = hora1.getTime(); 
							long lahora = hora2.getTime(); 
							long diferencia = (lahora - lantes); 
							long totalHoras = (diferencia/3600000);
							obtenido.setFechahoraDT(totalHoras);
						
						} catch (ParseException e) {
							e.printStackTrace();
						} 						
						
						flujoWms.add(obtenido);						
					}
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
		
		return flujoWms;		
	}
	
	
	
	
	public ArrayList<TituloFlujoWmsDTO> consultaTituloFlujoWms(TituloFlujoWmsDTO tituloFlujoWmsDTO) throws PersonalsoftException {
		
		CallableStatement cs = null;
		ResultSet rs = null;
		TituloFlujoWmsDTO obtenido = null;
		
		ArrayList<TituloFlujoWmsDTO> tituloFlujoWms = new ArrayList<TituloFlujoWmsDTO>();
		ArrayList<Parametro> parametros =  new ArrayList<Parametro>();

		parametros.add(new Parametro(Types.VARCHAR,tituloFlujoWmsDTO.getIdCompania(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR,tituloFlujoWmsDTO.getIdTituloFlujoWms(),Parametro.IN));
		
		try {
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_TITULOFLUJOWMS");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			if(cs != null){
				cs.execute();
				rs= cs.getResultSet();
				if(rs != null){
					while (rs.next()){
						obtenido = new TituloFlujoWmsDTO();	
						obtenido.setTitulo(rs.getString(2).trim());
						tituloFlujoWms.add(obtenido);
					}
				}
			}
			
		} catch (Exception e) {
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
		
		return tituloFlujoWms;
		
		
	}
	
	public AlertasDTO consultarEstados(String codCia) throws PersonalsoftException {
		AlertasDTO retorno = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Parametro> parametros =  new ArrayList<Parametro>();
		ArrayList<AlertasDTO> arrDetalle = new ArrayList<AlertasDTO>();
		
		parametros.add(new Parametro(Types.VARCHAR, codCia,Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		
		try {
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_FLUJOESTADOS");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			if(cs != null){
				cs.execute();
				
				retorno = new AlertasDTO();
				retorno.setEstado(cs.getString(2) != null ? cs.getString(2).trim() : "");
				retorno.setDescripcion(cs.getString(3) != null ? cs.getString(3).trim() : "");
				
				rs = cs.getResultSet();
				if(rs != null){
					while(rs.next()){
						AlertasDTO detalle = new AlertasDTO();
						detalle.setTitEstado(rs.getString(1) != null ? rs.getString(1).trim() : "");
						detalle.setPedidos(rs.getString(2) != null ? rs.getString(2).trim() : "");
						detalle.setDesEstado(rs.getString(3) != null ? rs.getString(3).trim() : "");
						detalle.setNroEstado(rs.getString(4) != null ? rs.getString(4).trim() : "");
						
						arrDetalle.add(detalle);
					}
					retorno.setDetalle(arrDetalle);
				}
			}
		} catch (Exception e) {
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
		
		return retorno;
	}
	
	public AlertasDTO consultarDetalleEstado(AlertasDTO alertasDTO) throws PersonalsoftException {
		AlertasDTO retorno = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Parametro> parametros =  new ArrayList<Parametro>();
		ArrayList<AlertasDTO> arrDetalle = new ArrayList<AlertasDTO>();
		
		parametros.add(new Parametro(Types.VARCHAR, alertasDTO.getCodCia(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, alertasDTO.getNroEstado(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		
		try {
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_FDETALLEESTADOS");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				
				retorno = new AlertasDTO();
				retorno.setEstado(cs.getString(3) != null ? cs.getString(3).trim() : "");
				retorno.setDescripcion(cs.getString(4) != null ? cs.getString(4).trim() : "");
				
				rs = cs.getResultSet();
				if(rs != null){
					while(rs.next()){
						AlertasDTO detalle = new AlertasDTO();
						detalle.setRegion(rs.getString(1) != null ? rs.getString(1).trim() : "");
						detalle.setMainPlan(rs.getString(2) != null ? rs.getString(2).trim() : "");
						detalle.setZona(rs.getString(3) != null ? rs.getString(3).trim() : "");
						detalle.setCedula(rs.getString(4) != null ? rs.getString(4).trim() : "");
						detalle.setNombre(rs.getString(5) != null ? rs.getString(5).trim() : "");
						detalle.setTransmision(rs.getString(6) != null ? rs.getString(6).trim() : "");
						detalle.setFechaTrans(rs.getString(7) != null ? rs.getString(7).trim() : "");
						detalle.setHoraTrans(rs.getString(8) != null ? rs.getString(8).trim() : "");
						detalle.setNroOrden(rs.getString(9) != null ? rs.getString(9).trim() : "");
						detalle.setConceptoRetencion(rs.getString(10) != null ? rs.getString(10).trim() : "");
						detalle.setFechaConferencia(rs.getString(11) != null ? rs.getString(11).trim() : "");
						detalle.setFechaLiberacion(rs.getString(12) != null ? rs.getString(12).trim() : "");
						detalle.setHoraLiberacion(rs.getString(13) != null ? rs.getString(13).trim() : "");
						detalle.setFechaAsignacion(rs.getString(14) != null ? rs.getString(14).trim() : "");
						detalle.setHoraAsignacion(rs.getString(15) != null ? rs.getString(15).trim() : "");
						detalle.setDepartamento(rs.getString(16) != null ? rs.getString(16).trim() : "");
						detalle.setCiudad(rs.getString(17) != null ? rs.getString(17).trim() : "");
						detalle.setPickTicket(rs.getString(18) != null ? rs.getString(18).trim() : "");
						detalle.setEstadoPickTicket(rs.getString(19) != null ? rs.getString(19).trim() : "");
						detalle.setNroCajas(rs.getString(20) != null ? rs.getString(20).trim() : "");
						detalle.setTransportista(rs.getString(21) != null ? rs.getString(21).trim() : "");
						detalle.setFechaConsolidacion(rs.getString(22) != null ? rs.getString(22).trim() : "");
						detalle.setHoraConsolidacion(rs.getString(23) != null ? rs.getString(23).trim() : "");
						detalle.setCampana(rs.getString(24) != null ? rs.getString(24).trim() : "");
						detalle.setHoraRetrasoWms(rs.getString(25) != null ? rs.getString(25).trim() : "");
						detalle.setHoraRetrasoConsolidado(rs.getString(26) != null ? rs.getString(26).trim() : "");
						
						arrDetalle.add(detalle);
					}
					retorno.setDetalle(arrDetalle);
				}
			}
			
		} catch (Exception e) {
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
		return retorno;
	}
}
