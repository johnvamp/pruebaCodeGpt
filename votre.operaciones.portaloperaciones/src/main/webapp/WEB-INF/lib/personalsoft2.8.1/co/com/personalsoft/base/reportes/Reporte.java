package co.com.personalsoft.base.reportes;

import co.com.personalsoft.base.excepcion.PersonalsoftException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.JRXmlExporter;

public class Reporte {
   public void generarReportePDF(Map parametros, String rutaJasper, OutputStream outstr) throws PersonalsoftException {
      JasperPrint jp = null;
      byte[] content = (byte[])null;
      FileInputStream fis = null;

      try {
         fis = new FileInputStream(rutaJasper);
         jp = JasperFillManager.fillReport(fis, parametros, new JREmptyDataSource());
         content = JasperExportManager.exportReportToPdf(jp);
         outstr.write(content);
         outstr.flush();
      } catch (Exception var18) {
         throw new PersonalsoftException(var18);
      } finally {
         if (fis != null) {
            try {
               fis.close();
            } catch (IOException var17) {
               throw new PersonalsoftException(var17);
            }
         }

         if (outstr != null) {
            try {
               outstr.close();
            } catch (IOException var16) {
               throw new PersonalsoftException(var16);
            }
         }

      }

   }

   public void generarReportePDF(Map parametros, Collection datos, String rutaJasper, OutputStream outstr) throws PersonalsoftException {
      if (datos != null && datos.size() > 0) {
         JasperPrint jp = null;
         byte[] content = (byte[])null;
         FileInputStream fis = null;

         try {
            fis = new FileInputStream(rutaJasper);
            jp = JasperFillManager.fillReport(fis, parametros, new JRBeanCollectionDataSource(datos));
            content = JasperExportManager.exportReportToPdf(jp);
            outstr.write(content);
            outstr.flush();
         } catch (Exception var19) {
            throw new PersonalsoftException(var19);
         } finally {
            if (fis != null) {
               try {
                  fis.close();
               } catch (IOException var18) {
                  throw new PersonalsoftException(var18);
               }
            }

            if (outstr != null) {
               try {
                  outstr.close();
               } catch (IOException var17) {
                  throw new PersonalsoftException(var17);
               }
            }

         }
      } else {
         this.generarReportePDF(parametros, rutaJasper, outstr);
      }

   }

