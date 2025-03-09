package votre.portaloperaciones.despachocatalogo.labels.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.util.CargadorMsj;
import votre.portaloperaciones.despachocatalogo.labels.beans.DespachoDTO;
import votre.portaloperaciones.despachocatalogo.labels.beans.LabelsDTO;
import votre.portaloperaciones.despachocatalogo.labels.beans.RegistrosLabelsDTO;
import votre.portaloperaciones.despachocatalogo.labels.beans.ZonasDTO;
import votre.portaloperaciones.util.Constantes;

public class LabelsDAO implements ILabelsDAO {
  private BDHelper bdHelper;
  private Logger logger = Logger.getLogger(this.getClass());

  public LabelsDAO(BDHelper bdhelper) {
    super();
    this.bdHelper = bdhelper;
  }

  public LabelsDTO cargarLabels(LabelsDTO labels) throws PersonalsoftException {
    LabelsDTO obtenido = null;
    RegistrosLabelsDTO[] registrosLabels = null;
    CallableStatement cs = null;
    ArrayList<Parametro> parametros = new ArrayList<Parametro>();
    ArrayList<RegistrosLabelsDTO> arrRegistros = new ArrayList<RegistrosLabelsDTO>();
    int pos = 0;

    PersonalsoftException ps = null;
    parametros.add(new Parametro(Types.VARCHAR, labels.getCodCia(), Parametro.IN_OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
    parametros.add(new Parametro(Types.VARCHAR, labels.getZona(), Parametro.IN_OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));

    try {
      String rutaSQL = Configuracion.getInstance().getParametroApp("SP_CARGAR_LABELS");
      if (Constantes.isLeocomus(labels.getCodCia())) {
        rutaSQL = Configuracion.getInstance().getParametroApp("SP_CARGAR_LABELS_USA");
      }
      cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);

      if (cs != null) {
        cs.execute();

        ResultSet rs = cs.getResultSet();

        obtenido = new LabelsDTO();
        obtenido.setCodCia(cs.getString(1).trim());
        obtenido.setTituloApli(cs.getString(2).trim());
        obtenido.setTituloPant(cs.getString(3).trim());
        obtenido.setTituloFecha(cs.getString(4).trim());
        obtenido.setBotonExcel(cs.getString(5).trim());
        obtenido.setPais(cs.getString(6).trim());
        obtenido.setTituloPais(cs.getString(7).trim());
        obtenido.setBotonLabels(cs.getString(8).trim());
        obtenido.setBotonRegresar(cs.getString(9).trim());
        obtenido.setBotonGenerar(cs.getString(10).trim());
        obtenido.setZona(cs.getString(13).trim());
        obtenido.setTituloZona(cs.getString(14).trim());

        obtenido.setEstado(cs.getString(11).trim());
        obtenido.setDesscrpcion(cs.getString(12).trim());

        if (rs != null) {
          while (rs.next()) {
            RegistrosLabelsDTO registros = new RegistrosLabelsDTO();
            registros.setCodigo(rs.getString(1).trim());
            registros.setTituloLabel(rs.getString(2).trim());
            registros.setCantidad(rs.getString(3).trim());
            registros.setIndFecha(rs.getString(4).trim());
            registros.setIndGnLabels(rs.getString(5).trim());
            arrRegistros.add(registros);
          }
        }

        pos = 0;
        registrosLabels = new RegistrosLabelsDTO[arrRegistros.size()];
        for (Iterator<RegistrosLabelsDTO> iter = arrRegistros.iterator(); iter.hasNext();) {
          RegistrosLabelsDTO registrosConsultaDTO = (RegistrosLabelsDTO) iter.next();
          registrosLabels[pos] = registrosConsultaDTO;
          ++pos;
        }
        obtenido.setRegistros(registrosLabels);
      }
    } catch (SQLException e) {
      ps = new PersonalsoftException(e);
      ps.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("errorGeneral"));
      logger.error(ps.getTraza());
      throw ps;
    } finally {
      try {
        BDHelper.close(cs);
      } catch (SQLException e) {
        ps = new PersonalsoftException(e);
        ps.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("errorGeneral"));
        logger.error(ps.getTraza());
        throw ps;
      }
    }

    return obtenido;
  }

  public DespachoDTO consultar(LabelsDTO labels) throws PersonalsoftException {
    DespachoDTO obtenido = null;
    CallableStatement cs = null;
    ArrayList<Parametro> parametros = new ArrayList<Parametro>();

    PersonalsoftException ps = null;
    parametros.add(new Parametro(Types.VARCHAR, labels.getCodCia(), Parametro.IN_OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
    parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));

    try {
      String rutaSQL = Configuracion.getInstance().getParametroApp("SP_CONSUL_DESPACHO");
      cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);

      if (cs != null) {
        cs.execute();

        obtenido = new DespachoDTO();
        obtenido.setCodCia(cs.getString(1).trim());
        obtenido.setTituloApli(cs.getString(2).trim());
        obtenido.setTituloPant(cs.getString(3).trim());
        obtenido.setTituloFecha(cs.getString(4).trim());
        obtenido.setBotonSolicitar(cs.getString(5).trim());
        obtenido.setPais(cs.getString(6).trim());
        obtenido.setTituloPais(cs.getString(7).trim());
        obtenido.setLabelsPendientes(cs.getString(8).trim());
        obtenido.setBotonRegresar(cs.getString(9).trim());
        obtenido.setTituloZona(cs.getString(12).trim());
        obtenido.setCampana(cs.getString(13).trim());

        obtenido.setEstado(cs.getString(10).trim());
        obtenido.setDescripcion(cs.getString(11).trim());
      }

    } catch (SQLException e) {
      ps = new PersonalsoftException(e);
      ps.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("errorGeneral"));
      logger.error(ps.getTraza());
      throw ps;
    } finally {
      try {
        BDHelper.close(cs);
      } catch (SQLException e) {
        ps = new PersonalsoftException(e);
        ps.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("errorGeneral"));
        logger.error(ps.getTraza());
        throw ps;
      }
    }

    return obtenido;
  }

  public ArrayList<ZonasDTO> cargarZonas(LabelsDTO labels) throws PersonalsoftException {
    ArrayList<ZonasDTO> zonas = new ArrayList<ZonasDTO>();
    CallableStatement cs = null;
    ArrayList<Parametro> parametros = new ArrayList<Parametro>();
    ZonasDTO zona;

    PersonalsoftException ps = null;
    parametros.add(new Parametro(Types.VARCHAR, labels.getCodCia(), Parametro.IN));

    try {
      String rutaSQL = Configuracion.getInstance().getParametroApp("SP_ZONAS");
      if (Constantes.isLeocomus(labels.getCodCia())) {
        rutaSQL = Configuracion.getInstance().getParametroApp("SP_ZONAS_USA");
      }

      cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);

      if (cs != null) {
        cs.execute();

        ResultSet rs = cs.getResultSet();

        if (rs != null) {
          while (rs.next()) {
            zona = new ZonasDTO();
            zona.setZona(rs.getString(1).trim());
            zonas.add(zona);
          }
        }
      }
    } catch (SQLException e) {
      ps = new PersonalsoftException(e);
      ps.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("errorGeneral"));
      logger.error(ps.getTraza());
      throw ps;
    } finally {
      try {
        BDHelper.close(cs);
      } catch (SQLException e) {
        ps = new PersonalsoftException(e);
        ps.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("errorGeneral"));
        logger.error(ps.getTraza());
        throw ps;
      }
    }

    return zonas;
  }
}
