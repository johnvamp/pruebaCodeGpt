package votre.dao;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import votre.portaloperaciones.activaciondemandas.dao.ActivacionDemandasDAO;
import votre.portaloperaciones.activaciondemandas.dao.IActivacionDemandasDAO;
import votre.portaloperaciones.consultasku.bodegas.dao.BodegaDAO;
import votre.portaloperaciones.consultasku.bodegas.dao.IBodegaDAO;
import votre.portaloperaciones.consultasku.consultas.dao.ConsultasDAO;
import votre.portaloperaciones.consultasku.consultas.dao.IConsultasDAO;
import votre.portaloperaciones.despachocatalogo.embarquecatalogo.dao.EmbarqueCatalogoDAO;
import votre.portaloperaciones.despachocatalogo.embarquecatalogo.dao.IEmbarqueCatalogoDAO;
import votre.portaloperaciones.despachocatalogo.labels.dao.ILabelsDAO;
import votre.portaloperaciones.despachocatalogo.labels.dao.LabelsDAO;
import votre.portaloperaciones.despachocatalogo.reportes.dao.IReportesDAO;
import votre.portaloperaciones.despachocatalogo.reportes.dao.ReportesDAO;
import votre.portaloperaciones.embarqueinternacional.camion.dao.CamionDAO;
import votre.portaloperaciones.embarqueinternacional.camion.dao.ICamionDAO;
import votre.portaloperaciones.embarqueinternacional.causales.dao.CausalDAO;
import votre.portaloperaciones.embarqueinternacional.causales.dao.ICausalDAO;
import votre.portaloperaciones.embarqueinternacional.embarque.dao.EmbarqueDAO;
import votre.portaloperaciones.embarqueinternacional.embarque.dao.IEmbarqueDAO;
import votre.portaloperaciones.embarqueinternacional.entrega.dao.EntregaDAO;
import votre.portaloperaciones.embarqueinternacional.entrega.dao.IEntregaDAO;
import votre.portaloperaciones.embarqueinternacional.reimprimir.dao.IReimprimirDAO;
import votre.portaloperaciones.embarqueinternacional.reimprimir.dao.ReimprimirDAO;
import votre.portaloperaciones.embarqueinternacional.transportador.dao.ITransportadorDAO;
import votre.portaloperaciones.embarqueinternacional.transportador.dao.TransportadorDAO;
import votre.portaloperaciones.flujowms.dao.FlujoWmsDAO;
import votre.portaloperaciones.flujowms.dao.IFlujoWmsDAO;
import votre.portaloperaciones.indicadordepedidos.dao.IIndicadorPedidosDAO;
import votre.portaloperaciones.indicadordepedidos.dao.IndicadorPedidosDAO;
import votre.portaloperaciones.logueo.dao.ILogueoDAO;
import votre.portaloperaciones.logueo.dao.LogueoDAO;
import votre.portaloperaciones.pedidos.dao.IPedidosDAO;
import votre.portaloperaciones.pedidos.dao.PedidosDAO;
import votre.portaloperaciones.pedidoscsl.dao.IPedidosCSLDAO;
import votre.portaloperaciones.pedidoscsl.dao.PedidosCSLDAO;
import votre.portaloperaciones.reprocesos.dao.IReprocesosDAO;
import votre.portaloperaciones.reprocesos.dao.ReprocesosDAO;
import votre.portaloperaciones.servicios.transportistas.dao.ITransportistaDAO;
import votre.portaloperaciones.servicios.transportistas.dao.TransportistaDAO;
import votre.portaloperaciones.solicitudcatalogo.catalogo.dao.CatalogoDAO;
import votre.portaloperaciones.solicitudcatalogo.catalogo.dao.ICatalogoDAO;
import votre.portaloperaciones.solicitudcatalogo.compradora.dao.CompradoraDAO;
import votre.portaloperaciones.solicitudcatalogo.compradora.dao.ICompradoraDAO;
import votre.portaloperaciones.solicitudcatalogo.referencia.dao.IReferenciaDAO;
import votre.portaloperaciones.solicitudcatalogo.referencia.dao.ReferenciaDAO;
import votre.portaloperaciones.suscripcioncatalogo.dao.ISuscripcionesDAO;
import votre.portaloperaciones.suscripcioncatalogo.dao.SuscripcionesDAO;
import votre.portaloperaciones.transportistas.dao.ITransportistasDAO;
import votre.portaloperaciones.transportistas.dao.TransportistasDAO;
import co.com.personalsoft.base.bd.BDHelper;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class DAOFactory {
	
	private BDHelper helper = null;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public DAOFactory() throws PersonalsoftException {
		try {
			helper = new BDHelper();
			helper.getConn().setAutoCommit(false);
		} catch (Exception e) {
			logger.error("Ocurrió un error en la clase "+this.getClass()+" Error: "+e);
			if (e instanceof PersonalsoftException) {
				try {
					throw e;
				} catch (Exception e1) {
					throw new PersonalsoftException(e1);
				}
			} else {
				throw new PersonalsoftException(e);
			}
		}
	}

	public DAOFactory(String jndi) throws PersonalsoftException{
		try {
			helper = new BDHelper(jndi);
			helper.getConn().setAutoCommit(false);
		} catch (Exception e) {
			logger.error("Ocurrió un error en la clase "+this.getClass()+" Error: "+e);
			if (e instanceof PersonalsoftException) {
				try {
					throw e;
				} catch (Exception e1) {
					throw new PersonalsoftException(e1);
				}
			} else {
				throw new PersonalsoftException(e);
			}
		}
	}

	public void cerrarConexion() throws SQLException {
		if(!this.helper.getConn().isClosed()){
			this.helper.cerrarConexion();
		}
	}

	public void commitTransaction() throws SQLException {
		this.helper.commitTransaction();

	}

	public void rolbackTransaction() throws SQLException {
		this.helper.rollbackTransaction();
	}
	
	public ILogueoDAO getLogueo(){
		return new LogueoDAO(this.helper);
	}

	public ICatalogoDAO getCatalogo(){
		return new CatalogoDAO(this.helper);
	}
	
	public ICompradoraDAO getCompradora(){
		return new CompradoraDAO(this.helper);
	}
	
	public IReferenciaDAO getReferencia(){
		return new ReferenciaDAO(this.helper);
	}
	
	public ILabelsDAO getLabels(){
		return new LabelsDAO(this.helper);
	}
	
	public IReportesDAO getReportes(){
		return new ReportesDAO(this.helper);
	}
	
	public IEmbarqueDAO getEmbarque(){
		return new EmbarqueDAO(this.helper);
	}
	
	public ITransportadorDAO getTransportador(){
		return new TransportadorDAO(this.helper);
	}
	
	public ITransportistaDAO getTransportista(){
		return new TransportistaDAO(this.helper);
	}
	
	public ITransportistasDAO getTransportistas(){
		return new TransportistasDAO(this.helper);
	}
	

	
	public IEntregaDAO getEntrega(){
		return new EntregaDAO(this.helper);
	}
	
	public ICamionDAO getCamion(){
		return new CamionDAO(this.helper);
	}
	
	public IReimprimirDAO getReimprimir(){
		return new ReimprimirDAO(this.helper);
	}
	
	public ICausalDAO getCausal(){
		return new CausalDAO(this.helper);
	}
	
	public IBodegaDAO getBodega(){
		return new BodegaDAO(this.helper);
	}
	
	public IConsultasDAO getConsultas(){
		return new ConsultasDAO(this.helper);
	}
	
	public ISuscripcionesDAO getSuscripciones(){
		return new SuscripcionesDAO(this.helper);
	}
	
	
	public IFlujoWmsDAO getFlujoWms(){
		return new FlujoWmsDAO(this.helper);		
	}
	
	public IFlujoWmsDAO getTituloFlujoWms(){
		return new FlujoWmsDAO(this.helper);
		
	}
	
	public IFlujoWmsDAO getFlujoWmsGrafica(){
		return new FlujoWmsDAO(this.helper);
	}
	
	public IIndicadorPedidosDAO getIndicadorPedidosGraf1(){
		return new IndicadorPedidosDAO(this.helper);
	}

	public IActivacionDemandasDAO getActDemandas(){
		return new ActivacionDemandasDAO(this.helper);
	}

	public IReprocesosDAO getReprocesos(){
		return new ReprocesosDAO(this.helper);
	}
	
	public IEmbarqueCatalogoDAO getEmbarqueCatalogo(){
		return new EmbarqueCatalogoDAO(this.helper);
	}
	
	public IPedidosDAO getPedidos(){
		return new PedidosDAO(this.helper);
	}
	
	public IPedidosCSLDAO getPedidosCSL(){
		return new PedidosCSLDAO(this.helper);
	}
}
