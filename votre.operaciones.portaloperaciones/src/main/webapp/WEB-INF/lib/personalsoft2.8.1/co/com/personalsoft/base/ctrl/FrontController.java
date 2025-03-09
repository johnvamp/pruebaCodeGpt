package co.com.personalsoft.base.ctrl;

import co.com.personalsoft.base.beans.Recurso;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.error.MensajesUsr;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.interfaces.IBaseCmd;
import co.com.personalsoft.base.util.CargadorMsj;
import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class FrontController extends HttpServlet implements Servlet {
   private static final long serialVersionUID = -5070981299664800017L;
   private Logger log = Logger.getLogger(this.getClass());

   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String clave = request.getRequestURI();
      int principioRecurso = clave.lastIndexOf("/");
      int finRecurso = clave.lastIndexOf(".");
      boolean recursoInexistente = false;
      boolean comandoEjecutado = false;
      Recurso recurso = null;
      IBaseCmd comando = null;
      String recursoAceptado = "";
      String mensaje = "";
      MensajesUsr mensajesUsr = (MensajesUsr)request.getAttribute("mensajesUsr");
      if (mensajesUsr == null) {
         mensajesUsr = new MensajesUsr();
      }

      request.setAttribute("mensajesUsr", mensajesUsr);
      response.setHeader("Cache-Control", "no-cache");
      response.setHeader("Cache-Control", "no-store");
      response.setDateHeader("Expires", 0L);
      response.setHeader("Pragma", "no-cache");

      try {
         if (principioRecurso != -1 && finRecurso != -1) {
            clave = clave.substring(principioRecurso + 1, finRecurso);
            recurso = (Recurso)Configuracion.getInstance().getParametro(clave, Configuracion.getInstance().getParametro("rutaRecursosControladosCache"));
            if (recurso == null) {
               recurso = (Recurso)Configuracion.getInstance().getParametro(clave, Configuracion.getInstance().getParametro("rutaRecursosAceptadosCache"));
            }

            if (recurso != null) {
               while(true) {
                  if (recurso.isEsComando()) {
                     comando = (IBaseCmd)Class.forName(recurso.getLocalizacion()).newInstance();
                     comandoEjecutado = true;
                     comando.execute(request, response);
                  } else {
                     comandoEjecutado = true;
                     this.getServletContext().getRequestDispatcher(recurso.getLocalizacion()).forward(request, response);
                  }

                  if (recurso.getSiguiente() == null || recurso.getSiguiente().equals("")) {
                     break;
                  }

                  recursoAceptado = recurso.getSiguiente();
                  recurso = (Recurso)Configuracion.getInstance().getParametro(recurso.getSiguiente(), Configuracion.getInstance().getParametro("rutaRecursosControladosCache"));
                  if (recurso == null) {
                     recurso = (Recurso)Configuracion.getInstance().getParametro(recursoAceptado, Configuracion.getInstance().getParametro("rutaRecursosAceptadosCache"));
                  }

                  if (recurso == null) {
                     recursoInexistente = true;
                     break;
                  }
               }
            } else if (Configuracion.getInstance().getParametro(clave, Configuracion.getInstance().getParametro("rutaRecursosAceptadosCache")) == null) {
               recursoInexistente = true;
            }
         } else if (Configuracion.getInstance().getParametro(clave, Configuracion.getInstance().getParametro("rutaRecursosAceptadosCache")) == null) {
            recursoInexistente = true;
         }

         if (recursoInexistente) {
            if (request.getSession(false) != null) {
               request.getSession(false).invalidate();
            }

            mensaje = CargadorMsj.getInstance().getMensaje("recursoInexistente");
            if (mensaje == null || mensaje.equals("")) {
               mensaje = "Recurso inexistente.";
            }

            request.setAttribute("mensaje", mensaje);
            this.getServletContext().getRequestDispatcher(Configuracion.getInstance().getParametro("paginaError")).include(request, response);
         } else if (!comandoEjecutado) {
            recurso = (Recurso)Configuracion.getInstance().getParametro(clave, Configuracion.getInstance().getParametro("rutaRecursosAceptadosCache"));
            this.getServletContext().getRequestDispatcher(recurso.getLocalizacion()).include(request, response);
         }
      } catch (PersonalsoftException var17) {
         if (var17.getMensajeUsuario() != null && !var17.getMensajeUsuario().equals("")) {
            mensaje = var17.getMensajeUsuario();
         } else if (request.getAttribute("mensaje") != null) {
            mensaje = (String)request.getAttribute("mensaje");
         } else if (request.getParameter("mensaje") != null) {
            mensaje = request.getParameter("mensaje");
         } else if (var17.getMensajeTecnico() != null && !var17.getMensajeTecnico().equals("")) {
            mensaje = CargadorMsj.getInstance().getMensaje("errorGeneral");
         }

         request.setAttribute("mensaje", mensaje);
         this.log.error(var17.getExcepcionRaiz() + " - " + var17.getMessage());
         this.log.error(var17.getMensajeTecnico());
         this.log.error(var17.getTraza());
         this.getServletContext().getRequestDispatcher(Configuracion.getInstance().getParametro("paginaError")).forward(request, response);
      } catch (Exception var18) {
         Exception e = var18;
         mensaje = CargadorMsj.getInstance().getMensaje("errorGeneral");
         if (mensaje == null) {
            mensaje = "Ha ocurrido un error inesperado.";
         }

         request.setAttribute("mensaje", mensaje);
         StringBuffer strException = new StringBuffer("\n" + var18.getClass().getCanonicalName() + " - " + var18.getMessage() + "\n");

         for(int k = 0; k < e.getStackTrace().length; ++k) {
            StackTraceElement trace = e.getStackTrace()[k];
            strException.append("\t" + trace.toString() + "\n");
         }

         this.log.error(strException.toString());
         this.getServletContext().getRequestDispatcher(Configuracion.getInstance().getParametro("paginaError")).forward(request, response);
      }

   }
}
