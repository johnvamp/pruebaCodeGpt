package co.com.personalsoft.seguridad.tld;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.seguridad.autorizacion.impl.AutorizadorBD;
import co.com.personalsoft.seguridad.autorizacion.interf.IAutorizador;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.log4j.Logger;

public class Autorizador extends BodyTagSupport {
   private Logger log = Logger.getLogger(this.getClass());
   private String perfil;
   private String recurso;
   private static final long serialVersionUID = 7672888616195340768L;

   public String getPerfil() {
      return this.perfil;
   }

   public void setPerfil(String perfil) {
      this.perfil = perfil;
   }

   public String getRecurso() {
      return this.recurso;
   }

   public void setRecurso(String recurso) {
      this.recurso = recurso;
   }

   public int doStartTag() throws JspException {
      return super.doStartTag();
   }

   public int doAfterBody() throws JspException {
      Object var1 = null;

      try {
         IAutorizador autorizador = new AutorizadorBD();
         if (autorizador.tienePermiso(this.getPerfil(), this.getRecurso())) {
            JspWriter out = this.getPreviousOut();
            out.println(this.bodyContent.getString());
         }
      } catch (IOException var4) {
         throw new JspException(var4.getMessage());
      } catch (PersonalsoftException var5) {
         if (var5.getMensajeTecnico() != null && !var5.getMensajeTecnico().equals("")) {
            this.log.error(var5.getMensajeTecnico());
         } else if (var5.getTraza() != null && !var5.getTraza().equals("")) {
            this.log.error(var5.getTraza());
         }
      }

      return 0;
   }
}
