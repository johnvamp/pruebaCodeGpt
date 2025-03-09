function calendario() {
    Calendar.setup({
        inputField     :    "fechaEntrega",     // id of the input field
        ifFormat       :    "%Y-%m-%d",     // format of the input field (even if hidden, this format will be honored)
        displayArea    :    "show_d",       // ID of the span where the date is to be shown
        daFormat       :    "%A, %B %d, %Y",// format of the displayed date
        align          :    "Tl",           // alignment (defaults to "Bl")
        button         :    "calendario",        
        //eventName      :    "focus"
        singleClick    :    true
    });
}

function calendario2(){
	Calendar.setup({
	    inputField     :    "fechaInicioR",     // id of the input field
        ifFormat       :    "%Y-%m-%d",     // format of the input field (even if hidden, this format will be honored)
        displayArea    :    "show_d",       // ID of the span where the date is to be shown
        daFormat       :    "%A, %B %d, %Y",// format of the displayed date
        align          :    "Tl",           // alignment (defaults to "Bl")
        button         :    "calendario2",
        singleClick    :    true
    });
}

function calendario3(){
	Calendar.setup({
        inputField     :    "fechaEntrega",     // id of the input field
        ifFormat       :    "%Y-%m-%d",     // format of the input field (even if hidden, this format will be honored)
        displayArea    :    "show_d",       // ID of the span where the date is to be shown
        daFormat       :    "%A, %B %d, %Y",// format of the displayed date
        align          :    "Tl",           // alignment (defaults to "Bl")
        button         :    "calendario",        
        //eventName      :    "focus"
        singleClick    :    true
    });
	
	Calendar.setup({
	    inputField     :    "fechaInicioR",     // id of the input field
        ifFormat       :    "%Y-%m-%d",     // format of the input field (even if hidden, this format will be honored)
        displayArea    :    "show_d",       // ID of the span where the date is to be shown
        daFormat       :    "%A, %B %d, %Y",// format of the displayed date
        align          :    "Tl",           // alignment (defaults to "Bl")
        button         :    "calendario2",
        singleClick    :    true
    });
}

function crearSolicitud(){
	var forma = document.forms[0];
	var fechaEntrega = document.getElementById('fechaEntrega').value;
	var tipoEntrega = document.getElementById('tipoEntrega').value;
	var mensaje = "";
	if(fechaEntrega == ""){
		mensaje += "Debe seleccionar la fecha de entrega.<br>";
	}
	if(tipoEntrega == "0"){
		mensaje += "Debe seleccionar un tipo de entrega.<br>";
	}
	if(mensaje != ""){
		mostrarMensaje(mensaje);
	}
	else{
		forma.action = "reprocesos.crearNeuvaSolicitud.do";
		forma.submit();
	}
}

function agregarReferencia(){
	var forma = document.forms[0];
	var referencia = document.getElementById('txtReferencia').value;
	var cantidad = document.getElementById('txtCantidad').value;
	var mensaje = "";
	if(referencia == ""){
		mensaje += "Debe digitar el campo Referencia.<br>";
	}
	if(cantidad == ""){
		mensaje += "Debe digitar el campo Cantidad.<br>";
	}
	if(mensaje != ""){
		mostrarMensaje(mensaje);
	}
	else{
		forma.action = "reprocesos.agregarReferencia.do";
		forma.submit();
	}
}

function paginacion(nroRegistros, registrosXPagina){
	if (nroRegistros == 0 && registrosXPagina == 0)		
		return;	
		var paginas = buscarPaginasT(nroRegistros, registrosXPagina);
		if (paginas != null && paginas.length > 0) {
			paginas[0].visible = "1";
			paginas[0].style.display="inline";
			if (document.getElementById('txtNroPaginaT')){
				document.getElementById('txtNroPaginaT').value = 1;
				document.getElementById('nroPaginasT').innerHTML = 'Numero de Paginas: ' + paginas.length;
			}	
		}
}

