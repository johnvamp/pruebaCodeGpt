package votre.portaloperaciones.solicitudcatalogo.compradora.facade;

import java.sql.SQLException;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import votre.dao.DAOFactory;
import votre.portaloperaciones.solicitudcatalogo.catalogo.beans.CatalogoDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.beans.CatalogoCompradoraDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.beans.CompradoraDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.beans.EnviosDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.manager.CompradoraMgr;
import votre.portaloperaciones.util.Constantes;

public class CompradoraFacade implements ICompradoraFacade {

  public CompradoraDTO verInformacionCompradora(CatalogoDTO catalogo) throws PersonalsoftException {
    DAOFactory daoFactory = null;
    CompradoraMgr compradoraMgr = null;
    CompradoraDTO obtenido = null;
    try {
      if (Constantes.isLeocomus(catalogo.getCodCia())) {
        daoFactory = new DAOFactory(Configuracion.getInstance().getParametro("jndiUSA"));
      } else {
        daoFactory = new DAOFactory();
      }
      compradoraMgr = new CompradoraMgr();
      obtenido = compradoraMgr.verInformacionCompradora(daoFactory, catalogo);
    } catch (Exception e) {
      if (e instanceof PersonalsoftException) {
        throw (PersonalsoftException) e;
      } else {
        throw new PersonalsoftException(e);
      }
    } finally {
      try {
        daoFactory.cerrarConexion();
      } catch (SQLException exception) {
        throw new PersonalsoftException(exception);
      }
    }
    return obtenido;
  }

  public CompradoraDTO verListadoCompradoras(CompradoraDTO compradora) throws PersonalsoftException {
    DAOFactory daoFactory = null;
    CompradoraMgr compradoraMgr = null;
    CompradoraDTO obtenido = null;
    try {
      if (Constantes.isLeocomus(compradora.getCodCia())) {
        daoFactory = new DAOFactory(Configuracion.getInstance().getParametro("jndiUSA"));
      } else {
        daoFactory = new DAOFactory();
      }
      compradoraMgr = new CompradoraMgr();
      obtenido = compradoraMgr.verListadoCompradoras(daoFactory, compradora);
    } catch (Exception e) {
      if (e instanceof PersonalsoftException) {
        throw (PersonalsoftException) e;
      } else {
        throw new PersonalsoftException(e);
      }
    } finally {
      try {
        daoFactory.cerrarConexion();
      } catch (SQLException exception) {
        throw new PersonalsoftException(exception);
      }
    }
    return obtenido;
  }

  public CatalogoCompradoraDTO consultarCatalogos(String codCia) throws PersonalsoftException {
    DAOFactory daoFactory = null;
    CompradoraMgr compradoraMgr = null;
    CatalogoCompradoraDTO obtenido = null;
    try {
      if (Constantes.isLeocomus(codCia)) {
        daoFactory = new DAOFactory(Configuracion.getInstance().getParametro("jndiUSA"));
      } else {
        daoFactory = new DAOFactory();
      }
      compradoraMgr = new CompradoraMgr();
      obtenido = compradoraMgr.consultarCatalogos(daoFactory, codCia);
    } catch (Exception e) {
      if (e instanceof PersonalsoftException) {
        throw (PersonalsoftException) e;
      } else {
        throw new PersonalsoftException(e);
      }
    } finally {
      try {
        daoFactory.cerrarConexion();
      } catch (SQLException exception) {
        throw new PersonalsoftException(exception);
      }
    }
    return obtenido;
  }

  public EnviosDTO verResumenEnvios(CompradoraDTO compradora) throws PersonalsoftException {
    DAOFactory daoFactory = null;
    CompradoraMgr compradoraMgr = null;
    EnviosDTO obtenido = null;
    try {
      if (Constantes.isLeocomus(compradora.getCodCia())) {
        daoFactory = new DAOFactory(Configuracion.getInstance().getParametro("jndiUSA"));
      } else {
        daoFactory = new DAOFactory();
      }
      compradoraMgr = new CompradoraMgr();
      obtenido = compradoraMgr.verResumenEnvios(daoFactory, compradora);
    } catch (Exception e) {
      if (e instanceof PersonalsoftException) {
        throw (PersonalsoftException) e;
      } else {
        throw new PersonalsoftException(e);
      }
    } finally {
      try {
        daoFactory.cerrarConexion();
      } catch (SQLException exception) {
        throw new PersonalsoftException(exception);
      }
    }
    return obtenido;
  }

  public CompradoraDTO guardar(CompradoraDTO compradora) throws PersonalsoftException {
    DAOFactory daoFactory = null;
    CompradoraMgr compradoraMgr = null;
    CompradoraDTO obtenido = null;
    try {
      if (Constantes.isLeocomus(compradora.getCodCia())) {
        daoFactory = new DAOFactory(Configuracion.getInstance().getParametro("jndiUSA"));
      } else {
        daoFactory = new DAOFactory();
      }
      compradoraMgr = new CompradoraMgr();
      obtenido = compradoraMgr.guardar(daoFactory, compradora);
    } catch (Exception e) {
      if (e instanceof PersonalsoftException) {
        throw (PersonalsoftException) e;
      } else {
        throw new PersonalsoftException(e);
      }
    } finally {
      try {
        daoFactory.cerrarConexion();
      } catch (SQLException exception) {
        throw new PersonalsoftException(exception);
      }
    }
    return obtenido;
  }

  public CompradoraDTO eliminar(CompradoraDTO compradora) throws PersonalsoftException {
    DAOFactory daoFactory = null;
    CompradoraMgr compradoraMgr = null;
    CompradoraDTO obtenido = null;
    try {
      if (Constantes.isLeocomus(compradora.getCodCia())) {
        daoFactory = new DAOFactory(Configuracion.getInstance().getParametro("jndiUSA"));
      } else {
        daoFactory = new DAOFactory();
      }
      compradoraMgr = new CompradoraMgr();
      obtenido = compradoraMgr.eliminar(daoFactory, compradora);
    } catch (Exception e) {
      if (e instanceof PersonalsoftException) {
        throw (PersonalsoftException) e;
      } else {
        throw new PersonalsoftException(e);
      }
    } finally {
      try {
        daoFactory.cerrarConexion();
      } catch (SQLException exception) {
        throw new PersonalsoftException(exception);
      }
    }
    return obtenido;
  }

  public CompradoraDTO getDatosCompradoraXGuia(CompradoraDTO compradora) throws PersonalsoftException {
    DAOFactory daoFactory = null;
    CompradoraMgr compradoraMgr = null;
    CompradoraDTO obtenido = null;
    try {
      daoFactory = new DAOFactory();
      compradoraMgr = new CompradoraMgr();
      obtenido = compradoraMgr.getDatosCompradoraXGuia(daoFactory, compradora);
      daoFactory.commitTransaction();
    } catch (Exception e) {
      if (e instanceof PersonalsoftException) {
        throw (PersonalsoftException) e;
      } else {
        throw new PersonalsoftException(e);
      }
    } finally {
      try {
        daoFactory.cerrarConexion();
      } catch (SQLException exception) {
        throw new PersonalsoftException(exception);
      }
    }
    return obtenido;
  }
}
