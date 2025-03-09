package co.com.personalsoft.base.config;

import co.com.personalsoft.base.beans.Mensaje;
import co.com.personalsoft.base.beans.Servicio;
import co.com.personalsoft.base.programacion.control.AgenteEjecucion;
import co.com.personalsoft.base.programacion.control.AgenteEjecucionBDHelper;
import co.com.personalsoft.base.programacion.control.AgenteEjecucionConfiguracionHelper;
import co.com.personalsoft.base.programacion.control.AgenteEjecucionHelper;
import co.com.personalsoft.base.programacion.control.AgenteValidador;
import co.com.personalsoft.base.util.CargadorMsj;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

public class ConfiguracionWS implements Serializable {
   private static final long serialVersionUID = -2226503232493339949L;

   public void putParametroApp(String clave, String valor) {
      Configuracion.getInstance().putParametroApp(clave, valor);
   }

   public void putMessage(String claveNodo, String valor, String idioma) {
      Configuracion.getInstance().putMensaje(new Mensaje(claveNodo, valor, new Locale(idioma)));
   }

   public void putQuery(String clave, String valor) {
      Configuracion.getInstance().putQuery(clave, valor);
   }

   public String[] getAllQuerys() {
      return Configuracion.getInstance().getAllQuery();
   }

   public void putServicio(String claveNodo, String valor) {
      Configuracion.getInstance().putServicio(claveNodo, valor);
   }

   public String getQuery(String clave) {
      return Configuracion.getInstance().getQuery(clave);
   }

   public String getParametroApp(String clave) {
      return Configuracion.getInstance().getParametro(clave);
   }

   public Servicio getServicio(String clave) {
      return Configuracion.getInstance().getServicio(clave);
   }

   public String getMessage(String clave) {
      return Configuracion.getInstance().getMensaje(clave);
   }

   public String[] cargarTodosMensajes() throws Exception {
      return CargadorMsj.getInstance().cargarTodosMensajes();
   }

   public void ingresarMensaje(String claveMensaje, String valorMensaje, String idioma) throws Exception {
      CargadorMsj.getInstance().ingresarMensajeBD(new Mensaje(claveMensaje, valorMensaje, new Locale(idioma)));
   }

   public void actualizarMensaje(String claveMensaje, String valorMensaje, String idioma) throws Exception {
      CargadorMsj.getInstance().actualizarMensajeBD(new Mensaje(claveMensaje, valorMensaje, new Locale(idioma)));
   }

   public void borrarMensaje(String claveMensaje, String idioma) throws Exception {
      CargadorMsj.getInstance().borrarMensajeBD(new Mensaje(claveMensaje, "", new Locale(idioma)));
   }

   public String[] cargarTodosParametros() throws Exception {
      return Configuracion.getInstance().cargarParametrosConfiguracionBD();
   }

   public void ingresarParametro(String clave, String valor) throws Exception {
      Configuracion.getInstance().ingresarParametroBD(clave, valor);
   }

   public void actualizarParametro(String clave, String valor) throws Exception {
      Configuracion.getInstance().actualizarParametroBD(clave, valor);
   }

   public void borrarParametro(String clave) throws Exception {
      Configuracion.getInstance().borrarParametroBD(clave);
   }

   public void limpiarNodoQuerysCache() throws Exception {
      Configuracion.getInstance().limpiarNodoQuerys();
   }

   public String[] consultarHilosSistema() {
      ConfiguracionNoCache config = new ConfiguracionNoCache();
      String[] hilosSistema = config.consultarHilosSistema();
      return hilosSistema;
   }

   public void detenerHilo(long id) {
      ConfiguracionNoCache config = new ConfiguracionNoCache();
      config.detenerHilo(id);
   }

   public String[] getRecursosControladosNavegacion() {
      return Configuracion.getInstance().getRecursosControladosNavegacion();
   }

   public String[] getRecursosAceptadosNavegacion() {
      return Configuracion.getInstance().getRecursosAceptadosNavegacion();
   }

   public String[] consultarLoggersApp(String patronBusqueda) {
      ConfiguracionNoCache config = new ConfiguracionNoCache();
      String[] loggersString = config.consultarLoggersApp(patronBusqueda);
      return loggersString;
   }

   public void cambiarNivelLogger(String nombre, String nivel) {
      ConfiguracionNoCache config = new ConfiguracionNoCache();
      config.cambiarNivelLogger(nombre, nivel);
   }

   public boolean pruebaConexionWS() {
      return true;
   }

