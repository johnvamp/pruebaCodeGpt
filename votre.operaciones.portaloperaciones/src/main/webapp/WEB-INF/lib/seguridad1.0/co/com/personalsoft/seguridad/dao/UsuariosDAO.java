package co.com.personalsoft.seguridad.dao;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.paginacion.beans.PaginacionDTO;
import co.com.personalsoft.base.seguridad.beans.AplicacionSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.PerfilSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.UsuarioSeguridadDTO;
import co.com.personalsoft.base.util.CargadorMsj;
import co.com.personalsoft.seguridad.servicio.GestorSeguridad;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {
   private BDHelper bdHelper;
   private boolean ejecucionPorQuerys;

   public UsuariosDAO() {
      String tipoRecurso = (String)GestorSeguridad.getInstance().getParametro("TIPO");
      this.ejecucionPorQuerys = tipoRecurso != null && tipoRecurso.equals("SQL");
   }

   public UsuariosDAO(BDHelper helper) {
      this();
      this.bdHelper = helper;
   }

   public List<UsuarioSeguridadDTO> consultarUsuarios(UsuarioSeguridadDTO usuarioSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO, PaginacionDTO paginacionDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("listarUsuariosPaginacion");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      List<UsuarioSeguridadDTO> usuarios = null;
      UsuarioSeguridadDTO usuarioSeguridadDataDTO = null;
      PersonalsoftException personalsoftException = null;
      BigDecimal totalRegistrosSp = null;
      String mensajeUsuario = null;
      PerfilSeguridadDTO perfilSeguridadDTO = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         usuarios = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("REGINI", 12, paginacionDTO.getRegistroInicial()));
               parametros.add(new Parametro("REGFIN", 12, paginacionDTO.getRegistroFinal()));
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("IDPERFIL", 4, new Integer(usuarioSeguridadDTO.getPerfilSeguridadDTO().getCodigoPerfil())));
               parametros.add(new Parametro("DSLOGIN", 12, usuarioSeguridadDTO.getUsuario().trim()));
               parametros.add(new Parametro("CONTAVANCE", 2, paginacionDTO.getControlAvance()));
               parametros.add(new Parametro("NUMREG", 2, paginacionDTO.getRegistrosPorPagina()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro(12, paginacionDTO.getRegistroInicial(), 1));
               parametros.add(new Parametro(12, paginacionDTO.getRegistroFinal(), 1));
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, new Integer(usuarioSeguridadDTO.getPerfilSeguridadDTO().getCodigoPerfil()), 1));
               parametros.add(new Parametro(12, usuarioSeguridadDTO.getUsuario().trim(), 1));
               parametros.add(new Parametro(2, paginacionDTO.getControlAvance(), 1));
               parametros.add(new Parametro(2, paginacionDTO.getRegistrosPorPagina(), 1));
               parametros.add(new Parametro(2, 0, 3));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(usuarioSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }

                  rs = cs.getResultSet();
                  totalRegistrosSp = (BigDecimal)cs.getObject(parametros.size() - 2);
                  paginacionDTO.setTotalRegistros((long)totalRegistrosSp.intValue());
               }
            }

            if (rs != null) {
               while(rs.next()) {
                  usuarioSeguridadDataDTO = new UsuarioSeguridadDTO();
                  if (this.ejecucionPorQuerys) {
                     paginacionDTO.setTotalRegistros((long)rs.getInt("TOTREG"));
                  }

                  usuarioSeguridadDataDTO.setUsuario(rs.getString("DSLOGIN").trim());
                  usuarioSeguridadDataDTO.setNombre(rs.getString("DSNOMBRE").trim());
                  usuarioSeguridadDataDTO.setActivo(rs.getString("SNACTIVO").trim());
                  perfilSeguridadDTO = new PerfilSeguridadDTO();
                  perfilSeguridadDTO.setCodigoPerfil(rs.getString("IDPERFIL").trim());
                  perfilSeguridadDTO.setNombrePerfil(rs.getString("DSPERFIL").trim());
                  usuarioSeguridadDataDTO.setPerfilSeguridadDTO(perfilSeguridadDTO);
                  usuarios.add(usuarioSeguridadDataDTO);
               }
            }
         } catch (SQLException var23) {
            personalsoftException = new PersonalsoftException(var23);
            personalsoftException.setMensajeTecnico(personalsoftException.getTraza());
            personalsoftException.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("001"));
            throw personalsoftException;
         } finally {
            try {
               BDHelper.close(rs);
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var22) {
               personalsoftException = new PersonalsoftException(var22);
               throw personalsoftException;
            }
         }
      }

      return usuarios;
   }

   public List<UsuarioSeguridadDTO> consultarUsuarios(UsuarioSeguridadDTO usuarioSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("consultarUsuario");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      UsuarioSeguridadDTO obtenido = null;
      List<UsuarioSeguridadDTO> usuarios = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         usuarios = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("DSLOGIN", 12, usuarioSeguridadDTO.getUsuario()));
               parametros.add(new Parametro("IDPERFIL", 4, usuarioSeguridadDTO.getPerfilSeguridadDTO() != null ? new Integer(usuarioSeguridadDTO.getPerfilSeguridadDTO().getCodigoPerfil()) : 0));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(12, usuarioSeguridadDTO.getUsuario(), 1));
               parametros.add(new Parametro(4, usuarioSeguridadDTO.getPerfilSeguridadDTO() != null ? new Integer(usuarioSeguridadDTO.getPerfilSeguridadDTO().getCodigoPerfil()) : 0, 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(usuarioSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }

                  rs = cs.getResultSet();
               }
            }

            if (rs != null) {
               for(; rs.next(); usuarios.add(obtenido)) {
                  obtenido = new UsuarioSeguridadDTO();
                  obtenido.setUsuario(rs.getString("DSLOGIN").trim());
                  obtenido.setNombre(rs.getString("DSNOMBRE").trim());
                  obtenido.setFechaUltimoLogueo(rs.getString("FEULT_LOG"));
                  obtenido.setActivo(rs.getString("SNACTIVO").trim());
                  obtenido.setCambioClave(rs.getString("SNCAMBIO").trim());
                  obtenido.setIntentos(rs.getString("NMINTENTOS").trim());
                  if (rs.getString("IDPERFIL") != null) {
                     PerfilSeguridadDTO perfilSeguridadDTO = new PerfilSeguridadDTO();
                     perfilSeguridadDTO.setCodigoPerfil(rs.getString("IDPERFIL").trim());
                     perfilSeguridadDTO.setNombrePerfil(rs.getString("DSPERFIL").trim());
                     obtenido.setPerfilSeguridadDTO(perfilSeguridadDTO);
                  }
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

      return usuarios;
   }

   public UsuarioSeguridadDTO consultarUsuario(UsuarioSeguridadDTO usuarioSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      List<UsuarioSeguridadDTO> users = this.consultarUsuarios(usuarioSeguridadDTO, aplicacionSeguridadDTO);
      return users != null && !users.isEmpty() ? (UsuarioSeguridadDTO)users.get(0) : null;
   }

   public UsuarioSeguridadDTO guardarUsuario(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("guardarUsuario");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ArrayList<Parametro> parametros = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("DSLOGIN", 12, usuarioSeguridadDTO.getUsuario()));
               parametros.add(new Parametro("DSCLAVE", 12, usuarioSeguridadDTO.getClave()));
               parametros.add(new Parametro("DSNOMBRE", 12, usuarioSeguridadDTO.getNombre()));
               parametros.add(new Parametro("NMINTENTOS", 4, new Integer(usuarioSeguridadDTO.getIntentos())));
               parametros.add(new Parametro("SNACTIVO", 12, usuarioSeguridadDTO.getActivo()));
               parametros.add(new Parametro("SNCAMBIO", 12, usuarioSeguridadDTO.getCambioClave() != null && !usuarioSeguridadDTO.getCambioClave().equals("") ? usuarioSeguridadDTO.getCambioClave() : "S"));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(12, usuarioSeguridadDTO.getUsuario(), 1));
               parametros.add(new Parametro(12, usuarioSeguridadDTO.getClave(), 1));
               parametros.add(new Parametro(12, usuarioSeguridadDTO.getNombre(), 1));
               parametros.add(new Parametro(4, new Integer(usuarioSeguridadDTO.getIntentos()), 1));
               parametros.add(new Parametro(12, usuarioSeguridadDTO.getActivo(), 1));
               parametros.add(new Parametro(12, usuarioSeguridadDTO.getCambioClave() != null ? usuarioSeguridadDTO.getCambioClave() : "S", 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(usuarioSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }
               }
            }
         } catch (SQLException var17) {
            personalsoftException = new PersonalsoftException(var17);
            personalsoftException.setMensajeTecnico(personalsoftException.getTraza());
            personalsoftException.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("001"));
            throw personalsoftException;
         } finally {
            try {
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var16) {
               personalsoftException = new PersonalsoftException(var16);
               throw personalsoftException;
            }
         }
      }

      return usuarioSeguridadDTO;
   }

   public UsuarioSeguridadDTO actualizarUsuario(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("actualizarUsuario");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ArrayList<Parametro> parametros = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("DSLOGIN", 12, usuarioSeguridadDTO.getUsuario()));
               parametros.add(new Parametro("DSNOMBRE", 12, usuarioSeguridadDTO.getNombre()));
               parametros.add(new Parametro("NMINTENTOS", 4, new Integer(usuarioSeguridadDTO.getIntentos())));
               parametros.add(new Parametro("SNACTIVO", 12, usuarioSeguridadDTO.getActivo()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(12, usuarioSeguridadDTO.getUsuario(), 1));
               parametros.add(new Parametro(12, usuarioSeguridadDTO.getNombre(), 1));
               parametros.add(new Parametro(4, new Integer(usuarioSeguridadDTO.getIntentos()), 1));
               parametros.add(new Parametro(12, usuarioSeguridadDTO.getActivo(), 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(usuarioSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }
               }
            }
         } catch (SQLException var17) {
            personalsoftException = new PersonalsoftException(var17);
            personalsoftException.setMensajeTecnico(personalsoftException.getTraza());
            personalsoftException.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("001"));
            throw personalsoftException;
         } finally {
            try {
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var16) {
               personalsoftException = new PersonalsoftException(var16);
               throw personalsoftException;
            }
         }
      }

      return usuarioSeguridadDTO;
   }

   public boolean cambiarClave(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("cambiarClave");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ArrayList<Parametro> parametros = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      boolean actualizada = false;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("DSLOGIN", 12, usuarioSeguridadDTO.getUsuario()));
               parametros.add(new Parametro("DSCLAVE_NUEVA", 12, usuarioSeguridadDTO.getClave()));
               parametros.add(new Parametro("DSCLAVE_ANTERIOR", 12, usuarioSeguridadDTO.getClaveAnterior()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(12, usuarioSeguridadDTO.getUsuario(), 1));
               parametros.add(new Parametro(12, usuarioSeguridadDTO.getClave(), 1));
               parametros.add(new Parametro(12, usuarioSeguridadDTO.getClaveAnterior(), 1));
               parametros.add(new Parametro(4, "", 2));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  actualizada = cs.getInt("REG_ACT") > 0;
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(usuarioSeguridadDTO.getMensajeDTO().getMensajeUsuario());
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
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var17) {
               personalsoftException = new PersonalsoftException(var17);
               throw personalsoftException;
            }
         }
      }

      return actualizada;
   }

   public UsuarioSeguridadDTO eliminarUsuario(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("eliminarUsuario");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ArrayList<Parametro> parametros = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("DSLOGIN", 12, usuarioSeguridadDTO.getUsuario()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(12, usuarioSeguridadDTO.getUsuario(), 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(usuarioSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }
               }
            }
         } catch (SQLException var17) {
            personalsoftException = new PersonalsoftException(var17);
            personalsoftException.setMensajeTecnico(personalsoftException.getTraza());
            personalsoftException.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("001"));
            throw personalsoftException;
         } finally {
            try {
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var16) {
               personalsoftException = new PersonalsoftException(var16);
               throw personalsoftException;
            }
         }
      }

      return usuarioSeguridadDTO;
   }

   public UsuarioSeguridadDTO guardarPerfilUsuario(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("guardarPerfilUsuario");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ArrayList<Parametro> parametros = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("IDPERFIL", 4, new Integer(usuarioSeguridadDTO.getPerfilSeguridadDTO().getCodigoPerfil())));
               parametros.add(new Parametro("DSLOGIN", 12, usuarioSeguridadDTO.getUsuario()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, new Integer(usuarioSeguridadDTO.getPerfilSeguridadDTO().getCodigoPerfil()), 1));
               parametros.add(new Parametro(12, usuarioSeguridadDTO.getUsuario(), 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (usuarioSeguridadDTO.getPerfilSeguridadDTO().getMensajeDTO().getMensajeTecnico() != null && !usuarioSeguridadDTO.getPerfilSeguridadDTO().getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(usuarioSeguridadDTO.getPerfilSeguridadDTO().getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(usuarioSeguridadDTO.getPerfilSeguridadDTO().getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }
               }
            }
         } catch (SQLException var17) {
            personalsoftException = new PersonalsoftException(var17);
            personalsoftException.setMensajeTecnico(personalsoftException.getTraza());
            personalsoftException.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("001"));
            throw personalsoftException;
         } finally {
            try {
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var16) {
               personalsoftException = new PersonalsoftException(var16);
               throw personalsoftException;
            }
         }
      }

      return usuarioSeguridadDTO;
   }

   public UsuarioSeguridadDTO eliminarPerfilUsuario(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("eliminarUsuarioPerfil");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ArrayList<Parametro> parametros = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("IDPERFIL", 4, new Integer(usuarioSeguridadDTO.getPerfilSeguridadDTO().getCodigoPerfil())));
               parametros.add(new Parametro("DSLOGIN", 12, usuarioSeguridadDTO.getUsuario()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, new Integer(usuarioSeguridadDTO.getPerfilSeguridadDTO().getCodigoPerfil()), 1));
               parametros.add(new Parametro(12, usuarioSeguridadDTO.getUsuario(), 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(usuarioSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }
               }
            }
         } catch (SQLException var17) {
            personalsoftException = new PersonalsoftException(var17);
            personalsoftException.setMensajeTecnico(personalsoftException.getTraza());
            personalsoftException.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("001"));
            throw personalsoftException;
         } finally {
            try {
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var16) {
               personalsoftException = new PersonalsoftException(var16);
               throw personalsoftException;
            }
         }
      }

      return usuarioSeguridadDTO;
   }

   public UsuarioSeguridadDTO autenticarUsuario(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("autenticarUsuario");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      UsuarioSeguridadDTO autenticado = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("DSLOGIN", 12, usuarioSeguridadDTO.getUsuario()));
               parametros.add(new Parametro("DSCLAVE", 12, usuarioSeguridadDTO.getClave()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
                  ps.executeUpdate();
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(12, usuarioSeguridadDTO.getUsuario(), 1));
               parametros.add(new Parametro(12, usuarioSeguridadDTO.getClave(), 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  usuarioSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     usuarioSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(usuarioSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(usuarioSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }

                  rs = cs.getResultSet();
               }
            }

            if (rs != null) {
               while(rs.next()) {
                  autenticado = usuarioSeguridadDTO;
                  usuarioSeguridadDTO.setClave("");
                  usuarioSeguridadDTO.setAutenticado(true);
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

      return autenticado;
   }
}
