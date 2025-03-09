function mostrarDivMasiva(){
	document.getElementById('divIndividual').style.display = 'none';
	document.getElementById('divMasiva').style.display = 'inline';
}

function mostrarDivIndividual(){
	document.getElementById('divMasiva').style.display = 'none';
	document.getElementById('divIndividual').style.display = 'inline';
}

function buscarSku(){
	var forma = document.forms[0];
	var mensaje = '';
	if(forma.refeBuscar.value.trim() == ""){
		mensaje += 'Debe indicar alguna referencia.<br>';
		forma.refeBuscar.focus();
	}
	if(forma.colorBuscar.value.trim() == ""){
		mensaje += 'Debe indicar algún color.<br>';
	}
	if(forma.tallaBuscar.value.trim() == ""){
		mensaje += 'Debe indicar alguna talla.<br>';
	}
	if(mensaje != ''){
		mostrarMensaje(mensaje);
	}
	else{
		forma.tipo.value = "M";
		forma.accion.value = "C";
		forma.action = "activaciondemandas.eliminarEnvios.do";
		forma.submit();
	}
}

function confirmar(){
	if(confirm('Está seguro que desea eliminar los registros?')){
		forma.tipo.value = "M";
		forma.accion.value = "A";
		forma.action = "activaciondemandas.eliminarEnvios.do";
		forma.submit();
	}
	else{
		return false;
	}
}

function buscarCedula(){
	var forma = document.forms[0];
	if(forma.cedulaBuscar.value.trim() != ""){
		forma.tipo.value = "I";
		forma.accion.value = "C";
		forma.action = "activaciondemandas.eliminarEnvios.do";
		forma.submit();
	}
	else{
		mostrarMensaje('Debe digitar la cédula.');
	}
}

function eliminarEnvio(fila){
	var forma = document.forms[0];
	forma.codInterno.value = document.getElementById("codInterno_"+fila).value;
	forma.numOrden.value = document.getElementById("numOrden_"+fila).value;
	forma.sku.value = document.getElementById("sku_"+fila).value;
	forma.tipo.value = "I";
	forma.accion.value = "A";
	forma.action = "activaciondemandas.eliminarEnvios.do";
	forma.submit();
}