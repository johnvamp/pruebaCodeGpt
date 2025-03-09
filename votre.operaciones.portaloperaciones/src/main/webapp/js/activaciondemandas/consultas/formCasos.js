function consultarCasos(todos, excel, pag)
{
	var forma = document.forms[0];
	var porReferencia = forma.SELECCION_REFERENCIA.value;
	
	if(forma.criterioBuscar.value.trim() == ""){
		mostrarMensaje("Debe indicar algún criterio.");
		forma.criterioBuscar.focus();
		return;
	}
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
	forma.generaExcel.value=excel;
	forma.action = "activaciondemandas.consultarCasos.do";
	mostrarCargar();
	if(excel != ""){
		verificarDesbloqueo();
	}
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
		//cedulaTransportista : jQuery("#cedulaTransportista_"+pos).val(),
		direccionDespacho : jQuery("#direccionDespacho_"+pos).val(),
		barrioDespacho : jQuery("#barrioDespacho_"+pos).val(),
		ciudadDespacho : jQuery("#ciudadDespacho_"+pos).val(),
		deptoDespacho : jQuery("#deptoDespacho_"+pos).val(),
		tel1Despacho : jQuery("#tel1Despacho_"+pos).val(),
		tarifa : jQuery("#tarifa_"+pos).val(),
		fechaRegistro : jQuery("#fechaRegistro_"+pos).val(),
		tipoDespacho : jQuery("#tipoDespacho_"+pos).val(),
		dsTransportadora : jQuery("#dsTransportadora_"+pos).val(),
		nombreTransportista : jQuery("#nombreTransportista_"+pos).val(),
		guiaMaster : jQuery("#guiaMaster_"+pos).val()
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

