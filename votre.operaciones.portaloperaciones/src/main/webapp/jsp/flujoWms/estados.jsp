<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<!-- <meta http-equiv="X-UA-Compatible" content="IE=8" charset="UTF-8"/> -->
<title>:: Portal Operaciones :: Leonisa S.A. ::</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/ajax/prototype.js"></script>
<script type="text/javascript" src="js/ajax/effects.js"></script>

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>

<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="jsp/flujoWms/estados.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Flujo Operativo / <span>Alertas Flujo Operativo</span> </h2>
	</div>
	<form name="forma" method="post">
		<input type="hidden" id="nroEstado" name="nroEstado" value="" />
		<input type="hidden" id="tituloEstado" name="tituloEstado" value="" />
		<table align="center">
			<tr>
				<td align="center">
				 	<div id="mensaje" align="center" class="mensajeUsuario" style="display:none;"></div>
				</td>
			</tr>
		</table>
		<br>
		<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%" colspan="2">
					<table width="100%" align="center">
						<tr>
							<td width="100%" align="center" class="titulos">ESTADOS DE PEDIDOS</td>
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
								        	<table width="800" height="81" border="0" cellpadding="0" cellspacing="0" align="center">
								        		<tr>
								        			<c:forEach items="${registros}" var="registro" varStatus="fila">
								        				<td>
								        					<table>
								        						<tr>
								        							<c:if test="${registro.nroEstado == codigoEnviado}">
								        								<td>
									        								<img width="114" height="59" src="${registro.pedidos != '0' ? 'imagenes/enviado_01.png' : 'imagenes/enviado_02.png'}" width="11" height="59" />
									        							</td>
								        							</c:if>
								        							<c:if test="${registro.nroEstado == codigoSubido}">
								        								<td>
									        								<img width="114" height="59" src="${registro.pedidos != '0' ? 'imagenes/subido_01.png' : 'imagenes/subido_02.png'}" width="11" height="59" />
									        							</td>
								        							</c:if>
								        							<c:if test="${registro.nroEstado == codigoLiberado}">
								        								<td>
									        								<img width="114" height="59" src="${registro.pedidos != '0' ? 'imagenes/liberado_01.png' : 'imagenes/liberado_02.png'}" width="11" height="59" />
									        							</td>
								        							</c:if>
								        							<c:if test="${registro.nroEstado == codigoAsignado}">
								        								<td>
									        								<img width="114" height="59" src="${registro.pedidos != '0' ? 'imagenes/asignado_01.png' : 'imagenes/asignado_02.png'}" width="11" height="59" />
									        							</td>
								        							</c:if>
								        							<c:if test="${registro.nroEstado == codigoEnviadoWms}">
								        								<td>
									        								<img width="114" height="59" src="${registro.pedidos != '0' ? 'imagenes/wms_01.png' : 'imagenes/wms_02.png'}" width="11" height="59" />
									        							</td>
								        							</c:if>
								        							<c:if test="${registro.nroEstado == codigoConsolidado}">
								        								<td>
									        								<img width="114" height="59" src="${registro.pedidos != '0' ? 'imagenes/consolidado_01.png' : 'imagenes/consolidado_02.png'}" width="11" height="59" />
									        							</td>
								        							</c:if>
								        							<c:if test="${fila.count < numRegistros}">
								        								<td>
									        								<img src="imagenes/flecha.png" width="11" height="59" />
									        							</td>
								        							</c:if>
								        						</tr>
								        						<tr class="filaTabla">
								        							<td class="contenido" style="cursor: pointer;" onclick="verDetalle('${registro.titEstado}','${registro.pedidos}','${registro.nroEstado}');" title="${registro.desEstado}">${registro.pedidos}</td>
								        						</tr>
								        					</table>
								        				</td>
								        			</c:forEach>
								        		</tr>
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
					</table>
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