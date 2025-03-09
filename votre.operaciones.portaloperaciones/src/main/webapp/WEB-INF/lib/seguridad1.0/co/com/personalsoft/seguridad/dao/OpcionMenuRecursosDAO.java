package co.com.personalsoft.seguridad.dao;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.seguridad.beans.AplicacionSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.OpcionMenuDTO;
import co.com.personalsoft.base.seguridad.beans.OpcionMenuRecursoSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.RecursoSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.UsuarioSeguridadDTO;
import co.com.personalsoft.base.util.CargadorMsj;
import co.com.personalsoft.seguridad.servicio.GestorSeguridad;
import co.com.personalsoft.seguridad.util.Util;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OpcionMenuRecursosDAO {
   public static final String TIPO_OPCION_WEB = "1";
   public static final String TIPO_ORIGEN_WEB = "W";
   private BDHelper bdHelper;
   private boolean ejecucionPorQuerys;

   public OpcionMenuRecursosDAO() {
      String tipoRecurso = (String)GestorSeguridad.getInstance().getParametro("TIPO");
      this.ejecucionPorQuerys = tipoRecurso != null && tipoRecurso.equals("SQL");
   }

   public OpcionMenuRecursosDAO(BDHelper helper) {
      this();
      this.bdHelper = helper;
   }

   public List<OpcionMenuRecursoSeguridadDTO> consultarRecursos(OpcionMenuDTO opcionMenuDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("consOpcionesMenu");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      List<OpcionMenuRecursoSeguridadDTO> opcionesMenuRecursos = null;
      OpcionMenuRecursoSeguridadDTO recursoSeguridadDataDTO = null;
      RecursoSeguridadDTO recursoSeguridadDTO = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         opcionesMenuRecursos = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("IDOPCION", 4, new Integer(opcionMenuDTO.getCodigoOpcion())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, new Integer(opcionMenuDTO.getCodigoOpcion()), 1));
               parametros.add(new Parametro(12, "", 3));
               parametros.add(new Parametro(12, "", 3));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  opcionMenuDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     opcionMenuDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     opcionMenuDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (opcionMenuDTO.getMensajeDTO().getMensajeTecnico() != null && !opcionMenuDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(opcionMenuDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(opcionMenuDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }

                  rs = cs.getResultSet();
               }
            }

            if (rs != null) {
               while(rs.next()) {
                  recursoSeguridadDataDTO = new OpcionMenuRecursoSeguridadDTO();
                  recursoSeguridadDTO = new RecursoSeguridadDTO();
                  recursoSeguridadDataDTO.setDsNombre(rs.getString("DSNOMBRE") != null ? rs.getString("DSNOMBRE").trim() : "");
                  recursoSeguridadDTO.setCodigoRecurso(rs.getString("IDRECURSO") != null ? rs.getString("IDRECURSO").trim() : "");
                  recursoSeguridadDTO.setNombreRecurso(rs.getString("DSRECURSO") != null ? rs.getString("DSRECURSO").trim() : "");
                  recursoSeguridadDataDTO.setRecursoSeguridadDTO(recursoSeguridadDTO);
                  opcionesMenuRecursos.add(recursoSeguridadDataDTO);
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

      return opcionesMenuRecursos;
   }

   public OpcionMenuRecursoSeguridadDTO asociarOpcionesMenuRecursosPerfilAplicacion(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuRecursoSeguridadDTO opcionMenuRecursoSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO, List<RecursoSeguridadDTO> listaRecursosPerfil) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("asociarOpcionesMenu");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      RecursoSeguridadDTO recursoSeguridadDTO = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         recursoSeguridadDTO = new RecursoSeguridadDTO();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("tipoOperacion", 12, "1"));
               parametros.add(new Parametro("usuario", 12, Util.configurarLogitudString(usuarioSeguridadDTO.getUsuario().trim(), 20)));
               parametros.add(new Parametro("origen", 12, "W"));
               parametros.add(new Parametro("cargaUtil", 12, this.dtoAssemblerTrama(aplicacionSeguridadDTO, usuarioSeguridadDTO, listaRecursosPerfil)));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  recursoSeguridadDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(12, "1", 1));
               parametros.add(new Parametro(12, Util.configurarLogitudString(usuarioSeguridadDTO.getUsuario().trim(), 20), 1));
               parametros.add(new Parametro(12, "W", 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, this.dtoAssemblerTrama(aplicacionSeguridadDTO, usuarioSeguridadDTO, listaRecursosPerfil), 3));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  recursoSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario.startsWith("E_")) {
                     recursoSeguridadDTO.getMensajeDTO().setTipo(1);
                  } else {
                     recursoSeguridadDTO.getMensajeDTO().setTipo(2);
                  }

                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  opcionMenuRecursoSeguridadDTO.setRecursoSeguridadDTO(recursoSeguridadDTO);
                  if (recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(recursoSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }

                  opcionMenuRecursoSeguridadDTO.setRecursoSeguridadDTO(recursoSeguridadDTO);
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

      return opcionMenuRecursoSeguridadDTO;
   }

   public boolean existeOpcionPerfil(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO, OpcionMenuDTO opcionMenuDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("asociarOpcionesMenu");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ArrayList<Parametro> parametros = null;
      PersonalsoftException personalsoftException = null;
      RecursoSeguridadDTO recursoSeguridadDTO = null;
      String mensajeUsuario = null;
      boolean existeOpcion = false;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         recursoSeguridadDTO = new RecursoSeguridadDTO();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("tipoOperacion", 12, "1"));
               parametros.add(new Parametro("usuario", 12, Util.configurarLogitudString(usuarioSeguridadDTO.getUsuario().trim(), 20)));
               parametros.add(new Parametro("origen", 12, "2"));
               parametros.add(new Parametro("cargaUtil", 12, this.dtoAssemblerTrama(aplicacionSeguridadDTO, usuarioSeguridadDTO, opcionMenuDTO)));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  existeOpcion = true;
               }
            } else {
               parametros.add(new Parametro(12, "2", 1));
               parametros.add(new Parametro(12, Util.configurarLogitudString(usuarioSeguridadDTO.getUsuario().trim(), 20), 1));
               parametros.add(new Parametro(12, "W", 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, this.dtoAssemblerTrama(aplicacionSeguridadDTO, usuarioSeguridadDTO, opcionMenuDTO), 3));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  recursoSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario.startsWith("E_")) {
                     existeOpcion = true;
                  }

                  if (recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(recursoSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(recursoSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }
               }
            }
         } catch (SQLException var20) {
            personalsoftException = new PersonalsoftException(var20);
            personalsoftException.setMensajeTecnico(personalsoftException.getTraza());
            personalsoftException.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("001"));
            throw personalsoftException;
         } finally {
            try {
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var19) {
               personalsoftException = new PersonalsoftException(var19);
               throw personalsoftException;
            }
         }
      }

      return existeOpcion;
   }

   public boolean existeRecursoPerfil(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO, RecursoSeguridadDTO recursoSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("asociarOpcionesMenu");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ArrayList<Parametro> parametros = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      boolean existeRecurso = false;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("tipoOperacion", 12, "1"));
               parametros.add(new Parametro("usuario", 12, Util.configurarLogitudString(usuarioSeguridadDTO.getUsuario().trim(), 20)));
               parametros.add(new Parametro("origen", 12, "3"));
               parametros.add(new Parametro("cargaUtil", 12, this.dtoAssemblerTrama(aplicacionSeguridadDTO, usuarioSeguridadDTO, recursoSeguridadDTO)));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  existeRecurso = true;
               }
            } else {
               parametros.add(new Parametro(12, "3", 1));
               parametros.add(new Parametro(12, Util.configurarLogitudString(usuarioSeguridadDTO.getUsuario().trim(), 20), 1));
               parametros.add(new Parametro(12, "W", 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, this.dtoAssemblerTrama(aplicacionSeguridadDTO, usuarioSeguridadDTO, recursoSeguridadDTO), 3));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  recursoSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario.startsWith("E_")) {
                     existeRecurso = true;
                  } else {
                     recursoSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (usuarioSeguridadDTO.getPerfilSeguridadDTO().getMensajeDTO().getMensajeTecnico() != null && !usuarioSeguridadDTO.getPerfilSeguridadDTO().getMensajeDTO().getMensajeTecnico().equals("")) {
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
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var18) {
               personalsoftException = new PersonalsoftException(var18);
               throw personalsoftException;
            }
         }
      }

      return existeRecurso;
   }

   private String dtoAssemblerTrama(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO, List<RecursoSeguridadDTO> listaRecursosPerfil) {
      StringBuilder cargaUtil = new StringBuilder();
      String codigoAplicacion = Util.configurarLogitudString(aplicacionSeguridadDTO.getCodigoAplicacion(), 9);
      String codigoPerfil = Util.configurarLogitudString(usuarioSeguridadDTO.getPerfilSeguridadDTO().getCodigoPerfil(), 9);
      String[] var10 = usuarioSeguridadDTO.getPerfilSeguridadDTO().getOpcionesPermitidas();
      int var8 = 0;

      for(int var9 = var10.length; var8 < var9; ++var8) {
         String str = var10[var8];
         cargaUtil.append(codigoAplicacion);
         cargaUtil.append(codigoPerfil);
         cargaUtil.append(Util.configurarLogitudString(str, 9));
         cargaUtil.append(Util.configurarLogitudString(" ", 9));
      }

      Iterator var12 = listaRecursosPerfil.iterator();

      while(var12.hasNext()) {
         RecursoSeguridadDTO recursoSeguridadDTO = (RecursoSeguridadDTO)var12.next();
         cargaUtil.append(codigoAplicacion);
         cargaUtil.append(codigoPerfil);
         cargaUtil.append(Util.configurarLogitudString(" ", 9));
         cargaUtil.append(Util.configurarLogitudString(recursoSeguridadDTO.getCodigoRecurso(), 9));
      }

      return cargaUtil.toString();
   }

   private String dtoAssemblerTrama(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO, OpcionMenuDTO opcionMenuDTO) {
      StringBuilder cargaUtil = new StringBuilder();
      cargaUtil.append(Util.configurarLogitudString(aplicacionSeguridadDTO.getCodigoAplicacion(), 9));
      cargaUtil.append(Util.configurarLogitudString(usuarioSeguridadDTO.getPerfilSeguridadDTO().getCodigoPerfil(), 9));
      cargaUtil.append(Util.configurarLogitudString(opcionMenuDTO.getCodigoOpcion(), 9));
      cargaUtil.append(Util.configurarLogitudString(" ", 9));
      return cargaUtil.toString();
   }

   private String dtoAssemblerTrama(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO, RecursoSeguridadDTO recursoSeguridadDTO) {
      StringBuilder cargaUtil = new StringBuilder();
      cargaUtil.append(Util.configurarLogitudString(aplicacionSeguridadDTO.getCodigoAplicacion(), 9));
      cargaUtil.append(Util.configurarLogitudString(usuarioSeguridadDTO.getPerfilSeguridadDTO().getCodigoPerfil(), 9));
      cargaUtil.append(Util.configurarLogitudString(" ", 9));
      cargaUtil.append(Util.configurarLogitudString(recursoSeguridadDTO.getCodigoRecurso(), 9));
      return cargaUtil.toString();
   }
}
