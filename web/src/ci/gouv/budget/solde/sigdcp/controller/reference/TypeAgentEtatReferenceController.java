package ci.gouv.budget.solde.sigdcp.controller.reference;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ci.gouv.budget.solde.sigdcp.model.identification.TypeAgentEtat;

@Named @ViewScoped
public class TypeAgentEtatReferenceController extends AbstractDynaEnumReferenceController<TypeAgentEtat> implements Serializable {

	private static final long serialVersionUID = -1061481987684469949L;
	
	@Override
	protected String nomEntite() {
		return "Type agent état";
	}

	@Override
	protected Class<TypeAgentEtat> clazz() {
		return TypeAgentEtat.class;
	}

}
