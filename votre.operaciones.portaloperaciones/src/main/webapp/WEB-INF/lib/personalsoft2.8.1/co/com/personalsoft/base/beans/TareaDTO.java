package co.com.personalsoft.base.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;

public class TareaDTO implements Serializable {
   private static final long serialVersionUID = 1501492629465833011L;
   private String mensajeUsuario = "";
   private String idTarea;
   private String nombre;
   private String nombreClase;
   private String metodoEjecucion;
   private String metodoEjecucionCasoError;
   private Object[] parametrosEjecucion;
   private Object[] parametrosEjecucionError;
   private String intervaloEjucionHoras;
   private String unidadTiempo;
   private String fechaEjecucion;
   private Calendar fecha;
   private String tareaPersistente;
   private Collection<Object> parametros;
   private String estado;
   private String fechaProgramacion;
   private String parametrosMetodoEjecucion;
   private String parametrosMetodoError;
   private int intervaloEjecucion;
   private boolean persistente;
   private String tareaPendiente;
   private String fechaEjecucionInicial;
   private String fechaEjecucionFinal;

   public String getIdTarea() {
      return this.idTarea;
   }

   public void setIdTarea(String idTarea) {
      this.idTarea = idTarea;
   }

   public String getMetodoEjecucion() {
      return this.metodoEjecucion;
   }

   public void setMetodoEjecucion(String metodoEjecucion) {
      this.metodoEjecucion = metodoEjecucion;
   }

   public String getMetodoEjecucionCasoError() {
      return this.metodoEjecucionCasoError;
   }

   public void setMetodoEjecucionCasoError(String metodoEjecucionCasoError) {
      this.metodoEjecucionCasoError = metodoEjecucionCasoError;
   }

   public String getNombreClase() {
      return this.nombreClase;
   }

   public void setNombreClase(String nombreClase) {
      this.nombreClase = nombreClase;
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

   public String getNombre() {
      return this.nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getIntervaloEjucionHoras() {
      return this.intervaloEjucionHoras;
   }

   public void setIntervaloEjucionHoras(String intervaloEjucionHoras) {
      this.intervaloEjucionHoras = intervaloEjucionHoras;
   }

   public String getUnidadTiempo() {
      return this.unidadTiempo;
   }

   public void setUnidadTiempo(String unidadTiempo) {
      this.unidadTiempo = unidadTiempo;
   }

   public String getFechaEjecucion() {
      return this.fechaEjecucion;
   }

   public void setFechaEjecucion(String fechaEjecucion) {
      this.fechaEjecucion = fechaEjecucion;
   }

   public Calendar getFecha() {
      return this.fecha;
   }

   public void setFecha(Calendar fecha) {
      this.fecha = fecha;
   }

   public String isTareaPersistente() {
      return this.tareaPersistente;
   }

   public void setTareaPersistente(String tareaPersistente) {
      this.tareaPersistente = tareaPersistente;
   }

   public Collection<Object> getParametros() {
      return this.parametros;
   }

   public void setParametros(Collection<Object> parametros) {
      this.parametros = parametros;
   }

   public String getTareaPersistente() {
      return this.tareaPersistente;
   }

   public String getEstado() {
      return this.estado;
   }

   public void setEstado(String estado) {
      this.estado = estado;
   }

   public String getFechaProgramacion() {
      return this.fechaProgramacion;
   }

   public void setFechaProgramacion(String fechaProgramacion) {
      this.fechaProgramacion = fechaProgramacion;
   }

   public int getIntervaloEjecucion() {
      return this.intervaloEjecucion;
   }

   public String getParametrosMetodoEjecucion() {
      return this.parametrosMetodoEjecucion;
   }

   public void setParametrosMetodoEjecucion(String parametrosMetodoEjecucion) {
      this.parametrosMetodoEjecucion = parametrosMetodoEjecucion;
   }

   public String getParametrosMetodoError() {
      return this.parametrosMetodoError;
   }

   public void setParametrosMetodoError(String parametrosMetodoError) {
      this.parametrosMetodoError = parametrosMetodoError;
   }

   public boolean isPersistente() {
      return this.persistente;
   }

   public void setPersistente(boolean persistente) {
      this.persistente = persistente;
   }

   public void setIntervaloEjecucion(int intervaloEjecucion) {
      this.intervaloEjecucion = intervaloEjecucion;
   }

   public String getMensajeUsuario() {
      return this.mensajeUsuario;
   }

   public void setMensajeUsuario(String mensajeUsuario) {
      this.mensajeUsuario = mensajeUsuario;
   }

   public String getTareaPendiente() {
      return this.tareaPendiente;
   }

   public void setTareaPendiente(String tareaPendiente) {
      this.tareaPendiente = tareaPendiente;
   }

   public String getFechaEjecucionFinal() {
      return this.fechaEjecucionFinal;
   }

   public void setFechaEjecucionFinal(String fechaEjecucionFinal) {
      this.fechaEjecucionFinal = fechaEjecucionFinal;
   }

   public String getFechaEjecucionInicial() {
      return this.fechaEjecucionInicial;
   }

   public void setFechaEjecucionInicial(String fechaEjecucionInicial) {
      this.fechaEjecucionInicial = fechaEjecucionInicial;
   }
}
