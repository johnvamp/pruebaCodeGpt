<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>:: Portal Operaciones :: Leonisa S.A. ::</title>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
<link href="estilos/transportista.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/ajax/prototype.js"></script>
<script type="text/javascript" src="js/ajax/effects.js"></script>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jqueryRotate.js"></script>
<script type="text/javascript" src="js/jquery.collapse.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="js/jquery.chromatable.js"></script>
<script type="text/javascript"
	src="jsp/transportistas/transportistaSeleccionar.js"></script>
<script type="text/javascript" src="js/jquery.simplePagination.js"></script>

<script type="text/javascript">
	jQuery(function() {
		mostrarCargar();
		var perPage = 250;
		var opened = 1;
		var onClass = 'on';
		var paginationSelector = '.pages';
		jQuery('.gallery').simplePagination(perPage, opened, onClass,
				paginationSelector);

		jQuery("#TableDatos").chromatable({
			width : "100%",
			height : "460px",
			scrolling : "yes"

		});

		function validaIngresoNum(e, funcion) {
			var forma = document.forms[0];
			var val1 = document.getElementById("buscar").value;
			var key = (navigator.appName == "Netscape") ? e.which : e.keyCode;
			if (e.altKey || e.ctrlKey) {
				return false;
			}
			if (key == 13) {
				txtkeypress();
				eval(funcion);
				return false;
			}
			if ((key > 57 | key < 48) & key != 0 & key != 8 & key != 13) {
				return false;
			} else {
				return true;
			}
		}
		$(".reversarInput").click(
				function() {
					if ($(".reversarInput").is(":checked")) {
						$("#reversarButton").removeClass(
								"button-transportista-disabled");
					} else {
						$("#reversarButton").addClass(
								"button-transportista-disabled");
					}
				});

		ocultarCargar();
	});
</script>

