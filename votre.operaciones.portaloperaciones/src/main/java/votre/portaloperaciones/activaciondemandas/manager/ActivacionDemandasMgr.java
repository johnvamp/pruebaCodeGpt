package votre.portaloperaciones.activaciondemandas.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.dao.DAOFactory;
import votre.portaloperaciones.activaciondemandas.beans.AsignarDTO;
import votre.portaloperaciones.activaciondemandas.beans.GuiasMasterDTO;
import votre.portaloperaciones.activaciondemandas.beans.ReferenciasDTO;
import votre.portaloperaciones.util.Constantes;

public class ActivacionDemandasMgr {
	
	private Logger logger = Logger.getLogger(this.getClass());

	public AsignarDTO asignarTransportadores(DAOFactory factory, AsignarDTO dto) throws PersonalsoftException{
		AsignarDTO obtenido = null;
		PersonalsoftException ps = null;
		//Si es automatico
		if("".equals(dto.getValor())){
			dto.setCantidad("99999");
			obtenido =  factory.getActDemandas().asignarTransportadores(dto);
//			if(obtenido.getEstado().equals(Constantes.EXITO_SP)){
				//Se invoca de nuevo
//				dto.setAccion(Constantes.ACCION_FINALIZAR_PROCESO);
//				dto.setValor("");
//				dto.setCantidad("");
//				obtenido =  factory.getActDemandas().asignarTransportadores(dto);
//			}
		}
		//Si es manual
		else{
			String[] valores = null;
			//Si es manual por referencias
			if(dto.getAccion().equals(Constantes.ACCION_REFERENCIAS)){
				valores = dto.getValor().split("\\|");
			}
			//Si es manual por cedulas
			if(dto.getAccion().equals(Constantes.ACCION_CEDULAS)){
				valores = dto.getValor().split("\r\n");
			}
			if(valores != null){
				for (int i = 0; i < valores.length; i++) {
					try {
						if (dto.getAccion().equals(Constantes.ACCION_REFERENCIAS)) {
							String[] datos = valores[i].split("\\,");
							dto.setValor(datos[0]);
							dto.setCantidad(datos[1]);
						}
						if (dto.getAccion().equals(Constantes.ACCION_CEDULAS)) {
							dto.setValor(valores[i]);
							dto.setCantidad("99999");
						}
						obtenido = factory.getActDemandas().asignarTransportadores(dto);
					} catch (Exception e) {
						ps = new PersonalsoftException(e);
						logger.error(ps.getTraza());
						throw ps;
					}
				}
				//Se invoca de nuevo
//				dto.setAccion(Constantes.ACCION_FINALIZAR_PROCESO);
//				dto.setValor("");
//				dto.setCantidad("");
//				obtenido =  factory.getActDemandas().asignarTransportadores(dto);
			}
		}
		return obtenido;
	}
	
	public ReferenciasDTO cambiarACorreoExtra(DAOFactory factory, ReferenciasDTO dto) throws PersonalsoftException{
		ReferenciasDTO obtenido = null;
		ArrayList<ReferenciasDTO> referencias = new ArrayList<ReferenciasDTO>();
		String[] referencia = null;
		String[] descripcion = null;
		referencia = dto.getReferencia().split("\\|");
		descripcion = dto.getDescripRefe().split("\\|");
		if(referencia != null){
			for (int i = 0; i < referencia.length; i++) {
				dto.setReferencia(referencia[i]);
				dto.setDescripRefe(descripcion[i]);
				obtenido =  factory.getActDemandas().cambiarACorreoExtra(dto);
				referencias.add(obtenido);
			}
			obtenido.setReferencias(referencias);
		}
		return obtenido;
	}
	
	public ArrayList<GuiasMasterDTO> imprimirGuiasMaster(DAOFactory factory, ArrayList<GuiasMasterDTO> arrGuias) throws PersonalsoftException{
		GuiasMasterDTO obtenido = null;
		ArrayList<GuiasMasterDTO> arrObtenido = new ArrayList<GuiasMasterDTO>();
		for (GuiasMasterDTO guiasMasterDTO : arrGuias) {
				obtenido = factory.getActDemandas().consultarGuiasMaster(guiasMasterDTO);
				if(!obtenido.getEstado().equals(Constantes.EXITO_SP)){
					obtenido.setCedulaTrans(guiasMasterDTO.getCedulaTrans());
					obtenido.setNombreTrans(guiasMasterDTO.getNombreTrans());
					arrObtenido.add(obtenido);
				}
		}
		return arrObtenido;
	}
}
