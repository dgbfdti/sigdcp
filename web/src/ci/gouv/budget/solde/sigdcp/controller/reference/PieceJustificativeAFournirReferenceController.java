package ci.gouv.budget.solde.sigdcp.controller.reference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ci.gouv.budget.solde.sigdcp.model.dossier.NatureDeplacement;
import ci.gouv.budget.solde.sigdcp.model.dossier.PieceJustificativeAFournir;
import ci.gouv.budget.solde.sigdcp.model.dossier.TypeDepense;
import ci.gouv.budget.solde.sigdcp.model.dossier.TypePieceJustificative;
import ci.gouv.budget.solde.sigdcp.model.identification.Role;
import ci.gouv.budget.solde.sigdcp.model.identification.TypePersonne;
import ci.gouv.budget.solde.sigdcp.service.resources.ListeResources;
import lombok.Getter;

@Named @ViewScoped
public class PieceJustificativeAFournirReferenceController extends AbstractEntiteReferenceController<PieceJustificativeAFournir,Long> implements Serializable {

	private static final long serialVersionUID = -1061481987684469949L;
	
	@Inject transient private ListeResources listeResources;
	
	@Getter private List<SelectItem> typePieceJustificatives = new ArrayList<>();
	@Getter private List<SelectItem> natureDeplacements = new ArrayList<>();
	@Getter private List<SelectItem> typePersonnes = new ArrayList<>();
	@Getter private List<SelectItem> typeDepenses = new ArrayList<>();
	@Getter private List<SelectItem> roles = new ArrayList<>();
	
	@Override
	protected void initialisation() {
		super.initialisation();
		for(TypePieceJustificative entity : listeResources.getTypePieceJustificatives())
			typePieceJustificatives.add(new SelectItem(entity, entity.getLibelle()));
			
		for(NatureDeplacement entity : listeResources.getNatureDeplacements())
			natureDeplacements.add(new SelectItem(entity, entity.getLibelle()));
		
		for(TypePersonne entity : listeResources.getTypePersonnes())
			typePersonnes.add(new SelectItem(entity, entity.getLibelle()));
		
		for(TypeDepense entity : listeResources.getTypeDepenes())
			typeDepenses.add(new SelectItem(entity, entity.getLibelle()));
		
		for(Role entity : listeResources.getRoles())
			roles.add(new SelectItem(entity, entity.getLibelle()));
	}
	
	@Override
	protected String nomEntite() {
		return "Pièce justificative à fournir";
	}
	
	@Override
	public Long identifiant(PieceJustificativeAFournir entity) {
		return entity.getId();
	}

	@Override
	protected Class<PieceJustificativeAFournir> clazz() {
		return PieceJustificativeAFournir.class;
	}
	
}
