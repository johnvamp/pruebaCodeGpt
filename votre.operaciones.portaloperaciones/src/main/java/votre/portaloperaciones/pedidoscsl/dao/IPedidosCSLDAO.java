package votre.portaloperaciones.pedidoscsl.dao;

import votre.portaloperaciones.pedidoscsl.beans.PedidosCSLDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public interface IPedidosCSLDAO {

	public PedidosCSLDTO listarOpciones(PedidosCSLDTO dto) throws PersonalsoftException;
	public PedidosCSLDTO validarSolicitud(PedidosCSLDTO dto) throws PersonalsoftException;
	public PedidosCSLDTO grabarItem(PedidosCSLDTO dto) throws PersonalsoftException;
	public PedidosCSLDTO consultarSolicitud(PedidosCSLDTO dto) throws PersonalsoftException;
	public PedidosCSLDTO consultarDetalleSolicitud(PedidosCSLDTO dto) throws PersonalsoftException;
	public PedidosCSLDTO consultarAuditoria(PedidosCSLDTO dto) throws PersonalsoftException;
}