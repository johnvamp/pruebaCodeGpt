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
<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="js/embarqueinternacional/cerrarEmbarque.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Embarque Internacional / <span>${titPantalla}</span> </h2>
	</div>
	<form name="forma" method="post">
		<input type="hidden" id="nomAccion" name="nomAccion" value="" />
		<input type="hidden" id="codTransportador" name="codTransportador" value="${codTransportador}" /> 
		<input type="hidden" id="fecha" name="fecha" value="${fecha}" />
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
					        				<input type="text" id="txtTransportador" name="txtTransportador" readonly="readonly" class="campo02" value="${transportador}" />
					        			</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right">${titCamion}</td>
					        			<td>
					        				<input type="text" id="txtCamion" name="txtCamion" readonly="readonly" class="campo02" value="${camion}" />
					        			</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right">${titTotal}</td>
					        			<td>
					        				<input type="text" id="txtTotal" name="txtTotal" readonly="readonly" class="campo02" value="${total}" />
					        			</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right">${titCerrar}</td>
					        			<td>
					        				<select id="respuesta" name="respuesta" class="combo03">
					        					<option ${accion == 'cerrarEmbarque' ? "value='S'" : "value='Si'"}>Si</option>
					        					<option ${accion == 'cerrarEmbarque' ? "value='N'" : "value='No'"}>No</option>
					        				</select>
					        			</td>
					        		</tr>
					        		<tr>
										<td align="center" colspan="2">
											<input type="button" class="boton01" name="btnAceptar" id="btnAceptar" style="cursor: pointer;" onclick="cerrarCamion('${accion}');" value="${titAceptar}" />
											<input type="button" class="boton01" name="btnFinalizar" id="btnFinalizar" style="cursor: pointer;" onclick="regresar('${accion}');" value="${titRegresar}" />
										</td>
									</tr>
					        	</table>
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
</body>
</html>
