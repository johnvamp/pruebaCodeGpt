<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<!-- <meta http-equiv="X-UA-Compatible" content="IE=8" charset="UTF-8"/> -->
<title>:: Portal Operaciones :: Leonisa S.A. ::</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/ajax/prototype.js"></script>
<script type="text/javascript" src="js/ajax/effects.js"></script>

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>

<script type="text/javascript" src="js/utilidades.js"></script>
<script type="text/javascript" src="js/reprocesos/reprocesos.js"></script>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body bgcolor="#f4f4f4">
	<form name="forma" method="post">
		<table width="60%" height="250" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%" align="center" colspan="2">
					<table width="300" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
					        <td class="cajaGris01"><img src="imagenes/spacer.gif" alt="img" width="9" height="9" /></td>
					        <td class="bgcajaGris"></td>
					        <td class="cajaGris02"><img src="imagenes/spacer.gif" alt="img" width="9" height="9" /></td>
					    </tr>
					    <tr>
					        <td class="bgcajaGris">&nbsp;</td>
					        <td class="bgcajaGris">
					        	<table width="330" height="100" border="0" cellpadding="0" cellspacing="0" align="center">
					        		<tr>
										<td colspan="2" width="100%" align="center" class="titulos">Rechazar Solicitud</td>
									</tr>
									<tr>
				                        <td style="padding-left: 10%">
				                       		<label class="txLabel" >Comentarios <a style="color: red;">*</a></label>&nbsp;&nbsp;
										</td>
				                    </tr>
				                    <tr>
										<td colspan="2" width="100%" align="center">
											<textarea rows="4" cols="45" id="comentario" name="comentario" style="font-family: Arial; font-size: 12px;"></textarea>
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
	    	<tr>
	    		<td height="10">
	    		</td>
	    	</tr>
	    	<tr>
	    		<td align="center">
	    			<input type="button" class="boton01" id="bntGuardar" name="btnGuardar" value="GUARDAR" onclick="rechazarSolicitud()" />
	    		</td>
	    	</tr>				       
		</table>
	</form>
</body>
</html>