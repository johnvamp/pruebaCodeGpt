jQuery.noConflict();

function load(accion){
	var forma = document.forms[0];
	if(accion == 'I'){
		forma.cantidad.focus();
		document.getElementById("ingresoCantidad").style.display = "inline";
		document.getElementById("editaCantidad").style.display = "none";
	}
	else{
		document.getElementById("ingresoCantidad").style.display = "none";
		document.getElementById("editaCantidad").style.display = "inline";
	}
}

function validarCantidad(){
	var forma = document.forms[0];
	var cantidad = document.getElementById('cantidad').value;
	if(cantidad == ""){
		alert('Debe ingresar la cantidad.');
		document.getElementById('cantidad').focus();	
	}
	else{
		if(parseInt(cantidad) > 12){
			if(window.confirm("Confirma que la cantidad de catálogos a ingresar es "+cantidad+"?")){
				forma.action="suscripciones.ingresarCatalogos.do";
				forma.submit();	
			}
		}
		else{
			forma.action="suscripciones.ingresarCatalogos.do";
			forma.submit();	
		}
	}
}

function editar(){
	var forma = document.forms[0];
	document.getElementById("ingresoCantidad").style.display = "inline";
	document.getElementById("editaCantidad").style.display = "none";
	forma.cantidad.focus();
}

function cancelarSuscripcion(){
	var forma = document.forms[0];
	if(window.confirm("Estas segura de deseas cancelar la suscripción a "+forma.cantidad.value+" catálogos \n Recuerda un catálogo más es una vitrina más que puede ayudar a crecer tu negocio.")){
		forma.action="suscripciones.cancelarCatalogos.do";
		forma.submit();	
		
	}
}

function validaIngresoCantidad(e) {
	var key = (navigator.appName == "Netscape") ? e.which : e.keyCode;
	if(key == 13){
		validarId();
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

function verPoliticas(){
	var forma = document.forms[0];
	var url = 'suscripciones.jspPoliticas.do';
	jQuery.fancybox({
		href : url,
		type : 'inline',
		width : '570',
		height : '560',
		padding : 1,
		modal : false
	});
	
}