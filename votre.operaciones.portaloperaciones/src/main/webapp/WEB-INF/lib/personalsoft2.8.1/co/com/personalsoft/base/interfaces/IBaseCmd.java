package co.com.personalsoft.base.interfaces;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IBaseCmd {
   void execute(HttpServletRequest var1, HttpServletResponse var2) throws PersonalsoftException;
}
