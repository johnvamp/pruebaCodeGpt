function cargarDatos(){
	var valor = document.getElementById('codigo').value;
	valor = valor.split('|');
	var sku  = valor[0];
	var desc = valor[1];
	var cant = valor[2];
	
	if(valor[0]!=''){
		document.getElementById('sku').value = sku;
		document.getElementById('txtDescripcion').value = desc;
		document.getElementById('txtCantidad').value = cant;
		document.getElementById('txtCantidad').focus();
	}else{
		document.getElementById('txtDescripcion').value = "";
		document.getElementById('txtCantidad').value = "";
		document.getElementById('codigo').focus();
	}	
}

function verListado(){
	var forma = document.forms[0];
	forma.action="compradora.verListadoCompradoras.do";
	forma.submit();	
}

function verDetalle(campana,nroCaso,sku){
	var forma = document.forms[0];
	document.getElementById('campana').value = campana;
	document.getElementById('nroLinea').value = nroCaso;
	document.getElementById('sku').value = sku;
	forma.action="historial.verInformacionReferencia.do";
	forma.submit();	
}

function eliminar(sku,cantidad,nroCaso){
	var forma = document.forms[0];
	document.getElementById('sku').value = sku;
	document.getElementById('cantidad').value = cantidad;
	document.getElementById('nroLinea').value = nroCaso;
	forma.action="compradora.eliminar.do";
	forma.submit();
}

function modificar(){
	var forma = document.forms[0];
	forma.action="compradora.verModificar.do";
	forma.submit();	
}

function grabar(){
	var forma = document.forms[0];
	if(validarDatos()){		
		forma.action="compradora.guardar.do";
		forma.submit();	
	}
}

function validarDatos(){
	var valor = document.getElementById('codigo').value;
	valor = valor.split('|');
	var sku  = valor[0];
	var desc = valor[1];
	var cant = document.getElementById('txtCantidad').value;
	
	if(sku!='' && desc!='' && cant!= ''){
		if(cant>=10){
			if(confirm('Esta segura que la cantidad es '+ cant + ' ?')){
				document.getElementById('descripcion').value = desc;
				document.getElementById('cantidad').value = cant;
				document.getElementById('codigo').selectedIndex = 0;
				return true;	
			}else{
				document.getElementById('cantidad').value = 1;
				document.getElementById('cantidad').focus();
				return false;		
			}		
		}else{
			document.getElementById('descripcion').value = desc;
			document.getElementById('cantidad').value = cant;
			document.getElementById('codigo').selectedIndex = 0;
			return true;
		}
	}else{
		alert('Debe digitar la información completa');
		return false;
	}
}

function volver(){
	var forma = document.forms[0];
	forma.action="catalogo.verCatalogo.do";
	forma.submit();
}