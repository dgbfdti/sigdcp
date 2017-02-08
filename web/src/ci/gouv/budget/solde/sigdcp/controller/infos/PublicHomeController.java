package ci.gouv.budget.solde.sigdcp.controller.infos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import ci.gouv.budget.solde.sigdcp.controller.ui.AbstractUIController;
import ci.gouv.budget.solde.sigdcp.model.Code;
import ci.gouv.budget.solde.sigdcp.model.dossier.NatureDeplacement;
import ci.gouv.budget.solde.sigdcp.service.dossier.NatureDeplacementService;

@Named @RequestScoped
public class PublicHomeController extends AbstractUIController implements Serializable {

	private static final long serialVersionUID = 7404320093212948431L;

	/*
	 * Services
	 */
	@Inject private NatureDeplacementService natureDeplacementService;
	
	@Getter @Setter private List<ServiceProposeDto> serviceProposeDtos = new ArrayList<>();
	
	private static final List<String> INDEXES = Arrays.asList(Code.NATURE_DEPLACEMENT_AFFECTATION,Code.NATURE_DEPLACEMENT_MUTATION,Code.NATURE_DEPLACEMENT_RETRAITE
			,Code.NATURE_DEPLACEMENT_MISSION_HCI,Code.NATURE_DEPLACEMENT_TITRE_TRANSPORT_STAGE,Code.NATURE_DEPLACEMENT_TITRE_TRANSPORT_CONGE
			,Code.NATURE_DEPLACEMENT_OBSEQUE_FRAIS,Code.NATURE_DEPLACEMENT_TRANSIT_BAGAGGES_STAGIAIRE,Code.NATURE_DEPLACEMENT_TRANSIT_BAGAGGES_MAE);
	
	@Override
	protected void initialisation() {
		super.initialisation();
		List<NatureDeplacement> natureDeplacements = natureDeplacementService.findAll();
		Collections.sort(natureDeplacements, new Comparator<NatureDeplacement>() {
			@Override
			public int compare(NatureDeplacement o1, NatureDeplacement o2) {
				return INDEXES.indexOf(o1.getCode()) - INDEXES.indexOf(o2.getCode());
			}
		});
		for(NatureDeplacement natureDeplacement : natureDeplacements)
			serviceProposeDtos.add(new ServiceProposeDto(natureDeplacement,webConstantResources));
		serviceProposeDtos.add(new ServiceProposeDto("Sotra","Description","lpus","lpv","lireplussotra",false,false,true));
	}
	
}
