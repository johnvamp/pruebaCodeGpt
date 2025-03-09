package co.com.personalsoft.seguridad.config;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.paginacion.beans.PaginacionDTO;
import co.com.personalsoft.base.seguridad.beans.AplicacionSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.GenericaDTO;
import co.com.personalsoft.base.seguridad.beans.OpcionMenuDTO;
import co.com.personalsoft.base.seguridad.beans.PerfilSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.RecursoSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.UsuarioSeguridadDTO;
import co.com.personalsoft.seguridad.bd.GenericasHelperBD;
import co.com.personalsoft.seguridad.bd.MenuHelperBD;
import co.com.personalsoft.seguridad.bd.PerfilesHelperBD;
import co.com.personalsoft.seguridad.bd.RecursosHelperBD;
import co.com.personalsoft.seguridad.bd.UsuariosHelperBD;
import java.io.Serializable;
import java.util.List;
import org.apache.log4j.Logger;

public class SeguridadWS implements Serializable {
   private static final long serialVersionUID = 1L;
   private Logger log = Logger.getLogger(this.getClass());

   public PerfilSeguridadDTO[] consultarPerfiles(String idApp, String dsPerfil) throws Exception {
      PerfilSeguridadDTO[] listado = (PerfilSeguridadDTO[])null;
      PerfilesHelperBD perfilesHelperBD = new PerfilesHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
      PerfilSeguridadDTO perfilSeguridadDTO = new PerfilSeguridadDTO();
      perfilSeguridadDTO.setNombrePerfil(dsPerfil);

      try {
         List<PerfilSeguridadDTO> listaPerfiles = perfilesHelperBD.consultarPerfiles(aplicacionSeguridadDTO, perfilSeguridadDTO);
         if (!listaPerfiles.isEmpty()) {
            listado = (PerfilSeguridadDTO[])listaPerfiles.toArray(new PerfilSeguridadDTO[listaPerfiles.size()]);
         }
      } catch (Exception var8) {
         this.imprimirExcepcion(var8);
      }

      return listado;
   }

   public PerfilSeguridadDTO[] consultarPerfilesPaginados(String idApp, PerfilSeguridadDTO perfilSeguridadDTO, PaginacionDTO paginacionDTO) throws Exception {
      PerfilSeguridadDTO[] listado = (PerfilSeguridadDTO[])null;
      PerfilesHelperBD perfilesHelperBD = new PerfilesHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);

      try {
         List<PerfilSeguridadDTO> listaPerfiles = perfilesHelperBD.consultarPerfiles(perfilSeguridadDTO, aplicacionSeguridadDTO, paginacionDTO);
         if (!listaPerfiles.isEmpty()) {
            listado = (PerfilSeguridadDTO[])listaPerfiles.toArray(new PerfilSeguridadDTO[listaPerfiles.size()]);
         }
      } catch (Exception var8) {
         this.imprimirExcepcion(var8);
      }

