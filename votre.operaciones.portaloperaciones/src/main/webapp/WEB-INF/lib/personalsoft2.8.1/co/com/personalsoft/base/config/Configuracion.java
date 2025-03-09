package co.com.personalsoft.base.config;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.bd.ConfiguracionHelper;
import co.com.personalsoft.base.beans.Mensaje;
import co.com.personalsoft.base.beans.Recurso;
import co.com.personalsoft.base.beans.Recursos;
import co.com.personalsoft.base.beans.Servicio;
import co.com.personalsoft.base.beans.Servicios;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.navegacion.CargadorXMLHelper;
import co.com.personalsoft.base.navegacion.CargadorXMLServiciosHelper;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.ServletContext;
import net.jcip.annotations.ThreadSafe;
import org.apache.log4j.Logger;
import org.jboss.cache.Cache;
import org.jboss.cache.CacheFactory;
import org.jboss.cache.DefaultCacheFactory;
import org.jboss.cache.Fqn;
import org.jboss.cache.Node;

@ThreadSafe
public class Configuracion implements Serializable {
   private static final long serialVersionUID = 647351829391455415L;
   private Logger log = Logger.getLogger(this.getClass());
   private static Configuracion configuracion;
   private Node<Object, Object> rootNode = null;
   private ServletContext context = null;
   private String nombreJndi;
   private final String rutaRecursosAceptadosCache = "/aceptados";
   private final String rutaRecursosControladosCache = "/controlados";
   private final String rutaRecursosCache = "/co/com/personalsoft/cache/recursos";
   private final String rutaMensajesCache = "/co/com/personalsoft/cache/msg";
   private final String rutaConfiguracionCache = "/co/com/personalsoft/cache/configuracion";
   private final String rutaServiciosCache = "/co/com/personalsoft/cache/servicios";
   private final String rutaQuerysCache = "/co/com/personalsoft/cache/query";

   private Configuracion() {
   }

   public static Configuracion getInstance() {
      if (configuracion == null) {
         configuracion = new Configuracion();
      }

      return configuracion;
   }

   public Node<Object, Object> getRootNode() {
      return this.rootNode;
   }

   public void setRootNode(Node<Object, Object> rootNode) {
      this.rootNode = rootNode;
   }

   public ServletContext getContext() {
      return this.context;
   }

   public String getParametro(String nombreParametro) {
      String parametro = this.context.getInitParameter(nombreParametro);
      if (parametro == null) {
         parametro = this.getParametroApp(nombreParametro);
      }

      return parametro;
   }

   public void setContext(ServletContext context) {
      this.context = context;
   }

   public void initCache() {
      String rutaArchivoConfigCache = this.getParametro("archivoConfiguracionCache");
      if (rutaArchivoConfigCache != null && !rutaArchivoConfigCache.equals("")) {
         InputStream inputStr = this.context.getResourceAsStream(rutaArchivoConfigCache);

         try {
            this.init(inputStr);
         } catch (SQLException var5) {
            PersonalsoftException personalE = new PersonalsoftException(var5);
            this.log.error(personalE.getTraza());
         } catch (PersonalsoftException var6) {
            this.log.error(var6.getTraza());
         }
      } else {
         this.log.error("No se encontró el parámetro de configuración que indica la ruta de configuración del cache. Por lo tanto el caché no podrá ser utilizado.");
      }

   }

