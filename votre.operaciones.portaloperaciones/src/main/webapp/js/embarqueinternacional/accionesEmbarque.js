function validarEmbarque(){
	var forma = document.forms[0];
	var transportador = document.getElementById('transportador').value;
	var camion = document.getElementById('camion').value;
	var accion = document.getElementById('accion').value;
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
		if(accion == "capturarEmbarque"){
			forma.action = "embarque.verCargarEmbarque.do";
			forma.submit();
		}
		if(accion == "cerrarEmbarque"){
			forma.action = "embarque.verCerrarEmbarque.do";
			forma.submit();
		}
		if(accion == "descargarEmbarque"){
			forma.action = "embarque.verDesembarque.do";
			forma.submit();
		}
	}
}

function regresar(){
	var forma = document.forms[0];
	forma.action="embarque.cargarTitulos.do";
	forma.submit();
}