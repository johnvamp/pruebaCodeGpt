package co.com.personalsoft.base.programacion.tareas;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.programacion.control.AgenteEjecucionConfiguracionHelper;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import org.apache.log4j.Logger;

public class Tarea extends Thread implements Serializable {
   private static final long serialVersionUID = 1503092629465833011L;
   private Logger logger = Logger.getLogger(this.getClass());
   private String idTarea;
   private Object procesoProgramado;
   private Method metodoEjecucion;
   private Method metodoEjecucionCasoError;
   private String nombreMetodoEjecutado;
   private Class[] tiposParametrosEjecucion;
   private Object[] parametrosEjecucion;
   private Class[] tiposParametrosEjecucionError;
   private Object[] parametrosEjecucionError;
   private Object resultadoEjecucionMetodo;
   private DelegatedObservable delegadoObservable;
   private boolean tareaTerminada;
   private boolean tareaTerminadaConExito;
   private Timer timer;
   private TareaProgramada tareaProgramada;
   private boolean tareaRecurrente;
   private boolean tareaEnProceso;
   private long tiempoInicio;
   private boolean tareaPersistente;
   private String unidadTiempo;
   private int intervaloEjecucion;
   private Calendar fechaEjecucion;

   public Tarea() {
   }

   public Tarea(Object procesoProgramado, String metodoEjecucion, Object[] parametros) throws SecurityException {
      this.procesoProgramado = procesoProgramado;
      if (!this.contieneCaracterEspecial(parametros)) {
         this.tiposParametrosEjecucion = this.getTiposParametrosOriginales(parametros);
         this.parametrosEjecucion = parametros;
      } else {
         this.tiposParametrosEjecucion = this.getTiposParametros(parametros);
         this.logger.debug("Tipos: " + this.tiposParametrosEjecucion);
         this.parametrosEjecucion = this.getParametros(parametros);
         this.logger.debug("Parámetros de ejecución: " + this.parametrosEjecucion);
      }

      this.setTiempoInicio(System.currentTimeMillis());
      this.logger.debug("Extracción del método " + metodoEjecucion);

      try {
         this.metodoEjecucion = procesoProgramado.getClass().getMethod(metodoEjecucion, this.tiposParametrosEjecucion);
      } catch (NoSuchMethodException var6) {
         PersonalsoftException personal = new PersonalsoftException(var6);
         this.logger.error(personal.getTraza());
      }

      this.delegadoObservable = new DelegatedObservable();
      if (this.logger.isInfoEnabled()) {
         this.logger.info(this.toString());
      }

   }

   public Tarea(Object procesoProgramado, Method metodoEjecucion, Object[] parametros) {
      this.procesoProgramado = procesoProgramado;
      this.metodoEjecucion = metodoEjecucion;
      this.setTiempoInicio(System.currentTimeMillis());
      if (!this.contieneCaracterEspecial(parametros)) {
         this.tiposParametrosEjecucion = this.getTiposParametrosOriginales(parametros);
         this.parametrosEjecucion = parametros;
      } else {
         this.tiposParametrosEjecucion = this.getTiposParametros(parametros);
         this.logger.debug("Tipos: " + this.tiposParametrosEjecucion);
         this.parametrosEjecucion = this.getParametros(parametros);
         this.logger.debug("Parámetros de ejecución: " + this.parametrosEjecucion);
      }

      this.delegadoObservable = new DelegatedObservable();
      if (this.logger.isInfoEnabled()) {
         this.logger.info(this.toString());
      }

   }

