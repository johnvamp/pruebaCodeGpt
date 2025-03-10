<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<!-- <meta http-equiv="X-UA-Compatible" content="IE=8" charset="UTF-8"/> -->
<title>:: Portal Operaciones :: Leonisa S.A. ::</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>

<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="js/reprocesos/reprocesos.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/ajax/prototype.js"></script>
<script type="text/javascript" src="js/ajax/effects.js"></script>
</head>
<body onload="mostrarMensaje('${mensaje}');mostrarPaginas(${nroRegistros != null ? nroRegistros : 0},${registrosXPagina != null ? registrosXPagina : 0});" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Reprocesos / <span>Ejecuci�n Reproceso</span> </h2>
	</div>
	<form name="forma" method="post">
		<input type="hidden" id="numRequerimiento" name="numRequerimiento" value="" />
		<input type="hidden" id="fechaEntrega" name="fechaEntrega" value="" />
		<input type="hidden" id="tipoEntrega" name="tipoEntrega" value="" />
		<input type="hidden" id="observacion" name="observacion" value="" />
		<input type="hidden" id="accionTramite" name="accionTramite" value="" />
		<input type="hidden" id="accion" name="accion" value="" />
		<table align="center">
			<tr>
				<td align="center">
				 	<div id="mensaje" align="center" class="mensajeUsuario" style="display:none;"></div>
				</td>
			</tr>
		</table>
		<br>
		<table width="50%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%" align="center" class="titulos">Ejecuci�n Reproceso</td>
			</tr>
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
		        				<c:forEach items="${solicitudes}" var="solicitud" varStatus="fila">
									<c:if test="${fila.count % registrosXPagina == 1}">
										<div id="pagina${fila.count}" style="display:none;">
						        			<table width="100%" border="1" cellpadding="0" cellspacing="0" align="center">
						        				<tr class="tituloTabla">
						        					<td width="150">N�mero Requerimiento</td>
						        					<td>Solicitante</td>
						        					<td>Fecha Inicio</td>
						        					<td>Estado</td>
						        				</tr>
						        	</c:if>
						        				<tr class="filaTabla">
						        					<td>
						        						<a href="#" class="barraSupPrehome" style="cursor: pointer;"  onclick="verReproceso('${solicitud.numRequerimiento}','${solicitud.fechaEntrega}','${solicitud.tipoEntrega}','${solicitud.observacion}','P')" >${solicitud.numRequerimiento}</a>
						        					</td>
						        					<td>${solicitud.usuario}</td>
						        					<td>${solicitud.fechaInicio}</td>
						        					<td>${solicitud.estado != '' ? solicitud.estado : '&nbsp;'}</td>
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
						<tr>
							<td height="5"></td>
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
	    	<tr>
	    		<td height="5">
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