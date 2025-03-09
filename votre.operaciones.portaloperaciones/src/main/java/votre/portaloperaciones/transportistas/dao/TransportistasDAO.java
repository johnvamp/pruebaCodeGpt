package votre.portaloperaciones.transportistas.dao;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.util.CargadorMsj;
import co.com.personalsoft.base.util.Fecha;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.apache.log4j.Logger;
import votre.portaloperaciones.transportistas.beans.TransportistaReversarDTO;
import votre.portaloperaciones.transportistas.beans.TransportistasComboDTO;
import votre.portaloperaciones.transportistas.beans.TransportistasDTO;
import votre.portaloperaciones.transportistas.beans.TransportistasSeleccionarDTO;

public class TransportistasDAO implements ITransportistasDAO{

	private BDHelper bdHelper;
	Logger logger = Logger.getLogger(this.getClass());
	Logger info = Logger.getLogger("info");
	
	public TransportistasDAO(){}
	public TransportistasDAO(BDHelper bdHelper){
		this.bdHelper = bdHelper;
	}
	

	public TransportistasDTO consultaTransportitasGuardar(TransportistasDTO transportistasDTO) throws PersonalsoftException{
		CallableStatement cs = null;
		
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		
		parametros.add(new Parametro(Types.VARCHAR, transportistasDTO.getpCia(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, transportistasDTO.getNumeroOrden(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, transportistasDTO.getNumeroGuia(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, transportistasDTO.getCodigoCombo(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, transportistasDTO.getEstadoCombo(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, transportistasDTO.getObservaciones(), Parametro.IN));
		
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_TRSGUARDAR");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				transportistasDTO.setError(cs.getString(7).trim());	
				transportistasDTO.setMsgError(cs.getString(8).trim());	
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
		return transportistasDTO;			
	}
	
	
	public TransportistasDTO consultaTransportitasGuardarMovil(TransportistasDTO transportistasDTO) throws PersonalsoftException{
		CallableStatement cs = null;
		
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		
		parametros.add(new Parametro(Types.VARCHAR, transportistasDTO.getpCia(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, transportistasDTO.getNumeroOrden(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, transportistasDTO.getNumeroGuia(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, transportistasDTO.getCodigoCombo(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, transportistasDTO.getEstadoCombo(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, transportistasDTO.getObservaciones(), Parametro.IN));
		
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		parametros.add(new Parametro(Types.VARCHAR, transportistasDTO.getFecEnt(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, transportistasDTO.getHorEnt(), Parametro.IN));
				
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_TRSGUARDARAPP");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				transportistasDTO.setError(cs.getString(7).trim());	
				transportistasDTO.setMsgError(cs.getString(8).trim());	
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
		return transportistasDTO;			
	}
	
	
	public TransportistasSeleccionarDTO consultaTransportitasListas(TransportistasSeleccionarDTO transportistasSeleccionarDTO) throws PersonalsoftException {
		CallableStatement cs = null;
		ResultSet rs = null;
		TransportistasSeleccionarDTO transportistasSeleccionar = new TransportistasSeleccionarDTO();
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		LinkedHashMap<String, String> desComboLista = new LinkedHashMap<String, String>();
	
		ArrayList<String> campanas = new ArrayList<String>();
		
		parametros.add(new Parametro(Types.VARCHAR, transportistasSeleccionarDTO.getDsCia(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, transportistasSeleccionarDTO.getDsTipo(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		String tipo = transportistasSeleccionarDTO.getDsTipo();
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_TRSLISTAR");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				rs= cs.getResultSet();

				if(rs != null){					
					if(tipo.equals("T"))
					{
					while (rs.next()){						
    					desComboLista.put(rs.getString(2).trim(),rs.getString(3).trim());										 
					}				
				     transportistasSeleccionar.setDsListaTranspoCampana(desComboLista);					 
					}				
										
					if(tipo.equals("E"))
					{
					while (rs.next()){						
						desComboLista.put (rs.getString(1).trim(),rs.getString(2).trim());				 
					}
					transportistasSeleccionar.setDsListaTranspoEstado(desComboLista);
					}
										
					if(tipo.equals("C"))
					{
					while (rs.next()){
						campanas.add(rs.getString(1).trim());
					}
					transportistasSeleccionar.setCampanas(campanas);
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
		
		
		return transportistasSeleccionar;
	}
	 
	
	public TransportistasComboDTO consultaTransportitasCombo(TransportistasComboDTO transportistasComboDTO) throws PersonalsoftException{
		CallableStatement cs = null;
		ResultSet rs = null;
		TransportistasComboDTO transportistasCombo = new TransportistasComboDTO();
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		LinkedHashMap<String, String> desCombo = new LinkedHashMap<String, String>();
	
		parametros.add(new Parametro(Types.VARCHAR, transportistasComboDTO.getDsCia(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, transportistasComboDTO.getDsTipo(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_TRSLISTAR");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				rs= cs.getResultSet();

				if(rs != null){
					while (rs.next()){
				     desCombo.put (rs.getString(2).trim().concat("-").concat(rs.getString(1).trim()),rs.getString(3).trim());				 
					}
					transportistasCombo.setDsEstaCombo(desCombo);							
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
		return transportistasCombo;			
	}
	
	
	public ArrayList<TransportistasDTO> consultaTransportitas(TransportistasDTO transportistasDTO) throws PersonalsoftException {
		
		CallableStatement cs = null;
		ResultSet rs = null;
		TransportistasDTO obtenido = null;
		
		ArrayList<TransportistasDTO> transportistas = new ArrayList<TransportistasDTO>();
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, transportistasDTO.getpCia(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, transportistasDTO.getpEst(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, transportistasDTO.getpUsr(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, transportistasDTO.getNumeroCampana(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, transportistasDTO.getNumeroZona(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, transportistasDTO.getpTipoConsult(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, transportistasDTO.getValor(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_TRSPEDIDOS");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if(cs != null){
				cs.execute();
				rs= cs.getResultSet();
				
				transportistasDTO.setNomTranspo(cs.getString(8).trim());
				transportistasDTO.setTotalPedi(cs.getString(9).trim());
				transportistasDTO.setError(cs.getString(10).trim());
				transportistasDTO.setMsgError(cs.getString(11).trim());
				
				if(rs != null){
					while (rs.next()){
						obtenido = new TransportistasDTO();
						
						obtenido.setNumeroCampana(rs.getString(2)!=null ? rs.getString(2).trim() : "");
						obtenido.setCampania(rs.getString(2)!=null ? rs.getString(2).trim() : "");
						obtenido.setNumeroOrden(rs.getString(1)!=null ? rs.getString(1).trim() : "");
						obtenido.setNumeroGuia(rs.getString(3)!=null ? rs.getString(3).trim() : "");
						obtenido.setCedula(rs.getString(4)!=null ? rs.getString(4).trim() : "");
						obtenido.setNombre(rs.getString(5)!=null ? rs.getString(5).trim() : "");
						
						String fecha = rs.getString(14)!=null ? rs.getString(14).toString() : "";
						obtenido.setFechaEmbarque(Fecha.cambiarFormatoFecha(fecha, Fecha.EXPR_YYYYMMDD, Fecha.EXPR_DDMMYYYY_LINEA));
												
						String hora = rs.getString(15) != null ? rs.getString(15).trim() : "";
						if(hora.length() == 5){
							hora = "0" + hora;
						}
						
						if(hora.length() == 6){
							hora = hora.substring(0,2) +":"+ hora.substring(2,4) +":"+ hora.substring(4);
						}						
						obtenido.setHoraEmbarque(hora);
						
						obtenido.setPrimeraVisita(rs.getString(18)!=null ? rs.getString(18).trim() : "");
						
						if(rs.getString(18).equals("                    "))
						{
							obtenido.setPrimeraVisita("");
						}
						
						obtenido.setSegundaVisita(rs.getString(19)!=null ? rs.getString(19).trim() : "");
						
						if(rs.getString(19).equals("                    "))
						{
							obtenido.setSegundaVisita("");
						}
						
						obtenido.setTerceraVisita(rs.getString(20)!=null ? rs.getString(20).trim() : "");
						
						if(rs.getString(20).equals("                    "))
						{
							obtenido.setTerceraVisita("");
						}
						
						obtenido.setObservaciones(rs.getString(23)!=null ? rs.getString(23).trim() : "");												
						obtenido.setNumeroTel1(rs.getString(6)!=null ? rs.getString(6).trim() : "");
						obtenido.setNumeroTel2(rs.getString(7)!=null ? rs.getString(7).trim() : "");
						obtenido.setNumeroZona(rs.getString(9)!=null ? rs.getString(9).trim() : "");
						obtenido.setDepartamento(rs.getString(10)!=null ? rs.getString(10).trim() : "");
						obtenido.setCiudad(rs.getString(11)!=null ? rs.getString(11).trim() : "");
						obtenido.setDireccion(rs.getString(12)!=null ? rs.getString(12).trim() : "");
						obtenido.setNumeroGuiaMas(rs.getString(13)!=null ? rs.getString(13).trim() : "");
						obtenido.setPorte(rs.getString(16)!=null ? rs.getString(16).trim() : "");
						obtenido.setValor(rs.getString(17)!=null ? rs.getString(17).trim() : "");	
											
						String fechaEnt = rs.getString(21)!=null ? rs.getString(21).toString() : "";
						
						if(!fechaEnt.equals("          ")){
							obtenido.setFecEnt(Fecha.cambiarFormatoFecha(fechaEnt, Fecha.EXPR_YYYYMMDD, Fecha.EXPR_DDMMYYYY_LINEA));	
						}
											
						String horaEnt = rs.getString(22) != null ? rs.getString(22).trim() : "";
						if(horaEnt.length() == 5){
							horaEnt = "0" + hora;
						}						
						if(horaEnt.length() == 6){
							horaEnt = horaEnt.substring(0,2) +":"+ horaEnt.substring(2,4) +":"+ horaEnt.substring(4);
						}						
						obtenido.setHorEnt(horaEnt);
						
						transportistas.add(obtenido);						
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
		
		return transportistas;

	}

	public TransportistaReversarDTO reversarEstado(TransportistaReversarDTO reversar) throws PersonalsoftException {
	    CallableStatement cs = null;
	    CallableStatement csl = null;
        ResultSet rs = null;
        
        ArrayList<Parametro> parametros = new ArrayList<Parametro>();
        ArrayList<Parametro> parametrosLibreria = new ArrayList<Parametro>();
        PersonalsoftException ps = null;
        
        parametros.add(new Parametro(Types.VARCHAR, reversar.getCompany(), Parametro.IN));
        parametros.add(new Parametro(Types.VARCHAR, reversar.getUser(), Parametro.IN));
        parametros.add(new Parametro(Types.VARCHAR, reversar.getJsonReversar(), Parametro.IN));
        parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
        parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
        
        parametrosLibreria.add(new Parametro(Types.VARCHAR, "YAJL", Parametro.IN_OUT));
        
        try{
            String rutaSQL = Configuracion.getInstance().getParametroApp("SP_TRSREVESTADO");
            String rutaSQLLibreria = Configuracion.getInstance().getParametroApp("SP_LIBRERIAAGREGAR");
            cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
            csl = this.bdHelper.cargarCallableStatement(rutaSQLLibreria, parametrosLibreria);
            if(cs != null){
                csl.execute();
                cs.execute();
                reversar.setState(cs.getString(4));
                reversar.setDescState(cs.getString(5).trim());
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
                BDHelper.close(csl);
                BDHelper.close(rs);
                
            }catch (SQLException e) {
                throw new PersonalsoftException(e);
            }
        }
        return reversar; 
	}
	
}

