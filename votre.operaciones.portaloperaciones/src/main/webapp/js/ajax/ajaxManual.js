var req = new Array();
var refrescarDatos = false;

function init(accion, idPeticion){
	if (window.XMLHttpRequest) {
		req[idPeticion] = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		try {
			req[idPeticion] = new ActiveXObject("MSXML2.XMLHTTP");
		} catch (e) {
			try {
				req[idPeticion] = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {}
		}
	}
	
	var url = accion;
	req[idPeticion].open("POST", url, true);
	req[idPeticion].setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
}

function eliminarItemZonasAjax(numeroFila, codigo, referencia, cantidad, idPeticion){
	init("pedidosZonas.eliminarItemAjax.do", idPeticion);
	
	req[idPeticion].onreadystatechange = function() { getReqEliminarItemZonas(idPeticion, numeroFila); };
 	req[idPeticion].send("lineaGrabacion="+codigo+"&cantidadPedida="+cantidad+"&numeroFila="+numeroFila+"&referencia="+referencia);
}

function getReqEliminarItemZonas(idPeticion, numeroFila){
	if (req[idPeticion].readyState == 4) {
		if (req[idPeticion].status == 200) {
			var item = req[idPeticion].responseXML.getElementsByTagName("items");
			
			try{
				var status = req[idPeticion].responseXML.getElementsByTagName("status")[0].childNodes[0].nodeValue
				var descStatus = req[idPeticion].responseXML.getElementsByTagName("descStatus")[0].childNodes[0].nodeValue
				
				if(status == "0"){
					document.getElementById("status").value = "";
					document.getElementById("descStatus").value = "";
					deleteFila(numeroFila);
				}else{
					document.getElementById("status").value = status;
					document.getElementById("descStatus").value = descStatus;
				}
				loadAdicionarPedido();
			}catch(ex){}
		}
	}
}

var intervalIdRecarga;
function verificarDesbloqueo(){
	intervalIdRecarga = setInterval(function() { 
		habilitarPaginaAjax(1);
	}, (2000)); 

}

function habilitarPaginaAjax(idPeticion){
	init("habilitarPagina.do", idPeticion);

	req[idPeticion].onreadystatechange = function() { getReqHabilitarPagina(idPeticion); };
	req[idPeticion].send("");
}

function getReqHabilitarPagina(idPeticion){
	if (req[idPeticion].readyState == 4) {
         if (req[idPeticion].status == 200) {
			var puede = req[idPeticion].responseXML.getElementsByTagName("puede")[0].childNodes[0].nodeValue;
         	//alert(puede);
			if(puede == "1"){
				clearInterval(intervalIdRecarga);
				if(refrescarDatos){
					refrescar();
					refrescarDatos = false;
				}
				ocultarCargar();
			}
         }
	}
}
