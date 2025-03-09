<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=10" charset="UTF-8"/>
<title>:: Portal Operaciones :: Leonisa S.A. ::</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="js/ajax/prototype.js"></script>
<script type="text/javascript" src="js/ajax/effects.js"></script>

<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="js/pedidoscsl/pedidoscsl.js"></script>
<script type="text/javascript" src="js/ajax/ajaxManual.js"></script>

<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');">
	<div id="url">
		<h2>Pedidos CSL 33 / <span>Consulta Solicitud</span> </h2>
	</div>
	<form name="forma" method="post">
		<input type="hidden" id="ordenConsultar" name="ordenConsultar" value=""/>
		<input type="hidden" id="estrategia" name="estrategia" value="" />
		<input type="hidden" id="desPedido" name="desPedido" value="" />
		<input type="hidden" id="usuario" name="usuario" value="${datos.usuario}" />
		<input type="hidden" id="numOrden" name="numOrden" value="${datos.numOrden}" />
		<input type="hidden" id="cedula" name="cedula" value="${datos.cedula}" />
		<table align="center">
			<tr>
				<td align="center">
				 	<div id="mensaje" align="center" class="mensajeUsuario" style="display:none;"></div>
				</td>
			</tr>
		</table>
		<br>
		<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td height="15"></td>
			</tr>
			<tr>
				<td width="100%" align="center" class="titulos">Consulta Solicitudes</td>
			</tr>
			<c:if test="${datos.usuario != ''}">
				<tr>
					<td height="10"></td>
				</tr>
				<tr>
					<td width="100%" align="center" class="titulosContenido">Usuario: <span class="txLabel">${datos.usuario}</span></td>
				</tr>
			</c:if>
			<c:if test="${datos.numOrden != ''}">
				<tr>
					<td height="10"></td>
				</tr>
				<tr>
					<td width="100%" align="center" class="titulosContenido">Orden: <span class="txLabel">${datos.numOrden}</span></td>
				</tr>
			</c:if>
			<c:if test="${datos.cedula != ''}">
				<tr>
					<td height="10"></td>
				</tr>
				<tr>
					<td width="100%" align="center" class="titulosContenido">C�dula: <span class="txLabel">${datos.cedula}</span></td>
				</tr>
			</c:if>
			<tr>
				<td>
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  						<tr>
  							<td height="10">
  							</td>
  						</tr>
  						<tr>
					        <td class="caja01"></td>
					        <td class="caja02"></td>
					        <td class="caja03"></td>
					   	</tr>
					   	<tr>
					        <td class="caja04"></td>
					        <td align="center">
					        	<table width="100%" border="1" cellpadding="0" cellspacing="0" align="center">
					        		<thead>
						        		<tr class="tituloTabla">
						        			<td>Orden</td>
						        			<td>Tipo</td>
						        			<td>Estrategia</td>
						        			<td>Estado</td>
						        			<td>Cantidad</td>
						        		</tr>
						        	</thead>
						        	<tbody>
						        		<c:forEach items="${datos.listaPedidos}" var="pedido">
						        			<tr class="filaTabla">
						        				<td><a class="barraSupPrehome" style="cursor: pointer;" href="#" onclick="consultarDetalleSolicitud('${pedido.numeroOrden}','${pedido.estrategia}','${pedido.desPedido}');">${pedido.numeroOrden}</a></td>
						        				<td>${pedido.tipoPedido}</td>
						        				<td>${pedido.estrategia}</td>
						        				<td>${pedido.estado}</td>
						        				<td>${pedido.cantidad}</td>
						        			</tr>
						        		</c:forEach>
						        	</tbody>
					        	</table>
					        </td>
					   		<td class="caja05"></td>
						</tr>
						<tr>
					        <td class="caja06"></td>
					        <td class="caja07"></td>
					        <td class="caja08"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
	    		<td height="10">
	    		</td>
	    	</tr>
		</table>
		<table width="60%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td align="center">
					<input type="button" class="boton01" value="REGRESAR" onclick="regresarConsultar();"/>
				</td>
			</tr>
			<tr>
	    		<td height="10"></td>
	    	</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="3" cellspacing="0">
	  		<tr>
	    		<th><iframe id="pie2" name="pie" src="jsp/pie.html" scrolling="no" width="100%" height="40" frameborder="0"> Su navegador no reconoce iframes] </iframe></th>
	  		</tr>
		</table>
	</form>
</body>
</html>