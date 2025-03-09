package votre.portaloperaciones.indicadordepedidos.dao;



import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import votre.portaloperaciones.indicadordepedidos.beans.DatosComboDTO;
import votre.portaloperaciones.indicadordepedidos.beans.IndicadorPedidosDTO;
import votre.portaloperaciones.indicadordepedidos.beans.ParamDepartamentoDTO;
import votre.portaloperaciones.indicadordepedidos.beans.ParametrosCiudadDTO;
import votre.portaloperaciones.indicadordepedidos.beans.PedidosPorRangoDTO;
import votre.portaloperaciones.indicadordepedidos.beans.ResultadoComboDTO;
import votre.portaloperaciones.util.Util;
import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class IndicadorPedidosDAO implements IIndicadorPedidosDAO {

	private BDHelper bdHelper;
	private static final String VACIO = "";
	Logger logger = Logger.getLogger(this.getClass());
	public IndicadorPedidosDAO(){}
	public IndicadorPedidosDAO(BDHelper bdHelper){
		this.bdHelper = bdHelper;
	}
	
	public ArrayList<IndicadorPedidosDTO> indicadorPedidosGraf1(IndicadorPedidosDTO indicadorPedidosDTO) throws PersonalsoftException {	
		CallableStatement cs = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		IndicadorPedidosDTO obtenido = null;
		
		ArrayList<IndicadorPedidosDTO> IndicadorPedidosGraf1 = new ArrayList<IndicadorPedidosDTO>();
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		
		parametros.add(new Parametro(Types.VARCHAR,indicadorPedidosDTO.getCodCia(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR,indicadorPedidosDTO.getAccion(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR,indicadorPedidosDTO.getEstadoIni(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR,indicadorPedidosDTO.getEstadoFin(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR,indicadorPedidosDTO.getDatoConsulta1(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR,indicadorPedidosDTO.getDatoConsulta2(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR,indicadorPedidosDTO.getDato6(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR,indicadorPedidosDTO.getDato7(),Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR,indicadorPedidosDTO.getDato8(),Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR,indicadorPedidosDTO.getDato9(),Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR,indicadorPedidosDTO.getDato10(),Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR,indicadorPedidosDTO.getDato30(),Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR,indicadorPedidosDTO.getDato31(),Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR,indicadorPedidosDTO.getDato35(),Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR,indicadorPedidosDTO.getDato38(),Parametro.OUT));
		
		String archivoExcel = indicadorPedidosDTO.getDato6();
		if (archivoExcel == null){
			archivoExcel = "";
		}
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_INDICA_PEDIDOS"); 
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			Util.imprimirCall(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				rs= cs.getResultSet();
				
				indicadorPedidosDTO.setDato6(cs.getString(8).trim());
				indicadorPedidosDTO.setDato7(cs.getString(9).trim());
				indicadorPedidosDTO.setDato8(cs.getString(10).trim());				
				indicadorPedidosDTO.setDato30(cs.getString(11).trim());
				indicadorPedidosDTO.setDato33(cs.getString(12).trim());
				/*indicadorPedidosDTO.setDato9(cs.getString(13).trim());
				indicadorPedidosDTO.setDato35(cs.getString(14).trim());
				indicadorPedidosDTO.setDato38(cs.getString().trim());
				*/
				indicadorPedidosDTO.setDato9(cs.getString(14).trim());
				indicadorPedidosDTO.setDato35(cs.getString(15).trim());
				indicadorPedidosDTO.setDato38(cs.getString(13).trim());
				
				if(rs != null){					
					if(archivoExcel.equals("")){
						while (rs.next()){
							obtenido = new IndicadorPedidosDTO();	
							obtenido.setCol1Graf1(rs.getString(1).trim());
							obtenido.setCol2Graf1(rs.getInt(3));							
							obtenido.setDato36(rs.getString(2).trim());
							obtenido.setDato37(rs.getString(5).trim());
							
							IndicadorPedidosGraf1.add(obtenido);
						}
					}
					if(archivoExcel.equals("S")){
						while (rs.next()){
							obtenido = new IndicadorPedidosDTO();	
							obtenido.setDato15(rs.getString(1).trim());
							obtenido.setDato16(rs.getString(2).trim());
							obtenido.setDato17(rs.getString(3).trim());
							obtenido.setDato18(rs.getString(4).trim());
							obtenido.setDato19(rs.getString(5).trim());
							obtenido.setDato20(rs.getString(6).trim());
							obtenido.setDato21(rs.getString(7).trim());
							obtenido.setDato22(rs.getString(8).trim());
							obtenido.setDato23(rs.getString(9).trim());
							obtenido.setDato24(rs.getString(10).trim());
							obtenido.setDato25(rs.getString(11).trim());
							obtenido.setDato26(rs.getString(12).trim());
							obtenido.setDato27(rs.getString(13).trim());
							obtenido.setDato28(rs.getString(14).trim());	
							obtenido.setDato29(rs.getString(15).trim());
							
							IndicadorPedidosGraf1.add(obtenido);	
						}
					}
					if(cs.getMoreResults()){
						rs2 = cs.getResultSet();
						if(rs2 != null){
							while (rs2.next()){
								obtenido = new IndicadorPedidosDTO();	
								obtenido.setDato10(rs2.getString(1).trim());							
								obtenido.setDato11(Math.round(rs2.getInt(2)));
								obtenido.setDato12(Math.round(rs2.getInt(3)));
								int dato13 = Math.round(rs2.getInt(4));
								if(dato13 == 0){
									dato13 = 1;
								}
								obtenido.setDato13(dato13);
								//obtenido.setDato13(Math.round(rs2.getInt(4)));							
								obtenido.setDato14(rs2.getString(5).trim());
								obtenido.setDato31(rs2.getString(6).trim());
								obtenido.setDato32(rs2.getString(7).trim());
								IndicadorPedidosGraf1.add(obtenido);					
							}
							if(cs.getMoreResults()){
								rs3 = cs.getResultSet();
								if(rs3 != null){
									while (rs3.next()){
										obtenido = new IndicadorPedidosDTO();	
										obtenido.setDato15(rs3.getString(1).trim());
										obtenido.setDato16(rs3.getString(2).trim());
										obtenido.setDato17(rs3.getString(3).trim());
										obtenido.setDato18(rs3.getString(4).trim());
										obtenido.setDato19(rs3.getString(5).trim());
										obtenido.setDato20(rs3.getString(6).trim());
										obtenido.setDato21(rs3.getString(7).trim());
										obtenido.setDato22(rs3.getString(8).trim());
										obtenido.setDato23(rs3.getString(9).trim());
										obtenido.setDato24(rs3.getString(10).trim());
										obtenido.setDato25(rs3.getString(11).trim());
										obtenido.setDato26(rs3.getString(12).trim());
										obtenido.setDato27(rs3.getString(13).trim());
										obtenido.setDato28(rs3.getString(14).trim());
										obtenido.setDato29(rs3.getString(15).trim());	
										
										IndicadorPedidosGraf1.add(obtenido);	
									}
								}
							}
						}
					}					
				}
			}
		}catch (Exception e) {
			logger.error(e);
			throw new PersonalsoftException(e);
		}finally{
			try{
				BDHelper.close(cs);
				BDHelper.close(rs);
				BDHelper.close(rs2);
				BDHelper.close(rs3);		
			}catch (SQLException e) {
				throw new PersonalsoftException(e);
			}
		}
		return IndicadorPedidosGraf1;
	}
	
	public DatosComboDTO consultarDatosCombo(DatosComboDTO datosComboDTO) throws PersonalsoftException {
		CallableStatement cs = null;
		ResultSet rs = null;
		
		DatosComboDTO obtenido = new DatosComboDTO();
		ArrayList<String> listado = new ArrayList<String>();
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		
		parametros.add(new Parametro (Types.VARCHAR, datosComboDTO.getCodCia(),Parametro.IN));
		parametros.add(new Parametro (Types.VARCHAR, datosComboDTO.getAccion(),Parametro.IN));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_INDPED_LISGIOS"); 
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			Util.imprimirCall(rutaSQL, parametros);
			if(cs != null){
				cs.execute();
				rs = cs.getResultSet();
				if(rs != null){
					while (rs.next()){
						listado.add(rs.getString(1).trim());
					}
					obtenido.setListado(listado);
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
		return obtenido;
	}
	
	public ArrayList<PedidosPorRangoDTO> pedidosPorRango(PedidosPorRangoDTO pedidosPorRangoDTO) throws PersonalsoftException {
		
		CallableStatement cs = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		ResultSet rs5 = null;
		ResultSet rs6 = null;
		ResultSet rs7 = null;
		ResultSet rs8 = null;
		PedidosPorRangoDTO obtenido = null;
		
		ArrayList<PedidosPorRangoDTO> pedidosPorRango = new ArrayList<PedidosPorRangoDTO>();
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		
		parametros.add(new Parametro(Types.VARCHAR, pedidosPorRangoDTO.getCodCia(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, pedidosPorRangoDTO.getAccion(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, pedidosPorRangoDTO.getEstadoIni(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, pedidosPorRangoDTO.getEstadoFin(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, pedidosPorRangoDTO.getDatoConsulta1(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, pedidosPorRangoDTO.getDatoConsulta2(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, pedidosPorRangoDTO.getRango(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, pedidosPorRangoDTO.getDato8(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, pedidosPorRangoDTO.getDato9(), Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, pedidosPorRangoDTO.getDato10(), Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_PEDIDOS_POR_RANGO"); 
			
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			if(cs != null){
				cs.execute();
				rs= cs.getResultSet();			
				pedidosPorRangoDTO.setDato9(cs.getString(9).trim());
				pedidosPorRangoDTO.setDato10(cs.getString(10).trim());		
				if(rs != null){		
					while (rs.next()){
						obtenido = new PedidosPorRangoDTO();	
						obtenido.setDato11(rs.getInt(3));
						obtenido.setDato12(rs.getString(1).trim());
						obtenido.setDato13(rs.getString(2).trim());
						obtenido.setDato14(rs.getString(4).trim());
						obtenido.setDato15(rs.getString(5).trim());
						obtenido.setDato16(rs.getString(6).trim());
						obtenido.setDato17(rs.getString(7).trim());	
						obtenido.setDato18(rs.getString(8).trim());		
						obtenido.setDato19("1");	
						pedidosPorRango.add(obtenido);
					}				
					if(cs.getMoreResults()){
						rs2 = cs.getResultSet();
						if(rs2 != null){
							while (rs2.next()){
								obtenido = new PedidosPorRangoDTO();
								obtenido.setDato11(rs2.getInt(3));
								obtenido.setDato12(rs2.getString(1).trim());
								obtenido.setDato13(rs2.getString(2).trim());
								obtenido.setDato14(rs2.getString(4).trim());
								obtenido.setDato15(rs2.getString(5).trim());
								obtenido.setDato16(rs2.getString(6).trim());
								obtenido.setDato17(rs2.getString(7).trim());
								obtenido.setDato18(rs2.getString(8).trim());
								obtenido.setDato19("2");	
								pedidosPorRango.add(obtenido);
							}
						}					
					}
					if(cs.getMoreResults()){
						rs3 = cs.getResultSet();
						if(rs3 != null){
							while (rs3.next()){
								obtenido = new PedidosPorRangoDTO();
								obtenido.setDato11(rs3.getInt(3));
								obtenido.setDato12(rs3.getString(1).trim());
								obtenido.setDato13(rs3.getString(2).trim());
								obtenido.setDato14(rs3.getString(4).trim());
								obtenido.setDato15(rs3.getString(5).trim());
								obtenido.setDato16(rs3.getString(6).trim());
								obtenido.setDato17(rs3.getString(7).trim());
								obtenido.setDato18(rs3.getString(8).trim());
								obtenido.setDato19("3");	
								pedidosPorRango.add(obtenido);
							}
						}					
					}
					if(cs.getMoreResults()){
						rs4 = cs.getResultSet();
						if(rs4 != null){
							while (rs4.next()){
								obtenido = new PedidosPorRangoDTO();
								obtenido.setDato11(rs4.getInt(3));
								obtenido.setDato12(rs4.getString(1).trim());
								obtenido.setDato13(rs4.getString(2).trim());
								obtenido.setDato14(rs4.getString(4).trim());
								obtenido.setDato15(rs4.getString(5).trim());
								obtenido.setDato16(rs4.getString(6).trim());
								obtenido.setDato17(rs4.getString(7).trim());
								obtenido.setDato18(rs4.getString(8).trim());
								obtenido.setDato19("4");	
								pedidosPorRango.add(obtenido);
							}
						}					
					}
					if(cs.getMoreResults()){
						rs5 = cs.getResultSet();
						if(rs5 != null){
							while (rs5.next()){
								obtenido = new PedidosPorRangoDTO();
								obtenido.setDato11(rs5.getInt(3));
								obtenido.setDato12(rs5.getString(1).trim());
								obtenido.setDato13(rs5.getString(2).trim());
								obtenido.setDato14(rs5.getString(4).trim());
								obtenido.setDato15(rs5.getString(5).trim());
								obtenido.setDato16(rs5.getString(6).trim());
								obtenido.setDato17(rs5.getString(7).trim());
								obtenido.setDato18(rs5.getString(8).trim());
								obtenido.setDato19("5");	
								pedidosPorRango.add(obtenido);
							}
						}					
					}
					if(cs.getMoreResults()){
						rs6 = cs.getResultSet();
						if(rs6 != null){
							while (rs6.next()){
								obtenido = new PedidosPorRangoDTO();
								obtenido.setDato11(rs6.getInt(3));
								obtenido.setDato12(rs6.getString(1).trim());
								obtenido.setDato13(rs6.getString(2).trim());
								obtenido.setDato14(rs6.getString(4).trim());
								obtenido.setDato15(rs6.getString(5).trim());
								obtenido.setDato16(rs6.getString(6).trim());
								obtenido.setDato17(rs6.getString(7).trim());	
								obtenido.setDato18(rs6.getString(8).trim());
								obtenido.setDato19("6");	
								pedidosPorRango.add(obtenido);
							}
						}					
					}
					if(cs.getMoreResults()){
						rs7 = cs.getResultSet();
						if(rs7 != null){
							while (rs7.next()){
								obtenido = new PedidosPorRangoDTO();
								obtenido.setDato11(rs7.getInt(3));
								obtenido.setDato12(rs7.getString(1).trim());
								obtenido.setDato13(rs7.getString(2).trim());
								obtenido.setDato14(rs7.getString(4).trim());
								obtenido.setDato15(rs7.getString(5).trim());
								obtenido.setDato16(rs7.getString(6).trim());
								obtenido.setDato17(rs7.getString(7).trim());	
								obtenido.setDato18(rs7.getString(8).trim());
								obtenido.setDato19("7");	
								pedidosPorRango.add(obtenido);
							}
						}					
					}
					if(cs.getMoreResults()){
						rs8 = cs.getResultSet();
						if(rs8 != null){
							while (rs8.next()){
								obtenido = new PedidosPorRangoDTO();
								obtenido.setDato11(rs8.getInt(3));
								obtenido.setDato12(rs8.getString(1).trim());
								obtenido.setDato13(rs8.getString(2).trim());
								obtenido.setDato14(rs8.getString(4).trim());
								obtenido.setDato15(rs8.getString(5).trim());
								obtenido.setDato16(rs8.getString(6).trim());
								obtenido.setDato17(rs8.getString(7).trim());	
								obtenido.setDato18(rs8.getString(8).trim());
								obtenido.setDato19("8");	
								pedidosPorRango.add(obtenido);
							}
						}					
					}
				}
			}
		}catch (SQLException e) {
			logger.error(e);
			throw new PersonalsoftException(e);
		}finally{
			try{
				BDHelper.close(cs);
				BDHelper.close(rs);
				BDHelper.close(rs2);
				BDHelper.close(rs3);
				BDHelper.close(rs4);
				BDHelper.close(rs5);
				BDHelper.close(rs6);
				BDHelper.close(rs7);
				BDHelper.close(rs8);
			}catch (SQLException e) {
				throw new PersonalsoftException(e);
			}
		}
		return pedidosPorRango;
	}
	
	@Override
	public ParametrosCiudadDTO consultarCiudades(ParametrosCiudadDTO parametrosCiudad) throws PersonalsoftException {
		
		CallableStatement cs = null;
		ResultSet rs = null;
		ResultadoComboDTO ciudad = null;
		
		ParametrosCiudadDTO obtenido = new ParametrosCiudadDTO();
		List<ResultadoComboDTO> ciudades = new ArrayList<ResultadoComboDTO>();
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		
		parametros.add(new Parametro (Types.VARCHAR, parametrosCiudad.getCodCia(), Parametro.IN));
		parametros.add(new Parametro (Types.VARCHAR, VACIO, Parametro.IN));
		parametros.add(new Parametro (Types.VARCHAR, parametrosCiudad.getCodDpto(), Parametro.IN));
		parametros.add(new Parametro (Types.VARCHAR, VACIO, Parametro.IN));
		parametros.add(new Parametro (Types.VARCHAR, VACIO, Parametro.OUT));
		parametros.add(new Parametro (Types.VARCHAR, VACIO, Parametro.OUT));
		
		String codError = "";
		String msgError = "";
		
		try{
			
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_CM_LISTARBARRIOS"); 
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			Util.imprimirCall(rutaSQL, parametros);
			
			if(cs != null){
				
				cs.execute();
				
				codError = cs.getString(5);
				msgError = cs.getString(6);
				
				rs = cs.getResultSet();
				
				if(rs != null && codError.equals("0")){
					
					while (rs.next()){
						
						ciudad = new ResultadoComboDTO();
						
						String codigo = rs.getString(1).trim();
						
						ciudad.setCodigo(codigo);
						
						ciudades.add(ciudad);
					}
					
					obtenido.setCiudades(ciudades);
				} else if (!codError.equals("0")) {
					logger.error("Codigo: " + codError + " Mensaje: " + msgError);
					throw new PersonalsoftException(codError);
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
		return obtenido;
		
	}
	
	@Override
	public ParamDepartamentoDTO consultarDepartamentos (ParamDepartamentoDTO paramDepartamento) throws PersonalsoftException {
		
		CallableStatement cs = null;
		ResultSet rs = null;
		ResultadoComboDTO departamento = null;
		
		ParamDepartamentoDTO obtenido = new ParamDepartamentoDTO();
		List<ResultadoComboDTO> departamentos = new ArrayList<ResultadoComboDTO>();
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		
		parametros.add(new Parametro (Types.VARCHAR, paramDepartamento.getCodCia(), Parametro.IN));
		parametros.add(new Parametro (Types.VARCHAR, VACIO, Parametro.IN));
		parametros.add(new Parametro (Types.VARCHAR, VACIO, Parametro.IN));
		parametros.add(new Parametro (Types.VARCHAR, VACIO, Parametro.IN));
		parametros.add(new Parametro (Types.VARCHAR, VACIO, Parametro.OUT));
		parametros.add(new Parametro (Types.VARCHAR, VACIO, Parametro.OUT));
		
		String codError = "";
		String msgError = "";
		
		try{
			
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_CM_LISTARBARRIOS"); 
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			Util.imprimirCall(rutaSQL, parametros);
			
			if(cs != null){
				
				cs.execute();
				
				codError = cs.getString(5);
				msgError = cs.getString(6);
				
				rs = cs.getResultSet();
				
				if(rs != null && codError.equals("0")){
					
					while (rs.next()){
						
						departamento = new ResultadoComboDTO();
						
						String codigo = rs.getString(1).trim();
						String nombre = rs.getString(2).trim();
						
						departamento.setCodigo(codigo);
						departamento.setNombre(nombre);
						
						departamentos.add(departamento);
					}
					
					obtenido.setDepartamentos(departamentos);
					
				} else if (!codError.equals("0")) {
					logger.error("Codigo: " + codError + " Mensaje: " + msgError);
					throw new PersonalsoftException(codError);
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
		return obtenido;
		
	}
}