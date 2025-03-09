package votre.portaloperaciones.flujowms.facade;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import votre.dao.DAOFactory;
import votre.portaloperaciones.flujowms.beans.AlertasDTO;
import votre.portaloperaciones.flujowms.beans.FlujoWmsDTO;
import votre.portaloperaciones.flujowms.beans.TituloFlujoWmsDTO;
import votre.portaloperaciones.flujowms.mgr.FlujoWmsMgr;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class FlujoWmsFacade implements IFlujoWmsFacade{
	Logger logger = Logger.getLogger(this.getClass());
	
	
	public ArrayList<FlujoWmsDTO> consultaFlujoWms(FlujoWmsDTO flujoWmsDTO) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		FlujoWmsMgr flujoWmsMgr = new FlujoWmsMgr();
		ArrayList<FlujoWmsDTO> flujoWms = new ArrayList<FlujoWmsDTO>();
		try{			
			daoFactory = new DAOFactory();
			flujoWms = flujoWmsMgr.flujoWms(daoFactory, flujoWmsDTO);
			daoFactory.commitTransaction();
		}
		catch (Exception e) {
			try{
				daoFactory.rolbackTransaction();
			}
			catch(SQLException sqlE)
			{
				throw (PersonalsoftException) e;
			}
			if(e instanceof PersonalsoftException)
			{
				throw(PersonalsoftException) e;
			}
			else
			{
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
		return flujoWms;
	}


	public ArrayList<TituloFlujoWmsDTO> consultaTituloFlujoWms(TituloFlujoWmsDTO tituloFlujoWmsDTO) throws PersonalsoftException {
	
		DAOFactory daoFactory = null;
		FlujoWmsMgr flujoWmsMgr = new FlujoWmsMgr();
		ArrayList<TituloFlujoWmsDTO> tituloFlujoWms = new ArrayList<TituloFlujoWmsDTO>();
		
		try{
			daoFactory = new DAOFactory();
			tituloFlujoWms = flujoWmsMgr.tituloFlujoWms(daoFactory, tituloFlujoWmsDTO);
			daoFactory.commitTransaction();
		}catch (Exception e) {
			try{
				daoFactory.rolbackTransaction();
			}
			catch(SQLException sqlE)
			{
				throw (PersonalsoftException) e;
			}
			if(e instanceof PersonalsoftException)
			{
				throw(PersonalsoftException) e;
			}
			else
			{
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
		
		return tituloFlujoWms;
	}


	public AlertasDTO consultarEstados(String codCia) throws PersonalsoftException {
		DAOFactory factory = null;
		FlujoWmsMgr flujoWmsMgr = new FlujoWmsMgr();
		AlertasDTO obtenido = null;
		
		try{
			factory = new DAOFactory();
			flujoWmsMgr = new FlujoWmsMgr();
			obtenido =  flujoWmsMgr.consultarEstados(factory, codCia);
		}
		catch (Exception e) {
			if (e instanceof PersonalsoftException) {
				throw (PersonalsoftException) e;
			} else {
				throw new PersonalsoftException(e);
			}
		} finally {
			try {
				factory.cerrarConexion();
			} catch (SQLException exception) {
				throw new PersonalsoftException(exception);
			}
		}
		
		return obtenido;
	}

	public AlertasDTO consultarDetalleEstado(AlertasDTO alertasDTO) throws PersonalsoftException {
		DAOFactory factory = null;
		FlujoWmsMgr flujoWmsMgr = new FlujoWmsMgr();
		AlertasDTO obtenido = null;
		
		try{
			factory = new DAOFactory();
			flujoWmsMgr = new FlujoWmsMgr();
			obtenido =  flujoWmsMgr.consultarDetalleEstado(factory, alertasDTO);
		}
		catch (Exception e) {
			if (e instanceof PersonalsoftException) {
				throw (PersonalsoftException) e;
			} else {
				throw new PersonalsoftException(e);
			}
		} finally {
			try {
				factory.cerrarConexion();
			} catch (SQLException exception) {
				throw new PersonalsoftException(exception);
			}
		}
		return obtenido;
	}

}
