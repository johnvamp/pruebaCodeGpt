package co.com.personalsoft.base.correo;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

class SMTPAuthenticator extends Authenticator {
   private String usuario;
   private String clave;

   public SMTPAuthenticator(String usuario, String clave) {
      this.usuario = usuario;
      this.clave = clave;
   }

   public PasswordAuthentication getPasswordAuthentication() {
      String username = this.usuario;
      String password = this.clave;
      return new PasswordAuthentication(username, password);
   }
}
