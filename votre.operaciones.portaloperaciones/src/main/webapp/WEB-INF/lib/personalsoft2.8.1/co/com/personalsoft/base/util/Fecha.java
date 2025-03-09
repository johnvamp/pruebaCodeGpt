package co.com.personalsoft.base.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;

public class Fecha {
   private static Logger logger = Logger.getLogger(Fecha.class);
   public static final String EXPR_DDMMYYYY = "ddMMyyyy";
   public static final String EXPR_DDMMYYYY_LINEA = "dd-MM-yyyy";
   public static final String EXPR_DDMMYYYY_SLASH = "dd/MM/yyyy";
   public static final String EXPR_DDMMYYYY_HHMMSS = "ddMMyyyy HH:mm:ss";
   public static final String EXPR_DDMMYYYY_HHMMSS_LINEA = "dd-MM-yyyy HH:mm:ss";
   public static final String EXPR_DDMMYYYY_HHMMSS_SLASH = "dd/MM/yyyy HH:mm:ss";
   public static final String EXPR_YYYYMMDD = "yyyyMMdd";
   public static final String EXPR_YYYYMMDD_LINEA = "yyyy-MM-dd";
   public static final String EXPR_YYYYMMDD_SLASH = "yyyy/MM/dd";
   public static final String EXPR_YYYYMMDD_HHMMSS = "yyyyMMdd HH:mm:ss";
   public static final String EXPR_YYYYMMDD_HHMMSS_LINEA = "yyyy-MM-dd HH:mm:ss";
   public static final String EXPR_YYYYMMDD_LINEA_HHMMSS_LINEA = "yyyy-MM-dd-HH-mm-ss";
   public static final String EXPR_YYYYMMDD_LINEA_HHMMSS_SIN_ESPACIO = "yyyyMMdd-HH-mm-ss";
   public static final String EXPR_YYYYMMDD_HHMMSS_SLASH = "yyyy/MM/dd HH:mm:ss";
   public static final String EXPR_YYYYMM_BLANK = "MMMM ''yyyy";
   public static final String EXPR_YYYYMMDD_BLANK = "MMMM dd 'de 'yyyy";
   public static final String EXPR_MMMM = "MMMM";
   public static final String EXPR_HHMMSS_DOS_PUNTOS = "HH:mm:ss";
   public static final String EXPR_HHMMSS_UN_PUNTO = "HH.mm.ss";
   public static final int TYPE_DATE = 1;
   public static final int TYPE_TIMESTAMP = 2;

   public static String getFechaServidor(String strFormato) {
      String strFecha = "";
      SimpleDateFormat simpleFormatDate = null;
      Date date = null;
      if (strFormato != null && esFechaValido(strFormato)) {
         simpleFormatDate = new SimpleDateFormat(strFormato);
         date = new Date();
         strFecha = simpleFormatDate.format(date);
         if ("MMMM ''yyyy".equals(strFormato)) {
            strFecha = strFecha.replaceAll("'", "");
         }
      }

      return strFecha;
   }

   public static boolean esFechaValido(String formato) {
      boolean isValid = false;
      if (formato.equals("ddMMyyyy")) {
         isValid = true;
      } else if (formato.equals("dd-MM-yyyy")) {
         isValid = true;
      } else if (formato.equals("dd/MM/yyyy")) {
         isValid = true;
      } else if (formato.equals("ddMMyyyy HH:mm:ss")) {
         isValid = true;
      } else if (formato.equals("dd-MM-yyyy HH:mm:ss")) {
         isValid = true;
      } else if (formato.equals("dd/MM/yyyy HH:mm:ss")) {
         isValid = true;
      } else if (formato.equals("yyyyMMdd")) {
         isValid = true;
      } else if (formato.equals("yyyy-MM-dd")) {
         isValid = true;
      } else if (formato.equals("yyyy/MM/dd")) {
         isValid = true;
      } else if (formato.equals("yyyyMMdd HH:mm:ss")) {
         isValid = true;
      } else if (formato.equals("yyyy-MM-dd HH:mm:ss")) {
         isValid = true;
      } else if (formato.equals("yyyy-MM-dd-HH-mm-ss")) {
         isValid = true;
      } else if (formato.equals("yyyy/MM/dd HH:mm:ss")) {
         isValid = true;
      } else if (formato.equals("MMMM ''yyyy")) {
         isValid = true;
      } else if (formato.equals("MMMM")) {
         isValid = true;
      } else if (formato.equals("MMMM dd 'de 'yyyy")) {
         isValid = true;
      } else if (formato.equals("HH:mm:ss")) {
         isValid = true;
      } else if (formato.equals("HH.mm.ss")) {
         isValid = true;
      } else if (formato.equals("yyyyMMdd-HH-mm-ss")) {
         isValid = true;
      } else {
         logger.error(" formato invalido ...." + formato);
      }

      return isValid;
   }