   private void init(InputStream inputStr) throws SQLException, PersonalsoftException {
      Fqn<String> queryRecords = null;
      Fqn<String> configuracionRecords = null;
      Fqn<String> recursosRecords = null;
      Fqn<String> recursosAceptadosRecords = null;
      Fqn<String> recursosControladosRecords = null;
      Node<Object, Object> recursosRecordsNode = null;
      Fqn<String> serviciosRecords = null;
      Fqn<String> msgRecords = null;
      CargadorXMLConfigFW cargadorXMLConfigFW = new CargadorXMLConfigFW();
      Cache cache = null;
      if (inputStr != null) {
         CacheFactory factory = new DefaultCacheFactory();
         cache = factory.createCache(inputStr, false);
         cache.create();
         cache.start();
         queryRecords = Fqn.fromString("/co/com/personalsoft/cache/query");
         configuracionRecords = Fqn.fromString("/co/com/personalsoft/cache/configuracion");
         recursosRecords = Fqn.fromString("/co/com/personalsoft/cache/recursos");
         recursosAceptadosRecords = Fqn.fromString("/aceptados");
         recursosControladosRecords = Fqn.fromString("/controlados");
         serviciosRecords = Fqn.fromString("/co/com/personalsoft/cache/servicios");
         msgRecords = Fqn.fromString("/co/com/personalsoft/cache/msg");
         this.rootNode = cache.getRoot();
         this.rootNode.addChild(queryRecords);
         this.rootNode.addChild(configuracionRecords);
         recursosRecordsNode = this.rootNode.addChild(recursosRecords);
         recursosRecordsNode.addChild(recursosAceptadosRecords);
         recursosRecordsNode.addChild(recursosControladosRecords);
         this.rootNode.addChild(serviciosRecords);
         this.rootNode.addChild(msgRecords);

         try {
            cargadorXMLConfigFW.parseDocument();
         } catch (Exception var14) {
            throw new PersonalsoftException(var14);
         }

         this.putRecursos();
         this.putServicios();
         this.putParametrosApp();
         this.putParametroApp("rutaQuerysCache", "/co/com/personalsoft/cache/query");
         this.putParametroApp("rutaConfiguracionCache", "/co/com/personalsoft/cache/configuracion");
         this.putParametroApp("rutaRecursosCache", "/co/com/personalsoft/cache/recursos");
         this.putParametroApp("rutaRecursosAceptadosCache", "/aceptados");
         this.putParametroApp("rutaRecursosControladosCache", "/controlados");
         this.putParametroApp("rutaServiciosCache", "/co/com/personalsoft/cache/servicios");
         this.putParametroApp("rutaMensajesCache", "/co/com/personalsoft/cache/msg");
      }

   }

   private void putParametrosApp() throws PersonalsoftException {
      BDHelper bdHelper = null;
      HashMap<String, String> configuraciones = null;
      ConfiguracionHelper configuracionHelper = null;
      String clave = "";
      String valor = "";
      String habilitarCacheParam = this.getParametro("habilitarCacheParametrosBD");
      boolean habilitarCache = true;
      if (habilitarCacheParam != null && habilitarCacheParam.equals("N")) {
         habilitarCache = false;
      }

      Fqn configuracionRecords = Fqn.fromString("/co/com/personalsoft/cache/configuracion");
      Node<Object, Object> configuracionRecordsNode = this.rootNode.getChild(configuracionRecords);
      if (this.nombreJndi != null && !this.nombreJndi.equals("")) {
         configuracionRecordsNode.put("jndi", this.nombreJndi);
      } else {
         configuracionRecordsNode.put("jndi", this.getParametro("jndi"));
      }

      if (habilitarCache) {
         bdHelper = new BDHelper();
         configuracionHelper = new ConfiguracionHelper(bdHelper);

         try {
            configuraciones = configuracionHelper.cargarConfiguracion();
            if (configuraciones != null && configuraciones.size() > 0) {
               Iterator it = configuraciones.keySet().iterator();

               while(it.hasNext()) {
                  clave = (String)it.next();
                  valor = (String)configuraciones.get(clave);
                  configuracionRecordsNode.put(clave, valor);
               }
            }
         } catch (Exception var11) {
            if (var11 instanceof PersonalsoftException) {
               throw (PersonalsoftException)var11;
            }

            throw new PersonalsoftException(var11);
         }
      }

   }

   public void putParametroApp(String clave, String valor) {
      Fqn configuracionRecords = Fqn.fromString("/co/com/personalsoft/cache/configuracion");
      Node<Object, Object> configuracionRecordsNode = this.rootNode.getChild(configuracionRecords);
      configuracionRecordsNode.put(clave, valor);
   }

