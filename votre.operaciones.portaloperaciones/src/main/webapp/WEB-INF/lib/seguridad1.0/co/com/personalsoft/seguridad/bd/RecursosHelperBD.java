package co.com.personalsoft.seguridad.bd;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.paginacion.beans.PaginacionDTO;
import co.com.personalsoft.base.seguridad.beans.AplicacionSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.PerfilSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.RecursoSeguridadDTO;
import co.com.personalsoft.seguridad.dao.RecursosDAO;
import co.com.personalsoft.seguridad.servicio.GestorSeguridad;
import java.util.HashSet;
import java.util.List;

public class RecursosHelperBD {
   public List<RecursoSeguridadDTO> cargarRecursos(RecursoSeguridadDTO recursoSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      RecursosDAO recursosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      List recursos = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         recursosDAO = new RecursosDAO(bdHelper);
         recursos = recursosDAO.consultarRecursos(recursoSeguridadDTO, aplicacionSeguridadDTO);
      } catch (Exception var11) {
         bdConfiguracionHelper.evaluarExcepcion(var11, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return recursos;
   }

   public List<RecursoSeguridadDTO> consultarRecursosPerfil(PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      RecursosDAO recursosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      List recursos = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         recursosDAO = new RecursosDAO(bdHelper);
         recursos = recursosDAO.consultarRecursosPerfil(perfilSeguridadDTO, aplicacionSeguridadDTO);
      } catch (Exception var11) {
         bdConfiguracionHelper.evaluarExcepcion(var11, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return recursos;
   }

   public RecursoSeguridadDTO consultarRecurso(RecursoSeguridadDTO recursoSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      RecursosDAO recursosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         recursosDAO = new RecursosDAO(bdHelper);
         recursoSeguridadDTO = recursosDAO.consultarRecurso(recursoSeguridadDTO, aplicacionSeguridadDTO);
      } catch (Exception var10) {
         bdConfiguracionHelper.evaluarExcepcion(var10, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return recursoSeguridadDTO;
   }

   public List<RecursoSeguridadDTO> consultarRecursos(RecursoSeguridadDTO recursoSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      RecursosDAO recursosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      List recursos = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         recursosDAO = new RecursosDAO(bdHelper);
         recursos = recursosDAO.consultarRecursos(recursoSeguridadDTO, aplicacionSeguridadDTO);
      } catch (Exception var11) {
         bdConfiguracionHelper.evaluarExcepcion(var11, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return recursos;
   }

   public List<RecursoSeguridadDTO> consultarRecursos(RecursoSeguridadDTO recursoSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO, PaginacionDTO paginacionDTO) throws PersonalsoftException {
      RecursosDAO recursosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      List recursos = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         recursosDAO = new RecursosDAO(bdHelper);
         recursos = recursosDAO.consultarRecursos(recursoSeguridadDTO, aplicacionSeguridadDTO, paginacionDTO);
      } catch (Exception var12) {
         bdConfiguracionHelper.evaluarExcepcion(var12, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return recursos;
   }

   public RecursoSeguridadDTO guardarRecurso(RecursoSeguridadDTO recursoSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      RecursosDAO recursosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         recursosDAO = new RecursosDAO(bdHelper);
         recursoSeguridadDTO = recursosDAO.guardarRecurso(recursoSeguridadDTO, aplicacionSeguridadDTO);
         GestorSeguridad.getInstance().putRecurso(recursoSeguridadDTO);
      } catch (Exception var10) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var10, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return recursoSeguridadDTO;
   }

   public RecursoSeguridadDTO actualizarRecurso(RecursoSeguridadDTO recursoSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      RecursosDAO recursosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         recursosDAO = new RecursosDAO(bdHelper);
         recursoSeguridadDTO = recursosDAO.actualizarRecurso(recursoSeguridadDTO, aplicacionSeguridadDTO);
      } catch (Exception var10) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var10, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return recursoSeguridadDTO;
   }

   public RecursoSeguridadDTO eliminarRecurso(RecursoSeguridadDTO recursoSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      RecursosDAO recursosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         recursosDAO = new RecursosDAO(bdHelper);
         recursoSeguridadDTO = recursosDAO.eliminarRecurso(recursoSeguridadDTO, aplicacionSeguridadDTO);
         if (recursoSeguridadDTO.getMensajeDTO().getMensajeUsuario() != null && !recursoSeguridadDTO.getMensajeDTO().getMensajeUsuario().equals("")) {
            GestorSeguridad.getInstance().removerRecurso(recursoSeguridadDTO.getCodigoRecurso());
         }
      } catch (Exception var10) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var10, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return recursoSeguridadDTO;
   }

   public RecursoSeguridadDTO guardarRecursoPerfil(RecursoSeguridadDTO recursoSeguridadDTO, PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      RecursosDAO recursosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      RecursoSeguridadDTO recursoSeguridadAdminDTO = null;
      HashSet listaPermitidos = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         recursosDAO = new RecursosDAO(bdHelper);
         recursoSeguridadDTO = recursosDAO.guardarRecursoPerfil(recursoSeguridadDTO, perfilSeguridadDTO, aplicacionSeguridadDTO);
         if (recursoSeguridadDTO.getMensajeDTO().getMensajeUsuario() != null && !recursoSeguridadDTO.getMensajeDTO().getMensajeUsuario().equals("")) {
            perfilSeguridadDTO = GestorSeguridad.getInstance().getPerfilSeguridad(perfilSeguridadDTO.getCodigoPerfil());
            recursoSeguridadAdminDTO = GestorSeguridad.getInstance().getRecursoSeguridad(recursoSeguridadDTO.getNombreRecurso());
            if (recursoSeguridadAdminDTO == null) {
               recursoSeguridadDTO = this.consultarRecurso(recursoSeguridadDTO, aplicacionSeguridadDTO);
               listaPermitidos = new HashSet();
               listaPermitidos.add(perfilSeguridadDTO.getCodigoPerfil());
               recursoSeguridadDTO.setPerfilesPermitidos(listaPermitidos);
            }

            if (recursoSeguridadDTO != null && recursoSeguridadDTO.getNombreRecurso() != null && !recursoSeguridadDTO.getNombreRecurso().equals("")) {
               GestorSeguridad.getInstance().putRecurso(recursoSeguridadDTO);
            }
         }
      } catch (Exception var13) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var13, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return recursoSeguridadDTO;
   }

   public RecursoSeguridadDTO eliminarRecursoPerfil(RecursoSeguridadDTO recursoSeguridadDTO, PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      RecursosDAO recursosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         recursosDAO = new RecursosDAO(bdHelper);
         recursoSeguridadDTO = recursosDAO.consultarRecurso(recursoSeguridadDTO, aplicacionSeguridadDTO);
         recursoSeguridadDTO = recursosDAO.eliminarRecursoPerfil(recursoSeguridadDTO, perfilSeguridadDTO, aplicacionSeguridadDTO);
         recursoSeguridadDTO = GestorSeguridad.getInstance().getRecursoSeguridad(recursoSeguridadDTO.getNombreRecurso());
         if (recursoSeguridadDTO != null) {
            GestorSeguridad.getInstance().removerRecurso(recursoSeguridadDTO.getNombreRecurso());
         }
      } catch (Exception var11) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var11, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return recursoSeguridadDTO;
   }

   public List<RecursoSeguridadDTO> consultarRecursosConPerfiles(PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      RecursosDAO recursosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      List recursos = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         recursosDAO = new RecursosDAO(bdHelper);
         recursos = recursosDAO.consultarRecursosConPerfiles(perfilSeguridadDTO, aplicacionSeguridadDTO);
      } catch (Exception var11) {
         bdConfiguracionHelper.evaluarExcepcion(var11, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return recursos;
   }
}
