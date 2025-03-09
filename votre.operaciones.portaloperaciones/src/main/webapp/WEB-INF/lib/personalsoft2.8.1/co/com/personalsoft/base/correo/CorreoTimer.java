package co.com.personalsoft.base.correo;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.TimerTask;
import org.apache.log4j.Logger;

public class CorreoTimer extends TimerTask implements Serializable {
   private Logger logger = Logger.getLogger(this.getClass());
   private static final long serialVersionUID = -3673403430454061009L;
   private String servidor;
   private String usuario;
   private String clave;
   private String[] destinatario;
   private String[] cc;
   private String[] cco;
   private String asunto;
   private String cuerpo;
   private String[] rutasArchivosAdjuntos;
   private Calendar fechaProgramada;

   public CorreoTimer(String servidor, String usuario, String clave, String[] destinatario, String[] cc, String[] cco, String asunto, String cuerpo, String[] rutasArchivosAdjuntos, Calendar fechaProgramada) {
      this.servidor = servidor;
      this.usuario = usuario;
      this.clave = clave;
      this.destinatario = destinatario;
      this.cc = cc;
      this.cco = cco;
      this.asunto = asunto;
      this.cuerpo = cuerpo;
      this.rutasArchivosAdjuntos = rutasArchivosAdjuntos;
      this.fechaProgramada = fechaProgramada;
   }

   public void run() {
      Correo correo = new Correo(this.servidor, this.usuario, this.clave);

      try {
         if (this.logger.isInfoEnabled()) {
            this.logger.info("Se ha iniciado la ejecución del correo:\n");
         }

         correo.enviarCorreo(this.destinatario, this.cc, this.cco, this.asunto, this.cuerpo, this.rutasArchivosAdjuntos);
      } catch (Exception var4) {
         PersonalsoftException personal = new PersonalsoftException(var4);
         this.logger.error(personal.getTraza());
      }

   }

   public String getAsunto() {
      return this.asunto;
   }

   public void setAsunto(String asunto) {
      this.asunto = asunto;
   }

   public String[] getCc() {
      return this.cc;
   }

   public void setCc(String[] cc) {
      this.cc = cc;
   }

   public String[] getCco() {
      return this.cco;
   }

   public void setCco(String[] cco) {
      this.cco = cco;
   }

   public String getClave() {
      return this.clave;
   }

   public void setClave(String clave) {
      this.clave = clave;
   }

   public String getCuerpo() {
      return this.cuerpo;
   }

   public void setCuerpo(String cuerpo) {
      this.cuerpo = cuerpo;
   }

   public String[] getDestinatario() {
      return this.destinatario;
   }

   public void setDestinatario(String[] destinatario) {
      this.destinatario = destinatario;
   }

   public Calendar getFechaProgramada() {
      return this.fechaProgramada;
   }

   public void setFechaProgramada(Calendar fechaProgramada) {
      this.fechaProgramada = fechaProgramada;
   }

   public String[] getRutasArchivosAdjuntos() {
      return this.rutasArchivosAdjuntos;
   }

   public void setRutasArchivosAdjuntos(String[] rutasArchivosAdjuntos) {
      this.rutasArchivosAdjuntos = rutasArchivosAdjuntos;
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
