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

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jqueryRotate.js"></script>
<script type="text/javascript" src="js/jquery.collapse.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>

<script type="text/javascript" src="js/ajax/ajaxManual.js"></script>
<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="js/consultasku/consultasSKU.js"></script>
</head>
<body onload="javascript:load();mostrarMensaje('${mensaje}');mostrarPaginas(${nroRegistros != null ? nroRegistros : 0},${registrosXPagina != null ? registrosXPagina : 0});" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Consultas / <span>Consulta SKU</span> </h2>
	</div>
	<form name="forma" method="post">
		<input type="hidden" id="sku" name="sku" value="" />
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
					<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
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
										<td colspan="8" class="titulos" align="center">${tituloPais}</td>
									</tr>
									<tr>
										<td colspan="8" class="titulos" align="center">${tituloSaldos}</td>
									</tr>
									<tr>
										<td height="15%" colspan="8">
										</td>
									</tr>
									<tr>
										<td class="titulosContenido" width="100" align="right">${tituloReferencia}</td>
										<td width="100">
											<input type="text" id="referencia" name="referencia" class="campo01" size="7" maxlength="7" title="Digite la referencia." tabindex="1" value="${referencia}" />
										</td>
										<td class="titulosContenido" width="100" align="right">${tituloColor}</td>
										<td width="100">
											<input type="text" id="color" name="color" class="campo00" size="3" maxlength="3" title="Digite el color." tabindex="2" value="${color}" />
										</td>
										<td class="titulosContenido" width="100" align="right">${tituloTalla}</td>
										<td width="100">
											<input type="text" id="talla" name="talla" class="campo00" size="3" maxlength="3" title="Digite la talla." tabindex="3" value="${talla}" />
										</td>
										<td class="titulosContenido" width="100" align="right">${tituloBodega}</td>
										<td>
											<select class="combo03" id="nomBodega" name="nomBodega" tabindex="4">
												<option value="">- Seleccione -</option>
												<c:forEach items="${bodegas}" var="bodega">
													<option value="${bodega.codigo}" ${bodega.codigo == codBodega ? "selected='selected'" : ""} >${bodega.codigo} - ${bodega.nombre}</option>
												</c:forEach>
											</select>
										</td>
									<tr>
									<tr>
										<td height="15%" colspan="8">
										</td>
									</tr>
									<tr>
										<td colspan="8" align="center">
											<input type="button" id="consultar" name="consultar" class="boton01" onclick="consultarReferencias();" value="${tituloConsultar}" tabindex="5" />
											<input type="button" id="limpiar" name="limpiar" class="boton01" onclick="limpiarCampos();" value="${tituloLimpiar}" tabindex="6" />
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
				<td height="15">
				</td>
			</tr>						
		</table>
		<c:if test="${mostrarResulset == 'S'}">
			<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="100%" colspan="2">
						<table width="100%" align="center">				
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
									        	<c:forEach items="${detalles}" var="detalle" varStatus="fila">
									        		<c:if test="${fila.count % registrosXPagina == 1}">
														<div id="pagina${fila.count}" style="display:none;">
										        			<table width="100%" class='sortable' border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
										        				<tr class="tituloTabla">
										        					<td>${tituloReferencia}</td>
										        					<td>${tituloDescripcion}</td>
										        					<td>${tituloLinea}</td>
										        					<td>${tituloUbicacion}</td>
										        					<td>${tituloCantidadD}</td>
										        					<td>${tituloCantidadP}</td>
										        					<td>${tituloCantidadA}</td>
										        					<td>${tituloDisponibleP}</td>
										        					<td>${tituloDisponibleA}</td>
										        					<td>${tituloCanPicking}</td>
										        					<td>${tituloCodigoBarras}</td>
										        					<td>&nbsp;</td>
										        				</tr>
										        	</c:if>
										        				<tr class="filaTabla">
																	<td class="contenido">
																		<a class="barraSupPrehome" style="cursor: pointer;" href="#" onclick="consultarAuditoria('${detalle.referencia}');">${!empty detalle.referencia ? detalle.referencia : "&nbsp;"}</a>
																	</td>
																	<td class="contenido">
																		<a class="barraSupPrehome" style="cursor: pointer;" href="#" onclick="consultarDescripcion('${detalle.referencia}');">${!empty detalle.descripcion ? detalle.descripcion : "&nbsp;"}</a>
																	</td>
																	<td class="contenido">${!empty detalle.lineaGrabacion ? detalle.lineaGrabacion : "&nbsp;"}</td>
																	<td class="contenido">${!empty detalle.ubicacion ? detalle.ubicacion : "&nbsp;"}</td>
																	<td class="contenido">${!empty detalle.cantidadDispo ? detalle.cantidadDispo : "&nbsp;"}</td>
																	<td class="contenido">${!empty detalle.cantidadPedi ? detalle.cantidadPedi : "&nbsp;"}</td>
																	<td class="contenido">${!empty detalle.cantidadAsig ? detalle.cantidadAsig : "&nbsp;"}</td>
																	<td class="contenido">${!empty detalle.disponiblePedir ? detalle.disponiblePedir : "&nbsp;"}</td>
																	<td class="contenido">${!empty detalle.disponibleAsig ? detalle.disponibleAsig : "&nbsp;"}</td>
																	<td class="contenido">${!empty detalle.cantidadPicking ? detalle.cantidadPicking : "&nbsp;"}</td>
																	<td class="contenido">${!empty detalle.codigoBarras ? detalle.codigoBarras : "&nbsp;"}</td>
																	<td align="center">
																		<a class="barraSupPrehome" style="cursor: pointer;" href="#" onclick="consultarPrecios('${detalle.referencia}');">${tituloPrecio}</a>
																	</td>
																</tr>
																<c:if test="${fila.count % registrosXPagina == 0 || fila.count == nroRegistros}">
										        			</table>
										        		</div>
										        				</c:if>
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
								</td>
							</tr>
							<tr>
								<td height="10">
								</td>
							</tr>
							<c:if test="${nroRegistros > registrosXPagina}">
								<tr>
									<td align="center" colspan="5">
										<div style="display: none;">
											<input type="text">										
										</div>
										<button class="btnAnt" type="button" onclick="paginaAnterior(${nroRegistros},${registrosXPagina})"></button>												
										<input type="text" name="txtNroPagina" id="txtNroPagina" onkeydown="irPagina(this, event,${nroRegistros},${registrosXPagina})" class="cajaPagina" />
										<button class="btnSig" type="button" onclick="paginaSiguiente(${nroRegistros},${registrosXPagina})"></button>
									</td>
								</tr>
								<tr>
									<td colspan="5">
										<div id="nroPaginas" class="contenido" align="center"></div>
									</td>
								</tr>
							</c:if>	
						</table>
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
