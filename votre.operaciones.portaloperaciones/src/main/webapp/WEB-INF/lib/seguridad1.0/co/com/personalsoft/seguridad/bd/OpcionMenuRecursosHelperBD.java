package co.com.personalsoft.seguridad.bd;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.seguridad.beans.AplicacionSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.OpcionMenuDTO;
import co.com.personalsoft.base.seguridad.beans.OpcionMenuRecursoSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.RecursoSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.UsuarioSeguridadDTO;
import co.com.personalsoft.seguridad.dao.OpcionMenuRecursosDAO;
import java.util.List;

public class OpcionMenuRecursosHelperBD {
   public OpcionMenuRecursoSeguridadDTO asociarOpcionesMenuRecursosPerfilAplicacion(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuRecursoSeguridadDTO opcionMenuRecursoSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO, List<RecursoSeguridadDTO> listaRecursosPerfil) throws PersonalsoftException {
      OpcionMenuRecursosDAO opcionMenuRecursosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         opcionMenuRecursosDAO = new OpcionMenuRecursosDAO(bdHelper);
         opcionMenuRecursoSeguridadDTO = opcionMenuRecursosDAO.asociarOpcionesMenuRecursosPerfilAplicacion(aplicacionSeguridadDTO, opcionMenuRecursoSeguridadDTO, usuarioSeguridadDTO, listaRecursosPerfil);
      } catch (Exception var12) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var12, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return opcionMenuRecursoSeguridadDTO;
   }

   public boolean existeOpcionPerfil(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO, OpcionMenuDTO opcionMenuDTO) throws PersonalsoftException {
      OpcionMenuRecursosDAO opcionMenuRecursosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      boolean opcionPerfil = false;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         opcionMenuRecursosDAO = new OpcionMenuRecursosDAO(bdHelper);
         opcionPerfil = opcionMenuRecursosDAO.existeOpcionPerfil(aplicacionSeguridadDTO, usuarioSeguridadDTO, opcionMenuDTO);
      } catch (Exception var12) {
         bdConfiguracionHelper.evaluarExcepcion(var12, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return opcionPerfil;
   }

   public boolean existeRecursoPerfil(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO, RecursoSeguridadDTO recursoSeguridadDTO) throws PersonalsoftException {
      OpcionMenuRecursosDAO opcionMenuRecursosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      boolean recursoPerfil = false;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         opcionMenuRecursosDAO = new OpcionMenuRecursosDAO(bdHelper);
         recursoPerfil = opcionMenuRecursosDAO.existeRecursoPerfil(aplicacionSeguridadDTO, usuarioSeguridadDTO, recursoSeguridadDTO);
      } catch (Exception var12) {
         bdConfiguracionHelper.evaluarExcepcion(var12, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return recursoPerfil;
   }
}
