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

<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>
<script type='text/javascript' src='js/tablesorter/jquery.tablesorter.js'></script>
<script type='text/javascript' src='js/tablesorter/jquery.tablesorter.widgets.js'></script> 

<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="jsp/flujoWms/estados.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
<link href="estilos/theme.ice.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	jQuery(document).ready(function () {
		definirTablaFiltros('#tablaDetalle');
	});
</script> 
</head>
<body onload="mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Flujo Operativo / <span>Alertas Flujo Operativo</span> </h2>
	</div>
	<form name="forma" method="post">
		<input type="hidden" id="nroEstado" name="nroEstado" value="${nroEstado}" />
		<input type="hidden" id="tituloEstado" name="tituloEstado" value="${tituloEstado}" />
		<table align="center">
			<tr>
				<td align="center">
				 	<div id="mensaje" align="center" class="mensajeUsuario" style="display:none;"></div>
				</td>
			</tr>
		</table>
		<br>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%" colspan="2">
					<table width="100%" align="center">
						<tr>
							<td width="100%" align="center" class="titulos">DETALLE ${tituloEstado}</td>
						</tr>
						<tr>
							<td height="15">
							</td>
						</tr>												
						<tr>
							<td>
					        	<div class="${nroEstado ==  codigoEnviadoWms || nroEstado == codigoConsolidado ? 'scrollFiltros' : ''}">
					        		<table id="tablaDetalle">
						        		<thead>
						        			<tr class="tituloTabla">
												<th>REGION</th>
												<th>MAIN PLAN</th>
												<th>ZONA</th>
												<th>CAMPAÑA</th>
												<c:if test="${nroEstado == codigoEnviadoWms || nroEstado == codigoConsolidado}">
													<th>DEPARTAMENTO</th>
													<th>CIUDAD</th>
												</c:if>
												<th>CEDULA</th>
												<th>NOMBRE</th>
												<c:if test="${nroEstado == codigoEnviado}">
													<th>TIPO TRANSMISION</th>
													<th>FECHA TRANSMISION</th>
													<th>HORA TRASMISION</th>
												</c:if>
												<c:if test="${nroEstado == codigoSubido}">
													<th># DE ORDEN</th>
													<th>CONCEPTO DE RETENCION</th>
													<th>DIA DE CONFERENCIA OFICIAL</th>
												</c:if>
												<c:if test="${nroEstado == codigoLiberado}">
													<th># DE ORDEN</th>
													<th>FECHA DE LIBERACION</th>
													<th>HORA DE LIBERACION</th>
												</c:if>
												<c:if test="${nroEstado == codigoAsignado}">
													<th># DE ORDEN</th>
													<th>FECHA DE LIBERACION</th>
													<th>HORA DE LIBERACION</th>
													<th>FECHA DE ASIGNACION</th>
													<th>HORA DE ASIGNACION</th>
												</c:if>
												<c:if test="${nroEstado == codigoEnviadoWms}">
													<th># DE ORDEN</th>
													<th># DE PICK TICKET</th>
													<th>ESTADO ACTUAL PICK TICKET</th>
													<th>CANTIDAD DE CARTONES</th>
													<th>HORAS RETRASO</th>
												</c:if>
												<c:if test="${nroEstado == codigoConsolidado}">
													<th># DE ORDEN</th>
													<th># DE PICK TICKET</th>
													<th>TRANSPORTISTA</th>
													<th>FECHA DE CONSOLIDACION</th>
													<th>HORA DE CONSOLIDACION</th>
													<th>HORAS RETRASO</th>
												</c:if>
											</tr>
						        		</thead>
						        		<tbody>
						        			<c:forEach items="${registros}" var="registro" varStatus="fila">
						        				<tr>
													<td>${! empty registro.region ? registro.region : '&nbsp;'}</td>
													<td>${! empty registro.mainPlan ? registro.mainPlan : '&nbsp;'}</td>
													<td>${! empty registro.zona ? registro.zona : '&nbsp;'}</td>
													<td>${! empty registro.campana ? registro.campana : '&nbsp;'}</td>
													<c:if test="${nroEstado == codigoEnviadoWms || nroEstado == codigoConsolidado}">
														<td>${! empty registro.departamento ? registro.departamento : '&nbsp;'}</td>
														<td>${! empty registro.ciudad ? registro.ciudad : '&nbsp;'}</td>
													</c:if>
													<td>${! empty registro.cedula ? registro.cedula : '&nbsp;'}</td>
													<td>${! empty registro.nombre ? registro.nombre : '&nbsp;'}</td>
													<c:if test="${nroEstado == codigoEnviado}">
														<td>${! empty registro.transmision ? registro.transmision : '&nbsp;'}</td>
														<td>${! empty registro.fechaTrans ? registro.fechaTrans : '&nbsp;'}</td>
														<td>${! empty registro.horaTrans ? registro.horaTrans : '&nbsp;'}</td>
													</c:if>
													<c:if test="${nroEstado == codigoSubido}">
														<td>${! empty registro.nroOrden ? registro.nroOrden : '&nbsp;'}</td>
														<td>${! empty registro.conceptoRetencion ? registro.conceptoRetencion : '&nbsp;'}</td>
														<td>${! empty registro.fechaConferencia ? registro.fechaConferencia : '&nbsp;'}</td>
													</c:if>
													<c:if test="${nroEstado == codigoLiberado}">
														<td>${! empty registro.nroOrden ? registro.nroOrden : '&nbsp;'}</td>
														<td>${! empty registro.fechaLiberacion ? registro.fechaLiberacion : '&nbsp;'}</td>
														<td>${! empty registro.horaLiberacion ? registro.horaLiberacion : '&nbsp;'}</td>
													</c:if>
													<c:if test="${nroEstado == codigoAsignado}">
														<td>${! empty registro.nroOrden ? registro.nroOrden : '&nbsp;'}</td>
														<td>${! empty registro.fechaLiberacion ? registro.fechaLiberacion : '&nbsp;'}</td>
														<td>${! empty registro.horaLiberacion ? registro.horaLiberacion : '&nbsp;'}</td>
														<td>${! empty registro.fechaAsignacion ? registro.fechaAsignacion : '&nbsp;'}</td>
														<td>${! empty registro.horaAsignacion ? registro.horaAsignacion : '&nbsp;'}</td>																	
													</c:if>
													<c:if test="${nroEstado == codigoEnviadoWms}">
														<td>${! empty registro.nroOrden ? registro.nroOrden : '&nbsp;'}</td>
														<td>${! empty registro.pickTicket ? registro.pickTicket : '&nbsp;'}</td>
														<td>${! empty registro.estadoPickTicket ? registro.estadoPickTicket : '&nbsp;'}</td>
														<td>${! empty registro.nroCajas ? registro.nroCajas : '&nbsp;'}</td>
														<td>${! empty registro.horaRetrasoWms ? registro.horaRetrasoWms : '&nbsp;'}</td>
													</c:if>
													<c:if test="${nroEstado == codigoConsolidado}">
														<td>${! empty registro.nroOrden ? registro.nroOrden : '&nbsp;'}</td>
														<td>${! empty registro.pickTicket ? registro.pickTicket : '&nbsp;'}</td>
														<td>${! empty registro.transportista ? registro.transportista : '&nbsp;'}</td>
														<td>${! empty registro.fechaConsolidacion ? registro.fechaConsolidacion : '&nbsp;'}</td>
														<td>${! empty registro.horaConsolidacion ? registro.horaConsolidacion : '&nbsp;'}</td>
														<td>${! empty registro.horaRetrasoConsolidado ? registro.horaRetrasoConsolidado : '&nbsp;'}</td>
													</c:if>
												</tr>
						        			</c:forEach>
						        		</tbody>
						        	</table>
					        	</div>
							</td>
						</tr>
						<tr>
							<td height="10">
							</td>
						</tr>
						<tr>
							<td align="center">
								<input type="button" class="boton01" id="btnRegresar" name="btnRegresar" onclick="regresar();" value="REGRESAR" />
								<input type="button" class="boton01" id="btnExcel" name="btnExcel" onclick="generarExcel();" value="GENERAR EXCEL" />
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