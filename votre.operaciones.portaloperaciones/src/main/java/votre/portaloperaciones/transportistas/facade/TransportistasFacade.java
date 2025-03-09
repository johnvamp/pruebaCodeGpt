package votre.portaloperaciones.transportistas.facade;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import votre.dao.DAOFactory;
import votre.portaloperaciones.transportistas.beans.TransportistaReversarDTO;
import votre.portaloperaciones.transportistas.beans.TransportistasComboDTO;
import votre.portaloperaciones.transportistas.beans.TransportistasDTO;
import votre.portaloperaciones.transportistas.beans.TransportistasSeleccionarDTO;
import votre.portaloperaciones.transportistas.mgr.TransportistasMgr;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class TransportistasFacade implements ITransportistasFacade {

	Logger logger = Logger.getLogger(this.getClass());
	
	public TransportistasSeleccionarDTO transportistasSeleccionar(TransportistasSeleccionarDTO transportistasSeleccionarDTO)throws PersonalsoftException {
		DAOFactory daoFactory =null;
		TransportistasMgr transportistasMgr = new TransportistasMgr();		
		TransportistasSeleccionarDTO  consultaTransportistasSeleccionar = null;		
		try{
			daoFactory = new DAOFactory();		
			consultaTransportistasSeleccionar  = daoFactory.getTransportistas().consultaTransportitasListas(transportistasSeleccionarDTO);
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
		
		return consultaTransportistasSeleccionar;
	}
	
	
	public TransportistasComboDTO transportistasCombo(TransportistasComboDTO transportistasComboDTO)	throws PersonalsoftException {
//	public ArrayList<TransportistasComboDTO> transportistasCombo(TransportistasComboDTO transportistasComboDTO)	throws PersonalsoftException {
		DAOFactory daoFactory =null;
		TransportistasMgr transportistasMgr = new TransportistasMgr();
		
		//ArrayList<TransportistasComboDTO> consultaTransportitasCombo = new ArrayList<TransportistasComboDTO>();
	     TransportistasComboDTO consultaTransportitasCombo = null;
		try{
			
			daoFactory = new DAOFactory();
			//consultaTransportitasCombo = transportistasMgr.transportistasCombo(daoFactory, transportistasComboDTO);
			consultaTransportitasCombo = daoFactory.getTransportistas().consultaTransportitasCombo(transportistasComboDTO);
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
		
		
		return consultaTransportitasCombo;
		
		
	}
	
	
	
	
	
	public ArrayList<TransportistasDTO> transportistas(TransportistasDTO transportistasDTO) throws PersonalsoftException {
		DAOFactory daoFactory =null;
		
		TransportistasMgr transportistasMgr = new TransportistasMgr();
		ArrayList<TransportistasDTO> consultaTransportitas = new ArrayList<TransportistasDTO>();
		
		try{
			
			daoFactory = new DAOFactory();
			consultaTransportitas = transportistasMgr.transportistas(daoFactory, transportistasDTO);
			
			
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
		
		
		return consultaTransportitas;
	}





	public TransportistasDTO transportistasGuardar(TransportistasDTO transportistasDTO) throws PersonalsoftException {
	
	DAOFactory daoFactory =null;
		
		TransportistasMgr transportistasMgr = new TransportistasMgr();
		TransportistasDTO guardarTransportitas = null;
		
		try{
			
			daoFactory = new DAOFactory();
			guardarTransportitas = transportistasMgr.transportistasGuardar(daoFactory, transportistasDTO);
			
			
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
		
		
		return guardarTransportitas;
		
		
	}

	public ArrayList<TransportistasDTO> guardarEntregados(ArrayList<TransportistasDTO> arrTrans) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		TransportistasMgr transportistasMgr = null;
		ArrayList<TransportistasDTO> obtenido = null;
		try{
			daoFactory = new DAOFactory();
			transportistasMgr = new TransportistasMgr();
			obtenido = transportistasMgr.guardarEntregados(daoFactory, arrTrans);
		} catch (Exception e) {
			if (e instanceof PersonalsoftException) {
				throw (PersonalsoftException) e;
			} else {
				throw new PersonalsoftException(e);
			}
		} finally {
			try {
				daoFactory.cerrarConexion();
			} catch (SQLException exception) {
				throw new PersonalsoftException(exception);
			}
		}
		return obtenido;
	}
	
	
	public ArrayList<TransportistasDTO> guardarEntregadosMovil(ArrayList<TransportistasDTO> arrTrans) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		TransportistasMgr transportistasMgr = null;
		ArrayList<TransportistasDTO> obtenido = null;
		try{
			daoFactory = new DAOFactory();
			transportistasMgr = new TransportistasMgr();
			obtenido = transportistasMgr.guardarEntregadosMovil(daoFactory, arrTrans);
		} catch (Exception e) {
			if (e instanceof PersonalsoftException) {
				throw (PersonalsoftException) e;
			} else {
				throw new PersonalsoftException(e);
			}
		} finally {
			try {
				daoFactory.cerrarConexion();
			} catch (SQLException exception) {
				throw new PersonalsoftException(exception);
			}
		}
		return obtenido;
	}
	
	public TransportistaReversarDTO reversarEstado(TransportistaReversarDTO reversar) throws PersonalsoftException {
	    DAOFactory daoFactory = null;
        TransportistasMgr transportistasMgr = null;
        ArrayList<TransportistasDTO> obtenido = null;
        TransportistaReversarDTO response = null;
        try{
            daoFactory = new DAOFactory();
            transportistasMgr = new TransportistasMgr();
            response = transportistasMgr.reversarEstado(daoFactory, reversar);
        } catch (Exception e) {
            if (e instanceof PersonalsoftException) {
                throw (PersonalsoftException) e;
            } else {
                throw new PersonalsoftException(e);
            }
        } finally {
            try {
                daoFactory.cerrarConexion();
            } catch (SQLException exception) {
                throw new PersonalsoftException(exception);
            }
        }
        return response;
	}
}
