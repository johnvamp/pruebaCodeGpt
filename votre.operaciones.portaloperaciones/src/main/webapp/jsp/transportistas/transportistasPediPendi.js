function buscarTransportista(pEst, tipoConsult){
	var forma = document.forms[0];
	var Campana = document.getElementById("transportistasComboCampana").value;
	var guiaBuscar = document.getElementById("buscar").value;
	var cedulaBuscar = document.getElementById("buscarCedula").value;
	var zonaBuscar = document.getElementById("buscarZona").value;
	forma.buscarCampana.value = Campana;
	forma.pEstBuscar.value = pEst;
	forma.buscarGuia.value = guiaBuscar;
	forma.buscarCedula.value = cedulaBuscar;
	forma.buscarZona.value = zonaBuscar;
	forma.tipoConsulta.value = tipoConsult;
	forma.action = "transportistas.transportistasPediPendi.do";
	forma.target = "contenido";
	mostrarCargar();
	forma.submit();
}

function validaIngresoNum(e,funcion) {
	var key = (navigator.appName == "Netscape") ? e.which : e.keyCode;
	if (key < 48 | key > 57) {
		return false;
	} else {
		return true;
	}
}

 function generarExcel(tipoExcel){
	var forma = document.forms[0];
	var Campana = document.getElementById("transportistasComboCampana").value;	
	forma.buscarCampana.value = Campana;	
	forma.idTipoExcel.value = tipoExcel;
	forma.action= "transportistas.transportistasXLS.do";	
	mostrarCargar();
	forma.submit();	
}


function mostrarCargar(){

	jQuery.blockUI({
		message: '... PROCESANDO ...',
		css: { 
            border: 'none', 
            padding: '20px', 
            backgroundColor: '#fff', 
            '-webkit-border-radius': '10px', 
            '-moz-border-radius': '10px', 
            opacity: .4, 
            color: '#000' 
        }
	});
	
	setTimeout(jQuery.unblockUI, 7000);
}

function ocultarCargar(){
	jQuery.unblockUI();
}

function idTransportistasCombo(){
	var forma = document.forms[0];
	//forma.submit();		
}

function guardarTransportista(fila){
	var forma = document.forms[0];
	var Campana = document.getElementById("transportistasComboCampana").value;
	var numeroOrden = document.getElementById('numeroOrden_'+fila).value;
	var numeroGuia = document.getElementById('numeroGuia_'+fila).value;
	var estado = document.getElementById('transportistasCombo_'+fila).value;
	var observaciones = document.getElementById('observaciones_'+fila).value;
	
	forma.buscarCampana.value = Campana;
	forma.numeroOrdenE.value = numeroOrden;
	forma.numeroGuiaE.value = numeroGuia;	
	forma.estadoE.value = estado;
	forma.observacionE.value = observaciones;	
	
	forma.action = "transportistas.transportistasGuardar.do";	
	forma.target = "contenido";
	forma.submit();
	
	
}

function guardarTransportistaListGuia(){
	var forma = document.forms[0];
	forma.action = "transportistas.transportistasGuardarLista.do";
	forma.target = "contenido";
	forma.submit();
}

function guardarEntregados(nroRegistros){
	var forma = document.forms[0];
	var vec_orden = '';
	var vec_guia = '';
	var bandera = false;
	for(var i = 1; i <= nroRegistros; i++){
		var check = document.getElementById('entregado_'+i);
		if(check.checked){
			vec_orden = vec_orden + document.getElementById('nroOrden_'+i).value + ',';
			vec_guia = vec_guia + document.getElementById('nroGuia_'+i).value + ',';
			bandera = true;
		}
	}
	if(bandera == false){
		mostrarMensaje("Debe seleccionar al menos un registro");
	}
	else{
		document.getElementById('pEstBuscar').value = 'P';
		forma.vec_orden.value = vec_orden;
		forma.vec_guia.value = vec_guia;
		forma.action = "transportistas.guardarEntregados.do";
		forma.submit();
	}
}