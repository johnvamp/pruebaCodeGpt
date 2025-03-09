package co.com.personalsoft.seguridad.bd;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.paginacion.beans.PaginacionDTO;
import co.com.personalsoft.base.seguridad.beans.AplicacionSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.UsuarioSeguridadDTO;
import co.com.personalsoft.base.seguridad.encripcion.MD5;
import co.com.personalsoft.seguridad.dao.UsuariosDAO;
import java.util.List;

public class UsuariosHelperBD {
   public UsuarioSeguridadDTO consultarUsuario(UsuarioSeguridadDTO usuarioSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      UsuariosDAO usuariosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         usuariosDAO = new UsuariosDAO(bdHelper);
         usuarioSeguridadDTO = usuariosDAO.consultarUsuario(usuarioSeguridadDTO, aplicacionSeguridadDTO);
      } catch (Exception var10) {
         bdConfiguracionHelper.evaluarExcepcion(var10, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return usuarioSeguridadDTO;
   }

   public List<UsuarioSeguridadDTO> consultarUsuarios(UsuarioSeguridadDTO usuarioSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO, PaginacionDTO paginacionDTO) throws PersonalsoftException {
      UsuariosDAO usuariosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      List usuarios = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         usuariosDAO = new UsuariosDAO(bdHelper);
         usuarios = usuariosDAO.consultarUsuarios(usuarioSeguridadDTO, aplicacionSeguridadDTO, paginacionDTO);
      } catch (Exception var12) {
         bdConfiguracionHelper.evaluarExcepcion(var12, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return usuarios;
   }

   public List<UsuarioSeguridadDTO> consultarUsuarios(UsuarioSeguridadDTO usuarioSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      UsuariosDAO usuariosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      List usuarios = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         usuariosDAO = new UsuariosDAO(bdHelper);
         usuarios = usuariosDAO.consultarUsuarios(usuarioSeguridadDTO, aplicacionSeguridadDTO);
      } catch (Exception var11) {
         bdConfiguracionHelper.evaluarExcepcion(var11, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return usuarios;
   }

   public UsuarioSeguridadDTO guardarUsuario(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO) throws PersonalsoftException {
      UsuariosDAO usuariosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      String claveEncriptada = null;
      MD5 md5 = new MD5();

      try {
         claveEncriptada = md5.getHashString(usuarioSeguridadDTO.getUsuario());
         usuarioSeguridadDTO.setClave(claveEncriptada);
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         usuariosDAO = new UsuariosDAO(bdHelper);
         usuarioSeguridadDTO = usuariosDAO.guardarUsuario(aplicacionSeguridadDTO, usuarioSeguridadDTO);
      } catch (Exception var12) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var12, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return usuarioSeguridadDTO;
   }

   public UsuarioSeguridadDTO actualizarUsuario(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO) throws PersonalsoftException {
      UsuariosDAO usuariosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         usuariosDAO = new UsuariosDAO(bdHelper);
         usuarioSeguridadDTO = usuariosDAO.actualizarUsuario(aplicacionSeguridadDTO, usuarioSeguridadDTO);
      } catch (Exception var10) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var10, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return usuarioSeguridadDTO;
   }

   public boolean cambiarClave(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO) throws PersonalsoftException {
      UsuariosDAO usuariosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      boolean actualizada = false;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         usuariosDAO = new UsuariosDAO(bdHelper);
         actualizada = usuariosDAO.cambiarClave(aplicacionSeguridadDTO, usuarioSeguridadDTO);
      } catch (Exception var11) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var11, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return actualizada;
   }

   public UsuarioSeguridadDTO eliminarUsuario(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO) throws PersonalsoftException {
      UsuariosDAO usuariosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         usuariosDAO = new UsuariosDAO(bdHelper);
         usuarioSeguridadDTO = usuariosDAO.eliminarUsuario(aplicacionSeguridadDTO, usuarioSeguridadDTO);
      } catch (Exception var10) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var10, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return usuarioSeguridadDTO;
   }

   public UsuarioSeguridadDTO guardarPerfilUsuario(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO) throws PersonalsoftException {
      UsuariosDAO usuariosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         usuariosDAO = new UsuariosDAO(bdHelper);
         usuarioSeguridadDTO = usuariosDAO.guardarPerfilUsuario(aplicacionSeguridadDTO, usuarioSeguridadDTO);
      } catch (Exception var10) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var10, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return usuarioSeguridadDTO;
   }

   public UsuarioSeguridadDTO eliminarPerfilUsuario(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO) throws PersonalsoftException {
      UsuariosDAO usuariosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         usuariosDAO = new UsuariosDAO(bdHelper);
         usuarioSeguridadDTO = usuariosDAO.eliminarPerfilUsuario(aplicacionSeguridadDTO, usuarioSeguridadDTO);
      } catch (Exception var10) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var10, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return usuarioSeguridadDTO;
   }

   public UsuarioSeguridadDTO autenticarUsuario(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO) throws PersonalsoftException {
      UsuariosDAO usuariosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      String claveEncriptada = null;
      MD5 md5 = new MD5();

      try {
         claveEncriptada = md5.getHashString(usuarioSeguridadDTO.getClave());
         usuarioSeguridadDTO.setClave(claveEncriptada);
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         usuariosDAO = new UsuariosDAO(bdHelper);
         usuarioSeguridadDTO = usuariosDAO.autenticarUsuario(aplicacionSeguridadDTO, usuarioSeguridadDTO);
      } catch (Exception var12) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var12, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return usuarioSeguridadDTO;
   }
}
