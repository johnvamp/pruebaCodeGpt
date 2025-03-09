package co.com.personalsoft.seguridad.config;


public interface SeguridadWS_SEI extends java.rmi.Remote
{
  public java.lang.String bajarOpcion(java.lang.String idApp,co.com.personalsoft.base.seguridad.beans.OpcionMenuDTO opcionMenuDTO) throws java.lang.Exception;
  public java.lang.String eliminarOpcion(java.lang.String idApp,java.lang.String idOpcion) throws java.lang.Exception;
  public co.com.personalsoft.base.seguridad.beans.PerfilSeguridadDTO guardarPerfil(java.lang.String idApp,co.com.personalsoft.base.seguridad.beans.PerfilSeguridadDTO perfilSeguridadDTO) throws java.lang.Exception;
  public co.com.personalsoft.base.seguridad.beans.UsuarioSeguridadDTO[] consultarUsuariosPaginados(java.lang.String idApp,java.lang.String dsLogin,java.lang.String idPerfil,co.com.personalsoft.base.paginacion.beans.PaginacionDTO paginacionDTO) throws java.lang.Exception;
  public co.com.personalsoft.base.seguridad.beans.GenericaDTO[] consultarEntidadesGenericas(java.lang.String idApp,java.lang.String idGenerica) throws java.lang.Exception;
  public co.com.personalsoft.base.seguridad.beans.RecursoSeguridadDTO[] consultarRecursosPerfil(java.lang.String idApp,java.lang.String idPerfil) throws java.lang.Exception;
  public java.lang.String subirOpcion(java.lang.String idApp,co.com.personalsoft.base.seguridad.beans.OpcionMenuDTO opcionMenuDTO) throws java.lang.Exception;
  public java.lang.String eliminarRecursoPerfil(java.lang.String idApp,java.lang.String idRecurso,java.lang.String idPerfil) throws java.lang.Exception;
  public co.com.personalsoft.base.seguridad.beans.OpcionMenuDTO eliminarOpcionPerfil(java.lang.String idApp,java.lang.String idOpcion,java.lang.String idPerfil) throws java.lang.Exception;
  public co.com.personalsoft.base.seguridad.beans.GenericaDTO consultarEntidadGenerica(java.lang.String idApp,java.lang.String idGenerica) throws java.lang.Exception;
  public co.com.personalsoft.base.seguridad.beans.PerfilSeguridadDTO[] consultarPerfiles(java.lang.String idApp,java.lang.String dsPerfil) throws java.lang.Exception;
  public co.com.personalsoft.base.seguridad.beans.PerfilSeguridadDTO[] consultarPerfilesUsuario(java.lang.String idApp,java.lang.String usuario) throws java.lang.Exception;
  public java.lang.String actualizarRecurso(java.lang.String idApp,co.com.personalsoft.base.seguridad.beans.RecursoSeguridadDTO recursoSeguridadDTO) throws java.lang.Exception;
  public co.com.personalsoft.base.seguridad.beans.OpcionMenuDTO guardarOpcion(java.lang.String idApp,co.com.personalsoft.base.seguridad.beans.OpcionMenuDTO opcionMenuDTO) throws java.lang.Exception;
  public co.com.personalsoft.base.seguridad.beans.OpcionMenuDTO guardarOpcionPerfil(java.lang.String idApp,java.lang.String idOpcion,java.lang.String idPerfil) throws java.lang.Exception;
  public java.lang.String actualizarUsuario(java.lang.String idApp,co.com.personalsoft.base.seguridad.beans.UsuarioSeguridadDTO usuarioSeguridadDTO) throws java.lang.Exception;
  public java.lang.String eliminarPerfilXUsuario(java.lang.String idApp,java.lang.String usuario,java.lang.String idPerfil) throws java.lang.Exception;
  public co.com.personalsoft.base.seguridad.beans.RecursoSeguridadDTO[] consultarRecursos(java.lang.String idApp,java.lang.String dsRecurso) throws java.lang.Exception;
  public co.com.personalsoft.base.seguridad.beans.UsuarioSeguridadDTO[] consultarUsuarios(java.lang.String idApp,java.lang.String dsLogin,java.lang.String idPerfil) throws java.lang.Exception;
  public java.lang.String eliminarRecurso(java.lang.String idApp,java.lang.String idRecurso) throws java.lang.Exception;
  public co.com.personalsoft.base.seguridad.beans.PerfilSeguridadDTO consultarPerfil(java.lang.String idApp,java.lang.String codigoPerfil) throws java.lang.Exception;
  public co.com.personalsoft.base.seguridad.beans.RecursoSeguridadDTO guardarRecursoPerfil(java.lang.String idApp,java.lang.String idRecurso,java.lang.String idPerfil) throws java.lang.Exception;
  public co.com.personalsoft.base.seguridad.beans.RecursoSeguridadDTO consultarRecurso(java.lang.String idApp,java.lang.String codigoRecurso) throws java.lang.Exception;
  public java.lang.String actualizarPerfil(java.lang.String idApp,co.com.personalsoft.base.seguridad.beans.PerfilSeguridadDTO perfilSeguridadDTO) throws java.lang.Exception;
  public java.lang.String eliminarPerfil(java.lang.String idApp,java.lang.String idPerfil) throws java.lang.Exception;
  public java.lang.String eliminarGenericaXPerfil(java.lang.String idApp,java.lang.String idPerfil,java.lang.String idGenerica) throws java.lang.Exception;
  public co.com.personalsoft.base.seguridad.beans.UsuarioSeguridadDTO consultarUsuario(java.lang.String idApp,java.lang.String dsLogin) throws java.lang.Exception;
  public java.lang.String guardarPerfilXUsuario(java.lang.String idApp,java.lang.String usuario,java.lang.String idPerfil) throws java.lang.Exception;
  public java.lang.String eliminarUsuario(java.lang.String idApp,co.com.personalsoft.base.seguridad.beans.UsuarioSeguridadDTO usuarioSeguridadDTO) throws java.lang.Exception;
  public co.com.personalsoft.base.seguridad.beans.RecursoSeguridadDTO[] consultarRecursosConPerfiles(java.lang.String idApp,java.lang.String idPerfil) throws java.lang.Exception;
  public co.com.personalsoft.base.seguridad.beans.RecursoSeguridadDTO guardarRecurso(java.lang.String idApp,co.com.personalsoft.base.seguridad.beans.RecursoSeguridadDTO recursoSeguridadDTO) throws java.lang.Exception;
  public co.com.personalsoft.base.seguridad.beans.OpcionMenuDTO[] consultarOpcionesMenu(java.lang.String idApp) throws java.lang.Exception;
  public co.com.personalsoft.base.seguridad.beans.OpcionMenuDTO[] consultarOpcionesConPerfil(java.lang.String idApp,java.lang.String idPerfil) throws java.lang.Exception;
  public java.lang.String guardarUsuario(java.lang.String idApp,co.com.personalsoft.base.seguridad.beans.UsuarioSeguridadDTO usuarioSeguridadDTO) throws java.lang.Exception;
  public co.com.personalsoft.base.seguridad.beans.GenericaDTO[] consultarEntidadesGenericaXPerfil(java.lang.String idApp,java.lang.String idPerfil) throws java.lang.Exception;
  public co.com.personalsoft.base.seguridad.beans.PerfilSeguridadDTO[] consultarPerfilesPaginados(java.lang.String idApp,co.com.personalsoft.base.seguridad.beans.PerfilSeguridadDTO perfilSeguridadDTO,co.com.personalsoft.base.paginacion.beans.PaginacionDTO paginacionDTO) throws java.lang.Exception;
  public java.lang.String guardarGenericaXPerfil(java.lang.String idApp,java.lang.String idPerfil,java.lang.String idGenerica) throws java.lang.Exception;
}