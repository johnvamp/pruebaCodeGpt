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
<script type="text/javascript" src="js/solicitudcatalogo/relacionEnvio.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Solicitud Catálogos / <span>Relación Envió</span> </h2>
	</div>
	<form name="forma" method="post">
		<input type="hidden" name="sku" id="sku" value="">
		<input type="hidden" name="cedula" id="cedula" value="${cedula}">
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
				<td width="100%" colspan="2">
					<table width="100%" align="center">									
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
										<td colspan="3" class="contenido">${direccion}</td>
									</tr>
									<tr>
										<td align="left" class="titulosContenido" width="21%">${titulo9}</td> 
										<td class="contenido">${telefono1}</td>
										<td align="left" class="titulosContenido" width="12%">${titulo10}</td>
										<td class="contenido">${telefono2}</td>
									</tr>
									<tr>
										<td align="left" class="titulosContenido" width="21%">${titulo6}</td>
										<td class="contenido">${barrio}</td>
										<td align="left" class="titulosContenido" width="12%">${titulo11}</td>
										<td class="contenido">${celular}</td>
									</tr>
									<tr>
										<td align="left" class="titulosContenido" width="21%">${titulo7}</td>
										<td class="contenido">${municipio}</td>
										<td align="left" class="titulosContenido" width="12%">${titulo12}</td>
										<td class="contenido">${departamento}</td>
									</tr>
									<tr>
										<td align="left" class="titulosContenido" width="21%">${titulo8}</td>
										<td class="contenido">${codigoPostal}</td>
										<td align="left" class="titulosContenido" width="12%">${titulo13}</td>
										<td class="contenido">${pais}</td>
									</tr>															
								</table>
							</td>
						</tr>
						<tr>
							<td height="15">
							</td>
						</tr>
						<tr>
							<td align="left" width="100%">
								<table width="100%" border="0" cellpadding="3" cellspacing="0">
									<tr>
										<td width="100%" class="titulos">${titulo6Ref}</td>
									</tr>
									<tr>
										<td height="15">
										</td>
									</tr>
									<tr>
										<td>														
											<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				    							<tr>
											        <td class="caja01"></td>
											        <td class="caja02"></td>
											        <td class="caja03"></td>
											   	</tr>
											   	<tr>
											        <td class="caja04"></td>
											        <td>
											        	<table width="100%" class='sortable' border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
															<tr class="tituloTabla">
																<td>${titulo1Ref}</td>
																<td>${titulo3Ref}</td>
																<td>${titulo4Ref}</td>
																<td>${titulo5Ref}</td>
															</tr>
															<c:forEach items="${registros}" var="registro">
																<tr class="filaTabla">
																	<td class="contenido">${registro.campana}</td>
																	<td class="contenido">${registro.sku}</td>
																	<td class="contenido">${registro.descripcion}</td>
																	<td class="contenido">${registro.cantidad}</td>
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
								</table>
							</td>
						</tr>
						<tr>
							<td height="15">
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellpadding="3" cellspacing="0">
									<tr>
										<td width="100%" class="titulos">${titulo5Audi}</td>
									</tr>
									<tr>
										<td height="15">
										</td>
									</tr>
									<tr>
										<td>														
											<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				    							<tr>
											        <td class="caja01"></td>
											        <td class="caja02"></td>
											        <td class="caja03"></td>
											   	</tr>
											   	<tr>
											        <td class="caja04"></td>
											        <td>
											        	<table width="100%" class='sortable' border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
															<tr class="tituloTabla">
																<td>${titulo1Audi}</td>
																<td>${titulo2Audi}</td>
																<td>${titulo3Audi}</td>
																<td>${titulo4Audi}</td>
															</tr>
															<c:forEach items="${registrosAudi}" var="registro">
																<tr class="filaTabla">
																	<td class="contenido">${registro.descripAuditoria}</td>
																	<td class="contenido">${registro.fecha}</td>
																	<td class="contenido">${registro.hora}</td>
																	<td class="contenido">${registro.usuario}</td>
																</tr>
															</c:forEach>	
														</table>
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
									<tr>
										<td colspan="2" width="100%" align="center">
											<br>								
											<input type="button" class="boton01" name="regresar" id="regresar" style="cursor: pointer;" value="REGRESAR" onclick="volver()">
										</td>
									</tr>
								</table>
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
