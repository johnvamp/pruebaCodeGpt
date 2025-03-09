package co.com.personalsoft.base.programacion.control;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class AgenteValidador implements Serializable {
   private static final long serialVersionUID = 5991972946041089478L;

   public String[] consultarMetodosClase(String nombreClase) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
      Method[] metodos = (Method[])null;
      String nombreMetodo = "";
      String[] object = (String[])null;
      Class clase = Class.forName(nombreClase);
      if (clase != null && Modifier.isPublic(clase.getModifiers())) {
         clase.newInstance();
         metodos = clase.getDeclaredMethods();
         if (metodos != null && metodos.length > 0) {
            object = new String[metodos.length];

            for(int k = 0; k < metodos.length; ++k) {
               Method metodo = metodos[k];
               Class[] tiposParametros = metodo.getParameterTypes();
               String str = new String();
               String parametros = new String();
               str = str + "(";
               Class[] var14 = tiposParametros;
               int var12 = 0;

               for(int var13 = tiposParametros.length; var12 < var13; ++var12) {
                  Class cla = var14[var12];
                  str = str + cla.getSimpleName() + ",";
                  parametros = parametros + cla.getSimpleName() + ",";
               }

               if (str.endsWith(",")) {
                  str = str.substring(0, str.length() - 1);
               }

               if (parametros.endsWith(",")) {
                  parametros = parametros.substring(0, parametros.length() - 1);
               }

               str = str + ")";
               if (Modifier.isPublic(metodo.getModifiers())) {
                  nombreMetodo = metodo.getName() + ">" + parametros + "-" + "public " + metodo.getReturnType().getSimpleName() + " " + metodo.getName() + str;
               } else if (Modifier.isPrivate(metodo.getModifiers())) {
                  nombreMetodo = "-private " + metodo.getName() + str;
               } else if (Modifier.isAbstract(metodo.getModifiers())) {
                  nombreMetodo = "-abstract " + metodo.getName() + str;
               } else if (Modifier.isFinal(metodo.getModifiers())) {
                  nombreMetodo = "-final " + metodo.getName() + str;
               } else if (Modifier.isInterface(metodo.getModifiers())) {
                  nombreMetodo = "-interface " + metodo.getName() + str;
               } else if (Modifier.isNative(metodo.getModifiers())) {
                  nombreMetodo = "-native " + metodo.getName() + str;
               } else if (Modifier.isProtected(metodo.getModifiers())) {
                  nombreMetodo = "-protected " + metodo.getName() + str;
               } else if (Modifier.isStatic(metodo.getModifiers())) {
                  nombreMetodo = "-static " + metodo.getName() + str;
               } else if (Modifier.isStrict(metodo.getModifiers())) {
                  nombreMetodo = "-strict " + metodo.getName() + str;
               } else if (Modifier.isSynchronized(metodo.getModifiers())) {
                  nombreMetodo = "-synchronized " + metodo.getName() + str;
               }

               object[k] = nombreMetodo;
            }
         }
      }

      return object;
   }

   public String[] consultarParametrosMetodo(String nombreClase, String nombreMetodo, Object[] parametros) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
      Method metodo = null;
      String[] object = (String[])null;
      Class clase = Class.forName(nombreClase);
      if (clase != null) {
         if (Modifier.isPublic(clase.getModifiers())) {
            clase.newInstance();
            metodo = clase.getMethod(nombreMetodo, this.getTiposParametros(parametros));
            Class[] tiposParametros = metodo.getParameterTypes();
            object = new String[tiposParametros.length];

            for(int k = 0; k < tiposParametros.length; ++k) {
               Class clas = tiposParametros[k];
               if (clas.equals(String.class)) {
                  object[k] = clas.getCanonicalName();
               } else if (clas.isPrimitive()) {
                  object[k] = clas.getCanonicalName();
               } else {
                  object[k] = "_" + clas.getCanonicalName();
               }
            }
         } else {
            object = new String[]{"_Clase que implementa la lógica no existe"};
         }
      }

      return object;
   }

   private Class[] getTiposParametros(Object[] parametros) {
      Class[] tiposParametrosEjecucionTmp = (Class[])null;
      if (parametros != null && parametros.length > 0) {
         tiposParametrosEjecucionTmp = new Class[parametros.length];

         for(int i = 0; i < parametros.length; ++i) {
            Object objeto = parametros[i].toString();
            if (objeto.toString().equalsIgnoreCase("int")) {
               tiposParametrosEjecucionTmp[i] = Integer.TYPE;
            } else if (objeto.toString().equalsIgnoreCase("float")) {
               tiposParametrosEjecucionTmp[i] = Float.TYPE;
            } else if (objeto.toString().equalsIgnoreCase("double")) {
               tiposParametrosEjecucionTmp[i] = Double.TYPE;
            } else if (objeto.toString().equalsIgnoreCase("char")) {
               tiposParametrosEjecucionTmp[i] = Character.TYPE;
            } else if (objeto.toString().equalsIgnoreCase("long")) {
               tiposParametrosEjecucionTmp[i] = Long.TYPE;
            } else if (objeto.toString().equalsIgnoreCase("boolean")) {
               tiposParametrosEjecucionTmp[i] = Boolean.TYPE;
            } else if (objeto.toString().equalsIgnoreCase("short")) {
               tiposParametrosEjecucionTmp[i] = Short.TYPE;
            } else {
               tiposParametrosEjecucionTmp[i] = String.class;
            }
         }

         return tiposParametrosEjecucionTmp;
      } else {
         return null;
      }
   }
}
