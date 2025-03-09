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
<script type="text/javascript" src="js/jQuery1_6_4.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/fancybox/jquery.fancybox-1.3.4.js"></script>
<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="js/suscripcioncatalogo/formCatalogos.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
</head>
<body onload="load('${accion}');mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Suscripción Catálogos / <span>Ingreso</span> </h2>
	</div>
	<form name="forma" method="post">
		<input type="hidden" id="cedula" name="cedula" value="${cedula}" />
		<input type="hidden" id="accion" name="accion" value="${accion}" />
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
					<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
					        <td class="cajaGris01"><img src="imagenes/spacer.gif" alt="img" width="9" height="9" /></td>
					        <td class="bgcajaGris"></td>
					        <td class="cajaGris02"><img src="imagenes/spacer.gif" alt="img" width="9" height="9" /></td>
					    </tr>
					    <tr>
					        <td class="bgcajaGris">&nbsp;</td>
					        <td class="bgcajaGris">
					        	<div id="ingresoCantidad">
						        	<table width="100%" border="0" cellspacing="1" cellpadding="2">
										<tr>
											<td colspan="2" class="txLabelCentro">${sessionScope.nomPais}</td>
										</tr>
										<tr>
											<td class="txLabel">NÚMERO DE CATÁLOGOS QUE DESEA INSCRIBIR:</td>
											<td>
												<label><input type="text" name="cantidad" id="cantidad" class="campo02" title="Digite la cantidad." onkeypress="return validaIngresoCantidad(event)" value="${cantidad}"  maxlength="2"></label>
											</td>																	
										</tr>
										<tr>
								        	<td colspan="2" height="10"></td>
								        </tr>
										<tr>
		    								<td colspan="2" class="txLabel">
		    									<div align="center">
		    										<input type="button" class="boton01" name="btnConsultar" id="btnConsultar" style="cursor: pointer;" onclick="validarCantidad();" value="GUARDAR">
		    									</div>
		    								</td>
		    							</tr>
									</table>
					        	</div>
					        	<div id="editaCantidad">
						        	<table width="100%" border="0" cellspacing="1" cellpadding="2">
										<tr>
											<td class="txLabelCentro">ACTUALMENTE ESTÁS INSCRITA ${cantidad} CON CATÁLOGOS</td>
										</tr>
										<tr>
		    								<td class="txLabel">
		    									<div align="center">
		    										<input type="button" class="boton01" name="btnEdita" id="btnEdita" style="cursor: pointer;" onclick="editar();" value="EDITAR">&nbsp;
		    										<input type="button" class="boton01" name="btnEdita" id="btnEdita" style="cursor: pointer;" onclick="cancelarSuscripcion();" value="CANCELAR SUSCRIPCIÓN">
		    									</div>
		    								</td>
		    							</tr>
										<tr>
		    								<td class="txLabel">
		    									<div align="center">
		    										<a href="#" onclick="verPoliticas()">Ver políticas y condiciones</a>
		    									</div>
		    								</td>
		    							</tr>
									</table>
					        	</div>
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
