package co.com.personalsoft.base.seguridad.encripcion;

public class Blowfish {
   public String encriptar(String texto, String semilla) throws Exception {
      BlowfishSimple bfes = new BlowfishSimple(semilla.toCharArray());
      String encriptado = bfes.encryptString(texto);
      return encriptado;
   }

   public String desencriptar(String texto, String semilla) throws Exception {
      BlowfishSimple bfes = new BlowfishSimple(semilla.toCharArray());
      String desencriptado = bfes.decryptString(texto);
      return desencriptado;
   }
}
