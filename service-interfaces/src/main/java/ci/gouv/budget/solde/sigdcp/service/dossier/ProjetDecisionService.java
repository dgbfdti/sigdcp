package ci.gouv.budget.solde.sigdcp.service.dossier;

import ci.gouv.budget.solde.sigdcp.model.dossier.Dossier;
import ci.gouv.budget.solde.sigdcp.model.dossier.ProjetDecision;

public interface ProjetDecisionService extends AbstractPieceProduiteService<ProjetDecision> {

	ProjetDecision findByDemande(Dossier dossier);
	
}
