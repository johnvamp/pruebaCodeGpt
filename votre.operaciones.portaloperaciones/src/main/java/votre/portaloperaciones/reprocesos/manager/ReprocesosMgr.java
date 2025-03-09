package votre.portaloperaciones.reprocesos.manager;

import java.util.ArrayList;

import votre.dao.DAOFactory;
import votre.portaloperaciones.reprocesos.beans.SolicitudDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class ReprocesosMgr {

	public SolicitudDTO crearNuevaSolicitud(DAOFactory daoFactory, SolicitudDTO dto) throws PersonalsoftException{
		return daoFactory.getReprocesos().crearNuevaSolictud(dto);
	}
	
	public SolicitudDTO agregarReferencia(DAOFactory daoFactory, SolicitudDTO dto) throws PersonalsoftException{
		SolicitudDTO obtenido = null;
		ArrayList<SolicitudDTO> arrSolicitudes = null;
		obtenido = daoFactory.getReprocesos().agregarReferencia(dto);
		arrSolicitudes = daoFactory.getReprocesos().consultarSolicitudes(obtenido);
		obtenido.setArrSolicitudes(arrSolicitudes);
		return obtenido;
	}
	
	public ArrayList<SolicitudDTO> consultarSolicitudes(DAOFactory daoFactory, SolicitudDTO dto) throws PersonalsoftException{
		return daoFactory.getReprocesos().consultarSolicitudes(dto);
	}
	
	public SolicitudDTO aprobarSolicitud(DAOFactory daoFactory, SolicitudDTO dto) throws PersonalsoftException{
		return daoFactory.getReprocesos().aprobarSolicitud(dto);
	}
	
	public SolicitudDTO procesarReferecia(DAOFactory daoFactory, SolicitudDTO dto) throws PersonalsoftException{
		return daoFactory.getReprocesos().procesarReferencia(dto);
	}
}
