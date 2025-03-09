package co.com.personalsoft.base.seguridad.encripcion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class MD5 {
   private MD5.MD5State workingState = new MD5.MD5State((MD5.MD5State)null);
   private MD5.MD5State finalState = new MD5.MD5State((MD5.MD5State)null);
   private int[] decodeBuffer = new int[16];
   private final byte[] padding = new byte[]{-128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

   public MD5() {
      this.reset();
   }

   public byte[] getHash(byte[] b) {
      this.update(b);
      return this.getHash();
   }

   public String getHashString(byte[] b) {
      this.update(b);
      return this.getHashString();
   }

   public byte[] getHash(InputStream in) throws IOException {
      byte[] buffer = new byte[1024];

      int read;
      while((read = in.read(buffer)) != -1) {
         this.update(buffer, read);
      }

      return this.getHash();
   }

   public String getHashString(InputStream in) throws IOException {
      byte[] buffer = new byte[1024];

      int read;
      while((read = in.read(buffer)) != -1) {
         this.update(buffer, read);
      }

      return this.getHashString();
   }

   public byte[] getHash(File archivo) throws IOException {
      InputStream is = new FileInputStream(archivo);
      byte[] hash = this.getHash((InputStream)is);
      is.close();
      return hash;
   }

   public String getHashString(File archivo) throws IOException {
      InputStream is = new FileInputStream(archivo);
      String hash = this.getHashString((InputStream)is);
      is.close();
      return hash;
   }

   public byte[] getHash(String cadena) {
      this.update(cadena);
      return this.getHash();
   }

   public String getHashString(String s) {
      this.update(s);
      return this.getHashString();
   }

   public byte[] getHash(String cadena, String enc) throws UnsupportedEncodingException {
      this.update(cadena, enc);
      return this.getHash();
   }

   public String getHashString(String s, String enc) throws UnsupportedEncodingException {
      this.update(s, enc);
      return this.getHashString();
   }

   private byte[] getHash() {
      if (!this.finalState.valid) {
         this.finalState.copy(this.workingState);
         long bitCount = this.finalState.bitCount;
         int leftOver = (int)(bitCount >>> 3 & 63L);
         int padlen = leftOver < 56 ? 56 - leftOver : 120 - leftOver;
         this.update(this.finalState, this.padding, 0, padlen);
         this.update(this.finalState, this.encode(bitCount), 0, 8);
         this.finalState.valid = true;
      }

      return this.encode(this.finalState.state, 16);
   }

   private String getHashString() {
      String retorno = "";
      retorno = this.toHex(this.getHash());
      this.reset();
      return retorno;
   }

   private void reset() {
      this.workingState.reset();
      this.finalState.valid = false;
   }

   public String toString() {
      return this.getHashString();
   }

   private void update(MD5.MD5State state, byte[] buffer, int offset, int length) {
      this.finalState.valid = false;
      if (length + offset > buffer.length) {
         length = buffer.length - offset;
      }

      int index = (int)(state.bitCount >>> 3) & 63;
      state.bitCount = state.bitCount + (long)(length << 3);
      int partlen = 64 - index;
      int i = 0;
      if (length >= partlen) {
         System.arraycopy(buffer, offset, state.buffer, index, partlen);
         this.transform(state, this.decode(state.buffer, 64, 0));

         for(i = partlen; i + 63 < length; i += 64) {
            this.transform(state, this.decode(buffer, 64, i));
         }

         index = 0;
      }

      if (i < length) {
         for(int start = i; i < length; ++i) {
            state.buffer[index + i - start] = buffer[i + offset];
         }
      }

   }

   private void update(byte[] buffer, int offset, int length) {
      this.update(this.workingState, buffer, offset, length);
   }

   private void update(byte[] buffer, int length) {
      this.update(buffer, 0, length);
   }

   private void update(byte[] buffer) {
      this.update(buffer, 0, buffer.length);
   }

   private void update(String s) {
      this.update(s.getBytes());
   }

   private void update(String s, String enc) throws UnsupportedEncodingException {
      this.update(s.getBytes(enc));
   }

   private String toHex(byte[] hash) {
      StringBuffer buf = new StringBuffer(hash.length * 2);
      byte[] var6 = hash;
      int var4 = 0;

      for(int var5 = hash.length; var4 < var5; ++var4) {
         byte element = var6[var4];
         int intVal = element & 255;
         if (intVal < 16) {
            buf.append("0");
         }

         buf.append(Integer.toHexString(intVal));
      }

      return buf.toString();
   }

   private int FF(int a, int b, int c, int d, int x, int s, int ac) {
      a += b & c | ~b & d;
      a += x;
      a += ac;
      a = a << s | a >>> 32 - s;
      return a + b;
   }

   private int GG(int a, int b, int c, int d, int x, int s, int ac) {
      a += b & d | c & ~d;
      a += x;
      a += ac;
      a = a << s | a >>> 32 - s;
      return a + b;
   }

   private int HH(int a, int b, int c, int d, int x, int s, int ac) {
      a += b ^ c ^ d;
      a += x;
      a += ac;
      a = a << s | a >>> 32 - s;
      return a + b;
   }

   private int II(int a, int b, int c, int d, int x, int s, int ac) {
      a += c ^ (b | ~d);
      a += x;
      a += ac;
      a = a << s | a >>> 32 - s;
      return a + b;
   }

   private byte[] encode(long l) {
      byte[] out = new byte[]{(byte)((int)(l & 255L)), (byte)((int)(l >>> 8 & 255L)), (byte)((int)(l >>> 16 & 255L)), (byte)((int)(l >>> 24 & 255L)), (byte)((int)(l >>> 32 & 255L)), (byte)((int)(l >>> 40 & 255L)), (byte)((int)(l >>> 48 & 255L)), (byte)((int)(l >>> 56 & 255L))};
      return out;
   }

   private byte[] encode(int[] input, int len) {
      byte[] out = new byte[len];
      int j = 0;

      for(int i = 0; j < len; j += 4) {
         out[j] = (byte)(input[i] & 255);
         out[j + 1] = (byte)(input[i] >>> 8 & 255);
         out[j + 2] = (byte)(input[i] >>> 16 & 255);
         out[j + 3] = (byte)(input[i] >>> 24 & 255);
         ++i;
      }

      return out;
   }

   private int[] decode(byte[] buffer, int len, int offset) {
      int j = 0;

      for(int i = 0; j < len; j += 4) {
         this.decodeBuffer[i] = buffer[j + offset] & 255 | (buffer[j + 1 + offset] & 255) << 8 | (buffer[j + 2 + offset] & 255) << 16 | (buffer[j + 3 + offset] & 255) << 24;
         ++i;
      }

      return this.decodeBuffer;
   }

   private void transform(MD5.MD5State state, int[] x) {
      int a = state.state[0];
      int b = state.state[1];
      int c = state.state[2];
      int d = state.state[3];
      a = this.FF(a, b, c, d, x[0], 7, -680876936);
      d = this.FF(d, a, b, c, x[1], 12, -389564586);
      c = this.FF(c, d, a, b, x[2], 17, 606105819);
      b = this.FF(b, c, d, a, x[3], 22, -1044525330);
      a = this.FF(a, b, c, d, x[4], 7, -176418897);
      d = this.FF(d, a, b, c, x[5], 12, 1200080426);
      c = this.FF(c, d, a, b, x[6], 17, -1473231341);
      b = this.FF(b, c, d, a, x[7], 22, -45705983);
      a = this.FF(a, b, c, d, x[8], 7, 1770035416);
      d = this.FF(d, a, b, c, x[9], 12, -1958414417);
      c = this.FF(c, d, a, b, x[10], 17, -42063);
      b = this.FF(b, c, d, a, x[11], 22, -1990404162);
      a = this.FF(a, b, c, d, x[12], 7, 1804603682);
      d = this.FF(d, a, b, c, x[13], 12, -40341101);
      c = this.FF(c, d, a, b, x[14], 17, -1502002290);
      b = this.FF(b, c, d, a, x[15], 22, 1236535329);
      a = this.GG(a, b, c, d, x[1], 5, -165796510);
      d = this.GG(d, a, b, c, x[6], 9, -1069501632);
      c = this.GG(c, d, a, b, x[11], 14, 643717713);
      b = this.GG(b, c, d, a, x[0], 20, -373897302);
      a = this.GG(a, b, c, d, x[5], 5, -701558691);
      d = this.GG(d, a, b, c, x[10], 9, 38016083);
      c = this.GG(c, d, a, b, x[15], 14, -660478335);
      b = this.GG(b, c, d, a, x[4], 20, -405537848);
      a = this.GG(a, b, c, d, x[9], 5, 568446438);
      d = this.GG(d, a, b, c, x[14], 9, -1019803690);
      c = this.GG(c, d, a, b, x[3], 14, -187363961);
      b = this.GG(b, c, d, a, x[8], 20, 1163531501);
      a = this.GG(a, b, c, d, x[13], 5, -1444681467);
      d = this.GG(d, a, b, c, x[2], 9, -51403784);
      c = this.GG(c, d, a, b, x[7], 14, 1735328473);
      b = this.GG(b, c, d, a, x[12], 20, -1926607734);
      a = this.HH(a, b, c, d, x[5], 4, -378558);
      d = this.HH(d, a, b, c, x[8], 11, -2022574463);
      c = this.HH(c, d, a, b, x[11], 16, 1839030562);
      b = this.HH(b, c, d, a, x[14], 23, -35309556);
      a = this.HH(a, b, c, d, x[1], 4, -1530992060);
      d = this.HH(d, a, b, c, x[4], 11, 1272893353);
      c = this.HH(c, d, a, b, x[7], 16, -155497632);
      b = this.HH(b, c, d, a, x[10], 23, -1094730640);
      a = this.HH(a, b, c, d, x[13], 4, 681279174);
      d = this.HH(d, a, b, c, x[0], 11, -358537222);
      c = this.HH(c, d, a, b, x[3], 16, -722521979);
      b = this.HH(b, c, d, a, x[6], 23, 76029189);
      a = this.HH(a, b, c, d, x[9], 4, -640364487);
      d = this.HH(d, a, b, c, x[12], 11, -421815835);
      c = this.HH(c, d, a, b, x[15], 16, 530742520);
      b = this.HH(b, c, d, a, x[2], 23, -995338651);
      a = this.II(a, b, c, d, x[0], 6, -198630844);
      d = this.II(d, a, b, c, x[7], 10, 1126891415);
      c = this.II(c, d, a, b, x[14], 15, -1416354905);
      b = this.II(b, c, d, a, x[5], 21, -57434055);
      a = this.II(a, b, c, d, x[12], 6, 1700485571);
      d = this.II(d, a, b, c, x[3], 10, -1894986606);
      c = this.II(c, d, a, b, x[10], 15, -1051523);
      b = this.II(b, c, d, a, x[1], 21, -2054922799);
      a = this.II(a, b, c, d, x[8], 6, 1873313359);
      d = this.II(d, a, b, c, x[15], 10, -30611744);
      c = this.II(c, d, a, b, x[6], 15, -1560198380);
      b = this.II(b, c, d, a, x[13], 21, 1309151649);
      a = this.II(a, b, c, d, x[4], 6, -145523070);
      d = this.II(d, a, b, c, x[11], 10, -1120210379);
      c = this.II(c, d, a, b, x[2], 15, 718787259);
      b = this.II(b, c, d, a, x[9], 21, -343485551);
      int[] var10000 = state.state;
      var10000[0] += a;
      var10000 = state.state;
      var10000[1] += b;
      var10000 = state.state;
      var10000[2] += c;
      var10000 = state.state;
      var10000[3] += d;
   }

   private class MD5State {
      private boolean valid;
      private int[] state;
      private long bitCount;
      private byte[] buffer;

      private void reset() {
         this.state[0] = 1732584193;
         this.state[1] = -271733879;
         this.state[2] = -1732584194;
         this.state[3] = 271733878;
         this.bitCount = 0L;
      }

      private MD5State() {
         this.valid = true;
         this.state = new int[4];
         this.buffer = new byte[64];
         this.reset();
      }

      private void copy(MD5.MD5State from) {
         System.arraycopy(from.buffer, 0, this.buffer, 0, this.buffer.length);
         System.arraycopy(from.state, 0, this.state, 0, this.state.length);
         this.valid = from.valid;
         this.bitCount = from.bitCount;
      }

      // $FF: synthetic method
      MD5State(MD5.MD5State var2) {
         this();
      }
   }
}
