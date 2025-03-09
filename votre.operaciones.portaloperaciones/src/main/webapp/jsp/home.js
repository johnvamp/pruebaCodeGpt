function Pedidos(){
	var forma = document.forms[0];
	forma.action = "pedidos.pedidosInicio.do";
	forma.target = "contenido";
	forma.submit();

}

function buscarPedidos(){
	var forma = document.forms[0];
	forma.action = "pedidos.buscarPedido.do";
	forma.target = "contenido";
	forma.submit();

}

