package votre.portaloperaciones.activaciondemandas.facade;

import java.sql.SQLException;
import java.util.ArrayList;

import votre.dao.DAOFactory;
import votre.portaloperaciones.activaciondemandas.beans.AsignarDTO;
import votre.portaloperaciones.activaciondemandas.beans.CasoDTO;
import votre.portaloperaciones.activaciondemandas.beans.CmpDemandaDTO;
import votre.portaloperaciones.activaciondemandas.beans.EnviosDTO;
import votre.portaloperaciones.activaciondemandas.beans.GuiasMasterDTO;
import votre.portaloperaciones.activaciondemandas.beans.NovedadActDemandaDTO;
import votre.portaloperaciones.activaciondemandas.beans.OpcionDTO;
import votre.portaloperaciones.activaciondemandas.beans.RefeDemandaDTO;
import votre.portaloperaciones.activaciondemandas.beans.ReferenciasDTO;
import votre.portaloperaciones.activaciondemandas.manager.ActivacionDemandasMgr;
import co.com.personalsoft.base.excepcion.PersonalsoftException;

public class ActivacionDemandasFacade implements IActivacionDemandasFacade {


	public ArrayList<OpcionDTO> consultarOpciones(OpcionDTO opcionDTO) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		ArrayList<OpcionDTO> obtenidos = null;
		try {
			daoFactory = new DAOFactory();
			obtenidos = daoFactory.getActDemandas().consultarOpciones(opcionDTO);
		} catch (Exception e) {
			if (e instanceof PersonalsoftException) {
				throw (PersonalsoftException) e;
			} else {
				throw new PersonalsoftException(e);
			}
		} finally {
			try {
				daoFactory.cerrarConexion();
			} catch (SQLException exception) {
				throw new PersonalsoftException(exception);
			}
		}
		return obtenidos;
	}

	public CmpDemandaDTO validarCompradora(CmpDemandaDTO cmpDemandaDTO) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		try {
			daoFactory = new DAOFactory();
			cmpDemandaDTO = daoFactory.getActDemandas().validarCompradora(cmpDemandaDTO);
		} catch (Exception e) {
			if (e instanceof PersonalsoftException) {
				throw (PersonalsoftException) e;
			} else {
				throw new PersonalsoftException(e);
			}
		} finally {
			try {
				daoFactory.cerrarConexion();
			} catch (SQLException exception) {
				throw new PersonalsoftException(exception);
			}
		}
		return cmpDemandaDTO;
	}

	public RefeDemandaDTO validarReferencia(RefeDemandaDTO refeDemandaDTO) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		try {
			daoFactory = new DAOFactory();
			refeDemandaDTO = daoFactory.getActDemandas().validarReferencia(refeDemandaDTO);
		} catch (Exception e) {
			if (e instanceof PersonalsoftException) {
				throw (PersonalsoftException) e;
			} else {
				throw new PersonalsoftException(e);
			}
		} finally {
			try {
				daoFactory.cerrarConexion();
			} catch (SQLException exception) {
				throw new PersonalsoftException(exception);
			}
		}
		return refeDemandaDTO;
	}

	public NovedadActDemandaDTO grabarNovedad(NovedadActDemandaDTO dto) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		try {
			daoFactory = new DAOFactory();
			dto = daoFactory.getActDemandas().grabarNovedad(dto);
		} catch (Exception e) {
			if (e instanceof PersonalsoftException) {
				throw (PersonalsoftException) e;
			} else {
				throw new PersonalsoftException(e);
			}
		} finally {
			try {
				daoFactory.cerrarConexion();
			} catch (SQLException exception) {
				throw new PersonalsoftException(exception);
			}
		}
		return dto;
	}

	public ArrayList<CasoDTO> consultarCasos(CasoDTO dto) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		ArrayList<CasoDTO> obtenidos = null;
		try {
			daoFactory = new DAOFactory();
			obtenidos = daoFactory.getActDemandas().consultarCasos(dto);
		} catch (Exception e) {
			if (e instanceof PersonalsoftException) {
				throw (PersonalsoftException) e;
			} else {
				throw new PersonalsoftException(e);
			}
		} finally {
			try {
				daoFactory.cerrarConexion();
			} catch (SQLException exception) {
				throw new PersonalsoftException(exception);
			}
		}
		return obtenidos;
	}

	public CasoDTO grabarAprobacion(CasoDTO dto) throws PersonalsoftException {
		DAOFactory daoFactory = null;
		try {
			daoFactory = new DAOFactory();
			dto = daoFactory.getActDemandas().grabarAprobacion(dto);
		} catch (Exception e) {
			if (e instanceof PersonalsoftException) {
				throw (PersonalsoftException) e;
			} else {
				throw new PersonalsoftException(e);
			}
		} finally {
			try {
				daoFactory.cerrarConexion();
			} catch (SQLException exception) {
				throw new PersonalsoftException(exception);
			}
		}
		return dto;
	}

	public AsignarDTO asignarTransportadores(AsignarDTO dto) throws PersonalsoftException {
		AsignarDTO obtenido = null;
		DAOFactory daoFactory = null;
		ActivacionDemandasMgr manager = null;
		try {
			daoFactory = new DAOFactory();
			manager = new ActivacionDemandasMgr();
			obtenido = manager.asignarTransportadores(daoFactory, dto);
		} catch (Exception e) {
			if (e instanceof PersonalsoftException) {
				throw (PersonalsoftException) e;
			} else {
				throw new PersonalsoftException(e);
			}
		} finally {
			try {
				daoFactory.cerrarConexion();
			} catch (SQLException exception) {
				throw new PersonalsoftException(exception);
			}
		}
		return obtenido;
	}

	public ReferenciasDTO consultarReferencias(ReferenciasDTO dto) throws PersonalsoftException {
		ReferenciasDTO obtenido = null;
		DAOFactory daoFactory = null;
		try {
			daoFactory = new DAOFactory();
			obtenido = daoFactory.getActDemandas().consultarReferencias(dto);
		} catch (Exception e) {
			if (e instanceof PersonalsoftException) {
				throw (PersonalsoftException) e;
			} else {
				throw new PersonalsoftException(e);
			}
		} finally {
			try {
				daoFactory.cerrarConexion();
			} catch (SQLException exception) {
				throw new PersonalsoftException(exception);
			}
		}
		return obtenido;
	}

	public AsignarDTO generarGuias(AsignarDTO dto) throws PersonalsoftException {
		AsignarDTO obtenido = null;
		DAOFactory daoFactory = null;
		try {
			daoFactory = new DAOFactory();
			obtenido = daoFactory.getActDemandas().generarGuias(dto);
		} catch (Exception e) {
			if (e instanceof PersonalsoftException) {
				throw (PersonalsoftException) e;
			} else {
				throw new PersonalsoftException(e);
			}
		} finally {
			try {
				daoFactory.cerrarConexion();
			} catch (SQLException exception) {
				throw new PersonalsoftException(exception);
			}
		}
		return obtenido;
	}

	public ReferenciasDTO cambiarACorreoExtra(ReferenciasDTO dto) throws PersonalsoftException {
		ReferenciasDTO obtenido = null;
		DAOFactory daoFactory = null;
		ActivacionDemandasMgr mgr = null;
		try {
			daoFactory = new DAOFactory();
			mgr = new ActivacionDemandasMgr();
			obtenido = mgr.cambiarACorreoExtra(daoFactory, dto);
		} catch (Exception e) {
			if (e instanceof PersonalsoftException) {
				throw (PersonalsoftException) e;
			} else {
				throw new PersonalsoftException(e);
			}
		} finally {
			try {
				daoFactory.cerrarConexion();
			} catch (SQLException exception) {
				throw new PersonalsoftException(exception);
			}
		}
		return obtenido;
	}
	
	public EnviosDTO eliminarEnvios(EnviosDTO dto) throws PersonalsoftException {
		EnviosDTO obtenido = null;
		DAOFactory daoFactory = null;
		try {
			daoFactory = new DAOFactory();
			obtenido = daoFactory.getActDemandas().eliminarEnvios(dto);
		} catch (Exception e) {
			if (e instanceof PersonalsoftException) {
				throw (PersonalsoftException) e;
			} else {
				throw new PersonalsoftException(e);
			}
		} finally {
			try {
				daoFactory.cerrarConexion();
			} catch (SQLException exception) {
				throw new PersonalsoftException(exception);
			}
		}
		return obtenido;
	}

	public GuiasMasterDTO consultarGuiasMaster(GuiasMasterDTO dto) throws PersonalsoftException {
		GuiasMasterDTO obtenido = null;
		DAOFactory daoFactory = null;
		try {
			daoFactory = new DAOFactory();
			obtenido = daoFactory.getActDemandas().consultarGuiasMaster(dto);
		} catch (Exception e) {
			if (e instanceof PersonalsoftException) {
				throw (PersonalsoftException) e;
			} else {
				throw new PersonalsoftException(e);
			}
		} finally {
			try {
				daoFactory.cerrarConexion();
			} catch (SQLException exception) {
				throw new PersonalsoftException(exception);
			}
		}
		return obtenido;
	}

	public ArrayList<GuiasMasterDTO> imprimirGuiasMaster(ArrayList<GuiasMasterDTO> arrGuias) throws PersonalsoftException {
		ArrayList<GuiasMasterDTO> obtenido = null;
		DAOFactory daoFactory = null;
		ActivacionDemandasMgr mgr = null;
		try {
			daoFactory = new DAOFactory();
			mgr = new ActivacionDemandasMgr();
			obtenido = mgr.imprimirGuiasMaster(daoFactory, arrGuias);
		} catch (Exception e) {
			if (e instanceof PersonalsoftException) {
				throw (PersonalsoftException) e;
			} else {
				throw new PersonalsoftException(e);
			}
		} finally {
			try {
				daoFactory.cerrarConexion();
			} catch (SQLException exception) {
				throw new PersonalsoftException(exception);
			}
		}
		return obtenido;
	}
}
