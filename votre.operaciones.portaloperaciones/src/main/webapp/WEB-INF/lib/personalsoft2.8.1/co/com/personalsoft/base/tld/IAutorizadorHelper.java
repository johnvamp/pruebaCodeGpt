package co.com.personalsoft.base.tld;

import javax.servlet.http.HttpServletRequest;

public interface IAutorizadorHelper {
   boolean tienePermiso(HttpServletRequest var1, String var2);
}
