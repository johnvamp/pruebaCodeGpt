//Para deshabilitar el menu del click derecho
//document.oncontextmenu=new Function("return false");

//$.noConflict();
/*
try{
	jQuery.noConflict();
}	
catch(){}	
*/

//AAAA/MM/DD
function esFechaValida(fecha){
	if(fecha == "" || fecha.length != 10){
		return false;
	}
	
	var ano = Number(fecha.substring(0,4));
	var mes = Number(fecha.substring(5,7));
	var dia = Number(fecha.substring(8,10));
	
	if( isNaN(dia) || isNaN(mes) || isNaN(ano) ){
		return false;
	}
	var num_dias = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
	if(((ano % 4 == 0) && (ano % 100 != 0)) || (ano % 400 == 0)){
		num_dias[1] = 29;
	}
	if( mes < 0  || mes > 12 || dia > num_dias[mes-1] || dia < 0){
		return false;
	}
	return true;
}

function mostrarMensaje(mensaje){
	if(mensaje != ''){
		desplegarMensaje(mensaje);
	}
}

function mostrarElemento(elemento){
	if(document.getElementById(elemento)){
		Element.show(elemento);
	}
}

function desvanecerMensaje(elemento){
	if(document.getElementById(elemento)){
		Effect.DropOut(elemento);
	}
}

function iniciarProgreso() {
	Element.show('progreso');
}

function finalizarProgreso() {
	Effect.Fade('progreso');
}

function iniciarProgresoAuxiliar() {
	Element.show('progresoAuxiliar');
}

function finalizarProgresoAuxiliar() {
	Effect.Fade('progresoAuxiliar');
}
 
/*****************************************Utilidades para los mensajes en pantalla**************************************************/
function cambiarMensaje(mensaje, introducirEspacio){
	var forma = document.forms[0];
	if(document.getElementById("mensaje")){
		if(introducirEspacio){
			mensaje = '<div align="right"><a href="javascript:desvanecerMensaje("mensaje");desvanecerMensaje("espacio");"><img src="images/cerrar.gif" border="0"/></a></div>' +'&nbsp;&nbsp;&nbsp;&nbsp;'+mensaje+"<br><br>";
		}
		document.getElementById("mensaje").innerHTML = mensaje;
		document.location.href = "#top";
	}
}


/*
	Funci�n que despliega un mensaje al usuario
	msg - Mensaje a presentar
	tipo - Tipo del mensaje: A=Alerta, E=Error, I=Informaci�n, N=Inconsistencia
	funci�n - Funci�n javascrip que se ejecuta al cerrar la ventana
	ancho - ancho de la ventana
	alto - alto de la ventana
	funcionAceptar - Funci�n javascrip que se ejecuta al aceptar la ventana
*/
function desplegarMensaje(mensaje, funcionCerrar, funcionAceptar, desaparecerBotonCerrar){

	var codigoBotones;

	if( funcionCerrar == null || funcionCerrar == '' ){
		funcionCerrar = "desvanecerMensaje('mensaje');desvanecerMensaje('espacio');";
	}

	if(!desaparecerBotonCerrar){
		mensaje = '<div align="right"><a href="javascript:'+funcionCerrar+'"><img src="imagenes/cerrar.gif" border="0"/></a></div>' + mensaje+"<br>";
	}else{
		mensaje = '<br>'+mensaje;
	}
	
	codigoBotones = '';
	
	if(funcionAceptar){
		codigoBotones +='<br><div align="center" ><a href="javascript:'+funcionAceptar+'" class="LinkCerrar" >Aceptar</a></div><br>';
	}
	
	mensaje += codigoBotones; 
	
	cambiarMensaje(mensaje, false);
	mostrarElemento('mensaje');
	mostrarElemento('espacio');
	
}

