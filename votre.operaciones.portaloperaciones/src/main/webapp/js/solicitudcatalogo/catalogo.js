function mostrarCedula(){
	var campo;
	campo = document.getElementById("divCedula");
	if (campo.visible != "1")
		Effect.SlideDown(campo);
	campo.visible = "1";
	campo = document.getElementById("divNombre");
	if (campo.visible == "1")
		Effect.SlideUp(campo);
	campo.visible = "0";
}

function mostrarNombre(){
	var campo;
	campo = document.getElementById("divCedula");
	if (campo.visible == "1")
		Effect.SlideUp(campo);
	campo.visible = "0";
	campo = document.getElementById("divNombre");
	if (campo.visible != "1")
		Effect.SlideDown(campo);
	campo.visible = "1";
}

function consultar(parametro){
	var forma = document.forms[0];
	if ( validarDatos(parametro) ){
		if(parametro == 'validarCedula'){
			forma.action="compradora.verInformacionCompradora.do";
			forma.submit();	
		}
		else{
			forma.action="compradora.verListadoCompradoras.do";
			forma.submit();	
		}		
	}
}

function validarDatos(parametro){
	var cedula = document.getElementById('cedula').value;
	var nombre = document.getElementById('nombre').value;
	
	if(parametro == 'validarCedula' && cedula == ""){
		alert("Debe digitar una cédula o Código.");
		return false;
	}
	if(parametro == 'validarNombre' && nombre == ""){
		alert("Debe digitar un nombre para la búsqueda.");
		return false;
	}	
	return true;
}