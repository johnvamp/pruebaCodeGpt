package co.com.personalsoft.seguridad.dao;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.seguridad.beans.AplicacionSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.GenericaDTO;
import co.com.personalsoft.base.seguridad.beans.PerfilSeguridadDTO;
import co.com.personalsoft.base.util.CargadorMsj;
import co.com.personalsoft.seguridad.servicio.GestorSeguridad;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenericasDAO {
   private BDHelper bdHelper;
   private boolean ejecucionPorQuerys;

   public GenericasDAO() {
      String tipoRecurso = (String)GestorSeguridad.getInstance().getParametro("TIPO");
      this.ejecucionPorQuerys = tipoRecurso != null && tipoRecurso.equals("SQL");
   }

   public GenericasDAO(BDHelper helper) {
      this();
      this.bdHelper = helper;
   }

   public List<GenericaDTO> consultarEntidadesGenericas(AplicacionSeguridadDTO aplicacionSeguridadDTO, GenericaDTO entidadGenericaDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("consultarGenericas");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      List<GenericaDTO> genericos = null;
      GenericaDTO genericaDTO = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         genericos = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("IDGENERICA", 4, entidadGenericaDTO != null ? new Integer(entidadGenericaDTO.getAtributo1()) : 0));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, entidadGenericaDTO != null ? new Integer(entidadGenericaDTO.getAtributo1()) : 0, 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
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
                  genericaDTO = new GenericaDTO();
                  genericaDTO.setAtributo1(rs.getString("CAMPO1"));
                  genericaDTO.setAtributo2(rs.getString("CAMPO2"));
                  genericaDTO.setAtributo3(rs.getString("CAMPO3"));
                  genericos.add(genericaDTO);
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

      return genericos;
   }

   public GenericaDTO consultarEntidadGenerica(AplicacionSeguridadDTO aplicacionSeguridadDTO, GenericaDTO entidadGenericaDTO) throws PersonalsoftException {
      List<GenericaDTO> genericas = this.consultarEntidadesGenericas(aplicacionSeguridadDTO, entidadGenericaDTO);
      return genericas != null && !genericas.isEmpty() ? (GenericaDTO)genericas.get(0) : null;
   }

   public GenericaDTO guardarGenericaXPerfil(GenericaDTO genericaDTO, PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("guardarGenericaXPerfil");
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
               parametros.add(new Parametro("IDGENERICO", 4, new Integer(genericaDTO.getAtributo1())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  genericaDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  genericaDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, new Integer(perfilSeguridadDTO.getCodigoPerfil()), 1));
               parametros.add(new Parametro(4, new Integer(genericaDTO.getAtributo1()), 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  genericaDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     genericaDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     genericaDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (genericaDTO.getMensajeDTO().getMensajeTecnico() != null && !genericaDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(genericaDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(genericaDTO.getMensajeDTO().getMensajeUsuario());
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

      return genericaDTO;
   }

   public GenericaDTO eliminarGenericaXPerfil(GenericaDTO genericaDTO, PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("eliminarGenericaXPerfil");
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
               parametros.add(new Parametro("IDGENERICO", 4, new Integer(genericaDTO.getAtributo1())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  genericaDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  genericaDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, new Integer(perfilSeguridadDTO.getCodigoPerfil()), 1));
               parametros.add(new Parametro(4, new Integer(genericaDTO.getAtributo1()), 1));
               parametros.add(new Parametro(12, "", 2));
               parametros.add(new Parametro(12, "", 2));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  genericaDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     genericaDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     genericaDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (genericaDTO.getMensajeDTO().getMensajeTecnico() != null && !genericaDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(genericaDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(genericaDTO.getMensajeDTO().getMensajeUsuario());
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

      return genericaDTO;
   }

   public List<GenericaDTO> consultarEntidadesGenericaXPerfil(AplicacionSeguridadDTO aplicacionSeguridadDTO, PerfilSeguridadDTO perfilSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("consultarGenericasXPerfil");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      List<GenericaDTO> genericos = null;
      GenericaDTO genericaDTO = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         genericos = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("IDPERFIL", 4, perfilSeguridadDTO.getCodigoPerfil() != null && !perfilSeguridadDTO.getCodigoPerfil().trim().equals("") ? new Integer(perfilSeguridadDTO.getCodigoPerfil()) : null));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, perfilSeguridadDTO.getCodigoPerfil() != null && !perfilSeguridadDTO.getCodigoPerfil().trim().equals("") ? new Integer(perfilSeguridadDTO.getCodigoPerfil()) : null, 1));
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
                  genericaDTO = new GenericaDTO();
                  genericaDTO.setAtributo1(rs.getString("CAMPO1"));
                  genericaDTO.setAtributo2(rs.getString("CAMPO2"));
                  genericaDTO.setAtributo3(rs.getString("CAMPO3"));
                  genericos.add(genericaDTO);
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

      return genericos;
   }
}
