<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>:: Portal Operaciones :: Leonisa S.A. ::</title>
<link href="estilos/estilosCabezote.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/time.js"></script>


<!--[if lt IE 7]>
<script defer type="text/javascript" src="../js/png.js"></script>
<![endif]-->

</head>

<body onLoad="goforit()">

<!--<div id="cab">-->
<!--  <div align="right"><img src="imagenes/logo_leonisa.png" width="160" height="96" /></div>-->
<!--</div>-->
<div id="name">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td width="33%"><img src="imagenes/ico_usuario.gif" width="22" height="16" />${sessionScope.USUARIO}</td>
      <!-- <td width="37%"><img src="images/ico_col.gif" width="22" height="16" />Colombia</td>  -->
      <td id="clock" align="right"></td>
    </tr>
  </table>
</div>

</body>
</html>
