package ci.gouv.budget.solde.sigdcp.controller.reference;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ci.gouv.budget.solde.sigdcp.model.identification.Section;

@Named @ViewScoped
public class SectionReferenceController extends AbstractDynaEnumReferenceController<Section> implements Serializable {

	private static final long serialVersionUID = -1061481987684469949L;
	
	@Override
	protected String nomEntite() {
		return "Service";
	}

	@Override
	protected Class<Section> clazz() {
		return Section.class;
	}

}
