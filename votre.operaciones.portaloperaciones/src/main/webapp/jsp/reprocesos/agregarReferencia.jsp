<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
<script type="text/javascript" src="js/reprocesos/reprocesos.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Reprocesos / <span>Ingresar Nueva Solicitud</span> </h2>
	</div>
	<form name="forma" method="post">
		<input type="hidden" id="numRequerimiento" name="numRequerimiento" value="${numRequerimiento}" />
		<input type="hidden" id="fechaEntrega" name="fechaEntrega" value="${fechaEntrega}" />
		<input type="hidden" id="tipoEntrega" name="tipoEntrega" value="${tipoEntrega}" />
		<input type="hidden" id="usuario" name="usuario" value="${usuario}" />
		<table align="center">
			<tr>
				<td align="center">
				 	<div id="mensaje" align="center" class="mensajeUsuario" style="display:none;"></div>
				</td>
			</tr>
		</table>
		<br>
		<table width="60%" border="0" align="center" cellpadding="0" cellspacing="0">
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
					        	<table width="330" height="81" border="0" cellpadding="0" cellspacing="0" align="center">
					        		<tr>
										<td colspan="2" width="100%" align="center" class="titulos">Ingresar Nueva Solicitud</td>
									</tr>
									<tr>
										<td height="15" colspan="2">
										</td>
									</tr>
					        		<tr>
				                        <td width="50%" align="right">
				                       		<label class="txLabel">Número de Requerimiento:</label>&nbsp;&nbsp;
										</td>
										<td width="50%">
											<label class="contenido">${numRequerimiento}</label>
										</td>
									</tr>
									<tr>
				                        <td width="50%" align="right">
				                       		<label class="txLabel">Fecha de Creación:</label>&nbsp;&nbsp;
										</td>
										<td width="50%">
											<label class="contenido">${fechaEntrega}</label>
										</td>
									</tr>
									<tr>
				                        <td width="50%" align="right">
				                       		<label class="txLabel">Usuario Solicitante:</label>&nbsp;&nbsp;
										</td>
										<td width="50%">
											<label class="contenido">${usuario}</label>
										</td>
									</tr>
									<tr>
				                        <td width="50%" align="right">
				                       		<label class="txLabel">Tipo de Entrega:</label>&nbsp;&nbsp;
										</td>
										<td width="50%">
											<label class="contenido">${tipoEntrega}</label>
										</td>
									</tr>
									<tr>
										<td height="15" colspan="2">
										</td>
									</tr>
									<tr>
										<td colspan="2" width="100%" align="center" class="titulos">Ingrese las referencias asociadas al reproceso</td>
									</tr>
									<tr>
										<td height="15" colspan="2">
										</td>
									</tr>
									<tr align="center">
										<td align="center">
											<label class="txLabel">Referencia <a style="color: red;">*</a></label>
										</td>
										<td align="center">
											<label class="txLabel">Cantidad <a style="color: red;">*</a></label>
										</td>
									</tr>
									<tr align="center">
										<td>
											<input type="text" id="txtReferencia" name="txtReferencia" class="campo01" maxlength="15" />
										</td>
										<td>
											<input type="text" id="txtCantidad" name="txtCantidad" class="campo01" onkeypress="return validaIngresoNum2(event);" maxlength="5" />
										</td>
									</tr>
									<tr>
										<td height="15" colspan="2">
										</td>
									</tr>
									<tr>
										<td colspan="2" align="center">
											<input type="button" id="btnAgregar" name="btnAgregar" class="boton01" value="AGREGAR" title="Agregar referencia." onclick="agregarReferencia();" />
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
	    	<c:if test="${numRegistros > 0}">
	    		<tr>
		    		<td height="10">
		    		</td>
		    	</tr>
		    	<tr>
		    		<td align="center">
		    			<div id="url">
						</div>
						<br>
						<a class="titulos">Referencias Agregadas</a>
						<br>
						<table width="50%" border="0" align="center" cellpadding="0" cellspacing="0">
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
						        <td>
						        	<table width="100%" border="1" cellpadding="0" cellspacing="0" align="center">
						        		<tr class="tituloTabla">
						        			<td>Referencia</td>
						        			<td>Cantidad</td>
						        		</tr>
						        		<c:forEach items="${registros}" var="registro" varStatus="fila">
						        			<tr class="filaTabla">
						        				<td>${registro.referencia}</td>
						        				<td>${registro.cantidad}</td>
						        			</tr>
						        		</c:forEach>
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
	    	</c:if>	
	    	<tr>
	    		<td height="10">
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