<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<!-- <meta http-equiv="X-UA-Compatible" content="IE=8" charset="UTF-8"/> -->
<title>:: Portal Operaciones :: Leonisa S.A. ::</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" media="all" href="estilos/calendar-system.css" title="win2k-cold-1" />
<script type="text/javascript" src="js/ajax/prototype.js"></script>
<script type="text/javascript" src="js/ajax/effects.js"></script>

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>

<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="js/reprocesos/reprocesos.js"></script>
<script type="text/javascript" src="js/calendario/calendar.js"></script>
<script type="text/javascript" src="js/calendario/calendar-es.js"></script>
<script type="text/javascript" src="js/calendario/calendar-setup.js"></script>

<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');calendario();" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Reprocesos / <span>Ingresar Nueva Solicitud</span> </h2>
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
				                        <td align="right">
				                       		<label class="txLabel" >Seleccione fecha de entrega <a style="color: red;">*</a></label>&nbsp;&nbsp;
										</td>
				                        <td>
				                            <input type="text" name="fechaEntrega" id="fechaEntrega" size="10"  readonly="readonly"/>
				                            <img src="imagenes/ico_calendar.gif" alt="Calendario" width="16" height="16" id="calendario" style="cursor: pointer;" />
				                        </td>
				                    </tr>
				                    <tr>
				                    	<td height="20">
				                    	</td>
				                    </tr>
				                    <tr>
				                    	<td align="right">
				                       		<label class="txLabel" >Tipo de entrega <a style="color: red;">*</a></label>&nbsp;&nbsp;
										</td>
										<td>
											<select name="tipoEntrega" id="tipoEntrega" class="selects">
												<option value="0" >-Seleccione-</option>
												<option value="PARCIAL" >Parcial</option>
												<option value="TOTAL" >Total</option>
											</select>
										</td>
				                    </tr>
				                    <tr>
				                    	<td height="20">
				                    	</td>
				                    </tr>
				                    <tr>
				                    	<td align="right">
				                    		<label class="txLabel" >Observaciones</label>&nbsp;&nbsp;
				                    	</td>
				                    	<td>
				                    		<textarea rows="5" cols="17" id="observacion" name="observacion" style="font-family: Arial; font-size: 12px;"></textarea>
				                    	</td>
				                    </tr>
				                    <tr>
				                    	<td height="20">
				                    	</td>
				                    </tr>
				                    <tr>
				                    	<td colspan="2" align="center">
				                    		<input type="button" class="boton01" id="btnSiguiente" name="btnSiguiente" value="SIGUIENTE" onclick="crearSolicitud();" />
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