package votre.portaloperaciones.pedidoscsl.cmd;

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

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;
import votre.portaloperaciones.embarqueinternacional.entrega.beans.EntregaDTO;
import votre.portaloperaciones.pedidoscsl.beans.PedidosCSLDTO;
import votre.portaloperaciones.pedidoscsl.facade.PedidosCSLFacade;
import votre.portaloperaciones.util.Constantes;
import votre.utils.excel.read.Celda;
import votre.utils.excel.read.LectorExcel;

public class ImportarExcelCmd implements IBaseCmd {
	
	private Logger logger = Logger.getLogger(this.getClass());
	PersonalsoftException ps = null;
	private String codCia;
	private String accion;
	private String tipoPedido;
	private String desPedido;
	private String destinatario;
	private String desEstrategia;
	private byte [] contenidoArchivo = null;
	private ArrayList<PedidosCSLDTO> arrPedidos = null;
	private String mensaje;

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		ArrayList<PedidosCSLDTO> resultado = new ArrayList<PedidosCSLDTO>();
		ArrayList<PedidosCSLDTO> errores = new ArrayList<PedidosCSLDTO>();
		String siguienteRuta = "pedidoscsl.jspListaPedidosExcel.do";
		boolean banderaErrorArchivo = false;
		try{
			obtenerDatos(req);
			if(!validarDatos()){
				req.setAttribute("mensaje", mensaje);
				siguienteRuta = "pedidoscsl.verImportarExcel.do";
				Configuracion.getInstance().getContext().getRequestDispatcher(siguienteRuta).forward(req, res);
				return;
			}
			leerExcel();
			resultado = validar();
			if(resultado.size() > 0){
				for (PedidosCSLDTO pedidosCSLDTO : resultado) {
					if(pedidosCSLDTO.getSpSts().equals(Constantes.ERROR_ARCHIVO_EXCEL)){
						banderaErrorArchivo = true;
						errores.add(pedidosCSLDTO);
					}
					else if(pedidosCSLDTO.getSpSts().equals(Constantes.ERROR_SP)){
						errores.add(pedidosCSLDTO);
					}
				}
			}
			else{
				req.setAttribute("mensaje", mensaje);
				siguienteRuta = "pedidoscsl.verImportarExcel.do";
			}
			
			if(errores.size() > 0 ){
				req.setAttribute("mensajeError", "Se encontraron " + errores.size()  + " errores al cargar el archivo.");
				req.setAttribute("tituloError", "Dato Invalido");
				req.setAttribute("datos", errores);
				req.setAttribute("nroRegistros", resultado.size());
				req.setAttribute("registrosXPagina", Constantes.REGISTROSXPAGINA_DATOS_ERROR);
				int ultimaPagina = 0;
				for (int i = 1; i <= resultado.size(); i = i + Constantes.REGISTROSXPAGINA_DATOS_ERROR){
					ultimaPagina++;
				}
				req.setAttribute("ultimaPag", ultimaPagina);
				siguienteRuta = "pedidoscsl.jspErrorExcel.do";
			}
			else{
				req.setAttribute("pedidos", resultado);
				req.setAttribute("nroRegistros", resultado.size());
				
				//Se consultan las opciones del Combo dependiendo el Destinatario
				if(destinatario.equals(Constantes.DESTINATARIO_REGION) || destinatario.equals(Constantes.DESTINATARIO_LIDER)
						|| destinatario.equals(Constantes.DESTINATARIO_COMANDO)){
					PedidosCSLFacade pedidosCSLFacade = new PedidosCSLFacade();
					PedidosCSLDTO dto = new PedidosCSLDTO();
					String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";;
					dto.setCodCia(codCia);
					if(destinatario.equals(Constantes.DESTINATARIO_REGION)){
						dto.setAccion(Constantes.ACCION_REGIONES);
					}
					else if(destinatario.equals(Constantes.DESTINATARIO_LIDER)){
						dto.setAccion(Constantes.ACCION_ZONA);
					}
					else if(destinatario.equals(Constantes.DESTINATARIO_COMANDO)){
						dto.setAccion(Constantes.ACCION_COMANDO);
					}
					
					dto = pedidosCSLFacade.listarOpciones(dto);
					if(dto != null){
						if(dto.getSpSts().equals(Constantes.EXITO_SP)){
							if(destinatario.equals(Constantes.DESTINATARIO_COMANDO)){
								req.setAttribute("arrComandos", dto.getArrPedidos());
							}
							else{
								req.setAttribute("arrCombo", dto.getArrDatosCombo());
							}
						}
					}
				}
			}
			req.setAttribute("errorArchivo", banderaErrorArchivo);
			req.setAttribute("tipoPedido", tipoPedido);
			req.setAttribute("desPedido", desPedido);
			req.setAttribute("destinatario", destinatario);
			req.setAttribute("desEstrategia", desEstrategia);
			req.setAttribute("accion", accion);
			
			Configuracion.getInstance().getContext().getRequestDispatcher(siguienteRuta).forward(req, res);
		}
		catch (Exception e) {
			if (e instanceof IOException){
				mensaje = "Error en el reenvio a pagina de error";
			}
			if (e instanceof FileUploadException){
				mensaje = CargadorMsj.getInstance().getMensaje("FILE_UPLOAD");
			}
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
				if (item.isFormField()){
					String campo = item.getFieldName();
					if(campo.trim().equals("accion")){
						accion = item.getString();
						continue;
					}
					if(campo.trim().equals("tipoPedido")){
						tipoPedido = item.getString();
						continue;
					}
					if(campo.trim().equals("desPedido")){
						desPedido = item.getString();
						continue;
					}
					if(campo.trim().equals("destinatario")){
						destinatario = item.getString();
						continue;
					}
					if(campo.trim().equals("desEstrategia")){
						desEstrategia = item.getString();
						continue;
					}
				}
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
		if (contenidoArchivo == null){
			mensaje = "Error en el archivo";
			return false;
		}
		return true;
	}
	
