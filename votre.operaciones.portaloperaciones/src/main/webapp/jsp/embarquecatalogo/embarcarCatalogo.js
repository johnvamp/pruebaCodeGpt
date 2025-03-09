function enviarGuias(){
	var forma = document.forms[0];
	var mensaje = "";
	if(forma.transportadoras.value == ""){
		mensaje += "Debe seleccionar la transportadora.<br>";
	}
	if(forma.guias.value == ""){
		mensaje += "Debe digitar el número de la guía.<br>";
	}
	if(mensaje != ""){
		mostrarMensaje(mensaje);
	}
	else{
		forma.action = "embarquecatalogo.enviarGuias.do";
		forma.submit();
	}
}