   public Tarea(Object procesoProgramado, String metodoEjecucion, Object[] parametros, String metodoEjecucionCasoError, Object[] parametrosCasoError, int intervaloEjejcucion, String unidadTiempo, Calendar fechaEjecucion) throws SecurityException, NoSuchMethodException {
      this.procesoProgramado = procesoProgramado;
      this.intervaloEjecucion = intervaloEjejcucion;
      this.unidadTiempo = unidadTiempo;
      this.fechaEjecucion = fechaEjecucion;
      if (!this.contieneCaracterEspecial(parametros)) {
         this.tiposParametrosEjecucion = this.getTiposParametrosOriginales(parametros);
         this.parametrosEjecucion = parametros;
      } else {
         this.tiposParametrosEjecucion = this.getTiposParametros(parametros);
         this.logger.debug("Tipos: " + this.tiposParametrosEjecucion);
         this.parametrosEjecucion = this.getParametros(parametros);
         this.logger.debug("Parámetros de ejecución: " + this.parametrosEjecucion);
      }

      if (!this.contieneCaracterEspecial(parametrosCasoError)) {
         this.tiposParametrosEjecucionError = this.getTiposParametrosOriginales(parametrosCasoError);
         this.parametrosEjecucionError = parametrosCasoError;
      } else {
         this.tiposParametrosEjecucionError = this.getTiposParametros(parametrosCasoError);
         this.logger.debug("Tipos del método de error: " + this.tiposParametrosEjecucion);
         this.parametrosEjecucionError = this.getParametros(parametrosCasoError);
         this.logger.debug("Parámetros de error: " + parametrosCasoError);
      }

      this.setTiempoInicio(System.currentTimeMillis());

      PersonalsoftException personal;
      try {
         this.metodoEjecucion = procesoProgramado.getClass().getMethod(metodoEjecucion, this.tiposParametrosEjecucion);
      } catch (NoSuchMethodException var12) {
         personal = new PersonalsoftException(var12);
         this.logger.error(personal.getTraza());
      }

      if (metodoEjecucionCasoError != null && !metodoEjecucionCasoError.equals("")) {
         try {
            this.metodoEjecucionCasoError = procesoProgramado.getClass().getMethod(metodoEjecucionCasoError, this.tiposParametrosEjecucionError);
         } catch (NoSuchMethodException var11) {
            personal = new PersonalsoftException(var11);
            this.logger.error(personal.getTraza());
         }
      }

      this.delegadoObservable = new DelegatedObservable();
      if (this.logger.isInfoEnabled()) {
         this.logger.info(this.toString());
      }

   }

   public Tarea(Object procesoProgramado, Method metodoEjecucion, Object[] parametros, Method metodoEjecucionCasoError, Object[] parametrosCasoError) {
      this.procesoProgramado = procesoProgramado;
      this.metodoEjecucion = metodoEjecucion;
      this.metodoEjecucionCasoError = metodoEjecucionCasoError;
      if (!this.contieneCaracterEspecial(parametros)) {
         this.tiposParametrosEjecucion = this.getTiposParametrosOriginales(parametros);
         this.parametrosEjecucion = parametros;
      } else {
         this.tiposParametrosEjecucion = this.getTiposParametros(parametros);
         this.logger.debug("Tipos: " + this.tiposParametrosEjecucion);
         this.parametrosEjecucion = this.getParametros(parametros);
         this.logger.debug("Parámetros de ejecución: " + this.parametrosEjecucion);
      }

      if (!this.contieneCaracterEspecial(parametrosCasoError)) {
         this.tiposParametrosEjecucionError = this.getTiposParametrosOriginales(parametrosCasoError);
         this.parametrosEjecucionError = parametrosCasoError;
      } else {
         this.tiposParametrosEjecucionError = this.getTiposParametros(parametrosCasoError);
         this.logger.debug("Tipos del método de error: " + this.tiposParametrosEjecucion);
         this.parametrosEjecucionError = this.getParametros(parametrosCasoError);
         this.logger.debug("Parámetros de error: " + parametrosCasoError);
      }

      this.setTiempoInicio(System.currentTimeMillis());
      this.delegadoObservable = new DelegatedObservable();
      if (this.logger.isInfoEnabled()) {
         this.logger.info(this.toString());
      }

   }

