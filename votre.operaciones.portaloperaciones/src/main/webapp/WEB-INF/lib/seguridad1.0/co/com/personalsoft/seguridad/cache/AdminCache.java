package co.com.personalsoft.seguridad.cache;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.seguridad.beans.PerfilSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.RecursoSeguridadDTO;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.jcip.annotations.ThreadSafe;
import org.apache.log4j.Logger;
import org.jboss.cache.Cache;
import org.jboss.cache.DefaultCacheFactory;
import org.jboss.cache.Fqn;
import org.jboss.cache.Node;

@ThreadSafe
public class AdminCache implements Serializable {
   private static final long serialVersionUID = 647351829391455415L;
   private Logger log = Logger.getLogger(this.getClass());
   private Node<String, Object> rootNode = null;
   private final String rutaParametrosCache = "/co/com/personalsoft/cache/seguridad/parametros";
   private final String rutaPerfilesCache = "/co/com/personalsoft/cache/seguridad/perfiles";
   private final String rutaRecursosCache = "/co/com/personalsoft/cache/seguridad/recursos";
   private final String rutaPerfilesOpcionesCache = "/co/com/personalsoft/cache/seguridad/perfilesOpciones";

   public void initCache() throws PersonalsoftException {
      InputStream inputStr = null;
      String rutaArchivoConfigCache = Configuracion.getInstance().getParametro("archivoConfiguracionCache");
      if (rutaArchivoConfigCache != null && !rutaArchivoConfigCache.equals("")) {
         inputStr = Configuracion.getInstance().getContext().getResourceAsStream(rutaArchivoConfigCache);

         try {
            this.init(inputStr);
         } catch (Exception var5) {
            PersonalsoftException personalE = new PersonalsoftException(var5);
            throw personalE;
         }
      } else {
         this.log.error("No se encontró el parámetro de configuración que indica la ruta de configuración del cache. Por lo tanto el caché no podrá ser utilizado.");
      }

   }

   private void init(InputStream inputStr) throws SQLException, PersonalsoftException {
      Cache cache = null;
      Fqn<String> parametrosRecords = null;
      Fqn<String> perfilesRecords = null;
      Fqn<String> recursosRecords = null;
      Fqn<String> perfilesOpcionesRecords = null;
      Node<String, Object> recursosRecordsNode = null;
      Node<String, Object> parametrosRecordsNode = null;
      Node<String, Object> perfilesRecordsNode = null;
      Node<String, Object> perfilesOpcionesRecordsNode = null;
      if (inputStr != null) {
         cache = (new DefaultCacheFactory()).createCache(inputStr);
         cache.start();
         parametrosRecords = Fqn.fromString("/co/com/personalsoft/cache/seguridad/parametros");
         perfilesRecords = Fqn.fromString("/co/com/personalsoft/cache/seguridad/perfiles");
         recursosRecords = Fqn.fromString("/co/com/personalsoft/cache/seguridad/recursos");
         perfilesOpcionesRecords = Fqn.fromString("/co/com/personalsoft/cache/seguridad/perfilesOpciones");
         this.rootNode = cache.getRoot();
         this.rootNode.addChild(parametrosRecords);
         this.rootNode.addChild(perfilesRecords);
         this.rootNode.addChild(recursosRecords);
         this.rootNode.addChild(perfilesOpcionesRecords);
         parametrosRecordsNode = this.rootNode.addChild(parametrosRecords);
         parametrosRecordsNode.addChild(parametrosRecords);
         perfilesRecordsNode = this.rootNode.addChild(perfilesRecords);
         perfilesRecordsNode.addChild(perfilesRecords);
         recursosRecordsNode = this.rootNode.addChild(recursosRecords);
         recursosRecordsNode.addChild(recursosRecords);
         perfilesOpcionesRecordsNode = this.rootNode.addChild(perfilesOpcionesRecords);
         perfilesOpcionesRecordsNode.addChild(perfilesOpcionesRecords);
      }

   }

   private Node<String, Object> consultarNodo(String clave) {
      Fqn consultarRecords = Fqn.fromString(clave);
      Node<String, Object> parametroRecordsNode = this.rootNode.getChild(consultarRecords);
      return parametroRecordsNode;
   }

   public Map<String, Object> consultarParametrosConfiguracion() {
      Node<String, Object> parametroRecordsNode = this.consultarNodo("/co/com/personalsoft/cache/seguridad/parametros");
      return parametroRecordsNode.getData();
   }

   public List<PerfilSeguridadDTO> consultarPerfiles() {
      Node<String, Object> perfilRecordsNode = this.consultarNodo("/co/com/personalsoft/cache/seguridad/perfiles");
      return perfilRecordsNode.getData() != null ? new ArrayList(perfilRecordsNode.getData().values()) : null;
   }

