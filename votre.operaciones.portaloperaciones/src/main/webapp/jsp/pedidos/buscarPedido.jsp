<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>:: Portal Operaciones :: Leonisa S.A. ::</title>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />


<script type="text/javascript"  src="js/jquery.js"></script>
<script type="text/javascript"  src="js/jqueryRotate.js"></script>
<script type="text/javascript"  src="js/jquery.collapse.js"></script>
<script type="text/javascript"  src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript"  src="js/jquery.blockUI.js"></script>
<script type="text/javascript"  src="js/utilidades.js"></script>
<script type="text/javascript"  src="js/jquery.chromatable.js"></script>
<script type="text/javascript"  src="jsp/pedidos/tipoPedidoSeleccionar.js"></script>


<script type="text/javascript">
		
</script>

</head>
<body>

	<div id="url">
		<h2>Pedidos  / Seleccionar Tipo de Pedido	</h2>
	</div>	

<form name="form" method="post">

<input type="hidden" id="buscarTipoPedido"  name="buscarTipoPedido"/>
<input type="hidden" id="numeroPedido"  name="numeroPedido"/>

<table HEIGHT="100">

<tr>
	<td class="txLabel">
	Seleccione Tipo de Pedido 
	</td>
	<td>
	  	<label>
		<select id="tipoPedido" name="tipoPedido">
			<option value="">Seleccione</option>
			<c:forEach items="${TipoPedido}" var="cate">
				<option value="${cate.key}" ${cate.key == tipoPedidoSelect ? "selected='selected'" : ""}>${cate.value}</option>	
			</c:forEach>
		</select>
		</label>
	</td>
	<td>
	<a class="boton01" href="javascript:buscarPedidoTipo()">Buscar</a>
	</td>
	
	<td class="txLabel" width="100"></td>
	<td class="txLabel">Nor Pedido:	</td>
	<td><input class="required" maxlength="12" type="text" name="numeroPedidoBuscar" id="numeroPedidoBuscar"  size="12" onkeypress="return validaIngresoNumBuscar(event);"/></td>
	<td><a class="boton01" href="javascript:buscarPedidoNumero()">Buscar</a>
	</td>
</tr>	


</table>
		<c:choose>
			<c:when test="${status == '1'}">
				<table>
				<tr>
					<td>"${pstatus}"</td>			
				</tr>
				</table>
			</c:when>
		 </c:choose>
			

	<c:choose>
	  <c:when test="${listarDetalleCompradora != null}">				
    	 <div class="tabla7">
			<table id="TableDatos2" width="900" border="0" cellspacing="0" cellpadding="0">
	    		<thead>
		      		<tr>
		      			<th class="tituloTablaFijos">obtenida.dsNumPed</th>  
		       			<th class="tituloTablaFijos">obtenida.dsOrdPed</th>
			           	<th class="tituloTablaFijos">obtenida.dsCedd</th>
				     	<th class="tituloTablaFijos">obtenida.dsSku</th>
						<th class="tituloTablaFijos">obtenida.dsUnip</th>
						<th class="tituloTablaFijos">obtenida.dsEstado</th>
						<th class="tituloTablaFijos">obtenida.dsUsu</th>
						<th class="tituloTablaFijos">obtenida.dsFecha</th>
						<th class="tituloTablaFijos">obtenida.dsHora</th>
						<th class="tituloTablaFijos">obtenida.dsSec</th>	            
			           	<th class="tituloTablaFijos">obtenida.dsZona</th>													 
					</tr>
				</thead>
			<c:forEach items="${listarDetalleCompradora}" var="obtenida" varStatus="status" >
					<tr> 
					   	<td>${obtenida.dsNumPed}</td>
						<td>${obtenida.dsOrdPed}</td>
						<td>${obtenida.dsCedd}</td>														
						<td>${obtenida.dsSku}</td>
						<td>${obtenida.dsUnip}</td>
						<td>${obtenida.dsEstado}</td>
						<td>${obtenida.dsUsu}</td>
						<td>${obtenida.dsFecha}</td>
						<td>${obtenida.dsHora}</td>
						<td>${obtenida.dsSec}</td>
						<td>${obtenida.dsZona}</td>								
					</tr>
	       	</c:forEach>
			</table>
		</div>
		</c:when>
		</c:choose>		
		
<table width="100%" border="0" align="center" cellpadding="3" cellspacing="0">
	<tr>
    	<td height="100" ></td>
  	</tr>
</table>
	
<table width="100%" border="0" align="center" cellpadding="3" cellspacing="0">
	<tr>
    	<th><iframe id="pie2" name="pie" src="jsp/pie.html" scrolling="no" width="100%" height="40" frameborder="0"> Su navegador no reconoce iframes] </iframe></th>
  	</tr>
</table>



</form>
</body>
</html>


