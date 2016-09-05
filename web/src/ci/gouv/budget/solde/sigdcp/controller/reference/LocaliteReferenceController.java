package ci.gouv.budget.solde.sigdcp.controller.reference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ci.gouv.budget.solde.sigdcp.model.geographie.Localite;
import ci.gouv.budget.solde.sigdcp.service.geographie.LocaliteService;


@Named @ViewScoped
public class LocaliteReferenceController extends AbstractDynaEnumReferenceController<Localite> implements Serializable {

	private static final long serialVersionUID = -1061481987684469949L;

	@Inject private LocaliteService localiteService;
	
	@Override
	protected String nomEntite() {
		return "Localité";
	}

	@Override
	protected Class<Localite> clazz() {
		return Localite.class;
	}
	
	@Override
	protected List<Localite> load() {
		return new ArrayList<>(localiteService.findAll());
	}

}
