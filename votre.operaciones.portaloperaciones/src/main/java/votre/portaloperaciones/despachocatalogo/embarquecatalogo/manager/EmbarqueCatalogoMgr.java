package votre.portaloperaciones.despachocatalogo.embarquecatalogo.manager;

import java.util.ArrayList;

import votre.dao.DAOFactory;
import votre.portaloperaciones.despachocatalogo.embarquecatalogo.beans.GuiasEmbarqueDTO;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class EmbarqueCatalogoMgr {
	
	public GuiasEmbarqueDTO enviarGuiasEmbarque(DAOFactory factory, GuiasEmbarqueDTO dto) throws PersonalsoftException{
		GuiasEmbarqueDTO obtenido = null;
		String[] guias = dto.getNroguia().split("\r\n");
		ArrayList<GuiasEmbarqueDTO> arrResumen = new ArrayList<GuiasEmbarqueDTO>();
		if(guias != null){
			for (int i = 0; i < guias.length; i++) {
				dto.setNroguia(guias[i].trim());
				obtenido = factory.getEmbarqueCatalogo().enviarGuiasEmbarque(dto);
				if(!obtenido.getStatus().equals(Constantes.EXITO_SP)){
					arrResumen.add(obtenido);
				}
			}
			obtenido.setArrResumen(arrResumen);
		}
		factory.getEmbarqueCatalogo().finalizarEmbarqueGuias(dto.getCodCia());
		return obtenido;
	}
}
