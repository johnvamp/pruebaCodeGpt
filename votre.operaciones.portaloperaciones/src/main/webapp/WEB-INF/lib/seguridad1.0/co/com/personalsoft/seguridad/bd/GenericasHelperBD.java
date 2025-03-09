package co.com.personalsoft.seguridad.bd;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.seguridad.beans.AplicacionSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.GenericaDTO;
import co.com.personalsoft.base.seguridad.beans.PerfilSeguridadDTO;
import co.com.personalsoft.seguridad.dao.GenericasDAO;
import java.util.List;

public class GenericasHelperBD {
   public List<GenericaDTO> consultarEntidadesGenericas(AplicacionSeguridadDTO aplicacionSeguridadDTO, GenericaDTO entidadGenericaDTO) throws PersonalsoftException {
      GenericasDAO genericasDAO = null;
      BDHelper bdHelper = null;
      List<GenericaDTO> genericas = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         genericasDAO = new GenericasDAO(bdHelper);
         genericas = genericasDAO.consultarEntidadesGenericas(aplicacionSeguridadDTO, entidadGenericaDTO);
      } catch (Exception var11) {
         bdConfiguracionHelper.evaluarExcepcion(var11, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return genericas;
   }

   public GenericaDTO consultarEntidadGenerica(AplicacionSeguridadDTO aplicacionSeguridadDTO, GenericaDTO entidadGenericaDTO) throws PersonalsoftException {
      GenericasDAO genericasDAO = null;
      BDHelper bdHelper = null;
      GenericaDTO genericas = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         genericasDAO = new GenericasDAO(bdHelper);
         genericas = genericasDAO.consultarEntidadGenerica(aplicacionSeguridadDTO, entidadGenericaDTO);
      } catch (Exception var11) {
         bdConfiguracionHelper.evaluarExcepcion(var11, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return genericas;
   }

   public List<GenericaDTO> consultarEntidadesGenericaXPerfil(AplicacionSeguridadDTO aplicacionSeguridadDTO, PerfilSeguridadDTO perfilSeguridadDTO) throws PersonalsoftException {
      GenericasDAO genericasDAO = null;
      BDHelper bdHelper = null;
      List<GenericaDTO> genericas = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         genericasDAO = new GenericasDAO(bdHelper);
         genericas = genericasDAO.consultarEntidadesGenericaXPerfil(aplicacionSeguridadDTO, perfilSeguridadDTO);
      } catch (Exception var11) {
         bdConfiguracionHelper.evaluarExcepcion(var11, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return genericas;
   }

   public GenericaDTO guardarGenericaXPerfil(GenericaDTO genericaDTO, PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      GenericasDAO genericasDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         genericasDAO = new GenericasDAO(bdHelper);
         genericaDTO = genericasDAO.guardarGenericaXPerfil(genericaDTO, perfilSeguridadDTO, aplicacionSeguridadDTO);
      } catch (Exception var11) {
         bdConfiguracionHelper.evaluarExcepcion(var11, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return genericaDTO;
   }

   public GenericaDTO eliminarGenericaXPerfil(GenericaDTO genericaDTO, PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      GenericasDAO genericasDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         genericasDAO = new GenericasDAO(bdHelper);
         genericaDTO = genericasDAO.eliminarGenericaXPerfil(genericaDTO, perfilSeguridadDTO, aplicacionSeguridadDTO);
      } catch (Exception var11) {
         bdConfiguracionHelper.evaluarExcepcion(var11, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return genericaDTO;
   }
}