   public void generarReporteXML(Map parametros, String rutaJasper, OutputStream outstr) throws PersonalsoftException {
      JasperPrint jp = null;
      FileInputStream fis = null;
      JRXmlExporter exporter = null;

      try {
         fis = new FileInputStream(rutaJasper);
         jp = JasperFillManager.fillReport(fis, parametros, new JREmptyDataSource());
         exporter = new JRXmlExporter();
         exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jp);
         exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outstr);
         exporter.exportReport();
      } catch (Exception var15) {
         throw new PersonalsoftException(var15);
      } finally {
         if (fis != null) {
            try {
               fis.close();
            } catch (IOException var14) {
               throw new PersonalsoftException(var14);
            }
         }

      }

   }

   public void generarReporteXML(Map parametros, Collection datos, String rutaJasper, OutputStream outstr) throws PersonalsoftException {
      if (datos != null && datos.size() > 0) {
         JasperPrint jp = null;
         FileInputStream fis = null;
         JRXmlExporter exporter = null;

         try {
            fis = new FileInputStream(rutaJasper);
            jp = JasperFillManager.fillReport(fis, parametros, new JRBeanCollectionDataSource(datos));
            exporter = new JRXmlExporter();
            exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jp);
            exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outstr);
            exporter.exportReport();
         } catch (Exception var16) {
            throw new PersonalsoftException(var16);
         } finally {
            if (fis != null) {
               try {
                  fis.close();
               } catch (IOException var15) {
                  throw new PersonalsoftException(var15);
               }
            }

         }
      } else {
         this.generarReporteXML(parametros, rutaJasper, outstr);
      }

   }

   public void generarReporteHTML(Map parametros, String rutaJasper, OutputStream outstr) throws PersonalsoftException {
      JasperPrint jp = null;
      FileInputStream fis = null;
      JRHtmlExporter exporter = null;

      try {
         fis = new FileInputStream(rutaJasper);
         jp = JasperFillManager.fillReport(fis, parametros, new JREmptyDataSource());
         exporter = new JRHtmlExporter();
         exporter.setParameter(JRHtmlExporterParameter.JASPER_PRINT, jp);
         exporter.setParameter(JRHtmlExporterParameter.OUTPUT_STREAM, outstr);
         exporter.exportReport();
      } catch (Exception var15) {
         throw new PersonalsoftException(var15);
      } finally {
         if (fis != null) {
            try {
               fis.close();
            } catch (IOException var14) {
               throw new PersonalsoftException(var14);
            }
         }

      }

   }

   public void generarReporteHTML(Map parametros, Collection datos, String rutaJasper, OutputStream outstr, HttpSession session) throws PersonalsoftException {
      if (datos != null && datos.size() > 0) {
         JasperPrint jp = null;
         FileInputStream fis = null;
         JRHtmlExporter exporter = null;

         try {
            fis = new FileInputStream(rutaJasper);
            jp = JasperFillManager.fillReport(fis, parametros, new JRBeanCollectionDataSource(datos));
            exporter = new JRHtmlExporter();
            exporter.setParameter(JRHtmlExporterParameter.JASPER_PRINT, jp);
            exporter.setParameter(JRHtmlExporterParameter.OUTPUT_STREAM, outstr);
            exporter.setParameter(JRHtmlExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
            if (session != null) {
               session.setAttribute("net.sf.jasperreports.j2ee.jasper_print", jp);
               exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "image?image=");
            }

            exporter.exportReport();
         } catch (Exception var17) {
            throw new PersonalsoftException(var17);
         } finally {
            if (fis != null) {
               try {
                  fis.close();
               } catch (IOException var16) {
                  throw new PersonalsoftException(var16);
               }
            }

         }
      } else {
         this.generarReporteHTML(parametros, datos, rutaJasper, outstr);
      }

   }

   public void generarReporteHTML(Map parametros, Collection datos, String rutaJasper, OutputStream outstr) throws PersonalsoftException {
      this.generarReporteHTML(parametros, datos, rutaJasper, outstr, (HttpSession)null);
   }

   public void generarReporteXLS(Map parametros, String rutaJasper, OutputStream outstr) throws PersonalsoftException {
      JasperPrint jp = null;
      FileInputStream fis = null;
      JRXlsExporter exporter = null;

      try {
         fis = new FileInputStream(rutaJasper);
         jp = JasperFillManager.fillReport(fis, parametros, new JREmptyDataSource());
         exporter = new JRXlsExporter();
         exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jp);
         exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outstr);
         exporter.exportReport();
      } catch (Exception var15) {
         throw new PersonalsoftException(var15);
      } finally {
         if (fis != null) {
            try {
               fis.close();
            } catch (IOException var14) {
               throw new PersonalsoftException(var14);
            }
         }

      }

   }

   public void generarReporteXLS(Map parametros, Collection datos, String rutaJasper, OutputStream outstr) throws PersonalsoftException {
      if (datos != null && datos.size() > 0) {
         JasperPrint jp = null;
         FileInputStream fis = null;
         JRXlsExporter exporter = null;

         try {
            fis = new FileInputStream(rutaJasper);
            jp = JasperFillManager.fillReport(fis, parametros, new JRBeanCollectionDataSource(datos));
            exporter = new JRXlsExporter();
            exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jp);
            exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outstr);
            exporter.exportReport();
         } catch (Exception var16) {
            throw new PersonalsoftException(var16);
         } finally {
            if (fis != null) {
               try {
                  fis.close();
               } catch (IOException var15) {
                  throw new PersonalsoftException(var15);
               }
            }

         }
      } else {
         this.generarReporteXLS(parametros, rutaJasper, outstr);
      }

   }

   public void generarReporteCSV(Map parametros, String rutaJasper, OutputStream outstr) throws PersonalsoftException {
      JasperPrint jp = null;
      FileInputStream fis = null;
      JRCsvExporter exporter = null;

      try {
         fis = new FileInputStream(rutaJasper);
         jp = JasperFillManager.fillReport(fis, parametros, new JREmptyDataSource());
         exporter = new JRCsvExporter();
         exporter.setParameter(JRCsvExporterParameter.JASPER_PRINT, jp);
         exporter.setParameter(JRCsvExporterParameter.OUTPUT_STREAM, outstr);
         exporter.exportReport();
      } catch (Exception var15) {
         throw new PersonalsoftException(var15);
      } finally {
         if (fis != null) {
            try {
               fis.close();
            } catch (IOException var14) {
               throw new PersonalsoftException(var14);
            }
         }

      }

   }

   public void generarReporteCSV(Map parametros, Collection datos, String rutaJasper, OutputStream outstr) throws PersonalsoftException {
      if (datos != null && datos.size() > 0) {
         JasperPrint jp = null;
         FileInputStream fis = null;
         JRCsvExporter exporter = null;

         try {
            fis = new FileInputStream(rutaJasper);
            jp = JasperFillManager.fillReport(fis, parametros, new JRBeanCollectionDataSource(datos));
            exporter = new JRCsvExporter();
            exporter.setParameter(JRCsvExporterParameter.JASPER_PRINT, jp);
            exporter.setParameter(JRCsvExporterParameter.OUTPUT_STREAM, outstr);
            exporter.exportReport();
         } catch (Exception var16) {
            throw new PersonalsoftException(var16);
         } finally {
            if (fis != null) {
               try {
                  fis.close();
               } catch (IOException var15) {
                  throw new PersonalsoftException(var15);
               }
            }

         }
      } else {
         this.generarReporteCSV(parametros, datos, rutaJasper, outstr);
      }

   }
}
