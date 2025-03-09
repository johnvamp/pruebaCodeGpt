<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=10" charset="UTF-8"/>
<title>:: Portal Operaciones :: Leonisa S.A. ::</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="js/ajax/prototype.js"></script>
<script type="text/javascript" src="js/ajax/effects.js"></script>

<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="js/pedidoscsl/pedidoscsl.js"></script>
<script type="text/javascript" src="js/ajax/ajaxManual.js"></script>

<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');">
	<div id="url">
		<h2>
			Pedidos CSL 33 / <span>Crear Solicitud / Pedido Individual</span>
		</h2>
	</div>
	<form name="forma" method="post">
		<input type="hidden" id="accion" name="accion" value="${accion}" /> <input
			type="hidden" id="tipoPedido" name="tipoPedido" value="${tipoPedido}" />
		<input type="hidden" id="desPedido" name="desPedido"
			value="${desPedido}" /> <input type="hidden" id="destinatario"
			name="destinatario" value="${destinatario}" /> <input type="hidden"
			id="desEstrategia" name="desEstrategia" value="${desEstrategia}" />
		<input type="hidden" id="nroRegistros" name="nroRegistros" value="0" />
		<input type="hidden" id="accionSiguiente" name="accionSiguiente"
			value="I">
		<table align="center">
			<tr>
				<td align="center">
					<div id="mensaje" align="center" class="mensajeUsuario"
						style="display: none;"></div>
				</td>
			</tr>
		</table>
		<br>
		<table width="60%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="100%" align="center" colspan="2">
					<table width="600" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td class="cajaGris01"><img src="imagenes/spacer.gif"
								alt="img" width="9" height="9" /></td>
							<td class="bgcajaGris"></td>
							<td class="cajaGris02"><img src="imagenes/spacer.gif"
								alt="img" width="9" height="9" /></td>
						</tr>
						<tr>
							<td class="bgcajaGris">&nbsp;</td>
							<td class="bgcajaGris">
								<table width="750" height="81" border="0" cellpadding="0"
									cellspacing="0" align="center">
									<tr>
										<td width="100%" align="center" class="titulos">Creación
											Pedido Individual</td>
									</tr>
									<tr>
										<td height="15"></td>
									</tr>
									<tr>
										<td align="center"><label class="titulosContenido">Tipo
												de Pedido:</label><label class="txLabel"> ${desPedido}</label></td>
									</tr>
									<tr>
										<td align="center"><label class="titulosContenido">Estratregia:</label><label
											class="txLabel"> ${desEstrategia}</label></td>
									</tr>
									<tr>
										<td height="15"></td>
									</tr>
									<tr>
										<td>
											<table width="100%" cellpadding="0" cellspacing="0"
												align="center">
												<tr class="tituloTabla">
													<c:if test="${destinatario == 'COMPRADORA'}">
														<td>Código Interno Compradora</td>
													</c:if>
													<c:if test="${destinatario == 'REGION'}">
														<td>Región</td>
													</c:if>
													<c:if test="${destinatario == 'LIDER'}">
														<td>Zona</td>
													</c:if>
													<c:if test="${destinatario == 'COMANDO'}">
														<td>Comando</td>
													</c:if>
													<td>Referencia</td>
													<td>Color</td>
													<td>Talla</td>
													<td>Cantidad</td>
													<td>Centro de Costos</td>
													<td></td>
												</tr>
												<tr class="filaTabla">
													<c:if test="${destinatario == 'COMPRADORA'}">
														<td><input type="text" id="val1" name="val1"
															class="campo02" maxlength="10"
															style="text-align: center;"
															onkeypress="return validarTextoGeneral(event);" /></td>
													</c:if>
													<c:if test="${destinatario == 'REGION'}">
														<td><select id="val1" name="val1" class="selects">
																<option value="">Seleccione</option>
																<c:forEach items="${arrCombo}" var="region">
																	<option value="${region}">${region}</option>
																</c:forEach>
														</select></td>
													</c:if>
													<c:if test="${destinatario == 'LIDER'}">
														<td><select id="val1" name="val1" class="selects">
																<option value="">Seleccione</option>
																<c:forEach items="${arrCombo}" var="zona">
																	<option value="${zona}">${zona}</option>
																</c:forEach>
														</select></td>
													</c:if>
													<c:if test="${destinatario == 'COMANDO'}">
														<td><select id="val1" name="val1" class="selects">
																<option value="">Seleccione</option>
																<c:forEach items="${arrComandos}" var="comando">
																	<option value="${comando.cedulaComando}">${comando.nombreComando}</option>
																</c:forEach>
														</select></td>
													</c:if>
													<td><input type="text" id="txtRefe" name="txtRefe"
														class="campoW" maxlength="7" size="7"
														style="text-align: center;"
														onkeypress="return validarTextoGeneral(event);" /></td>
													<td><input type="text" id="txtColor" name="txtColor"
														class="campoW" maxlength="3" size="3"
														style="text-align: center;"
														onkeypress="return validarTextoGeneral(event);" /></td>
													<td><input type="text" id="txtTalla" name="txtTalla"
														class="campoW" maxlength="3" size="3"
														style="text-align: center;"
														onkeypress="return validarTextoGeneral(event);" /></td>
													<td><input type="text" id="cantidad" name="cantidad"
														class="campo01" maxlength="5" style="text-align: center;"
														onkeypress="return validarNumeroGeneral(event);" /></td>
													<td><input type="text" id="centroCostos"
														name="centroCostos" class="campo01" maxlength="5"
														style="text-align: center;"
														onkeypress="return validarNumeroGeneral(event);" /></td>
													<td><input type="button" class="boton01"
														style="cursor: pointer;" onclick="validarSolcitud();"
														value="AGREGAR" /></td>
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
			<tr>
				<td height="10"></td>
			</tr>
		</table>
		<div id="divSolicitudes" style="display: none;">
			<table width="750" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td height="15"></td>
				</tr>
				<tr>
					<td width="100%" align="center" class="titulos">Resumen
						Solicitud</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td height="10"></td>
							</tr>
							<tr>
								<td class="caja01"></td>
								<td class="caja02"></td>
								<td class="caja03"></td>
							</tr>
							<tr>
								<td class="caja04"></td>
								<td align="center">
									<table id="tSolicitudes" width="100%" border="1"
										cellpadding="0" cellspacing="0" align="center">
										<thead>
											<tr class="tituloTabla">
												<c:if test="${destinatario == 'COMPRADORA'}">
													<td>Código Interno Compradora</td>
												</c:if>
												<c:if test="${destinatario == 'REGION'}">
													<td>Región</td>
												</c:if>
												<c:if test="${destinatario == 'LIDER'}">
													<td>Zona</td>
												</c:if>
												<c:if test="${destinatario == 'VOTRE'}">
													<td>Destinatario</td>
												</c:if>
												<c:if test="${destinatario == 'COMANDO'}">
													<td>Comando</td>
												</c:if>
												<td>Referencia</td>
												<td>Color</td>
												<td>Talla</td>
												<td>Descripción</td>
												<td>Cantidad</td>
												<td>Centro de Costos</td>
												<td></td>
											</tr>
										</thead>
										<tbody>
										</tbody>
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
						<table width="50%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td height="10"></td>
							</tr>
							<tr>
								<td align="center"><input type="button" class="boton01"
									value="GRABAR SOLICITUD" onclick="grabarPedidos();" /></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td height="10"></td>
				</tr>
			</table>
		</div>
		<table width="60%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="center"><input type="button" class="boton01"
					value="REGRESAR" onclick="regresarListaPedidos();" /></td>
			</tr>
			<tr>
				<td height="10"></td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="3"
			cellspacing="0">
			<tr>
				<th><iframe id="pie2" name="pie" src="jsp/pie.html"
						scrolling="no" width="100%" height="40" frameborder="0">
						Su navegador no reconoce iframes] </iframe></th>
			</tr>
		</table>
	</form>
</body>
</html>