function modificar(){
	var mensaje = validar();
	if(mensaje == ''){
		var forma = document.forms[0];
		forma.action = "activaciondemandas.cambiarACorreoExtra.do";
		forma.submit();
	}
	else{
		mostrarMensaje(mensaje);
	}
}

function validar(){
	var totalRefe = document.getElementById('totalRefe').value;
	var referencias = '';
	var descripciones = '';
	var banderaRefe = false;
	var mensaje = '';
	for(var i = 1; i <= totalRefe; i++){
		var referencia = document.getElementById('chekRefe_'+i);
		if(referencia.checked){
			banderaRefe = true;
			if(referencias == ''){
				referencias = referencia.value;
			}
			else{
				referencias = referencias + '|' + referencia.value;
			}
			var descripcion = document.getElementById('descripcion_'+i);
			if(descripciones == ''){
				descripciones = descripcion.value;
			}
			else{
				descripciones = descripciones + '|' + descripcion.value;
			}
		}
	}
	document.getElementById('referencias').value = referencias;
	document.getElementById('descripciones').value = descripciones;
	
	if(!banderaRefe){
		mensaje = 'Debe seleccionar minimo una Referencia.';
	}
	
	return mensaje;
}

function regresar(){
	var forma = document.forms[0];
	document.getElementById('accion').value = "B";
	forma.action = "activaciondemandas.consultarReferencias.do";
	forma.submit();
}