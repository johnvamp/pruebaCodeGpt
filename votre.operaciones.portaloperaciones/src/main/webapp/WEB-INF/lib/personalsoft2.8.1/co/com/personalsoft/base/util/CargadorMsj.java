package co.com.personalsoft.base.util;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.bd.MensajesHelper;
import co.com.personalsoft.base.beans.Item;
import co.com.personalsoft.base.beans.Mensaje;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import org.apache.log4j.Logger;

public class CargadorMsj {
   private Logger log = Logger.getLogger(this.getClass());
   private static CargadorMsj cargadorMsj;

   private CargadorMsj() {
   }

   public static CargadorMsj getInstance() {
      if (cargadorMsj == null) {
         cargadorMsj = new CargadorMsj();
      }

      return cargadorMsj;
   }

   public String getMensaje(String claveMensaje) {
      return this.getMensaje(new Mensaje(claveMensaje));
   }

   public String getMensaje(String claveMensaje, Locale localizacion) {
      return this.getMensaje(new Mensaje(claveMensaje, "", localizacion));
   }

   public String getMensaje(Mensaje mensaje) {
      String mensajeTexto = Configuracion.getInstance().getMensaje(mensaje.getClavePrimaria());
      BDHelper helper = null;
      MensajesHelper mensajesHelper = null;
      String habilitarCacheMensajesBD = Configuracion.getInstance().getParametro("habilitarCacheMensajesBD");
      if ((habilitarCacheMensajesBD == null || habilitarCacheMensajesBD.equals("S")) && mensajeTexto == null) {
         try {
            helper = new BDHelper();
         } catch (PersonalsoftException var8) {
            this.log.error(var8.getTraza());
         }

         mensajesHelper = new MensajesHelper(helper);

         try {
            mensajeTexto = mensajesHelper.cargarMensaje(mensaje);
            mensaje.setValor(mensajeTexto);
            if (mensajeTexto != null) {
               Configuracion.getInstance().putMensaje(mensaje);
            } else {
               mensajeTexto = "El mensaje " + mensaje.getClave() + " no se encuentra disponible.";
            }
         } catch (PersonalsoftException var7) {
            this.log.error(var7.getTraza());
         }
      }

      return mensajeTexto;
   }

   public String getMensaje(String claveMensaje, Collection<Item> parametros) {
      return this.getMensaje(new Mensaje(claveMensaje), parametros);
   }

   public String getMensaje(String claveMensaje, Collection<Item> parametros, Locale localizacion) {
      return this.getMensaje(new Mensaje(claveMensaje, "", localizacion), parametros);
   }

   public String getMensaje(Mensaje mensaje, Collection<Item> parametros) {
      ArrayList<Item> parametrosTmp = new ArrayList(parametros);
      String mensajeConsultado = this.getMensaje(mensaje);
      Item item = null;

      for(int i = 0; i < parametros.size(); ++i) {
         item = (Item)parametrosTmp.get(i);
         if (item != null && item.getDescripcion() != null) {
            mensajeConsultado = mensajeConsultado.replaceAll(":" + item.getCodigo(), item.getDescripcion());
         }
      }

      return mensajeConsultado;
   }

   public String getMensaje(String claveMensaje, Item parametro) {
      return this.getMensaje(new Mensaje(claveMensaje), parametro);
   }

   public String getMensaje(String claveMensaje, Item parametro, Locale localizacion) {
      return this.getMensaje(new Mensaje(claveMensaje, "", localizacion), parametro);
   }

   public String getMensaje(Mensaje mensaje, Item parametro) {
      ArrayList<Item> parametrosTmp = new ArrayList();
      parametrosTmp.add(parametro);
      return this.getMensaje((Mensaje)mensaje, (Collection)parametrosTmp);
   }

   public String[] cargarTodosMensajes() throws Exception {
      BDHelper helper = null;
      MensajesHelper mensajesHelper = null;
      String[] mensajes = (String[])null;

      try {
         helper = new BDHelper();
         mensajesHelper = new MensajesHelper(helper);
         mensajes = mensajesHelper.cargarTodosMensajes();
         return mensajes;
      } catch (PersonalsoftException var5) {
         this.log.error(var5.getTraza());
         throw new Exception(var5.getException());
      }
   }

   public void ingresarMensajeBD(Mensaje mensaje) throws Exception {
      BDHelper helper = null;
      MensajesHelper mensajesHelper = null;

      try {
         helper = new BDHelper();
         mensajesHelper = new MensajesHelper(helper);
         mensajesHelper.ingresarMensaje(mensaje);
         Configuracion.getInstance().putMensaje(mensaje);
         helper.commitTransaction();
      } catch (PersonalsoftException var9) {
         helper.rollbackTransaction();
         this.log.error(var9.getTraza());
         throw new Exception(var9.getException());
      } catch (Exception var10) {
         helper.rollbackTransaction();
         this.log.error(var10);
         throw var10;
      } finally {
         if (helper != null) {
            helper.cerrarConexion();
         }

      }

   }

   public void actualizarMensajeBD(Mensaje mensaje) throws Exception {
      BDHelper helper = null;
      MensajesHelper mensajesHelper = null;

      try {
         helper = new BDHelper();
         mensajesHelper = new MensajesHelper(helper);
         mensajesHelper.actualizarMensaje(mensaje);
         Configuracion.getInstance().putMensaje(mensaje);
         helper.commitTransaction();
      } catch (PersonalsoftException var9) {
         helper.rollbackTransaction();
         this.log.error(var9.getTraza());
         throw new Exception(var9.getException());
      } catch (Exception var10) {
         helper.rollbackTransaction();
         this.log.error(var10);
         throw var10;
      } finally {
         if (helper != null) {
            helper.cerrarConexion();
         }

      }

   }

   public void borrarMensajeBD(Mensaje mensaje) throws Exception {
      BDHelper helper = null;
      MensajesHelper mensajesHelper = null;

      try {
         helper = new BDHelper();
         mensajesHelper = new MensajesHelper(helper);
         mensajesHelper.borrarMensaje(mensaje);
         Configuracion.getInstance().eliminarMensageCache(mensaje);
         helper.commitTransaction();
      } catch (PersonalsoftException var9) {
         helper.rollbackTransaction();
         this.log.error(var9.getTraza());
         throw new Exception(var9.getException());
      } catch (Exception var10) {
         helper.rollbackTransaction();
         this.log.error(var10);
         throw var10;
      } finally {
         if (helper != null) {
            helper.cerrarConexion();
         }

      }

   }
}