</head>
<body onload="mostrarExito('${mensaje}');">
	<div id="url">
		<h2>Transportistas / Seleccionar Transportista</h2>
	</div>
	<form name="form" method="post">
		<input type="hidden" id="buscarTranspor" name="buscarTranspor" /> <input
			type="hidden" id="buscarCampana" name="buscarCampana" /> <input
			type="hidden" id="buscarEstado" name="buscarEstado" /> <input
			type="hidden" id="idTipoTranspo" name="idTipoTranspo" value="" />
		<table align="center">
			<tr>
				<td align="center">
					<div id="mensaje" align="center" class="mensajeUsuario"
						style="display: none;"></div>
				</td>
			</tr>
		</table>

		<table HEIGHT="100">
			<tr>
				<td class="txLabel">Seleccione Transportista</td>
				<td><label> <select id="transportistasComboTranspor"
						name="transportistasComboTranspor">
							<option value="">Seleccione</option>
							<c:forEach items="${transportistasComboTranspor}" var="cate">
								<option value="${cate.value}"
									${cate.value == transportistasComboTransporSelect ? "selected='selected'" : ""}>${cate.key}</option>
							</c:forEach>
					</select>
				</label></td>
				<td class="txLabel">Seleccione La Campaña</td>
				<td><label> <select id="transportistasComboCampana"
						name="transportistasComboCampana">
							<option value="">Seleccione</option>
							<c:forEach items="${transportistasComboCampana}" var="cate">
								<option value="${cate}"
									${cate == transportistasComboCampanaSelect ? "selected='selected'" : ""}>${cate}</option>
							</c:forEach>
					</select>
				</label></td>
				<td class="txLabel">Seleccione El Estado</td>
				<td><label> <select id="transportistasComboEstado"
						name="transportistasComboEstado">
							<option value="">Seleccione</option>
							<c:forEach items="${transportistasComboEstado}" var="cate">
								<option value="${cate.key}"
									${cate.key == transportistasComboEstadoSelect ? "selected='selected'" : ""}>${cate.value}</option>
							</c:forEach>
					</select>
				</label></td>
				<td><a class="button-transportista"
					href="javascript:buscarTransportista()">Buscar</a></td>
			</tr>
		</table>
	</form>

	<c:choose>
		<c:when test="${NRegistros != '0'}">
			<c:choose>
				<c:when test="${NRegistros != ''}">
					<table width="100%" id="table-transportistas">
						<tr>
							<td class="txLabel" style="text-align: left;">Numero de
								Pedidos: ${NRegistros}</td>
							<c:if test="${transportistasComboEstadoSelect == 'E'}">
								<td colspan="2">
									<div>
										<div class="txLabel title-radius">Ingrese número de guía
											con novedad</div>
										<div class="body-find">
											<input type="text" class="form-control" id="numeroGuia"
												name="numeroGuia" onkeyup="buscarGuia()" />
										</div>
									</div>
								</td>
								<td colspan="2">
									<div>
										<div class="txLabel title-radius">Ingrese número de la
											cédula</div>
										<div class="body-find">
											<input type="text" class="form-control" id="numeroCedula"
												name="numeroCedula" onkeyup="buscarGuia()" />
										</div>
									</div>
								</td>
								<td colspan="2">
									<div>
										<div class="txLabel title-radius">Ingrese número de la
											zona</div>
										<div class="body-find">
											<input type="text" class="form-control" id="numeroZona"
												name="numeroZona" onkeyup="buscarGuia()" />
										</div>
									</div>
								</td>
							</c:if>
						</tr>
					</table>
					<div class="tabla7">
						<form id="formCambiarEstado" name="formCambiarEstado"
							method="post" action="transportistas.transporReversarEstado.do">
							<input type="hidden" id="arrayReversar" name="arrayReversar" />
							<table id="TableDatos" width="900" border="0" cellspacing="0"
								cellpadding="0">
								<thead>
									<tr>
										<c:if test="${transportistasComboEstadoSelect == 'E'}">
											<th class="tituloTablaFijos" width="5.5%">Reversar
												Estado</th>
										</c:if>
										<th class="tituloTablaFijos" width="5.5%">Campaña</th>
										<th class="tituloTablaFijos" width="5.5%">N° Orden</th>
										<th class="tituloTablaFijos" width="5.5%">Guia</th>
										<th class="tituloTablaFijos" width="7.5%">Zona</th>
										<th class="tituloTablaFijos" width="5.5%">Cédula</th>
										<th class="tituloTablaFijos" width="15.5%">Nombre</th>
										<th class="tituloTablaFijos" width="5.5%">F.Embarque</th>
										<th class="tituloTablaFijos" width="5.5%">H.Embarque</th>
										<th class="tituloTablaFijos" width="5.5%">1. Visita</th>
										<th class="tituloTablaFijos" width="5.5%">2. Visita</th>
										<th class="tituloTablaFijos" width="5.5%">3. Visita</th>
										<th class="tituloTablaFijos" width="7.5%">Observaciones</th>

									</tr>
								</thead>
								<c:forEach items="${transportistas}" var="obtenida"
									varStatus="status">
									<tr>
										<c:if test="${transportistasComboEstadoSelect == 'E'}">
											<td width="5.5%"><input type="checkbox"
												name="reversarInput" id="reversarInput" class="reversarInput"
												value="${obtenida.numeroGuia}-${obtenida.numeroOrden}" /></td>
										</c:if>
										<td width="5.5%">${obtenida.numeroCampana}</td>
										<td width="5.5%">${obtenida.numeroOrden}</td>
										<td width="5.5%">${obtenida.numeroGuia}</td>
										<td width="7.5%">${obtenida.numeroZona}</td>
										<td width="5.5%">${obtenida.cedula}</td>
										<td width="15.5%">${obtenida.nombre}</td>
										<td width="5.5%">${obtenida.fechaEmbarque}</td>
										<td width="5.5%">${obtenida.horaEmbarque}</td>
										<td width="5.5%">${obtenida.primeraVisita}</td>
										<td width="5.5%">${obtenida.segundaVisita}</td>
										<td width="5.5%">${obtenida.terceraVisita}</td>
										<td width="7.5%">${obtenida.observaciones}</td>
									</tr>
								</c:forEach>
							</table>
						</form>
					</div>

					<table HEIGHT="50" width="100%" border="0" cellspacing="0"
						cellpadding="0" align="center">
						<tr>
							<td align="center"><a class="button-transportista"
								href="javascript:generarExcel()">Exportar a Excel</a></td>
							<c:if test="${transportistasComboEstadoSelect == 'E'}">
								<td align="center"><a
									class="button-transportista button-transportista-disabled"
									id="reversarButton" href="javascript:cambiarEstado()">Reversar
										Estado</a></td>
							</c:if>
						</tr>
					</table>

				</c:when>
			</c:choose>
		</c:when>
	</c:choose>

	<c:choose>
		<c:when test="${NomTrasnpo != ''}">
			<c:choose>
				<c:when test="${ErrorLista == '1'}">
					<table>
						<tr>
							<td class="txLabel">${menErrorLista}</td>
						</tr>
					</table>
				</c:when>
			</c:choose>
		</c:when>
	</c:choose>

	<table width="100%" border="0" align="center" cellpadding="3"
		cellspacing="0">
		<tr>
			<th><iframe id="pie2" name="pie" src="jsp/pie.html"
					scrolling="no" width="100%" height="40" frameborder="0">
					Su navegador no reconoce iframes] </iframe></th>
		</tr>
	</table>
</body>
</html>