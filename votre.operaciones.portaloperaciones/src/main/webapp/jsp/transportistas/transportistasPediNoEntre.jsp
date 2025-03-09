<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>:: Portal Operaciones :: Leonisa S.A. ::</title>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jqueryRotate.js"></script>
<script type="text/javascript" src="js/jquery.collapse.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="js/jquery.chromatable.js"></script>
<script type="text/javascript"
	src="jsp/transportistas/transportistasPediNoEntre.js"></script>
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

		jQuery("#TableDatos2").chromatable({
			width : "100%",
			height : "200px",
			scrolling : "yes"

		});

		jQuery("#TableDatos1").chromatable({
			width : "100%",
			height : "160px",
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

		ocultarCargar();
	});
</script>

</head>
<body>
	<div id="url">
		<h2>Transportistas / Pedidos No Entregados</h2>
	</div>

	<form name="form" method="post">
		<input type="hidden" id="idTipoTranspo" name="idTipoTranspo" value="" />
		<input type="hidden" id="idTipoFlujo" name="idTipoFlujo" value="" />
		<input type="hidden" id="numeroOrden" name="numeroOrden" /> 
		<input type="hidden" id="numeroGuia" name="numeroGuia" /> 
		<input type="hidden" id="buscarGuia" name="buscarGuia" /> 
		<input type="hidden" id="pEstBuscar" name="pEstBuscar" /> 
		<input type="hidden" id="idTipoExcel" name="idTipoExcel" /> 
		<input type="hidden" id="buscarCampana" name="buscarCampana" /> 
		<input type="hidden" id="cedulaBuscar" name="cedulaBuscar" />
		<input type="hidden" id="tipoConsulta"  name="tipoConsulta"/>

		<table>
			<tr>
				<td>
					<div>
						<h2 class="tituloDatos">Transportista: ${NomTranspo}</h2>
					</div>
				</td>
				<td></td>
			</tr>
			<tr>
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
				<td><input type="button" class="boton01" onclick="buscarTransportista('N','')" value="Buscar" /></td>
			</tr>
		</table>

		<table width="100%" class="tabla7">
			<tr>
				<td valign="top">
					<table width="60%" style="width: 326px">
						<tr>
							<th class="tituloTablaFijos">
								<p>Ingrese Número de Guía con Novedad</p>
							</th>
							<th class="tituloTablaFijos">
								<p>Ingrese Número de la Cedula</p>
							</th>
							<th class="tituloTablaFijos">
								<p>Ingrese Número de la Zona</p>
							</th>
						</tr>
						<tr>
							<td class="tablesinbor">
								<table width="100%">
									<tr>
										<td><input class="required" maxlength="15" type="text"
											name="buscar" id="buscar" size="10"
											onkeypress="return validaIngresoNum(event);" /></td>
										<td><input type="button" class="boton01" onclick="buscarTransportista('N','G')" value="Buscar" /></td>
									</tr>
								</table>
							</td>
							<td class="tablesinbor">
								<table width="100%">
									<tr>
										<td><input class="required" type="text"
											name="buscarCedula" id="buscarCedula" size="10"
											onkeypress="return validaIngresoNum(event);" /></td>
										<td><input type="button" class="boton01" onclick="buscarTransportista('N','C')" value="Buscar" /></td>
									</tr>
								</table>
							</td>
							<td class="tablesinbor">
								<table width="100%">
									<tr>
										<td><input maxlength="3" class="required" type="text"
											name="buscarZona" id="buscarZona" size="10"
											onkeypress="return validaIngresoNum(event);" /></td>
										<td><input type="button" class="boton01" onclick="buscarTransportista('N','')" value="Buscar" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
				<td></td>
			</tr>
		</table>

		<c:choose>
			<c:when test="${transportistasComboCampanaSelect != null}">
				<c:choose>
					<c:when test="${transportistasComboCampanaSelect != ''}">
						<div>
							<h2 class="tituloDatos">Total Pedidos: ${TotalPedi}</h2>
						</div>
					</c:when>
				</c:choose>
			</c:when>
		</c:choose>

		<c:choose>
			<c:when test="${codigoZona != null}">
				<c:choose>
					<c:when test="${codigoZona != ''}">
						<div>
							<h2 class="tituloDatos">Zona: ${codigoZona}</h2>
						</div>
					</c:when>
				</c:choose>
			</c:when>
		</c:choose>

		<c:choose>
			<c:when test="${ErrorGuia == '1'}">
				<table id="tablaError" width="100%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td class="tituloTablaFijos" width="5.5%">&nbsp;${MsgErrorGuia}</td>
					</tr>
				</table>
			</c:when>
		</c:choose>

		<c:choose>
			<c:when test="${ErrorCedual == '1'}">
				<table id="tablaError" width="100%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td class="tituloTablaFijos" width="5.5%">&nbsp;${MsgErrorCedual}</td>
					</tr>
				</table>
			</c:when>
		</c:choose>

		<c:choose>
			<c:when test="${NRegistros != '0'}">
				<c:choose>
					<c:when test="${NRegistros != ''}">
						<div class="tabla7">
							<table id="TableDatos2" width="900" border="0" cellspacing="0"
								cellpadding="0">
								<thead>
									<tr>
										<th class="tituloTablaFijos" width="5.5%">Campaña</th>
										<th class="tituloTablaFijos" width="5.5%">N° Orden</th>
										<th class="tituloTablaFijos" width="5.5%">Guia</th>
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
										<td width="5.5%">${obtenida.numeroCampana}</td>
										<td width="5.5%">${obtenida.numeroOrden}</td>
										<td width="5.5%">${obtenida.numeroGuia}</td>
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
						</div>

						<table HEIGHT="50" width="100%" border="0" cellspacing="0"
							cellpadding="0" align="center">
							<tr>
								<td align="center"><a class="boton01"
									href="javascript:generarExcel('N')">Exportar a Excel</a></td>
							</tr>
						</table>
					</c:when>
				</c:choose>
			</c:when>
		</c:choose>

	</form>
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


