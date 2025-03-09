package co.com.personalsoft.seguridad.util;

import java.io.Serializable;

public class Util implements Serializable {
   private static final long serialVersionUID = -4112219277348482518L;
   public static final String ESPACIO = " ";
   public static final String CERO = "0";
   public static final String UNO = "1";
   public static final String TIPO_OPCION_WEB = "1";
   public static final String TIPO_OPCION_PERFIL = "2";
   public static final String TIPO_RECURSO_PERFIL = "3";
   public static final String TIPO_OPCION_1 = "1";
   public static final String TIPO_OPCION_2 = "2";
   public static final String TIPO_OPCION_3 = "3";
   public static final String TIPO_OPCION_4 = "4";
   public static final String TIPO_OPCION_5 = "5";
   public static final String TIPO_OPCION_6 = "6";
   public static final String TIPO_ORIGEN_WEB = "W";
   public static final String IGUAL = "=";
   public static final String UNDERSCORE = "_";
   public static final String PORCENTAJE = "%";
   public static final String ASTERISCO = "*";
   public static final String NUMERO = "#";
   public static final String S = "S";
   public static final String N = "N";
   public static final String EXISTE_REGISTRO = "002";
   public static final String NO_EXISTE_REGISTRO = "007";
   public static final String CLAVE_DUPLICADA = "009";
   public static final String PATRON_BUSQUEDA_MENSAJE = "E_";

   public static String configurarLogitudString(String str, int longitud) {
      if (str != null && str.length() > 0) {
         while(str.length() < longitud) {
            str = str + " ";
         }
      }

      return str;
   }

   public static String configurarLogitudNumerico(String str, int longitud) {
      if (str != null && str.length() > 0) {
         while(str.length() < longitud) {
            str = "0" + str;
         }
      }

      return str;
   }
}