function transformarEnter(){
	var ENTER = 13;
    var TAB = 9;
   	document.onkeydown = function(){
	if(window.event){           
         //    window.event.srcElement.getAttribute('type')
         //    permite obtener el type del objeto al que se le presiona el enter.

         if (window.event.srcElement.getAttribute('type') != "file" && window.event.keyCode == ENTER){ //Si la tecla digitada es ENTER
             window.event.keyCode = TAB; //cambia la tecla ENTER por TAB.
          }
          window.event.returnValue = true; //retorne un evento valido

    	}
    }    
}

function capturarDescripcion(nombreElemento){
	var forma = document.forms[0];
	var textoSeleccionado = forma.elements[nombreElemento].options[forma.elements[nombreElemento].selectedIndex].text;
	return textoSeleccionado;
}

function checksValidos(campoCheck){

	var forma = document.forms[0];

	if(campoCheck){
		if(campoCheck.length){
			for(var i = 0; i < campoCheck.length; i++){
				if(campoCheck[i].checked){
					return true;
				}
			}
		}else{
			if(campoCheck.checked){
				return true;
			}else{
				return false;
			}
		}
	}
	return false;
}

String.prototype.trim = function() { return this.replace(/^\s+|\s+$/g, ""); };

// Valida el ingreso de caracteres inv�lidos
// Se saca de la validaci�n la tecla enter Key == 13
function validaIngresoCaracteresInv(e) {
	 var key = (navigator.appName == "Netscape") ? e.which : e.keyCode;
//alert(key);
	 //alert(String.fromCharCode(key)); //Retorna el caracter correspondiente al unicode
	//alert("~".charCodeAt(0)); //Retorna el unicode correspondiente al caracter
	if(e.shiftKey || e.altKey || e.ctrlKey) {
		return false;
	}
	if(!((key >= 8 && key <= 10) || key == 13  || key == 32  || (key >= 35 && key <= 37) 
		 || (key >= 40 && key <= 46) || (key >= 48 && key <= 59) || key == 61 || (key >= 64 && key <= 93)
		 || (key >= 94 && key <= 123) || (key >= 125 && key <= 126) || (key == 130 || key == 144) 
		 || (key >= 161 && key <= 162) || (key >= 164 && key <= 165) || (key == 180 || key == 191 
		 || key == 193 || key == 201 || key == 205 || key == 209 || key == 211 || key == 218 
		 || key == 220 || key == 225 || key == 233 || key == 237 || key == 241 
		 ||  key == 243 || key == 250 || key == 252))) {
		 		 return false;
	} 
	else {
        return true;
    }
}


// Valida el ingreso de caracteres inv�lidos
// Se saca de la validaci�n la tecla enter Key == 13
function validaIngresoCaracteresInv2(e) {
	 var key = (navigator.appName == "Netscape") ? e.which : e.keyCode;
	//alert(key);
	 //alert(String.fromCharCode(key)); //Retorna el caracter correspondiente al unicode
	//alert("~".charCodeAt(0)); //Retorna el unicode correspondiente al caracter
	if(e.shiftKey || e.altKey || e.ctrlKey) {
		return false;
	}
	if(!((key >= 8 && key <= 10) || key == 13 || key == 0 || key == 32  || (key >= 36 && key <= 37) 
		 || (key >= 40 && key <= 42) || (key >= 48 && key <= 59) || key == 61 || (key >= 65 && key <= 90)
		 || (key >= 97 && key <= 122) || key == 126 || (key == 130 || key == 144) 
		 || key == 162 || (key >= 164 && key <= 165) || (key == 181 || key == 191 
		 || key == 193 || key == 201 || key == 205 || key == 209 || key == 211 || key == 218 
		 || key == 220 || key == 225 || key == 233 || key == 237 || key == 241 
		 ||  key == 243 || key == 250 || key == 252 || key ==94 || key==95))) {
		 		 return false;
	} 
	else {
        return true;
    }
}

