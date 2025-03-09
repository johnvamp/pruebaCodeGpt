function generarExcelIndipedi(idEstado){
	var forma = document.forms[0];
	forma.idEstadoIni.value = idEstado;	
	forma.action= "indicaPedido.indicaPedidosXLS.do";		
	mostrarCargar();
	forma.submit();	
}


function buscarIndicadorEstado(idEstado){
	var forma = document.forms[0];
	forma.idEstadoIni.value = idEstado;	
	forma.action = "indicaPedido.jspindicaPedidosEstado.do";	
    window.open('', 'formpopup3', '');
    forma.target = "formpopup3";
	forma.submit();	
}

function buscarIndicador(){
	var forma = document.forms[0];
	if(validar(forma)){
		forma.action = "indicaPedido.generarIndicadores.do";	
	   	window.open('', 'formpopup2', '');
	   	forma.target = "formpopup2";
		forma.submit();	
	}
}


function buscarCiudad(){
	var forma = document.forms[0];
	forma.action = "indicaPedido.consultarCiudades.do";	
	forma.submit();
	mostrarCargar();
	cambiarSeleccion(document.getElementById('idOpcSelect').value);
}

function cargarCombosUbicacion(departamento, estadoInicial, estadoFinal, campana){
	document.getElementById('idDepartamentoSelect').value = departamento;
	document.getElementById('idEstadoInicial').value = estadoInicial;
	document.getElementById('idEstadoFinal').value = estadoFinal;
	document.getElementById('campanaTBox').value = campana;
}


function validar(forma){
	var codOpcion = forma.idOpcSelect.value;
	var mensaje = '';
	if(codOpcion == 0){
		mensaje += "Debe seleccionar un tipo de busqueda.<br>";
	}
	if(codOpcion == 1){
		if(forma.idEstadoInicial.value == '' || forma.idEstadoFinal.value == ''){
			mensaje += "Debe seleccionar el rango de Estados.<br>";
		}
		if(forma.fechaInicialTBox.value == '' || forma.fechaFinalTBox.value == ''){
			mensaje += "Debe digitar el rango de Fechas.<br>";
		}
	}
	if(codOpcion == 2){
		if(forma.idEstadoInicial.value == '' || forma.idEstadoFinal.value == ''){
			mensaje += "Debe seleccionar el rango de Estados.<br>";
		}
		if(forma.zonaTBox.value == ''){
			mensaje += "Debe digitar el campo Zona.<br>";
		}
		if(forma.campanaTBoxZ.value == ''){
			mensaje += "Debe digitar el campo Campa&ntilde;a.<br>";
		}
	}
	if(codOpcion == 3){
		if(forma.idEstadoInicial.value == '' || forma.idEstadoFinal.value == ''){
			mensaje += "Debe seleccionar el rango de Estados.<br>";
		}
		if(forma.campanaTBox.value == ''){
			mensaje += "Debe digitar el campo Campa&ntilde;a.<br>";
		}
	}
	if(codOpcion == 4){
		if(forma.idEstadoInicial.value == '' || forma.idEstadoFinal.value == ''){
			mensaje += "Debe seleccionar el rango de Estados.<br>";
		}
		if(forma.idRegionSelect.value == ''){
			mensaje += "Debe seleccionar la Región.<br>";
		}
		if(forma.campanaTBoxR.value == ''){
			mensaje += "Debe digitar el campo Campa&ntilde;a.<br>";
		}
	}
	if(codOpcion == 5){
		if(forma.idEstadoInicial.value == '' || forma.idEstadoFinal.value == ''){
			mensaje += "Debe seleccionar el rango de Estados.<br>";
		}
		if(forma.idCompradoraSelect.value == ''){
			mensaje += "Debe seleccionar el Tipo de Compradora.<br>";
		}
		if(forma.campanaTBoxC.value == ''){
			mensaje += "Debe digitar el campo Campa&ntilde;a.<br>";
		}
	}
	
	if(codOpcion == 6){
		
		if(forma.idEstadoInicial.value == '' || forma.idEstadoFinal.value == ''){
			mensaje += "Debe seleccionar el rango de Estados.<br>";
		}
		
		if(forma.idDepartamentoSelect.value == ''){
			mensaje += "Debe seleccionar un Departamento.<br>";
		}
		
		if(forma.idCiudadSelect.value == ''){
			mensaje += "Debe seleccionar una Ciudad.<br>";
		}
		
		if(forma.campanaTBox.value == ''){
			mensaje += "Debe digitar el campo Campa&ntilde;a.<br>";
		}
	}
	
	if(mensaje == ''){
		return true;
	}
	else{
		mostrarMensajeIndicador(mensaje);
		return false;
	}
}

function pedidosPorRango(idRango){	
	var forma = document.forms[0];
	forma.idRango.value = idRango;
	forma.action = "indicaPedido.jsppedidosPorRango.do";	
   	window.open('', 'formpopup4', '');
   	forma.target = "formpopup4";
	forma.submit();	
	
}

