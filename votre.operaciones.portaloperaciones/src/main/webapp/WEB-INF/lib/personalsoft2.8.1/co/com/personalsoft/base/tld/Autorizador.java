package co.com.personalsoft.base.tld;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class Autorizador extends BodyTagSupport {
   private String perfiles;
   private String claseSeguridad;
   private static final long serialVersionUID = 7672888616195340768L;

   public String getPerfiles() {
      return this.perfiles;
   }

   public void setPerfiles(String permiso) {
      this.perfiles = permiso;
   }

   public String getClaseSeguridad() {
      return this.claseSeguridad;
   }

   public void setClaseSeguridad(String claseSeguridad) {
      this.claseSeguridad = claseSeguridad;
   }

   public int doStartTag() throws JspException {
      return super.doStartTag();
   }

   public int doAfterBody() throws JspException {
      try {
         HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
         IAutorizadorHelper autorizadorHelper = (IAutorizadorHelper)Class.forName(this.getClaseSeguridad()).newInstance();
         if (autorizadorHelper != null && autorizadorHelper.tienePermiso(request, this.getPerfiles())) {
            JspWriter out = this.getPreviousOut();
            out.println(this.bodyContent.getString());
         }

         return 0;
      } catch (IOException var4) {
         throw new JspException(var4.getMessage());
      } catch (InstantiationException var5) {
         throw new JspException(var5.getMessage());
      } catch (IllegalAccessException var6) {
         throw new JspException(var6.getMessage());
      } catch (ClassNotFoundException var7) {
         throw new JspException(var7.getMessage());
      }
   }
}