function validarTeclas(e){
	if(e.shiftKey || e.altKey || e.ctrlKey) {
		return false;
	}
	else{
		return true;
	}
}

function emergenteSeleccion(url,alto,ancho){

	LeftPosition = (screen.width)/2;
	TopPosition  = (screen.height)/3;
	configuracion = "toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=yes,height="+alto+",width="+ancho+",top="+TopPosition+",left="+LeftPosition;	
	window.open(url, "ventana", configuracion);	
}

function esValorNegativo(valor){
	if(parseInt(valor)<0){
		return true;
	}
	else{
		return false;
	}
}

function mostrarEleccion(){
	var id = document.getElementById("escogeYear");
	if(id.style.display=="inline"){
		id.style.display="none";
	}
	else{
		id.style.display="inline";
	}
}
/*
//Valida caracteres invalidos en campos de texto.
function validarCaracteresInv() {		 
		 var mensaje = "";
		 var caracteres = "";
		 
		 var formulario = document.forms[0];

		 for(var f = 0; f < formulario.length; f++) {
		 		 switch(formulario[f].type) {
		 		 		 case "text":
		 		 		 case "textarea":
		 		 		 case "password":
		 		 		 		 if (!formulario[f].readOnly && !formulario[f].disabled) {
		 		 		 		 		 var texto = formulario[f].value;
		 		 		 		 		 var swiche = false;
		 		 		 		 		 for(var i=0; i<texto.length; i++) {
		 		 		 		 		 		 var key = texto.charCodeAt(i);
		 
		 		 		 		 		 		 if(!((key >= 8 && key <= 10) || key == 13 || key == 32 || (key >= 35 && key <= 37) || (key >= 40 && key <= 46) || (key >= 48 && key <= 59) || key == 61 || key == 63 || (key >= 65 && key <= 93)
		 		 		 		 		 		 || (key >= 94 && key <= 123) || (key >= 125 && key <= 126) || (key == 130 || key == 144) || (key >= 160 && key <= 165)
		 		 		 		 		 		 || (key == 180 || key == 191 || key == 193 || key == 201 || key == 205 || key == 209 || key == 211 || key == 218 || key == 220 || key == 225 || key == 233 || key == 237 || key == 241 
		 		 		 		 		 		 ||  key == 243 || key == 250 || key == 252))) {
		 		 		 		 		 		 		 if(mensaje == "") {
		 		 		 		 		 		 		 		 mensaje = "Por favor, revise los siguientes campos ya que tienen caracteres inv�lidos.\n";
		 		 		 		 		 		 		 		 formulario[f].focus();
		 		 		 		 		 		 		 }
		 		 		 		 		 		 		 if(!swiche) {
		 		 		 		 		 		 		 		 swiche = true;
		 		 		 		 		 		 		 		 mensaje += "- " + formulario[f].nombreCampo + "\n";		 		 		 		 		 		 		 
		 		 		 		 		 		 		 }
		 		 		 		 		 		 		 caracteres += String.fromCharCode(key) + ", ";
		 		 		 		 		 		 }
		 		 		 		 		 }
		 		 		 		 }		 
		 		 		 		 break;
		 		 }
		 }
		 
		 if(mensaje.length > 0) {
		 		 mensaje += "Los caracteres inv�lidos son: \n" + caracteres.substring(0, caracteres.length-2);
		 }

		 return mensaje;		 
}
*/
//Funci�n para validar sql injection desde el copiar - pegar y html
/*
function validar(expresion){  		 
   	var texto = escape(expresion.value);		 
   	texto = Trim(texto);		    
      texto = texto.replace(/%0A/g, " ");
      texto = texto.replace(/%0D/g, " ");   
      texto = unescape(texto);
      if (texto.match(/^.*['\u0027;\u003B=\u003D\*\u002A<\u003C\u003E>].*$/))
      {  
		return false;
      }else{ 
       	return true;
      }        		  
} 	
*/