   public void run() {
      this.tareaEnProceso = true;
      PersonalsoftException personal = null;

      try {
         if (this.logger.isInfoEnabled()) {
            this.logger.info("Se ha iniciado la ejecución de la siguiente tarea programada:\n");
            this.logger.info(this.toString());
         }

         this.setResultadoEjecucionMetodo(this.metodoEjecucion.invoke(this.procesoProgramado, this.parametrosEjecucion));
         this.nombreMetodoEjecutado = this.metodoEjecucion.getName();
         this.tareaTerminadaConExito = true;
         this.tareaEnProceso = false;
      } catch (Exception var11) {
         Exception e = var11;

         try {
            this.tareaTerminadaConExito = false;
            personal = new PersonalsoftException(e);
            this.logger.error(personal.getTraza());
            this.logger.info("Ha fallado la ejecucion del siguiente proceso:\n");
            this.logger.info(this.procesoProgramado.toString());
            if (this.metodoEjecucionCasoError != null) {
               this.logger.info("Iniciando la ejecución del proceso de respaldo...\n");
               this.setResultadoEjecucionMetodo(this.metodoEjecucionCasoError.invoke(this.procesoProgramado, this.parametrosEjecucionError));
               this.nombreMetodoEjecutado = this.metodoEjecucionCasoError.getName();
            }

            this.tareaEnProceso = false;
         } catch (Exception var10) {
            this.nombreMetodoEjecutado = "No se ejecuto ningún método";
            this.tareaEnProceso = false;
            this.setResultadoEjecucionMetodo((Object)null);
            personal = new PersonalsoftException(var10);
            this.logger.error(personal.getTraza());
         }
      } finally {
         this.tareaEnProceso = false;
         this.tareaTerminada = true;
      }

      AgenteEjecucionConfiguracionHelper agenteEjecucionConfiguracionHelper = new AgenteEjecucionConfiguracionHelper();

      try {
         agenteEjecucionConfiguracionHelper.actualizarResultadoTarea(this);
      } catch (Exception var9) {
         personal = new PersonalsoftException(var9);
         this.logger.error(personal.getTraza());
      }

   }

   private Class[] getTiposParametros(Object[] parametros) {
      Class[] tiposParametrosEjecucionTmp = (Class[])null;
      if (parametros != null && parametros.length > 0) {
         tiposParametrosEjecucionTmp = new Class[parametros.length];

         for(int i = 0; i < parametros.length; ++i) {
            String[] valoresTmp = parametros[i].toString().split("Æ");
            if (valoresTmp[1].equalsIgnoreCase("int")) {
               tiposParametrosEjecucionTmp[i] = Integer.TYPE;
            } else if (valoresTmp[1].equalsIgnoreCase("float")) {
               tiposParametrosEjecucionTmp[i] = Float.TYPE;
            } else if (valoresTmp[1].equalsIgnoreCase("double")) {
               tiposParametrosEjecucionTmp[i] = Double.TYPE;
            } else if (valoresTmp[1].equalsIgnoreCase("char")) {
               tiposParametrosEjecucionTmp[i] = Character.TYPE;
            } else if (valoresTmp[1].equalsIgnoreCase("long")) {
               tiposParametrosEjecucionTmp[i] = Long.TYPE;
            } else if (valoresTmp[1].equalsIgnoreCase("boolean")) {
               tiposParametrosEjecucionTmp[i] = Boolean.TYPE;
            } else if (valoresTmp[1].equalsIgnoreCase("short")) {
               tiposParametrosEjecucionTmp[i] = Short.TYPE;
            } else {
               tiposParametrosEjecucionTmp[i] = String.class;
            }
         }

         return tiposParametrosEjecucionTmp;
      } else {
         return null;
      }
   }

