package co.com.personalsoft.base.seguridad.encripcion;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
   public void encriptar(String semilla, InputStream origen, OutputStream destino) throws Exception {
      byte[] pass16 = this.getClave16(semilla.toCharArray());
      Arrays.fill(semilla.toCharArray(), '\u0000');
      byte[] iv = "1234567890123456".getBytes("UTF-8");
      IvParameterSpec extra = new IvParameterSpec(iv);
      Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
      SecretKeySpec key = new SecretKeySpec(pass16, "AES");
      Arrays.fill(pass16, (byte)0);
      c.init(1, key, extra);
      destino.write(iv);
      byte[] buffer = new byte[4096];

      int i;
      while((i = origen.read(buffer)) != -1) {
         destino.write(c.update(buffer, 0, i));
      }

      destino.write(c.doFinal());
      destino.close();
      origen.close();
   }

   public void desencriptar(String semilla, InputStream origen, OutputStream destino) throws Exception {
      byte[] pass16 = this.getClave16(semilla.toCharArray());
      Arrays.fill(semilla.toCharArray(), '\u0000');
      byte[] iv = "1234567890123456".getBytes("UTF-8");
      IvParameterSpec extra = new IvParameterSpec(iv);
      Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
      SecretKeySpec key = new SecretKeySpec(pass16, "AES");
      Arrays.fill(pass16, (byte)0);
      c.init(2, key, extra);

      int n;
      for(int total = 0; total < iv.length; total += n) {
         n = origen.read(iv, total, iv.length - total);
         if (n == -1) {
            throw new Exception("No se pudo desencriptar.");
         }
      }

      byte[] buffer = new byte[4096];

      while((n = origen.read(buffer)) != -1) {
         destino.write(c.update(buffer, 0, n));
      }

      destino.write(c.doFinal());
      destino.close();
      origen.close();
   }

   private byte[] getClave16(char[] clave) {
      MessageDigest md = null;

      try {
         md = MessageDigest.getInstance("SHA-1");
      } catch (Exception var7) {
      }

      byte[] msg = new byte[clave.length];

      for(int i = 0; i < clave.length; ++i) {
         msg[i] = (byte)clave[i];
      }

      md.update(msg);
      byte[] msg2 = md.digest();
      Arrays.fill(msg, (byte)0);

      for(int i = 0; i < 70000; ++i) {
         msg = new byte[msg2.length + 1];
         System.arraycopy(msg2, 0, msg, 0, msg2.length);
         msg[msg2.length] = (byte)(i % 128);
         md.update(msg);
         Arrays.fill(msg2, (byte)0);
         msg2 = md.digest();
         Arrays.fill(msg, (byte)0);
      }

      byte[] clave16 = new byte[16];

      for(int i = 0; i < 16; ++i) {
         clave16[i] = msg2[i];
      }

      Arrays.fill(msg2, (byte)0);
      return clave16;
   }
}
