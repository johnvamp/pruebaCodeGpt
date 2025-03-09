package co.com.personalsoft.seguridad.bd;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.paginacion.beans.PaginacionDTO;
import co.com.personalsoft.base.seguridad.beans.AplicacionSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.OpcionMenuDTO;
import co.com.personalsoft.base.seguridad.beans.OpcionMenuRecursoSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.PerfilSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.UsuarioSeguridadDTO;
import co.com.personalsoft.seguridad.dao.MenusDAO;
import co.com.personalsoft.seguridad.dao.OpcionMenuRecursosDAO;
import co.com.personalsoft.seguridad.servicio.GestorSeguridad;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MenuHelperBD {
   public String generarMenu(Set<OpcionMenuDTO> listaMenu, UsuarioSeguridadDTO usuarioSeguridadDTO) {
      StringBuilder menu = new StringBuilder();
      menu.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>");
      menu.append("<menu>");
      Iterator var5 = listaMenu.iterator();

      while(var5.hasNext()) {
         OpcionMenuDTO opcionMenuDTO = (OpcionMenuDTO)var5.next();
         menu.append(opcionMenuDTO.getXML(usuarioSeguridadDTO));
      }

      menu.append("</menu>");
      return menu.toString();
   }

   public List<OpcionMenuDTO> listarMenu(Set<OpcionMenuDTO> listaMenu) {
      List<OpcionMenuDTO> menu = new ArrayList();
      Iterator var4 = listaMenu.iterator();

      while(var4.hasNext()) {
         OpcionMenuDTO opcionMenuTmp = (OpcionMenuDTO)var4.next();
         opcionMenuTmp.addItems(menu);
      }

      return menu;
   }

   public List<OpcionMenuDTO> consultarOpcionesMenuPerfil(AplicacionSeguridadDTO aplicacionSeguridadDTO, PerfilSeguridadDTO perfilSeguridadDTO) throws PersonalsoftException {
      MenusDAO menusDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      Set<OpcionMenuDTO> menus = null;
      List listaMenu = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         menusDAO = new MenusDAO(bdHelper);
         menus = this.organizarConJerarquia(menusDAO.consultarMenuPerfil(aplicacionSeguridadDTO, perfilSeguridadDTO));
         if (menus != null && !menus.isEmpty()) {
            listaMenu = this.listarMenu(menus);
         }
      } catch (Exception var12) {
         bdConfiguracionHelper.evaluarExcepcion(var12, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return listaMenu;
   }

   public List<OpcionMenuDTO> consultarOpcionesMenuRecursos(OpcionMenuDTO opcionMenuDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      MenusDAO menusDAO = null;
      OpcionMenuRecursosDAO opcionMenuRecursosDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      Set<OpcionMenuDTO> menus = null;
      List listaMenu = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         menusDAO = new MenusDAO(bdHelper);
         menus = this.organizarConJerarquia(menusDAO.consultarOpcionesMenu(opcionMenuDTO, aplicacionSeguridadDTO));
         if (menus != null && !menus.isEmpty()) {
            listaMenu = this.listarMenu(menus);
            opcionMenuRecursosDAO = new OpcionMenuRecursosDAO(bdHelper);
            Iterator var11 = listaMenu.iterator();

            while(var11.hasNext()) {
               OpcionMenuDTO opcionMenuTmpDTO = (OpcionMenuDTO)var11.next();
               if (opcionMenuTmpDTO.getTieneHijos().equals("N")) {
                  List<OpcionMenuRecursoSeguridadDTO> listaOpcionesMenuRecursosPadres = opcionMenuRecursosDAO.consultarRecursos(opcionMenuTmpDTO, aplicacionSeguridadDTO);
                  if (listaOpcionesMenuRecursosPadres != null && !listaOpcionesMenuRecursosPadres.isEmpty()) {
                     opcionMenuTmpDTO.setListaOpcionesMenuRecursosPadres(listaOpcionesMenuRecursosPadres);
                  }
               }
            }
         }
      } catch (Exception var15) {
         bdConfiguracionHelper.evaluarExcepcion(var15, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return listaMenu;
   }

   public List<OpcionMenuDTO> consultarOpcionesMenu(OpcionMenuDTO opcionMenuDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      MenusDAO menusDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      ArrayList listaMenu = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         menusDAO = new MenusDAO(bdHelper);
         listaMenu = this.organizarAscendente(menusDAO.consultarOpcionesMenu(opcionMenuDTO, aplicacionSeguridadDTO));
      } catch (Exception var11) {
         bdConfiguracionHelper.evaluarExcepcion(var11, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return listaMenu;
   }

   public String consultarMenuPerfil(AplicacionSeguridadDTO aplicacionSeguridadDTO, UsuarioSeguridadDTO usuarioSeguridadDTO) throws PersonalsoftException {
      MenusDAO menusDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      Set<OpcionMenuDTO> menu = null;
      String menuXml = "";

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         menusDAO = new MenusDAO(bdHelper);
         menu = this.organizarConJerarquia(menusDAO.consultarMenuPerfil(aplicacionSeguridadDTO, usuarioSeguridadDTO.getPerfilSeguridadDTO()));
         if (menu != null && !menu.isEmpty()) {
            menuXml = this.generarMenu(menu, usuarioSeguridadDTO);
         }
      } catch (Exception var12) {
         bdConfiguracionHelper.evaluarExcepcion(var12, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return menuXml;
   }

   public List<OpcionMenuDTO> consultarMenus(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuDTO nodoDTO, PaginacionDTO paginacionDTO) throws PersonalsoftException {
      MenusDAO menusDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      Set<OpcionMenuDTO> menu = null;
      List menus = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         menusDAO = new MenusDAO(bdHelper);
         menu = this.organizarConJerarquia(menusDAO.consultarMenus(aplicacionSeguridadDTO, nodoDTO, paginacionDTO));
         if (menu != null && !menu.isEmpty()) {
            menus = this.listarMenu(menu);
         }
      } catch (Exception var13) {
         bdConfiguracionHelper.evaluarExcepcion(var13, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return menus;
   }

   public List<OpcionMenuDTO> consultarMenus(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuDTO nodoDTO) throws PersonalsoftException {
      MenusDAO menusDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      List menus = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         menusDAO = new MenusDAO(bdHelper);
         menus = menusDAO.consultarMenus(aplicacionSeguridadDTO, nodoDTO);
      } catch (Exception var11) {
         bdConfiguracionHelper.evaluarExcepcion(var11, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return menus;
   }

   public OpcionMenuDTO consultarNodo(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuDTO nodoDTO) throws PersonalsoftException {
      MenusDAO menusDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      new OpcionMenuDTO();

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         menusDAO = new MenusDAO(bdHelper);
         nodoDTO = menusDAO.consultarNodo(aplicacionSeguridadDTO, nodoDTO);
         if (nodoDTO != null) {
            OpcionMenuDTO opcionMenuPadreDTO = new OpcionMenuDTO();
            opcionMenuPadreDTO.setCodigoOpcion(nodoDTO.getCodigoOpcionPadre());
            if (opcionMenuPadreDTO.getCodigoOpcion() != null) {
               opcionMenuPadreDTO = menusDAO.consultarNodo(aplicacionSeguridadDTO, opcionMenuPadreDTO);
               nodoDTO.setOpcionPadreDTO(opcionMenuPadreDTO);
               System.err.println(" PAPA ....." + nodoDTO.getOpcionPadreDTO().getNombreOpcion());
            }
         }
      } catch (Exception var11) {
         bdConfiguracionHelper.evaluarExcepcion(var11, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return nodoDTO;
   }

   public OpcionMenuDTO consultarNodoJerarquiaOrden(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuDTO nodoDTO) throws PersonalsoftException {
      MenusDAO menusDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         menusDAO = new MenusDAO(bdHelper);
         nodoDTO = menusDAO.consultarNodoJerarquiaOrden(aplicacionSeguridadDTO, nodoDTO);
      } catch (Exception var10) {
         bdConfiguracionHelper.evaluarExcepcion(var10, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return nodoDTO;
   }

   public OpcionMenuDTO guardarNodo(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuDTO nodoDTO) throws PersonalsoftException {
      MenusDAO menusDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         menusDAO = new MenusDAO(bdHelper);
         nodoDTO = menusDAO.guardarNodo(aplicacionSeguridadDTO, nodoDTO);
      } catch (Exception var10) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var10, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return nodoDTO;
   }

   public OpcionMenuDTO actualizarNodo(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuDTO nodoDTO) throws PersonalsoftException {
      MenusDAO menusDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         menusDAO = new MenusDAO(bdHelper);
         nodoDTO = menusDAO.actualizarNodo(aplicacionSeguridadDTO, nodoDTO);
      } catch (Exception var10) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var10, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return nodoDTO;
   }

   public OpcionMenuDTO eliminarNodo(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuDTO nodoDTO) throws PersonalsoftException {
      MenusDAO menusDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         menusDAO = new MenusDAO(bdHelper);
         nodoDTO = menusDAO.eliminarNodo(aplicacionSeguridadDTO, nodoDTO);
      } catch (Exception var10) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var10, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return nodoDTO;
   }

   public OpcionMenuDTO subirNodo(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuDTO nodoDTO) throws PersonalsoftException {
      MenusDAO menusDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         menusDAO = new MenusDAO(bdHelper);
         nodoDTO = menusDAO.subirNodo(aplicacionSeguridadDTO, nodoDTO);
      } catch (Exception var10) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var10, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return nodoDTO;
   }

   public OpcionMenuDTO bajarNodo(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuDTO nodoDTO) throws PersonalsoftException {
      MenusDAO menusDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         menusDAO = new MenusDAO(bdHelper);
         nodoDTO = menusDAO.bajarNodo(aplicacionSeguridadDTO, nodoDTO);
      } catch (Exception var10) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var10, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return nodoDTO;
   }

   public OpcionMenuDTO guardarNodoPerfil(AplicacionSeguridadDTO aplicacionSeguridadDTO, OpcionMenuDTO nodoDTO, PerfilSeguridadDTO perfilSeguridadDTO) throws PersonalsoftException {
      MenusDAO menusDAO = null;
      PerfilesHelperBD perfilesHelperBD = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      PerfilSeguridadDTO perfilSeguridadDataDTO = null;
      HashSet listaPermitidos = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         menusDAO = new MenusDAO(bdHelper);
         nodoDTO = menusDAO.guardarNodoPerfil(aplicacionSeguridadDTO, nodoDTO, perfilSeguridadDTO);
         perfilSeguridadDTO = GestorSeguridad.getInstance().getPerfilSeguridadOpcion(perfilSeguridadDTO.getCodigoPerfil());
         perfilSeguridadDataDTO = GestorSeguridad.getInstance().getPerfilSeguridadOpcion(perfilSeguridadDTO.getCodigoPerfil());
         if (perfilSeguridadDataDTO == null) {
            perfilSeguridadDTO = ((PerfilesHelperBD)perfilesHelperBD).consultarPerfil(aplicacionSeguridadDTO, perfilSeguridadDTO);
            listaPermitidos = new HashSet();
            listaPermitidos.add(perfilSeguridadDTO.getCodigoPerfil());
            perfilSeguridadDTO.setOpcionesPermitidas((String[])listaPermitidos.toArray(new String[listaPermitidos.size()]));
         }

         if (perfilSeguridadDTO != null && perfilSeguridadDTO.getNombrePerfil() != null && !perfilSeguridadDTO.getNombrePerfil().equals("")) {
            GestorSeguridad.getInstance().putPerfilSeguridadOpcion(perfilSeguridadDTO);
         }
      } catch (Exception var14) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var14, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return nodoDTO;
   }

   private Set<OpcionMenuDTO> organizarConJerarquia(List<OpcionMenuDTO> menus) {
      List<OpcionMenuDTO> listaOpcionesEliminar = null;

      int i;
      for(i = 0; i < menus.size(); ++i) {
         for(int j = i + 1; j < menus.size(); ++j) {
            if (((OpcionMenuDTO)menus.get(i)).getCodigoOpcion().equals(((OpcionMenuDTO)menus.get(j)).getCodigoOpcionPadre())) {
               if (((OpcionMenuDTO)menus.get(i)).getListaOpcionesHijas() == null) {
                  ((OpcionMenuDTO)menus.get(i)).setListaOpcionesHijas(new TreeSet());
               }

               ((OpcionMenuDTO)menus.get(i)).getListaOpcionesHijas().add((OpcionMenuDTO)menus.get(j));
            }
         }
      }

      listaOpcionesEliminar = new ArrayList();

      for(i = 0; i < menus.size(); ++i) {
         if (((OpcionMenuDTO)menus.get(i)).getCodigoOpcionPadre() != null) {
            listaOpcionesEliminar.add((OpcionMenuDTO)menus.get(i));
         }
      }

      menus.removeAll(listaOpcionesEliminar);
      return new TreeSet(menus);
   }

   private ArrayList<OpcionMenuDTO> organizarAscendente(List<OpcionMenuDTO> menu) {
      ArrayList<OpcionMenuDTO> retorno = new ArrayList();
      OpcionMenuDTO opcion = null;
      if (menu != null && !menu.isEmpty()) {
         for(int i = 0; i < menu.size(); ++i) {
            retorno.addAll(i, this.obtenerHijos(opcion, menu));
            opcion = (OpcionMenuDTO)retorno.get(i);
         }
      }

      return retorno;
   }

   private ArrayList<OpcionMenuDTO> obtenerHijos(OpcionMenuDTO opcion, List<OpcionMenuDTO> menu) {
      ArrayList<OpcionMenuDTO> retorno = new ArrayList();
      OpcionMenuDTO opcionAux;
      Iterator var5;
      if (opcion == null) {
         var5 = menu.iterator();

         while(var5.hasNext()) {
            opcionAux = (OpcionMenuDTO)var5.next();
            if (opcionAux.getNivel() == 1) {
               retorno.add(opcionAux);
            }
         }
      } else {
         var5 = menu.iterator();

         while(var5.hasNext()) {
            opcionAux = (OpcionMenuDTO)var5.next();
            if (opcionAux.getCodigoOpcionPadre() != null && opcionAux.getCodigoOpcionPadre().equals(opcion.getCodigoOpcion())) {
               retorno.add(opcionAux);
            }
         }
      }

      for(int i = 0; i < retorno.size(); ++i) {
         if (i == 0 && retorno.size() > 1) {
            ((OpcionMenuDTO)retorno.get(i)).setCodigoOpcionPosterior(((OpcionMenuDTO)retorno.get(i + 1)).getCodigoOpcion());
            ((OpcionMenuDTO)retorno.get(i)).setCodigoOpcionAnterior("0");
         } else if (i == retorno.size() - 1 && retorno.size() > 1) {
            ((OpcionMenuDTO)retorno.get(i)).setCodigoOpcionPosterior("0");
            ((OpcionMenuDTO)retorno.get(i)).setCodigoOpcionAnterior(((OpcionMenuDTO)retorno.get(i - 1)).getCodigoOpcion());
         } else if (retorno.size() > 1) {
            ((OpcionMenuDTO)retorno.get(i)).setCodigoOpcionPosterior(((OpcionMenuDTO)retorno.get(i + 1)).getCodigoOpcion());
            ((OpcionMenuDTO)retorno.get(i)).setCodigoOpcionAnterior(((OpcionMenuDTO)retorno.get(i - 1)).getCodigoOpcion());
         } else {
            ((OpcionMenuDTO)retorno.get(i)).setCodigoOpcionPosterior("0");
            ((OpcionMenuDTO)retorno.get(i)).setCodigoOpcionAnterior("0");
         }
      }

      return retorno;
   }

   public List<OpcionMenuDTO> consultarOpcionesConPerfil(PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      MenusDAO menuDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;
      ArrayList opciones = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         menuDAO = new MenusDAO(bdHelper);
         opciones = this.organizarAscendente(menuDAO.consultarOpcionesConPerfil(perfilSeguridadDTO, aplicacionSeguridadDTO));
      } catch (Exception var11) {
         bdConfiguracionHelper.evaluarExcepcion(var11, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexion(bdHelper);
      }

      return opciones;
   }

   public OpcionMenuDTO guardarOpcionPerfil(OpcionMenuDTO opcionMenuDTO, PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      MenusDAO menuDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         menuDAO = new MenusDAO(bdHelper);
         opcionMenuDTO = menuDAO.guardarOpcionPerfil(opcionMenuDTO, perfilSeguridadDTO, aplicacionSeguridadDTO);
      } catch (Exception var11) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var11, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return opcionMenuDTO;
   }

   public OpcionMenuDTO eliminarOpcionPerfil(OpcionMenuDTO opcionMenuDTO, PerfilSeguridadDTO perfilSeguridadDTO, AplicacionSeguridadDTO aplicacionSeguridadDTO) throws PersonalsoftException {
      MenusDAO menuDAO = null;
      BDHelper bdHelper = null;
      ConfiguracionBDHelper bdConfiguracionHelper = null;

      try {
         bdConfiguracionHelper = new ConfiguracionBDHelper();
         bdHelper = bdConfiguracionHelper.bHelper();
         menuDAO = new MenusDAO(bdHelper);
         opcionMenuDTO = menuDAO.eliminarOpcionPerfil(opcionMenuDTO, perfilSeguridadDTO, aplicacionSeguridadDTO);
      } catch (Exception var11) {
         bdConfiguracionHelper.evaluarExcepcionSQL(var11, bdHelper);
      } finally {
         bdConfiguracionHelper.cerrarConexionCommit(bdHelper);
      }

      return opcionMenuDTO;
   }
}
