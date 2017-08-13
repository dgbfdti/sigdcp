package ci.gouv.budget.solde.sigdcp.dao.dossier;

import ci.gouv.budget.solde.sigdcp.model.dossier.Dossier;
import ci.gouv.budget.solde.sigdcp.model.dossier.ProjetDecision;

public interface ProjetDecisionDao extends AbstractPieceProduiteDao<ProjetDecision> {

	ProjetDecision readByDemande(Dossier dossier);

	/*
	Collection<BulletinLiquidation> readByDernierTraitementIsNull(Collection<NatureDeplacement> natureDeplacements);
	
	BulletinLiquidation readByNumero(String numero);
	
	Collection<BulletinLiquidation> readByNatureDeplacementsByBordereauNatureOperationIdByBordereauStatutId(Collection<NatureDeplacement> natureDeplacements,String natureOperationId,String statutId);
	*/
	
}
