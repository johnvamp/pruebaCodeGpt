package co.com.personalsoft.seguridad.dao;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.paginacion.beans.PaginacionDTO;
import co.com.personalsoft.base.seguridad.beans.AplicacionSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.PerfilSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.RecursoSeguridadDTO;
import co.com.personalsoft.base.util.CargadorMsj;
import co.com.personalsoft.seguridad.servicio.GestorSeguridad;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecursosDAO {
   private BDHelper bdHelper;
   private boolean ejecucionPorQuerys;

   public RecursosDAO() {
      String tipoRecurso = (String)GestorSeguridad.getInstance().getParametro("TIPO");
      this.ejecucionPorQuerys = tipoRecurso != null && tipoRecurso.equals("SQL");
   }

   public RecursosDAO(BDHelper helper) {
      this();
      this.bdHelper = helper;
   }

   public Map<String, RecursoSeguridadDTO> consultarPerfilesRecurso(AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("perfilesRecurso");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      Map<String, RecursoSeguridadDTO> perfilesRecurso = null;
      RecursoSeguridadDTO recursoSeguridadDTO = null;
      Set<String> recursosPermitidos = null;
      PersonalsoftException personalsoftException = null;
      String codigoRecursoActual = "";
      String mensajeUsuario = null;
      String mensajeTecnico = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         perfilesRecurso = new HashMap();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  mensajeTecnico = cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "";
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     mensajeUsuario = CargadorMsj.getInstance().getMensaje(mensajeUsuario);
                  } else {
                     mensajeUsuario = "";
                  }

                  if (mensajeTecnico != null && !mensajeTecnico.equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(mensajeTecnico);
                     personalsoftException.setMensajeUsuario(mensajeUsuario);
                     throw personalsoftException;
                  }

                  rs = cs.getResultSet();
               }
            }

            if (rs != null) {
               recursoSeguridadDTO = new RecursoSeguridadDTO();

               while(rs.next()) {
                  codigoRecursoActual = rs.getString("IDRECURSO");
                  if (!codigoRecursoActual.equals(recursoSeguridadDTO.getCodigoRecurso())) {
                     recursoSeguridadDTO = new RecursoSeguridadDTO();
                     recursoSeguridadDTO.setCodigoRecurso(codigoRecursoActual);
                     recursoSeguridadDTO.setNombreRecurso(rs.getString("DSRECURSO"));
                     recursosPermitidos = new HashSet();
                  }

                  recursosPermitidos.add(rs.getString("IDPERFIL"));
                  recursoSeguridadDTO.setPerfilesPermitidos(recursosPermitidos);
                  perfilesRecurso.put(recursoSeguridadDTO.getNombreRecurso(), recursoSeguridadDTO);
               }
            }
         } catch (SQLException var22) {
            personalsoftException = new PersonalsoftException(var22);
            personalsoftException.setMensajeTecnico(personalsoftException.getTraza());
            personalsoftException.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("001"));
            throw personalsoftException;
         } finally {
            try {
               BDHelper.close(rs);
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var21) {
               personalsoftException = new PersonalsoftException(var21);
               throw personalsoftException;
            }
         }
      }

      return perfilesRecurso;
   }

   public List<RecursoSeguridadDTO> consultarRecursos(RecursoSeguridadDTO recursoSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO, PaginacionDTO paginacionDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("listarRecursosPaginacion");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      List<RecursoSeguridadDTO> recursos = null;
      RecursoSeguridadDTO recursoSeguridadDataDTO = null;
      PersonalsoftException personalsoftException = null;
      BigDecimal totalRegistrosSp = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         recursos = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDRECURSO", 4, recursoSeguridadDTO.getCodigoRecurso() != null && !recursoSeguridadDTO.getCodigoRecurso().trim().equals("") ? new Integer(recursoSeguridadDTO.getCodigoRecurso()) : null));
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("DSRECURSO", 12, recursoSeguridadDTO.getNombreRecurso()));
               parametros.add(new Parametro("REGINI", 12, paginacionDTO.getRegistroInicial()));
               parametros.add(new Parametro("REGFIN", 12, paginacionDTO.getRegistroFinal()));
               parametros.add(new Parametro("NUMREG", 2, paginacionDTO.getRegistrosPorPagina()));
               parametros.add(new Parametro("CONTAVANCE", 2, paginacionDTO.getControlAvance()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro(12, paginacionDTO.getRegistroInicial(), 1));
               parametros.add(new Parametro(12, paginacionDTO.getRegistroFinal(), 1));
               parametros.add(new Parametro(4, recursoSeguridadDTO.getCodigoRecurso() != null && !recursoSeguridadDTO.getCodigoRecurso().trim().equals("") ? new Integer(recursoSeguridadDTO.getCodigoRecurso()) : null, 1));
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(12, recursoSeguridadDTO.getNombreRecurso(), 1));
               parametros.add(new Parametro(2, paginacionDTO.getControlAvance(), 1));
               parametros.add(new Parametro(2, paginacionDTO.getRegistrosPorPagina(), 1));
               parametros.add(new Parametro(2, 0, 3));
               parametros.add(new Parametro(12, "", 3));
               parametros.add(new Parametro(12, "", 3));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  recursoSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(recursoSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }

                  rs = cs.getResultSet();
                  totalRegistrosSp = (BigDecimal)cs.getObject(parametros.size() - 2);
                  paginacionDTO.setTotalRegistros((long)totalRegistrosSp.intValue());
               }
            }

            if (rs != null) {
               while(rs.next()) {
                  recursoSeguridadDataDTO = new RecursoSeguridadDTO();
                  if (this.ejecucionPorQuerys) {
                     paginacionDTO.setTotalRegistros((long)rs.getInt("TOTREG"));
                  }

                  recursoSeguridadDataDTO.setCodigoRecurso(rs.getString("IDRECURSO"));
                  recursoSeguridadDataDTO.setNombreRecurso(rs.getString("DSRECURSO"));
                  recursos.add(recursoSeguridadDataDTO);
               }
            }
         } catch (SQLException var22) {
            personalsoftException = new PersonalsoftException(var22);
            personalsoftException.setMensajeTecnico(personalsoftException.getTraza());
            personalsoftException.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("001"));
            throw personalsoftException;
         } finally {
            try {
               BDHelper.close(rs);
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var21) {
               personalsoftException = new PersonalsoftException(var21);
               throw personalsoftException;
            }
         }
      }

      return recursos;
   }

   public List<RecursoSeguridadDTO> consultarRecursos(RecursoSeguridadDTO recursoSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("consultarRecursos");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      List<RecursoSeguridadDTO> recursos = null;
      RecursoSeguridadDTO recursoSeguridadDataDTO = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         recursos = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(12, "", 3));
               parametros.add(new Parametro(12, "", 3));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  recursoSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(recursoSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }

                  rs = cs.getResultSet();
               }
            }

            if (rs != null) {
               while(rs.next()) {
                  recursoSeguridadDataDTO = new RecursoSeguridadDTO();
                  recursoSeguridadDataDTO.setCodigoRecurso(rs.getString("IDRECURSO"));
                  recursoSeguridadDataDTO.setNombreRecurso(rs.getString("DSRECURSO"));
                  recursos.add(recursoSeguridadDataDTO);
               }
            }
         } catch (SQLException var20) {
            personalsoftException = new PersonalsoftException(var20);
            personalsoftException.setMensajeTecnico(personalsoftException.getTraza());
            personalsoftException.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("001"));
            throw personalsoftException;
         } finally {
            try {
               BDHelper.close(rs);
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var19) {
               personalsoftException = new PersonalsoftException(var19);
               throw personalsoftException;
            }
         }
      }

      return recursos;
   }

   public List<RecursoSeguridadDTO> consultarRecursosPerfil(PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("consultarRecursosPerfil");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      List<RecursoSeguridadDTO> recursos = null;
      RecursoSeguridadDTO recursoSeguridadDataDTO = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         recursos = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("IDPERFIL", 4, new Integer(perfilSeguridadDTO.getCodigoPerfil())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, new Integer(perfilSeguridadDTO.getCodigoPerfil()), 1));
               parametros.add(new Parametro(12, "", 3));
               parametros.add(new Parametro(12, "", 3));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  perfilSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     perfilSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     perfilSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (perfilSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !perfilSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(perfilSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(perfilSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }

                  rs = cs.getResultSet();
               }
            }

            if (rs != null) {
               while(rs.next()) {
                  recursoSeguridadDataDTO = new RecursoSeguridadDTO();
                  recursoSeguridadDataDTO.setCodigoRecurso(rs.getString("IDRECURSO"));
                  recursoSeguridadDataDTO.setNombreRecurso(rs.getString("DSRECURSO"));
                  recursos.add(recursoSeguridadDataDTO);
               }
            }
         } catch (SQLException var20) {
            personalsoftException = new PersonalsoftException(var20);
            personalsoftException.setMensajeTecnico(personalsoftException.getTraza());
            personalsoftException.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("001"));
            throw personalsoftException;
         } finally {
            try {
               BDHelper.close(rs);
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var19) {
               personalsoftException = new PersonalsoftException(var19);
               throw personalsoftException;
            }
         }
      }

      return recursos;
   }

   public RecursoSeguridadDTO consultarRecurso(RecursoSeguridadDTO recursoSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("consultarRecurso");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("IDRECURSO", 4, recursoSeguridadDTO.getCodigoRecurso() != null && !recursoSeguridadDTO.getCodigoRecurso().trim().equals("") ? new Integer(recursoSeguridadDTO.getCodigoRecurso()) : null));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, recursoSeguridadDTO.getCodigoRecurso() != null && !recursoSeguridadDTO.getCodigoRecurso().trim().equals("") ? new Integer(recursoSeguridadDTO.getCodigoRecurso()) : null, 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  recursoSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(recursoSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }

                  rs = cs.getResultSet();
               }
            }

            if (rs != null) {
               while(rs.next()) {
                  recursoSeguridadDTO.setNombreRecurso(rs.getString("DSRECURSO"));
               }
            }
         } catch (SQLException var18) {
            personalsoftException = new PersonalsoftException(var18);
            personalsoftException.setMensajeTecnico(personalsoftException.getTraza());
            personalsoftException.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("001"));
            throw personalsoftException;
         } finally {
            try {
               BDHelper.close(rs);
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var17) {
               personalsoftException = new PersonalsoftException(var17);
               throw personalsoftException;
            }
         }
      }

      return recursoSeguridadDTO;
   }

   public RecursoSeguridadDTO guardarRecurso(RecursoSeguridadDTO recursoSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("guardarRecurso");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDRECURSO", 4, new Integer(recursoSeguridadDTO.getCodigoRecurso())));
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("DSRECURSO", 12, recursoSeguridadDTO.getNombreRecurso()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  recursoSeguridadDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(12, recursoSeguridadDTO.getNombreRecurso(), 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  recursoSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (!mensajeUsuario.equals("") && mensajeUsuario.startsWith("E_")) {
                     recursoSeguridadDTO.getMensajeDTO().setTipo(1);
                  } else {
                     recursoSeguridadDTO.getMensajeDTO().setTipo(2);
                  }

                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(recursoSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }
               }
            }
         } catch (SQLException var18) {
            personalsoftException = new PersonalsoftException(var18);
            personalsoftException.setMensajeTecnico(personalsoftException.getTraza());
            personalsoftException.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("001"));
            throw personalsoftException;
         } finally {
            try {
               BDHelper.close(rs);
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var17) {
               personalsoftException = new PersonalsoftException(var17);
               throw personalsoftException;
            }
         }
      }

      return recursoSeguridadDTO;
   }

   public RecursoSeguridadDTO actualizarRecurso(RecursoSeguridadDTO recursoSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("actualizarRecurso");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("IDRECURSO", 4, new Integer(recursoSeguridadDTO.getCodigoRecurso())));
               parametros.add(new Parametro("DSRECURSO", 12, recursoSeguridadDTO.getNombreRecurso()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  recursoSeguridadDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, new Integer(recursoSeguridadDTO.getCodigoRecurso()), 1));
               parametros.add(new Parametro(12, recursoSeguridadDTO.getNombreRecurso(), 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  recursoSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (!mensajeUsuario.equals("") && mensajeUsuario.startsWith("E_")) {
                     recursoSeguridadDTO.getMensajeDTO().setTipo(1);
                  } else {
                     recursoSeguridadDTO.getMensajeDTO().setTipo(2);
                  }

                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(recursoSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }
               }
            }
         } catch (SQLException var18) {
            personalsoftException = new PersonalsoftException(var18);
            personalsoftException.setMensajeTecnico(personalsoftException.getTraza());
            personalsoftException.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("001"));
            throw personalsoftException;
         } finally {
            try {
               BDHelper.close(rs);
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var17) {
               personalsoftException = new PersonalsoftException(var17);
               throw personalsoftException;
            }
         }
      }

      return recursoSeguridadDTO;
   }

   public RecursoSeguridadDTO eliminarRecurso(RecursoSeguridadDTO recursoSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("eliminarRecurso");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("IDRECURSO", 4, new Integer(recursoSeguridadDTO.getCodigoRecurso())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  recursoSeguridadDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, new Integer(recursoSeguridadDTO.getCodigoRecurso()), 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  recursoSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (!mensajeUsuario.equals("") && mensajeUsuario.startsWith("E_")) {
                     recursoSeguridadDTO.getMensajeDTO().setTipo(1);
                  } else {
                     recursoSeguridadDTO.getMensajeDTO().setTipo(2);
                  }

                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(recursoSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }
               }
            }
         } catch (SQLException var18) {
            personalsoftException = new PersonalsoftException(var18);
            personalsoftException.setMensajeTecnico(personalsoftException.getTraza());
            personalsoftException.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("001"));
            throw personalsoftException;
         } finally {
            try {
               BDHelper.close(rs);
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var17) {
               personalsoftException = new PersonalsoftException(var17);
               throw personalsoftException;
            }
         }
      }

      return recursoSeguridadDTO;
   }

   public RecursoSeguridadDTO guardarRecursoPerfil(RecursoSeguridadDTO recursoSeguridadDTO, PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("guardarRecursoPerfil");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDPERFIL", 4, new Integer(perfilSeguridadDTO.getCodigoPerfil())));
               parametros.add(new Parametro("IDRECURSO", 4, new Integer(recursoSeguridadDTO.getCodigoRecurso())));
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  recursoSeguridadDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(perfilSeguridadDTO.getCodigoPerfil()), 1));
               parametros.add(new Parametro(4, new Integer(recursoSeguridadDTO.getCodigoRecurso()), 1));
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(12, "", 3));
               parametros.add(new Parametro(12, "", 3));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  recursoSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (!mensajeUsuario.equals("") && mensajeUsuario.startsWith("E_")) {
                     recursoSeguridadDTO.getMensajeDTO().setTipo(1);
                  } else {
                     recursoSeguridadDTO.getMensajeDTO().setTipo(2);
                  }

                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(recursoSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }
               }
            }
         } catch (SQLException var19) {
            personalsoftException = new PersonalsoftException(var19);
            personalsoftException.setMensajeTecnico(personalsoftException.getTraza());
            personalsoftException.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("001"));
            throw personalsoftException;
         } finally {
            try {
               BDHelper.close(rs);
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var18) {
               personalsoftException = new PersonalsoftException(var18);
               throw personalsoftException;
            }
         }
      }

      return recursoSeguridadDTO;
   }

   public RecursoSeguridadDTO eliminarRecursoPerfil(RecursoSeguridadDTO recursoSeguridadDTO, PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("eliminarRecursoPerfil");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDPERFIL", 4, new Integer(perfilSeguridadDTO.getCodigoPerfil())));
               parametros.add(new Parametro("IDRECURSO", 4, new Integer(recursoSeguridadDTO.getCodigoRecurso())));
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  recursoSeguridadDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(perfilSeguridadDTO.getCodigoPerfil()), 1));
               parametros.add(new Parametro(4, new Integer(recursoSeguridadDTO.getCodigoRecurso()), 1));
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(12, "", 3));
               parametros.add(new Parametro(12, "", 3));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  recursoSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (!mensajeUsuario.equals("") && mensajeUsuario.startsWith("E_")) {
                     recursoSeguridadDTO.getMensajeDTO().setTipo(1);
                  } else {
                     recursoSeguridadDTO.getMensajeDTO().setTipo(2);
                  }

                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(recursoSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }
               }
            }
         } catch (SQLException var19) {
            personalsoftException = new PersonalsoftException(var19);
            personalsoftException.setMensajeTecnico(personalsoftException.getTraza());
            personalsoftException.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("001"));
            throw personalsoftException;
         } finally {
            try {
               BDHelper.close(rs);
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var18) {
               personalsoftException = new PersonalsoftException(var18);
               throw personalsoftException;
            }
         }
      }

      return recursoSeguridadDTO;
   }

   public List<RecursoSeguridadDTO> consultarRecursosConPerfiles(PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("consultarRecursosConPerfiles");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      List<RecursoSeguridadDTO> recursos = null;
      RecursoSeguridadDTO recursoSeguridadDataDTO = null;
      PersonalsoftException personalsoftException = null;
      PerfilSeguridadDTO perfilSeguridadDTORS = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         recursos = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("IDPERFIL", 4, new Integer(perfilSeguridadDTO.getCodigoPerfil())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, new Integer(perfilSeguridadDTO.getCodigoPerfil()), 1));
               parametros.add(new Parametro(12, "", 3));
               parametros.add(new Parametro(12, "", 3));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  perfilSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     perfilSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     perfilSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (perfilSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !perfilSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(perfilSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(perfilSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }

                  rs = cs.getResultSet();
               }
            }

            if (rs != null) {
               while(rs.next()) {
                  recursoSeguridadDataDTO = new RecursoSeguridadDTO();
                  perfilSeguridadDTORS = new PerfilSeguridadDTO();
                  recursoSeguridadDataDTO.setCodigoRecurso(rs.getString("IDRECURSO"));
                  recursoSeguridadDataDTO.setNombreRecurso(rs.getString("DSRECURSO"));
                  if (rs.getString("IDPERFIL") != null && !rs.getString("IDPERFIL").equalsIgnoreCase("null") && !rs.getString("IDPERFIL").equalsIgnoreCase("")) {
                     perfilSeguridadDTORS.setCodigoPerfil(rs.getString("IDPERFIL"));
                  } else {
                     perfilSeguridadDTORS.setCodigoPerfil("0");
                  }

                  recursoSeguridadDataDTO.setPerfilSeguridadDTO(perfilSeguridadDTORS);
                  recursos.add(recursoSeguridadDataDTO);
               }
            }
         } catch (SQLException var21) {
            personalsoftException = new PersonalsoftException(var21);
            personalsoftException.setMensajeTecnico(personalsoftException.getTraza());
            personalsoftException.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("001"));
            throw personalsoftException;
         } finally {
            try {
               BDHelper.close(rs);
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var20) {
               personalsoftException = new PersonalsoftException(var20);
               throw personalsoftException;
            }
         }
      }

      return recursos;
   }
}
