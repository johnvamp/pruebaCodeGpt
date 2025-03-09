package co.com.personalsoft.base.paginacion.beans;

import java.io.Serializable;

public class PaginacionDTO implements Serializable {
   private static final long serialVersionUID = -1230963141915466564L;
   private static int POSICIONARSE_EN_LA_PRIMERA_PAGINA = 1;
   private static int POSICIONARSE_EN_LA_PAGINA_ANTERIOR = 2;
   private static int POSICIONARSE_EN_LA_PAGINA_SIGUIENTE = 3;
   private static int POSICIONARSE_EN_LA_PAGINA_ACTUAL = 4;
   private String registroInicial;
   private String registroFinal;
   private int controlAvance;
   private int paginaActual;
   private int registrosPorPagina;
   private long numeroPaginas;
   private long totalRegistros;

   public int getControlAvance() {
      return this.controlAvance;
   }

   public void setControlAvance(int controlAvance) {
      this.controlAvance = controlAvance;
   }

   public String getRegistroFinal() {
      return this.registroFinal;
   }

   public void setRegistroFinal(String registroFinal) {
      this.registroFinal = registroFinal;
   }

   public String getRegistroInicial() {
      return this.registroInicial;
   }

   public void setRegistroInicial(String registroInicial) {
      this.registroInicial = registroInicial;
   }

   public long getTotalRegistros() {
      return this.totalRegistros;
   }

   public void setTotalRegistros(long totalRegistros) {
      this.totalRegistros = totalRegistros;
   }

   public int getRegistrosPorPagina() {
      return this.registrosPorPagina;
   }

   public void setRegistrosPorPagina(int registrosPorPagina) {
      this.registrosPorPagina = registrosPorPagina;
   }

   public int getPaginaActual() {
      return this.paginaActual;
   }

   public void setPaginaActual(int paginaActual) {
      this.paginaActual = paginaActual;
   }

   public long getNumeroPaginas() {
      return this.numeroPaginas;
   }

   public void setNumeroPaginas(long numeroPaginas) {
      this.numeroPaginas = numeroPaginas;
   }

   public PaginacionDTO dtoAssember(PaginacionDTO paginacionDTO) {
      long totalRegistros = paginacionDTO.getTotalRegistros();
      long numPaginas = 0L;
      if (Double.parseDouble(String.valueOf(totalRegistros)) % (double)paginacionDTO.getRegistrosPorPagina() == 0.0D) {
         numPaginas = totalRegistros / (long)paginacionDTO.getRegistrosPorPagina();
      } else {
         numPaginas = totalRegistros / (long)paginacionDTO.getRegistrosPorPagina() + 1L;
      }

      paginacionDTO.setNumeroPaginas(numPaginas);
      if (paginacionDTO.getControlAvance() != 0 && paginacionDTO.getControlAvance() != POSICIONARSE_EN_LA_PRIMERA_PAGINA) {
         if (paginacionDTO.getControlAvance() == POSICIONARSE_EN_LA_PAGINA_ANTERIOR) {
            paginacionDTO.setPaginaActual(paginacionDTO.getPaginaActual() - 1);
         } else if (paginacionDTO.getControlAvance() == POSICIONARSE_EN_LA_PAGINA_SIGUIENTE) {
            paginacionDTO.setPaginaActual(paginacionDTO.getPaginaActual() + 1);
         } else if (paginacionDTO.getControlAvance() == POSICIONARSE_EN_LA_PAGINA_ACTUAL) {
            paginacionDTO.setPaginaActual((int)numPaginas);
         }
      } else {
         paginacionDTO.setPaginaActual(1);
      }

      return paginacionDTO;
   }
}
