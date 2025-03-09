package co.com.personalsoft.seguridad.config;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.seguridad.beans.AplicacionSeguridadDTO;
import co.com.personalsoft.base.seguridad.beans.ServidorBDDTO;
import co.com.personalsoft.base.seguridad.beans.ServidorLDAPDTO;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import sun.misc.BASE64Decoder;

public class ParserConfiguracion {
   private Logger log = Logger.getLogger(this.getClass());

   public Map<String, Object> parseDocument(String rutaConfiguracionSeguridad) throws PersonalsoftException {
      Map<String, Object> cargarDocumento = null;
      PersonalsoftException personalsoftException = null;

      try {
         cargarDocumento = this.cargarDocument(rutaConfiguracionSeguridad);
         return cargarDocumento;
      } catch (Exception var5) {
         personalsoftException = new PersonalsoftException(var5);
         throw personalsoftException;
      }
   }

   private Map<String, Object> cargarDocument(String rutaConfiguracionSeguridad) throws JDOMException, IOException {
      Map<String, Object> tablaSeguridad = null;
      Element raiz = null;
      Document document = null;
      List listaParametros = null;
      List listaServidoresLDAP = null;
      List listaServidoresBD = null;
      List listaSentencias = null;
      document = this.cargarDocumento(rutaConfiguracionSeguridad);
      if (document != null) {
         tablaSeguridad = new HashMap();
         raiz = document.getRootElement();
         listaParametros = raiz.getChildren("parametros");
         this.putParametros(listaParametros, tablaSeguridad);
         listaServidoresLDAP = raiz.getChildren("servidores-LDAP");
         this.putServidoresLDAP(listaServidoresLDAP, tablaSeguridad);
         listaServidoresBD = raiz.getChildren("servidores-BD");
         this.putServidoresBD(listaServidoresBD, tablaSeguridad);
         listaSentencias = raiz.getChildren("sentencias");
         this.putSentencias(listaSentencias, tablaSeguridad);
      }

      return tablaSeguridad;
   }

   private Document cargarDocumento(String rutaConfiguracionSeguridad) throws JDOMException, IOException {
      SAXBuilder sax = null;
      sax = new SAXBuilder();
      Document document = null;
      InputStream inputStr = Configuracion.getInstance().getContext().getResourceAsStream(rutaConfiguracionSeguridad);

      try {
         document = sax.build(inputStr);
         return document;
      } catch (JDOMException var6) {
         throw var6;
      }
   }

   private Map<String, Object> putParametros(List<Element> parametros, Map<String, Object> tablaSeguridad) {
      Element valor = null;
      AplicacionSeguridadDTO aplicacionSeguridadDTO = null;
      String nombre = null;
      if (parametros != null && !parametros.isEmpty()) {
         Iterator var7 = parametros.iterator();

         while(true) {
            Element element;
            do {
               do {
                  do {
                     if (!var7.hasNext()) {
                        return tablaSeguridad;
                     }

                     element = (Element)var7.next();
                  } while(element == null);
               } while(element.getChildren() == null);
            } while(element.getChildren().isEmpty());

            for(int j = 0; j < element.getChildren().size(); ++j) {
               valor = (Element)element.getChildren().get(j);
               nombre = valor.getAttributeValue("nombre");
               if (nombre.equalsIgnoreCase("aplicacion")) {
                  aplicacionSeguridadDTO = new AplicacionSeguridadDTO();
                  aplicacionSeguridadDTO.setCodigoAplicacion(valor.getAttributeValue("valor"));
                  tablaSeguridad.put(nombre, aplicacionSeguridadDTO);
               } else {
                  tablaSeguridad.put(nombre, valor.getAttributeValue("valor"));
               }
            }
         }
      } else {
         return tablaSeguridad;
      }
   }

