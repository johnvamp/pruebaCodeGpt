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
	forma.action = "transportistas.transportistasPediEntre.do";	
	forma.target = "contenido";
	mostrarCargar();
	forma.submit();	
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
