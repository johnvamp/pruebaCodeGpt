function generarExcel(){
	var forma = document.forms[0];
	forma.action = "reimprimir.generarExcel.do"
	forma.submit();	
	
}

function regresar(accion){
	var forma = document.forms[0];
	if(accion == 'reimprimir'){
		forma.action="camion.verConsultar.do";
		forma.submit();	
	}
	if(accion == 'cerrarEmbarque'){
		forma.action="embarque.verAccionesEmbarque.do";
		forma.submit();	
	}
}

function imprimir(){
	window.print();
}