package co.com.personalsoft.base.util;

import co.com.personalsoft.base.beans.Campo;
import co.com.personalsoft.base.beans.Item;
import co.com.personalsoft.base.beans.Mensaje;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {
   public static final int NUMERO = 1;
   public static final int ENTERO = 2;
   public static final int ENTERO_POSITIVO = 3;
   public static final int ENTERO_NEGATIVO = 4;
   public static final int DOUBLE_POSITIVO = 5;
   public static final int DOUBLE_NEGATIVO = 6;
   public static final int EMAIL = 7;
   public static final int FECHA = 8;
   public static final int STRING = 9;
   public static final String EXPRESSION_EMAIL = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
   public static final String EXPRESSION_ENTERO = "^[-+]?[0-9]+$";
   public static final String EXPRESSION_ENTERO_NEGATIVO = "^[-]?[0-9]+$";
   public static final String EXPRESSION_ENTERO_POSITIVO = "^[+]?[0-9]+$";
   public static final String EXPRESSION_NUMBER = "^[-+]?[0-9]*\\.?[0-9]+$";
   public static final String EXPRESSION_NUMBER_POSITIVE = "^[+]?[0-9]*\\.?[0-9]+$";
   public static final String EXPRESSION_NUMBER_NEGATIVE = "^[-]?[0-9]*\\.?[0-9]+$";
   public static final String EXPRESSION_CHARACTERS_SPECIAL = "^([\\s*\\w\\á\\Á\\é\\É\\í\\Í\\ó\\Ó\\ú\\Ú\\s*]+)?$";
   public static final String EXPRESSION_CHARACTERS_SPECIAL_CODE = "^([\\w\\á\\Á\\é\\É\\í\\Í\\ó\\Ó\\ú\\Ú]+)?$";
   public static final String EXPRESSION_CHARACTERS_POINT = "^([\\w\\á\\Á\\é\\É\\í\\Í\\ó\\Ó\\ú\\Ú\\.]+)?$";

   public static String validarFormulario(Collection<Campo> valores) {
      StringBuffer strMessage = new StringBuffer();
      Campo campo = null;
      String valor = null;
      ArrayList datos = null;
      if (valores != null && valores.size() > 0) {
         datos = new ArrayList(valores);

         for(int i = 0; i < datos.size(); ++i) {
            campo = (Campo)datos.get(i);
            if (campo != null) {
               valor = campo.getNombre() != null && campo.getNombre().trim().length() > 0 ? campo.getNombre().trim() : "";
               if (campo.isUppperCase() && !esNulo(valor)) {
                  valor = valor.toUpperCase();
               }

               switch(campo.getType()) {
               case 1:
                  if (campo.isObligatorio()) {
                     if (esVacio(valor)) {
                        strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("valorVacio"), new Item("PARAM", campo.getNombreUsuario())));
                        strMessage.append("<br>");
                     } else if (!esNumero(valor)) {
                        strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("valorIngNoEsNum"), new Item("PARAM", campo.getNombreUsuario())));
                        strMessage.append("<br>");
                     }
                  } else if (esLength(valor) && !esNumero(valor)) {
                     strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("ingValorNum"), new Item("PARAM", campo.getNombreUsuario())));
                     strMessage.append("<br>");
                  }
                  break;
               case 2:
                  if (campo.isObligatorio()) {
                     if (esEntero(valor)) {
                        strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("valorVacio"), new Item("PARAM", campo.getNombreUsuario())));
                        strMessage.append("<br>");
                     } else if (!esEntero(valor)) {
                        strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("valorIngNoEsEnt"), new Item("PARAM", campo.getNombreUsuario())));
                        strMessage.append("<br>");
                     }
                  } else if (esLength(valor) && !esEntero(valor)) {
                     strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("ingValorEnt"), new Item("PARAM", campo.getNombreUsuario())));
                     strMessage.append("<br>");
                  }
                  break;
               case 3:
                  if (campo.isObligatorio()) {
                     if (esVacio(valor)) {
                        strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("valorVacio"), new Item("PARAM", campo.getNombreUsuario())));
                        strMessage.append("<br>");
                     } else if (!esEnteroPositivo(valor)) {
                        strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("valorIngNoEsEntPos"), new Item("PARAM", campo.getNombreUsuario())));
                        strMessage.append("<br>");
                     }
                  } else if (esLength(valor) && !esEnteroPositivo(valor)) {
                     strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("ingValorEntPos"), new Item("PARAM", campo.getNombreUsuario())));
                     strMessage.append("<br>");
                  }
                  break;
               case 4:
                  if (campo.isObligatorio()) {
                     if (esVacio(valor)) {
                        strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("valorVacio"), new Item("PARAM", campo.getNombreUsuario())));
                        strMessage.append("<br>");
                     } else if (!esEnteroNegativo(valor)) {
                        strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("valorIngNoEsEntNeg"), new Item("PARAM", campo.getNombreUsuario())));
                        strMessage.append("<br>");
                     }
                  } else if (esLength(valor) && !esEnteroNegativo(valor)) {
                     strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("ingValorEntNeg"), new Item("PARAM", campo.getNombreUsuario())));
                     strMessage.append("<br>");
                  }
                  break;
               case 5:
                  if (campo.isObligatorio()) {
                     if (esVacio(valor)) {
                        strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("valorVacio"), new Item("PARAM", campo.getNombreUsuario())));
                        strMessage.append("<br>");
                     } else if (!esDoublePositivo(valor)) {
                        strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("valorIngNoEsNumPos"), new Item("PARAM", campo.getNombreUsuario())));
                        strMessage.append("<br>");
                     }
                  } else if (esLength(valor) && !esDoublePositivo(valor)) {
                     strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("ingValorNumPos"), new Item("PARAM", campo.getNombreUsuario())));
                     strMessage.append("<br>");
                  }
                  break;
               case 6:
                  if (campo.isObligatorio()) {
                     if (esVacio(valor)) {
                        strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("valorVacio"), new Item("PARAM", campo.getNombreUsuario())));
                        strMessage.append("<br>");
                     } else if (!esDoubleNegativo(valor)) {
                        strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("valorIngNoEsNumNeg"), new Item("PARAM", campo.getNombreUsuario())));
                        strMessage.append("<br>");
                     }
                  } else if (esLength(valor) && !esDoubleNegativo(valor)) {
                     strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("ingValorNumNeg"), new Item("PARAM", campo.getNombreUsuario())));
                     strMessage.append("<br>");
                  }
                  break;
               case 7:
                  if (campo.isObligatorio()) {
                     if (esVacio(valor)) {
                        strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("valorVacio"), new Item("PARAM", campo.getNombreUsuario())));
                        strMessage.append("<br>");
                     } else if (!esEmail(valor)) {
                        strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("valorIngNoEsEmail"), new Item("PARAM", campo.getNombreUsuario())));
                        strMessage.append("<br>");
                     }
                  } else if (esLength(valor) && !esEmail(valor)) {
                     strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("ingValorEmail"), new Item("PARAM", campo.getNombreUsuario())));
                     strMessage.append("<br>");
                  }
                  break;
               case 8:
                  if (campo.isObligatorio()) {
                     if (esVacio(valor)) {
                        strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("valorVacio"), new Item("PARAM", campo.getNombreUsuario())));
                        strMessage.append("<br>");
                     } else if (!Fecha.esFecha(valor, "ddMMyyyy")) {
                        strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("valorIngNoEsFecha"), new Item("PARAM", campo.getNombreUsuario())));
                        strMessage.append("<br>");
                     }
                  } else if (esLength(valor) && Fecha.esFecha(valor, "ddMMyyyy")) {
                     strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("ingValorFecha"), new Item("PARAM", campo.getNombreUsuario())));
                     strMessage.append("<br>");
                  }
                  break;
               default:
                  if (campo.isObligatorio() && (esNulo(valor) || esVacio(valor))) {
                     strMessage.append(CargadorMsj.getInstance().getMensaje(new Mensaje("valorObligatorio"), new Item("PARAM", campo.getNombreUsuario())));
                     strMessage.append("<br>");
                  }
               }
            }
         }
      }

      return strMessage.toString();
   }

   public static boolean esNumero(String valor) {
      boolean isValid = false;
      Pattern pattern = Pattern.compile("^[-+]?[0-9]*\\.?[0-9]+$");
      Matcher matcher = pattern.matcher(valor);
      if (matcher.matches()) {
         isValid = true;
      }

      return isValid;
   }

   public static boolean esEntero(String valor) {
      boolean isValid = false;
      Pattern pattern = Pattern.compile("^[-+]?[0-9]+$");
      Matcher matcher = pattern.matcher(valor);
      if (matcher.matches()) {
         isValid = true;
      }

      return isValid;
   }

   public static boolean esEnteroPositivo(String valor) {
      boolean isValid = false;
      Pattern pattern = Pattern.compile("^[+]?[0-9]+$");
      Matcher matcher = pattern.matcher(valor);
      if (matcher.matches()) {
         isValid = true;
      }

      return isValid;
   }

   public static boolean esEnteroNegativo(String valor) {
      boolean isValid = false;
      Pattern pattern = Pattern.compile("^[-]?[0-9]+$");
      Matcher matcher = pattern.matcher(valor);
      if (matcher.matches()) {
         isValid = true;
      }

      return isValid;
   }

   public static boolean esDouble(String valor) {
      boolean isValid = false;
      Pattern pattern = Pattern.compile("^[-+]?[0-9]*\\.?[0-9]+$");
      Matcher matcher = pattern.matcher(valor);
      if (matcher.matches()) {
         isValid = true;
      }

      return isValid;
   }

   public static boolean esDoublePositivo(String valor) {
      boolean isValid = false;
      Pattern pattern = Pattern.compile("^[+]?[0-9]*\\.?[0-9]+$");
      Matcher matcher = pattern.matcher(valor);
      if (matcher.matches()) {
         isValid = true;
      }

      return isValid;
   }

   public static boolean esDoubleNegativo(String valor) {
      boolean isValid = false;
      Pattern pattern = Pattern.compile("^[-]?[0-9]*\\.?[0-9]+$");
      Matcher matcher = pattern.matcher(valor);
      if (matcher.matches()) {
         isValid = true;
      }

      return isValid;
   }

   public static boolean esEmail(String valor) {
      boolean isValid = false;
      Pattern pattern = Pattern.compile("^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$", 2);
      Matcher matcher = pattern.matcher(valor);
      if (matcher.matches()) {
         isValid = true;
      }

      return isValid;
   }

   public static boolean esCaracterEspecial(String valor) {
      boolean isValid = false;
      Pattern pattern = Pattern.compile("^([\\s*\\w\\á\\Á\\é\\É\\í\\Í\\ó\\Ó\\ú\\Ú\\s*]+)?$");
      Matcher matcher = pattern.matcher(valor);
      if (!matcher.matches()) {
         isValid = true;
      }

      return isValid;
   }

   public static boolean esCaracterEspecialCodigo(String valor) {
      boolean isValid = false;
      Pattern pattern = Pattern.compile("^([\\w\\á\\Á\\é\\É\\í\\Í\\ó\\Ó\\ú\\Ú]+)?$");
      Matcher matcher = pattern.matcher(valor);
      if (!matcher.matches()) {
         isValid = true;
      }

      return isValid;
   }

   public static boolean esCaracterEspecialPunto(String valor) {
      boolean isValid = false;
      Pattern pattern = Pattern.compile("^([\\w\\á\\Á\\é\\É\\í\\Í\\ó\\Ó\\ú\\Ú\\.]+)?$");
      Matcher matcher = pattern.matcher(valor);
      if (matcher.matches()) {
         isValid = true;
      }

      return isValid;
   }

   public static boolean esNulo(String valor) {
      boolean isValid = false;
      if (valor == null) {
         isValid = true;
      }

      return isValid;
   }

   public static boolean esVacio(String valor) {
      boolean isValid = false;
      if (valor != null && valor.equals("")) {
         isValid = true;
      }

      return isValid;
   }

   public static boolean esLength(String valor) {
      boolean isValid = false;
      if (valor != null && valor.trim().length() > 0) {
         isValid = true;
      }

      return isValid;
   }
}
