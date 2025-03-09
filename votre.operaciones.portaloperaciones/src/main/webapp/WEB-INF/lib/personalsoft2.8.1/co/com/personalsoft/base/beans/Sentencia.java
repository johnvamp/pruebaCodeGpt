package co.com.personalsoft.base.beans;

import java.util.ArrayList;

public class Sentencia {
   private long fechaUltimoAcceso;
   private String sentenciaOrigen;
   private String sentenciaPsmt;
   private String rutaRecurso;
   private ArrayList parametros;
   private String nombreProcedimiento;
   private String invocacionProcedimiento;

   public Sentencia() {
   }

   public Sentencia(String sentencia) {
      this.sentenciaOrigen = sentencia;
   }

   public Sentencia(String sentencia, String rutaRecurso) {
      this.sentenciaOrigen = sentencia;
      this.rutaRecurso = rutaRecurso;
   }

   public Sentencia(long fechaUltimoAcceso, String sentencia, String rutaRecurso) {
      this.fechaUltimoAcceso = fechaUltimoAcceso;
      this.sentenciaOrigen = sentencia;
      this.rutaRecurso = rutaRecurso;
   }

   public Sentencia(long fechaUltimoAcceso, String rutaRecurso) {
      this.fechaUltimoAcceso = fechaUltimoAcceso;
      this.rutaRecurso = rutaRecurso;
   }

   public long getFechaUltimoAcceso() {
      return this.fechaUltimoAcceso;
   }

   public void setFechaUltimoAcceso(long fechaUltimoAcceso) {
      this.fechaUltimoAcceso = fechaUltimoAcceso;
   }

   public String getRutaRecurso() {
      return this.rutaRecurso;
   }

   public void setRutaRecurso(String rutaRecurso) {
      this.rutaRecurso = rutaRecurso;
   }

   public String getSentenciaOrigen() {
      return this.sentenciaOrigen;
   }

   public void setSentenciaOrigen(String sentencia) {
      this.sentenciaOrigen = sentencia;
   }

   public ArrayList getParametros() {
      return this.parametros;
   }

   public void setParametros(ArrayList parametros) {
      if (parametros != null) {
         this.parametros = parametros;
      } else {
         new ArrayList();
      }

   }

   public String getSentenciaPsmt() {
      return this.sentenciaPsmt;
   }

   public void setSentenciaPsmt(String sentenciaPsmt) {
      this.sentenciaPsmt = sentenciaPsmt;
   }

   public String getNombreProcedimiento() {
      return this.nombreProcedimiento;
   }

   public void setNombreProcedimiento(String nombreProcedimiento) {
      this.nombreProcedimiento = nombreProcedimiento;
   }

   public String getInvocacionProcedimiento() {
      return this.invocacionProcedimiento;
   }

   public void setInvocacionProcedimiento(String invocacionProcedimiento) {
      this.invocacionProcedimiento = invocacionProcedimiento;
   }
}
