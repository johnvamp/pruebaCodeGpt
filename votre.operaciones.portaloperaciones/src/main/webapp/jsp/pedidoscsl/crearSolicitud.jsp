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

<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');">
	<div id="url">
		<h2>Pedidos CSL 33 / <span>Crear Solicitud</span> </h2>
	</div>
	<form name="forma" method="post">
		<input type="hidden" id="tipoPedido" name="tipoPedido" value="" />
		<input type="hidden" id="desPedido" name="desPedido" value="" />
		<input type="hidden" id="destinatario" name="destinatario" value="" />
		<input type="hidden" id="accion" name="accion" value="${accion}">
		<table align="center">
			<tr>
				<td align="center">
				 	<div id="mensaje" align="center" class="mensajeUsuario" style="display:none;"></div>
				</td>
			</tr>
		</table>
		<br>
		<table width="60%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%" align="center" colspan="2">
					<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
					        <td class="cajaGris01"><img src="imagenes/spacer.gif" alt="img" width="9" height="9" /></td>
					        <td class="bgcajaGris"></td>
					        <td class="cajaGris02"><img src="imagenes/spacer.gif" alt="img" width="9" height="9" /></td>
					    </tr>
					    <tr>
					        <td class="bgcajaGris">&nbsp;</td>
					        <td class="bgcajaGris">
					        	<table width="750" height="81" border="0" cellpadding="0" cellspacing="0" align="center">
					        		<tr>
										<td colspan="4" width="100%" align="center" class="titulos">Crear Solicitud</td>
									</tr>
									<tr>
										<td height="15" colspan="4">
										</td>
									</tr>
									<tr>
										<td align="right">
											<label class="txLabel">Seleccione Tipo de Pedido:</label>
										</td>
										<td>
											<select id="pedido" name="pedido" class="selects">
												<option value="">Seleccione</option>
												<c:forEach items="${datos.listaPedidos}" var="lista">
													<option value="${lista.tipoPedido}|${lista.desPedido}|${lista.destinatario}">${lista.desPedido}</option>
												</c:forEach>
											</select>
										</td>
										<td >
											<label class="txLabel">Descripción Estrategia:</label>
										</td>
										<td>
											<input type="text" class="campo03" id="desEstrategia" name="desEstrategia"/>
										</td>
									</tr>
				                    <tr>
				                    	<td height="20">
				                    	</td>
				                    </tr>
				                    <tr>
				                    	<td colspan="4" align="center">
				                    		<input type="button" class="boton01"  value="CREAR PEDIDO INDIVIDUAL" onclick="crearSolicitud('I');" />
				                    		<input type="button" class="boton01"  value="IMPORTAR BASE EXCEL" onclick="crearSolicitud('E');" />
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
	    	<tr>
	    		<td height="10">
	    		</td>
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