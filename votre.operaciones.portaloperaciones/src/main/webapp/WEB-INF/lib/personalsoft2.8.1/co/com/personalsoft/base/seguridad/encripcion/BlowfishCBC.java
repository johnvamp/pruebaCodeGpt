package co.com.personalsoft.base.seguridad.encripcion;

final class BlowfishCBC extends BlowfishECB {
   int ivLo;
   int ivHi;

   long getCBCIV() {
      return BinConverter.makeLong(this.ivLo, this.ivHi);
   }

   void getCBCIV(byte[] dest, int ofs) {
      BinConverter.intToByteArray(this.ivHi, dest, ofs);
      BinConverter.intToByteArray(this.ivLo, dest, ofs + 4);
   }

   void setCBCIV(long newIV) {
      this.ivHi = BinConverter.longHi32(newIV);
      this.ivLo = BinConverter.longLo32(newIV);
   }

   void setCBCIV(byte[] newIV, int ofs) {
      this.ivHi = BinConverter.byteArrayToInt(newIV, ofs);
      this.ivLo = BinConverter.byteArrayToInt(newIV, ofs + 4);
   }

   BlowfishCBC(byte[] key, int ofs, int len) {
      super(key, ofs, len);
   }

   BlowfishCBC(byte[] key, int ofs, int len, long initIV) {
      super(key, ofs, len);
      this.setCBCIV(initIV);
   }

   BlowfishCBC(byte[] key, int ofs, int len, byte[] initIV, int ivOfs) {
      super(key, ofs, len);
      this.setCBCIV(initIV, ivOfs);
   }

   public void cleanUp() {
      this.ivHi = this.ivLo = 0;
      super.cleanUp();
   }

