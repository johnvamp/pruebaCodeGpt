/*
Muestra los campos de las fechas y el boton "GENERAR LABELS" de acuerdo
a la seleccion en los Label Pendientes
*/
function habilitarCamposDeFechas(campos){	
	campos = campos.value.split("-")[0];
	var muestraFechas=false;
	var muestraLabels=false;
	var muestraZonas=false;
	var muestraCampana=false;
	var codCia = document.getElementById("codCia").value;
	var despliegaCampana = document.getElementById("despliegaCampana");
	var despliegaLabels=document.getElementById('despliegaLabels');
	var despliegaFechas=document.getElementById('despliegaFechas');
	var despliegaBoton=document.getElementById('despliegaBotonFechas');
	var despliegaZonas=document.getElementById('despliegaZonas');	
	var despliegaZonas2=document.getElementById('despliegaZonas2');
	
	if(campos ==8 && codCia==450){			
		muestraCampana=true;					
		despliegaCampana.style.display="inline";	
	}
	else{
		despliegaCampana.style.display="none";	
		//limpiar el campo campaña
		document.getElementById('txtCampana').value="";
	}
	
	//capturamos los valores de las banderas para mostrar o no las Fechas y los Labels
	var bandFechas = document.getElementsByName('indFecha');
	for (i=0; i<bandFechas.length; i++) 
	{
		if(bandFechas[i].value==campos)
		{
			muestraFechas=true;
			break;
		}
	}
	
	var bandLabels = document.getElementsByName('indLabels');
	for (i=0; i<bandLabels.length; i++) 
	{
		if(bandLabels[i].value==campos)
		{
			muestraLabels=true;
			break;
		}
	}	
	
	if(muestraLabels){
		despliegaLabels.style.display="inline";
	}
	else{
		despliegaLabels.style.display="none";
	}
	
	if (codCia==450 || codCia==200 || codCia == 350 || codCia == 650 || codCia == 600){
		muestraZonas=true;
	}
	
	if(muestraFechas)
	{
		despliegaFechas.style.display="inline";
		despliegaBoton.style.display="inline";
	}
	else{
		despliegaFechas.style.display="none";
		despliegaBoton.style.display="none";

		//limpiar los campos de las fechas
		document.getElementById('txtDiaI').value="";
		document.getElementById('txtMesI').value="";
		document.getElementById('txtAnoI').value="";

		//Variables de captura de datos de fecha final
		document.getElementById('txtDiaF').value="";
		document.getElementById('txtMesF').value="";
		document.getElementById('txtAnoF').value="";
		document.getElementById('fechaIni').value="";
		document.getElementById('fechaFin').value="";
	}
	
	if(muestraZonas){
		despliegaZonas.style.display="inline";
		despliegaZonas2.style.display="inline";
	}
	else{
		despliegaZonas.style.display="none";
		despliegaZonas2.style.display="none";
	}
}

function completaLongitud(campo,longitud){
	var campoValidar = document.getElementById(campo).value;
	if((campoValidar.length<longitud) && (campoValidar.length>0)) {			
		campoValidar= '0'+campoValidar;
		document.getElementById(campo).value=campoValidar;
		return false;
	}
}