   public static boolean esPosterior(String strFecha, String strFechaReferencia, String strFormato) {
      boolean isValid = false;
      if (strFecha != null && strFechaReferencia != null && strFormato != null && esFechaValido(strFormato)) {
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormato);

         try {
            Date dateFecha = simpleDateFormat.parse(strFecha);
            Date dateFechaReferencia = simpleDateFormat.parse(strFechaReferencia);
            if (dateFecha.after(dateFechaReferencia)) {
               isValid = true;
            }
         } catch (ParseException var7) {
            logger.error(var7);
         }
      }

      return isValid;
   }

   public static String cambiarFormatoFecha(String strFecha, String strFormatoAnterior, String strFormatoNuevo) {
      if (!Validador.esNulo(strFecha) && !Validador.esNulo(strFormatoAnterior) && !Validador.esNulo(strFormatoNuevo) && esFechaValido(strFormatoAnterior) && esFechaValido(strFormatoNuevo)) {
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormatoAnterior);

         try {
            Date dateStrFecha = simpleDateFormat.parse(strFecha);
            simpleDateFormat.applyLocalizedPattern(strFormatoNuevo);
            strFecha = simpleDateFormat.format(dateStrFecha);
            if ("MMMM ''yyyy".equals(strFormatoNuevo)) {
               strFecha = strFecha.replaceAll("'", "");
            }
         } catch (ParseException var5) {
            strFecha = null;
            logger.error(var5);
         }
      }

      return strFecha;
   }

   public static String sumarCantidadFecha(String strFechaOriginal, String strFormato, int unidadTiempoAdicion, int cantidadTiempoAdicionar) {
      if (!Validador.esNulo(strFechaOriginal) && !Validador.esNulo(strFormato) && esFechaValido(strFormato)) {
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormato);
         Calendar calendar = GregorianCalendar.getInstance();

         try {
            Date dateStrFechaOriginal = simpleDateFormat.parse(strFechaOriginal);
            calendar.setTime(dateStrFechaOriginal);
         } catch (ParseException var7) {
            strFechaOriginal = null;
            logger.error(var7);
            return strFechaOriginal;
         }

         switch(unidadTiempoAdicion) {
         case 1:
            calendar.add(1, cantidadTiempoAdicionar);
            break;
         case 2:
            calendar.add(2, cantidadTiempoAdicionar);
            break;
         case 3:
            calendar.add(3, cantidadTiempoAdicionar);
            break;
         case 4:
            calendar.add(4, cantidadTiempoAdicionar);
            break;
         case 5:
            calendar.add(5, cantidadTiempoAdicionar);
            break;
         case 6:
            calendar.add(6, cantidadTiempoAdicionar);
            break;
         case 7:
            calendar.add(7, cantidadTiempoAdicionar);
            break;
         case 8:
            calendar.add(8, cantidadTiempoAdicionar);
         case 9:
         default:
            break;
         case 10:
            calendar.add(10, cantidadTiempoAdicionar);
            break;
         case 11:
            calendar.add(11, cantidadTiempoAdicionar);
            break;
         case 12:
            calendar.add(12, cantidadTiempoAdicionar);
            break;
         case 13:
            calendar.add(13, cantidadTiempoAdicionar);
            break;
         case 14:
            calendar.add(14, cantidadTiempoAdicionar);
         }

         strFechaOriginal = simpleDateFormat.format(calendar.getTime());
      }

      return strFechaOriginal;
   }

   public static String cambiarTipoDato(Date date, String strFormato) {
      String strDate = "";
      if (date != null && esFechaValido(strFormato)) {
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormato);
         strDate = simpleDateFormat.format(date);
      }

      return strDate;
   }

   public static String cambiarTipoDato(Timestamp date, String strFormato) {
      String strDate = "";
      if (date != null && esFechaValido(strFormato)) {
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormato);
         strDate = simpleDateFormat.format(date);
      }

      return strDate;
   }

   public static Date cambiarTipoDatoDate(String strFecha, String strFormato) {
      Date date = null;
      if (!Validador.esNulo(strFecha) && !Validador.esNulo(strFormato) && esFechaValido(strFormato)) {
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormato);

         try {
            date = simpleDateFormat.parse(strFecha);
         } catch (ParseException var5) {
            logger.error(var5);
         }
      }

      return date;
   }

   public static Timestamp cambiarTipoDatoTimestamp(String strFecha, String strFormato) {
      Timestamp timestamp = null;
      if (!Validador.esNulo(strFecha) && !Validador.esNulo(strFormato) && esFechaValido(strFormato)) {
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormato);

         try {
            Date date = simpleDateFormat.parse(strFecha);
            timestamp = new Timestamp(date.getTime());
         } catch (ParseException var5) {
            logger.error(var5);
         }
      }

      return timestamp;
   }

   public static boolean esFecha(String strFecha, String strFormato) {
      boolean isValid = false;
      if (esFechaValido(strFormato)) {
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormato);

         try {
            simpleDateFormat.parse(strFecha);
            isValid = true;
         } catch (ParseException var5) {
            logger.error(var5);
         }
      }

      return isValid;
   }

   public boolean isDate(String strFecha, String strSeparador, String strFormato) throws Exception {
      ArrayList vctDato = null;
      String strAno = "0";
      String strMes = "0";
      String strDia = "0";
      boolean var8 = false;

      try {
         vctDato = this.descomponerCadena(strFecha, strSeparador);
         if (vctDato.size() == 0) {
            return true;
         } else if (vctDato.size() > 0 && vctDato.size() != 3) {
            return false;
         } else {
            if (strFormato.toUpperCase().equals("YYYY/MM/DD")) {
               strAno = (String)vctDato.get(0);
               strMes = (String)vctDato.get(1);
               strDia = (String)vctDato.get(2);
            }

            if (strFormato.toUpperCase().equals("MM/DD/YYYY")) {
               strMes = (String)vctDato.get(0);
               strDia = (String)vctDato.get(1);
               strAno = (String)vctDato.get(2);
            }

            if (strFormato.toUpperCase().equals("DD/MM/YYYY")) {
               strDia = (String)vctDato.get(0);
               strMes = (String)vctDato.get(1);
               strAno = (String)vctDato.get(2);
            }

            byte iDiasFebrero;
            if (Integer.parseInt(strAno) % 4 != 0 && Integer.parseInt(strAno) % 100 != 0) {
               iDiasFebrero = 28;
            } else {
               iDiasFebrero = 29;
            }

            if (Integer.parseInt(strDia) <= 0) {
               return false;
            } else if (Integer.parseInt(strAno) > 1850 && Integer.parseInt(strAno) < 3000) {
               if (Integer.parseInt(strMes) > 0 && Integer.parseInt(strMes) <= 12) {
                  if (Integer.parseInt(strDia) > 31 && this.mesesdeTresUno(Integer.parseInt(strMes))) {
                     return false;
                  } else if (Integer.parseInt(strDia) > 30 && !this.mesesdeTresUno(Integer.parseInt(strMes))) {
                     return false;
                  } else {
                     return Integer.parseInt(strDia) <= iDiasFebrero || Integer.parseInt(strMes) != 2;
                  }
               } else {
                  return false;
               }
            } else {
               return false;
            }
         }
      } catch (Exception var10) {
         throw new Exception("vctDato() " + var10.toString());
      }
   }

   private boolean mesesdeTresUno(int iMes) throws Exception {
      boolean bSi = false;
      switch(iMes) {
      case 1:
         bSi = true;
      case 2:
      case 4:
      case 6:
      case 9:
      case 11:
      default:
         break;
      case 3:
         bSi = true;
         break;
      case 5:
         bSi = true;
         break;
      case 7:
         bSi = true;
         break;
      case 8:
         bSi = true;
         break;
      case 10:
         bSi = true;
         break;
      case 12:
         bSi = true;
      }

      return bSi;
   }

   public static int getDiaSemana(String fecha) throws Exception {
      Date fechaToDate = toDate(fecha, "dd/MM/yyyy");
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(fechaToDate);
      int dia = -1;
      switch(calendar.get(7)) {
      case 0:
         dia = 7;
         break;
      case 1:
         dia = 1;
         break;
      case 2:
         dia = 2;
         break;
      case 3:
         dia = 3;
         break;
      case 4:
         dia = 4;
         break;
      case 5:
         dia = 5;
         break;
      case 6:
         dia = 6;
      }

      return dia;
   }

   public ArrayList descomponerCadena(String strValor, String strSeparador) throws Exception {
      StringTokenizer stoToquen = new StringTokenizer(strValor + strSeparador, strSeparador);
      ArrayList<String> vecDatos = new ArrayList();
      boolean var5 = false;

      while(stoToquen.hasMoreTokens()) {
         String strDato = stoToquen.nextToken();
         int iIndx = vecDatos.size();
         vecDatos.add(iIndx, strDato);
      }

      return vecDatos;
   }

   public static Date toDate(String dateString, String dateFormatPattern) {
      SimpleDateFormat dateFormat = new SimpleDateFormat();
      Date date = null;
      dateFormat.applyPattern(dateFormatPattern);
      dateFormat.setLenient(true);

      try {
         date = dateFormat.parse(dateString);
      } catch (ParseException var5) {
         logger.error(var5);
      }

      return date;
   }
}
