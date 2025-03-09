package co.com.personalsoft.base.navegacion;

import co.com.personalsoft.base.beans.Servicio;
import co.com.personalsoft.base.beans.Servicios;
import co.com.personalsoft.base.config.Configuracion;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class CargadorXMLServiciosHelper {
   private HashMap<String, Servicio> getServicios(List listaServicios, HashMap<String, Servicio> tablaServicios) {
      Element valor = null;
      Servicio servicio = null;
      if (listaServicios != null && listaServicios.size() > 0) {
         for(int i = 0; i < listaServicios.size(); ++i) {
            valor = (Element)listaServicios.get(i);
            if (valor != null && valor.getAttributeValue("nombre") != null) {
               servicio = new Servicio(valor.getAttributeValue("nombre"), valor.getAttributeValue("localizacion"), valor.getAttributeValue("tipo"));
               servicio.setUsuario(valor.getAttributeValue("usuario"));
               servicio.setClave(valor.getAttributeValue("clave"));
               tablaServicios.put(valor.getAttributeValue("nombre"), servicio);
            }
         }
      }

      return tablaServicios;
   }

   private Document loadDocument() throws JDOMException, IOException {
      InputStream inputStr = Configuracion.getInstance().getContext().getResourceAsStream(Configuracion.getInstance().getParametro("rutaConfiguracionServicios"));
      SAXBuilder sax = null;
      sax = new SAXBuilder();
      Document document = null;

      try {
         document = sax.build(inputStr);
         return document;
      } catch (JDOMException var5) {
         throw var5;
      }
   }

   private Servicios parseDocument() throws JDOMException, IOException {
      HashMap<String, Servicio> tablaServicios = new HashMap();
      Element raiz = null;
      List listaRecursos = null;
      Document document = null;
      Servicios servicios = new Servicios();
      document = this.loadDocument();
      if (document != null) {
         raiz = document.getRootElement();
         listaRecursos = raiz.getChildren("servicio");
         tablaServicios = this.getServicios(listaRecursos, tablaServicios);
         servicios.setServicios(tablaServicios);
      }

      return servicios;
   }

   public Servicios getServicios() throws JDOMException, IOException {
      return this.parseDocument();
   }
}
