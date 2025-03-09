package votre.portaloperaciones.embarqueinternacional.entrega.cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import votre.portaloperaciones.embarqueinternacional.entrega.beans.EntregaDTO;
import votre.portaloperaciones.embarqueinternacional.entrega.delegate.EntregaBusiness;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;
import votre.utils.excel.read.Celda;
import votre.utils.excel2.read.LectorExcel;

public class ImportarEntregaCmd implements IBaseCmd {

	private Logger logger = Logger.getLogger(this.getClass());
	private String mensaje;
	private String codCia;
	private byte [] contenidoArchivo = null;
	private ArrayList<EntregaDTO> entregas = null;
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		String siguienteRuta = "embarque.cargarTitulos.do";
		EntregaDTO retorno = new EntregaDTO();
		ArrayList<EntregaDTO> resultado = new ArrayList<EntregaDTO>();
		try{
			obtenerDatos(req);
			if(!validarDatos()){
				req.setAttribute("mensaje", mensaje);
				siguienteRuta = "entrega.jspEntregaExcel.do";
				Configuracion.getInstance().getContext().getRequestDispatcher(siguienteRuta).forward(req, res);
				return;
			}
			leerExcel();
			resultado = importar();
			retorno = resultado.get(0);
			
			if(retorno != null){
				if(retorno.getEstado().equals(Constantes.EXITO_SP) || retorno.getEstado().equals("")){
					req.setAttribute("mensaje", "Se procesaron "+ retorno.getCantidadProcesada() + " registros.");
				}
				else{
					mensaje = retorno.getDescripcion();
				}
			}
			
			if (mensaje != null && !mensaje.equals("")) {
				if (resultado.size() > 0 ){
					if (!resultado.get(0).getEstado().equals( Constantes.ERROR_ARCHIVO_EXCEL )) {
						if (!resultado.get(0).getEstado().equals( Constantes.ERROR_SP )){
							req.setAttribute("mensajeError", "Se encontraron " + resultado.size() + " errores al importar los participantes");
							req.setAttribute("boton", "Regresar");
							req.setAttribute("tituloError", "Dato Invalido");
							req.setAttribute("entregas", resultado);
							req.setAttribute("nroRegistros", resultado.size());
							req.setAttribute("registrosXPagina", Constantes.REGISTROSXPAGINA_);
							
							siguienteRuta = "entrega.jspEntregasError.do";
						}
						else{
							req.setAttribute("mensaje", mensaje);
							siguienteRuta = "entrega.jspEntregaExcel.do";
						}
					}
					else {
						req.setAttribute("mensaje", mensaje);
						siguienteRuta = "entrega.jspEntregaExcel.do";
					}
				}				
			}
			
			Configuracion.getInstance().getContext().getRequestDispatcher(siguienteRuta).forward(req, res);
		}
		catch (Exception e) {
			if (e instanceof IOException)
				mensaje = "Error forwarding to error page.";
			if (e instanceof FileUploadException)
				mensaje = CargadorMsj.getInstance().getMensaje("FILE_UPLOAD");
			PersonalsoftException exception = new PersonalsoftException(e);
			logger.error(exception.getTraza());
		}
	}
	
	private void obtenerDatos(HttpServletRequest req) throws FileUploadException{
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		
		if(isMultipart)	{

			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List fileItems = upload.parseRequest(req);
			Iterator iter = fileItems.iterator();		
			
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (!item.isFormField()){
					String fileName = item.getName();
					if (fileName != null && !fileName.equals("")){
						String contentType = item.getContentType();
						byte[] data = item.get();
						if((contentType.equals("application/vnd.ms-excel") || contentType.equals("application/octet-stream") || contentType.equals("application/ms-excel")) && ((fileName.substring(fileName.lastIndexOf(".")).toLowerCase().equals(".xls")))){
							//|| (fileName.substring(fileName.lastIndexOf(".")).toLowerCase().equals(".xlsx")) || (fileName.substring(fileName.lastIndexOf(".")).toLowerCase().equals(".ods")))){
							contenidoArchivo = data;
						}
						else{
							mensaje = CargadorMsj.getInstance().getMensaje("ARCHIVO_NOEXCEL");
						}
					}
				}
			}
		}
		else{
			mensaje = "Formulario Incorrecto";
		}
	}
	
	private boolean validarDatos(){
		if (mensaje != null && !mensaje.equals(""))
			return false;
		if (contenidoArchivo == null){
			mensaje = "Error en el archivo";
			return false;
		}
		return true;
	}
	
	private void leerExcel() throws PersonalsoftException{
		ArrayList<Celda> columnas = new ArrayList<Celda>();
//		Transportista	Orden	Estado	Fecha
		columnas.add(new Celda<String>("", 1));
		columnas.add(new Celda<String>("", 11));
		columnas.add(new Celda<String>("", 3));
		columnas.add(new Celda<String>("", 8));
		
		LectorExcel lectorExcel = null;
		try {
			lectorExcel = new LectorExcel(contenidoArchivo, columnas);
			mensaje = lectorExcel.validarArchivo();
		}
		catch (Exception e) {
			mensaje = "Formato de archivo invalido";
			EntregaDTO error = new EntregaDTO();
			error.setEstado( Constantes.ERROR_ARCHIVO_EXCEL );
			error.setDescripcion( "Formato de archivo invalido" );
			
			entregas = new ArrayList<EntregaDTO>();
			entregas.add(error);
		}
		
		if(mensaje.equals("")){
			ArrayList<ArrayList<Celda>> filasEntrega = lectorExcel.leerArchivo(lectorExcel.getNroHojas());
			entregas = new ArrayList<EntregaDTO>();
			
			if (filasEntrega.isEmpty()){
				mensaje = "Se generaron problemas al cargar los datos, verifique el contenido del archivo";
			}
			else if (filasEntrega.get(0).size() < columnas.size() && filasEntrega.get(0).get(0).getLength() == -1) {
				for (ArrayList<Celda> fila : filasEntrega){
					for (Celda columna : fila) {
						EntregaDTO entregaDTO = new EntregaDTO();
						entregaDTO.setEstado(Constantes.ERROR_EXCEL);
						entregaDTO.setDescripcion((String)columna.getValor() + "." + (columna.getErrorDato() != null ? columna.getErrorDato() : ""));
						entregas.add(entregaDTO);
					}
				}
				return;
			}
			for (ArrayList<Celda> fila : filasEntrega){
				EntregaDTO entregaDTO = new EntregaDTO();
				entregaDTO.setCodCia(codCia);
				entregaDTO.setCodTransportador((String)fila.get(0).getValor().toString());
				entregaDTO.setNumOrden((String)fila.get(1).getValor().toString());
				entregaDTO.setCausal((String)fila.get(2).getValor().toString());
				entregaDTO.setFecha((String)fila.get(3).getValor().toString());
				entregaDTO.setEstado(Constantes.EXITO_EXCEL);
				
				entregas.add(entregaDTO);
			}
		}
	}
	
	private ArrayList<EntregaDTO> importar() {
		ArrayList<EntregaDTO> retorno = new ArrayList<EntregaDTO>();
		EntregaDTO entregaDTO = new EntregaDTO();
		try{
			EntregaBusiness entregaBusiness = new EntregaBusiness();
			if(mensaje.equals("")){
				if(entregas != null){
					if(entregas.get(0).getEstado().equals(Constantes.EXITO_EXCEL)){
						entregaDTO = entregaBusiness.procesarExcel(entregas);
						retorno.add(entregaDTO);
					}
					else{
						return entregas;
					}
				}
			}
			else{
				EntregaDTO error = new EntregaDTO();
				error.setEstado(Constantes.ERROR_ARCHIVO_EXCEL);
				error.setDescripcion(mensaje);
				
				retorno.add(error);
			}
		}
		catch (Exception e) {
			EntregaDTO error = new EntregaDTO();
			error.setEstado(Constantes.ERROR_ARCHIVO_EXCEL);
			error.setDescripcion(mensaje);
			
			retorno.add(error);
		}
		
		return retorno;
	}
}
