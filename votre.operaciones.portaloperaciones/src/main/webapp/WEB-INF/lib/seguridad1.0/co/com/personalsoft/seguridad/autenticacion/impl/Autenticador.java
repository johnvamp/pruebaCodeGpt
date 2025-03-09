package co.com.personalsoft.seguridad.autenticacion.impl;

import co.com.personalsoft.base.seguridad.beans.UsuarioSeguridadDTO;
import co.com.personalsoft.seguridad.servicio.GestorSeguridad;

public abstract class Autenticador {
   public static Autenticador getInstance() {
      Autenticador autenticador = null;
      String medioAutenticacion = (String)GestorSeguridad.getInstance().getParametro("medioAutenticacion");
      if (medioAutenticacion.equals("LDAP")) {
         autenticador = new AutenticadorLDAP();
      } else {
         autenticador = new AutenticadorBD();
      }

      return (Autenticador)autenticador;
   }

   public abstract UsuarioSeguridadDTO autenticar(UsuarioSeguridadDTO var1);
}
