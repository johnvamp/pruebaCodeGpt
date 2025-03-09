<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>:: Portal Operaciones :: Leonisa S.A. ::</title>
<link href="estilos/estilos.css" rel="stylesheet" type="text/css" />
<!-- <script type="text/javascript" src="js/ajax/prototype.js"></script> -->
<!-- <script type="text/javascript" src="js/ajax/effects.js"></script> -->

<!-- <script type="text/javascript"  src="js/jquery.js"></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"  src="js/jqueryRotate.js"></script>
<!-- <script type="text/javascript"  src="js/jquery.collapse.js"></script> -->
<!-- <script type="text/javascript"  src="js/jquery.easing.1.3.js"></script> -->
<script type="text/javascript"  src="js/jquery.blockUI.js"></script>
<script type="text/javascript"  src="js/utilidades.js"></script>
<script type="text/javascript"  src="js/jquery.chromatable.js"></script>
<script type="text/javascript"  src="jsp/transportistas/transportistasPediPendi.js"></script>
<script type="text/javascript"  src="js/jquery.simplePagination.js"></script>

<script type="text/javascript">

		jQuery(function(){
			mostrarCargar();		
		    var perPage = 250;
			var opened = 1;
			var onClass = 'on';
			var paginationSelector = '.pages';
			jQuery('.gallery').simplePagination(perPage, opened, onClass, paginationSelector);       
		
		
		jQuery("#TableDatos2").chromatable({
				width: "100%",
				height: "200px",
				scrolling: "yes"				
				
			});	
			
		jQuery("#TableDatos1").chromatable({
				width: "100%",
				height: "160px",
				scrolling: "yes"				
				
			});
			
			
function validaIngresoNum(e,funcion) {
	var forma = document.forms[0];
	var val1 = document.getElementById("buscar").value;	
	var key = (navigator.appName == "Netscape") ? e.which : e.keyCode;
	if(e.altKey || e.ctrlKey) {
	return false;
	}	
	if(key == 13){
	txtkeypress();
	eval(funcion);
	return false;	
	}
	if ((key > 57 | key < 48 ) &  key != 0 & key != 8 & key != 13) {
	return false;
	} 
	else {
	     return true;
	    }
}
		ocultarCargar();
		});
