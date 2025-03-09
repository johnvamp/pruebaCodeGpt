package co.com.personalsoft.base.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ConfiguracionNoCache implements Serializable {
   private static final long serialVersionUID = 8129688995376593876L;

   public String[] consultarLoggersApp(String patronBusqueda) {
      Logger log = null;
      String[] loggersString = (String[])null;
      ArrayList<String> loggersArray = new ArrayList();
      Enumeration enumeracion = LogManager.getCurrentLoggers();

      while(enumeracion.hasMoreElements()) {
         log = (Logger)enumeracion.nextElement();
         if (log.getName().contains(patronBusqueda)) {
            loggersArray.add(log.getName());
            loggersArray.add(log.getEffectiveLevel().toString());
         }
      }

      loggersString = new String[loggersArray.size()];

      for(int i = 0; i < loggersArray.size(); i += 2) {
         loggersString[i] = (String)loggersArray.get(i);
         loggersString[i + 1] = (String)loggersArray.get(i + 1);
      }

      return loggersString;
   }

   public void cambiarNivelLogger(String nombre, String nivel) {
      Logger logger = Logger.getLogger(nombre);
      logger.setLevel(Level.toLevel(nivel));
   }

   public String[] consultarHilosSistema() {
      StringBuffer trace = null;
      Map<Thread, StackTraceElement[]> mapHilos = Thread.getAllStackTraces();
      ArrayList<Thread> hilos = new ArrayList((Collection)mapHilos.keySet());
      StackTraceElement[] trazasPorHilo = (StackTraceElement[])null;
      String[] retorno = new String[hilos.size() * 3];
      int z = 0;

      for(int i = 0; i < hilos.size(); ++i) {
         trazasPorHilo = (StackTraceElement[])mapHilos.get(hilos.get(i));
         trace = new StringBuffer();

         for(int j = 0; j < trazasPorHilo.length; ++j) {
            trace.append(trazasPorHilo[j].toString());
            trace.append("\n");
         }

         retorno[z] = String.valueOf(((Thread)hilos.get(i)).getId());
         ++z;
         retorno[z] = ((Thread)hilos.get(i)).getName();
         ++z;
         retorno[z] = trace.toString();
         ++z;
      }

      return retorno;
   }

   public void detenerHilo(long id) {
      Map<Thread, StackTraceElement[]> mapHilos = Thread.getAllStackTraces();
      ArrayList<Thread> hilos = new ArrayList((Collection)mapHilos.keySet());
      Iterator var6 = hilos.iterator();

      while(var6.hasNext()) {
         Thread hilo = (Thread)var6.next();
         if (hilo.getId() == id) {
            hilo.stop();
         }
      }

   }
}
