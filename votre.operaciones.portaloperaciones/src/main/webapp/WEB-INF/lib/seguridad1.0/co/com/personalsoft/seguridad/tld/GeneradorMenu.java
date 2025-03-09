package co.com.personalsoft.seguridad.tld;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.seguridad.beans.OpcionMenuDTO;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class GeneradorMenu extends BodyTagSupport {
   private static final long serialVersionUID = 1L;
   private String menuXml;

   public int doStartTag() throws JspException {
      try {
         ParserMenu parserMenu = new ParserMenu();
         if (this.menuXml != null && !this.menuXml.equals("")) {
            Set<OpcionMenuDTO> opciones = parserMenu.getOpciones(this.menuXml);
            Iterator var4 = opciones.iterator();

            while(var4.hasNext()) {
               OpcionMenuDTO menuDTO = (OpcionMenuDTO)var4.next();
               this.pageContext.getOut().print(this.pintarOpcion(menuDTO));
            }
         }
      } catch (IOException var6) {
         throw new JspException(var6.getMessage());
      } catch (PersonalsoftException var7) {
         PersonalsoftException e = var7;

         try {
            throw e;
         } catch (PersonalsoftException var5) {
         }
      }

      return 0;
   }

   private String pintarOpcion(OpcionMenuDTO opcionMenuDTO) {
      StringBuilder html = new StringBuilder();
      OpcionMenuDTO hija;
      Iterator var4;
      if (opcionMenuDTO.getNivel() == 1) {
         if (opcionMenuDTO.getTieneHijos().trim().equals("S")) {
            html.append("<div class=\"menuheader expandable\">" + opcionMenuDTO.getNombreOpcion() + "</div>");
            html.append("<ul class=\"categoryitems\">");
            if (opcionMenuDTO.getListaOpcionesHijas() != null && !opcionMenuDTO.getListaOpcionesHijas().isEmpty()) {
               var4 = opcionMenuDTO.getListaOpcionesHijas().iterator();

               while(var4.hasNext()) {
                  hija = (OpcionMenuDTO)var4.next();
                  html.append(this.pintarOpcion(hija));
               }
            }

            html.append("</ul>");
         } else {
            html.append("<div class=\"menuheader Noexpandable\"><a ");
            html.append("href=\"" + opcionMenuDTO.getUrl() + "\" ");
            html.append("target=\"" + opcionMenuDTO.getTarget() + "\" ");
            html.append("> ");
            html.append(opcionMenuDTO.getNombreOpcion());
            html.append("</a></div>");
         }
      } else if (opcionMenuDTO.getTieneHijos().trim().equals("S")) {
         if (opcionMenuDTO.getNivel() == 2) {
            html.append("<li>");
            html.append("<a class=\"subexpandable\" ");
            if (!opcionMenuDTO.getUrl().equals("") && !opcionMenuDTO.getUrl().equals("#")) {
               html.append("href=\"" + opcionMenuDTO.getUrl() + "\" ");
               html.append("target=\"" + opcionMenuDTO.getTarget() + "\" ");
            }

            html.append("> ");
            html.append(opcionMenuDTO.getNombreOpcion());
            html.append("</a>");
            html.append("<ul class=\"subcategoryitems\">");
            if (opcionMenuDTO.getListaOpcionesHijas() != null && !opcionMenuDTO.getListaOpcionesHijas().isEmpty()) {
               var4 = opcionMenuDTO.getListaOpcionesHijas().iterator();

               while(var4.hasNext()) {
                  hija = (OpcionMenuDTO)var4.next();
                  html.append(this.pintarOpcion(hija));
               }
            }

            html.append("</ul>");
            html.append("</li>");
         }

         if (opcionMenuDTO.getNivel() == 3) {
            html.append("<li>");
            html.append("<a class=\"sub2expandable\" ");
            if (!opcionMenuDTO.getUrl().equals("") && !opcionMenuDTO.getUrl().equals("#")) {
               html.append("href=\"" + opcionMenuDTO.getUrl() + "\" ");
               html.append("target=\"" + opcionMenuDTO.getTarget() + "\" ");
            }

            html.append("> ");
            html.append(opcionMenuDTO.getNombreOpcion());
            html.append("</a>");
            html.append("<ul class=\"sub2categoryitems\">");
            if (opcionMenuDTO.getListaOpcionesHijas() != null && !opcionMenuDTO.getListaOpcionesHijas().isEmpty()) {
               var4 = opcionMenuDTO.getListaOpcionesHijas().iterator();

               while(var4.hasNext()) {
                  hija = (OpcionMenuDTO)var4.next();
                  html.append(this.pintarOpcion(hija));
               }
            }

            html.append("</ul>");
            html.append("</li>");
         }
      } else {
         html.append("<li><a ");
         html.append("href=\"" + opcionMenuDTO.getUrl() + "\" ");
         html.append("target=\"" + opcionMenuDTO.getTarget() + "\" ");
         html.append("> ");
         html.append(opcionMenuDTO.getNombreOpcion());
         html.append("</a></li>");
      }

      return html.toString();
   }

   public String getMenuXml() {
      return this.menuXml;
   }

   public void setMenuXml(String menuXml) {
      this.menuXml = menuXml;
   }
}
