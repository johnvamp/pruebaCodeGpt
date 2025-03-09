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
<script type="text/javascript" src="js/cambiarClave.js"></script>
<script type="text/javascript" src="js/ajax/ajaxManual.js"></script>
<link href="estilos/estilosLogin.css" rel="stylesheet" type="text/css" />
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="javascript:load();mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<form id="form1" name="form1" method="post">
		<div id="cab">
		</div>	
		<br>
		<div id="center">
			<table width="100%" border="0" cellspacing="0" cellpadding="3">
				<tr>
			   		<td height="95" colspan="2" valign="top"></td>
			    </tr>
				<tr>
					<td width="41%" class="label">Usuario:</td>
					<td width="59%"><input type="text" class="campoLog" name="usu" id="usu" maxlength="30"  onkeypress="return validarTextoGeneral(event);"></td>
				</tr>
				<tr>
					<td class="label">Contraseña Actual:</td>
					<td><input type="password" class="campoLog" name="psw" id="psw" maxlength="50"/></td>
				</tr>
				<tr>
					<td class="label">Nueva Contraseña:</td>
					<td><input type="password" class="campoLog" name="newpsw" id="newpsw" maxlength="50"/></td>
				</tr>
				<tr>
					<td class="label">Confirmar Contraseña:</td>
					<td><input type="password" class="campoLog" name="conpsw" id="conpsw" maxlength="50"/></td>
				</tr>
				<tr>
					<th colspan="3">&nbsp;
						<input align="middle" type="button" value="GUARDAR" name="btnCambiar" class="botonLog" onclick="guardarClave();" title="Guardar."/>
					</th>					
				</tr>
				<tr>
					<td colspan="2" height="15">
						<div id="mensaje" align="center" class="mensajeLogin" ></div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
