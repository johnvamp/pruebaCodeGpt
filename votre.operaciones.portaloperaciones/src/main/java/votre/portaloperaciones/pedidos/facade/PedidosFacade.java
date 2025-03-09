package votre.portaloperaciones.pedidos.facade;

import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import votre.dao.DAOFactory;
import votre.portaloperaciones.pedidos.beans.PedidosDTO;
import votre.portaloperaciones.pedidos.mgr.PedidosMgr;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class PedidosFacade implements IPedidosFacade{

	Logger logger = Logger.getLogger(this.getClass());
	
	public PedidosDTO listaPedidos(PedidosDTO pedidosDTO)throws PersonalsoftException {
		DAOFactory daoFactory = null;
		PedidosMgr pedidosMgr = new PedidosMgr();
		PedidosDTO consultarTipoPedidos = null;
		try{
			daoFactory = new DAOFactory();
			consultarTipoPedidos = daoFactory.getPedidos().tipoPedidosCombo(pedidosDTO);		
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
		return consultarTipoPedidos;
	}

	public PedidosDTO guardarCompradora(PedidosDTO pedidosDTO)throws PersonalsoftException {
		DAOFactory daoFactory = null;
		PedidosMgr pedidosMgr = new PedidosMgr();
		PedidosDTO guardarCompradora = null;		
		
		try{
			daoFactory = new DAOFactory();
			guardarCompradora = daoFactory.getPedidos().guardarCompradora(pedidosDTO);		
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
		return guardarCompradora;
		
	}


	public ArrayList<PedidosDTO> listarDetalle(PedidosDTO pedidosDTO)throws PersonalsoftException {
		DAOFactory daoFactory= null;
		PedidosMgr pedidosMgr = new PedidosMgr();
		
		ArrayList<PedidosDTO> listarDetalle = new ArrayList<PedidosDTO>();
		
		try{
			daoFactory = new DAOFactory();
			listarDetalle = pedidosMgr.listarDetalle(daoFactory, pedidosDTO);
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
		
		return listarDetalle;
	}


	public PedidosDTO tipoCombo(PedidosDTO pedidosDTO)throws PersonalsoftException {
		DAOFactory daoFactory = null;
		PedidosMgr pedidosMgr = new PedidosMgr();
		PedidosDTO consultarTipoCombo = null;
		try{
			daoFactory = new DAOFactory();
			consultarTipoCombo = daoFactory.getPedidos().tipoCombo(pedidosDTO);		
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
		return consultarTipoCombo;		
	}

}
