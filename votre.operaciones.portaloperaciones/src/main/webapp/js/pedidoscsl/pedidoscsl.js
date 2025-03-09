var jQ = jQuery.noConflict();

function crearSolicitud(accion){
	var mensaje = '';
	var select = jQ('#pedido').val();
	var desEstrategia = jQ('#desEstrategia').val();
	var tipo = '';
	var desTipo = '';
	var destinatario = '';
	if(select == ''){
		mensaje += "Debe seleccionar el tipo de pedido.<br>"
	}
	else{
		var opcion = select.split("|");
		if(opcion != ''){
			tipo = opcion[0];
			desTipo = opcion[1];
			destinatario = opcion[2];
		}
	}
	if(desEstrategia == ''){
		mensaje += "Debe digitar la descripción de la estrategia.<br>"
	}
	if(mensaje != ''){
		mostrarMensaje(mensaje);
	}
	else{
		var forma = document.forms[0];
		jQ('#tipoPedido').val(tipo);
		jQ('#desPedido').val(desTipo);
		jQ('#destinatario').val(destinatario);
		if(accion == 'I'){
			forma.action = "pedidoscsl.verPedidoIndividual.do";
		}
		else if(accion == 'E'){
			forma.action = "pedidoscsl.verImportarExcel.do";
		}
		mostrarCargar();
		forma.submit();
	}
}

function validarSolcitud(){
	var destinatario = jQ('#destinatario').val();
	var val1 = jQ('#val1').val() != undefined ? jQ('#val1').val() : '';
	var referencia = jQ('#txtRefe').val();
	var color = jQ('#txtColor').val();
	var talla = jQ('#txtTalla').val();
	var cantidad = jQ('#cantidad').val();
	var centroCostos = jQ('#centroCostos').val();
	var mensaje = '';
	if(val1 == ''){
		if(destinatario == 'COMPRADORA'){
			mensaje += 'Debe digitar el código interno de la compradora.<br>';
		}
		else if(destinatario == 'REGION'){
			mensaje += 'Debe seleccionar la región.<br>';
		}
		else if(destinatario == 'ZONA'){
			mensaje += 'Debe seleccionar la zona.<br>';
		}
		else if(destinatario == 'VOTRE'){
			val1 = destinatario;
		}
	}
	if(referencia == ''){
		mensaje += 'Debe digitar la referencia.<br>';
	}
	if(color == ''){
		mensaje += 'Debe digitar el color.<br>';
	}
	if(talla == ''){
		mensaje += 'Debe digitar la talla.<br>';
	}
	if(cantidad == ''){
		mensaje += 'Debe digitar la cantidad.<br>';
	}
	else if(parseInt(cantidad) <= 0){
		mensaje += 'La cantidad debe ser mayor a cero.<br>';
	}
	if(centroCostos == ''){
		mensaje += 'Debe digitar el centro de costos.<br>';
	}
	if(mensaje != ''){
		mostrarMensaje(mensaje);
	}
	else{
		var idPeticion = 0;
		init("pedidoscsl.validarSolicitudAjax.do", idPeticion);
	    req[idPeticion].onreadystatechange =function() { getReqValidarSolicitud(idPeticion); };
 	    req[idPeticion].send('destinatario='+destinatario+'&val1='+val1+'&referencia='+referencia+'&color='+color+'&talla='+talla+'&cantidad='+cantidad+'&centroCostos='+centroCostos);
 	   mostrarCargar();
	}
}

