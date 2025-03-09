package co.com.personalsoft.base.crypt;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.Logger;

public class AgenteEncripcion {
   public static final String BLOWFISH = "Blowfish";
   public static final String COMPLEMENTO_CLAVE = "personalsoftFW";
   private static Logger logger = Logger.getLogger(AgenteEncripcion.class);

   public static String encriptarBlowfish(String contenido, String semilla) {
      SecretKeySpec skeySpec = new SecretKeySpec(semilla.getBytes(), "Blowfish");
      Cipher cipher = null;
      byte[] encrypted = (byte[])null;

      try {
         cipher = Cipher.getInstance("Blowfish");
         cipher.init(1, skeySpec);
         encrypted = cipher.doFinal(contenido.getBytes());
      } catch (Exception var7) {
         PersonalsoftException personalsoftException = new PersonalsoftException(var7);
         logger.error(personalsoftException.getTraza());
      }

      return Base64.encode(encrypted);
   }

   public static String desencriptarBlowfish(String contenidoEncriptado, String semilla) {
      SecretKeySpec skeySpec = new SecretKeySpec(semilla.getBytes(), "Blowfish");
      Cipher cipher = null;
      byte[] decrypted = (byte[])null;

      try {
         cipher = Cipher.getInstance("Blowfish");
         cipher.init(2, skeySpec);
         decrypted = cipher.doFinal(Base64.decode(contenidoEncriptado));
      } catch (Exception var7) {
         PersonalsoftException personalsoftException = new PersonalsoftException(var7);
         logger.error(personalsoftException.getTraza());
      }

      return new String(decrypted);
   }
}