function mayusculas(campo){
	campo.value = campo.value.toUpperCase();
}

function existeSeleccion() {
	retorno = 0;
	var el = document.forms[0].elements;
	for(i=0;i<el.length;i++) {
		if(el[i].type == 'checkbox'){
			if(el[i].checked){
				retorno++;
			}
		}
	}
	return retorno;
}

// Valida que algun radio buttom este seleccionado.
function validaRadioButton(obj) {
	retorno =  false;
	for (i=0; i < obj.length; i++) {
		if (obj[i].checked) {
			retorno = true;
			i = obj.length + 1
		} else {
			retorno = false;
		}
	}
	return retorno;
}

function getValorSeleccionCheck() {
	retorno = "";
	var el = document.forms[0].elements;
	for(i=0;i<el.length;i++) {
		if(el[i].type == 'checkbox'){
			if(el[i].checked){
				retorno = el[i].value;
				break;
			}
		}
	}
	return retorno;
}

//Usado cuando existan varios grupos de checkbox en la misma pagina. 
//Parametro: Nombre del campo checkbox a validar
function existeSeleccionVariosCheckbox(campo) {
	retorno = 0;
	var el = document.forms[0].elements;
	for(i=0;i<el.length;i++) {
		if(el[i].type == 'checkbox'){
			if(el[i].id == campo){
				if(el[i].checked){
					retorno++;
				}
			}
		}
	}
	return retorno;
}

function esNumero(valor){
	if (valor != '') {
		if(!isNaN(valor)){
			if(valor.indexOf(".") != -1 || valor.indexOf(",") != -1){
				return false;
			}else{
				return true;
			}
		}else{
			return false;
		}
	}else{
		return false;
	}
}

function enter(e,funcion){
	var key = (navigator.appName == "Netscape") ? e.which : e.keyCode;
	if(key == 13){
		eval(funcion);
	}
}

function verificarNumero(numero){
	var reg = /^[-]?[0-9]+[\.]?[0-9]+$/;
	return reg.test(numero);
}

function validaEmail(correo){
	var filter=/^(\w+([-+.]\w+)*@\w+([-.]+\w+)*\.\w+([-.]\w+)*)$/;
	return filter.test(correo);
}

function getChar(e){
	var key = (navigator.appName == "Netscape") ? e.which : e.keyCode;
	var c = String.fromCharCode(key);
	return c;
}

function validaIngresoNumeros(e) {
	var c = getChar(e);
	//alert(c)
	if(verificarNumero(c)) {
		return true
	}else {
		return false;
	} 
}

function validaIngresoNum2(e) {
	var key = (navigator.appName == "Netscape") ? e.which : e.keyCode;
	if ((key > 57 | key < 48 ) &  key != 0 & key != 8 & key != 13) {
		return false;
	} else {
		return true;
	}
}

function validarFormularioGenerico(forma){
       var muestraMensaje= false;
        
        for(i=0;i<=forma.elements.length; i++){
               if(forma.elements[i]){
                      var tipo = forma.elements[i].type;
                       
                       if(tipo== 'text' ||tipo == 'textarea'|| tipo == 'password'){
                              var campo= forma.elements[i];
                              var valorCampo = campo.value;
                              
                              if(valorCampo!= ""){
                                     if(valorCampo.indexOf("<")!= -1 || valorCampo.indexOf(">")!= -1 || valorCampo.indexOf("&")!= -1
                                             || valorCampo.indexOf("\"")!= -1){
                                             campo.style.border="2pxsolid #F00";
                                             muestraMensaje = true;
                                      }else{
                                             try{
                                                    campo.style.border= null;
                                             }catch(ex){
                                                    //Sirevienta es porque es IE
                                                    campo.style.border= "1px  ";
                                             }
                                     }
                              }
                      }
                      
               }
       }
       
       if(muestraMensaje){
               alert("Existen car�cteres inv�lidos en el formulario.");
               return false;
        }
       else{
               return true;
        }
}