   private Object[] getParametros(Object[] parametros) {
      Object[] parametrosEjecucionTmp = (Object[])null;
      if (parametros != null && parametros.length > 0) {
         parametrosEjecucionTmp = new Object[parametros.length];

         for(int i = 0; i < parametros.length; ++i) {
            String[] valoresTmp = parametros[i].toString().split("Æ");
            if (valoresTmp[1].equalsIgnoreCase("int")) {
               parametrosEjecucionTmp[i] = new Integer(valoresTmp[0]);
            } else if (valoresTmp[1].equalsIgnoreCase("float")) {
               parametrosEjecucionTmp[i] = new Float(valoresTmp[0]);
            } else if (valoresTmp[1].equalsIgnoreCase("double")) {
               parametrosEjecucionTmp[i] = new Double(valoresTmp[0]);
            } else if (valoresTmp[1].equalsIgnoreCase("char")) {
               parametrosEjecucionTmp[i] = new Character(valoresTmp[0].charAt(0));
            } else if (valoresTmp[1].equalsIgnoreCase("long")) {
               parametrosEjecucionTmp[i] = new Long(valoresTmp[0]);
            } else if (valoresTmp[1].equalsIgnoreCase("boolean")) {
               parametrosEjecucionTmp[i] = new Boolean(valoresTmp[0]);
            } else if (valoresTmp[1].equalsIgnoreCase("short")) {
               parametrosEjecucionTmp[i] = new Short(valoresTmp[0]);
            } else {
               parametrosEjecucionTmp[i] = valoresTmp[0];
            }
         }

         return parametrosEjecucionTmp;
      } else {
         return null;
      }
   }

   private boolean contieneCaracterEspecial(Object[] parametros) {
      return parametros != null && parametros.length > 0 && parametros[0].toString().indexOf("Æ") != -1;
   }

   private Class[] getTiposParametrosOriginales(Object[] parametros) {
      Class[] parametrosEjecucionTmp = (Class[])null;
      if (parametros != null && parametros.length > 0) {
         parametrosEjecucionTmp = new Class[parametros.length];

         for(int i = 0; i < parametros.length; ++i) {
            Object valoresTmp = parametros[i];
            if (valoresTmp != null) {
               parametrosEjecucionTmp[i] = valoresTmp.getClass();
            }
         }

         return parametrosEjecucionTmp;
      } else {
         return null;
      }
   }

   public void setResultadoEjecucionMetodo(Object resultadoEjecucionMetodo) {
      this.resultadoEjecucionMetodo = resultadoEjecucionMetodo;
      this.delegadoObservable.setChanged();
      this.delegadoObservable.notifyObservers(this.idTarea);
   }

   public String toString() {
      StringBuffer descripcion = new StringBuffer();
      descripcion.append("Caracteristicas de la tarea programada:");
      descripcion.append("\n\n");
      descripcion.append("Clase: " + this.procesoProgramado.getClass().getCanonicalName());
      descripcion.append("\n");
      descripcion.append("Metodo: " + this.metodoEjecucion.getName());
      descripcion.append("\n");
      descripcion.append(this.tiposParametrosEjecucion != null ? "Tipos de parametros recibidos: " + this.tiposParametrosEjecucion.toString() : "");
      descripcion.append("\n");
      if (this.metodoEjecucionCasoError != null) {
         descripcion.append("Método de respaldo (caso de error): " + this.metodoEjecucionCasoError.getName());
      }

      descripcion.append("\n");
      if (this.tiposParametrosEjecucionError != null) {
         descripcion.append("Tipos de parametros recibidos para método de respaldo: " + this.tiposParametrosEjecucionError.toString());
      }

      descripcion.append("\n");
      return descripcion.toString().replaceAll("\n\n", "\n");
   }

   public DelegatedObservable getDelegadoObservable() {
      return this.delegadoObservable;
   }

   public Observable getObservable() {
      return this.delegadoObservable;
   }

   public void addObserver(Observer observador) {
      this.delegadoObservable.addObserver(observador);
   }

   public void deleteObserver(Observer observador) {
      this.delegadoObservable.deleteObserver(observador);
   }

   public Object getResultadoEjecucionMetodo() {
      return this.resultadoEjecucionMetodo;
   }

   public String getIdTarea() {
      return this.idTarea;
   }

   public void setIdTarea(String idTarea) {
      this.idTarea = idTarea;
   }

   public Method getMetodoEjecucion() {
      return this.metodoEjecucion;
   }

   public void setMetodoEjecucion(Method metodoEjecucion) {
      this.metodoEjecucion = metodoEjecucion;
   }

   public Method getMetodoEjecucionCasoError() {
      return this.metodoEjecucionCasoError;
   }

