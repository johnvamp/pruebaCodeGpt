function cerrarCamion(accion){
	var forma = document.forms[0];
	var total = document.getElementById('txtTotal').value;
	var respuesta = document.getElementById('respuesta').value;
	if(respuesta == "S"){
		if(total != "0"){
			forma.action = "embarque.cerrarEmbarque.do";
			forma.submit();	
		}
		else{
			alert("Para cerrar el camión, el número de cajas debe ser mayor a 0.");
		}
	}
	else{
		if(accion == "cerrarEmbarque"){
			document.getElementById('nomAccion').value = "cerrarEmbarque";
			forma.action = "embarque.verAccionesEmbarque.do";
			forma.submit();	
		}
		if(accion == "entregaTotal"){
			if(respuesta = "Si"){
				forma.action = "entrega.confirmarEntrega.do";
				forma.submit();
			}
			else{
				forma.action = "embarque.cargarTitulos.do";
				forma.submit();
			}
		}
	}
}

function regresar(accion){
	var forma = document.forms[0];
	if(accion == "cerrarEmbarque"){
		document.getElementById('nomAccion').value = "cerrarEmbarque";
		forma.action = "embarque.verAccionesEmbarque.do";
		forma.submit();	
	}
	if(accion == "entregaTotal"){
		forma.action = "entrega.verEntrega.do";
		forma.submit();
	}
}