package co.com.personalsoft.base.programacion.control;

import co.com.personalsoft.base.beans.TareaDTO;
import co.com.personalsoft.base.programacion.tareas.Tarea;
import co.com.personalsoft.base.util.Fecha;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import org.apache.log4j.Logger;

public class AgenteEjecucionConfiguracionHelper implements Serializable {
   private static final long serialVersionUID = 5991972946041014478L;
   private static Logger logger = Logger.getLogger(AgenteEjecucionConfiguracionHelper.class);

   public String dtoAssembler(Tarea tarea) {
      StringBuilder str = new StringBuilder();
      String metodoError = " ";
      String fechaEjecucion = " ";
      String resultadoMetodo = "";
      String estado = " ";
      String nombreMetodoEjecutado = " - ";
      if (tarea.getMetodoEjecucionCasoError() != null) {
         metodoError = tarea.getMetodoEjecucionCasoError().getName().trim();
         if (metodoError.equals("")) {
            metodoError = " ";
         }
      }

      if (tarea.getFechaEjecucion() != null) {
         fechaEjecucion = Fecha.cambiarTipoDato(tarea.getFechaEjecucion().getTime(), "yyyy/MM/dd HH:mm:ss");
         if (fechaEjecucion.equals("")) {
            fechaEjecucion = " ";
         }
      }

      str.append(tarea.getIdTarea() + "Æ");
      str.append(tarea.getName().trim() + "Æ");
      str.append(tarea.getProcesoProgramado().getClass().getCanonicalName() + "Æ");
      str.append(tarea.getMetodoEjecucion().getName().trim() + "Æ");
      str.append(metodoError + "Æ");
      str.append(tarea.getIntervaloEjecucion() + "Æ");
      str.append(tarea.getUnidadTiempo() + "Æ");
      str.append(fechaEjecucion + "Æ");
      str.append("N");
      int k;
      if (tarea.getParametrosEjecucion() != null && tarea.getParametrosEjecucion().length > 0) {
         for(k = 0; k < tarea.getParametrosEjecucion().length; ++k) {
            str.append("Æ" + tarea.getTiposParametrosEjecucion()[k].getSimpleName() + "," + tarea.getParametrosEjecucion()[k]);
         }
      }

      if (tarea.getParametrosEjecucionError() != null && tarea.getParametrosEjecucionError().length > 0) {
         for(k = 0; k < tarea.getParametrosEjecucionError().length; ++k) {
            str.append("Æ" + tarea.getTiposParametrosEjecucionError()[k].getSimpleName() + ";" + tarea.getParametrosEjecucionError()[k]);
         }
      }

      resultadoMetodo = tarea.getResultadoEjecucionMetodo() != null ? tarea.getResultadoEjecucionMetodo().toString() : "-";
      if (tarea.isTareaTerminada() && tarea.isTareaTerminadaConExito()) {
         estado = "Terminada";
      } else if (tarea.isTareaEnProceso()) {
         estado = "En proceso";
      } else if (tarea.isTareaTerminada() && !tarea.isTareaTerminadaConExito()) {
         estado = "Terminada con error";
      }

      if (tarea.getNombreMetodoEjecutado() != null) {
         nombreMetodoEjecutado = tarea.getNombreMetodoEjecutado();
         if (nombreMetodoEjecutado.equals("")) {
            nombreMetodoEjecutado = " - ";
         }
      }

      resultadoMetodo = estado + "€" + fechaEjecucion + "€" + resultadoMetodo + "€" + nombreMetodoEjecutado;
      str.append("Æ" + resultadoMetodo);
      return str.toString();
   }