   private Map<String, Object> putServidoresLDAP(List<Element> servidoresLDAP, Map<String, Object> tablaSeguridad) {
      Element valor = null;
      ServidorLDAPDTO servidorLDAPDTO = null;
      BASE64Decoder decoder = null;
      PersonalsoftException personalsoftException = null;
      if (servidoresLDAP != null && !servidoresLDAP.isEmpty()) {
         decoder = new BASE64Decoder();
         Iterator var8 = servidoresLDAP.iterator();

         while(true) {
            Element element;
            do {
               do {
                  do {
                     if (!var8.hasNext()) {
                        return tablaSeguridad;
                     }

                     element = (Element)var8.next();
                  } while(element == null);
               } while(element.getChildren() == null);
            } while(element.getChildren().isEmpty());

            for(int j = 0; j < element.getChildren().size(); ++j) {
               valor = (Element)element.getChildren().get(j);
               servidorLDAPDTO = new ServidorLDAPDTO();
               servidorLDAPDTO.setNombreServidor(valor.getAttributeValue("nombre"));
               servidorLDAPDTO.setPuerto(Integer.parseInt(valor.getAttributeValue("puerto")));
               servidorLDAPDTO.setPuertoSSL(Integer.parseInt(valor.getAttributeValue("puertoSSL")));
               servidorLDAPDTO.setBaseDN(valor.getAttributeValue("baseDN"));
               servidorLDAPDTO.setUsuario(valor.getAttributeValue("usuario"));
               servidorLDAPDTO.setHost(valor.getAttributeValue("servidor"));

               try {
                  servidorLDAPDTO.setClave(new String(decoder.decodeBuffer(valor.getAttributeValue("clave"))));
               } catch (IOException var11) {
                  personalsoftException = new PersonalsoftException(var11);
                  this.log.error(personalsoftException.getTraza());
               }

               tablaSeguridad.put(servidorLDAPDTO.getNombreServidor(), servidorLDAPDTO);
            }
         }
      } else {
         return tablaSeguridad;
      }
   }

   private Map<String, Object> putServidoresBD(List<Element> servidoresBD, Map<String, Object> tablaSeguridad) {
      Element valor = null;
      ServidorBDDTO servidorBDDTO = null;
      String jndi = null;
      if (servidoresBD != null && !servidoresBD.isEmpty()) {
         Iterator var7 = servidoresBD.iterator();

         while(true) {
            Element element;
            do {
               do {
                  do {
                     if (!var7.hasNext()) {
                        return tablaSeguridad;
                     }

                     element = (Element)var7.next();
                  } while(element == null);
               } while(element.getChildren() == null);
            } while(element.getChildren().isEmpty());

            for(int j = 0; j < element.getChildren().size(); ++j) {
               valor = (Element)element.getChildren().get(j);
               jndi = valor.getAttributeValue("jndi");
               servidorBDDTO = new ServidorBDDTO();
               servidorBDDTO.setNombreServidor(valor.getAttributeValue("nombre"));
               if (jndi != null) {
                  servidorBDDTO.setNombreJNDI(jndi);
               } else {
                  servidorBDDTO.setUrl(valor.getAttributeValue("url"));
                  servidorBDDTO.setDriver(valor.getAttributeValue("driver"));
                  servidorBDDTO.setUsuario(valor.getAttributeValue("usuario"));
                  servidorBDDTO.setClave(valor.getAttributeValue("clave"));
               }

               tablaSeguridad.put(servidorBDDTO.getNombreServidor(), servidorBDDTO);
            }
         }
      } else {
         return tablaSeguridad;
      }
   }

   private Map<String, Object> putSentencias(List<Element> sentencias, Map<String, Object> tablaSeguridad) {
      Element valor = null;
      if (sentencias != null && !sentencias.isEmpty()) {
         Iterator var5 = sentencias.iterator();

         while(true) {
            Element element;
            do {
               do {
                  do {
                     if (!var5.hasNext()) {
                        return tablaSeguridad;
                     }

                     element = (Element)var5.next();
                     tablaSeguridad.put("TIPO", element.getAttributeValue("tipo"));
                  } while(element == null);
               } while(element.getChildren() == null);
            } while(element.getChildren().isEmpty());

            for(int j = 0; j < element.getChildren().size(); ++j) {
               valor = (Element)element.getChildren().get(j);
               tablaSeguridad.put(valor.getAttributeValue("id"), valor.getAttributeValue("valor"));
            }
         }
      } else {
         return tablaSeguridad;
      }
   }
}
