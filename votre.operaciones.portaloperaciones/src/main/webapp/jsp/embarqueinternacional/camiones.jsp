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
<script type="text/javascript" src="js/embarqueinternacional/camiones.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');mostrarPaginas(${nroRegistros != null ? nroRegistros : 0},${registrosXPagina != null ? registrosXPagina : 0});" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Embarque Internacional / <span>${titConsultas}</span> </h2>
	</div>
	<form name="forma" method="post">
		<table align="center">
			<tr>
				<td align="center">
				 	<div id="mensaje" align="center" class="mensajeUsuario" style="display:none;"></div>
				</td>
			</tr>
		</table>
		<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%" colspan="2">
					<table width="100%" align="center">
						<tr>
							<td width="100%" align="center" class="titulos">${titCamiones}</td>
						</tr>
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
								        	<c:forEach items="${registros}" var="camion" varStatus="fila">
												<c:if test="${fila.count % registrosXPagina == 1}">
													<div id="pagina${fila.count}" style="display:none;">
										        		<table width="100%" class='sortable' border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
															<tr class="tituloTabla">
																<td>${titEstado}</td>
																<td>${titTransportador}</td>
																<td>${titFecha}</td>
																<td>${titCamion}</td>
																<td>${titNumCajas}</td>
															</tr>
												</c:if>
															<tr class="filaTabla">
																<td class="contenido">${camion.estado}</td>
																<td class="contenido">${camion.transportador}</td>
																<td class="contenido">${camion.fecha }</td>
																<td class="contenido">${camion.camion}</td>
																<td class="contenido">${camion.numCajas}</td>
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
						<tr>
							<td width="100%" align="center" class="titulosContenido">${titTotal}</td>
						</tr>
						<tr>
							<td width="100%" align="center" class="titulosContenido">${titCamionesAbiertos}&nbsp;${camionesAbiertos}</td>
						</tr>
						<tr>
							<td width="100%" align="center" class="titulosContenido">${titCamionesPendientes}&nbsp;${camionesPendientes}</td>
						</tr>
						<tr>
							<td height="15">
							</td>
						</tr>
						<tr>
							<td align="center" width="100%">
								<input type="button" class="boton01" name="btnRegresar" id="btnRegresar" style="cursor: pointer;" onclick="regresar()" value="${titRegresar}" />
							</td>
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
