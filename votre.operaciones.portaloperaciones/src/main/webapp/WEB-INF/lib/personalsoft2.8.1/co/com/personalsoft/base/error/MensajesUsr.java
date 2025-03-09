package co.com.personalsoft.base.error;

import java.util.ArrayList;
import java.util.Collection;

public class MensajesUsr {
   private Collection<String> msgError = new ArrayList();
   private Collection<String> msgWarning = new ArrayList();
   private Collection<String> msgInfo = new ArrayList();
   private Collection<String> msgAlert = new ArrayList();

   public void addError(String msg) {
      this.msgError.add(msg);
   }

   public void addWarning(String msg) {
      this.msgWarning.add(msg);
   }

   public void addInfo(String msg) {
      this.msgInfo.add(msg);
   }

   public void addAlert(String msg) {
      this.msgAlert.add(msg);
   }

   public Collection getMsgErrors() {
      return this.msgError;
   }

   public Collection getMsgWarning() {
      return this.msgWarning;
   }

   public Collection getMsgInfo() {
      return this.msgInfo;
   }

   public Collection getMsgAlert() {
      return this.msgAlert;
   }
}
