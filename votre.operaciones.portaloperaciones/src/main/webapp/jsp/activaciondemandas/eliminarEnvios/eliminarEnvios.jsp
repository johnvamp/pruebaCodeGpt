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

<script type='text/javascript' src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>

<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="js/activaciondemandas/eliminarEnvios/eliminarEnvios.js"></script>
<script type="text/javascript" src="js/ajax/ajaxManual.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Activación de la Demanda / <span>Eliminar Envios Pendientes</span> </h2>
	</div>
	<form name="forma" method="post">
		<input type="hidden" id="tipo" name="tipo" value="" />
		<input type="hidden" id="accion" name="accion" value="" />
		<input type="hidden" id="cedula" name="cedula" value="" />
		<input type="hidden" id="codInterno" name="codInterno" value="" />
		<input type="hidden" id="numOrden" name="numOrden" value="" />
		<input type="hidden" id="sku" name="sku" value="" />
		<table align="center">
			<tr>
				<td align="center">
				 	<div id="mensaje" align="center" class="mensajeUsuario" style="display:none;"></div>
				</td>
			</tr>
		</table>
		<br>
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
					        			<td class="titulos" align="center" width="40%">ELIMINAR ENVIOS PENDIENTES</td>
					        		</tr>
					        		<tr>
					        			<td height="15"></td>
					        		</tr>
					        		<tr>
					        			<td align="center">
					        				<span class="titulosContenido">Seleccione un tipo de operación a realizar</span>
					        			</td>
					        		</tr>
					        		<tr>
					        			<td align="center">
					        				<input type="radio" id="masiva" name="tipoOperacion" style="cursor: pointer;" onclick="mostrarDivMasiva();" ${!empty banderaCheckMasiva ? banderaCheckMasiva : ''} /><span class="titulosContenido">Masiva</span>
					        				<input type="radio" id="individual" name="tipoOperacion" onclick="mostrarDivIndividual();" style="cursor: pointer;" ${!empty banderaCheckIndi ? banderaCheckIndi : ''} /><span class="titulosContenido">Individual</span>
					        			</td>
					        		</tr>
					        		<tr>
					        			<td align="center">
					        				<div id="divMasiva" style="width: 100%; display: ${!empty mostrarDivMasiva ? mostrarDivMasiva : 'none'}" align="center">
					        					<table>
					        						<tr>
					        							<td><span class="titulosContenido">Ingrese SKU:</span></td>
					        							<td>
					        								<input type="text" class="campoW" id="refeBuscar" name="refeBuscar" maxlength="7" size="7" value="${refeBuscar}" title="Referencia" onkeypress="return validarTextoGeneral(event);"/>
					        							</td>
					        							<td>
					        								<input type="text" class="campoW" id="colorBuscar" name="colorBuscar" maxlength="3" size="3" value="${colorBuscar}" title="Color" onkeypress="return validarTextoGeneral(event);"/>
					        							</td>
					        							<td>
					        								<input type="text" class="campoW" id="tallaBuscar" name="tallaBuscar" maxlength="3" size="3" value="${tallaBuscar}" title="Talla" onkeypress="return validarTextoGeneral(event);"/>
					        							</td>
					        							<td>
					        								<input type="button" class="boton01" id="btnBuscar" name="btnBuscar" value="BUSCAR" onclick="buscarSku();" />
					        							</td>
					        						</tr>
					        					</table>
					        					<c:if test="${nroRegistrosM > 0}">
													<div id="divEnvios" style="width: 30%">
														<table class="sortable" cellspacing="0" cellpadding="0" width="100%" bordercolor="#FFFFFF" border="1">
															<tr class="tituloTabla">
																<td>Tipo Envío</td>
																<td>Total Registros</td>
															</tr>
															<c:forEach items="${arrEnvios}" var="envios" varStatus="fila">
																<tr class="filaTabla" style="text-align: left;">
																	<td class="contenido" align="center">
																		${envios.tipoEnvio}
																	</td>
																	<td class="contenido" align="center">
																		${envios.totalRegistros}
																	</td>
																</tr>
															</c:forEach>
														</table>
														<table>
															<tr>
																<td height="10"></td>
															</tr>
															<tr>
																<td align="center">
																	<input type="button" class="boton01" id="btnConfirmar" name="btnConfirmar" value="CONFIRMAR" onclick="confirmar();" />
																</td>
															</tr>
														</table>
													</div>					        					
					        					</c:if>
					        				</div>
					        				<div id="divIndividual" style="width: 100%; display: ${!empty mostrarDivIndi ? mostrarDivIndi : 'none'}" align="center">
					        					<table>
					        						<tr>
					        							<td><span class="titulosContenido">Ingrese la cédula:</span></td>
					        							<td>
					        								<input type="text" id="cedulaBuscar" name="cedulaBuscar" class="campo01" maxlength="15" onkeypress="return validaIngresoNum2(event)" value="${cedulaBuscar}" />
					        							</td>
					        							<td>
					        								<input type="button" class="boton01" id="btnBuscar" name="btnBuscar" value="BUSCAR" onclick="buscarCedula();" />
					        							</td>
					        						</tr>
					        					</table>
					        					<c:if test="${nroRegistrosI > 0}">
													<div id="divEnvios" style="width: 100%">
														<table class="sortable" cellspacing="0" cellpadding="0" width="100%" bordercolor="#FFFFFF" border="1">
															<tr class="tituloTabla">
																<td>Campaña</td>
																<td>Cédula</td>
																<td>Nombre</td>
																<td>SKU</td>
																<td>Descripción</td>
																<td>&nbsp;</td>
															</tr>
															<c:forEach items="${arrEnvios}" var="envios" varStatus="fila">
																<tr class="filaTabla" style="text-align: left;">
																	<td class="contenido" align="center">
																		${envios.campana}
																	</td>
																	<td class="contenido" align="center">
																		${envios.cedula}
																	</td>
																	<td class="contenido" align="center">
																		${envios.nombre}
																	</td>
																	<td class="contenido" align="center">
																		${envios.sku}
																	</td>
																	<td class="contenido" align="center">
																		${envios.descripSku}
																	</td>
																	<td>
																		<input type="hidden" id="sku_${fila.count}" name="sku_${fila.count}" value="${envios.sku}" />
																		<input type="hidden" id="codInterno_${fila.count}" name="codInterno_${fila.count}" value="${envios.codInterno}" />
																		<input type="hidden" id="numOrden_${fila.count}" name="numOrden_${fila.count}" value="${envios.numOrden}" />
																		<a href="#" onclick="eliminarEnvio('${fila.count}');">
						        											<img width="32" height="32" border="0" title="Eliminar" src="imagenes/ico_delete.png">
						        										</a>
																	</td>
																</tr>
															</c:forEach>
														</table>
													</div>
												</c:if>
					        				</div>
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
		<table width="100%" border="0" align="center" cellpadding="3" cellspacing="0">
	  		<tr>
	    		<th><iframe id="pie2" name="pie" src="jsp/pie.html" scrolling="no" width="100%" height="40" frameborder="0"> Su navegador no reconoce iframes] </iframe></th>
	  		</tr>
		</table>							
	</form>
</body>
</html>