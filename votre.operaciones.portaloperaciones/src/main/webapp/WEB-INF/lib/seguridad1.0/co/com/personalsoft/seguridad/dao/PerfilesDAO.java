package co.com.personalsoft.seguridad.dao;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.paginacion.beans.PaginacionDTO;
import co.com.personalsoft.base.seguridad.beans.AplicacionSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.OpcionMenuDTO;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PerfilesDAO {
   private BDHelper bdHelper;
   private boolean ejecucionPorQuerys;

   public PerfilesDAO() {
      String tipoRecurso = (String)GestorSeguridad.getInstance().getParametro("TIPO");
      this.ejecucionPorQuerys = tipoRecurso != null && tipoRecurso.equals("SQL");
   }

   public PerfilesDAO(BDHelper helper) {
      this();
      this.bdHelper = helper;
   }

   public Map<String, PerfilSeguridadDTO> consultarPerfilesOpcion(AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("perfilesOpcion");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      Map<String, PerfilSeguridadDTO> perfilesOpcion = null;
      PerfilSeguridadDTO perfilSeguridadDTO = null;
      Set<String> opcionesPermitidas = null;
      PersonalsoftException personalsoftException = null;
      String codigoOpcionActual = "";
      String mensajeUsuario = null;
      String mensajeTecnico = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         perfilesOpcion = new HashMap();

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
               perfilSeguridadDTO = new PerfilSeguridadDTO();

               while(rs.next()) {
                  codigoOpcionActual = rs.getString("IDPERFIL");
                  if (!codigoOpcionActual.equals(perfilSeguridadDTO.getCodigoPerfil())) {
                     perfilSeguridadDTO = new PerfilSeguridadDTO();
                     perfilSeguridadDTO.setCodigoPerfil(codigoOpcionActual);
                     perfilSeguridadDTO.setNombrePerfil(rs.getString("DSPERFIL"));
                     opcionesPermitidas = new HashSet();
                  }

                  opcionesPermitidas.add(rs.getString("IDOPCION"));
                  perfilSeguridadDTO.setOpcionesPermitidas((String[])opcionesPermitidas.toArray(new String[opcionesPermitidas.size()]));
                  perfilesOpcion.put(perfilSeguridadDTO.getNombrePerfil(), perfilSeguridadDTO);
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

      return perfilesOpcion;
   }

   public List<PerfilSeguridadDTO> consultarPerfilesUsuario(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("listarPerfilesUsuario");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      List<PerfilSeguridadDTO> perfiles = null;
      PerfilSeguridadDTO perfilSeguridadDataDTO = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         perfiles = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("DSLOGIN", 12, usuarioSeguridadDTO.getUsuario()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
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

                  rs = cs.getResultSet();
               }
            }

            if (rs != null) {
               while(rs.next()) {
                  perfilSeguridadDataDTO = new PerfilSeguridadDTO();
                  perfilSeguridadDataDTO.setCodigoPerfil(rs.getString("IDPERFIL"));
                  perfilSeguridadDataDTO.setNombrePerfil(rs.getString("DSPERFIL"));
                  perfiles.add(perfilSeguridadDataDTO);
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

      return perfiles;
   }

   public List<PerfilSeguridadDTO> consultarPerfiles(AplicacionSeguridadDTO aplicacionSeguridadDTO, PerfilSeguridadDTO perfilSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("listarPerfiles");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      List<PerfilSeguridadDTO> perfiles = null;
      PerfilSeguridadDTO perfilSeguridadDataDTO = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         perfiles = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("IDPERFIL", 4, perfilSeguridadDTO.getCodigoPerfil() != null && !perfilSeguridadDTO.getCodigoPerfil().trim().equals("") ? new Integer(perfilSeguridadDTO.getCodigoPerfil()) : null));
               parametros.add(new Parametro("DSPERFIL", 12, perfilSeguridadDTO.getNombrePerfil()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, perfilSeguridadDTO.getCodigoPerfil() != null && !perfilSeguridadDTO.getCodigoPerfil().trim().equals("") ? new Integer(perfilSeguridadDTO.getCodigoPerfil()) : null, 1));
               parametros.add(new Parametro(12, perfilSeguridadDTO.getNombrePerfil(), 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
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
                  perfilSeguridadDataDTO = new PerfilSeguridadDTO();
                  perfilSeguridadDataDTO.setCodigoPerfil(rs.getString("IDPERFIL"));
                  perfilSeguridadDataDTO.setNombrePerfil(rs.getString("DSPERFIL"));
                  perfiles.add(perfilSeguridadDataDTO);
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

      return perfiles;
   }

   public PerfilSeguridadDTO consultarPerfil(AplicacionSeguridadDTO aplicacionSeguridadDTO, PerfilSeguridadDTO perfilSeguridadDTO) throws PersonalsoftException {
      List<PerfilSeguridadDTO> perfiles = this.consultarPerfiles(aplicacionSeguridadDTO, perfilSeguridadDTO);
      return perfiles != null && !perfiles.isEmpty() ? (PerfilSeguridadDTO)perfiles.get(0) : null;
   }

   public List<PerfilSeguridadDTO> consultarPerfiles(PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO, PaginacionDTO paginacionDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("listarPerfilesPaginacion");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      List<PerfilSeguridadDTO> perfiles = null;
      PerfilSeguridadDTO perfilSeguridadDataDTO = null;
      BigDecimal totalRegistrosSp = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         perfiles = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("IDPERFIL", 4, perfilSeguridadDTO.getCodigoPerfil() != null && !perfilSeguridadDTO.getCodigoPerfil().trim().equals("") ? new Integer(perfilSeguridadDTO.getCodigoPerfil()) : null));
               parametros.add(new Parametro("DSPERFIL", 12, perfilSeguridadDTO.getNombrePerfil()));
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
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, perfilSeguridadDTO.getCodigoPerfil() != null && !perfilSeguridadDTO.getCodigoPerfil().trim().equals("") ? new Integer(perfilSeguridadDTO.getCodigoPerfil()) : null, 1));
               parametros.add(new Parametro(12, perfilSeguridadDTO.getNombrePerfil(), 1));
               parametros.add(new Parametro(2, paginacionDTO.getControlAvance(), 1));
               parametros.add(new Parametro(2, paginacionDTO.getRegistrosPorPagina(), 1));
               parametros.add(new Parametro(2, 0, 3));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  perfilSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT").trim() != null ? cs.getString("MSGT").trim() : "");
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
                  totalRegistrosSp = (BigDecimal)cs.getObject(parametros.size() - 2);
                  paginacionDTO.setTotalRegistros((long)totalRegistrosSp.intValue());
               }
            }

            if (rs != null) {
               while(rs.next()) {
                  perfilSeguridadDataDTO = new PerfilSeguridadDTO();
                  if (this.ejecucionPorQuerys) {
                     paginacionDTO.setTotalRegistros((long)rs.getInt("TOTREG"));
                  }

                  perfilSeguridadDataDTO.setCodigoPerfil(rs.getString("IDPERFIL"));
                  perfilSeguridadDataDTO.setNombrePerfil(rs.getString("DSPERFIL"));
                  perfiles.add(perfilSeguridadDataDTO);
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

      return perfiles;
   }

   public PerfilSeguridadDTO guardarPerfil(PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("guardarPerfil");
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
               parametros.add(new Parametro("DSPERFIL", 12, perfilSeguridadDTO.getNombrePerfil()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  perfilSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  perfilSeguridadDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(12, perfilSeguridadDTO.getNombrePerfil(), 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
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

      return perfilSeguridadDTO;
   }

   public PerfilSeguridadDTO actualizarPerfil(PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("actualizarPerfil");
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
               parametros.add(new Parametro("IDPERFIL", 4, new Integer(perfilSeguridadDTO.getCodigoPerfil())));
               parametros.add(new Parametro("DSPERFIL", 12, perfilSeguridadDTO.getNombrePerfil()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  perfilSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  perfilSeguridadDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, new Integer(perfilSeguridadDTO.getCodigoPerfil()), 1));
               parametros.add(new Parametro(12, perfilSeguridadDTO.getNombrePerfil(), 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
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

      return perfilSeguridadDTO;
   }

   public PerfilSeguridadDTO eliminarPerfil(PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("eliminarPerfil");
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
               parametros.add(new Parametro("IDDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("IDPERFIL", 4, new Integer(perfilSeguridadDTO.getCodigoPerfil())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  perfilSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  perfilSeguridadDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, new Integer(perfilSeguridadDTO.getCodigoPerfil()), 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
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

      return perfilSeguridadDTO;
   }

   public PerfilSeguridadDTO eliminarNodoPerfil(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuDTO opcionMenuDTO, PerfilSeguridadDTO perfilSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("eliminarNodoPerfil");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      PersonalsoftException personalsoftException = null;
      String mensajeTecnico = "";
      String mensajeUsuario = "";
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("IDPERFIL", 4, new Integer(perfilSeguridadDTO.getCodigoPerfil())));
               parametros.add(new Parametro("IDOPCION", 4, new Integer(opcionMenuDTO.getCodigoOpcion())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  opcionMenuDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  opcionMenuDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, new Integer(perfilSeguridadDTO.getCodigoPerfil()), 1));
               parametros.add(new Parametro(4, new Integer(opcionMenuDTO.getCodigoOpcion()), 1));
               parametros.add(new Parametro(12, "", 3));
               parametros.add(new Parametro(12, "", 3));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  mensajeTecnico = cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "";
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (!mensajeUsuario.equals("") && mensajeUsuario.startsWith("E_")) {
                     perfilSeguridadDTO.getMensajeDTO().setTipo(1);
                  } else {
                     perfilSeguridadDTO.getMensajeDTO().setTipo(2);
                  }

                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     perfilSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     perfilSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (mensajeTecnico != null && !mensajeTecnico.equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(mensajeTecnico);
                     personalsoftException.setMensajeUsuario(mensajeUsuario);
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
               BDHelper.close(rs);
               BDHelper.close(cs);
               BDHelper.close(ps);
            } catch (SQLException var19) {
               personalsoftException = new PersonalsoftException(var19);
               throw personalsoftException;
            }
         }
      }

      return perfilSeguridadDTO;
   }
}
