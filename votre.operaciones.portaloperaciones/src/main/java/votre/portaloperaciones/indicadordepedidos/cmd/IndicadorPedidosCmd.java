package votre.portaloperaciones.indicadordepedidos.cmd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import votre.portaloperaciones.indicadordepedidos.beans.IndicadorPedidosDTO;
import votre.portaloperaciones.indicadordepedidos.delegate.IndicadorPedidosBusiness;
import votre.portaloperaciones.util.Constantes;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;

public class IndicadorPedidosCmd implements IBaseCmd{

	public void execute(HttpServletRequest req, HttpServletResponse res) throws PersonalsoftException {
		
		HttpSession sesion=req.getSession(true);
		String codCia = req.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)req.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
								
		String idOpcSelect = req.getParameter("idOpcSelect");
		String idCampana = req.getParameter("campanaTBox");
		String idZona = req.getParameter("zonaTBox");
		String idCampanaZ = req.getParameter("campanaTBoxZ");
		String idCampanaR = req.getParameter("campanaTBoxR");		
		String idFechaInicial = req.getParameter("fechaInicialTBox");
		String idFechaFinal = req.getParameter("fechaFinalTBox");
		String tipoCompradora = req.getParameter("idCompradoraSelect");
		String campanaCompra = req.getParameter("campanaTBoxC");
		String idEstado = req.getParameter("idEstadoB");
		String idRegion = req.getParameter("idRegionSelect");
		String idEstadoInicial = req.getParameter("idEstadoInicial");
		String idDepartamento = req.getParameter("idDepartamentoSelect");
		String idCiudad = req.getParameter("idCiudadSelect");
		if(idEstadoInicial.equals("TODOS")){
			idEstadoInicial = "";
		}
		String idEstadoFinal = req.getParameter("idEstadoFinal");
		if(idEstadoFinal.equals("TODOS")){
			idEstadoFinal = "";
		}
		
		sesion.setAttribute("idOpcSelect",idOpcSelect);
		sesion.setAttribute("idCampana",idCampana);
		sesion.setAttribute("idZona",idZona);
		sesion.setAttribute("idCampanaZ",idCampanaZ);
		sesion.setAttribute("idCampanaR",idCampanaR);
		sesion.setAttribute("idFechaInicial",idFechaInicial);
		sesion.setAttribute("idFechaFinal",idFechaFinal);
		sesion.setAttribute("idRegion",idRegion);
		sesion.setAttribute("idEstado",idEstado);
		sesion.setAttribute("idEstadoIni",idEstadoInicial);
		sesion.setAttribute("idEstadoFin",idEstadoFinal);
		sesion.setAttribute("tipoCompradora",tipoCompradora);
		sesion.setAttribute("campanaCompra",campanaCompra);
		
		IndicadorPedidosDTO indicadorPedidosDTO = new IndicadorPedidosDTO();
		IndicadorPedidosBusiness indicadorPedidosBusiness;
		indicadorPedidosBusiness = new IndicadorPedidosBusiness();

		indicadorPedidosDTO.setCodCia(codCia);
		
