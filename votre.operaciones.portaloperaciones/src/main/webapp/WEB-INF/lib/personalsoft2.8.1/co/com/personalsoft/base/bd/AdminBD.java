package co.com.personalsoft.base.bd;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class AdminBD {
   private static AdminBD adminBD;
   private Logger logger = Logger.getLogger(this.getClass());

   private AdminBD() {
   }

   public static AdminBD getInstance() {
      if (adminBD == null) {
         adminBD = new AdminBD();
      }

      return adminBD;
   }

   public Connection obtenerConexionJNDI() throws PersonalsoftException {
      Connection conn = null;

      PersonalsoftException personalE;
      try {
         Context initContext = new InitialContext();
         DataSource ds = null;

         try {
            ds = (DataSource)initContext.lookup(Configuracion.getInstance().getParametroApp("jndi"));
         } catch (NamingException var5) {
            this.logger.error("Se produjo un error en la localización del recurso jndi por medio directo.");
         }

         if (ds == null) {
            ds = (DataSource)initContext.lookup("java:comp/env/" + Configuracion.getInstance().getParametroApp("jndi"));
         }

         conn = ds.getConnection();
         return conn;
      } catch (NamingException var6) {
         personalE = new PersonalsoftException(var6);
         this.logger.error(personalE.getTraza());
         throw new PersonalsoftException(var6);
      } catch (SQLException var7) {
         personalE = new PersonalsoftException(var7);
         this.logger.error(personalE.getTraza());
         throw new PersonalsoftException(var7);
      }
   }

   public Connection obtenerConexionJNDI(String jndi) throws PersonalsoftException {
      Connection conn = null;

      PersonalsoftException personalE;
      try {
         Context initContext = new InitialContext();
         DataSource ds = null;

         try {
            ds = (DataSource)initContext.lookup(jndi);
         } catch (NamingException var6) {
            this.logger.error("Se produjo un error en la localización del recurso jndi por medio directo.");
         }

         if (ds == null) {
            ds = (DataSource)initContext.lookup("java:comp/env/" + jndi);
         }

         conn = ds.getConnection();
         return conn;
      } catch (NamingException var7) {
         personalE = new PersonalsoftException(var7);
         this.logger.error(personalE.getTraza());
         throw new PersonalsoftException(var7);
      } catch (SQLException var8) {
         personalE = new PersonalsoftException(var8);
         this.logger.error(personalE.getTraza());
         throw new PersonalsoftException(var8);
      }
   }

   public Connection obtenerConexion(String driver, String url, String usuario, String clave) throws PersonalsoftException {
      Connection conn = null;

      try {
         Class.forName(driver);
         conn = DriverManager.getConnection(url, usuario, clave);
         return conn;
      } catch (Exception var7) {
         throw new PersonalsoftException(var7);
      }
   }
}
