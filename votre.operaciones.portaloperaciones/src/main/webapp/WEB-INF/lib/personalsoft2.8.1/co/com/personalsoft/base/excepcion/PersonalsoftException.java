package co.com.personalsoft.base.excepcion;

import co.com.personalsoft.base.beans.Parametro;
import java.io.Serializable;
import java.util.ArrayList;
import org.apache.log4j.Logger;

public class PersonalsoftException extends Exception implements Serializable {
   private Logger log = Logger.getLogger(this.getClass());
   private String mensajeTecnico;
   private String mensajeUsuario;
   private String excepcionRaiz;
   private String traza;
   private ArrayList parametros;
   private Exception exception;
   private static final long serialVersionUID = 3389740187586422932L;

   public PersonalsoftException() {
   }

   public PersonalsoftException(String mensaje, String mensajeTecnico, String mensajeUsuario, String exceptionRaiz, ArrayList parametros, Exception exception) {
      super(mensaje);
      this.mensajeTecnico = mensajeTecnico;
      this.mensajeUsuario = mensajeUsuario;
      this.excepcionRaiz = exceptionRaiz;
      this.parametros = parametros;
      this.exception = exception;
      this.init();
   }

   public PersonalsoftException(String mensaje, ArrayList parametros) {
      super(mensaje);
      this.traza = this.getMessage();
      this.parametros = parametros;
      this.init();
   }

   public PersonalsoftException(String mensaje, Exception e) {
      super(mensaje);
      this.exception = e;
      this.init();
   }

   public PersonalsoftException(String mensaje, String exceptionRaiz, ArrayList parametros) {
      super(mensaje);
      this.excepcionRaiz = exceptionRaiz;
      this.parametros = parametros;
      this.init();
   }

   public PersonalsoftException(Exception exception, ArrayList parametros) {
      this.exception = exception;
      this.parametros = parametros;
      this.init();
   }

   public PersonalsoftException(Exception exception) {
      this.exception = exception;
      this.init();
   }

   public PersonalsoftException(String mensaje) {
      super(mensaje);
      this.traza = this.getMessage();
      this.init();
   }

   public PersonalsoftException(String mensajeTecnico, ArrayList parametros, Exception exception) {
      this.mensajeTecnico = mensajeTecnico;
      this.exception = exception;
      this.parametros = parametros;
      this.init();
   }

   private void init() {
      if (this.exception != null) {
         StringBuffer strException = new StringBuffer("\n" + this.exception.getClass().getCanonicalName() + " - " + this.exception.getMessage() + "\n");

         for(int k = 0; k < this.exception.getStackTrace().length; ++k) {
            StackTraceElement trace = this.exception.getStackTrace()[k];
            strException.append("\t" + trace.toString() + "\n");
         }

         this.traza = strException.toString();
         strException = null;
         if (this.traza.endsWith("\n")) {
            this.traza = this.traza.substring(0, this.traza.length() - 1);
         }

         this.excepcionRaiz = this.exception.getClass().getCanonicalName();
      }

      if (this.log.isDebugEnabled() && this.parametros != null && this.parametros.size() > 0) {
         for(int k = 0; k < this.parametros.size(); ++k) {
            Parametro paramDebug = (Parametro)this.parametros.get(k);
            this.log.debug("[Parametro: " + paramDebug.getNombreParametro() + " Tipo: " + paramDebug.getTipoParametro() + " Valor: " + paramDebug.getValorParametro().toString() + "]\n");
         }
      }

   }

   public String getExcepcionRaiz() {
      return this.excepcionRaiz;
   }

   public void setExcepcionRaiz(String excepcionRaiz) {
      this.excepcionRaiz = excepcionRaiz;
   }

   public String getMensajeTecnico() {
      return this.mensajeTecnico;
   }

   public void setMensajeTecnico(String mensajeTecnico) {
      this.mensajeTecnico = mensajeTecnico;
   }

   public String getMensajeUsuario() {
      return this.mensajeUsuario;
   }

   public void setMensajeUsuario(String mensajeUsuario) {
      this.mensajeUsuario = mensajeUsuario;
   }

   public String getTraza() {
      return this.traza;
   }

   public void setTraza(String traza) {
      this.traza = traza;
   }

   public Exception getException() {
      return this.exception;
   }

   public void setException(Exception exception) {
      this.exception = exception;
   }

   public ArrayList getParametros() {
      return this.parametros;
   }

   public void setParametros(ArrayList parametros) {
      this.parametros = parametros;
   }
}
