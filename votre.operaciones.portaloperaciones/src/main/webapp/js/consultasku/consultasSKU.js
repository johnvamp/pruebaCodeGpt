function load(){
	var forma = document.forms[0];
	forma.referencia.focus();
}

function consultarReferencias(){
	var forma = document.forms[0];
	var referencia = document.getElementById('referencia').value;
	var bodega = document.getElementById('nomBodega').value;
	var asignoFoco = false;
	var mensaje = "";
	
	if(referencia == ""){
		mensaje += 'Debe digitar una referencia. \n';
		document.getElementById('referencia').focus();
		asignoFoco = true;
	}
	if(bodega==''){
		mensaje += 'Debe seleccionar una bodega. \n';
		if(!asignoFoco){
			document.getElementById('nomBodega').focus();
		}
	}
	if(mensaje != ""){
		alert(mensaje);
	}else{
		forma.action = "consultasku.cargarTitulos.do";
		forma.submit();
	}
}

function limpiarCampos() {	
	var forma = document.forms[0];
	document.getElementById('referencia').value = "";
	document.getElementById('color').value = "";
	document.getElementById('talla').value = "";
	document.getElementById('nomBodega').value = "";
	forma.action = "consultasku.cargarTitulos.do";
	forma.submit();
}

function consultarPrecios(sku){
	var forma = document.forms[0];
	document.getElementById('sku').value = sku;
	forma.action = "consultasku.consultarPrecios.do";
	forma.submit();
}

function consultarAuditoria(sku){
	var forma = document.forms[0];
	document.getElementById('sku').value = sku;
	forma.action = "consultasku.consultarAuditoria.do";
	mostrarCargar();
	forma.submit();
	verificarDesbloqueo();
}

function consultarDescripcion(sku){
	var forma = document.forms[0];
	document.getElementById('sku').value = sku;
	forma.action = "consultasku.consultarDescripcion.do";
	forma.submit();
}

function regresarTitulos(){
	var forma = document.forms[0];
	forma.action = "consultasku.cargarTitulos.do";
	forma.submit();
}