package com.sandeep.jfreechart.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.dial.DialBackground;
import org.jfree.chart.plot.dial.DialCap;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialPointer;
import org.jfree.chart.plot.dial.DialValueIndicator;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.StandardDialRange;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.LineRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.ui.TextAnchor;

import co.com.personalsoft.base.excepcion.PersonalsoftException;

import votre.portaloperaciones.indicadordepedidos.beans.IndicadorPedidosDTO;
import votre.portaloperaciones.indicadordepedidos.beans.PedidosPorRangoDTO;
import votre.portaloperaciones.indicadordepedidos.delegate.IndicadorPedidosBusiness;
import votre.portaloperaciones.util.Constantes;




public class JFreeChartServlet extends HttpServlet {
 private static final long serialVersionUID = 1L;



 public JFreeChartServlet() {
  super();
  // TODO Auto-generated constructor stub
 }


 
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
	IndicadorPedidosDTO indicadorPedidosDTO = new IndicadorPedidosDTO();
	
	IndicadorPedidosBusiness indicadorPedidosBusiness;
	try {
		indicadorPedidosBusiness = new IndicadorPedidosBusiness();
		
		String TipGra = request.getParameter("TipGra");	
		String TipGraEsta = request.getParameter("TipGraEsta");
		String TipGraRango = request.getParameter("TipGraRango");
		
		if(TipGraEsta == null){
			TipGraEsta = "";
		}
		
		
		if(TipGra.equals("1")){		
				Double idMax = Double.parseDouble(request.getParameter("Max"));		
				Double idMin = Double.parseDouble(request.getParameter("Min"));
				Double idPro = Double.parseDouble(request.getParameter("Pro"));	
				Double idMed = Double.parseDouble(request.getParameter("Med"));	
				
				int dato1i =  (int)Math.round(idPro);
				int dato2i =  (int)Math.round(idMin);	
				int dato3i =  (int)Math.round(idMax);
				int dato4i =  (int)Math.round(idMed);	
				
				dato1i = Math.round(dato1i);
				dato2i = Math.round(dato2i);
				dato3i = Math.round(dato3i);
				dato4i = Math.round(dato4i);
				
				  response.setContentType("image/png");		
				  ServletOutputStream os = response.getOutputStream();
				  
				  DialPlot dialplot = new DialPlot();
				  dialplot.setDialFrame(new StandardDialFrame());
				  dialplot.addLayer(new DialPointer.Pointer());
		
			        DefaultValueDataset dataset2 = new DefaultValueDataset(dato4i);  
		            dialplot.setDataset(1, dataset2);
			        
			        StandardDialScale standarddialscale1 = new StandardDialScale(dato2i, dato3i,-120, -300, (dato3i - dato2i)/8, 8);
			        standarddialscale1.setTickRadius(0.5D);
			        standarddialscale1.setTickLabelOffset(0.14999999999999999D);
		            standarddialscale1.setTickLabelFont(new Font("Dialog", 0, 10));
		            standarddialscale1.setMajorTickPaint(Color.red); 
		            standarddialscale1.setMinorTickPaint(Color.red);  
		            dialplot.addScale(1, standarddialscale1);                   
		
		            DialValueIndicator dialvalueindicator1 = new DialValueIndicator(1); 
		            dialvalueindicator1.setFont(new Font("Dialog", 0, 10));     
		            dialvalueindicator1.setOutlinePaint(Color.red);              
		            dialvalueindicator1.setRadius(0.8);       
		            dialvalueindicator1.setAngle(-77D);                    
		            dialplot.addLayer(dialvalueindicator1);
		            
		            dialplot.mapDatasetToScale(1, 1);            
		            org.jfree.chart.plot.dial.DialPointer.Pin pin = new org.jfree.chart.plot.dial.DialPointer.Pin(1);  
		            pin.setRadius(0.5);  
		            dialplot.addPointer(pin); 
		            
		            DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
		            dialvalueindicator.setFont(new Font("Dialog", 0, 10)); 
		            dialvalueindicator.setOutlinePaint(Color.darkGray);  
		            dialvalueindicator.setRadius(0.8);     
		            dialvalueindicator.setAngle(-103D);                  
		            dialplot.addLayer(dialvalueindicator);
		            	        
			        DialCap cap = new DialCap();
			        cap.setRadius(0.05);
			        dialplot.setCap(cap);        
				  		  
			      StandardDialRange localStandardDialRange1 = new StandardDialRange(dato2i, dato3i, Color.red);
				  localStandardDialRange1.setInnerRadius(0.52D);       
				  localStandardDialRange1.setOuterRadius(0.55D);  
				  dialplot.addLayer(localStandardDialRange1);
		  
				  GradientPaint localGradientPaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(255, 255, 255));       
				  DialBackground localDialBackground = new DialBackground(localGradientPaint);       
				  localDialBackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));       
				  dialplot.setBackground(localDialBackground); 		  
		     
				  StandardDialScale scale = new StandardDialScale(dato2i, dato3i,-120, -300, (dato3i - dato2i)/8, 8);
				  scale.setTickRadius(0.88);
				  scale.setTickLabelOffset(0.20);
				  dialplot.addScale(0, scale);  
				  
				  DefaultValueDataset dataset1 = new DefaultValueDataset(dato1i);  
				  ValueDataset paramValueDataset = dataset1;	  
				  dialplot.setDataset(paramValueDataset); 	
		
				  JFreeChart chart = new JFreeChart(dialplot);		  
				  int colorCode = 0x00FFFFFF;  
		          chart.setBackgroundPaint(new Color(colorCode)); 		  
				  RenderedImage chartImage = chart.createBufferedImage(350, 350);		  
				  ImageIO.write(chartImage, "png", os);
				  os.flush();
				  os.close(); 				  
		}
		  

		if(TipGra.equals("2")){
			/*
					String codCia = request.getSession().getAttribute( Constantes.COD_CIA ) != null ? ((String)request.getSession().getAttribute( Constantes.COD_CIA )).trim() : "";
					String  idOpcSelect = (String) request.getSession().getAttribute("idOpcSelect");
					String  idCampana = (String) request.getSession().getAttribute("idCampana");
					String  idZona = (String) request.getSession().getAttribute("idZona");
					String  idCampanaZ = (String) request.getSession().getAttribute("idCampanaZ");
					String  idFechaInicial = (String) request.getSession().getAttribute("idFechaInicial");
					String  idFechaFinal = (String) request.getSession().getAttribute("idFechaFinal");
					String  idEstado = (String) request.getSession().getAttribute("idEstado");				
					String  idEstadoInicial = (String) request.getSession().getAttribute("idEstadoIni");
					
					String idEstadoFinal = ""; 
					
					
					if(TipGraEsta.equals("2"))
					{
						idEstadoFinal = ""; 
					}
					else
					{
						idEstadoFinal = (String) request.getSession().getAttribute("idEstadoFin"); 
					}
					
					
					if(idEstado == null){
						idEstado = "";
					}
					
					String idCampanaR = (String) request.getSession().getAttribute("idCampanaR");
					String idRegion = (String) request.getSession().getAttribute("idRegion");	
					
				
					if(idOpcSelect.equals("1")){
						indicadorPedidosDTO.setDato1(codCia);
						indicadorPedidosDTO.setDato2("F");
						indicadorPedidosDTO.setDato3(idEstadoInicial);
						indicadorPedidosDTO.setDato34(idEstadoFinal);
						indicadorPedidosDTO.setDato4(idFechaInicial);
						indicadorPedidosDTO.setDato5(idFechaFinal);	
						indicadorPedidosDTO.setDato6("");
					
						
					}			
					if(idOpcSelect.equals("2")){
						indicadorPedidosDTO.setDato1(codCia);
						indicadorPedidosDTO.setDato2("Z");
						indicadorPedidosDTO.setDato3(idEstadoInicial);
						indicadorPedidosDTO.setDato34(idEstadoFinal);
						indicadorPedidosDTO.setDato4(idCampanaZ);
						indicadorPedidosDTO.setDato5(idZona);
						indicadorPedidosDTO.setDato6("");
					}			
					if(idOpcSelect.equals("3")){
						indicadorPedidosDTO.setDato1(codCia);
						indicadorPedidosDTO.setDato2("C");
						indicadorPedidosDTO.setDato3(idEstadoInicial);
						indicadorPedidosDTO.setDato34(idEstadoFinal);;
						indicadorPedidosDTO.setDato4(idCampana);
						indicadorPedidosDTO.setDato5("");
						indicadorPedidosDTO.setDato6("");
					}			
					if(idOpcSelect.equals("4")){
						indicadorPedidosDTO.setDato1(codCia);
						indicadorPedidosDTO.setDato2("R");
						indicadorPedidosDTO.setDato3(idEstadoInicial);
						indicadorPedidosDTO.setDato34(idEstadoFinal);	
						indicadorPedidosDTO.setDato4(idCampanaR);
						indicadorPedidosDTO.setDato5(idRegion);
						indicadorPedidosDTO.setDato6("");
					}
		
					ArrayList<IndicadorPedidosDTO>  indicadorPedidosGrafica = new ArrayList<IndicadorPedidosDTO>();
					indicadorPedidosGrafica = indicadorPedidosBusiness.IndicadorPedidosGraf1(indicadorPedidosDTO);
					
			 	*/
					 
					  ArrayList<IndicadorPedidosDTO>  indicadorPedidosGrafica = new ArrayList<IndicadorPedidosDTO>();
					//  indicadorPedidosGrafica = (ArrayList<IndicadorPedidosDTO>) request.getSession().getAttribute("indicadorPedidosGrafica");
			            indicadorPedidosGrafica = (ArrayList<IndicadorPedidosDTO>) request.getSession().getAttribute("indicadorPedidosEstado");
					  
					  
					
					  response.setContentType("image/png");			
					  ServletOutputStream os = response.getOutputStream();			
					  DefaultCategoryDataset dataset = new DefaultCategoryDataset();			  
					  for(IndicadorPedidosDTO indicadorPedidosDTO1 : indicadorPedidosGrafica)
					  	{	
						  if (indicadorPedidosDTO1.getCol1Graf1() != null){
							  if (indicadorPedidosDTO1.getCol2Graf1() != null){
								  dataset.addValue(indicadorPedidosDTO1.getCol2Graf1(), "Pedidos", indicadorPedidosDTO1.getCol1Graf1()); 
							  }					 	
						  }			  
						} 
					 
					  
					  String tipoDato = indicadorPedidosDTO.getDato33();
			  		   JFreeChart chart = ChartFactory.createBarChart3D("",tipoDato,"Pedidos", dataset,  PlotOrientation.VERTICAL, true, true, false);
			       	  
			  		   CategoryPlot cp = chart.getCategoryPlot();  
					   NumberAxis rangeAxis = (NumberAxis) cp.getRangeAxis();  
					   rangeAxis.setUpperMargin(0.1);  
		
					   CategoryPlot plot = chart.getCategoryPlot();
					   BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();
					   CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
					   renderer.setSeriesItemLabelGenerator(0, generator);
					   renderer.setSeriesItemLabelsVisible(0, true);
					   renderer.setSeriesPositiveItemLabelPosition(0, new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12,TextAnchor.BASELINE_CENTER));
					   renderer.setItemLabelAnchorOffset(15);
								   
			    	   cp.setBackgroundPaint(Color.lightGray);  
			           cp.setDomainGridlinePaint(Color.black);  
			           cp.setDomainGridlinesVisible(true);  
			           cp.setRangeGridlinePaint(Color.white);  
			     
			           int colorCode = 0x00FFFFFF;  
			           chart.setBackgroundPaint(new Color(colorCode));  
			           chart.getTitle().setPaint(Color.black);  
			           CategoryPlot p = chart.getCategoryPlot();  
			           p.setRangeGridlinePaint(Color.black);			
			                    
			           
			           final CategoryItemRenderer renderer1 = cp.getRenderer();
			           renderer1.setSeriesPaint(0, Color.BLUE);
			           
			           
			           
			           plot.setDataset(1, dataset);
			           //plot.mapDatasetToRangeAxis(1, 1);
			           //ValueAxis axis2 = new NumberAxis("");
			           //plot.setRangeAxis(1, axis2);
			          // plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);   
			           LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
			           renderer2.setSeriesPaint(0, Color.RED);				   
			           plot.setRenderer(1, renderer1);
			           plot.setRenderer(0, renderer2);	
			           
			           
			           
			           RenderedImage chartImage = chart.createBufferedImage(600, 400);
			           ImageIO.write(chartImage, "png", os);
			           os.flush();
			           os.close();	        
				         
					           
		}
		
		if(TipGra.equals("3")){
			
						Double idMax = Double.parseDouble(request.getParameter("Max"));		
						Double idMin = Double.parseDouble(request.getParameter("Min"));
						Double idPro = Double.parseDouble(request.getParameter("Pro"));				
						int dato1i =  (int)Math.round(idPro);
						int dato2i =  (int)Math.round(idMin);	
						int dato3i =  (int)Math.round(idMax);			
						dato1i = Math.round(dato1i);
						dato2i = Math.round(dato2i);
						dato3i = Math.round(dato3i);
						
						  response.setContentType("image/png");			
						  ServletOutputStream os = response.getOutputStream();		  
						  DialPlot dialplot = new DialPlot();
						  dialplot.setDialFrame(new StandardDialFrame());
			
						  	  
						    DialValueIndicator dvi = new DialValueIndicator(0);
					        dvi.setFont(new Font("Dialog", Font.PLAIN, 15));
					        dvi.setOutlinePaint(Color.darkGray);
					        dialplot.addLayer(dvi);
					        dialplot.addLayer(new DialPointer.Pointer());		  
					        DialPointer needle = new DialPointer.Pointer(1);
					        dialplot.addLayer(needle);		        
					       
					      // Inicio Circulo Indicador   
					        DialCap cap = new DialCap();
					        cap.setRadius(0.05);
					        dialplot.setCap(cap);
					      // Fin Circulo Indicador  
					  
						  GradientPaint localGradientPaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(255, 255, 255));       
						  DialBackground localDialBackground = new DialBackground(localGradientPaint);       
						  localDialBackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));       
						  dialplot.setBackground(localDialBackground); 		
				     
						  int escala =  (dato3i - dato2i)/8;
						  if (escala < 1)
						  {
							  escala = 1;
						  }
						  
						  StandardDialScale scale = new StandardDialScale(dato2i, dato3i,-120, -300,   escala, 8);
						  scale.setMajorTickIncrement((scale.getUpperBound()-scale.getLowerBound())/8); 		  
						  scale.setTickRadius(0.88);
						  scale.setTickLabelOffset(0.20);
						  dialplot.addScale(0, scale);  
						  
						  DefaultValueDataset dataset1 = new DefaultValueDataset(dato1i);  
						  ValueDataset paramValueDataset = dataset1;	  
						  dialplot.setDataset(paramValueDataset);
						  JFreeChart chart = new JFreeChart(dialplot);			  
						  int colorCode = 0x00FFFFFF;  
				          chart.setBackgroundPaint(new Color(colorCode)); 			  
						  RenderedImage chartImage = chart.createBufferedImage(250, 250);
						  ImageIO.write(chartImage, "png", os);		  
						  os.flush();
						  os.close(); 
			
			
		}
		

		
		
		if(TipGra.equals("4")){			
					
			  ArrayList<PedidosPorRangoDTO>  pedidosPorRango = new ArrayList<PedidosPorRangoDTO>();
			  pedidosPorRango = (ArrayList<PedidosPorRangoDTO>) request.getSession().getAttribute("pedidosPorRango");
			 
			  response.setContentType("image/png");			
			  ServletOutputStream os = response.getOutputStream();			
			  DefaultCategoryDataset dataset = new DefaultCategoryDataset();	
			  String tipoDato ="";	
			//  String estadoI ="";
			//  String estadoF ="";
			  
			  for(PedidosPorRangoDTO pedidosPorRangoDTO : pedidosPorRango)
			  	{	
				  if (pedidosPorRangoDTO.getDato19().equals(TipGraRango)){
						  dataset.addValue(pedidosPorRangoDTO.getDato11(), "Pedidos", pedidosPorRangoDTO.getDato12()); 
						   tipoDato = pedidosPorRangoDTO.getDato17();
 	
				  }			  
				} 			  
			   
	  		   JFreeChart chart = ChartFactory.createBarChart3D("",tipoDato,"Pedidos", dataset,  PlotOrientation.VERTICAL, true, true, false);
	       	  
	  		   CategoryPlot cp = chart.getCategoryPlot();  
			   NumberAxis rangeAxis = (NumberAxis) cp.getRangeAxis();  
			   rangeAxis.setUpperMargin(0.1);  

			   CategoryPlot plot = chart.getCategoryPlot();
			   BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();
			   CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
			   renderer.setSeriesItemLabelGenerator(0, generator);
			   renderer.setSeriesItemLabelsVisible(0, true);
			   renderer.setSeriesPositiveItemLabelPosition(0, new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12,TextAnchor.BASELINE_CENTER));
			   renderer.setItemLabelAnchorOffset(15);
						   
	    	   cp.setBackgroundPaint(Color.lightGray);  
	           cp.setDomainGridlinePaint(Color.black);  
	           cp.setDomainGridlinesVisible(true);  
	           cp.setRangeGridlinePaint(Color.white);  
	     
	           int colorCode = 0x00FFFFFF;  
	           chart.setBackgroundPaint(new Color(colorCode));  
	           chart.getTitle().setPaint(Color.black);  
	           CategoryPlot p = chart.getCategoryPlot();  
	           p.setRangeGridlinePaint(Color.black);			
	                    
	           
	           final CategoryItemRenderer renderer1 = cp.getRenderer();
	           renderer1.setSeriesPaint(0, Color.BLUE);
	           
	           
	           
	           plot.setDataset(1, dataset);
	           //plot.mapDatasetToRangeAxis(1, 1);
	           //ValueAxis axis2 = new NumberAxis("");
	           //plot.setRangeAxis(1, axis2);
	          // plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);   
	           LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
	           renderer2.setSeriesPaint(0, Color.RED);				   
	           plot.setRenderer(1, renderer1);
	           plot.setRenderer(0, renderer2);	
	           
	           
	           
	           RenderedImage chartImage = chart.createBufferedImage(500, 300);
	           ImageIO.write(chartImage, "png", os);
	           os.flush();
	           os.close();	


		         
			           
			}
		
		
		
	} catch (PersonalsoftException e) {
		// TODO Bloque catch generado autom�ticamente
		e.printStackTrace();
	}   
 }
 
 ////////
 
 
 
 


/////////
/**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
  *      response)
  */
 protected void doPost(HttpServletRequest request,
   HttpServletResponse response) throws ServletException, IOException {

 }

}
