package co.com.personalsoft.base.seguridad.encripcion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class RSA {
   private static final int KEYSIZE = 512;

   public void generarLlaves(String rutaLlavePublica, String rutaLlavePrivada) throws Exception {
      KeyPairGenerator pairgen = KeyPairGenerator.getInstance("RSA");
      SecureRandom random = new SecureRandom();
      pairgen.initialize(512, random);
      KeyPair keyPair = pairgen.generateKeyPair();
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(rutaLlavePublica));
      out.writeObject(keyPair.getPublic());
      out.close();
      out = new ObjectOutputStream(new FileOutputStream(rutaLlavePrivada));
      out.writeObject(keyPair.getPrivate());
   }

   public void encriptar(String rutaArchivoEncritado, String rutaArchivoEncriptar, String rutaLlavePublica) throws Exception {
      KeyGenerator keygen = KeyGenerator.getInstance("AES");
      SecureRandom random = new SecureRandom();
      keygen.init(random);
      SecretKey key = keygen.generateKey();
      ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(rutaLlavePublica));
      Key publicKey = (Key)keyIn.readObject();
      keyIn.close();
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(3, publicKey);
      byte[] wrappedKey = cipher.wrap(key);
      DataOutputStream out = new DataOutputStream(new FileOutputStream(rutaArchivoEncritado));
      out.writeInt(wrappedKey.length);
      out.write(wrappedKey);
      InputStream in = new FileInputStream(rutaArchivoEncriptar);
      cipher = Cipher.getInstance("AES");
      cipher.init(1, key);
      this.transformar(in, out, cipher);
      in.close();
      out.close();
   }

   public void desencriptar(String rutaArchivoEncriptado, String rutaArchivoDesencriptado, String rutaLlavePrivada) throws Exception {
      DataInputStream in = new DataInputStream(new FileInputStream(rutaArchivoEncriptado));
      int length = in.readInt();
      byte[] wrappedKey = new byte[length];
      in.read(wrappedKey, 0, length);
      ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(rutaLlavePrivada));
      Key privateKey = (Key)keyIn.readObject();
      keyIn.close();
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(4, privateKey);
      Key key = cipher.unwrap(wrappedKey, "AES", 3);
      OutputStream out = new FileOutputStream(rutaArchivoDesencriptado);
      cipher = Cipher.getInstance("AES");
      cipher.init(2, key);
      this.transformar(in, out, cipher);
      in.close();
      out.close();
   }

   private void transformar(InputStream in, OutputStream out, Cipher cipher) throws IOException, GeneralSecurityException {
      int blockSize = cipher.getBlockSize();
      int outputSize = cipher.getOutputSize(blockSize);
      byte[] inBytes = new byte[blockSize];
      byte[] outBytes = new byte[outputSize];
      int inLength = 0;
      boolean more = true;

      while(more) {
         inLength = in.read(inBytes);
         if (inLength == blockSize) {
            int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
            out.write(outBytes, 0, outLength);
         } else {
            more = false;
         }
      }

      if (inLength > 0) {
         outBytes = cipher.doFinal(inBytes, 0, inLength);
      } else {
         outBytes = cipher.doFinal();
      }

      out.write(outBytes);
   }
}