   public Tarea configurarTarea(Object clase, Tarea tarea, String tareaObj, String nombre, String nombreMetodoEjecucion, Object[] parametrosEjecucion, String metodoCasoError, Object[] parametrosCasosError, Calendar nuevaFechaEjecucion, int intervaloEjecucion, boolean tareaPersistente, String unidadTiempo) throws Exception {
      if (parametrosEjecucion != null && parametrosEjecucion.length > 0 && parametrosCasosError != null && parametrosCasosError.length > 0) {
         tarea = new Tarea(clase, nombreMetodoEjecucion, parametrosEjecucion, metodoCasoError, parametrosCasosError, intervaloEjecucion, unidadTiempo, nuevaFechaEjecucion);
      } else if (parametrosEjecucion != null && parametrosEjecucion.length > 0 && parametrosCasosError == null) {
         tarea = new Tarea(clase, nombreMetodoEjecucion, parametrosEjecucion, metodoCasoError, (Object[])null, intervaloEjecucion, unidadTiempo, nuevaFechaEjecucion);
      } else if (parametrosEjecucion == null && parametrosCasosError != null && parametrosCasosError.length > 0) {
         tarea = new Tarea(clase, nombreMetodoEjecucion, (Object[])null, metodoCasoError, parametrosCasosError, intervaloEjecucion, unidadTiempo, nuevaFechaEjecucion);
      } else if (parametrosEjecucion == null && parametrosCasosError == null) {
         tarea = new Tarea(clase, nombreMetodoEjecucion, (Object[])null, metodoCasoError, (Object[])null, intervaloEjecucion, unidadTiempo, nuevaFechaEjecucion);
      }

      return tarea;
   }

   public Tarea reprogramarTarea(Tarea tarea) throws Exception {
      AgenteEjecucionBDHelper agenteEjecucionBDHelper = new AgenteEjecucionBDHelper();
      String consultarPersistenciaTareasBD = agenteEjecucionBDHelper.consultarHabilitarPersistenciaTareasBD();
      AgenteEjecucionHelper agenteEjecucionHelper = new AgenteEjecucionHelper();
      if (consultarPersistenciaTareasBD.equalsIgnoreCase("S")) {
         String idTarea = agenteEjecucionBDHelper.consultarTareaProgramadaBD(tarea.getIdTarea());
         String consultarHistoricoAgenteTareasBD;
         if (idTarea != null) {
            if (tarea.isTareaPersistente()) {
               consultarHistoricoAgenteTareasBD = null;
               if (tarea.getFechaEjecucion() != null) {
                  consultarHistoricoAgenteTareasBD = Fecha.cambiarTipoDato(tarea.getFechaEjecucion().getTime(), "yyyy-MM-dd HH:mm:ss");
               }

               agenteEjecucionBDHelper.actualizarTarea(tarea, consultarHistoricoAgenteTareasBD, tarea.getUnidadTiempo(), tarea.getIntervaloEjecucion());
            } else if (!tarea.isTareaPersistente()) {
               consultarHistoricoAgenteTareasBD = agenteEjecucionBDHelper.consultarHistoricoAgenteTareasBD();
               if (consultarHistoricoAgenteTareasBD.equalsIgnoreCase("N")) {
                  agenteEjecucionBDHelper.eliminarTarea(tarea);
               }
            }
         } else if (tarea.isTareaPersistente()) {
            consultarHistoricoAgenteTareasBD = null;
            if (tarea.getFechaEjecucion() != null) {
               consultarHistoricoAgenteTareasBD = Fecha.cambiarTipoDato(tarea.getFechaEjecucion().getTime(), "yyyy-MM-dd HH:mm:ss");
            }

            agenteEjecucionBDHelper.adicionarTarea(tarea, consultarHistoricoAgenteTareasBD, tarea.getUnidadTiempo(), tarea.getIntervaloEjecucion());
         }
      } else {
         logger.warn(agenteEjecucionHelper.mensajeWarning());
      }

      return tarea;
   }

