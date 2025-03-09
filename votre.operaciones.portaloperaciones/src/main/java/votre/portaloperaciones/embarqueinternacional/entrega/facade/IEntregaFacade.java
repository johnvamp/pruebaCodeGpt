package votre.portaloperaciones.embarqueinternacional.entrega.facade;

import java.util.ArrayList;

import votre.portaloperaciones.embarqueinternacional.entrega.beans.EntregaDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public interface IEntregaFacade {

	public EntregaDTO verEntrega(EntregaDTO entregaDTO) throws PersonalsoftException;
	public EntregaDTO verEntregaParcial(EntregaDTO entregaDTO) throws PersonalsoftException;
	public EntregaDTO confirmarEntrega(EntregaDTO entregaDTO) throws PersonalsoftException;
	public EntregaDTO procesarExcel(ArrayList<EntregaDTO> entregas) throws PersonalsoftException;
}
