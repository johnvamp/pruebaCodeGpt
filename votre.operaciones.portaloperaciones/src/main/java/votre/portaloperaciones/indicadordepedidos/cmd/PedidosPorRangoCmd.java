package votre.portaloperaciones.indicadordepedidos.cmd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import votre.portaloperaciones.indicadordepedidos.beans.PedidosPorRangoDTO;
import votre.portaloperaciones.indicadordepedidos.delegate.IndicadorPedidosBusiness;
import votre.portaloperaciones.util.Constantes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class PedidosPorRangoCmd implements IBaseCmd{

	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
	
		HttpSession sesion=req.getSession(true);
		
		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
		String idOpcSelect = (String) req.getSession().getAttribute("idOpcSelect");
		String idCampana = (String) req.getSession().getAttribute("idCampana");
		String idZona = (String) req.getSession().getAttribute("idZona");
		String idCampanaZ = (String) req.getSession().getAttribute("idCampanaZ");
		String idFechaInicial = (String) req.getSession().getAttribute("idFechaInicial");
		String idFechaFinal = (String) req.getSession().getAttribute("idFechaFinal");
		String idEstado = (String) req.getSession().getAttribute("idEstado");				
		String idEstadoInicial = (String) req.getSession().getAttribute("idEstadoIni");
		String idCampanaR = (String) req.getSession().getAttribute("idCampanaR");
		String idRegion = (String) req.getSession().getAttribute("idRegion");	
		String idEstadoFinal = (String) req.getSession().getAttribute("idEstadoFin");
		String tipoCompradora = (String) req.getSession().getAttribute("tipoCompradora");
		String campanaCompra = (String) req.getSession().getAttribute("campanaCompra");
		if(idEstado == null){
			idEstado = "";
		}
		String idRango = req.getParameter("idRango");
		
		PedidosPorRangoDTO pedidosPorRangoDTO = new PedidosPorRangoDTO();
		IndicadorPedidosBusiness indicadorPedidosBusiness;
		indicadorPedidosBusiness = new IndicadorPedidosBusiness();
		
		pedidosPorRangoDTO.setCodCia(codCia);
		if(idOpcSelect.equals("1")){
			pedidosPorRangoDTO.setAccion("F");
			pedidosPorRangoDTO.setEstadoIni(idEstadoInicial);
			pedidosPorRangoDTO.setEstadoFin(idEstadoFinal);
			pedidosPorRangoDTO.setDatoConsulta1(idFechaInicial);
			pedidosPorRangoDTO.setDatoConsulta2(idFechaFinal);	
			pedidosPorRangoDTO.setRango(idRango);
			pedidosPorRangoDTO.setDato8("");	
			
		}			
		if(idOpcSelect.equals("2")){
			pedidosPorRangoDTO.setAccion("Z");
			pedidosPorRangoDTO.setEstadoIni(idEstadoInicial);
			pedidosPorRangoDTO.setEstadoFin(idEstadoFinal);
			pedidosPorRangoDTO.setDatoConsulta1(idCampanaZ);
			pedidosPorRangoDTO.setDatoConsulta2(idZona);
			pedidosPorRangoDTO.setRango(idRango);
			pedidosPorRangoDTO.setDato8("");
		}			
		if(idOpcSelect.equals("3")){
			pedidosPorRangoDTO.setAccion("C");
			pedidosPorRangoDTO.setEstadoIni(idEstadoInicial);
			pedidosPorRangoDTO.setEstadoFin(idEstadoFinal);
			pedidosPorRangoDTO.setDatoConsulta1(idCampana);
			pedidosPorRangoDTO.setDatoConsulta2("");
			pedidosPorRangoDTO.setRango(idRango);
			pedidosPorRangoDTO.setDato8("");
		}			
		if(idOpcSelect.equals("4")){
			pedidosPorRangoDTO.setAccion("R");
			pedidosPorRangoDTO.setEstadoIni(idEstadoInicial);
			pedidosPorRangoDTO.setEstadoFin(idEstadoFinal);
			pedidosPorRangoDTO.setDatoConsulta1(idCampanaR);
			pedidosPorRangoDTO.setDatoConsulta2(idRegion);
			pedidosPorRangoDTO.setRango(idRango);
			pedidosPorRangoDTO.setDato8("");
		}
		if(idOpcSelect.equals("5")){
			pedidosPorRangoDTO.setAccion("V");
			pedidosPorRangoDTO.setEstadoIni(idEstadoInicial);
			pedidosPorRangoDTO.setEstadoFin(idEstadoFinal);
			pedidosPorRangoDTO.setDatoConsulta1(campanaCompra);
			pedidosPorRangoDTO.setDatoConsulta2(tipoCompradora);
			pedidosPorRangoDTO.setRango(idRango);
			pedidosPorRangoDTO.setDato8("");
		}
		
		ArrayList<PedidosPorRangoDTO> pedidosPorRango = new ArrayList<PedidosPorRangoDTO>();
		pedidosPorRango = indicadorPedidosBusiness.pedidosPorRango(pedidosPorRangoDTO);
		
		String tipoDato = "";
		String tipoRango = "";
		String estadoI = "";
		String estadoF = "";
		
		for(PedidosPorRangoDTO pedidosPorRangoDTOR : pedidosPorRango){
		   tipoRango = pedidosPorRangoDTOR.getDato19();
		   tipoDato = pedidosPorRangoDTOR.getDato17();
		   estadoI = pedidosPorRangoDTOR.getDato16();
		   estadoF = pedidosPorRangoDTOR.getDato18();
		   
		   if(tipoRango.equals("1")){
			   req.setAttribute("tipoDatoRango1",tipoDato);
	           req.setAttribute("estadoI1",estadoI);
	           req.setAttribute("estadoF1",estadoF);
           }
		   if(tipoRango.equals("2")){
			   req.setAttribute("tipoDatoRango2",tipoDato);
	           req.setAttribute("estadoI2",estadoI);
	           req.setAttribute("estadoF2",estadoF);
           }
           if(tipoRango.equals("3")){
	           req.setAttribute("tipoDatoRango3",tipoDato);
	           req.setAttribute("estadoI3",estadoI);
	           req.setAttribute("estadoF3",estadoF);
           }
           if(tipoRango.equals("4")){
	           req.setAttribute("tipoDatoRango4",tipoDato);
	           req.setAttribute("estadoI4",estadoI);
	           req.setAttribute("estadoF4",estadoF);
           }
           if(tipoRango.equals("5")){
	           req.setAttribute("tipoDatoRango5",tipoDato);
	           req.setAttribute("estadoI5",estadoI);
	           req.setAttribute("estadoF5",estadoF);
           }
           if(tipoRango.equals("6")) {
	           req.setAttribute("tipoDatoRango6",tipoDato);
	           req.setAttribute("estadoI6",estadoI);
	           req.setAttribute("estadoF6",estadoF);
           }
           if(tipoRango.equals("7")){
	           req.setAttribute("tipoDatoRango7",tipoDato);
	           req.setAttribute("estadoI7",estadoI);
	           req.setAttribute("estadoF7",estadoF);
           }
           if(tipoRango.equals("8")){
	           req.setAttribute("tipoDatoRango8",tipoDato);
	           req.setAttribute("estadoI8",estadoI);
	           req.setAttribute("estadoF8",estadoF);
           }
		}
		sesion.setAttribute("pedidosPorRango",pedidosPorRango);
		String menError = pedidosPorRangoDTO.getDato9();
		req.setAttribute("menError",menError);
		req.setAttribute("idRango",idRango);
	}
}