function no_imagen(esto){
	esto.src = "img/foto.jpg"
}  

function mostrarCargar(){

	jQuery.blockUI({ 
		message: '<h1><b>... PROCESANDO ...</b></h1>',
		css: { 
            border: 'none', 
            padding: '15px', 
            backgroundColor: '#fff', 
            '-webkit-border-radius': '10px', 
            '-moz-border-radius': '10px', 
            opacity: .8, 
            color: '#000' 
        } 
	});
	
	//setTimeout(jQuery.unblockUI, 7000); 
}

function ocultarCargar(){
	jQuery.unblockUI();
}

/* Paginacion */
function mostrarPaginas(nroRegistros, registrosXPagina){
	if (nroRegistros == 0 && registrosXPagina == 0)		
		return;	
		var paginas = buscarPaginas(nroRegistros, registrosXPagina);
		if (paginas != null && paginas.length > 0) {
			paginas[0].visible = "1"
			paginas[0].style.display="inline";
			if (document.getElementById('txtNroPagina')){
				document.getElementById('txtNroPagina').value = 1;
				document.getElementById('nroPaginas').innerHTML = 'Numero de Paginas: ' + paginas.length;
			}	
		}
		
}

function paginaAnterior(nroRegistros, registrosXPagina){
	var paginas = buscarPaginas(nroRegistros, registrosXPagina);
	for (var i = 0; i < paginas.length; i++){
		if (paginas[i].visible == "1") {
			if (i-1 < 0) {
				alert('Esta es la primera pagina');
				return;
			}
			paginas[i].visible = "0"
			paginas[i].style.display="none";
	    	paginas[i-1].visible = "1"
			paginas[i-1].style.display="inline";
			document.getElementById('txtNroPagina').value = i;
			break;
		}
	}
}

function paginaSiguiente(nroRegistros, registrosXPagina){
	var paginas = buscarPaginas(nroRegistros, registrosXPagina);
	for (var i = 0; i < paginas.length; i++){
		if (paginas[i].visible == "1") {
			if (i+1 >= paginas.length) {
				alert('Esta es la ultima pagina');
				return;
			}
			paginas[i].visible = "0"
			paginas[i].style.display="none";			
	    	paginas[i+1].visible = "1"
			paginas[i+1].style.display="inline";
			document.getElementById('txtNroPagina').value = i + 2;
			break;
		}
	}
}

function buscarPaginas(nroRegistros, registrosXPagina){
	var nroPaginas = 0;
	var paginas = new Array();
	for (var i = 1; i <= nroRegistros; i = i + registrosXPagina){
		if ( document.getElementById('pagina' + i ) != null ){
			paginas[nroPaginas] = document.getElementById('pagina' + i);
			nroPaginas++;
		}
	}
	return paginas;
}

function irPagina(text, e, nroRegistros, registrosXPagina){
	var key = (navigator.appName == "Netscape") ? e.which : e.keyCode;
	if (key == 13) {
		var nroPagina = text.value;
		var paginas = buscarPaginas(nroRegistros, registrosXPagina);
		if (nroPagina >0 && nroPagina <= paginas.length) {
			for (var i = 0; i < paginas.length; i++){
				if (paginas[i].visible == "1") {
					paginas[i].visible = "0"
					paginas[i].style.display="none";
			    	paginas[nroPagina - 1].visible = "1"
					paginas[nroPagina - 1].style.display="inline";
					break;
				}
			}
		}
		else
			alert('Este no corresponde a un numero de pagina');
	}
	
}
/**/

