package co.com.personalsoft.seguridad.tld;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.seguridad.beans.OpcionMenuDTO;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ParserMenu {
   private Logger log = Logger.getLogger(this.getClass());

   public Set<OpcionMenuDTO> getOpciones(String menuXml) throws PersonalsoftException {
      ArrayList<OpcionMenuDTO> menus = new ArrayList();
      List<OpcionMenuDTO> listaOpcionesEliminar = null;
      Document document = null;
      Element raiz = null;
      Element opcion = null;
      OpcionMenuDTO opcionMenuDTO = null;
      NodeList nl = null;
      PersonalsoftException personalsoftException = null;

      try {
         document = this.cargarDocumento(menuXml);
         if (document != null) {
            raiz = document.getDocumentElement();
            nl = raiz.getElementsByTagName("opcion");
            if (nl != null && nl.getLength() > 0) {
               int i;
               for(i = 0; i < nl.getLength(); ++i) {
                  opcion = (Element)nl.item(i);
                  opcionMenuDTO = this.getOpcion(opcion);
                  menus.add(opcionMenuDTO);
               }

               for(i = 0; i < menus.size(); ++i) {
                  for(int j = i + 1; j < menus.size(); ++j) {
                     if (((OpcionMenuDTO)menus.get(i)).getCodigoOpcion().equals(((OpcionMenuDTO)menus.get(j)).getCodigoOpcionPadre())) {
                        if (((OpcionMenuDTO)menus.get(i)).getListaOpcionesHijas() == null) {
                           ((OpcionMenuDTO)menus.get(i)).setListaOpcionesHijas(new TreeSet());
                        }

                        ((OpcionMenuDTO)menus.get(i)).getListaOpcionesHijas().add((OpcionMenuDTO)menus.get(j));
                     }
                  }
               }

               listaOpcionesEliminar = new ArrayList();

               for(i = 0; i < menus.size(); ++i) {
                  if (((OpcionMenuDTO)menus.get(i)).getCodigoOpcionPadre() != null && !((OpcionMenuDTO)menus.get(i)).getCodigoOpcionPadre().equals("")) {
                     listaOpcionesEliminar.add((OpcionMenuDTO)menus.get(i));
                  }
               }

               menus.removeAll(listaOpcionesEliminar);
            }
         }
      } catch (Exception var12) {
         personalsoftException = new PersonalsoftException(var12);
         this.log.error(personalsoftException.getTraza());
         throw personalsoftException;
      }

      return new TreeSet(menus);
   }

   private OpcionMenuDTO getOpcion(Element opcion) {
      OpcionMenuDTO opcionMenuDTO = new OpcionMenuDTO();
      opcionMenuDTO.setCodigoOpcion(opcion.getAttribute("codigo"));
      opcionMenuDTO.setNombreOpcion(opcion.getAttribute("nombre"));
      opcionMenuDTO.setCodigoOpcionPadre(opcion.getAttribute("padre"));
      opcionMenuDTO.setUrl(opcion.getAttribute("url"));
      opcionMenuDTO.setTarget(opcion.getAttribute("target"));
      opcionMenuDTO.setNivel(Integer.parseInt(opcion.getAttribute("nivel")));
      opcionMenuDTO.setOrden(Integer.parseInt(opcion.getAttribute("orden")));
      opcionMenuDTO.setTieneHijos(opcion.getAttribute("hijos"));
      return opcionMenuDTO;
   }

   private Document cargarDocumento(String menuXml) throws IOException, ParserConfigurationException, SAXException {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

      try {
         DocumentBuilder db = dbf.newDocumentBuilder();
         Document dom = db.parse(new InputSource(new StringReader(menuXml)));
         return dom;
      } catch (ParserConfigurationException var5) {
         throw var5;
      } catch (SAXException var6) {
         throw var6;
      } catch (IOException var7) {
         throw var7;
      }
   }
}
