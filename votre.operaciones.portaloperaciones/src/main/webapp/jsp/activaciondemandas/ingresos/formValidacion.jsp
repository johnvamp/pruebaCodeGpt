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
<script type="text/javascript" src="js/activaciondemandas/ingresos/formValidacion.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Activación de la Demanda / <span>Ingreso</span> </h2>
	</div>
	<form name="forma" method="post">
		<input type="hidden" id="confirmacion" name="confirmacion" value="${novedad.tipo}" />
		<input type="hidden" id="cedula" name="cedula" value="${novedad.cmpDemandaDTO.id}" />
		<input type="hidden" id="sku" name="sku" value="${novedad.refeDemandaDTO.sku}" />
		<input type="hidden" id="skuSusti" name="skuSusti" value="${skuSusti}" />
		<input type="hidden" id="txtRefe" name="txtRefe" value="${novedad.refeDemandaDTO.referencia}" />
		<input type="hidden" id="txtColor" name="txtColor" value="${novedad.refeDemandaDTO.color}" />
		<input type="hidden" id="txtTalla" name="txtTalla" value="${novedad.refeDemandaDTO.talla}" />
		<input type="hidden" id="txtRefeSusti" name="txtRefeSusti" value="${refeSusti}" />
		<input type="hidden" id="txtColorSusti" name="txtColorSusti" value="${colorSusti}" />
		<input type="hidden" id="txtTallaSusti" name="txtTallaSusti" value="${tallaSusti}" />
		<input type="hidden" id="motivo" name="motivo" value="${novedad.motivo}" />
		<input type="hidden" id="campana" name="campana" value="${novedad.refeDemandaDTO.campana}" />
		<input type="hidden" id="cantidad" name="cantidad" value="${novedad.cantidad}" />
		<input type="hidden" id="area" name="area" value="${novedad.area}" />
		<input type="hidden" id="codInterno" name="codInterno" value="${novedad.cmpDemandaDTO.codInterno}" />

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
					        			<td width="50%" class="contenido">&nbsp;${novedad.cmpDemandaDTO.id}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right" width="50%">NOMBRE:</td>
					        			<td width="50%" class="contenido">&nbsp;${novedad.cmpDemandaDTO.nombre}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right" width="50%">UBICACIÓN:</td>
					        			<td width="50%" class="contenido">&nbsp;${novedad.cmpDemandaDTO.ciudad}&nbsp;${novedad.cmpDemandaDTO.depto}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right" width="50%">REGIÓN:</td>
					        			<td width="50%" class="contenido">&nbsp;${novedad.cmpDemandaDTO.region}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right" width="50%">MAIL PLAN:</td>
					        			<td width="50%" class="contenido">&nbsp;${novedad.cmpDemandaDTO.mailPlan}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right" width="50%">TIPO:</td>
					        			<td width="50%" class="contenido">&nbsp;${novedad.cmpDemandaDTO.tipo}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right" width="50%">ZONA:</td>
					        			<td width="50%" class="contenido">&nbsp;${novedad.cmpDemandaDTO.zona}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right" width="50%">CÓDIGO INTERNO:</td>
					        			<td width="50%" class="contenido">&nbsp;${novedad.cmpDemandaDTO.codInterno}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right" width="50%">SKU:</td>
					        			<td width="50%" class="contenido">&nbsp;${novedad.refeDemandaDTO.sku}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right" width="50%">SKU SUSTITUTO:</td>
					        			<td width="50%" class="contenido">&nbsp;${skuSusti}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right" width="50%">MOTIVO</td>
					        			<td width="50%" class="contenido">&nbsp;${novedad.dsMotivo}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right" width="50%">CAMPAÑA PREMIO:</td>
					        			<td width="50%" class="contenido">&nbsp;${novedad.refeDemandaDTO.campana}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right" width="50%">CANTIDAD</td>
					        			<td width="50%" class="contenido">&nbsp;${novedad.cantidad}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right" width="50%">AREA INGRESO</td>
					        			<td width="50%" class="contenido">&nbsp;${novedad.dsArea}</td>
					        		</tr>
					        		<tr>
										<td align="center" colspan="2">
											<input type="button" class="boton01" name="btnAceptar" id="btnAceptar" style="cursor: pointer;" onclick="return grabarNovedad();" value="${TITULO_GUARDAR}" />&nbsp;
											<input type="button" class="boton01" name="btnAceptar" id="btnAceptar" style="cursor: pointer;" onclick="return regresar();" value="REGRESAR" />
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