function paginaAnteriorT(nroRegistros, registrosXPagina){
	var paginas = buscarPaginasT(nroRegistros, registrosXPagina);
	for (var i = 0; i < paginas.length; i++){
		if (paginas[i].visible == "1") {
			if (i-1 < 0) {
				alert('Esta es la primera pagina');
				return;
			}
			paginas[i].visible = "0";
			paginas[i].style.display="none";
	    	paginas[i-1].visible = "1";
			paginas[i-1].style.display="inline";
			document.getElementById('txtNroPaginaT').value = i;
			break;
		}
	}
}

function paginaSiguienteT(nroRegistros, registrosXPagina){
	var paginas = buscarPaginasT(nroRegistros, registrosXPagina);
	for (var i = 0; i < paginas.length; i++){
		if (paginas[i].visible == "1") {
			if (i+1 >= paginas.length) {
				alert('Esta es la ultima pagina');
				return;
			}
			paginas[i].visible = "0";
			paginas[i].style.display="none";			
	    	paginas[i+1].visible = "1";
			paginas[i+1].style.display="inline";
			document.getElementById('txtNroPaginaT').value = i + 2;
			break;
		}
	}
}

function buscarPaginasT(nroRegistros, registrosXPagina){
	var nroPaginas = 0;
	var paginas = new Array();
	for (var i = 1; i <= nroRegistros; i = i + registrosXPagina){
		if ( document.getElementById('paginaT' + i ) != null ){
			paginas[nroPaginas] = document.getElementById('paginaT' + i);
			nroPaginas++;
		}
	}
	return paginas;
}

function irPaginaT(text, e, nroRegistros, registrosXPagina){
	var key = (navigator.appName == "Netscape") ? e.which : e.keyCode;
	if (key == 13) {
		var nroPagina = text.value;
		var paginas = buscarPaginasT(nroRegistros, registrosXPagina);
		if (nroPagina >0 && nroPagina <= paginas.length) {
			for (var i = 0; i < paginas.length; i++){
				if (paginas[i].visible == "1") {
					paginas[i].visible = "0";
					paginas[i].style.display="none";
			    	paginas[nroPagina - 1].visible = "1";
					paginas[nroPagina - 1].style.display="inline";
					break;
				}
			}
		}
		else
			alert('Este no corresponde a un numero de pagina');
	}
}

function verDetalle(numReque, fechaCreacion, fechaEntrega, tipoEntrega, observacion,accion){
	var forma = document.forms[0];
	document.getElementById('numRequerimiento').value = numReque;
	document.getElementById('fechaCreacion').value = fechaCreacion;
	document.getElementById('fechaEntrega').value = fechaEntrega;
	document.getElementById('tipoEntrega').value = tipoEntrega;
	document.getElementById('observacion').value = observacion;
	document.getElementById('accion').value = accion;
	forma.action = "reprocesos.verDatalleSolicitud.do";
	forma.submit();
}

function abrirAprobar(){
	var ancho = 500;
	var alto = 300;
	var posicion_x; 
	var posicion_y; 
	posicion_x=(screen.width/2)-(ancho/2); 
	posicion_y=(screen.height/2)-(alto/2); 
	window.open('reprocesos.jspAprobarSolicitud.do', '', "width="+ancho+",height="+alto+",menubar=0,toolbar=0,directories=0,scrollbars=no,resizable=no,left="+posicion_x+",top="+posicion_y+"");
}

function aprobarSolicitud(){
	var fecha = document.getElementById('fechaInicioR').value;
	if(fecha != ""){
		window.opener.document.forms[0].accion.value = 'A';
		window.opener.document.forms[0].fechaInicio.value = fecha;
		window.opener.document.forms[0].action = "reprocesos.aprobarSolicitud.do";
		window.opener.document.forms[0].submit();
		window.close();
	}
	else{
		mostrarMensaje('Debe seleccionar la fecha de inicio.');
	}
}

function abrirModificar(){
	var ancho = 500;
	var alto = 300;
	var posicion_x; 
	var posicion_y; 
	posicion_x=(screen.width/2)-(ancho/2); 
	posicion_y=(screen.height/2)-(alto/2); 
	window.open('reprocesos.jspModificarSolicitud.do', '', "width="+ancho+",height="+alto+",menubar=0,toolbar=0,directories=0,scrollbars=no,resizable=no,left="+posicion_x+",top="+posicion_y+"");
}

