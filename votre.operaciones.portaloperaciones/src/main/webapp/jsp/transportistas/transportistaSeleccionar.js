function buscarTransportista() {
	var forma = document.forms[0];
	var Transpor = document.getElementById("transportistasComboTranspor").value;
	var Campana = document.getElementById("transportistasComboCampana").value;
	var Estado = document.getElementById("transportistasComboEstado").value;

	forma.buscarTranspor.value = Transpor;
	forma.buscarCampana.value = Campana;
	forma.buscarEstado.value = Estado;
	forma.action = "transportistas.transporSeleccionar.do";
	forma.target = "contenido";
	mostrarCargar();
	forma.submit();
}

function generarExcel() {
	var forma = document.forms[0];
	var Transpor = document.getElementById("transportistasComboTranspor").value;
	var Campana = document.getElementById("transportistasComboCampana").value;
	var Estado = document.getElementById("transportistasComboEstado").value;

	forma.buscarTranspor.value = Transpor;
	forma.buscarCampana.value = Campana;
	forma.buscarEstado.value = Estado;

	forma.action = "transportistas.transportistasSeleccionarXLS.do";
	mostrarCargar();
	forma.submit();
}

function mostrarCargar() {
	jQuery.blockUI({
		message : '... PROCESANDO ...',
		css : {
			border : 'none',
			padding : '20px',
			backgroundColor : '#fff',
			'-webkit-border-radius' : '10px',
			'-moz-border-radius' : '10px',
			opacity : .4,
			color : '#000'
		}
	});

	setTimeout(jQuery.unblockUI, 9000);
}

function mostrarExito(message) {

	if(message != null && message !=''){
		jQuery.blockUI({
			message : message,
			css : {
				border : 'none',
				padding : '20px',
				backgroundColor : '#fff',
				'-webkit-border-radius' : '10px',
				'-moz-border-radius' : '10px',
				opacity : .9,
				color : '#000'
			}
		});
		
		setTimeout(jQuery.unblockUI, 2000);		
	}
}

function ocultarCargar() {
	jQuery.unblockUI();
}

function buscarGuia() {
	// Declare variables
	var input, filter, tdGuia, tdCedula, td, i, txtValueGuia, txtValueCedula, txtValueZona;
	filterGuia = $("#numeroGuia").val().toUpperCase();
	filterCedula = $("#numeroCedula").val().toUpperCase();
	filterZona = $("#numeroZona").val().toUpperCase();

	// Loop through all table rows, and hide those who don't match the search
	// query
	$("#TableDatos tbody tr")
			.each(
					function() {
						var arrayTd = $(this).find("td");
						tdGuia = arrayTd[3];
						tdCedula = arrayTd[5];
						tdZona = arrayTd[4];
						var inputStatus = $(this).find("input");
						if (tdGuia || tdCedula) {
							txtValueGuia = tdGuia.textContent
									|| tdGuia.innerText;
							txtValueCedula = tdCedula.textContent
									|| tdCedula.innerText;
							txtValueZona = tdZona.textContent
									|| tdZona.innerText;
							if ((txtValueGuia.toUpperCase().indexOf(filterGuia) > -1
									&& txtValueCedula.toUpperCase().indexOf(
											filterCedula) > -1 && txtValueZona
									.toUpperCase().indexOf(filterZona) > -1)
									|| inputStatus.is(':checked')) {
								$(this).css("display", "");
							} else {
								$(this).css("display", "none");
							}
						}
					});
}

function cambiarEstado() {
	var forma = $("#formCambiarEstado");
	var arrayReversarEstado = [];

	$("#TableDatos tbody tr").each(function() {
		var inputStatus = $(this).find("input");
		if (inputStatus.is(':checked')) {
			var array = inputStatus.val().split("-");
			arrayReversarEstado.push({
				"orden" : array[0],
				"guia" : array[1]
			});
		}
	});
	var jsonInput = {
		"results" : arrayReversarEstado
	};
	$("#arrayReversar").val(JSON.stringify(jsonInput) + '%');

	forma.submit();
}
