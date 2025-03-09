function mostrarDiv(){
	document.getElementById('mensaje').style.display = 'none';
	document.getElementById('divBotonA').style.display = 'none';
	document.getElementById('divManual').style.display = 'inline';
}

function mostrarDivAuto(){
	document.getElementById('mensaje').style.display = 'none';
	document.getElementById('divManual').style.display = 'none';
	document.getElementById('divBotonA').style.display = 'inline';
	document.getElementById('divBotonR').style.display = 'none';
	document.getElementById('divBotonC').style.display = 'none';
}

function mostrarDivCedulas(){
	document.getElementById('divReferencias').style.display = 'none';
	document.getElementById('divCedulas').style.display = 'inline';
	document.getElementById('divBotonC').style.display = 'inline';
	document.getElementById('divBotonA').style.display = 'none';
	document.getElementById('divBotonR').style.display = 'none';
}

function asignarAutomatico(){
	var forma = document.forms[0];
	forma.action = "activaciondemandas.asignarTransportadores.do";
	forma.submit();
}

function consultarReferencias(){
	var forma = document.forms[0];
	document.getElementById('accion').value = "R";
	forma.action = "activaciondemandas.consultarReferencias.do";
	forma.submit();
}

function asignarManual(){
	var mensaje = validar();
	if(mensaje == ''){
		var forma = document.forms[0];
		forma.action = "activaciondemandas.asignarTransportadores.do";
		forma.submit();
	}
	else{
		mostrarMensaje(mensaje);
	}
}

function validar(){
	var totalRefe = document.getElementById('totalRefe').value;
	var busquedaRefe = '';
	var banderaRefe = false;
	var mensaje = '';
	for(var i = 1; i <= totalRefe; i++){
		var referencia = document.getElementById('chekRefe_'+i);
		var cantidadDispo = parseInt(document.getElementById('cantidadDispo_'+i).value);
		var cantidad = document.getElementById('cantidad_'+i).value;
		if(referencia.checked){
			banderaRefe = true;
			if(cantidad == ""){
				mensaje = 'Debe digitar la cantidad';
				break;
			}
			else{
				cantidad = parseInt(cantidad);
			}
			if(cantidad > cantidadDispo){
				mensaje = 'La cantidad debe ser menor o igual a la cantidad disponible';
				break;
			}
			if(cantidad == 0){
				mensaje = 'La cantidad debe ser mayor a cero';
				break;
			}
			if(busquedaRefe == ''){
				busquedaRefe = referencia.value + ',' + cantidad;
			}
			else{
				busquedaRefe = busquedaRefe + '|' + referencia.value + ',' + cantidad;
			}
		}
	}
	document.getElementById('valor').value = busquedaRefe;
	
	if(!banderaRefe){
		mensaje += 'Debe seleccionar minimo una Referencia.';
	}
	
	return mensaje;
}

function asignarCedulas(){
	var forma = document.forms[0];
	var listaCedulas = document.getElementById('listaCedulas').value;
	if(listaCedulas != ""){
		document.getElementById('valor').value= listaCedulas;
		document.getElementById('accion').value= 'C';
		forma.action = "activaciondemandas.asignarTransportadores.do";
		forma.submit();
	}
	else{
		mostrarMensaje("Debe ingresar las cedulas.");
	}
}

function generarGuias(){
	var forma = document.forms[0];
	forma.action = "activaciondemandas.generarGuias.do";
	forma.submit();
}

function imprimirGuiasMaster(){
	var forma = document.forms[0];
	forma.action = "activaciondemandas.imprimirGuiasMaster.do";
	forma.submit();
}