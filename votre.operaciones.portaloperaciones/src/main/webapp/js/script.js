

$(document).ready(function(e) {
	$("#paisSelecionado").click(function(){
		$("#menu").slideToggle();
	});
	
});

function cambiarSeleccion(codPais){
	var cadPais = "";
	
	switch(codPais){
		case "56":
			cadPais = "CHILE";
			break;
		case "57":
			cadPais = "COLOMBIA";
			break;
		case "506":
			cadPais = "COSTA RICA";
			break;
		case "593":
			cadPais = "ECUADOR";
			break;
		case "34":
			cadPais = "ESPAÑA";
			break;
		case "502":
			cadPais = "GUATEMALA";
			break;
		case "52":
			cadPais = "MEXICO";
			break;
		case "51":
			cadPais = "PERU";
			break;
		case "17":
			cadPais = "PUERTO RICO";
			break;
		default:
			cadPais = "COLOMBIA";
			break;
	}
	
	$("#paisSelecionado").html(cadPais);
	$("#codPais").val(codPais);
	$("#menu").slideToggle();
}