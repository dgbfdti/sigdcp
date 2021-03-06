package ci.gouv.budget.solde.sigdcp.controller.reference;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ci.gouv.budget.solde.sigdcp.model.indemnite.GroupeMission;

@Named @ViewScoped
public class GroupemhciReferenceController extends AbstractDynaEnumReferenceController<GroupeMission> implements Serializable {

	private static final long serialVersionUID = -1061481987684469949L;
	
	@Override
	protected String nomEntite() {
		return "Groupe de mission";
	}

	@Override
	protected Class<GroupeMission> clazz() {
		return GroupeMission.class;
	}

}
