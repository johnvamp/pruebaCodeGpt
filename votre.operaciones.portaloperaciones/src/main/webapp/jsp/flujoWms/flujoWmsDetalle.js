function generarExcel(tipoflujo){
	var forma = document.forms[0];
	forma.idTipoFlujo.value = tipoflujo;
	forma.action= "flujo.flujoXLS.do";	
	mostrarCargar();
	forma.submit();	
}

function FlujoWMS(TipoFlujo){
	var forma = document.forms[0];
	forma.idTipoFlujo.value = TipoFlujo;
	mostrarCargar();
	forma.action = "flujo.flujowms.do";	
	forma.target = "contenido";
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
