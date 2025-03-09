<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<title>:: Portal Operaciones :: Leonisa S.A. ::</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="js/ajax/prototype.js"></script>
<script type="text/javascript" src="js/ajax/effects.js"></script>

<link rel="stylesheet" href="css/estilos.css" type="text/css" />
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>

<script type="text/javascript"  src="js/jquery.js"></script>
<script type="text/javascript"  src="js/jqueryRotate.js"></script>
<script type="text/javascript"  src="js/jquery.collapse.js"></script>
<script type="text/javascript"  src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript"  src="js/jquery.blockUI.js"></script>
<script type="text/javascript"  src="js/utilidades.js"></script>
<script type="text/javascript"  src="js/indicadordepedidos/indipedi.js"></script>
<script type="text/javascript" src="js/jquery.chromatable.js"></script>





<script type="text/javascript">
jQuery(document).ready(function() {
	jQuery("#TableDatos").chromatable({		
				width: "1120px",
				height: "1200px",
				scrolling: "yes"				
			});				
 }); 
</script>	


<body onload="">
	<div id="url">
		<h2>Indicador de Pedidos</h2>
	</div>

<form name="form" method="post">

<input type="hidden" id="Max"  name="Max"  value="" /> 
<input type="hidden" id="Min"  name="Min"  value="" /> 
<input type="hidden" id="Pro"  name="Pro"  value="" /> 

<input type="hidden" id="idOpcSelectB"  name="idOpcSelectB"  value="" /> 
<input type="hidden" id="campanaTBox"  name="campanaTBox"  value="" /> 
<input type="hidden" id="zonaTBox"  name="zonaTBox"  value="" /> 
<input type="hidden" id="campanaTBoxZ"  name="campanaTBoxZ"  value="" /> 
<input type="hidden" id="campanaTBoxR"  name="campanaTBoxR"  value="" /> 
<input type="hidden" id="fechaInicialTBox"  name="fechaInicialTBox"  value="" /> 
<input type="hidden" id="fechaFinalTBox"  name="fechaFinalTBox"  value="" /> 
<input type="hidden" id="idEstadoB"  name="idEstadoB"  value="" /> 
<input type="hidden" id="idEstado"  name="idEstado"  value="0" /> 
<input type="hidden" id="idRegion"  name="idRegion"  value="${idRegion}" /> 

<input type="hidden" id="idEstadoIni"  name="idEstadoIni"  value="" /> 
<input type="hidden" id="idEstadoFin"  name="idEstadoFin"  value="" /> 

<input type="hidden" id="idRango"  name="idRango"  value="" />






<input type="hidden" id="FlujoWMS"  name="FlujoWMS"  value="" /> 


<c:if test="${menError== '1'}">

<table align="center" width="100%">
<tr>
<td align="center">
	<div id="url">
		<h2>Error en Indicador de Pedidos --- ${menErrorPantalla}</h2>
	</div>
	
</td>



</tr>
</table>

</c:if>

<c:if test="${menError== '0'}">

<table align="center" width="100%">
<tr>
<td align="center">
<img src="JFreeChartServlet?TipGra=2"></img>
</td>

<td>
			<table>
   			 <thead>
   			 <tr>   			 
   			   <th class="tituloTablaFijos" width="10%">Total Pedidos</th>
	           <th class="tituloTablaFijos" width="10%">${TotalPedidos}</th>   			 
   			 </tr>
	          <tr>	           
	      	   <th class="tituloTablaFijos" width="10%">${datoTipoDato}</th>
	           <th class="tituloTablaFijos" width="10%">% de Pedidos</th>	                     
	          </tr>
	           </thead>
	           </table>
	       <table>
	         <c:forEach items="${indicadorPedidos}" var="obtenido" varStatus="status">

 			<c:if test="${obtenido.col1Graf1 != null}"> 
 				<c:if test="${obtenido.dato36 != null}"> 
 					<c:if test="${obtenido.dato37 != null}"> 
	 				 <tr class="txLabel">				         
				      	 <td class="txLabel" width="10%"><a href="javascript:pedidosPorRango('${obtenido.dato36}')">${obtenido.col1Graf1}</a></td>
						<td  width="10%">${obtenido.dato37}</td>
				      </tr> 
					</c:if>
				</c:if>
			</c:if>
	           
	          </c:forEach>	          
	     </table>  


</td>

<td align="center">


       <table>                
                    <tr>
                        <td class="tituloGraficaFijos">
                            Minimo</td>
                        <td class="txLabel">
                            ${datoMin}</td>
                        <td class="tituloGraficaFijos">
                            Promedio</td>
                        <td class="txLabel">
                            ${datoProm}</td>
                        <td class="tituloGraficaFijos">
                            Maximo</td>
                        <td class="txLabel">
                            ${datoMax}</td>
                        <td class="tituloGraficaFijos">
                            Media</td>
                        <td class="txLabel">
                            ${datoMed}</td>
                    </tr>
                </table>

<img src="JFreeChartServlet?Max=${datoMax}&Min=${datoMin}&Pro=${datoProm}&Med=${datoMed}&TipGra=1"></img>