function cambiarSeleccion(codOpcion){
	if(codOpcion == 0){	
		document.getElementById('divEstados').style.display = 'none';
		document.getElementById('divFecha').style.display = 'none';
		document.getElementById('divZona').style.display = 'none';
		document.getElementById('divCampana').style.display = 'none';
		document.getElementById('divRegion').style.display = 'none';
		document.getElementById('divCompradora').style.display = 'none';
		document.getElementById('divUbicacion').style.display = 'none';
	}
	if(codOpcion == 1){
		document.getElementById('divEstados').style.display = 'inline';
		document.getElementById('divFecha').style.display = 'inline';
		document.getElementById('divZona').style.display = 'none';
		document.getElementById('divCampana').style.display = 'none';
		document.getElementById('divRegion').style.display = 'none';
		document.getElementById('divCompradora').style.display = 'none';
		document.getElementById('fechaInicialTBox').focus();
		document.getElementById('divUbicacion').style.display = 'none';
	}
	if(codOpcion == 2){
		document.getElementById('divEstados').style.display = 'inline';
		document.getElementById('divFecha').style.display = 'none';
		document.getElementById('divZona').style.display = 'inline';
		document.getElementById('divCampana').style.display = 'none';
		document.getElementById('divRegion').style.display = 'none';
		document.getElementById('divCompradora').style.display = 'none';
		document.getElementById('zonaTBox').focus();
		document.getElementById('divUbicacion').style.display = 'none';
	}
	if(codOpcion == 3){
		document.getElementById('divEstados').style.display = 'inline';
		document.getElementById('divFecha').style.display = 'none';
		document.getElementById('divZona').style.display = 'none';
		document.getElementById('divCampana').style.display = 'inline';
		document.getElementById('divRegion').style.display = 'none';
		document.getElementById('divCompradora').style.display = 'none';
		document.getElementById('campanaTBox').focus();
		document.getElementById('divUbicacion').style.display = 'none';
	}	
	if(codOpcion == 4){
		document.getElementById('divEstados').style.display = 'inline';
		document.getElementById('divFecha').style.display = 'none';
		document.getElementById('divZona').style.display = 'none';
		document.getElementById('divCampana').style.display = 'none';
		document.getElementById('divRegion').style.display = 'inline';
		document.getElementById('divCompradora').style.display = 'none';
		document.getElementById('divUbicacion').style.display = 'none';
	}
	if(codOpcion == 5){
		document.getElementById('divEstados').style.display = 'inline';
		document.getElementById('divFecha').style.display = 'none';
		document.getElementById('divZona').style.display = 'none';
		document.getElementById('divCampana').style.display = 'none';
		document.getElementById('divRegion').style.display = 'none';
		document.getElementById('divCompradora').style.display = 'inline';
		document.getElementById('divUbicacion').style.display = 'none';
	}
	if(codOpcion == 6){
		document.getElementById('divEstados').style.display = 'inline';
		document.getElementById('divFecha').style.display = 'none';
		document.getElementById('divZona').style.display = 'none';
		document.getElementById('divCampana').style.display = 'inline';
		document.getElementById('divRegion').style.display = 'none';
		document.getElementById('divCompradora').style.display = 'none';
		document.getElementById('divUbicacion').style.display = 'inline';
		document.getElementById('campanaTBox').focus();
	}
}

/*function IndicaPedido(){
	var forma = document.forms[0];
	//forma.action = "indicaPedido.jspindicaPedidos.do";
	forma.action = "indicaPedido.jspindicaPedidosR.do";		
	forma.target = "contenido";
	forma.submit();
	cambiarSeleccion(0);
	mostrarCargar();
}*/

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
		message: '.. PROCESANDO ..',
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

function validaIngresoNum(e,funcion) {
	var forma = document.forms[0];
	//var val1 = document.getElementById("camapanaTBox").value;	
	var key = (navigator.appName == "Netscape") ? e.which : e.keyCode;
	if(e.altKey || e.ctrlKey) {
	return false;
	}	
	if(key == 13){
	eval(funcion);
	return false;	
	}
	if ((key > 57 | key < 48 ) &  key != 0 & key != 8 & key != 13) {
	return false;
	} 
	else {
	     return true;
	    }
}

function mostrarMensajeIndicador(mensaje){
	if(mensaje != ''){
		desplegarMensajeIndicador(mensaje);
	}
}

function desplegarMensajeIndicador(mensaje, funcionCerrar, funcionAceptar, desaparecerBotonCerrar){

	var codigoBotones;

	if( funcionCerrar == null || funcionCerrar == '' ){
		funcionCerrar = "desvanecerMensajeIndicador('mensaje');desvanecerMensajeIndicador('espacio');";
	}

	if(!desaparecerBotonCerrar){
		mensaje = '<div align="right"><a href="javascript:'+funcionCerrar+'"><img src="imagenes/cerrar.gif" border="0"/></a></div>' + mensaje+"<br>";
	}else{
		mensaje = '<br>'+mensaje;
	}
	
	codigoBotones = '';
	
	if(funcionAceptar){
		codigoBotones +='<br><div align="center" ><a href="javascript:'+funcionAceptar+'" class="LinkCerrar" >Aceptar</a></div><br>';
	}
	
	mensaje += codigoBotones; 
	
	cambiarMensajeIndicador(mensaje, false);
	mostrarElementoIndicador('mensaje');
	mostrarElementoIndicador('espacio');
	
}

function cambiarMensajeIndicador(mensaje, introducirEspacio){
	var forma = document.forms[0];
	if(document.getElementById("mensaje")){
		if(introducirEspacio){
			mensaje = '<div align="right"><a href="javascript:desvanecerMensajeIndicador("mensaje");desvanecerMensajeIndicador("espacio");"><img src="images/cerrar.gif" border="0"/></a></div>' +'&nbsp;&nbsp;&nbsp;&nbsp;'+mensaje+"<br><br>";
		}
		document.getElementById("mensaje").innerHTML = mensaje;
		document.location.href = "#top";
	}
}

function mostrarElementoIndicador(elemento){
	if(document.getElementById(elemento)){
		document.getElementById(elemento).style.display = 'inline';
	}
}

function desvanecerMensajeIndicador(elemento){
	if(document.getElementById(elemento)){
		document.getElementById(elemento).style.display = 'none';
	}
}


