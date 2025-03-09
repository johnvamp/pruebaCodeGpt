package votre.portaloperaciones.pedidos.dao;

import java.util.ArrayList;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.portaloperaciones.pedidos.beans.PedidosDTO;

public interface IPedidosDAO {

	public PedidosDTO tipoPedidosCombo (PedidosDTO pedidosDTO) throws PersonalsoftException;
	public PedidosDTO guardarCompradora (PedidosDTO pedidosDTO) throws PersonalsoftException;
	public ArrayList<PedidosDTO> listarDetalle (PedidosDTO pedidosDTO) throws PersonalsoftException; 
	public PedidosDTO tipoCombo (PedidosDTO pedidosDTO) throws PersonalsoftException; 
}
