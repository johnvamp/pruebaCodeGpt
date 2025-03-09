package co.com.personalsoft.seguridad.autenticacion.impl;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.seguridad.beans.ServidorLDAPDTO;
import co.com.personalsoft.base.seguridad.beans.UsuarioSeguridadDTO;
import co.com.personalsoft.seguridad.servicio.GestorSeguridad;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import javax.naming.AuthenticationException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.PartialResultException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import org.apache.log4j.Logger;

public class AutenticadorLDAP extends Autenticador {
   private ArrayList<ServidorLDAPDTO> servidores = new ArrayList();
   private Logger logger = Logger.getLogger(this.getClass());

   public AutenticadorLDAP() {
      ServidorLDAPDTO servidorLDAPDTO = (ServidorLDAPDTO)GestorSeguridad.getInstance().getParametro("servidorLDAP");
      this.servidores.add(servidorLDAPDTO);
   }

   public ArrayList<ServidorLDAPDTO> getServidores() {
      return this.servidores;
   }

   public void setServidores(ArrayList<ServidorLDAPDTO> servidores) {
      this.servidores = servidores;
   }

   public UsuarioSeguridadDTO autenticar(UsuarioSeguridadDTO usuario) {
      DirContext ctx = null;
      Iterator var4 = this.servidores.iterator();

      while(var4.hasNext()) {
         ServidorLDAPDTO servidor = (ServidorLDAPDTO)var4.next();
         usuario = this.consultarDatosUsuario(usuario, servidor, false);
         if (usuario != null && usuario.getNombre() != null && !usuario.getNombre().equals("")) {
            try {
               ctx = this.conectarLDAPUser(servidor, usuario);
               usuario.setAutenticado(true);
               break;
            } catch (AuthenticationException var16) {
               usuario.setAutenticado(false);
               this.logger.warn("Intento de logueo fallido para " + usuario.getUsuario());
            } catch (NamingException var17) {
               usuario.setAutenticado(false);
               this.logger.error((new PersonalsoftException(var17)).getTraza());
            } catch (Exception var18) {
               this.logger.error((new PersonalsoftException(var18)).getTraza());
            } finally {
               try {
                  if (ctx != null) {
                     ctx.close();
                  }
               } catch (NamingException var15) {
                  this.logger.error((new PersonalsoftException(var15)).getTraza());
               }

            }
         }
      }

      return usuario;
   }

   public UsuarioSeguridadDTO consultarDatosUsuario(UsuarioSeguridadDTO usuario, ServidorLDAPDTO servidor, boolean ssl) {
      try {
         DirContext ctx = this.conectarLDAPPrincipal(servidor, ssl);
         String filter = "(&(objectClass=Person) ((sAMAccountName=" + usuario.getUsuario() + ")))";
         String[] attrIDs = new String[]{"cn", "sAMAccountName", "distinguishedName", "mail"};
         SearchControls ctls = new SearchControls();
         ctls.setReturningAttributes(attrIDs);
         ctls.setSearchScope(2);
         NamingEnumeration answer = ctx.search(servidor.getBaseDN(), filter, ctls);

         try {
            if (answer.hasMore()) {
               SearchResult sr = (SearchResult)answer.next();
               Attributes attrs = sr.getAttributes();
               if (attrs != null) {
                  usuario = this.assemblerUsuario(usuario, attrs);
               }

               return usuario;
            }
         } catch (PartialResultException var15) {
            this.logger.warn("Finalizó el recorrido de la consulta con advertencias. " + var15.getMessage());
            return usuario;
         } finally {
            ctx.close();
         }

         return null;
      } catch (Exception var17) {
         this.logger.error((new PersonalsoftException(var17)).getTraza());
         return usuario;
      }
   }

   private UsuarioSeguridadDTO assemblerUsuario(UsuarioSeguridadDTO usuario, Attributes attrs) {
      if (usuario == null) {
         usuario = new UsuarioSeguridadDTO();
      }

      usuario.setNombre(attrs.get("cn") != null ? attrs.get("cn").toString().replaceAll("cn: ", "") : "");
      usuario.setUsuario(attrs.get("sAMAccountName") != null ? attrs.get("sAMAccountName").toString().replaceAll("sAMAccountName: ", "") : "");
      usuario.setDistintivoLDAP(attrs.get("distinguishedName") != null ? attrs.get("distinguishedName").toString().replaceAll("distinguishedName: ", "") : "");
      usuario.setEmail(attrs.get("mail") != null ? attrs.get("mail").toString().replaceAll("mail: ", "") : "");
      return usuario;
   }

   private DirContext conectarLDAPPrincipal(ServidorLDAPDTO servidor, boolean protocoloSSL) throws NamingException {
      Hashtable<String, String> env = new Hashtable();
      env.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
      env.put("java.naming.factory.state", "PersonStateFactory");
      env.put("java.naming.factory.object", "PersonObjectFactory");
      env.put("java.naming.security.principal", servidor.getUsuario());
      env.put("java.naming.security.credentials", servidor.getClave());
      env.put("java.naming.factory.control", "com.sun.jndi.ldap.ControlFactory");
      if (protocoloSSL) {
         env.put("java.naming.provider.url", "ldap://" + servidor.getHost() + ":" + servidor.getPuertoSSL() + "/");
         env.put("java.naming.security.protocol", "ssl");
      } else {
         env.put("java.naming.provider.url", "ldap://" + servidor.getHost() + ":" + servidor.getPuerto() + "/");
      }

      DirContext ctx = new InitialDirContext(env);
      return ctx;
   }

   public DirContext conectarLDAPUser(ServidorLDAPDTO servidor, UsuarioSeguridadDTO usuario) throws NamingException {
      Hashtable<String, String> env = new Hashtable();
      env.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
      env.put("java.naming.factory.state", "PersonStateFactory");
      env.put("java.naming.factory.object", "PersonObjectFactory");
      env.put("java.naming.security.principal", usuario.getDistintivoLDAP());
      env.put("java.naming.security.credentials", usuario.getClave());
      env.put("java.naming.factory.control", "com.sun.jndi.ldap.ControlFactory");
      env.put("java.naming.provider.url", "ldap://" + servidor.getHost() + ":" + servidor.getPuerto() + "/");
      DirContext ctx = new InitialDirContext(env);
      return ctx;
   }
}