/*
Envia detalle de labels a excel
*/
function generarExcel(){
	var forma = document.forms[0];
	var codCia = document.getElementById("codCia").value;
	var generoReporteXLS = document.getElementById('generoReporteXLS').value;
	var codigoLabel = document.getElementsByName("opcionLabel");
	var campana = document.getElementById('txtCampana').value;
	var zona = document.getElementById('zonas').value;
	var zona2 = document.getElementById('zonas2').value;
	var seleccion = '';
	var cantidad = '';
	var titulo = '';
	var mensaje='';	
	var fecIni = '';
	var fecFin = '';
	
	//Variables de captura de datos de fecha inicial
	var Y1 = document.getElementById('txtDiaI').value;
	var M1 = document.getElementById('txtMesI').value;
	var D1 = document.getElementById('txtAnoI').value;
	//Variables de captura de datos de fecha final
	var Y2 = document.getElementById('txtDiaF').value;
	var M2 = document.getElementById('txtMesF').value;
	var D2 = document.getElementById('txtAnoF').value;
	
	if (document.getElementById('despliegaCampana').style.display != 'none'){
		if (campana == ''){
			mensaje = mensaje + '\n Debe digitar la campaña.'
		}
		if (zona2 == '0'){
			mensaje = mensaje + '\n Debe seleccionar la zona.'
		}
	}
	
	if (document.getElementById('despliegaFechas').style.display != 'none') {
		for (var i=0; i<codigoLabel.length; i++) {
			if (codigoLabel[i].checked) {
				seleccion = codigoLabel[i].value.split("-")[0];
				cantidad = codigoLabel[i].value.split("-")[1];
				titulo = codigoLabel[i].value.split("-")[2];
			}
		}
		if(seleccion == '' ){
		mensaje = mensaje + '\n Debe seleccionar una opción.'
		}
		
		if(cantidad == 0){
			mensaje = mensaje + '\n No existen registros para generar el informe.'
		}
		
		//'se valida la fecha
		//Validacion para cuando no se ingresa el mes o es mayor a 12 y menor que 1
		if(M1!=''){
			if(M1>12){
				mensaje = mensaje + '\n El mes en la fecha inicial no puede ser mayor a 12.'
			}else if(M1<=0){
				mensaje = mensaje + '\n El mes en la fecha inicial no puede ser menor que 0.'
			}	
		}else{
			mensaje = mensaje + '\n Debe digitar el mes de la fecha inicial.'
		}
		if(M2!=''){
			if(M2>12){
				mensaje = mensaje + '\n El mes en la fecha final no puede ser mayor a 12.'
			}else if(M2<=0){
				mensaje = mensaje + '\n El mes en la fecha final no puede ser menor que 0.'
			}
		}else{
			mensaje = mensaje + '\n Debe digitar el mes de la fecha final.'
		}
	
		//Validacion para cuando no se ingresa el dia o es mayor a 31 y menor que 1
		if(D1!=''){
			if(D1>31){
				mensaje = mensaje + '\n El día en la fecha inicial no puede ser mayor a 31.'
			}else if(D1<=0){
				mensaje = mensaje + '\n El día en la fecha inicial no puede ser menor que 0.'
			}
		}else{
			mensaje = mensaje + '\n Debe digitar el día de la fecha inicial.'
		}
		if(D2!=''){
			if(D2>31){
				mensaje = mensaje + '\n El día en la fecha final no puede ser mayor a 31.'
			}else if(D2<=0){
				mensaje = mensaje + '\n El día en la fecha final no puede ser menor que 0.'
			}
		}else{
			mensaje = mensaje + '\n Debe digitar el día de la fecha final.'
		}
	
		//Validacion para cuando no se ingresa el año
		if(Y1==''){
			mensaje = mensaje + '\n Debe digitar el año de la fecha inicial.'
		}else{
			if(Y1.length<4){
				mensaje= mensaje + '\n El año de la fecha inicial debe ser de longitud 4';
			}
		}
		if(Y2==''){
			mensaje = mensaje + '\n Debe digitar el año de la fecha final.'
		}else{
			if(Y2.length<4){
				mensaje= mensaje + '\n El año de la fecha final debe ser de longitud 4';
			}
		}
		
		//Se valida que la fecha inicial no sea mayor que la final
		if(mensaje==''){
			var fecha1 = new Date(Y1 , (parseInt(M1) - 1), D1) ;
//			fecha1.setFullYear(Y1);
//			fecha1.setMonth((parseInt(M1)-1));
//			fecha1.setDate(D1);
			var fecha2 = new Date(Y2 , (parseInt(M2) - 1), D2) ;
//			fecha2.setFullYear(Y2);
//			fecha2.setMonth((parseInt(M2)-1));
//			fecha2.setDate(D2);
			if(fecha1>fecha2){
				mensaje = mensaje + '\n La fecha inicial no puede ser mayor que la final.'
			}
		}
		
		if (zona == '0'){
			mensaje = mensaje + '\n Debe seleccionar la zona.'
		}
	
		var fecIni = Y1+M1+D1;
		var fecFin = Y2+M2+D2;
		
		if(mensaje!= ''){
			alert(mensaje);
			return false;
		}
		else{
			if(seleccion!=''){
				document.getElementById("codigoLabel").value = seleccion;
				document.getElementById("fechaIni").value = fecIni;
				document.getElementById("fechaFin").value = fecFin;
				document.getElementById("zona").value = zona;
				document.getElementById("parametro").value = "generarExcel";
				document.getElementById("titulo").value = titulo;
				document.getElementById("generoReporteXLS").value=1;
				forma.action="reportes.generarReportes.do";
				mostrarCargar();
				forma.submit();
				verificarDesbloqueo();
			}
			return true;		
		}
	}
	else{
		if(mensaje!= ''){
			alert(mensaje);
			return false;
		}
		for (var i=0; i<codigoLabel.length; i++) {
			if (codigoLabel[i].checked) {
				seleccion = codigoLabel[i].value.split("-")[0];
				cantidad = codigoLabel[i].value.split("-")[1];
				titulo = codigoLabel[i].value.split("-")[2];
			}
		}
		if(seleccion!=''){	
			if(cantidad > 0 || seleccion == 2 || seleccion == 3){
				document.getElementById("codigoLabel").value = seleccion;
				document.getElementById("fechaIni").value = fecIni;
				document.getElementById("fechaFin").value = fecFin;
				document.getElementById("zona").value = zona2;
				document.getElementById("parametro").value = "generarExcel";
				document.getElementById("titulo").value = titulo;
				document.getElementById("generoReporteXLS").value=1;
				forma.action="reportes.generarReportes.do";
				mostrarCargar();
				forma.submit();
				verificarDesbloqueo();		
			}
			else{
				alert("No existen registros para generar el informe.");
			}
		}
		else{
			alert('Debe seleccionar una opción.');
		}
		return false;
	}
}

