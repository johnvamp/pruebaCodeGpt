package co.com.personalsoft.base.config;

import co.com.personalsoft.base.beans.Servicio;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class CargadorXMLConfigFW {
   private Document loadDocument() throws JDOMException, IOException {
      InputStream inputStr = Configuracion.getInstance().getContext().getResourceAsStream(Configuracion.getInstance().getParametro("rutaArchivoConfiguracionFW"));
      Document document = null;
      SAXBuilder sax = new SAXBuilder();

      try {
         document = sax.build(inputStr);
         return document;
      } catch (JDOMException var5) {
         throw var5;
      }
   }

   public void parseDocument() throws JDOMException, IOException {
      Element raiz = null;
      List items = null;
      Document document = null;
      Element valor = null;
      Servicio servicio = null;
      document = this.loadDocument();
      if (document != null) {
         raiz = document.getRootElement();
         items = raiz.getChildren("item");
         if (items != null && items.size() > 0) {
            for(int i = 0; i < items.size(); ++i) {
               valor = (Element)items.get(i);
               if (valor.getAttributeValue("tipo") == null) {
                  throw new JDOMException("Error de sintaxis en el archivo de configuración.");
               }

               if (!valor.getAttributeValue("tipo").equalsIgnoreCase("query") && !valor.getAttributeValue("tipo").equalsIgnoreCase("sp")) {
                  if (valor.getAttributeValue("tipo").equals("parametro")) {
                     Configuracion.getInstance().putParametroApp(valor.getAttributeValue("nombre"), valor.getAttributeValue("valor"));
                  } else if (valor.getAttributeValue("tipo").equals("servicio")) {
                     servicio = new Servicio();
                     servicio.setRuta(valor.getAttributeValue("ruta"));
                     servicio.setNombre(valor.getAttributeValue("nombre"));
                     servicio.setUsuario(valor.getAttributeValue("usuario"));
                     servicio.setClave(valor.getAttributeValue("clave"));
                     Configuracion.getInstance().putServicio(valor.getAttributeValue("nombre"), servicio);
                  }
               } else {
                  Configuracion.getInstance().putParametroApp(valor.getAttributeValue("nombre"), valor.getAttributeValue("ruta"));
               }
            }
         }
      }

   }
}
