package votre.portaloperaciones.solicitudcatalogo.compradora.dao;

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
import votre.portaloperaciones.solicitudcatalogo.catalogo.beans.CatalogoDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.beans.CatalogoCompradoraDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.beans.CompradoraDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.beans.EnviosDTO;
import votre.portaloperaciones.solicitudcatalogo.compradora.beans.RegistrosConsultaDTO;
import votre.portaloperaciones.util.Constantes;

public class CompradoraDAO implements ICompradoraDAO {

	private BDHelper bdHelper;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public CompradoraDAO(BDHelper bdHelper) {
		super();
		this.bdHelper = bdHelper;
	}

	public CompradoraDTO verInformacionCompradora(CatalogoDTO catalogo) throws PersonalsoftException {
		CompradoraDTO obtenido = null;		
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, catalogo.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.DECIMAL, new Integer( catalogo.getCedula() ),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.DECIMAL, "", Parametro.OUT));
		parametros.add(new Parametro(Types.DECIMAL, "", Parametro.OUT));
		parametros.add(new Parametro(Types.DECIMAL, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, catalogo.getNroCaso(),Parametro.IN_OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_INFO_COMPRADORA");
      if (Constantes.isLeocomus(catalogo.getCodCia())) {
				rutaSQL = Configuracion.getInstance().getParametroApp("SP_INFO_COMPRADO_USA");
			}
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if (cs != null) {
				cs.execute();
				
				obtenido = new CompradoraDTO();
				obtenido.setCodCia(cs.getString(1).trim());
				obtenido.setCedula(cs.getString(2).trim());
				obtenido.setTitulo1(cs.getString(3).trim());
				obtenido.setTitulo2(cs.getString(4).trim());
				obtenido.setTitulo3(cs.getString(5).trim());
				obtenido.setTitulo4(cs.getString(6).trim());
				obtenido.setTitulo5(cs.getString(7).trim());
				obtenido.setTitulo6(cs.getString(8).trim());
				obtenido.setTitulo7(cs.getString(9).trim());
				obtenido.setTitulo8(cs.getString(10).trim());
				obtenido.setTitulo9(cs.getString(11).trim());
				obtenido.setTitulo10(cs.getString(12).trim());
				obtenido.setTitulo11(cs.getString(13).trim());
				obtenido.setTitulo12(cs.getString(14).trim());
				obtenido.setTitulo13(cs.getString(15).trim());
				obtenido.setNombreCompradora(cs.getString(16).trim());
				obtenido.setDireccion(cs.getString(17).trim());
				obtenido.setBarrio(cs.getString(18).trim());
				obtenido.setMunicipio(cs.getString(19).trim());
				obtenido.setCodigoPostal(cs.getString(20).trim());
				obtenido.setTelefono1(cs.getString(21).trim());
				obtenido.setTelefono2(cs.getString(22).trim());
				obtenido.setCelular(cs.getString(23).trim());
				obtenido.setDepartamento(cs.getString(24).trim());
				obtenido.setPais(cs.getString(25).trim());
				obtenido.setNroCaso(cs.getString(28) != null ? (cs.getString(28).trim()) : "" );
				
				obtenido.setEstado(cs.getString(26).trim());
				obtenido.setDescripcion(cs.getString(27).trim());
				
			}
			
		} catch (SQLException e) {
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

	public CompradoraDTO verListadoCompradoras(CompradoraDTO compradora) throws PersonalsoftException {
		CompradoraDTO obtenido = null;
		RegistrosConsultaDTO[] registrosConsulta = null;
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<RegistrosConsultaDTO> arrRegistros = new ArrayList<RegistrosConsultaDTO>();
		int pos=0;
		
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, compradora.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, compradora.getNombre(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_LIST_COMPRADORA");
      if (Constantes.isLeocomus(compradora.getCodCia())) {
				rutaSQL = Configuracion.getInstance().getParametroApp("SP_LIST_COMPRADO_USA");
			}
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if (cs != null) {
				cs.execute();
				
				ResultSet rs = cs.getResultSet();
				
				obtenido = new CompradoraDTO();
				obtenido.setTituloPais(cs.getString(3).trim());
				obtenido.setTituloTipo(cs.getString(4).trim());
				obtenido.setTituloCedula(cs.getString(5).trim());
				obtenido.setTituloNombre(cs.getString(6).trim());
				obtenido.setTituloTelefono(cs.getString(7).trim());
				obtenido.setTituloCaso(cs.getString(8).trim());				
				obtenido.setEstado(cs.getString(9).trim());
				obtenido.setDescripcion(cs.getString(10).trim());
				
				if (rs != null) {
					while (rs.next()) {
						RegistrosConsultaDTO registros = new RegistrosConsultaDTO();
						registros.setPais(rs.getString(1).trim());
						registros.setTipo(rs.getString(2).trim());
						registros.setCedula(rs.getString(3).trim());
						registros.setNombreCompradora(rs.getString(4).trim());
						registros.setTelefono(rs.getString(5).trim());
						registros.setNroCaso(rs.getString(6).trim());
						
						arrRegistros.add(registros);
					}
				}
				
				pos = 0;
				registrosConsulta = new RegistrosConsultaDTO[arrRegistros.size()];
				for (Iterator<RegistrosConsultaDTO> iter = arrRegistros.iterator(); iter.hasNext();){
					RegistrosConsultaDTO registrosConsultaDTO = (RegistrosConsultaDTO) iter.next();
					registrosConsulta[pos] = registrosConsultaDTO;
					++pos;
				}
				obtenido.setRegistros(registrosConsulta);
			}
			
		} catch (SQLException e) {
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

	public CatalogoCompradoraDTO consultarCatalogos(String codCia) throws PersonalsoftException {
		CatalogoCompradoraDTO obtenido = null;
		RegistrosConsultaDTO[] registrosConsulta = null;
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<RegistrosConsultaDTO> arrRegistros = new ArrayList<RegistrosConsultaDTO>();
		int pos =0;
		
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, codCia,Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_CATALOGCAMP");
      if (Constantes.isLeocomus(codCia)) {
				rutaSQL = Configuracion.getInstance().getParametroApp("SP_CATALOGCAMP_USA");
			}
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if (cs != null) {
				cs.execute();
				
				obtenido = new CatalogoCompradoraDTO();
				obtenido.setCodCia(cs.getString(1).trim());
				obtenido.setTitulo1(cs.getString(2).trim());
				obtenido.setTitulo2(cs.getString(3).trim());
				obtenido.setTitulo3(cs.getString(4).trim());
				obtenido.setTitulo4(cs.getString(5).trim());
				obtenido.setBoton(cs.getString(6).trim());				
				obtenido.setEstado(cs.getString(7).trim());
				obtenido.setDescripcion(cs.getString(8).trim());
				
				ResultSet rs = cs.getResultSet();	
				if (rs != null) {
					while (rs.next()) {
						RegistrosConsultaDTO registrosConsultaDTO = new RegistrosConsultaDTO();
						registrosConsultaDTO.setSku(rs.getString(1).trim());
						registrosConsultaDTO.setDescripcion(rs.getString(2).trim());
						registrosConsultaDTO.setCantidad(rs.getString(3).trim());
						arrRegistros.add(registrosConsultaDTO);
					}
				}
				pos = 0;
				registrosConsulta = new RegistrosConsultaDTO[arrRegistros.size()];
				for (Iterator<RegistrosConsultaDTO> iter = arrRegistros.iterator(); iter.hasNext();){
					RegistrosConsultaDTO registrosConsultaDTO = (RegistrosConsultaDTO) iter.next();
					registrosConsulta[pos] = registrosConsultaDTO;
					++pos;
				}
				obtenido.setRegistros(registrosConsulta);
			}
			
			
		} catch (SQLException e) {
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

	public EnviosDTO verResumenEnvios(CompradoraDTO compradora) throws PersonalsoftException {
		EnviosDTO obtenido = null;
		RegistrosConsultaDTO[] registrosConsulta = null;
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		ArrayList<RegistrosConsultaDTO> arrRegistros = new ArrayList<RegistrosConsultaDTO>();
		int pos =0;
		
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, compradora.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.DECIMAL, new Integer( compradora.getCedula() ),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, compradora.getNroCaso(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_HISTORIACOMPRA");
      if (Constantes.isLeocomus(compradora.getCodCia())) {
				rutaSQL = Configuracion.getInstance().getParametroApp("SP_HISTORIACOMP_USA");
			}
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if (cs != null) {
				cs.execute();
				
				obtenido = new EnviosDTO();
				obtenido.setCodCia(cs.getString(1).trim());
				obtenido.setCedula(cs.getString(2).trim());
				obtenido.setNroCaso(cs.getString(3).trim());
				obtenido.setTitulo1(cs.getString(4).trim());
				obtenido.setTitulo2(cs.getString(5).trim());
				obtenido.setTitulo3(cs.getString(6).trim());
				obtenido.setTitulo4(cs.getString(7).trim());
				obtenido.setTitulo5(cs.getString(8).trim());
				obtenido.setTitulo6(cs.getString(9).trim());
				obtenido.setTitulo7(cs.getString(10).trim());
				obtenido.setEstado(cs.getString(11).trim());
				obtenido.setDescripcion(cs.getString(12).trim());
				
				ResultSet rs = cs.getResultSet();	
				if (rs != null) {
					while (rs.next()) {
						RegistrosConsultaDTO registrosConsultaDTO = new RegistrosConsultaDTO();
						registrosConsultaDTO.setCampana(rs.getString(1).trim());
						registrosConsultaDTO.setLineaGrabacion(rs.getString(2).trim());
						registrosConsultaDTO.setSku(rs.getString(3).trim());
						registrosConsultaDTO.setDescripcion(rs.getString(4).trim());							
						registrosConsultaDTO.setCantidad(rs.getString(5).trim());
						registrosConsultaDTO.setNroCaso(rs.getString(6).trim());
						registrosConsultaDTO.setBtnEliminar(rs.getString(7).trim());
						arrRegistros.add(registrosConsultaDTO);
					}
				}
				pos = 0;
				registrosConsulta = new RegistrosConsultaDTO[arrRegistros.size()];
				for (Iterator<RegistrosConsultaDTO> iter = arrRegistros.iterator(); iter.hasNext();){
					RegistrosConsultaDTO registrosConsultaDTO = (RegistrosConsultaDTO) iter.next();
					registrosConsulta[pos] = registrosConsultaDTO;
					++pos;
				}
				obtenido.setRegistros(registrosConsulta);
			}
			
		} catch (SQLException e) {
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

	public CompradoraDTO guardar(CompradoraDTO compradora) throws PersonalsoftException {
		CompradoraDTO obtenido = null;		
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, compradora.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.DECIMAL, new Integer( compradora.getCedula() ),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, compradora.getNombre(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, compradora.getDireccion(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, compradora.getBarrio(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, compradora.getMunicipio(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, compradora.getCodigoPostal(),Parametro.IN));
		parametros.add(new Parametro(Types.DECIMAL, compradora.getTelefono1(),Parametro.IN));
		parametros.add(new Parametro(Types.DECIMAL, compradora.getTelefono2(),Parametro.IN));
		parametros.add(new Parametro(Types.DECIMAL, compradora.getCelular(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, compradora.getDepartamento(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, compradora.getSku(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, compradora.getDescripRefer(),Parametro.IN));
		parametros.add(new Parametro(Types.DECIMAL, compradora.getCantidad(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, compradora.getAccion() ,Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, compradora.getUsuario(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, compradora.getNroCaso(),Parametro.IN));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_GUARDAR");
      if (Constantes.isLeocomus(compradora.getCodCia())) {
				rutaSQL = Configuracion.getInstance().getParametroApp("SP_GUARDAR_USA");
			}
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if (cs != null) {
				cs.execute();
				
				obtenido = new CompradoraDTO();
				obtenido.setEstado(cs.getString(17).trim());
				obtenido.setDescripcion(cs.getString(18).trim());
			}
			
		} catch (SQLException e) {
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

	public CompradoraDTO eliminar(CompradoraDTO compradora) throws PersonalsoftException {
		CompradoraDTO obtenido = null;		
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, compradora.getCodCia(),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.DECIMAL, new Integer( compradora.getCedula() ),Parametro.IN_OUT));
		parametros.add(new Parametro(Types.VARCHAR, compradora.getSku(),Parametro.IN));
		parametros.add(new Parametro(Types.DECIMAL, new Integer( compradora.getCantidad() ),Parametro.IN));
		parametros.add(new Parametro(Types.DECIMAL, new Integer( compradora.getFila() ),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_ELIMINAR");
      if (Constantes.isLeocomus(compradora.getCodCia())) {
				rutaSQL = Configuracion.getInstance().getParametroApp("SP_ELIMINAR_USA");
			}
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if (cs != null) {
				cs.execute();
				
				obtenido = new CompradoraDTO();
				obtenido.setEstado(cs.getString(6).trim());
				obtenido.setDescripcion(cs.getString(7).trim());
			}
			
		} catch (SQLException e) {
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
	
	
	public CompradoraDTO getDatosCompradoraXGuia(CompradoraDTO compradora) throws PersonalsoftException {
		CompradoraDTO obtenido = null;		
		CallableStatement cs = null;
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		int pos = 0;
		
		PersonalsoftException ps = null;
		
		parametros.add(new Parametro(Types.VARCHAR, compradora.getCodCia(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, compradora.getNroGuia(),Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, "0", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "0", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		parametros.add(new Parametro(Types.VARCHAR, "", Parametro.OUT));
		
		try{
			String rutaSQL = Configuracion.getInstance().getParametroApp("SP_GETDATOSCOMPXGUIA");
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if (cs != null) {
				cs.execute();
				pos = 2;
				
				obtenido = new CompradoraDTO();
				obtenido.setNroOrden( cs.getString(++pos) != null ? cs.getString(pos).trim() : "0" );
				obtenido.setCedula( cs.getString(++pos) != null ? cs.getString(pos).trim() : "0" );
				obtenido.setNombre( cs.getString(++pos) != null ? cs.getString(pos).trim() : "" );
				obtenido.setCampanaPedido( cs.getString(++pos) != null ? cs.getString(pos).trim() : "" );
				obtenido.setEmail( cs.getString(++pos) != null ? cs.getString(pos).trim() : "" );
				obtenido.setAutorizaEnvioEmail( cs.getString(++pos) != null ? cs.getString(pos).trim() : "N" );
			}
			
		} catch (SQLException e) {
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
