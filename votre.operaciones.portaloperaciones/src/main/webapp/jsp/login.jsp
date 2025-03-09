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
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/ajax/ajaxManual.js"></script>
<link href="estilos/estilosLogin.css" rel="stylesheet" type="text/css" />
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body onload="javascript:load();mostrarMensaje('${mensaje}');" ondragenter="cancelEvent()" ondragover="cancelEvent()">
	<form id="form1" name="form1" method="post" onsubmit="return validar(this)">
		<div id="cab">
			<div align="right">
				<p>&nbsp;</p>
				<p><img src="imagenes/logo_leonisa.png" width="160" height="96" /></p>
			</div>
		</div>	
		<input type="hidden" name="intento" id="intento" value="${intento }">
		<input type="hidden" name="parametro" id="parametro" value="">
		<br>
		<div id="center">
			<table width="100%" border="0" cellspacing="0" cellpadding="3">
				<tr>
			   		<td height="95" colspan="2" valign="top"></td>
			    </tr>
				<tr>
					<td width="41%" class="label">Usuario:</td>
					<td width="59%"><input type="text" class="campoLog" name="usu" id="usu" maxlength="30"  onkeypress="return validarTextoGeneral(event);" onblur="cargarCompanias(this,0);"></td>
				</tr>
				<tr>
					<td class="label">Contraseña:</td>
					<td><input type="password" class="campoLog" name="psw" id="psw" maxlength="50"/></td>
				</tr>
				<tr>
					<td class="label">Compañia:</td>
					<td align="left">
						<select name="cia" id="cia" class="comboLog">
							<option value="0" >-Seleccione-</option>
						</select>
					</td>
				</tr>
				<!--<tr>
					<td class="label">País:</td>
					<td align="left">
						<select name="codCia" id="codCia" class="combo03">
								<option value="0" >-Seleccione-</option>						
							<c:forEach items="${paises}" var="pais">
								<option value="${pais.codCia}-${pais.nombrePais}" >${pais.nombrePais}</option>
							</c:forEach>
						</select>
					</td>
				</tr>-->
				<tr>
					<th colspan="2">&nbsp;
						<input align="middle" type="submit" value="INGRESAR" name="btnLogin" class="botonLog" title="Ingresar."/>
							&nbsp;
						<input align="middle" type="reset" value="BORRAR" name="btnBorrar" class="botonLog" onclick="borrar();" title="Borrar."/>
					</th>					
				</tr>
				<tr>
					<th colspan="2">
						<a href="#" class="barraSupPrehomeLog" onclick="abrir();" title="Cambiar Contraseña." >Cambiar Contraseña</a>
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
