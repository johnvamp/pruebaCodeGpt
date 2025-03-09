package votre.portaloperaciones.util;

public class Constantes {
	
	public static final String ERROR_LOGIN = "Nombre de Usuario o Contrase�a no validos";
	public static final String USUARIO_SESION = "usuario";
	public static final String NOMBRE_USUARIO = "USUARIO";
	public static final String COD_CIA = "codCia";
	public static final String NOM_PAIS = "nomPais";
	public final static String OBJETO_BANDERA_EXPORTAR = "PUEDE_EXPORTAR";
	public final static String SEMILLA = "LeonisaSiEsMujer";
	
	public static final String ERROR_SP = "1";
	public static final String EXITO_SP = "0";
	public static final String NO_EXI_DATOS = "2";
	public static final String EXITO_EXCEL = "1";
	public static final String ERROR_EXCEL = "2";
	public static final String ERROR_ARCHIVO_EXCEL = "3";
	public static final String EXPORTAR_EXCEL_SI = "S";
	public static final String EXPORTAR_EXCEL_NO = "N";
	
	public static final int EXITO_MOD_SEG= 1;
	public static final String SELECCION_TODO = "T";
	public static final String SELECCION_REFERENCIA = "S";
	
	public static final String ACCION_VALIDAR = "I";
	public static final String ACCION_GUARDAR = "I";
	public static final String ACCION_MODIFICAR = "A";
	public static final String ACCION_GENERAR = "D";
	public static final String ACCION_GENERAR_LABEL = "L";
	public static final String ACCION_APROBAR = "A";
	public static final String ACCION_RECHAZAR = "R";

	public static final String ACCION_CONFIRMACION = "C";
	
	//Embarque
	public static final String ACCION_TRANSPORTADOR = "T";
	public static final String ACCION_CAMION = "C";
	public static final String ACCION_ENTREGA = "P";
	public static final String ACCION_VERCONSULTAR = "P";
	public static final String ACCION_CONSULTA = "C";
	public static final String ACCION_TRANSPORTADOR_REIMPRIMIR = "P";
	public static final String ACCION_CAMION_REIMPRIMIR = "C";
	public static final String ACCION_REIMPRIMIR = "R";
	public static final String ACCION_ABRIR_EMBARQUE = "C";
	public static final String ACCION_EMBARQUE = "E";
	public static final String ACCION_VERCERRAR = "P";
	public static final String ACCION_CERRAR = "C";
	public static final String ACCION_VERDESEMBARQUE = "P";
	public static final String ACCION_DESEMBARQUE = "D";
	public static final String ACCION_FINALIZAR = "F";
	public static final String ACCION_ENTREGATOTAL = "C";
	
	//Consulta SKU
	public static final String ACCION_TITULOS = "T";
	public static final String ACCION_PROCESAR = "P";
	public static final String MOSTRAR_SI = "S";
	public static final String MOSTRAR_NO = "N";
	
	//demandas
	public static final String ACCION_MOTIVOS_DEMANDA = "M";
	public static final String ACCION_AREAS_DEMANDA = "A";
	public static final String ACCION_SELECCION_DEMANDA = "S";
	public static final String ACCION_SELECCION_CRITERIOS = "C";
	public static final String ACCION_SELECCION_TIPOSDESPACHO = "D";
	public static final String ACCION_SELECCION_TRANSPORTADOR = "T";
	public static final String ACCION_SELECCION_TRANSPORTISTA = "N";
	public static final String ACCION_ENVIAR_MANUAL = "A";
	public static final String ACCION_MODIFICAR_ENVIO_MANUAL = "M";

	public static final String CRITERIO_APROBACION_DEMANDA = "P";
	public static final String CRITERIO_ENVIO_MANUAL = "D";
	public static final String CRITERIO_MODIFICACION_ENVIO_MANUAL = "E";
	
	//Suscripciones
	public static final String SUSCRIPCIONES_ACCION_CONSULTAR = "C";
	public static final String SUSCRIPCIONES_ACCION_INGRESAR = "I";
	public static final String SUSCRIPCIONES_ACCION_ACTUALIZAR = "A";
	public static final String SUSCRIPCIONES_ACCION_RETIRAR = "R";

	public static final String SUSCRIPCIONES_MEDIO = "Web";
	public static final String SUSCRIPCIONES_ESTADO_NUEVA = "N";
	public static final String SUSCRIPCIONES_ESTADO_VIGENTE = "R";

	public static final String CHILE = "200";
	public static final int REGISTROSXPAGINA= 13;
	public static final int REGISTROSXPAGINA_REFERENCIAS= 10;
	public static final int REGISTROSXPAGINA_ = 15;
	public static final int REGISTROSXPAGINA_DATOS_ERROR = 15;
	
	public static final String RUTA_ARCHIVOS_TMP = "/was/FileAppTemp/PortalCompradoras/";
	
	public static final String CODCIA_CHILE = "200";
	public static final String CODCIA_COLOMBIA = "001";
	public static final String CODCIA_COSTARICA = "100";
	public static final String CODCIA_ECUADOR = "500";
	public static final String CODCIA_ESPANA = "150";
	public static final String CODCIA_GUATEMALA = "450";
	public static final String CODCIA_MEXICO = "350";
	public static final String CODCIA_PERU = "400";
	public static final String CODCIA_PUERTORICO = "600";
	public static final String CODCIA_USA = "255";
	public static final String CODCIA_PANAMA = "650";
	
