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
<script type="text/javascript" src="js/solicitudcatalogo/editar.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');cargarAnteriores();" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Solicitud Catálogos / <span>Modificar Información</span> </h2>
	</div>
	<form name="forma" method="post">		
		<input type="hidden" name="direccionAnt" id="direccionAnt" value="">
		<input type="hidden" name="barrioAnt" id="barrioAnt" value="">
		<input type="hidden" name="municipioAnt" id="municipioAnt" value="">
		<input type="hidden" name="cedula" id="cedula" value="${cedula}">
		<input type="hidden" name="nombreCompra" id="nombreCompra" value="${nombreCompra}">
		<input type="hidden" name="telefono1" id="telefono1" value="${telefono1}">
		<input type="hidden" name="telefono2" id="telefono2" value="${telefono2}">
		<input type="hidden" name="celular" id="celular" value="${celular}">
		<input type="hidden" name="departamento" id="departamento" value="${departamento}">
		<input type="hidden" name="pais" id="pais" value="${pais}">
		<input type="hidden" name="nroCaso" id="nroCaso" value="${nroCaso}">
		<input type="hidden" name="parametro" id="parametro" value="${parametro}">
		<input type="hidden" name="nombre" id="nombre" value="${nombre}">
		<table align="center">
			<tr>
				<td align="center">
				 	<div id="mensaje" align="center" class="mensajeUsuario" style="display:none;"></div>
				</td>
			</tr>
		</table>
		<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">			
			<tr>
				<td colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">															
						<tr>
							<td align="left" class="titulos">MODIFICAR INFORMACIÓN</td>
						</tr>
						<tr>
							<td height="15">
							</td>
						</tr>
						<tr>
							<td align="left" class="titulos">${titulo2}</td>
						</tr>
						<tr>
							<td height="15">
							</td>
						</tr>																
						<tr>
							<td>
								<table width="100%" border="0" cellpadding="3" cellspacing="0">												
									<tr>
										<td align="left" colspan="1" class="titulosContenido" width="21%">${titulo3}</td>
										<td colspan="3" class="contenido">${cedula}</td>
									</tr>
									<tr>
									<td align="left" colspan="1" class="titulosContenido" width="21%">${titulo4}</td>
										<td colspan="3" class="contenido">${nombreCompra}</td>
									</tr>
									<tr>
										<td align="left" colspan="1" class="titulosContenido" width="21%">${titulo5}</td>
										<td colspan="3">
											<input type="text" class="contenido" name="direccion" id="direccion" size="50" maxlength="180" value="${direccion}">
										</td>
									</tr>
									<tr>
										<td align="left" class="titulosContenido" width="21%">${titulo9}</td> 
										<td class="contenido" width="25">${telefono1}</td>
										<td align="left" class="titulosContenido" width="12%">${titulo10}</td>
										<td class="contenido">${telefono2}</td>
									</tr>
									<tr>
										<td align="left" class="titulosContenido" width="21%">${titulo6}</td>
										<td width="25">
											<input type="text" class="contenido" name="barrio" id="barrio" size="50" maxlength="80" value="${barrio}">
										</td>
										<td align="left" class="titulosContenido" width="12%">${titulo11}</td>
										<td class="contenido">${celular}</td>
									</tr>
									<tr>
										<td align="left" class="titulosContenido" width="21%">${titulo7}</td>
										<td width="25">
											<input type="text" class="contenido" name="municipio" id="municipio" size="50" maxlength="40" value="${municipio}">
										</td>
										<td align="left" class="titulosContenido" width="12%">${titulo12}</td>
										<td class="contenido">${departamento}</td>
									</tr>
									<tr>
										<td align="left" class="titulosContenido" width="21%">${titulo8}</td>
										<c:choose>
											<c:when test="${codCia == '600' || codCia == '350' || codCia == '255' }">
												<td width="25">
													<input type="text" class="contenido" name="codigoPostal" id="codigoPostal" size="50" maxlength="10" value="${codigoPostal}">
												</td>
											</c:when>
											<c:otherwise>
												<td class="contenido" width="25">${codigoPostal}</td>
												<input type="hidden" name="codigoPostal" id="codigoPostal" value="${codigoPostal}">
											</c:otherwise>
										</c:choose>
										<td align="left" class="titulosContenido" width="12%">${titulo13}</td>
										<td class="contenido">${pais}</td>
									</tr>
								</table>											
							</td>
						</tr>	
						<tr>
							<td align="center">
								<br>
								<input type="button" class="boton01" name="bntGuardar" id="btnGuardar" style="cursor: pointer;" value="GUARDAR" onclick="guardar()">
								<input type="button" class="boton01" name="btnRegresar" id="btnRegresar" style="cursor: pointer;" value="REGRESAR" onclick="regresar()">
							</td>
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
