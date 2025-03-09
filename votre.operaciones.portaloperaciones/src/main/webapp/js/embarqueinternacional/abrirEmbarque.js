function abrir(){
	var forma = document.forms[0];
	var codTrans = document.getElementById('transportador').value;
	var placa = document.getElementById('txtCamion').value;
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
	if(codTrans == "0"){
		mensaje +="Debe seleccionar un transportador.\n"
	}
	if(placa == ""){
		mensaje +="Debe digitar la placa del vehículo.\n"
	}
	if(mensaje == ""){
		document.getElementById('tipoEmbarque').value = accion;
		forma.action="embarque.abrirEmbarque.do";
		forma.submit();
	}
	else{
		alert(mensaje);
	}
}

function regresar(){
	var forma = document.forms[0];
	forma.action="embarque.cargarTitulos.do";
	forma.submit();
}