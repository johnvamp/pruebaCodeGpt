package co.com.personalsoft.base.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.apache.log4j.Logger;

public class OperadorArchivos {
   private static Logger logger = Logger.getLogger(OperadorArchivos.class);

   public static String[] cargarArchivoLineas(InputStream inStr) throws IOException {
      ArrayList lines = new ArrayList();

      try {
         BufferedReader in = new BufferedReader(new InputStreamReader(inStr));

         String str;
         while((str = in.readLine()) != null) {
            lines.add(str);
         }

         in.close();
         return lines != null && lines.size() > 0 ? (String[])lines.toArray(new String[0]) : new String[0];
      } catch (IOException var4) {
         logger.error(var4);
         throw var4;
      }
   }

   public static String cargarArchivo(InputStream inStr) throws IOException {
      StringBuffer lines = new StringBuffer();

      try {
         BufferedReader in = new BufferedReader(new InputStreamReader(inStr));

         String str;
         while((str = in.readLine()) != null) {
            lines.append(str);
            lines.append("\n");
         }

         in.close();
         return lines.toString();
      } catch (IOException var4) {
         logger.error(var4);
         throw var4;
      }
   }

   public static void generarArchivo(PrintWriter printWriter) throws IOException {
      try {
         BufferedWriter bufferedWriter = new BufferedWriter(printWriter);
         bufferedWriter.close();
      } catch (IOException var2) {
         logger.error(var2);
         throw var2;
      }
   }
}
