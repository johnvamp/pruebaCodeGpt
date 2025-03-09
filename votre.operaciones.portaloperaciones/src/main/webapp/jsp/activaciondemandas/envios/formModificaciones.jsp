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
<script type="text/javascript" src="js/calendario/calendar.js"></script>
<script type="text/javascript" src="js/calendario/calendar-es.js"></script>
<script type="text/javascript" src="js/calendario/calendar-setup.js"></script>
<script type='text/javascript' src="js/jquery-1.7.1.min.js"></script>
<script type='text/javascript' src="js/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/fancybox/jquery.fancybox-1.3.4.js"></script>
<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="js/activaciondemandas/envios/formModificaciones.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
<link href="estilos/calendar-system.css" rel="stylesheet" type="text/css" media="all" title="win2k-cold-1" />
<link href="js/fancybox/jquery.fancybox-1.3.4.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body onload="mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<div id="url">
		<h2>Activación de la Demanda / <span>Modificaciones de Envíos</span> </h2>
	</div>
	<form id="formApr" name="forma" method="post">
		<input type="hidden" id="consultar" name="consultar" value="1" />
		<input type="hidden" id="cantidadEnvios" name="cantidadEnvios" value="${cantidadEnvios}" />
		<input type="hidden" id="paginaActual" name="paginaActual" value="${busqueda.paginaActual}" />
		<input type="hidden" id="SELECCION_REFERENCIA" name="SELECCION_REFERENCIA" value="${SELECCION_REFERENCIA}" />
		<table align="center">
			<tr>
				<td align="center">
				 	<div id="mensaje" align="center" class="mensajeUsuario" style="display:none;"></div>
				</td>
			</tr>
		</table></br>
		<table width="80%" border="0" cellpadding="0" cellspacing="0" align="center">
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
					        			<td class="titulosContenido" align="right" width="40%">SELECCIÓN</td>
					        			<td>
					        				<select id="seleccionBuscar" name="seleccionBuscar" class="combo04" onchange="mostrarValor('${SELECCION_TODO}')">
					        					<option value="" >-Seleccione-</option>						
												<c:forEach items="${opciones}" var="mot">
													<option value="${mot.id}" ${busqueda.seleccion == mot.id ? 'selected':'' }>${mot.nombre}</option>
												</c:forEach>
					        				</select>
					        			</td>
					        		</tr>
					        		<tr id="trValor" style="display:${busqueda.seleccion == SELECCION_TODO ? 'none':''}">
					        			<td class="titulosContenido" align="right">DATO BUSCAR:</td>
					        			<td>
					        				<div id="divValor" style="display:${busqueda.seleccion == SELECCION_REFERENCIA ? 'none':''}">
						        				<input type="text" id="opcionBuscar" name="opcionBuscar" value="${busqueda.valor}" class="campo02" maxlength="30" onkeypress="return validarTextoGeneral(event);"/>
						        			</div>	
					        				<div id="divSku" style="display:${busqueda.seleccion == SELECCION_REFERENCIA ? '':'none'}">
						        				<input type="text" id="refeBuscar" name="refeBuscar" value="${busqueda.refeSku}" class="campoW" maxlength="7" size="7" onkeypress="return validarTextoGeneral(event);"/>
						        				<input type="text" id="colorBuscar" name="colorBuscar" value="${busqueda.colorSku}" class="campoW" maxlength="3" size="3" onkeypress="return validarTextoGeneral(event);"/>
						        				<input type="text" id="tallaBuscar" name="tallaBuscar" value="${busqueda.tallaSku}" class="campoW" maxlength="3" size="3" onkeypress="return validarTextoGeneral(event);"/>
						        			</div>	
					        			</td>
					        		</tr>
					        		<tr>
										<td align="center" colspan="2">
											<input type="button" class="boton01" name="btnAceptar" id="btnAceptar" style="cursor: pointer;" onclick="return consultarEnvios('${SELECCION_TODO}','1');" value="CONSULTAR" />
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
		</table></br>
	</form>
		<c:if test="${!empty envios}">
			<c:if test="${busqueda.totalPaginas > 1}">
				<table width="60%" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td align="center">
							<a href="javascript:consultarEnvios('${SELECCION_TODO}','1')" class="linkPaginacion">
								<img src="imagenes/fl_first.png" border="0" title="Primera"/>
							</a>
							<a href="javascript:consultarEnvios('${SELECCION_TODO}','${busqueda.paginaActual - 1}')" class="linkPaginacion">
								<img src="imagenes/fl_prev.png" border="0" title="Anterior"/>
							</a>
							<c:if test="${busqueda.paginaActual < busqueda.totalPaginas}">
								<a href="javascript:consultarEnvios('${SELECCION_TODO}','${busqueda.paginaActual + 1}')" class="linkPaginacion">
									<img src="imagenes/fl_next.png" border="0" title="Siguiente"/>
								</a>
								<a href="javascript:consultarEnvios('${SELECCION_TODO}','${busqueda.totalPaginas}')" class="linkPaginacion">
									<img src="imagenes/fl_last.png" border="0" title="Ultima"/>
								</a>
							</c:if>
							</br>Página ${busqueda.paginaActual} / ${busqueda.totalPaginas}
						</td>
					</tr>
				</table>
			</c:if>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
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
						        			<th width="4%" class="tituloTablaFijos">Cédula</th>
						        			<th width="10%" class="tituloTablaFijos">Nombre</th>
						        			<th width="3%" class="tituloTablaFijos">Zona</th>
						        			<th width="9%" class="tituloTablaFijos">SKU</th>
						        			<th width="3%" class="tituloTablaFijos">Cantidad Entregada</th>
						        			<th width="5%" class="tituloTablaFijos">Nro Guia</th>
						        			<th width="5%" class="tituloTablaFijos">Transportadora</th>
						        			<th width="5%" class="tituloTablaFijos">Transportista</th>
						        			<th width="9%" class="tituloTablaFijos">Fecha Embarque</th>
						        			<th width="2%" class="tituloTablaFijos">&nbsp;</th>
						        		</tr>
						        		<c:forEach items="${envios}" var="det" varStatus="fila">
					        				<input type="hidden" id="id_${fila.count}" name="id_${fila.count}" value="${det.id}"/>
					        				<input type="hidden" id="codInterno_${fila.count}" name="codInterno_${fila.count}" value="${det.codInterno}"/>
					        				<input type="hidden" id="nombre_${fila.count}" name="nombre_${fila.count}" value="${det.nombre}"/>
					        				<input type="hidden" id="ciudad_${fila.count}" name="ciudad_${fila.count}" value="${det.ciudad}"/>
					        				<input type="hidden" id="depto_${fila.count}" name="depto_${fila.count}" value="${det.depto}"/>
					        				<input type="hidden" id="region_${fila.count}" name="region_${fila.count}" value="${det.region}"/>
					        				<input type="hidden" id="mailPlan_${fila.count}" name="mailPlan_${fila.count}" value="${det.mailPlan}"/>
					        				<input type="hidden" id="tipo_${fila.count}" name="tipo_${fila.count}" value="${det.tipo}"/>
					        				<input type="hidden" id="zona_${fila.count}" name="zona_${fila.count}" value="${det.zona}"/>
					        				<input type="hidden" id="sku_${fila.count}" name="sku_${fila.count}" value="${det.referencia}"/>
					        				<input type="hidden" id="campana_${fila.count}" name="campana_${fila.count}" value="${det.campanaCaso}"/>
					        				<input type="hidden" id="descripcion_${fila.count}" name="descripcion_${fila.count}" value="${det.descripcion}"/>
					        				<input type="hidden" id="cantidad_${fila.count}" name="cantidad_${fila.count}" value="${det.cantidad}"/>
					        				<input type="hidden" id="motivo_${fila.count}" name="motivo_${fila.count}" value="${det.motivo}"/>
					        				<input type="hidden" id="area_${fila.count}" name="area_${fila.count}" value="${det.area}"/>
					        				<input type="hidden" id="fechaRegistro_${fila.count}" name="fechaRegistro_${fila.count}" value="${det.fechaRegistro}"/>
					        				<input type="hidden" id="campanaReclamo_${fila.count}" name="campanaReclamo_${fila.count}" value="${det.campanaReclamo}"/>
					        				<input type="hidden" id="estado_${fila.count}" name="estado_${fila.count}" value="${det.estado}"/>
					        				<input type="hidden" id="dsEstado_${fila.count}" name="dsEstado_${fila.count}" value="${det.dsEstado}"/>
					        				<input type="hidden" id="orden_${fila.count}" name="orden_${fila.count}" value="${det.orden}"/>
					        				<input type="hidden" id="skuSustituto_${fila.count}" name="skuSustituto_${fila.count}" value="${det.skuSustituto}"/>
					        				<input type="hidden" id="dsSkuSustituto_${fila.count}" name="dsSkuSustituto_${fila.count}" value="${det.dsSkuSustituto}"/>
					        				<input type="hidden" id="cantidadEntregada_${fila.count}" name="cantidadEntregada_${fila.count}" value="${det.cantidadEntregada}"/>
					        				<input type="hidden" id="tipoDespacho_${fila.count}" name="tipoDespacho_${fila.count}" value="${det.tipoDespacho}"/>
					        				<input type="hidden" id="fechaAprobacion_${fila.count}" name="fechaAprobacion_${fila.count}" value="${det.fechaAprobacion}"/>
					        				<input type="hidden" id="guia_${fila.count}" name="guia_${fila.count}" value="${det.guia}"/>
					        				<input type="hidden" id="transportadora_${fila.count}" name="transportadora_${fila.count}" value="${det.transportadora}"/>
					        				<input type="hidden" id="cedulaTransportista_${fila.count}" name="cedulaTransportista_${fila.count}" value="${det.cedulaTransportista}"/>
					        				<input type="hidden" id="direccionDespacho_${fila.count}" name="direccionDespacho_${fila.count}" value="${det.direccionDespacho}"/>
					        				<input type="hidden" id="barrioDespacho_${fila.count}" name="barrioDespacho_${fila.count}" value="${det.barrioDespacho}"/>
					        				<input type="hidden" id="ciudadDespacho_${fila.count}" name="ciudadDespacho_${fila.count}" value="${det.ciudadDespacho}"/>
					        				<input type="hidden" id="deptoDespacho_${fila.count}" name="deptoDespacho_${fila.count}" value="${det.deptoDespacho}"/>
					        				<input type="hidden" id="tel1Despacho_${fila.count}" name="tel1Despacho_${fila.count}" value="${det.tel1Despacho}"/>
					        				<input type="hidden" id="tarifa_${fila.count}" name="tarifa_${fila.count}" value="${det.tarifa}"/>
					        				<input type="hidden" id="fechaDespacho_${fila.count}" name="fechaDespacho_${fila.count}" value="${det.fechaDespacho}"/>

							        		<tr id="tr_${fila.count}" class="filaTabla11">
							        			<td class="${fila.count % 2 == 0 ? 'cel1' : 'cel2'}"><a href="javascript:verDetalleCaso('${fila.count}')">${det.id}</a></td>
							        			<td class="${fila.count % 2 == 0 ? 'cel1' : 'cel2'}">${det.nombre}</td>
							        			<td class="${fila.count % 2 == 0 ? 'cel1' : 'cel2'}">${det.zona}</td>
							        			<td class="${fila.count % 2 == 0 ? 'cel1' : 'cel2'}">${det.referencia}</td>
							        			<td class="${fila.count % 2 == 0 ? 'cel1' : 'cel2'}">${det.cantidadEntregada}</td>
							        			<td class="${fila.count % 2 == 0 ? 'cel1' : 'cel2'}">
							        				<input type="text" id="txtGuia_${fila.count}" name="txtGuia_${fila.count}" value="${det.guia}" class="campoW" maxlength="15" size="12" onkeypress="return validarTextoGeneral(event);"/>
						        				</td>
							        			<td class="${fila.count % 2 == 0 ? 'cel1' : 'cel2'}">
							        				<select id="cbTransportadora_${fila.count}" name="cbTransportadora_${fila.count}" class="combo01" onchange="limpiarTransportista('${fila.count}')">
							        					<option value="" >-Seleccione-</option>						
														<c:forEach items="${transportadoras}" var="mot">
															<option value="${mot.id}" ${mot.id == det.transportadora ? 'selected':''}>${mot.nombre}</option>
														</c:forEach>
							        				</select>
							        			</td>
							        			<td class="${fila.count % 2 == 0 ? 'cel1' : 'cel2'}">
							        				<select id="cbTransportista_${fila.count}" name="cbTransportista_${fila.count}" class="combo01" onchange="limpiarTransportadora('${fila.count}')">
							        					<option value="" >-Seleccione-</option>						
														<c:forEach items="${transportistas}" var="mot">
															<option value="${mot.id}" ${mot.id == det.cedulaTransportista ? 'selected':''}>${mot.nombre}</option>
														</c:forEach>
							        				</select>
							        			</td>
							        			<td class="${fila.count % 2 == 0 ? 'cel1' : 'cel2'}">
							        				<input type="text" id="txtFecha_${fila.count}" name="txtFecha_${fila.count}" value="${det.fechaDespacho}" class="campoW" maxlength="10" size="8" readonly="readonly"/>
							        				<img src="imagenes/ico_calendar.gif" alt="Calendario" width="16" height="16" align="absmiddle" id="calendario_${fila.count}" style="cursor:hand"/>
							        			</td>
							        			<td class="${fila.count % 2 == 0 ? 'cel1' : 'cel2'}">
							        				<div id="divProcesar_${fila.count}">
								        				<a href="javascript:realizarModificacion('${fila.count}')">
								        					<img src="imagenes/ico_guardar.png" border="0" width="14" height="14" title="Modificar"/>
								        				</a>
							        				</div>
							        			</td>
							        		</tr>
						        		</c:forEach>
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
		</c:if>
	<table width="100%" border="0" align="center" cellpadding="3" cellspacing="0">
  		<tr>
    		<th><iframe id="pie2" name="pie" src="jsp/pie.html" scrolling="no" width="100%" height="40" frameborder="0"> Su navegador no reconoce iframes] </iframe></th>
  		</tr>
	</table>
<script type="text/javascript">
	var cant = jQuery("#cantidadEnvios").val();
	for(i=1; i<=cant; i++){
	    Calendar.setup({
	        inputField     :    "txtFecha_"+i,     // id of the input field
	        ifFormat       :    "%Y%m%d",     // format of the input field (even if hidden, this format will be honored)
	        displayArea    :    "show_d",       // ID of the span where the date is to be shown
	        daFormat       :    "%A, %B %d, %Y",// format of the displayed date
	        align          :    "Tl",           // alignment (defaults to "Bl")
	        button         :    "calendario_"+i,
	        singleClick    :    true
	    });
	}
</script>	
</body>
</html>