function getReqValidarSolicitud(idPeticion){
	if (req[idPeticion].readyState == 4) {
		if (req[idPeticion].status == 200){
			try{
				var spSts = req[idPeticion].responseXML.getElementsByTagName("spSts")[0].childNodes[0].nodeValue;
				var spDes = req[idPeticion].responseXML.getElementsByTagName("spDes")[0].childNodes[0].nodeValue;
				if(spSts != '0'){
					mostrarMensaje(spDes);
				}
				else{
					var val1 = req[idPeticion].responseXML.getElementsByTagName("val1")[0].childNodes[0].nodeValue;
					var sku = req[idPeticion].responseXML.getElementsByTagName("sku")[0].childNodes[0].nodeValue;
					var referencia = req[idPeticion].responseXML.getElementsByTagName("referencia")[0].childNodes[0].nodeValue;
					var color = req[idPeticion].responseXML.getElementsByTagName("color")[0].childNodes[0].nodeValue;
					var talla = req[idPeticion].responseXML.getElementsByTagName("talla")[0].childNodes[0].nodeValue;
					var descripcion = req[idPeticion].responseXML.getElementsByTagName("spDes")[0].childNodes[0].nodeValue;
					var cantidad = req[idPeticion].responseXML.getElementsByTagName("cantidad")[0].childNodes[0].nodeValue;
					var centroCostos = req[idPeticion].responseXML.getElementsByTagName("centroCostos")[0].childNodes[0].nodeValue;
					agregarFila(val1, sku, referencia, color, talla, descripcion, cantidad, centroCostos);
					
					//Se limpian los campos
					jQ('#val1').val('');
					jQ('#txtRefe').val('');
					jQ('#txtColor').val('');
					jQ('#txtTalla').val('');
					jQ('#cantidad').val('');
					jQ('#centroCostos').val('');
				}
				ocultarCargar();
			}
			catch(ex){
				ocultarCargar();
				//alert(ex);
			}
		}
	}
}

function agregarFila(val1, sku, referencia, color, talla, descripcion, cantidad, centroCostos){
	document.getElementById("divSolicitudes").style.display = 'inline';
	var tabla=document.getElementById("tSolicitudes");
	var tbody=tabla.getElementsByTagName("tbody")[0];
	ubicacion = tbody.rows.length;
	numrows = ubicacion+1;
	jQ('#nroRegistros').val(numrows);
	
	var newRow = tbody.insertRow(ubicacion);
	newRow.className = "filaTabla";
	newRow.id = "row"+numrows;
	var a=newRow.insertCell(0);
	a.innerHTML="<span>"+val1+"</span><input type='hidden' id='variable"+numrows+"' name='variable"+numrows+"' value='"+(val1 == 'VOTRE' ? '' : val1)+"'/>";
	
	var b=newRow.insertCell(1);
	b.innerHTML="<span>"+referencia+"</span><input type='hidden' id='sku"+numrows+"' name='sku"+numrows+"' value='"+sku+"'/>";
	
	var c=newRow.insertCell(2);
	c.innerHTML="<span>"+color+"</span>";
	
	var c=newRow.insertCell(3);
	c.innerHTML="<span>"+talla+"</span>";
	
	var d=newRow.insertCell(4);
	d.innerHTML="<span>"+descripcion+"</span>";
	
	var e=newRow.insertCell(5);
	e.innerHTML="<span>"+cantidad+"</span><input type='hidden' id='cantidad"+numrows+"' name='cantidad"+numrows+"' value='"+cantidad+"'/>";
	
	var f=newRow.insertCell(6);
	f.innerHTML="<span>"+centroCostos+"</span><input type='hidden' id='centroCostos"+numrows+"' name='centroCostos"+numrows+"' value='"+centroCostos+"'/>";
	
	var g=newRow.insertCell(7);
	g.innerHTML="<input type='button' class='boton01' style='cursor: pointer;' onclick='eliminarFila("+numrows+");' value='ELIMINAR'/>";
}

function eliminarFila(numrows){
	var row = document.getElementById('row'+numrows);
	row.parentNode.removeChild(row);
	jQ('#nroRegistros').val((numrows-1));
}

