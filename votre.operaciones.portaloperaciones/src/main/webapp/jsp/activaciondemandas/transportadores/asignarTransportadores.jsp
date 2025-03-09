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
<script type="text/javascript" src="js/jqueryRotate.js"></script>
<script type="text/javascript" src="js/jquery.collapse.js"></script>
<script  type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>

<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="js/activaciondemandas/transportadores/asignarTransportadores.js"></script>
<script type="text/javascript" src="js/ajax/ajaxManual.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Activación de la Demanda / <span>Asignar Transportadores</span> </h2>
	</div>
	<form name="forma" method="post">
		<input type="hidden" id="accion" name="accion" value="${accion}" />
		<input type="hidden" id="valor" name="valor" value="" />
		<input type="hidden" id="totalRefe" name="totalRefe" value="${totalRefe}" />
		<input type="hidden" id="nroGuiasMaster" name="nroGuiasMaster" value="${nroGuiasMaster}" />
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
					        			<td class="titulos" align="center" width="40%">CREAR PEDIDO PARA WMS</td>
					        		</tr>
					        		<tr>
					        			<td height="15"></td>
					        		</tr>
					        		<tr>
					        			<td align="center">
					        				<span class="titulosContenido">Seleccione un tipo de operación para crear pedido.</span>
					        			</td>
					        		</tr>
					        		<tr>
					        			<td align="center">
					        				<input type="radio" id="automatica" name="tipo" style="cursor: pointer;" onclick="mostrarDivAuto();" /><span class="titulosContenido">Automática</span>
					        				<input type="radio" id="manual" name="tipo" onclick="mostrarDiv();" style="cursor: pointer;" ${!empty banderaCheck ? banderaCheck : ''} /><span class="titulosContenido">Manual</span>
					        			</td>
					        		</tr>
					        		<tr>
					        			<td align="center">
					        				<div id="divManual" style="display: ${!empty mostrarDiv ? mostrarDiv : 'none'};" align="center">
					        					<table>
					        						<tr>
					        							<td>
					        								<input type="radio" id="referencias" name="filtro" onclick="consultarReferencias();" style="cursor: pointer;" ${!empty banderaCheck ? banderaCheck : ''} /><span class="titulosContenido">Referencias</span>
					        								<input type="radio" id="cedulas" name="filtro" onclick="mostrarDivCedulas();" style="cursor: pointer;" /><span class="titulosContenido">Cedulas</span>
					        							</td>
					        						</tr>
					        					</table>
				        						<div id="divReferencias" class="scrollReferencias" style="width: 80%">
													<table width="100%" border="1" cellpadding="0" cellspacing="0" align="center">
														<c:forEach items="${referencias}" var="referencia" varStatus="fila">
															<tr class="filaTabla" style="text-align: left;">
																<td align="center">
																	<input type="checkbox" id="chekRefe_${fila.count}" name="chekRefe_${fila.count}" value="${referencia.referencia}"/>
																</td>
																<td class="contenido" align="center">
																	${referencia.referencia}
																</td>
																<td class="contenido" align="center">
																	${referencia.descripRefe}
																</td>
																<td class="contenido" align="center">
																	${referencia.cantidadCompra}
																	<input type="hidden" id="cantidadDispo_${fila.count}" name="cantidadDispo_${fila.count}" value="${referencia.cantidadCompra}" />
																</td>
																<td class="contenido" align="center">
																	<input type="text" id="cantidad_${fila.count}" name="cantidad_${fila.count}" class="campoW" style="text-align: center;" value="${referencia.cantidadCompra}" size="3" maxlength="3" onkeypress="return validaIngresoNum2(event)"/>
																</td>
															</tr>
														</c:forEach>
													</table>
												</div>
												<div id="divCedulas" style="display: none;">
													<table>
														<tr>
															<td align="center"><span class="titulosContenido">Digite las cedulas a asignar.</span></td>
														</tr>
														<tr>
															<td align="center">
																<textarea id="listaCedulas" name="listaCedulas" rows="8" cols="18" class="contenido" onkeypress="return validaIngresoNum2(event);"></textarea>
															</td>
														</tr>
													</table>
												</div>
					        				</div>
					        			</td>
					        		</tr>
					        		<tr>
										<td align="center">
											<div id="divBotonA" style="display:none;" align="center">
												<input type="button" id="asignar" name="asignar" class="boton01" onclick="asignarAutomatico();" value="ASIGNAR" />
											</div>
											<div id="divBotonR" style="display: ${!empty mostrarDiv ? mostrarDiv : 'none'};" align="center">
												<input type="button" id="asignar" name="asignar" class="boton01" onclick="asignarManual();" value="ASIGNAR" />
											</div>
											<div id="divBotonC" style="display:none;" align="center">
												<input type="button" id="asignar" name="asignar" class="boton01" onclick="asignarCedulas();" value="ASIGNAR" />
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
		<table>
			<tr>
				<td height="30"></td>
			</tr>
		</table>
