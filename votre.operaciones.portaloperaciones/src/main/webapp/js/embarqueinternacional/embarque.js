function procesar() {
	var forma = document.forms[0];
	var radio = document.getElementsByName('accion');	
	var accion = '';
	for (var i=0; i<radio.length; i++) {
		if (radio[i].checked) {
			accion= radio[i].value;
		}
	}
	if(accion==''){
		alert("Debe seleccionar una opción.")
		return false;
	}
	if(accion == "abrirEmbarque"){
		forma.action="embarque.verAbrirEmbarque.do";
		forma.submit();
		return true;
	}
	if(accion == "capturarEmbarque" || accion == "cerrarEmbarque" || accion == "descargarEmbarque"){
		document.getElementById('nomAccion').value = accion;
		forma.action="embarque.verAccionesEmbarque.do";
		forma.submit();
		return true;
	}
	if(accion == "entregarEmbarque"){
		forma.action="entrega.verEntrega.do";
		forma.submit();
		return true;
	}
	if(accion == "consultar"){
		forma.action="camion.verConsultar.do";
		forma.submit();
		return true;
	}
	
}

function consultar(){
	var forma = document.forms[0];
	var radio = document.getElementsByName('opcion');	
	var accion = '';
	for (var i=0; i<radio.length; i++) {
		if (radio[i].checked) {
			accion= radio[i].value;
		}
	}
	if(accion==''){
		alert("Debe seleccionar una opción.")
		return false;
	}
	if(accion == 'C'){
		forma.action="camion.consultarCamionesAbiertos.do";
		forma.submit();	
	}
	if(accion == 'R'){
		forma.action="reimprimir.verReimprimir.do";
		forma.submit();	
	}
}

function regresar(){
	var forma = document.forms[0];
	forma.action="embarque.cargarTitulos.do";
	forma.submit();
}