   public void setMetodoEjecucionCasoError(Method metodoEjecucionCasoError) {
      this.metodoEjecucionCasoError = metodoEjecucionCasoError;
   }

   public Object[] getParametrosEjecucion() {
      return this.parametrosEjecucion;
   }

   public void setParametrosEjecucion(Object[] parametrosEjecucion) {
      this.parametrosEjecucion = parametrosEjecucion;
   }

   public Object[] getParametrosEjecucionError() {
      return this.parametrosEjecucionError;
   }

   public void setParametrosEjecucionError(Object[] parametrosEjecucionError) {
      this.parametrosEjecucionError = parametrosEjecucionError;
   }

   public Object getProcesoProgramado() {
      return this.procesoProgramado;
   }

   public void setProcesoProgramado(Object procesoProgramado) {
      this.procesoProgramado = procesoProgramado;
   }

   public boolean isTareaTerminada() {
      return this.tareaTerminada;
   }

   public void setTareaTerminada(boolean tareaTerminada) {
      this.tareaTerminada = tareaTerminada;
   }

   public boolean isTareaTerminadaConExito() {
      return this.tareaTerminadaConExito;
   }

   public void setTareaTerminadaConExito(boolean tareaTerminadaConExito) {
      this.tareaTerminadaConExito = tareaTerminadaConExito;
   }

   public Timer getTimer() {
      return this.timer;
   }

   public void setTimer(Timer timer) {
      this.timer = timer;
   }

   public boolean isTareaRecurrente() {
      return this.tareaRecurrente;
   }

   public void setTareaRecurrente(boolean tareaRecurrente) {
      this.tareaRecurrente = tareaRecurrente;
   }

   public boolean isTareaEnProceso() {
      return this.tareaEnProceso;
   }

   public void setTareaEnProceso(boolean tareaEnProceso) {
      this.tareaEnProceso = tareaEnProceso;
   }

   public TareaProgramada getTareaProgramada() {
      return this.tareaProgramada;
   }

   public void setTareaProgramada(TareaProgramada tareaProgramada) {
      this.tareaProgramada = tareaProgramada;
   }

   public long getTiempoInicio() {
      return this.tiempoInicio;
   }

   public void setTiempoInicio(long tiempoInicio) {
      this.tiempoInicio = tiempoInicio;
   }

   public boolean isTareaPersistente() {
      return this.tareaPersistente;
   }

   public void setTareaPersistente(boolean tareaPersistente) {
      this.tareaPersistente = tareaPersistente;
   }

   public Class[] getTiposParametrosEjecucion() {
      return this.tiposParametrosEjecucion;
   }

   public void setTiposParametrosEjecucion(Class[] tiposParametrosEjecucion) {
      this.tiposParametrosEjecucion = tiposParametrosEjecucion;
   }

   public Class[] getTiposParametrosEjecucionError() {
      return this.tiposParametrosEjecucionError;
   }

   public void setTiposParametrosEjecucionError(Class[] tiposParametrosEjecucionError) {
      this.tiposParametrosEjecucionError = tiposParametrosEjecucionError;
   }

   public int getIntervaloEjecucion() {
      return this.intervaloEjecucion;
   }

   public void setIntervaloEjecucion(int intervaloEjecucion) {
      this.intervaloEjecucion = intervaloEjecucion;
   }

   public String getUnidadTiempo() {
      return this.unidadTiempo;
   }

   public void setUnidadTiempo(String unidadTiempo) {
      this.unidadTiempo = unidadTiempo;
   }

   public Calendar getFechaEjecucion() {
      return this.fechaEjecucion;
   }

   public void setFechaEjecucion(Calendar fechaEjecucion) {
      this.fechaEjecucion = fechaEjecucion;
   }

   public String getNombreMetodoEjecutado() {
      return this.nombreMetodoEjecutado;
   }

   public void setNombreMetodoEjecutado(String nombreMetodoEjecutado) {
      this.nombreMetodoEjecutado = nombreMetodoEjecutado;
   }
}
