package co.com.personalsoft.base.bd;

import co.com.personalsoft.base.beans.Mensaje;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MensajesHelper {
   private BDHelper bdHelper;
   private boolean ejecucionPorQuerys;

   public MensajesHelper() {
      this.bdHelper = null;
      String tipoRecurso = Configuracion.getInstance().getParametro("tipoRecursosBDFramework");
      this.ejecucionPorQuerys = tipoRecurso != null && tipoRecurso.equals("SQL");
   }

   public MensajesHelper(BDHelper helper) {
      this();
      this.bdHelper = helper;
   }

   public String cargarMensaje(Mensaje mensaje) throws PersonalsoftException {
      String rutaQueryMensaje = Configuracion.getInstance().getParametro("queryMensaje");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ResultSet rs = null;
      String valor = "";
      ArrayList<Parametro> parametros = new ArrayList();
      String idiomaMensaje = this.establecerIdiomaBusquedaMensaje(mensaje);
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("CLAVE", 12, mensaje.getClave()));
               parametros.add(new Parametro("IDIOMA", 12, idiomaMensaje));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
                  if (this.bdHelper.isMySql()) {
                     this.bdHelper.commitTransaction();
                  }
               }
            } else {
               parametros.add(new Parametro(12, mensaje.getClave(), 1));
               parametros.add(new Parametro(12, mensaje.getIdioma().getLanguage(), 1));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  rs = cs.getResultSet();
               }
            }

            if (rs != null && rs.next()) {
               valor = rs.getString("VALOR") != null ? rs.getString("VALOR").trim() : null;
            }
         } catch (Exception var17) {
            var17.printStackTrace();
            throw new PersonalsoftException(var17);
         } finally {
            try {
               BDHelper.close(cs);
               BDHelper.close(ps);
               BDHelper.close(rs);
               if (this.bdHelper != null) {
                  this.bdHelper.cerrarConexion();
               }
            } catch (Exception var16) {
               new PersonalsoftException(var16);
            }

         }
      }

      if (valor != null && valor.trim().equals("")) {
         valor = null;
      }

      return valor;
   }

   private String establecerIdiomaBusquedaMensaje(Mensaje mensaje) {
      HashMap<String, String> idiomasDisponiblesMap = Configuracion.getInstance().obtenerIdiomasDisponiblesApp();
      String idiomaPorDefecto = Configuracion.getInstance().getParametro("idiomaPorDefecto");
      String idiomaMensaje = "";
      if (mensaje.getIdioma() == null) {
         idiomaMensaje = idiomaPorDefecto;
      } else {
         idiomaMensaje = mensaje.getIdioma().getLanguage().toLowerCase() + "_" + mensaje.getIdioma().getCountry().toUpperCase();
         if (!idiomasDisponiblesMap.containsKey(idiomaMensaje)) {
            idiomaMensaje = this.buscarIdiomaMasApropiado(idiomasDisponiblesMap, mensaje);
            if (idiomaMensaje == null) {
               idiomaMensaje = idiomaPorDefecto;
            }
         }
      }

      return idiomaMensaje;
   }

   private String buscarIdiomaMasApropiado(HashMap<String, String> idiomasDisponiblesMap, Mensaje mensaje) {
      String[] idiomaPais = (String[])null;
      Set<String> claves = idiomasDisponiblesMap.keySet();
      String idiomaMensajeEntrante = mensaje.getIdioma().getLanguage();
      Iterator iter = claves.iterator();

      while(iter.hasNext()) {
         String cadena = (String)iter.next();
         idiomaPais = cadena.split("_");
         if (idiomaMensajeEntrante.equalsIgnoreCase(idiomaPais[0])) {
            return cadena;
         }
      }

      return null;
   }

   public String[] cargarTodosMensajes() throws PersonalsoftException {
      String rutaQueryMensaje = Configuracion.getInstance().getParametro("queryMensajes");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ResultSet rs = null;
      String valor = "";
      ArrayList<String> mensajes = new ArrayList();
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         try {
            if (this.ejecucionPorQuerys) {
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje);
               if (ps != null) {
                  rs = ps.executeQuery();
                  if (this.bdHelper.isMySql()) {
                     this.bdHelper.commitTransaction();
                  }
               }
            } else {
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje);
               if (cs != null) {
                  cs.execute();
                  rs = cs.getResultSet();
               }
            }

            if (rs != null) {
               while(rs.next()) {
                  if (valor != null && valor.trim().equals("")) {
                     mensajes.add(rs.getString("CLAVE") != null ? rs.getString("CLAVE").trim() : "");
                     mensajes.add(rs.getString("VALOR") != null ? rs.getString("VALOR").trim() : "");
                     mensajes.add(rs.getString("IDIOMA") != null ? rs.getString("IDIOMA").trim() : "");
                  }
               }
            }
         } catch (Exception var15) {
            var15.printStackTrace();
            throw new PersonalsoftException(var15);
         } finally {
            try {
               BDHelper.close(cs);
               BDHelper.close(ps);
               BDHelper.close(rs);
               if (this.bdHelper != null) {
                  this.bdHelper.cerrarConexion();
               }
            } catch (Exception var14) {
               new PersonalsoftException(var14);
            }

         }
      }

      return (String[])mensajes.toArray(new String[mensajes.size()]);
   }

   public void ingresarMensaje(Mensaje mensaje) throws PersonalsoftException {
      String rutaQueryMensaje = Configuracion.getInstance().getParametro("insertMensaje");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ArrayList<Parametro> parametros = new ArrayList();
      String idiomaMensaje = "";
      if (mensaje.getIdioma() == null) {
         idiomaMensaje = Configuracion.getInstance().getParametro("idiomaPorDefecto");
      } else {
         idiomaMensaje = mensaje.getIdioma().getLanguage();
      }

      try {
         if (this.ejecucionPorQuerys) {
            parametros.add(new Parametro("CLAVE", 12, mensaje.getClave()));
            parametros.add(new Parametro("VALOR", 12, mensaje.getValor()));
            parametros.add(new Parametro("IDIOMA", 12, idiomaMensaje));
            ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
            if (ps != null) {
               ps.executeUpdate();
            }
         } else {
            parametros.add(new Parametro(12, mensaje.getClave(), 1));
            parametros.add(new Parametro(12, mensaje.getValor(), 1));
            parametros.add(new Parametro(12, mensaje.getIdioma().getLanguage(), 1));
            cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
            if (cs != null) {
               cs.execute();
            }
         }
      } catch (Exception var15) {
         var15.printStackTrace();
         throw new PersonalsoftException(var15);
      } finally {
         try {
            BDHelper.close(cs);
            BDHelper.close(ps);
         } catch (Exception var14) {
            throw new PersonalsoftException(var14);
         }
      }

   }

   public void actualizarMensaje(Mensaje mensaje) throws PersonalsoftException {
      String rutaQueryMensaje = Configuracion.getInstance().getParametro("updateMensaje");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ArrayList<Parametro> parametros = new ArrayList();
      String idiomaMensaje = "";
      if (mensaje.getIdioma() == null) {
         idiomaMensaje = Configuracion.getInstance().getParametro("idiomaPorDefecto");
      } else {
         idiomaMensaje = mensaje.getIdioma().getLanguage();
      }

      try {
         if (this.ejecucionPorQuerys) {
            parametros.add(new Parametro("CLAVE", 12, mensaje.getClave()));
            parametros.add(new Parametro("VALOR", 12, mensaje.getValor()));
            parametros.add(new Parametro("IDIOMA", 12, idiomaMensaje));
            ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
            if (ps != null) {
               ps.executeUpdate();
            }
         } else {
            parametros.add(new Parametro(12, mensaje.getClave(), 1));
            parametros.add(new Parametro(12, mensaje.getValor(), 1));
            parametros.add(new Parametro(12, idiomaMensaje, 1));
            cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
            if (cs != null) {
               cs.execute();
            }
         }
      } catch (Exception var15) {
         var15.printStackTrace();
         throw new PersonalsoftException(var15);
      } finally {
         try {
            BDHelper.close(cs);
            BDHelper.close(ps);
         } catch (Exception var14) {
            throw new PersonalsoftException(var14);
         }
      }

   }

   public void borrarMensaje(Mensaje mensaje) throws PersonalsoftException {
      String rutaQueryMensaje = Configuracion.getInstance().getParametro("deleteMensaje");
      PreparedStatement ps = null;
      CallableStatement cs = null;
      ArrayList<Parametro> parametros = new ArrayList();
      String idiomaMensaje = "";
      if (mensaje.getIdioma() == null) {
         idiomaMensaje = Configuracion.getInstance().getParametro("idiomaPorDefecto");
      } else {
         idiomaMensaje = mensaje.getIdioma().getLanguage();
      }

      try {
         if (this.ejecucionPorQuerys) {
            parametros.add(new Parametro("CLAVE", 12, mensaje.getClave()));
            parametros.add(new Parametro("IDIOMA", 12, idiomaMensaje));
            ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
            if (ps != null) {
               ps.executeUpdate();
            }
         } else {
            parametros.add(new Parametro(12, mensaje.getClave(), 1));
            parametros.add(new Parametro(12, mensaje.getIdioma().getLanguage(), 1));
            cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
            if (cs != null) {
               cs.execute();
            }
         }
      } catch (Exception var15) {
         var15.printStackTrace();
         throw new PersonalsoftException(var15);
      } finally {
         try {
            BDHelper.close(cs);
            BDHelper.close(ps);
         } catch (Exception var14) {
            throw new PersonalsoftException(var14);
         }
      }

   }
}
