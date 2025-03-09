package votre.portaloperaciones.servicios.transportistas.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import votre.portaloperaciones.servicios.transportistas.beans.EstructuraArchivoDTO;
import votre.portaloperaciones.servicios.transportistas.beans.RegistroDTO;
import votre.portaloperaciones.servicios.transportistas.beans.TransportistaDTO;
import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.beans.Parametro;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class TransportistaDAO implements ITransportistaDAO{
	
	private BDHelper bdHelper;
	
	Logger logger = Logger.getLogger(this.getClass());
	
	public TransportistaDAO(){}
	
	public TransportistaDAO(BDHelper bdHelper){
		this.bdHelper= bdHelper;
	}
	
	
	/**
	 * Método para consultar la estructura de un archivo del AS/400
	 * @param transportistaDTO
	 * @return
	 * @throws PersonalsoftException
	 */
	public TransportistaDTO consultarEstructuraArchivo(TransportistaDTO transportistaDTO) throws PersonalsoftException {
		
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<EstructuraArchivoDTO> arrColumnas = new ArrayList<EstructuraArchivoDTO>();
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		EstructuraArchivoDTO item = null;
		
		parametros.add(new Parametro(Types.VARCHAR, transportistaDTO.getNombreArchivo(), Parametro.IN));
		parametros.add(new Parametro(Types.VARCHAR, transportistaDTO.getBiblioteca(), Parametro.IN));
		
		try {
			String rutaSQL = "VADMWP00.SP_ESTRUCTURA_ARCHIVO_CONSULTAR"; 
			
			cs = this.bdHelper.cargarCallableStatement(rutaSQL, parametros);
			
			if (cs != null) {
				cs.execute();
				rs = cs.getResultSet();
				
				if (rs != null) {
					while (rs.next()) {
						item = new EstructuraArchivoDTO();
						
						item.setNombre(rs.getString("WHFLDE") != null ? rs.getString("WHFLDE").trim() : null);
						item.setDescripcion(rs.getString("WHFTXT") != null ? rs.getString("WHFTXT").trim() : null);
						item.setTipoCampo(rs.getString("WHFLDT") != null ? rs.getString("WHFLDT").trim() : null);
						item.setNumeroDecimales(rs.getInt("WHFLDP"));
						item.setNumeroEnteros(rs.getInt("WHFLDD"));
						item.setTamanoCampo(rs.getInt("WHFLDB"));
						item.setPosicionInicial(rs.getInt("WHFOBO"));
						
						arrColumnas.add(item);
					}
				}
				
				transportistaDTO.setColumnas(arrColumnas);
			}
			
		} catch (SQLException e) {
			throw new PersonalsoftException(e);
		} finally {
			try {
				BDHelper.close(cs);
				BDHelper.close(rs);
			} catch (SQLException e) {
				throw new PersonalsoftException(e);
			}
		}
		
		return transportistaDTO;
	}
	
	/**
	 * Método para consultar la la información de un archivo genérico en el AS/400
	 * @param transportistaDTO
	 * @return
	 * @throws PersonalsoftException
	 */
	public TransportistaDTO consultarDetalleArchivoTransportistas(TransportistaDTO transportistaDTO) throws PersonalsoftException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<RegistroDTO[]> registros = new ArrayList<RegistroDTO[]>();
		int pos=0;
		
		try {
//			ps = this.bdHelper.cargarPreparedStatement("SELECT * FROM "+transportistaDTO.getBiblioteca()+"."+transportistaDTO.getNombreArchivo());
			ps = this.bdHelper.getConn().prepareStatement(transportistaDTO.getQuery());
			
			if (ps != null) {
				ps.execute();
				rs = ps.getResultSet();
				
				if (rs != null) {
					while (rs.next()) {
						pos = 0;
						
						if(transportistaDTO.getColumnas() != null){
							RegistroDTO[] celdas = new RegistroDTO[transportistaDTO.getColumnas().size()];
							
							for (EstructuraArchivoDTO columna : transportistaDTO.getColumnas()) {
								RegistroDTO registroDTO = new RegistroDTO();
								registroDTO.setValor(rs.getString(columna.getNombre()) != null ? rs.getString(columna.getNombre()).trim() : null);
								registroDTO.setEstructura(columna);
								celdas[pos++]  = registroDTO;
							}
							
							registros.add(celdas);
						}
					}
					
					transportistaDTO.setRegistros(registros);
				}
			}
			
		} catch (SQLException e) {
			throw new PersonalsoftException(e);
		} finally {
			try {
				BDHelper.close(ps);
				BDHelper.close(rs);
			} catch (SQLException e) {
				throw new PersonalsoftException(e);
			}
		}
		
		return transportistaDTO;
	}

}
