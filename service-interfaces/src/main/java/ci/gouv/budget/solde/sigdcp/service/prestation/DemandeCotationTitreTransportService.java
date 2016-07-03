package ci.gouv.budget.solde.sigdcp.service.prestation;

import ci.gouv.budget.solde.sigdcp.model.dossier.DossierMission;
import ci.gouv.budget.solde.sigdcp.model.prestation.DemandeCotationTitreTransport;
import ci.gouv.budget.solde.sigdcp.service.traitement.TraitableService;

public interface DemandeCotationTitreTransportService extends TraitableService<DemandeCotationTitreTransport,Long> {
	
	DemandeCotationTitreTransport nouveau(DossierMission dossierMission);
	 
	//void enregistrerDemandeCotationTitreTransport(DossierTitreTransports dossier, Collection<Prestataire> prestataires) throws ServiceException;
	
}
