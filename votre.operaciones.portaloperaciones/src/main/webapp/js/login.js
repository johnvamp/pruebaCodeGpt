function load(){
	var forma = document.forms[0];
	forma.usu.focus();
}

function validar(forma){
	var mensaje = "";
	var compania = document.getElementById("cia").value;

	if(forma.usu.value == ""){
		mensaje += "Debe ingresar la información del campo Usuario.\n";
		forma.usu.focus();
	}
	
	if(forma.psw.value == ""){
		mensaje += "Debe ingresar la información del campo Contraseña.\n";
		forma.psw.focus();
	}
	/*
	if(compania == null || compania == "" || compania == "0"){
		mensaje +="Debe seleccionar la Compañia.\n";
		forma.psw.focus();
	}*/
	if(mensaje == ""){
		forma.action = "validar.do";
		document.getElementById('parametro').value = "logueo";
		return true;
	}
	else{
		alert(mensaje);
		return false;
	}
}

function borrar(){
	var forma = document.forms[0];
	forma.usuario.value = "";
	forma.password.value = "";
}

function cargarCompanias(usuario,idPeticion){
	usuario = usuario.value;
	if(usuario != null && usuario != ""){
		init("cargarCompania.do", idPeticion);
	    req[idPeticion].onreadystatechange =function() { getReqCompanias(idPeticion); };
 	    req[idPeticion].send('usuario='+usuario);
	}
}

function getReqCompanias(idPeticion){
	if (req[idPeticion].readyState == 4) {
		if (req[idPeticion].status == 200){
			var xmlCompanias = null;
			if(req[idPeticion].responseXML == null){
				var strCompanias = req[idPeticion].response;
				var xmlCompanias = StringToXMLDom(strCompanias);
				
				var compania = document.getElementById("cia");
				compania.options.length = 0;
				var item = xmlCompanias.getElementsByTagName("item");
				for (var i = 0; i < item.length; i++){
					var option = document.createElement('option');
					var nombre = xmlCompanias.getElementsByTagName("item")[i].childNodes[0].firstChild.nodeValue;
					var value = xmlCompanias.getElementsByTagName("item")[i].childNodes[1].firstChild.nodeValue;
					compania.options.add(option, i);
					compania.options[i].value = value+'-'+nombre;
					compania.options[i].text = nombre;
				}
			}
			else{
				var compania = document.getElementById("cia");
				compania.options.length = 0;
				var item = req[idPeticion].responseXML.getElementsByTagName("item");
				for (var i = 0; i < item.length; i++){
					var option = document.createElement('option');
					var nombre = req[idPeticion].responseXML.getElementsByTagName("item")[i].childNodes[0].firstChild.nodeValue;
					var value = req[idPeticion].responseXML.getElementsByTagName("item")[i].childNodes[1].firstChild.nodeValue;
					compania.options.add(option, i);
					compania.options[i].value = value+'-'+nombre;
					compania.options[i].text = nombre;
				}
			}
		}
	}
}

function StringToXMLDom(string){
	var xmlDoc=null;
	if (window.DOMParser)
	{
		parser=new DOMParser();
		xmlDoc=parser.parseFromString(string,"text/xml");
	}
	else // Internet Explorer
	{
		xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
		xmlDoc.async="false";
		xmlDoc.loadXML(string);
	}
	return xmlDoc;
}

function abrir(){
	var ancho = 500;
	var alto = 300;
	var posicion_x; 
	var posicion_y; 
	posicion_x=(screen.width/2)-(ancho/2); 
	posicion_y=(screen.height/2)-(alto/2); 
	window.open('jspCambiarClave.do', '', "width="+ancho+",height="+alto+",menubar=0,toolbar=0,directories=0,scrollbars=no,resizable=no,left="+posicion_x+",top="+posicion_y+"");
}