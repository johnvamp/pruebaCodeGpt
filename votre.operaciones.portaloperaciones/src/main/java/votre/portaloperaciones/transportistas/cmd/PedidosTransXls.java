package votre.portaloperaciones.transportistas.cmd;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import votre.portaloperaciones.transportistas.beans.TransportistasDTO;
import votre.portaloperaciones.transportistas.delegate.TransportistasBusiness;
import votre.portaloperaciones.util.Constantes;
import votre.utils.excel.write.Celda;
import votre.utils.excel.write.GeneradorExcel;
import votre.utils.excel.write.ReporteExcelDTO;
import votre.utils.moduloseguridad.beans.UsuarioDTO;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class PedidosTransXls  implements IBaseCmd{

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {

		TransportistasDTO transportistasDTO = new TransportistasDTO();
		TransportistasBusiness transportistasBusiness= new TransportistasBusiness();
		
		String pCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		UsuarioDTO pUsr = (UsuarioDTO) (req.getSession().getAttribute( Constantes.USUARIO_SESION) != null ? req.getSession().getAttribute( Constantes.USUARIO_SESION ) : "");
		
		String tipoExcel = req.getParameter("idTipoExcel") != null ? req.getParameter("idTipoExcel") : "";
		String codigoCampana =  req.getParameter("buscarCampana") != null ? req.getParameter("buscarCampana") : "";
		
		transportistasDTO.setpCia(pCia);
		transportistasDTO.setpEst(tipoExcel);
		transportistasDTO.setpUsr(pUsr.getUsuario());
		transportistasDTO.setNumeroZona("");
		
		transportistasDTO.setNumeroCampana(codigoCampana);	
		
		transportistasDTO.setpTipoConsult("");
		transportistasDTO.setValor("");
		
		ArrayList<TransportistasDTO> transportistas = new ArrayList<TransportistasDTO>();
		transportistas = transportistasBusiness.Transportistas(transportistasDTO);
		req.setAttribute("transportistas",  transportistas);
		
		String NomTranspo = transportistasDTO.getNomTranspo();
		String TotalPedi = transportistasDTO.getTotalPedi();
		
		req.setAttribute("NomTranspo",NomTranspo);
		req.setAttribute("TotalPedi", TotalPedi);	
		   
		String descripcion = "";
		String enviarExcel = "S";
		String lxcTitulo = "";
		
		if(tipoExcel.equals("E")){
			lxcTitulo = "Transportistas -- Pedidos Entregados";
		}
		
		if(tipoExcel.equals("P")){
			lxcTitulo = "Transportistas -- Pedidos Pendientes";
		}
		
		
		 if(enviarExcel != null && enviarExcel.equals("S")){	
			 ReporteExcelDTO excelDTO = new ReporteExcelDTO("Transportistas");
				ArrayList<ArrayList<String>> cabeceras = new ArrayList<ArrayList<String>>();
				ArrayList<List<String>> registros = new ArrayList<List<String>>();
				ArrayList<String> titulosArch = new ArrayList<String>();
				ArrayList<List<Celda>> pies = new ArrayList<List<Celda>>();
				
				
				//Cabeceras
				ArrayList<String> cabecera = new ArrayList<String>();
			
				cabecera = new ArrayList<String>();
				cabecera.add("Compa�ia: ");
				cabecera.add(pCia);			
				cabeceras.add(cabecera);
				
				cabecera = new ArrayList<String>();
				cabecera.add("NomTranspo: ");
				cabecera.add(NomTranspo);
				cabeceras.add(cabecera);
				
				
				cabecera = new ArrayList<String>();
				cabecera.add("TotalPedi: ");
				cabecera.add(TotalPedi);
				cabeceras.add(cabecera);
				
				cabecera = new ArrayList<String>();
				cabecera.add("");
				cabecera.add("");
				cabecera.add(lxcTitulo);
				cabecera.add(descripcion);
				cabeceras.add(cabecera);
				
				
				//Titulos			
				titulosArch.add("Campa�a");
				titulosArch.add("N�mero de Orde");
				titulosArch.add("Guia");
				titulosArch.add("C�dula");
				titulosArch.add("Nombre");
				titulosArch.add("Fecha Embarque");
				titulosArch.add("Hora Embarque");
				titulosArch.add("Primera Visita");
				titulosArch.add("Segunda Visita");	
				titulosArch.add("Tercera Visita");	
				titulosArch.add("Observaciones");
				
				titulosArch.add("Telefono 1");	
				titulosArch.add("Telefono 2");	
				titulosArch.add("Zona");
				titulosArch.add("Departamento");				
				titulosArch.add("Ciudad");
				titulosArch.add("Direccion");
				titulosArch.add("Numero Guia Mas.");
				titulosArch.add("Entrega Porteria");
				titulosArch.add("Valor");
				titulosArch.add("Fecha Entrega");
				titulosArch.add("Hora Entrega");
				
				//Registros				
				for (TransportistasDTO  transportistaDTO: transportistas){					
					List<String> columnas = new ArrayList<String>();
					columnas.add(transportistaDTO.getNumeroCampana());
					columnas.add(transportistaDTO.getNumeroOrden());
					columnas.add(transportistaDTO.getNumeroGuia());
					columnas.add(transportistaDTO.getCedula());
					columnas.add(transportistaDTO.getNombre());
					columnas.add(transportistaDTO.getFechaEmbarque());
					columnas.add(transportistaDTO.getHoraEmbarque());
					columnas.add(transportistaDTO.getPrimeraVisita());
					columnas.add(transportistaDTO.getSegundaVisita());
					columnas.add(transportistaDTO.getTerceraVisita());
					columnas.add(transportistaDTO.getObservaciones());
					columnas.add(transportistaDTO.getNumeroTel1());
					columnas.add(transportistaDTO.getNumeroTel2());
					columnas.add(transportistaDTO.getNumeroZona());
					columnas.add(transportistaDTO.getDepartamento());
					columnas.add(transportistaDTO.getCiudad());
					columnas.add(transportistaDTO.getDireccion());
					columnas.add(transportistaDTO.getNumeroGuiaMas());
					columnas.add(transportistaDTO.getPorte());
					columnas.add(transportistaDTO.getValor());
					columnas.add(transportistaDTO.getFecEnt());
					columnas.add(transportistaDTO.getHorEnt());
					registros.add(columnas);
				}

				excelDTO.setCabeceras(cabeceras);	
				excelDTO.setRegistros(registros);
				excelDTO.setTitulos(titulosArch);
				excelDTO.addPies(pies);
				
				GeneradorExcel generadorExcel = new GeneradorExcel();	    				
				HSSFWorkbook workbook = generadorExcel.generarInforme(excelDTO);

				String filenameXls = "";
				
				
				if(tipoExcel.equals("E")){
					filenameXls = "TransPedidosEntregados.xls";
				}
				
				if(tipoExcel.equals("P")){
					filenameXls = "TransPedidosPendientes.xls";
				}
				if(tipoExcel.equals("N")){
					filenameXls = "TransPedidosNoEntregados.xls";
				}
						
				
				
			 	try {
		    		res.setHeader("Content-Disposition", "attachment; filename="+filenameXls+"");
			        ServletOutputStream out = res.getOutputStream();
			        workbook.write(out);
			        out.flush();
			        out.close();	
		    	}catch (Exception e) {
		    
		    	}
				
		 }
		
	}

}
