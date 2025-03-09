package co.com.personalsoft.base.seguridad.encripcion;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1 {
   public String encriptar(String cadena) {
      String hash = "";

      try {
         byte[] buffer = cadena.getBytes();
         MessageDigest md = MessageDigest.getInstance("SHA1");
         md.update(buffer);
         byte[] digest = md.digest();
         byte[] var9 = digest;
         int var7 = 0;

         for(int var8 = digest.length; var7 < var8; ++var7) {
            byte aux = var9[var7];
            int b = aux & 255;
            if (Integer.toHexString(b).length() == 1) {
               hash = hash + "0";
            }

            hash = hash + Integer.toHexString(b);
         }
      } catch (NoSuchAlgorithmException var11) {
         var11.printStackTrace();
      }

      return hash;
   }
}
