package votre.portaloperaciones.reprocesos.dao;

import java.util.ArrayList;

import votre.portaloperaciones.reprocesos.beans.SolicitudDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public interface IReprocesosDAO {

	public SolicitudDTO crearNuevaSolictud(SolicitudDTO dto) throws PersonalsoftException;
	public SolicitudDTO agregarReferencia(SolicitudDTO dto) throws PersonalsoftException;
	public ArrayList<SolicitudDTO> consultarSolicitudes(SolicitudDTO dto) throws PersonalsoftException;
	public SolicitudDTO aprobarSolicitud(SolicitudDTO dto) throws PersonalsoftException;
	public SolicitudDTO procesarReferencia(SolicitudDTO dto) throws PersonalsoftException;
}