function traerFecha(){
	document.getElementById('fechaEntrega').value = window.opener.document.forms[0].fechaOriginal.value;
}

function modificarSolicitud(){
	var mensaje = '';
	var fechaEntrega = document.getElementById('fechaEntrega').value;
	var fechaInicio = document.getElementById('fechaInicioR').value;
	if(fechaEntrega == ""){
		mensaje += "Debe seleccionar la fecha de entrega.<br>";
	}
	if(fechaInicio == ""){
		mensaje += "Debe seleccionar la fecha de inicio.<br>";
	}
	if(mensaje == ""){
		window.opener.document.forms[0].accion.value = 'M';
		window.opener.document.forms[0].fechaEntrega.value = fechaEntrega;
		window.opener.document.forms[0].fechaInicio.value = fechaInicio;
		window.opener.document.forms[0].action = "reprocesos.aprobarSolicitud.do";
		window.opener.document.forms[0].submit();
		window.close();
	}
	else{
		mostrarMensaje(mensaje);
	}
}

function abrirRechazar(){
	var ancho = 500;
	var alto = 300;
	var posicion_x; 
	var posicion_y; 
	posicion_x=(screen.width/2)-(ancho/2); 
	posicion_y=(screen.height/2)-(alto/2); 
	window.open('reprocesos.jspRechazarSolicitud.do', '', "width="+ancho+",height="+alto+",menubar=0,toolbar=0,directories=0,scrollbars=no,resizable=no,left="+posicion_x+",top="+posicion_y+"");
}

function rechazarSolicitud(){
	var comentario = document.getElementById('comentario').value;
	if(comentario != ""){
		window.opener.document.forms[0].accion.value = 'R';
		window.opener.document.forms[0].comentario.value = comentario;
		window.opener.document.forms[0].action = "reprocesos.aprobarSolicitud.do";
		window.opener.document.forms[0].submit();
		window.close();
	}
	else{
		mostrarMensaje('Debe digitar el campo comentario.');
	}
}

function guardarSolicitud(fila){
	var forma = document.forms[0];
	var numReque;
	var referencia;
	var ubicacion;
	var estado;
	estado = document.getElementById('estado_'+fila).value;
	if(estado != ""){
		numReque = document.getElementById('numRequerimiento_'+fila).value;
		referencia = document.getElementById('referencia_'+fila).value;
		ubicacion = document.getElementById('ubicacion_'+fila).value;
		
		document.getElementById('numRequerimiento').value = numReque;
		document.getElementById('referencia').value = referencia;
		document.getElementById('ubicacion').value = ubicacion;
		document.getElementById('estado').value = estado;
		document.getElementById('accion').value = "I";
		forma.action = "reprocesos.procesarReferencia.do";
		forma.submit();
	}
	else{
		mostrarMensaje('Debe seleccionar un estado.');
	}
}

function verReproceso(numReque, fechaEntrega, tipoEntrega, observacion,accion){
	var forma = document.forms[0];
	document.getElementById('numRequerimiento').value = numReque;
	document.getElementById('fechaEntrega').value = fechaEntrega;
	document.getElementById('tipoEntrega').value = tipoEntrega;
	document.getElementById('observacion').value = observacion;
	document.getElementById('accionTramite').value = 'P';
	document.getElementById('accion').value = accion;
	forma.action = "reprocesos.verDatalleSolicitud.do";
	forma.submit();
}

function guardarReproceso(fila){
	var forma = document.forms[0];
	var referencia;
	var cantidad;
	cantidad = document.getElementById('cantProcesada_'+fila).value;
	if(cantidad != ""){
		referencia = document.getElementById('referencia_'+fila).value;
		document.getElementById('referencia').value = referencia;
		document.getElementById('cantidad').value = cantidad;
		document.getElementById('accion').value = "E";
		forma.action = "reprocesos.procesarReferencia.do";
		forma.submit();
	}
	else{
		mostrarMensaje('Debe digitar la cantidad procesada.');
	}
}