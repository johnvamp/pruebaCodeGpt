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

$(document).ready(function() {
	$("#TableDatos").chromatable({
		
				width: "100%",
				height: "1200px",
				scrolling: "yes"
				
			});	
			

			
 }); 
</script>	


<body>
	<div id="url">
		<h2>Indicador de Pedidos \ Rango : ${idRango}</h2>
	</div>

<form name="form" method="post">

<input type="hidden" id="Max"  name="Max"  value="" /> 
<input type="hidden" id="Min"  name="Min"  value="" /> 
<input type="hidden" id="Pro"  name="Pro"  value="" /> 

<input type="hidden" id="idOpcSelectB"  name="idOpcSelectB"  value="" /> 
<input type="hidden" id="campanaTBox"  name="campanaTBox"  value="" /> 
<input type="hidden" id="zonaTBox"  name="zonaTBox"  value="" /> 
<input type="hidden" id="campanaTBoxZ"  name="campanaTBoxZ"  value="" /> 
<input type="hidden" id="campanaTBoxR"  name="campanaTBoxR"  value="${idCampanaR}" /> 
<input type="hidden" id="fechaInicialTBox"  name="fechaInicialTBox"  value="" /> 
<input type="hidden" id="fechaFinalTBox"  name="fechaFinalTBox"  value="" /> 
<input type="hidden" id="idEstadoB"  name="idEstadoB"  value="" /> 
<input type="hidden" id="idEstado"  name="idEstado"  value="" /> 
<input type="hidden" id="idRegion"  name="idRegion"  value="${idRegion}" /> 

<input type="hidden" id="idEstadoIni"  name="idEstadoIni"  value="" /> 
<input type="hidden" id="idEstadoFin"  name="idEstadoFin"  value="" /> 






<input type="hidden" id="FlujoWMS"  name="FlujoWMS"  value="" /> 


<c:if test="${menError== '1'}">

<table align="center" width="100%">
<tr>
<td align="center">
	<div id="url">
		<h2>Error en Indicador de Pedidos</h2>
	</div>
</td>
</tr>
</table>

</c:if>

<c:if test="${menError== '0'}">

<table align="center" width="100%">
<tr>
<td align="center">
<table>
 <tr>
   <td class="tituloGraficaFijos">
    Tiempo en ${tipoDatoRango1} del Estado ${estadoI1} hasta  ${estadoF1}
  </td>
</tr>
</table>
<img src="JFreeChartServlet?TipGra=4&TipGraRango=1"></img>
</td>




<td align="center">
<table>
 <tr>
   <td class="tituloGraficaFijos">
    Tiempo en ${tipoDatoRango2} del Estado ${estadoI2} hasta  ${estadoF2}
  </td>
</tr>
</table>
<img src="JFreeChartServlet?TipGra=4&TipGraRango=2"></img>
</td>
</tr>
</table>

	<div id="url">
	</div>



<table align="center" width="100%">
<tr>
<td align="center">
<table>
 <tr>
   <td class="tituloGraficaFijos">
    Tiempo en ${tipoDatoRango3} del Estado ${estadoI3} hasta  ${estadoF3}
  </td>
</tr>
</table>
<img src="JFreeChartServlet?TipGra=4&TipGraRango=3"></img>
</td>




<td align="center">
<table>
 <tr>
   <td class="tituloGraficaFijos">
    Tiempo en ${tipoDatoRango4} del Estado ${estadoI4} hasta  ${estadoF4}
  </td>
</tr>
</table>
<img src="JFreeChartServlet?TipGra=4&TipGraRango=4"></img>
</td>
</tr>
</table>

	<div id="url">
	</div>

<table align="center" width="100%">
<tr>
<td align="center">
<table>
 <tr>
   <td class="tituloGraficaFijos">
    Tiempo en ${tipoDatoRango5} del Estado ${estadoI5} hasta  ${estadoF5}
  </td>
</tr>
</table>
<img src="JFreeChartServlet?TipGra=4&TipGraRango=5"></img>
</td>




<td align="center">
<table>
 <tr>
   <td class="tituloGraficaFijos">
    Tiempo en ${tipoDatoRango6} del Estado ${estadoI6} hasta  ${estadoF6}
  </td>
</tr>
</table>
<img src="JFreeChartServlet?TipGra=4&TipGraRango=6"></img>
</td>
</tr>
</table>	

	<div id="url">
	</div>




<table align="center" width="100%">
<tr>
<td align="center">
<table>
 <tr>
   <td class="tituloGraficaFijos">
    Tiempo en ${tipoDatoRango7} del Estado ${estadoI7} hasta  ${estadoF7}
  </td>
</tr>
</table>
<img src="JFreeChartServlet?TipGra=4&TipGraRango=7"></img>
</td>




<td align="center">
<table>
 <tr>
   <td class="tituloGraficaFijos">
    Tiempo en ${tipoDatoRango8} del Estado ${estadoI8} hasta  ${estadoF8}
  </td>
</tr>
</table>
<img src="JFreeChartServlet?TipGra=4&TipGraRango=8"></img>
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


