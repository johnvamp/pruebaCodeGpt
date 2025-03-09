package votre.portaloperaciones.indicadordepedidos.facade;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import votre.dao.DAOFactory;
import votre.portaloperaciones.indicadordepedidos.beans.DatosComboDTO;
import votre.portaloperaciones.indicadordepedidos.beans.IndicadorPedidosDTO;
import votre.portaloperaciones.indicadordepedidos.beans.ParamDepartamentoDTO;
import votre.portaloperaciones.indicadordepedidos.beans.ParametrosCiudadDTO;
import votre.portaloperaciones.indicadordepedidos.beans.PedidosPorRangoDTO;
import votre.portaloperaciones.indicadordepedidos.mgr.IndicadorPedidosMgr;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class IndicadorPedidosFacade implements IIndicadorPedidosFacade {
	Logger logger = Logger.getLogger(this.getClass());

	public ArrayList<IndicadorPedidosDTO> indicadorPedidosGraf1(IndicadorPedidosDTO indicadorPedidosDTO) throws PersonalsoftException {
		DAOFactory daoFactory =null;
		IndicadorPedidosMgr indicadorPedidosMgr = new IndicadorPedidosMgr();
		ArrayList<IndicadorPedidosDTO> IndicadorPedidosGraf1 = new ArrayList<IndicadorPedidosDTO>(); 	
		
		try{
			daoFactory = new DAOFactory();
			IndicadorPedidosGraf1 = indicadorPedidosMgr.indicadorPedidosGraf1(daoFactory, indicadorPedidosDTO);
			daoFactory.commitTransaction();
		}catch (Exception e) {
			try{
				daoFactory.rolbackTransaction();
			}
			catch(SQLException sqlE){
				throw (PersonalsoftException) e;
			}
			if(e instanceof PersonalsoftException){
				throw(PersonalsoftException) e;
			}
			else{
				throw new PersonalsoftException(e);
			}	
		}
		finally{
			try{
				daoFactory.cerrarConexion();
			}
			catch(SQLException exception){
				throw new PersonalsoftException(exception);
			}
		}		
		return IndicadorPedidosGraf1;
	}

	public DatosComboDTO consultarDatosCombo(DatosComboDTO datosComboDTO) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		DatosComboDTO  obtenido = null;
		try{
			daoFactory = new DAOFactory();
			obtenido = daoFactory.getIndicadorPedidosGraf1().consultarDatosCombo(datosComboDTO);
		}
		catch (Exception e) {
			if (e instanceof PersonalsoftException) {
				throw (PersonalsoftException) e;
			}
			else {
				throw new PersonalsoftException(e);
			}
		} 
		finally {
			try{
				daoFactory.cerrarConexion();
		    } 
			catch (SQLException exception){
				throw new PersonalsoftException(exception);
			}
        }	
		return obtenido;
	}

	public ArrayList<PedidosPorRangoDTO> pedidosPorRango(PedidosPorRangoDTO pedidosPorRangoDTO) throws PersonalsoftException {
		DAOFactory daoFactory =null;
		IndicadorPedidosMgr indicadorPedidosMgr = new IndicadorPedidosMgr();
		ArrayList<PedidosPorRangoDTO> PedidosPorRango = new ArrayList<PedidosPorRangoDTO>(); 
		
		try{
			daoFactory = new DAOFactory();
			PedidosPorRango = indicadorPedidosMgr.pedidosPorRango(daoFactory, pedidosPorRangoDTO);
			daoFactory.commitTransaction();
		}catch (Exception e) {
			try{
				daoFactory.rolbackTransaction();
			}
			catch(SQLException sqlE){
				throw (PersonalsoftException) e;
			}
			if(e instanceof PersonalsoftException){
				throw(PersonalsoftException) e;
			}
			else{
				throw new PersonalsoftException(e);
			}	
		}
		finally{
			try{
				daoFactory.cerrarConexion();
			}
			catch(SQLException exception){
				throw new PersonalsoftException(exception);
			}
		}	
		return PedidosPorRango;
	}

	@Override
	public ParametrosCiudadDTO consultarCiudades(ParametrosCiudadDTO parametrosCiudad) throws PersonalsoftException {
		
		DAOFactory daoFactory = null;
		ParametrosCiudadDTO obtenido = null;
		
		try{
			daoFactory = new DAOFactory();
			obtenido = daoFactory.getIndicadorPedidosGraf1().consultarCiudades(parametrosCiudad);
		}
		catch (Exception e) {
			if (e instanceof PersonalsoftException) {
				throw (PersonalsoftException) e;
			}
			else {
				throw new PersonalsoftException(e);
			}
		} 
		finally {
			try{
				daoFactory.cerrarConexion();
		    } 
			catch (SQLException exception){
				throw new PersonalsoftException(exception);
			}
        }	
		return obtenido;
	}
	
	@Override
	public ParamDepartamentoDTO consultarDepartamentos(ParamDepartamentoDTO paramDepartamento) throws PersonalsoftException {
		
		DAOFactory daoFactory = null;
		ParamDepartamentoDTO obtenido = null;
		
		try{
			daoFactory = new DAOFactory();
			obtenido = daoFactory.getIndicadorPedidosGraf1().consultarDepartamentos(paramDepartamento);
		}
		catch (Exception e) {
			if (e instanceof PersonalsoftException) {
				throw (PersonalsoftException) e;
			}
			else {
				throw new PersonalsoftException(e);
			}
		} 
		finally {
			try{
				daoFactory.cerrarConexion();
		    } 
			catch (SQLException exception){
				throw new PersonalsoftException(exception);
			}
        }	
		return obtenido;
	}
}