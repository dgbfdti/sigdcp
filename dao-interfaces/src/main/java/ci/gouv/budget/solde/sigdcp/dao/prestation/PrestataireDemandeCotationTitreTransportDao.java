package ci.gouv.budget.solde.sigdcp.dao.prestation;

import java.util.Collection;

import ci.gouv.budget.solde.sigdcp.dao.DataAccessObject;
import ci.gouv.budget.solde.sigdcp.model.prestation.PrestataireDemandeCotationTitreTransport;
import ci.gouv.budget.solde.sigdcp.model.prestation.PrestataireDemandeCotationTitreTransportId;

public interface PrestataireDemandeCotationTitreTransportDao extends DataAccessObject<PrestataireDemandeCotationTitreTransport,PrestataireDemandeCotationTitreTransportId> {

	Collection<PrestataireDemandeCotationTitreTransport> readByDemandeId(Long demandeId);
	Collection<PrestataireDemandeCotationTitreTransport> readAllByPrestataireId(Long prestataireId);
	Collection<PrestataireDemandeCotationTitreTransport> readATraiterByPrestataireId(Long prestataireId);
	Collection<PrestataireDemandeCotationTitreTransport> readATraiter();
}
