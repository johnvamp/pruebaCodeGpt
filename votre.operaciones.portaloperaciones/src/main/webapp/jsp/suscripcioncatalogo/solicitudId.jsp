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
<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="js/suscripcioncatalogo/solicitudId.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="load();mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Suscripción Catálogos / <span>Consultas</span> </h2>
	</div>
	<form name="forma" method="post">
		<table align="center">
			<tr>
				<td align="center">
				 	<div id="mensaje" align="center" class="mensajeUsuario" style="display:none;"></div>
				</td>
			</tr>
		</table>
		<table width="50%" border="0" cellpadding="0" cellspacing="0" align="center">									
			<tr>
				<td width="100%" align="center" colspan="2">
					<table width="650" border="0" align="center" cellpadding="0" cellspacing="0">
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
										<td colspan="2" class="txLabelCentro">${sessionScope.nomPais}</td>
									</tr>
									<tr>
										<td class="txLabel" >POR FAVOR DIGITE LA IDENTIFICACIÓN DE LA COMPRADORA A CONSULTAR:</td>
										<td>
											<input type="text" name="cedula" id="cedula" class="campo02" title="Digite la cédula o código." onkeypress="return validaIngresoId(event)" value="${cedula}"  maxlength="15">
										</td>																	
									</tr>
									<tr>
							        	<td colspan="2" height="10"></td>
							        </tr>
									<tr>
	    								<td colspan="2" class="txLabel">
	    									<div align="center">
	    										<input type="button" class="boton01" name="btnConsultar" id="btnConsultar" style="cursor: pointer;" onclick="validarId();" value="CONSULTAR">
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
	</form>
	<table width="100%" border="0" align="center" cellpadding="3" cellspacing="0">
  		<tr>
    		<th><iframe id="pie2" name="pie" src="jsp/pie.html" scrolling="no" width="100%" height="40" frameborder="0"> Su navegador no reconoce iframes] </iframe></th>
  		</tr>
	</table>
</body>
</html>