</script>
</head>
<body onload="mostrarMensaje('${mensaje}');">
	<div id="url"><h2>Transportistas  / Pedidos Pendientes</h2></div>
	<form name="form" method="post">
		<input type="hidden" id="idTipoTranspo"  name="idTipoTranspo"/> 
		<input type="hidden" id="idTipoFlujo"  name="idTipoFlujo"/> 
		<input type="hidden" id="numeroOrden"  name="numeroOrden" />
		<input type="hidden" id="numeroGuia"  name="numeroGuia"  />
		<input type="hidden" id="buscarGuia"  name="buscarGuia"  />
		<input type="hidden" id="pEstBuscar"  name="pEstBuscar"  />
		<input type="hidden" id="idTipoExcel"  name="idTipoExcel"/> 
		<input type="hidden" id="buscarCampana"  name="buscarCampana"/> 
		<input type="hidden" id="cedulaBuscar"  name="cedulaBuscar"/> 
		<input type="hidden" id="tipoConsulta"  name="tipoConsulta"/> 
		<input type="hidden" id="transportistasComboGuar"  name="transportistasComboGuar"/> 
		<input type="hidden" id="observacionesGuar"  name="observacionesGuar"/> 
		<!-- Campos Editar -->
		<input type="hidden" id="numeroOrdenE" name="numeroOrdenE" value="" />
		<input type="hidden" id="numeroGuiaE" name="numeroGuiaE" value="" />
		<input type="hidden" id="estadoE" name="estadoE" value="" />
		<input type="hidden" id="observacionE" name="observacionE" value="" />
		<input type="hidden" id="zona" name="zona" value="${zona}" />
		
		<input type="hidden" id="vec_orden" name="vec_orden" value="" />
		<input type="hidden" id="vec_guia" name="vec_guia" value="" />
		<table align="center">
			<tr>
				<td align="center">
				 	<div id="mensaje" align="center" class="mensajeUsuario" style="display:none;"></div>
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td>
					<div><h2 class="tituloDatos">Transportista: ${NomTranspo}</h2></div>
				</td>
			</tr>
			<tr>
				<td class="txLabel">Seleccione La Campaña</td>
				<td>
					<select id="transportistasComboCampana" name="transportistasComboCampana">
						<option value="">Seleccione</option>
						<c:forEach items="${transportistasComboCampana}" var="cate">
							<option value="${cate}" ${cate == transportistasComboCampanaSelect ? "selected='selected'" : ""}>${cate}</option>	
						</c:forEach>
					</select>
				</td>
				<td>
					<input type="button" class="boton01" onclick="buscarTransportista('P', '')" value="Buscar" />
				</td>
			</tr>
		</table>
		<table width="100%" class="tabla7">
			<tr>
				<td valign="top">
					<table width="80%" >
						<tr>
							<th class="tituloTablaFijos">
								<p> Ingrese Número de Guía con Novedad</p>
							</th>
							<th class="tituloTablaFijos">
								<p> Ingrese Número de la Cedula</p>
							</th>
							<th class="tituloTablaFijos">
								<p>Ingrese Número de la Zona </p>
							</th>
						</tr>
						<tr>
							<td>
								<table width="100%" border="0">
									<tr>
										<td style="border: 0">
											<input class="required" maxlength="15"  type="text" name="buscar" id="buscar"  size="10" onkeypress="return validaIngresoNum(event);"/>
										</td>
										<td style="border: 0">
											<input type="button" class="boton01" onclick="buscarTransportista('P', 'G')" value="Buscar" />
										</td>
									</tr>
								</table>
							</td>
							<td>
								<table width="100%" border="0">
									<tr>
										<td style="border: 0">
											<input class="required" type="text" name="buscarCedula" id="buscarCedula"  size="10" onkeypress="return validaIngresoNum(event);"/>
										</td>
										<td style="border: 0">
											<input type="button" class="boton01" onclick="buscarTransportista('P', 'C')" value="Buscar" />
										</td>
									</tr>
								</table>
							</td>
							<td>
								<table width="100%" border="0">
									<tr>
										<td style="border: 0">
											<input  maxlength="3" class="required" type="text" name="buscarZona" id="buscarZona"  size="10" onkeypress="return validaIngresoNum(event);"/>
										</td>
										<td style="border: 0">
											<input type="button" class="boton01" onclick="buscarTransportista('P','')" value="Buscar" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
				<td>
		       		<table width="60%">
		       			<tr>
		       				<th class="tituloTablaFijos">  	
								<p>Ingreso de Listado de Guías Entregadas</p>
							</th>
		       			</tr>
		       			<tr>
		       				<td>
						       	<table>
						       		<tr align="center">
										<td style="border: 0">
											<textarea name="listGuia" rows="5" cols="20"></textarea>
										</td>
										<td width="7.5%" style="border: 0">
											<input type="button" class="boton01" onclick="guardarTransportistaListGuia();" value="Guardar" />
										</td>
										<c:choose>
											<c:when test="${ErrorLista == '1'}">
												<td>
													<table id="tablaError" width="100%" border="0" cellspacing="0" cellpadding="0">
											 		   	<thead>
												          <tr>      	          	
												           		<th class="tituloTablaFijos" width="5.5%">Error</th>
												          </tr>
											        	</thead>      
											       	   <c:forEach items="${ListGuiaError}" var="obtenida" varStatus="status" >
													     	<tr> 
														       	<td width="5.5%">&nbsp;${obtenida}</td>
												        	</tr>	
												       </c:forEach>
													</table>					
												</td>
											</c:when>
										</c:choose>						      
						       		</tr>		       	
						       	</table>		       	
		       				</td>
		       			</tr>
		       		</table>
				</td>
			</tr>
		</table>
	</form>
		<c:choose>
			<c:when test="${transportistasComboCampanaSelect != null}">
				<c:choose>
					<c:when test="${transportistasComboCampanaSelect != ''}">
						<c:choose>
							<c:when test="${NRegistrosPendi != '0'}">
								<c:choose>
									<c:when test="${NRegistrosPendi != ''}">
										<div>
											<h2 class="tituloDatos">Total Pedidos: ${TotalPedi}</h2>		
										</div>
									</c:when>
								</c:choose>
							</c:when>
						</c:choose>	
					</c:when>
				</c:choose>	
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${codigoZona != null}">
				<c:choose>
					<c:when test="${codigoZona != ''}">
						<div>
							<h2 class="tituloDatos">  Zona: ${codigoZona}</h2>		
						</div>
					</c:when>
				</c:choose>	
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${ErrorGuia == '1'}">
				<table id="tablaError" width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr> 
			       		<td class="tituloTablaFijos" width="5.5%">&nbsp;${MsgErrorGuia}</td>
		        	</tr>	
				</table>					
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${ErrorCedual == '1'}">
				<table id="tablaError" width="100%" border="0" cellspacing="0" cellpadding="0">
		     		<tr> 
			       		<td class="tituloTablaFijos" width="5.5%">&nbsp;${MsgErrorCedual}</td>
		        	</tr>	
				</table>					
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${Error == '1'}">						
				<table id="tablaError" width="100%" border="0" cellspacing="0" cellpadding="0">
		 		   	<thead>
			          <tr>      	          	
			           		<th class="tituloTablaFijos" width="5.5%">Error</th>
			          </tr>
		        	</thead>      
	
				     	<tr> 
					       	<td class="tituloTablaFijos" width="5.5%">&nbsp;${MsgError}</td>
			        	</tr>
		   		</table>					
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${NRegistrosPendi == '0'}">
				<c:choose>
					<c:when test="${mosTabla == '1'}">
						<div class="tabla7">
							<table id="TableDatos1" width="900" border="0" cellspacing="0" cellpadding="0">
						     	<thead>
						        	<tr>
					       	       		<th class="tituloTablaFijos" width="5.5%">Campaña</th>
						            	<th class="tituloTablaFijos" width="5.5%">N° Orden</th>
								     	<th class="tituloTablaFijos" width="5.5%">Guia</th>
										<th class="tituloTablaFijos" width="5.5%">Cédula</th>
										<th class="tituloTablaFijos" width="15.5%">Nombre</th>
										<th class="tituloTablaFijos" width="5.5%">F.Embarque</th>
										<th class="tituloTablaFijos" width="5.5%">H.Embarque</th>
										<th class="tituloTablaFijos" width="5.5%">1. Visita</th>
										<th class="tituloTablaFijos" width="5.5%">2. Visita</th>	            
						            	<th class="tituloTablaFijos" width="5.5%">3. Visita</th>
										<th class="tituloTablaFijos" width="7.5%">Observaciones</th> 
										<th class="tituloTablaFijos" width="7.5%">-</th>    
					         		</tr>
				        		</thead>         	
        						<tbody>			
		     						<c:forEach items="${transportistas}" var="obtenida" varStatus="fila" >
										<c:choose>
											<c:when test="${obtenida.numeroGuia == buscarGuia}">
										     	<tr>
											       	<td>${obtenida.numeroCampana}</td>
											       	<td>
											       		<input type="hidden" id="numeroOrden_${fila.count}" name="numeroOrden" value="${obtenida.numeroOrden}" />
											       		${obtenida.numeroOrden}
											       	</td>
											     	<td>
											     		<input type="hidden" id="numeroGuia_${fila.count}" name="numeroGuia" value="${obtenida.numeroGuia}" />
											     		${obtenida.numeroGuia}
											     	</td>
													<td>${obtenida.cedula}</td>
													<td>${obtenida.nombre}</td>
													<td>${obtenida.fechaEmbarque}</td>
													<td>${obtenida.horaEmbarque}</td>
													<c:choose>
														<c:when test="${obtenida.primeraVisita == ''}">
															<td>
															   <select id="transportistasCombo_${fila.count}" name="transportistasCombo"  onchange="javascript:idTransportistasCombo()">
														 			<option value="">Seleccione</option>
															       	<c:forEach items="${transportistasCombo1y2}" var="cate">
													          			<option value="${cate.key}" ${cate.value == transportistasCombo ? "selected='selected'" : ""}>${cate.value}</option>	
														       		</c:forEach>
															    </select>
															</td>
															<td>-</td>
															<td>-</td>
														</c:when>
														<c:otherwise>
															<td>${obtenida.primeraVisita}</td>
																<c:choose>
																	<c:when test="${obtenida.segundaVisita == ''}">
																		<td>
																		   <select id="transportistasCombo_${fila.count}" name="transportistasCombo" class="textArea02"   class="required">
															 					<option value="">Seleccione</option>
																		       		<c:forEach items="${transportistasCombo1y2}" var="cate">
																		          		<option value="${cate.key}" ${cate.value == transportistasCombo2 ? "selected='selected'" : ""}>${cate.value}</option>	
																		       	 </c:forEach>
																		    </select>
																		</td>
																		<td>-</td>
																	</c:when>
																<c:otherwise>
																	<td>${obtenida.segundaVisita}</td>
																	<c:choose>
																		<c:when test="${obtenida.terceraVisita == ''}">
																			<td>
																				<select id="transportistasCombo_${fila.count}" name="transportistasCombo" class="textArea02"   class="required">
																	 				<option value="">Seleccione</option>
																	       			<c:forEach items="${transportistasCombo3}" var="cate">
																		          		<option value="${cate.key}" ${cate.value == transportistasCombo3 ? "selected='selected'" : ""}>${cate.value}</option>	
																	       	 		</c:forEach>
																			    </select>
																			</td>
																		</c:when>
																		<c:otherwise>
																			<td>${obtenida.terceraVisita}</td>
																		</c:otherwise>
																	</c:choose>						
																</c:otherwise>
															</c:choose>
														</c:otherwise>
													</c:choose>
													<td align="center"> 
														<input maxlength="58" class="required" type="text" id="observaciones_${fila.count}" name="observaciones" />
													</td>
													<td width="7.5%">
														<a class="boton01" href="javascript:guardarTransportista('${fila.count}')">Guardar</a>
													</td>		
		 										</tr> 
											</c:when>
										</c:choose>
									</c:forEach> 	
       							</tbody>
							</table> 
						</div>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${mosTabla == '2'}">
						<div class="tabla7">
							<table id="TableDatos1" width="900" border="0" cellspacing="0" cellpadding="0">
						     	<thead>
						        	<tr>      	          	
					       	    		<th class="tituloTablaFijos" width="5.5%">Campaña</th>
						            	<th class="tituloTablaFijos" width="5.5%">N° Orden</th>
								     	<th class="tituloTablaFijos" width="5.5%">Guia</th>
										<th class="tituloTablaFijos" width="5.5%">Cédula</th>
										<th class="tituloTablaFijos" width="15.5%">Nombre</th>
										<th class="tituloTablaFijos" width="5.5%">F.Embarque</th>
										<th class="tituloTablaFijos" width="5.5%">H.Embarque</th>
										<th class="tituloTablaFijos" width="5.5%">1. Visita</th>
										<th class="tituloTablaFijos" width="5.5%">2. Visita</th>	            
						            	<th class="tituloTablaFijos" width="5.5%">3. Visita</th>
										<th class="tituloTablaFijos" width="7.5%">Observaciones</th>  
										<th class="tituloTablaFijos" width="7.5%">-</th>   
					          		</tr>
					        	</thead>         	
        						<tbody>
	      							<c:forEach items="${transportistas}" var="obtenida" varStatus="fila" >
									    <c:choose>
											<c:when test="${obtenida.cedula == cedulaBuscar}">	     
												<tr>
											       	<td>${obtenida.numeroCampana}</td>
											       	<td>
											       		<input type="hidden" id="numeroOrden_${fila.count}" name="numeroOrden" value="${obtenida.numeroOrden}" />
											       		${obtenida.numeroOrden}
											       	</td>
											     	<td>
											     		<input type="hidden" id="numeroGuia_${fila.count}" name="numeroGuia" value="${obtenida.numeroGuia}" />
											     		${obtenida.numeroGuia}
											     	</td>
													<td>${obtenida.cedula}</td>
													<td>${obtenida.nombre}</td>
													<td>${obtenida.fechaEmbarque}</td>
													<td>${obtenida.horaEmbarque}</td>
													<c:choose>
														<c:when test="${obtenida.primeraVisita == ''}">
															<td>
														   		<select id="transportistasCombo_${fila.count}" name="transportistasCombo"  onchange="javascript:idTransportistasCombo()">
													 				<option value="">Seleccione</option>
												       				<c:forEach items="${transportistasCombo1y2}" var="cate">
														          		<option value="${cate.key}" ${cate.value == transportistasCombo ? "selected='selected'" : ""}>${cate.value}</option>	
														       	 	</c:forEach>
														    	</select>
															</td>
															<td>-</td>
															<td>-</td>
														</c:when>
														<c:otherwise>
															<td>${obtenida.primeraVisita}</td>	
															<c:choose>
																<c:when test="${obtenida.segundaVisita == ''}">
																	<td>
																   		<select id="transportistasCombo_${fila.count}" name="transportistasCombo" class="textArea02"   class="required">
																 			<option value="">Seleccione</option>
																       		<c:forEach items="${transportistasCombo1y2}" var="cate">
																          		<option value="${cate.key}" ${cate.value == transportistasCombo2 ? "selected='selected'" : ""}>${cate.value}</option>	
																	       	 </c:forEach>
																    	</select>
																	</td>
																		<td>-</td>
																</c:when>
																<c:otherwise>
																	<td>${obtenida.segundaVisita}</td>
																		<c:choose>
																			<c:when test="${obtenida.terceraVisita == ''}">
																				<td>
																		   			<select id="transportistasCombo_${fila.count}" name="transportistasCombo" class="textArea02"   class="required">
																		 				<option value="">Seleccione</option>
																			       		<c:forEach items="${transportistasCombo3}" var="cate">
																			          		<option value="${cate.key}" ${cate.value == transportistasCombo3 ? "selected='selected'" : ""}>${cate.value}</option>	
																				       	 </c:forEach>
																				    </select>
																				</td>
																			</c:when>
																			<c:otherwise>
																				<td>${obtenida.terceraVisita}</td>
																			</c:otherwise>
																		</c:choose>						
																</c:otherwise>
															</c:choose>
														</c:otherwise>
													</c:choose>
													<td align="center"> 
														<input maxlength="58" class="required" type="text" id="observaciones_${fila.count}" name="observaciones" />
													</td>
													<td width="7.5%">
														<a class="boton01" href="javascript:guardarTransportista('${fila.count}')">Guardar</a>
													</td>		
	 											</tr>
											</c:when>
										</c:choose>		
									</c:forEach>       	
       							</tbody>   	    	
							</table> 
						</div>
					</c:when>
				</c:choose>  
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${TotalPedi != '0'}">
				<c:choose>
					<c:when test="${TotalPedi != ''}">
						<c:choose>
							<c:when test="${NRegistrosPendi != '0'}">
								<c:choose>
									<c:when test="${NRegistrosPendi != ''}">
										<div class="tabla7">
											<table id="TableDatos2" width="900" border="0" cellspacing="0" cellpadding="0">
								 				<thead>
									          		<tr>
									          			<th class="tituloTablaFijos" width="2%">Entregado</th>  
									           			<th class="tituloTablaFijos" width="5.5%">Campaña</th>
										            	<th class="tituloTablaFijos" width="5.5%">N° Orden</th>
												     	<th class="tituloTablaFijos" width="5.5%">Guia</th>
														<th class="tituloTablaFijos" width="5.5%">Cédula</th>
														<th class="tituloTablaFijos" width="15.5%">Nombre</th>
														<th class="tituloTablaFijos" width="5.5%">F.Embarque</th>
														<th class="tituloTablaFijos" width="5.5%">H.Embarque</th>
														<th class="tituloTablaFijos" width="5.5%">1. Visita</th>
														<th class="tituloTablaFijos" width="5.5%">2. Visita</th>	            
										            	<th class="tituloTablaFijos" width="5.5%">3. Visita</th>
														<th class="tituloTablaFijos" width="7.5%">Observaciones</th>   
													</tr>
												</thead>
												<c:forEach items="${transportistas}" var="obtenida" varStatus="status" >
													<tr> 
												       	<td>
												       		<input type="checkbox" id="entregado_${status.count}" name="entregado" value="${status.count}" />
												       	</td>
												       	<td width="5.5%">${obtenida.numeroCampana}</td>
												       	<td width="5.5%">
												       		<input type="hidden" id="nroOrden_${status.count}" name="nroOrden_${status.count}" value="${obtenida.numeroOrden}" />
												       		${obtenida.numeroOrden}
												       	</td>
												     	<td width="5.5%">
												     		<input type="hidden" id="nroGuia_${status.count}" name="nroGuia_${status.count}" value="${obtenida.numeroGuia}" />
												     		${obtenida.numeroGuia}
												     	</td>
														<td width="5.5%">${obtenida.cedula}</td>
														<td width="15.5%">${obtenida.nombre}</td>
														<td width="5.5%">${obtenida.fechaEmbarque}</td>
														<td width="5.5%">${obtenida.horaEmbarque}</td>
														<c:choose>
															<c:when test="${obtenida.primeraVisita == ''}">
																<td>-</td>
															</c:when>
															<c:otherwise>
																<td width="5.5%">${obtenida.primeraVisita}</td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${obtenida.segundaVisita == ''}">
																<td>-</td>
															</c:when>
															<c:otherwise>
																<td width="5.5%">${obtenida.segundaVisita}</td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${obtenida.terceraVisita == ''}">
																	<td>-</td>
															</c:when>
															<c:otherwise>
																<td width="5.5%">${obtenida.terceraVisita}</td>
															</c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${obtenida.observaciones == ''}">
																	<td>-</td>
															</c:when>
															<c:otherwise>
															<td width="5.5%">${obtenida.observaciones}</td>
															</c:otherwise>
														</c:choose>
	       											</tr>
	       										</c:forEach>
											</table>
										</div>
										<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
											<tr>
												<td height="10"></td>
											</tr>
											<tr>
												<td align="center">
													<input type="button" class="boton01" onclick="guardarEntregados('${TotalPedi}')" value="Guardar Entregados" />
													<input type="button" class="boton01" onclick="generarExcel('P');" value="Exportar a Excel" />
												</td>
											</tr>
										</table>
									</c:when>
								</c:choose>
							</c:when>
						</c:choose>
					</c:when>
				</c:choose>
			</c:when>
		</c:choose>
		<c:if test="${nroMensajes > 0}">
			<table width="100%" border="0" cellspacing="1" cellpadding="2">
				<tr>
					<td height="15"></td>
				</tr>	
	       		<tr>
	       			<td class="titulos" align="center" width="40%">ESTADOS ENTREGAODS</td>
	       		</tr>
	       		<tr>
	       			<td align="center">
						<div class="${nroMensajes > 5 ? 'scrollReferencias' : ''}">
							<table width="50%" border="0" cellspacing="0" cellpadding="0">
				          		<tr class="tituloTabla">
				          			<td>N° Orden</td>
				          			<td>N° Guia</td>
				          			<td>Estado</td>
				          		</tr>
					          	<c:forEach items="${arrMensajes}" var="mensaje" varStatus="fila">
					          		<tr class="filaTabla" style="border:1"> 
							       		<td>${mensaje.numeroOrden}</td>
							       		<td>${mensaje.numeroGuia}</td>
							       		<td>${mensaje.msgError}</td>
							       	</tr>
					          	</c:forEach>
				          	</table>
			         	</div>
			         </td>
	        	</tr>
	        </table>
		</c:if>	
		
	<table width="100%" border="0" align="center" cellpadding="3" cellspacing="0" onmouseover="">
		<tr>
   			<th><iframe id="pie2" name="pie" src="jsp/pie.html" scrolling="no" width="100%" height="40" frameborder="0"> Su navegador no reconoce iframes] </iframe></th>
  		</tr>
	</table>
</body>
</html>