<!-- 		<table width="50%" border="0" cellpadding="0" cellspacing="0" align="center">									 -->
<!-- 			<tr> -->
<!-- 				<td width="100%" align="center" colspan="2"> -->
<!-- 					<table width="600" border="0" align="center" cellpadding="0" cellspacing="0"> -->
<!-- 						<tr> -->
<!-- 					        <td class="cajaGris01"><img src="imagenes/spacer.gif" alt="img" width="9" height="9" /></td> -->
<!-- 					        <td class="bgcajaGris"></td> -->
<!-- 					        <td class="cajaGris02"><img src="imagenes/spacer.gif" alt="img" width="9" height="9" /></td> -->
<!-- 					    </tr> -->
<!-- 					    <tr> -->
<!-- 					        <td class="bgcajaGris">&nbsp;</td> -->
<!-- 					        <td class="bgcajaGris"> -->
<!-- 					        	<table width="100%" border="0" cellspacing="1" cellpadding="2">	 -->
<!-- 					        		<tr> -->
<!-- 					        			<td class="titulos" align="center" width="40%">GENERAR GUIAS</td> -->
<!-- 					        		</tr> -->
<!-- 					        		<tr> -->
<!-- 					        			<td align="center"> -->
<!-- 					        				<input type="button" id="generar" name="generar" class="boton01" onclick="generarGuias();" value="GENERAR" /> -->
<!-- 					        			</td> -->
<!-- 					        		</tr> -->
<!-- 					        	</table> -->
<!-- 					        </td> -->
<!-- 	    					<td class="bgcajaGris">&nbsp;</td> -->
<!-- 	    				</tr>				 -->
<!-- 						<tr> -->
<!-- 					        <td class="cajaGris03"></td> -->
<!-- 					        <td class="bgcajaGris"></td> -->
<!-- 					        <td class="cajaGris04"></td> -->
<!-- 		     			</tr>      				 -->
<!-- 	    			</table> -->
<!-- 	    		</td> -->
<!-- 	    	</tr>				 -->
<!-- 		</table> -->
		<table>
			<tr>
				<td height="30"></td>
			</tr>
		</table>
