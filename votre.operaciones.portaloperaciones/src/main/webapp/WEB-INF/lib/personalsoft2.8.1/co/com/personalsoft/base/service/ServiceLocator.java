package co.com.personalsoft.base.service;

import co.com.personalsoft.base.beans.Servicio;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

public class ServiceLocator {
   private Map cache = Collections.synchronizedMap(new HashMap());
   private static ServiceLocator serviceLocator;

   private ServiceLocator() {
   }

   public static ServiceLocator getInstance() {
      if (serviceLocator == null) {
         serviceLocator = new ServiceLocator();
      }

      return serviceLocator;
   }

   private Object getRemoteHomeEJB(String nombreRecurso, String serviceName) throws PersonalsoftException {
      Object var3 = null;

      try {
         Context initial = new InitialContext();
         Object objref = initial.lookup(nombreRecurso);
         Object obj = PortableRemoteObject.narrow(objref, (Class)getObject(serviceName));
         return obj;
      } catch (NamingException var7) {
         throw new PersonalsoftException(var7);
      } catch (Exception var8) {
         throw new PersonalsoftException(var8);
      }
   }

   public Object getLocalHomeEJB(String jndiHomeName) throws PersonalsoftException {
      Object home = this.cache.get(jndiHomeName);
      if (home == null) {
         try {
            Context initial = new InitialContext();
            home = initial.lookup(jndiHomeName);
            this.cache.put(jndiHomeName, home);
         } catch (NamingException var4) {
            throw new PersonalsoftException(var4);
         }
      }

      return home;
   }

   private static Object getObject(String serviceName) throws PersonalsoftException {
      Object object = null;

      try {
         object = Class.forName(serviceName).newInstance();
         return object;
      } catch (IllegalAccessException var3) {
         throw new PersonalsoftException(var3);
      } catch (InstantiationException var4) {
         throw new PersonalsoftException(var4);
      } catch (SecurityException var5) {
         throw new PersonalsoftException(var5);
      } catch (ClassNotFoundException var6) {
         throw new PersonalsoftException(var6);
      }
   }

   private static Object getPojo(String serviceName) throws PersonalsoftException {
      Class className = null;
      Object object = null;
      boolean existeClase = false;

      try {
         className = Class.forName(serviceName);
         className.getMethod("getInstance");
         existeClase = true;
      } catch (ClassNotFoundException var9) {
         throw new PersonalsoftException(var9);
      } catch (SecurityException var10) {
         throw new PersonalsoftException(var10);
      } catch (NoSuchMethodException var11) {
      }

      if (!existeClase) {
         try {
            object = Class.forName(serviceName).newInstance();
         } catch (IllegalAccessException var5) {
            throw new PersonalsoftException(var5);
         } catch (InstantiationException var6) {
            throw new PersonalsoftException(var6);
         } catch (SecurityException var7) {
            throw new PersonalsoftException(var7);
         } catch (ClassNotFoundException var8) {
            throw new PersonalsoftException(var8);
         }
      } else {
         object = className.getClass();
      }

      return object;
   }

   public Object lookup(String ruta) throws PersonalsoftException {
      return getPojo(ruta);
   }

   public Object lookup(Servicio servicio) throws PersonalsoftException {
      Object object = null;
      if (servicio != null) {
         if (servicio.getTipo().equals("EJB")) {
            object = this.getRemoteHomeEJB(servicio.getNombre(), servicio.getRuta());
         } else if (servicio.getTipo().equals("POJO")) {
            object = getPojo(servicio.getRuta());
         } else if (servicio.getTipo().equals("WS")) {
            object = getObject(servicio.getRuta());
         }
      }

      return object;
   }
}
