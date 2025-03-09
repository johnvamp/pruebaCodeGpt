package votre.portaloperaciones.activaciondemandas.cmd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import votre.portaloperaciones.activaciondemandas.beans.CasoDTO;
import votre.portaloperaciones.activaciondemandas.beans.OpcionDTO;
import votre.portaloperaciones.activaciondemandas.delegate.ActivacionDemandasBusiness;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class ModificarEnviosManualesCmd implements IBaseCmd {

	//private Logger logger = Logger.getLogger(this.getClass());
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		ActivacionDemandasBusiness actBusiness = new ActivacionDemandasBusiness();
		ArrayList<OpcionDTO> opciones = new ArrayList<OpcionDTO>();
		ArrayList<OpcionDTO> transportadoras = new ArrayList<OpcionDTO>();
		ArrayList<OpcionDTO> transportistas = new ArrayList<OpcionDTO>();
		OpcionDTO opcion = new OpcionDTO();
		CasoDTO dto = null;
		ArrayList<CasoDTO> lista = new ArrayList<CasoDTO>();

		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		String consultar = req.getParameter("consultar");

		opcion.setCia(codCia);
		opcion.setAccion(Constantes.ACCION_SELECCION_DEMANDA);
		opciones = actBusiness.consultarOpciones(opcion);
		
		opcion.setAccion(Constantes.ACCION_SELECCION_TRANSPORTADOR);
		transportadoras = actBusiness.consultarOpciones(opcion);

		opcion.setAccion(Constantes.ACCION_SELECCION_TRANSPORTISTA);
		transportistas = actBusiness.consultarOpciones(opcion);

		if(consultar != null){
			dto = obtenerModificacion(req);
			lista = actBusiness.consultarCasos(dto);
			req.setAttribute("busqueda", dto);
		}

		req.setAttribute("opciones", opciones);
		req.setAttribute("transportadoras", transportadoras);
		req.setAttribute("transportistas", transportistas);
		req.setAttribute("envios", lista);
		req.setAttribute("cantidadEnvios", lista.size());
		req.setAttribute("SELECCION_TODO", Constantes.SELECCION_TODO);
		req.setAttribute("SELECCION_REFERENCIA", Constantes.SELECCION_REFERENCIA);
	}

	private CasoDTO obtenerModificacion(HttpServletRequest req){

		CasoDTO casoDTO = new CasoDTO();
		int relleno;
		
		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		casoDTO.setCia(codCia);
		casoDTO.setCriterio(Constantes.CRITERIO_MODIFICACION_ENVIO_MANUAL);
		casoDTO.setSeleccion(req.getParameter("seleccionBuscar") != null ? req.getParameter("seleccionBuscar") : "");
		casoDTO.setValor(req.getParameter("opcionBuscar") != null ? req.getParameter("opcionBuscar") : "");
		if(Constantes.SELECCION_TODO.equals(casoDTO.getSeleccion())){
			casoDTO.setValor("");
		}
		else if(Constantes.SELECCION_REFERENCIA.equals(casoDTO.getSeleccion())){
			StringBuffer refe = new StringBuffer(casoDTO.getRefeSku());
			if(refe.length()>=7){
				refe = new StringBuffer(refe.substring(0, 7));
			}
			else{
				relleno = 7 - refe.length();
				for (int i = 0; i < relleno; i++) {
					refe.append(" ");
				}
			}
			
			StringBuffer color = new StringBuffer(casoDTO.getColorSku());
			if(color.length()>=3){
				color = new StringBuffer(color.substring(0, 3));
			}
			else{
				relleno = 3 - color.length();
				for (int i = 0; i < relleno; i++) {
					color.insert(0, " ");
				}
			}

			StringBuffer talla = new StringBuffer(casoDTO.getTallaSku());
			if(talla.length()>=3){
				talla = new StringBuffer(talla.substring(0, 3));
			}
			else{
				relleno = 3 - talla.length();
				for (int i = 0; i < relleno; i++) {
					talla.insert(0, " ");
				}
			}

			StringBuffer sku = new StringBuffer(refe);
			sku.append(" ");
			sku.append(color);
			sku.append(" ");
			sku.append(talla);
			casoDTO.setValor(sku.toString());
		}

		int paginaActual = req.getParameter("paginaActual") != null && !"".equals(req.getParameter("paginaActual")) ? Integer.parseInt(req.getParameter("paginaActual")) : 1;
		String registro = Configuracion.getInstance().getParametroApp("PAGINACION_CANT_REG");
		int incremento = registro != null ? Integer.parseInt(registro):30;
		int semilla = 0;
		if(paginaActual > 1){
			semilla = (paginaActual-1) * incremento;
		}
		else{
			paginaActual = 1;
		}
		casoDTO.setSemilla(semilla);
		casoDTO.setPaginaActual(paginaActual);

		return casoDTO;
	}
	
}
