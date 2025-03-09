package co.com.personalsoft.seguridad.bd;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.seguridad.beans.ServidorBDDTO;
import co.com.personalsoft.seguridad.servicio.GestorSeguridad;
import java.sql.SQLException;

public class ConfiguracionBDHelper {
   public BDHelper bHelper() throws PersonalsoftException {
      ServidorBDDTO seguServidorBD = null;
      BDHelper bdHelper = null;
      seguServidorBD = (ServidorBDDTO)GestorSeguridad.getInstance().getParametro("servidor-jndi");
      if (seguServidorBD != null) {
         bdHelper = new BDHelper(seguServidorBD.getNombreJNDI());
      } else {
         seguServidorBD = (ServidorBDDTO)GestorSeguridad.getInstance().getParametro("servidor-jdbc");
         if (seguServidorBD != null) {
            bdHelper = new BDHelper(seguServidorBD.getDriver(), seguServidorBD.getUrl(), seguServidorBD.getUsuario(), seguServidorBD.getClave());
         } else {
            bdHelper = new BDHelper();
         }
      }

      return bdHelper;
   }

   public void evaluarExcepcionSQL(Exception exception, BDHelper bdHelper) throws PersonalsoftException {
      PersonalsoftException personalsoftException = null;
      if (exception instanceof PersonalsoftException) {
         personalsoftException = (PersonalsoftException)exception;
      } else {
         personalsoftException = new PersonalsoftException(exception);
      }

      if (personalsoftException.getException() instanceof SQLException) {
         try {
            bdHelper.rollbackTransaction();
         } catch (SQLException var5) {
            personalsoftException = new PersonalsoftException(var5);
         }
      }

      throw personalsoftException;
   }

   public void evaluarExcepcion(Exception e, BDHelper bdHelper) throws PersonalsoftException {
      PersonalsoftException personalsoftException = null;
      if (e instanceof PersonalsoftException) {
         personalsoftException = (PersonalsoftException)e;
      } else {
         personalsoftException = new PersonalsoftException(e);
      }

      throw personalsoftException;
   }

   public void cerrarConexion(BDHelper bdHelper) throws PersonalsoftException {
      PersonalsoftException personalsoftException = null;

      try {
         bdHelper.cerrarConexion();
      } catch (SQLException var4) {
         personalsoftException = new PersonalsoftException(var4);
         throw personalsoftException;
      }
   }

   public void cerrarConexionCommit(BDHelper bdHelper) throws PersonalsoftException {
      PersonalsoftException personalsoftException = null;

      try {
         bdHelper.commitTransaction();
         bdHelper.cerrarConexion();
      } catch (SQLException var4) {
         personalsoftException = new PersonalsoftException(var4);
         throw personalsoftException;
      }
   }
}
