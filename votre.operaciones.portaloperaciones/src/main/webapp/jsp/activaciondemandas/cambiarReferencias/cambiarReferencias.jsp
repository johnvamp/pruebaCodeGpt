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
<script type='text/javascript' src="js/jquery.blockUI.js"></script>

<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="js/activaciondemandas/cambiarReferencias/cambiarReferencias.js"></script>
<script type="text/javascript" src="js/ajax/ajaxManual.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Activación de la Demanda / <span>Cambiar Referencia a Correo Extra</span> </h2>
	</div>
	<form name="forma" method="post">
		<input type="hidden" id="totalRefe" name="totalRefe" value="${totalRefe}" />
		<input type="hidden" id="referencias" name="referencias" value="" />
		<input type="hidden" id="descripciones" name="descripciones" value="" />
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
					        			<td class="titulos" align="center" width="40%">CAMBIAR TIPO DE DESPACHO A CORREO EXTRA</td>
					        		</tr>
					        		<tr>
					        			<td height="15"></td>
					        		</tr>
					        		<tr>
					        			<td align="center">
					        				<span class="titulosContenido">Seleccione las referencias que desean que se despachen por correo extra</span>
					        			</td>
					        		</tr>
					        		<tr>
					        			<td height="15"></td>
					        		</tr>
					        		<tr>
					        			<td>
					        				<c:if test="${estado == 0}">
						        				<div id="divReferencias" class="scrollReferencias" align="center" style="width: 100%; height: 310px;">
							        				<table class="sortable" cellspacing="0" cellpadding="0" width="100%" bordercolor="#FFFFFF" border="1">
							        					<tr class="tituloTabla">
							        						<td>&nbsp;</td>
							        						<td>Referencia</td>
							        						<td>Descripción</td>
							        						<td>Cantidad Compradoras</td>
							        					</tr>
														<c:forEach items="${referencias}" var="referencia" varStatus="fila">
															<tr class="filaTabla" style="text-align: left;">
																<td align="center">
																	<input type="checkbox" id="chekRefe_${fila.count}" name="chekRefe_${fila.count}" value="${referencia.referencia}"/>
																</td>
																<td class="contenido" align="center">
																	${referencia.referencia}
																</td>
																<td class="contenido" align="center">
																	<input type="hidden" id="descripcion_${fila.count}" name="chekRefe_${fila.count}" value="${referencia.descripRefe}" />
																	${referencia.descripRefe}
																</td>
																<td class="contenido" align="center">
																	${referencia.cantidadCompra}
																</td>
															</tr>
														</c:forEach>
													</table>
												</div>
											</c:if>
					        			</td>
					        		</tr>
					        		<tr>
					        			<td height="15"></td>
					        		</tr>
					        		<tr>
					        			<td align="center">
					        				<input type="button" id="btnModificar" name="btnModificar" class="boton01" value="MODIFICAR" onclick="modificar()" />
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
</body>
</html>