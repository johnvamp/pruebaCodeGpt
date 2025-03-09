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
<script type="text/javascript" src="js/embarqueinternacional/embarque.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Embarque Internacional / <span>Inicio</span> </h2>
	</div>
	<form name="forma" method="post">
		<table align="center">
			<tr>
				<td align="center">
				 	<div id="mensaje" align="center" class="mensajeUsuario" style="display:none;"></div>
				</td>
			</tr>
		</table>
		<input type="hidden" id="nomAccion" name="nomAccion" value="" />
		<table width="60%" border="0" cellpadding="0" cellspacing="0" align="center">
			<tr>
				<td width="100%" align="center" colspan="2">
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
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
										<td class="titulos" align="center" colspan="2">País: ${nomPais}</td>
									</tr>
									<tr>
										<td colspan="2" align="center">
											<table>
												<tr>
													<td><input type="radio" name="accion" id="accion" value="abrirEmbarque" checked="checked"></td>
													<td class="titulosContenido" align="left">${titulo2}</td>	
												</tr>
												<tr>
													<td><input type="radio" name="accion" id="accion" value="capturarEmbarque"></td>
													<td class="titulosContenido" align="left">${titulo3}</td>
												</tr>
												<tr>
													<td><input type="radio" name="accion" id="accion" value="cerrarEmbarque"></td>
													<td class="titulosContenido" align="left">${titulo4}</td>
												</tr>
												<tr>
													<td><input type="radio" name="accion" id="accion" value="descargarEmbarque"></td>
													<td class="titulosContenido" align="left" >${titulo5}</td>
												</tr>
												<tr>
													<td><input type="radio" name="accion" id="accion" value="entregarEmbarque"></td>
													<td class="titulosContenido" align="left">Entregar Embarque</td>
												</tr>
												<tr>
													<td><input type="radio" name="accion" id="accion" value="consultar"></td>
													<td class="titulosContenido" align="left">${titulo6}</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td height="10" colspan="2">
										</td>
									</tr>
									<tr>
										<td align="center"  colspan="2">
											<input type="button" class="boton01" name="btnProcesar" id="btnProcesar" style="cursor: pointer;" onclick="return procesar()" value="PROCESAR">
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
