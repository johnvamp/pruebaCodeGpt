package co.com.personalsoft.seguridad.dao;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.paginacion.beans.PaginacionDTO;
import co.com.personalsoft.base.seguridad.beans.AplicacionSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.CargoSeguridadDTO;
import co.com.personalsoft.base.util.CargadorMsj;
import co.com.personalsoft.seguridad.servicio.GestorSeguridad;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CargosDAO {
   private BDHelper bdHelper;
   private boolean ejecucionPorQuerys;

   public CargosDAO() {
      String tipoRecurso = (String)GestorSeguridad.getInstance().getParametro("TIPO");
      this.ejecucionPorQuerys = tipoRecurso != null && tipoRecurso.equals("SQL");
   }

   public CargosDAO(BDHelper helper) {
      this();
      this.bdHelper = helper;
   }

   public List<CargoSeguridadDTO> consultarCargos(CargoSeguridadDTO cargoSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO, PaginacionDTO paginacionDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("listarCargosPaginacion");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      List<CargoSeguridadDTO> cargos = null;
      CargoSeguridadDTO cargoSeguridadDataDTO = null;
      PersonalsoftException personalsoftException = null;
      BigDecimal totalRegistrosSp = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         cargos = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("REGINI", 12, paginacionDTO.getRegistroInicial()));
               parametros.add(new Parametro("REGFIN", 12, paginacionDTO.getRegistroFinal()));
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("IDCARGO", 4, cargoSeguridadDTO.getCodigoCargo() != null && !cargoSeguridadDTO.getCodigoCargo().trim().equals("") ? new Integer(cargoSeguridadDTO.getCodigoCargo()) : null));
               parametros.add(new Parametro("DSCARGO", 12, cargoSeguridadDTO.getNombreCargo()));
               parametros.add(new Parametro("NIVEL", 4, cargoSeguridadDTO.getNivel() != null && !cargoSeguridadDTO.getNivel().trim().equals("") ? new Integer(cargoSeguridadDTO.getNivel()) : null));
               parametros.add(new Parametro("CONTAVANCE", 2, paginacionDTO.getControlAvance()));
               parametros.add(new Parametro("NUMREG", 2, paginacionDTO.getRegistrosPorPagina()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro(4, new Integer(paginacionDTO.getRegistroInicial()), 1));
               parametros.add(new Parametro(4, new Integer(paginacionDTO.getRegistroFinal()), 1));
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, cargoSeguridadDTO.getCodigoCargo() != null && !cargoSeguridadDTO.getCodigoCargo().trim().equals("") ? new Integer(cargoSeguridadDTO.getCodigoCargo()) : null, 1));
               parametros.add(new Parametro(12, cargoSeguridadDTO.getNombreCargo(), 1));
               parametros.add(new Parametro(4, cargoSeguridadDTO.getNivel() != null && !cargoSeguridadDTO.getNivel().trim().equals("") ? new Integer(cargoSeguridadDTO.getNivel()) : null, 1));
               parametros.add(new Parametro(2, paginacionDTO.getControlAvance(), 1));
               parametros.add(new Parametro(2, paginacionDTO.getRegistrosPorPagina(), 1));
               parametros.add(new Parametro(2, 0, 3));
               parametros.add(new Parametro(12, "", 3));
               parametros.add(new Parametro(12, "", 3));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  cargoSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     cargoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     cargoSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (cargoSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !cargoSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(cargoSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(cargoSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }

                  rs = cs.getResultSet();
                  totalRegistrosSp = (BigDecimal)cs.getObject(parametros.size() - 2);
                  paginacionDTO.setTotalRegistros((long)totalRegistrosSp.intValue());
               }
            }

            if (rs != null) {
               while(rs.next()) {
                  cargoSeguridadDataDTO = new CargoSeguridadDTO();
                  if (this.ejecucionPorQuerys) {
                     paginacionDTO.setTotalRegistros((long)rs.getInt("TOTREG"));
                  }

                  cargoSeguridadDataDTO.setCodigoCargo(rs.getString("IDCARGO").trim());
                  cargoSeguridadDataDTO.setNombreCargo(rs.getString("DSCARGO").trim());
                  cargoSeguridadDataDTO.setNivel(rs.getString("IDNIVEL").trim());
                  cargos.add(cargoSeguridadDataDTO);
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

      return cargos;
   }

   public List<CargoSeguridadDTO> consultarCargos(CargoSeguridadDTO cargoSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("consultarCargos");
      CallableStatement cs = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ArrayList<Parametro> parametros = null;
      List<CargoSeguridadDTO> cargos = null;
      CargoSeguridadDTO cargoSeguridadDataDTO = null;
      PersonalsoftException personalsoftException = null;
      String mensajeUsuario = null;
      if (rutaQueryMensaje != null && rutaQueryMensaje.length() > 0) {
         parametros = new ArrayList();
         cargos = new ArrayList();

         try {
            if (this.ejecucionPorQuerys) {
               parametros.add(new Parametro("IDAPP", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("IDCARGO", 4, cargoSeguridadDTO.getCodigoCargo() != null && !cargoSeguridadDTO.getCodigoCargo().trim().equals("") ? new Integer(cargoSeguridadDTO.getCodigoCargo()) : null));
               parametros.add(new Parametro("DSCARGO", 12, cargoSeguridadDTO.getNombreCargo()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, cargoSeguridadDTO.getCodigoCargo() != null && !cargoSeguridadDTO.getCodigoCargo().trim().equals("") ? new Integer(cargoSeguridadDTO.getCodigoCargo()) : null, 1));
               parametros.add(new Parametro(12, cargoSeguridadDTO.getNombreCargo(), 1));
               parametros.add(new Parametro(12, "", 3));
               parametros.add(new Parametro(12, "", 3));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  cargoSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     cargoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     cargoSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (cargoSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !cargoSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(cargoSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(cargoSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }

                  rs = cs.getResultSet();
               }
            }

            if (rs != null) {
               while(rs.next()) {
                  cargoSeguridadDataDTO = new CargoSeguridadDTO();
                  cargoSeguridadDataDTO.setCodigoCargo(rs.getString("IDCARGO"));
                  cargoSeguridadDataDTO.setNombreCargo(rs.getString("DSCARGO"));
                  cargos.add(cargoSeguridadDataDTO);
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

      return cargos;
   }

   public CargoSeguridadDTO consultarCargo(CargoSeguridadDTO cargoSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("consultarCargo");
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
               parametros.add(new Parametro("IDCARGO", 4, cargoSeguridadDTO.getCodigoCargo() != null && !cargoSeguridadDTO.getCodigoCargo().trim().equals("") ? new Integer(cargoSeguridadDTO.getCodigoCargo()) : null));
               parametros.add(new Parametro("DSCARGO", 12, cargoSeguridadDTO.getNombreCargo()));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  rs = ps.executeQuery();
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, new Integer(cargoSeguridadDTO.getCodigoCargo()), 1));
               parametros.add(new Parametro(12, cargoSeguridadDTO.getNombreCargo(), 1));
               parametros.add(new Parametro(12, "", 3));
               parametros.add(new Parametro(12, "", 3));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  cargoSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     cargoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     cargoSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (cargoSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !cargoSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(cargoSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(cargoSeguridadDTO.getMensajeDTO().getMensajeUsuario());
                     throw personalsoftException;
                  }

                  rs = cs.getResultSet();
               }
            }

            if (rs != null && rs.next()) {
               cargoSeguridadDTO.setNombreCargo(rs.getString("DSCARGO").trim());
               cargoSeguridadDTO.setNivel(rs.getString("IDNIVEL").trim());
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

      return cargoSeguridadDTO;
   }

   public CargoSeguridadDTO guardarCargo(CargoSeguridadDTO cargoSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("guardarCargo");
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
               parametros.add(new Parametro("IDCARGO", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("DSCARGO", 12, cargoSeguridadDTO.getNombreCargo()));
               parametros.add(new Parametro("NIVEL", 4, new Integer(cargoSeguridadDTO.getNivel())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  cargoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  cargoSeguridadDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, new Integer(cargoSeguridadDTO.getCodigoCargo()), 1));
               parametros.add(new Parametro(12, cargoSeguridadDTO.getNombreCargo(), 1));
               parametros.add(new Parametro(4, new Integer(cargoSeguridadDTO.getNivel()), 1));
               parametros.add(new Parametro(12, "", 3));
               parametros.add(new Parametro(12, "", 3));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  cargoSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (!mensajeUsuario.equals("") && mensajeUsuario.startsWith("E_")) {
                     cargoSeguridadDTO.getMensajeDTO().setTipo(1);
                  } else {
                     cargoSeguridadDTO.getMensajeDTO().setTipo(2);
                  }

                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     cargoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     cargoSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (cargoSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !cargoSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(cargoSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(cargoSeguridadDTO.getMensajeDTO().getMensajeUsuario());
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

      return cargoSeguridadDTO;
   }

   public CargoSeguridadDTO actualizarCargo(CargoSeguridadDTO cargoSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("actualizarCargo");
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
               parametros.add(new Parametro("IDCARGO", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               parametros.add(new Parametro("DSCARGO", 12, cargoSeguridadDTO.getNombreCargo()));
               parametros.add(new Parametro("NIVEL", 4, new Integer(cargoSeguridadDTO.getNivel())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  cargoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  cargoSeguridadDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, new Integer(cargoSeguridadDTO.getCodigoCargo()), 1));
               parametros.add(new Parametro(12, cargoSeguridadDTO.getNombreCargo(), 1));
               parametros.add(new Parametro(4, new Integer(cargoSeguridadDTO.getNivel()), 1));
               parametros.add(new Parametro(12, "", 3));
               parametros.add(new Parametro(12, "", 3));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  cargoSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (!mensajeUsuario.equals("") && mensajeUsuario.startsWith("E_")) {
                     cargoSeguridadDTO.getMensajeDTO().setTipo(1);
                  } else {
                     cargoSeguridadDTO.getMensajeDTO().setTipo(2);
                  }

                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     cargoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     cargoSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (cargoSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !cargoSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(cargoSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(cargoSeguridadDTO.getMensajeDTO().getMensajeUsuario());
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

      return cargoSeguridadDTO;
   }

   public CargoSeguridadDTO eliminarCargo(CargoSeguridadDTO cargoSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      String rutaQueryMensaje = (String)GestorSeguridad.getInstance().getParametro("eliminarCargo");
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
               parametros.add(new Parametro("IDCARGO", 4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion())));
               ps = this.bdHelper.cargarPreparedStatement(rutaQueryMensaje, parametros);
               if (ps != null) {
                  ps.executeUpdate();
                  cargoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje("002"));
                  cargoSeguridadDTO.getMensajeDTO().setMensajeTecnico("");
               }
            } else {
               parametros.add(new Parametro(4, new Integer(aplicacionSeguridadDTO.getCodigoAplicacion()), 1));
               parametros.add(new Parametro(4, new Integer(cargoSeguridadDTO.getCodigoCargo()), 1));
               parametros.add(new Parametro(12, "", 3));
               parametros.add(new Parametro(12, "", 3));
               cs = this.bdHelper.cargarCallableStatement(rutaQueryMensaje, parametros);
               if (cs != null) {
                  cs.execute();
                  cargoSeguridadDTO.getMensajeDTO().setMensajeTecnico(cs.getString("MSGT") != null ? cs.getString("MSGT").trim() : "");
                  mensajeUsuario = cs.getString("MSGU") != null ? cs.getString("MSGU").trim() : "";
                  if (!mensajeUsuario.equals("") && mensajeUsuario.startsWith("E_")) {
                     cargoSeguridadDTO.getMensajeDTO().setTipo(1);
                  } else {
                     cargoSeguridadDTO.getMensajeDTO().setTipo(2);
                  }

                  if (mensajeUsuario != null && !mensajeUsuario.equals("")) {
                     cargoSeguridadDTO.getMensajeDTO().setMensajeUsuario(CargadorMsj.getInstance().getMensaje(mensajeUsuario));
                  } else {
                     cargoSeguridadDTO.getMensajeDTO().setMensajeUsuario("");
                  }

                  if (cargoSeguridadDTO.getMensajeDTO().getMensajeTecnico() != null && !cargoSeguridadDTO.getMensajeDTO().getMensajeTecnico().equals("")) {
                     personalsoftException = new PersonalsoftException();
                     personalsoftException.setMensajeTecnico(cargoSeguridadDTO.getMensajeDTO().getMensajeTecnico());
                     personalsoftException.setMensajeUsuario(cargoSeguridadDTO.getMensajeDTO().getMensajeUsuario());
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

      return cargoSeguridadDTO;
   }
}
