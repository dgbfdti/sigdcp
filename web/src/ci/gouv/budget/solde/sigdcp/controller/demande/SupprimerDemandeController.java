package ci.gouv.budget.solde.sigdcp.controller.demande;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import ci.gouv.budget.solde.sigdcp.controller.ui.form.AbstractEntityFormUIController;
import ci.gouv.budget.solde.sigdcp.model.dossier.Dossier;
import ci.gouv.budget.solde.sigdcp.service.dossier.DossierService;

@Named @ViewScoped @Getter @Setter
public class SupprimerDemandeController extends AbstractEntityFormUIController<Dossier> implements Serializable {

	private static final long serialVersionUID = -8067038151558697675L;

	private String numero;
	private DossierService dossierService;
	
	@Override
	protected void initialisation() {
		super.initialisation();
		defaultSubmitCommand.setValue(text("bouton.sampledatacreate"));
		defaultSubmitCommand.setNotificationMessageId("notification.sampledata.created");
		defaultSubmitCommand.onSuccessStayOnCurrentView();
		title="Supprimer un dossier";
	} 
	
	@Override
	protected void onDefaultSubmitAction() throws Exception {
		//dossierService.deleteByNumero(numero);
	}
	
	/*@Override
	public CRUDType getCrudType() {
		return CRUDType.READ;
	}*/
	
}
