function adelante(){
	var forma = document.forms[0];
	forma.actBtn.value= "A";
	forma.actXLS.value= " ";
	
	var pag = forma.paginaPintar.value;
	forma.paginaPintar.value = parseInt(pag) + 1;

	forma.idPag.value= forma.idPagSig.value;
	forma.numPaginacion.value=forma.idPag.value;
	forma.action= forma.accion.value;
	mostrarCargar();
	forma.submit();	
}

function atras(){
	var forma = document.forms[0];
	forma.actBtn.value= "R";
	forma.actXLS.value= " ";

	var pag = forma.paginaPintar.value;
	forma.paginaPintar.value = parseInt(pag) - 1;

	forma.idPag.value= forma.idPagAnt.value;
	forma.numPaginacion.value=forma.idPag.value;
	forma.action= forma.accion.value;
	mostrarCargar();
	forma.submit();	
}

