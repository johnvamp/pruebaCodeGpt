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

<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="js/pedidoscsl/pedidoscsl.js"></script>

<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');mostrarPaginas(${nroRegistros != null ? nroRegistros : 0},${registrosXPagina != null ? registrosXPagina : 0});" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Pedidos CSL 33 / <span>Crear Solicitud / Error Excel</span> </h2>
	</div>
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
				        	<p class="txLabelCentro">${mensajeError}</p>
				        	<table align="center">
				        		<tr>
				        			<td>
							        	<c:if test="${!empty datos}">
							        		<c:forEach var="dato" items="${datos }" varStatus="fila">
							        			<c:if test="${fila.count % registrosXPagina == 1}">
							        				<div id="pagina${fila.count}" style="display:none;">
							        					<table>
															<tr>
																<c:if test="${!errorArchivo}">
																	<td>
																		<div class="titulos" align="center">Destinatario</div>
																	</td>
																</c:if>
																<td>
																	<div class="titulos" align="center">${tituloError}</div>
																</td>
															</tr>
												</c:if>
															<tr>
																<c:if test="${!errorArchivo}">
																	<td class="contenido">
																		<div align="center" >${dato.val1}</div>
																	</td>
																</c:if>
																<td class="contenido">
																	<div align="center" >${dato.spDes }</div>
																</td>
															</tr>
															<c:if test="${fila.count % registrosXPagina == 0 || fila.count == nroRegistros}">
														</table>
							        				</div>
							        						</c:if>
							        		</c:forEach>
							       	</td>
							  	</tr>
							        		<c:if test="${nroRegistros > registrosXPagina}">
												<tr>
													<td align="center" colspan="5">
														<button class="btnAnt" type="button" onclick="paginaAnterior(${nroRegistros},${registrosXPagina})"></button>
														<input type="text" name="txtNroPagina" id="txtNroPagina" onkeydown="irPagina(this, event,${nroRegistros},${registrosXPagina})" class="cajaPagina" id="cajaPagina">
														<button class="btnSig" type="button" onclick="paginaSiguiente(${nroRegistros},${registrosXPagina})"></button>
													</td>
												</tr>
												<tr>
													<td colspan="3">
														<div class="contenido" id="nroPaginas" align="center"></div>
													</td>
												</tr>
											</c:if>
											<tr>
												<td align="center" colspan="2" >
													<button type="button" class="boton01" onclick="regresarImportarExcel()">REGRESAR</button>
												</td>
											</tr>
							        	</c:if>
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
	<form name="forma" method="post">
		<input type="hidden" id="accion" name="accion" value="${accion}" />
		<input type="hidden" id="tipoPedido" name="tipoPedido" value="${tipoPedido}" />
		<input type="hidden" id="desPedido" name="desPedido" value="${desPedido}" />
		<input type="hidden" id="destinatario" name="destinatario" value="${destinatario}" />
		<input type="hidden" id="desEstrategia" name="desEstrategia" value="${desEstrategia}" />
	</form>
	<table width="100%" border="0" align="center" cellpadding="3" cellspacing="0">
  		<tr>
    		<th><iframe id="pie2" name="pie" src="jsp/pie.html" scrolling="no" width="100%" height="40" frameborder="0"> Su navegador no reconoce iframes] </iframe></th>
  		</tr>
	</table>
</body>
</html>
