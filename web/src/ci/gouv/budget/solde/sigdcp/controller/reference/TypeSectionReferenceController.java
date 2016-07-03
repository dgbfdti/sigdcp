package ci.gouv.budget.solde.sigdcp.controller.reference;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ci.gouv.budget.solde.sigdcp.model.identification.TypeSection;

@Named @ViewScoped
public class TypeSectionReferenceController extends AbstractDynaEnumReferenceController<TypeSection> implements Serializable {

	private static final long serialVersionUID = -1061481987684469949L;
	
	@Override
	protected String nomEntite() {
		return "Type service";
	}

	@Override
	protected Class<TypeSection> clazz() {
		return TypeSection.class;
	}

}
