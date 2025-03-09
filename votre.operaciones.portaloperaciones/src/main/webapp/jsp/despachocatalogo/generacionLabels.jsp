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

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jqueryRotate.js"></script>
<script type="text/javascript" src="js/jquery.collapse.js"></script>
<script  type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>

<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="js/despachocatalogo/generacionLabels.js"></script>
<script type="text/javascript" src="js/ajax/ajaxManual.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Despacho Catálogos / <span>Labels</span> </h2>
	</div>
	<form name="forma" method="post">
		<input type="hidden" id="codCia" name="codCia" value="${codCia}">
		<input type="hidden" id="usuario" name="usuario" value="${usuario}">
		<input type="hidden" id="codigoLabel" name="codigoLabel" value="">
		<input type="hidden" id="fechaIni" name="fechaIni" value="">
		<input type="hidden" id="fechaFin" name="fechaFin" value="">
		<input type="hidden" id="zona" name="zona" value="">
		<input type="hidden" id="parametro" name="parametro" value="">
		<input type="hidden" id="titulo" name="titulo" value="">
		<input type="hidden" id="generoReporteXLS" value="0">				
		<table width="50%" border="0" cellpadding="0" cellspacing="0" align="center">									
			<tr>
				<td width="100%" align="center" colspan="2">
					<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
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
										<td width="60%" class="titulos">${tituloPant}</td>
										<td width="40%" align="right" class="titulos">${pais} ${nomPais}</td>
									</tr>
									<tr>
										<td colspan="2">
											<br>
											<c:forEach items="${opciones}" var="opcion" varStatus="fila">
												<input type="radio" id="opcionLabel" name="opcionLabel" value="${opcion.codigo}-${opcion.cantidad}-${opcion.tituloLabel}" onclick="habilitarCamposDeFechas(this)"><span class="titulosContenido">${opcion.tituloLabel} - ${opcion.cantidad}</span><br>
												<c:if test="${opcion.indFecha=='S'}">
													<input type="hidden" id="indFecha" name="indFecha" value="${opcion.codigo}">
												</c:if>
												<c:if test="${opcion.indGnLabels=='S'}">
													<input type="hidden" id="indLabels" name="indLabels" value="${opcion.codigo}">
												</c:if>
											</c:forEach>
										</td>
									</tr>
									<tr>
										<td colspan="2" style="padding-left: 20px">
											<div id="despliegaFechas" style="display:none;">
												<table width="100%">
													<tr>
														<td colspan="11" class="titulosContenido">FECHA (DD/MM/YYYY)</td>
														<td colspan="1" class="titulosContenido">${seleccioneZona}</td>
													</tr>
													<tr>
														<td><input type="text" id="txtDiaI" name="txtDiaI" class="contenido" size="2" maxlength="2" title="DÍA" onkeypress="return validaIngresoNum2(event)" onblur="completaLongitud('txtDiaI','2')"></td>
														<td class="titulosContenido">/</td>
														<td><input type="text" id="txtMesI" name="txtMesI" class="contenido"  size="2" maxlength="2" title="MES" onkeypress="return validaIngresoNum2(event)" onblur="completaLongitud('txtMesI','2')"></td>															
														<td class="titulosContenido">/</td>
														<td><input type="text" id="txtAnoI" name="txtAnoI" class="contenido" size="4" maxlength="4" title="AÑO" onkeypress="return validaIngresoNum2(event)" onblur="completaLongitud('txtAnoI','4')"></td>
														<td class="titulosContenido">Hasta</td>
														<td><input type="text" id="txtDiaF" name="txtDiaF" class="contenido" size="2" maxlength="2" title="DÍA" onkeypress="return validaIngresoNum2(event)" onblur="completaLongitud('txtDiaF','2')"></td>
														<td class="titulosContenido">/</td>
														<td><input type="text" id="txtMesF" name="txtMesF" class="contenido" size="2" maxlength="2" title="MES" onkeypress="return validaIngresoNum2(event)" onblur="completaLongitud('txtMesF','2')"></td>
														<td class="titulosContenido">/</td>
														<td><input type="text" id="txtAnoF" name="txtAnoF" class="contenido" size="4" maxlength="4" title="AÑO" onkeypress="return validaIngresoNum2(event)" onblur="completaLongitud('txtAnoF','4')"></td>
														<td>
															<div id="despliegaZonas" style="display:none;">																													
																<select id="zonas" name="zonas" class="combo03">
																		<option value="0" >-Seleccione-</option>						
																	<c:forEach items="${zonas}" var="zona">
																		<option value="${zona.zona}">${zona.zona}</option>
																	</c:forEach>											
																</select>
															</div>
														</td>
													</tr>	
												</table>
											</div>
										</td>
									</tr>	
									<tr>
										<td colspan="2" style="padding-left: 20px">
											<div id="despliegaCampana" style="display:none;">
												<table width="50%">
													<tr>
														<td class="titulosContenido">${campana}</td>
														<td><input type="text" id="txtCampana" name="txtCampana" class="contenido" size="1" maxlength="4" onkeypress="return validaIngresoNum2(event)"></td>								
														<td>								
															<div id="despliegaZonas2" style="display:none;">
																<table>
																	<tr>
																		<td class="titulosContenido">${tituloZona}</td>
																		<td>
																			<select id="zonas2" name="zonas2" class="combo03">
																					<option value="0" >-Seleccione-</option>						
																				<c:forEach items="${zonas}" var="zona">
																					<option value="${zona.zona}">${zona.zona}</option>
																				</c:forEach>
																			</select>
																		</td>
																	</tr>											
																</table>									
															</div>
														</td>
													</tr>
												</table>		
											</div>
										</td>
									</tr>
									<tr>
										<td align="center" colspan="2">
											<br>
											<div id="despliegaBotonFechas" style="display:none">
												<input type="button" id="generarInfo" name="generarInfo" class="boton01" onclick="return solicitar()" value="${botonGenerar}">
											</div>
											<input type="button" id="enviarExcel" name="enviarExcel" class="boton01" onclick="return generarExcel()" value="${botonExcel}">
											<div id="despliegaLabels" style="display:none">
												<input type="button" id="enviarLabels" name="enviarLabels" class="boton01" onclick="return generarLabels()" value="${botonLabels}">
											</div>
											<input type="button" id="actualizar" name="actualizar" class="boton01" onclick="refrescar()" value="ACTUALIZAR">
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
		</table>							
	</form>
	<table width="100%" border="0" align="center" cellpadding="3" cellspacing="0">
  		<tr>
    		<th><iframe id="pie2" name="pie" src="jsp/pie.html" scrolling="no" width="100%" height="40" frameborder="0"> Su navegador no reconoce iframes] </iframe></th>
  		</tr>
	</table>
</body>
</html>
