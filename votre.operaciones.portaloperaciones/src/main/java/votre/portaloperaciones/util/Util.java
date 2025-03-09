package votre.portaloperaciones.util;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import co.com.personalsoft.base.beans.Parametro;

public class Util {

	public static boolean isNumeroEntero(String numero) {
		if (numero == null)
			return false;
		if (numero.equals(""))
			return false;
		if (numero.length() > 10)
			return false;
		for (int i = 0; i < numero.length(); i++){
			if (numero.charAt(i) < '0' || numero.charAt(i) > '9')
				return false;
		}
		try {
			Integer.parseInt(numero);
		}
		catch (NumberFormatException e){
			return false;
		}
		
		return true;
	}
	
	public static boolean isXSS(String texto){
		if (texto.indexOf("\\") != -1) //&#92
			return true;
		if (texto.indexOf("/") != -1) //&#47
			return true;
		if (texto.indexOf("\"") != -1) //&#34
			return true;
		if (texto.indexOf("'") != -1) //&#39
			return true;
		if (texto.toLowerCase().indexOf("script") != -1)
			return true;
		if (texto.indexOf("(") != -1) //&#40 &#41
			return true;
		if (texto.indexOf("<") != -1) //&#60 &#62
			return true;
		if (texto.indexOf(";") != -1) //&#59
			return true;
		
		return false;
	}
	
	public static void imprimirCall(String nombreSP, ArrayList<Parametro> parametros){
		StringBuffer call = new StringBuffer();
		call.append("CALL "+nombreSP+"(");
		for(int i=0; i<parametros.size(); i++){
			call.append("'"+parametros.get(i).getValorParametro()+"'");
			if(i != parametros.size()-1){
				call.append(",");
			}
		}
		call.append(");");
		
		System.err.println(call.toString());
	}
	
	public static void imprimirCallInfo(String nombreSP, ArrayList<Parametro> parametros, Logger info){
		StringBuffer call = new StringBuffer();
		call.append("CALL "+nombreSP+"(");
		for(int i=0; i<parametros.size(); i++){
			call.append("'"+parametros.get(i).getValorParametro()+"'");
			if(i != parametros.size()-1){
				call.append(",");
			}
		}
		call.append(");");
		
		info.info(call.toString());
	}
	
	/**
	 * Verificar que una variable no venga vacia
	 * 
	* @author camiloserna
	* @since 27/04/2017
	 */
	public static String ifEmpty(String parametro) {
		if (parametro == null || parametro.length() == 0) {
			return "";
		} else {
			return parametro.toString();
		}
	}
	
}