   private void putRecursos() {
      Recursos recursos = null;
      CargadorXMLHelper cargador = new CargadorXMLHelper();
      HashMap recursosAceptados = null;
      HashMap recursosControlados = null;
      Recurso recurso = null;
      String clave = "";
      Fqn<String> recursosRecords = Fqn.fromString("/co/com/personalsoft/cache/recursos");
      Node<Object, Object> recursosRecordsNode = this.rootNode.getChild(recursosRecords);
      Fqn<String> recursosAceptadosRecords = Fqn.fromString("/aceptados");
      Node<Object, Object> recursosAceptadosRecordsNode = recursosRecordsNode.getChild(recursosAceptadosRecords);
      Fqn<String> recursosControladosRecords = Fqn.fromString("/controlados");
      Node recursosControladosRecordsNode = recursosRecordsNode.getChild(recursosControladosRecords);

      try {
         recursos = cargador.getRecursos();
         recursosControlados = recursos.getRecursosControlados();
         recursosAceptados = recursos.getRecursosAceptados();
      } catch (Exception var15) {
         PersonalsoftException personalsoftException = new PersonalsoftException(var15);
         this.log.error(personalsoftException.getTraza());
         this.log.error(personalsoftException.getMensajeTecnico());
      }

      Iterator it;
      if (recursosAceptados != null && recursosAceptados.size() > 0) {
         it = recursosAceptados.keySet().iterator();

         while(it.hasNext()) {
            clave = (String)it.next();
            recurso = (Recurso)recursosAceptados.get(clave);
            recursosAceptadosRecordsNode.put(clave, recurso);
         }
      }

      if (recursosControlados != null && recursosControlados.size() > 0) {
         it = recursosControlados.keySet().iterator();

         while(it.hasNext()) {
            clave = (String)it.next();
            recurso = (Recurso)recursosControlados.get(clave);
            recursosControladosRecordsNode.put(clave, recurso);
         }
      }

   }

   public Object getParametro(String claveNodo, String valor) {
      Object object = null;
      Fqn recursosRecords;
      Node recursosRecordsNode;
      if (valor.equals("/co/com/personalsoft/cache/query")) {
         recursosRecords = Fqn.fromString("/co/com/personalsoft/cache/query");
         recursosRecordsNode = this.rootNode.getChild(recursosRecords);
         object = recursosRecordsNode.get(claveNodo);
      } else if (valor.equals("/co/com/personalsoft/cache/configuracion")) {
         recursosRecords = Fqn.fromString("/co/com/personalsoft/cache/configuracion");
         recursosRecordsNode = this.rootNode.getChild(recursosRecords);
         object = recursosRecordsNode.get(claveNodo);
      } else if (valor.equals("/co/com/personalsoft/cache/servicios")) {
         recursosRecords = Fqn.fromString("/co/com/personalsoft/cache/servicios");
         recursosRecordsNode = this.rootNode.getChild(recursosRecords);
         object = recursosRecordsNode.get(claveNodo);
      } else if (valor.equals("/co/com/personalsoft/cache/msg")) {
         recursosRecords = Fqn.fromString("/co/com/personalsoft/cache/msg");
         recursosRecordsNode = this.rootNode.getChild(recursosRecords);
         object = recursosRecordsNode.get(claveNodo);
      } else {
         Fqn recursosControladosRecords;
         Node recursosControladosRecordsNode;
         if (valor.equals("/aceptados")) {
            recursosRecords = Fqn.fromString("/co/com/personalsoft/cache/recursos");
            recursosRecordsNode = this.rootNode.getChild(recursosRecords);
            recursosControladosRecords = Fqn.fromString("/aceptados");
            recursosControladosRecordsNode = recursosRecordsNode.getChild(recursosControladosRecords);
            object = recursosControladosRecordsNode.get(claveNodo);
         } else if (valor.equals("/controlados")) {
            recursosRecords = Fqn.fromString("/co/com/personalsoft/cache/recursos");
            recursosRecordsNode = this.rootNode.getChild(recursosRecords);
            recursosControladosRecords = Fqn.fromString("/controlados");
            recursosControladosRecordsNode = recursosRecordsNode.getChild(recursosControladosRecords);
            object = (Recurso)recursosControladosRecordsNode.get(claveNodo);
         }
      }

      return object;
   }