<%-- 		<c:if test="${nroGuiasMaster > 0}"> --%>
<!-- 			<table width="50%" border="0" cellpadding="0" cellspacing="0" align="center">									 -->
<!-- 				<tr> -->
<!-- 					<td width="100%" align="center" colspan="2"> -->
<!-- 						<table width="600" border="0" align="center" cellpadding="0" cellspacing="0"> -->
<!-- 							<tr> -->
<!-- 						        <td class="cajaGris01"><img src="imagenes/spacer.gif" alt="img" width="9" height="9" /></td> -->
<!-- 						        <td class="bgcajaGris"></td> -->
<!-- 						        <td class="cajaGris02"><img src="imagenes/spacer.gif" alt="img" width="9" height="9" /></td> -->
<!-- 						    </tr> -->
<!-- 						    <tr> -->
<!-- 						        <td class="bgcajaGris">&nbsp;</td> -->
<!-- 						        <td class="bgcajaGris"> -->
<!-- 						        	<table width="100%" border="0" cellspacing="1" cellpadding="2">	 -->
<!-- 						        		<tr> -->
<!-- 						        			<td class="titulos" align="center" width="40%">GENERAR GUIAS MASTER</td> -->
<!-- 						        		</tr> -->
<!-- 						        		<tr> -->
<!-- 						        			<td align="center"> -->
<%-- 						        				<div class="${nroGuiasMaster > 5 ? 'scrollReferencias' : ''}"> --%>
<!-- 									        		<table width="100%" border="1" cellpadding="0" cellspacing="0" align="center"> -->
<!-- 								        				<tr class="tituloTabla"> -->
<!-- 								        					<td>Cédula Transportista</td> -->
<!-- 								        					<td>Nombre Transportista</td> -->
<!-- 								        					<td>Total Premios</td> -->
<!-- 								        					<td>Cantidad</td> -->
<!-- 								        				</tr> -->
<%-- 								        				<c:forEach items="${guias}" var="guia" varStatus="fila"> --%>
<!-- 															<tr class="filaTabla"> -->
<!-- 																<td class="contenido"> -->
<%-- 																	${guia.cedulaTrans} --%>
<%-- 																	<input type="hidden" id="cedulaTrans_${fila.count}" name="cedulaTrans_${fila.count}" value="${guia.cedulaTrans}" /> --%>
<!-- 																</td> -->
<!-- 																<td class="contenido" align="center"> -->
<%-- 																	${guia.nombreTrans} --%>
<%-- 																	<input type="hidden" id="codigoTrans_${fila.count}" name="codigoTrans_${fila.count}" value="${guia.codigoTrans}" /> --%>
<%-- 																	<input type="hidden" id="nombreTrans_${fila.count}" name="nombreTrans_${fila.count}" value="${guia.nombreTrans}" /> --%>
<!-- 																</td> -->
<!-- 																<td class="contenido" align="center"> -->
<%-- 																	${guia.totalPremios} --%>
<!-- 																</td> -->
<!-- 																<td> -->
<%-- 																	<input type="text" id="cantidad_${fila.count}" name="cantidad_${fila.count}" class="campoW" style="text-align: center;" value="1" size="3" maxlength="3" onkeypress="return validaIngresoNum2(event)"/> --%>
<!-- 																</td> -->
<!-- 															</tr> -->
<%-- 														</c:forEach> --%>
<!-- 													</table> -->
<!-- 												</div> -->
<!-- 						        			</td> -->
<!-- 						        		</tr> -->
<!-- 						        		<tr> -->
<!-- 						        			<td height="10"> -->
<!-- 						        			</td> -->
<!-- 						        		</tr> -->
<!-- 						        		<tr> -->
<!-- 						        			<td align="center"> -->
<!-- 						        				<input type="button" id="generar" name="generar" class="boton01" onclick="imprimirGuiasMaster();" value="IMPRIMIR MASTER" /> -->
<!-- 						        			</td> -->
<!-- 						        		</tr> -->
<!-- 						        	</table> -->
<!-- 								 </td> -->
<!-- 		    					<td class="bgcajaGris">&nbsp;</td> -->
<!-- 		    				</tr>				 -->
<!-- 							<tr> -->
<!-- 						        <td class="cajaGris03"></td> -->
<!-- 						        <td class="bgcajaGris"></td> -->
<!-- 						        <td class="cajaGris04"></td> -->
<!-- 			     			</tr>      				 -->
<!-- 		    			</table> -->
<!-- 		    		</td> -->
<!-- 		    	</tr>				 -->
<!-- 			</table> -->
<%-- 		</c:if> --%>
		<c:if test="${nroMensajes > 0}">
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
						        			<td class="titulos" align="center" width="40%">ESTADOS GUIAS MASTER</td>
						        		</tr>
						        		<tr>
						        			<td align="center">
						        				<div class="${nroMensajes > 5 ? 'scrollReferencias' : ''}">
									        		<table width="100%" border="1" cellpadding="0" cellspacing="0" align="center">
								        				<tr class="tituloTabla">
								        					<td>Cédula Transportista</td>
								        					<td>Nombre Transportista</td>
								        					<td>Estado</td>
								        				</tr>
								        				<c:forEach items="${arrMensajes}" var="mensaje" varStatus="fila">
								        					<tr class="filaTabla">
								        						<td class="contenido">${mensaje.cedulaTrans}</td>
									        					<td class="contenido">${mensaje.nombreTrans}</td>
									        					<td class="contenido">${mensaje.descripcion}</td>
								        					</tr>								        					
								        				</c:forEach>
													</table>
												</div>
																			        		
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
		</c:if>					        
		<table width="100%" border="0" align="center" cellpadding="3" cellspacing="0">
	  		<tr>
	    		<th><iframe id="pie2" name="pie" src="jsp/pie.html" scrolling="no" width="100%" height="40" frameborder="0"> Su navegador no reconoce iframes] </iframe></th>
	  		</tr>
		</table>							
	</form>
</body>
</html>