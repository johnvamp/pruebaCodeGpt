package co.com.personalsoft.seguridad.servicio;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.seguridad.beans.AplicacionSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.PerfilSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.RecursoSeguridadDTO;
import co.com.personalsoft.seguridad.bd.PerfilesHelperBD;
import co.com.personalsoft.seguridad.cache.AdminCache;
import co.com.personalsoft.seguridad.config.ParserConfiguracion;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.apache.log4j.Logger;

public class GestorSeguridad {
   private static Logger log = Logger.getLogger(GestorSeguridad.class);
   private static GestorSeguridad gestorSeguridad;
   private AdminCache adminCache = null;
   public Map<String, Object> listaRecursos;

   private GestorSeguridad() {
   }

   public static GestorSeguridad getInstance() {
      if (gestorSeguridad == null) {
         gestorSeguridad = new GestorSeguridad();
         gestorSeguridad.adminCache = new AdminCache();
      }

      return gestorSeguridad;
   }

   public void init() throws PersonalsoftException {
      if (log.isDebugEnabled()) {
         log.info(" inicializando la caché");
      }

      this.adminCache.initCache();
      if (log.isDebugEnabled()) {
         log.info(" caché finalizada");
      }

      this.configurarParametros();
      this.cargarPerfiles();
      this.cargarPerfilesRecurso();
      this.cargarPerfilesOpcion();
      if (log.isDebugEnabled()) {
         log.info(" fin carga del gestor");
      }

   }

   public void removerRecursos() {
      this.adminCache.removerRecursos();
   }

   public void reCargarPerfilesRecurso() throws PersonalsoftException {
      this.cargarPerfilesRecurso();
   }

   private void cargarPerfilesRecurso() throws PersonalsoftException {
      PerfilesHelperBD perfilesHelperBD = new PerfilesHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = (AplicacionSeguridadDTO)this.getParametro("aplicacion");
      perfilesHelperBD.cargarPerfilesRecurso(aplicacionSeguridadDTO);
   }

   private void configurarParametros() throws PersonalsoftException {
      ParserConfiguracion parseConfiguracion = new ParserConfiguracion();
      Entry<String, Object> entrada = null;
      Set<Entry<String, Object>> entradas = null;
      this.listaRecursos = parseConfiguracion.parseDocument(Configuracion.getInstance().getParametro("rutaArchivoConfiguracionSeguridad"));
      if (this.listaRecursos != null && !this.listaRecursos.isEmpty()) {
         entradas = this.listaRecursos.entrySet();
         Iterator iterador = entradas.iterator();

         while(iterador.hasNext()) {
            entrada = (Entry)iterador.next();
            this.adminCache.putParametro((String)entrada.getKey(), entrada.getValue());
         }
      }

   }

   private void cargarPerfiles() throws PersonalsoftException {
      PerfilesHelperBD perfilesHelperBD = new PerfilesHelperBD();
      PerfilSeguridadDTO perfilSeguridadDTO = new PerfilSeguridadDTO();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = (AplicacionSeguridadDTO)this.getParametro("aplicacion");
      perfilesHelperBD.cargarPerfiles(aplicacionSeguridadDTO, perfilSeguridadDTO);
   }

   private void cargarPerfilesOpcion() throws PersonalsoftException {
      PerfilesHelperBD perfilesHelperBD = new PerfilesHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = (AplicacionSeguridadDTO)this.getParametro("aplicacion");
      perfilesHelperBD.cargarPerfilesOpcion(aplicacionSeguridadDTO);
   }

   public Object getParametro(String parametro) {
      return this.adminCache.getParametro(parametro);
   }

   public void putParametro(String clave, Object valor) {
      this.adminCache.putParametro(clave, valor);
   }

   public List<PerfilSeguridadDTO> getPerfilesSeguridad() {
      return this.adminCache.consultarPerfiles();
   }

   public Map<String, Object> getParametrosConfiguracion() {
      return this.adminCache.consultarParametrosConfiguracion();
   }

   public void putPerfilSeguridad(PerfilSeguridadDTO perfilSeguridad) {
      this.adminCache.putPerfilSeguridad(perfilSeguridad);
   }

   public void removerPerfilSeguridad(String clave) {
      this.adminCache.removerPerfilSeguridad(clave);
   }

   public PerfilSeguridadDTO getPerfilSeguridad(String clave) {
      return this.adminCache.getPerfilSeguridad(clave);
   }

   public void putRecurso(RecursoSeguridadDTO recursoSeguridad) {
      this.adminCache.putRecurso(recursoSeguridad);
   }

   public void removerRecurso(String clave) {
      this.adminCache.removerRecurso(clave);
   }

   public RecursoSeguridadDTO getRecursoSeguridad(String clave) {
      return this.adminCache.getRecursoSeguridad(clave);
   }

   public void putPerfilSeguridadOpcion(PerfilSeguridadDTO perfilSeguridadDTO) {
      this.adminCache.putPerfilSeguridadOpcion(perfilSeguridadDTO);
   }

   public void removerPerfilSeguridadOpcion(String clave) {
      this.adminCache.removerPerfilSeguridadOpcion(clave);
   }

   public PerfilSeguridadDTO getPerfilSeguridadOpcion(String clave) {
      return this.adminCache.getPerfilSeguridadOpcionDTO(clave);
   }

   public Map<String, Object> getRecursos() {
      return this.adminCache.consultarRecursos();
   }

   public Map<String, Object> getPerfilesOpcion() {
      return this.adminCache.consultarPerfilesOpcion();
   }
}
