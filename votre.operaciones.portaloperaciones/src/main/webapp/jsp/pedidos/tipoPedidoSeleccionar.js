function buscarTipoPedido(){
	var forma = document.forms[0];
	var tipoPedido = document.getElementById("tipoPedido").value;
	forma.buscarTipoPedido.value = tipoPedido;
	forma.action = "pedidos.pedidosAgregarTipo.do";		
	forma.target = "contenido";
	mostrarCargar();
	forma.submit();	
}

function buscarPedidoTipo(){
	var forma = document.forms[0];
	var tipoPedido = document.getElementById("tipoPedido").value;
	forma.buscarTipoPedido.value = tipoPedido;
	forma.action = "pedidos.pedidosBuscarTipo.do";		
	forma.target = "contenido";
	mostrarCargar();
	forma.submit();	
}

function buscarPedidoNumero(){
	var forma = document.forms[0];
	var numeroPedido = document.getElementById("numeroPedidoBuscar").value;
	forma.numeroPedido.value = numeroPedido;
	forma.action = "pedidos.pedidosBuscarTipo.do";		
	forma.target = "contenido";
	mostrarCargar();
	forma.submit();	
}



function agregarCompradora(){
	var forma = document.forms[0];
	var codCompradora = document.getElementById("codCompradora").value;
	var codReferencia = document.getElementById("codReferencia").value;
	var codTalla = document.getElementById("codTalla").value;
	var codColor = document.getElementById("codColor").value;
	var codCantidad = document.getElementById("codCantidad").value;
	var tipoPedido = document.getElementById("tipoPedido").value;
	
	forma.guardarCodCompradora.value = codCompradora;
	forma.guardarCodReferencia.value = codReferencia;
	forma.guardarCodTalla.value = codTalla;
	forma.guardarCodColor.value = codColor;
	forma.guardarcodCantidad.value = codCantidad;
	forma.buscarTipoPedido.value = tipoPedido;

	forma.action = "pedidos.pedidosAgregar.do";	
	forma.target = "contenido";
	mostrarCargar();
	forma.submit();	
	
}


function agregarLider(){
	var forma = document.forms[0];
	var codReferencia = document.getElementById("codReferencia").value;
	var codTalla = document.getElementById("codTalla").value;
	var codColor = document.getElementById("codColor").value;
	var codCantidad = document.getElementById("codCantidad").value;
	var codZona = document.getElementById("codZona").value;
	var tipoPedido = document.getElementById("tipoPedido").value;
	
	forma.guardarCodReferencia.value = codReferencia;
	forma.guardarCodTalla.value = codTalla;
	forma.guardarCodColor.value = codColor;
	forma.guardarcodCantidad.value = codCantidad;
	forma.guardarcodZona.value = codZona;
	forma.buscarTipoPedido.value = tipoPedido;

	forma.action = "pedidos.pedidosAgregar.do";	
	forma.target = "contenido";
	mostrarCargar();
	forma.submit();	
	
}

function agregarRegion(){
	var forma = document.forms[0];
	var codReferencia = document.getElementById("codReferencia").value;
	var codTalla = document.getElementById("codTalla").value;
	var codColor = document.getElementById("codColor").value;
	var codCantidad = document.getElementById("codCantidad").value;
	var codTipoCombo = document.getElementById("tipoCombo").value;	
	
	
	
	var tipoPedido = document.getElementById("tipoPedido").value;
	
	forma.guardarCodReferencia.value = codReferencia;
	forma.guardarCodTalla.value = codTalla;
	forma.guardarCodColor.value = codColor;
	forma.guardarcodCantidad.value = codCantidad;
	forma.guardarTipoCombo.value = codTipoCombo;
	forma.buscarTipoPedido.value = tipoPedido;

	forma.action = "pedidos.pedidosAgregar.do";	
	forma.target = "contenido";
	mostrarCargar();
	forma.submit();	
	
}

function agregarComando(){
	var forma = document.forms[0];
	var codReferencia = document.getElementById("codReferencia").value;
	var codTalla = document.getElementById("codTalla").value;
	var codColor = document.getElementById("codColor").value;
	var codCantidad = document.getElementById("codCantidad").value;
	var codTipoCombo = document.getElementById("tipoCombo").value;	
	var tipoPedido = document.getElementById("tipoPedido").value;
	
	forma.guardarCodReferencia.value = codReferencia;
	forma.guardarCodTalla.value = codTalla;
	forma.guardarCodColor.value = codColor;
	forma.guardarcodCantidad.value = codCantidad;
	forma.guardarTipoCombo.value = codTipoCombo;
	forma.buscarTipoPedido.value = tipoPedido;

	forma.action = "pedidos.pedidosAgregar.do";	
	forma.target = "contenido";
	mostrarCargar();
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
	
	setTimeout(jQuery.unblockUI, 9000); 
}

function ocultarCargar(){
	jQuery.unblockUI();
}


function validaIngresoNum(e) {
	var key = (navigator.appName == "Netscape") ? e.which : e.keyCode;
	if(e.altKey || e.ctrlKey) {
	return false;
	}	
	if(key == 13){
	return false;	
	}
	if ((key > 57 | key < 48 ) &  key != 0 & key != 8 & key != 13) {
	return false;
	} 
	else {
	     return true;
	    }
}

function validaIngresoNumBuscar(e) {
	var key = (navigator.appName == "Netscape") ? e.which : e.keyCode;
	if(e.altKey || e.ctrlKey) {
	return true;
	}	
	if(key == 13){
	return false;	
	}
	if ((key > 57 | key < 48 ) &  key != 0 & key != 8 & key != 13 & (key > 90 | key < 65 ) & (key > 122 | key < 97 )) {
	return false;
	} 
	else {
	     return true;
	    }
}



