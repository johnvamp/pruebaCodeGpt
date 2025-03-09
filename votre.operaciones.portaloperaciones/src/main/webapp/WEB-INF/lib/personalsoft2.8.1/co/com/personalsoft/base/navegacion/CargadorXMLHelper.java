package co.com.personalsoft.base.navegacion;

import co.com.personalsoft.base.beans.Recurso;
import co.com.personalsoft.base.beans.Recursos;
import co.com.personalsoft.base.config.Configuracion;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class CargadorXMLHelper {
   private HashMap<String, Recurso> getRecursosControlados(List listaRecursos, HashMap<String, Recurso> tablaRecursos) {
      List acciones = null;
      Element valor = null;
      Element valorAccion = null;
      if (listaRecursos != null && listaRecursos.size() > 0) {
         for(int i = 0; i < listaRecursos.size(); ++i) {
            valor = (Element)listaRecursos.get(i);
            acciones = valor.getChildren();
            if (acciones != null && acciones.size() > 0) {
               for(int j = 0; j < acciones.size(); ++j) {
                  valorAccion = (Element)acciones.get(j);
                  if (valorAccion.getAttributeValue("accion") != null) {
                     tablaRecursos.put(valor.getAttributeValue("entidad") + "." + valorAccion.getAttributeValue("accion"), new Recurso(valor.getAttributeValue("entidad"), valorAccion.getAttributeValue("accion"), valorAccion.getAttributeValue("localizacion"), valorAccion.getAttributeValue("siguiente"), true));
                  } else if (valorAccion.getAttributeValue("nombre") != null) {
                     tablaRecursos.put(valor.getAttributeValue("entidad") + "." + valorAccion.getAttributeValue("nombre"), new Recurso(valor.getAttributeValue("entidad"), valorAccion.getAttributeValue("accion"), valorAccion.getAttributeValue("localizacion"), valorAccion.getAttributeValue("siguiente"), false));
                  }
               }
            }
         }
      }

      return tablaRecursos;
   }

   private HashMap<String, Recurso> getRecursosAceptados(List listaRecursos, HashMap<String, Recurso> tablaRecursosAceptados) {
      Element valor = null;
      if (listaRecursos != null && listaRecursos.size() > 0) {
         for(int i = 0; i < listaRecursos.size(); ++i) {
            valor = (Element)listaRecursos.get(i);
            if (valor != null && valor.getAttributeValue("localizacion") != null && (valor.getAttributeValue("accion") != null || valor.getAttributeValue("nombre") != null)) {
               if (valor.getAttributeValue("accion") != null) {
                  tablaRecursosAceptados.put(valor.getAttributeValue("accion"), new Recurso(valor.getAttributeValue("entidad"), valor.getAttributeValue("accion"), valor.getAttributeValue("localizacion"), valor.getAttributeValue("siguiente"), true));
               } else if (valor.getAttributeValue("nombre") != null) {
                  tablaRecursosAceptados.put(valor.getAttributeValue("nombre"), new Recurso(valor.getAttributeValue("entidad"), valor.getAttributeValue("accion"), valor.getAttributeValue("localizacion"), valor.getAttributeValue("siguiente"), false));
               }
            }
         }
      }

      return tablaRecursosAceptados;
   }

   private Document loadDocument() throws JDOMException, IOException {
      InputStream inputStr = Configuracion.getInstance().getContext().getResourceAsStream(Configuracion.getInstance().getParametro("rutaConfiguracionRecursos"));
      Document document = null;
      SAXBuilder sax = new SAXBuilder();

      try {
         document = sax.build(inputStr);
         return document;
      } catch (JDOMException var5) {
         throw var5;
      }
   }

   private Recursos parseDocument() throws JDOMException, IOException {
      HashMap<String, Recurso> tablaRecursos = new HashMap();
      HashMap<String, Recurso> tablaRecursosAceptados = new HashMap();
      Recursos recursos = new Recursos();
      Element raiz = null;
      List listaRecursos = null;
      Document document = null;
      document = this.loadDocument();
      if (document != null) {
         raiz = document.getRootElement();
         listaRecursos = raiz.getChildren("recurso");
         tablaRecursos = this.getRecursosControlados(listaRecursos, tablaRecursos);
         recursos.setRecursosControlados(tablaRecursos);
         listaRecursos = raiz.getChildren("recursoAceptado");
         tablaRecursosAceptados = this.getRecursosAceptados(listaRecursos, tablaRecursosAceptados);
         recursos.setRecursosAceptados(tablaRecursosAceptados);
      }

      return recursos;
   }

   public Recursos getRecursos() throws JDOMException, IOException {
      return this.parseDocument();
   }
}
