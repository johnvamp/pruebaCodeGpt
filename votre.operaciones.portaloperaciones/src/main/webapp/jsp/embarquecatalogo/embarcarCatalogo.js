function enviarGuias(){
	var forma = document.forms[0];
	var mensaje = "";
	if(forma.transportadoras.value == ""){
		mensaje += "Debe seleccionar la transportadora.<br>";
	}
	if(forma.guias.value == ""){
		mensaje += "Debe digitar el n�mero de la gu�a.<br>";
	}
	if(mensaje != ""){
		mostrarMensaje(mensaje);
	}
	else{
		forma.action = "embarquecatalogo.enviarGuias.do";
		forma.submit();
	}
}