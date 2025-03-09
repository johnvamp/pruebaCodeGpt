package co.com.personalsoft.base.seguridad.encripcion;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

class BlowfishSimple {
   BlowfishCBC bfc;
   SecureRandom srnd = new SecureRandom();

   BlowfishSimple(char[] passw) {
      MessageDigest md = null;

      try {
         md = MessageDigest.getInstance("SHA-1");
      } catch (NoSuchAlgorithmException var6) {
         throw new UnsupportedOperationException();
      }

      int i = 0;

      for(int c = passw.length; i < c; ++i) {
         char pc = passw[i];
         md.update((byte)(pc >>> 8));
         md.update((byte)(pc & 255));
      }

      byte[] hash = md.digest();
      this.bfc = new BlowfishCBC(hash, 0, hash.length, 0L);
   }

   String encryptString(String plainText) {
      return this.encStr(plainText, this.srnd.nextLong());
   }

   String encryptString(String plaintext, Random rndgen) {
      return this.encStr(plaintext, rndgen.nextLong());
   }

   private String encStr(String plainText, long newCBCIV) {
      int strlen = plainText.length();
      byte[] buf = new byte[(strlen << 1 & -8) + 8];
      int pos = 0;

      for(int i = 0; i < strlen; ++i) {
         char achar = plainText.charAt(i);
         buf[pos++] = (byte)(achar >> 8 & 255);
         buf[pos++] = (byte)(achar & 255);
      }

      for(byte padval = (byte)(buf.length - (strlen << 1)); pos < buf.length; buf[pos++] = padval) {
      }

      this.bfc.setCBCIV(newCBCIV);
      this.bfc.encrypt(buf, 0, buf, 0, buf.length);
      byte[] newIV = new byte[8];
      BinConverter.longToByteArray(newCBCIV, newIV, 0);
      return BinConverter.bytesToHexStr(newIV, 0, 8) + BinConverter.bytesToHexStr(buf, 0, buf.length);
   }

   String decryptString(String cipherText) {
      int len = cipherText.length() >> 1 & -8;
      if (8 > len) {
         return null;
      } else {
         byte[] cbciv = new byte[8];
         int numOfBytes = BinConverter.hexStrToBytes(cipherText, cbciv, 0, 0, 8);
         if (numOfBytes < 8) {
            return null;
         } else {
            this.bfc.setCBCIV(cbciv, 0);
            len -= 8;
            if (len == 0) {
               return "";
            } else {
               byte[] buf = new byte[len];
               numOfBytes = BinConverter.hexStrToBytes(cipherText, buf, 16, 0, len);
               if (numOfBytes < len) {
                  return null;
               } else {
                  this.bfc.decrypt(buf, 0, buf, 0, buf.length);
                  int padbyte = buf[buf.length - 1] & 255;
                  if (8 < padbyte) {
                     padbyte = 0;
                  }

                  numOfBytes -= padbyte;
                  return numOfBytes < 0 ? "" : BinConverter.byteArrayToStr(buf, 0, numOfBytes);
               }
            }
         }
      }
   }

   void destroy() {
      this.bfc.cleanUp();
   }
}
