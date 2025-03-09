function load(){
	var forma = document.forms[0];
	forma.usu.focus();
}

function guardarClave(){
	var forma = document.forms[0];
	var usuario = document.getElementById('usu').value;
	var password = document.getElementById('psw').value;
	var newPassword = document.getElementById('newpsw').value;
	var conPassword = document.getElementById('conpsw').value;
	
	var mensaje = "";
	
	if(usuario == ""){
		mensaje += "Debe ingresar la información del campo Usuario.\n";
		document.getElementById('usu').focus();
	}
	if(password == ""){
		mensaje += "Debe ingresar la información del campo Contraseña.\n";
		document.getElementById('psw').focus();
	}
	if(newPassword == ""){
		mensaje += "Debe ingresar la información del campo Nueva Contraseña.\n";
		document.getElementById('newpsw').focus();
	}
	if(conPassword == ""){
		mensaje += "Debe ingresar la información del campo Confirmar Contraseña.\n";
		document.getElementById('newpsw').focus();
	}
	
	if(newPassword != conPassword){
		mensaje += "Las contraseñas no coinciden.\n";
	}
	
	if(mensaje == ""){
		forma.action = "cambiarClave.do";
		forma.submit();
	}
	else{
		alert(mensaje);
	}
}