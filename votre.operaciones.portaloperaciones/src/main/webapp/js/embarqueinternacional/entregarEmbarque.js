function validarDatos(){
	var forma = document.forms[0];
	var transportador = document.getElementById('transportador').value;
	var camion = document.getElementById('camion').value;
	var radio = document.getElementsByName('accion');
	var accion = "";
	var mensaje = "";
	
	for (var i=0; i<radio.length; i++) {
		if (radio[i].checked) {
			accion= radio[i].value;
		}
	}
	if(accion == ""){
		mensaje +="Debe seleccionar una opción.\n"
	}
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
		if(accion == "P"){
			forma.action = "entrega.verEntregaParcial.do";
			forma.submit();
		}
		if(accion == "T"){
			forma.action = "entrega.verEntregaTotal.do";
			forma.submit();
		}
	}
}

function verEntregaExcel(){
	var forma = document.forms[0];
	forma.action = "entrega.jspEntregaExcel.do";
	forma.submit();
}

function regresar(){
	var forma = document.forms[0];
	forma.action="embarque.cargarTitulos.do";
	forma.submit();
}