		if(idOpcSelect != null){
			if(idOpcSelect.equals("1")){
				indicadorPedidosDTO.setAccion("F");
				indicadorPedidosDTO.setEstadoIni(idEstadoInicial);
				indicadorPedidosDTO.setEstadoFin(idEstadoFinal);
				indicadorPedidosDTO.setDatoConsulta1(idFechaInicial);
				indicadorPedidosDTO.setDatoConsulta2(idFechaFinal);	
				indicadorPedidosDTO.setDato6("");
			}
			if(idOpcSelect.equals("2")){
				indicadorPedidosDTO.setAccion("Z");
				indicadorPedidosDTO.setEstadoIni(idEstadoInicial);
				indicadorPedidosDTO.setEstadoFin(idEstadoFinal);
				indicadorPedidosDTO.setDatoConsulta1(idCampanaZ);
				indicadorPedidosDTO.setDatoConsulta2(idZona);
				indicadorPedidosDTO.setDato6("");
			}
			if(idOpcSelect.equals("3")){
				indicadorPedidosDTO.setAccion("C");
				indicadorPedidosDTO.setEstadoIni(idEstadoInicial);
				indicadorPedidosDTO.setEstadoFin(idEstadoFinal);
				indicadorPedidosDTO.setDatoConsulta1(idCampana);
				indicadorPedidosDTO.setDatoConsulta2("");
				indicadorPedidosDTO.setDato6("");
			}
			if(idOpcSelect.equals("4")){
				indicadorPedidosDTO.setAccion("R");
				indicadorPedidosDTO.setEstadoIni(idEstadoInicial);
				indicadorPedidosDTO.setEstadoFin(idEstadoFinal);
				indicadorPedidosDTO.setDatoConsulta1(idCampanaR);
				indicadorPedidosDTO.setDatoConsulta2(idRegion);
				indicadorPedidosDTO.setDato6("");
			}
			if(idOpcSelect.equals("5")){
				indicadorPedidosDTO.setAccion("V");
				indicadorPedidosDTO.setEstadoIni(idEstadoInicial);
				indicadorPedidosDTO.setEstadoFin(idEstadoFinal);
				indicadorPedidosDTO.setDatoConsulta1(campanaCompra);
				indicadorPedidosDTO.setDatoConsulta2(tipoCompradora);
				indicadorPedidosDTO.setDato6("");
			}
			
			if(idOpcSelect.equals("6")){
				indicadorPedidosDTO.setAccion("D");
				indicadorPedidosDTO.setEstadoIni(idEstadoInicial);
				indicadorPedidosDTO.setEstadoFin(idEstadoFinal);
				indicadorPedidosDTO.setDatoConsulta1(idCampana);
				indicadorPedidosDTO.setDatoConsulta2(idDepartamento + idCiudad);
				indicadorPedidosDTO.setDato6("");
			}
			
			ArrayList<IndicadorPedidosDTO>  indicadorPedidosGrafica = new ArrayList<IndicadorPedidosDTO>();
			indicadorPedidosGrafica = indicadorPedidosBusiness.IndicadorPedidosGraf1(indicadorPedidosDTO);
			req.setAttribute("indicadorPedidos", indicadorPedidosGrafica);		
			sesion.setAttribute("indicadorPedidosEstado", indicadorPedidosGrafica);
		
			String menError = indicadorPedidosDTO.getDato9();
			String menErrorPantalla = indicadorPedidosDTO.getDato35();
			
			if(menError.equals("0")){
				Double idMed = Double.parseDouble(indicadorPedidosDTO.getDato30());
				Double idMax = Double.parseDouble(indicadorPedidosDTO.getDato8());		
				Double idMin = Double.parseDouble(indicadorPedidosDTO.getDato7());
				Double idPro = Double.parseDouble(indicadorPedidosDTO.getDato6());
				String tipoDato = indicadorPedidosDTO.getDato33();
				String TotalPedidos = indicadorPedidosDTO.getDato38();	
		
				int dato1i =  (int)Math.round(idPro);
				int dato2i =  (int)Math.round(idMin);	
				int dato3i =  (int)Math.round(idMax);
				int dato4i =  (int)Math.round(idMed);
		
				dato1i = Math.round(dato1i);
				dato2i = Math.round(dato2i);
				dato3i = Math.round(dato3i);
				dato4i = Math.round(dato4i);
		
				req.setAttribute("TotalPedidos",TotalPedidos);	
				req.setAttribute("idOpcSelect",idOpcSelect);
				req.setAttribute("idCampana",idCampana);
				req.setAttribute("idZona",idZona);
				req.setAttribute("idCampanaZ",idCampanaZ);
				req.setAttribute("idCampanaR",idCampanaR);
				req.setAttribute("idFechaInicial",idFechaInicial);
				req.setAttribute("idFechaFinal",idFechaFinal);
				req.setAttribute("idEstado",idEstado);	
				req.setAttribute("idRegion",idRegion);
				req.setAttribute("datoTipoDato",tipoDato);
				req.setAttribute("datoProm",dato1i);
				req.setAttribute("datoMin", dato2i);
				req.setAttribute("datoMax", dato3i);
				req.setAttribute("datoMed", dato4i);
			}
			req.setAttribute("menError",menError);
			req.setAttribute("menErrorPantalla",menErrorPantalla);
		}
	}
}