   private void putServicios() {
      CargadorXMLServiciosHelper cargador = new CargadorXMLServiciosHelper();
      Servicios servicio = null;
      HashMap servicios = null;
      Servicio valor = null;
      String clave = "";
      Fqn<String> serviciosRecords = Fqn.fromString("/co/com/personalsoft/cache/servicios");
      Node serviciosRecordsNode = this.rootNode.getChild(serviciosRecords);

      try {
         servicio = cargador.getServicios();
         servicios = servicio.getServicios();
      } catch (Exception var10) {
         PersonalsoftException personalsoftException = new PersonalsoftException(var10);
         this.log.error(personalsoftException.getTraza());
         this.log.error(personalsoftException.getMensajeTecnico());
      }

      if (servicios != null && servicios.size() > 0) {
         Iterator it = servicios.keySet().iterator();

         while(it.hasNext()) {
            clave = (String)it.next();
            valor = (Servicio)servicios.get(clave);
            serviciosRecordsNode.put(clave, valor);
         }
      }

   }

   public void putServicio(String claveNodo, String valor) {
      Servicio servicio = new Servicio(claveNodo, claveNodo, valor);
      Fqn<String> serviciosRecords = Fqn.fromString("/co/com/personalsoft/cache/servicios");
      Node<Object, Object> serviciosRecordsNode = this.rootNode.getChild(serviciosRecords);
      serviciosRecordsNode.put(claveNodo, servicio);
   }

   public void putServicio(String clave, Servicio servicio) {
      Fqn<String> serviciosRecords = Fqn.fromString("/co/com/personalsoft/cache/servicios");
      Node<Object, Object> serviciosRecordsNode = this.rootNode.getChild(serviciosRecords);
      serviciosRecordsNode.put(clave, servicio);
   }

   public void putMensaje(Mensaje mensaje) {
      Fqn<String> msgRecords = Fqn.fromString("/co/com/personalsoft/cache/msg");
      Node<Object, Object> msgRecordsNode = this.rootNode.getChild(msgRecords);
      msgRecordsNode.put(mensaje.getClavePrimaria(), mensaje);
   }

   public void putQuery(String clave, String valor) {
      Fqn<String> queryRecords = Fqn.fromString("/co/com/personalsoft/cache/query");
      Node<Object, Object> queryRecordsNode = this.rootNode.getChild(queryRecords);
      queryRecordsNode.put(clave, valor);
   }

   public String getQuery(String clave) {
      Fqn<String> queryRecords = Fqn.fromString("/co/com/personalsoft/cache/query");
      Node<Object, Object> queryRecordsNode = this.rootNode.getChild(queryRecords);
      return queryRecordsNode.get(clave) != null ? (String)queryRecordsNode.get(clave) : null;
   }

   public String[] getAllQuery() {
      Fqn<String> queryRecords = Fqn.fromString("/co/com/personalsoft/cache/query");
      Node<Object, Object> queryRecordsNode = this.rootNode.getChild(queryRecords);
      Set<Object> querys = queryRecordsNode.getKeys();
      String[] querysString = new String[querys.size()];
      ArrayList<String> clavesValores = new ArrayList();
      querys.toArray(querysString);

      for(int i = 0; i < querysString.length; ++i) {
         clavesValores.add(querysString[i]);
         clavesValores.add((String)queryRecordsNode.get(querysString[i]));
      }

      return (String[])clavesValores.toArray(new String[clavesValores.size()]);
   }

   public String getParametroApp(String clave) {
      Fqn configuracionRecords = Fqn.fromString("/co/com/personalsoft/cache/configuracion");
      Node<Object, Object> configuracionRecordsNode = this.rootNode.getChild(configuracionRecords);
      return configuracionRecordsNode.get(clave) != null ? (String)configuracionRecordsNode.get(clave) : null;
   }

   public Object getParametroObjectApp(String clave) {
      Fqn configuracionRecords = Fqn.fromString("/co/com/personalsoft/cache/configuracion");
      Node<Object, Object> configuracionRecordsNode = this.rootNode.getChild(configuracionRecords);
      return configuracionRecordsNode.get(clave) != null ? configuracionRecordsNode.get(clave) : null;
   }

   public Servicio getServicio(String clave) {
      Fqn serviciosRecords = Fqn.fromString("/co/com/personalsoft/cache/servicios");
      Node<Object, Object> serviciosRecordsNode = this.rootNode.getChild(serviciosRecords);
      return serviciosRecordsNode.get(clave) != null ? (Servicio)serviciosRecordsNode.get(clave) : null;
   }

