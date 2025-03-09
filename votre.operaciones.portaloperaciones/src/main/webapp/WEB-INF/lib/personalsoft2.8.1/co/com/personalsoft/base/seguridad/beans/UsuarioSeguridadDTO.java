package co.com.personalsoft.base.seguridad.beans;

import java.io.Serializable;

public class UsuarioSeguridadDTO implements Serializable, Cloneable, Comparable<UsuarioSeguridadDTO> {
   private String clave;
   private String claveAnterior;
   private String usuario;
   private String nombre;
   private String multiSesion;
   private String intentos;
   private String fechaUltimoLogueo;
   private String activo;
   private String cambioClave;
   private String email;
   private PerfilSeguridadDTO perfilSeguridadDTO;
   private CargoSeguridadDTO cargoSeguridadDTO;
   private Object entidadAdicional;
   private String distintivoLDAP;
   private boolean autenticado;
   private MensajeDTO mensajeDTO = new MensajeDTO();
   private static final long serialVersionUID = -3609942845314266166L;

   public Object clone() throws CloneNotSupportedException {
      UsuarioSeguridadDTO usuarioSeguridadDTO = null;
      usuarioSeguridadDTO = (UsuarioSeguridadDTO)super.clone();
      if (this.getPerfilSeguridadDTO() != null) {
         usuarioSeguridadDTO.setPerfilSeguridadDTO((PerfilSeguridadDTO)this.getPerfilSeguridadDTO().clone());
      }

      if (this.getCargoSeguridadDTO() != null) {
         usuarioSeguridadDTO.setCargoSeguridadDTO((CargoSeguridadDTO)this.getCargoSeguridadDTO().clone());
      }

      if (this.getMensajeDTO() != null) {
         usuarioSeguridadDTO.setMensajeDTO((MensajeDTO)this.getMensajeDTO().clone());
      }

      return usuarioSeguridadDTO;
   }

   public String getClave() {
      return this.clave;
   }

   public void setClave(String clave) {
      this.clave = clave;
   }

   public String getNombre() {
      return this.nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public void setPerfilSeguridadDTO(PerfilSeguridadDTO perfilSeguridadDTO) {
      this.perfilSeguridadDTO = perfilSeguridadDTO;
   }

   public String getUsuario() {
      return this.usuario;
   }

   public void setUsuario(String usuario) {
      this.usuario = usuario;
   }

   public Object getEntidadAdicional() {
      return this.entidadAdicional;
   }

   public void setEntidadAdicional(Object entidadAdicional) {
      this.entidadAdicional = entidadAdicional;
   }

   public String getMultiSesion() {
      return this.multiSesion;
   }

   public void setMultiSesion(String multiSesion) {
      this.multiSesion = multiSesion;
   }

   public String getActivo() {
      return this.activo;
   }

   public void setActivo(String activo) {
      this.activo = activo;
   }

   public String getFechaUltimoLogueo() {
      return this.fechaUltimoLogueo;
   }

   public void setFechaUltimoLogueo(String fechaUltimoLogueo) {
      this.fechaUltimoLogueo = fechaUltimoLogueo;
   }

   public String getIntentos() {
      return this.intentos;
   }

   public void setIntentos(String intentos) {
      this.intentos = intentos;
   }

   public PerfilSeguridadDTO getPerfilSeguridadDTO() {
      return this.perfilSeguridadDTO;
   }

   public CargoSeguridadDTO getCargoSeguridadDTO() {
      return this.cargoSeguridadDTO;
   }

   public void setCargoSeguridadDTO(CargoSeguridadDTO cargoSeguridadDTO) {
      this.cargoSeguridadDTO = cargoSeguridadDTO;
   }

   public boolean isAutenticado() {
      return this.autenticado;
   }

   public void setAutenticado(boolean autenticado) {
      this.autenticado = autenticado;
   }

   public String getDistintivoLDAP() {
      return this.distintivoLDAP;
   }

   public void setDistintivoLDAP(String distintivoLDAP) {
      this.distintivoLDAP = distintivoLDAP;
   }

   public String toString() {
      StringBuilder str = new StringBuilder();
      str.append(" Usuario " + this.getUsuario() + "\n");
      str.append(" Nombre " + this.getNombre() + "\n");
      str.append(" MultiSesion " + this.getMultiSesion() + "\n");
      str.append(" Código Cargo " + (this.getCargoSeguridadDTO() != null ? this.getCargoSeguridadDTO().getCodigoCargo() : "") + "\n");
      str.append(" Código Perfil " + (this.getPerfilSeguridadDTO() != null ? this.getPerfilSeguridadDTO().getCodigoPerfil() : "") + "\n");
      str.append(" Autenticado " + this.isAutenticado() + "\n");
      str.append(" Email " + this.getEmail() + "\n");
      str.append(" Activo " + this.getActivo() + "\n");
      str.append(" Fecha Último Logueo " + this.getFechaUltimoLogueo() + "\n");
      str.append(" Número Intentos " + this.getIntentos() + "\n");
      str.append(" Mensaje Usuario " + this.mensajeDTO.getMensajeUsuario() + "\n");
      str.append(" Mensaje Técnico " + this.mensajeDTO.getMensajeTecnico());
      return str.toString();
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public int compareTo(UsuarioSeguridadDTO usuarioSeguridadDTO) {
      return this.nombre.compareTo(usuarioSeguridadDTO.getNombre());
   }

   public MensajeDTO getMensajeDTO() {
      return this.mensajeDTO;
   }

   public void setMensajeDTO(MensajeDTO mensajeDTO) {
      this.mensajeDTO = mensajeDTO;
   }

   public String getCambioClave() {
      return this.cambioClave;
   }

   public void setCambioClave(String cambioClave) {
      this.cambioClave = cambioClave;
   }

   public String getClaveAnterior() {
      return this.claveAnterior;
   }

   public void setClaveAnterior(String claveAnterior) {
      this.claveAnterior = claveAnterior;
   }
}
