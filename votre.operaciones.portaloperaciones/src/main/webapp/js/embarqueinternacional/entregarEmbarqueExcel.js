function procesar(){
	var forma = document.forms[0];
	seguir = validarArchivo();
	if(seguir){
		forma.action = "entrega.procesarExcel.do";
		forma.submit();
	}
}

function validarArchivo(){
	var archivo = document.getElementById("archivo").value;
	extensiones_permitidas = new Array(".xls");
	
	if (archivo == null || archivo == '') {
		mostrarMensaje ('No se ha seleccionado el archivo a importar.');
		return false;
	}
	else{
		extension = (archivo.substring(archivo.lastIndexOf("."))).toLowerCase();
		permitida = false; 
      	for (var i = 0; i < extensiones_permitidas.length; i++) { 
	        if (extensiones_permitidas[i] == extension) { 
		        permitida = true; 
		        break; 
         	}
        }
	}	
	if (!permitida) { 
		mostrarMensaje ("Comprueba la extensión del archivo a subir. Sólo se puede subir archivos en formato .xls"); 
		document.getElementById('archivo').value = "";
		return false;	
    } 
	return true;
}

function regresar(){
	var forma = document.forms[0];
	forma.action = "entrega.verEntrega.do";
	forma.submit();
}