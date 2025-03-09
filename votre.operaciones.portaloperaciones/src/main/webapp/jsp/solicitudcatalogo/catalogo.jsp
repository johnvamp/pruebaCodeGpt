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
<script type="text/javascript" src="js/solicitudcatalogo/catalogo.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Solicitud Catálogos / <span>Consultas</span> </h2>
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
										<td class="titulos" align="center">${pais}: ${nomPais}</td>
									</tr>																							
									<tr>
										<td align="center">											
											<input type="radio" name="opcion" onclick="mostrarCedula()"><span class="titulosContenido">${opcionCedula}</span><br>
											<input type="radio" name="opcion"  onclick="mostrarNombre()"><span class="titulosContenido">${opcionNombre}</span><br>
											<br>										
											<table align="center" width="350">
												<tr>										
													<td align="center">
														<div id="divCedula" style="display:none; width: 400px;" >
															<table>
																<tr>
																	<td class="txLabel">Digite la cédula o código:</td>
																	<td>
																		<label><input type="text" name="cedula" id="cedula" class="campo02" title="Digite la cédula o código." onkeypress="return validaIngresoNum2(event)" value="${cedula}"></label>
																	</td>																	
																</tr>
																<tr>
														        	<td colspan="2" height="10"></td>
														        </tr>
																<tr>
	           														<td colspan="2" class="txLabel">
	           															<div align="center">
	           																<input type="button" class="boton01" name="btnConsultar" id="btnConsultar" style="cursor: pointer;" onclick="consultar('validarCedula')" value="CONSULTAR">
	           															</div>
	           														</td>
	           													</tr>
															</table>																
														</div>
													</td>
												</tr>		
											</table>
											<table align="center" width="350">
												<tr>										
													<td align="center">
														<div id="divNombre" style="display:none; width: 400px;">
															<table>
																<tr>
																	<td class="txLabel">Digite el nombre a consultar:</td>
																	<td>
																		<label><input type="text" name="nombre" id="nombre" class="campo02" title="Digite el nombre de la Compradora o Contacto" value="${nombre}"></label>
																	</td>
																</tr>
																<tr>
															   		<td colspan="2" height="10"></td>
															    </tr>
															    <tr>
	           														<td colspan="2" class="txLabel">
	           															<div align="center">
	           																<input type="button" class="boton01" name="btnConsultar" id="btnConsultar" style="cursor: pointer;" onclick="consultar('validarNombre')" value="CONSULTAR">				
	           															</div>
	           														</td>
	           													</tr>		
															</table>
														</div>
													</td>
												</tr>		
											</table>
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
