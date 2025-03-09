package co.com.personalsoft.seguridad.autenticacion.interf;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.seguridad.beans.UsuarioSeguridadDTO;

public interface Autenticador {
   UsuarioSeguridadDTO autenticar(UsuarioSeguridadDTO var1) throws PersonalsoftException;
}
