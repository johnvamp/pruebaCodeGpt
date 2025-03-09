function validarReimpresion(){
	var forma = document.forms[0];
	var transportador = document.getElementById('transportador').value;
	var camion = document.getElementById('camion').value;
	var mensaje = "";
	
	if(transportador == "0"){
		mensaje += "Debe seleccionar un transportador.\n";
	}
	if(camion == "0"){
		mensaje += "Debe seleccionar un camión.\n";
	}
	
	if(mensaje != ""){
		alert(mensaje);
	}
	else{
		forma.action = "reimprimir.consultar.do";
		forma.submit();
	}
}

function regresar(){
	var forma = document.forms[0];
	forma.action="camion.verConsultar.do";
	forma.submit();
}