   public String programarTarea(String tareaObj, String nombreTarea, String nombreMetodoEjecucion, Object[] parametrosEjecucion, String metodoCasoError, Object[] parametrosCasosError, Calendar fechaEjecucion, int intervaloEjecucionHoras, boolean tareaPersistente, String unidadTiempo) throws Exception {
      return AgenteEjecucion.getInstance().programarTarea((Object)null, tareaObj, (Object[])(nombreTarea, nombreMetodoEjecucion, parametrosEjecucion, metodoCasoError, parametrosCasosError, fechaEjecucion, intervaloEjecucionHoras, tareaPersistente, unidadTiempo, true));
   }

   public void reprogramarTarea(String idTarea, String tareaObj, String nombreTarea, String nombreMetodoEjecucion, Object[] parametrosEjecucion, String metodoCasoError, Object[] parametrosCasosError, Calendar fechaEjecucion, int intervaloEjecucionHoras, boolean tareaPersistente, String unidadTiempo) throws Exception {
      AgenteEjecucion.getInstance().reprogramarTarea(idTarea, tareaObj, nombreTarea, nombreMetodoEjecucion, parametrosEjecucion, metodoCasoError, parametrosCasosError, fechaEjecucion, intervaloEjecucionHoras, tareaPersistente, unidadTiempo);
   }

   public boolean cancelarEjecucionTarea(String idTarea) {
      return AgenteEjecucion.getInstance().cancelarEjecucionTarea(idTarea);
   }

   public Object consultarResultadoTarea(String idTarea) throws Exception {
      return AgenteEjecucion.getInstance().consultarResultadoTarea(idTarea);
   }

   public Object consultarResultadoTareaConError(String idTarea) {
      return AgenteEjecucion.getInstance().consultarResultadoTareaConError(idTarea);
   }

   public String consultarHabilitarPersistenciaTareasBD() {
      return AgenteEjecucion.getInstance().consultarHabilitarPersistenciaTareasBD();
   }

   public void esperarResultadosTareas(long tiempoMaxEsperaTarea, String... idTarea) {
      AgenteEjecucion.getInstance().esperarResultadosTareas(tiempoMaxEsperaTarea, idTarea);
   }

   public String[] consultarMetodosClase(String nombreClase) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
      AgenteValidador agenteValidador = new AgenteValidador();
      return agenteValidador.consultarMetodosClase(nombreClase);
   }

   public String[] consultarParametrosMetodo(String nombreClase, String nombreMetodoEjecucion, Object[] parametros) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
      AgenteValidador agenteValidador = new AgenteValidador();
      return agenteValidador.consultarParametrosMetodo(nombreClase, nombreMetodoEjecucion, parametros);
   }

   public String[] consultarIdiomasDisponibles() {
      HashMap<String, String> idiomasDisponiblesMap = Configuracion.getInstance().obtenerIdiomasDisponiblesApp();
      ArrayList<String> idiomasDisponibles = new ArrayList();
      Iterator iter = idiomasDisponiblesMap.keySet().iterator();

      while(iter.hasNext()) {
         idiomasDisponibles.add((String)iter.next());
      }

      return (String[])idiomasDisponibles.toArray(new String[idiomasDisponibles.size()]);
   }

   public String[] consultarTareasProgramadas() throws Exception {
      AgenteEjecucionHelper agenteEjecucionHelper = new AgenteEjecucionHelper();
      return agenteEjecucionHelper.consultarTareasProgramadas();
   }

   public String[] consultarTareasProgramadasBD() throws Exception {
      AgenteEjecucionBDHelper agenteEjecucionBDHelper = new AgenteEjecucionBDHelper();
      return agenteEjecucionBDHelper.consultarTareasProgramadasBD();
   }

   public String consultarTareaProgramadaBD(String idTarea) throws Exception {
      AgenteEjecucionBDHelper agenteEjecucionBDHelper = new AgenteEjecucionBDHelper();
      return agenteEjecucionBDHelper.consultarTareaProgramadaBD(idTarea);
   }

   public String consultarTareaProgramada(String idTarea) throws Exception {
      return AgenteEjecucion.getInstance().consultarDatosTarea(idTarea);
   }

   public String[] buscarTareas(String nombre, boolean isPersistente, String idTarea, String estado, String fechaEjecucion, String fechaEjecucionInicial, String fechaEjecucionFinal) throws Exception {
      AgenteEjecucionConfiguracionHelper agenteEjecucionConfiguracionHelper = new AgenteEjecucionConfiguracionHelper();
      return agenteEjecucionConfiguracionHelper.buscarTareas(nombre, isPersistente, idTarea, estado, fechaEjecucion, fechaEjecucionInicial, fechaEjecucionFinal);
   }
}
