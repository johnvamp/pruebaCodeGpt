function validar()
{
	var forma = document.forms[0];
	if(forma.cedula.value.trim() == ""){
		mostrarMensaje("Debe indicar la identificación.");
		forma.cedula.focus();
		return;
	}
	if(forma.txtRefe.value.trim() == ""){
		mostrarMensaje("Debe indicar la referencia.");
		forma.txtRefe.focus();
		return;
	}
	if(forma.motivo.value == ""){
		mostrarMensaje("Debe indicar el motivo.");
		forma.motivo.focus();
		return;
	}
	if(forma.campana.value.trim() == ""){
		mostrarMensaje("Debe indicar la campaña.");
		forma.campana.focus();
		return;
	}
	if(forma.cantidad.value.trim() == ""){
		mostrarMensaje("Debe indicar la cantidad.");
		forma.cantidad.focus();
		return;
	}
	else{
		if(!esNumero(forma.cantidad.value)){
			mostrarMensaje("Cantidad es invalida.");
			forma.cantidad.focus();
			return;
		}
		else{
			if(parseInt(forma.cantidad.value) == 0){
				mostrarMensaje("Cantidad es invalida.");
				forma.cantidad.focus();
				return;
			}
			if(parseInt(forma.cantidad.value) > 10){
				if(!window.confirm("Confirma que la cantidad ingresada es "+forma.cantidad.value+"?")){
					forma.cantidad.focus();
					return;
				}
			}
		}
	}
	if(forma.area.value == ""){
		mostrarMensaje("Debe indicar el area.");
		forma.area.focus();
		return;
	}
	
	forma.action = "activaciondemandas.validarIngreso.do";
	forma.submit();
}

function capturarArea(){
	var forma = document.forms[0];
	forma.txtArea.value = capturarDescripcion('area');	
}

function capturarMotivo(){
	var forma = document.forms[0];
	forma.txtMotivo.value = capturarDescripcion('motivo');	
}