	private void leerExcel() throws PersonalsoftException{
		ArrayList<Celda> columnas = new ArrayList<Celda>();
		//DESTINATARIO
		columnas.add(new Celda<String>("", 20));
		//REFERENCIA
		columnas.add(new Celda<String>("", 7));
		//COLOR
		columnas.add(new Celda<String>("", 3));
		//TALLA
		columnas.add(new Celda<String>("", 3));
		//CANTIDAD
		columnas.add(new Celda<String>("", 5));
		//CENTRO_DE_COSTOS
		columnas.add(new Celda<String>("", 5));
		
		LectorExcel lectorExcel = null;
		try {
			lectorExcel = new LectorExcel(contenidoArchivo, columnas);
			mensaje = lectorExcel.validarArchivo();
		}
		catch (Exception e) {
			mensaje = "Formato de archivo invalido";
			PedidosCSLDTO error = new PedidosCSLDTO();
			error.setSpSts(Constantes.ERROR_ARCHIVO_EXCEL);
			error.setSpDes(mensaje);
			
			arrPedidos = new ArrayList<PedidosCSLDTO>();
			arrPedidos.add(error);
		}
		
		if(mensaje.equals("")){
			ArrayList<ArrayList<Celda>> filasPedidos = lectorExcel.leerArchivo( lectorExcel.getNroHojas() );
			arrPedidos = new ArrayList<PedidosCSLDTO>();
			if (filasPedidos.isEmpty()){
				mensaje = "Se generaron problemas al importar los pedidos, verifique el contenido del archivo";
			}
			else if (filasPedidos.get(0).size() < columnas.size() && filasPedidos.get(0).get(0).getLength() == -1) {
				for (ArrayList<Celda> fila : filasPedidos) {
					for (Celda columna : fila) {
						PedidosCSLDTO pedido = new PedidosCSLDTO();
						pedido.setSpSts( Constantes.ERROR_EXCEL );
						pedido.setSpDes( (String)columna.getValor() + "." + (columna.getErrorDato() != null ? columna.getErrorDato() : ""));
						arrPedidos.add(pedido);
					}
				}
				return;
			}
			
			for (ArrayList<Celda> fila : filasPedidos) {
				PedidosCSLDTO pedidos = new PedidosCSLDTO();
				pedidos.setVal1((String)fila.get(0).getValor().toString());
				pedidos.setReferencia((String)fila.get(1).getValor().toString());
				pedidos.setColor((String)fila.get(2).getValor().toString());
				pedidos.setTalla((String)fila.get(3).getValor().toString());
				pedidos.setCantidad((String)fila.get(4).getValor().toString());
				pedidos.setCentroCostos((String)fila.get(5).getValor().toString());
				
				arrPedidos.add(pedidos);
			}
		}
	}
	
	private ArrayList<PedidosCSLDTO> validar(){
		ArrayList<PedidosCSLDTO> retorno = new ArrayList<PedidosCSLDTO>();
		PedidosCSLFacade pedidosCSLFacade = new PedidosCSLFacade();
		try{
			if(mensaje.equals("")){
				for (PedidosCSLDTO pedidosCSLDTO : arrPedidos) {
					int relleno;
					StringBuffer refe = new StringBuffer(pedidosCSLDTO.getReferencia());
					if(refe.length()>=7){
						refe = new StringBuffer(refe.substring(0, 7));
					}
					else{
						relleno = 7 - refe.length();
						for (int i = 0; i < relleno; i++) {
							refe.append(" ");
						}
					}
					
					StringBuffer color = new StringBuffer(pedidosCSLDTO.getColor());
					pedidosCSLDTO.setColor(color.toString());
					if(color.length()>=3){
						color = new StringBuffer(color.substring(0, 3));
					}
					else{
						relleno = 3 - color.length();
						for (int i = 0; i < relleno; i++) {
							color.insert(0, " ");
						}
					}
					
					StringBuffer talla = new StringBuffer(pedidosCSLDTO.getTalla());
					pedidosCSLDTO.setTalla(talla.toString());
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
					
					pedidosCSLDTO.setCodCia(codCia);
					pedidosCSLDTO.setDestinatario(destinatario);
					pedidosCSLDTO.setSku(sku.toString());
					
					pedidosCSLDTO = pedidosCSLFacade.validarSolictud(pedidosCSLDTO);
					retorno.add(pedidosCSLDTO);
				}
			}
			else{
				PedidosCSLDTO error = new PedidosCSLDTO();
				error.setSpSts(Constantes.ERROR_ARCHIVO_EXCEL);
				error.setSpDes(mensaje);
				
				retorno.add(error);
			}
		}
		catch (Exception e) {
			PedidosCSLDTO error = new PedidosCSLDTO();
			error.setSpSts(Constantes.ERROR_ARCHIVO_EXCEL);
			error.setSpDes(mensaje);
			
			retorno.add(error);
		}
		
		return retorno;
	}
}
