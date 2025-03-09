package votre.portaloperaciones.pedidoscsl.facade;

import votre.portaloperaciones.pedidoscsl.beans.PedidosCSLDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public interface IPedidosCSLFacade {

	public PedidosCSLDTO listarOpciones(PedidosCSLDTO dto) throws PersonalsoftException;
	public PedidosCSLDTO validarSolictud(PedidosCSLDTO dto) throws PersonalsoftException;
	public PedidosCSLDTO grabarItem(PedidosCSLDTO dto) throws PersonalsoftException;
	public PedidosCSLDTO consultarSolicitud(PedidosCSLDTO dto) throws PersonalsoftException;
	public PedidosCSLDTO consultarDetalleSolicitud(PedidosCSLDTO dto) throws PersonalsoftException;
	public PedidosCSLDTO consultarAuditoria(PedidosCSLDTO dto) throws PersonalsoftException;
}
