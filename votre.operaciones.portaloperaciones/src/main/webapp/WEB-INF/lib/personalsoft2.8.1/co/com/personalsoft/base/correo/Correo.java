package co.com.personalsoft.base.correo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;

public class Correo implements Serializable {
   private Logger log = Logger.getLogger(this.getClass());
   private static final long serialVersionUID = 4300500593108552791L;
   private String servidor;
   private String usuario;
   private String clave;

   public Correo(String servidor, String usuario, String clave) {
      this.servidor = servidor;
      this.usuario = usuario;
      this.clave = clave;
   }

   public void enviarCorreo(String destinatario, String asunto, String cuerpo) throws Exception {
      String[] dest = (String[])null;
      if (destinatario != null) {
         dest = new String[]{destinatario};
      }

      this.enviarCorreo(dest, (String[])null, (String[])null, asunto, cuerpo, (String[])null);
   }

   public void enviarCorreo(String destinatario, String asunto, String cuerpo, String[] rutasArchivos) throws Exception {
      String[] dest = (String[])null;
      if (destinatario != null) {
         dest = new String[]{destinatario};
      }

      this.enviarCorreo(dest, (String[])null, (String[])null, asunto, cuerpo, rutasArchivos);
   }

   public void enviarCorreoProgramado(String[] destinatario, String[] cc, String[] cco, String asunto, String cuerpo, String[] rutasArchivosAdjuntos, Calendar fechaProgramada) throws Exception {
      Timer timer = new Timer();
      CorreoTimer correoTimer = new CorreoTimer(this.servidor, this.usuario, this.clave, destinatario, cc, cco, asunto, cuerpo, rutasArchivosAdjuntos, fechaProgramada);
      timer.schedule(correoTimer, fechaProgramada.getTime());
   }

   public void enviarCorreo(String[] destinatario, String[] cc, String[] cco, String asunto, String cuerpo, String[] rutasArchivosAdjuntos, boolean html) throws Exception {
      Properties props = System.getProperties();
      props.put("mail.smtp.host", this.servidor);
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      Authenticator auth = new SMTPAuthenticator(this.usuario, this.clave);
      Session session = Session.getInstance(props, auth);
      if (this.log.isDebugEnabled()) {
         session.setDebug(true);
      }

      MimeMessage msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress(this.usuario));
      this.configurarDestinatarios(msg, destinatario);
      this.configurarCopias(msg, cc);
      this.configurarCopiasOcultas(msg, cco);
      msg.setSubject(asunto);
      BodyPart plainMessageBodyPart = null;
      Multipart multipart = new MimeMultipart("alternative");
      BodyPart htmlMessageBodyPart = null;
      if (html) {
         htmlMessageBodyPart = new MimeBodyPart();
         htmlMessageBodyPart.setContent(cuerpo, "text/html");
         multipart.addBodyPart(htmlMessageBodyPart);
      } else {
         plainMessageBodyPart = new MimeBodyPart();
         plainMessageBodyPart.setContent(cuerpo, "text/plain");
         multipart.addBodyPart(plainMessageBodyPart);
      }

      this.configurarArchivos(msg, multipart, rutasArchivosAdjuntos);
      msg.setContent(multipart);
      msg.setSentDate(new Date());
      Transport.send(msg);
   }

   public void enviarCorreo(String[] destinatarios, String[] cc, String[] cco, String asunto, String cuerpo, String[] rutasArchivosAdjuntos) throws Exception {
      this.enviarCorreo(destinatarios, cc, cco, asunto, cuerpo, rutasArchivosAdjuntos, false);
   }

   private void configurarArchivos(MimeMessage msg, Multipart mp, String[] rutasArchivosAdjuntos) throws Exception {
      if (rutasArchivosAdjuntos != null && rutasArchivosAdjuntos.length > 0) {
         String[] var7 = rutasArchivosAdjuntos;
         int var5 = 0;

         for(int var6 = rutasArchivosAdjuntos.length; var5 < var6; ++var5) {
            String archivo = var7[var5];
            MimeBodyPart mbp2 = new MimeBodyPart();
            FileDataSource fds = new FileDataSource(archivo) {
               public String getContentType() {
                  return "application/octet-stream";
               }
            };
            mbp2.setDataHandler(new DataHandler(fds));
            mbp2.setFileName(fds.getName());
            msg.saveChanges();
            mbp2.setHeader("Content-Transfer-Encoding", "base64");
            mp.addBodyPart(mbp2);
         }
      }

   }

   private void configurarDestinatarios(MimeMessage msg, String[] destinatario) throws Exception {
      if (destinatario != null && destinatario.length > 0) {
         ArrayList<InternetAddress> direcciones = new ArrayList();
         String[] var7 = destinatario;
         int var5 = 0;

         for(int var6 = destinatario.length; var5 < var6; ++var5) {
            String dest = var7[var5];
            direcciones.add(new InternetAddress(dest));
         }

         InternetAddress[] address = (InternetAddress[])direcciones.toArray(new InternetAddress[direcciones.size()]);
         msg.setRecipients(RecipientType.TO, address);
      }

   }

   private void configurarCopias(MimeMessage msg, String[] cc) throws Exception {
      if (cc != null && cc.length > 0) {
         ArrayList<InternetAddress> ccCopias = new ArrayList();
         String[] var7 = cc;
         int var5 = 0;

         for(int var6 = cc.length; var5 < var6; ++var5) {
            String dest = var7[var5];
            ccCopias.add(new InternetAddress(dest));
         }

         InternetAddress[] copias = (InternetAddress[])ccCopias.toArray(new InternetAddress[ccCopias.size()]);
         msg.setRecipients(RecipientType.CC, copias);
      }

   }

   private void configurarCopiasOcultas(MimeMessage msg, String[] cco) throws Exception {
      if (cco != null && cco.length > 0) {
         ArrayList<InternetAddress> ccCopiasOcultas = new ArrayList();
         String[] var7 = cco;
         int var5 = 0;

         for(int var6 = cco.length; var5 < var6; ++var5) {
            String dest = var7[var5];
            ccCopiasOcultas.add(new InternetAddress(dest));
         }

         InternetAddress[] copiasOcultas = (InternetAddress[])ccCopiasOcultas.toArray(new InternetAddress[ccCopiasOcultas.size()]);
         msg.setRecipients(RecipientType.BCC, copiasOcultas);
      }

   }

   public String getClave() {
      return this.clave;
   }

   public void setClave(String clave) {
      this.clave = clave;
   }

   public String getServidor() {
      return this.servidor;
   }

   public void setServidor(String servidor) {
      this.servidor = servidor;
   }

   public String getUsuario() {
      return this.usuario;
   }

   public void setUsuario(String usuario) {
      this.usuario = usuario;
   }
}
