package co.com.personalsoft.base.seguridad.encripcion;

import com.sun.crypto.provider.SunJCE;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class TripleDES {
   public TripleDES() {
      try {
         Cipher.getInstance("DESede");
      } catch (Exception var3) {
         Provider sunjce = new SunJCE();
         Security.addProvider(sunjce);
      }

   }

   public void encriptar(InputStream claveEncripcion, InputStream contenidoEncriptar, OutputStream contenidoEncriptado) throws Exception {
      SecretKey secretKey = this.readKey(claveEncripcion);
      this.encrypt(secretKey, contenidoEncriptar, contenidoEncriptado);
   }

   public void desencriptar(InputStream claveEncripcion, InputStream contenidoEncriptado, OutputStream contenidoDesencriptado) throws Exception {
      SecretKey secretKey = this.readKey(claveEncripcion);
      this.decrypt(secretKey, contenidoEncriptado, contenidoDesencriptado);
   }

   public void generarClave(OutputStream clave) throws Exception {
      KeyGenerator keygen = KeyGenerator.getInstance("DESede");
      SecretKey key = keygen.generateKey();
      SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
      DESedeKeySpec keyspec = (DESedeKeySpec)keyfactory.getKeySpec(key, DESedeKeySpec.class);
      byte[] rawkey = keyspec.getKey();
      clave.write(rawkey);
      clave.close();
   }

   private SecretKey readKey(InputStream inputStream) throws IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
      File f = new File("C:/key.txt");
      DataInputStream in = new DataInputStream(new FileInputStream(f));
      byte[] rawkey = new byte[(int)f.length()];
      in.readFully(rawkey);
      in.close();
      DESedeKeySpec keyspec = new DESedeKeySpec(rawkey);
      SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
      SecretKey key = keyfactory.generateSecret(keyspec);
      return key;
   }

   private void encrypt(SecretKey key, InputStream in, OutputStream out) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IOException {
      Cipher cipher = Cipher.getInstance("DESede");
      cipher.init(1, key);
      CipherOutputStream cos = new CipherOutputStream(out, cipher);
      byte[] buffer = new byte[2048];

      int bytesRead;
      while((bytesRead = in.read(buffer)) != -1) {
         cos.write(buffer, 0, bytesRead);
      }

      cos.close();
      Arrays.fill(buffer, (byte)0);
   }

   private void decrypt(SecretKey key, InputStream in, OutputStream out) throws NoSuchAlgorithmException, InvalidKeyException, IOException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException {
      Cipher cipher = Cipher.getInstance("DESede");
      cipher.init(2, key);
      byte[] buffer = new byte[2048];

      int bytesRead;
      while((bytesRead = in.read(buffer)) != -1) {
         out.write(cipher.update(buffer, 0, bytesRead));
      }

      out.write(cipher.doFinal());
      out.flush();
   }
}
