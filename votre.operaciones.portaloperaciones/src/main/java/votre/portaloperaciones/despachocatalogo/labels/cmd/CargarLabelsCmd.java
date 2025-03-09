package votre.portaloperaciones.despachocatalogo.labels.cmd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import votre.portaloperaciones.despachocatalogo.labels.beans.DespachoDTO;
import votre.portaloperaciones.despachocatalogo.labels.beans.LabelsDTO;
import votre.portaloperaciones.despachocatalogo.labels.beans.ZonasDTO;
import votre.portaloperaciones.despachocatalogo.labels.delegate.LabelsBusiness;
import votre.portaloperaciones.seguridad.util.UtilSesion;
import votre.portaloperaciones.util.Constantes;
import votre.utils.moduloseguridad.beans.UsuarioDTO;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;

public class CargarLabelsCmd implements IBaseCmd {
	private Logger logger = Logger.getLogger(this.getClass());
	private String codCia;
	private String zona;
	private String nomPais;
	UsuarioDTO usuario = new UsuarioDTO();
	private String mensaje;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		
		LabelsBusiness labelsBusiness = new LabelsBusiness();
		LabelsDTO labels = new LabelsDTO();
		DespachoDTO despacho = new DespachoDTO();
		ArrayList<ZonasDTO> zonas = new ArrayList<ZonasDTO>(); 
		
		try{
			obtenerDatos(req);
			dtoAssembler(labels);			
			labels = labelsBusiness.cargarLabels(labels);
			despacho = labelsBusiness.consultar(labels);
			zonas = labelsBusiness.cargarZonas(labels);
			if(labels != null){
				if(labels.getEstado().equals(Constantes.EXITO_SP) || labels.getEstado().equals(Constantes.NO_EXI_DATOS)){
						req.setAttribute("codCia", labels.getCodCia());
						req.setAttribute("tituloApli", labels.getTituloApli());
						req.setAttribute("tituloPant", labels.getTituloPant());
						req.setAttribute("pais", labels.getTituloPais());
						req.setAttribute("nomPais", nomPais);
						req.setAttribute("botonExcel", labels.getBotonExcel());
						req.setAttribute("botonRegresar", labels.getBotonRegresar());					
						req.setAttribute("botonGenerar", labels.getBotonGenerar());
						req.setAttribute("botonLabels", labels.getBotonLabels());
						req.setAttribute("tituloZona", labels.getTituloZona());
						req.setAttribute("seleccioneZona", despacho.getTituloZona());
						req.setAttribute("campana", despacho.getCampana());
						req.setAttribute("opciones", labels.getRegistros());
						req.setAttribute("usuario", usuario.getUsuario());
				}
				else{
					mensaje= labels.getDesscrpcion();
				}
			}
			if(zonas != null){
				req.setAttribute("zonas", zonas);
			}
			
			if (mensaje != null && !mensaje.equals("")){
				req.setAttribute("mensaje", mensaje);				
			}
		}
		catch (Exception e) {
			logger.error(new PersonalsoftException(e).getTraza());
			req.setAttribute("mensaje", CargadorMsj.getInstance().getMensaje("errorGeneral"));
		}		
	}
	
	public void obtenerDatos(HttpServletRequest req){
		codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		nomPais = req.getSession().getAttribute( Constantes.NOM_PAIS ) != null ? ((String)req.getSession().getAttribute( Constantes.NOM_PAIS )).trim() : "";
		zona = "";
		usuario= (UsuarioDTO) UtilSesion.getObjetoEnSesion(req, Constantes.USUARIO_SESION);
	}
	
	private void dtoAssembler(final LabelsDTO labels){
		labels.setCodCia(codCia);
		labels.setZona(zona);
	}
}
