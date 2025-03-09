function cargarAnteriores(){
	document.getElementById('direccionAnt').value = document.getElementById('direccion').value;
	document.getElementById('barrioAnt').value = document.getElementById('barrio').value;
	document.getElementById('municipioAnt').value = document.getElementById('municipio').value;
}

function guardar(){
	var forma = document.forms[0];
	if(validarDatos()){
		forma.action="compradora.modificar.do";
		forma.submit();	
	}
}

function validarDatos(){
	var direccion = document.getElementById('direccion').value;
	var barrio = document.getElementById('barrio').value;
	var municipio = document.getElementById('municipio').value;
	var codigoPostal = document.getElementById('codigoPostal').value;
	if(direccion!='' && barrio!='' && municipio!= '' && codigoPostal != ''){
		if(confirm('Los datos ingresados son correctos?')){
			return true;
		}
		else{
			return false;
		}
	}else{
		alert('Debe digitar la información completa');
		return false;
	}	
}

function regresar(){
	var forma = document.forms[0];
	document.getElementById('direccion').value = document.getElementById('direccionAnt').value;
	document.getElementById('barrio').value = document.getElementById('barrioAnt').value;
	document.getElementById('municipio').value = document.getElementById('municipioAnt').value;
	
	forma.action="compradora.verInformacionCompradora.do";
	forma.submit();
}

function volver(){
	var forma = document.forms[0];
	forma.action="compradora.verInformacionCompradora.do";
	forma.submit();
}