function consultarAprobaciones(todos,pag)
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
	forma.action = "activaciondemandas.consultarAprobaciones.do";
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

function limpiarCampos(pos){
	jQuery("#txtRefe_"+pos).val("");
	jQuery("#txtColor_"+pos).val("");
	jQuery("#txtTalla_"+pos).val("");
	jQuery("#txtCantidad_"+pos).val(1);
	jQuery("#tipoDespacho_"+pos).val("");
	
	mostrarCampos(pos,0);
}

function mostrarCampos(pos, accion){
	if(accion == 1){
		var sku = jQuery("#skuSustituto_"+pos).val();
		if(sku != ''){
			var refe = sku.slice(0,7);
			var color = sku.slice(8,11);
			var talla = sku.slice(12,15);
			jQuery("#txtRefe_"+pos).val(refe);
			jQuery("#txtColor_"+pos).val(color);
			jQuery("#txtTalla_"+pos).val(talla);
		}
		jQuery("#divSku_"+pos).show();
		jQuery("#divCantidad_"+pos).show();
		jQuery("#divTipo_"+pos).show();
		jQuery("#txtRefe_"+pos).focus();
	}
	else{
		jQuery("#divSku_"+pos).hide();
		jQuery("#divCantidad_"+pos).hide();
		jQuery("#divTipo_"+pos).hide();
	}
}

function procesarAprobacion(pos){
	var validarSku = true;
	var accion;
	if( jQuery("#eleccionA_"+pos).is(':checked') || jQuery("#eleccionR_"+pos).is(':checked')) {  
		if(jQuery("#eleccionR_"+pos).is(':checked')){
			validarSku = false;
			accion = jQuery("#eleccionR_"+pos).val();
		}
		else{
			accion = jQuery("#eleccionA_"+pos).val();
		}
	}
	else{
		mostrarMensaje("Debe seleccionar alguna operación.");
		return;
	}
	
	var refe = jQuery("#txtRefe_"+pos).val();
	var color = jQuery("#txtColor_"+pos).val();
	var talla = jQuery("#txtTalla_"+pos).val();
	var cantidad = jQuery("#txtCantidad_"+pos).val();
	var campana = jQuery("#campana_"+pos).val();
	var tipoDespacho = jQuery("#tipoDespacho_"+pos).val();
	var sku = jQuery("#sku_"+pos).val();
	var codInterno = jQuery("#codInterno_"+pos).val();
	var orden = jQuery("#orden_"+pos).val();

	if(validarSku){
		if(refe.trim() == ""){
			jQuery("#txtRefe_"+pos).focus();
			return;
		}

		if(cantidad.trim() == ""){
			mostrarMensaje("Debe indicar la cantidad.");
			jQuery("#txtCantidad_"+pos).focus();
			return;
		}
		else{
			if(!esNumero(cantidad)){
				mostrarMensaje("Cantidad es invalida.");
				jQuery("#txtCantidad_"+pos).focus();
				return;
			}
			else{
				if(parseInt(cantidad) == 0){
					mostrarMensaje("Cantidad es invalida.");
					jQuery("#txtCantidad_"+pos).focus();
					return;
				}
			}
		}
		if(tipoDespacho.trim() == ""){
			mostrarMensaje("Debe indicar el tipo de despacho.");
			jQuery("#tipoDespacho"+pos).focus();
			return;
		}
	}
	
	//Se procede a consultar por ajax
	var url = "activaciondemandas.procesarAprobacionAjax.do";
	var formData = {txtRefe:refe, 
			txtColor:color, 
			txtTalla:talla, 
			cantidad:cantidad,
			campana:campana,
			tipoDespacho:tipoDespacho,
			sku:sku,
			codInterno:codInterno,
			orden:orden,
			accion:accion};
	jQuery.ajax({
	    url : url,
	    type: "POST",
	    data : formData,
	    success: function(data, textStatus, jqXHR)
	    {
	    	//alert(jQuery(data).find('valor').text());
	    	//msgInfo(jQuery(data).find('valor').text(), jQuery(data).find('clave').text());
	    	var status = jQuery(data).find('status').text();
	    	if(status == '0'){
	    		jQuery("#divProcesar_"+pos).hide();
	    		jQuery("#tr_"+pos).hide();
	    	}
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
