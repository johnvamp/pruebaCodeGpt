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
<script type="text/javascript"  src="jsp/flujoWms/flujoWmsDetalle.js"></script>
<script type="text/javascript"  src="js/jquery.simplePagination.js"></script>


<script type="text/javascript">

		jQuery(function(){
			mostrarCargar();		
		    var perPage = 250;
			var opened = 1;
			var onClass = 'on';
			var paginationSelector = '.pages';
			jQuery('.gallery').simplePagination(perPage, opened, onClass, paginationSelector);       
		
		
		jQuery("#TableDatos").chromatable({
				width: "100%",
				height: "570px",
				scrolling: "yes"				
				
			});	

		ocultarCargar();
		});
	
		
</script>






</head>
<body>

	<div id="url">
		<h2>Flujo Operativo / 
		<c:choose>
				    <c:when test="${tipoFlujo == '1'}">
				    <span>Pedido Sin Consolidar</span>
				    </c:when>
				      <c:when test="${tipoFlujo == '2'}">
				    <span>Pedidos Sin Embarcar</span>
				    </c:when>
		</c:choose>		
		</h2>
	</div>

<form name="form" method="post">


<input type="hidden" id="idTipoFlujo"  name="idTipoFlujo"  value="" /> 
<input type="hidden" id="idTipoTranspo"  name="idTipoTranspo" value=""/> 
<input type="hidden" id="idOpcSelect"  name="idOpcSelect"  value="" /> 



	<ul>
 <li> <table width="99%">
	     	<thead>
	          <tr>      	          	
	           		<td class="tituloTablaFijos" width="7.5%">${titulo1}</td>
	            	<td class="tituloTablaFijos" width="3.5%">${titulo2}</td>
			     	<td class="tituloTablaFijos" width="5.5%">${titulo3}</td>
					<td class="tituloTablaFijos" width="6.5%">${titulo4}</td>
					<td class="tituloTablaFijos" width="5.5%">${titulo5}</td>
					<td class="tituloTablaFijos" width="7.5%">${titulo6}</td>
					<td class="tituloTablaFijos" width="17%">${titulo7}</td>
					<td class="tituloTablaFijos" width="5.5%">${titulo8}</td>
					<td class="tituloTablaFijos" width="5.5%">${titulo9}</td>	            
	            	<td class="tituloTablaFijos" width="5.5%">${titulo10}</td>
					<td class="tituloTablaFijos" width="7.5%">${titulo11}</td>
					<td class="tituloTablaFijos" width="5%">${titulo12}</td>
					<td class="tituloTablaFijos" width="3%">${titulo13}</td>	   
	          </tr>
        	</thead>
    </table> 
   </li>
   </ul> 
           	
<table id="TableDatos" width="100%">  


 	<tr>
      	<td>
       	<ul class="gallery" >
	       	<c:forEach items="${flujowms}" var="obtenida" varStatus="status" >
		     <li><table width="95%">
		     	<tr> 
			       	<td width="7%">${obtenida.orden}</td>
			       	<td width="4.5%">${obtenida.estado}</td>
			     	<td width="4.5%">${obtenida.pickt}</td>
					<td width="6.5%">${obtenida.fecha}</td>
					<td width="5.5%">${obtenida.hora}</td>
					<td width="6%">${obtenida.cedula}</td>
					<td width="15%">${obtenida.nombre}</td>
					<td width="5.5%">${obtenida.proceso}</td>
					<td width="5.5%">${obtenida.fechahoraDT}</td>						
					<td width="4%">${obtenida.tipoCom}</td>		
					<td width="7.5%">${obtenida.transpo}</td>		
					<td width="4%">${obtenida.zona}</td>		
					<td width="3%">${obtenida.mp}</td>					
					
					       	
		       	</tr>
		       	</table>	
		     </li>
		
	       </c:forEach>
	       </ul> 
	  	</td>
    </tr>


</table>

<table width="100%"  align="center">
<tr align="center">
<td>
<div align="center" class="pages"></div>
</td>
</tr>
</table>
<table width="100%"  align="center"> 
<tr  align="center">
<td><a class="boton01" href="javascript:generarExcel('${tipoFlujo}')">Exportar a Excel</a></td>

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


