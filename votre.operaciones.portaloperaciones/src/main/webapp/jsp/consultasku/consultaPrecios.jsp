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
		<h2>Consultas / <span>Precios SKU</span> </h2>
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
		<table width="70%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%" colspan="2">
					<table width="100%" align="center">				
						<tr>
							<td>
								<c:if test="${estado == '0'}">
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		    							<tr>
									        <td class="caja01"></td>
									        <td class="caja02"></td>
									        <td class="caja03"></td>
									   	</tr>
									   	<tr>
									        <td class="caja04"></td>
									        <td>
									        	<c:forEach items="${detalles}" var="detalle" varStatus="fila">
									        		<table width="100%" class='sortable' border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
											        	<tr class="tituloTabla">
											        		<td>${tituloCampana}</td>
											        		<td>${tituloCustomer}</td>
											        		<td>${tituloCantidad}</td>
											        		<td>${tituloPrecio}</td>
											        	</tr>
											        	<tr class="filaTabla">
															<td class="contenido">${detalle.campana}</td>
															<td class="contenido">${!empty detalle.customerClass ? detalle.customerClass : "&nbsp;"}</td>
															<td class="contenido">${detalle.cantidad}</td>
															<td class="contenido">${detalle.precio}</td>
														</tr>
													</table>
									        	</c:forEach>
									      	</td>
											<td class="caja05"></td>
										</tr>
										<tr>
									        <td class="caja06"></td>
									        <td class="caja07"></td>
									        <td class="caja08"></td>
										</tr>
									</table>
								</c:if>
							</td>
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
					<input type="button" id="regresar" name="regresar" class="boton01" onclick="regresarTitulos();" value="${tituloRegresar}" />
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
