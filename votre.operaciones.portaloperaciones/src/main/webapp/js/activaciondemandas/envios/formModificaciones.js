function consultarEnvios(todos,pag)
{
	var forma = document.forms[0];
	var porReferencia = forma.SELECCION_REFERENCIA.value;

	if(forma.seleccionBuscar.value.trim() == ""){
		mostrarMensaje("Debe indicar alguna opción.");
		forma.seleccionBuscar.focus();
		return;
	}
	if(forma.seleccionBuscar.value.trim()==porReferencia){
		if(forma.refeBuscar.value.trim() == ""){
			mostrarMensaje("Debe indicar alguna referencia.");
			forma.refeBuscar.focus();
			return;
		}
	}
	else{
		if(forma.seleccionBuscar.value.trim()!=todos && forma.opcionBuscar.value.trim() == ""){
			mostrarMensaje("Debe indicar algún valor.");
			forma.opcionBuscar.focus();
			return;
		}
	}
	
	forma.paginaActual.value=pag;
	forma.action = "activaciondemandas.modificarEnviosManuales.do";
	mostrarCargar();
	forma.submit();
}

function mostrarValor(accion){
	var forma = document.forms[0];
	var porReferencia = forma.SELECCION_REFERENCIA.value;

	var seleccion = jQuery("#seleccionBuscar").val();
	if(seleccion == accion){
		jQuery("#trValor").hide();
	}
	else{
		jQuery("#trValor").show();
		//Ahora se verifica si la busqueda es por referencia
		if(seleccion == porReferencia){
			jQuery("#divValor").hide();
			jQuery("#divSku").show();
			jQuery("#refeBuscar").val('');
			jQuery("#colorBuscar").val('');
			jQuery("#tallaBuscar").val('');
		}
		else{
			jQuery("#opcionBuscar").val('');
			jQuery("#divValor").show();
			jQuery("#divSku").hide();
		}
	}
}

function limpiarTransportista(pos){
	jQuery("#cbTransportista_"+pos).val("");
}

function limpiarTransportadora(pos){
	jQuery("#cbTransportadora_"+pos).val("");
}

function realizarModificacion(pos){

	var txtGuia = jQuery("#txtGuia_"+pos).val();
	var cbTransportadora = jQuery("#cbTransportadora_"+pos).val();
	var cbTransportista = jQuery("#cbTransportista_"+pos).val();
	var txtFecha = jQuery("#txtFecha_"+pos).val();

	var tipoDespacho = jQuery("#tipoDespacho_"+pos).val();
	var sku = jQuery("#sku_"+pos).val();
	var skuSustituto = jQuery("#skuSustituto_"+pos).val();
	var cantidadEntregada = jQuery("#cantidadEntregada_"+pos).val();
	var codInterno = jQuery("#codInterno_"+pos).val();
	var orden = jQuery("#orden_"+pos).val();

	if(txtGuia.trim() == ""){
		jQuery("#txtGuia_"+pos).focus();
		return;
	}
	if(cbTransportadora.trim() == "" && cbTransportista.trim() == ""){
		mostrarMensaje("Debe indicar alguna transportadora o transportista.");
		jQuery("#cbTransportadora_"+pos).focus();
		return;
	}
	if(txtFecha.trim() == ""){
		mostrarMensaje("Debe indicar la fecha de embarque.");
		return;
	}
	
	//Se procede a consultar por ajax
	var url = "activaciondemandas.procesarModificacionEnvioAjax.do";
	var formData = {txtGuia:txtGuia, 
			transportadora:cbTransportadora,
			transportista:cbTransportista, 
			txtFecha:txtFecha, 
			cantidad:cantidadEntregada,
			tipoDespacho:tipoDespacho,
			sku:sku,
			skuSustituto:skuSustituto,
			codInterno:codInterno,
			orden:orden};
	jQuery.ajax({
	    url : url,
	    type: "POST",
	    data : formData,
	    success: function(data, textStatus, jqXHR)
	    {
	    	var status = jQuery(data).find('status').text();
	    	/*
	    	if(status == '0'){
	    		jQuery("#divProcesar_"+pos).hide();
	    		jQuery("#tr_"+pos).hide();
	    	}*/
    		mostrarMensaje(jQuery(data).find('dsStatus').text());
	    },
	    error: function (jqXHR, textStatus, errorThrown)
	    {
	    	alert("Hubo error en la peticion");
	    }
	});	
}

function verDetalleCaso(pos){
	/*
	var url = "activaciondemandas.verDetalleCaso.do?sku="+sku;
	jQuery.fancybox({
		href : url,
		type : 'iframe',
		frameWidth : '500',
		frameHeight : '300',
		padding : 1,
		modal : false
	});
	*/

	var url = "activaciondemandas.verDetalleCaso.do";
	var formData = {id : jQuery("#id_"+pos).val(),
		codInterno : jQuery("#codInterno_"+pos).val(),
		nombre : jQuery("#nombre_"+pos).val(),
		ciudad : jQuery("#ciudad_"+pos).val(),
		depto : jQuery("#depto_"+pos).val(),
		region : jQuery("#region_"+pos).val(),
		mailPlan : jQuery("#mailPlan_"+pos).val(),
		tipo : jQuery("#tipo_"+pos).val(),
		zona : jQuery("#zona_"+pos).val(),
		sku : jQuery("#sku_"+pos).val(),
		campana : jQuery("#campana_"+pos).val(),
		descripcion : jQuery("#descripcion_"+pos).val(),
		cantidad : jQuery("#cantidad_"+pos).val(),
		motivo : jQuery("#motivo_"+pos).val(),
		area : jQuery("#area_"+pos).val(),
		fechaDespacho : jQuery("#fechaDespacho_"+pos).val(),
		campanaReclamo : jQuery("#campanaReclamo_"+pos).val(),
		estado : jQuery("#estado_"+pos).val(),
		dsEstado : jQuery("#dsEstado_"+pos).val(),
		orden : jQuery("#orden_"+pos).val(),
		skuSustituto : jQuery("#skuSustituto_"+pos).val(),
		dsSkuSustituto : jQuery("#dsSkuSustituto_"+pos).val(),
		cantidadEntregada : jQuery("#cantidadEntregada_"+pos).val(),
		fechaAprobacion : jQuery("#fechaAprobacion_"+pos).val(),
		guia : jQuery("#guia_"+pos).val(),
		transportadora : jQuery("#transportadora_"+pos).val(),
		cedulaTransportista : jQuery("#cedulaTransportista_"+pos).val(),
		direccionDespacho : jQuery("#direccionDespacho_"+pos).val(),
		barrioDespacho : jQuery("#barrioDespacho_"+pos).val(),
		ciudadDespacho : jQuery("#ciudadDespacho_"+pos).val(),
		deptoDespacho : jQuery("#deptoDespacho_"+pos).val(),
		tel1Despacho : jQuery("#tel1Despacho_"+pos).val(),
		tarifa : jQuery("#tarifa_"+pos).val(),
		fechaRegistro : jQuery("#fechaRegistro_"+pos).val()
	};
	
	jQuery.ajax({
	    url : url,
	    type: 'POST',
	    dataType: 'html',
	    data : formData,
	    success: function(data, textStatus, jqXHR)
	    {
	    	jQuery.fancybox({
                    'title': "Detalles del caso",
                    'autoDimensions' : false,
					'width': '790',
					'height' : 'auto',
                    'content': data
            });
	    },
	    error: function (jqXHR, textStatus, errorThrown)
	    {
	    	alert("Hubo error en la peticion");
	    }
	});	

}