   public int encrypt(byte[] inbuf, int inpos, byte[] outbuf, int outpos, int len) {
      len -= len % 8;
      int c = inpos + len;
      int[] pbox = this.pbox;
      int pbox00 = pbox[0];
      int pbox01 = pbox[1];
      int pbox02 = pbox[2];
      int pbox03 = pbox[3];
      int pbox04 = pbox[4];
      int pbox05 = pbox[5];
      int pbox06 = pbox[6];
      int pbox07 = pbox[7];
      int pbox08 = pbox[8];
      int pbox09 = pbox[9];
      int pbox10 = pbox[10];
      int pbox11 = pbox[11];
      int pbox12 = pbox[12];
      int pbox13 = pbox[13];
      int pbox14 = pbox[14];
      int pbox15 = pbox[15];
      int pbox16 = pbox[16];
      int pbox17 = pbox[17];
      int[] sbox1 = this.sbox1;
      int[] sbox2 = this.sbox2;
      int[] sbox3 = this.sbox3;
      int[] sbox4 = this.sbox4;
      int ivHi = this.ivHi;

      int ivLo;
      int hi;
      for(ivLo = this.ivLo; inpos < c; ivLo = hi) {
         hi = inbuf[inpos] << 24 | inbuf[inpos + 1] << 16 & 16711680 | inbuf[inpos + 2] << 8 & '\uff00' | inbuf[inpos + 3] & 255;
         int lo = inbuf[inpos + 4] << 24 | inbuf[inpos + 5] << 16 & 16711680 | inbuf[inpos + 6] << 8 & '\uff00' | inbuf[inpos + 7] & 255;
         inpos += 8;
         hi ^= ivHi;
         lo ^= ivLo;
         hi ^= pbox00;
         lo ^= (sbox1[hi >>> 24] + sbox2[hi >>> 16 & 255] ^ sbox3[hi >>> 8 & 255]) + sbox4[hi & 255] ^ pbox01;
         hi ^= (sbox1[lo >>> 24] + sbox2[lo >>> 16 & 255] ^ sbox3[lo >>> 8 & 255]) + sbox4[lo & 255] ^ pbox02;
         lo ^= (sbox1[hi >>> 24] + sbox2[hi >>> 16 & 255] ^ sbox3[hi >>> 8 & 255]) + sbox4[hi & 255] ^ pbox03;
         hi ^= (sbox1[lo >>> 24] + sbox2[lo >>> 16 & 255] ^ sbox3[lo >>> 8 & 255]) + sbox4[lo & 255] ^ pbox04;
         lo ^= (sbox1[hi >>> 24] + sbox2[hi >>> 16 & 255] ^ sbox3[hi >>> 8 & 255]) + sbox4[hi & 255] ^ pbox05;
         hi ^= (sbox1[lo >>> 24] + sbox2[lo >>> 16 & 255] ^ sbox3[lo >>> 8 & 255]) + sbox4[lo & 255] ^ pbox06;
         lo ^= (sbox1[hi >>> 24] + sbox2[hi >>> 16 & 255] ^ sbox3[hi >>> 8 & 255]) + sbox4[hi & 255] ^ pbox07;
         hi ^= (sbox1[lo >>> 24] + sbox2[lo >>> 16 & 255] ^ sbox3[lo >>> 8 & 255]) + sbox4[lo & 255] ^ pbox08;
         lo ^= (sbox1[hi >>> 24] + sbox2[hi >>> 16 & 255] ^ sbox3[hi >>> 8 & 255]) + sbox4[hi & 255] ^ pbox09;
         hi ^= (sbox1[lo >>> 24] + sbox2[lo >>> 16 & 255] ^ sbox3[lo >>> 8 & 255]) + sbox4[lo & 255] ^ pbox10;
         lo ^= (sbox1[hi >>> 24] + sbox2[hi >>> 16 & 255] ^ sbox3[hi >>> 8 & 255]) + sbox4[hi & 255] ^ pbox11;
         hi ^= (sbox1[lo >>> 24] + sbox2[lo >>> 16 & 255] ^ sbox3[lo >>> 8 & 255]) + sbox4[lo & 255] ^ pbox12;
         lo ^= (sbox1[hi >>> 24] + sbox2[hi >>> 16 & 255] ^ sbox3[hi >>> 8 & 255]) + sbox4[hi & 255] ^ pbox13;
         hi ^= (sbox1[lo >>> 24] + sbox2[lo >>> 16 & 255] ^ sbox3[lo >>> 8 & 255]) + sbox4[lo & 255] ^ pbox14;
         lo ^= (sbox1[hi >>> 24] + sbox2[hi >>> 16 & 255] ^ sbox3[hi >>> 8 & 255]) + sbox4[hi & 255] ^ pbox15;
         hi ^= (sbox1[lo >>> 24] + sbox2[lo >>> 16 & 255] ^ sbox3[lo >>> 8 & 255]) + sbox4[lo & 255] ^ pbox16;
         lo ^= pbox17;
         outbuf[outpos] = (byte)(lo >>> 24);
         outbuf[outpos + 1] = (byte)(lo >>> 16);
         outbuf[outpos + 2] = (byte)(lo >>> 8);
         outbuf[outpos + 3] = (byte)lo;
         outbuf[outpos + 4] = (byte)(hi >>> 24);
         outbuf[outpos + 5] = (byte)(hi >>> 16);
         outbuf[outpos + 6] = (byte)(hi >>> 8);
         outbuf[outpos + 7] = (byte)hi;
         outpos += 8;
         ivHi = lo;
      }

      this.ivHi = ivHi;
      this.ivLo = ivLo;
      return len;
   }