</td>
</tr>
</table>





	<div id="url">
	</div>
	
	<table width="100%"  align="center"> 
	<tr  align="center">
		<td><a class="boton01" href="javascript:generarExcelIndipedi('1')">Exportar a Excel</a></td>	
	</tr>
	</table>
<div id="url">
	</div>
	
	
    <table>
        <tr>
            <td valign="top">
               <c:forEach items="${indicadorPedidos}" var="obtenido" varStatus="status">
				 <c:if test="${obtenido.dato10 != null}"> 
		
				 <c:if test="${obtenido.dato11 > 0}"> 
				 	<c:if test="${obtenido.dato13 > 0}"> 
		
		
				   <table>
		             <tr>
			             <td class="tituloGraficaFijos">
			             Tiempo en ${obtenido.dato31} del Estado ${obtenido.dato10} hasta  ${obtenido.dato32}
			             </td>
		             </tr>
		            </table>
				 	
             <table>          
               
                    <tr>
                        <td class="tituloGraficaFijos">
                            Estado</td>
                        <td class="txLabel">                        
                        <a href="javascript:buscarIndicadorEstado('${obtenido.dato10}')">${obtenido.dato10}</a></td>
          		        <td class="tituloGraficaFijos">
                            Minimo</td>
                        <td class="txLabel">
                            ${obtenido.dato12}</td>
                        <td class="tituloGraficaFijos">
                            Promedio</td>
                        <td class="txLabel">
                            ${obtenido.dato13}</td>
                        <td class="tituloGraficaFijos">
                            Maximo</td>
                        <td class="txLabel">
                            ${obtenido.dato11}</td>
                    </tr>
                </table>
         
                <table>
                    <tr>
                        <td>
                            <img src="JFreeChartServlet?Max=${obtenido.dato11}&Min=${obtenido.dato12}&Pro=${obtenido.dato13}&TipGra=3"></img>
       
                        
                        </td>
                    </tr>
                </table>
               </c:if>  
            </c:if> 
  			</c:if> 
		 </c:forEach>  
            </td>
            <td valign="top">
                <table  width="1120px">
   			 <thead>
	          <tr>
	           
	      	   <th class="tituloTablaFijos" width="5%">Orden</th>
	           <th class="tituloTablaFijos" width="1%">Estado</th>	
	           <th class="tituloTablaFijos" width="4%">Pickt</th>	 	           	
	           <th class="tituloTablaFijos" width="5%">Fecha</th>		                   
	           <th class="tituloTablaFijos" width="4%">Hora</th>	
	           <th class="tituloTablaFijos" width="4%">Cedula</th>	
	           <th class="tituloTablaFijos" width="7%">Nombre</th>	
	           <th class="tituloTablaFijos" width="4%">Proceso</th>	
	           <th class="tituloTablaFijos" width="4%">Tipo Compradora</th>	
	           <th class="tituloTablaFijos" width="5%">Transpo</th>	
	           <th class="tituloTablaFijos" width="3%">Zona</th>	
		       <th class="tituloTablaFijos" width="5%">Campaña</th>	
	  	       <th class="tituloTablaFijos" width="3%">MP</th>	
	           <th class="tituloTablaFijos" width="6%">Region</th>	
	 		   <th class="tituloTablaFijos" width="3%">Tiempo</th>	
	                     
	          </tr>
	           </thead>
	           </table>
	          
	       <table id="TableDatos"  width="100%">
	         <c:forEach items="${indicadorPedidos}" var="obtenido" varStatus="status">

 			<c:if test="${obtenido.dato15 != null}"> 
			 <tr class="gallery">
	          	<td  width="5%" align="center">${obtenido.dato15}</td>
				<td  width="2%" align="center">${obtenido.dato16}</td>
				<td  width="3%" align="center">${obtenido.dato17}</td>
				<td  width="3%" align="center">${obtenido.dato18}</td>
				<td  width="3%" align="center">${obtenido.dato19}</td>
				<td  width="4%" align="center">${obtenido.dato20}</td>
				<td  width="6%" align="center">${obtenido.dato21}</td>
				<td  width="5%" align="center">${obtenido.dato22}</td>
				<td  width="5%" align="center">${obtenido.dato23}</td>
				<td  width="5%" align="center">${obtenido.dato24}</td>
				<td  width="3%" align="center">${obtenido.dato25}</td>
				<td  width="5%" align="center">${obtenido.dato26}</td>
				<td  width="3%" align="center">${obtenido.dato27}</td>
				<td  width="6%" align="center">${obtenido.dato28}</td>	
				<td  width="3%" align="center">${obtenido.dato29}</td>				
	          </tr> 
			</c:if>
	           
	          </c:forEach>	          
	     </table>  
	     </td>	     
        </tr>
    </table>	

</c:if>

</form>
	<table width="100%" border="0" align="center" cellpadding="3" cellspacing="0">
  		<tr>
    		<th><iframe id="pie2" name="pie" src="jsp/pie.html" scrolling="no" width="100%" height="40" frameborder="0"> Su navegador no reconoce iframes] </iframe></th>
  		</tr>
	</table>
</body>
</html>


