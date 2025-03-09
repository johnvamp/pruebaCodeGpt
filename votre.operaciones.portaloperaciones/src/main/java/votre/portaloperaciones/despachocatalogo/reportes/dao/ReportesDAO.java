package votre.portaloperaciones.despachocatalogo.reportes.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.config.Configuracion;
import co.com.personalsoft.base.excepcion.PersonalsoftException;
import co.com.personalsoft.base.util.CargadorMsj;
import votre.portaloperaciones.despachocatalogo.reportes.beans.DetalleReportesDTO;
import votre.portaloperaciones.despachocatalogo.reportes.beans.InformacionDTO;
import votre.portaloperaciones.despachocatalogo.reportes.beans.ReportesDTO;
import votre.portaloperaciones.util.Constantes;

public class ReportesDAO implements IReportesDAO {
	
	private BDHelper bdHelper;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public ReportesDAO(BDHelper bdhelper){
		super();		
		this.bdHelper = bdhelper;
	}

	public ReportesDTO generarExcel(ReportesDTO reporte) throws PersonalsoftException {
		ReportesDTO obtenido = null;
		DetalleReportesDTO[] detalle = null;
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<DetalleReportesDTO> arrDetalle = new ArrayList<DetalleReportesDTO>();
		
		PersonalsoftException ps = null;
		parametros.add(new Parametro(Types.VARCHAR, reporte.getCodCia(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.DECIMAL, new Integer( reporte.getCodigoLabel() ), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, reporte.getAccion(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, reporte.getUsuario(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, reporte.getZona(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, reporte.getCampana(), Parametro.IN_OUT));		
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT)); //titulo Obs
		if(Constantes.isLeocomus(reporte.getCodCia())) {
			parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT)); //titulo Email
			parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT)); //titulo Fecha inscripcion
			parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT)); //titulo Idioma
		}
		else{
			parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		}
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_GENERAR_EXCEL");
	    if(Constantes.isLeocomus(reporte.getCodCia())) {
				rutaSQL = Configuracion.getInstance().getParametroApp("SP_GENERAR_EXCEL_USA");
			}
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if (cs != null) {
				cs.execute();
				
				ResultSet rs = cs.getResultSet();
				
				obtenido = new ReportesDTO();
				obtenido.setCodCia(cs.getString(1).trim());
				obtenido.setCodigoLabel(cs.getString(2).trim());
				obtenido.setAccion(cs.getString(3).trim());				
				obtenido.setUsuario(cs.getString(4).trim());
				obtenido.setTitulo(cs.getString(5).trim());
				obtenido.setTituloNombre(cs.getString(6).trim());
				obtenido.setTituloDireccion(cs.getString(7).trim());
				obtenido.setTituloCiudad(cs.getString(8).trim());
				obtenido.setTituloDepartamento(cs.getString(9).trim());
				obtenido.setTituloCodPostal(cs.getString(10).trim());
				obtenido.setTituloTelefono1(cs.getString(11).trim());
				obtenido.setTituloBarrio(cs.getString(12).trim());
				obtenido.setTituloTelefono2(cs.getString(13).trim());
				obtenido.setEstado(cs.getString(14).trim());
				obtenido.setDescripcion(cs.getString(15).trim());
				obtenido.setTituloCedula(cs.getString(16).trim());				
				obtenido.setTituloNroCatalogos(cs.getString(17).trim());
				obtenido.setTituloTipo(cs.getString(18).trim());
				obtenido.setTituloZona(cs.getString(19).trim());
				obtenido.setTituloMailPlan(cs.getString(20).trim());
				obtenido.setZona(cs.getString(21).trim());
				obtenido.setTituloTipoCompra(cs.getString(22).trim());
				obtenido.setCampana(cs.getString(23).trim());
				obtenido.setTituloReferencia(cs.getString(24).trim());
				obtenido.setTituloCantidad(cs.getString(25).trim());
				if(reporte.getCodigoLabel().equals(Constantes.COD_LABEL_ENVIO)){
					obtenido.setTituloObservacion(cs.getString(26) != null ? cs.getString(26).trim() : "");
				  if(Constantes.isLeocomus(reporte.getCodCia())) {
						obtenido.setTituloEmail(cs.getString(27) != null ? cs.getString(27).trim() : "");
						obtenido.setTituloFechaInscripcion(cs.getString(28) != null ? cs.getString(28).trim() : "");
						obtenido.setTituloIdioma(cs.getString(29) != null ? cs.getString(29).trim() : "");
					}
				}
				obtenido.setTituloCelular(cs.getString(27) != null ? cs.getString(27).trim() : "");
				
				if(rs != null){
					while (rs.next()) {
						DetalleReportesDTO detalleReporte = new DetalleReportesDTO();
						if(obtenido.getCodCia().equals(Constantes.CHILE) && (obtenido.getCodigoLabel().equals("2") || obtenido.getCodigoLabel().equals("3"))){
							detalleReporte.setCedula(rs.getString(1).trim().equals("")?"-":rs.getString(1).trim());
							detalleReporte.setNombre(rs.getString(2).trim().equals("")?"-":rs.getString(2).trim());
							detalleReporte.setDireccion(rs.getString(3).trim().equals("")?"-":rs.getString(3).trim());
							detalleReporte.setTelefono1(rs.getString(4).trim().equals("")?"-":rs.getString(4).trim());
							detalleReporte.setTelefono2(rs.getString(5).trim().equals("")?"-":rs.getString(5).trim());
							detalleReporte.setBarrio(rs.getString(6).trim().equals("")?"-":rs.getString(6).trim());
							detalleReporte.setCiudad(rs.getString(7).trim().equals("")?"-":rs.getString(7).trim());
							detalleReporte.setDepartamento(rs.getString(8).trim().equals("")?"-":rs.getString(8).trim());
							detalleReporte.setCodPostal(rs.getString(9).trim().equals("")?"-":rs.getString(9).trim());
							detalleReporte.setNroCatalogos(rs.getString(10).trim().equals("")?"-":rs.getString(10).trim());
							detalleReporte.setTipo(rs.getString(11).trim().equals("")?"-":rs.getString(11).trim());
							arrDetalle.add(detalleReporte);
						}
						else{
							detalleReporte.setCedula(rs.getString(1).trim().equals("")?"-":rs.getString(1).trim());
							detalleReporte.setNombre(rs.getString(2).trim().equals("")?"-":rs.getString(2).trim());
							detalleReporte.setDireccion(rs.getString(3).trim().equals("")?"-":rs.getString(3).trim());
							detalleReporte.setTelefono1(rs.getString(4).trim().equals("")?"-":rs.getString(4).trim());
							detalleReporte.setTelefono2(rs.getString(5).trim().equals("")?"-":rs.getString(5).trim());
							detalleReporte.setBarrio(rs.getString(6).trim().equals("")?"-":rs.getString(6).trim());
							detalleReporte.setCiudad(rs.getString(7).trim().equals("")?"-":rs.getString(7).trim());
							detalleReporte.setDepartamento(rs.getString(8).trim().equals("")?"-":rs.getString(8).trim());
							detalleReporte.setCodPostal(rs.getString(9).trim().equals("")?"-":rs.getString(9).trim());
							detalleReporte.setNroCatalogos(rs.getString(10).trim().equals("")?"-":rs.getString(10).trim());
							detalleReporte.setTipo(rs.getString(11).trim().equals("")?"-":rs.getString(11).trim());
							detalleReporte.setZona(rs.getString(12).trim().equals("")?"-":rs.getString(12).trim());
							detalleReporte.setMailPlan(rs.getString(13).trim().equals("")?"-":rs.getString(13).trim());
							detalleReporte.setTipoCompra(rs.getString(14).trim().equals("")?"-":rs.getString(14).trim());
							if(reporte.getCodigoLabel().equals(Constantes.COD_LABEL_ENVIO) || reporte.getCodigoLabel().equals(Constantes.COD_LABEL_CORTE_CATALOGOS)){
								detalleReporte.setObservacion(rs.getString(15).trim().equals("")?"-":rs.getString(15).trim());
						    if(Constantes.isLeocomus(reporte.getCodCia())) {
									detalleReporte.setEmail(rs.getString(16).trim().equals("")?"-":rs.getString(16).trim());
									detalleReporte.setFechaInscripcion(rs.getString(17).trim().equals("")?"-":rs.getString(17).trim());
									detalleReporte.setIdioma(rs.getString(18).trim().equals("")?"-":rs.getString(18).trim());
									detalleReporte.setCelular(rs.getString(19).trim().equals("")?"-":rs.getString(19).trim());
								}
								else{
									detalleReporte.setCelular(rs.getString(18).trim().equals("")?"-":rs.getString(18).trim());
								}
							}
//							else if(reporte.getCodigoLabel().equals(Constantes.COD_LABEL_CORTE_CATALOGOS)){
//								detalleReporte.setCelular(rs.getString(18).trim().equals("")?"-":rs.getString(18).trim());
//							}
							else if (reporte.getCodigoLabel().equals(Constantes.COD_LABEL_COMPRADORAS_ACTIVAS)) {
								detalleReporte.setCelular(rs.getString(15) != null ? rs.getString(15).trim() : "");
							}
							arrDetalle.add(detalleReporte);
						}						
					}
				}
				
				detalle = arrDetalle.toArray(new DetalleReportesDTO[arrDetalle.size()]);
				obtenido.setDetalle(detalle);
			}
		}
		catch (SQLException e) {
			ps = new PersonalsoftException(e);
			ps.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("errorGeneral"));
			logger.error(ps.getTraza());
			throw ps;
		} finally {
			try {
				BDHelper.close(cs);
			} catch (SQLException e) {
				ps = new PersonalsoftException(e);
				ps.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("errorGeneral"));
				logger.error(ps.getTraza());
				throw ps;
			}
		}
		
		return obtenido;
	}

	public InformacionDTO consultar(InformacionDTO informacion) throws PersonalsoftException {
		InformacionDTO obtenido = null;
		DetalleReportesDTO[] detalle = null;
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<DetalleReportesDTO> arrDetalle = new ArrayList<DetalleReportesDTO>();
		int pos =0;
		
		PersonalsoftException ps = null;
		parametros.add(new Parametro(Types.VARCHAR, informacion.getCodCia(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.DECIMAL, new Integer( informacion.getCodigoLabel() ), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, informacion.getAccion(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, informacion.getFechaIni(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, informacion.getFechaFin(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, informacion.getUsuario(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, informacion.getZona(), Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "",Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, informacion.getCampana(), Parametro.IN_OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_INFO_EXCEL");
			if( Constantes.CODCIA_USA.equals(informacion.getCodCia()) || Constantes.CODCIA_PUERTORICO.equals(informacion.getCodCia()) ){
				rutaSQL = Configuracion.getInstance().getParametroApp("SP_INFO_EXCEL_USA");
			}
			else if(Constantes.CODCIA_MEXICO.equals(informacion.getCodCia()) || Constantes.CODCIA_PANAMA.equals(informacion.getCodCia()) || 
			  Constantes.CODCIA_ESPANA.equals(informacion.getCodCia())){
				rutaSQL = Configuracion.getInstance().getParametroApp("SP_DETACATLOGPN");				
			}
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			if (cs != null) {
				cs.execute();
				
				ResultSet rs = cs.getResultSet();
				
				obtenido = new InformacionDTO();
				obtenido.setCodCia(cs.getString(1).trim());
				obtenido.setCodigoLabel(cs.getString(2).trim());
				obtenido.setAccion(cs.getString(3).trim());
				obtenido.setFechaIni(cs.getString(4).trim());
				obtenido.setFechaFin(cs.getString(5).trim());
				obtenido.setUsuario(cs.getString(6).trim());
				obtenido.setTitulo(cs.getString(7).trim());
				obtenido.setTituloNombre(cs.getString(8).trim());
				obtenido.setTituloDireccion(cs.getString(9).trim());
				obtenido.setTituloCiudad(cs.getString(10).trim());
				obtenido.setTituloDepartamento(cs.getString(11).trim());
				obtenido.setTituloCodPostal(cs.getString(12).trim());
				obtenido.setEstado(cs.getString(14).trim());
				obtenido.setDescripcion(cs.getString(15).trim());
				obtenido.setTituloTelefono1(cs.getString(16).trim());
				obtenido.setTituloCedula(cs.getString(17).trim());
				obtenido.setTituloTelefono2(cs.getString(18).trim());
				obtenido.setTituloBarrio(cs.getString(19).trim());							
				obtenido.setTituloNroCatalogos(cs.getString(20).trim());				
				obtenido.setTituloZona(cs.getString(22).trim());
				obtenido.setTituloMailPlan(cs.getString(23).trim());
				obtenido.setZona(cs.getString(24).trim());
				obtenido.setTituloTipoCompra(cs.getString(25).trim());
				obtenido.setCampana(cs.getString(26).trim());
				
				if(rs != null){
					while (rs.next()) {
						DetalleReportesDTO detalleReporte = new DetalleReportesDTO();
						if(obtenido.getCodCia().equals(Constantes.CHILE) && (obtenido.getCodigoLabel().equals("2") || obtenido.getCodigoLabel().equals("3"))){
								detalleReporte.setCedula(rs.getString(1).trim());
								detalleReporte.setNombre(rs.getString(2).trim());
								detalleReporte.setDireccion(rs.getString(3).trim());
								detalleReporte.setTelefono1(rs.getString(4).trim());
								detalleReporte.setTelefono2(rs.getString(5).trim());
								detalleReporte.setBarrio(rs.getString(6).trim());
								detalleReporte.setCiudad(rs.getString(7).trim());
								detalleReporte.setDepartamento(rs.getString(8).trim());
								detalleReporte.setCodPostal(rs.getString(9).trim());
								detalleReporte.setNroCatalogos(rs.getString(10).trim());
								detalleReporte.setTipo(rs.getString(11).trim());
								arrDetalle.add(detalleReporte);
						}
						else{
							detalleReporte.setCedula(rs.getString(1).trim());
							detalleReporte.setNombre(rs.getString(2).trim());
							detalleReporte.setDireccion(rs.getString(3).trim());
							detalleReporte.setTelefono1(rs.getString(4).trim());
							detalleReporte.setTelefono2(rs.getString(5).trim());
							detalleReporte.setBarrio(rs.getString(6).trim());
							detalleReporte.setCiudad(rs.getString(7).trim());
							detalleReporte.setDepartamento(rs.getString(8).trim());
							detalleReporte.setCodPostal(rs.getString(9).trim());
							detalleReporte.setNroCatalogos(rs.getString(10).trim());
							detalleReporte.setTipo(rs.getString(11).trim());						
							detalleReporte.setZona(rs.getString(12).trim());
							detalleReporte.setMailPlan(rs.getString(13).trim());
							detalleReporte.setTipoCompra(rs.getString(14).trim());
							arrDetalle.add(detalleReporte);
						}
					}
				}
				pos = 0;
				detalle = new DetalleReportesDTO[arrDetalle.size()];
				for (Iterator<DetalleReportesDTO> iter = arrDetalle.iterator(); iter.hasNext();){
					DetalleReportesDTO detalleReportesDTO = (DetalleReportesDTO) iter.next();
					detalle[pos] = detalleReportesDTO;
					++pos;
				}
				obtenido.setDetalle(detalle);
			}
		}
		catch (SQLException e) {
			ps = new PersonalsoftException(e);
			ps.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("errorGeneral"));
			logger.error(ps.getTraza());
			throw ps;
		} finally {
			try {
				BDHelper.close(cs);
			} catch (SQLException e) {
				ps = new PersonalsoftException(e);
				ps.setMensajeUsuario(CargadorMsj.getInstance().getMensaje("errorGeneral"));
				logger.error(ps.getTraza());
				throw ps;
			}
		}
		
		return obtenido;
	}
}
