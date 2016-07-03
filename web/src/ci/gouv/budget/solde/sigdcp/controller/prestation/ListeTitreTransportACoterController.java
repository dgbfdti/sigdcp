package ci.gouv.budget.solde.sigdcp.controller.prestation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import ci.gouv.budget.solde.sigdcp.controller.ui.AbstractEntityListUIController;
import ci.gouv.budget.solde.sigdcp.model.Code;
import ci.gouv.budget.solde.sigdcp.model.prestation.DemandeCotationTitreTransport;
import ci.gouv.budget.solde.sigdcp.service.prestation.DemandeCotationTitreTransportService;

@Named @ViewScoped
public class ListeTitreTransportACoterController extends AbstractEntityListUIController<DemandeCotationTitreTransport> implements Serializable {

	private static final long serialVersionUID = -2520806200426979462L;
	
	/*
	 * Services
	 */
	
	@Inject protected DemandeCotationTitreTransportService demandeCotationTitreTransportService;
	/*
	 * Dto
	 */
	@Getter @Setter protected Boolean showMontant=Boolean.TRUE,showObservation=Boolean.FALSE,
			showValidation=Boolean.FALSE,showDateCreation=Boolean.TRUE;
	@Override
	protected void initialisation() {
		super.initialisation();
		title="Demande de cotation des titres de transport";
		listTitle="Liste des demandes des titres de transports";
		selectLabel = "bouton.selectionner";
		defaultSubmitCommand.setRendered(false);
	}

	@Override
	protected List<DemandeCotationTitreTransport> load() {
		return (List<DemandeCotationTitreTransport>) demandeCotationTitreTransportService.findATraiter(Code.NATURE_OPERATION_DEMANDE_COTATION);
	}
	
	@Override
	protected String identifierFieldName() {
		return "dossierId";
	}

	
	/*public String href(DemandeCotationTitreTransport DemandeCotationTitreTransport){
		return navigationManager.url("demande_cotation_fo_form", new Object[]{webConstantResources.getRequestParamDossier(),DemandeCotationTitreTransport.getDossier().getId(),webConstantResources.getRequestParamTabMenuItemIndex(), userSessionManager.getUiMenuTabPrestationCotationFoIndex()},false);
	}*/
	
	@Override
	protected void hrefParameters(Map<String, Object> parameters, DemandeCotationTitreTransport demandeCotationTitreTransport) {		
		super.hrefParameters(parameters, demandeCotationTitreTransport);
		parameters.put(webConstantResources.getRequestParamDossier(), demandeCotationTitreTransport.getDossierId());
		parameters.put(webConstantResources.getRequestParamTabMenuItemIndex(), userSessionManager.getUiMenuTabPrestationCotationTTIndex());
	}
	
	@Override
	protected ProcessingType getProcessingType() {
		return ProcessingType.SINGLE;
	}
	
	

}
