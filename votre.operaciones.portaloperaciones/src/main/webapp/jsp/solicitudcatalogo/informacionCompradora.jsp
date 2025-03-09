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
<script type="text/javascript" src="js/solicitudcatalogo/informacionCompradora.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Solicitud Catálogos / <span>Información Compradoras</span> </h2>
	</div>
	<form name="forma" method="post">		
		<input type="hidden" name="codCia" id="codCia" value="${codCia}">
		<input type="hidden" name="nombre" id="nombre" value="${nombre}">
		<input type="hidden" name="cedula" id="cedula" value="${cedula}">
		<input type="hidden" name="nombreCompra" id="nombreCompra" value="${nombreCompra}">
		<input type="hidden" name="telefono1" id="telefono1" value="${telefono1}">
		<input type="hidden" name="telefono2" id="telefono2" value="${telefono2}">
		<input type="hidden" name="celular" id="celular" value="${celular}">
		<input type="hidden" name="departamento" id="departamento" value="${departamento}">
		<input type="hidden" name="codigoPostal" id="codigoPostal" value="${codigoPostal}">
		<input type="hidden" name="pais" id="pais" value="${pais}">
		<input type="hidden" name="direccion" id="direccion" value="${direccion}">
		<input type="hidden" name="barrio" id="barrio" value="${barrio}">
		<input type="hidden" name="municipio" id="municipio" value="${municipio}">
		<input type="hidden" name="campana" id="campana" value="">
		<input type="hidden" name="nroCaso" id="nroCaso" value="${nroCaso}">
		<input type="hidden" name="sku" id="sku" value="">
		<input type="hidden" name="descripcion" id="descripcion" value="">
		<input type="hidden" name="cantidad" id="cantidad" value="">
		<input type="hidden" name="parametro" id="parametro" value="${parametro}">
		<input type="hidden" name="nroLinea" id="nroLinea" value="">
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
							<td align="right">
								<c:if test="${codCia!='255'}">
									<input type="button" class="boton01" name="btnModificar" id="btnModificar" style="cursor: pointer;" value="MODIFICAR DATOS" onclick="modificar()">
								</c:if>								
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
							<td>
								<table width="100%" border="0" cellpadding="3" cellspacing="0">												
									<tr>
										<td colspan="4" height="15">												
										</td>
									</tr>
									<tr>
										<td colspan="4" class="titulosContenido">${titulo1Cata}</td>
									</tr>
									<tr>
										<td height="15">
										</td>
									</tr>
									<c:if test="${estadoCata=='0'}">
										<tr>
											<td width="15%" align="left" class="titulosContenido">${titulo2Cata}</td>
											<td width="20%" align="left" class="titulosContenido">${titulo3Cata}</td>
											<td width="10%" align="left" class="titulosContenido">${titulo4Cata}</td>
										</tr>
										<tr>
											<td width="15%" align="left">														
												<select name="codigo" id="codigo" onchange="cargarDatos()" class="combo03">
													<option value="" >-Seleccione-</option>						
													<c:forEach items="${catalogos}" var="catalogo">
														<option value="${catalogo.sku}|${catalogo.descripcion}|${catalogo.cantidad}" >${catalogo.sku}</option>
													</c:forEach>
												</select>
											</td>
											<td width="20%" align="left">	
												<input type="text" name="txtDescripcion" id="txtDescripcion" class="contenido" readonly="readonly" maxlength="30" size="35">
											</td>
											<td width="10%" align="left">
												<input type="text" name="txtCantidad" id="txtCantidad" class="contenido" maxlength="3" size="4" title="Digite la cantidad." onkeypress="return validaIngresoNum(event)"> 
											</td>
											<td width="15%" align="left">														
												<input type="button" class="boton01" name="btnGrabar" id="btnGrabar" style="cursor: pointer;" onclick="grabar()" value="${boton}">
											</td>
										</tr>	
									</c:if>	
									<c:if test="${estadoCata!='0'}">											
										<tr>
											<td colspan="4" align="center" class="titulosContenido">${descripCata}</td>
										</tr>
									</c:if>
								</table>
							</td>
						</tr>
						<c:if test="${estado=='0'}">
							<tr>
								<td>
									<table width="100%" border="0" cellpadding="3" cellspacing="0">
										<tr>
											<td height="15">												
											</td>
										</tr>
										<tr>
											<td class="titulosContenido">${titulo1His}</td>
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
																	<td>${titulo7His}</td>
																	<td>${titulo2His}</td>
																	<td>${titulo4His}</td>
																	<td>${titulo5His}</td>
																	<td>${titulo6His}</td>
																</tr>
																<c:forEach items="${registros}" var="registro">
																	<tr class="filaTabla">
																		<td align="center">
																			<a class="barraSupPrehome" style="cursor: pointer;" href="#" onclick="eliminar('${registro.sku}','${registro.cantidad}','${registro.nroCaso}')">${registro.btnEliminar}</a>
																		</td>
																		<td align="center">
																			<a class="barraSupPrehome" style="cursor: pointer;" href="#" onclick="verDetalle('${registro.campana}','${registro.nroCaso}','${registro.sku}')">${registro.campana}</a>
																		</td>
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
						</c:if>
						<tr>
							<td colspan="2" align="center">		
								<br>									
								<input type="button" class="boton01"  name="regresar" id="regresar" style="cursor: pointer;" value="REGRESAR" onclick="volver()">
								<c:if test="${parametro=='listado'}">
									<input type="submit" class="boton01"  name="volverListado" id="volverListado" style="cursor: pointer;" value="VOLVER LISTADO" onclick="verListado()">
								</c:if>
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
