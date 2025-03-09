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

<input type="hidden" id="guardarCodReferencia"  name="guardarCodReferencia"/>
<input type="hidden" id="guardarCodTalla"  name="guardarCodTalla"/>
<input type="hidden" id="guardarCodColor"  name="guardarCodColor"/>
<input type="hidden" id="guardarcodZona"  name="guardarcodZona" value=""/>
<input type="hidden" id="guardarCodCompradora"  name="guardarCodCompradora" value=""/>
<input type="hidden" id="guardarcodCantidad"  name="guardarcodCantidad"/>
<input type="hidden" id="guardarTipoCombo"  name="guardarTipoCombo"/>



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
	<a class="boton01" href="javascript:buscarTipoPedido()">Crear</a>
	</td>
</tr>	

   
		<c:choose>
			<c:when test="${status == '1'}">
				<div class="tabla7" align="center">
				<table id="TableDatos2"  width="800" border="0" cellspacing="0" cellpadding="0"> 
				<tr>
					<td class="txLabel">"${pstatus}"</td>			
				</tr>
				</table>
				</div>
			</c:when>
		 </c:choose>
      


</table>
<c:choose>
	<c:when test="${tipoPedidoSelect == 'AD COMPRADORA'}">
		<table>
		<tr>
			<td class="txLabel">Codigo Interno Compradora:	</td>
			<td><input class="required" maxlength="9" type="text" name="codCompradora" id="codCompradora"  size="9" onkeypress="return validaIngresoNum(event);"/></td>
		</tr>
		</table>
		<table>
		<tr>
			<td class="txLabel">Referencia:	</td>
			<td><input class="required" maxlength="6" type="text" name="codReferencia" id="codReferencia" size="6"/></td>
			<td class="txLabel" >Talla:	</td>
			<td><input class="required" maxlength="2" type="text" name="codTalla" id="codTalla"  size="2"/></td>
			<td class="txLabel">Color:	</td>
			<td><input class="required" maxlength="3" type="text" name="codColor" id="codColor"  size="3" onkeypress="return validaIngresoNum(event);"/></td>
		</tr>		
		</table>
		<table>
		<tr>
			<td class="txLabel">Cantidad: </td>
			<td><input class="required" maxlength="2" type="text" name="codCantidad" id="codCantidad"  size="2" onkeypress="return validaIngresoNum(event);"/></td>
		</tr>
		</table>	

		<table>
		<tr>
		<td>
		<a class="boton01" href="javascript:agregarCompradora()">Agregar</a>
		</td>
		</tr>
		</table>
		
		

			
				
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


<c:choose>
	<c:when test="${tipoPedidoSelect == 'MC LIDER'}">
		<table>
		<tr>
			<td class="txLabel">Zona: </td>
			<td><input class="required" maxlength="3" type="text" name="codZona" id="codZona"  size="3" onkeypress="return validaIngresoNum(event);"/></td>
		</tr>
		</table>
		<table>
		<tr>
			<td class="txLabel">Referencia:	</td>
			<td><input class="required" maxlength="6" type="text" name="codReferencia" id="codReferencia" size="6"/></td>
			<td class="txLabel">Talla:	</td>
			<td><input class="required" maxlength="2" type="text" name="codTalla" id="codTalla"  size="2"/></td>
			<td class="txLabel">Color:	</td>
			<td><input class="required" maxlength="3" type="text" name="codColor" id="codColor"  size="3" onkeypress="return validaIngresoNum(event);"/></td>
		</tr>		
		</table>
		<table>
		<tr>
			<td class="txLabel">Cantidad: </td>
			<td><input class="required" maxlength="2" type="text" name="codCantidad" id="codCantidad"  size="2" onkeypress="return validaIngresoNum(event);"/></td>
		</tr>
		</table>	

		<table>
		<tr>
		<td>
		<a class="boton01" href="javascript:agregarLider()">Agregar</a>
		</td>
		</tr>
		</table>
	
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



<c:choose>
	<c:when test="${tipoPedidoSelect == 'MJ REGION'}">
		<table>
		<tr>
			<td class="txLabel">
			Region
			</td>
			<td>
			  	<label>
				<select id="tipoCombo" name="tipoCombo">
					<option value="">Seleccione</option>
					<c:forEach items="${TipoCombo}" var="cate">
						<option value="${cate.key}" ${cate.key == tipoPedidoSelect ? "selected='selected'" : ""}>${cate.value}</option>	
					</c:forEach>
				</select>
				</label>
			</td>
		</tr>		
		</table>
				
		<table>
		<tr>
			<td class="txLabel">Referencia:	</td>
			<td><input class="required" maxlength="6" type="text" name="codReferencia" id="codReferencia" size="6"/></td>
			<td class="txLabel">Talla:	</td>
			<td><input class="required" maxlength="2" type="text" name="codTalla" id="codTalla"  size="2"/></td>
			<td class="txLabel">Color:	</td>
			<td><input class="required" maxlength="3" type="text" name="codColor" id="codColor"  size="3" onkeypress="return validaIngresoNum(event);"/></td>
		</tr>		
		</table>
		<table>
		<tr>
			<td>Cantidad: </td>
			<td><input class="required" maxlength="2" type="text" name="codCantidad" id="codCantidad"  size="2" onkeypress="return validaIngresoNum(event);"/></td>
		</tr>
		</table>	

		<table>
		<tr>
		<td>
		<a class="boton01" href="javascript:agregarRegion()">Agregar</a>
		</td>
		</tr>
		</table>		
		
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




<c:choose>
	<c:when test="${tipoPedidoSelect == 'CM COMANDO'}">
		<table>
		<tr>
			<td class="txLabel">
			Comando
			</td>
			<td>
			  	<label>
				<select id="tipoCombo" name="tipoCombo">
					<option value="">Seleccione</option>
					<c:forEach items="${TipoCombo}" var="cate">
						<option value="${cate.key}" ${cate.key == tipoPedidoSelect ? "selected='selected'" : ""}>${cate.value}</option>	
					</c:forEach>
				</select>
				</label>
			</td>
		</tr>		
		</table>
		
		<table>
		<tr>
			<td class="txLabel">Referencia:	</td>
			<td><input class="required" maxlength="6" type="text" name="codReferencia" id="codReferencia" size="6"/></td>
			<td class="txLabel">Talla:	</td>
			<td><input class="required" maxlength="2" type="text" name="codTalla" id="codTalla"  size="2"/></td>
			<td class="txLabel">Color:	</td>
			<td><input class="required" maxlength="3" type="text" name="codColor" id="codColor"  size="3" onkeypress="return validaIngresoNum(event);"/></td>
		</tr>		
		</table>
		<table>
		<tr>
			<td class="txLabel">Cantidad: </td>
			<td><input class="required" maxlength="2" type="text" name="codCantidad" id="codCantidad"  size="2" onkeypress="return validaIngresoNum(event);"/></td>
		</tr>
		</table>	

		<table>
		<tr>
		<td>
		<a class="boton01" href="javascript:agregarComando()">Agregar</a>
		</td>
		</tr>
		</table>		
		
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


