package co.com.personalsoft.seguridad.bd;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.paginacion.beans.PaginacionDTO;
import co.com.personalsoft.base.seguridad.beans.AplicacionSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.PerfilSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.RecursoSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.UsuarioSeguridadDTO;
import co.com.personalsoft.seguridad.dao.PerfilesDAO;
import co.com.personalsoft.seguridad.dao.RecursosDAO;
import co.com.personalsoft.seguridad.servicio.GestorSeguridad;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class PerfilesHelperBD {
   public List<PerfilSeguridadDTO> consultarPerfilesUsuario(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO) throws PersonalsoftException {
      PerfilesDAO perfilesDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      List perfilesUsuario = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         perfilesDAO = new PerfilesDAO(bdHelper);
         perfilesUsuario = perfilesDAO.consultarPerfilesUsuario(aplicacionSeguridadDTO, usuarioSeguridadDTO);
      } catch (Exception var11) {
         bdConfiguracionHelper.evaluarExcepcion(var11, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return perfilesUsuario;
   }

   public void cargarPerfiles(AplicacionSeguridadDTO aplicacionSeguridadDTO, PerfilSeguridadDTO perfilSeguridadDTO) throws PersonalsoftException {
      PerfilesDAO perfilesDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      List perfiles = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         perfilesDAO = new PerfilesDAO(bdHelper);
         perfiles = perfilesDAO.consultarPerfiles(aplicacionSeguridadDTO, perfilSeguridadDTO);
         if (perfiles != null && !perfiles.isEmpty()) {
            Iterator var8 = perfiles.iterator();

            while(var8.hasNext()) {
               PerfilSeguridadDTO perfilSeguridad = (PerfilSeguridadDTO)var8.next();
               GestorSeguridad.getInstance().putPerfilSeguridad(perfilSeguridad);
            }
         }
      } catch (Exception var12) {
         bdConfiguracionHelper.evaluarExcepcion(var12, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

   }

   public List<PerfilSeguridadDTO> consultarPerfiles(AplicacionSeguridadDTO aplicacionSeguridadDTO, PerfilSeguridadDTO perfilSeguridadDTO) throws PersonalsoftException {
      PerfilesDAO perfilesDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      List perfiles = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         perfilesDAO = new PerfilesDAO(bdHelper);
         perfiles = perfilesDAO.consultarPerfiles(aplicacionSeguridadDTO, perfilSeguridadDTO);
      } catch (Exception var11) {
         bdConfiguracionHelper.evaluarExcepcion(var11, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return perfiles;
   }

   public List<PerfilSeguridadDTO> consultarPerfiles(PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO, PaginacionDTO paginacionDTO) throws PersonalsoftException {
      PerfilesDAO perfilesDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      List perfiles = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         perfilesDAO = new PerfilesDAO(bdHelper);
         perfiles = perfilesDAO.consultarPerfiles(perfilSeguridadDTO, aplicacionSeguridadDTO, paginacionDTO);
      } catch (Exception var12) {
         bdConfiguracionHelper.evaluarExcepcion(var12, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return perfiles;
   }

   public PerfilSeguridadDTO consultarPerfil(AplicacionSeguridadDTO aplicacionSeguridadDTO, PerfilSeguridadDTO perfilSeguridadDTO) throws PersonalsoftException {
      PerfilesDAO perfilesDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         perfilesDAO = new PerfilesDAO(bdHelper);
         perfilSeguridadDTO = perfilesDAO.consultarPerfil(aplicacionSeguridadDTO, perfilSeguridadDTO);
      } catch (Exception var10) {
         bdConfiguracionHelper.evaluarExcepcion(var10, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return perfilSeguridadDTO;
   }

   public PerfilSeguridadDTO guardarPerfil(PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      PerfilesDAO perfilesDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         perfilesDAO = new PerfilesDAO(bdHelper);
         perfilSeguridadDTO = perfilesDAO.guardarPerfil(perfilSeguridadDTO, aplicacionSeguridadDTO);
         if (perfilSeguridadDTO.getMensajeDTO().getMensajeUsuario() != null && !perfilSeguridadDTO.getMensajeDTO().getMensajeUsuario().equals("")) {
            GestorSeguridad.getInstance().putPerfilSeguridad(perfilSeguridadDTO);
         }
      } catch (Exception var10) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var10, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return perfilSeguridadDTO;
   }

   public PerfilSeguridadDTO actualizarPerfil(PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      PerfilesDAO perfilesDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         perfilesDAO = new PerfilesDAO(bdHelper);
         perfilSeguridadDTO = perfilesDAO.actualizarPerfil(perfilSeguridadDTO, aplicacionSeguridadDTO);
         if (perfilSeguridadDTO.getMensajeDTO().getMensajeUsuario() != null && !perfilSeguridadDTO.getMensajeDTO().getMensajeUsuario().equals("")) {
            GestorSeguridad.getInstance().putPerfilSeguridad(perfilSeguridadDTO);
         }
      } catch (Exception var10) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var10, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return perfilSeguridadDTO;
   }

   public PerfilSeguridadDTO eliminarPerfil(PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      PerfilesDAO perfilesDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         perfilesDAO = new PerfilesDAO(bdHelper);
         perfilSeguridadDTO = perfilesDAO.eliminarPerfil(perfilSeguridadDTO, aplicacionSeguridadDTO);
         if (perfilSeguridadDTO.getMensajeDTO().getMensajeUsuario() != null && !perfilSeguridadDTO.getMensajeDTO().getMensajeUsuario().equals("")) {
            GestorSeguridad.getInstance().removerPerfilSeguridad(perfilSeguridadDTO.getCodigoPerfil());
         }
      } catch (Exception var10) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var10, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return perfilSeguridadDTO;
   }

   public void cargarPerfilesRecurso(AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      RecursosDAO recursosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      Entry<String, RecursoSeguridadDTO> entrada = null;
      Set<Entry<String, RecursoSeguridadDTO>> entradas = null;
      Map consultarPerfilesRecurso = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         recursosDAO = new RecursosDAO(bdHelper);
         consultarPerfilesRecurso = recursosDAO.consultarPerfilesRecurso(aplicacionSeguridadDTO);
         if (consultarPerfilesRecurso != null && !consultarPerfilesRecurso.isEmpty()) {
            entradas = consultarPerfilesRecurso.entrySet();
            Iterator iterador = entradas.iterator();

            while(iterador.hasNext()) {
               entrada = (Entry)iterador.next();
               GestorSeguridad.getInstance().putRecurso((RecursoSeguridadDTO)entrada.getValue());
            }
         }
      } catch (Exception var12) {
         bdConfiguracionHelper.evaluarExcepcion(var12, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

   }

   public void cargarPerfilesOpcion(AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      PerfilesDAO perfilesDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      Entry<String, PerfilSeguridadDTO> entrada = null;
      Set<Entry<String, PerfilSeguridadDTO>> entradas = null;
      Map consultarPerfilesOpcion = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         perfilesDAO = new PerfilesDAO(bdHelper);
         consultarPerfilesOpcion = perfilesDAO.consultarPerfilesOpcion(aplicacionSeguridadDTO);
         if (consultarPerfilesOpcion != null && !consultarPerfilesOpcion.isEmpty()) {
            entradas = consultarPerfilesOpcion.entrySet();
            Iterator iterador = entradas.iterator();

            while(iterador.hasNext()) {
               entrada = (Entry)iterador.next();
               GestorSeguridad.getInstance().putPerfilSeguridadOpcion((PerfilSeguridadDTO)entrada.getValue());
            }
         }
      } catch (Exception var12) {
         bdConfiguracionHelper.evaluarExcepcion(var12, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

   }
}
