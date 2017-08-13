package ci.gouv.budget.solde.sigdcp.controller.reference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ci.gouv.budget.solde.sigdcp.model.dossier.CategorieDeplacement;
import ci.gouv.budget.solde.sigdcp.model.indemnite.IndemniteTrancheDistance;
import ci.gouv.budget.solde.sigdcp.service.resources.ListeResources;
import lombok.Getter;

@Named @ViewScoped
public class IndemniteTrancheDistanceReferenceController extends AbstractEntiteReferenceController<IndemniteTrancheDistance,Long> implements Serializable {

	private static final long serialVersionUID = -1061481987684469949L;
	
	@Inject transient private ListeResources listeResources;
	
	@Getter private List<SelectItem> categorieDeplacements = new ArrayList<>();
	
	@Override
	protected void initialisation() {
		super.initialisation();
		for(CategorieDeplacement entity : listeResources.getCategorieDeplacements())
			categorieDeplacements.add(new SelectItem(entity, entity.getLibelle()));
	}
	
	@Override
	protected String nomEntite() {
		return "Indemnité tranche distance";
	}
	
	@Override
	public Long identifiant(IndemniteTrancheDistance entity) {
		return entity.getId();
	}

	@Override
	protected Class<IndemniteTrancheDistance> clazz() {
		return IndemniteTrancheDistance.class;
	}
	
}
