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
<script type="text/javascript" src="js/embarqueinternacional/detalle.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<c:choose>
		<c:when test="${accion == 'cerrarEmbarque'}">
			<div id="url">
				<h2>Embarque Internacional / <span>${titCerrarCamion}</span> </h2>
			</div>
		</c:when>
		<c:otherwise>
			<div id="url">
				<h2>Embarque Internacional / <span>${titReimpresion}</span> </h2>
			</div>
		</c:otherwise>
	</c:choose>
	<form name="forma" method="post">
		<input type="hidden" id="codTransportador" name="codTransportador" value="${codTransportador}" />
		<input type="hidden" id="camion" name="camion" value="${camion}" />
		<input type="hidden" id="fecha" name="fecha" value="${fecha}" />
		<input type="hidden" id="nomAccion" name="nomAccion" value="${accion}" />
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
					<c:if test="${estado == '0'}">
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
											<td align="center" class="titulosContenido">${titCompraDirecta}</td>
										</tr>
										<tr>
											<td align="center" class="contenido">${titRelacion}</td>
										</tr>
										<tr>
											<td align="center" class="titulosContenido">${titTransportador}:&nbsp;${transportador}</td>
										</tr>
										<tr>
											<td align="center" class="titulosContenido">${titCamion}</td>
										</tr>
										<tr>
											<td align="center" class="titulosContenido">${titFecha}</td>
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
			<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="100%" colspan="2">
						<table width="100%" align="center">
							<tr>
								<td height="15">
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		    							<tr>
									        <td class="caja01"></td>
									        <td class="caja02"></td>
									        <td class="caja03"></td>
									   	</tr>
									   	<tr>
									        <td class="caja04"></td>
									        <td>
									        	<div class="page">
										    	<table width="100%" class='sortable' border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
													<tr class="tituloTabla2">
														<td>${titObservacion}</td>
														<td>${titNroOrden}</td>
														<td>${titZona}</td>
														<td>${titCedula}</td>
														<td>${titNombre}</td>
														<td>${titDireccion}</td>
														<td>${titTelefono}</td>
														<td>${titDestino}</td>
														<td>${titVdeclarado}</td>
														<td>${titCampana}</td>
														<td>${titPorteria}</td>
														<td>${titTelefono2}</td>
														<td>${titCelular}</td>
														<td>${titDistrito}</td>
														<td>${titCanton}</td>
														<td>${titProvincia}</td>
														<td>${titValorOrden}</td>
													</tr>
													<c:forEach items="${registros}" var="detalle" varStatus="fila">
														<tr>
															<td class="contenido2">${!empty detalle.observacion ? detalle.observacion : "&nbsp;"}</td>
															<td class="contenido2">${!empty detalle.numOrden ? detalle.numOrden : "&nbsp;"}</td>
															<td class="contenido2">${!empty detalle.zona ? detalle.zona : "&nbsp;"}</td>
															<td class="contenido2">${!empty detalle.cedula ? detalle.cedula : "&nbsp;"}</td>
															<td class="contenido2">${!empty detalle.nombre ? detalle.nombre : "&nbsp;"}</td>
															<td class="contenido2">${!empty detalle.direccion ? detalle.direccion : "&nbsp;"}</td>
															<td class="contenido2">${!empty detalle.telefono ? detalle.telefono : "&nbsp;"}</td>
															<td class="contenido2">${!empty detalle.destino ? detalle.destino : "&nbsp;"}</td>
															<td class="contenido2">${!empty detalle.valorDeclarado ? detalle.valorDeclarado : "&nbsp;"}</td>
															<td class="contenido2">${!empty detalle.campana ? detalle.campana : "&nbsp;"}</td>
															<td class="contenido2">${!empty detalle.porteria ? detalle.porteria : "&nbsp;"}</td>
															<td class="contenido2">${!empty detalle.telefono2 ? detalle.telefono2 : "&nbsp;"}</td>
															<td class="contenido2">${!empty detalle.celular ? detalle.celular : "&nbsp;"}</td>
															<td class="contenido2">${!empty detalle.distrito ? detalle.distrito : "&nbsp;"}</td>
															<td class="contenido2">${!empty detalle.canton ? detalle.canton : "&nbsp;"}</td>
															<td class="contenido2">${!empty detalle.provincia ? detalle.provincia : "&nbsp;"}</td>
															<td class="contenido2">${!empty detalle.valorOrden ? detalle.valorOrden : "&nbsp;"}</td>
														</tr>
													</c:forEach>
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
							<tr>
								<td width="100%" align="center" class="titulosContenido">${titTotal}</td>
							</tr>
							<tr>
								<td align="center" colspan="3">
									<input type="button" class="boton01" name="btnExcel" id="btnExcel" style="cursor: pointer;" onclick="generarExcel();" value="ENVIAR A EXCEL" />
									<input type="button" class="boton01" name="btnImprimir" id="btnImprimir" style="cursor: pointer;" onclick="imprimir();" value="IMPRIMIR" />
									<input type="button" class="boton01" name="btnRegresar" id="btnRegresar" style="cursor: pointer;" onclick="regresar('${accion}')" value="${titRegresar}" />
								</td>
							</tr>
						</table>
					</c:if>
					<c:if test="${estado == '1'}">
						<tr>
							<td height="10">
							</td>
						</tr>
						<tr>
							<td align="center">
								<input type="button" class="boton01" name="btnRegresar" id="btnRegresar" style="cursor: pointer;" onclick="regresar('${accion}')" value="REGRESAR" />
							</td>
						</tr>
					</c:if>
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
