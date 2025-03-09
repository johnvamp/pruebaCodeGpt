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
<script type="text/javascript" src="js/activaciondemandas/ingresos/formIngreso.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Activación de la Demanda / <span>Ingreso</span> </h2>
	</div>
	<form name="forma" method="post">
		<input type="hidden" id="txtMotivo" name="txtMotivo" value="${novedad.dsMotivo}" />
		<input type="hidden" id="txtArea" name="txtArea" value="${novedad.dsArea}" />
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
					        			<td class="titulosContenido" align="right" width="50%">IDENTIFICACIÓN:</td>
					        			<td width="50%">
					        				<input type="text" id="cedula" name="cedula" value="${novedad.cmpDemandaDTO.id}" class="campo02" maxlength="30" onkeypress="return validarTextoGeneral(event);"/>
					        			</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right" width="50%">SKU:</td>
					        			<td width="50%">
					        				<input type="text" id="txtRefe" name="txtRefe" value="${novedad.refeDemandaDTO.referencia}" class="campoW" maxlength="7" size="7" onkeypress="return validarTextoGeneral(event);"/>
					        				<input type="text" id="txtColor" name="txtColor" value="${novedad.refeDemandaDTO.color}" class="campoW" maxlength="3" size="3" onkeypress="return validarTextoGeneral(event);"/>
					        				<input type="text" id="txtTalla" name="txtTalla" value="${novedad.refeDemandaDTO.talla}" class="campoW" maxlength="3" size="3" onkeypress="return validarTextoGeneral(event);"/>
					        			</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right" width="50%">SKU SUSTITUO:</td>
					        			<td width="50%">
					        				<input type="text" id="txtRefeSusti" name="txtRefeSusti" value="${refeSusti}" class="campoW" maxlength="7" size="7" onkeypress="return validarTextoGeneral(event);"/>
					        				<input type="text" id="txtColorSusti" name="txtColorSusti" value="${colorSusti}" class="campoW" maxlength="3" size="3" onkeypress="return validarTextoGeneral(event);"/>
					        				<input type="text" id="txtTallaSusti" name="txtTallaSusti" value="${tallaSusti}" class="campoW" maxlength="3" size="3" onkeypress="return validarTextoGeneral(event);"/>
					        			</td>
					        		</tr>
					        		<tr>
					        			<td colspan="2" align="center">
					        				<a class="titulosContenido">Si requiere SKU sustituto, por favor ingresarlo, de lo contrario dejar en blanco</a>
					        			</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right" width="50%">MOTIVO:</td>
					        			<td width="50%">
					        				<select id="motivo" name="motivo" class="combo02" onchange="capturarMotivo()">
					        					<option value="" >-Seleccione-</option>						
												<c:forEach items="${motivos}" var="mot">
													<option value="${mot.id}" ${novedad.motivo == mot.id ? 'selected':'' }>${mot.nombre}</option>
												</c:forEach>
					        				</select>
					        			</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right" width="50%">CAMPAÑA PREMIO:</td>
					        			<td width="50%">
					        				<input type="text" id="campana" name="campana" value="${novedad.refeDemandaDTO.campana}" class="campo01" maxlength="4" onkeypress="return validarNumeroGeneral(event);"/>
					        			</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right" width="50%">CANTIDAD:</td>
					        			<td width="50%">
					        				<input type="text" id="cantidad" name="cantidad" value="${novedad.cantidad}" class="campo01" maxlength="4" onkeypress="return validarNumeroGeneral(event);"/>
					        			</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right" width="50%">AREA INGRESO:</td>
					        			<td width="50%">
					        				<select id="area" name="area" class="combo02" onchange="capturarArea()">
					        					<option value="" >-Seleccione-</option>						
												<c:forEach items="${areas}" var="mot">
													<option value="${mot.id}" ${novedad.area == mot.id ? 'selected':'' }>${mot.nombre}</option>
												</c:forEach>
					        				</select>
					        			</td>
					        		</tr>
					        		<tr>
										<td align="center" colspan="2">
											<input type="button" class="boton01" name="btnAceptar" id="btnAceptar" style="cursor: pointer;" onclick="return validar();" value="CONSULTAR" />
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