function grabarPedidos(){
	var forma = document.forms[0];
	var nroRegsitros = parseInt(jQ('#nroRegistros').val());
	var mensaje = '';
	if(nroRegsitros > 2000){
		mensaje += "Se ha sobrepasado el límite de 2000 registros permitidos para procesar correctamente.<br>";
		mostrarMensaje(mensaje);
		return;
	}
	if(nroRegsitros > 0){
		forma.action = "pedidoscsl.grabarPedidos.do";
		mostrarCargar();
		forma.submit();
	}
	else{
		mostrarMensaje("Debe agregar primero los pedidos para grabar la solicitud.");
	}
}

function regresarListaPedidos(){
	var forma = document.forms[0];
	forma.action = "pedidoscsl.listarPedidos.do?accion=P";
	mostrarCargar();
	forma.submit();
}

function consultarSolicitud(){
	var forma = document.forms[0];
	var usuario = jQ('#usuario').val();
	var orden = jQ('#numOrden').val();
	var cedula = jQ('#cedula').val();
	var mensaje = '';
	if(usuario == '' && orden == '' && cedula == ''){
		mensaje += "Debe digitar uno de los campos a consultar.<br>"
	}
	if(mensaje != ''){
		mostrarMensaje(mensaje);
	}
	else{
		forma.action = "pedidoscsl.consultarSolicitud.do";
		mostrarCargar();
		forma.submit();
	}
}

function borrarOtrosCampos(cmp){
	if(cmp != 'usuario'){
		jQ('#usuario').val("");
	}
	if(cmp != 'numOrden'){
		jQ('#numOrden').val("");
	}
	if(cmp != 'cedula'){
		jQ('#cedula').val("");
	}
}

function regresarConsultar(){
	var forma = document.forms[0];
	forma.action = "pedidoscsl.jspConsultarSolicitud.do";
	mostrarCargar();
	forma.submit();
}

function consultarDetalleSolicitud(orden, estrategia, desPedido){
	var forma = document.forms[0];
	jQ('#ordenConsultar').val(orden);
	jQ('#estrategia').val(estrategia);
	jQ('#desPedido').val(desPedido);
	forma.action = "pedidoscsl.consultarDetalleSolicitud.do";
	mostrarCargar();
	forma.submit();
}

function regresarListaSolicitudes(){
	var forma = document.forms[0];
	forma.action = "pedidoscsl.consultarSolicitud.do";
	mostrarCargar();
	forma.submit();
}

function consultarAuditoriaSolicitud(){
	var forma = document.forms[0];
	forma.action = "pedidoscsl.consultarAuditoriaSolicitud.do";
	mostrarCargar();
	forma.submit();
}

function regresarDetalleSolicitud(){
	var forma = document.forms[0];
	forma.action = "pedidoscsl.consultarDetalleSolicitud.do";
	mostrarCargar();
	forma.submit();
}

function importarExcel(){
	if(validarArchivo()){
		var forma = document.forms[0];
		forma.action = "pedidoscsl.importarExcel.do";
		mostrarCargar();
		forma.submit();
	}
}

function validarArchivo(){
	var archivo = jQ('#archivo').val();
	var extensiones_permitidas = new Array(".xls");
	
	if (archivo == null || archivo == '') {
		mostrarMensaje ('No se ha seleccionado el archivo a importar.');
		return false;
	}
	else{
		var extension = (archivo.substring(archivo.lastIndexOf("."))).toLowerCase();
		var permitida = false; 
      	for (var i = 0; i < extensiones_permitidas.length; i++) { 
	        if (extensiones_permitidas[i] == extension) { 
		        permitida = true; 
		        break; 
         	}
        }
	}	
	if (!permitida) { 
		mostrarMensaje ("Comprueba la extensión del archivo a subir. Sólo se puede subir archivos en formato .xls"); 
		jQ('#archivo').val('');
		return false;	
    } 
	return true;
}

function regresarImportarExcel(){
	var forma = document.forms[0];
	forma.action = "pedidoscsl.verImportarExcel.do";
	mostrarCargar();
	forma.submit();
}