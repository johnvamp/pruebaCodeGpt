<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="http://jsptags.com/tags/personal/autorizador" prefix="personal"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>:: Portal Operaciones :: Leonisa S.A. ::</title>

<link href="estilos/estilosMenu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.2.2.pack.js"></script>
<script type="text/javascript" src="js/ddaccordion.js"></script>
<script type="text/javascript" src="js/acordion.js"></script>
<script type="text/javascript" src="js/time.js"></script>

<script type="text/javascript"  src="js/jquery.js"></script>
<script type="text/javascript"  src="js/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="jsp/home.js"></script>
</head>

<body onLoad="goforit()">

<form name="form" method="post">



	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
	    	<td class="name" width="33%"><img src="imagenes/ico_usuario.gif" width="22" height="16" />${sessionScope.USUARIO}</td>
	    </tr>
	   	<tr>
	    	<td id="clock"></td>
	   	</tr>
 	</table>
<div id="top_mm"></div>
<div class="arrowlistmenu">
	<personal:construirMenu menuXml="${menuXml}"></personal:construirMenu>
<!-- 	<a href="activaciondemandas.jspAsignarTransportadores.do" target="contenido">Asignar Transportadores</a> -->
	<div class="Noexpandable"><a href="salir.do" target="_parent">Salida segura</a></div>
</div>  
								
</form>
</body>
</html>
