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
<script type="text/javascript" src="js/embarqueinternacional/entregarEmbarque.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Embarque Internacional / <span>${titEntrega}</span> </h2>
	</div>
	<form name="forma" method="post">
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
					        				<input type="radio" id="parcial" name="accion" value="P" /><span class="titulosContenido">${titParcial}</span>
					        				<input type="radio" id="total" name="accion" value="T" checked="checked" /><span class="titulosContenido">${titTotal}</span>
					        			</td>
					        		</tr>
					        		<tr>
										<td align="center" colspan="3">
											<input type="button" class="boton01" name="btnAceptar" id="btnAceptar" style="cursor: pointer;" onclick="validarDatos();" value="${titAceptar}" />
											<input type="button" class="boton01" name="btnRegresar" id="btnRegresar" style="cursor: pointer;" onclick="regresar()" value="${titRegresar}" />
											<input type="button" class="boton01" name="btnReporte" id="btnReporte" style="cursor: pointer;" onclick="verEntregaExcel();" value="ENTREGA POR XLS" />
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
	<ajax:select baseUrl="entrega.consultarCamionesEntrega.do" source="transportador"
	target="camion" parameters="codTrans={transportador}" emptyOptionValue=""
	emptyOptionName="-Seleccione-" />
</body>
</html>
