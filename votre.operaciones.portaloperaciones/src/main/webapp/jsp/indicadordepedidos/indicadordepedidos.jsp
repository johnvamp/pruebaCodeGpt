<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>:: Portal Operaciones :: Leonisa S.A. ::</title>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" media="all" href="estilos/calendar-system.css" title="win2k-cold-1" />
<script type="text/javascript" src="js/ajax/prototype.js"></script>
<script type="text/javascript" src="js/ajax/effects.js"></script>


<script type="text/javascript"  src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript"  src="js/jquery.blockUI.js"></script>
<script type="text/javascript"  src="js/utilidades.js"></script>
<script type="text/javascript"  src="js/indicadordepedidos/indipedi.js"></script>

<script type="text/javascript" src="js/calendario/calendar.js"></script>
<script type="text/javascript" src="js/calendario/calendar-es.js"></script>
<script type="text/javascript" src="js/calendario/calendar-setup.js"></script>

</head>
<body onload="cambiarSeleccion(${idOpcSelect}); 
			  cargarCombosUbicacion('${idDepartamentoSelect}', '${idEstadoInicial}', '${idEstadoFinal}', '${campanaTBox}'); 
			  mostrarMensajeIndicador('${mensaje}');">
	<div id="url">
		<h2>Indicador de Pedidos</h2>
	</div>
	<form name="forma" method="post">
		<input type="hidden" id="idTipoFlujo"  name="idTipoFlujo"  value="" />
		<input type="hidden" id="idTipoTranspo"  name="idTipoTranspo" value=""/> 
		<input type="hidden" id="idRegionSelectB"  name="idRegionSelectB"  value="" />
		<table align="center">
			<tr>
				<td align="center">
				 	<div id="mensaje" align="center" class="mensajeUsuario" style="display:none;"></div>
				</td>
			</tr>
		</table>
		<table width="60%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%" align="center" colspan="2">
					<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
					        <td class="cajaGris01"><img src="imagenes/spacer.gif" alt="img" width="9" height="9" /></td>
					        <td class="bgcajaGris"></td>
					        <td class="cajaGris02"><img src="imagenes/spacer.gif" alt="img" width="9" height="9" /></td>
					    </tr>
					    <tr>
					        <td class="bgcajaGris">&nbsp;</td>
					        <td class="bgcajaGris">
					        	<table width="100%" height="81" border="0" cellpadding="0" cellspacing="0" align="center">
					        		<tr>
										<td colspan="2" width="100%" align="center" class="titulos">Indicador de Pedidos</td>
									</tr>
									<tr>
										<td height="10" colspan="2">
										</td>
									</tr>
									<tr>
							        	<td width="50%" class="txLabel">Tipo de Busqueda:</td>
							            <td width="50%">
								        	<select  id="idOpcSelect" name="idOpcSelect" onchange="cambiarSeleccion(this.value);">		
												<option value="0" ${'0' == idOpcSelect ? "selected='selected'" : ""}>Seleccionar</option>
												<option value="1" ${'1' == idOpcSelect ? "selected='selected'" : ""}>Fecha</option>
												<option value="2" ${'2' == idOpcSelect ? "selected='selected'" : ""}>Zona</option>
												<option value="3" ${'3' == idOpcSelect ? "selected='selected'" : ""}>Campaña</option>	
												<option value="4" ${'4' == idOpcSelect ? "selected='selected'" : ""}>Región</option>
												<option value="5" ${'5' == idOpcSelect ? "selected='selected'" : ""}>Tipo Compradora</option>
												<option value="6" ${'6' == idOpcSelect ? "selected='selected'" : ""}>Destino</option>
											</select>	
										</td>
							        </tr>
							        <tr>
										<td height="10" colspan="2">
										</td>
									</tr>
							        <tr>
							        	<td colspan="2" align="center">
							        		<div id="divEstados" style="display: none;">
							        			<table width="80%">
							        				<tr>
								            			<td align="right" width="25%">
								            				<label class="txLabel" id="tituloEstadoIncial">Estado Inicial:</label>
								            			</td>
								           				<td width="25%">
												   			<select id="idEstadoInicial" name="idEstadoInicial" class="textArea02" class="required" style="width: 90%">
												 				<option value="">Seleccione</option>
													       		<c:forEach items="${estados}" var="estadoIni">
													          		<option value="${estadoIni}" ${estadoIni == idEstadoInicial ? "selected='selected'" : ""}>${estadoIni}</option>
													          	</c:forEach>
												    		</select>
								            			</td>
									            		<td align="right" width="25%">
									                 		<label class="txLabel" id="tituloEstadoFinal">Estado Final:</label>
									            		</td>
											            <td width="25%">
														   <select id="idEstadoFinal" name="idEstadoFinal" class="textArea02" class="required">
												 				<option value="">Seleccione</option>
													       		<c:forEach items="${estados}" var="estadoFin">
													          		<option value="${estadoFin}" ${estadoFin == idEstadoFinal ? "selected='selected'" : ""}>${estadoFin}</option>
													       		</c:forEach>
														    </select>
											            </td>
											      	</tr>
							        			</table>
							        		</div>
							        		<div id="divUbicacion" style="display: none;">
							        			<table width="80%">
							        				<tr>
								                        <td align="right" width="25%">
								                        	<label class="txLabel" id="tituloDepartamentos">Departamentos:</label>   
								                        </td>
								                        <td width="25%">
														   	<select id="idDepartamentoSelect" name="idDepartamentoSelect" class="textArea02" class="required" style="width: 90%" onchange="buscarCiudad();">
													 			<option value="">Seleccione</option>
															    <c:forEach items="${departamentos}" var="departamento">
															    	<option value="${departamento.codigo}" ${departamento.codigo == idDepartamentoSelect ? "selected='selected'" : ""}>${departamento.nombre}</option>
														     	</c:forEach>
														    </select>
								                        </td>
														<td align="right" width="25%">
								                        	<label class="txLabel" id="tituloCiudad">Ciudad:</label>   
								                        </td>
								                        <td width="25%">
														   	<select id="idCiudadSelect" name="idCiudadSelect" class="textArea02" class="required" style="width: 80%" >
													 			<option value="">Seleccione</option>
															    <c:forEach items="${ciudades}" var="ciudad">
															    	<option value="${ciudad.codigo}" ${ciudad.codigo == idCiudadSelect ? "selected='selected'" : ""}>${ciudad.codigo}</option>
														     	</c:forEach>
														    </select>
								                        </td>
								                    </tr>
							        			</table>
							        		</div>
							        		<div id="divFecha" style="display: none;">
							        			<table width="80%">
							        				<tr>
								                        <td align="right" width="25%">
								                        	<label class="txLabel" id="tituloFechaInicio">Fecha Inicio:</label>
														</td>
								                        <td width="25%">
								                           	<input class="required" type="text" name="fechaInicialTBox" id="fechaInicialTBox" size="10"  readonly="readonly"/>
								                            <img src="imagenes/ico_calendar.gif" alt="Calendario" width="16" height="16" align="absmiddle" id="calendario1" style="cursor: pointer;" />
								                        </td>
								                        <td align="right" width="25%">
								                            <label class="txLabel" id="tituloFechaFinal">Fecha Final:</label>
								                        </td>
								                        <td width="25%">
								                            <input class="required" type="text" name="fechaFinalTBox" id="fechaFinalTBox" size="10"  readonly="readonly"/>
								                        	<img src="imagenes/ico_calendar.gif" alt="Calendario" width="16" height="16" align="absmiddle" id="calendario2" style="cursor:pointer;" />
								                        </td>
								                    </tr>
							        			</table>
							        		</div>
							        		<div id="divZona" style="display: none">
							        			<table width="80%">
							        				<tr>
								                        <td align="right" width="25%">
								                        	<label class="txLabel" id="tituloZona">Zona:</label>
								                        </td>
								                        <td width="25%">
								                        	<input class="required" type="text" name="zonaTBox" id="zonaTBox" size="5" maxlength="3" onkeypress="return validaIngresoNum(event);"/>
								                        </td>
								                        <td align="right" width="25%">
								                        	<label class="txLabel" id="tituloCampanaZ">Campaña:</label>
								                        </td>
								                        <td width="25%">
								                        	<input class="required" type="text" name="campanaTBoxZ" id="campanaTBoxZ" size="5" maxlength="4" onkeypress="return validaIngresoNum(event);"/>
								                        </td>
								                    </tr>
							        			</table>
							        		</div>
							        		<div id="divCampana" style="display: none;">
							        			<table width="80%">
							        				<tr>
								                        <td align="right" width="25%">
								                         	<label class="txLabel" id="tituloCampana">Campaña:</label>
								                        </td>
								                        <td>
								                        	<input class="required" type="text" name="campanaTBox" id="campanaTBox" size="5" maxlength="4" onkeypress="return validaIngresoNum(event);"/>
								                        </td>
								                    </tr>
							        			</table>
							        		</div>
							        		<div id="divRegion" style="display: none;">
							        			<table width="80%">
							        				<tr>
								                        <td align="right" width="25%">
								                        	<label class="txLabel" id="tituloRegion">Región:</label>   
								                        </td>
								                        <td width="25%">
														   	<select id="idRegionSelect" name="idRegionSelect" class="textArea02" class="required" style="width: 90%">
													 			<option value="">Seleccione</option>
															    <c:forEach items="${regiones}" var="region">
															    	<option value="${region}" ${region == idRegionSelect ? "selected='selected'" : ""}>${region}</option>
														     	</c:forEach>
														    </select>
								                        </td>
								                 		<td align="right" width="25%">
								                        	<label class="txLabel" id="tituloCampanaR">Campaña:</label>
								                        </td>
								                        <td width="25%">
								                        	<input class="required" type="text" name="campanaTBoxR" id="campanaTBoxR" size="5" maxlength="4" onkeypress="return validaIngresoNum(event);"/>
								                        </td>
								                    </tr>
							        			</table>
							        		</div>
							        		<div id="divCompradora" style="display: none;">
							        			<table width="80%">
							        				<tr>
								                        <td align="right" width="25%">
								                        	<label class="txLabel" id="tituloCompradora">Tipo Compradora:</label>   
								                        </td>
								                        <td width="25%">
														   	<select id="idCompradoraSelect" name="idCompradoraSelect" class="textArea02" class="required" style="width: 90%">
													 			<option value="">Seleccione</option>
															    <c:forEach items="${compradoras}" var="compradora">
															    	<option value="${compradora}" ${compradora == idCompradoraSelect ? "selected='selected'" : ""}>${compradora}</option>
														     	</c:forEach>
														    </select>
								                        </td>
								                 		<td align="right" width="25%">
								                        	<label class="txLabel" id="tituloCampanaC">Campaña:</label>
								                        </td>
								                        <td width="25%">
								                        	<input class="required" type="text" name="campanaTBoxC" id="campanaTBoxC" size="5" maxlength="4" onkeypress="return validaIngresoNum(event);"/>
								                        </td>
								                    </tr>
							        			</table>
							        		</div>
							        	</td>
							        </tr>
							        <tr>
										<td height="10" colspan="2">
										</td>
									</tr>
							        <tr>
										<td colspan="2" align="center"><a class="boton01" onclick="buscarIndicador();">PROCESAR</a></td>
									</tr>
								</table>
					        </td>
	    					<td class="bgcajaGris">&nbsp;</td>
	    				</tr>				
						<tr>
					        <td class="cajaGris03"></td>
					        <td class="bgcajaGris"></td>
					        <td class="cajaGris04"></td>
		     			</tr>      				
	    			</table>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td height="10">
	    		</td>
	    	</tr>				       
		</table>
	  	<table width="100%" border="0" align="center" cellpadding="3" cellspacing="0">
	  		<tr>
	    		<th><iframe id="pie2" name="pie" src="jsp/pie.html" scrolling="no" width="100%" height="40" frameborder="0"> Su navegador no reconoce iframes] </iframe></th>
	  		</tr>
		</table>
	</form>
<script type="text/javascript">
    Calendar.setup({
        inputField     :    "fechaInicialTBox",     // id of the input field
        ifFormat       :    "%Y-%m-%d",     // format of the input field (even if hidden, this format will be honored)
        displayArea    :    "show_d",       // ID of the span where the date is to be shown
        daFormat       :    "%A, %B %d, %Y",// format of the displayed date
        align          :    "Tl",           // alignment (defaults to "Bl")
        button         :    "calendario1",
        singleClick    :    true
    });
    
    Calendar.setup({
        inputField     :    "fechaFinalTBox",     // id of the input field
        ifFormat       :    "%Y-%m-%d",     // format of the input field (even if hidden, this format will be honored)
        displayArea    :    "show_d",       // ID of the span where the date is to be shown
        daFormat       :    "%A, %B %d, %Y",// format of the displayed date
        align          :    "Tl",           // alignment (defaults to "Bl")
        button         :    "calendario2",
        singleClick    :    true
    });
    
</script>
</body>
</html>