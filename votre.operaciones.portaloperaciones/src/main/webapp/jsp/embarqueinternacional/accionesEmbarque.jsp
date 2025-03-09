<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<html>
<head>
<title>:: Portal Operaciones :: Leonisa S.A. ::</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/ajax/prototype.js"></script>
<script type="text/javascript" src="js/ajax/effects.js"></script>
<script type="text/javascript" src="js/ajax/controls.js"></script>
<script type="text/javascript" src="js/ajax/ajaxtags.js"></script>
<script type="text/javascript" src="js/ajax/ajaxtags_controls.js"></script>
<script type="text/javascript" src="js/ajax/ajaxtags_parser.js"></script>
<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="js/embarqueinternacional/accionesEmbarque.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<c:choose>
		<c:when test="${accion == 'capturarEmbarque'}">
			<div id="url">
				<h2>Embarque Internacional / <span>Capturar Embarque</span> </h2>
			</div>
		</c:when>
		<c:when test="${accion == 'cerrarEmbarque'}">
			<div id="url">
				<h2>Embarque Internacional / <span>Cerrar Embarque</span> </h2>
			</div>
		</c:when>
		<c:otherwise>
			<div id="url">
				<h2>Embarque Internacional / <span>Desembarque</span> </h2>
			</div>
		</c:otherwise>
	</c:choose>
	<form name="forma" method="post">
		<input type="hidden" id="accion" name="accion" value="${accion}" />
		<table align="center">
			<tr>
				<td align="center">
				 	<div id="mensaje" align="center" class="mensajeUsuario" style="display:none;"></div>
				</td>
			</tr>
		</table>
		<table width="60%" border="0" cellpadding="0" cellspacing="0" align="center">
			<tr>
				<td width="100%" align="center" colspan="2">
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
					        <td class="cajaGris01"><img src="imagenes/spacer.gif" alt="img" width="9" height="9" /></td>
					        <td class="bgcajaGris"></td>
					        <td class="cajaGris02"><img src="imagenes/spacer.gif" alt="img" width="9" height="9" /></td>
					    </tr>
					    <tr>
					        <td class="bgcajaGris">&nbsp;</td>
					        <td class="bgcajaGris">
					        	<table width="100%" border="0" cellspacing="1" cellpadding="2">
					        		<tr>
					        			<td class="titulosContenido" align="right" width="40%">${titTransportador}</td>
					        			<td width="50%">
					        				<select id="transportador" name="transportador" class="combo03">
					        					<option value="0" >-Seleccione-</option>						
												<c:forEach items="${transportadores}" var="trans">
													<option value="${trans.codigo}" >${trans.nombre}</option>
												</c:forEach>
					        				</select>
					        			</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right">${titCamion}</td>
					        			<td>
					        				<select id="camion" name="camion" class="combo03">
					        					<option value="0" >-Seleccione-</option>
					        					<c:forEach items="${camiones}" var="cami">
													<option value="${cami.valorConcatenado}" >${cami.camion}</option>
												</c:forEach>
					        				</select>
					        			</td>
					        		</tr>
					        		<tr>
										<td align="center" colspan="2">
											<input type="button" class="boton01" name="btnAceptar" id="btnAceptar" style="cursor: pointer;" onclick="validarEmbarque();" value="${titAceptar}" />
											<input type="button" class="boton01" name="btnRegresar" id="btnRegresar" style="cursor: pointer;" onclick="regresar()" value="${titRegresar}" />
										</td>
									</tr>
					        	</table>
					        </td>
					   		<td class="bgcajaGris">&nbsp;</td>
						</tr>
						<tr>
					        <td class="cajaGris03"></td>
					        <td class="bgcajaGris"></td>
					        <td class="cajaGris04"></td>
		     			</tr>
		     		</table>
		     	</td>
			</tr>
		</table>
	</form>
	<table width="100%" border="0" align="center" cellpadding="3" cellspacing="0">
  		<tr>
    		<th><iframe id="pie2" name="pie" src="jsp/pie.html" scrolling="no" width="100%" height="40" frameborder="0"> Su navegador no reconoce iframes] </iframe></th>
  		</tr>
	</table>
	<ajax:select baseUrl="transportador.consultarCamiones.do" source="transportador"
	target="camion" parameters="codTrans={transportador}" emptyOptionValue=""
	emptyOptionName="-Seleccione-" />
</body>
</html>
