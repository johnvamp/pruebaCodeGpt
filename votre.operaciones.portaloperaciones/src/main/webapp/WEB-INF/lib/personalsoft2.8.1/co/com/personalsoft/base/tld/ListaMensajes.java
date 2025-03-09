package co.com.personalsoft.base.tld;

import co.com.personalsoft.base.error.MensajesUsr;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class ListaMensajes extends TagSupport {
   private static final long serialVersionUID = 1L;
   protected MensajesUsr listaMensajes;

   public int doStartTag() throws JspException {
      try {
         this.pageContext.getOut().print(this.construirHTML(this.listaMensajes).toString());
         return 6;
      } catch (IOException var2) {
         throw new JspException(var2.getMessage());
      }
   }

   public StringBuilder construirHTML(MensajesUsr mensajes) {
      StringBuilder tag = new StringBuilder();
      tag.append("<table width=\"10%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table2\">");
      tag.append("<tr>");
      tag.append("\t<td align=\"center\"><a href=\"javascript:ShowHide('sub1Div','visible')\">");
      tag.append("\t<img src=\"imagenes/ico_arriba.gif\" alt=\"Ver estado actual del proceso\" width=\"16\" height=\"16\" border=\"0\"></a>");
      tag.append("\t<a href=\"javascript:ShowHide('sub1Div','hidden')\">");
      tag.append("\t<img src=\"imagenes/ico_abajo.gif\" alt=\"Ocultar estado actual del proceso\" width=\"16\" height=\"16\" border=\"0\"></a>");
      tag.append("\t</td>");
      tag.append("</tr>");
      tag.append("</table>");
      tag.append("<div id=\"sub1Div\">");
      if (mensajes != null && (mensajes.getMsgAlert().size() > 0 || mensajes.getMsgInfo().size() > 0 || mensajes.getMsgErrors().size() > 0)) {
         tag.append("<table width=\"600\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
         tag.append("<tr>");
         tag.append("<td class=\"caja01\"><img src=\"imagenes/spacer.gif\" alt=\"img\" width=\"6\" height=\"6\" /></td>");
         tag.append("<td class=\"caja02\"></td>");
         tag.append("<td class=\"caja03\"><img src=\"imagenes/spacer.gif\" alt=\"img\" width=\"6\" height=\"6\" /></td>");
         tag.append("</tr>");
         tag.append("<tr>");
         tag.append("<td class=\"caja04\"></td>");
         tag.append("<td class=\"bgcaja\">");
         tag.append("<table width=\"100%\" border=\"0\" cellpadding=\"1\" cellspacing=\"1\"  id=\"tabla\">");
         Collection listMsgErrors = mensajes.getMsgErrors();
         if (listMsgErrors != null && listMsgErrors.size() > 0) {
            Iterator var5 = listMsgErrors.iterator();

            while(var5.hasNext()) {
               Object msgError = var5.next();
               tag.append("\t\t<tr>");
               tag.append("\t\t\t<td class=\"celda01\" width=\"9%\" height=\"25\">");
               tag.append("\t\t\t<div align=\"center\">");
               tag.append("\t\t\t\t<img src=\"imagenes/ico_error.gif\" width=\"21\" height=\"22\" border=\"0\"></div>");
               tag.append("\t\t\t</td>");
               tag.append("\t\t\t<td width=\"91%\" class=\"celda01\">" + msgError.toString() + "</td>");
               tag.append("\t\t</tr>");
            }
         }

         Collection listMsgAlert = mensajes.getMsgAlert();
         if (listMsgAlert != null && listMsgAlert.size() > 0) {
            Iterator var6 = listMsgAlert.iterator();

            while(var6.hasNext()) {
               Object msgAlert = var6.next();
               tag.append("\t\t<tr>");
               tag.append("\t\t\t<td class=\"celda01\" width=\"9%\" height=\"25\">");
               tag.append("\t\t\t<div align=\"center\">");
               tag.append("\t\t\t\t<img src=\"imagenes/ico_alerta_48x48.gif\" width=\"21\" height=\"22\" border=\"0\"></div>");
               tag.append("\t\t\t</td>");
               tag.append("\t\t\t<td width=\"91%\" class=\"celda01\">" + msgAlert + "</td>");
               tag.append("\t\t</tr>");
            }
         }

         Collection listMsgInfo = mensajes.getMsgInfo();
         if (listMsgInfo != null && listMsgInfo.size() > 0) {
            Iterator var7 = listMsgInfo.iterator();

            while(var7.hasNext()) {
               Object msgInfo = var7.next();
               tag.append("\t\t<tr>");
               tag.append("\t\t\t<td class=\"celda01\" width=\"9%\" height=\"25\">");
               tag.append("\t\t\t<div align=\"center\">");
               tag.append("\t\t\t<img src=\"imagenes/ico_comentario.gif\" width=\"21\" height=\"22\" border=\"0\"></div>");
               tag.append("\t\t\t</td>");
               tag.append("\t\t\t<td width=\"91%\" class=\"celda01\">" + msgInfo + "</td>");
               tag.append("\t\t</tr>");
            }
         }

         tag.append("</table>");
         tag.append("</td>");
         tag.append("<td class=\"caja05\"></td>");
         tag.append("</tr>");
         tag.append("<tr>");
         tag.append("<td class=\"caja06\"></td>");
         tag.append("<td class=\"caja07\"></td>");
         tag.append("<td class=\"caja08\"></td>");
         tag.append("</tr>");
         tag.append("</table>");
         tag.append("</div>");
      }

      return tag;
   }

   public MensajesUsr getListaMensajes() {
      return this.listaMensajes;
   }

   public void setListaMensajes(MensajesUsr listaMensajes) {
      this.listaMensajes = listaMensajes;
   }
}