      return listado;
   }

   public PerfilSeguridadDTO consultarPerfil(String idApp, String codigoPerfil) throws Exception {
      PerfilesHelperBD perfilesHelperBD = new PerfilesHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      PerfilSeguridadDTO perfilSeguridadDTO = null;
      PerfilSeguridadDTO obtenido = null;
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
      perfilSeguridadDTO = new PerfilSeguridadDTO();
      perfilSeguridadDTO.setCodigoPerfil(codigoPerfil);

      try {
         obtenido = perfilesHelperBD.consultarPerfil(aplicacionSeguridadDTO, perfilSeguridadDTO);
      } catch (Exception var8) {
         this.imprimirExcepcion(var8);
      }

      return obtenido;
   }

   public PerfilSeguridadDTO[] consultarPerfilesUsuario(String idApp, String usuario) throws Exception {
      PerfilSeguridadDTO[] listado = (PerfilSeguridadDTO[])null;
      PerfilesHelperBD perfilesHelperBD = new PerfilesHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      UsuarioSeguridadDTO usuarioSeguridadDTO = new UsuarioSeguridadDTO();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
      usuarioSeguridadDTO.setUsuario(usuario);

      try {
         List<PerfilSeguridadDTO> listaPerfiles = perfilesHelperBD.consultarPerfilesUsuario(aplicacionSeguridadDTO, usuarioSeguridadDTO);
         if (!listaPerfiles.isEmpty()) {
            listado = (PerfilSeguridadDTO[])listaPerfiles.toArray(new PerfilSeguridadDTO[listaPerfiles.size()]);
         }
      } catch (Exception var8) {
         this.imprimirExcepcion(var8);
      }

      return listado;
   }

   public PerfilSeguridadDTO guardarPerfil(String idApp, PerfilSeguridadDTO perfilSeguridadDTO) throws Exception {
      PerfilSeguridadDTO registrado = null;

      try {
         PerfilesHelperBD perfilesHelperBD = new PerfilesHelperBD();
         AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
         aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
         registrado = perfilesHelperBD.guardarPerfil(perfilSeguridadDTO, aplicacionSeguridadDTO);
      } catch (Exception var6) {
         this.imprimirExcepcion(var6);
      }

      return registrado;
   }

   public String actualizarPerfil(String idApp, PerfilSeguridadDTO perfilSeguridadDTO) throws Exception {
      String mensaje = null;
      PerfilSeguridadDTO registrado = null;

      try {
         PerfilesHelperBD perfilesHelperBD = new PerfilesHelperBD();
         AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
         aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
         registrado = perfilesHelperBD.actualizarPerfil(perfilSeguridadDTO, aplicacionSeguridadDTO);
         if (registrado != null) {
            mensaje = registrado.getMensajeDTO().getMensajeUsuario();
         }
      } catch (Exception var7) {
         this.imprimirExcepcion(var7);
      }

      return mensaje;
   }

   public String eliminarPerfil(String idApp, String idPerfil) throws Exception {
      String mensaje = null;
      PerfilSeguridadDTO perfilSeguridadDTO = new PerfilSeguridadDTO();
      PerfilSeguridadDTO eliminado = null;
      PerfilesHelperBD perfilesHelperBD = new PerfilesHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);

      try {
         perfilSeguridadDTO.setCodigoPerfil(idPerfil);
         eliminado = perfilesHelperBD.eliminarPerfil(perfilSeguridadDTO, aplicacionSeguridadDTO);
         if (eliminado != null) {
            mensaje = eliminado.getMensajeDTO().getMensajeUsuario();
         }
      } catch (Exception var9) {
         this.imprimirExcepcion(var9);
      }

      return mensaje;
   }

   public UsuarioSeguridadDTO consultarUsuario(String idApp, String dsLogin) throws Exception {
      UsuarioSeguridadDTO user = null;
      UsuariosHelperBD usuariosHelperBD = new UsuariosHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
      UsuarioSeguridadDTO usuarioSeguridadDTO = new UsuarioSeguridadDTO();
      usuarioSeguridadDTO.setUsuario(dsLogin);

      try {
         user = usuariosHelperBD.consultarUsuario(usuarioSeguridadDTO, aplicacionSeguridadDTO);
      } catch (Exception var8) {
         this.imprimirExcepcion(var8);
      }

      return user;
   }

   public UsuarioSeguridadDTO[] consultarUsuarios(String idApp, String dsLogin, String idPerfil) throws Exception {
      UsuarioSeguridadDTO[] users = (UsuarioSeguridadDTO[])null;
      List<UsuarioSeguridadDTO> usuarios = null;
      UsuariosHelperBD usuariosHelperBD = new UsuariosHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
      UsuarioSeguridadDTO usuarioSeguridadDTO = new UsuarioSeguridadDTO();
      PerfilSeguridadDTO perfilSeguridadDTO = new PerfilSeguridadDTO();
      usuarioSeguridadDTO.setUsuario(dsLogin);
      if (idPerfil != null && !idPerfil.trim().equals("")) {
         perfilSeguridadDTO.setCodigoPerfil(idPerfil);
         usuarioSeguridadDTO.setPerfilSeguridadDTO(perfilSeguridadDTO);
      }

      try {
         usuarios = usuariosHelperBD.consultarUsuarios(usuarioSeguridadDTO, aplicacionSeguridadDTO);
         if (usuarios != null && !usuarios.isEmpty()) {
            users = (UsuarioSeguridadDTO[])usuarios.toArray(new UsuarioSeguridadDTO[usuarios.size()]);
         }
      } catch (Exception var11) {
         this.imprimirExcepcion(var11);
      }

      return users;
   }

   public UsuarioSeguridadDTO[] consultarUsuariosPaginados(String idApp, String dsLogin, String idPerfil, PaginacionDTO paginacionDTO) throws Exception {
      UsuarioSeguridadDTO[] users = (UsuarioSeguridadDTO[])null;
      List<UsuarioSeguridadDTO> usuarios = null;
      UsuariosHelperBD usuariosHelperBD = new UsuariosHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
      UsuarioSeguridadDTO usuarioSeguridadDTO = new UsuarioSeguridadDTO();
      PerfilSeguridadDTO perfilSeguridadDTO = new PerfilSeguridadDTO();
      usuarioSeguridadDTO.setUsuario(dsLogin);
      if (idPerfil != null && !idPerfil.trim().equals("")) {
         perfilSeguridadDTO.setCodigoPerfil(idPerfil);
         usuarioSeguridadDTO.setPerfilSeguridadDTO(perfilSeguridadDTO);
      }

      try {
         usuarios = usuariosHelperBD.consultarUsuarios(usuarioSeguridadDTO, aplicacionSeguridadDTO, paginacionDTO);
         if (usuarios != null && !usuarios.isEmpty()) {
            users = (UsuarioSeguridadDTO[])usuarios.toArray(new UsuarioSeguridadDTO[usuarios.size()]);
         }
      } catch (Exception var12) {
         this.imprimirExcepcion(var12);
      }

      return users;
   }

   public String guardarUsuario(String idApp, UsuarioSeguridadDTO usuarioSeguridadDTO) throws Exception {
      String mensaje = null;
      UsuarioSeguridadDTO user = null;

      try {
         UsuariosHelperBD usuariosHelperBD = new UsuariosHelperBD();
         AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
         aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
         user = usuariosHelperBD.guardarUsuario(aplicacionSeguridadDTO, usuarioSeguridadDTO);
         if (user != null) {
            mensaje = user.getMensajeDTO().getMensajeUsuario();
         }
      } catch (Exception var7) {
         this.imprimirExcepcion(var7);
      }

      return mensaje;
   }

   public String actualizarUsuario(String idApp, UsuarioSeguridadDTO usuarioSeguridadDTO) throws Exception {
      UsuarioSeguridadDTO user = null;
      String mensaje = null;

      try {
         UsuariosHelperBD usuariosHelperBD = new UsuariosHelperBD();
         AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
         aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
         user = usuariosHelperBD.actualizarUsuario(aplicacionSeguridadDTO, usuarioSeguridadDTO);
         if (user != null) {
            mensaje = user.getMensajeDTO().getMensajeUsuario();
         }
      } catch (Exception var7) {
         this.imprimirExcepcion(var7);
      }

      return mensaje;
   }

   public String eliminarUsuario(String idApp, UsuarioSeguridadDTO usuarioSeguridadDTO) throws Exception {
      UsuarioSeguridadDTO user = null;
      String mensaje = null;

      try {
         UsuariosHelperBD usuariosHelperBD = new UsuariosHelperBD();
         AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
         aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
         user = usuariosHelperBD.eliminarUsuario(aplicacionSeguridadDTO, usuarioSeguridadDTO);
         if (user != null) {
            mensaje = user.getMensajeDTO().getMensajeUsuario();
         }
      } catch (Exception var7) {
         this.imprimirExcepcion(var7);
      }

      return mensaje;
   }

   public String guardarPerfilXUsuario(String idApp, String usuario, String idPerfil) throws Exception {
      String mensaje = null;
      UsuariosHelperBD usuariosHelperBD = new UsuariosHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      UsuarioSeguridadDTO usuarioSeguridadDTO = new UsuarioSeguridadDTO();
      PerfilSeguridadDTO perfilSeguridadDTO = new PerfilSeguridadDTO();
      UsuarioSeguridadDTO registrado = null;
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
      usuarioSeguridadDTO.setUsuario(usuario);
      perfilSeguridadDTO.setCodigoPerfil(idPerfil);
      usuarioSeguridadDTO.setPerfilSeguridadDTO(perfilSeguridadDTO);

      try {
         registrado = usuariosHelperBD.guardarPerfilUsuario(aplicacionSeguridadDTO, usuarioSeguridadDTO);
         if (registrado != null) {
            mensaje = registrado.getMensajeDTO().getMensajeUsuario();
         }
      } catch (Exception var11) {
         this.imprimirExcepcion(var11);
      }

      return mensaje;
   }

   public String eliminarPerfilXUsuario(String idApp, String usuario, String idPerfil) throws Exception {
      String mensaje = null;
      UsuariosHelperBD usuariosHelperBD = new UsuariosHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      UsuarioSeguridadDTO usuarioSeguridadDTO = new UsuarioSeguridadDTO();
      PerfilSeguridadDTO perfilSeguridadDTO = new PerfilSeguridadDTO();
      UsuarioSeguridadDTO borrado = null;
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
      usuarioSeguridadDTO.setUsuario(usuario);
      perfilSeguridadDTO.setCodigoPerfil(idPerfil);
      usuarioSeguridadDTO.setPerfilSeguridadDTO(perfilSeguridadDTO);

      try {
         borrado = usuariosHelperBD.eliminarPerfilUsuario(aplicacionSeguridadDTO, usuarioSeguridadDTO);
         if (borrado != null) {
            mensaje = borrado.getMensajeDTO().getMensajeUsuario();
         }
      } catch (Exception var11) {
         this.imprimirExcepcion(var11);
      }

      return mensaje;
   }

   public GenericaDTO[] consultarEntidadesGenericas(String idApp, String idGenerica) throws Exception {
      List<GenericaDTO> genericas = null;
      GenericaDTO[] listado = (GenericaDTO[])null;
      GenericaDTO genericaDTO = null;
      GenericasHelperBD genericasHelperBD = new GenericasHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
      if (idGenerica != null && !idGenerica.equals("")) {
         genericaDTO = new GenericaDTO();
         genericaDTO.setAtributo1(idGenerica);
      }

      try {
         genericas = genericasHelperBD.consultarEntidadesGenericas(aplicacionSeguridadDTO, genericaDTO);
         if (genericas != null && !genericas.isEmpty()) {
            listado = (GenericaDTO[])genericas.toArray(new GenericaDTO[genericas.size()]);
         }
      } catch (Exception var9) {
         this.imprimirExcepcion(var9);
      }

      return listado;
   }

   public GenericaDTO[] consultarEntidadesGenericaXPerfil(String idApp, String idPerfil) throws Exception {
      List<GenericaDTO> genericas = null;
      GenericaDTO[] listado = (GenericaDTO[])null;
      PerfilSeguridadDTO perfilSeguridadDTO = new PerfilSeguridadDTO();
      GenericasHelperBD genericasHelperBD = new GenericasHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
      perfilSeguridadDTO.setCodigoPerfil(idPerfil);

      try {
         genericas = genericasHelperBD.consultarEntidadesGenericaXPerfil(aplicacionSeguridadDTO, perfilSeguridadDTO);
         if (genericas != null && !genericas.isEmpty()) {
            listado = (GenericaDTO[])genericas.toArray(new GenericaDTO[genericas.size()]);
         }
      } catch (Exception var9) {
         this.imprimirExcepcion(var9);
      }

      return listado;
   }

   public GenericaDTO consultarEntidadGenerica(String idApp, String idGenerica) throws Exception {
      GenericaDTO obtenida = null;
      GenericaDTO genericaDTO = null;
      GenericasHelperBD genericasHelperBD = new GenericasHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
      if (idGenerica != null && !idGenerica.equals("")) {
         genericaDTO = new GenericaDTO();
         genericaDTO.setAtributo1(idGenerica);
      }

      try {
         obtenida = genericasHelperBD.consultarEntidadGenerica(aplicacionSeguridadDTO, genericaDTO);
      } catch (Exception var8) {
         this.imprimirExcepcion(var8);
      }

      return obtenida;
   }

   public String guardarGenericaXPerfil(String idApp, String idPerfil, String idGenerica) throws Exception {
      String mensaje = null;
      GenericaDTO obtenida = null;
      GenericaDTO genericaDTO = null;
      PerfilSeguridadDTO perfilSeguridadDTO = new PerfilSeguridadDTO();
      GenericasHelperBD genericasHelperBD = new GenericasHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
      perfilSeguridadDTO.setCodigoPerfil(idPerfil);
      genericaDTO = new GenericaDTO();
      genericaDTO.setAtributo1(idGenerica);

      try {
         obtenida = genericasHelperBD.guardarGenericaXPerfil(genericaDTO, perfilSeguridadDTO, aplicacionSeguridadDTO);
         if (obtenida != null) {
            mensaje = obtenida.getMensajeDTO().getMensajeUsuario();
         }
      } catch (Exception var11) {
         this.imprimirExcepcion(var11);
      }

      return mensaje;
   }

   public String eliminarGenericaXPerfil(String idApp, String idPerfil, String idGenerica) throws Exception {
      String mensaje = null;
      GenericaDTO obtenida = null;
      GenericaDTO genericaDTO = null;
      PerfilSeguridadDTO perfilSeguridadDTO = new PerfilSeguridadDTO();
      GenericasHelperBD genericasHelperBD = new GenericasHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
      perfilSeguridadDTO.setCodigoPerfil(idPerfil);
      genericaDTO = new GenericaDTO();
      genericaDTO.setAtributo1(idGenerica);

      try {
         obtenida = genericasHelperBD.eliminarGenericaXPerfil(genericaDTO, perfilSeguridadDTO, aplicacionSeguridadDTO);
         if (obtenida != null) {
            mensaje = obtenida.getMensajeDTO().getMensajeUsuario();
         }
      } catch (Exception var11) {
         this.imprimirExcepcion(var11);
      }

      return mensaje;
   }

   private void imprimirExcepcion(Exception e) throws Exception {
      PersonalsoftException personalsoftException = null;
      if (e instanceof PersonalsoftException) {
         personalsoftException = (PersonalsoftException)e;
         this.log.error(personalsoftException.getTraza());
         throw personalsoftException.getException();
      } else {
         personalsoftException = new PersonalsoftException(e);
         this.log.error(personalsoftException.getTraza());
         throw e;
      }
   }

   public RecursoSeguridadDTO[] consultarRecursos(String idApp, String dsRecurso) throws Exception {
      RecursoSeguridadDTO[] listado = (RecursoSeguridadDTO[])null;
      RecursosHelperBD recursosHelperBD = new RecursosHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
      RecursoSeguridadDTO recursoSeguridadDTO = new RecursoSeguridadDTO();
      recursoSeguridadDTO.setNombreRecurso(dsRecurso);
      List<RecursoSeguridadDTO> listaRecursos = recursosHelperBD.consultarRecursos(recursoSeguridadDTO, aplicacionSeguridadDTO);
      if (!listaRecursos.isEmpty()) {
         listado = (RecursoSeguridadDTO[])listaRecursos.toArray(new RecursoSeguridadDTO[listaRecursos.size()]);
      }

      return listado;
   }

   public RecursoSeguridadDTO guardarRecurso(String idApp, RecursoSeguridadDTO recursoSeguridadDTO) throws Exception {
      RecursoSeguridadDTO registrado = null;

      try {
         RecursosHelperBD recursosHelperBD = new RecursosHelperBD();
         AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
         aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
         registrado = recursosHelperBD.guardarRecurso(recursoSeguridadDTO, aplicacionSeguridadDTO);
      } catch (Exception var6) {
         this.imprimirExcepcion(var6);
      }

      return registrado;
   }

   public String actualizarRecurso(String idApp, RecursoSeguridadDTO recursoSeguridadDTO) throws Exception {
      String mensaje = null;
      RecursoSeguridadDTO registrado = null;

      try {
         RecursosHelperBD registrosHelperBD = new RecursosHelperBD();
         AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
         aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
         registrado = registrosHelperBD.actualizarRecurso(recursoSeguridadDTO, aplicacionSeguridadDTO);
         if (registrado != null) {
            mensaje = registrado.getMensajeDTO().getMensajeUsuario();
         }
      } catch (Exception var7) {
         this.imprimirExcepcion(var7);
      }

      return mensaje;
   }

   public String eliminarRecurso(String idApp, String idRecurso) throws Exception {
      String mensaje = null;
      RecursoSeguridadDTO recursoSeguridadDTO = new RecursoSeguridadDTO();
      RecursoSeguridadDTO eliminado = null;
      RecursosHelperBD recursosHelperBD = new RecursosHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);

      try {
         recursoSeguridadDTO.setCodigoRecurso(idRecurso);
         eliminado = recursosHelperBD.eliminarRecurso(recursoSeguridadDTO, aplicacionSeguridadDTO);
         if (eliminado != null) {
            mensaje = eliminado.getMensajeDTO().getMensajeUsuario();
         }
      } catch (Exception var9) {
         this.imprimirExcepcion(var9);
      }

      return mensaje;
   }

   public RecursoSeguridadDTO consultarRecurso(String idApp, String codigoRecurso) throws Exception {
      RecursosHelperBD recursosHelperBD = new RecursosHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      RecursoSeguridadDTO recursoSeguridadDTO = null;
      RecursoSeguridadDTO obtenido = null;
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
      recursoSeguridadDTO = new RecursoSeguridadDTO();
      recursoSeguridadDTO.setCodigoRecurso(codigoRecurso);
      obtenido = recursosHelperBD.consultarRecurso(recursoSeguridadDTO, aplicacionSeguridadDTO);
      return obtenido;
   }

   public OpcionMenuDTO[] consultarOpcionesMenu(String idApp) throws Exception {
      OpcionMenuDTO[] listado = (OpcionMenuDTO[])null;
      MenuHelperBD menuHelperBD = new MenuHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);

      try {
         List<OpcionMenuDTO> listaOpciones = menuHelperBD.consultarOpcionesMenu(new OpcionMenuDTO(), aplicacionSeguridadDTO);
         if (!listaOpciones.isEmpty()) {
            listado = (OpcionMenuDTO[])listaOpciones.toArray(new OpcionMenuDTO[listaOpciones.size()]);
         }
      } catch (Exception var6) {
         this.imprimirExcepcion(var6);
      }

      return listado;
   }

   public OpcionMenuDTO guardarOpcion(String idApp, OpcionMenuDTO opcionMenuDTO) throws Exception {
      OpcionMenuDTO registrado = null;

      try {
         MenuHelperBD menuHelperBD = new MenuHelperBD();
         AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
         aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
         registrado = menuHelperBD.guardarNodo(aplicacionSeguridadDTO, opcionMenuDTO);
      } catch (Exception var6) {
         this.imprimirExcepcion(var6);
      }

      return registrado;
   }

   public String eliminarOpcion(String idApp, String idOpcion) throws Exception {
      String mensaje = null;
      OpcionMenuDTO opcionMenuDTO = new OpcionMenuDTO();
      OpcionMenuDTO eliminado = null;
      MenuHelperBD menuHelperBD = new MenuHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);

      try {
         opcionMenuDTO.setCodigoOpcion(idOpcion);
         eliminado = menuHelperBD.eliminarNodo(aplicacionSeguridadDTO, opcionMenuDTO);
         if (eliminado != null) {
            mensaje = eliminado.getMensajeDTO().getMensajeUsuario();
         }
      } catch (Exception var9) {
         this.imprimirExcepcion(var9);
      }

      return mensaje;
   }

   public String bajarOpcion(String idApp, OpcionMenuDTO opcionMenuDTO) throws Exception {
      String mensaje = null;
      OpcionMenuDTO modificado = null;
      MenuHelperBD menuHelperBD = new MenuHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);

      try {
         modificado = menuHelperBD.bajarNodo(aplicacionSeguridadDTO, opcionMenuDTO);
         if (modificado != null) {
            mensaje = modificado.getMensajeDTO().getMensajeUsuario();
         }
      } catch (Exception var8) {
         this.imprimirExcepcion(var8);
      }

      return mensaje;
   }

   public String subirOpcion(String idApp, OpcionMenuDTO opcionMenuDTO) throws Exception {
      String mensaje = null;
      OpcionMenuDTO modificado = null;
      MenuHelperBD menuHelperBD = new MenuHelperBD();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);

      try {
         modificado = menuHelperBD.subirNodo(aplicacionSeguridadDTO, opcionMenuDTO);
         if (modificado != null) {
            mensaje = modificado.getMensajeDTO().getMensajeUsuario();
         }
      } catch (Exception var8) {
         this.imprimirExcepcion(var8);
      }

      return mensaje;
   }

   public RecursoSeguridadDTO[] consultarRecursosPerfil(String idApp, String idPerfil) throws Exception {
      RecursoSeguridadDTO[] listaArrRecursos = (RecursoSeguridadDTO[])null;
      PerfilSeguridadDTO perfilSeguridadDTO = new PerfilSeguridadDTO();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      RecursosHelperBD recursosHelperBD = new RecursosHelperBD();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
      perfilSeguridadDTO.setCodigoPerfil(idPerfil);

      try {
         List<RecursoSeguridadDTO> listaRecursos = recursosHelperBD.consultarRecursosPerfil(perfilSeguridadDTO, aplicacionSeguridadDTO);
         if (!listaRecursos.isEmpty()) {
            listaArrRecursos = (RecursoSeguridadDTO[])listaRecursos.toArray(new RecursoSeguridadDTO[listaRecursos.size()]);
         }
      } catch (Exception var8) {
         this.imprimirExcepcion(var8);
      }

      return listaArrRecursos;
   }

   public RecursoSeguridadDTO guardarRecursoPerfil(String idApp, String idRecurso, String idPerfil) throws Exception {
      PerfilSeguridadDTO perfilSeguridadDTO = new PerfilSeguridadDTO();
      RecursoSeguridadDTO recursoSeguridadDTO = new RecursoSeguridadDTO();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      RecursosHelperBD recursosHelperBD = new RecursosHelperBD();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
      perfilSeguridadDTO.setCodigoPerfil(idPerfil);
      recursoSeguridadDTO.setCodigoRecurso(idRecurso);

      try {
         recursoSeguridadDTO = recursosHelperBD.guardarRecursoPerfil(recursoSeguridadDTO, perfilSeguridadDTO, aplicacionSeguridadDTO);
      } catch (Exception var9) {
         this.imprimirExcepcion(var9);
      }

      return recursoSeguridadDTO;
   }

   public String eliminarRecursoPerfil(String idApp, String idRecurso, String idPerfil) throws Exception {
      String mensaje = null;
      PerfilSeguridadDTO perfilSeguridadDTO = new PerfilSeguridadDTO();
      RecursoSeguridadDTO recursoSeguridadDTO = new RecursoSeguridadDTO();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      RecursosHelperBD recursosHelperBD = new RecursosHelperBD();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
      perfilSeguridadDTO.setCodigoPerfil(idPerfil);
      recursoSeguridadDTO.setCodigoRecurso(idRecurso);

      try {
         recursoSeguridadDTO = recursosHelperBD.eliminarRecursoPerfil(recursoSeguridadDTO, perfilSeguridadDTO, aplicacionSeguridadDTO);
         if (recursoSeguridadDTO != null) {
            mensaje = recursoSeguridadDTO.getMensajeDTO().getMensajeUsuario();
         }
      } catch (Exception var10) {
         this.imprimirExcepcion(var10);
      }

      return mensaje;
   }

   public RecursoSeguridadDTO[] consultarRecursosConPerfiles(String idApp, String idPerfil) throws Exception {
      RecursoSeguridadDTO[] listaArrRecursos = (RecursoSeguridadDTO[])null;
      PerfilSeguridadDTO perfilSeguridadDTO = new PerfilSeguridadDTO();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      RecursosHelperBD recursosHelperBD = new RecursosHelperBD();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
      perfilSeguridadDTO.setCodigoPerfil(idPerfil);

      try {
         List<RecursoSeguridadDTO> listaRecursos = recursosHelperBD.consultarRecursosConPerfiles(perfilSeguridadDTO, aplicacionSeguridadDTO);
         if (!listaRecursos.isEmpty()) {
            listaArrRecursos = (RecursoSeguridadDTO[])listaRecursos.toArray(new RecursoSeguridadDTO[listaRecursos.size()]);
         }
      } catch (Exception var8) {
         this.imprimirExcepcion(var8);
      }

      return listaArrRecursos;
   }

   public OpcionMenuDTO[] consultarOpcionesConPerfil(String idApp, String idPerfil) throws Exception {
      OpcionMenuDTO[] listaRetornoOpciones = (OpcionMenuDTO[])null;
      PerfilSeguridadDTO perfilSeguridadDTO = new PerfilSeguridadDTO();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      MenuHelperBD menuHelperBD = new MenuHelperBD();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
      perfilSeguridadDTO.setCodigoPerfil(idPerfil);

      try {
         List<OpcionMenuDTO> listaOpciones = menuHelperBD.consultarOpcionesConPerfil(perfilSeguridadDTO, aplicacionSeguridadDTO);
         if (!listaOpciones.isEmpty()) {
            listaRetornoOpciones = (OpcionMenuDTO[])listaOpciones.toArray(new OpcionMenuDTO[listaOpciones.size()]);
         }
      } catch (Exception var8) {
         this.imprimirExcepcion(var8);
      }

      return listaRetornoOpciones;
   }

   public OpcionMenuDTO guardarOpcionPerfil(String idApp, String idOpcion, String idPerfil) throws Exception {
      PerfilSeguridadDTO perfilSeguridadDTO = new PerfilSeguridadDTO();
      OpcionMenuDTO opcionMenuDTO = new OpcionMenuDTO();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      MenuHelperBD menuHelperBD = new MenuHelperBD();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
      perfilSeguridadDTO.setCodigoPerfil(idPerfil);
      opcionMenuDTO.setCodigoOpcion(idOpcion);

      try {
         opcionMenuDTO = menuHelperBD.guardarOpcionPerfil(opcionMenuDTO, perfilSeguridadDTO, aplicacionSeguridadDTO);
      } catch (Exception var9) {
         this.imprimirExcepcion(var9);
      }

      return opcionMenuDTO;
   }

   public OpcionMenuDTO eliminarOpcionPerfil(String idApp, String idOpcion, String idPerfil) throws Exception {
      PerfilSeguridadDTO perfilSeguridadDTO = new PerfilSeguridadDTO();
      OpcionMenuDTO opcionMenuDTO = new OpcionMenuDTO();
      AplicacionSeguridadDTO aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
      MenuHelperBD menuHelperBD = new MenuHelperBD();
      aplicacionSeguridadDTO.setCodigoAplicacion(idApp);
      perfilSeguridadDTO.setCodigoPerfil(idPerfil);
      opcionMenuDTO.setCodigoOpcion(idOpcion);

      try {
         opcionMenuDTO = menuHelperBD.eliminarOpcionPerfil(opcionMenuDTO, perfilSeguridadDTO, aplicacionSeguridadDTO);
      } catch (Exception var9) {
         this.imprimirExcepcion(var9);
      }

      return opcionMenuDTO;
   }
}
