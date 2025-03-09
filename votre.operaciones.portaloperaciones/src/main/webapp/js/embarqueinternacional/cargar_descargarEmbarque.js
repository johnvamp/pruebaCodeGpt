function load(){
	var forma = document.forms[0];
	forma.txtOrden.focus();
}

function validarOrden(accion){
	var forma = document.forms[0];
	var orden = document.getElementById('txtOrden').value;
	if(orden == ""){
		alert('Debe ingresar el #Orden o #Guia.');
		document.getElementById('txtOrden').focus();	
	}
	else{
		if(accion == "embarcar"){
			forma.action="embarque.cargarEmbarque.do";
			forma.submit();	
		}
		if(accion == "desembarcar"){
			forma.action="embarque.desembarcar.do";
			forma.submit();	
		}
	}
}

function detenerEmbarque(){
	var forma = document.forms[0];
	forma.action="embarque.detenerEmbarque.do";
	forma.submit();
}

function finalizar(){
	var forma = document.forms[0];
	forma.action = "embarque.cargarTitulos.do";
	forma.submit();
}

function regresar(){
	var forma = document.forms[0];
	document.getElementById('nomAccion').value = "descargarEmbarque";
	forma.action = "embarque.verAccionesEmbarque.do";
	forma.submit();
}

function validaIngresoNum2(e) {
	var key = (navigator.appName == "Netscape") ? e.which : e.keyCode;
	var accion = document.getElementById('ejecutarAccion').value;
	if(key == 13){
		validarOrden(accion);
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