   public String[] getRecursosControladosNavegacion() {
      Fqn<String> recursosRecords = Fqn.fromString("/co/com/personalsoft/cache/recursos/controlados");
      Node<Object, Object> nodo = this.rootNode.getChild(recursosRecords);
      Iterator<Object> iterador = nodo.getKeys().iterator();
      Recurso recurso = null;
      ArrayList<Recurso> recursos = new ArrayList();
      String[] recursosRetorno = (String[])null;

      while(iterador.hasNext()) {
         recurso = (Recurso)nodo.get(iterador.next());
         recursos.add(recurso);
      }

      Collections.sort(recursos);
      recursosRetorno = new String[recursos.size()];
      int j = 0;
      int z = 0;

      for(int i = 0; i < recursos.size(); ++i) {
         recursosRetorno[z] = ((Recurso)recursos.get(i)).getEntidad() != null ? ((Recurso)recursos.get(i)).getEntidad() : "";
         ++z;

         for(; j < recursos.size(); i = j++) {
            if (!((Recurso)recursos.get(i)).getEntidad().equals(((Recurso)recursos.get(j)).getEntidad())) {
               i = j - 1;
               break;
            }

            if (recursosRetorno[z] != null) {
               recursosRetorno[z] = recursosRetorno[z] + ((Recurso)recursos.get(j)).toString() + "\n";
            } else {
               recursosRetorno[z] = ((Recurso)recursos.get(j)).toString() + "\n";
            }
         }

         if (recursosRetorno.length > 0) {
            recursosRetorno[z].substring(0, recursosRetorno.length - 1);
         }

         ++z;
      }

      return recursosRetorno;
   }

   public String[] getRecursosAceptadosNavegacion() {
      Fqn<String> recursosRecords = Fqn.fromString("/co/com/personalsoft/cache/recursos/aceptados");
      Node<Object, Object> nodo = this.rootNode.getChild(recursosRecords);
      Iterator<Object> iterador = nodo.getKeys().iterator();
      Recurso recurso = null;
      ArrayList recursos = new ArrayList();

      while(iterador.hasNext()) {
         recurso = (Recurso)nodo.get(iterador.next());
         recursos.add(recurso.toString());
      }

      Collections.sort(recursos);
      return (String[])recursos.toArray(new String[recursos.size()]);
   }

   public HashMap<String, String> obtenerIdiomasDisponiblesApp() {
      HashMap<String, String> idiomasMap = (HashMap)this.getParametroObjectApp("idiomasDisponiblesMap");
      String idiomas = "";
      String[] idiomasArray = (String[])null;
      String[] idiomaPais = (String[])null;
      if (idiomasMap == null) {
         idiomasMap = new HashMap();
         idiomas = this.getParametro("idiomasDisponibles");
         if (idiomas == null) {
            idiomas = this.getParametro("idiomaPorDefecto");
            if (idiomas == null) {
               idiomas = "es-CO=Español";
            }
         }

         idiomasArray = idiomas.split(",");
         String[] var8 = idiomasArray;
         int var6 = 0;

         for(int var7 = idiomasArray.length; var6 < var7; ++var6) {
            String idioma = var8[var6];
            idiomaPais = idioma.split("=");
            if (idiomaPais.length > 1) {
               idiomasMap.put(idiomaPais[0], idiomaPais[1]);
            }
         }
      }

      return idiomasMap;
   }

   public String getMensaje(String clave) {
      Fqn msgRecords = Fqn.fromString("/co/com/personalsoft/cache/msg");
      Node<Object, Object> msgRecordsNode = this.rootNode.getChild(msgRecords);
      Mensaje mensaje = msgRecordsNode.get(clave) != null ? (Mensaje)msgRecordsNode.get(clave) : null;
      return mensaje != null && !mensaje.equals("") ? mensaje.getValor() : null;
   }

   public void eliminarMensageCache(Mensaje mensaje) {
      Fqn msgRecords = Fqn.fromString("/co/com/personalsoft/cache/msg");
      Node<Object, Object> msgRecordsNode = this.rootNode.getChild(msgRecords);
      msgRecordsNode.remove(mensaje.getClavePrimaria());
   }

