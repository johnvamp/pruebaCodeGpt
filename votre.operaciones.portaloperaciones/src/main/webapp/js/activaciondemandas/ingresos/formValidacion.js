function regresar(){
	var forma = document.forms[0];
	forma.action = "activaciondemandas.verIngreso.do";
	forma.submit();
}

function grabarNovedad(){
	var forma = document.forms[0];
	if(window.confirm("Notifica que desea registrar la novedad?")){
		forma.action = "activaciondemandas.grabarNovedad.do";
		forma.submit();
	}
}
