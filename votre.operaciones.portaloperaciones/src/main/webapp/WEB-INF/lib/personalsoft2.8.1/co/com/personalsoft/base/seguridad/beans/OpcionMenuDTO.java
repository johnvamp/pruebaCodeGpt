package co.com.personalsoft.base.seguridad.beans;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class OpcionMenuDTO implements Serializable, Comparable<OpcionMenuDTO> {
   private static final long serialVersionUID = -1414859898987320417L;
   public static final String IGUAL = "=";
   public static final int PRIMER_NIVEL = 1;
   private String codigoOpcion;
   private String nombreOpcion;
   private String codigoOpcionHija;
   private String codigoOpcionPadre;
   private String codigoOpcionPosterior;
   private String codigoOpcionAnterior;
   private String url;
   private String target;
   private String existe;
   private String activa;
   private int nivel;
   private int orden;
   private String tieneHijos;
   private Set<OpcionMenuDTO> listaOpcionesHijas;
   private List<OpcionMenuRecursoSeguridadDTO> listaOpcionesMenuRecursosPadres;
   private OpcionMenuDTO opcionPadreDTO;
   private MensajeDTO mensajeDTO = new MensajeDTO();
   private PerfilSeguridadDTO perfilSeguridadDTO;

   public String getCodigoOpcion() {
      return this.codigoOpcion;
   }

   public void setCodigoOpcion(String codigoOpcion) {
      this.codigoOpcion = codigoOpcion;
   }

   public String getCodigoOpcionHija() {
      return this.codigoOpcionHija;
   }

   public void setCodigoOpcionHija(String codigoOpcionHija) {
      this.codigoOpcionHija = codigoOpcionHija;
   }

   public String getCodigoOpcionPadre() {
      return this.codigoOpcionPadre;
   }

   public void setCodigoOpcionPadre(String codigoOpcionPadre) {
      this.codigoOpcionPadre = codigoOpcionPadre;
   }

   public int getNivel() {
      return this.nivel;
   }

   public void setNivel(int nivel) {
      this.nivel = nivel;
   }

   public String getNombreOpcion() {
      return this.nombreOpcion;
   }

   public void setNombreOpcion(String nombreOpcion) {
      this.nombreOpcion = nombreOpcion;
   }

   public String getTarget() {
      return this.target;
   }

   public void setTarget(String target) {
      this.target = target;
   }

   public String getUrl() {
      return this.url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public int getOrden() {
      return this.orden;
   }

   public void setOrden(int orden) {
      this.orden = orden;
   }

   public Set<OpcionMenuDTO> getListaOpcionesHijas() {
      return this.listaOpcionesHijas;
   }

   public void setListaOpcionesHijas(Set<OpcionMenuDTO> listaOpcionesHijas) {
      this.listaOpcionesHijas = listaOpcionesHijas;
   }

   public String getTieneHijos() {
      return this.tieneHijos;
   }

   public void setTieneHijos(String tieneHijos) {
      this.tieneHijos = tieneHijos;
   }

   public String getExiste() {
      return this.existe;
   }

   public void setExiste(String existe) {
      this.existe = existe;
   }

   public List<OpcionMenuRecursoSeguridadDTO> getListaOpcionesMenuRecursosPadres() {
      return this.listaOpcionesMenuRecursosPadres;
   }

   public void setListaOpcionesMenuRecursosPadres(List<OpcionMenuRecursoSeguridadDTO> listaOpcionesMenuRecursosPadres) {
      this.listaOpcionesMenuRecursosPadres = listaOpcionesMenuRecursosPadres;
   }

   public String getXML(UsuarioSeguridadDTO usuarioSeguridadDTO) {
      StringBuilder opcionMenu = new StringBuilder();
      opcionMenu.append("<opcion codigo=\"" + this.codigoOpcion + "\" ");
      opcionMenu.append("nombre=\"" + this.nombreOpcion + "\" ");
      opcionMenu.append("padre=\"" + (this.codigoOpcionPadre != null ? this.codigoOpcionPadre : "") + "\" ");
      if (this.url != null && this.url.endsWith("=")) {
         this.url = this.url + usuarioSeguridadDTO.getUsuario().trim();
         this.url = this.url.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("'", "&#8217;").replaceAll("\"", "&quot;").trim();
      }

      opcionMenu.append("url=\"" + this.url + "\" ");
      opcionMenu.append("target=\"" + this.target + "\" ");
      opcionMenu.append("nivel=\"" + this.nivel + "\" ");
      opcionMenu.append("orden=\"" + this.orden + "\" ");
      opcionMenu.append("hijos=\"" + (this.listaOpcionesHijas != null && !this.listaOpcionesHijas.isEmpty() ? "S" : "N") + "\" ");
      opcionMenu.append(">");
      if (this.listaOpcionesHijas != null && !this.listaOpcionesHijas.isEmpty()) {
         Iterator var4 = this.listaOpcionesHijas.iterator();

         while(var4.hasNext()) {
            OpcionMenuDTO opcionMenuTmp = (OpcionMenuDTO)var4.next();
            opcionMenu.append(opcionMenuTmp.getXML(usuarioSeguridadDTO));
         }
      }

      opcionMenu.append("</opcion>");
      return opcionMenu.toString();
   }

   public List<OpcionMenuDTO> addItems(List<OpcionMenuDTO> listaOpcionesMenu) {
      OpcionMenuDTO opcionMenuDTO = new OpcionMenuDTO();
      opcionMenuDTO.setCodigoOpcion(this.codigoOpcion);
      opcionMenuDTO.setNombreOpcion(this.nombreOpcion);
      opcionMenuDTO.setCodigoOpcionPadre(this.codigoOpcionPadre != null ? this.codigoOpcionPadre : "");
      opcionMenuDTO.setUrl(this.url);
      opcionMenuDTO.setTarget(this.target);
      opcionMenuDTO.setNivel(this.nivel);
      opcionMenuDTO.setOrden(this.orden);
      opcionMenuDTO.setTieneHijos(this.listaOpcionesHijas != null && !this.listaOpcionesHijas.isEmpty() ? "S" : "N");
      listaOpcionesMenu.add(opcionMenuDTO);
      if (this.listaOpcionesHijas != null && !this.listaOpcionesHijas.isEmpty()) {
         Iterator var4 = this.listaOpcionesHijas.iterator();

         while(var4.hasNext()) {
            OpcionMenuDTO opcionMenuTmp = (OpcionMenuDTO)var4.next();
            opcionMenuTmp.addItems(listaOpcionesMenu);
         }
      }

      return listaOpcionesMenu;
   }

   public String toString() {
      StringBuilder str = new StringBuilder();
      str.append(" Código Opción " + this.getCodigoOpcion() + "\n");
      str.append(" Nombre Opción " + this.getNombreOpcion() + "\n");
      str.append(" Código Padre  " + this.getCodigoOpcionPadre() + "\n");
      str.append(" Url " + this.getUrl() + "\n");
      str.append(" Target " + this.getTarget() + "\n");
      str.append(" Nivel " + this.getNivel() + "\n");
      str.append(" Mensaje Usuario " + this.mensajeDTO.getMensajeUsuario() + "\n");
      str.append(" Mensaje Técnico " + this.mensajeDTO.getMensajeTecnico());
      str.append(" Orden " + this.getOrden());
      return str.toString();
   }

   public int compareTo(OpcionMenuDTO opcion) {
      if (this.orden < opcion.getOrden()) {
         return -1;
      } else {
         return this.orden > opcion.getOrden() ? 1 : 0;
      }
   }

   public String getActiva() {
      return this.activa;
   }

   public void setActiva(String activa) {
      this.activa = activa;
   }

   public MensajeDTO getMensajeDTO() {
      return this.mensajeDTO;
   }

   public void setMensajeDTO(MensajeDTO mensajeDTO) {
      this.mensajeDTO = mensajeDTO;
   }

   public OpcionMenuDTO getOpcionPadreDTO() {
      return this.opcionPadreDTO;
   }

   public void setOpcionPadreDTO(OpcionMenuDTO opcionPadreDTO) {
      this.opcionPadreDTO = opcionPadreDTO;
   }

   public PerfilSeguridadDTO getPerfilSeguridadDTO() {
      return this.perfilSeguridadDTO;
   }

   public void setPerfilSeguridadDTO(PerfilSeguridadDTO perfilSeguridadDTO) {
      this.perfilSeguridadDTO = perfilSeguridadDTO;
   }

   public String getCodigoOpcionAnterior() {
      return this.codigoOpcionAnterior;
   }

   public void setCodigoOpcionAnterior(String codigoOpcionAnterior) {
      this.codigoOpcionAnterior = codigoOpcionAnterior;
   }

   public String getCodigoOpcionPosterior() {
      return this.codigoOpcionPosterior;
   }

   public void setCodigoOpcionPosterior(String codigoOpcionPosterior) {
      this.codigoOpcionPosterior = codigoOpcionPosterior;
   }
}
