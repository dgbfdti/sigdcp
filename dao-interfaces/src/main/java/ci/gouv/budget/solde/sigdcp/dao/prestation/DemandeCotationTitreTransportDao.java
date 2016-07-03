package ci.gouv.budget.solde.sigdcp.dao.prestation;

import java.util.Collection;

import ci.gouv.budget.solde.sigdcp.dao.traitement.TraitableDao;
import ci.gouv.budget.solde.sigdcp.model.prestation.DemandeCotationObseque;
import ci.gouv.budget.solde.sigdcp.model.prestation.DemandeCotationTitreTransport;

public interface DemandeCotationTitreTransportDao extends TraitableDao<DemandeCotationTitreTransport,Long> {

	Collection<DemandeCotationTitreTransport> readACommander();


}