   public Tarea programarTarea(Tarea tarea) throws Exception {
      AgenteEjecucionBDHelper agenteEjecucionBDHelper = new AgenteEjecucionBDHelper();
      String consultarPersistenciaTareasBD = agenteEjecucionBDHelper.consultarHabilitarPersistenciaTareasBD();
      AgenteEjecucionHelper agenteEjecucionHelper = new AgenteEjecucionHelper();
      if (consultarPersistenciaTareasBD.equalsIgnoreCase("S")) {
         if (tarea.isTareaPersistente()) {
            String fechaEjecucionTarea = null;
            if (tarea.getFechaEjecucion() != null) {
               fechaEjecucionTarea = Fecha.cambiarTipoDato(tarea.getFechaEjecucion().getTime(), "yyyy-MM-dd HH:mm:ss");
            }

            agenteEjecucionBDHelper.adicionarTarea(tarea, fechaEjecucionTarea, tarea.getUnidadTiempo(), tarea.getIntervaloEjecucion());
         }
      } else {
         logger.warn(agenteEjecucionHelper.mensajeWarning());
      }

      return tarea;
   }

   public String[] dtoAssembler(ArrayList<TareaDTO> data) {
      ArrayList<String> str = new ArrayList();
      if (data != null && !data.isEmpty()) {
         Iterator var4 = data.iterator();

         while(var4.hasNext()) {
            TareaDTO tareaDTO = (TareaDTO)var4.next();
            str.add(this.dtoAssembler(tareaDTO));
         }
      }

      return str != null && !str.isEmpty() ? (String[])str.toArray(new String[0]) : new String[0];
   }

   private String dtoAssembler(TareaDTO tareaDTO) {
      StringBuilder str = new StringBuilder();
      String unidadEjecucion = tareaDTO.getIntervaloEjecucion() + " " + tareaDTO.getUnidadTiempo();
      String estado = " ";
      str.append(tareaDTO.getIdTarea() + "Æ");
      str.append(tareaDTO.getNombre() + "Æ");
      str.append(unidadEjecucion + "Æ");
      if (tareaDTO.getTareaPendiente().equalsIgnoreCase("N")) {
         estado = "Terminada";
      } else {
         estado = "Pendiente";
      }

      str.append(estado + "Æ");
      str.append(tareaDTO.getFechaProgramacion() + "Æ");
      return str.toString();
   }

   public void actualizarResultadoTarea(Tarea tarea) throws Exception {
      AgenteEjecucionBDHelper agenteEjecucionBDHelper = new AgenteEjecucionBDHelper();
      String consultarPersistenciaTareasBD = agenteEjecucionBDHelper.consultarHabilitarPersistenciaTareasBD();
      AgenteEjecucionHelper agenteEjecucionHelper = new AgenteEjecucionHelper();
      if (consultarPersistenciaTareasBD.equalsIgnoreCase("S")) {
         agenteEjecucionBDHelper.actualizarResultadoTarea(tarea);
      } else {
         logger.warn(agenteEjecucionHelper.mensajeWarning());
      }

   }

   public String[] buscarTareas(String nombre, boolean isPersistente, String idTarea, String estado, String fechaEjecucion, String fechaEjecucionInicial, String fechaEjecucionFinal) throws Exception {
      ArrayList<String> tareas = new ArrayList();
      TareaDTO tareaDTO = new TareaDTO();
      AgenteEjecucionBDHelper agenteEjecucionBDHelper = new AgenteEjecucionBDHelper();
      String consultarPersistenciaTareasBD = agenteEjecucionBDHelper.consultarHabilitarPersistenciaTareasBD();
      Collection<Tarea> data = AgenteEjecucion.getInstance().getColaProcesos();
      tareaDTO.setNombre(nombre);
      tareaDTO.setIdTarea(idTarea);
      tareaDTO.setEstado(estado);
      tareaDTO.setFechaEjecucionInicial(fechaEjecucionInicial);
      tareaDTO.setFechaEjecucionFinal(fechaEjecucionFinal);
      if (isPersistente && consultarPersistenciaTareasBD.equalsIgnoreCase("S")) {
         tareaDTO.setFechaEjecucion(fechaEjecucion);
         if (estado.equalsIgnoreCase("P")) {
            tareaDTO.setEstado("S");
         } else {
            tareaDTO.setEstado((String)null);
         }

         tareas = agenteEjecucionBDHelper.consultarTareasProgramadasBD(tareaDTO);
      } else {
         tareas = this.configurarInformacionColas(tareaDTO, data, tareas);
      }

      return tareas != null && !tareas.isEmpty() ? (String[])tareas.toArray(new String[0]) : new String[0];
   }