	public static final String CODINTERNACIONAL_CHILE = "56";
	public static final String CODINTERNACIONAL_COLOMBIA = "57";
	public static final String CODINTERNACIONAL_COSTARICA = "506";
	public static final String CODINTERNACIONAL_ECUADOR = "593";
	public static final String CODINTERNACIONAL_ESPANA = "34";
	public static final String CODINTERNACIONAL_GUATEMALA = "502";
	public static final String CODINTERNACIONAL_MEXICO = "52";
	public static final String CODINTERNACIONAL_PERU = "51";
	public static final String CODINTERNACIONAL_PUERTORICO = "17";
	public static final String CODINTERNACIONAL_USA = "1";
	
	public static final String COD_LABEL_ENVIO = "6";
	public static final String COD_LABEL_CORTE_CATALOGOS = "7";
	public static final String COD_LABEL_COMPRADORAS_ACTIVAS = "2";

	public static String getCodInternacional(String codCia){
		String retorno = CODINTERNACIONAL_COLOMBIA;
		if(CODCIA_CHILE.equals(codCia)){
			retorno = CODINTERNACIONAL_CHILE;
		} else if (CODCIA_COLOMBIA.equals(codCia)) {
			retorno = CODINTERNACIONAL_COLOMBIA;
		} else if (CODCIA_COSTARICA.equals(codCia)) {
			retorno = CODINTERNACIONAL_COSTARICA;
		} else if (CODCIA_ECUADOR.equals(codCia)) {
			retorno = CODINTERNACIONAL_ECUADOR;
		} else if (CODCIA_ESPANA.equals(codCia)) {
			retorno = CODINTERNACIONAL_ESPANA;
		} else if (CODCIA_GUATEMALA.equals(codCia)) {
			retorno = CODINTERNACIONAL_GUATEMALA;
		} else if (CODCIA_MEXICO.equals(codCia)) {
			retorno = CODINTERNACIONAL_MEXICO;
		} else if (CODCIA_PERU.equals(codCia)) {
			retorno = CODINTERNACIONAL_PERU;
		} else if (CODCIA_PUERTORICO.equals(codCia)) {
			retorno = CODINTERNACIONAL_PUERTORICO;
		} else if (CODCIA_USA.equals(codCia)) {
			retorno = CODINTERNACIONAL_USA;
		}
		
		return retorno;
	}
	
	public static boolean isLeocomus(String codCia) {
	  boolean retorno = false;
    if(Constantes.CODCIA_USA.equals(codCia) || Constantes.CODCIA_PUERTORICO.equals(codCia)
      || Constantes.CODCIA_MEXICO.equals(codCia) || Constantes.CODCIA_PANAMA.equals(codCia) || Constantes.CODCIA_ESPANA.equals(codCia)){
      retorno = true;
    }
	  return retorno;
	}
	
	//Activaci�n Demanda
	public static final String ACCION_REFERENCIAS = "R";
	public static final String ACCION_CAMBIAR_REFE = "B";
	public static final String ACCION_CEDULAS = "C";
	public static final String ACCION_GUIA_MASTER = "C";
	public static final String ACCION_IMPRIMIR_MASTER = "I";
	public static final String CODIGO_PEDIDO_ENVIADO = "1";
	public static final String CODIGO_PEDIDO_SUBIDO = "2";
	public static final String CODIGO_PEDIDO_LIBERADO = "3";
	public static final String CODIGO_PEDIDO_ASIGNADO = "4";
	public static final String CODIGO_PEDIDO_ENVIADO_WMS = "5";
	public static final String CODIGO_PEDIDO_CONSOLIDADO = "6";
	
	public static final String TIPO_DESPACHO_EXTRA = "E";
	public static final String TIPO_DESPACHO_BACK = "B";
	
	//Asignar Transportadores
	public static final String ACCION_FINALIZAR_PROCESO = "F";
	
	//Reprocesos
	public static final String ACCION_CONSULTAR_REFERENCIA = "R";
	public static final String ACCION_CONSULTAR_SOLICITUD = "S";
	public static final String ACCION_CONSULTAR_TRAMITE = "T";
	public static final int REGISTROSXPAGINA_SOLICITUD = 7;
	public static final int REGISTROSXPAGINA_REQUERIMIENTOS = 15;
	public static final String ACCION_APROBAR_SOLICITUD = "A";
	public static final String ACCION_MODIFICAR_SOLICITUD = "M";
	public static final String ACCION_RECHAZAR_SOLICITUD = "R";
	public static final String ACCION_CONSULTAR_INSUMOS = "A";
	public static final String ACCION_CONSULTAR_REPROCESO = "P";
	
	//Eliminar Envios
	public static final String TIPO_ELIMINAR_ENVIO = "M";
	public static final String TIPO_ELIMINAR_CEDULA = "I";	
	public static final String ACCION_CONSULTAR_ENVIO = "C";
	public static final String ACCION_COMFIRMAR_ENVIO = "A";
	
	public static final String CODIGO_COMBO = "520";
	
	//Embarque Catalagos
	public static final String ACCION_TRANSPORTADORAS = "T";
	
	//Indicador Pedidos
	public static final String ACCION_ESTADOS = "E";
	public static final String ACCION_REGIONES = "R";
	public static final String ACCION_COMPRADORAS = "C";
	
	//Pedidos CSL
	public static final String ACCION_TIPO_PEDIDO = "P";
	public static final String ACCION_ZONA = "Z";
	public static final String ACCION_COMANDO = "D";
	public static final String DESTINATARIO_REGION = "REGION";
	public static final String DESTINATARIO_LIDER = "LIDER";
	public static final String DESTINATARIO_COMANDO = "COMANDO";
	public static final String ACCION_PEDIDO_INDIVIDUAL = "I";
	public static final String ACCION_PEDIDO_EXCEL = "E";
}
