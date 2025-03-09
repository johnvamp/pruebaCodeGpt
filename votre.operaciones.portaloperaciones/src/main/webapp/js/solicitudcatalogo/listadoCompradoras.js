function verInformacion(cedula,nroCaso){
	var forma = document.forms[0];
	document.getElementById('cedula').value = cedula; 
	document.getElementById('nroCaso').value = nroCaso; 
	forma.action="compradora.verInformacionCompradora.do";
	forma.submit();	
}

function volver(){
	var forma = document.forms[0];
	forma.action="catalogo.verCatalogo.do";
	forma.submit();
}