   private ArrayList<String> configurarInformacionColas(TareaDTO tareaDTO, Collection<Tarea> colTareas, ArrayList<String> tareas) {
      if (colTareas != null && !colTareas.isEmpty()) {
         AgenteEjecucionHelper agenteEjecucionHelper = new AgenteEjecucionHelper();
         Iterator var6 = colTareas.iterator();

         while(true) {
            while(true) {
               Tarea tarea;
               do {
                  if (!var6.hasNext()) {
                     return tareas;
                  }

                  tarea = (Tarea)var6.next();
               } while(tarea.isTareaPersistente());

               if (tareaDTO.getEstado() == null && tareaDTO.getIdTarea() == null && tareaDTO.getNombre() == null && tareaDTO.getFechaEjecucionInicial() == null && tareaDTO.getFechaEjecucionFinal() == null) {
                  tareas.add(agenteEjecucionHelper.dtoAssembler(tarea));
               } else {
                  Calendar fechaEjecucionInicial;
                  Calendar fechaEjecucionFinal;
                  if (tareaDTO.getEstado() != null && tareaDTO.getIdTarea() != null && tareaDTO.getNombre() != null && tareaDTO.getFechaEjecucionInicial() != null && tareaDTO.getFechaEjecucionFinal() != null) {
                     fechaEjecucionInicial = GregorianCalendar.getInstance();
                     fechaEjecucionInicial.setTime(Fecha.cambiarTipoDatoDate(tareaDTO.getFechaEjecucionInicial(), "yyyy/MM/dd"));
                     fechaEjecucionFinal = GregorianCalendar.getInstance();
                     fechaEjecucionFinal.setTime(Fecha.cambiarTipoDatoDate(tareaDTO.getFechaEjecucionFinal(), "yyyy/MM/dd"));
                     if ((tareaDTO.getEstado().equalsIgnoreCase("P") && tarea.isTareaEnProceso() || tareaDTO.getEstado().equalsIgnoreCase("T") && tarea.isTareaTerminada() || tareaDTO.getEstado().equalsIgnoreCase("E") && tarea.isTareaTerminadaConExito() || tareaDTO.getEstado().equalsIgnoreCase("NE") && !tarea.isTareaTerminadaConExito() || tareaDTO.getEstado().equalsIgnoreCase("N") && !tarea.isTareaTerminada() || tareaDTO.getEstado().equalsIgnoreCase("0")) && tareaDTO.getIdTarea().equals(tarea.getIdTarea()) && tareaDTO.getNombre().equals(tarea.getName()) && tarea.getFechaEjecucion() != null && tarea.getFechaEjecucion().getTime().getTime() >= fechaEjecucionInicial.getTime().getTime() && tarea.getFechaEjecucion().getTime().getTime() <= fechaEjecucionFinal.getTime().getTime()) {
                        tareas.add(agenteEjecucionHelper.dtoAssembler(tarea));
                     }
                  } else if (tareaDTO.getEstado() != null && tareaDTO.getIdTarea() != null && tareaDTO.getNombre() != null) {
                     if ((tareaDTO.getEstado().equalsIgnoreCase("P") && tarea.isTareaEnProceso() || tareaDTO.getEstado().equalsIgnoreCase("T") && tarea.isTareaTerminada() || tareaDTO.getEstado().equalsIgnoreCase("E") && tarea.isTareaTerminadaConExito() || tareaDTO.getEstado().equalsIgnoreCase("NE") && !tarea.isTareaTerminadaConExito() || tareaDTO.getEstado().equalsIgnoreCase("N") && !tarea.isTareaTerminada() || tareaDTO.getEstado().equalsIgnoreCase("0")) && tareaDTO.getIdTarea().equals(tarea.getIdTarea()) && tareaDTO.getNombre().equalsIgnoreCase(tarea.getName())) {
                        tareas.add(agenteEjecucionHelper.dtoAssembler(tarea));
                     }
                  } else if (tareaDTO.getEstado() != null && tareaDTO.getNombre() != null && tareaDTO.getFechaEjecucionInicial() != null && tareaDTO.getFechaEjecucionFinal() != null) {
                     fechaEjecucionInicial = GregorianCalendar.getInstance();
                     fechaEjecucionInicial.setTime(Fecha.cambiarTipoDatoDate(tareaDTO.getFechaEjecucionInicial(), "yyyy/MM/dd"));
                     fechaEjecucionFinal = GregorianCalendar.getInstance();
                     fechaEjecucionFinal.setTime(Fecha.cambiarTipoDatoDate(tareaDTO.getFechaEjecucionFinal(), "yyyy/MM/dd"));
                     if ((tareaDTO.getEstado().equalsIgnoreCase("P") && tarea.isTareaEnProceso() || tareaDTO.getEstado().equalsIgnoreCase("T") && tarea.isTareaTerminada() || tareaDTO.getEstado().equalsIgnoreCase("E") && tarea.isTareaTerminadaConExito() || tareaDTO.getEstado().equalsIgnoreCase("NE") && !tarea.isTareaTerminadaConExito() || tareaDTO.getEstado().equalsIgnoreCase("N") && !tarea.isTareaTerminada() || tareaDTO.getEstado().equalsIgnoreCase("0")) && tarea.getName().equals(tareaDTO.getNombre()) && tarea.getFechaEjecucion().getTime().getTime() >= fechaEjecucionInicial.getTime().getTime() && tarea.getFechaEjecucion().getTime().getTime() <= fechaEjecucionFinal.getTime().getTime()) {
                        tareas.add(agenteEjecucionHelper.dtoAssembler(tarea));
                     }
                  } else if (tareaDTO.getNombre() != null && tareaDTO.getIdTarea() != null && tareaDTO.getFechaEjecucionInicial() != null && tareaDTO.getFechaEjecucionFinal() != null) {
                     fechaEjecucionInicial = GregorianCalendar.getInstance();
                     fechaEjecucionInicial.setTime(Fecha.cambiarTipoDatoDate(tareaDTO.getFechaEjecucionInicial(), "yyyy/MM/dd"));
                     fechaEjecucionFinal = GregorianCalendar.getInstance();
                     fechaEjecucionFinal.setTime(Fecha.cambiarTipoDatoDate(tareaDTO.getFechaEjecucionFinal(), "yyyy/MM/dd"));
                     if (tarea.getName().equalsIgnoreCase(tareaDTO.getNombre()) && tarea.getIdTarea().equals(tareaDTO.getIdTarea()) && tarea.getFechaEjecucion() != null && tarea.getFechaEjecucion().getTime().getTime() >= fechaEjecucionInicial.getTime().getTime() && tarea.getFechaEjecucion().getTime().getTime() <= fechaEjecucionFinal.getTime().getTime()) {
                        tareas.add(agenteEjecucionHelper.dtoAssembler(tarea));
                     }
                  } else if (tareaDTO.getEstado() != null && tareaDTO.getIdTarea() != null && tareaDTO.getFechaEjecucionInicial() != null && tareaDTO.getFechaEjecucionFinal() != null) {
                     fechaEjecucionInicial = GregorianCalendar.getInstance();
                     fechaEjecucionInicial.setTime(Fecha.cambiarTipoDatoDate(tareaDTO.getFechaEjecucionInicial(), "yyyy/MM/dd"));
                     fechaEjecucionFinal = GregorianCalendar.getInstance();
                     fechaEjecucionFinal.setTime(Fecha.cambiarTipoDatoDate(tareaDTO.getFechaEjecucionFinal(), "yyyy/MM/dd"));
                     if ((tareaDTO.getEstado().equalsIgnoreCase("P") && tarea.isTareaEnProceso() || tareaDTO.getEstado().equalsIgnoreCase("T") && tarea.isTareaTerminada() && tarea.isTareaTerminadaConExito() || tareaDTO.getEstado().equalsIgnoreCase("E") && tarea.isTareaTerminada() && !tarea.isTareaTerminadaConExito() || tareaDTO.getEstado().equalsIgnoreCase("0")) && tareaDTO.getIdTarea().equals(tarea.getIdTarea()) && tarea.getFechaEjecucion() != null && tarea.getFechaEjecucion().getTime().getTime() >= fechaEjecucionInicial.getTime().getTime() && tarea.getFechaEjecucion().getTime().getTime() <= fechaEjecucionFinal.getTime().getTime()) {
                        tareas.add(agenteEjecucionHelper.dtoAssembler(tarea));
                     }
                  } else if (tareaDTO.getIdTarea() != null && tareaDTO.getFechaEjecucionInicial() != null && tareaDTO.getFechaEjecucionFinal() != null) {
                     fechaEjecucionInicial = GregorianCalendar.getInstance();
                     fechaEjecucionInicial.setTime(Fecha.cambiarTipoDatoDate(tareaDTO.getFechaEjecucionInicial(), "yyyy/MM/dd"));
                     fechaEjecucionFinal = GregorianCalendar.getInstance();
                     fechaEjecucionFinal.setTime(Fecha.cambiarTipoDatoDate(tareaDTO.getFechaEjecucionFinal(), "yyyy/MM/dd"));
                     if (tarea.getIdTarea().equals(tareaDTO.getIdTarea()) && tarea.getFechaEjecucion() != null && tarea.getFechaEjecucion().getTime().getTime() >= fechaEjecucionInicial.getTime().getTime() && tarea.getFechaEjecucion().getTime().getTime() <= fechaEjecucionFinal.getTime().getTime()) {
                        tareas.add(agenteEjecucionHelper.dtoAssembler(tarea));
                     }
                  } else if (tareaDTO.getEstado() != null && tareaDTO.getNombre() != null) {
                     if ((tareaDTO.getEstado().equalsIgnoreCase("P") && tarea.isTareaEnProceso() || tareaDTO.getEstado().equalsIgnoreCase("T") && tarea.isTareaTerminada() && tarea.isTareaTerminadaConExito() || tareaDTO.getEstado().equalsIgnoreCase("E") && tarea.isTareaTerminada() && !tarea.isTareaTerminadaConExito() || tareaDTO.getEstado().equalsIgnoreCase("0")) && tareaDTO.getNombre().equals(tarea.getName())) {
                        tareas.add(agenteEjecucionHelper.dtoAssembler(tarea));
                     }
                  } else if (tareaDTO.getEstado() != null && tareaDTO.getIdTarea() != null) {
                     if ((tareaDTO.getEstado().equalsIgnoreCase("P") && tarea.isTareaEnProceso() || tareaDTO.getEstado().equalsIgnoreCase("T") && tarea.isTareaTerminada() && tarea.isTareaTerminadaConExito() || tareaDTO.getEstado().equalsIgnoreCase("E") && tarea.isTareaTerminada() && !tarea.isTareaTerminadaConExito() || tareaDTO.getEstado().equalsIgnoreCase("0")) && tareaDTO.getIdTarea().equals(tarea.getIdTarea())) {
                        tareas.add(agenteEjecucionHelper.dtoAssembler(tarea));
                     }
                  } else if (tareaDTO.getIdTarea() != null && tareaDTO.getNombre() != null) {
                     if (tareaDTO.getIdTarea().equals(tarea.getIdTarea()) && tareaDTO.getNombre().equalsIgnoreCase(tarea.getName())) {
                        tareas.add(agenteEjecucionHelper.dtoAssembler(tarea));
                     }
                  } else if (tareaDTO.getNombre() != null && tareaDTO.getFechaEjecucionInicial() != null && tareaDTO.getFechaEjecucionFinal() != null) {
                     fechaEjecucionInicial = GregorianCalendar.getInstance();
                     fechaEjecucionInicial.setTime(Fecha.cambiarTipoDatoDate(tareaDTO.getFechaEjecucionInicial(), "yyyy/MM/dd"));
                     fechaEjecucionFinal = GregorianCalendar.getInstance();
                     fechaEjecucionFinal.setTime(Fecha.cambiarTipoDatoDate(tareaDTO.getFechaEjecucionFinal(), "yyyy/MM/dd"));
                     if (tarea.getName().equalsIgnoreCase(tareaDTO.getNombre()) && tarea.getFechaEjecucion() != null && tarea.getFechaEjecucion().getTime().getTime() >= fechaEjecucionInicial.getTime().getTime() && tarea.getFechaEjecucion().getTime().getTime() <= fechaEjecucionFinal.getTime().getTime()) {
                        tareas.add(agenteEjecucionHelper.dtoAssembler(tarea));
                     }
                  } else if (tareaDTO.getEstado() != null && tareaDTO.getFechaEjecucionInicial() != null && tareaDTO.getFechaEjecucionFinal() != null) {
                     fechaEjecucionInicial = GregorianCalendar.getInstance();
                     fechaEjecucionInicial.setTime(Fecha.cambiarTipoDatoDate(tareaDTO.getFechaEjecucionInicial(), "yyyy/MM/dd"));
                     fechaEjecucionFinal = GregorianCalendar.getInstance();
                     fechaEjecucionFinal.setTime(Fecha.cambiarTipoDatoDate(tareaDTO.getFechaEjecucionFinal(), "yyyy/MM/dd"));
                     if ((tareaDTO.getEstado().equalsIgnoreCase("P") && tarea.isTareaEnProceso() || tareaDTO.getEstado().equalsIgnoreCase("T") && tarea.isTareaTerminada() && tarea.isTareaTerminadaConExito() || tareaDTO.getEstado().equalsIgnoreCase("E") && tarea.isTareaTerminada() && !tarea.isTareaTerminadaConExito() || tareaDTO.getEstado().equalsIgnoreCase("0")) && tarea.getFechaEjecucion() != null && tarea.getFechaEjecucion().getTime().getTime() >= fechaEjecucionInicial.getTime().getTime() && tarea.getFechaEjecucion().getTime().getTime() <= fechaEjecucionFinal.getTime().getTime()) {
                        tareas.add(agenteEjecucionHelper.dtoAssembler(tarea));
                     }
                  } else if (tareaDTO.getFechaEjecucionInicial() != null && tareaDTO.getFechaEjecucionFinal() != null) {
                     fechaEjecucionInicial = GregorianCalendar.getInstance();
                     fechaEjecucionInicial.setTime(Fecha.cambiarTipoDatoDate(tareaDTO.getFechaEjecucionInicial(), "yyyy/MM/dd"));
                     fechaEjecucionFinal = GregorianCalendar.getInstance();
                     fechaEjecucionFinal.setTime(Fecha.cambiarTipoDatoDate(tareaDTO.getFechaEjecucionFinal(), "yyyy/MM/dd"));
                     if (tarea.getFechaEjecucion() != null && tarea.getFechaEjecucion().getTime().getTime() >= fechaEjecucionInicial.getTime().getTime() && tarea.getFechaEjecucion().getTime().getTime() <= fechaEjecucionFinal.getTime().getTime()) {
                        tareas.add(agenteEjecucionHelper.dtoAssembler(tarea));
                     }
                  } else if (tareaDTO.getEstado() != null && (tareaDTO.getEstado().equalsIgnoreCase("P") && tarea.isTareaEnProceso() || tareaDTO.getEstado().equalsIgnoreCase("T") && tarea.isTareaTerminada() && tarea.isTareaTerminadaConExito() || tareaDTO.getEstado().equalsIgnoreCase("E") && tarea.isTareaTerminada() && !tarea.isTareaTerminadaConExito() || tareaDTO.getEstado().equalsIgnoreCase("0"))) {
                     tareas.add(agenteEjecucionHelper.dtoAssembler(tarea));
                  }
               }
            }
         }
      } else {
         return tareas;
      }
   }
}
