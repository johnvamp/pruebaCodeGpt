package co.com.personalsoft.base.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class OperadorZIP {
   private static final int BUFFER = 2048;

   public void comprimir(String archivoComprimir, String archivoZIPDestino) throws Exception {
      File archivoDest = null;
      File file = null;
      FileOutputStream dest = null;
      ZipOutputStream zos = null;

      try {
         archivoDest = new File(archivoZIPDestino);
         dest = new FileOutputStream(archivoDest);
         zos = new ZipOutputStream(new BufferedOutputStream(dest));
         file = new File(archivoComprimir);
         this.iniciarCompresion(file, zos);
      } catch (Exception var11) {
         throw var11;
      } finally {
         if (dest != null) {
            dest.close();
         }

         if (zos != null) {
            zos.close();
         }

      }

   }

   public void comprimir(String archivoComprimir, OutputStream salida, boolean cerrarFlujo) throws Exception {
      ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(salida));

      try {
         File file = new File(archivoComprimir);
         this.iniciarCompresion(file, zos);
      } catch (Exception var9) {
         throw var9;
      } finally {
         if (zos != null) {
            zos.close();
         }

         if (cerrarFlujo && salida != null) {
            salida.close();
         }

      }

   }

   public void comprimir(String archivoComprimir, String archivoZIPDestino, boolean compresionRecursiva) throws Exception {
      if (compresionRecursiva) {
         File file = new File(archivoComprimir);
         ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(archivoZIPDestino));

         try {
            this.compresionRecursiva(file, zos, archivoComprimir);
         } catch (Exception var10) {
            throw var10;
         } finally {
            zos.close();
         }
      } else {
         this.comprimir(archivoComprimir, archivoZIPDestino);
      }

   }

   public void comprimir(String archivoComprimir, OutputStream salida, boolean cerrarFlujo, boolean compresionRecursiva) throws Exception {
      if (compresionRecursiva) {
         File file = new File(archivoComprimir);
         ZipOutputStream zos = new ZipOutputStream(salida);

         try {
            this.compresionRecursiva(file, zos, archivoComprimir);
         } catch (Exception var11) {
            throw var11;
         } finally {
            if (zos != null) {
               zos.close();
            }

            if (cerrarFlujo && salida != null) {
               salida.close();
            }

         }
      } else {
         this.comprimir(archivoComprimir, salida, cerrarFlujo);
      }

   }

   public void descomprimir(String archivoZIP, String destino) throws Exception {
      BufferedOutputStream dest = null;
      BufferedInputStream bis = new BufferedInputStream(new FileInputStream(archivoZIP));
      ZipInputStream zis = new ZipInputStream(bis);
      this.iniciarDescomprension(zis, destino, (BufferedOutputStream)dest);
      bis.close();
      zis.close();
   }

   public void descomprimir(FileInputStream archivoZIP, String destino) throws Exception {
      BufferedOutputStream dest = null;
      BufferedInputStream bis = new BufferedInputStream(archivoZIP);
      ZipInputStream zis = new ZipInputStream(bis);
      this.iniciarDescomprension(zis, destino, (BufferedOutputStream)dest);
      bis.close();
      zis.close();
   }

   private void compresionRecursiva(File file, ZipOutputStream zos, String archivoComprimir) throws Exception {
      int len;
      if (file.isDirectory()) {
         String[] listaArchivos = file.list();
         if (listaArchivos != null) {
            for(len = 0; len < listaArchivos.length; ++len) {
               this.compresionRecursiva(new File(file, listaArchivos[len]), zos, archivoComprimir);
            }
         }
      } else {
         byte[] buf = new byte[2048];
         String raiz = file.getParent().substring(archivoComprimir.lastIndexOf("/") + 1);
         String rutaRelativa = file.getParent().substring(file.getParent().indexOf(raiz));
         ZipEntry zipEntry = new ZipEntry(rutaRelativa + "/" + file.getName());
         FileInputStream fin = new FileInputStream(file);
         BufferedInputStream in = new BufferedInputStream(fin);

         try {
            zos.putNextEntry(zipEntry);

            while((len = in.read(buf)) >= 0) {
               zos.write(buf, 0, len);
            }
         } catch (Exception var15) {
            throw var15;
         } finally {
            in.close();
            zos.closeEntry();
         }
      }

   }

   private void iniciarCompresion(File file, ZipOutputStream zos) throws Exception {
      BufferedInputStream origin = null;
      FileInputStream fi = null;
      byte[] data = new byte[2048];

      try {
         fi = new FileInputStream(file);
         origin = new BufferedInputStream(fi, 2048);
         ZipEntry entry = new ZipEntry(file.getName());
         zos.putNextEntry(entry);

         int count;
         while((count = origin.read(data, 0, 2048)) != -1) {
            zos.write(data, 0, count);
         }
      } catch (Exception var11) {
         throw var11;
      } finally {
         if (origin != null) {
            origin.close();
         }

         if (fi != null) {
            fi.close();
         }

      }

   }

   private void iniciarDescomprension(ZipInputStream zis, String destino, BufferedOutputStream dest) throws Exception {
      if (zis != null) {
         byte[] data = new byte[2048];
         File file = new File(destino);
         file.mkdirs();

         while(true) {
            ZipEntry entry;
            while((entry = zis.getNextEntry()) != null) {
               if (entry.isDirectory()) {
                  file = new File(destino + entry.getName());
                  file.mkdirs();
               } else if (!entry.isDirectory()) {
                  String entryName = entry.getName();
                  String destFN = destino + entryName;
                  FileOutputStream fos = null;

                  try {
                     fos = new FileOutputStream(destFN);
                  } catch (FileNotFoundException var14) {
                     int posCarpeta = destFN.lastIndexOf("/");
                     String rutaCarpetas = destFN.substring(0, posCarpeta);
                     file = new File(rutaCarpetas);
                     file.mkdirs();
                     fos = new FileOutputStream(destFN);
                  }

                  dest = new BufferedOutputStream(fos, 2048);

                  int count;
                  while((count = zis.read(data, 0, 2048)) != -1) {
                     dest.write(data, 0, count);
                  }

                  dest.flush();
                  dest.close();
                  fos.close();
               }
            }

            return;
         }
      }
   }
}
