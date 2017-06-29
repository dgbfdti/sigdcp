package ci.gouv.budget.solde.sigdcp.controller.remboursement;

import java.io.Serializable;
import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ci.gouv.budget.solde.sigdcp.model.Code;
import ci.gouv.budget.solde.sigdcp.model.dossier.ProjetDecision;

@Named @ViewScoped
public class ValiderProjetDecisionController extends AbstractProjetDecisionListePersonnelController implements Serializable {

	private static final long serialVersionUID = -2412073347414420827L;
	
	@Override
	protected void initialisation() {
		super.initialisation();
		listTitle="Liste des projets de décision";
		selectLabel="bouton.selectionner";
		showNumeroCabinet=true;
	}
	
	@Override
	public Date dateCreation(ProjetDecision projetDecision) {
		dossierService.init(projetDecision.getDossier(), null);
		return projetDecision.getDossier().getDateCreation();
	}
	
	@Override
	protected String natureOperationCode() {
		return Code.NATURE_OPERATION_VALIDATION_PROJDEC;
	}
	
				
}
