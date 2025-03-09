package co;

import co.com.personalsoft.base.seguridad.encripcion.MD5;

public class P {
   public static void main(String[] args) {
      MD5 m = new MD5();
      System.err.println(m.getHashString("12345"));
   }
}