/*
Genera un doc de excel para control de envio de catalogos. Para labels pendientes.
*/
function generarLabels(){
	var forma = document.forms[0];
	var codCia = document.getElementById("codCia").value;
	var generoReporteXLS = document.getElementById('generoReporteXLS').value;
	var codigoLabel = document.getElementsByName("opcionLabel");
	var seleccion = '';
	var cantidad = '';
	var titulo = '';
	
	for (var i=0; i<codigoLabel.length; i++) {
		if (codigoLabel[i].checked) {
			seleccion = codigoLabel[i].value.split("-")[0];
			cantidad = codigoLabel[i].value.split("-")[1];
			titulo = codigoLabel[i].value.split("-")[2];
		}
	}
	if(generoReporteXLS=="1" || cantidad != "0"){
		document.getElementById("codigoLabel").value = seleccion;
		document.getElementById("parametro").value = "generarLabel";
		document.getElementById("titulo").value = titulo;
		forma.action="reportes.generarReportes.do";
		mostrarCargar();
		forma.submit();
		refrescarDatos = true;
		verificarDesbloqueo();
	}
	else{
		alert('Debe generar primero el detalle de labels en excel.');
		return false;	
	}
}

function solicitar(){
	//Variables de captura de datos de fecha inicial
	var Y1 = document.getElementById('txtAnoI').value;
	var M1 = document.getElementById('txtMesI').value;
	var D1 = document.getElementById('txtDiaI').value;
	//Variables de captura de datos de fecha final
	var Y2 = document.getElementById('txtAnoF').value;
	var M2 = document.getElementById('txtMesF').value;
	var D2 = document.getElementById('txtDiaF').value;
	
	var codCia = document.getElementById("codCia").value;
	var zona = document.getElementById('zonas').value;
	var codigoLabel = document.getElementsByName("opcionLabel");
	var seleccion = '';	
	var mensaje=''
	
	for (var i=0; i<codigoLabel.length; i++) {
		if (codigoLabel[i].checked) {
			seleccion = codigoLabel[i].value.split("-")[0];
		}
	}
	
	if(seleccion == '' ){
		mensaje = mensaje + '\n Debe seleccionar una opción.'
	}
	
	//'se valida la fecha
	//Validacion para cuando no se ingresa el mes o es mayor a 12 y menor que 1
	if(M1!=''){
		if(M1>12){
			mensaje = mensaje + '\n El mes en la fecha inicial no puede ser mayor a 12.'
		}else if(M1<=0){
			mensaje = mensaje + '\n El mes en la fecha inicial no puede ser menor que 0.'
		}	
	}else{
		mensaje = mensaje + '\n Debe digitar el mes de la fecha inicial.'
	}
	if(M2!=''){
		if(M2>12){
			mensaje = mensaje + '\n El mes en la fecha final no puede ser mayor a 12.'
		}else if(M2<=0){
			mensaje = mensaje + '\n El mes en la fecha final no puede ser menor que 0.'
		}
	}else{
		mensaje = mensaje + '\n Debe digitar el mes de la fecha final.'
	}

	//Validacion para cuando no se ingresa el dia o es mayor a 31 y menor que 1
	if(D1!=''){
		if(D1>31){
			mensaje = mensaje + '\n El día en la fecha inicial no puede ser mayor a 31.'
		}else if(D1<=0){
			mensaje = mensaje + '\n El día en la fecha inicial no puede ser menor que 0.'
		}
	}else{
		mensaje = mensaje + '\n Debe digitar el día de la fecha inicial.'
	}
	if(D2!=''){
		if(D2>31){
			mensaje = mensaje + '\n El día en la fecha final no puede ser mayor a 31.'
		}else if(D2<=0){
			mensaje = mensaje + '\n El día en la fecha final no puede ser menor que 0.'
		}
	}else{
		mensaje = mensaje + '\n Debe digitar el día de la fecha final.'
	}

	//Validacion para cuando no se ingresa el año
	if(Y1==''){
		mensaje = mensaje + '\n Debe digitar el año de la fecha inicial.'
	}else{
		if(Y1.length<4){
			mensaje= mensaje + '\n El año de la fecha inicial debe ser de longitud 4';
		}
	}
	if(Y2==''){
		mensaje = mensaje + '\n Debe digitar el año de la fecha final.'
	}else{
		if(Y2.length<4){
			mensaje= mensaje + '\n El año de la fecha final debe ser de longitud 4';
		}
	}
	
	//Se valida que la fecha inicial no sea mayor que la final
	if(mensaje==''){
		var fecha1 = new Date(Y1 , (parseInt(M1) - 1), D1) ;
//		fecha1.setFullYear(Y1);
//		fecha1.setMonth((parseInt(M1)-1));
//		fecha1.setDate(D1);
		var fecha2 = new Date(Y2 , (parseInt(M2) - 1), D2) ;
//		fecha2.setFullYear(Y2);
//		fecha2.setMonth((parseInt(M2)-1));
//		fecha2.setDate(D2);
		if(fecha1>fecha2){
			mensaje = mensaje + '\n La fecha inicial no puede ser mayor que la final.'
		}
	}

	var fecIni = Y1+M1+D1;
	var fecFin = Y2+M2+D2;
	
	if((codCia == 450 || codCia == 200 || codCia == 350 || codCia == 650 || codCia == 600) && zona=='0'){
		mensaje = mensaje + '\n Debe seleccionar la zona.'
	}
	
	document.getElementById('fechaIni').value = fecIni;
	document.getElementById('fechaFin').value = fecFin;
	document.getElementById('codigoLabel').value= seleccion;
	document.getElementById("zona").value = zona;
		
	if(mensaje!= ''){
		alert(mensaje);
		return false
	}else{
		document.getElementById("codigoLabel").value = seleccion;
		document.getElementById("fechaIni").value = fecIni;
		document.getElementById("fechaFin").value = fecFin;
		document.getElementById("zona").value = zona;
		forma.action="reportes.generarInformacion.do";
		forma.submit();
		mostrarCargar();
		return true;
	}
}

function refrescar(){
	var forma = document.forms[0];
	forma.action = "labels.cargarLabels.do";
	forma.submit();
}