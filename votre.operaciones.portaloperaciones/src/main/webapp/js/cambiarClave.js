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
		mensaje += "Debe ingresar la informaci�n del campo Usuario.\n";
		document.getElementById('usu').focus();
	}
	if(password == ""){
		mensaje += "Debe ingresar la informaci�n del campo Contrase�a.\n";
		document.getElementById('psw').focus();
	}
	if(newPassword == ""){
		mensaje += "Debe ingresar la informaci�n del campo Nueva Contrase�a.\n";
		document.getElementById('newpsw').focus();
	}
	if(conPassword == ""){
		mensaje += "Debe ingresar la informaci�n del campo Confirmar Contrase�a.\n";
		document.getElementById('newpsw').focus();
	}
	
	if(newPassword != conPassword){
		mensaje += "Las contrase�as no coinciden.\n";
	}
	
	if(mensaje == ""){
		forma.action = "cambiarClave.do";
		forma.submit();
	}
	else{
		alert(mensaje);
	}
}