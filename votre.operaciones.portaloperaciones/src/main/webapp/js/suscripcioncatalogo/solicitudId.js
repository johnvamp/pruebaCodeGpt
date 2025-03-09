function load(){
	var forma = document.forms[0];
	forma.cedula.focus();
}

function validarId(){
	var forma = document.forms[0];
	var cedula = document.getElementById('cedula').value;
	if(cedula == ""){
		alert('Debe ingresar la identificación.');
		document.getElementById('cedula').focus();	
	}
	else{
		forma.action="suscripciones.consultarCompradora.do";
		forma.submit();	
	}
}

function validaIngresoId(e) {
	var key = (navigator.appName == "Netscape") ? e.which : e.keyCode;
	if(key == 13){
		validarId();
		return true;
	}
	else{
		if ((key > 57 | key < 48 ) &  key != 0 & key != 8) {
			return false;
		} else {
			return true;
		}	
	}
}