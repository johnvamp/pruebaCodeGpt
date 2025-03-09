function verDetalle(titulo, numPedidos, nroEstado){
	var forma = document.forms[0];
	if(numPedidos != 0){
		document.getElementById("nroEstado").value = nroEstado;
		document.getElementById("tituloEstado").value = titulo;
		forma.action = "flujo.consultarDetalleEstado.do";
		mostrarCargar();
		forma.submit();
	}
	else{
		alert("Estado sin pedidos atrasados.");
	}
}

function regresar(){
	var forma = document.forms[0];
	forma.action = "flujo.consultarEstados.do";
	mostrarCargar();
	forma.submit();
}

function generarExcel(){
	var forma = document.forms[0];
	forma.action = "flujo.generarExcelDetalle.do";
	forma.submit();
}