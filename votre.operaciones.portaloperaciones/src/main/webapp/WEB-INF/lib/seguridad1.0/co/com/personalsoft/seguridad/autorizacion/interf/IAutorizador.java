package co.com.personalsoft.seguridad.autorizacion.interf;

import co.com.personalsoft.base.excepcion.PersonalsoftException;

public interface IAutorizador {
   boolean tienePermiso(String var1, String var2) throws PersonalsoftException;
}
