package co.com.personalsoft.seguridad.dao;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.paginacion.beans.PaginacionDTO;
import co.com.personalsoft.base.seguridad.beans.AplicacionSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.OpcionMenuDTO;
import co.com.personalsoft.base.seguridad.beans.PerfilSeguridadDTO;
import co.com.personalsoft.base.util.CargadorMsj;
import co.com.personalsoft.seguridad.servicio.GestorSeguridad;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenusDAO {
   private BDHelper bdHelper;
   private boolean ejecucionPorQuerys;

   public MenusDAO() {
      String tipoRecurso = (String)GestorSeguridad.getInstance().getParametro("TIPO");
      this.ejecucionPorQuerys = tipoRecurso != null && tipoRecurso.equals("SQL");
   }

   public MenusDAO(BDHelper helper) {
      this();
      this.bdHelper = helper;
   }

   public List<OpcionMenuDTO> consultarMenuPerfil(AplicacionSeguridadDTO aplicacionSeguridadDTO, PerfilSeguridadDTO perfilSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("consultarMenuPerfil");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      List<OpcionMenuDTO> menus = null;
      OpcionMenuDTO nodoDataDTO = null;
      String mensajeUsuario = null;
      PersonalsoftException personalsoftException = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         menus = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("IDPERFIL", 4, perfilSeguridadDTO.getCodigoPerfil() != null ? new Integer(perfilSeguridadDTO.getCodigoPerfil()) : null));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
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

                  rs = cs.getResultSet();
               }
            }

            if (rs != null) {
               while(rs.next()) {
                  nodoDataDTO = new OpcionMenuDTO();
                  nodoDataDTO.setCodigoOpcion(rs.getString("IDOPCION").trim());
                  nodoDataDTO.setCodigoOpcionPadre(rs.getString("IDPADRE") != null && !rs.getString("IDPADRE").equalsIgnoreCase("0") ? rs.getString("IDPADRE").trim() : null);
                  nodoDataDTO.setUrl(rs.getString("DSURL") != null ? rs.getString("DSURL").trim() : "#");
                  nodoDataDTO.setNombreOpcion(rs.getString("DSOPCION") != null ? rs.getString("DSOPCION").trim() : "");
                  nodoDataDTO.setTarget(rs.getString("DSTARGET") != null ? rs.getString("DSTARGET").trim() : "");
                  nodoDataDTO.setOrden(rs.getInt("NMORDEN"));
                  nodoDataDTO.setNivel(rs.getInt("NMNIVEL"));
                  menus.add(nodoDataDTO);
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

      return menus;
   }

   public List<OpcionMenuDTO> consultarMenus(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuDTO opcionMenuDTO, PaginacionDTO paginacionDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("listarMenusPaginacion");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      List<OpcionMenuDTO> menus = null;
      OpcionMenuDTO menuDataDTO = null;
      BigDecimal totalRegistrosSp = null;
      String mensajeUsuario = null;
      PersonalsoftException personalsoftException = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         menus = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("ROWINI", 12, paginacionDTO.getRegistroInicial()));
               parametros.add(new Parametro("ROWFIN", 12, paginacionDTO.getRegistroFinal()));
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("IDOPCION", 4, opcionMenuDTO.getCodigoOpcion() != null ? new Integer(opcionMenuDTO.getCodigoOpcion()) : null));
               parametros.add(new Parametro("DSOPCION", 12, opcionMenuDTO.getNombreOpcion()));
               parametros.add(new Parametro("CONTROL", 2, paginacionDTO.getControlAvance()));
               parametros.add(new Parametro("NUMREG", 2, paginacionDTO.getRegistrosPorPagina()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro(4, new Integer(paginacionDTO.getRegistroInicial()), 1));
               parametros.add(new Parametro(4, new Integer(paginacionDTO.getRegistroFinal()), 1));
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, opcionMenuDTO.getCodigoOpcion() != null ? new Integer(opcionMenuDTO.getCodigoOpcion()) : null, 1));
               parametros.add(new Parametro(12, opcionMenuDTO.getNombreOpcion(), 1));
               parametros.add(new Parametro(2, paginacionDTO.getControlAvance(), 1));
               parametros.add(new Parametro(2, paginacionDTO.getRegistrosPorPagina(), 1));
               parametros.add(new Parametro(2, 0, 3));
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
                  totalRegistrosSp = (BigDecimal)cs.getObject(parametros.size() - 2);
                  paginacionDTO.setTotalRegistros((long)totalRegistrosSp.intValue());
               }
            }

            if (rs != null) {
               while(rs.next()) {
                  menuDataDTO = new OpcionMenuDTO();
                  if (this.ejecucionPorQuerys) {
                     paginacionDTO.setTotalRegistros((long)rs.getInt("TOTREG"));
                  }

                  menuDataDTO.setCodigoOpcion(rs.getString("IDOPCION").trim());
                  menuDataDTO.setCodigoOpcionPadre(rs.getString("IDPADRE") != null && !rs.getString("IDPADRE").equalsIgnoreCase("0") ? rs.getString("IDPADRE").trim() : null);
                  menuDataDTO.setUrl(rs.getString("DSURL") != null ? rs.getString("DSURL").trim() : "#");
                  menuDataDTO.setNombreOpcion(rs.getString("DSOPCION") != null ? rs.getString("DSOPCION").trim() : "");
                  menuDataDTO.setTarget(rs.getString("DSTARGET") != null ? rs.getString("DSTARGET").trim() : "");
                  menuDataDTO.setOrden(rs.getInt("NMORDEN"));
                  menuDataDTO.setNivel(rs.getInt("NMNIVEL"));
                  menus.add(menuDataDTO);
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

      return menus;
   }

   public List<OpcionMenuDTO> consultarOpcionesMenu(OpcionMenuDTO opcionMenuDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("consultarOpcionesMenu");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      List<OpcionMenuDTO> menus = null;
      OpcionMenuDTO nodoDataDTO = null;
      String mensajeUsuario = null;
      PersonalsoftException personalsoftException = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         menus = new ArrayList();

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
               parametros.add(new Parametro(4, opcionMenuDTO.getCodigoOpcion() != null ? new Integer(opcionMenuDTO.getCodigoOpcion()) : null, 1));
               parametros.add(new Parametro(12, "", 3));
               parametros.add(new Parametro(12, "", 3));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  aplicacionSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     aplicacionSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     aplicacionSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (aplicacionSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !aplicacionSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(aplicacionSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(aplicacionSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }

                  rs = cs.getResultSet();
               }
            }

            if (rs != null) {
               while(rs.next()) {
                  nodoDataDTO = new OpcionMenuDTO();
                  nodoDataDTO.setCodigoOpcion(rs.getString("IDOPCION").trim());
                  nodoDataDTO.setCodigoOpcionPadre(rs.getString("IDPADRE") != null && !rs.getString("IDPADRE").equalsIgnoreCase("0") ? rs.getString("IDPADRE").trim() : null);
                  nodoDataDTO.setNombreOpcion(rs.getString("DSOPCION") != null ? rs.getString("DSOPCION").trim() : "");
                  nodoDataDTO.setUrl(rs.getString("DSURL") != null ? rs.getString("DSURL").trim() : "");
                  nodoDataDTO.setNivel(rs.getInt("NMNIVEL"));
                  nodoDataDTO.setOrden(rs.getInt("NMORDEN"));
                  menus.add(nodoDataDTO);
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

      return menus;
   }

   public List<OpcionMenuDTO> consultarMenus(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuDTO opcionMenuDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("consultarOpcionesMenu");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      List<OpcionMenuDTO> menus = null;
      OpcionMenuDTO menuDataDTO = null;
      String mensajeUsuario = "";
      PersonalsoftException personalsoftException = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         menus = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("IDOPCION", 4, opcionMenuDTO.getCodigoOpcion() != null ? new Integer(opcionMenuDTO.getCodigoOpcion()) : null));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, opcionMenuDTO.getCodigoOpcion() != null ? new Integer(opcionMenuDTO.getCodigoOpcion()) : null, 1));
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
                  menuDataDTO = new OpcionMenuDTO();
                  menuDataDTO.setCodigoOpcion(rs.getString("IDOPCION"));
                  menuDataDTO.setNombreOpcion(rs.getString("DSOPCION"));
                  menuDataDTO.setCodigoOpcionPadre(rs.getString("IDPADRE"));
                  menuDataDTO.setUrl(rs.getString("DSURL"));
                  menuDataDTO.setTarget(rs.getString("DSTARGET"));
                  menuDataDTO.setNivel(rs.getInt("NMNIVEL"));
                  menuDataDTO.setOrden(rs.getInt("NMORDEN"));
                  menus.add(menuDataDTO);
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

      return menus;
   }

   public OpcionMenuDTO consultarNodo(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuDTO opcionMenuDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("listarMenus");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      String mensajeUsuario = null;
      PersonalsoftException personalsoftException = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();

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

            if (rs != null && rs.next()) {
               opcionMenuDTO.setCodigoOpcion(rs.getString("IDOPCION"));
               opcionMenuDTO.setNombreOpcion(rs.getString("DSOPCION"));
               opcionMenuDTO.setCodigoOpcionPadre(rs.getString("IDPADRE"));
               opcionMenuDTO.setUrl(rs.getString("DSURL"));
               opcionMenuDTO.setTarget(rs.getString("DSTARGET"));
               opcionMenuDTO.setNivel(rs.getInt("NMNIVEL"));
               opcionMenuDTO.setOrden(rs.getInt("NMORDEN"));
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

      return opcionMenuDTO;
   }

   public OpcionMenuDTO consultarNodoJerarquiaOrden(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuDTO opcionMenuDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("consultarOpcionesMenuJerarquiaOrden");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      String mensajeUsuario = null;
      PersonalsoftException personalsoftException = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("IDOPCION", 4, opcionMenuDTO.getCodigoOpcion() != null ? new Integer(opcionMenuDTO.getCodigoOpcion()) : null));
               parametros.add(new Parametro("DSOPCION", 12, opcionMenuDTO.getNombreOpcion()));
               parametros.add(new Parametro("IDPADRE", 4, opcionMenuDTO.getCodigoOpcionPadre() != null ? new Integer(opcionMenuDTO.getCodigoOpcionPadre()) : null));
               parametros.add(new Parametro("DSURL", 4, opcionMenuDTO.getUrl()));
               parametros.add(new Parametro("DSTARGET", 4, opcionMenuDTO.getTarget()));
               parametros.add(new Parametro("NMNIVEL", 4, new Integer(opcionMenuDTO.getNivel())));
               parametros.add(new Parametro("NMORDEN", 4, new Integer(opcionMenuDTO.getOrden())));
               parametros.add(new Parametro("SNACTIVA", 12, opcionMenuDTO.getActiva()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, opcionMenuDTO.getCodigoOpcion() != null ? new Integer(opcionMenuDTO.getCodigoOpcion()) : null, 1));
               parametros.add(new Parametro(12, opcionMenuDTO.getNombreOpcion(), 1));
               parametros.add(new Parametro(4, opcionMenuDTO.getCodigoOpcionPadre() != null ? new Integer(opcionMenuDTO.getCodigoOpcionPadre()) : null, 1));
               parametros.add(new Parametro(12, opcionMenuDTO.getUrl(), 1));
               parametros.add(new Parametro(12, opcionMenuDTO.getTarget(), 1));
               parametros.add(new Parametro(4, new Integer(opcionMenuDTO.getNivel()), 1));
               parametros.add(new Parametro(4, new Integer(opcionMenuDTO.getOrden()), 1));
               parametros.add(new Parametro(12, opcionMenuDTO.getActiva(), 1));
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

            if (rs != null && rs.next()) {
               opcionMenuDTO.setCodigoOpcion(rs.getString("IDOPCION"));
               opcionMenuDTO.setNombreOpcion(rs.getString("DSOPCION"));
               opcionMenuDTO.setCodigoOpcionPadre(rs.getString("IDPADRE"));
               opcionMenuDTO.setUrl(rs.getString("DSURL"));
               opcionMenuDTO.setTarget(rs.getString("DSTARGET"));
               opcionMenuDTO.setNivel(rs.getInt("NMNIVEL"));
               opcionMenuDTO.setOrden(rs.getInt("NMORDEN"));
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

      return opcionMenuDTO;
   }

   public OpcionMenuDTO guardarNodo(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuDTO opcionMenuDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("guardarNodo");
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
               parametros.add(new Parametro("IDOPCION", 4, new Integer(opcionMenuDTO.getCodigoOpcion())));
               parametros.add(new Parametro("DSNOMBRE", 12, opcionMenuDTO.getNombreOpcion()));
               parametros.add(new Parametro("IDPADRE", 4, new Integer(opcionMenuDTO.getCodigoOpcionPadre())));
               parametros.add(new Parametro("DSURL", 12, opcionMenuDTO.getUrl()));
               parametros.add(new Parametro("DSTARGET", 12, opcionMenuDTO.getTarget()));
               parametros.add(new Parametro("SNACTIVA", 12, "S"));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  opcionMenuDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  opcionMenuDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, opcionMenuDTO.getCodigoOpcionPadre() != null ? new Integer(opcionMenuDTO.getCodigoOpcionPadre()) : null, 1));
               parametros.add(new Parametro(12, opcionMenuDTO.getNombreOpcion(), 1));
               parametros.add(new Parametro(12, opcionMenuDTO.getUrl(), 1));
               parametros.add(new Parametro(12, opcionMenuDTO.getTarget(), 1));
               parametros.add(new Parametro(12, "S", 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  opcionMenuDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario.startsWith("E_")) {
                     opcionMenuDTO.getMensajeDTO().setTipo(1);
                  } else {
                     opcionMenuDTO.getMensajeDTO().setTipo(2);
                  }

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

      return opcionMenuDTO;
   }

   public OpcionMenuDTO actualizarNodo(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuDTO opcionMenuDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("actualizarNodo");
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
               parametros.add(new Parametro("IDOPCION", 4, new Integer(opcionMenuDTO.getCodigoOpcion())));
               parametros.add(new Parametro("DSNOMBRE", 12, opcionMenuDTO.getNombreOpcion()));
               parametros.add(new Parametro("IDPADRE", 4, new Integer(opcionMenuDTO.getCodigoOpcionPadre())));
               parametros.add(new Parametro("DSURL", 12, opcionMenuDTO.getUrl()));
               parametros.add(new Parametro("DSTARGET", 12, opcionMenuDTO.getTarget()));
               parametros.add(new Parametro("NMNIVEL", 4, new Integer(opcionMenuDTO.getNivel())));
               parametros.add(new Parametro("NMORDEN", 4, new Integer(opcionMenuDTO.getOrden())));
               parametros.add(new Parametro("SNACTIVA", 12, opcionMenuDTO.getActiva()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  opcionMenuDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  opcionMenuDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, new Integer(opcionMenuDTO.getCodigoOpcion()), 1));
               parametros.add(new Parametro(12, opcionMenuDTO.getNombreOpcion(), 1));
               parametros.add(new Parametro(4, opcionMenuDTO.getCodigoOpcionPadre() != null ? new Integer(opcionMenuDTO.getCodigoOpcionPadre()) : null, 1));
               parametros.add(new Parametro(12, opcionMenuDTO.getUrl(), 1));
               parametros.add(new Parametro(12, opcionMenuDTO.getTarget(), 1));
               parametros.add(new Parametro(4, new Integer(opcionMenuDTO.getNivel()), 1));
               parametros.add(new Parametro(4, new Integer(opcionMenuDTO.getOrden()), 1));
               parametros.add(new Parametro(12, opcionMenuDTO.getActiva(), 1));
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

      return opcionMenuDTO;
   }

   public OpcionMenuDTO eliminarNodo(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuDTO opcionMenuDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("eliminarNodo");
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
               parametros.add(new Parametro("IDOPCION", 4, new Integer(opcionMenuDTO.getCodigoOpcion())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  opcionMenuDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  opcionMenuDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, new Integer(opcionMenuDTO.getCodigoOpcion()), 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  opcionMenuDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario.startsWith("E_")) {
                     opcionMenuDTO.getMensajeDTO().setTipo(1);
                  } else {
                     opcionMenuDTO.getMensajeDTO().setTipo(2);
                  }

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

      return opcionMenuDTO;
   }

   public OpcionMenuDTO subirNodo(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuDTO opcionMenuDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("subirNodo");
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
               parametros.add(new Parametro("IDOPCION", 4, new Integer(opcionMenuDTO.getCodigoOpcion())));
               parametros.add(new Parametro("IDOPCANT", 4, new Integer(opcionMenuDTO.getCodigoOpcion())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  opcionMenuDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  opcionMenuDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, new Integer(opcionMenuDTO.getCodigoOpcion()), 1));
               parametros.add(new Parametro(4, new Integer(opcionMenuDTO.getCodigoOpcionAnterior()), 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  opcionMenuDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario.startsWith("E_")) {
                     opcionMenuDTO.getMensajeDTO().setTipo(1);
                  } else {
                     opcionMenuDTO.getMensajeDTO().setTipo(2);
                  }

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

      return opcionMenuDTO;
   }

   public OpcionMenuDTO bajarNodo(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuDTO opcionMenuDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("bajarNodo");
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
               parametros.add(new Parametro("IDOPCION", 4, new Integer(opcionMenuDTO.getCodigoOpcion())));
               parametros.add(new Parametro("IDOPCDES", 4, new Integer(opcionMenuDTO.getCodigoOpcion())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  opcionMenuDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  opcionMenuDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, new Integer(opcionMenuDTO.getCodigoOpcion()), 1));
               parametros.add(new Parametro(4, new Integer(opcionMenuDTO.getCodigoOpcionPosterior()), 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  opcionMenuDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario.startsWith("E_")) {
                     opcionMenuDTO.getMensajeDTO().setTipo(1);
                  } else {
                     opcionMenuDTO.getMensajeDTO().setTipo(2);
                  }

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

      return opcionMenuDTO;
   }

   public OpcionMenuDTO guardarNodoPerfil(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuDTO opcionMenuDTO, PerfilSeguridadDTO perfilSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("guardarNodoPerfil");
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
                  opcionMenuDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario.startsWith("E_")) {
                     opcionMenuDTO.getMensajeDTO().setTipo(1);
                  } else {
                     opcionMenuDTO.getMensajeDTO().setTipo(2);
                  }

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

      return opcionMenuDTO;
   }

   public List<OpcionMenuDTO> consultarOpcionesConPerfil(PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("consultarOpcionesConPerfil");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      List<OpcionMenuDTO> opciones = null;
      OpcionMenuDTO opcionMenuDTO = null;
      PersonalsoftException personalsoftException = null;
      PerfilSeguridadDTO perfilSeguridadDTORS = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         opciones = new ArrayList();

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
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  if (mensajeUsuario != null && !((String)mensajeUsuario).equals("")) {
                     perfilSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje((String)mensajeUsuario));
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
                  opcionMenuDTO = new OpcionMenuDTO();
                  perfilSeguridadDTORS = new PerfilSeguridadDTO();
                  opcionMenuDTO.setCodigoOpcion(rs.getString("IDOPCION"));
                  opcionMenuDTO.setNombreOpcion(rs.getString("DSOPCION"));
                  opcionMenuDTO.setNivel(rs.getInt("NMNIVEL"));
                  opcionMenuDTO.setCodigoOpcionPadre(rs.getString("IDPADRE"));
                  if (rs.getString("IDPERFIL") != null && !rs.getString("IDPERFIL").equalsIgnoreCase("")) {
                     perfilSeguridadDTORS.setCodigoPerfil(rs.getString("IDPERFIL"));
                  } else {
                     perfilSeguridadDTORS.setCodigoPerfil("0");
                  }

                  opcionMenuDTO.setPerfilSeguridadDTO(perfilSeguridadDTORS);
                  opciones.add(opcionMenuDTO);
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

      return opciones;
   }

   public OpcionMenuDTO guardarOpcionPerfil(OpcionMenuDTO opcionMenuDTO, PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("guardarOpcionPerfil");
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
               parametros.add(new Parametro("IDOPCION", 4, new Integer(opcionMenuDTO.getCodigoOpcion())));
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  opcionMenuDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  opcionMenuDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(perfilSeguridadDTO.getCodigoPerfil()), 1));
               parametros.add(new Parametro(4, new Integer(opcionMenuDTO.getCodigoOpcion()), 1));
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(12, "", 3));
               parametros.add(new Parametro(12, "", 3));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  opcionMenuDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (!mensajeUsuario.equals("") && mensajeUsuario.startsWith("E_")) {
                     opcionMenuDTO.getMensajeDTO().setTipo(1);
                  } else {
                     opcionMenuDTO.getMensajeDTO().setTipo(2);
                  }

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

      return opcionMenuDTO;
   }

   public OpcionMenuDTO eliminarOpcionPerfil(OpcionMenuDTO opcionMenuDTO, PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("eliminarOpcionPerfil");
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
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  opcionMenuDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (!mensajeUsuario.equals("") && mensajeUsuario.startsWith("E_")) {
                     opcionMenuDTO.getMensajeDTO().setTipo(1);
                  } else {
                     opcionMenuDTO.getMensajeDTO().setTipo(2);
                  }

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

      return opcionMenuDTO;
   }
}
