function validarDatos(){
	var forma = document.forms[0];
	var causal = document.getElementById('causal').value;
	var numOrden = document.getElementById('txtOrden').value;
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
	
	if(accion == "O"){
		if(causal == "0"){
			mensaje +="Debe seleccionar una causal.\n";
		}	
	}
	if(numOrden == ""){
		mensaje +="Debe ingresar el #Orden o #Guia.\n";
	}
	if(mensaje != ""){
		alert(mensaje);
	}
	else{
		document.getElementById('nomAccion').value = accion;
		forma.action = "entrega.entregaParcial.do";
		forma.submit();
	}
}

function finalizar(){
	var forma = document.forms[0];
	forma.action = "entrega.finalizarEntrega.do";
	forma.submit();
}

function cancelar(){
	var forma = document.forms[0];
	forma.action = "entrega.verEntrega.do";
	forma.submit();
}

function validaIngresoNumParcial(e) {
	var key = (navigator.appName == "Netscape") ? e.which : e.keyCode;
	if(key == 13){
		validarDatos();
		return true;
	}
	else{
		if ((key > 57 | key < 48 ) &  key != 0 & key != 8) {
			return false;
		} else {
			return true;
		}	
	}
}