function validarIngresoTextoGeneral(campo){
	var texto = campo.value;
	var patron = /^[0-9|A-Z|a-z|#|.|,|&|\-|_| |�|�|?|!|%|=|+|$|@|(|)]*$/;
	if ( !texto.match(patron) ){
		alert('La informaci�n ingresada contiene caracteres inv�lidos');
		campo.value = "";
		campo.focus();
	}
}

function validarTextoGeneral(e) {
	var patron = /^[0-9|A-Z|a-z|#|.|,|&|\-|_| |�|�|?|!|%|=|+|$|@|(|)]*$/;
	var key = (navigator.appName == "Netscape") ? e.which : e.keyCode;
	if(key == 8 || key == 0){ // para poder borrar backspace
		return true;
	}
	else{
		var keychar = String.fromCharCode(key);
		//alert(key+" es: "+keychar);
		if ( !keychar.match(patron) ){
			return false;
		}
		else{
			return true;
		}
	}
}

function validarNumeroGeneral(e) {
	var patron = /^[0-9|.]*$/;
	var key = (navigator.appName == "Netscape") ? e.which : e.keyCode;
	if(key == 8 || key == 0){ // para poder borrar backspace
		return true;
	}
	else{
		var keychar = String.fromCharCode(key);
		//alert(key+" es: "+keychar);
		if ( !keychar.match(patron) ){
			return false;
		}
		else{
			return true;
		}
	}
}

function validarIngresoNumeros(campo){
	var texto = campo.value;
	var patron = /^[0-9|.]*$/;
	if ( !texto.match(patron) ){
		alert('La informaci�n ingresada contiene caracteres inv�lidos');
		campo.value = "";
		campo.focus();
	}
}

function definirTablaFiltros(tabla){
	// call the tablesorter plugin
	  jQuery(tabla).tablesorter({
	    theme: 'ice',

	    // hidden filter input/selects will resize the columns, so try to minimize the change
	    widthFixed : true,

	    // initialize zebra striping and filter widgets
	    widgets: ["zebra", "filter"],

	    // headers: { 5: { sorter: false, filter: false } },

	    widgetOptions : {

	      // If there are child rows in the table (rows with class name from "cssChildRow" option)
	      // and this option is true and a match is found anywhere in the child row, then it will make that row
	      // visible; default is false
	      filter_childRows : false,

	      // if true, a filter will be added to the top of each table column;
	      // disabled by using -> headers: { 1: { filter: false } } OR add class="filter-false"
	      // if you set this to false, make sure you perform a search using the second method below
	      filter_columnFilters : true,

	      // extra css class applied to the table row containing the filters & the inputs within that row
	      filter_cssFilter : '',

	      // class added to filtered rows (rows that are not showing); needed by pager plugin
	      filter_filteredRow   : 'filtered',

	      // add custom filter elements to the filter row
	      // see the filter formatter demos for more specifics
	      filter_formatter : null,

	      // add custom filter functions using this option
	      // see the filter widget custom demo for more specifics on how to use this option
	      filter_functions : null,

	      // if true, filters are collapsed initially, but can be revealed by hovering over the grey bar immediately
	      // below the header row. Additionally, tabbing through the document will open the filter row when an input gets focus
	      filter_hideFilters : true,

	      // Set this option to false to make the searches case sensitive
	      filter_ignoreCase : true,

	      // if true, search column content while the user types (with a delay)
	      filter_liveSearch : true,

	      // jQuery selector string of an element used to reset the filters
	      filter_reset : 'button.reset',

	      // Delay in milliseconds before the filter widget starts searching; This option prevents searching for
	      // every character while typing and should make searching large tables faster.
	      filter_searchDelay : 300,

	      // if true, server-side filtering should be performed because client-side filtering will be disabled, but
	      // the ui and events will still be used.
	      filter_serversideFiltering: false,

	      // Set this option to true to use the filter to find text from the start of the column
	      // So typing in "a" will find "albert" but not "frank", both have a's; default is false
	      filter_startsWith : false,

	      // Filter using parsed content for ALL columns
	      // be careful on using this on date columns as the date is parsed and stored as time in seconds
	      filter_useParsedData : false

	    }

	  });
}