   public Map<String, Object> consultarRecursos() {
      Node<String, Object> recursoRecordsNode = this.consultarNodo("/co/com/personalsoft/cache/seguridad/recursos");
      return recursoRecordsNode != null ? recursoRecordsNode.getData() : null;
   }

   public void removerRecursos() {
      Node<String, Object> recursoRecordsNode = this.consultarNodo("/co/com/personalsoft/cache/seguridad/recursos");
      Map<String, Object> consultarRecursos = recursoRecordsNode != null ? recursoRecordsNode.getData() : null;
      Entry<String, Object> entrada = null;
      Set<Entry<String, Object>> entradas = null;
      Map<String, Object> removerRecursos = null;
      if (consultarRecursos != null && !consultarRecursos.isEmpty()) {
         entradas = consultarRecursos.entrySet();
         removerRecursos = new HashMap();
         Iterator iterador = entradas.iterator();

         while(iterador.hasNext()) {
            entrada = (Entry)iterador.next();
            removerRecursos.put((String)entrada.getKey(), entrada.getKey());
         }

         entradas = removerRecursos.entrySet();
         iterador = entradas.iterator();

         while(iterador.hasNext()) {
            entrada = (Entry)iterador.next();
            this.removerRecurso((String)entrada.getKey());
         }
      }

   }

   public Map<String, Object> consultarPerfilesOpcion() {
      Node<String, Object> perfilesRecordsNode = this.consultarNodo("/co/com/personalsoft/cache/seguridad/perfilesOpciones");
      return perfilesRecordsNode != null ? perfilesRecordsNode.getData() : null;
   }

   public Object getParametro(String clave) {
      Node<String, Object> parametroRecordsNode = this.consultarNodo("/co/com/personalsoft/cache/seguridad/parametros");
      return parametroRecordsNode.get(clave) != null ? parametroRecordsNode.get(clave) : null;
   }

   public void putRecurso(RecursoSeguridadDTO recursoSeguridadDTO) {
      Node<String, Object> nodo = this.consultarNodo("/co/com/personalsoft/cache/seguridad/recursos");
      nodo.put(recursoSeguridadDTO.getNombreRecurso(), recursoSeguridadDTO);
   }

   public void removerRecurso(String clave) {
      Node<String, Object> nodo = this.consultarNodo("/co/com/personalsoft/cache/seguridad/recursos");
      nodo.remove(clave);
   }

   public RecursoSeguridadDTO getRecursoSeguridad(String clave) {
      Node<String, Object> nodo = this.consultarNodo("/co/com/personalsoft/cache/seguridad/recursos");
      return nodo.get(clave) != null ? (RecursoSeguridadDTO)nodo.get(clave) : null;
   }

   public void putPerfilSeguridadOpcion(PerfilSeguridadDTO perfilSeguridadDTO) {
      Node<String, Object> nodo = this.consultarNodo("/co/com/personalsoft/cache/seguridad/perfilesOpciones");
      nodo.put(perfilSeguridadDTO.getCodigoPerfil(), perfilSeguridadDTO);
   }

   public void removerPerfilSeguridadOpcion(String clave) {
      Node<String, Object> nodo = this.consultarNodo("/co/com/personalsoft/cache/seguridad/perfilesOpciones");
      nodo.remove(clave);
   }

   public PerfilSeguridadDTO getPerfilSeguridadOpcionDTO(String clave) {
      Node<String, Object> nodo = this.consultarNodo("/co/com/personalsoft/cache/seguridad/perfilesOpciones");
      return nodo.get(clave) != null ? (PerfilSeguridadDTO)nodo.get(clave) : null;
   }

   public void putPerfilSeguridad(PerfilSeguridadDTO perfilSeguridad) {
      Node<String, Object> nodo = this.consultarNodo("/co/com/personalsoft/cache/seguridad/perfiles");
      nodo.put(perfilSeguridad.getCodigoPerfil(), perfilSeguridad);
   }

   public void removerPerfilSeguridad(String clave) {
      Node<String, Object> nodo = this.consultarNodo("/co/com/personalsoft/cache/seguridad/perfiles");
      nodo.remove(clave);
   }

   public PerfilSeguridadDTO getPerfilSeguridad(String clave) {
      Node<String, Object> nodo = this.consultarNodo("/co/com/personalsoft/cache/seguridad/perfiles");
      return nodo.get(clave) != null ? (PerfilSeguridadDTO)nodo.get(clave) : null;
   }

   public void putParametro(String clave, Object valor) {
      Node<String, Object> nodo = this.consultarNodo("/co/com/personalsoft/cache/seguridad/parametros");
      nodo.put(clave, valor);
   }

   public Node<String, Object> getRootNode() {
      return this.rootNode;
   }

   public void setRootNode(Node<String, Object> rootNode) {
      this.rootNode = rootNode;
   }
}
