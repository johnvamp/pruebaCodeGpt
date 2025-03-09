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
		<h2>Pedidos CSL 33 / <span>Crear Solicitud / Pedidos Archivo Excel</span> </h2>
	</div>
	<form name="forma" method="post" enctype="multipart/form-data">
		<input type="hidden" id="accion" name="accion" value="${accion}" />
		<input type="hidden" id="tipoPedido" name="tipoPedido" value="${tipoPedido}" />
		<input type="hidden" id="desPedido" name="desPedido" value="${desPedido}" />
		<input type="hidden" id="destinatario" name="destinatario" value="${destinatario}" />
		<input type="hidden" id="desEstrategia" name="desEstrategia" value="${desEstrategia}" />
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
										<td width="100%" align="center" class="titulos">Creación Pedido Excel</td>
									</tr>
									<tr><td height="10"></td></tr>
									<tr>
										<td align="center">
											<label class="titulosContenido">Tipo de Pedido:</label><label class="txLabel"> ${desPedido}</label>
										</td>
									</tr>
									<tr>
										<td align="center">
											<label class="titulosContenido">Estratregia:</label><label class="txLabel"> ${desEstrategia}</label>
										</td>
									</tr>
									<tr><td height="5"></td></tr>
									<tr>													
										<td align="right">
        									<a class="barraSupPrehome" href="plantillaCSL/Plantilla Pedidos CSL.xls" >Descargar Plantilla</a>
        								</td>
									</tr>
									<tr><td height="5"></td></tr>
									<tr>
										<td align="center">
											<label class="titulosContenido">Seleccione la ruta donde se encuentre el archivo que desea importar.</label>
										</td>
									</tr>
									<tr><td height="5"></td></tr>
									<tr>
										<td align="center">								
											<input name="archivo" id="archivo" type="file" style="cursor: pointer;">
										</td>
									</tr>
									<tr><td height="10"></td></tr>
									<tr>
										<td align="center">
											<input type="button" onclick="importarExcel();" value="IMPORTAR" class="boton01">
											<input type="button" onclick="regresarListaPedidos();" value="REGRESAR" class="boton01">
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