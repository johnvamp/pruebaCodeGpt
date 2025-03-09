<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="url">
		<h2>Activación de la Demanda / <span>Detalle Caso</span> </h2>
	</div></br>
		<table width="95%" border="0" cellpadding="0" cellspacing="0" align="center">
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
					        			<td class="titulosContenido" align="right" width="15%">Identificación</td>
					        			<td class="contenido" width="35%">${caso.id}</td>
					        			<td class="titulosContenido" align="right" width="15%">Nombre</td>
					        			<td class="contenido" width="35%">${caso.nombre}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right">Código Interno</td>
					        			<td class="contenido">${caso.codInterno}</td>
					        			<td class="titulosContenido" align="right">Orden</td>
					        			<td class="contenido">${caso.orden}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right">Departamento</td>
					        			<td class="contenido">${caso.depto}</td>
					        			<td class="titulosContenido" align="right">Ciudad</td>
					        			<td class="contenido">${caso.ciudad}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right">Región</td>
					        			<td class="contenido">${caso.region}</td>
					        			<td class="titulosContenido" align="right">MailPlan</td>
					        			<td class="contenido">${caso.mailPlan}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right">Zona</td>
					        			<td class="contenido">${caso.zona}</td>
					        			<td class="titulosContenido" align="right">Tipo</td>
					        			<td class="contenido">${caso.tipo}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right">SKU</td>
					        			<td class="contenido">${caso.referencia}</td>
					        			<td class="titulosContenido" align="right">Descripción</td>
					        			<td class="contenido">${caso.descripcion}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right">Cantidad</td>
					        			<td class="contenido">${caso.cantidad}</td>
					        			<td class="titulosContenido" align="right">Campaña origen</td>
					        			<td class="contenido">${caso.campanaCaso}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right">Motivo</td>
					        			<td class="contenido">${caso.motivo}</td>
					        			<td class="titulosContenido" align="right">Area</td>
					        			<td class="contenido">${caso.area}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right">Fecha registro</td>
					        			<td class="contenido">${caso.fechaRegistro}</td>
					        			<td class="titulosContenido" align="right">Estado</td>
					        			<td class="contenido">${caso.dsEstado}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right">SKU sustituto</td>
					        			<td class="contenido">${caso.skuSustituto}</td>
					        			<td class="titulosContenido" align="right">Descripción</td>
					        			<td class="contenido">${caso.dsSkuSustituto}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right">Cantidad entregada</td>
					        			<td class="contenido">${caso.cantidadEntregada}</td>
					        			<td class="titulosContenido" align="right">Tipo despacho</td>
					        			<td class="contenido">${caso.tipoDespacho}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right">Fecha aprobación</td>
					        			<td class="contenido">${caso.fechaAprobacion}</td>
					        			<td class="titulosContenido" align="right">Nro Guía</td>
					        			<td class="contenido">${caso.guia}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right">Transportadora</td>
					        			<td class="contenido">${caso.transportadora}</td>
<!-- 					        			<td class="titulosContenido" align="right">Cédula Transportista</td> -->
<%-- 					        			<td class="contenido">${caso.cedulaTransportista}</td> --%>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right">Dirección despacho</td>
					        			<td class="contenido">${caso.direccionDespacho}</td>
					        			<td class="titulosContenido" align="right">Barrio despacho</td>
					        			<td class="contenido">${caso.barrioDespacho}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right">Ciudad despacho</td>
					        			<td class="contenido">${caso.ciudadDespacho}</td>
					        			<td class="titulosContenido" align="right">Departamento despacho</td>
					        			<td class="contenido">${caso.deptoDespacho}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right">Teléfono despacho</td>
					        			<td class="contenido">${caso.tel1Despacho}</td>
					        			<td class="titulosContenido" align="right">Fecha despacho</td>
					        			<td class="contenido">${caso.fechaDespacho}</td>
					        		</tr>
					        		<tr>
					        			<td class="titulosContenido" align="right">Tarifa</td>
					        			<td class="contenido">${caso.tarifa}</td>
					        			<td class="titulosContenido" align="right">&nbsp;</td>
					        			<td class="contenido">&nbsp;</td>
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
</body>
</html>
