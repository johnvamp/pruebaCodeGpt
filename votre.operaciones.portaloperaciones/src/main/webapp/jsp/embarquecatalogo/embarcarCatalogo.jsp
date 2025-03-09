<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: Portal Operaciones :: Leonisa S.A. ::</title>
<script type="text/javascript" src="js/ajax/prototype.js"></script>
<script type="text/javascript" src="js/ajax/effects.js"></script>

<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="jsp/embarquecatalogo/embarcarCatalogo.js"></script>
<script type="text/javascript" src="js/ajax/ajaxManual.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Catálogos / <span>Embarque Catálogos</span> </h2>
	</div>
	<form name="forma" method="post">
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
					        			<td colspan="2" class="titulos" align="center" width="40%">EMBARQUE DE CATÁLOGOS</td>
					        		</tr>
					        		<tr>
					        			<td colspan="2" height="15"></td>
					        		</tr>
					        		<tr>
					        			<td align="right">
					        				<span class="titulosContenido">Seleccione la transportadora:</span>
					        			</td>
					        			<td align="left">
											<select name="transportadoras" id="transportadoras" class="combo03">
													<option value="" >-Seleccione-</option>						
												<c:forEach items="${transportadoras}" var="transportadora">
													<option value="${transportadora.codigoTrans}" >${transportadora.transportadora}</option>
												</c:forEach>
											</select>
										</td>
					        		</tr>
					        		<tr>
					        			<td colspan="2" height="15"></td>
					        		</tr>
					        		<tr>
				                    	<td align="right">
				                    		<span class="titulosContenido">Ingrese listado de guías embarcadas:</span>
				                    	</td>
				                    	<td>
				                    		<textarea rows="5" cols="17" id="guias" name="guias" style="font-family: Arial; font-size: 12px; width: 60%" onkeypress="return validaIngresoNum2(event);"></textarea>
				                    	</td>
				                    </tr>
				                    <tr>
					        			<td colspan="2" height="15"></td>
					        		</tr>
					        		<tr>
				                    	<td colspan="2" align="center">
				                    		<input type="button" class="boton01" id="btnEnviar" name="btnEnviar" value="ENVIAR" onclick="enviarGuias();" />
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
						        			<td class="titulos" align="center" width="40%">ESTADO DE GUIAS NO EMBARCADAS</td>
						        		</tr>
						        		<tr>
						        			<td align="center">
						        				<div class="${nroMensajes > 5 ? 'scrollReferencias' : ''}">
									        		<table width="100%" border="1" cellpadding="0" cellspacing="0" align="center">
								        				<tr class="tituloTabla">
								        					<td width="33%">Código Transportadora</td>
								        					<td width="33%">Número Guía</td>
								        					<td width="33%">Estado</td>
								        				</tr>
								        				<c:forEach items="${arrMensajes}" var="mensaje" varStatus="fila">
								        					<tr class="filaTabla">
								        						<td class="contenido">${mensaje.codTrans}</td>
									        					<td class="contenido">${mensaje.nroguia}</td>
									        					<td class="contenido">${mensaje.dsStatus}</td>
								        					</tr>								        					
								        				</c:forEach>
													</table>
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
		</c:if>					        
		<table width="100%" border="0" align="center" cellpadding="3" cellspacing="0">
	  		<tr>
	    		<th><iframe id="pie2" name="pie" src="jsp/pie.html" scrolling="no" width="100%" height="40" frameborder="0"> Su navegador no reconoce iframes] </iframe></th>
	  		</tr>
		</table>
	</form>
</body>
</html>