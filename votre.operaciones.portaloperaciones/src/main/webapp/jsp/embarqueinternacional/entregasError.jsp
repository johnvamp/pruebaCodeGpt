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
<script type="text/javascript" src="js/embarqueinternacional/entregarEmbarqueExcel.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');mostrarPaginas(${nroRegistros != null ? nroRegistros : 0},${registrosXPagina != null ? registrosXPagina : 0});" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Embarque Internacional / <span>Error</span> </h2>
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
							        	<c:if test="${!empty entregas}">
							        		<c:forEach var="orden" items="${entregas }" varStatus="fila">
							        			<c:if test="${fila.count % registrosXPagina == 1}">
							        				<div id="pagina${fila.count}" style="display:none;">
							        					<table>
															<tr>
																<td>
																	<div class="titulos" align="center">${tituloError}</div>
																</td>
															</tr>
												</c:if>
															<tr>
																<td class="contenido">
																	<div align="center" >${orden.descripcion }</div>
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
													<button type="button" class="boton01" onclick="regresar()">${boton}</button>
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
	</form>
	<table width="100%" border="0" align="center" cellpadding="3" cellspacing="0">
  		<tr>
    		<th><iframe id="pie2" name="pie" src="jsp/pie.html" scrolling="no" width="100%" height="40" frameborder="0"> Su navegador no reconoce iframes] </iframe></th>
  		</tr>
	</table>
</body>
</html>
