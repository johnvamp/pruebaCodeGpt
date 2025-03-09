<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<title>:: Portal Operaciones :: Leonisa S.A. ::</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/ajax/prototype.js"></script>
<script type="text/javascript" src="js/ajax/effects.js"></script>
<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="js/consultasku/consultasSKU.js"></script>
</head>
<body onload="mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Consultas / <span>Descripción SKU</span> </h2>
	</div>
	<form name="forma" method="post">
		<input type="hidden" id="referencia" name="referencia" value="${referencia}" />
		<input type="hidden" id="color" name="color" value="${color}" />
		<input type="hidden" id="talla" name="talla" value="${talla}" />
		<input type="hidden" id="nomBodega" name="nomBodega" value="${codBodega}" />
		<table align="center">
			<tr>
				<td align="center">
				 	<div id="mensaje" align="center" class="mensajeUsuario" style="display:none;"></div>
				</td>
			</tr>
		</table>
		<table width="50%" border="0" cellpadding="0" cellspacing="0" align="center">									
			<tr>
				<td width="100%" align="center" colspan="2">
					<table width="400" border="0" align="center" cellpadding="0" cellspacing="0">
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
										<td width="50%" align="right" class="titulosContenido">${tituloReferencia}</td>
										<td width="50%" class="contenido">${referenciaSp}</td>
									</tr>
									<tr>
										<td width="50%" align="right" class="titulosContenido">${tituloDescRef}</td>
										<td width="50%" class="contenido">${descripcionRef}</td>
									</tr>
									<tr>
										<td width="50%" align="right" class="titulosContenido">${tituloVendor}</td>
										<td width="50%" class="contenido">${vendor}</td>
									</tr>
									<tr>
										<td width="50%" align="right" class="titulosContenido">${tituloMarca}</td>
										<td width="50%" class="contenido">${marca}</td>
									</tr>
									<tr>
										<td width="50%" align="right" class="titulosContenido">${tituloEstado}</td>
										<td width="50%" class="contenido">${estadoOp}</td>
									</tr>
									<tr>
										<td width="50%" align="right" class="titulosContenido">${tituloUen}</td>
										<td width="50%" class="contenido">${uen}</td>
									</tr>
									<tr>
										<td width="50%" align="right" class="titulosContenido">${tituloKit}</td>
										<td width="50%" class="contenido">${kit}</td>
									</tr>
									<tr>
										<td width="50%" align="right" class="titulosContenido">${tituloIndic}</td>
										<td width="50%" class="contenido">${indic}</td>
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
				<td height="15">
				</td>
			</tr>
			<tr>
				<td align="center">
					<input type="button" id="regresar" name="regresar" class="boton01" onclick="regresarTitulos();" value="REGRESAR" />
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
