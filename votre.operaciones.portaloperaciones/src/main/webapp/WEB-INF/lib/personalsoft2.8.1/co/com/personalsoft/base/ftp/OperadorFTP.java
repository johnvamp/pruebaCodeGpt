package co.com.personalsoft.base.ftp;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import com.amoebacode.ftp.FTPClient;
import com.amoebacode.ftp.IllegalFTPResponseException;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import org.apache.log4j.Logger;

public class OperadorFTP {
   private Logger logger = Logger.getLogger(this.getClass());

   public FTPClient obtenerConexion(Servidor servidor) throws PersonalsoftException {
      FTPClient ftpClient = new FTPClient();

      try {
         ftpClient.openConnection(servidor.getHost(), servidor.getPuerto());
         ftpClient.login(servidor.getUsuario(), servidor.getClave());
         return ftpClient;
      } catch (UnknownHostException var4) {
         this.logger.error("Host desconocido.", var4);
         throw new PersonalsoftException(var4);
      } catch (IllegalFTPResponseException var5) {
         this.logger.error("Error conexión FTP.", var5);
         throw new PersonalsoftException(var5);
      } catch (IOException var6) {
         this.logger.error("Error de entrada/salida en la autenticación.", var6);
         throw new PersonalsoftException(var6);
      }
   }

   public void cerrarConexion(FTPClient ftpClient) throws PersonalsoftException {
      try {
         ftpClient.closeConnection();
      } catch (IOException var3) {
         this.logger.error("Error al cerrar la conexión FTP.", var3);
         throw new PersonalsoftException(var3);
      }
   }

   public void downloadFile(FTPClient ftpClient, Servidor servidorLocal, Servidor servidorOrigen, String archivo) throws PersonalsoftException {
      try {
         ftpClient.changeDirectory(servidorOrigen.getRutaArchivos());
         ftpClient.downloadFile(archivo, servidorLocal.getRutaArchivos() + archivo, servidorOrigen.isAscii());
      } catch (IllegalFTPResponseException var6) {
         this.logger.error("Error conexión FTP.", var6);
         throw new PersonalsoftException(var6);
      } catch (IOException var7) {
         this.logger.error("Error de entrada/salida en el proceso de descarga.", var7);
         throw new PersonalsoftException(var7);
      }
   }

   public void downloadDirectory(FTPClient ftpClient, Servidor servidorLocal, Servidor servidorOrigen, boolean descargaRecursiva) throws PersonalsoftException {
      try {
         ftpClient.downloadDirectory(servidorOrigen.getRutaArchivos(), servidorLocal.getRutaArchivos(), servidorOrigen.isAscii(), descargaRecursiva);
      } catch (IllegalFTPResponseException var6) {
         this.logger.error("Error conexión FTP.", var6);
         throw new PersonalsoftException(var6);
      } catch (IOException var7) {
         this.logger.error("Error de entrada/salida en el proceso de descarga.", var7);
         throw new PersonalsoftException(var7);
      }
   }

   public void uploadFile(FTPClient ftpClient, Servidor servidorLocal, Servidor servidorDestino, String archivo) throws PersonalsoftException {
      try {
         ftpClient.changeDirectory(servidorDestino.getRutaArchivos());
         ftpClient.uploadFile(archivo, servidorLocal.getRutaArchivos() + archivo, servidorDestino.isAscii());
      } catch (IllegalFTPResponseException var6) {
         this.logger.error("Error conexión FTP.", var6);
         throw new PersonalsoftException(var6);
      } catch (IOException var7) {
         this.logger.error("Error de entrada/salida en el proceso de descarga.", var7);
         throw new PersonalsoftException(var7);
      }
   }

   public void uploadDirectory(FTPClient ftpClient, Servidor servidorLocal, Servidor servidorDestino, boolean descargaRecursiva) throws PersonalsoftException {
      try {
         ftpClient.uploadDirectory(servidorDestino.getRutaArchivos(), servidorLocal.getRutaArchivos(), servidorLocal.isAscii(), descargaRecursiva);
      } catch (IllegalFTPResponseException var6) {
         this.logger.error("Error conexión FTP.", var6);
         throw new PersonalsoftException(var6);
      } catch (IOException var7) {
         this.logger.error("Error de entrada/salida en el proceso de descarga.", var7);
         throw new PersonalsoftException(var7);
      }
   }

   public boolean deleteLocalDirectory(Servidor servidorIntermedio) {
      File path = new File(servidorIntermedio.getRutaArchivos());
      Servidor servidorTmp = null;
      if (path.exists()) {
         File[] files = path.listFiles();

         for(int i = 0; i < files.length; ++i) {
            servidorTmp = new Servidor();
            servidorTmp.setRutaArchivos(files[i].getAbsolutePath());
            if (files[i].isDirectory()) {
               this.deleteLocalDirectory(servidorTmp);
            } else {
               files[i].delete();
            }
         }
      }

      return path.delete();
   }

   public void deleteFiles(Servidor servidorIntermedio) {
      File path = new File(servidorIntermedio.getRutaArchivos());
      if (path.exists()) {
         File[] files = path.listFiles();

         for(int i = 0; i < files.length; ++i) {
            files[i].delete();
         }
      }

   }
}
