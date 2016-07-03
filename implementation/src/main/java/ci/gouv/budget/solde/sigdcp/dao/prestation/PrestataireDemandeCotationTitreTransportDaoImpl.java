package ci.gouv.budget.solde.sigdcp.dao.prestation;

import java.io.Serializable;
import java.util.Collection;

import ci.gouv.budget.solde.sigdcp.dao.JpaDaoImpl;
import ci.gouv.budget.solde.sigdcp.model.prestation.PrestataireDemandeCotationTitreTransport;
import ci.gouv.budget.solde.sigdcp.model.prestation.PrestataireDemandeCotationTitreTransportId;

public class PrestataireDemandeCotationTitreTransportDaoImpl extends JpaDaoImpl<PrestataireDemandeCotationTitreTransport, PrestataireDemandeCotationTitreTransportId> implements PrestataireDemandeCotationTitreTransportDao, Serializable {

	private static final long serialVersionUID = -1425013722199012221L;

	@Override
	public Collection<PrestataireDemandeCotationTitreTransport> readByDemandeId(Long demandeId) {
		return entityManager.createQuery("SELECT pd FROM PrestataireDemandeCotationTitreTransport pd WHERE pd.id.demandeCotationMissionId = :demandeId ", clazz)
				.setParameter("demandeId", demandeId)
				.getResultList();
	}

	@Override
	public Collection<PrestataireDemandeCotationTitreTransport> readAllByPrestataireId(Long prestataireId) {
		return entityManager.createQuery("SELECT pd FROM PrestataireDemandeCotationTitreTransport pd WHERE pd.id.prestataireId = :prestataireId ", clazz)
				.setParameter("prestataireId", prestataireId)
				.getResultList();
	}

	@Override
	public Collection<PrestataireDemandeCotationTitreTransport> readATraiterByPrestataireId(Long prestataireId) {
		return entityManager.createQuery("SELECT pd FROM PrestataireDemandeCotationTitreTransport pd WHERE pd.id.prestataireId = :prestataireId AND pd.montantTransport IS NULL  ", clazz)
				.setParameter("prestataireId", prestataireId)
				.getResultList();
	}

	@Override
	public Collection<PrestataireDemandeCotationTitreTransport> readATraiter() {
		return entityManager.createQuery("SELECT pd FROM PrestataireDemandeCotationTitreTransport pd WHERE pd.montantTransport IS NOT NULL AND NOT EXISTS ("
				+ " SELECT cmd FROM CommandeTitreTransport cmd WHERE cmd.prestataireDemandeCotationTitreTransport=pd) ", clazz)
				.getResultList();
	}

	

}
 