   public int decrypt(byte[] inbuf, int inpos, byte[] outbuf, int outpos, int len) {
      len -= len % 8;
      int c = inpos + len;
      int[] pbox = this.pbox;
      int pbox00 = pbox[0];
      int pbox01 = pbox[1];
      int pbox02 = pbox[2];
      int pbox03 = pbox[3];
      int pbox04 = pbox[4];
      int pbox05 = pbox[5];
      int pbox06 = pbox[6];
      int pbox07 = pbox[7];
      int pbox08 = pbox[8];
      int pbox09 = pbox[9];
      int pbox10 = pbox[10];
      int pbox11 = pbox[11];
      int pbox12 = pbox[12];
      int pbox13 = pbox[13];
      int pbox14 = pbox[14];
      int pbox15 = pbox[15];
      int pbox16 = pbox[16];
      int pbox17 = pbox[17];
      int[] sbox1 = this.sbox1;
      int[] sbox2 = this.sbox2;
      int[] sbox3 = this.sbox3;
      int[] sbox4 = this.sbox4;
      int ivHi = this.ivHi;

      int ivLo;
      int tmpLo;
      for(ivLo = this.ivLo; inpos < c; ivLo = tmpLo) {
         int hi = inbuf[inpos++] << 24 | inbuf[inpos++] << 16 & 16711680 | inbuf[inpos++] << 8 & '\uff00' | inbuf[inpos++] & 255;
         int lo = inbuf[inpos++] << 24 | inbuf[inpos++] << 16 & 16711680 | inbuf[inpos++] << 8 & '\uff00' | inbuf[inpos++] & 255;
         int tmpHi = hi;
         tmpLo = lo;
         hi ^= pbox17;
         lo ^= (sbox1[hi >>> 24] + sbox2[hi >>> 16 & 255] ^ sbox3[hi >>> 8 & 255]) + sbox4[hi & 255] ^ pbox16;
         hi ^= (sbox1[lo >>> 24] + sbox2[lo >>> 16 & 255] ^ sbox3[lo >>> 8 & 255]) + sbox4[lo & 255] ^ pbox15;
         lo ^= (sbox1[hi >>> 24] + sbox2[hi >>> 16 & 255] ^ sbox3[hi >>> 8 & 255]) + sbox4[hi & 255] ^ pbox14;
         hi ^= (sbox1[lo >>> 24] + sbox2[lo >>> 16 & 255] ^ sbox3[lo >>> 8 & 255]) + sbox4[lo & 255] ^ pbox13;
         lo ^= (sbox1[hi >>> 24] + sbox2[hi >>> 16 & 255] ^ sbox3[hi >>> 8 & 255]) + sbox4[hi & 255] ^ pbox12;
         hi ^= (sbox1[lo >>> 24] + sbox2[lo >>> 16 & 255] ^ sbox3[lo >>> 8 & 255]) + sbox4[lo & 255] ^ pbox11;
         lo ^= (sbox1[hi >>> 24] + sbox2[hi >>> 16 & 255] ^ sbox3[hi >>> 8 & 255]) + sbox4[hi & 255] ^ pbox10;
         hi ^= (sbox1[lo >>> 24] + sbox2[lo >>> 16 & 255] ^ sbox3[lo >>> 8 & 255]) + sbox4[lo & 255] ^ pbox09;
         lo ^= (sbox1[hi >>> 24] + sbox2[hi >>> 16 & 255] ^ sbox3[hi >>> 8 & 255]) + sbox4[hi & 255] ^ pbox08;
         hi ^= (sbox1[lo >>> 24] + sbox2[lo >>> 16 & 255] ^ sbox3[lo >>> 8 & 255]) + sbox4[lo & 255] ^ pbox07;
         lo ^= (sbox1[hi >>> 24] + sbox2[hi >>> 16 & 255] ^ sbox3[hi >>> 8 & 255]) + sbox4[hi & 255] ^ pbox06;
         hi ^= (sbox1[lo >>> 24] + sbox2[lo >>> 16 & 255] ^ sbox3[lo >>> 8 & 255]) + sbox4[lo & 255] ^ pbox05;
         lo ^= (sbox1[hi >>> 24] + sbox2[hi >>> 16 & 255] ^ sbox3[hi >>> 8 & 255]) + sbox4[hi & 255] ^ pbox04;
         hi ^= (sbox1[lo >>> 24] + sbox2[lo >>> 16 & 255] ^ sbox3[lo >>> 8 & 255]) + sbox4[lo & 255] ^ pbox03;
         lo ^= (sbox1[hi >>> 24] + sbox2[hi >>> 16 & 255] ^ sbox3[hi >>> 8 & 255]) + sbox4[hi & 255] ^ pbox02;
         hi ^= (sbox1[lo >>> 24] + sbox2[lo >>> 16 & 255] ^ sbox3[lo >>> 8 & 255]) + sbox4[lo & 255] ^ pbox01;
         lo ^= pbox00;
         hi ^= ivLo;
         lo ^= ivHi;
         outbuf[outpos++] = (byte)(lo >>> 24);
         outbuf[outpos++] = (byte)(lo >>> 16);
         outbuf[outpos++] = (byte)(lo >>> 8);
         outbuf[outpos++] = (byte)lo;
         outbuf[outpos++] = (byte)(hi >>> 24);
         outbuf[outpos++] = (byte)(hi >>> 16);
         outbuf[outpos++] = (byte)(hi >>> 8);
         outbuf[outpos++] = (byte)hi;
         ivHi = tmpHi;
      }

      this.ivHi = ivHi;
      this.ivLo = ivLo;
      return len;
   }
}
