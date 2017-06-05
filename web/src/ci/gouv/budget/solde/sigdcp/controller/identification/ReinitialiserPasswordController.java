package ci.gouv.budget.solde.sigdcp.controller.identification;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import ci.gouv.budget.solde.sigdcp.model.utils.validation.groups.Client;
import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped
public class ReinitialiserPasswordController extends AbstractDeverouillerCompteUtilisateurController implements Serializable {
 
	private static final long serialVersionUID = 6591392098578555259L;
	
	/*
	 * Dtos
	 */
	@NotNull(groups=Client.class)
	@Getter @Setter private String confirmationMotPasse;
	
	@Override
	protected void initialisation() {
		System.out.println("ReinitialiserPasswordController.initialisation()");
		super.initialisation();
	}
	
}
