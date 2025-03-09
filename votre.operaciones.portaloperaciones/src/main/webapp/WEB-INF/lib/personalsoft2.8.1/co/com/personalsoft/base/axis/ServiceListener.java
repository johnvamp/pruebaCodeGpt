package co.com.personalsoft.base.axis;

import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.crypt.AgenteEncripcion;
import java.util.Iterator;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import org.apache.axis.AxisFault;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.w3c.dom.Node;

public class ServiceListener extends BasicHandler {
   private static final long serialVersionUID = 1L;
   public boolean admisionServicio = false;

   public void invoke(MessageContext msgContext) throws AxisFault {
      try {
         Message msg = msgContext.getRequestMessage();
         SOAPEnvelope envelope = msg.getSOAPEnvelope();
         SOAPHeader header = envelope.getHeader();
         Iterator it = header.examineAllHeaderElements();

         while(it.hasNext()) {
            SOAPHeaderElement hel = (SOAPHeaderElement)it.next();
            String headerName = hel.getNodeName();
            if (headerName.equals("sec:securityHeader")) {
               this.checkUsername(hel);
            }
         }

         if (!this.admisionServicio) {
            throw new AxisFault("No pudieron ser validadas las credenciales de seguridad.");
         }
      } catch (SOAPException var8) {
         throw new AxisFault("Failed to retrieve the SOAP Header or it's details properly.", var8);
      }
   }

   private void checkUsername(SOAPHeaderElement hel) throws AxisFault {
      String username = AgenteEncripcion.desencriptarBlowfish(this.getUsername(hel), Configuracion.getInstance().getParametro("semillaEncripcion"));
      String password = AgenteEncripcion.desencriptarBlowfish(this.getPassword(hel), Configuracion.getInstance().getParametro("semillaEncripcion"));
      if (Configuracion.getInstance().getServicio("adminFW") == null) {
         throw new AxisFault("El servicio adminFW no fué encontrado en el archivo de configuración.");
      } else if (username.equals(Configuracion.getInstance().getServicio("adminFW").getUsuario()) && password.equals(Configuracion.getInstance().getServicio("adminFW").getClave())) {
         this.admisionServicio = true;
      } else {
         throw new AxisFault("Acceso Denegado");
      }
   }

   private String getPassword(SOAPHeaderElement hel) throws AxisFault {
      Node passwordNode = hel.getLastChild();
      String nodename = passwordNode.getNodeName();
      if (!nodename.equals("sec:password")) {
         throw new AxisFault("No se encontró el password en el encabezado SOAP.");
      } else {
         String password = passwordNode.getFirstChild().getNodeValue();
         return password;
      }
   }

   private String getUsername(SOAPHeaderElement hel) throws AxisFault {
      Node usernameNode = hel.getFirstChild();
      String nodename = usernameNode.getNodeName();
      if (!nodename.equals("sec:username")) {
         throw new AxisFault("No se encontró el password en el encabezado SOAP.");
      } else {
         String username = usernameNode.getFirstChild().getNodeValue();
         return username;
      }
   }
}