   public void ingresarParametroBD(String clave, String valor) throws Exception {
      BDHelper helper = null;
      ConfiguracionHelper configuracionHelper = null;

      try {
         helper = new BDHelper();
         configuracionHelper = new ConfiguracionHelper(helper);
         configuracionHelper.ingresarParametroBD(clave, valor);
         this.putParametroApp(clave, valor);
         helper.commitTransaction();
      } catch (PersonalsoftException var11) {
         helper.rollbackTransaction();
         this.log.error(var11.getTraza());
         throw new Exception(var11.getException());
      } catch (Exception var12) {
         PersonalsoftException personalE = new PersonalsoftException(var12);
         this.log.error(personalE.getTraza());
         if (helper != null) {
            helper.rollbackTransaction();
         }

         throw var12;
      } finally {
         if (helper != null) {
            helper.cerrarConexion();
         }

      }

   }

   public String[] cargarParametrosConfiguracionBD() throws Exception {
      BDHelper helper = null;
      ConfiguracionHelper configuracionHelper = null;
      String[] parametros = (String[])null;

      try {
         helper = new BDHelper();
         configuracionHelper = new ConfiguracionHelper(helper);
         parametros = configuracionHelper.cargarParametrosConfiguracionBD();
      } catch (PersonalsoftException var10) {
         this.log.error(var10.getTraza());
         throw new Exception(var10.getException());
      } catch (Exception var11) {
         PersonalsoftException personalE = new PersonalsoftException(var11);
         this.log.error(personalE.getTraza());
         if (helper != null) {
            helper.rollbackTransaction();
         }

         throw var11;
      } finally {
         if (helper != null) {
            helper.cerrarConexion();
         }

      }

      return parametros;
   }

   public void actualizarParametroBD(String clave, String valor) throws Exception {
      BDHelper helper = null;
      ConfiguracionHelper configuracionHelper = null;

      try {
         helper = new BDHelper();
         configuracionHelper = new ConfiguracionHelper(helper);
         configuracionHelper.actualizarParametroBD(clave, valor);
         this.putParametroApp(clave, valor);
         helper.commitTransaction();
      } catch (PersonalsoftException var11) {
         helper.rollbackTransaction();
         this.log.error(var11.getTraza());
         throw new Exception(var11.getException());
      } catch (Exception var12) {
         PersonalsoftException personalE = new PersonalsoftException(var12);
         this.log.error(personalE.getTraza());
         if (helper != null) {
            helper.rollbackTransaction();
         }

         throw var12;
      } finally {
         if (helper != null) {
            helper.cerrarConexion();
         }

      }

   }

   public void borrarParametroBD(String clave) throws Exception {
      BDHelper helper = null;
      ConfiguracionHelper configuracionHelper = null;

      try {
         helper = new BDHelper();
         configuracionHelper = new ConfiguracionHelper(helper);
         configuracionHelper.borrarParametroBD(clave);
         this.borrarParametroCache(clave);
         helper.commitTransaction();
      } catch (PersonalsoftException var10) {
         helper.rollbackTransaction();
         this.log.error(var10.getTraza());
         throw new Exception(var10.getException());
      } catch (Exception var11) {
         PersonalsoftException personalE = new PersonalsoftException(var11);
         this.log.error(personalE.getTraza());
         if (helper != null) {
            helper.rollbackTransaction();
         }

         throw var11;
      } finally {
         if (helper != null) {
            helper.cerrarConexion();
         }

      }

   }

   private void borrarParametroCache(String clave) {
      Fqn configRecords = Fqn.fromString("/co/com/personalsoft/cache/configuracion");
      Node<Object, Object> configRecordsNode = this.rootNode.getChild(configRecords);
      configRecordsNode.remove(clave);
   }

   public void limpiarNodoQuerys() {
      Fqn configRecords = Fqn.fromString("/co/com/personalsoft/cache/query");
      Node<Object, Object> configRecordsNode = this.rootNode.getChild(configRecords);
      configRecordsNode.clearData();
   }

   public String getNombreJndi() {
      return this.nombreJndi;
   }

   public void setNombreJndi(String nombreJndi) {
      this.nombreJndi = nombreJndi;
   }
}
